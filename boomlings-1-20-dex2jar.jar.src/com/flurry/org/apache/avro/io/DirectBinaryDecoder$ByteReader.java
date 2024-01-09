package com.flurry.org.apache.avro.io;

import java.nio.ByteBuffer;

class DirectBinaryDecoder$ByteReader {
  final DirectBinaryDecoder this$0;
  
  private DirectBinaryDecoder$ByteReader() {}
  
  public ByteBuffer read(ByteBuffer paramByteBuffer, int paramInt) {
    if (paramByteBuffer != null && paramInt <= paramByteBuffer.capacity()) {
      paramByteBuffer.clear();
      DirectBinaryDecoder.this.doReadBytes(paramByteBuffer.array(), paramByteBuffer.position(), paramInt);
      paramByteBuffer.limit(paramInt);
      return paramByteBuffer;
    } 
    paramByteBuffer = ByteBuffer.allocate(paramInt);
    DirectBinaryDecoder.this.doReadBytes(paramByteBuffer.array(), paramByteBuffer.position(), paramInt);
    paramByteBuffer.limit(paramInt);
    return paramByteBuffer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\DirectBinaryDecoder$ByteReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */