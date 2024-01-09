package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.util.List;
import java.util.Map;

class Protocol$TwoWayMessage extends Protocol$Message {
  private Schema errors;
  
  private Schema response;
  
  final Protocol this$0;
  
  private Protocol$TwoWayMessage(String paramString1, String paramString2, Map paramMap, Schema paramSchema1, Schema paramSchema2, Schema paramSchema3) {
    super(paramProtocol, paramString1, paramString2, paramMap, paramSchema1, null);
    this.response = paramSchema2;
    this.errors = paramSchema3;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (!super.equals(paramObject))
      return bool2; 
    boolean bool1 = bool2;
    if (paramObject instanceof Protocol$TwoWayMessage) {
      paramObject = paramObject;
      bool1 = bool2;
      if (this.response.equals(((Protocol$TwoWayMessage)paramObject).response)) {
        bool1 = bool2;
        if (this.errors.equals(((Protocol$TwoWayMessage)paramObject).errors))
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  public Schema getErrors() {
    return this.errors;
  }
  
  public Schema getResponse() {
    return this.response;
  }
  
  public int hashCode() {
    return super.hashCode() + this.response.hashCode() + this.errors.hashCode();
  }
  
  public boolean isOneWay() {
    return false;
  }
  
  void toJson1(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeFieldName("response");
    this.response.toJson(Protocol.access$100(Protocol.this), paramJsonGenerator);
    List list = this.errors.getTypes();
    if (list.size() > 1) {
      Schema schema = Schema.createUnion(list.subList(1, list.size()));
      paramJsonGenerator.writeFieldName("errors");
      schema.toJson(Protocol.access$100(Protocol.this), paramJsonGenerator);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Protocol$TwoWayMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */