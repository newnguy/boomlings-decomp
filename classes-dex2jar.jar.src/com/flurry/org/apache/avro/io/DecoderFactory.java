package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;
import java.io.InputStream;

public class DecoderFactory {
  static final int DEFAULT_BUFFER_SIZE = 8192;
  
  private static final DecoderFactory DEFAULT_FACTORY = new DecoderFactory$DefaultDecoderFactory(null);
  
  int binaryDecoderBufferSize = 8192;
  
  @Deprecated
  public static DecoderFactory defaultFactory() {
    return get();
  }
  
  public static DecoderFactory get() {
    return DEFAULT_FACTORY;
  }
  
  public BinaryDecoder binaryDecoder(InputStream paramInputStream, BinaryDecoder paramBinaryDecoder) {
    return (paramBinaryDecoder == null || !paramBinaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(paramInputStream, this.binaryDecoderBufferSize) : paramBinaryDecoder.configure(paramInputStream, this.binaryDecoderBufferSize);
  }
  
  public BinaryDecoder binaryDecoder(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, BinaryDecoder paramBinaryDecoder) {
    return (paramBinaryDecoder == null || !paramBinaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(paramArrayOfbyte, paramInt1, paramInt2) : paramBinaryDecoder.configure(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public BinaryDecoder binaryDecoder(byte[] paramArrayOfbyte, BinaryDecoder paramBinaryDecoder) {
    return binaryDecoder(paramArrayOfbyte, 0, paramArrayOfbyte.length, paramBinaryDecoder);
  }
  
  public DecoderFactory configureDecoderBufferSize(int paramInt) {
    int i = 16777216;
    byte b = 32;
    if (paramInt < 32)
      paramInt = b; 
    if (paramInt > 16777216)
      paramInt = i; 
    this.binaryDecoderBufferSize = paramInt;
    return this;
  }
  
  @Deprecated
  public BinaryDecoder createBinaryDecoder(InputStream paramInputStream, BinaryDecoder paramBinaryDecoder) {
    return binaryDecoder(paramInputStream, paramBinaryDecoder);
  }
  
  @Deprecated
  public BinaryDecoder createBinaryDecoder(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, BinaryDecoder paramBinaryDecoder) {
    return (paramBinaryDecoder == null || !paramBinaryDecoder.getClass().equals(BinaryDecoder.class)) ? new BinaryDecoder(paramArrayOfbyte, paramInt1, paramInt2) : paramBinaryDecoder.configure(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  @Deprecated
  public BinaryDecoder createBinaryDecoder(byte[] paramArrayOfbyte, BinaryDecoder paramBinaryDecoder) {
    return binaryDecoder(paramArrayOfbyte, 0, paramArrayOfbyte.length, paramBinaryDecoder);
  }
  
  public BinaryDecoder directBinaryDecoder(InputStream paramInputStream, BinaryDecoder paramBinaryDecoder) {
    return (paramBinaryDecoder == null || !paramBinaryDecoder.getClass().equals(DirectBinaryDecoder.class)) ? new DirectBinaryDecoder(paramInputStream) : ((DirectBinaryDecoder)paramBinaryDecoder).configure(paramInputStream);
  }
  
  public int getConfiguredBufferSize() {
    return this.binaryDecoderBufferSize;
  }
  
  public JsonDecoder jsonDecoder(Schema paramSchema, InputStream paramInputStream) {
    return new JsonDecoder(paramSchema, paramInputStream);
  }
  
  public JsonDecoder jsonDecoder(Schema paramSchema, String paramString) {
    return new JsonDecoder(paramSchema, paramString);
  }
  
  public ResolvingDecoder resolvingDecoder(Schema paramSchema1, Schema paramSchema2, Decoder paramDecoder) {
    return new ResolvingDecoder(paramSchema1, paramSchema2, paramDecoder);
  }
  
  public ValidatingDecoder validatingDecoder(Schema paramSchema, Decoder paramDecoder) {
    return new ValidatingDecoder(paramSchema, paramDecoder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\DecoderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */