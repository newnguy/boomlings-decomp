package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.Schema;

public interface DatumWriter {
  void setSchema(Schema paramSchema);
  
  void write(Object paramObject, Encoder paramEncoder);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\DatumWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */