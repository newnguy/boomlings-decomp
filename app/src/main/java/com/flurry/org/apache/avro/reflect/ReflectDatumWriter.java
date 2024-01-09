package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.specific.SpecificDatumWriter;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ReflectDatumWriter extends SpecificDatumWriter {
    public ReflectDatumWriter() {
        this(ReflectData.get());
    }

    public ReflectDatumWriter(Schema schema) {
        this(schema, ReflectData.get());
    }

    protected ReflectDatumWriter(Schema schema, ReflectData reflectData) {
        super(schema, reflectData);
    }

    protected ReflectDatumWriter(ReflectData reflectData) {
        super(reflectData);
    }

    public ReflectDatumWriter(Class cls) {
        this(cls, ReflectData.get());
    }

    public ReflectDatumWriter(Class cls, ReflectData reflectData) {
        this(reflectData.getSchema(cls), reflectData);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    protected Iterator getArrayElements(final Object obj) {
        return obj instanceof Collection ? ((Collection) obj).iterator() : new Iterator() { // from class: com.flurry.org.apache.avro.reflect.ReflectDatumWriter.1
            private int i = 0;
            private final int length;

            {
                ReflectDatumWriter.this = this;
                this.length = Array.getLength(obj);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < this.length;
            }

            @Override // java.util.Iterator
            public Object next() {
                Object obj2 = obj;
                int i = this.i;
                this.i = i + 1;
                return Array.get(obj2, i);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    protected long getArraySize(Object obj) {
        return obj instanceof Collection ? ((Collection) obj).size() : Array.getLength(obj);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    public void write(Schema schema, Object obj, Encoder encoder) {
        if (obj instanceof Byte) {
            obj = Integer.valueOf(((Byte) obj).intValue());
        } else if (obj instanceof Short) {
            obj = Integer.valueOf(((Short) obj).intValue());
        }
        try {
            super.write(schema, obj, encoder);
        } catch (NullPointerException e) {
            NullPointerException nullPointerException = new NullPointerException("in " + schema.getFullName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + e.getMessage());
            Throwable cause = e.getCause();
            Throwable th = e;
            if (cause != null) {
                th = e.getCause();
            }
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    public void writeBytes(Object obj, Encoder encoder) {
        if (obj instanceof byte[]) {
            encoder.writeBytes((byte[]) obj);
        } else {
            super.writeBytes(obj, encoder);
        }
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    protected void writeString(Schema schema, Object obj, Encoder encoder) {
        if (schema.getProp("java-class") != null) {
            obj = obj.toString();
        }
        writeString(obj, encoder);
    }
}
