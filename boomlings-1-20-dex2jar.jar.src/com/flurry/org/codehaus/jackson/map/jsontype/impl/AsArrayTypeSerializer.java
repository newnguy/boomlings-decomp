package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

public class AsArrayTypeSerializer extends TypeSerializerBase {
  public AsArrayTypeSerializer(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty) {
    super(paramTypeIdResolver, paramBeanProperty);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValue(paramObject));
    paramJsonGenerator.writeStartArray();
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValueAndType(paramObject, paramClass));
    paramJsonGenerator.writeStartArray();
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValue(paramObject));
    paramJsonGenerator.writeStartObject();
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValueAndType(paramObject, paramClass));
    paramJsonGenerator.writeStartObject();
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValue(paramObject));
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartArray();
    paramJsonGenerator.writeString(this._idResolver.idFromValueAndType(paramObject, paramClass));
  }
  
  public void writeTypeSuffixForArray(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndArray();
    paramJsonGenerator.writeEndArray();
  }
  
  public void writeTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndObject();
    paramJsonGenerator.writeEndArray();
  }
  
  public void writeTypeSuffixForScalar(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndArray();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\AsArrayTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */