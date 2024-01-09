package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonFactory;
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
import java.util.Map;
import java.util.Set;

public class Protocol {
  private static final Set MESSAGE_RESERVED = new HashSet();
  
  private static final Set PROTOCOL_RESERVED;
  
  public static final Schema SYSTEM_ERROR = Schema.create(Schema$Type.STRING);
  
  public static final Schema SYSTEM_ERRORS;
  
  public static final long VERSION = 1L;
  
  private String doc;
  
  private byte[] md5;
  
  private Map messages = new LinkedHashMap<Object, Object>();
  
  private String name;
  
  private String namespace;
  
  Schema$Props props = new Schema$Props(PROTOCOL_RESERVED);
  
  private Schema$Names types = new Schema$Names();
  
  static {
    ArrayList<Schema> arrayList = new ArrayList();
    arrayList.add(SYSTEM_ERROR);
    SYSTEM_ERRORS = Schema.createUnion(arrayList);
    PROTOCOL_RESERVED = new HashSet();
    Collections.addAll(PROTOCOL_RESERVED, new String[] { "namespace", "protocol", "doc", "messages", "types", "errors" });
  }
  
  private Protocol() {}
  
  public Protocol(String paramString1, String paramString2) {
    this(paramString1, null, paramString2);
  }
  
  public Protocol(String paramString1, String paramString2, String paramString3) {
    this.name = paramString1;
    this.doc = paramString2;
    this.namespace = paramString3;
  }
  
  public static void main(String[] paramArrayOfString) {
    System.out.println(parse(new File(paramArrayOfString[0])));
  }
  
  private static Protocol parse(JsonParser paramJsonParser) {
    try {
      Protocol protocol = new Protocol();
      this();
      protocol.parse(Schema.MAPPER.readTree(paramJsonParser));
      return protocol;
    } catch (IOException iOException) {
      throw new SchemaParseException(iOException);
    } 
  }
  
  public static Protocol parse(File paramFile) {
    return parse(Schema.FACTORY.createJsonParser(paramFile));
  }
  
  public static Protocol parse(InputStream paramInputStream) {
    return parse(Schema.FACTORY.createJsonParser(paramInputStream));
  }
  
  public static Protocol parse(String paramString) {
    try {
      JsonFactory jsonFactory = Schema.FACTORY;
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
      this(paramString.getBytes("UTF-8"));
      return parse(jsonFactory.createJsonParser(byteArrayInputStream));
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  private void parse(JsonNode paramJsonNode) {
    parseNamespace(paramJsonNode);
    parseName(paramJsonNode);
    parseTypes(paramJsonNode);
    parseMessages(paramJsonNode);
    parseDoc(paramJsonNode);
    parseProps(paramJsonNode);
  }
  
  private void parseDoc(JsonNode paramJsonNode) {
    this.doc = parseDocNode(paramJsonNode);
  }
  
  private String parseDocNode(JsonNode paramJsonNode) {
    paramJsonNode = paramJsonNode.get("doc");
    return (paramJsonNode == null) ? null : paramJsonNode.getTextValue();
  }
  
  private Protocol$Message parseMessage(String paramString, JsonNode paramJsonNode) {
    String str = parseDocNode(paramJsonNode);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    Iterator<String> iterator = paramJsonNode.getFieldNames();
    while (iterator.hasNext()) {
      String str1 = iterator.next();
      if (!MESSAGE_RESERVED.contains(str1)) {
        JsonNode jsonNode = paramJsonNode.get(str1);
        if (jsonNode.isValueNode() && jsonNode.isTextual())
          linkedHashMap.put(str1, jsonNode.getTextValue()); 
      } 
    } 
    JsonNode jsonNode1 = paramJsonNode.get("request");
    if (jsonNode1 == null || !jsonNode1.isArray())
      throw new SchemaParseException("No request specified: " + paramJsonNode); 
    ArrayList<Schema$Field> arrayList = new ArrayList();
    for (JsonNode jsonNode3 : jsonNode1) {
      jsonNode1 = jsonNode3.get("name");
      if (jsonNode1 == null)
        throw new SchemaParseException("No param name: " + jsonNode3); 
      JsonNode jsonNode4 = jsonNode3.get("type");
      if (jsonNode4 == null)
        throw new SchemaParseException("No param type: " + jsonNode3); 
      arrayList.add(new Schema$Field(jsonNode1.getTextValue(), Schema.parse(jsonNode4, this.types), null, jsonNode3.get("default")));
    } 
    Schema schema1 = Schema.createRecord(arrayList);
    boolean bool = false;
    jsonNode1 = paramJsonNode.get("one-way");
    if (jsonNode1 != null) {
      if (!jsonNode1.isBoolean())
        throw new SchemaParseException("one-way must be boolean: " + paramJsonNode); 
      bool = jsonNode1.getBooleanValue();
    } 
    jsonNode1 = paramJsonNode.get("response");
    if (!bool && jsonNode1 == null)
      throw new SchemaParseException("No response specified: " + paramJsonNode); 
    JsonNode jsonNode2 = paramJsonNode.get("errors");
    if (bool) {
      if (jsonNode2 != null)
        throw new SchemaParseException("one-way can't have errors: " + paramJsonNode); 
      if (jsonNode1 != null && Schema.parse(jsonNode1, this.types).getType() != Schema$Type.NULL)
        throw new SchemaParseException("One way response must be null: " + paramJsonNode); 
      return new Protocol$Message(this, paramString, str, linkedHashMap, schema1, null);
    } 
    Schema schema2 = Schema.parse(jsonNode1, this.types);
    ArrayList<Schema> arrayList1 = new ArrayList();
    arrayList1.add(SYSTEM_ERROR);
    if (jsonNode2 != null) {
      if (!jsonNode2.isArray())
        throw new SchemaParseException("Errors not an array: " + paramJsonNode); 
      Iterator<JsonNode> iterator1 = jsonNode2.iterator();
      while (iterator1.hasNext()) {
        String str1 = ((JsonNode)iterator1.next()).getTextValue();
        Schema schema = this.types.get(str1);
        if (schema == null)
          throw new SchemaParseException("Undefined error: " + str1); 
        if (!schema.isError())
          throw new SchemaParseException("Not an error: " + str1); 
        arrayList1.add(schema);
      } 
    } 
    return new Protocol$TwoWayMessage(this, paramString, str, linkedHashMap, schema1, schema2, Schema.createUnion(arrayList1), null);
  }
  
  private void parseMessages(JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode.get("messages");
    if (jsonNode != null) {
      Iterator<String> iterator = jsonNode.getFieldNames();
      while (true) {
        if (iterator.hasNext()) {
          String str = iterator.next();
          this.messages.put(str, parseMessage(str, jsonNode.get(str)));
          continue;
        } 
        return;
      } 
    } 
  }
  
  private void parseName(JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode.get("protocol");
    if (jsonNode == null)
      throw new SchemaParseException("No protocol name specified: " + paramJsonNode); 
    this.name = jsonNode.getTextValue();
  }
  
  private void parseNamespace(JsonNode paramJsonNode) {
    paramJsonNode = paramJsonNode.get("namespace");
    if (paramJsonNode != null) {
      this.namespace = paramJsonNode.getTextValue();
      this.types.space(this.namespace);
    } 
  }
  
  private void parseProps(JsonNode paramJsonNode) {
    Iterator<String> iterator = paramJsonNode.getFieldNames();
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (!PROTOCOL_RESERVED.contains(str)) {
        JsonNode jsonNode = paramJsonNode.get(str);
        if (jsonNode.isValueNode() && jsonNode.isTextual())
          addProp(str, jsonNode.getTextValue()); 
      } 
    } 
  }
  
  private void parseTypes(JsonNode paramJsonNode) {
    paramJsonNode = paramJsonNode.get("types");
    if (paramJsonNode != null) {
      if (!paramJsonNode.isArray())
        throw new SchemaParseException("Types not an array: " + paramJsonNode); 
      Iterator<JsonNode> iterator = paramJsonNode.iterator();
      while (true) {
        if (iterator.hasNext()) {
          JsonNode jsonNode = iterator.next();
          if (!jsonNode.isObject())
            throw new SchemaParseException("Type not an object: " + jsonNode); 
          Schema.parse(jsonNode, this.types);
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void addProp(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: aload_2
    //   8: invokevirtual add : (Ljava/lang/String;Ljava/lang/String;)V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	finally
  }
  
  @Deprecated
  public Protocol$Message createMessage(String paramString1, String paramString2, Schema paramSchema) {
    return createMessage(paramString1, paramString2, new LinkedHashMap<Object, Object>(), paramSchema);
  }
  
  @Deprecated
  public Protocol$Message createMessage(String paramString1, String paramString2, Schema paramSchema1, Schema paramSchema2, Schema paramSchema3) {
    return createMessage(paramString1, paramString2, new LinkedHashMap<Object, Object>(), paramSchema1, paramSchema2, paramSchema3);
  }
  
  public Protocol$Message createMessage(String paramString1, String paramString2, Map paramMap, Schema paramSchema) {
    return new Protocol$Message(this, paramString1, paramString2, paramMap, paramSchema, null);
  }
  
  public Protocol$Message createMessage(String paramString1, String paramString2, Map paramMap, Schema paramSchema1, Schema paramSchema2, Schema paramSchema3) {
    return new Protocol$TwoWayMessage(this, paramString1, paramString2, paramMap, paramSchema1, paramSchema2, paramSchema3, null);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Protocol))
        return false; 
      paramObject = paramObject;
      if (!this.name.equals(((Protocol)paramObject).name) || !this.namespace.equals(((Protocol)paramObject).namespace) || !this.types.equals(((Protocol)paramObject).types) || !this.messages.equals(((Protocol)paramObject).messages) || !this.props.equals(this.props))
        bool = false; 
    } 
    return bool;
  }
  
  public String getDoc() {
    return this.doc;
  }
  
  public byte[] getMD5() {
    if (this.md5 == null)
      try {
        this.md5 = MessageDigest.getInstance("MD5").digest(toString().getBytes("UTF-8"));
        return this.md5;
      } catch (Exception exception) {
        throw new AvroRuntimeException(exception);
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
  
  public String getProp(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast java/lang/String
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	18	finally
  }
  
  public Map getProps() {
    return Collections.unmodifiableMap(this.props);
  }
  
  public Schema getType(String paramString) {
    return this.types.get(paramString);
  }
  
  public Collection getTypes() {
    return this.types.values();
  }
  
  public int hashCode() {
    return this.name.hashCode() + this.namespace.hashCode() + this.types.hashCode() + this.messages.hashCode() + this.props.hashCode();
  }
  
  public void setTypes(Collection paramCollection) {
    this.types = new Schema$Names();
    for (Schema schema : paramCollection)
      this.types.add(schema); 
  }
  
  void toJson(JsonGenerator paramJsonGenerator) {
    this.types.space(this.namespace);
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField("protocol", this.name);
    paramJsonGenerator.writeStringField("namespace", this.namespace);
    if (this.doc != null)
      paramJsonGenerator.writeStringField("doc", this.doc); 
    this.props.write(paramJsonGenerator);
    paramJsonGenerator.writeArrayFieldStart("types");
    Schema$Names schema$Names = new Schema$Names(this.namespace);
    for (Schema schema : this.types.values()) {
      if (!schema$Names.contains(schema))
        schema.toJson(schema$Names, paramJsonGenerator); 
    } 
    paramJsonGenerator.writeEndArray();
    paramJsonGenerator.writeObjectFieldStart("messages");
    for (Map.Entry entry : this.messages.entrySet()) {
      paramJsonGenerator.writeFieldName((String)entry.getKey());
      ((Protocol$Message)entry.getValue()).toJson(paramJsonGenerator);
    } 
    paramJsonGenerator.writeEndObject();
    paramJsonGenerator.writeEndObject();
  }
  
  public String toString() {
    return toString(false);
  }
  
  public String toString(boolean paramBoolean) {
    try {
      StringWriter stringWriter = new StringWriter();
      this();
      JsonGenerator jsonGenerator = Schema.FACTORY.createJsonGenerator(stringWriter);
      if (paramBoolean)
        jsonGenerator.useDefaultPrettyPrinter(); 
      toJson(jsonGenerator);
      jsonGenerator.flush();
      return stringWriter.toString();
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  static {
    Collections.addAll(MESSAGE_RESERVED, new String[] { "doc", "response", "request", "errors", "one-way" });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Protocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */