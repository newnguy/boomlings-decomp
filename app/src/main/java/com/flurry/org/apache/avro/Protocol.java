package com.flurry.org.apache.avro;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class Protocol {
    private static final Set MESSAGE_RESERVED = new HashSet();
    private static final Set PROTOCOL_RESERVED;
    public static final Schema SYSTEM_ERROR;
    public static final Schema SYSTEM_ERRORS;
    public static final long VERSION = 1;
    private String doc;
    private byte[] md5;
    private Map messages;
    private String name;
    private String namespace;
    Schema.Props props;
    private Schema.Names types;

    /* loaded from: classes.dex */
    public class Message {
        private String doc;
        private String name;
        private final Schema.Props props;
        private Schema request;

        private Message(String str, String str2, Map map, Schema schema) {
            Protocol.this = r4;
            this.props = new Schema.Props(Protocol.MESSAGE_RESERVED);
            this.name = str;
            this.doc = str2;
            this.request = schema;
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    addProp((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }

        public synchronized void addProp(String str, String str2) {
            this.props.add(str, str2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Message) {
                Message message = (Message) obj;
                return this.name.equals(message.name) && this.request.equals(message.request) && this.props.equals(message.props);
            }
            return false;
        }

        public String getDoc() {
            return this.doc;
        }

        public Schema getErrors() {
            return Schema.createUnion(new ArrayList());
        }

        public String getName() {
            return this.name;
        }

        public synchronized String getProp(String str) {
            return (String) this.props.get(str);
        }

        public Map getProps() {
            return Collections.unmodifiableMap(this.props);
        }

        public Schema getRequest() {
            return this.request;
        }

        public Schema getResponse() {
            return Schema.create(Schema.Type.NULL);
        }

        public int hashCode() {
            return this.name.hashCode() + this.request.hashCode() + this.props.hashCode();
        }

        public boolean isOneWay() {
            return true;
        }

        void toJson(JsonGenerator jsonGenerator) {
            jsonGenerator.writeStartObject();
            if (this.doc != null) {
                jsonGenerator.writeStringField("doc", this.doc);
            }
            this.props.write(jsonGenerator);
            jsonGenerator.writeFieldName("request");
            this.request.fieldsToJson(Protocol.this.types, jsonGenerator);
            toJson1(jsonGenerator);
            jsonGenerator.writeEndObject();
        }

        void toJson1(JsonGenerator jsonGenerator) {
            jsonGenerator.writeStringField("response", DataFileConstants.NULL_CODEC);
            jsonGenerator.writeBooleanField("one-way", true);
        }

        public String toString() {
            try {
                StringWriter stringWriter = new StringWriter();
                JsonGenerator createJsonGenerator = Schema.FACTORY.createJsonGenerator(stringWriter);
                toJson(createJsonGenerator);
                createJsonGenerator.flush();
                return stringWriter.toString();
            } catch (IOException e) {
                throw new AvroRuntimeException(e);
            }
        }
    }

    /* loaded from: classes.dex */
    public class TwoWayMessage extends Message {
        private Schema errors;
        private Schema response;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private TwoWayMessage(String str, String str2, Map map, Schema schema, Schema schema2, Schema schema3) {
            super(str, str2, map, schema);
            Protocol.this = r8;
            this.response = schema2;
            this.errors = schema3;
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        public boolean equals(Object obj) {
            if (super.equals(obj) && (obj instanceof TwoWayMessage)) {
                TwoWayMessage twoWayMessage = (TwoWayMessage) obj;
                return this.response.equals(twoWayMessage.response) && this.errors.equals(twoWayMessage.errors);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        public Schema getErrors() {
            return this.errors;
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        public Schema getResponse() {
            return this.response;
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        public int hashCode() {
            return super.hashCode() + this.response.hashCode() + this.errors.hashCode();
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        public boolean isOneWay() {
            return false;
        }

        @Override // com.flurry.org.apache.avro.Protocol.Message
        void toJson1(JsonGenerator jsonGenerator) {
            jsonGenerator.writeFieldName("response");
            this.response.toJson(Protocol.this.types, jsonGenerator);
            List types = this.errors.getTypes();
            if (types.size() > 1) {
                Schema createUnion = Schema.createUnion(types.subList(1, types.size()));
                jsonGenerator.writeFieldName("errors");
                createUnion.toJson(Protocol.this.types, jsonGenerator);
            }
        }
    }

    static {
        Collections.addAll(MESSAGE_RESERVED, "doc", "response", "request", "errors", "one-way");
        SYSTEM_ERROR = Schema.create(Schema.Type.STRING);
        ArrayList arrayList = new ArrayList();
        arrayList.add(SYSTEM_ERROR);
        SYSTEM_ERRORS = Schema.createUnion(arrayList);
        PROTOCOL_RESERVED = new HashSet();
        Collections.addAll(PROTOCOL_RESERVED, "namespace", "protocol", "doc", "messages", "types", "errors");
    }

    private Protocol() {
        this.types = new Schema.Names();
        this.messages = new LinkedHashMap();
        this.props = new Schema.Props(PROTOCOL_RESERVED);
    }

    public Protocol(String str, String str2) {
        this(str, null, str2);
    }

    public Protocol(String str, String str2, String str3) {
        this.types = new Schema.Names();
        this.messages = new LinkedHashMap();
        this.props = new Schema.Props(PROTOCOL_RESERVED);
        this.name = str;
        this.doc = str2;
        this.namespace = str3;
    }

    public static void main(String[] strArr) {
        System.out.println(parse(new File(strArr[0])));
    }

    private static Protocol parse(JsonParser jsonParser) {
        try {
            Protocol protocol = new Protocol();
            protocol.parse(Schema.MAPPER.readTree(jsonParser));
            return protocol;
        } catch (IOException e) {
            throw new SchemaParseException(e);
        }
    }

    public static Protocol parse(File file) {
        return parse(Schema.FACTORY.createJsonParser(file));
    }

    public static Protocol parse(InputStream inputStream) {
        return parse(Schema.FACTORY.createJsonParser(inputStream));
    }

    public static Protocol parse(String str) {
        try {
            return parse(Schema.FACTORY.createJsonParser(new ByteArrayInputStream(str.getBytes("UTF-8"))));
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    private void parse(JsonNode jsonNode) {
        parseNamespace(jsonNode);
        parseName(jsonNode);
        parseTypes(jsonNode);
        parseMessages(jsonNode);
        parseDoc(jsonNode);
        parseProps(jsonNode);
    }

    private void parseDoc(JsonNode jsonNode) {
        this.doc = parseDocNode(jsonNode);
    }

    private String parseDocNode(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("doc");
        if (jsonNode2 == null) {
            return null;
        }
        return jsonNode2.getTextValue();
    }

    private Message parseMessage(String str, JsonNode jsonNode) {
        String parseDocNode = parseDocNode(jsonNode);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator fieldNames = jsonNode.getFieldNames();
        while (fieldNames.hasNext()) {
            String str2 = (String) fieldNames.next();
            if (!MESSAGE_RESERVED.contains(str2)) {
                JsonNode jsonNode2 = jsonNode.get(str2);
                if (jsonNode2.isValueNode() && jsonNode2.isTextual()) {
                    linkedHashMap.put(str2, jsonNode2.getTextValue());
                }
            }
        }
        JsonNode jsonNode3 = jsonNode.get("request");
        if (jsonNode3 == null || !jsonNode3.isArray()) {
            throw new SchemaParseException("No request specified: " + jsonNode);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = jsonNode3.iterator();
        while (it.hasNext()) {
            JsonNode jsonNode4 = (JsonNode) it.next();
            JsonNode jsonNode5 = jsonNode4.get("name");
            if (jsonNode5 == null) {
                throw new SchemaParseException("No param name: " + jsonNode4);
            }
            JsonNode jsonNode6 = jsonNode4.get("type");
            if (jsonNode6 == null) {
                throw new SchemaParseException("No param type: " + jsonNode4);
            }
            arrayList.add(new Schema.Field(jsonNode5.getTextValue(), Schema.parse(jsonNode6, this.types), null, jsonNode4.get("default")));
        }
        Schema createRecord = Schema.createRecord(arrayList);
        boolean z = false;
        JsonNode jsonNode7 = jsonNode.get("one-way");
        if (jsonNode7 != null) {
            if (!jsonNode7.isBoolean()) {
                throw new SchemaParseException("one-way must be boolean: " + jsonNode);
            }
            z = jsonNode7.getBooleanValue();
        }
        JsonNode jsonNode8 = jsonNode.get("response");
        if (z || jsonNode8 != null) {
            JsonNode jsonNode9 = jsonNode.get("errors");
            if (z) {
                if (jsonNode9 != null) {
                    throw new SchemaParseException("one-way can't have errors: " + jsonNode);
                }
                if (jsonNode8 == null || Schema.parse(jsonNode8, this.types).getType() == Schema.Type.NULL) {
                    return new Message(str, parseDocNode, linkedHashMap, createRecord);
                }
                throw new SchemaParseException("One way response must be null: " + jsonNode);
            }
            Schema parse = Schema.parse(jsonNode8, this.types);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(SYSTEM_ERROR);
            if (jsonNode9 != null) {
                if (!jsonNode9.isArray()) {
                    throw new SchemaParseException("Errors not an array: " + jsonNode);
                }
                Iterator it2 = jsonNode9.iterator();
                while (it2.hasNext()) {
                    String textValue = ((JsonNode) it2.next()).getTextValue();
                    Schema schema = this.types.get((Object) textValue);
                    if (schema == null) {
                        throw new SchemaParseException("Undefined error: " + textValue);
                    }
                    if (!schema.isError()) {
                        throw new SchemaParseException("Not an error: " + textValue);
                    }
                    arrayList2.add(schema);
                }
            }
            return new TwoWayMessage(str, parseDocNode, linkedHashMap, createRecord, parse, Schema.createUnion(arrayList2));
        }
        throw new SchemaParseException("No response specified: " + jsonNode);
    }

    private void parseMessages(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("messages");
        if (jsonNode2 == null) {
            return;
        }
        Iterator fieldNames = jsonNode2.getFieldNames();
        while (fieldNames.hasNext()) {
            String str = (String) fieldNames.next();
            this.messages.put(str, parseMessage(str, jsonNode2.get(str)));
        }
    }

    private void parseName(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("protocol");
        if (jsonNode2 == null) {
            throw new SchemaParseException("No protocol name specified: " + jsonNode);
        }
        this.name = jsonNode2.getTextValue();
    }

    private void parseNamespace(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("namespace");
        if (jsonNode2 == null) {
            return;
        }
        this.namespace = jsonNode2.getTextValue();
        this.types.space(this.namespace);
    }

    private void parseProps(JsonNode jsonNode) {
        Iterator fieldNames = jsonNode.getFieldNames();
        while (fieldNames.hasNext()) {
            String str = (String) fieldNames.next();
            if (!PROTOCOL_RESERVED.contains(str)) {
                JsonNode jsonNode2 = jsonNode.get(str);
                if (jsonNode2.isValueNode() && jsonNode2.isTextual()) {
                    addProp(str, jsonNode2.getTextValue());
                }
            }
        }
    }

    private void parseTypes(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("types");
        if (jsonNode2 == null) {
            return;
        }
        if (!jsonNode2.isArray()) {
            throw new SchemaParseException("Types not an array: " + jsonNode2);
        }
        Iterator it = jsonNode2.iterator();
        while (it.hasNext()) {
            JsonNode jsonNode3 = (JsonNode) it.next();
            if (!jsonNode3.isObject()) {
                throw new SchemaParseException("Type not an object: " + jsonNode3);
            }
            Schema.parse(jsonNode3, this.types);
        }
    }

    public synchronized void addProp(String str, String str2) {
        this.props.add(str, str2);
    }

    @Deprecated
    public Message createMessage(String str, String str2, Schema schema) {
        return createMessage(str, str2, new LinkedHashMap(), schema);
    }

    @Deprecated
    public Message createMessage(String str, String str2, Schema schema, Schema schema2, Schema schema3) {
        return createMessage(str, str2, new LinkedHashMap(), schema, schema2, schema3);
    }

    public Message createMessage(String str, String str2, Map map, Schema schema) {
        return new Message(str, str2, map, schema);
    }

    public Message createMessage(String str, String str2, Map map, Schema schema, Schema schema2, Schema schema3) {
        return new TwoWayMessage(str, str2, map, schema, schema2, schema3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Protocol) {
            Protocol protocol = (Protocol) obj;
            return this.name.equals(protocol.name) && this.namespace.equals(protocol.namespace) && this.types.equals(protocol.types) && this.messages.equals(protocol.messages) && this.props.equals(this.props);
        }
        return false;
    }

    public String getDoc() {
        return this.doc;
    }

    public byte[] getMD5() {
        if (this.md5 == null) {
            try {
                this.md5 = MessageDigest.getInstance("MD5").digest(toString().getBytes("UTF-8"));
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }
        return this.md5;
    }

    public Map getMessages() {
        return this.messages;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public synchronized String getProp(String str) {
        return (String) this.props.get(str);
    }

    public Map getProps() {
        return Collections.unmodifiableMap(this.props);
    }

    public Schema getType(String str) {
        return this.types.get((Object) str);
    }

    public Collection getTypes() {
        return this.types.values();
    }

    public int hashCode() {
        return this.name.hashCode() + this.namespace.hashCode() + this.types.hashCode() + this.messages.hashCode() + this.props.hashCode();
    }

    public void setTypes(Collection collection) {
        this.types = new Schema.Names();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            this.types.add((Schema) it.next());
        }
    }

    void toJson(JsonGenerator jsonGenerator) {
        this.types.space(this.namespace);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("protocol", this.name);
        jsonGenerator.writeStringField("namespace", this.namespace);
        if (this.doc != null) {
            jsonGenerator.writeStringField("doc", this.doc);
        }
        this.props.write(jsonGenerator);
        jsonGenerator.writeArrayFieldStart("types");
        Schema.Names names = new Schema.Names(this.namespace);
        for (Schema schema : this.types.values()) {
            if (!names.contains(schema)) {
                schema.toJson(names, jsonGenerator);
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeObjectFieldStart("messages");
        for (Map.Entry entry : this.messages.entrySet()) {
            jsonGenerator.writeFieldName((String) entry.getKey());
            ((Message) entry.getValue()).toJson(jsonGenerator);
        }
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean z) {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonGenerator createJsonGenerator = Schema.FACTORY.createJsonGenerator(stringWriter);
            if (z) {
                createJsonGenerator.useDefaultPrettyPrinter();
            }
            toJson(createJsonGenerator);
            createJsonGenerator.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }
}
