package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroRuntimeException;

class EncoderFactory$DefaultEncoderFactory extends EncoderFactory {
  private EncoderFactory$DefaultEncoderFactory() {}
  
  public EncoderFactory configureBlockSize(int paramInt) {
    throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
  }
  
  public EncoderFactory configureBufferSize(int paramInt) {
    throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\EncoderFactory$DefaultEncoderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */