package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import java.lang.reflect.Type;

public class UnknownSerializer extends SerializerBase {
  public UnknownSerializer() {
    super(Object.class);
  }
  
  protected void failForEmpty(Object paramObject) {
    throw new JsonMappingException("No serializer found for class " + paramObject.getClass().getName() + " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS) )");
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return null;
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS))
      failForEmpty(paramObject); 
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeEndObject();
  }
  
  public final void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS))
      failForEmpty(paramObject); 
    paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
    paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\UnknownSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */