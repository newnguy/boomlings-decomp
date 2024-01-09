package com.flurry.org.apache.avro.io;

import java.io.OutputStream;

class BufferedBinaryEncoder$OutputStreamSink extends BufferedBinaryEncoder$ByteSink {
  private final OutputStream out;
  
  private BufferedBinaryEncoder$OutputStreamSink(OutputStream paramOutputStream) {
    this.out = paramOutputStream;
  }
  
  protected void innerFlush() {
    this.out.flush();
  }
  
  protected void innerWrite(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.out.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BufferedBinaryEncoder$OutputStreamSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */