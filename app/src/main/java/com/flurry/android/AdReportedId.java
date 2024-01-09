package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class AdReportedId extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdReportedId\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"type\",\"type\":\"int\"},{\"name\":\"id\",\"type\":\"bytes\"}]}");
    public int a;
    public ByteBuffer b;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
        private int a;
        private ByteBuffer b;

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(AdReportedId.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdReportedId build() {
            try {
                AdReportedId adReportedId = new AdReportedId();
                adReportedId.a = fieldSetFlags()[0] ? this.a : ((Integer) defaultValue(fields()[0])).intValue();
                adReportedId.b = fieldSetFlags()[1] ? this.b : (ByteBuffer) defaultValue(fields()[1]);
                return adReportedId;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearId() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearType() {
            fieldSetFlags()[0] = false;
            return this;
        }

        public ByteBuffer getId() {
            return this.b;
        }

        public Integer getType() {
            return Integer.valueOf(this.a);
        }

        public boolean hasId() {
            return fieldSetFlags()[1];
        }

        public boolean hasType() {
            return fieldSetFlags()[0];
        }

        public Builder setId(ByteBuffer byteBuffer) {
            validate(fields()[1], byteBuffer);
            this.b = byteBuffer;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setType(int i) {
            validate(fields()[0], Integer.valueOf(i));
            this.a = i;
            fieldSetFlags()[0] = true;
            return this;
        }
    }

    AdReportedId() {
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
                this.a = ((Integer) obj).intValue();
                return;
            case 1:
                this.b = (ByteBuffer) obj;
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
