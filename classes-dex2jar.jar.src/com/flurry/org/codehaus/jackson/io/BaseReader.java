package com.flurry.org.codehaus.jackson.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

abstract class BaseReader extends Reader {
  protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
  
  protected static final char NULL_BYTE = '\000';
  
  protected static final char NULL_CHAR = '\000';
  
  protected byte[] _buffer;
  
  protected final IOContext _context;
  
  protected InputStream _in;
  
  protected int _length;
  
  protected int _ptr;
  
  protected char[] _tmpBuf = null;
  
  protected BaseReader(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this._context = paramIOContext;
    this._in = paramInputStream;
    this._buffer = paramArrayOfbyte;
    this._ptr = paramInt1;
    this._length = paramInt2;
  }
  
  public void close() {
    InputStream inputStream = this._in;
    if (inputStream != null) {
      this._in = null;
      freeBuffers();
      inputStream.close();
    } 
  }
  
  public final void freeBuffers() {
    byte[] arrayOfByte = this._buffer;
    if (arrayOfByte != null) {
      this._buffer = null;
      this._context.releaseReadIOBuffer(arrayOfByte);
    } 
  }
  
  public int read() {
    if (this._tmpBuf == null)
      this._tmpBuf = new char[1]; 
    return (read(this._tmpBuf, 0, 1) < 1) ? -1 : this._tmpBuf[0];
  }
  
  protected void reportBounds(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    throw new ArrayIndexOutOfBoundsException("read(buf," + paramInt1 + "," + paramInt2 + "), cbuf[" + paramArrayOfchar.length + "]");
  }
  
  protected void reportStrangeStream() {
    throw new IOException("Strange I/O stream, returned 0 bytes on read");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\BaseReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */