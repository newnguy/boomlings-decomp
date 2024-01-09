package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.node.ObjectNode;

final class JsonNodeDeserializer$ObjectDeserializer extends BaseNodeDeserializer {
  protected static final JsonNodeDeserializer$ObjectDeserializer _instance = new JsonNodeDeserializer$ObjectDeserializer();
  
  protected JsonNodeDeserializer$ObjectDeserializer() {
    super(ObjectNode.class);
  }
  
  public static JsonNodeDeserializer$ObjectDeserializer getInstance() {
    return _instance;
  }
  
  public ObjectNode deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
      paramJsonParser.nextToken();
      return deserializeObject(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());
    } 
    if (paramJsonParser.getCurrentToken() == JsonToken.FIELD_NAME)
      return deserializeObject(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory()); 
    throw paramDeserializationContext.mappingException(ObjectNode.class);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\JsonNodeDeserializer$ObjectDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */