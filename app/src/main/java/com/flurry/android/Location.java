package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

/* loaded from: classes.dex */
public class Location extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Location\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"lat\",\"type\":\"float\",\"default\":0.0},{\"name\":\"lon\",\"type\":\"float\",\"default\":0.0}]}");
    public float a;
    public float b;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private float a;
        private float b;

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(Location.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public Location build() {
            try {
                Location location = new Location();
                location.a = fieldSetFlags()[0] ? this.a : ((Float) defaultValue(fields()[0])).floatValue();
                location.b = fieldSetFlags()[1] ? this.b : ((Float) defaultValue(fields()[1])).floatValue();
                return location;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearLat() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearLon() {
            fieldSetFlags()[1] = false;
            return this;
        }

        public Float getLat() {
            return Float.valueOf(this.a);
        }

        public Float getLon() {
            return Float.valueOf(this.b);
        }

        public boolean hasLat() {
            return fieldSetFlags()[0];
        }

        public boolean hasLon() {
            return fieldSetFlags()[1];
        }

        public Builder setLat(float f) {
            validate(fields()[0], Float.valueOf(f));
            this.a = f;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setLon(float f) {
            validate(fields()[1], Float.valueOf(f));
            this.b = f;
            fieldSetFlags()[1] = true;
            return this;
        }
    }

    Location() {
    }

    public static Builder a() {
        return new Builder();
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Float.valueOf(this.a);
            case 1:
                return Float.valueOf(this.b);
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
                this.a = ((Float) obj).floatValue();
                return;
            case 1:
                this.b = ((Float) obj).floatValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
