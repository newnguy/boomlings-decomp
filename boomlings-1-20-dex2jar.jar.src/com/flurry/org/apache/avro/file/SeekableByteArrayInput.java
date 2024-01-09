package com.flurry.org.apache.avro.file;

import java.io.ByteArrayInputStream;

public class SeekableByteArrayInput extends ByteArrayInputStream implements SeekableInput {
  public SeekableByteArrayInput(byte[] paramArrayOfbyte) {
    super(paramArrayOfbyte);
  }
  
  public long length() {
    return this.count;
  }
  
  public void seek(long paramLong) {
    reset();
    skip(paramLong);
  }
  
  public long tell() {
    return this.pos;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\SeekableByteArrayInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */