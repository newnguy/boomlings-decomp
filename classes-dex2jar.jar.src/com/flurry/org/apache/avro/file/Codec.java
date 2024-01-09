package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

abstract class Codec {
  abstract ByteBuffer compress(ByteBuffer paramByteBuffer);
  
  abstract ByteBuffer decompress(ByteBuffer paramByteBuffer);
  
  public abstract boolean equals(Object paramObject);
  
  abstract String getName();
  
  public abstract int hashCode();
  
  public String toString() {
    return getName();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\Codec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */