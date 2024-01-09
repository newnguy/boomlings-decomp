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

/* loaded from: classes.dex */
public class ReflectDatumReader extends SpecificDatumReader {
    public ReflectDatumReader() {
        this(null, null, ReflectData.get());
    }

    public ReflectDatumReader(Schema schema) {
        this(schema, schema, ReflectData.get());
    }

    public ReflectDatumReader(Schema schema, Schema schema2) {
        this(schema, schema2, ReflectData.get());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReflectDatumReader(Schema schema, Schema schema2, ReflectData reflectData) {
        super(schema, schema2, reflectData);
    }

    public ReflectDatumReader(Class cls) {
        this(ReflectData.get().getSchema(cls));
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected void addToArray(Object obj, long j, Object obj2) {
        if (obj instanceof Collection) {
            ((Collection) obj).add(obj2);
        } else {
            Array.set(obj, (int) j, obj2);
        }
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object createString(String str) {
        return str;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object newArray(Object obj, int i, Schema schema) {
        ReflectData reflectData = ReflectData.get();
        Class classProp = ReflectData.getClassProp(schema, "java-class");
        if (classProp != null) {
            if (!(obj instanceof Collection)) {
                return classProp.isAssignableFrom(ArrayList.class) ? new ArrayList() : ReflectData.newInstance(classProp, schema);
            }
            ((Collection) obj).clear();
            return obj;
        }
        Class classProp2 = ReflectData.getClassProp(schema, "java-element-class");
        if (classProp2 == null) {
            classProp2 = reflectData.getClass(schema.getElementType());
        }
        return Array.newInstance(classProp2, i);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object peekArray(Object obj) {
        return null;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object readBytes(Object obj, Decoder decoder) {
        ByteBuffer readBytes = decoder.readBytes(null);
        byte[] bArr = new byte[readBytes.remaining()];
        readBytes.get(bArr);
        return bArr;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object readInt(Object obj, Schema schema, Decoder decoder) {
        Integer valueOf = Integer.valueOf(decoder.readInt());
        String prop = schema.getProp("java-class");
        return Byte.class.getName().equals(prop) ? Byte.valueOf(valueOf.byteValue()) : Short.class.getName().equals(prop) ? Short.valueOf(valueOf.shortValue()) : valueOf;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    protected Object readString(Object obj, Schema schema, Decoder decoder) {
        String str = (String) readString(null, decoder);
        Class classProp = ReflectData.getClassProp(schema, "java-class");
        if (classProp != null) {
            try {
                return classProp.getConstructor(String.class).newInstance(str);
            } catch (IllegalAccessException e) {
                throw new AvroRuntimeException(e);
            } catch (InstantiationException e2) {
                throw new AvroRuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                throw new AvroRuntimeException(e3);
            } catch (InvocationTargetException e4) {
                throw new AvroRuntimeException(e4);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    public Object readString(Object obj, Decoder decoder) {
        return super.readString(null, decoder).toString();
    }
}
