package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

public class AsWrapperTypeSerializer extends TypeSerializerBase {
  public AsWrapperTypeSerializer(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty) {
    super(paramTypeIdResolver, paramBeanProperty);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.WRAPPER_OBJECT;
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeArrayFieldStart(this._idResolver.idFromValue(paramObject));
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeArrayFieldStart(this._idResolver.idFromValueAndType(paramObject, paramClass));
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeObjectFieldStart(this._idResolver.idFromValue(paramObject));
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeObjectFieldStart(this._idResolver.idFromValueAndType(paramObject, paramClass));
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeFieldName(this._idResolver.idFromValue(paramObject));
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeFieldName(this._idResolver.idFromValueAndType(paramObject, paramClass));
  }
  
  public void writeTypeSuffixForArray(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndArray();
    paramJsonGenerator.writeEndObject();
  }
  
  public void writeTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndObject();
    paramJsonGenerator.writeEndObject();
  }
  
  public void writeTypeSuffixForScalar(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndObject();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\AsWrapperTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */