package com.flurry.org.apache.avro.file;

import java.io.IOException;
import java.io.InputStream;

class DataFileReader$SeekableInputStream extends InputStream implements SeekableInput {
  private SeekableInput in;
  
  private final byte[] oneByte = new byte[1];
  
  DataFileReader$SeekableInputStream(SeekableInput paramSeekableInput) {
    this.in = paramSeekableInput;
  }
  
  public int available() {
    long l = this.in.length() - this.in.tell();
    return (l > 2147483647L) ? Integer.MAX_VALUE : (int)l;
  }
  
  public void close() {
    this.in.close();
    super.close();
  }
  
  public long length() {
    return this.in.length();
  }
  
  public int read() {
    int j = read(this.oneByte, 0, 1);
    int i = j;
    if (j == 1)
      i = this.oneByte[0] & 0xFF; 
    return i;
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return this.in.read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return this.in.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void seek(long paramLong) {
    if (paramLong < 0L)
      throw new IOException("Illegal seek: " + paramLong); 
    this.in.seek(paramLong);
  }
  
  public long skip(long paramLong) {
    long l2 = this.in.tell();
    long l1 = this.in.length() - l2;
    if (l1 > paramLong) {
      this.in.seek(paramLong);
      return this.in.tell() - l2;
    } 
    this.in.seek(l1);
    return this.in.tell() - l2;
  }
  
  public long tell() {
    return this.in.tell();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileReader$SeekableInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */