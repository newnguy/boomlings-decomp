package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.Map;

public final class SimpleType extends TypeBase {
  protected final String[] _typeNames;
  
  protected final JavaType[] _typeParameters;
  
  protected SimpleType(Class paramClass) {
    this(paramClass, (String[])null, (JavaType[])null, (Object)null, (Object)null);
  }
  
  @Deprecated
  protected SimpleType(Class paramClass, String[] paramArrayOfString, JavaType[] paramArrayOfJavaType) {
    this(paramClass, paramArrayOfString, paramArrayOfJavaType, (Object)null, (Object)null);
  }
  
  protected SimpleType(Class paramClass, String[] paramArrayOfString, JavaType[] paramArrayOfJavaType, Object paramObject1, Object paramObject2) {
    super(paramClass, 0, paramObject1, paramObject2);
    if (paramArrayOfString == null || paramArrayOfString.length == 0) {
      this._typeNames = null;
      this._typeParameters = null;
      return;
    } 
    this._typeNames = paramArrayOfString;
    this._typeParameters = paramArrayOfJavaType;
  }
  
  public static SimpleType construct(Class<?> paramClass) {
    if (Map.class.isAssignableFrom(paramClass))
      throw new IllegalArgumentException("Can not construct SimpleType for a Map (class: " + paramClass.getName() + ")"); 
    if (Collection.class.isAssignableFrom(paramClass))
      throw new IllegalArgumentException("Can not construct SimpleType for a Collection (class: " + paramClass.getName() + ")"); 
    if (paramClass.isArray())
      throw new IllegalArgumentException("Can not construct SimpleType for an array (class: " + paramClass.getName() + ")"); 
    return new SimpleType(paramClass);
  }
  
  public static SimpleType constructUnsafe(Class paramClass) {
    return new SimpleType(paramClass, null, null, null, null);
  }
  
  protected JavaType _narrow(Class paramClass) {
    return new SimpleType(paramClass, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this._class.getName());
    if (this._typeParameters != null && this._typeParameters.length > 0) {
      stringBuilder.append('<');
      boolean bool = true;
      for (JavaType javaType : this._typeParameters) {
        if (bool) {
          bool = false;
        } else {
          stringBuilder.append(',');
        } 
        stringBuilder.append(javaType.toCanonical());
      } 
      stringBuilder.append('>');
    } 
    return stringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt) {
    return (paramInt < 0 || this._typeParameters == null || paramInt >= this._typeParameters.length) ? null : this._typeParameters[paramInt];
  }
  
  public int containedTypeCount() {
    return (this._typeParameters == null) ? 0 : this._typeParameters.length;
  }
  
  public String containedTypeName(int paramInt) {
    return (paramInt < 0 || this._typeNames == null || paramInt >= this._typeNames.length) ? null : this._typeNames[paramInt];
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_1
    //   4: aload_0
    //   5: if_acmpne -> 14
    //   8: iconst_1
    //   9: istore #4
    //   11: iload #4
    //   13: ireturn
    //   14: iload #5
    //   16: istore #4
    //   18: aload_1
    //   19: ifnull -> 11
    //   22: iload #5
    //   24: istore #4
    //   26: aload_1
    //   27: invokevirtual getClass : ()Ljava/lang/Class;
    //   30: aload_0
    //   31: invokevirtual getClass : ()Ljava/lang/Class;
    //   34: if_acmpne -> 11
    //   37: aload_1
    //   38: checkcast com/flurry/org/codehaus/jackson/map/type/SimpleType
    //   41: astore #6
    //   43: iload #5
    //   45: istore #4
    //   47: aload #6
    //   49: getfield _class : Ljava/lang/Class;
    //   52: aload_0
    //   53: getfield _class : Ljava/lang/Class;
    //   56: if_acmpne -> 11
    //   59: aload_0
    //   60: getfield _typeParameters : [Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   63: astore_1
    //   64: aload #6
    //   66: getfield _typeParameters : [Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   69: astore #6
    //   71: aload_1
    //   72: ifnonnull -> 96
    //   75: aload #6
    //   77: ifnull -> 90
    //   80: iload #5
    //   82: istore #4
    //   84: aload #6
    //   86: arraylength
    //   87: ifne -> 11
    //   90: iconst_1
    //   91: istore #4
    //   93: goto -> 11
    //   96: iload #5
    //   98: istore #4
    //   100: aload #6
    //   102: ifnull -> 11
    //   105: iload #5
    //   107: istore #4
    //   109: aload_1
    //   110: arraylength
    //   111: aload #6
    //   113: arraylength
    //   114: if_icmpne -> 11
    //   117: aload_1
    //   118: arraylength
    //   119: istore_3
    //   120: iconst_0
    //   121: istore_2
    //   122: iload_2
    //   123: iload_3
    //   124: if_icmpge -> 150
    //   127: iload #5
    //   129: istore #4
    //   131: aload_1
    //   132: iload_2
    //   133: aaload
    //   134: aload #6
    //   136: iload_2
    //   137: aaload
    //   138: invokevirtual equals : (Ljava/lang/Object;)Z
    //   141: ifeq -> 11
    //   144: iinc #2, 1
    //   147: goto -> 122
    //   150: iconst_1
    //   151: istore #4
    //   153: goto -> 11
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder) {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder) {
    byte b = 0;
    _classSignature(this._class, paramStringBuilder, false);
    StringBuilder stringBuilder = paramStringBuilder;
    if (this._typeParameters != null) {
      paramStringBuilder.append('<');
      JavaType[] arrayOfJavaType = this._typeParameters;
      int i = arrayOfJavaType.length;
      while (b < i) {
        paramStringBuilder = arrayOfJavaType[b].getGenericSignature(paramStringBuilder);
        b++;
      } 
      paramStringBuilder.append('>');
      stringBuilder = paramStringBuilder;
    } 
    stringBuilder.append(';');
    return stringBuilder;
  }
  
  public boolean isContainerType() {
    return false;
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(40);
    stringBuilder.append("[simple type, class ").append(buildCanonicalName()).append(']');
    return stringBuilder.toString();
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
  }
  
  public JavaType withContentTypeHandler(Object paramObject) {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
  }
  
  public SimpleType withContentValueHandler(Object paramObject) {
    throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
  }
  
  public SimpleType withTypeHandler(Object paramObject) {
    return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, paramObject);
  }
  
  public SimpleType withValueHandler(Object paramObject) {
    return (paramObject == this._valueHandler) ? this : new SimpleType(this._class, this._typeNames, this._typeParameters, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\SimpleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */