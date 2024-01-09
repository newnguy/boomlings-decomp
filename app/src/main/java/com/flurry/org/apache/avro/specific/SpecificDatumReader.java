package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumReader;

/* loaded from: classes.dex */
public class SpecificDatumReader extends GenericDatumReader {
    public SpecificDatumReader() {
        this(null, null, SpecificData.get());
    }

    public SpecificDatumReader(Schema schema) {
        this(schema, schema, SpecificData.get());
    }

    public SpecificDatumReader(Schema schema, Schema schema2) {
        this(schema, schema2, SpecificData.get());
    }

    public SpecificDatumReader(Schema schema, Schema schema2, SpecificData specificData) {
        super(schema, schema2, specificData);
    }

    public SpecificDatumReader(Class cls) {
        this(SpecificData.get().getSchema(cls));
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader
    public Object createEnum(String str, Schema schema) {
        Class cls = getSpecificData().getClass(schema);
        return cls == null ? super.createEnum(str, schema) : Enum.valueOf(cls, str);
    }

    public SpecificData getSpecificData() {
        return (SpecificData) getData();
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumReader, com.flurry.org.apache.avro.io.DatumReader
    public void setSchema(Schema schema) {
        SpecificData specificData;
        Class cls;
        if (getExpected() == null && schema != null && schema.getType() == Schema.Type.RECORD && (cls = (specificData = getSpecificData()).getClass(schema)) != null && SpecificRecord.class.isAssignableFrom(cls)) {
            setExpected(specificData.getSchema(cls));
        }
        super.setSchema(schema);
    }
}
