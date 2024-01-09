package com.flurry.org.apache.avro.io;

class BinaryData$HashData {
  private final BinaryDecoder$BufferAccessor bytes = this.decoder.getBufferAccessor();
  
  private final BinaryDecoder decoder = new BinaryDecoder(new byte[0], 0, 0);
  
  public void set(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.decoder.configure(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryData$HashData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */