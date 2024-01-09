package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.io.Flushable;
import java.nio.ByteBuffer;

public abstract class Encoder implements Flushable {
  public abstract void setItemCount(long paramLong);
  
  public abstract void startItem();
  
  public abstract void writeArrayEnd();
  
  public abstract void writeArrayStart();
  
  public abstract void writeBoolean(boolean paramBoolean);
  
  public abstract void writeBytes(ByteBuffer paramByteBuffer);
  
  public void writeBytes(byte[] paramArrayOfbyte) {
    writeBytes(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public abstract void writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public abstract void writeDouble(double paramDouble);
  
  public abstract void writeEnum(int paramInt);
  
  public void writeFixed(byte[] paramArrayOfbyte) {
    writeFixed(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public abstract void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public abstract void writeFloat(float paramFloat);
  
  public abstract void writeIndex(int paramInt);
  
  public abstract void writeInt(int paramInt);
  
  public abstract void writeLong(long paramLong);
  
  public abstract void writeMapEnd();
  
  public abstract void writeMapStart();
  
  public abstract void writeNull();
  
  public abstract void writeString(Utf8 paramUtf8);
  
  public void writeString(CharSequence paramCharSequence) {
    if (paramCharSequence instanceof Utf8) {
      writeString((Utf8)paramCharSequence);
      return;
    } 
    writeString(paramCharSequence.toString());
  }
  
  public void writeString(String paramString) {
    writeString(new Utf8(paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */