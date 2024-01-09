package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.util.Collection;
import java.util.Iterator;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase implements ResolvableSerializer {
  protected JsonSerializer _serializer;
  
  public StringCollectionSerializer(BeanProperty paramBeanProperty) {
    super(Collection.class, paramBeanProperty);
  }
  
  private final void serializeContents(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._serializer != null) {
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
      return;
    } 
    Iterator<String> iterator = paramCollection.iterator();
    for (byte b = 0;; b++) {
      if (iterator.hasNext()) {
        String str = iterator.next();
        if (str == null) {
          try {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            b++;
          } catch (Exception exception) {
            wrapAndThrow(paramSerializerProvider, exception, paramCollection, b);
          } 
          continue;
        } 
        paramJsonGenerator.writeString((String)exception);
      } else {
        return;
      } 
    } 
  }
  
  private void serializeUsingCustom(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    JsonSerializer jsonSerializer = this._serializer;
    for (String str : paramCollection) {
      if (str == null) {
        try {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } catch (Exception exception) {
          wrapAndThrow(paramSerializerProvider, exception, paramCollection, 0);
        } 
        continue;
      } 
      jsonSerializer.serialize(exception, paramJsonGenerator, paramSerializerProvider);
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
  
  public void serialize(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartArray();
    if (this._serializer == null) {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
    } 
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeWithType(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForArray(paramCollection, paramJsonGenerator);
    if (this._serializer == null) {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeUsingCustom(paramCollection, paramJsonGenerator, paramSerializerProvider);
    } 
    paramTypeSerializer.writeTypeSuffixForArray(paramCollection, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StringCollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */