package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.io.BinaryEncoder;
import java.nio.ByteBuffer;

class DataFileStream$DataBlock {
  private int blockSize;
  
  private byte[] data;
  
  private long numEntries;
  
  private int offset = 0;
  
  private DataFileStream$DataBlock(long paramLong, int paramInt) {
    this.data = new byte[paramInt];
    this.numEntries = paramLong;
    this.blockSize = paramInt;
  }
  
  DataFileStream$DataBlock(ByteBuffer paramByteBuffer, long paramLong) {
    this.data = paramByteBuffer.array();
    this.blockSize = paramByteBuffer.remaining();
    this.offset = paramByteBuffer.arrayOffset() + paramByteBuffer.position();
    this.numEntries = paramLong;
  }
  
  void compressUsing(Codec paramCodec) {
    ByteBuffer byteBuffer = paramCodec.compress(getAsByteBuffer());
    this.data = byteBuffer.array();
    this.blockSize = byteBuffer.remaining();
  }
  
  void decompressUsing(Codec paramCodec) {
    ByteBuffer byteBuffer = paramCodec.decompress(getAsByteBuffer());
    this.data = byteBuffer.array();
    this.blockSize = byteBuffer.remaining();
  }
  
  ByteBuffer getAsByteBuffer() {
    return ByteBuffer.wrap(this.data, this.offset, this.blockSize);
  }
  
  int getBlockSize() {
    return this.blockSize;
  }
  
  byte[] getData() {
    return this.data;
  }
  
  long getNumEntries() {
    return this.numEntries;
  }
  
  void writeBlockTo(BinaryEncoder paramBinaryEncoder, byte[] paramArrayOfbyte) {
    paramBinaryEncoder.writeLong(this.numEntries);
    paramBinaryEncoder.writeLong(this.blockSize);
    paramBinaryEncoder.writeFixed(this.data, this.offset, this.blockSize);
    paramBinaryEncoder.writeFixed(paramArrayOfbyte);
    paramBinaryEncoder.flush();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileStream$DataBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */