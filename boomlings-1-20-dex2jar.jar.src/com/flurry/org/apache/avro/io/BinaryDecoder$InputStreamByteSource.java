package com.flurry.org.apache.avro.io;

import java.io.EOFException;
import java.io.InputStream;

class BinaryDecoder$InputStreamByteSource extends BinaryDecoder$ByteSource {
  private InputStream in;
  
  protected boolean isEof = false;
  
  private BinaryDecoder$InputStreamByteSource(InputStream paramInputStream) {
    this.in = paramInputStream;
  }
  
  public void close() {
    this.in.close();
  }
  
  public boolean isEof() {
    return this.isEof;
  }
  
  public int read() {
    if (this.ba.getLim() - this.ba.getPos() == 0)
      return this.in.read(); 
    int j = this.ba.getPos();
    int i = this.ba.getBuf()[j] & 0xFF;
    this.ba.setPos(j + 1);
    return i;
  }
  
  protected void readRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    while (paramInt2 > 0) {
      int i = this.in.read(paramArrayOfbyte, paramInt1, paramInt2);
      if (i < 0) {
        this.isEof = true;
        throw new EOFException();
      } 
      paramInt2 -= i;
      paramInt1 += i;
    } 
  }
  
  protected void skipSourceBytes(long paramLong) {
    boolean bool = false;
    while (paramLong > 0L) {
      long l = this.in.skip(paramLong);
      if (l > 0L) {
        paramLong -= l;
        continue;
      } 
      if (l == 0L) {
        if (bool) {
          this.isEof = true;
          throw new EOFException();
        } 
        bool = true;
        continue;
      } 
      this.isEof = true;
      throw new EOFException();
    } 
  }
  
  protected int tryReadRaw(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = paramInt2;
    while (true) {
      if (i > 0)
        try {
          int j = this.in.read(paramArrayOfbyte, paramInt1, i);
          if (j < 0) {
            this.isEof = true;
            return paramInt2 - i;
          } 
          i -= j;
          paramInt1 += j;
          continue;
        } catch (EOFException eOFException) {
          this.isEof = true;
        }  
      return paramInt2 - i;
    } 
  }
  
  protected long trySkipBytes(long paramLong) {
    boolean bool = false;
    long l = paramLong;
    while (true) {
      if (l > 0L)
        try {
          long l1 = this.in.skip(paramLong);
          if (l1 > 0L) {
            l -= l1;
            continue;
          } 
          if (l1 == 0L) {
            if (bool) {
              this.isEof = true;
              return paramLong - l;
            } 
            bool = true;
            continue;
          } 
          this.isEof = true;
        } catch (EOFException eOFException) {
          this.isEof = true;
        }  
      return paramLong - l;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryDecoder$InputStreamByteSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */