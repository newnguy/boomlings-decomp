package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.util.ByteBufferInputStream;
import java.nio.ByteBuffer;

class DirectBinaryDecoder$ReuseByteReader extends DirectBinaryDecoder$ByteReader {
  private final ByteBufferInputStream bbi;
  
  final DirectBinaryDecoder this$0;
  
  public DirectBinaryDecoder$ReuseByteReader(ByteBufferInputStream paramByteBufferInputStream) {
    super(paramDirectBinaryDecoder, null);
    this.bbi = paramByteBufferInputStream;
  }
  
  public ByteBuffer read(ByteBuffer paramByteBuffer, int paramInt) {
    return (paramByteBuffer != null) ? super.read(paramByteBuffer, paramInt) : this.bbi.readBuffer(paramInt);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\DirectBinaryDecoder$ReuseByteReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */