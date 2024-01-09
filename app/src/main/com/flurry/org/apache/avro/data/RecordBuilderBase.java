package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericData;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.apache.avro.io.parsing.ResolvingGrammarGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public abstract class RecordBuilderBase implements RecordBuilder {
    private static final ConcurrentMap DEFAULT_VALUE_CACHE = new ConcurrentHashMap();
    private static final Schema.Field[] EMPTY_FIELDS = new Schema.Field[0];
    private final GenericData data;
    private final boolean[] fieldSetFlags;
    private final Schema.Field[] fields;
    private final Schema schema;
    private BinaryEncoder encoder = null;
    private BinaryDecoder decoder = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public RecordBuilderBase(Schema schema, GenericData genericData) {
        this.schema = schema;
        this.data = genericData;
        this.fields = (Schema.Field[]) schema.getFields().toArray(EMPTY_FIELDS);
        this.fieldSetFlags = new boolean[this.fields.length];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RecordBuilderBase(RecordBuilderBase recordBuilderBase, GenericData genericData) {
        this.schema = recordBuilderBase.schema;
        this.data = genericData;
        this.fields = (Schema.Field[]) this.schema.getFields().toArray(EMPTY_FIELDS);
        this.fieldSetFlags = new boolean[recordBuilderBase.fieldSetFlags.length];
        System.arraycopy(recordBuilderBase.fieldSetFlags, 0, this.fieldSetFlags, 0, this.fieldSetFlags.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isValidValue(Schema.Field field, Object obj) {
        Schema schema;
        Schema.Type type;
        if (obj == null && (type = (schema = field.schema()).getType()) != Schema.Type.NULL) {
            if (type == Schema.Type.UNION) {
                for (Schema schema2 : schema.getTypes()) {
                    if (schema2.getType() == Schema.Type.NULL) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final GenericData data() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object defaultValue(Schema.Field field) {
        JsonNode defaultValue = field.defaultValue();
        if (defaultValue == null) {
            throw new AvroRuntimeException("Field " + field + " not set and has no default value");
        }
        if (defaultValue.isNull() && (field.schema().getType() == Schema.Type.NULL || (field.schema().getType() == Schema.Type.UNION && ((Schema) field.schema().getTypes().get(0)).getType() == Schema.Type.NULL))) {
            return null;
        }
        ConcurrentMap concurrentMap = (ConcurrentMap) DEFAULT_VALUE_CACHE.get(this.schema.getFullName());
        if (concurrentMap == null) {
            DEFAULT_VALUE_CACHE.putIfAbsent(this.schema.getFullName(), new ConcurrentHashMap(this.fields.length));
            concurrentMap = (ConcurrentMap) DEFAULT_VALUE_CACHE.get(this.schema.getFullName());
        }
        Object obj = concurrentMap.get(Integer.valueOf(field.pos()));
        if (obj == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, this.encoder);
            ResolvingGrammarGenerator.encode(this.encoder, field.schema(), defaultValue);
            this.encoder.flush();
            this.decoder = DecoderFactory.get().binaryDecoder(byteArrayOutputStream.toByteArray(), this.decoder);
            obj = this.data.createDatumReader(field.schema()).read(null, this.decoder);
            concurrentMap.putIfAbsent(Integer.valueOf(field.pos()), obj);
        }
        return this.data.deepCopy(field.schema(), obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            RecordBuilderBase recordBuilderBase = (RecordBuilderBase) obj;
            if (Arrays.equals(this.fieldSetFlags, recordBuilderBase.fieldSetFlags)) {
                return this.schema == null ? recordBuilderBase.schema == null : this.schema.equals(recordBuilderBase.schema);
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean[] fieldSetFlags() {
        return this.fieldSetFlags;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Schema.Field[] fields() {
        return this.fields;
    }

    public int hashCode() {
        return (this.schema == null ? 0 : this.schema.hashCode()) + ((Arrays.hashCode(this.fieldSetFlags) + 31) * 31);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Schema schema() {
        return this.schema;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validate(Schema.Field field, Object obj) {
        if (!isValidValue(field, obj) && field.defaultValue() == null) {
            throw new AvroRuntimeException("Field " + field + " does not accept null values");
        }
    }
}
