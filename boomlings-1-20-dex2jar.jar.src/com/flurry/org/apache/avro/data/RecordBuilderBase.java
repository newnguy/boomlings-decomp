package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericData;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.apache.avro.io.parsing.ResolvingGrammarGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class RecordBuilderBase implements RecordBuilder {
  private static final ConcurrentMap DEFAULT_VALUE_CACHE = new ConcurrentHashMap<Object, Object>();
  
  private static final Schema.Field[] EMPTY_FIELDS = new Schema.Field[0];
  
  private final GenericData data;
  
  private BinaryDecoder decoder = null;
  
  private BinaryEncoder encoder = null;
  
  private final boolean[] fieldSetFlags;
  
  private final Schema.Field[] fields;
  
  private final Schema schema;
  
  protected RecordBuilderBase(Schema paramSchema, GenericData paramGenericData) {
    this.schema = paramSchema;
    this.data = paramGenericData;
    this.fields = (Schema.Field[])paramSchema.getFields().toArray((Object[])EMPTY_FIELDS);
    this.fieldSetFlags = new boolean[this.fields.length];
  }
  
  protected RecordBuilderBase(RecordBuilderBase paramRecordBuilderBase, GenericData paramGenericData) {
    this.schema = paramRecordBuilderBase.schema;
    this.data = paramGenericData;
    this.fields = (Schema.Field[])this.schema.getFields().toArray((Object[])EMPTY_FIELDS);
    this.fieldSetFlags = new boolean[paramRecordBuilderBase.fieldSetFlags.length];
    System.arraycopy(paramRecordBuilderBase.fieldSetFlags, 0, this.fieldSetFlags, 0, this.fieldSetFlags.length);
  }
  
  protected static boolean isValidValue(Schema.Field paramField, Object paramObject) {
    Iterator<Schema> iterator;
    if (paramObject != null)
      return true; 
    paramObject = paramField.schema();
    Schema.Type type = paramObject.getType();
    if (type == Schema.Type.NULL)
      return true; 
    if (type == Schema.Type.UNION) {
      iterator = paramObject.getTypes().iterator();
      while (iterator.hasNext()) {
        if (((Schema)iterator.next()).getType() == Schema.Type.NULL)
          return true; 
      } 
    } 
    boolean bool = false;
    while (iterator.hasNext()) {
      if (((Schema)iterator.next()).getType() == Schema.Type.NULL)
        return true; 
    } 
  }
  
  protected final GenericData data() {
    return this.data;
  }
  
  protected Object defaultValue(Schema.Field paramField) {
    JsonNode jsonNode = paramField.defaultValue();
    if (jsonNode == null)
      throw new AvroRuntimeException("Field " + paramField + " not set and has no default value"); 
    if (jsonNode.isNull() && (paramField.schema().getType() == Schema.Type.NULL || (paramField.schema().getType() == Schema.Type.UNION && ((Schema)paramField.schema().getTypes().get(0)).getType() == Schema.Type.NULL)))
      return null; 
    ConcurrentMap<Integer, Object> concurrentMap2 = (ConcurrentMap)DEFAULT_VALUE_CACHE.get(this.schema.getFullName());
    ConcurrentMap<Integer, Object> concurrentMap1 = concurrentMap2;
    if (concurrentMap2 == null) {
      DEFAULT_VALUE_CACHE.putIfAbsent(this.schema.getFullName(), new ConcurrentHashMap<Object, Object>(this.fields.length));
      concurrentMap1 = (ConcurrentMap)DEFAULT_VALUE_CACHE.get(this.schema.getFullName());
    } 
    Object object2 = concurrentMap1.get(Integer.valueOf(paramField.pos()));
    Object object1 = object2;
    if (object2 == null) {
      object1 = new ByteArrayOutputStream();
      this.encoder = EncoderFactory.get().binaryEncoder((OutputStream)object1, this.encoder);
      ResolvingGrammarGenerator.encode((Encoder)this.encoder, paramField.schema(), jsonNode);
      this.encoder.flush();
      this.decoder = DecoderFactory.get().binaryDecoder(object1.toByteArray(), this.decoder);
      object1 = this.data.createDatumReader(paramField.schema()).read(null, (Decoder)this.decoder);
      concurrentMap1.putIfAbsent(Integer.valueOf(paramField.pos()), object1);
    } 
    return this.data.deepCopy(paramField.schema(), object1);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (paramObject == null)
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if (!Arrays.equals(this.fieldSetFlags, ((RecordBuilderBase)paramObject).fieldSetFlags))
        return false; 
      if (this.schema == null) {
        if (((RecordBuilderBase)paramObject).schema != null)
          bool = false; 
        return bool;
      } 
      if (!this.schema.equals(((RecordBuilderBase)paramObject).schema))
        bool = false; 
    } 
    return bool;
  }
  
  protected final boolean[] fieldSetFlags() {
    return this.fieldSetFlags;
  }
  
  protected final Schema.Field[] fields() {
    return this.fields;
  }
  
  public int hashCode() {
    int j = Arrays.hashCode(this.fieldSetFlags);
    if (this.schema == null) {
      byte b = 0;
      return b + (j + 31) * 31;
    } 
    int i = this.schema.hashCode();
    return i + (j + 31) * 31;
  }
  
  protected final Schema schema() {
    return this.schema;
  }
  
  protected void validate(Schema.Field paramField, Object paramObject) {
    if (!isValidValue(paramField, paramObject) && paramField.defaultValue() == null)
      throw new AvroRuntimeException("Field " + paramField + " does not accept null values"); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\data\RecordBuilderBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */