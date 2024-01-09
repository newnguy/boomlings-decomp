package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumWriter;
import com.flurry.org.apache.avro.io.Encoder;

/* loaded from: classes.dex */
public class SpecificDatumWriter extends GenericDatumWriter {
    public SpecificDatumWriter() {
        super(SpecificData.get());
    }

    public SpecificDatumWriter(Schema schema) {
        super(schema, SpecificData.get());
    }

    public SpecificDatumWriter(Schema schema, SpecificData specificData) {
        super(schema, specificData);
    }

    public SpecificDatumWriter(SpecificData specificData) {
        super(specificData);
    }

    public SpecificDatumWriter(Class cls) {
        super(SpecificData.get().getSchema(cls), SpecificData.get());
    }

    @Override // com.flurry.org.apache.avro.generic.GenericDatumWriter
    public void writeEnum(Schema schema, Object obj, Encoder encoder) {
        if (obj instanceof Enum) {
            encoder.writeEnum(((Enum) obj).ordinal());
        } else {
            super.writeEnum(schema, obj, encoder);
        }
    }
}
