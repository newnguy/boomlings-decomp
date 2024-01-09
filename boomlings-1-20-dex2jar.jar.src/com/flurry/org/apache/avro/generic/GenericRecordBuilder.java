package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilderBase;
import java.io.IOException;

public class GenericRecordBuilder extends RecordBuilderBase {
  private final GenericData$Record record;
  
  public GenericRecordBuilder(Schema paramSchema) {
    super(paramSchema, GenericData.get());
    this.record = new GenericData$Record(paramSchema);
  }
  
  public GenericRecordBuilder(GenericData$Record paramGenericData$Record) {
    super(paramGenericData$Record.getSchema(), GenericData.get());
    this.record = new GenericData$Record(paramGenericData$Record, true);
    for (Schema.Field field : schema().getFields()) {
      Object object = paramGenericData$Record.get(field.pos());
      if (isValidValue(field, object))
        set(field, data().deepCopy(field.schema(), object)); 
    } 
  }
  
  public GenericRecordBuilder(GenericRecordBuilder paramGenericRecordBuilder) {
    super(paramGenericRecordBuilder, GenericData.get());
    this.record = new GenericData$Record(paramGenericRecordBuilder.record, true);
  }
  
  private Object getWithDefault(Schema.Field paramField) {
    return fieldSetFlags()[paramField.pos()] ? this.record.get(paramField.pos()) : defaultValue(paramField);
  }
  
  private GenericRecordBuilder set(Schema.Field paramField, int paramInt, Object paramObject) {
    validate(paramField, paramObject);
    this.record.put(paramInt, paramObject);
    fieldSetFlags()[paramInt] = true;
    return this;
  }
  
  public GenericData$Record build() {
    try {
      GenericData$Record genericData$Record = new GenericData$Record(schema());
      Schema.Field[] arrayOfField = fields();
      int i = arrayOfField.length;
      byte b = 0;
      while (b < i) {
        Schema.Field field = arrayOfField[b];
        try {
          Object object = getWithDefault(field);
          if (object != null)
            genericData$Record.put(field.pos(), object); 
          b++;
        } catch (IOException null) {
          throw new AvroRuntimeException(exception);
        } 
      } 
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
    return (GenericData$Record)exception;
  }
  
  protected GenericRecordBuilder clear(int paramInt) {
    this.record.put(paramInt, (Object)null);
    fieldSetFlags()[paramInt] = false;
    return this;
  }
  
  public GenericRecordBuilder clear(Schema.Field paramField) {
    return clear(paramField.pos());
  }
  
  public GenericRecordBuilder clear(String paramString) {
    return clear(schema().getField(paramString));
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (!super.equals(paramObject))
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if (this.record == null) {
        if (((GenericRecordBuilder)paramObject).record != null)
          bool = false; 
        return bool;
      } 
      if (!this.record.equals(((GenericRecordBuilder)paramObject).record))
        bool = false; 
    } 
    return bool;
  }
  
  protected Object get(int paramInt) {
    return this.record.get(paramInt);
  }
  
  public Object get(Schema.Field paramField) {
    return get(paramField.pos());
  }
  
  public Object get(String paramString) {
    return get(schema().getField(paramString));
  }
  
  protected boolean has(int paramInt) {
    return fieldSetFlags()[paramInt];
  }
  
  public boolean has(Schema.Field paramField) {
    return has(paramField.pos());
  }
  
  public boolean has(String paramString) {
    return has(schema().getField(paramString));
  }
  
  public int hashCode() {
    int j = super.hashCode();
    if (this.record == null) {
      byte b = 0;
      return b + j * 31;
    } 
    int i = this.record.hashCode();
    return i + j * 31;
  }
  
  protected GenericRecordBuilder set(int paramInt, Object paramObject) {
    return set(fields()[paramInt], paramInt, paramObject);
  }
  
  public GenericRecordBuilder set(Schema.Field paramField, Object paramObject) {
    return set(paramField, paramField.pos(), paramObject);
  }
  
  public GenericRecordBuilder set(String paramString, Object paramObject) {
    return set(schema().getField(paramString), paramObject);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericRecordBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */