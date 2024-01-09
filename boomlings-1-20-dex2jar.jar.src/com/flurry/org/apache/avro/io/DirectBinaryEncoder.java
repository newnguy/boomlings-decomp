package com.flurry.org.apache.avro.io;

import java.io.OutputStream;

public class DirectBinaryEncoder extends BinaryEncoder {
  private final byte[] buf = new byte[12];
  
  private OutputStream out;
  
  DirectBinaryEncoder(OutputStream paramOutputStream) {
    configure(paramOutputStream);
  }
  
  public int bytesBuffered() {
    return 0;
  }
  
  DirectBinaryEncoder configure(OutputStream paramOutputStream) {
    if (paramOutputStream == null)
      throw new NullPointerException("OutputStream cannot be null!"); 
    this.out = paramOutputStream;
    return this;
  }
  
  public void flush() {
    this.out.flush();
  }
  
  public void writeBoolean(boolean paramBoolean) {
    boolean bool;
    OutputStream outputStream = this.out;
    if (paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    outputStream.write(bool);
  }
  
  public void writeDouble(double paramDouble) {
    byte[] arrayOfByte = new byte[8];
    int i = BinaryData.encodeDouble(paramDouble, arrayOfByte, 0);
    this.out.write(arrayOfByte, 0, i);
  }
  
  public void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeFloat(float paramFloat) {
    int i = BinaryData.encodeFloat(paramFloat, this.buf, 0);
    this.out.write(this.buf, 0, i);
  }
  
  public void writeInt(int paramInt) {
    int i = paramInt << 1 ^ paramInt >> 31;
    if ((i & 0xFFFFFF80) == 0) {
      this.out.write(i);
      return;
    } 
    if ((i & 0xFFFFC000) == 0) {
      this.out.write(i | 0x80);
      this.out.write(i >>> 7);
      return;
    } 
    paramInt = BinaryData.encodeInt(paramInt, this.buf, 0);
    this.out.write(this.buf, 0, paramInt);
  }
  
  public void writeLong(long paramLong) {
    long l = paramLong << 1L ^ paramLong >> 63L;
    if ((0xFFFFFFFF80000000L & l) == 0L) {
      int j;
      for (j = (int)l; (j & 0xFFFFFF80) != 0; j >>>= 7)
        this.out.write((byte)((j | 0x80) & 0xFF)); 
      this.out.write((byte)j);
      return;
    } 
    int i = BinaryData.encodeLong(paramLong, this.buf, 0);
    this.out.write(this.buf, 0, i);
  }
  
  protected void writeZero() {
    this.out.write(0);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\DirectBinaryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */