package com.flurry.org.apache.avro.file;

class DeflateCodec$Option extends CodecFactory {
  private int compressionLevel;
  
  DeflateCodec$Option(int paramInt) {
    this.compressionLevel = paramInt;
  }
  
  protected Codec createInstance() {
    return new DeflateCodec(this.compressionLevel);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\DeflateCodec$Option.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */