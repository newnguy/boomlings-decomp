package com.flurry.org.apache.avro.io;

class BinaryData$Decoders {
  private final BinaryDecoder$BufferAccessor b1 = this.d1.getBufferAccessor();
  
  private final BinaryDecoder$BufferAccessor b2 = this.d2.getBufferAccessor();
  
  private final BinaryDecoder d1 = new BinaryDecoder(new byte[0], 0, 0);
  
  private final BinaryDecoder d2 = new BinaryDecoder(new byte[0], 0, 0);
  
  public void set(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4) {
    this.d1.configure(paramArrayOfbyte1, paramInt1, paramInt2);
    this.d2.configure(paramArrayOfbyte2, paramInt3, paramInt4);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryData$Decoders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */