package com.flurry.org.apache.avro.file;

import java.nio.ByteBuffer;

final class NullCodec extends Codec {
  private static final NullCodec INSTANCE = new NullCodec();
  
  public static final CodecFactory OPTION = new NullCodec$Option();
  
  ByteBuffer compress(ByteBuffer paramByteBuffer) {
    return paramByteBuffer;
  }
  
  ByteBuffer decompress(ByteBuffer paramByteBuffer) {
    return paramByteBuffer;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject && getClass() != paramObject.getClass())
      bool = false; 
    return bool;
  }
  
  String getName() {
    return "null";
  }
  
  public int hashCode() {
    return 2;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\NullCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */