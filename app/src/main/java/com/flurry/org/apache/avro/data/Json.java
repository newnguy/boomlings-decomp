package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.ResolvingDecoder;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.BooleanNode;
import com.flurry.org.codehaus.jackson.node.DoubleNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.LongNode;
import com.flurry.org.codehaus.jackson.node.NullNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.node.TextNode;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Json {
    public static final Schema SCHEMA;

    /* loaded from: classes.dex */
    public enum JsonType {
        LONG,
        DOUBLE,
        STRING,
        BOOLEAN,
        NULL,
        ARRAY,
        OBJECT
    }

    /* loaded from: classes.dex */
    public class Reader implements DatumReader {
        private ResolvingDecoder resolver;
        private Schema written;

        @Override // com.flurry.org.apache.avro.io.DatumReader
        public JsonNode read(JsonNode jsonNode, Decoder decoder) {
            if (this.written == null) {
                return Json.read(decoder);
            }
            if (this.resolver == null) {
                this.resolver = DecoderFactory.get().resolvingDecoder(this.written, Json.SCHEMA, null);
            }
            this.resolver.configure(decoder);
            JsonNode read = Json.read(this.resolver);
            this.resolver.drain();
            return read;
        }

        @Override // com.flurry.org.apache.avro.io.DatumReader
        public void setSchema(Schema schema) {
            if (Json.SCHEMA.equals(this.written)) {
                schema = null;
            }
            this.written = schema;
        }
    }

    /* loaded from: classes.dex */
    public class Writer implements DatumWriter {
        @Override // com.flurry.org.apache.avro.io.DatumWriter
        public void setSchema(Schema schema) {
            if (!Json.SCHEMA.equals(schema)) {
                throw new RuntimeException("Not the Json schema: " + schema);
            }
        }

        @Override // com.flurry.org.apache.avro.io.DatumWriter
        public void write(JsonNode jsonNode, Encoder encoder) {
            Json.write(jsonNode, encoder);
        }
    }

    static {
        try {
            SCHEMA = Schema.parse(Json.class.getResourceAsStream("/com/flurry/org/apache/avro/data/Json.avsc"));
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    private Json() {
    }

    public static JsonNode read(Decoder decoder) {
        switch (JsonType.values()[decoder.readIndex()]) {
            case LONG:
                return new LongNode(decoder.readLong());
            case DOUBLE:
                return new DoubleNode(decoder.readDouble());
            case STRING:
                return new TextNode(decoder.readString());
            case BOOLEAN:
                return decoder.readBoolean() ? BooleanNode.TRUE : BooleanNode.FALSE;
            case NULL:
                decoder.readNull();
                return NullNode.getInstance();
            case ARRAY:
                ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
                long readArrayStart = decoder.readArrayStart();
                while (true) {
                    long j = readArrayStart;
                    if (j <= 0) {
                        return arrayNode;
                    }
                    for (long j2 = 0; j2 < j; j2++) {
                        arrayNode.add(read(decoder));
                    }
                    readArrayStart = decoder.arrayNext();
                }
            case OBJECT:
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                long readMapStart = decoder.readMapStart();
                while (true) {
                    long j3 = readMapStart;
                    if (j3 <= 0) {
                        return objectNode;
                    }
                    for (long j4 = 0; j4 < j3; j4++) {
                        objectNode.put(decoder.readString(), read(decoder));
                    }
                    readMapStart = decoder.mapNext();
                }
            default:
                throw new AvroRuntimeException("Unexpected Json node type");
        }
    }

    public static void write(JsonNode jsonNode, Encoder encoder) {
        switch (jsonNode.asToken()) {
            case VALUE_NUMBER_INT:
                encoder.writeIndex(JsonType.LONG.ordinal());
                encoder.writeLong(jsonNode.getLongValue());
                return;
            case VALUE_NUMBER_FLOAT:
                encoder.writeIndex(JsonType.DOUBLE.ordinal());
                encoder.writeDouble(jsonNode.getDoubleValue());
                return;
            case VALUE_STRING:
                encoder.writeIndex(JsonType.STRING.ordinal());
                encoder.writeString(jsonNode.getTextValue());
                return;
            case VALUE_TRUE:
                encoder.writeIndex(JsonType.BOOLEAN.ordinal());
                encoder.writeBoolean(true);
                return;
            case VALUE_FALSE:
                encoder.writeIndex(JsonType.BOOLEAN.ordinal());
                encoder.writeBoolean(false);
                return;
            case VALUE_NULL:
                encoder.writeIndex(JsonType.NULL.ordinal());
                encoder.writeNull();
                return;
            case START_ARRAY:
                encoder.writeIndex(JsonType.ARRAY.ordinal());
                encoder.writeArrayStart();
                encoder.setItemCount(jsonNode.size());
                Iterator it = jsonNode.iterator();
                while (it.hasNext()) {
                    encoder.startItem();
                    write((JsonNode) it.next(), encoder);
                }
                encoder.writeArrayEnd();
                return;
            case START_OBJECT:
                encoder.writeIndex(JsonType.OBJECT.ordinal());
                encoder.writeMapStart();
                encoder.setItemCount(jsonNode.size());
                Iterator fieldNames = jsonNode.getFieldNames();
                while (fieldNames.hasNext()) {
                    encoder.startItem();
                    String str = (String) fieldNames.next();
                    encoder.writeString(str);
                    write(jsonNode.get(str), encoder);
                }
                encoder.writeMapEnd();
                return;
            default:
                throw new AvroRuntimeException(jsonNode.asToken() + " unexpected: " + jsonNode);
        }
    }
}
