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
public class AdResponse extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdResponse\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"adUnits\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdUnit\",\"fields\":[{\"name\":\"adSpace\",\"type\":\"string\"},{\"name\":\"expiration\",\"type\":\"long\"},{\"name\":\"adFrames\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdFrame\",\"fields\":[{\"name\":\"binding\",\"type\":\"int\"},{\"name\":\"display\",\"type\":\"string\"},{\"name\":\"content\",\"type\":\"string\"},{\"name\":\"adSpaceLayout\",\"type\":{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}},{\"name\":\"callbacks\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Callback\",\"fields\":[{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"actions\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}}},{\"name\":\"adGuid\",\"type\":\"string\"}]}}},{\"name\":\"combinable\",\"type\":\"int\",\"default\":0}]}}},{\"name\":\"errors\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"default\":[]}]}");
    public List a;
    public List b;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private List a;
        private List b;

        private Builder() {
            super(AdResponse.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdResponse build() {
            try {
                AdResponse adResponse = new AdResponse();
                adResponse.a = fieldSetFlags()[0] ? this.a : (List) defaultValue(fields()[0]);
                adResponse.b = fieldSetFlags()[1] ? this.b : (List) defaultValue(fields()[1]);
                return adResponse;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdUnits() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearErrors() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public List getAdUnits() {
            return this.a;
        }

        public List getErrors() {
            return this.b;
        }

        public boolean hasAdUnits() {
            return fieldSetFlags()[0];
        }

        public boolean hasErrors() {
            return fieldSetFlags()[1];
        }

        public Builder setAdUnits(List list) {
            validate(fields()[0], list);
            this.a = list;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setErrors(List list) {
            validate(fields()[1], list);
            this.b = list;
            fieldSetFlags()[1] = true;
            return this;
        }
    }

    AdResponse() {
    }

    public final List a() {
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
                this.a = (List) obj;
                return;
            case 1:
                this.b = (List) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
