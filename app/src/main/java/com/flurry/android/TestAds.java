package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

/* loaded from: classes.dex */
public class TestAds extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TestAds\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"adspacePlacement\",\"type\":\"int\",\"default\":0}]}");
    public int a;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private int a;

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(TestAds.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public TestAds build() {
            try {
                TestAds testAds = new TestAds();
                testAds.a = fieldSetFlags()[0] ? this.a : ((Integer) defaultValue(fields()[0])).intValue();
                return testAds;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdspacePlacement() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public Integer getAdspacePlacement() {
            return Integer.valueOf(this.a);
        }

        public boolean hasAdspacePlacement() {
            return fieldSetFlags()[0];
        }

        public Builder setAdspacePlacement(int i) {
            validate(fields()[0], Integer.valueOf(i));
            this.a = i;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    TestAds() {
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Integer.valueOf(this.a);
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
                this.a = ((Integer) obj).intValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
