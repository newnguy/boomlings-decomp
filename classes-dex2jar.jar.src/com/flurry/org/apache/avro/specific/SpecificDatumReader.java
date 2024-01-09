package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumReader;

public class SpecificDatumReader extends GenericDatumReader {
  public SpecificDatumReader() {
    this((Schema)null, (Schema)null, SpecificData.get());
  }
  
  public SpecificDatumReader(Schema paramSchema) {
    this(paramSchema, paramSchema, SpecificData.get());
  }
  
  public SpecificDatumReader(Schema paramSchema1, Schema paramSchema2) {
    this(paramSchema1, paramSchema2, SpecificData.get());
  }
  
  public SpecificDatumReader(Schema paramSchema1, Schema paramSchema2, SpecificData paramSpecificData) {
    super(paramSchema1, paramSchema2, paramSpecificData);
  }
  
  public SpecificDatumReader(Class paramClass) {
    this(SpecificData.get().getSchema(paramClass));
  }
  
  protected Object createEnum(String paramString, Schema paramSchema) {
    Class<String> clazz = getSpecificData().getClass(paramSchema);
    return (clazz == null) ? super.createEnum(paramString, paramSchema) : Enum.valueOf(clazz, paramString);
  }
  
  public SpecificData getSpecificData() {
    return (SpecificData)getData();
  }
  
  public void setSchema(Schema paramSchema) {
    if (getExpected() == null && paramSchema != null && paramSchema.getType() == Schema.Type.RECORD) {
      SpecificData specificData = getSpecificData();
      Class<?> clazz = specificData.getClass(paramSchema);
      if (clazz != null && SpecificRecord.class.isAssignableFrom(clazz))
        setExpected(specificData.getSchema(clazz)); 
    } 
    super.setSchema(paramSchema);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificDatumReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */