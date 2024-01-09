package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.util.Iterator;

public final class NodeCursor$Array extends NodeCursor {
  Iterator _contents;
  
  JsonNode _currentNode;
  
  public NodeCursor$Array(JsonNode paramJsonNode, NodeCursor paramNodeCursor) {
    super(1, paramNodeCursor);
    this._contents = paramJsonNode.getElements();
  }
  
  public boolean currentHasChildren() {
    return (((ContainerNode)currentNode()).size() > 0);
  }
  
  public JsonNode currentNode() {
    return this._currentNode;
  }
  
  public JsonToken endToken() {
    return JsonToken.END_ARRAY;
  }
  
  public String getCurrentName() {
    return null;
  }
  
  public JsonToken nextToken() {
    null = null;
    if (!this._contents.hasNext()) {
      this._currentNode = null;
      return null;
    } 
    this._currentNode = this._contents.next();
    return this._currentNode.asToken();
  }
  
  public JsonToken nextValue() {
    return nextToken();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\NodeCursor$Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */