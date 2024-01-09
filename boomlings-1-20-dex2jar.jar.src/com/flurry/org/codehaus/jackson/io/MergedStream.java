package com.flurry.org.codehaus.jackson.io;

import java.io.InputStream;

public final class MergedStream extends InputStream {
  byte[] _buffer;
  
  protected final IOContext _context;
  
  final int _end;
  
  final InputStream _in;
  
  int _ptr;
  
  public MergedStream(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this._context = paramIOContext;
    this._in = paramInputStream;
    this._buffer = paramArrayOfbyte;
    this._ptr = paramInt1;
    this._end = paramInt2;
  }
  
  private void freeMergedBuffer() {
    byte[] arrayOfByte = this._buffer;
    if (arrayOfByte != null) {
      this._buffer = null;
      if (this._context != null)
        this._context.releaseReadIOBuffer(arrayOfByte); 
    } 
  }
  
  public int available() {
    return (this._buffer != null) ? (this._end - this._ptr) : this._in.available();
  }
  
  public void close() {
    freeMergedBuffer();
    this._in.close();
  }
  
  public void mark(int paramInt) {
    if (this._buffer == null)
      this._in.mark(paramInt); 
  }
  
  public boolean markSupported() {
    return (this._buffer == null && this._in.markSupported());
  }
  
  public int read() {
    if (this._buffer != null) {
      byte[] arrayOfByte = this._buffer;
      int i = this._ptr;
      this._ptr = i + 1;
      int j = arrayOfByte[i] & 0xFF;
      i = j;
      if (this._ptr >= this._end) {
        freeMergedBuffer();
        i = j;
      } 
      return i;
    } 
    return this._in.read();
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this._buffer != null) {
      int j = this._end - this._ptr;
      int i = paramInt2;
      if (paramInt2 > j)
        i = j; 
      System.arraycopy(this._buffer, this._ptr, paramArrayOfbyte, paramInt1, i);
      this._ptr += i;
      paramInt1 = i;
      if (this._ptr >= this._end) {
        freeMergedBuffer();
        paramInt1 = i;
      } 
      return paramInt1;
    } 
    return this._in.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void reset() {
    if (this._buffer == null)
      this._in.reset(); 
  }
  
  public long skip(long paramLong) {
    long l1;
    long l2;
    if (this._buffer != null) {
      int i = this._end - this._ptr;
      if (i > paramLong) {
        this._ptr += (int)paramLong;
        return paramLong;
      } 
      freeMergedBuffer();
      l1 = i + 0L;
      l2 = paramLong - i;
    } else {
      l1 = 0L;
      l2 = paramLong;
    } 
    paramLong = l1;
    if (l2 > 0L)
      paramLong = l1 + this._in.skip(l2); 
    return paramLong;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\MergedStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */