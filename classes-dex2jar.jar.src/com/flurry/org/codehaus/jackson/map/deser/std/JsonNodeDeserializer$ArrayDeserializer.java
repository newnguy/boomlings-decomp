package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.node.ArrayNode;

final class JsonNodeDeserializer$ArrayDeserializer extends BaseNodeDeserializer {
  protected static final JsonNodeDeserializer$ArrayDeserializer _instance = new JsonNodeDeserializer$ArrayDeserializer();
  
  protected JsonNodeDeserializer$ArrayDeserializer() {
    super(ArrayNode.class);
  }
  
  public static JsonNodeDeserializer$ArrayDeserializer getInstance() {
    return _instance;
  }
  
  public ArrayNode deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.isExpectedStartArrayToken())
      return deserializeArray(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory()); 
    throw paramDeserializationContext.mappingException(ArrayNode.class);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\JsonNodeDeserializer$ArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */