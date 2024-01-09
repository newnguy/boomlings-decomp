package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Protocol$Message {
  private String doc;
  
  private String name;
  
  private final Schema$Props props = new Schema$Props(Protocol.access$000());
  
  private Schema request;
  
  final Protocol this$0;
  
  private Protocol$Message(String paramString1, String paramString2, Map paramMap, Schema paramSchema) {
    this.name = paramString1;
    this.doc = paramString2;
    this.request = paramSchema;
    if (paramMap != null)
      for (Map.Entry entry : paramMap.entrySet())
        addProp((String)entry.getKey(), (String)entry.getValue());  
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
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Protocol$Message))
        return false; 
      paramObject = paramObject;
      if (!this.name.equals(((Protocol$Message)paramObject).name) || !this.request.equals(((Protocol$Message)paramObject).request) || !this.props.equals(((Protocol$Message)paramObject).props))
        bool = false; 
    } 
    return bool;
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
  
  public Schema getRequest() {
    return this.request;
  }
  
  public Schema getResponse() {
    return Schema.create(Schema$Type.NULL);
  }
  
  public int hashCode() {
    return this.name.hashCode() + this.request.hashCode() + this.props.hashCode();
  }
  
  public boolean isOneWay() {
    return true;
  }
  
  void toJson(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    if (this.doc != null)
      paramJsonGenerator.writeStringField("doc", this.doc); 
    this.props.write(paramJsonGenerator);
    paramJsonGenerator.writeFieldName("request");
    this.request.fieldsToJson(Protocol.access$100(Protocol.this), paramJsonGenerator);
    toJson1(paramJsonGenerator);
    paramJsonGenerator.writeEndObject();
  }
  
  void toJson1(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStringField("response", "null");
    paramJsonGenerator.writeBooleanField("one-way", true);
  }
  
  public String toString() {
    try {
      StringWriter stringWriter = new StringWriter();
      this();
      JsonGenerator jsonGenerator = Schema.FACTORY.createJsonGenerator(stringWriter);
      toJson(jsonGenerator);
      jsonGenerator.flush();
      return stringWriter.toString();
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Protocol$Message.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */