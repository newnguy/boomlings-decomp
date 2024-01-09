package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase implements ResolvableSerializer {
  protected JsonSerializer _serializer;
  
  public IndexedStringListSerializer(BeanProperty paramBeanProperty) {
    super(List.class, paramBeanProperty);
  }
  
  private final void serializeContents(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    byte b2 = 0;
    byte b1 = 0;
    try {
      int i = paramList.size();
      while (b1 < i) {
        b2 = b1;
        String str = paramList.get(b1);
        if (str == null) {
          b2 = b1;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } else {
          b2 = b1;
          paramJsonGenerator.writeString(str);
        } 
        b1++;
      } 
    } catch (Exception exception) {
      wrapAndThrow(paramSerializerProvider, exception, paramList, b2);
    } 
  }
  
  private final void serializeUsingCustom(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    byte b1 = 0;
    byte b3 = 0;
    byte b2 = b1;
    try {
      int i = paramList.size();
      b2 = b1;
      JsonSerializer jsonSerializer = this._serializer;
      for (b1 = b3; b1 < i; b1++) {
        b2 = b1;
        String str = paramList.get(b1);
        if (str == null) {
          b2 = b1;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } else {
          b2 = b1;
          jsonSerializer.serialize(str, paramJsonGenerator, paramSerializerProvider);
        } 
      } 
    } catch (Exception exception) {
      wrapAndThrow(paramSerializerProvider, exception, paramList, b2);
    } 
  }
  
  protected JsonNode contentSchema() {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(String.class, this._property);
    if (!isDefaultSerializer(jsonSerializer))
      this._serializer = jsonSerializer; 
  }
  
  public void serialize(List paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartArray();
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider);
    } 
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeWithType(List paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForArray(paramList, paramJsonGenerator);
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider);
    } 
    paramTypeSerializer.writeTypeSuffixForArray(paramList, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\IndexedStringListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */