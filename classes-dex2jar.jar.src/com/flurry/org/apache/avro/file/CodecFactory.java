package com.flurry.org.apache.avro.file;

import com.flurry.org.apache.avro.AvroRuntimeException;
import java.util.HashMap;
import java.util.Map;

public abstract class CodecFactory {
  private static final int DEFAULT_DEFLATE_LEVEL = -1;
  
  private static final Map REGISTERED = new HashMap<Object, Object>();
  
  static {
    addCodec("null", nullCodec());
    addCodec("deflate", deflateCodec(-1));
    addCodec("snappy", snappyCodec());
  }
  
  public static CodecFactory addCodec(String paramString, CodecFactory paramCodecFactory) {
    return REGISTERED.put(paramString, paramCodecFactory);
  }
  
  public static CodecFactory deflateCodec(int paramInt) {
    return new DeflateCodec$Option(paramInt);
  }
  
  public static CodecFactory fromString(String paramString) {
    CodecFactory codecFactory = (CodecFactory)REGISTERED.get(paramString);
    if (codecFactory == null)
      throw new AvroRuntimeException("Unrecognized codec: " + paramString); 
    return codecFactory;
  }
  
  public static CodecFactory nullCodec() {
    return NullCodec.OPTION;
  }
  
  public static CodecFactory snappyCodec() {
    return new SnappyCodec$Option();
  }
  
  protected abstract Codec createInstance();
  
  public String toString() {
    return createInstance().toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\file\CodecFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */