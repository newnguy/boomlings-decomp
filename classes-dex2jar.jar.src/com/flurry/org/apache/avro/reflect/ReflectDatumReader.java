package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.specific.SpecificDatumReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class ReflectDatumReader extends SpecificDatumReader {
  public ReflectDatumReader() {
    this(null, null, ReflectData.get());
  }
  
  public ReflectDatumReader(Schema paramSchema) {
    this(paramSchema, paramSchema, ReflectData.get());
  }
  
  public ReflectDatumReader(Schema paramSchema1, Schema paramSchema2) {
    this(paramSchema1, paramSchema2, ReflectData.get());
  }
  
  protected ReflectDatumReader(Schema paramSchema1, Schema paramSchema2, ReflectData paramReflectData) {
    super(paramSchema1, paramSchema2, paramReflectData);
  }
  
  public ReflectDatumReader(Class paramClass) {
    this(ReflectData.get().getSchema(paramClass));
  }
  
  protected void addToArray(Object paramObject1, long paramLong, Object paramObject2) {
    if (paramObject1 instanceof Collection) {
      ((Collection<Object>)paramObject1).add(paramObject2);
      return;
    } 
    Array.set(paramObject1, (int)paramLong, paramObject2);
  }
  
  protected Object createString(String paramString) {
    return paramString;
  }
  
  protected Object newArray(Object paramObject, int paramInt, Schema paramSchema) {
    ReflectData reflectData = ReflectData.get();
    Class clazz = ReflectData.getClassProp(paramSchema, "java-class");
    if (clazz != null) {
      if (paramObject instanceof Collection) {
        ((Collection)paramObject).clear();
        return paramObject;
      } 
      return clazz.isAssignableFrom(ArrayList.class) ? new ArrayList() : ReflectData.newInstance(clazz, paramSchema);
    } 
    clazz = ReflectData.getClassProp(paramSchema, "java-element-class");
    paramObject = clazz;
    if (clazz == null)
      paramObject = reflectData.getClass(paramSchema.getElementType()); 
    return Array.newInstance((Class<?>)paramObject, paramInt);
  }
  
  protected Object peekArray(Object paramObject) {
    return null;
  }
  
  protected Object readBytes(Object paramObject, Decoder paramDecoder) {
    ByteBuffer byteBuffer = paramDecoder.readBytes(null);
    paramObject = new byte[byteBuffer.remaining()];
    byteBuffer.get((byte[])paramObject);
    return paramObject;
  }
  
  protected Object readInt(Object paramObject, Schema paramSchema, Decoder paramDecoder) {
    Integer integer = Integer.valueOf(paramDecoder.readInt());
    String str = paramSchema.getProp("java-class");
    if (Byte.class.getName().equals(str))
      return Byte.valueOf(integer.byteValue()); 
    paramObject = integer;
    if (Short.class.getName().equals(str))
      paramObject = Short.valueOf(integer.shortValue()); 
    return paramObject;
  }
  
  protected Object readString(Object paramObject, Schema paramSchema, Decoder paramDecoder) {
    String str = (String)readString(null, paramDecoder);
    Class clazz = ReflectData.getClassProp(paramSchema, "java-class");
    paramObject = str;
    if (clazz != null)
      try {
        return clazz.getConstructor(new Class[] { String.class }).newInstance(new Object[] { str });
      } catch (NoSuchMethodException noSuchMethodException) {
        throw new AvroRuntimeException(noSuchMethodException);
      } catch (InstantiationException instantiationException) {
        throw new AvroRuntimeException(instantiationException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new AvroRuntimeException(illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        throw new AvroRuntimeException(invocationTargetException);
      }  
    return invocationTargetException;
  }
  
  protected Object readString(Object paramObject, Decoder paramDecoder) {
    return super.readString(null, paramDecoder).toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\reflect\ReflectDatumReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */