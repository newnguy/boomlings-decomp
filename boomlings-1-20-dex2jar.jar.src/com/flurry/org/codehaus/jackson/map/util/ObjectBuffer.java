package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
  static final int INITIAL_CHUNK_SIZE = 12;
  
  static final int MAX_CHUNK_SIZE = 262144;
  
  static final int SMALL_CHUNK_SIZE = 16384;
  
  private ObjectBuffer$Node _bufferHead;
  
  private ObjectBuffer$Node _bufferTail;
  
  private int _bufferedEntryCount;
  
  private Object[] _freeBuffer;
  
  protected final void _copyTo(Object paramObject, int paramInt1, Object[] paramArrayOfObject, int paramInt2) {
    ObjectBuffer$Node objectBuffer$Node = this._bufferHead;
    int i = 0;
    while (objectBuffer$Node != null) {
      Object[] arrayOfObject = objectBuffer$Node.getData();
      int j = arrayOfObject.length;
      System.arraycopy(arrayOfObject, 0, paramObject, i, j);
      i += j;
      objectBuffer$Node = objectBuffer$Node.next();
    } 
    System.arraycopy(paramArrayOfObject, 0, paramObject, i, paramInt2);
    paramInt2 = i + paramInt2;
    if (paramInt2 != paramInt1)
      throw new IllegalStateException("Should have gotten " + paramInt1 + " entries, got " + paramInt2); 
  }
  
  protected void _reset() {
    if (this._bufferTail != null)
      this._freeBuffer = this._bufferTail.getData(); 
    this._bufferTail = null;
    this._bufferHead = null;
    this._bufferedEntryCount = 0;
  }
  
  public Object[] appendCompletedChunk(Object[] paramArrayOfObject) {
    ObjectBuffer$Node objectBuffer$Node = new ObjectBuffer$Node(paramArrayOfObject);
    if (this._bufferHead == null) {
      this._bufferTail = objectBuffer$Node;
      this._bufferHead = objectBuffer$Node;
    } else {
      this._bufferTail.linkNext(objectBuffer$Node);
      this._bufferTail = objectBuffer$Node;
    } 
    int i = paramArrayOfObject.length;
    this._bufferedEntryCount += i;
    if (i < 16384) {
      i += i;
      return new Object[i];
    } 
    i += i >> 2;
    return new Object[i];
  }
  
  public int bufferedSize() {
    return this._bufferedEntryCount;
  }
  
  public void completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, List<Object> paramList) {
    byte b1;
    byte b2 = 0;
    ObjectBuffer$Node objectBuffer$Node = this._bufferHead;
    while (true) {
      b1 = b2;
      if (objectBuffer$Node != null) {
        Object[] arrayOfObject = objectBuffer$Node.getData();
        int i = arrayOfObject.length;
        for (b1 = 0; b1 < i; b1++)
          paramList.add(arrayOfObject[b1]); 
        objectBuffer$Node = objectBuffer$Node.next();
        continue;
      } 
      break;
    } 
    while (b1 < paramInt) {
      paramList.add(paramArrayOfObject[b1]);
      b1++;
    } 
  }
  
  public Object[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt) {
    int i = this._bufferedEntryCount + paramInt;
    Object[] arrayOfObject = new Object[i];
    _copyTo(arrayOfObject, i, paramArrayOfObject, paramInt);
    return arrayOfObject;
  }
  
  public Object[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, Class<?> paramClass) {
    int i = paramInt + this._bufferedEntryCount;
    Object[] arrayOfObject = (Object[])Array.newInstance(paramClass, i);
    _copyTo(arrayOfObject, i, paramArrayOfObject, paramInt);
    _reset();
    return arrayOfObject;
  }
  
  public int initialCapacity() {
    return (this._freeBuffer == null) ? 0 : this._freeBuffer.length;
  }
  
  public Object[] resetAndStart() {
    _reset();
    return (this._freeBuffer == null) ? new Object[12] : this._freeBuffer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ObjectBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */