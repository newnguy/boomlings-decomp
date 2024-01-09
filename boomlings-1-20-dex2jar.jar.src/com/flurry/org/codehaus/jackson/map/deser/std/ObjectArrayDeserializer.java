package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase {
  protected final JavaType _arrayType;
  
  protected final Class _elementClass;
  
  protected final JsonDeserializer _elementDeserializer;
  
  protected final TypeDeserializer _elementTypeDeserializer;
  
  protected final boolean _untyped;
  
  public ObjectArrayDeserializer(ArrayType paramArrayType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(Object[].class);
    boolean bool;
    this._arrayType = (JavaType)paramArrayType;
    this._elementClass = paramArrayType.getContentType().getRawClass();
    if (this._elementClass == Object.class) {
      bool = true;
    } else {
      bool = false;
    } 
    this._untyped = bool;
    this._elementDeserializer = paramJsonDeserializer;
    this._elementTypeDeserializer = paramTypeDeserializer;
  }
  
  private final Object[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Object object;
    Object[] arrayOfObject;
    DeserializationContext deserializationContext = null;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return (Object[])deserializationContext; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && this._elementClass == Byte.class)
        return (Object[])deserializeFromBase64(paramJsonParser, paramDeserializationContext); 
      throw paramDeserializationContext.mappingException(this._arrayType.getRawClass());
    } 
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = null;
    } else if (this._elementTypeDeserializer == null) {
      object = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    } else {
      object = this._elementDeserializer.deserializeWithType((JsonParser)object, paramDeserializationContext, this._elementTypeDeserializer);
    } 
    if (this._untyped) {
      arrayOfObject = new Object[1];
    } else {
      arrayOfObject = (Object[])Array.newInstance(this._elementClass, 1);
    } 
    arrayOfObject[0] = object;
    return arrayOfObject;
  }
  
  public Object[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ObjectBuffer objectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = objectBuffer.resetAndStart();
    TypeDeserializer typeDeserializer = this._elementTypeDeserializer;
    int i = 0;
    while (true) {
      Object[] arrayOfObject1;
      JsonToken jsonToken = paramJsonParser.nextToken();
      if (jsonToken != JsonToken.END_ARRAY) {
        Object object;
        if (jsonToken == JsonToken.VALUE_NULL) {
          jsonToken = null;
        } else if (typeDeserializer == null) {
          object = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          object = this._elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, typeDeserializer);
        } 
        if (i >= arrayOfObject.length) {
          arrayOfObject = objectBuffer.appendCompletedChunk(arrayOfObject);
          i = 0;
        } 
        int j = i + 1;
        arrayOfObject[i] = object;
        i = j;
        continue;
      } 
      if (this._untyped) {
        arrayOfObject1 = objectBuffer.completeAndClearBuffer(arrayOfObject, i);
      } else {
        arrayOfObject1 = objectBuffer.completeAndClearBuffer(arrayOfObject, i, this._elementClass);
      } 
      paramDeserializationContext.returnObjectBuffer(objectBuffer);
      return arrayOfObject1;
    } 
  }
  
  protected Byte[] deserializeFromBase64(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    byte[] arrayOfByte1 = paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
    Byte[] arrayOfByte = new Byte[arrayOfByte1.length];
    byte b = 0;
    int i = arrayOfByte1.length;
    while (b < i) {
      arrayOfByte[b] = Byte.valueOf(arrayOfByte1[b]);
      b++;
    } 
    return arrayOfByte;
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return (Object[])paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer getContentDeserializer() {
    return this._elementDeserializer;
  }
  
  public JavaType getContentType() {
    return this._arrayType.getContentType();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\ObjectArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */