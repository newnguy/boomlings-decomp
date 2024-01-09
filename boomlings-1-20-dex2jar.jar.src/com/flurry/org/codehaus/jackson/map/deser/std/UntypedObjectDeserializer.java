package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer {
  private static final Object[] NO_OBJECTS = new Object[0];
  
  public UntypedObjectDeserializer() {
    super(Object.class);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (UntypedObjectDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.mappingException(Object.class);
      case 1:
        return mapObject(paramJsonParser, paramDeserializationContext);
      case 3:
        return mapArray(paramJsonParser, paramDeserializationContext);
      case 5:
        return mapObject(paramJsonParser, paramDeserializationContext);
      case 6:
        return paramJsonParser.getEmbeddedObject();
      case 7:
        return paramJsonParser.getText();
      case 8:
        return paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? paramJsonParser.getBigIntegerValue() : paramJsonParser.getNumberValue();
      case 9:
        return paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? paramJsonParser.getDecimalValue() : Double.valueOf(paramJsonParser.getDoubleValue());
      case 10:
        return Boolean.TRUE;
      case 11:
        return Boolean.FALSE;
      case 12:
        break;
    } 
    return null;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    switch (UntypedObjectDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken.ordinal()]) {
      default:
        throw paramDeserializationContext.mappingException(Object.class);
      case 1:
      case 3:
      case 5:
        return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
      case 7:
        return paramJsonParser.getText();
      case 8:
        return paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? paramJsonParser.getBigIntegerValue() : Integer.valueOf(paramJsonParser.getIntValue());
      case 9:
        return paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? paramJsonParser.getDecimalValue() : Double.valueOf(paramJsonParser.getDoubleValue());
      case 10:
        return Boolean.TRUE;
      case 11:
        return Boolean.FALSE;
      case 6:
        return paramJsonParser.getEmbeddedObject();
      case 12:
        break;
    } 
    return null;
  }
  
  protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
      return mapArrayToArray(paramJsonParser, paramDeserializationContext); 
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      return new ArrayList(4); 
    ObjectBuffer objectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = objectBuffer.resetAndStart();
    int i = 0;
    int j = 0;
    while (true) {
      Object[] arrayOfObject1;
      Object object = deserialize(paramJsonParser, paramDeserializationContext);
      int k = j + 1;
      if (i >= arrayOfObject.length) {
        arrayOfObject1 = objectBuffer.appendCompletedChunk(arrayOfObject);
        i = 0;
      } else {
        arrayOfObject1 = arrayOfObject;
      } 
      int m = i + 1;
      arrayOfObject1[i] = object;
      j = k;
      i = m;
      arrayOfObject = arrayOfObject1;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
        ArrayList arrayList = new ArrayList(k + (k >> 3) + 1);
        objectBuffer.completeAndClearBuffer(arrayOfObject1, m, arrayList);
        return arrayList;
      } 
    } 
  }
  
  protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      return NO_OBJECTS; 
    ObjectBuffer objectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = objectBuffer.resetAndStart();
    int i = 0;
    while (true) {
      Object[] arrayOfObject1;
      Object object = deserialize(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfObject.length) {
        arrayOfObject1 = objectBuffer.appendCompletedChunk(arrayOfObject);
        i = 0;
      } else {
        arrayOfObject1 = arrayOfObject;
      } 
      int j = i + 1;
      arrayOfObject1[i] = object;
      i = j;
      arrayOfObject = arrayOfObject1;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        return objectBuffer.completeAndClearBuffer(arrayOfObject1, j); 
    } 
  }
  
  protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    LinkedHashMap<Object, Object> linkedHashMap1;
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.START_OBJECT)
      jsonToken1 = paramJsonParser.nextToken(); 
    if (jsonToken1 != JsonToken.FIELD_NAME)
      return new LinkedHashMap<Object, Object>(4); 
    String str1 = paramJsonParser.getText();
    paramJsonParser.nextToken();
    Object object1 = deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME) {
      linkedHashMap1 = new LinkedHashMap<Object, Object>(4);
      linkedHashMap1.put(str1, object1);
      return linkedHashMap1;
    } 
    String str2 = linkedHashMap1.getText();
    linkedHashMap1.nextToken();
    Object object2 = deserialize((JsonParser)linkedHashMap1, paramDeserializationContext);
    if (linkedHashMap1.nextToken() != JsonToken.FIELD_NAME) {
      linkedHashMap1 = new LinkedHashMap<Object, Object>(4);
      linkedHashMap1.put(str1, object1);
      linkedHashMap1.put(str2, object2);
      return linkedHashMap1;
    } 
    LinkedHashMap<Object, Object> linkedHashMap2 = new LinkedHashMap<Object, Object>();
    linkedHashMap2.put(str1, object1);
    linkedHashMap2.put(str2, object2);
    while (true) {
      object1 = linkedHashMap1.getText();
      linkedHashMap1.nextToken();
      linkedHashMap2.put(object1, deserialize((JsonParser)linkedHashMap1, paramDeserializationContext));
      if (linkedHashMap1.nextToken() == JsonToken.END_OBJECT)
        return linkedHashMap2; 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\UntypedObjectDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */