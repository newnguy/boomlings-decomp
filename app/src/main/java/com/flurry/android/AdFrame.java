package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;

/* loaded from: classes.dex */
public class AdFrame extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdFrame\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"binding\",\"type\":\"int\"},{\"name\":\"display\",\"type\":\"string\"},{\"name\":\"content\",\"type\":\"string\"},{\"name\":\"adSpaceLayout\",\"type\":{\"type\":\"record\",\"name\":\"AdSpaceLayout\",\"fields\":[{\"name\":\"adWidth\",\"type\":\"int\"},{\"name\":\"adHeight\",\"type\":\"int\"},{\"name\":\"fix\",\"type\":\"string\"},{\"name\":\"format\",\"type\":\"string\"},{\"name\":\"alignment\",\"type\":\"string\"}]}},{\"name\":\"callbacks\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Callback\",\"fields\":[{\"name\":\"event\",\"type\":\"string\"},{\"name\":\"actions\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}}},{\"name\":\"adGuid\",\"type\":\"string\"}]}");
    public int a;
    public CharSequence b;
    public CharSequence c;
    public AdSpaceLayout d;
    public List e;
    public CharSequence f;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private int a;
        private CharSequence b;
        private CharSequence c;
        private AdSpaceLayout d;
        private List e;
        private CharSequence f;

        private Builder() {
            super(AdFrame.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdFrame build() {
            try {
                AdFrame adFrame = new AdFrame();
                adFrame.a = fieldSetFlags()[0] ? this.a : ((Integer) defaultValue(fields()[0])).intValue();
                adFrame.b = fieldSetFlags()[1] ? this.b : (CharSequence) defaultValue(fields()[1]);
                adFrame.c = fieldSetFlags()[2] ? this.c : (CharSequence) defaultValue(fields()[2]);
                adFrame.d = fieldSetFlags()[3] ? this.d : (AdSpaceLayout) defaultValue(fields()[3]);
                adFrame.e = fieldSetFlags()[4] ? this.e : (List) defaultValue(fields()[4]);
                adFrame.f = fieldSetFlags()[5] ? this.f : (CharSequence) defaultValue(fields()[5]);
                return adFrame;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdGuid() {
            this.f = null;
            fieldSetFlags()[5] = false;
            return this;
        }

        public Builder clearAdSpaceLayout() {
            this.d = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        public Builder clearBinding() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearCallbacks() {
            this.e = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        public Builder clearContent() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearDisplay() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public CharSequence getAdGuid() {
            return this.f;
        }

        public AdSpaceLayout getAdSpaceLayout() {
            return this.d;
        }

        public Integer getBinding() {
            return Integer.valueOf(this.a);
        }

        public List getCallbacks() {
            return this.e;
        }

        public CharSequence getContent() {
            return this.c;
        }

        public CharSequence getDisplay() {
            return this.b;
        }

        public boolean hasAdGuid() {
            return fieldSetFlags()[5];
        }

        public boolean hasAdSpaceLayout() {
            return fieldSetFlags()[3];
        }

        public boolean hasBinding() {
            return fieldSetFlags()[0];
        }

        public boolean hasCallbacks() {
            return fieldSetFlags()[4];
        }

        public boolean hasContent() {
            return fieldSetFlags()[2];
        }

        public boolean hasDisplay() {
            return fieldSetFlags()[1];
        }

        public Builder setAdGuid(CharSequence charSequence) {
            validate(fields()[5], charSequence);
            this.f = charSequence;
            fieldSetFlags()[5] = true;
            return this;
        }

        public Builder setAdSpaceLayout(AdSpaceLayout adSpaceLayout) {
            validate(fields()[3], adSpaceLayout);
            this.d = adSpaceLayout;
            fieldSetFlags()[3] = true;
            return this;
        }

        public Builder setBinding(int i) {
            validate(fields()[0], Integer.valueOf(i));
            this.a = i;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setCallbacks(List list) {
            validate(fields()[4], list);
            this.e = list;
            fieldSetFlags()[4] = true;
            return this;
        }

        public Builder setContent(CharSequence charSequence) {
            validate(fields()[2], charSequence);
            this.c = charSequence;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setDisplay(CharSequence charSequence) {
            validate(fields()[1], charSequence);
            this.b = charSequence;
            fieldSetFlags()[1] = true;
            return this;
        }
    }

    AdFrame() {
    }

    public final Integer a() {
        return Integer.valueOf(this.a);
    }

    public final CharSequence b() {
        return this.b;
    }

    public final CharSequence c() {
        return this.c;
    }

    public final AdSpaceLayout d() {
        return this.d;
    }

    public final List e() {
        return this.e;
    }

    public final CharSequence f() {
        return this.f;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return Integer.valueOf(this.a);
            case 1:
                return this.b;
            case 2:
                return this.c;
            case 3:
                return this.d;
            case 4:
                return this.e;
            case 5:
                return this.f;
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
                this.b = (CharSequence) obj;
                return;
            case 2:
                this.c = (CharSequence) obj;
                return;
            case 3:
                this.d = (AdSpaceLayout) obj;
                return;
            case 4:
                this.e = (List) obj;
                return;
            case 5:
                this.f = (CharSequence) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
