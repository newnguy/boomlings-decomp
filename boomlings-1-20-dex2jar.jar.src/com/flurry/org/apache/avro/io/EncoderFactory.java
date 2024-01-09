package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import java.io.OutputStream;

public class EncoderFactory {
  private static final int DEFAULT_BLOCK_BUFFER_SIZE = 65536;
  
  private static final int DEFAULT_BUFFER_SIZE = 2048;
  
  private static final EncoderFactory DEFAULT_FACTORY = new EncoderFactory$DefaultEncoderFactory(null);
  
  private static final int MAX_BLOCK_BUFFER_SIZE = 1073741824;
  
  private static final int MIN_BLOCK_BUFFER_SIZE = 64;
  
  protected int binaryBlockSize = 65536;
  
  protected int binaryBufferSize = 2048;
  
  public static EncoderFactory get() {
    return DEFAULT_FACTORY;
  }
  
  public BinaryEncoder binaryEncoder(OutputStream paramOutputStream, BinaryEncoder paramBinaryEncoder) {
    return (paramBinaryEncoder == null || !paramBinaryEncoder.getClass().equals(BufferedBinaryEncoder.class)) ? new BufferedBinaryEncoder(paramOutputStream, this.binaryBufferSize) : ((BufferedBinaryEncoder)paramBinaryEncoder).configure(paramOutputStream, this.binaryBufferSize);
  }
  
  public BinaryEncoder blockingBinaryEncoder(OutputStream paramOutputStream, BinaryEncoder paramBinaryEncoder) {
    return (paramBinaryEncoder == null || !paramBinaryEncoder.getClass().equals(BlockingBinaryEncoder.class)) ? new BlockingBinaryEncoder(paramOutputStream, this.binaryBlockSize, 32) : ((BlockingBinaryEncoder)paramBinaryEncoder).configure(paramOutputStream, this.binaryBlockSize, 32);
  }
  
  public EncoderFactory configureBlockSize(int paramInt) {
    int i = 1073741824;
    byte b = 64;
    if (paramInt < 64)
      paramInt = b; 
    if (paramInt > 1073741824)
      paramInt = i; 
    this.binaryBufferSize = paramInt;
    return this;
  }
  
  public EncoderFactory configureBufferSize(int paramInt) {
    int i = 16777216;
    byte b = 32;
    if (paramInt < 32)
      paramInt = b; 
    if (paramInt > 16777216)
      paramInt = i; 
    this.binaryBufferSize = paramInt;
    return this;
  }
  
  public BinaryEncoder directBinaryEncoder(OutputStream paramOutputStream, BinaryEncoder paramBinaryEncoder) {
    return (paramBinaryEncoder == null || !paramBinaryEncoder.getClass().equals(DirectBinaryEncoder.class)) ? new DirectBinaryEncoder(paramOutputStream) : ((DirectBinaryEncoder)paramBinaryEncoder).configure(paramOutputStream);
  }
  
  public int getBlockSize() {
    return this.binaryBlockSize;
  }
  
  public int getBufferSize() {
    return this.binaryBufferSize;
  }
  
  public JsonEncoder jsonEncoder(Schema paramSchema, JsonGenerator paramJsonGenerator) {
    return new JsonEncoder(paramSchema, paramJsonGenerator);
  }
  
  public JsonEncoder jsonEncoder(Schema paramSchema, OutputStream paramOutputStream) {
    return new JsonEncoder(paramSchema, paramOutputStream);
  }
  
  public ValidatingEncoder validatingEncoder(Schema paramSchema, Encoder paramEncoder) {
    return new ValidatingEncoder(paramSchema, paramEncoder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\EncoderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */