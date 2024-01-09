package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

public abstract class Decoder {
  public abstract long arrayNext();
  
  public abstract long mapNext();
  
  public abstract long readArrayStart();
  
  public abstract boolean readBoolean();
  
  public abstract ByteBuffer readBytes(ByteBuffer paramByteBuffer);
  
  public abstract double readDouble();
  
  public abstract int readEnum();
  
  public void readFixed(byte[] paramArrayOfbyte) {
    readFixed(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public abstract void readFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public abstract float readFloat();
  
  public abstract int readIndex();
  
  public abstract int readInt();
  
  public abstract long readLong();
  
  public abstract long readMapStart();
  
  public abstract void readNull();
  
  public abstract Utf8 readString(Utf8 paramUtf8);
  
  public abstract String readString();
  
  public abstract long skipArray();
  
  public abstract void skipBytes();
  
  public abstract void skipFixed(int paramInt);
  
  public abstract long skipMap();
  
  public abstract void skipString();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */