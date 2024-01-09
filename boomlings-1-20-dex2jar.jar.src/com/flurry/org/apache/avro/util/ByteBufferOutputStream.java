package com.flurry.org.apache.avro.util;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ByteBufferOutputStream extends OutputStream {
  public static final int BUFFER_SIZE = 8192;
  
  private List buffers;
  
  public ByteBufferOutputStream() {
    reset();
  }
  
  public void append(List paramList) {
    for (ByteBuffer byteBuffer : paramList)
      byteBuffer.position(byteBuffer.limit()); 
    this.buffers.addAll(paramList);
  }
  
  public List getBufferList() {
    List list = this.buffers;
    reset();
    Iterator<ByteBuffer> iterator = list.iterator();
    while (iterator.hasNext())
      ((ByteBuffer)iterator.next()).flip(); 
    return list;
  }
  
  public void prepend(List paramList) {
    for (ByteBuffer byteBuffer : paramList)
      byteBuffer.position(byteBuffer.limit()); 
    this.buffers.addAll(0, paramList);
  }
  
  public void reset() {
    this.buffers = new LinkedList();
    this.buffers.add(ByteBuffer.allocate(8192));
  }
  
  public void write(int paramInt) {
    ByteBuffer byteBuffer2 = this.buffers.get(this.buffers.size() - 1);
    ByteBuffer byteBuffer1 = byteBuffer2;
    if (byteBuffer2.remaining() < 1) {
      byteBuffer1 = ByteBuffer.allocate(8192);
      this.buffers.add(byteBuffer1);
    } 
    byteBuffer1.put((byte)paramInt);
  }
  
  public void write(ByteBuffer paramByteBuffer) {
    this.buffers.add(paramByteBuffer);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    ByteBuffer byteBuffer = this.buffers.get(this.buffers.size() - 1);
    int j = byteBuffer.remaining();
    int i = paramInt2;
    for (paramInt2 = j; i > paramInt2; paramInt2 = byteBuffer.remaining()) {
      byteBuffer.put(paramArrayOfbyte, paramInt1, paramInt2);
      i -= paramInt2;
      paramInt1 += paramInt2;
      byteBuffer = ByteBuffer.allocate(8192);
      this.buffers.add(byteBuffer);
    } 
    byteBuffer.put(paramArrayOfbyte, paramInt1, i);
  }
  
  public void writeBuffer(ByteBuffer paramByteBuffer) {
    if (paramByteBuffer.remaining() < 8192) {
      write(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining());
      return;
    } 
    ByteBuffer byteBuffer = paramByteBuffer.duplicate();
    byteBuffer.position(paramByteBuffer.limit());
    this.buffers.add(byteBuffer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avr\\util\ByteBufferOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */