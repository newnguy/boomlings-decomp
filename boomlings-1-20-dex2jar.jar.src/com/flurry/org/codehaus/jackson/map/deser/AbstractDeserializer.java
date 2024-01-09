package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class AbstractDeserializer extends JsonDeserializer {
  protected final boolean _acceptBoolean;
  
  protected final boolean _acceptDouble;
  
  protected final boolean _acceptInt;
  
  protected final boolean _acceptString;
  
  protected final JavaType _baseType;
  
  public AbstractDeserializer(JavaType paramJavaType) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: invokespecial <init> : ()V
    //   6: aload_0
    //   7: aload_1
    //   8: putfield _baseType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   11: aload_1
    //   12: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   15: astore_1
    //   16: aload_0
    //   17: aload_1
    //   18: ldc java/lang/String
    //   20: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   23: putfield _acceptString : Z
    //   26: aload_1
    //   27: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
    //   30: if_acmpeq -> 42
    //   33: aload_1
    //   34: ldc java/lang/Boolean
    //   36: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   39: ifeq -> 98
    //   42: iconst_1
    //   43: istore_2
    //   44: aload_0
    //   45: iload_2
    //   46: putfield _acceptBoolean : Z
    //   49: aload_1
    //   50: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   53: if_acmpeq -> 65
    //   56: aload_1
    //   57: ldc java/lang/Integer
    //   59: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   62: ifeq -> 103
    //   65: iconst_1
    //   66: istore_2
    //   67: aload_0
    //   68: iload_2
    //   69: putfield _acceptInt : Z
    //   72: aload_1
    //   73: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
    //   76: if_acmpeq -> 90
    //   79: iload_3
    //   80: istore_2
    //   81: aload_1
    //   82: ldc java/lang/Double
    //   84: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   87: ifeq -> 92
    //   90: iconst_1
    //   91: istore_2
    //   92: aload_0
    //   93: iload_2
    //   94: putfield _acceptDouble : Z
    //   97: return
    //   98: iconst_0
    //   99: istore_2
    //   100: goto -> 44
    //   103: iconst_0
    //   104: istore_2
    //   105: goto -> 67
  }
  
  protected Object _deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (AbstractDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        return null;
      case 1:
        if (this._acceptString)
          return paramJsonParser.getText(); 
      case 2:
        if (this._acceptInt)
          return Integer.valueOf(paramJsonParser.getIntValue()); 
      case 3:
        if (this._acceptDouble)
          return Double.valueOf(paramJsonParser.getDoubleValue()); 
      case 4:
        if (this._acceptBoolean)
          return Boolean.TRUE; 
      case 5:
        break;
    } 
    if (this._acceptBoolean)
      return Boolean.FALSE; 
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    throw paramDeserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types can only be instantiated with additional type information");
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    Object object = _deserializeIfNatural(paramJsonParser, paramDeserializationContext);
    return (object != null) ? object : paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\AbstractDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */