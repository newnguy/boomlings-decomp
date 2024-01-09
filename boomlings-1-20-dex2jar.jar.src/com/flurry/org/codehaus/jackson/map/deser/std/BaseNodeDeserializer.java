package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;

abstract class BaseNodeDeserializer extends StdDeserializer {
  public BaseNodeDeserializer(Class paramClass) {
    super(paramClass);
  }
  
  protected void _handleDuplicateField(String paramString, ObjectNode paramObjectNode, JsonNode paramJsonNode1, JsonNode paramJsonNode2) {}
  
  protected void _reportProblem(JsonParser paramJsonParser, String paramString) {
    throw new JsonMappingException(paramString, paramJsonParser.getTokenLocation());
  }
  
  protected final JsonNode deserializeAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory) {
    JsonParser.NumberType numberType;
    switch (BaseNodeDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.mappingException(getValueClass());
      case 1:
        return (JsonNode)deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      case 2:
        return (JsonNode)deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      case 5:
        return (JsonNode)deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      case 6:
        null = paramJsonParser.getEmbeddedObject();
        return (JsonNode)((null == null) ? paramJsonNodeFactory.nullNode() : ((null.getClass() == byte[].class) ? paramJsonNodeFactory.binaryNode((byte[])null) : paramJsonNodeFactory.POJONode(null)));
      case 3:
        return (JsonNode)paramJsonNodeFactory.textNode(paramJsonParser.getText());
      case 7:
        numberType = paramJsonParser.getNumberType();
        return (JsonNode)((numberType == JsonParser.NumberType.BIG_INTEGER || paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) ? paramJsonNodeFactory.numberNode(paramJsonParser.getBigIntegerValue()) : ((numberType == JsonParser.NumberType.INT) ? paramJsonNodeFactory.numberNode(paramJsonParser.getIntValue()) : paramJsonNodeFactory.numberNode(paramJsonParser.getLongValue())));
      case 8:
        return (JsonNode)((paramJsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS)) ? paramJsonNodeFactory.numberNode(paramJsonParser.getDecimalValue()) : paramJsonNodeFactory.numberNode(paramJsonParser.getDoubleValue()));
      case 9:
        return (JsonNode)paramJsonNodeFactory.booleanNode(true);
      case 10:
        return (JsonNode)paramJsonNodeFactory.booleanNode(false);
      case 11:
        break;
    } 
    return (JsonNode)paramJsonNodeFactory.nullNode();
  }
  
  protected final ArrayNode deserializeArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory) {
    ArrayNode arrayNode = paramJsonNodeFactory.arrayNode();
    while (true) {
      switch (BaseNodeDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.nextToken().ordinal()]) {
        case 1:
          arrayNode.add((JsonNode)deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
          break;
        case 2:
          arrayNode.add((JsonNode)deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
          break;
        case 3:
          arrayNode.add((JsonNode)paramJsonNodeFactory.textNode(paramJsonParser.getText()));
          break;
        case 4:
          return arrayNode;
      } 
    } 
  }
  
  protected final ObjectNode deserializeObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual objectNode : ()Lcom/flurry/org/codehaus/jackson/node/ObjectNode;
    //   4: astore #6
    //   6: aload_1
    //   7: invokevirtual getCurrentToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   10: astore #5
    //   12: aload #5
    //   14: astore #4
    //   16: aload #5
    //   18: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   21: if_acmpne -> 30
    //   24: aload_1
    //   25: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   28: astore #4
    //   30: aload #4
    //   32: getstatic com/flurry/org/codehaus/jackson/JsonToken.FIELD_NAME : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   35: if_acmpne -> 163
    //   38: aload_1
    //   39: invokevirtual getCurrentName : ()Ljava/lang/String;
    //   42: astore #5
    //   44: getstatic com/flurry/org/codehaus/jackson/map/deser/std/BaseNodeDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken : [I
    //   47: aload_1
    //   48: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   51: invokevirtual ordinal : ()I
    //   54: iaload
    //   55: tableswitch default -> 80, 1 -> 126, 2 -> 138, 3 -> 150
    //   80: aload_0
    //   81: aload_1
    //   82: aload_2
    //   83: aload_3
    //   84: invokevirtual deserializeAny : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/node/JsonNodeFactory;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   87: astore #4
    //   89: aload #6
    //   91: aload #5
    //   93: aload #4
    //   95: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   98: astore #7
    //   100: aload #7
    //   102: ifnull -> 117
    //   105: aload_0
    //   106: aload #5
    //   108: aload #6
    //   110: aload #7
    //   112: aload #4
    //   114: invokevirtual _handleDuplicateField : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/node/ObjectNode;Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/codehaus/jackson/JsonNode;)V
    //   117: aload_1
    //   118: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   121: astore #4
    //   123: goto -> 30
    //   126: aload_0
    //   127: aload_1
    //   128: aload_2
    //   129: aload_3
    //   130: invokevirtual deserializeObject : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/node/JsonNodeFactory;)Lcom/flurry/org/codehaus/jackson/node/ObjectNode;
    //   133: astore #4
    //   135: goto -> 89
    //   138: aload_0
    //   139: aload_1
    //   140: aload_2
    //   141: aload_3
    //   142: invokevirtual deserializeArray : (Lcom/flurry/org/codehaus/jackson/JsonParser;Lcom/flurry/org/codehaus/jackson/map/DeserializationContext;Lcom/flurry/org/codehaus/jackson/node/JsonNodeFactory;)Lcom/flurry/org/codehaus/jackson/node/ArrayNode;
    //   145: astore #4
    //   147: goto -> 89
    //   150: aload_3
    //   151: aload_1
    //   152: invokevirtual getText : ()Ljava/lang/String;
    //   155: invokevirtual textNode : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/node/TextNode;
    //   158: astore #4
    //   160: goto -> 89
    //   163: aload #6
    //   165: areturn
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\BaseNodeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */