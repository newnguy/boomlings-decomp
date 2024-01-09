package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
  protected final String _typePropertyName;
  
  public AsPropertyTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, Class paramClass, String paramString) {
    super(paramJavaType, paramTypeIdResolver, paramBeanProperty, paramClass);
    this._typePropertyName = paramString;
  }
  
  @Deprecated
  public AsPropertyTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, String paramString) {
    this(paramJavaType, paramTypeIdResolver, paramBeanProperty, (Class)null, paramString);
  }
  
  protected Object _deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (AsPropertyTypeDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        return null;
      case 1:
        if (this._baseType.getRawClass().isAssignableFrom(String.class))
          return paramJsonParser.getText(); 
      case 2:
        if (this._baseType.getRawClass().isAssignableFrom(Integer.class))
          return Integer.valueOf(paramJsonParser.getIntValue()); 
      case 3:
        if (this._baseType.getRawClass().isAssignableFrom(Double.class))
          return Double.valueOf(paramJsonParser.getDoubleValue()); 
      case 4:
        if (this._baseType.getRawClass().isAssignableFrom(Boolean.class))
          return Boolean.TRUE; 
      case 5:
        break;
    } 
    if (this._baseType.getRawClass().isAssignableFrom(Boolean.class))
      return Boolean.FALSE; 
  }
  
  protected Object _deserializeTypedUsingDefaultImpl(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TokenBuffer paramTokenBuffer) {
    if (this._defaultImpl != null) {
      JsonDeserializer jsonDeserializer = _findDefaultImplDeserializer(paramDeserializationContext);
      JsonParser jsonParser = paramJsonParser;
      if (paramTokenBuffer != null) {
        paramTokenBuffer.writeEndObject();
        jsonParser = paramTokenBuffer.asParser(paramJsonParser);
        jsonParser.nextToken();
      } 
      return jsonDeserializer.deserialize(jsonParser, paramDeserializationContext);
    } 
    Object object2 = _deserializeIfNatural(paramJsonParser, paramDeserializationContext);
    Object object1 = object2;
    if (object2 == null) {
      if (paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY)
        return super.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext); 
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.FIELD_NAME, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")");
    } 
    return object1;
  }
  
  public Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return (paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY) ? deserializeTypedFromArray(paramJsonParser, paramDeserializationContext) : deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCurrentToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   4: astore #4
    //   6: aload #4
    //   8: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   11: if_acmpne -> 105
    //   14: aload_1
    //   15: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   18: astore_3
    //   19: aconst_null
    //   20: astore #5
    //   22: aload_3
    //   23: astore #4
    //   25: aload #5
    //   27: astore_3
    //   28: aload #4
    //   30: getstatic com/flurry/org/codehaus/jackson/JsonToken.FIELD_NAME : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   33: if_acmpne -> 192
    //   36: aload_1
    //   37: invokevirtual getCurrentName : ()Ljava/lang/String;
    //   40: astore #5
    //   42: aload_1
    //   43: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   46: pop
    //   47: aload_0
    //   48: getfield _typePropertyName : Ljava/lang/String;
    //   51: aload #5
    //   53: invokevirtual equals : (Ljava/lang/Object;)Z
    //   56: ifeq -> 146
    //   59: aload_0
    //   60: aload_2
    //   61: aload_1
    //   62: invokevirtual getText : ()Ljava/lang/String;
    //   65: invokevirtual _findDeserializer : (Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   68: astore #5
    //   70: aload_1
    //   71: astore #4
    //   73: aload_3
    //   74: ifnull -> 88
    //   77: aload_3
    //   78: aload_1
    //   79: invokevirtual asParser : (Lcom/flurry/org/codehaus/jackson/JsonParser;)Lcom/flurry/org/codehaus/jackson/JsonParser;
    //   82: aload_1
    //   83: invokestatic createFlattened : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/JsonParser;)Lcom/flurry/org/codehaus/jackson/util/JsonParserSequence;
    //   86: astore #4
    //   88: aload #4
    //   90: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   93: pop
    //   94: aload #5
    //   96: aload #4
    //   98: aload_2
    //   99: invokevirtual deserialize : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;)Ljava/lang/Object;
    //   102: astore_1
    //   103: aload_1
    //   104: areturn
    //   105: aload #4
    //   107: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_ARRAY : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   110: if_acmpne -> 124
    //   113: aload_0
    //   114: aload_1
    //   115: aload_2
    //   116: aconst_null
    //   117: invokevirtual _deserializeTypedUsingDefaultImpl : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/util/TokenBuffer;)Ljava/lang/Object;
    //   120: astore_1
    //   121: goto -> 103
    //   124: aload #4
    //   126: astore_3
    //   127: aload #4
    //   129: getstatic com/flurry/org/codehaus/jackson/JsonToken.FIELD_NAME : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   132: if_acmpeq -> 19
    //   135: aload_0
    //   136: aload_1
    //   137: aload_2
    //   138: aconst_null
    //   139: invokevirtual _deserializeTypedUsingDefaultImpl : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/util/TokenBuffer;)Ljava/lang/Object;
    //   142: astore_1
    //   143: goto -> 103
    //   146: aload_3
    //   147: astore #4
    //   149: aload_3
    //   150: ifnonnull -> 163
    //   153: new com/flurry/org/codehaus/jackson/util/TokenBuffer
    //   156: dup
    //   157: aconst_null
    //   158: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/ObjectCodec;)V
    //   161: astore #4
    //   163: aload #4
    //   165: aload #5
    //   167: invokevirtual writeFieldName : (Ljava/lang/String;)V
    //   170: aload #4
    //   172: aload_1
    //   173: invokevirtual copyCurrentStructure : (Lcom/flurry/org/codehaus/jackson/JsonParser;)V
    //   176: aload_1
    //   177: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   180: astore #5
    //   182: aload #4
    //   184: astore_3
    //   185: aload #5
    //   187: astore #4
    //   189: goto -> 28
    //   192: aload_0
    //   193: aload_1
    //   194: aload_2
    //   195: aload_3
    //   196: invokevirtual _deserializeTypedUsingDefaultImpl : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/util/TokenBuffer;)Ljava/lang/Object;
    //   199: astore_1
    //   200: goto -> 103
  }
  
  public String getPropertyName() {
    return this._typePropertyName;
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.PROPERTY;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\AsPropertyTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */