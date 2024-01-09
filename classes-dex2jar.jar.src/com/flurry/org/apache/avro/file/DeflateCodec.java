package com.flurry.org.apache.avro.file;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

class DeflateCodec extends Codec {
  private int compressionLevel;
  
  private Deflater deflater;
  
  private Inflater inflater;
  
  private boolean nowrap = true;
  
  private ByteArrayOutputStream outputBuffer;
  
  public DeflateCodec(int paramInt) {
    this.compressionLevel = paramInt;
  }
  
  private Deflater getDeflater() {
    if (this.deflater == null)
      this.deflater = new Deflater(this.compressionLevel, this.nowrap); 
    this.deflater.reset();
    return this.deflater;
  }
  
  private Inflater getInflater() {
    if (this.inflater == null)
      this.inflater = new Inflater(this.nowrap); 
    this.inflater.reset();
    return this.inflater;
  }
  
  private ByteArrayOutputStream getOutputBuffer(int paramInt) {
    if (this.outputBuffer == null)
      this.outputBuffer = new ByteArrayOutputStream(paramInt); 
    this.outputBuffer.reset();
    return this.outputBuffer;
  }
  
  private void writeAndClose(ByteBuffer paramByteBuffer, OutputStream paramOutputStream) {
    byte[] arrayOfByte = paramByteBuffer.array();
    int j = paramByteBuffer.arrayOffset();
    int k = paramByteBuffer.position();
    int i = paramByteBuffer.remaining();
    try {
      paramOutputStream.write(arrayOfByte, j + k, i);
      return;
    } finally {
      paramOutputStream.close();
    } 
  }
  
  ByteBuffer compress(ByteBuffer paramByteBuffer) {
    ByteArrayOutputStream byteArrayOutputStream = getOutputBuffer(paramByteBuffer.remaining());
    writeAndClose(paramByteBuffer, new DeflaterOutputStream(byteArrayOutputStream, getDeflater()));
    return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
  }
  
  ByteBuffer decompress(ByteBuffer paramByteBuffer) {
    ByteArrayOutputStream byteArrayOutputStream = getOutputBuffer(paramByteBuffer.remaining());
    writeAndClose(paramByteBuffer, new InflaterOutputStream(byteArrayOutputStream, getInflater()));
    return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if (this.nowrap != ((DeflateCodec)paramObject).nowrap)
        bool = false; 
    } 
    return bool;
  }
  
  String getName() {
    return "deflate";
  }
  
  public int hashCode() {
    return this.nowrap ? 0 : 1;
  }
  
  public String toString() {
    return getName() + "-" + this.compressionLevel;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DeflateCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */