package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

public abstract class BinaryEncoder extends Encoder {
  public abstract int bytesBuffered();
  
  public void setItemCount(long paramLong) {
    if (paramLong > 0L)
      writeLong(paramLong); 
  }
  
  public void startItem() {}
  
  public void writeArrayEnd() {
    writeZero();
  }
  
  public void writeArrayStart() {}
  
  public void writeBytes(ByteBuffer paramByteBuffer) {
    int j = paramByteBuffer.position();
    int i = paramByteBuffer.arrayOffset();
    int k = paramByteBuffer.limit();
    writeBytes(paramByteBuffer.array(), i + j, k - j);
  }
  
  public void writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt2 == 0) {
      writeZero();
      return;
    } 
    writeInt(paramInt2);
    writeFixed(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeEnum(int paramInt) {
    writeInt(paramInt);
  }
  
  public void writeIndex(int paramInt) {
    writeInt(paramInt);
  }
  
  public void writeMapEnd() {
    writeZero();
  }
  
  public void writeMapStart() {}
  
  public void writeNull() {}
  
  public void writeString(Utf8 paramUtf8) {
    writeBytes(paramUtf8.getBytes(), 0, paramUtf8.getByteLength());
  }
  
  public void writeString(String paramString) {
    if (paramString.length() == 0) {
      writeZero();
      return;
    } 
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    writeInt(arrayOfByte.length);
    writeFixed(arrayOfByte, 0, arrayOfByte.length);
  }
  
  protected abstract void writeZero();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */