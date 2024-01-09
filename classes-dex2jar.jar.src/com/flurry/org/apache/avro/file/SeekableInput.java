package com.flurry.org.apache.avro.file;

import java.io.Closeable;

public interface SeekableInput extends Closeable {
  long length();
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  void seek(long paramLong);
  
  long tell();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\SeekableInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */