package com.flurry.org.apache.avro.io;

import java.io.OutputStream;

public class BufferedBinaryEncoder extends BinaryEncoder {
  private byte[] buf;
  
  private int bulkLimit;
  
  private int pos;
  
  private BufferedBinaryEncoder$ByteSink sink;
  
  BufferedBinaryEncoder(OutputStream paramOutputStream, int paramInt) {
    configure(paramOutputStream, paramInt);
  }
  
  private void ensureBounds(int paramInt) {
    if (this.buf.length - this.pos < paramInt)
      flushBuffer(); 
  }
  
  private void flushBuffer() {
    if (this.pos > 0) {
      this.sink.innerWrite(this.buf, 0, this.pos);
      this.pos = 0;
    } 
  }
  
  private void writeByte(int paramInt) {
    if (this.pos == this.buf.length)
      flushBuffer(); 
    byte[] arrayOfByte = this.buf;
    int i = this.pos;
    this.pos = i + 1;
    arrayOfByte[i] = (byte)(paramInt & 0xFF);
  }
  
  public int bytesBuffered() {
    return this.pos;
  }
  
  BufferedBinaryEncoder configure(OutputStream paramOutputStream, int paramInt) {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull -> 14
    //   4: new java/lang/NullPointerException
    //   7: dup
    //   8: ldc 'OutputStream cannot be null!'
    //   10: invokespecial <init> : (Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_0
    //   15: getfield sink : Lcom/flurry/org/apache/avro/io/BufferedBinaryEncoder$ByteSink;
    //   18: ifnull -> 32
    //   21: aload_0
    //   22: getfield pos : I
    //   25: ifle -> 32
    //   28: aload_0
    //   29: invokespecial flushBuffer : ()V
    //   32: aload_0
    //   33: new com/flurry/org/apache/avro/io/BufferedBinaryEncoder$OutputStreamSink
    //   36: dup
    //   37: aload_1
    //   38: aconst_null
    //   39: invokespecial <init> : (Ljava/io/OutputStream;Lcom/flurry/org/apache/avro/io/BufferedBinaryEncoder$1;)V
    //   42: putfield sink : Lcom/flurry/org/apache/avro/io/BufferedBinaryEncoder$ByteSink;
    //   45: aload_0
    //   46: iconst_0
    //   47: putfield pos : I
    //   50: aload_0
    //   51: getfield buf : [B
    //   54: ifnull -> 66
    //   57: aload_0
    //   58: getfield buf : [B
    //   61: arraylength
    //   62: iload_2
    //   63: if_icmpeq -> 73
    //   66: aload_0
    //   67: iload_2
    //   68: newarray byte
    //   70: putfield buf : [B
    //   73: aload_0
    //   74: aload_0
    //   75: getfield buf : [B
    //   78: arraylength
    //   79: iconst_1
    //   80: iushr
    //   81: putfield bulkLimit : I
    //   84: aload_0
    //   85: getfield bulkLimit : I
    //   88: sipush #512
    //   91: if_icmple -> 101
    //   94: aload_0
    //   95: sipush #512
    //   98: putfield bulkLimit : I
    //   101: aload_0
    //   102: areturn
    //   103: astore_1
    //   104: new com/flurry/org/apache/avro/AvroRuntimeException
    //   107: dup
    //   108: ldc 'Failure flushing old output'
    //   110: aload_1
    //   111: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   114: athrow
    // Exception table:
    //   from	to	target	type
    //   28	32	103	java/io/IOException
  }
  
  public void flush() {
    flushBuffer();
    this.sink.innerFlush();
  }
  
  public void writeBoolean(boolean paramBoolean) {
    if (this.buf.length == this.pos)
      flushBuffer(); 
    this.pos += BinaryData.encodeBoolean(paramBoolean, this.buf, this.pos);
  }
  
  public void writeDouble(double paramDouble) {
    ensureBounds(8);
    this.pos += BinaryData.encodeDouble(paramDouble, this.buf, this.pos);
  }
  
  public void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt2 > this.bulkLimit) {
      flushBuffer();
      this.sink.innerWrite(paramArrayOfbyte, paramInt1, paramInt2);
      return;
    } 
    ensureBounds(paramInt2);
    System.arraycopy(paramArrayOfbyte, paramInt1, this.buf, this.pos, paramInt2);
    this.pos += paramInt2;
  }
  
  public void writeFloat(float paramFloat) {
    ensureBounds(4);
    this.pos += BinaryData.encodeFloat(paramFloat, this.buf, this.pos);
  }
  
  public void writeInt(int paramInt) {
    ensureBounds(5);
    this.pos += BinaryData.encodeInt(paramInt, this.buf, this.pos);
  }
  
  public void writeLong(long paramLong) {
    ensureBounds(10);
    this.pos += BinaryData.encodeLong(paramLong, this.buf, this.pos);
  }
  
  protected void writeZero() {
    writeByte(0);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BufferedBinaryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */