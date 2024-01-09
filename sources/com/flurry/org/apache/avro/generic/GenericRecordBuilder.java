package com.flurry.org.apache.avro.generic;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilderBase;
import com.flurry.org.apache.avro.generic.GenericData;
import java.io.IOException;

/* loaded from: classes.dex */
public class GenericRecordBuilder extends RecordBuilderBase {
    private final GenericData.Record record;

    public GenericRecordBuilder(Schema schema) {
        super(schema, GenericData.get());
        this.record = new GenericData.Record(schema);
    }

    public GenericRecordBuilder(GenericData.Record record) {
        super(record.getSchema(), GenericData.get());
        this.record = new GenericData.Record(record, true);
        for (Schema.Field field : schema().getFields()) {
            Object obj = record.get(field.pos());
            if (isValidValue(field, obj)) {
                set(field, data().deepCopy(field.schema(), obj));
            }
        }
    }

    public GenericRecordBuilder(GenericRecordBuilder genericRecordBuilder) {
        super(genericRecordBuilder, GenericData.get());
        this.record = new GenericData.Record(genericRecordBuilder.record, true);
    }

    private Object getWithDefault(Schema.Field field) {
        return fieldSetFlags()[field.pos()] ? this.record.get(field.pos()) : defaultValue(field);
    }

    private GenericRecordBuilder set(Schema.Field field, int i, Object obj) {
        validate(field, obj);
        this.record.put(i, obj);
        fieldSetFlags()[i] = true;
        return this;
    }

    @Override // com.flurry.org.apache.avro.data.RecordBuilder
    public GenericData.Record build() {
        Schema.Field[] fields;
        try {
            GenericData.Record record = new GenericData.Record(schema());
            for (Schema.Field field : fields()) {
                try {
                    Object withDefault = getWithDefault(field);
                    if (withDefault != null) {
                        record.put(field.pos(), withDefault);
                    }
                } catch (IOException e) {
                    throw new AvroRuntimeException(e);
                }
            }
            return record;
        } catch (Exception e2) {
            throw new AvroRuntimeException(e2);
        }
    }

    protected GenericRecordBuilder clear(int i) {
        this.record.put(i, (Object) null);
        fieldSetFlags()[i] = false;
        return this;
    }

    public GenericRecordBuilder clear(Schema.Field field) {
        return clear(field.pos());
    }

    public GenericRecordBuilder clear(String str) {
        return clear(schema().getField(str));
    }

    @Override // com.flurry.org.apache.avro.data.RecordBuilderBase
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && getClass() == obj.getClass()) {
            GenericRecordBuilder genericRecordBuilder = (GenericRecordBuilder) obj;
            return this.record == null ? genericRecordBuilder.record == null : this.record.equals(genericRecordBuilder.record);
        }
        return false;
    }

    protected Object get(int i) {
        return this.record.get(i);
    }

    public Object get(Schema.Field field) {
        return get(field.pos());
    }

    public Object get(String str) {
        return get(schema().getField(str));
    }

    protected boolean has(int i) {
        return fieldSetFlags()[i];
    }

    public boolean has(Schema.Field field) {
        return has(field.pos());
    }

    public boolean has(String str) {
        return has(schema().getField(str));
    }

    @Override // com.flurry.org.apache.avro.data.RecordBuilderBase
    public int hashCode() {
        return (this.record == null ? 0 : this.record.hashCode()) + (super.hashCode() * 31);
    }

    protected GenericRecordBuilder set(int i, Object obj) {
        return set(fields()[i], i, obj);
    }

    public GenericRecordBuilder set(Schema.Field field, Object obj) {
        return set(field, field.pos(), obj);
    }

    public GenericRecordBuilder set(String str, Object obj) {
        return set(schema().getField(str), obj);
    }
}
