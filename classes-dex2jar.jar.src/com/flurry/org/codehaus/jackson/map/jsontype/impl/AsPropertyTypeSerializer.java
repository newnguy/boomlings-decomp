package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer {
  protected final String _typePropertyName;
  
  public AsPropertyTypeSerializer(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, String paramString) {
    super(paramTypeIdResolver, paramBeanProperty);
    this._typePropertyName = paramString;
  }
  
  public String getPropertyName() {
    return this._typePropertyName;
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.PROPERTY;
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField(this._typePropertyName, this._idResolver.idFromValue(paramObject));
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField(this._typePropertyName, this._idResolver.idFromValueAndType(paramObject, paramClass));
  }
  
  public void writeTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeEndObject();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\AsPropertyTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */