package com.flurry.org.apache.avro.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import org.xerial.snappy.Snappy;

class SnappyCodec extends Codec {
  private CRC32 crc32 = new CRC32();
  
  private SnappyCodec() {}
  
  ByteBuffer compress(ByteBuffer paramByteBuffer) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(Snappy.maxCompressedLength(paramByteBuffer.remaining()) + 4);
    int i = Snappy.compress(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining(), byteBuffer.array(), 0);
    this.crc32.reset();
    this.crc32.update(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining());
    byteBuffer.putInt(i, (int)this.crc32.getValue());
    byteBuffer.limit(i + 4);
    return byteBuffer;
  }
  
  ByteBuffer decompress(ByteBuffer paramByteBuffer) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(Snappy.uncompressedLength(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining() - 4));
    int i = Snappy.uncompress(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining() - 4, byteBuffer.array(), 0);
    byteBuffer.limit(i);
    this.crc32.reset();
    this.crc32.update(byteBuffer.array(), 0, i);
    if (paramByteBuffer.getInt(paramByteBuffer.limit() - 4) != (int)this.crc32.getValue())
      throw new IOException("Checksum failure"); 
    return byteBuffer;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject && getClass() != paramObject.getClass())
      bool = false; 
    return bool;
  }
  
  String getName() {
    return "snappy";
  }
  
  public int hashCode() {
    return getName().hashCode();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\SnappyCodec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */