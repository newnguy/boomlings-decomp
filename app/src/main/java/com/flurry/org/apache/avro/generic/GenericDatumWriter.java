package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumWriter;
import com.flurry.org.apache.avro.io.Encoder;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class GenericDatumWriter implements DatumWriter {
    private final GenericData data;
    private Schema root;

    public GenericDatumWriter() {
        this(GenericData.get());
    }

    public GenericDatumWriter(Schema schema) {
        this();
        setSchema(schema);
    }

    public GenericDatumWriter(Schema schema, GenericData genericData) {
        this(genericData);
        setSchema(schema);
    }

    public GenericDatumWriter(GenericData genericData) {
        this.data = genericData;
    }

    private void error(Schema schema, Object obj) {
        throw new AvroTypeException("Not a " + schema + ": " + obj);
    }

    protected Iterator getArrayElements(Object obj) {
        return ((Collection) obj).iterator();
    }

    protected long getArraySize(Object obj) {
        return ((Collection) obj).size();
    }

    public GenericData getData() {
        return this.data;
    }

    protected Iterable getMapEntries(Object obj) {
        return ((Map) obj).entrySet();
    }

    protected int getMapSize(Object obj) {
        return ((Map) obj).size();
    }

    protected NullPointerException npe(NullPointerException nullPointerException, String str) {
        NullPointerException nullPointerException2 = new NullPointerException(nullPointerException.getMessage() + str);
        Throwable cause = nullPointerException.getCause();
        Throwable th = nullPointerException;
        if (cause != null) {
            th = nullPointerException.getCause();
        }
        nullPointerException2.initCause(th);
        return nullPointerException2;
    }

    protected int resolveUnion(Schema schema, Object obj) {
        return this.data.resolveUnion(schema, obj);
    }

    @Override // com.flurry.org.apache.avro.io.DatumWriter
    public void setSchema(Schema schema) {
        this.root = schema;
    }

    public void write(Schema schema, Object obj, Encoder encoder) {
        try {
            switch (schema.getType()) {
                case RECORD:
                    writeRecord(schema, obj, encoder);
                    return;
                case ENUM:
                    writeEnum(schema, obj, encoder);
                    return;
                case ARRAY:
                    writeArray(schema, obj, encoder);
                    return;
                case MAP:
                    writeMap(schema, obj, encoder);
                    return;
                case UNION:
                    int resolveUnion = resolveUnion(schema, obj);
                    encoder.writeIndex(resolveUnion);
                    write((Schema) schema.getTypes().get(resolveUnion), obj, encoder);
                    return;
                case FIXED:
                    writeFixed(schema, obj, encoder);
                    return;
                case STRING:
                    writeString(schema, obj, encoder);
                    return;
                case BYTES:
                    writeBytes(obj, encoder);
                    return;
                case INT:
                    encoder.writeInt(((Number) obj).intValue());
                    return;
                case LONG:
                    encoder.writeLong(((Long) obj).longValue());
                    return;
                case FLOAT:
                    encoder.writeFloat(((Float) obj).floatValue());
                    return;
                case DOUBLE:
                    encoder.writeDouble(((Double) obj).doubleValue());
                    return;
                case BOOLEAN:
                    encoder.writeBoolean(((Boolean) obj).booleanValue());
                    return;
                case NULL:
                    encoder.writeNull();
                    return;
                default:
                    error(schema, obj);
                    return;
            }
        } catch (NullPointerException e) {
            throw npe(e, " of " + schema.getFullName());
        }
    }

    @Override // com.flurry.org.apache.avro.io.DatumWriter
    public void write(Object obj, Encoder encoder) {
        write(this.root, obj, encoder);
    }

    protected void writeArray(Schema schema, Object obj, Encoder encoder) {
        Schema elementType = schema.getElementType();
        long arraySize = getArraySize(obj);
        encoder.writeArrayStart();
        encoder.setItemCount(arraySize);
        Iterator arrayElements = getArrayElements(obj);
        while (arrayElements.hasNext()) {
            encoder.startItem();
            write(elementType, arrayElements.next(), encoder);
        }
        encoder.writeArrayEnd();
    }

    public void writeBytes(Object obj, Encoder encoder) {
        encoder.writeBytes((ByteBuffer) obj);
    }

    public void writeEnum(Schema schema, Object obj, Encoder encoder) {
        encoder.writeEnum(schema.getEnumOrdinal(obj.toString()));
    }

    protected void writeFixed(Schema schema, Object obj, Encoder encoder) {
        encoder.writeFixed(((GenericFixed) obj).bytes(), 0, schema.getFixedSize());
    }

    protected void writeMap(Schema schema, Object obj, Encoder encoder) {
        Schema valueType = schema.getValueType();
        int mapSize = getMapSize(obj);
        encoder.writeMapStart();
        encoder.setItemCount(mapSize);
        for (Map.Entry entry : getMapEntries(obj)) {
            encoder.startItem();
            writeString(entry.getKey(), encoder);
            write(valueType, entry.getValue(), encoder);
        }
        encoder.writeMapEnd();
    }

    protected void writeRecord(Schema schema, Object obj, Encoder encoder) {
        Object recordState = this.data.getRecordState(obj, schema);
        for (Schema.Field field : schema.getFields()) {
            try {
                write(field.schema(), this.data.getField(obj, field.name(), field.pos(), recordState), encoder);
            } catch (NullPointerException e) {
                throw npe(e, " in field " + field.name());
            }
        }
    }

    protected void writeString(Schema schema, Object obj, Encoder encoder) {
        writeString(obj, encoder);
    }

    public void writeString(Object obj, Encoder encoder) {
        encoder.writeString((CharSequence) obj);
    }
}
