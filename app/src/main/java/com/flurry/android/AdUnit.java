package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

/* loaded from: classes.dex */
public class AdUnit extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdUnit\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"adSpace\",\"type\":\"string\"},{\"name\":\"expiration\",\"type\":\"long\"},{\"name\":\"adFrames\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdFrame\",\"fields\":[{\"name\":\"binding\",\"type\":\"int\"},{\"name\":\"display\",\"type\":\"string\"},{\"name\":\"content\",\"type\":\"string\"},{\"name\":\"adSpaceLayout\",\"type\":{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}},{\"name\":\"callbacks\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Callback\",\"fields\":[{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"actions\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}}},{\"name\":\"adGuid\",\"type\":\"string\"}]}}},{\"name\":\"combinable\",\"type\":\"int\",\"default\":0}]}");
    public CharSequence a;
    public long b;
    public List c;
    public int d;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private CharSequence a;
        private long b;
        private List c;
        private int d;

        private Builder() {
            super(AdUnit.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdUnit build() {
            try {
                AdUnit adUnit = new AdUnit();
                adUnit.a = fieldSetFlags()[0] ? this.a : (CharSequence) defaultValue(fields()[0]);
                adUnit.b = fieldSetFlags()[1] ? this.b : ((Long) defaultValue(fields()[1])).longValue();
                adUnit.c = fieldSetFlags()[2] ? this.c : (List) defaultValue(fields()[2]);
                adUnit.d = fieldSetFlags()[3] ? this.d : ((Integer) defaultValue(fields()[3])).intValue();
                return adUnit;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdFrames() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearAdSpace() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearCombinable() {
            fieldSetFlags()[3] = false;
            return this;
        }

        public Builder clearExpiration() {
            fieldSetFlags()[1] = false;
            return this;
        }

        public List getAdFrames() {
            return this.c;
        }

        public CharSequence getAdSpace() {
            return this.a;
        }

        public Integer getCombinable() {
            return Integer.valueOf(this.d);
        }

        public Long getExpiration() {
            return Long.valueOf(this.b);
        }

        public boolean hasAdFrames() {
            return fieldSetFlags()[2];
        }

        public boolean hasAdSpace() {
            return fieldSetFlags()[0];
        }

        public boolean hasCombinable() {
            return fieldSetFlags()[3];
        }

        public boolean hasExpiration() {
            return fieldSetFlags()[1];
        }

        public Builder setAdFrames(List list) {
            validate(fields()[2], list);
            this.c = list;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setAdSpace(CharSequence charSequence) {
            validate(fields()[0], charSequence);
            this.a = charSequence;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setCombinable(int i) {
            validate(fields()[3], Integer.valueOf(i));
            this.d = i;
            fieldSetFlags()[3] = true;
            return this;
        }

        public Builder setExpiration(long j) {
            validate(fields()[1], Long.valueOf(j));
            this.b = j;
            fieldSetFlags()[1] = true;
            return this;
        }
    }

    AdUnit() {
    }

    public final CharSequence a() {
        return this.a;
    }

    public final Long b() {
        return Long.valueOf(this.b);
    }

    public final List c() {
        return this.c;
    }

    public final Integer d() {
        return Integer.valueOf(this.d);
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return this.a;
            case 1:
                return Long.valueOf(this.b);
            case 2:
                return this.c;
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
                this.a = (CharSequence) obj;
                return;
            case 1:
                this.b = ((Long) obj).longValue();
                return;
            case 2:
                this.c = (List) obj;
                return;
            case 3:
                this.d = ((Integer) obj).intValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
