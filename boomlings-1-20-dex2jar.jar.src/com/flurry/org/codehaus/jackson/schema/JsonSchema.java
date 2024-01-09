package com.flurry.org.codehaus.jackson.schema;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.annotate.JsonCreator;
import com.flurry.org.codehaus.jackson.annotate.JsonValue;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;

public class JsonSchema {
  private final ObjectNode schema;
  
  @JsonCreator
  public JsonSchema(ObjectNode paramObjectNode) {
    this.schema = paramObjectNode;
  }
  
  public static JsonNode getDefaultSchemaNode() {
    ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
    objectNode.put("type", "any");
    return (JsonNode)objectNode;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (!(paramObject instanceof JsonSchema))
        return false; 
      paramObject = paramObject;
      if (this.schema == null) {
        if (((JsonSchema)paramObject).schema != null)
          bool = false; 
        return bool;
      } 
      bool = this.schema.equals(((JsonSchema)paramObject).schema);
    } 
    return bool;
  }
  
  @JsonValue
  public ObjectNode getSchemaNode() {
    return this.schema;
  }
  
  public String toString() {
    return this.schema.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\schema\JsonSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */