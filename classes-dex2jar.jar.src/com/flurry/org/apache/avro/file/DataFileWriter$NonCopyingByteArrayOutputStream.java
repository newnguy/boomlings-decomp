package com.flurry.org.apache.avro.file;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

class DataFileWriter$NonCopyingByteArrayOutputStream extends ByteArrayOutputStream {
  DataFileWriter$NonCopyingByteArrayOutputStream(int paramInt) {
    super(paramInt);
  }
  
  ByteBuffer getByteArrayAsByteBuffer() {
    return ByteBuffer.wrap(this.buf, 0, this.count);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileWriter$NonCopyingByteArrayOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */