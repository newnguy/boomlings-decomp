package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

/* loaded from: classes.dex */
public class Callback extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Callback\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"actions\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}");
    public CharSequence a;
    public List b;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private CharSequence a;
        private List b;

        private Builder() {
            super(Callback.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public Callback build() {
            try {
                Callback callback = new Callback();
                callback.a = fieldSetFlags()[0] ? this.a : (CharSequence) defaultValue(fields()[0]);
                callback.b = fieldSetFlags()[1] ? this.b : (List) defaultValue(fields()[1]);
                return callback;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearActions() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearEvent() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public List getActions() {
            return this.b;
        }

        public CharSequence getEvent() {
            return this.a;
        }

        public boolean hasActions() {
            return fieldSetFlags()[1];
        }

        public boolean hasEvent() {
            return fieldSetFlags()[0];
        }

        public Builder setActions(List list) {
            validate(fields()[1], list);
            this.b = list;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setEvent(CharSequence charSequence) {
            validate(fields()[0], charSequence);
            this.a = charSequence;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    Callback() {
    }

    public final CharSequence a() {
        return this.a;
    }

    public final List b() {
        return this.b;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return this.a;
            case 1:
                return this.b;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.GenericContainer
    public Schema getSchema() {
        return SCHEMA$;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public void put(int i, Object obj) {
        switch (i) {
            case 0:
                this.a = (CharSequence) obj;
                return;
            case 1:
                this.b = (List) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
