package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

/* loaded from: classes.dex */
public class SdkAdLog extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SdkAdLog\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"sessionId\",\"type\":\"long\"},{\"name\":\"adLogGUID\",\"type\":\"string\"},{\"name\":\"sdkAdEvents\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"SdkAdEvent\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"params\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"timeOffset\",\"type\":\"long\"}]}}}]}");
    public long a;
    public CharSequence b;
    public List c;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private long a;
        private CharSequence b;
        private List c;

        private Builder() {
            super(SdkAdLog.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public SdkAdLog build() {
            try {
                SdkAdLog sdkAdLog = new SdkAdLog();
                sdkAdLog.a = fieldSetFlags()[0] ? this.a : ((Long) defaultValue(fields()[0])).longValue();
                sdkAdLog.b = fieldSetFlags()[1] ? this.b : (CharSequence) defaultValue(fields()[1]);
                sdkAdLog.c = fieldSetFlags()[2] ? this.c : (List) defaultValue(fields()[2]);
                return sdkAdLog;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdLogGUID() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearSdkAdEvents() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearSessionId() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public CharSequence getAdLogGUID() {
            return this.b;
        }

        public List getSdkAdEvents() {
            return this.c;
        }

        public Long getSessionId() {
            return Long.valueOf(this.a);
        }

        public boolean hasAdLogGUID() {
            return fieldSetFlags()[1];
        }

        public boolean hasSdkAdEvents() {
            return fieldSetFlags()[2];
        }

        public boolean hasSessionId() {
            return fieldSetFlags()[0];
        }

        public Builder setAdLogGUID(CharSequence charSequence) {
            validate(fields()[1], charSequence);
            this.b = charSequence;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setSdkAdEvents(List list) {
            validate(fields()[2], list);
            this.c = list;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setSessionId(long j) {
            validate(fields()[0], Long.valueOf(j));
            this.a = j;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    public final void a(CharSequence charSequence) {
        this.b = charSequence;
    }

    public final void a(Long l) {
        this.a = l.longValue();
    }

    public final void a(List list) {
        this.c = list;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Long.valueOf(this.a);
            case 1:
                return this.b;
            case 2:
                return this.c;
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
                this.a = ((Long) obj).longValue();
                return;
            case 1:
                this.b = (CharSequence) obj;
                return;
            case 2:
                this.c = (List) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
