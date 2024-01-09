package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class BinaryDecoder extends Decoder {
  private byte[] buf = null;
  
  private int limit = 0;
  
  private int minPos = 0;
  
  private int pos = 0;
  
  private final Utf8 scratchUtf8 = new Utf8();
  
  private BinaryDecoder$ByteSource source = null;
  
  protected BinaryDecoder() {}
  
  BinaryDecoder(InputStream paramInputStream, int paramInt) {
    configure(paramInputStream, paramInt);
  }
  
  BinaryDecoder(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    configure(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  private void configureSource(int paramInt, BinaryDecoder$ByteSource paramBinaryDecoder$ByteSource) {
    if (this.source != null)
      this.source.detach(); 
    paramBinaryDecoder$ByteSource.attach(paramInt, this);
    this.source = paramBinaryDecoder$ByteSource;
  }
  
  private long doSkipItems() {
    long l;
    for (l = readInt(); l < 0L; l = readInt())
      doSkipBytes(readLong()); 
    return l;
  }
  
  private void ensureBounds(int paramInt) {
    int i = this.limit - this.pos;
    if (i < paramInt)
      this.source.compactAndFill(this.buf, this.pos, this.minPos, i); 
  }
  
  private long innerLongDecode(long paramLong) {
    int i = this.buf[this.pos] & 0xFF;
    paramLong = (i & 0x7FL) << 28L ^ paramLong;
    if (i > 127) {
      byte[] arrayOfByte = this.buf;
      int j = this.pos;
      i = 2;
      j = arrayOfByte[1 + j] & 0xFF;
      long l = paramLong ^ (j & 0x7FL) << 35L;
      paramLong = l;
      if (j > 127) {
        arrayOfByte = this.buf;
        j = this.pos;
        i = 3;
        j = arrayOfByte[j + 2] & 0xFF;
        l ^= (j & 0x7FL) << 42L;
        paramLong = l;
        if (j > 127) {
          arrayOfByte = this.buf;
          j = this.pos;
          i = 4;
          j = arrayOfByte[j + 3] & 0xFF;
          l ^= (j & 0x7FL) << 49L;
          paramLong = l;
          if (j > 127) {
            arrayOfByte = this.buf;
            j = this.pos;
            i = 5;
            j = arrayOfByte[j + 4] & 0xFF;
            l ^= (j & 0x7FL) << 56L;
            paramLong = l;
            if (j > 127) {
              arrayOfByte = this.buf;
              j = this.pos;
              i = 6;
              j = arrayOfByte[j + 5] & 0xFF;
              paramLong = l ^ (j & 0x7FL) << 63L;
              if (j > 127)
                throw new IOException("Invalid long encoding"); 
            } 
          } 
        } 
      } 
    } else {
      i = 1;
    } 
    this.pos = i + this.pos;
    return paramLong;
  }
  
  public long arrayNext() {
    return doReadItemCount();
  }
  
  BinaryDecoder configure(InputStream paramInputStream, int paramInt) {
    configureSource(paramInt, new BinaryDecoder$InputStreamByteSource(paramInputStream, null));
    return this;
  }
  
  BinaryDecoder configure(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    configureSource(8192, new BinaryDecoder$ByteArrayByteSource(paramArrayOfbyte, paramInt1, paramInt2, null));
    return this;
  }
  
  protected void doReadBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = this.limit - this.pos;
    if (paramInt2 <= i) {
      System.arraycopy(this.buf, this.pos, paramArrayOfbyte, paramInt1, paramInt2);
      this.pos += paramInt2;
      return;
    } 
    System.arraycopy(this.buf, this.pos, paramArrayOfbyte, paramInt1, i);
    this.pos = this.limit;
    this.source.readRaw(paramArrayOfbyte, paramInt1 + i, paramInt2 - i);
  }
  
  protected long doReadItemCount() {
    long l2 = readLong();
    long l1 = l2;
    if (l2 < 0L) {
      readLong();
      l1 = -l2;
    } 
    return l1;
  }
  
  protected void doSkipBytes(long paramLong) {
    int i = this.limit - this.pos;
    if (paramLong <= i) {
      this.pos = (int)(this.pos + paramLong);
      return;
    } 
    this.pos = 0;
    this.limit = 0;
    long l = i;
    this.source.skipSourceBytes(paramLong - l);
  }
  
  BinaryDecoder$BufferAccessor getBufferAccessor() {
    return new BinaryDecoder$BufferAccessor(this, null);
  }
  
  public InputStream inputStream() {
    return this.source;
  }
  
  public boolean isEnd() {
    boolean bool = false;
    if (this.limit - this.pos <= 0) {
      if (this.source.isEof())
        return true; 
      int i = this.source.tryReadRaw(this.buf, 0, this.buf.length);
      this.pos = 0;
      this.limit = i;
      if (i == 0)
        bool = true; 
    } 
    return bool;
  }
  
  public long mapNext() {
    return doReadItemCount();
  }
  
  public long readArrayStart() {
    return doReadItemCount();
  }
  
  public boolean readBoolean() {
    boolean bool = true;
    if (this.limit == this.pos) {
      this.limit = this.source.tryReadRaw(this.buf, 0, this.buf.length);
      this.pos = 0;
      if (this.limit == 0)
        throw new EOFException(); 
    } 
    byte[] arrayOfByte = this.buf;
    int i = this.pos;
    this.pos = i + 1;
    if ((arrayOfByte[i] & 0xFF) != 1)
      bool = false; 
    return bool;
  }
  
  public ByteBuffer readBytes(ByteBuffer paramByteBuffer) {
    int i = readInt();
    if (paramByteBuffer != null && i <= paramByteBuffer.capacity()) {
      paramByteBuffer.clear();
      doReadBytes(paramByteBuffer.array(), paramByteBuffer.position(), i);
      paramByteBuffer.limit(i);
      return paramByteBuffer;
    } 
    paramByteBuffer = ByteBuffer.allocate(i);
    doReadBytes(paramByteBuffer.array(), paramByteBuffer.position(), i);
    paramByteBuffer.limit(i);
    return paramByteBuffer;
  }
  
  public double readDouble() {
    ensureBounds(8);
    byte b7 = this.buf[this.pos];
    byte b1 = this.buf[1 + this.pos];
    byte b2 = this.buf[this.pos + 2];
    byte b3 = this.buf[this.pos + 3];
    byte b4 = this.buf[this.pos + 4];
    byte b8 = this.buf[this.pos + 5];
    byte b5 = this.buf[this.pos + 6];
    byte b6 = this.buf[this.pos + 7];
    if (this.pos + 8 > this.limit)
      throw new EOFException(); 
    this.pos += 8;
    long l = ((b1 & 0xFF) << 8 | b7 & 0xFF | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 24);
    return Double.longBitsToDouble((b4 & 0xFF | (b8 & 0xFF) << 8 | (b5 & 0xFF) << 16 | (b6 & 0xFF) << 24) << 32L | l & 0xFFFFFFFFL);
  }
  
  public int readEnum() {
    return readInt();
  }
  
  public void readFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    doReadBytes(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public float readFloat() {
    ensureBounds(4);
    byte b2 = this.buf[this.pos];
    byte b3 = this.buf[1 + this.pos];
    byte b1 = this.buf[this.pos + 2];
    byte b4 = this.buf[this.pos + 3];
    if (this.pos + 4 > this.limit)
      throw new EOFException(); 
    this.pos += 4;
    return Float.intBitsToFloat((b3 & 0xFF) << 8 | b2 & 0xFF | (b1 & 0xFF) << 16 | (b4 & 0xFF) << 24);
  }
  
  public int readIndex() {
    return readInt();
  }
  
  public int readInt() {
    byte b = 5;
    ensureBounds(5);
    int j = this.buf[this.pos] & 0xFF;
    int i = j & 0x7F;
    if (j > 127) {
      byte[] arrayOfByte = this.buf;
      int k = this.pos;
      j = 2;
      int m = arrayOfByte[1 + k] & 0xFF;
      k = i ^ (m & 0x7F) << 7;
      i = k;
      if (m > 127) {
        arrayOfByte = this.buf;
        i = this.pos;
        j = 3;
        m = arrayOfByte[i + 2] & 0xFF;
        k ^= (m & 0x7F) << 14;
        i = k;
        if (m > 127) {
          arrayOfByte = this.buf;
          i = this.pos;
          j = 4;
          m = arrayOfByte[i + 3] & 0xFF;
          k ^= (m & 0x7F) << 21;
          i = k;
          if (m > 127) {
            m = this.buf[this.pos + 4] & 0xFF;
            i = k ^ (m & 0x7F) << 28;
            j = b;
            if (m > 127)
              throw new IOException("Invalid int encoding"); 
          } 
        } 
      } 
    } else {
      j = 1;
    } 
    this.pos = j + this.pos;
    if (this.pos > this.limit)
      throw new EOFException(); 
    return -(i & 0x1) ^ i >>> 1;
  }
  
  public long readLong() {
    long l;
    ensureBounds(10);
    byte[] arrayOfByte = this.buf;
    int i = this.pos;
    this.pos = i + 1;
    int j = arrayOfByte[i] & 0xFF;
    i = j & 0x7F;
    if (j > 127) {
      arrayOfByte = this.buf;
      j = this.pos;
      this.pos = j + 1;
      j = arrayOfByte[j] & 0xFF;
      i ^= (j & 0x7F) << 7;
      if (j > 127) {
        arrayOfByte = this.buf;
        j = this.pos;
        this.pos = j + 1;
        j = arrayOfByte[j] & 0xFF;
        i ^= (j & 0x7F) << 14;
        if (j > 127) {
          arrayOfByte = this.buf;
          j = this.pos;
          this.pos = j + 1;
          j = arrayOfByte[j] & 0xFF;
          i ^= (j & 0x7F) << 21;
          if (j > 127) {
            l = innerLongDecode(i);
          } else {
            l = i;
          } 
        } else {
          l = i;
        } 
      } else {
        l = i;
      } 
    } else {
      l = i;
    } 
    if (this.pos > this.limit)
      throw new EOFException(); 
    return -(l & 0x1L) ^ l >>> 1L;
  }
  
  public long readMapStart() {
    return doReadItemCount();
  }
  
  public void readNull() {}
  
  public Utf8 readString(Utf8 paramUtf8) {
    int i = readInt();
    if (paramUtf8 == null)
      paramUtf8 = new Utf8(); 
    paramUtf8.setByteLength(i);
    if (i != 0)
      doReadBytes(paramUtf8.getBytes(), 0, i); 
    return paramUtf8;
  }
  
  public String readString() {
    return readString(this.scratchUtf8).toString();
  }
  
  public long skipArray() {
    return doSkipItems();
  }
  
  public void skipBytes() {
    doSkipBytes(readInt());
  }
  
  public void skipFixed(int paramInt) {
    doSkipBytes(paramInt);
  }
  
  public long skipMap() {
    return doSkipItems();
  }
  
  public void skipString() {
    doSkipBytes(readInt());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */