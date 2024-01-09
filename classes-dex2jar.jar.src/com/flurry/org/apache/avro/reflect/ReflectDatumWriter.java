package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.specific.SpecificDatumWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public class ReflectDatumWriter extends SpecificDatumWriter {
  public ReflectDatumWriter() {
    this(ReflectData.get());
  }
  
  public ReflectDatumWriter(Schema paramSchema) {
    this(paramSchema, ReflectData.get());
  }
  
  protected ReflectDatumWriter(Schema paramSchema, ReflectData paramReflectData) {
    super(paramSchema, paramReflectData);
  }
  
  protected ReflectDatumWriter(ReflectData paramReflectData) {
    super(paramReflectData);
  }
  
  public ReflectDatumWriter(Class paramClass) {
    this(paramClass, ReflectData.get());
  }
  
  public ReflectDatumWriter(Class paramClass, ReflectData paramReflectData) {
    this(paramReflectData.getSchema(paramClass), paramReflectData);
  }
  
  protected Iterator getArrayElements(Object paramObject) {
    return (paramObject instanceof Collection) ? ((Collection)paramObject).iterator() : new ReflectDatumWriter$1(this, paramObject);
  }
  
  protected long getArraySize(Object paramObject) {
    return (paramObject instanceof Collection) ? ((Collection)paramObject).size() : Array.getLength(paramObject);
  }
  
  protected void write(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    Object object;
    if (paramObject instanceof Byte) {
      object = Integer.valueOf(((Byte)paramObject).intValue());
    } else {
      object = paramObject;
      if (paramObject instanceof Short)
        object = Integer.valueOf(((Short)paramObject).intValue()); 
    } 
    try {
      super.write(paramSchema, object, paramEncoder);
      return;
    } catch (NullPointerException null) {
      Throwable throwable;
      NullPointerException nullPointerException = new NullPointerException("in " + paramSchema.getFullName() + " " + throwable.getMessage());
      if (throwable.getCause() != null)
        throwable = throwable.getCause(); 
      nullPointerException.initCause(throwable);
      throw nullPointerException;
    } 
  }
  
  protected void writeBytes(Object paramObject, Encoder paramEncoder) {
    if (paramObject instanceof byte[]) {
      paramEncoder.writeBytes((byte[])paramObject);
      return;
    } 
    super.writeBytes(paramObject, paramEncoder);
  }
  
  protected void writeString(Schema paramSchema, Object paramObject, Encoder paramEncoder) {
    Object object = paramObject;
    if (paramSchema.getProp("java-class") != null)
      object = paramObject.toString(); 
    writeString(object, paramEncoder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\reflect\ReflectDatumWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */