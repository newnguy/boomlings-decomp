package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;

abstract class NodeCursor extends JsonStreamContext {
  final NodeCursor _parent;
  
  public NodeCursor(int paramInt, NodeCursor paramNodeCursor) {
    this._type = paramInt;
    this._index = -1;
    this._parent = paramNodeCursor;
  }
  
  public abstract boolean currentHasChildren();
  
  public abstract JsonNode currentNode();
  
  public abstract JsonToken endToken();
  
  public abstract String getCurrentName();
  
  public final NodeCursor getParent() {
    return this._parent;
  }
  
  public final NodeCursor iterateChildren() {
    JsonNode jsonNode = currentNode();
    if (jsonNode == null)
      throw new IllegalStateException("No current node"); 
    if (jsonNode.isArray())
      return new NodeCursor$Array(jsonNode, this); 
    if (jsonNode.isObject())
      return new NodeCursor$Object(jsonNode, this); 
    throw new IllegalStateException("Current node of type " + jsonNode.getClass().getName());
  }
  
  public abstract JsonToken nextToken();
  
  public abstract JsonToken nextValue();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\NodeCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */