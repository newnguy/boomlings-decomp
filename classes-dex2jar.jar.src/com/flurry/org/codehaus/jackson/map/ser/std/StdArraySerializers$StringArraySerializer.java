package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$StringArraySerializer extends StdArraySerializers$ArraySerializerBase implements ResolvableSerializer {
  protected JsonSerializer _elementSerializer;
  
  public StdArraySerializers$StringArraySerializer(BeanProperty paramBeanProperty) {
    super(String[].class, (TypeSerializer)null, paramBeanProperty);
  }
  
  private void serializeContentsSlow(String[] paramArrayOfString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    byte b = 0;
    int i = paramArrayOfString.length;
    while (b < i) {
      if (paramArrayOfString[b] == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      } else {
        paramJsonSerializer.serialize(paramArrayOfString[b], paramJsonGenerator, paramSerializerProvider);
      } 
      b++;
    } 
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return this;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", (JsonNode)createSchemaNode("string"));
    return (JsonNode)objectNode;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(String.class, this._property);
    if (jsonSerializer != null && jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null)
      this._elementSerializer = jsonSerializer; 
  }
  
  public void serializeContents(String[] paramArrayOfString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    int i = paramArrayOfString.length;
    if (i != 0) {
      if (this._elementSerializer != null) {
        serializeContentsSlow(paramArrayOfString, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
        return;
      } 
      byte b = 0;
      while (true) {
        if (b < i) {
          if (paramArrayOfString[b] == null) {
            paramJsonGenerator.writeNull();
          } else {
            paramJsonGenerator.writeString(paramArrayOfString[b]);
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$StringArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */