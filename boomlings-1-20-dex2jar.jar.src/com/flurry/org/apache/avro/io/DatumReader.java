package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;

public interface DatumReader {
  Object read(Object paramObject, Decoder paramDecoder);
  
  void setSchema(Schema paramSchema);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\DatumReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */