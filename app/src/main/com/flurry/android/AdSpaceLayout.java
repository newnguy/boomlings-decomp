package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AdSpaceLayout extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}");
    public int a;
    public int b;
    public CharSequence c;
    public CharSequence d;
    public CharSequence e;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private int a;
        private int b;
        private CharSequence c;
        private CharSequence d;
        private CharSequence e;

        private Builder() {
            super(AdSpaceLayout.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdSpaceLayout build() {
            try {
                AdSpaceLayout adSpaceLayout = new AdSpaceLayout();
                adSpaceLayout.a = fieldSetFlags()[0] ? this.a : ((Integer) defaultValue(fields()[0])).intValue();
                adSpaceLayout.b = fieldSetFlags()[1] ? this.b : ((Integer) defaultValue(fields()[1])).intValue();
                adSpaceLayout.c = fieldSetFlags()[2] ? this.c : (CharSequence) defaultValue(fields()[2]);
                adSpaceLayout.d = fieldSetFlags()[3] ? this.d : (CharSequence) defaultValue(fields()[3]);
                adSpaceLayout.e = fieldSetFlags()[4] ? this.e : (CharSequence) defaultValue(fields()[4]);
                return adSpaceLayout;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdHeight() {
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearAdWidth() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearAlignment() {
            this.e = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        public Builder clearFix() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearFormat() {
            this.d = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        public Integer getAdHeight() {
            return Integer.valueOf(this.b);
        }

        public Integer getAdWidth() {
            return Integer.valueOf(this.a);
        }

        public CharSequence getAlignment() {
            return this.e;
        }

        public CharSequence getFix() {
            return this.c;
        }

        public CharSequence getFormat() {
            return this.d;
        }

        public boolean hasAdHeight() {
            return fieldSetFlags()[1];
        }

        public boolean hasAdWidth() {
            return fieldSetFlags()[0];
        }

        public boolean hasAlignment() {
            return fieldSetFlags()[4];
        }

        public boolean hasFix() {
            return fieldSetFlags()[2];
        }

        public boolean hasFormat() {
            return fieldSetFlags()[3];
        }

        public Builder setAdHeight(int i) {
            validate(fields()[1], Integer.valueOf(i));
            this.b = i;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setAdWidth(int i) {
            validate(fields()[0], Integer.valueOf(i));
            this.a = i;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setAlignment(CharSequence charSequence) {
            validate(fields()[4], charSequence);
            this.e = charSequence;
            fieldSetFlags()[4] = true;
            return this;
        }

        public Builder setFix(CharSequence charSequence) {
            validate(fields()[2], charSequence);
            this.c = charSequence;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setFormat(CharSequence charSequence) {
            validate(fields()[3], charSequence);
            this.d = charSequence;
            fieldSetFlags()[3] = true;
            return this;
        }
    }

    AdSpaceLayout() {
    }

    public final Integer a() {
        return Integer.valueOf(this.a);
    }

    public final Integer b() {
        return Integer.valueOf(this.b);
    }

    public final CharSequence c() {
        return this.c;
    }

    public final CharSequence d() {
        return this.d;
    }

    public final CharSequence e() {
        return this.e;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Integer.valueOf(this.a);
            case 1:
                return Integer.valueOf(this.b);
            case 2:
                return this.c;
            case 3:
                return this.d;
            case 4:
                return this.e;
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
                this.c = (CharSequence) obj;
                return;
            case 3:
                this.d = (CharSequence) obj;
                return;
            case 4:
                this.e = (CharSequence) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
