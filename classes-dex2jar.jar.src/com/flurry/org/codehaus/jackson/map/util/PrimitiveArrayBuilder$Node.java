package com.flurry.org.codehaus.jackson.map.util;

final class PrimitiveArrayBuilder$Node {
  final Object _data;
  
  final int _dataLength;
  
  PrimitiveArrayBuilder$Node _next;
  
  public PrimitiveArrayBuilder$Node(Object paramObject, int paramInt) {
    this._data = paramObject;
    this._dataLength = paramInt;
  }
  
  public int copyData(Object paramObject, int paramInt) {
    System.arraycopy(this._data, 0, paramObject, paramInt, this._dataLength);
    return this._dataLength + paramInt;
  }
  
  public Object getData() {
    return this._data;
  }
  
  public void linkNext(PrimitiveArrayBuilder$Node paramPrimitiveArrayBuilder$Node) {
    if (this._next != null)
      throw new IllegalStateException(); 
    this._next = paramPrimitiveArrayBuilder$Node;
  }
  
  public PrimitiveArrayBuilder$Node next() {
    return this._next;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\PrimitiveArrayBuilder$Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */