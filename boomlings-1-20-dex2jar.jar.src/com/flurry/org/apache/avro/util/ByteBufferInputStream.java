package com.flurry.org.apache.avro.util;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class ByteBufferInputStream extends InputStream {
  private List buffers;
  
  private int current;
  
  public ByteBufferInputStream(List paramList) {
    this.buffers = paramList;
  }
  
  private ByteBuffer getBuffer() {
    while (this.current < this.buffers.size()) {
      ByteBuffer byteBuffer = this.buffers.get(this.current);
      if (byteBuffer.hasRemaining())
        return byteBuffer; 
      this.current++;
    } 
    throw new EOFException();
  }
  
  public int read() {
    return getBuffer().get() & 0xFF;
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return 0; 
    ByteBuffer byteBuffer = getBuffer();
    int i = byteBuffer.remaining();
    if (paramInt2 > i) {
      byteBuffer.get(paramArrayOfbyte, paramInt1, i);
      return i;
    } 
    byteBuffer.get(paramArrayOfbyte, paramInt1, paramInt2);
    return paramInt2;
  }
  
  public ByteBuffer readBuffer(int paramInt) {
    int i = 0;
    if (paramInt == 0)
      return ByteBuffer.allocate(0); 
    ByteBuffer byteBuffer1 = getBuffer();
    if (byteBuffer1.remaining() == paramInt) {
      this.current++;
      return byteBuffer1;
    } 
    ByteBuffer byteBuffer2 = ByteBuffer.allocate(paramInt);
    while (true) {
      byteBuffer1 = byteBuffer2;
      if (i < paramInt) {
        i += read(byteBuffer2.array(), i, paramInt - i);
        continue;
      } 
      return byteBuffer1;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avr\\util\ByteBufferInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */