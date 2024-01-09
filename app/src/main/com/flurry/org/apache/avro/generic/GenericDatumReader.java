package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericData;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.ResolvingDecoder;
import com.flurry.org.apache.avro.util.Utf8;
import com.flurry.org.apache.avro.util.WeakIdentityHashMap;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class GenericDatumReader implements DatumReader {
    private static final ThreadLocal RESOLVER_CACHE = new ThreadLocal() { // from class: com.flurry.org.apache.avro.generic.GenericDatumReader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Map initialValue() {
            return new WeakIdentityHashMap();
        }
    };
    private Schema actual;
    private final Thread creator;
    private ResolvingDecoder creatorResolver;
    private final GenericData data;
    private Schema expected;

    public GenericDatumReader() {
        this(null, null, GenericData.get());
    }

    public GenericDatumReader(Schema schema) {
        this(schema, schema, GenericData.get());
    }

    public GenericDatumReader(Schema schema, Schema schema2) {
        this(schema, schema2, GenericData.get());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GenericDatumReader(Schema schema, Schema schema2, GenericData genericData) {
        this.creatorResolver = null;
        this.actual = schema;
        this.expected = schema2;
        this.data = genericData;
        this.creator = Thread.currentThread();
    }

    public static void skip(Schema schema, Decoder decoder) {
        switch (schema.getType()) {
            case RECORD:
                for (Schema.Field field : schema.getFields()) {
                    skip(field.schema(), decoder);
                }
                return;
            case ENUM:
                decoder.readInt();
                return;
            case ARRAY:
                Schema elementType = schema.getElementType();
                long skipArray = decoder.skipArray();
                while (true) {
                    long j = skipArray;
                    if (j <= 0) {
                        return;
                    }
                    for (long j2 = 0; j2 < j; j2++) {
                        skip(elementType, decoder);
                    }
                    skipArray = decoder.skipArray();
                }
            case MAP:
                Schema valueType = schema.getValueType();
                long skipMap = decoder.skipMap();
                while (true) {
                    long j3 = skipMap;
                    if (j3 <= 0) {
                        return;
                    }
                    for (long j4 = 0; j4 < j3; j4++) {
                        decoder.skipString();
                        skip(valueType, decoder);
                    }
                    skipMap = decoder.skipMap();
                }
            case UNION:
                skip((Schema) schema.getTypes().get(decoder.readIndex()), decoder);
                return;
            case FIXED:
                decoder.skipFixed(schema.getFixedSize());
                return;
            case STRING:
                decoder.skipString();
                return;
            case BYTES:
                decoder.skipBytes();
                return;
            case INT:
                decoder.readInt();
                return;
            case LONG:
                decoder.readLong();
                return;
            case FLOAT:
                decoder.readFloat();
                return;
            case DOUBLE:
                decoder.readDouble();
                return;
            case BOOLEAN:
                decoder.readBoolean();
                return;
            case NULL:
                return;
            default:
                throw new RuntimeException("Unknown type: " + schema);
        }
    }

    protected void addToArray(Object obj, long j, Object obj2) {
        ((Collection) obj).add(obj2);
    }

    protected void addToMap(Object obj, Object obj2, Object obj3) {
        ((Map) obj).put(obj2, obj3);
    }

    protected Object createBytes(byte[] bArr) {
        return ByteBuffer.wrap(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object createEnum(String str, Schema schema) {
        return new GenericData.EnumSymbol(schema, str);
    }

    @Deprecated
    protected Object createFixed(Object obj, Schema schema) {
        return this.data.createFixed(obj, schema);
    }

    @Deprecated
    protected Object createFixed(Object obj, byte[] bArr, Schema schema) {
        return this.data.createFixed(obj, bArr, schema);
    }

    protected Object createString(String str) {
        return new Utf8(str);
    }

    public GenericData getData() {
        return this.data;
    }

    public Schema getExpected() {
        return this.expected;
    }

    protected final ResolvingDecoder getResolver(Schema schema, Schema schema2) {
        Map map;
        Thread currentThread = Thread.currentThread();
        if (currentThread != this.creator || this.creatorResolver == null) {
            Map map2 = (Map) ((Map) RESOLVER_CACHE.get()).get(schema);
            if (map2 == null) {
                map = new WeakIdentityHashMap();
                ((Map) RESOLVER_CACHE.get()).put(schema, map);
            } else {
                map = map2;
            }
            ResolvingDecoder resolvingDecoder = (ResolvingDecoder) map.get(schema2);
            if (resolvingDecoder == null) {
                resolvingDecoder = DecoderFactory.get().resolvingDecoder(Schema.applyAliases(schema, schema2), schema2, null);
                map.put(schema2, resolvingDecoder);
            }
            if (currentThread == this.creator) {
                this.creatorResolver = resolvingDecoder;
                return resolvingDecoder;
            }
            return resolvingDecoder;
        }
        return this.creatorResolver;
    }

    public Schema getSchema() {
        return this.actual;
    }

    protected Object newArray(Object obj, int i, Schema schema) {
        if (obj instanceof Collection) {
            ((Collection) obj).clear();
            return obj;
        }
        return new GenericData.Array(i, schema);
    }

    protected Object newMap(Object obj, int i) {
        if (obj instanceof Map) {
            ((Map) obj).clear();
            return obj;
        }
        return new HashMap(i);
    }

    @Deprecated
    protected Object newRecord(Object obj, Schema schema) {
        return this.data.newRecord(obj, schema);
    }

    protected Object peekArray(Object obj) {
        if (obj instanceof GenericArray) {
            return ((GenericArray) obj).peek();
        }
        return null;
    }

    protected Object read(Object obj, Schema schema, ResolvingDecoder resolvingDecoder) {
        switch (schema.getType()) {
            case RECORD:
                return readRecord(obj, schema, resolvingDecoder);
            case ENUM:
                return readEnum(schema, resolvingDecoder);
            case ARRAY:
                return readArray(obj, schema, resolvingDecoder);
            case MAP:
                return readMap(obj, schema, resolvingDecoder);
            case UNION:
                return read(obj, (Schema) schema.getTypes().get(resolvingDecoder.readIndex()), resolvingDecoder);
            case FIXED:
                return readFixed(obj, schema, resolvingDecoder);
            case STRING:
                return readString(obj, schema, resolvingDecoder);
            case BYTES:
                return readBytes(obj, resolvingDecoder);
            case INT:
                return readInt(obj, schema, resolvingDecoder);
            case LONG:
                return Long.valueOf(resolvingDecoder.readLong());
            case FLOAT:
                return Float.valueOf(resolvingDecoder.readFloat());
            case DOUBLE:
                return Double.valueOf(resolvingDecoder.readDouble());
            case BOOLEAN:
                return Boolean.valueOf(resolvingDecoder.readBoolean());
            case NULL:
                resolvingDecoder.readNull();
                return null;
            default:
                throw new AvroRuntimeException("Unknown type: " + schema);
        }
    }

    @Override // com.flurry.org.apache.avro.io.DatumReader
    public Object read(Object obj, Decoder decoder) {
        ResolvingDecoder resolver = getResolver(this.actual, this.expected);
        resolver.configure(decoder);
        Object read = read(obj, this.expected, resolver);
        resolver.drain();
        return read;
    }

    protected Object readArray(Object obj, Schema schema, ResolvingDecoder resolvingDecoder) {
        Schema elementType = schema.getElementType();
        long readArrayStart = resolvingDecoder.readArrayStart();
        long j = 0;
        if (readArrayStart > 0) {
            Object newArray = newArray(obj, (int) readArrayStart, schema);
            do {
                for (long j2 = 0; j2 < readArrayStart; j2++) {
                    addToArray(newArray, j + j2, read(peekArray(newArray), elementType, resolvingDecoder));
                }
                j += readArrayStart;
                readArrayStart = resolvingDecoder.arrayNext();
            } while (readArrayStart > 0);
            return newArray;
        }
        return newArray(obj, 0, schema);
    }

    protected Object readBytes(Object obj, Decoder decoder) {
        return decoder.readBytes(obj instanceof ByteBuffer ? (ByteBuffer) obj : null);
    }

    protected Object readEnum(Schema schema, Decoder decoder) {
        return createEnum((String) schema.getEnumSymbols().get(decoder.readEnum()), schema);
    }

    protected Object readFixed(Object obj, Schema schema, Decoder decoder) {
        GenericFixed genericFixed = (GenericFixed) this.data.createFixed(obj, schema);
        decoder.readFixed(genericFixed.bytes(), 0, schema.getFixedSize());
        return genericFixed;
    }

    protected Object readInt(Object obj, Schema schema, Decoder decoder) {
        return Integer.valueOf(decoder.readInt());
    }

    protected Object readMap(Object obj, Schema schema, ResolvingDecoder resolvingDecoder) {
        Schema valueType = schema.getValueType();
        long readMapStart = resolvingDecoder.readMapStart();
        Object newMap = newMap(obj, (int) readMapStart);
        if (readMapStart > 0) {
            do {
                for (int i = 0; i < readMapStart; i++) {
                    addToMap(newMap, readString(null, schema, resolvingDecoder), read(null, valueType, resolvingDecoder));
                }
                readMapStart = resolvingDecoder.mapNext();
            } while (readMapStart > 0);
            return newMap;
        }
        return newMap;
    }

    protected Object readRecord(Object obj, Schema schema, ResolvingDecoder resolvingDecoder) {
        Schema.Field[] readFieldOrder;
        Object newRecord = this.data.newRecord(obj, schema);
        Object recordState = this.data.getRecordState(newRecord, schema);
        for (Schema.Field field : resolvingDecoder.readFieldOrder()) {
            int pos = field.pos();
            String name = field.name();
            this.data.setField(newRecord, name, pos, read(obj != null ? this.data.getField(newRecord, name, pos, recordState) : null, field.schema(), resolvingDecoder), recordState);
        }
        return newRecord;
    }

    protected Object readString(Object obj, Schema schema, Decoder decoder) {
        GenericData genericData = this.data;
        GenericData genericData2 = this.data;
        return "String".equals(schema.getProp("avro.java.string")) ? decoder.readString() : readString(obj, decoder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object readString(Object obj, Decoder decoder) {
        return decoder.readString(obj instanceof Utf8 ? (Utf8) obj : null);
    }

    public void setExpected(Schema schema) {
        this.expected = schema;
        this.creatorResolver = null;
    }

    @Override // com.flurry.org.apache.avro.io.DatumReader
    public void setSchema(Schema schema) {
        this.actual = schema;
        if (this.expected == null) {
            this.expected = this.actual;
        }
        this.creatorResolver = null;
    }
}
