package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.UnresolvedUnionException;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericData {
  private static final GenericData INSTANCE = new GenericData();
  
  private static final Schema STRINGS = Schema.create(Schema.Type.STRING);
  
  protected static final String STRING_PROP = "avro.java.string";
  
  protected static final String STRING_TYPE_STRING = "String";
  
  public static GenericData get() {
    return INSTANCE;
  }
  
  public static void setStringType(Schema paramSchema, GenericData$StringType paramGenericData$StringType) {
    if (paramGenericData$StringType == GenericData$StringType.String)
      paramSchema.addProp("avro.java.string", "String"); 
  }
  
  private void writeEscapedString(String paramString, StringBuilder paramStringBuilder) {
    for (byte b = 0;; b++) {
      if (b < paramString.length()) {
        char c = paramString.charAt(b);
        switch (c) {
          default:
            if ((c >= '\000' && c <= '\037') || (c >= '' && c <= '') || (c >= ' ' && c <= '⃿')) {
              Integer.toHexString(c);
              paramStringBuilder.append("\\u");
              for (byte b1 = 0; b1 < 4 - paramStringBuilder.length(); b1++)
                paramStringBuilder.append('0'); 
              paramStringBuilder.append(paramString.toUpperCase());
            } else {
              break;
            } 
            b++;
            continue;
          case '"':
            paramStringBuilder.append("\\\"");
            b++;
            continue;
          case '\\':
            paramStringBuilder.append("\\\\");
            b++;
            continue;
          case '\b':
            paramStringBuilder.append("\\b");
            b++;
            continue;
          case '\f':
            paramStringBuilder.append("\\f");
            b++;
            continue;
          case '\n':
            paramStringBuilder.append("\\n");
            b++;
            continue;
          case '\r':
            paramStringBuilder.append("\\r");
            b++;
            continue;
          case '\t':
            paramStringBuilder.append("\\t");
            b++;
            continue;
          case '/':
            paramStringBuilder.append("\\/");
            b++;
            continue;
        } 
        paramStringBuilder.append(c);
      } else {
        break;
      } 
    } 
  }
  
  public int compare(Object paramObject1, Object paramObject2, Schema paramSchema) {
    return compare(paramObject1, paramObject2, paramSchema, false);
  }
  
  protected int compare(Object paramObject1, Object paramObject2, Schema paramSchema, boolean paramBoolean) {
    Schema.Field field;
    Schema schema;
    Iterator<Schema.Field> iterator;
    boolean bool = true;
    int j = 0;
    if (paramObject1 == paramObject2)
      int k = j; 
    int i = j;
    switch (GenericData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      case 14:
        return i;
      default:
        i = ((Comparable<Object>)paramObject1).compareTo(paramObject2);
      case 1:
        iterator = paramSchema.getFields().iterator();
        while (true) {
          i = j;
          if (iterator.hasNext()) {
            field = iterator.next();
            if (field.order() != Schema.Field.Order.IGNORE) {
              i = field.pos();
              String str = field.name();
              i = compare(getField(paramObject1, str, i), getField(paramObject2, str, i), field.schema(), paramBoolean);
              if (i != 0)
                if (field.order() == Schema.Field.Order.DESCENDING)
                  i = -i;  
            } 
          } 
        } 
      case 2:
        i = field.getEnumOrdinal(paramObject1.toString()) - field.getEnumOrdinal(paramObject2.toString());
      case 3:
        paramObject1 = paramObject1;
        paramObject2 = paramObject2;
        paramObject1 = paramObject1.iterator();
        paramObject2 = paramObject2.iterator();
        schema = field.getElementType();
        while (paramObject1.hasNext() && paramObject2.hasNext()) {
          i = compare(paramObject1.next(), paramObject2.next(), schema, paramBoolean);
          if (i != 0)
            return i; 
        } 
        if (paramObject1.hasNext())
          i = bool; 
        if (paramObject2.hasNext())
          i = -1; 
        i = 0;
      case 4:
        if (paramBoolean) {
          i = j;
          if (!((Map)paramObject1).equals(paramObject2))
            i = 1; 
        } 
        throw new AvroRuntimeException("Can't compare maps!");
      case 5:
        i = resolveUnion(schema, paramObject1);
        j = resolveUnion(schema, paramObject2);
        if (i == j)
          i = compare(paramObject1, paramObject2, schema.getTypes().get(i), paramBoolean); 
        i -= j;
      case 7:
        break;
    } 
    if (paramObject1 instanceof Utf8) {
      paramObject1 = paramObject1;
    } else {
      paramObject1 = new Utf8(paramObject1.toString());
    } 
    if (paramObject2 instanceof Utf8) {
      paramObject2 = paramObject2;
    } else {
      paramObject2 = new Utf8(paramObject2.toString());
    } 
    i = paramObject1.compareTo((Utf8)paramObject2);
  }
  
  public DatumReader createDatumReader(Schema paramSchema) {
    return new GenericDatumReader(paramSchema, paramSchema, this);
  }
  
  public Object createFixed(Object paramObject, Schema paramSchema) {
    if (!(paramObject instanceof GenericFixed) || (((GenericFixed)paramObject).bytes()).length != paramSchema.getFixedSize())
      paramObject = new GenericData$Fixed(paramSchema); 
    return paramObject;
  }
  
  public Object createFixed(Object paramObject, byte[] paramArrayOfbyte, Schema paramSchema) {
    paramObject = createFixed(paramObject, paramSchema);
    System.arraycopy(paramArrayOfbyte, 0, paramObject.bytes(), 0, paramSchema.getFixedSize());
    return paramObject;
  }
  
  public Object deepCopy(Schema paramSchema, Object<Object, Object> paramObject) {
    ByteBuffer byteBuffer;
    Schema.Field field;
    Iterator<Map.Entry> iterator;
    if (paramObject == null)
      Object object1 = null; 
    Object<Object, Object> object = paramObject;
    switch (GenericData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      case 2:
        return object;
      default:
        throw new AvroRuntimeException("Deep copy failed for schema \"" + paramSchema + "\" and value \"" + paramObject + "\"");
      case 3:
        paramObject = paramObject;
        object = (Object<Object, Object>)new GenericData$Array(paramObject.size(), paramSchema);
        paramObject = (Object<Object, Object>)paramObject.iterator();
        while (true) {
          if (paramObject.hasNext()) {
            Object object1 = paramObject.next();
            object.add(deepCopy(paramSchema.getElementType(), object1));
          } 
        } 
      case 13:
        object = (Object<Object, Object>)new Boolean(((Boolean)paramObject).booleanValue());
      case 8:
        byteBuffer = (ByteBuffer)paramObject;
        paramObject = (Object<Object, Object>)new byte[byteBuffer.capacity()];
        byteBuffer.rewind();
        byteBuffer.get((byte[])paramObject);
        byteBuffer.rewind();
        object = (Object<Object, Object>)ByteBuffer.wrap((byte[])paramObject);
      case 12:
        object = (Object<Object, Object>)new Double(((Double)paramObject).doubleValue());
      case 6:
        object = (Object<Object, Object>)createFixed(null, ((GenericFixed)paramObject).bytes(), (Schema)byteBuffer);
      case 11:
        object = (Object<Object, Object>)new Float(((Float)paramObject).floatValue());
      case 9:
        object = (Object<Object, Object>)new Integer(((Integer)paramObject).intValue());
      case 10:
        object = (Object<Object, Object>)new Long(((Long)paramObject).longValue());
      case 4:
        paramObject = paramObject;
        object = (Object<Object, Object>)new HashMap<Object, Object>(paramObject.size());
        iterator = paramObject.entrySet().iterator();
        while (true) {
          if (iterator.hasNext()) {
            paramObject = (Object<Object, Object>)iterator.next();
            object.put(deepCopy(STRINGS, paramObject.getKey()), deepCopy(byteBuffer.getValueType(), paramObject.getValue()));
          } 
        } 
      case 14:
        object = null;
      case 1:
        paramObject = paramObject;
        object = (Object<Object, Object>)newRecord(null, (Schema)byteBuffer);
        iterator = byteBuffer.getFields().iterator();
        while (true) {
          if (iterator.hasNext()) {
            field = (Schema.Field)iterator.next();
            object.put(field.pos(), deepCopy(field.schema(), paramObject.get(field.pos())));
          } 
        } 
      case 7:
        object = paramObject;
        if (!(paramObject instanceof String)) {
          if (paramObject instanceof Utf8)
            Utf8 utf81 = new Utf8((Utf8)paramObject); 
          Utf8 utf8 = new Utf8(paramObject.toString());
        } 
      case 5:
        break;
    } 
    object = (Object<Object, Object>)deepCopy(field.getTypes().get(resolveUnion((Schema)field, paramObject)), paramObject);
  }
  
  protected Schema getEnumSchema(Object paramObject) {
    return ((GenericContainer)paramObject).getSchema();
  }
  
  public Object getField(Object paramObject, String paramString, int paramInt) {
    return ((IndexedRecord)paramObject).get(paramInt);
  }
  
  protected Object getField(Object paramObject1, String paramString, int paramInt, Object paramObject2) {
    return getField(paramObject1, paramString, paramInt);
  }
  
  protected Schema getFixedSchema(Object paramObject) {
    return ((GenericContainer)paramObject).getSchema();
  }
  
  protected Schema getRecordSchema(Object paramObject) {
    return ((GenericContainer)paramObject).getSchema();
  }
  
  protected Object getRecordState(Object paramObject, Schema paramSchema) {
    return null;
  }
  
  protected String getSchemaName(Object paramObject) {
    if (paramObject == null)
      return Schema.Type.NULL.getName(); 
    if (isRecord(paramObject))
      return getRecordSchema(paramObject).getFullName(); 
    if (isEnum(paramObject))
      return getEnumSchema(paramObject).getFullName(); 
    if (isArray(paramObject))
      return Schema.Type.ARRAY.getName(); 
    if (isMap(paramObject))
      return Schema.Type.MAP.getName(); 
    if (isFixed(paramObject))
      return getFixedSchema(paramObject).getFullName(); 
    if (isString(paramObject))
      return Schema.Type.STRING.getName(); 
    if (isBytes(paramObject))
      return Schema.Type.BYTES.getName(); 
    if (paramObject instanceof Integer)
      return Schema.Type.INT.getName(); 
    if (paramObject instanceof Long)
      return Schema.Type.LONG.getName(); 
    if (paramObject instanceof Float)
      return Schema.Type.FLOAT.getName(); 
    if (paramObject instanceof Double)
      return Schema.Type.DOUBLE.getName(); 
    if (paramObject instanceof Boolean)
      return Schema.Type.BOOLEAN.getName(); 
    throw new AvroRuntimeException("Unknown datum type: " + paramObject);
  }
  
  public int hashCode(Object paramObject, Schema paramSchema) {
    Schema.Field field;
    Schema schema;
    Iterator<Schema.Field> iterator;
    int i = 0;
    if (paramObject == null);
    int j = 1;
    switch (GenericData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      case 14:
        return i;
      default:
        i = paramObject.hashCode();
      case 1:
        iterator = paramSchema.getFields().iterator();
        j = 1;
        while (true) {
          i = j;
          if (iterator.hasNext()) {
            field = iterator.next();
            if (field.order() != Schema.Field.Order.IGNORE)
              j = hashCodeAdd(j, getField(paramObject, field.name(), field.pos()), field.schema()); 
          } 
        } 
      case 3:
        paramObject = paramObject;
        schema = field.getElementType();
        paramObject = paramObject.iterator();
        i = j;
        while (true) {
          if (paramObject.hasNext())
            i = hashCodeAdd(i, paramObject.next(), schema); 
        } 
      case 5:
        i = hashCode(paramObject, schema.getTypes().get(resolveUnion(schema, paramObject)));
      case 2:
        i = schema.getEnumOrdinal(paramObject.toString());
      case 7:
        break;
    } 
    if (!(paramObject instanceof Utf8))
      paramObject = new Utf8(paramObject.toString()); 
    i = paramObject.hashCode();
  }
  
  protected int hashCodeAdd(int paramInt, Object paramObject, Schema paramSchema) {
    return paramInt * 31 + hashCode(paramObject, paramSchema);
  }
  
  public Schema induce(Object paramObject) {
    Schema schema = null;
    if (isRecord(paramObject))
      return getRecordSchema(paramObject); 
    if (paramObject instanceof Collection) {
      Iterator<Object> iterator = ((Collection)paramObject).iterator();
      schema = null;
      while (iterator.hasNext()) {
        Object object = iterator.next();
        if (schema == null) {
          schema = induce(object);
          continue;
        } 
        if (!schema.equals(induce(object)))
          throw new AvroTypeException("No mixed type arrays."); 
      } 
      if (schema == null)
        throw new AvroTypeException("Empty array: " + paramObject); 
      return Schema.createArray(schema);
    } 
    if (paramObject instanceof Map) {
      for (Map.Entry entry : ((Map)paramObject).entrySet()) {
        if (schema == null) {
          schema = induce(entry.getValue());
          continue;
        } 
        if (!schema.equals(induce(entry.getValue())))
          throw new AvroTypeException("No mixed type map values."); 
      } 
      if (schema == null)
        throw new AvroTypeException("Empty map: " + paramObject); 
      return Schema.createMap(schema);
    } 
    if (paramObject instanceof GenericFixed)
      return Schema.createFixed(null, null, null, (((GenericFixed)paramObject).bytes()).length); 
    if (paramObject instanceof CharSequence)
      return Schema.create(Schema.Type.STRING); 
    if (paramObject instanceof ByteBuffer)
      return Schema.create(Schema.Type.BYTES); 
    if (paramObject instanceof Integer)
      return Schema.create(Schema.Type.INT); 
    if (paramObject instanceof Long)
      return Schema.create(Schema.Type.LONG); 
    if (paramObject instanceof Float)
      return Schema.create(Schema.Type.FLOAT); 
    if (paramObject instanceof Double)
      return Schema.create(Schema.Type.DOUBLE); 
    if (paramObject instanceof Boolean)
      return Schema.create(Schema.Type.BOOLEAN); 
    if (paramObject == null)
      return Schema.create(Schema.Type.NULL); 
    throw new AvroTypeException("Can't create schema for: " + paramObject);
  }
  
  protected boolean instanceOf(Schema paramSchema, Object paramObject) {
    boolean bool = true;
    null = true;
    boolean bool1 = false;
    switch (GenericData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new AvroRuntimeException("Unexpected type: " + paramSchema);
      case 1:
        if (!isRecord(paramObject))
          return bool1; 
        if (paramSchema.getFullName() == null) {
          if (getRecordSchema(paramObject).getFullName() != null)
            null = false; 
          return null;
        } 
        return paramSchema.getFullName().equals(getRecordSchema(paramObject).getFullName());
      case 2:
        null = bool1;
        if (isEnum(paramObject))
          null = paramSchema.getFullName().equals(getEnumSchema(paramObject).getFullName()); 
        return null;
      case 3:
        return isArray(paramObject);
      case 4:
        return isMap(paramObject);
      case 6:
        null = bool1;
        if (isFixed(paramObject))
          null = paramSchema.getFullName().equals(getFixedSchema(paramObject).getFullName()); 
        return null;
      case 7:
        return isString(paramObject);
      case 8:
        return isBytes(paramObject);
      case 9:
        return paramObject instanceof Integer;
      case 10:
        return paramObject instanceof Long;
      case 11:
        return paramObject instanceof Float;
      case 12:
        return paramObject instanceof Double;
      case 13:
        return paramObject instanceof Boolean;
      case 14:
        break;
    } 
    return (paramObject == null) ? bool : false;
  }
  
  protected boolean isArray(Object paramObject) {
    return paramObject instanceof Collection;
  }
  
  protected boolean isBytes(Object paramObject) {
    return paramObject instanceof ByteBuffer;
  }
  
  protected boolean isEnum(Object paramObject) {
    return paramObject instanceof GenericEnumSymbol;
  }
  
  protected boolean isFixed(Object paramObject) {
    return paramObject instanceof GenericFixed;
  }
  
  protected boolean isMap(Object paramObject) {
    return paramObject instanceof Map;
  }
  
  protected boolean isRecord(Object paramObject) {
    return paramObject instanceof IndexedRecord;
  }
  
  protected boolean isString(Object paramObject) {
    return paramObject instanceof CharSequence;
  }
  
  public Object newRecord(Object paramObject, Schema paramSchema) {
    if (paramObject instanceof IndexedRecord) {
      paramObject = paramObject;
      if (paramObject.getSchema() == paramSchema)
        return paramObject; 
    } 
    return new GenericData$Record(paramSchema);
  }
  
  public int resolveUnion(Schema paramSchema, Object paramObject) {
    Integer integer = paramSchema.getIndexNamed(getSchemaName(paramObject));
    if (integer != null)
      return integer.intValue(); 
    throw new UnresolvedUnionException(paramSchema, paramObject);
  }
  
  public void setField(Object paramObject1, String paramString, int paramInt, Object paramObject2) {
    ((IndexedRecord)paramObject1).put(paramInt, paramObject2);
  }
  
  protected void setField(Object paramObject1, String paramString, int paramInt, Object paramObject2, Object paramObject3) {
    setField(paramObject1, paramString, paramInt, paramObject2);
  }
  
  public String toString(Object paramObject) {
    StringBuilder stringBuilder = new StringBuilder();
    toString(paramObject, stringBuilder);
    return stringBuilder.toString();
  }
  
  protected void toString(Object paramObject, StringBuilder paramStringBuilder) {
    int i = 0;
    if (isRecord(paramObject)) {
      paramStringBuilder.append("{");
      Schema schema = getRecordSchema(paramObject);
      Iterator<Schema.Field> iterator = schema.getFields().iterator();
      i = 0;
      while (iterator.hasNext()) {
        Schema.Field field = iterator.next();
        toString(field.name(), paramStringBuilder);
        paramStringBuilder.append(": ");
        toString(getField(paramObject, field.name(), field.pos()), paramStringBuilder);
        if (++i < schema.getFields().size())
          paramStringBuilder.append(", "); 
      } 
      paramStringBuilder.append("}");
      return;
    } 
    if (paramObject instanceof Collection) {
      paramObject = paramObject;
      paramStringBuilder.append("[");
      long l = (paramObject.size() - 1);
      paramObject = paramObject.iterator();
      while (paramObject.hasNext()) {
        toString(paramObject.next(), paramStringBuilder);
        if (i < l)
          paramStringBuilder.append(", "); 
        i++;
      } 
      paramStringBuilder.append("]");
      return;
    } 
    if (paramObject instanceof Map) {
      paramStringBuilder.append("{");
      paramObject = paramObject;
      Iterator<Map.Entry> iterator = paramObject.entrySet().iterator();
      i = 0;
      while (iterator.hasNext()) {
        Map.Entry entry = iterator.next();
        toString(entry.getKey(), paramStringBuilder);
        paramStringBuilder.append(": ");
        toString(entry.getValue(), paramStringBuilder);
        if (++i < paramObject.size())
          paramStringBuilder.append(", "); 
      } 
      paramStringBuilder.append("}");
      return;
    } 
    if (paramObject instanceof CharSequence || paramObject instanceof GenericEnumSymbol) {
      paramStringBuilder.append("\"");
      writeEscapedString(paramObject.toString(), paramStringBuilder);
      paramStringBuilder.append("\"");
      return;
    } 
    if (paramObject instanceof ByteBuffer) {
      paramStringBuilder.append("{\"bytes\": \"");
      paramObject = paramObject;
      for (i = paramObject.position(); i < paramObject.limit(); i++)
        paramStringBuilder.append((char)paramObject.get(i)); 
      paramStringBuilder.append("\"}");
      return;
    } 
    paramStringBuilder.append(paramObject);
  }
  
  public boolean validate(Schema paramSchema, Object paramObject) {
    Iterator<Schema> iterator;
    boolean bool;
    null = true;
    boolean bool1 = false;
    switch (GenericData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        return bool1;
      case 1:
        null = bool1;
        if (isRecord(paramObject)) {
          for (Schema.Field field : paramSchema.getFields()) {
            if (!validate(field.schema(), getField(paramObject, field.name(), field.pos()))) {
              null = bool1;
              // Byte code: goto -> 91
            } 
          } 
          null = true;
        } 
        return null;
      case 2:
        return field.getEnumSymbols().contains(paramObject.toString());
      case 3:
        null = bool1;
        if (paramObject instanceof Collection) {
          for (Object paramObject : paramObject) {
            if (!validate(field.getElementType(), paramObject)) {
              null = bool1;
              // Byte code: goto -> 91
            } 
          } 
          null = true;
        } 
        return null;
      case 4:
        null = bool1;
        if (paramObject instanceof Map) {
          paramObject = ((Map)paramObject).entrySet().iterator();
          while (paramObject.hasNext()) {
            Map.Entry entry = paramObject.next();
            if (!validate(field.getValueType(), entry.getValue())) {
              null = bool1;
              // Byte code: goto -> 91
            } 
          } 
          null = true;
        } 
        return null;
      case 5:
        iterator = field.getTypes().iterator();
        while (true) {
          null = bool1;
          if (iterator.hasNext()) {
            if (validate(iterator.next(), paramObject))
              return true; 
            continue;
          } 
          return null;
        } 
      case 6:
        return (paramObject instanceof GenericFixed && (((GenericFixed)paramObject).bytes()).length == iterator.getFixedSize());
      case 7:
        return isString(paramObject);
      case 8:
        return isBytes(paramObject);
      case 9:
        return paramObject instanceof Integer;
      case 10:
        return paramObject instanceof Long;
      case 11:
        return paramObject instanceof Float;
      case 12:
        return paramObject instanceof Double;
      case 13:
        return paramObject instanceof Boolean;
      case 14:
        break;
    } 
    if (paramObject != null)
      bool = false; 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */