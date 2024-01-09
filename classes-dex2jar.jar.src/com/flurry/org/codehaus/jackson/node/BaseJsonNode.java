package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.util.List;

public abstract class BaseJsonNode extends JsonNode implements JsonSerializableWithType {
  public abstract JsonToken asToken();
  
  public ObjectNode findParent(String paramString) {
    return null;
  }
  
  public List findParents(String paramString, List paramList) {
    return paramList;
  }
  
  public final JsonNode findPath(String paramString) {
    JsonNode jsonNode2 = findValue(paramString);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      jsonNode1 = MissingNode.getInstance(); 
    return jsonNode1;
  }
  
  public JsonNode findValue(String paramString) {
    return null;
  }
  
  public List findValues(String paramString, List paramList) {
    return paramList;
  }
  
  public List findValuesAsText(String paramString, List paramList) {
    return paramList;
  }
  
  public JsonParser.NumberType getNumberType() {
    return null;
  }
  
  public abstract void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider);
  
  public abstract void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer);
  
  public JsonParser traverse() {
    return (JsonParser)new TreeTraversingParser(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\BaseJsonNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */