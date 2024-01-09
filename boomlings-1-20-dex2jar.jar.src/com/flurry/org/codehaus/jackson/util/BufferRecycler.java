package com.flurry.org.codehaus.jackson.util;

public class BufferRecycler {
  public static final int DEFAULT_WRITE_CONCAT_BUFFER_LEN = 2000;
  
  protected final byte[][] _byteBuffers = new byte[(BufferRecycler$ByteBufferType.values()).length][];
  
  protected final char[][] _charBuffers = new char[(BufferRecycler$CharBufferType.values()).length][];
  
  private final byte[] balloc(int paramInt) {
    return new byte[paramInt];
  }
  
  private final char[] calloc(int paramInt) {
    return new char[paramInt];
  }
  
  public final byte[] allocByteBuffer(BufferRecycler$ByteBufferType paramBufferRecycler$ByteBufferType) {
    int i = paramBufferRecycler$ByteBufferType.ordinal();
    byte[] arrayOfByte = this._byteBuffers[i];
    if (arrayOfByte == null)
      return balloc(BufferRecycler$ByteBufferType.access$000(paramBufferRecycler$ByteBufferType)); 
    this._byteBuffers[i] = null;
    return arrayOfByte;
  }
  
  public final char[] allocCharBuffer(BufferRecycler$CharBufferType paramBufferRecycler$CharBufferType) {
    return allocCharBuffer(paramBufferRecycler$CharBufferType, 0);
  }
  
  public final char[] allocCharBuffer(BufferRecycler$CharBufferType paramBufferRecycler$CharBufferType, int paramInt) {
    int i = paramInt;
    if (BufferRecycler$CharBufferType.access$100(paramBufferRecycler$CharBufferType) > paramInt)
      i = BufferRecycler$CharBufferType.access$100(paramBufferRecycler$CharBufferType); 
    paramInt = paramBufferRecycler$CharBufferType.ordinal();
    char[] arrayOfChar = this._charBuffers[paramInt];
    if (arrayOfChar == null || arrayOfChar.length < i)
      return calloc(i); 
    this._charBuffers[paramInt] = null;
    return arrayOfChar;
  }
  
  public final void releaseByteBuffer(BufferRecycler$ByteBufferType paramBufferRecycler$ByteBufferType, byte[] paramArrayOfbyte) {
    this._byteBuffers[paramBufferRecycler$ByteBufferType.ordinal()] = paramArrayOfbyte;
  }
  
  public final void releaseCharBuffer(BufferRecycler$CharBufferType paramBufferRecycler$CharBufferType, char[] paramArrayOfchar) {
    this._charBuffers[paramBufferRecycler$CharBufferType.ordinal()] = paramArrayOfchar;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\BufferRecycler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */