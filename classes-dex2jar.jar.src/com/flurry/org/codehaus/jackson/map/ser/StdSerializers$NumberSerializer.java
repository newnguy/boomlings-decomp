package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class StdSerializers$NumberSerializer extends ScalarSerializerBase {
  public static final StdSerializers$NumberSerializer instance = new StdSerializers$NumberSerializer();
  
  public StdSerializers$NumberSerializer() {
    super(Number.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("number", true);
  }
  
  public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramNumber instanceof BigDecimal) {
      paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
      return;
    } 
    if (paramNumber instanceof BigInteger) {
      paramJsonGenerator.writeNumber((BigInteger)paramNumber);
      return;
    } 
    if (paramNumber instanceof Integer) {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
      return;
    } 
    if (paramNumber instanceof Long) {
      paramJsonGenerator.writeNumber(paramNumber.longValue());
      return;
    } 
    if (paramNumber instanceof Double) {
      paramJsonGenerator.writeNumber(paramNumber.doubleValue());
      return;
    } 
    if (paramNumber instanceof Float) {
      paramJsonGenerator.writeNumber(paramNumber.floatValue());
      return;
    } 
    if (paramNumber instanceof Byte || paramNumber instanceof Short) {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
      return;
    } 
    paramJsonGenerator.writeNumber(paramNumber.toString());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$NumberSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */