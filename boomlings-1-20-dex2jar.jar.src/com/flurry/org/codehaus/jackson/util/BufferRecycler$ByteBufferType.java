package com.flurry.org.codehaus.jackson.util;

public enum BufferRecycler$ByteBufferType {
  READ_IO_BUFFER(4000),
  WRITE_CONCAT_BUFFER(4000),
  WRITE_ENCODING_BUFFER(4000);
  
  private static final BufferRecycler$ByteBufferType[] $VALUES;
  
  private final int size;
  
  static {
    WRITE_CONCAT_BUFFER = new BufferRecycler$ByteBufferType("WRITE_CONCAT_BUFFER", 2, 2000);
    $VALUES = new BufferRecycler$ByteBufferType[] { READ_IO_BUFFER, WRITE_ENCODING_BUFFER, WRITE_CONCAT_BUFFER };
  }
  
  BufferRecycler$ByteBufferType(int paramInt1) {
    this.size = paramInt1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\BufferRecycler$ByteBufferType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */