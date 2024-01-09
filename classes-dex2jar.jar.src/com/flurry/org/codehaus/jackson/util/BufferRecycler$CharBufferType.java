package com.flurry.org.codehaus.jackson.util;

public enum BufferRecycler$CharBufferType {
  CONCAT_BUFFER,
  NAME_COPY_BUFFER,
  TEXT_BUFFER,
  TOKEN_BUFFER(2000);
  
  private static final BufferRecycler$CharBufferType[] $VALUES;
  
  private final int size;
  
  static {
    CONCAT_BUFFER = new BufferRecycler$CharBufferType("CONCAT_BUFFER", 1, 2000);
    TEXT_BUFFER = new BufferRecycler$CharBufferType("TEXT_BUFFER", 2, 200);
    NAME_COPY_BUFFER = new BufferRecycler$CharBufferType("NAME_COPY_BUFFER", 3, 200);
    $VALUES = new BufferRecycler$CharBufferType[] { TOKEN_BUFFER, CONCAT_BUFFER, TEXT_BUFFER, NAME_COPY_BUFFER };
  }
  
  BufferRecycler$CharBufferType(int paramInt1) {
    this.size = paramInt1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\BufferRecycler$CharBufferType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */