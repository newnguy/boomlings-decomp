package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class TypeBase extends JavaType implements JsonSerializableWithType {
  volatile String _canonicalName;
  
  @Deprecated
  protected TypeBase(Class paramClass, int paramInt) {
    super(paramClass, paramInt);
  }
  
  protected TypeBase(Class paramClass, int paramInt, Object paramObject1, Object paramObject2) {
    super(paramClass, paramInt);
    this._valueHandler = paramObject1;
    this._typeHandler = paramObject2;
  }
  
  protected static StringBuilder _classSignature(Class<boolean> paramClass, StringBuilder paramStringBuilder, boolean paramBoolean) {
    if (paramClass.isPrimitive()) {
      if (paramClass == boolean.class) {
        paramStringBuilder.append('Z');
        return paramStringBuilder;
      } 
      if (paramClass == byte.class) {
        paramStringBuilder.append('B');
        return paramStringBuilder;
      } 
      if (paramClass == short.class) {
        paramStringBuilder.append('S');
        return paramStringBuilder;
      } 
      if (paramClass == char.class) {
        paramStringBuilder.append('C');
        return paramStringBuilder;
      } 
      if (paramClass == int.class) {
        paramStringBuilder.append('I');
        return paramStringBuilder;
      } 
      if (paramClass == long.class) {
        paramStringBuilder.append('J');
        return paramStringBuilder;
      } 
      if (paramClass == float.class) {
        paramStringBuilder.append('F');
        return paramStringBuilder;
      } 
      if (paramClass == double.class) {
        paramStringBuilder.append('D');
        return paramStringBuilder;
      } 
      if (paramClass == void.class) {
        paramStringBuilder.append('V');
        return paramStringBuilder;
      } 
      throw new IllegalStateException("Unrecognized primitive type: " + paramClass.getName());
    } 
    paramStringBuilder.append('L');
    String str = paramClass.getName();
    int i = str.length();
    for (byte b = 0; b < i; b++) {
      char c1 = str.charAt(b);
      char c = c1;
      if (c1 == '.')
        c = '/'; 
      paramStringBuilder.append(c);
    } 
    if (paramBoolean)
      paramStringBuilder.append(';'); 
    return paramStringBuilder;
  }
  
  protected abstract String buildCanonicalName();
  
  public abstract StringBuilder getErasedSignature(StringBuilder paramStringBuilder);
  
  public abstract StringBuilder getGenericSignature(StringBuilder paramStringBuilder);
  
  public Object getTypeHandler() {
    return this._typeHandler;
  }
  
  public Object getValueHandler() {
    return this._valueHandler;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeString(toCanonical());
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(this, paramJsonGenerator);
    serialize(paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(this, paramJsonGenerator);
  }
  
  public String toCanonical() {
    String str2 = this._canonicalName;
    String str1 = str2;
    if (str2 == null)
      str1 = buildCanonicalName(); 
    return str1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */