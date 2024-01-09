package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SdkLogRequest extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SdkLogRequest\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"apiKey\",\"type\":\"string\"},{\"name\":\"adReportedIds\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdReportedId\",\"fields\":[{\"name\":\"type\",\"type\":\"int\"},{\"name\":\"id\",\"type\":\"bytes\"}]}}},{\"name\":\"sdkAdLogs\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"SdkAdLog\",\"fields\":[{\"name\":\"sessionId\",\"type\":\"long\"},{\"name\":\"adLogGUID\",\"type\":\"string\"},{\"name\":\"sdkAdEvents\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"SdkAdEvent\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"params\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"timeOffset\",\"type\":\"long\"}]}}}]}}},{\"name\":\"agentTimestamp\",\"type\":\"long\"},{\"name\":\"testDevice\",\"type\":\"boolean\",\"default\":false}]}");
    public CharSequence a;
    public List b;
    public List c;
    public long d;
    public boolean e;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private CharSequence a;
        private List b;
        private List c;
        private long d;
        private boolean e;

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(SdkLogRequest.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public SdkLogRequest build() {
            try {
                SdkLogRequest sdkLogRequest = new SdkLogRequest();
                sdkLogRequest.a = fieldSetFlags()[0] ? this.a : (CharSequence) defaultValue(fields()[0]);
                sdkLogRequest.b = fieldSetFlags()[1] ? this.b : (List) defaultValue(fields()[1]);
                sdkLogRequest.c = fieldSetFlags()[2] ? this.c : (List) defaultValue(fields()[2]);
                sdkLogRequest.d = fieldSetFlags()[3] ? this.d : ((Long) defaultValue(fields()[3])).longValue();
                sdkLogRequest.e = fieldSetFlags()[4] ? this.e : ((Boolean) defaultValue(fields()[4])).booleanValue();
                return sdkLogRequest;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdReportedIds() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearAgentTimestamp() {
            fieldSetFlags()[3] = false;
            return this;
        }

        public Builder clearApiKey() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearSdkAdLogs() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearTestDevice() {
            fieldSetFlags()[4] = false;
            return this;
        }

        public List getAdReportedIds() {
            return this.b;
        }

        public Long getAgentTimestamp() {
            return Long.valueOf(this.d);
        }

        public CharSequence getApiKey() {
            return this.a;
        }

        public List getSdkAdLogs() {
            return this.c;
        }

        public Boolean getTestDevice() {
            return Boolean.valueOf(this.e);
        }

        public boolean hasAdReportedIds() {
            return fieldSetFlags()[1];
        }

        public boolean hasAgentTimestamp() {
            return fieldSetFlags()[3];
        }

        public boolean hasApiKey() {
            return fieldSetFlags()[0];
        }

        public boolean hasSdkAdLogs() {
            return fieldSetFlags()[2];
        }

        public boolean hasTestDevice() {
            return fieldSetFlags()[4];
        }

        public Builder setAdReportedIds(List list) {
            validate(fields()[1], list);
            this.b = list;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setAgentTimestamp(long j) {
            validate(fields()[3], Long.valueOf(j));
            this.d = j;
            fieldSetFlags()[3] = true;
            return this;
        }

        public Builder setApiKey(CharSequence charSequence) {
            validate(fields()[0], charSequence);
            this.a = charSequence;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setSdkAdLogs(List list) {
            validate(fields()[2], list);
            this.c = list;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setTestDevice(boolean z) {
            validate(fields()[4], Boolean.valueOf(z));
            this.e = z;
            fieldSetFlags()[4] = true;
            return this;
        }
    }

    SdkLogRequest() {
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return this.a;
            case 1:
                return this.b;
            case 2:
                return this.c;
            case 3:
                return Long.valueOf(this.d);
            case 4:
                return Boolean.valueOf(this.e);
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
            case 2:
                this.c = (List) obj;
                return;
            case 3:
                this.d = ((Long) obj).longValue();
                return;
            case 4:
                this.e = ((Boolean) obj).booleanValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
