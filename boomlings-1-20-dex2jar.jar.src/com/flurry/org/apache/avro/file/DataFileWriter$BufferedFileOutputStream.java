package com.flurry.org.apache.avro.file;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

class DataFileWriter$BufferedFileOutputStream extends BufferedOutputStream {
  private long position;
  
  final DataFileWriter this$0;
  
  public DataFileWriter$BufferedFileOutputStream(OutputStream paramOutputStream) {
    super((OutputStream)null);
    this.out = new DataFileWriter$BufferedFileOutputStream$PositionFilter(this, paramOutputStream);
  }
  
  public long tell() {
    return this.position + this.count;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileWriter$BufferedFileOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */