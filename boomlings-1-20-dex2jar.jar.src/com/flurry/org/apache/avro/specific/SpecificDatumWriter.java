package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumWriter;
import com.flurry.org.apache.avro.io.Encoder;

public class SpecificDatumWriter extends GenericDatumWriter {
  public SpecificDatumWriter() {
    super(SpecificData.get());
  }
  
  public SpecificDatumWriter(Schema paramSchema) {
    super(paramSchema, SpecificData.get());
  }
  
  protected SpecificDatumWriter(Schema paramSchema, SpecificData paramSpecificData) {
    super(paramSchema, paramSpecificData);
  }
  
  protected SpecificDatumWriter(SpecificData paramSpecificData) {
    super(paramSpecificData);
  }
  
  public SpecificDatumWriter(Class paramClass) {
    super(SpecificData.get().getSchema(paramClass), SpecificData.get());
  }
  
  protected void writeEnum(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    if (!(paramObject instanceof Enum)) {
      super.writeEnum(paramSchema, paramObject, paramEncoder);
      return;
    } 
    paramEncoder.writeEnum(((Enum)paramObject).ordinal());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificDatumWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */