package com.flurry.org.codehaus.jackson.map.util;

final class ObjectBuffer$Node {
  final Object[] _data;
  
  ObjectBuffer$Node _next;
  
  public ObjectBuffer$Node(Object[] paramArrayOfObject) {
    this._data = paramArrayOfObject;
  }
  
  public Object[] getData() {
    return this._data;
  }
  
  public void linkNext(ObjectBuffer$Node paramObjectBuffer$Node) {
    if (this._next != null)
      throw new IllegalStateException(); 
    this._next = paramObjectBuffer$Node;
  }
  
  public ObjectBuffer$Node next() {
    return this._next;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ObjectBuffer$Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */