package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.generic.GenericData;

public abstract class SpecificFixed extends GenericData.Fixed {
  public SpecificFixed() {
    setSchema(SpecificData.get().getSchema(getClass()));
  }
  
  public SpecificFixed(byte[] paramArrayOfbyte) {
    this();
    bytes(paramArrayOfbyte);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificFixed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */