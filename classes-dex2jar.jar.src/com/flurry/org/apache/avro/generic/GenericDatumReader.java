package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.ResolvingDecoder;
import com.flurry.org.apache.avro.util.Utf8;
import com.flurry.org.apache.avro.util.WeakIdentityHashMap;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericDatumReader implements DatumReader {
  private static final ThreadLocal RESOLVER_CACHE = new GenericDatumReader$1();
  
  private Schema actual;
  
  private final Thread creator;
  
  private ResolvingDecoder creatorResolver = null;
  
  private final GenericData data;
  
  private Schema expected;
  
  public GenericDatumReader() {
    this(null, null, GenericData.get());
  }
  
  public GenericDatumReader(Schema paramSchema) {
    this(paramSchema, paramSchema, GenericData.get());
  }
  
  public GenericDatumReader(Schema paramSchema1, Schema paramSchema2) {
    this(paramSchema1, paramSchema2, GenericData.get());
  }
  
  protected GenericDatumReader(Schema paramSchema1, Schema paramSchema2, GenericData paramGenericData) {
    this.actual = paramSchema1;
    this.expected = paramSchema2;
    this.data = paramGenericData;
    this.creator = Thread.currentThread();
  }
  
  public static void skip(Schema paramSchema, Decoder paramDecoder) {
    Iterator<Schema.Field> iterator;
    Schema schema;
    long l;
    switch (GenericDatumReader$2.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new RuntimeException("Unknown type: " + paramSchema);
      case 1:
        iterator = paramSchema.getFields().iterator();
        while (iterator.hasNext())
          skip(((Schema.Field)iterator.next()).schema(), paramDecoder); 
      case 2:
        paramDecoder.readInt();
      case 14:
        return;
      case 3:
        schema = iterator.getElementType();
        l = paramDecoder.skipArray();
        while (true) {
          if (l > 0L) {
            long l1;
            for (l1 = 0L; l1 < l; l1++)
              skip(schema, paramDecoder); 
            l = paramDecoder.skipArray();
          } 
        } 
      case 4:
        schema = schema.getValueType();
        l = paramDecoder.skipMap();
        while (true) {
          if (l > 0L) {
            long l1;
            for (l1 = 0L; l1 < l; l1++) {
              paramDecoder.skipString();
              skip(schema, paramDecoder);
            } 
            l = paramDecoder.skipMap();
          } 
        } 
      case 5:
        skip(schema.getTypes().get(paramDecoder.readIndex()), paramDecoder);
      case 6:
        paramDecoder.skipFixed(schema.getFixedSize());
      case 7:
        paramDecoder.skipString();
      case 8:
        paramDecoder.skipBytes();
      case 9:
        paramDecoder.readInt();
      case 10:
        paramDecoder.readLong();
      case 11:
        paramDecoder.readFloat();
      case 12:
        paramDecoder.readDouble();
      case 13:
        break;
    } 
    paramDecoder.readBoolean();
  }
  
  protected void addToArray(Object paramObject1, long paramLong, Object paramObject2) {
    ((Collection<Object>)paramObject1).add(paramObject2);
  }
  
  protected void addToMap(Object paramObject1, Object paramObject2, Object paramObject3) {
    ((Map<Object, Object>)paramObject1).put(paramObject2, paramObject3);
  }
  
  protected Object createBytes(byte[] paramArrayOfbyte) {
    return ByteBuffer.wrap(paramArrayOfbyte);
  }
  
  protected Object createEnum(String paramString, Schema paramSchema) {
    return new GenericData$EnumSymbol(paramSchema, paramString);
  }
  
  @Deprecated
  protected Object createFixed(Object paramObject, Schema paramSchema) {
    return this.data.createFixed(paramObject, paramSchema);
  }
  
  @Deprecated
  protected Object createFixed(Object paramObject, byte[] paramArrayOfbyte, Schema paramSchema) {
    return this.data.createFixed(paramObject, paramArrayOfbyte, paramSchema);
  }
  
  protected Object createString(String paramString) {
    return new Utf8(paramString);
  }
  
  public GenericData getData() {
    return this.data;
  }
  
  public Schema getExpected() {
    return this.expected;
  }
  
  protected final ResolvingDecoder getResolver(Schema paramSchema1, Schema paramSchema2) {
    WeakIdentityHashMap<Schema, ResolvingDecoder> weakIdentityHashMap;
    Thread thread = Thread.currentThread();
    if (thread == this.creator && this.creatorResolver != null)
      return this.creatorResolver; 
    Map map = (Map)((Map)RESOLVER_CACHE.get()).get(paramSchema1);
    if (map == null) {
      weakIdentityHashMap = new WeakIdentityHashMap();
      ((Map<Schema, WeakIdentityHashMap>)RESOLVER_CACHE.get()).put(paramSchema1, weakIdentityHashMap);
    } 
    ResolvingDecoder resolvingDecoder3 = (ResolvingDecoder)weakIdentityHashMap.get(paramSchema2);
    ResolvingDecoder resolvingDecoder2 = resolvingDecoder3;
    if (resolvingDecoder3 == null) {
      resolvingDecoder2 = DecoderFactory.get().resolvingDecoder(Schema.applyAliases(paramSchema1, paramSchema2), paramSchema2, null);
      weakIdentityHashMap.put(paramSchema2, resolvingDecoder2);
    } 
    ResolvingDecoder resolvingDecoder1 = resolvingDecoder2;
    if (thread == this.creator) {
      this.creatorResolver = resolvingDecoder2;
      resolvingDecoder1 = resolvingDecoder2;
    } 
    return resolvingDecoder1;
  }
  
  public Schema getSchema() {
    return this.actual;
  }
  
  protected Object newArray(Object paramObject, int paramInt, Schema paramSchema) {
    if (paramObject instanceof Collection) {
      ((Collection)paramObject).clear();
      return paramObject;
    } 
    return new GenericData$Array(paramInt, paramSchema);
  }
  
  protected Object newMap(Object<Object, Object> paramObject, int paramInt) {
    if (paramObject instanceof Map) {
      ((Map)paramObject).clear();
      return paramObject;
    } 
    return new HashMap<Object, Object>(paramInt);
  }
  
  @Deprecated
  protected Object newRecord(Object paramObject, Schema paramSchema) {
    return this.data.newRecord(paramObject, paramSchema);
  }
  
  protected Object peekArray(Object paramObject) {
    return (paramObject instanceof GenericArray) ? ((GenericArray)paramObject).peek() : null;
  }
  
  protected Object read(Object paramObject, Schema paramSchema, ResolvingDecoder paramResolvingDecoder) {
    switch (GenericDatumReader$2.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new AvroRuntimeException("Unknown type: " + paramSchema);
      case 1:
        return readRecord(paramObject, paramSchema, paramResolvingDecoder);
      case 2:
        return readEnum(paramSchema, (Decoder)paramResolvingDecoder);
      case 3:
        return readArray(paramObject, paramSchema, paramResolvingDecoder);
      case 4:
        return readMap(paramObject, paramSchema, paramResolvingDecoder);
      case 5:
        return read(paramObject, paramSchema.getTypes().get(paramResolvingDecoder.readIndex()), paramResolvingDecoder);
      case 6:
        return readFixed(paramObject, paramSchema, (Decoder)paramResolvingDecoder);
      case 7:
        return readString(paramObject, paramSchema, (Decoder)paramResolvingDecoder);
      case 8:
        return readBytes(paramObject, (Decoder)paramResolvingDecoder);
      case 9:
        return readInt(paramObject, paramSchema, (Decoder)paramResolvingDecoder);
      case 10:
        return Long.valueOf(paramResolvingDecoder.readLong());
      case 11:
        return Float.valueOf(paramResolvingDecoder.readFloat());
      case 12:
        return Double.valueOf(paramResolvingDecoder.readDouble());
      case 13:
        return Boolean.valueOf(paramResolvingDecoder.readBoolean());
      case 14:
        break;
    } 
    paramResolvingDecoder.readNull();
    return null;
  }
  
  public Object read(Object paramObject, Decoder paramDecoder) {
    ResolvingDecoder resolvingDecoder = getResolver(this.actual, this.expected);
    resolvingDecoder.configure(paramDecoder);
    paramObject = read(paramObject, this.expected, resolvingDecoder);
    resolvingDecoder.drain();
    return paramObject;
  }
  
  protected Object readArray(Object paramObject, Schema paramSchema, ResolvingDecoder paramResolvingDecoder) {
    Schema schema = paramSchema.getElementType();
    long l1 = paramResolvingDecoder.readArrayStart();
    long l2 = 0L;
    if (l1 > 0L) {
      long l;
      paramObject = newArray(paramObject, (int)l1, paramSchema);
      do {
        for (l = 0L; l < l1; l++)
          addToArray(paramObject, l2 + l, read(peekArray(paramObject), schema, paramResolvingDecoder)); 
        l2 += l1;
        l = paramResolvingDecoder.arrayNext();
        l1 = l;
      } while (l > 0L);
      return paramObject;
    } 
    return newArray(paramObject, 0, paramSchema);
  }
  
  protected Object readBytes(Object paramObject, Decoder paramDecoder) {
    if (paramObject instanceof ByteBuffer) {
      paramObject = paramObject;
      return paramDecoder.readBytes((ByteBuffer)paramObject);
    } 
    paramObject = null;
    return paramDecoder.readBytes((ByteBuffer)paramObject);
  }
  
  protected Object readEnum(Schema paramSchema, Decoder paramDecoder) {
    return createEnum(paramSchema.getEnumSymbols().get(paramDecoder.readEnum()), paramSchema);
  }
  
  protected Object readFixed(Object paramObject, Schema paramSchema, Decoder paramDecoder) {
    paramObject = this.data.createFixed(paramObject, paramSchema);
    paramDecoder.readFixed(paramObject.bytes(), 0, paramSchema.getFixedSize());
    return paramObject;
  }
  
  protected Object readInt(Object paramObject, Schema paramSchema, Decoder paramDecoder) {
    return Integer.valueOf(paramDecoder.readInt());
  }
  
  protected Object readMap(Object paramObject, Schema paramSchema, ResolvingDecoder paramResolvingDecoder) {
    Schema schema = paramSchema.getValueType();
    long l = paramResolvingDecoder.readMapStart();
    paramObject = newMap(paramObject, (int)l);
    if (l > 0L) {
      long l1;
      do {
        for (byte b = 0; b < l; b++)
          addToMap(paramObject, readString(null, paramSchema, (Decoder)paramResolvingDecoder), read(null, schema, paramResolvingDecoder)); 
        l1 = paramResolvingDecoder.mapNext();
        l = l1;
      } while (l1 > 0L);
    } 
    return paramObject;
  }
  
  protected Object readRecord(Object paramObject, Schema paramSchema, ResolvingDecoder paramResolvingDecoder) {
    Object object1 = this.data.newRecord(paramObject, paramSchema);
    Object object2 = this.data.getRecordState(object1, paramSchema);
    for (Schema.Field field : paramResolvingDecoder.readFieldOrder()) {
      int i = field.pos();
      String str = field.name();
      if (paramObject != null) {
        Object object = this.data.getField(object1, str, i, object2);
      } else {
        paramSchema = null;
      } 
      this.data.setField(object1, str, i, read(paramSchema, field.schema(), paramResolvingDecoder), object2);
    } 
    return object1;
  }
  
  protected Object readString(Object paramObject, Schema paramSchema, Decoder paramDecoder) {
    GenericData genericData = this.data;
    genericData = this.data;
    return "String".equals(paramSchema.getProp("avro.java.string")) ? paramDecoder.readString() : readString(paramObject, paramDecoder);
  }
  
  protected Object readString(Object paramObject, Decoder paramDecoder) {
    if (paramObject instanceof Utf8) {
      paramObject = paramObject;
      return paramDecoder.readString((Utf8)paramObject);
    } 
    paramObject = null;
    return paramDecoder.readString((Utf8)paramObject);
  }
  
  public void setExpected(Schema paramSchema) {
    this.expected = paramSchema;
    this.creatorResolver = null;
  }
  
  public void setSchema(Schema paramSchema) {
    this.actual = paramSchema;
    if (this.expected == null)
      this.expected = this.actual; 
    this.creatorResolver = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericDatumReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */