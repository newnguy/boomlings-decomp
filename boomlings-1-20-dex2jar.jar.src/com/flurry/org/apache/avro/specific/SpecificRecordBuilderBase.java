package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilderBase;

public abstract class SpecificRecordBuilderBase extends RecordBuilderBase {
  protected SpecificRecordBuilderBase(Schema paramSchema) {
    super(paramSchema, SpecificData.get());
  }
  
  protected SpecificRecordBuilderBase(SpecificRecord paramSpecificRecord) {
    super(paramSpecificRecord.getSchema(), SpecificData.get());
  }
  
  protected SpecificRecordBuilderBase(SpecificRecordBuilderBase paramSpecificRecordBuilderBase) {
    super(paramSpecificRecordBuilderBase, SpecificData.get());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificRecordBuilderBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */