package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;

public class GenericData$Record implements GenericRecord, Comparable {
  private final Schema schema;
  
  private final Object[] values;
  
  public GenericData$Record(Schema paramSchema) {
    if (paramSchema == null || !Schema.Type.RECORD.equals(paramSchema.getType()))
      throw new AvroRuntimeException("Not a record schema: " + paramSchema); 
    this.schema = paramSchema;
    this.values = new Object[paramSchema.getFields().size()];
  }
  
  public GenericData$Record(GenericData$Record paramGenericData$Record, boolean paramBoolean) {
    this.schema = paramGenericData$Record.schema;
    this.values = new Object[this.schema.getFields().size()];
    if (paramBoolean) {
      for (byte b = 0; b < this.values.length; b++)
        this.values[b] = GenericData.access$000().deepCopy(((Schema.Field)this.schema.getFields().get(b)).schema(), paramGenericData$Record.values[b]); 
    } else {
      System.arraycopy(paramGenericData$Record.values, 0, this.values, 0, paramGenericData$Record.values.length);
    } 
  }
  
  public int compareTo(GenericData$Record paramGenericData$Record) {
    return GenericData.get().compare(this, paramGenericData$Record, this.schema);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof GenericData$Record))
        return false; 
      paramObject = paramObject;
      if (!this.schema.getFullName().equals(((GenericData$Record)paramObject).schema.getFullName()))
        return false; 
      if (GenericData.get().compare(this, paramObject, this.schema, true) != 0)
        bool = false; 
    } 
    return bool;
  }
  
  public Object get(int paramInt) {
    return this.values[paramInt];
  }
  
  public Object get(String paramString) {
    Schema.Field field = this.schema.getField(paramString);
    return (field == null) ? null : this.values[field.pos()];
  }
  
  public Schema getSchema() {
    return this.schema;
  }
  
  public int hashCode() {
    return GenericData.get().hashCode(this, this.schema);
  }
  
  public void put(int paramInt, Object paramObject) {
    this.values[paramInt] = paramObject;
  }
  
  public void put(String paramString, Object paramObject) {
    Schema.Field field = this.schema.getField(paramString);
    if (field == null)
      throw new AvroRuntimeException("Not a valid schema field: " + paramString); 
    this.values[field.pos()] = paramObject;
  }
  
  public String toString() {
    return GenericData.get().toString(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData$Record.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */