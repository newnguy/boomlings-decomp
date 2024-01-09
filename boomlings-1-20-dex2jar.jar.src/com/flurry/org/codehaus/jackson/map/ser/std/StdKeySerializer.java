package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import java.util.Date;

public class StdKeySerializer extends SerializerBase {
  static final StdKeySerializer instace = new StdKeySerializer();
  
  public StdKeySerializer() {
    super(Object.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("string");
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramObject instanceof Date) {
      paramSerializerProvider.defaultSerializeDateKey((Date)paramObject, paramJsonGenerator);
      return;
    } 
    paramJsonGenerator.writeFieldName(paramObject.toString());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */