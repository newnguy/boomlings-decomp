package com.flurry.org.apache.avro.io;

import java.io.InputStream;

abstract class BinaryDecoder$ByteSource extends InputStream {
  protected BinaryDecoder$BufferAccessor ba;
  
  protected void attach(int paramInt, BinaryDecoder paramBinaryDecoder) {
    BinaryDecoder.access$302(paramBinaryDecoder, new byte[paramInt]);
    BinaryDecoder.access$402(paramBinaryDecoder, 0);
    BinaryDecoder.access$602(paramBinaryDecoder, 0);
    BinaryDecoder.access$502(paramBinaryDecoder, 0);
    this.ba = new BinaryDecoder$BufferAccessor(paramBinaryDecoder, null);
  }
  
  public int available() {
    return this.ba.getLim() - this.ba.getPos();
  }
  
  protected void compactAndFill(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(paramArrayOfbyte, paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
    this.ba.setPos(paramInt2);
    paramInt1 = tryReadRaw(paramArrayOfbyte, paramInt2 + paramInt3, paramArrayOfbyte.length - paramInt3);
    this.ba.setLimit(paramInt1 + paramInt3);
  }
  
  protected void detach() {
    this.ba.detach();
  }
  
  abstract boolean isEof();
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int j = this.ba.getLim();
    int i = this.ba.getPos();
    byte[] arrayOfByte = this.ba.getBuf();
    j -= i;
    if (j >= paramInt2) {
      System.arraycopy(arrayOfByte, i, paramArrayOfbyte, paramInt1, paramInt2);
      this.ba.setPos(i + paramInt2);
      return paramInt2;
    } 
    System.arraycopy(arrayOfByte, i, paramArrayOfbyte, paramInt1, j);
    this.ba.setPos(i + j);
    paramInt1 = j + tryReadRaw(paramArrayOfbyte, paramInt1 + j, paramInt2 - j);
    paramInt2 = paramInt1;
    if (paramInt1 == 0)
      paramInt2 = -1; 
    return paramInt2;
  }
  
  protected abstract void readRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public long skip(long paramLong) {
    int i = this.ba.getLim();
    int k = this.ba.getPos();
    int j = i - k;
    if (j > paramLong) {
      i = (int)(k + paramLong);
      this.ba.setPos(i);
      return paramLong;
    } 
    this.ba.setPos(i);
    return trySkipBytes(paramLong - j) + j;
  }
  
  protected abstract void skipSourceBytes(long paramLong);
  
  protected abstract int tryReadRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  protected abstract long trySkipBytes(long paramLong);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryDecoder$ByteSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */