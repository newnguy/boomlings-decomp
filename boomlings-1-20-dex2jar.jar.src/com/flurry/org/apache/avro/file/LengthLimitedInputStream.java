package com.flurry.org.apache.avro.file;

import java.io.FilterInputStream;
import java.io.InputStream;

class LengthLimitedInputStream extends FilterInputStream {
  private long remaining;
  
  protected LengthLimitedInputStream(InputStream paramInputStream, long paramLong) {
    super(paramInputStream);
    this.remaining = paramLong;
  }
  
  private int remainingInt() {
    return (int)Math.min(this.remaining, 2147483647L);
  }
  
  public int available() {
    return Math.min(super.available(), remainingInt());
  }
  
  public int read() {
    if (this.remaining > 0L) {
      int j = super.read();
      int i = j;
      if (j != -1) {
        this.remaining--;
        i = j;
      } 
      return i;
    } 
    return -1;
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = -1;
    if (this.remaining == 0L)
      return i; 
    i = paramInt2;
    if (paramInt2 > this.remaining)
      i = remainingInt(); 
    paramInt1 = super.read(paramArrayOfbyte, paramInt1, i);
    if (paramInt1 != -1)
      this.remaining -= paramInt1; 
    return paramInt1;
  }
  
  public long skip(long paramLong) {
    paramLong = super.skip(Math.min(this.remaining, paramLong));
    this.remaining -= paramLong;
    return paramLong;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\LengthLimitedInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */