package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.util.Iterator;

public abstract class ObjectCodec {
  public abstract JsonNode createArrayNode();
  
  public abstract JsonNode createObjectNode();
  
  public abstract JsonNode readTree(JsonParser paramJsonParser);
  
  public abstract Object readValue(JsonParser paramJsonParser, JavaType paramJavaType);
  
  public abstract Object readValue(JsonParser paramJsonParser, TypeReference paramTypeReference);
  
  public abstract Object readValue(JsonParser paramJsonParser, Class paramClass);
  
  public abstract Iterator readValues(JsonParser paramJsonParser, JavaType paramJavaType);
  
  public abstract Iterator readValues(JsonParser paramJsonParser, TypeReference paramTypeReference);
  
  public abstract Iterator readValues(JsonParser paramJsonParser, Class paramClass);
  
  public abstract JsonParser treeAsTokens(JsonNode paramJsonNode);
  
  public abstract Object treeToValue(JsonNode paramJsonNode, Class paramClass);
  
  public abstract void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode);
  
  public abstract void writeValue(JsonGenerator paramJsonGenerator, Object paramObject);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ObjectCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */