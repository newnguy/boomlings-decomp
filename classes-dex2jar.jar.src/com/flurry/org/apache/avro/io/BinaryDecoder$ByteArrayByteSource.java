package com.flurry.org.apache.avro.io;

import java.io.EOFException;

class BinaryDecoder$ByteArrayByteSource extends BinaryDecoder$ByteSource {
  private boolean compacted = false;
  
  private byte[] data;
  
  private int max;
  
  private int position;
  
  private BinaryDecoder$ByteArrayByteSource(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte.length < 16 || paramInt2 < 16) {
      this.data = new byte[16];
      System.arraycopy(paramArrayOfbyte, paramInt1, this.data, 0, paramInt2);
      this.position = 0;
      this.max = paramInt2;
      return;
    } 
    this.data = paramArrayOfbyte;
    this.position = paramInt1;
    this.max = paramInt1 + paramInt2;
  }
  
  protected void attach(int paramInt, BinaryDecoder paramBinaryDecoder) {
    BinaryDecoder.access$302(paramBinaryDecoder, this.data);
    BinaryDecoder.access$402(paramBinaryDecoder, this.position);
    BinaryDecoder.access$602(paramBinaryDecoder, this.position);
    BinaryDecoder.access$502(paramBinaryDecoder, this.max);
    this.ba = new BinaryDecoder$BufferAccessor(paramBinaryDecoder, null);
  }
  
  public void close() {
    this.ba.setPos(this.ba.getLim());
  }
  
  protected void compactAndFill(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    if (!this.compacted) {
      byte[] arrayOfByte = new byte[paramInt3 + 16];
      System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt3);
      this.ba.setBuf(arrayOfByte, 0, paramInt3);
      this.compacted = true;
    } 
  }
  
  public boolean isEof() {
    return (this.ba.getLim() - this.ba.getPos() == 0);
  }
  
  public int read() {
    this.max = this.ba.getLim();
    this.position = this.ba.getPos();
    if (this.position >= this.max)
      return -1; 
    byte[] arrayOfByte = this.ba.getBuf();
    int i = this.position;
    this.position = i + 1;
    i = arrayOfByte[i] & 0xFF;
    this.ba.setPos(this.position);
    return i;
  }
  
  protected void readRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (tryReadRaw(paramArrayOfbyte, paramInt1, paramInt2) < paramInt2)
      throw new EOFException(); 
  }
  
  protected void skipSourceBytes(long paramLong) {
    if (trySkipBytes(paramLong) < paramLong)
      throw new EOFException(); 
  }
  
  protected int tryReadRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return 0;
  }
  
  protected long trySkipBytes(long paramLong) {
    this.max = this.ba.getLim();
    this.position = this.ba.getPos();
    long l = (this.max - this.position);
    if (l >= paramLong) {
      this.position = (int)(this.position + paramLong);
      this.ba.setPos(this.position);
      return paramLong;
    } 
    this.position = (int)(this.position + l);
    this.ba.setPos(this.position);
    return l;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryDecoder$ByteArrayByteSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */