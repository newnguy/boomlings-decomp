package com.flurry.org.apache.avro.io;

class BinaryDecoder$BufferAccessor {
  private byte[] buf;
  
  private final BinaryDecoder decoder;
  
  boolean detached = false;
  
  private int limit;
  
  private int pos;
  
  private BinaryDecoder$BufferAccessor(BinaryDecoder paramBinaryDecoder) {
    this.decoder = paramBinaryDecoder;
  }
  
  void detach() {
    this.buf = BinaryDecoder.access$300(this.decoder);
    this.pos = BinaryDecoder.access$400(this.decoder);
    this.limit = BinaryDecoder.access$500(this.decoder);
    this.detached = true;
  }
  
  byte[] getBuf() {
    return this.detached ? this.buf : BinaryDecoder.access$300(this.decoder);
  }
  
  int getLim() {
    return this.detached ? this.limit : BinaryDecoder.access$500(this.decoder);
  }
  
  int getPos() {
    return this.detached ? this.pos : BinaryDecoder.access$400(this.decoder);
  }
  
  void setBuf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.detached) {
      this.buf = paramArrayOfbyte;
      this.limit = paramInt1 + paramInt2;
      this.pos = paramInt1;
      return;
    } 
    BinaryDecoder.access$302(this.decoder, paramArrayOfbyte);
    BinaryDecoder.access$502(this.decoder, paramInt1 + paramInt2);
    BinaryDecoder.access$402(this.decoder, paramInt1);
    BinaryDecoder.access$602(this.decoder, paramInt1);
  }
  
  void setLimit(int paramInt) {
    if (this.detached) {
      this.limit = paramInt;
      return;
    } 
    BinaryDecoder.access$502(this.decoder, paramInt);
  }
  
  void setPos(int paramInt) {
    if (this.detached) {
      this.pos = paramInt;
      return;
    } 
    BinaryDecoder.access$402(this.decoder, paramInt);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryDecoder$BufferAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */