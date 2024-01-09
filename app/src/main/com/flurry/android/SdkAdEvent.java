package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SdkAdEvent extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SdkAdEvent\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"params\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"timeOffset\",\"type\":\"long\"}]}");
    public CharSequence a;
    public Map b;
    public long c;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private CharSequence a;
        private Map b;
        private long c;

        private Builder() {
            super(SdkAdEvent.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public SdkAdEvent build() {
            try {
                SdkAdEvent sdkAdEvent = new SdkAdEvent();
                sdkAdEvent.a = fieldSetFlags()[0] ? this.a : (CharSequence) defaultValue(fields()[0]);
                sdkAdEvent.b = fieldSetFlags()[1] ? this.b : (Map) defaultValue(fields()[1]);
                sdkAdEvent.c = fieldSetFlags()[2] ? this.c : ((Long) defaultValue(fields()[2])).longValue();
                return sdkAdEvent;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearParams() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearTimeOffset() {
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearType() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public Map getParams() {
            return this.b;
        }

        public Long getTimeOffset() {
            return Long.valueOf(this.c);
        }

        public CharSequence getType() {
            return this.a;
        }

        public boolean hasParams() {
            return fieldSetFlags()[1];
        }

        public boolean hasTimeOffset() {
            return fieldSetFlags()[2];
        }

        public boolean hasType() {
            return fieldSetFlags()[0];
        }

        public Builder setParams(Map map) {
            validate(fields()[1], map);
            this.b = map;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setTimeOffset(long j) {
            validate(fields()[2], Long.valueOf(j));
            this.c = j;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setType(CharSequence charSequence) {
            validate(fields()[0], charSequence);
            this.a = charSequence;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    public final void a(CharSequence charSequence) {
        this.a = charSequence;
    }

    public final void a(Long l) {
        this.c = l.longValue();
    }

    public final void a(Map map) {
        this.b = map;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return this.a;
            case 1:
                return this.b;
            case 2:
                return Long.valueOf(this.c);
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
                this.b = (Map) obj;
                return;
            case 2:
                this.c = ((Long) obj).longValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
