package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdSerializers$BooleanSerializer extends NonTypedScalarSerializerBase {
  final boolean _forPrimitive;
  
  public StdSerializers$BooleanSerializer(boolean paramBoolean) {
    super(Boolean.class);
    this._forPrimitive = paramBoolean;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (!this._forPrimitive) {
      boolean bool1 = true;
      return (JsonNode)createSchemaNode("boolean", bool1);
    } 
    boolean bool = false;
    return (JsonNode)createSchemaNode("boolean", bool);
  }
  
  public void serialize(Boolean paramBoolean, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeBoolean(paramBoolean.booleanValue());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$BooleanSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */