package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

public abstract class TypeSerializer {
  public abstract String getPropertyName();
  
  public abstract TypeIdResolver getTypeIdResolver();
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public abstract void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator);
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    writeTypePrefixForArray(paramObject, paramJsonGenerator);
  }
  
  public abstract void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator);
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    writeTypePrefixForObject(paramObject, paramJsonGenerator);
  }
  
  public abstract void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator);
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, Class paramClass) {
    writeTypePrefixForScalar(paramObject, paramJsonGenerator);
  }
  
  public abstract void writeTypeSuffixForArray(Object paramObject, JsonGenerator paramJsonGenerator);
  
  public abstract void writeTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator);
  
  public abstract void writeTypeSuffixForScalar(Object paramObject, JsonGenerator paramJsonGenerator);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\TypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */