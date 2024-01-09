package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.UnresolvedUnionException;
import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.apache.avro.io.BinaryData;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GenericData {
    private static final GenericData INSTANCE = new GenericData();
    private static final Schema STRINGS = Schema.create(Schema.Type.STRING);
    protected static final String STRING_PROP = "avro.java.string";
    protected static final String STRING_TYPE_STRING = "String";

    /* loaded from: classes.dex */
    public class Array extends AbstractList implements GenericArray, Comparable {
        private static final Object[] EMPTY = new Object[0];
        private Object[] elements;
        private final Schema schema;
        private int size;

        public Array(int i, Schema schema) {
            this.elements = EMPTY;
            if (schema == null || !Schema.Type.ARRAY.equals(schema.getType())) {
                throw new AvroRuntimeException("Not an array schema: " + schema);
            }
            this.schema = schema;
            if (i != 0) {
                this.elements = new Object[i];
            }
        }

        public Array(Schema schema, Collection collection) {
            this.elements = EMPTY;
            if (schema == null || !Schema.Type.ARRAY.equals(schema.getType())) {
                throw new AvroRuntimeException("Not an array schema: " + schema);
            }
            this.schema = schema;
            if (collection != null) {
                this.elements = new Object[collection.size()];
                addAll(collection);
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, Object obj) {
            if (i > this.size || i < 0) {
                throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
            }
            if (this.size == this.elements.length) {
                Object[] objArr = new Object[((this.size * 3) / 2) + 1];
                System.arraycopy(this.elements, 0, objArr, 0, this.size);
                this.elements = objArr;
            }
            System.arraycopy(this.elements, i, this.elements, i + 1, this.size - i);
            this.elements[i] = obj;
            this.size++;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(Object obj) {
            if (this.size == this.elements.length) {
                Object[] objArr = new Object[((this.size * 3) / 2) + 1];
                System.arraycopy(this.elements, 0, objArr, 0, this.size);
                this.elements = objArr;
            }
            Object[] objArr2 = this.elements;
            int i = this.size;
            this.size = i + 1;
            objArr2[i] = obj;
            return true;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.size = 0;
        }

        @Override // java.lang.Comparable
        public int compareTo(GenericArray genericArray) {
            return GenericData.get().compare(this, genericArray, getSchema());
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            if (i >= this.size) {
                throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
            }
            return this.elements[i];
        }

        @Override // com.flurry.org.apache.avro.generic.GenericContainer
        public Schema getSchema() {
            return this.schema;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator iterator() {
            return new Iterator() { // from class: com.flurry.org.apache.avro.generic.GenericData.Array.1
                private int position = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.position < Array.this.size;
                }

                @Override // java.util.Iterator
                public Object next() {
                    Object[] objArr = Array.this.elements;
                    int i = this.position;
                    this.position = i + 1;
                    return objArr[i];
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override // com.flurry.org.apache.avro.generic.GenericArray
        public Object peek() {
            if (this.size < this.elements.length) {
                return this.elements[this.size];
            }
            return null;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object remove(int i) {
            if (i >= this.size) {
                throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
            }
            Object obj = this.elements[i];
            this.size--;
            System.arraycopy(this.elements, i + 1, this.elements, i, this.size - i);
            this.elements[this.size] = null;
            return obj;
        }

        @Override // com.flurry.org.apache.avro.generic.GenericArray
        public void reverse() {
            int i = 0;
            for (int length = this.elements.length - 1; i < length; length--) {
                Object obj = this.elements[i];
                this.elements[i] = this.elements[length];
                this.elements[length] = obj;
                i++;
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public Object set(int i, Object obj) {
            if (i >= this.size) {
                throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
            }
            Object obj2 = this.elements[i];
            this.elements[i] = obj;
            return obj2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            int i = 0;
            Iterator it = iterator();
            while (it.hasNext()) {
                Object next = it.next();
                stringBuffer.append(next == null ? DataFileConstants.NULL_CODEC : next.toString());
                i++;
                if (i < size()) {
                    stringBuffer.append(", ");
                }
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }

    /* loaded from: classes.dex */
    public class EnumSymbol implements GenericEnumSymbol {
        private Schema schema;
        private String symbol;

        public EnumSymbol(Schema schema, String str) {
            this.schema = schema;
            this.symbol = str;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof GenericEnumSymbol) && this.symbol.equals(obj.toString());
        }

        @Override // com.flurry.org.apache.avro.generic.GenericContainer
        public Schema getSchema() {
            return this.schema;
        }

        public int hashCode() {
            return this.symbol.hashCode();
        }

        @Override // com.flurry.org.apache.avro.generic.GenericEnumSymbol
        public String toString() {
            return this.symbol;
        }
    }

    /* loaded from: classes.dex */
    public class Fixed implements GenericFixed, Comparable {
        private byte[] bytes;
        private Schema schema;

        /* JADX INFO: Access modifiers changed from: protected */
        public Fixed() {
        }

        public Fixed(Schema schema) {
            setSchema(schema);
        }

        public Fixed(Schema schema, byte[] bArr) {
            this.schema = schema;
            this.bytes = bArr;
        }

        public void bytes(byte[] bArr) {
            this.bytes = bArr;
        }

        @Override // com.flurry.org.apache.avro.generic.GenericFixed
        public byte[] bytes() {
            return this.bytes;
        }

        @Override // java.lang.Comparable
        public int compareTo(Fixed fixed) {
            return BinaryData.compareBytes(this.bytes, 0, this.bytes.length, fixed.bytes, 0, fixed.bytes.length);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof GenericFixed) && Arrays.equals(this.bytes, ((GenericFixed) obj).bytes());
        }

        @Override // com.flurry.org.apache.avro.generic.GenericContainer
        public Schema getSchema() {
            return this.schema;
        }

        public int hashCode() {
            return Arrays.hashCode(this.bytes);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setSchema(Schema schema) {
            this.schema = schema;
            this.bytes = new byte[schema.getFixedSize()];
        }

        public String toString() {
            return Arrays.toString(this.bytes);
        }
    }

    /* loaded from: classes.dex */
    public class Record implements GenericRecord, Comparable {
        private final Schema schema;
        private final Object[] values;

        public Record(Schema schema) {
            if (schema == null || !Schema.Type.RECORD.equals(schema.getType())) {
                throw new AvroRuntimeException("Not a record schema: " + schema);
            }
            this.schema = schema;
            this.values = new Object[schema.getFields().size()];
        }

        public Record(Record record, boolean z) {
            int i = 0;
            this.schema = record.schema;
            this.values = new Object[this.schema.getFields().size()];
            if (!z) {
                System.arraycopy(record.values, 0, this.values, 0, record.values.length);
                return;
            }
            while (true) {
                int i2 = i;
                if (i2 >= this.values.length) {
                    return;
                }
                this.values[i2] = GenericData.INSTANCE.deepCopy(((Schema.Field) this.schema.getFields().get(i2)).schema(), record.values[i2]);
                i = i2 + 1;
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(Record record) {
            return GenericData.get().compare(this, record, this.schema);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Record) {
                Record record = (Record) obj;
                return this.schema.getFullName().equals(record.schema.getFullName()) && GenericData.get().compare(this, record, this.schema, true) == 0;
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.generic.IndexedRecord
        public Object get(int i) {
            return this.values[i];
        }

        @Override // com.flurry.org.apache.avro.generic.GenericRecord
        public Object get(String str) {
            Schema.Field field = this.schema.getField(str);
            if (field == null) {
                return null;
            }
            return this.values[field.pos()];
        }

        @Override // com.flurry.org.apache.avro.generic.GenericContainer
        public Schema getSchema() {
            return this.schema;
        }

        public int hashCode() {
            return GenericData.get().hashCode(this, this.schema);
        }

        @Override // com.flurry.org.apache.avro.generic.IndexedRecord
        public void put(int i, Object obj) {
            this.values[i] = obj;
        }

        @Override // com.flurry.org.apache.avro.generic.GenericRecord
        public void put(String str, Object obj) {
            Schema.Field field = this.schema.getField(str);
            if (field == null) {
                throw new AvroRuntimeException("Not a valid schema field: " + str);
            }
            this.values[field.pos()] = obj;
        }

        public String toString() {
            return GenericData.get().toString(this);
        }
    }

    /* loaded from: classes.dex */
    public enum StringType {
        CharSequence,
        String,
        Utf8
    }

    public static GenericData get() {
        return INSTANCE;
    }

    public static void setStringType(Schema schema, StringType stringType) {
        if (stringType == StringType.String) {
            schema.addProp(STRING_PROP, STRING_TYPE_STRING);
        }
    }

    private void writeEscapedString(String str, StringBuilder sb) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    if ((charAt < 0 || charAt > 31) && ((charAt < 127 || charAt > 159) && (charAt < 8192 || charAt > 8447))) {
                        sb.append(charAt);
                        break;
                    } else {
                        Integer.toHexString(charAt);
                        sb.append("\\u");
                        for (int i2 = 0; i2 < 4 - sb.length(); i2++) {
                            sb.append('0');
                        }
                        sb.append(str.toUpperCase());
                        break;
                    }
            }
        }
    }

    public int compare(Object obj, Object obj2, Schema schema) {
        return compare(obj, obj2, schema, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int compare(Object obj, Object obj2, Schema schema, boolean z) {
        if (obj == obj2) {
            return 0;
        }
        switch (schema.getType()) {
            case RECORD:
                for (Schema.Field field : schema.getFields()) {
                    if (field.order() != Schema.Field.Order.IGNORE) {
                        int pos = field.pos();
                        String name = field.name();
                        int compare = compare(getField(obj, name, pos), getField(obj2, name, pos), field.schema(), z);
                        if (compare != 0) {
                            return field.order() == Schema.Field.Order.DESCENDING ? -compare : compare;
                        }
                    }
                }
                return 0;
            case ENUM:
                return schema.getEnumOrdinal(obj.toString()) - schema.getEnumOrdinal(obj2.toString());
            case ARRAY:
                Iterator it = ((Collection) obj).iterator();
                Iterator it2 = ((Collection) obj2).iterator();
                Schema elementType = schema.getElementType();
                while (it.hasNext() && it2.hasNext()) {
                    int compare2 = compare(it.next(), it2.next(), elementType, z);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return it.hasNext() ? 1 : it2.hasNext() ? -1 : 0;
            case MAP:
                if (z) {
                    return !((Map) obj).equals(obj2) ? 1 : 0;
                }
                throw new AvroRuntimeException("Can't compare maps!");
            case UNION:
                int resolveUnion = resolveUnion(schema, obj);
                int resolveUnion2 = resolveUnion(schema, obj2);
                return resolveUnion == resolveUnion2 ? compare(obj, obj2, (Schema) schema.getTypes().get(resolveUnion), z) : resolveUnion - resolveUnion2;
            case FIXED:
            case BYTES:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case BOOLEAN:
            default:
                return ((Comparable) obj).compareTo(obj2);
            case STRING:
                return (obj instanceof Utf8 ? (Utf8) obj : new Utf8(obj.toString())).compareTo(obj2 instanceof Utf8 ? (Utf8) obj2 : new Utf8(obj2.toString()));
            case NULL:
                return 0;
        }
    }

    public DatumReader createDatumReader(Schema schema) {
        return new GenericDatumReader(schema, schema, this);
    }

    public Object createFixed(Object obj, Schema schema) {
        return ((obj instanceof GenericFixed) && ((GenericFixed) obj).bytes().length == schema.getFixedSize()) ? obj : new Fixed(schema);
    }

    public Object createFixed(Object obj, byte[] bArr, Schema schema) {
        GenericFixed genericFixed = (GenericFixed) createFixed(obj, schema);
        System.arraycopy(bArr, 0, genericFixed.bytes(), 0, schema.getFixedSize());
        return genericFixed;
    }

    public Object deepCopy(Schema schema, Object obj) {
        if (obj == null) {
            return null;
        }
        switch (schema.getType()) {
            case RECORD:
                IndexedRecord indexedRecord = (IndexedRecord) obj;
                IndexedRecord indexedRecord2 = (IndexedRecord) newRecord(null, schema);
                for (Schema.Field field : schema.getFields()) {
                    indexedRecord2.put(field.pos(), deepCopy(field.schema(), indexedRecord.get(field.pos())));
                }
                return indexedRecord2;
            case ENUM:
                return obj;
            case ARRAY:
                List<Object> list = (List) obj;
                Array array = new Array(list.size(), schema);
                for (Object obj2 : list) {
                    array.add(deepCopy(schema.getElementType(), obj2));
                }
                return array;
            case MAP:
                Map map = (Map) obj;
                HashMap hashMap = new HashMap(map.size());
                for (Map.Entry entry : map.entrySet()) {
                    hashMap.put((CharSequence) deepCopy(STRINGS, entry.getKey()), deepCopy(schema.getValueType(), entry.getValue()));
                }
                return hashMap;
            case UNION:
                return deepCopy((Schema) schema.getTypes().get(resolveUnion(schema, obj)), obj);
            case FIXED:
                return createFixed(null, ((GenericFixed) obj).bytes(), schema);
            case STRING:
                return !(obj instanceof String) ? obj instanceof Utf8 ? new Utf8((Utf8) obj) : new Utf8(obj.toString()) : obj;
            case BYTES:
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                byte[] bArr = new byte[byteBuffer.capacity()];
                byteBuffer.rewind();
                byteBuffer.get(bArr);
                byteBuffer.rewind();
                return ByteBuffer.wrap(bArr);
            case INT:
                return new Integer(((Integer) obj).intValue());
            case LONG:
                return new Long(((Long) obj).longValue());
            case FLOAT:
                return new Float(((Float) obj).floatValue());
            case DOUBLE:
                return new Double(((Double) obj).doubleValue());
            case BOOLEAN:
                return new Boolean(((Boolean) obj).booleanValue());
            case NULL:
                return null;
            default:
                throw new AvroRuntimeException("Deep copy failed for schema \"" + schema + "\" and value \"" + obj + "\"");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Schema getEnumSchema(Object obj) {
        return ((GenericContainer) obj).getSchema();
    }

    public Object getField(Object obj, String str, int i) {
        return ((IndexedRecord) obj).get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getField(Object obj, String str, int i, Object obj2) {
        return getField(obj, str, i);
    }

    protected Schema getFixedSchema(Object obj) {
        return ((GenericContainer) obj).getSchema();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Schema getRecordSchema(Object obj) {
        return ((GenericContainer) obj).getSchema();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getRecordState(Object obj, Schema schema) {
        return null;
    }

    protected String getSchemaName(Object obj) {
        if (obj == null) {
            return Schema.Type.NULL.getName();
        }
        if (isRecord(obj)) {
            return getRecordSchema(obj).getFullName();
        }
        if (isEnum(obj)) {
            return getEnumSchema(obj).getFullName();
        }
        if (isArray(obj)) {
            return Schema.Type.ARRAY.getName();
        }
        if (isMap(obj)) {
            return Schema.Type.MAP.getName();
        }
        if (isFixed(obj)) {
            return getFixedSchema(obj).getFullName();
        }
        if (isString(obj)) {
            return Schema.Type.STRING.getName();
        }
        if (isBytes(obj)) {
            return Schema.Type.BYTES.getName();
        }
        if (obj instanceof Integer) {
            return Schema.Type.INT.getName();
        }
        if (obj instanceof Long) {
            return Schema.Type.LONG.getName();
        }
        if (obj instanceof Float) {
            return Schema.Type.FLOAT.getName();
        }
        if (obj instanceof Double) {
            return Schema.Type.DOUBLE.getName();
        }
        if (obj instanceof Boolean) {
            return Schema.Type.BOOLEAN.getName();
        }
        throw new AvroRuntimeException("Unknown datum type: " + obj);
    }

    public int hashCode(Object obj, Schema schema) {
        if (obj == null) {
            return 0;
        }
        int i = 1;
        switch (schema.getType()) {
            case RECORD:
                int i2 = 1;
                for (Schema.Field field : schema.getFields()) {
                    if (field.order() != Schema.Field.Order.IGNORE) {
                        i2 = hashCodeAdd(i2, getField(obj, field.name(), field.pos()), field.schema());
                    }
                }
                return i2;
            case ENUM:
                return schema.getEnumOrdinal(obj.toString());
            case ARRAY:
                Schema elementType = schema.getElementType();
                for (Object obj2 : (Collection) obj) {
                    i = hashCodeAdd(i, obj2, elementType);
                }
                return i;
            case MAP:
            case FIXED:
            case BYTES:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case BOOLEAN:
            default:
                return obj.hashCode();
            case UNION:
                return hashCode(obj, (Schema) schema.getTypes().get(resolveUnion(schema, obj)));
            case STRING:
                if (!(obj instanceof Utf8)) {
                    obj = new Utf8(obj.toString());
                }
                return obj.hashCode();
            case NULL:
                return 0;
        }
    }

    protected int hashCodeAdd(int i, Object obj, Schema schema) {
        return (i * 31) + hashCode(obj, schema);
    }

    public Schema induce(Object obj) {
        Schema schema;
        Schema schema2 = null;
        if (isRecord(obj)) {
            return getRecordSchema(obj);
        }
        if (obj instanceof Collection) {
            Schema schema3 = null;
            for (Object obj2 : (Collection) obj) {
                if (schema3 == null) {
                    schema3 = induce(obj2);
                } else if (!schema3.equals(induce(obj2))) {
                    throw new AvroTypeException("No mixed type arrays.");
                }
            }
            if (schema3 == null) {
                throw new AvroTypeException("Empty array: " + obj);
            }
            return Schema.createArray(schema3);
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (schema2 == null) {
                    schema = induce(entry.getValue());
                } else if (!schema2.equals(induce(entry.getValue()))) {
                    throw new AvroTypeException("No mixed type map values.");
                } else {
                    schema = schema2;
                }
                schema2 = schema;
            }
            if (schema2 == null) {
                throw new AvroTypeException("Empty map: " + obj);
            }
            return Schema.createMap(schema2);
        } else if (obj instanceof GenericFixed) {
            return Schema.createFixed(null, null, null, ((GenericFixed) obj).bytes().length);
        } else {
            if (obj instanceof CharSequence) {
                return Schema.create(Schema.Type.STRING);
            }
            if (obj instanceof ByteBuffer) {
                return Schema.create(Schema.Type.BYTES);
            }
            if (obj instanceof Integer) {
                return Schema.create(Schema.Type.INT);
            }
            if (obj instanceof Long) {
                return Schema.create(Schema.Type.LONG);
            }
            if (obj instanceof Float) {
                return Schema.create(Schema.Type.FLOAT);
            }
            if (obj instanceof Double) {
                return Schema.create(Schema.Type.DOUBLE);
            }
            if (obj instanceof Boolean) {
                return Schema.create(Schema.Type.BOOLEAN);
            }
            if (obj == null) {
                return Schema.create(Schema.Type.NULL);
            }
            throw new AvroTypeException("Can't create schema for: " + obj);
        }
    }

    protected boolean instanceOf(Schema schema, Object obj) {
        switch (schema.getType()) {
            case RECORD:
                if (isRecord(obj)) {
                    if (schema.getFullName() != null) {
                        r0 = schema.getFullName().equals(getRecordSchema(obj).getFullName());
                    } else if (getRecordSchema(obj).getFullName() != null) {
                        r0 = false;
                    }
                    return r0;
                }
                return false;
            case ENUM:
                if (isEnum(obj)) {
                    return schema.getFullName().equals(getEnumSchema(obj).getFullName());
                }
                return false;
            case ARRAY:
                return isArray(obj);
            case MAP:
                return isMap(obj);
            case UNION:
            default:
                throw new AvroRuntimeException("Unexpected type: " + schema);
            case FIXED:
                if (isFixed(obj)) {
                    return schema.getFullName().equals(getFixedSchema(obj).getFullName());
                }
                return false;
            case STRING:
                return isString(obj);
            case BYTES:
                return isBytes(obj);
            case INT:
                return obj instanceof Integer;
            case LONG:
                return obj instanceof Long;
            case FLOAT:
                return obj instanceof Float;
            case DOUBLE:
                return obj instanceof Double;
            case BOOLEAN:
                return obj instanceof Boolean;
            case NULL:
                return obj == null;
        }
    }

    protected boolean isArray(Object obj) {
        return obj instanceof Collection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isBytes(Object obj) {
        return obj instanceof ByteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEnum(Object obj) {
        return obj instanceof GenericEnumSymbol;
    }

    protected boolean isFixed(Object obj) {
        return obj instanceof GenericFixed;
    }

    protected boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isRecord(Object obj) {
        return obj instanceof IndexedRecord;
    }

    protected boolean isString(Object obj) {
        return obj instanceof CharSequence;
    }

    public Object newRecord(Object obj, Schema schema) {
        if (obj instanceof IndexedRecord) {
            IndexedRecord indexedRecord = (IndexedRecord) obj;
            if (indexedRecord.getSchema() == schema) {
                return indexedRecord;
            }
        }
        return new Record(schema);
    }

    public int resolveUnion(Schema schema, Object obj) {
        Integer indexNamed = schema.getIndexNamed(getSchemaName(obj));
        if (indexNamed != null) {
            return indexNamed.intValue();
        }
        throw new UnresolvedUnionException(schema, obj);
    }

    public void setField(Object obj, String str, int i, Object obj2) {
        ((IndexedRecord) obj).put(i, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setField(Object obj, String str, int i, Object obj2, Object obj3) {
        setField(obj, str, i, obj2);
    }

    public String toString(Object obj) {
        StringBuilder sb = new StringBuilder();
        toString(obj, sb);
        return sb.toString();
    }

    protected void toString(Object obj, StringBuilder sb) {
        Collection collection;
        int i = 0;
        if (isRecord(obj)) {
            sb.append("{");
            Schema recordSchema = getRecordSchema(obj);
            Iterator it = recordSchema.getFields().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    sb.append("}");
                    return;
                }
                Schema.Field field = (Schema.Field) it.next();
                toString(field.name(), sb);
                sb.append(": ");
                toString(getField(obj, field.name(), field.pos()), sb);
                i = i2 + 1;
                if (i < recordSchema.getFields().size()) {
                    sb.append(", ");
                }
            }
        } else if (obj instanceof Collection) {
            sb.append("[");
            long size = collection.size() - 1;
            for (Object obj2 : (Collection) obj) {
                toString(obj2, sb);
                int i3 = i + 1;
                if (i < size) {
                    sb.append(", ");
                }
                i = i3;
            }
            sb.append("]");
        } else if (obj instanceof Map) {
            sb.append("{");
            Map map = (Map) obj;
            Iterator it2 = map.entrySet().iterator();
            while (true) {
                int i4 = i;
                if (!it2.hasNext()) {
                    sb.append("}");
                    return;
                }
                Map.Entry entry = (Map.Entry) it2.next();
                toString(entry.getKey(), sb);
                sb.append(": ");
                toString(entry.getValue(), sb);
                i = i4 + 1;
                if (i < map.size()) {
                    sb.append(", ");
                }
            }
        } else if ((obj instanceof CharSequence) || (obj instanceof GenericEnumSymbol)) {
            sb.append("\"");
            writeEscapedString(obj.toString(), sb);
            sb.append("\"");
        } else if (!(obj instanceof ByteBuffer)) {
            sb.append(obj);
        } else {
            sb.append("{\"bytes\": \"");
            ByteBuffer byteBuffer = (ByteBuffer) obj;
            for (int position = byteBuffer.position(); position < byteBuffer.limit(); position++) {
                sb.append((char) byteBuffer.get(position));
            }
            sb.append("\"}");
        }
    }

    public boolean validate(Schema schema, Object obj) {
        switch (schema.getType()) {
            case RECORD:
                if (isRecord(obj)) {
                    for (Schema.Field field : schema.getFields()) {
                        if (!validate(field.schema(), getField(obj, field.name(), field.pos()))) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            case ENUM:
                return schema.getEnumSymbols().contains(obj.toString());
            case ARRAY:
                if (obj instanceof Collection) {
                    for (Object obj2 : (Collection) obj) {
                        if (!validate(schema.getElementType(), obj2)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            case MAP:
                if (obj instanceof Map) {
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        if (!validate(schema.getValueType(), entry.getValue())) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            case UNION:
                for (Schema schema2 : schema.getTypes()) {
                    if (validate(schema2, obj)) {
                        return true;
                    }
                }
                return false;
            case FIXED:
                return (obj instanceof GenericFixed) && ((GenericFixed) obj).bytes().length == schema.getFixedSize();
            case STRING:
                return isString(obj);
            case BYTES:
                return isBytes(obj);
            case INT:
                return obj instanceof Integer;
            case LONG:
                return obj instanceof Long;
            case FLOAT:
                return obj instanceof Float;
            case DOUBLE:
                return obj instanceof Double;
            case BOOLEAN:
                return obj instanceof Boolean;
            case NULL:
                return obj == null;
            default:
                return false;
        }
    }
}
