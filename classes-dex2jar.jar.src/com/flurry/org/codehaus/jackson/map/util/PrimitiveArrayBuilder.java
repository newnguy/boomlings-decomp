package com.flurry.org.codehaus.jackson.map.util;

public abstract class PrimitiveArrayBuilder {
  static final int INITIAL_CHUNK_SIZE = 12;
  
  static final int MAX_CHUNK_SIZE = 262144;
  
  static final int SMALL_CHUNK_SIZE = 16384;
  
  PrimitiveArrayBuilder$Node _bufferHead;
  
  PrimitiveArrayBuilder$Node _bufferTail;
  
  int _bufferedEntryCount;
  
  Object _freeBuffer;
  
  protected abstract Object _constructArray(int paramInt);
  
  protected void _reset() {
    if (this._bufferTail != null)
      this._freeBuffer = this._bufferTail.getData(); 
    this._bufferTail = null;
    this._bufferHead = null;
    this._bufferedEntryCount = 0;
  }
  
  public final Object appendCompletedChunk(Object paramObject, int paramInt) {
    paramObject = new PrimitiveArrayBuilder$Node(paramObject, paramInt);
    if (this._bufferHead == null) {
      this._bufferTail = (PrimitiveArrayBuilder$Node)paramObject;
      this._bufferHead = (PrimitiveArrayBuilder$Node)paramObject;
    } else {
      this._bufferTail.linkNext((PrimitiveArrayBuilder$Node)paramObject);
      this._bufferTail = (PrimitiveArrayBuilder$Node)paramObject;
    } 
    this._bufferedEntryCount += paramInt;
    if (paramInt < 16384) {
      paramInt += paramInt;
      return _constructArray(paramInt);
    } 
    paramInt = (paramInt >> 2) + paramInt;
    return _constructArray(paramInt);
  }
  
  public Object completeAndClearBuffer(Object paramObject, int paramInt) {
    int j = paramInt + this._bufferedEntryCount;
    Object object = _constructArray(j);
    PrimitiveArrayBuilder$Node primitiveArrayBuilder$Node = this._bufferHead;
    int i = 0;
    while (primitiveArrayBuilder$Node != null) {
      i = primitiveArrayBuilder$Node.copyData(object, i);
      primitiveArrayBuilder$Node = primitiveArrayBuilder$Node.next();
    } 
    System.arraycopy(paramObject, 0, object, i, paramInt);
    paramInt = i + paramInt;
    if (paramInt != j)
      throw new IllegalStateException("Should have gotten " + j + " entries, got " + paramInt); 
    return object;
  }
  
  public Object resetAndStart() {
    _reset();
    return (this._freeBuffer == null) ? _constructArray(12) : this._freeBuffer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\PrimitiveArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */