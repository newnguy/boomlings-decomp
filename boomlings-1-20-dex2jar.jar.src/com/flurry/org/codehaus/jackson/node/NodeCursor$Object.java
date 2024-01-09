package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.util.Iterator;
import java.util.Map;

public final class NodeCursor$Object extends NodeCursor {
  Iterator _contents;
  
  Map.Entry _current;
  
  boolean _needEntry;
  
  public NodeCursor$Object(JsonNode paramJsonNode, NodeCursor paramNodeCursor) {
    super(2, paramNodeCursor);
    this._contents = ((ObjectNode)paramJsonNode).getFields();
    this._needEntry = true;
  }
  
  public boolean currentHasChildren() {
    return (((ContainerNode)currentNode()).size() > 0);
  }
  
  public JsonNode currentNode() {
    return (this._current == null) ? null : (JsonNode)this._current.getValue();
  }
  
  public JsonToken endToken() {
    return JsonToken.END_OBJECT;
  }
  
  public String getCurrentName() {
    return (this._current == null) ? null : (String)this._current.getKey();
  }
  
  public JsonToken nextToken() {
    null = null;
    if (this._needEntry) {
      if (!this._contents.hasNext()) {
        this._current = null;
        return null;
      } 
      this._needEntry = false;
      this._current = this._contents.next();
      return JsonToken.FIELD_NAME;
    } 
    this._needEntry = true;
    return ((JsonNode)this._current.getValue()).asToken();
  }
  
  public JsonToken nextValue() {
    JsonToken jsonToken2 = nextToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.FIELD_NAME)
      jsonToken1 = nextToken(); 
    return jsonToken1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\NodeCursor$Object.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */