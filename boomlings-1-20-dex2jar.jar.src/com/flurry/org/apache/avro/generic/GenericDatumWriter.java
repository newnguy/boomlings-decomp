package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.Encoder;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class GenericDatumWriter implements DatumWriter {
  private final GenericData data;
  
  private Schema root;
  
  public GenericDatumWriter() {
    this(GenericData.get());
  }
  
  public GenericDatumWriter(Schema paramSchema) {
    this();
    setSchema(paramSchema);
  }
  
  protected GenericDatumWriter(Schema paramSchema, GenericData paramGenericData) {
    this(paramGenericData);
    setSchema(paramSchema);
  }
  
  protected GenericDatumWriter(GenericData paramGenericData) {
    this.data = paramGenericData;
  }
  
  private void error(Schema paramSchema, Object paramObject) {
    throw new AvroTypeException("Not a " + paramSchema + ": " + paramObject);
  }
  
  protected Iterator getArrayElements(Object paramObject) {
    return ((Collection)paramObject).iterator();
  }
  
  protected long getArraySize(Object paramObject) {
    return ((Collection)paramObject).size();
  }
  
  public GenericData getData() {
    return this.data;
  }
  
  protected Iterable getMapEntries(Object paramObject) {
    return ((Map)paramObject).entrySet();
  }
  
  protected int getMapSize(Object paramObject) {
    return ((Map)paramObject).size();
  }
  
  protected NullPointerException npe(NullPointerException paramNullPointerException, String paramString) {
    Throwable throwable;
    NullPointerException nullPointerException = new NullPointerException(paramNullPointerException.getMessage() + paramString);
    if (paramNullPointerException.getCause() != null)
      throwable = paramNullPointerException.getCause(); 
    nullPointerException.initCause(throwable);
    return nullPointerException;
  }
  
  protected int resolveUnion(Schema paramSchema, Object paramObject) {
    return this.data.resolveUnion(paramSchema, paramObject);
  }
  
  public void setSchema(Schema paramSchema) {
    this.root = paramSchema;
  }
  
  protected void write(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    try {
      int i;
      switch (GenericDatumWriter$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
        default:
          error(paramSchema, paramObject);
          return;
        case 1:
          writeRecord(paramSchema, paramObject, paramEncoder);
          return;
        case 2:
          writeEnum(paramSchema, paramObject, paramEncoder);
          return;
        case 3:
          writeArray(paramSchema, paramObject, paramEncoder);
          return;
        case 4:
          writeMap(paramSchema, paramObject, paramEncoder);
          return;
        case 5:
          i = resolveUnion(paramSchema, paramObject);
          paramEncoder.writeIndex(i);
          write(paramSchema.getTypes().get(i), paramObject, paramEncoder);
          return;
        case 6:
          writeFixed(paramSchema, paramObject, paramEncoder);
          return;
        case 7:
          writeString(paramSchema, paramObject, paramEncoder);
          return;
        case 8:
          writeBytes(paramObject, paramEncoder);
          return;
        case 9:
          paramEncoder.writeInt(((Number)paramObject).intValue());
          return;
        case 10:
          paramEncoder.writeLong(((Long)paramObject).longValue());
          return;
        case 11:
          paramEncoder.writeFloat(((Float)paramObject).floatValue());
          return;
        case 12:
          paramEncoder.writeDouble(((Double)paramObject).doubleValue());
          return;
        case 13:
          paramEncoder.writeBoolean(((Boolean)paramObject).booleanValue());
          return;
        case 14:
          break;
      } 
    } catch (NullPointerException nullPointerException) {
      throw npe(nullPointerException, " of " + paramSchema.getFullName());
    } 
    paramEncoder.writeNull();
  }
  
  public void write(Object paramObject, Encoder paramEncoder) {
    write(this.root, paramObject, paramEncoder);
  }
  
  protected void writeArray(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    paramSchema = paramSchema.getElementType();
    long l = getArraySize(paramObject);
    paramEncoder.writeArrayStart();
    paramEncoder.setItemCount(l);
    paramObject = getArrayElements(paramObject);
    while (paramObject.hasNext()) {
      paramEncoder.startItem();
      write(paramSchema, paramObject.next(), paramEncoder);
    } 
    paramEncoder.writeArrayEnd();
  }
  
  protected void writeBytes(Object paramObject, Encoder paramEncoder) {
    paramEncoder.writeBytes((ByteBuffer)paramObject);
  }
  
  protected void writeEnum(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    paramEncoder.writeEnum(paramSchema.getEnumOrdinal(paramObject.toString()));
  }
  
  protected void writeFixed(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    paramEncoder.writeFixed(((GenericFixed)paramObject).bytes(), 0, paramSchema.getFixedSize());
  }
  
  protected void writeMap(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    paramSchema = paramSchema.getValueType();
    int i = getMapSize(paramObject);
    paramEncoder.writeMapStart();
    paramEncoder.setItemCount(i);
    for (Object paramObject : getMapEntries(paramObject)) {
      paramEncoder.startItem();
      writeString(paramObject.getKey(), paramEncoder);
      write(paramSchema, paramObject.getValue(), paramEncoder);
    } 
    paramEncoder.writeMapEnd();
  }
  
  protected void writeRecord(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    Object object = this.data.getRecordState(paramObject, paramSchema);
    for (Schema.Field field : paramSchema.getFields()) {
      Object object1 = this.data.getField(paramObject, field.name(), field.pos(), object);
      try {
        write(field.schema(), object1, paramEncoder);
      } catch (NullPointerException nullPointerException) {
        throw npe(nullPointerException, " in field " + field.name());
      } 
    } 
  }
  
  protected void writeString(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    writeString(paramObject, paramEncoder);
  }
  
  protected void writeString(Object paramObject, Encoder paramEncoder) {
    paramEncoder.writeString((CharSequence)paramObject);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericDatumWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */