package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

/* loaded from: classes.dex */
public class AdViewContainer extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdViewContainer\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"viewWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"viewHeight\",\"type\":\"int\",\"default\":0},{\"name\":\"screenWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"screenHeight\",\"type\":\"int\",\"default\":0}]}");
    public int a;
    public int b;
    public int c;
    public int d;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private int a;
        private int b;
        private int c;
        private int d;

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(AdViewContainer.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdViewContainer build() {
            try {
                AdViewContainer adViewContainer = new AdViewContainer();
                adViewContainer.a = fieldSetFlags()[0] ? this.a : ((Integer) defaultValue(fields()[0])).intValue();
                adViewContainer.b = fieldSetFlags()[1] ? this.b : ((Integer) defaultValue(fields()[1])).intValue();
                adViewContainer.c = fieldSetFlags()[2] ? this.c : ((Integer) defaultValue(fields()[2])).intValue();
                adViewContainer.d = fieldSetFlags()[3] ? this.d : ((Integer) defaultValue(fields()[3])).intValue();
                return adViewContainer;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearScreenHeight() {
            fieldSetFlags()[3] = false;
            return this;
        }

        public Builder clearScreenWidth() {
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearViewHeight() {
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearViewWidth() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public Integer getScreenHeight() {
            return Integer.valueOf(this.d);
        }

        public Integer getScreenWidth() {
            return Integer.valueOf(this.c);
        }

        public Integer getViewHeight() {
            return Integer.valueOf(this.b);
        }

        public Integer getViewWidth() {
            return Integer.valueOf(this.a);
        }

        public boolean hasScreenHeight() {
            return fieldSetFlags()[3];
        }

        public boolean hasScreenWidth() {
            return fieldSetFlags()[2];
        }

        public boolean hasViewHeight() {
            return fieldSetFlags()[1];
        }

        public boolean hasViewWidth() {
            return fieldSetFlags()[0];
        }

        public Builder setScreenHeight(int i) {
            validate(fields()[3], Integer.valueOf(i));
            this.d = i;
            fieldSetFlags()[3] = true;
            return this;
        }

        public Builder setScreenWidth(int i) {
            validate(fields()[2], Integer.valueOf(i));
            this.c = i;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setViewHeight(int i) {
            validate(fields()[1], Integer.valueOf(i));
            this.b = i;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setViewWidth(int i) {
            validate(fields()[0], Integer.valueOf(i));
            this.a = i;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    AdViewContainer() {
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Integer.valueOf(this.a);
            case 1:
                return Integer.valueOf(this.b);
            case 2:
                return Integer.valueOf(this.c);
            case 3:
                return Integer.valueOf(this.d);
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
            case 1:
                this.b = ((Integer) obj).intValue();
                return;
            case 2:
                this.c = ((Integer) obj).intValue();
                return;
            case 3:
                this.d = ((Integer) obj).intValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
