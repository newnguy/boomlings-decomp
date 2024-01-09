package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.ByteBufferInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

class DirectBinaryDecoder extends BinaryDecoder {
  private final byte[] buf = new byte[8];
  
  private DirectBinaryDecoder$ByteReader byteReader;
  
  private InputStream in;
  
  DirectBinaryDecoder(InputStream paramInputStream) {
    configure(paramInputStream);
  }
  
  DirectBinaryDecoder configure(InputStream paramInputStream) {
    this.in = paramInputStream;
    if (paramInputStream instanceof ByteBufferInputStream) {
      DirectBinaryDecoder$ReuseByteReader directBinaryDecoder$ReuseByteReader = new DirectBinaryDecoder$ReuseByteReader(this, (ByteBufferInputStream)paramInputStream);
      this.byteReader = directBinaryDecoder$ReuseByteReader;
      return this;
    } 
    DirectBinaryDecoder$ByteReader directBinaryDecoder$ByteReader = new DirectBinaryDecoder$ByteReader(this, null);
    this.byteReader = directBinaryDecoder$ByteReader;
    return this;
  }
  
  protected void doReadBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    while (true) {
      int i = this.in.read(paramArrayOfbyte, paramInt1, paramInt2);
      if (i == paramInt2 || paramInt2 == 0)
        return; 
      if (i < 0)
        throw new EOFException(); 
      paramInt1 += i;
      paramInt2 -= i;
    } 
  }
  
  protected void doSkipBytes(long paramLong) {
    while (paramLong > 0L) {
      long l = this.in.skip(paramLong);
      if (l <= 0L)
        throw new EOFException(); 
      paramLong -= l;
    } 
  }
  
  public InputStream inputStream() {
    return this.in;
  }
  
  public boolean isEnd() {
    throw new UnsupportedOperationException();
  }
  
  public boolean readBoolean() {
    boolean bool = true;
    int i = this.in.read();
    if (i < 0)
      throw new EOFException(); 
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public ByteBuffer readBytes(ByteBuffer paramByteBuffer) {
    int i = readInt();
    return this.byteReader.read(paramByteBuffer, i);
  }
  
  public double readDouble() {
    doReadBytes(this.buf, 0, 8);
    return Double.longBitsToDouble(this.buf[0] & 0xFFL | (this.buf[1] & 0xFFL) << 8L | (this.buf[2] & 0xFFL) << 16L | (this.buf[3] & 0xFFL) << 24L | (this.buf[4] & 0xFFL) << 32L | (this.buf[5] & 0xFFL) << 40L | (this.buf[6] & 0xFFL) << 48L | (this.buf[7] & 0xFFL) << 56L);
  }
  
  public float readFloat() {
    doReadBytes(this.buf, 0, 4);
    return Float.intBitsToFloat(this.buf[0] & 0xFF | (this.buf[1] & 0xFF) << 8 | (this.buf[2] & 0xFF) << 16 | (this.buf[3] & 0xFF) << 24);
  }
  
  public int readInt() {
    int i = 0;
    int j = 0;
    while (true) {
      int k = this.in.read();
      if (k >= 0) {
        j |= (k & 0x7F) << i;
        if ((k & 0x80) == 0)
          return j >>> 1 ^ -(j & 0x1); 
      } else {
        throw new EOFException();
      } 
      k = i + 7;
      i = k;
      if (k >= 32)
        throw new IOException("Invalid int encoding"); 
    } 
  }
  
  public long readLong() {
    long l = 0L;
    int i = 0;
    while (true) {
      int j = this.in.read();
      if (j >= 0) {
        l |= (j & 0x7FL) << i;
        if ((j & 0x80) == 0)
          return -(l & 0x1L) ^ l >>> 1L; 
      } else {
        throw new EOFException();
      } 
      j = i + 7;
      i = j;
      if (j >= 64)
        throw new IOException("Invalid long encoding"); 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\DirectBinaryDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */