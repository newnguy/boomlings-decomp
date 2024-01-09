package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;

public final class NodeCursor$RootValue extends NodeCursor {
  protected boolean _done = false;
  
  JsonNode _node;
  
  public NodeCursor$RootValue(JsonNode paramJsonNode, NodeCursor paramNodeCursor) {
    super(0, paramNodeCursor);
    this._node = paramJsonNode;
  }
  
  public boolean currentHasChildren() {
    return false;
  }
  
  public JsonNode currentNode() {
    return this._node;
  }
  
  public JsonToken endToken() {
    return null;
  }
  
  public String getCurrentName() {
    return null;
  }
  
  public JsonToken nextToken() {
    JsonToken jsonToken = null;
    if (!this._done) {
      this._done = true;
      return this._node.asToken();
    } 
    this._node = null;
    return jsonToken;
  }
  
  public JsonToken nextValue() {
    return nextToken();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\NodeCursor$RootValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */