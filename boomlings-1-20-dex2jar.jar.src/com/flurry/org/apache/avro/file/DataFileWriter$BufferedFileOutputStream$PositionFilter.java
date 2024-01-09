package com.flurry.org.apache.avro.file;

import java.io.FilterOutputStream;
import java.io.OutputStream;

class DataFileWriter$BufferedFileOutputStream$PositionFilter extends FilterOutputStream {
  final DataFileWriter$BufferedFileOutputStream this$1;
  
  public DataFileWriter$BufferedFileOutputStream$PositionFilter(OutputStream paramOutputStream) {
    super(paramOutputStream);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
    DataFileWriter$BufferedFileOutputStream.access$014(DataFileWriter$BufferedFileOutputStream.this, paramInt2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\file\DataFileWriter$BufferedFileOutputStream$PositionFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */