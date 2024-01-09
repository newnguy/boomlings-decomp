package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.DateSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;

/* loaded from: classes.dex */
public class StdSerializers {

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class BooleanSerializer extends NonTypedScalarSerializerBase {
        final boolean _forPrimitive;

        public BooleanSerializer(boolean z) {
            super(Boolean.class);
            this._forPrimitive = z;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", !this._forPrimitive);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBoolean(bool.booleanValue());
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class CalendarSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.CalendarSerializer {
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class DoubleSerializer extends NonTypedScalarSerializerBase {
        static final DoubleSerializer instance = new DoubleSerializer();

        public DoubleSerializer() {
            super(Double.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(d.doubleValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class FloatSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        static final FloatSerializer instance = new FloatSerializer();

        public FloatSerializer() {
            super(Float.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(f.floatValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class IntLikeSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        static final IntLikeSerializer instance = new IntLikeSerializer();

        public IntLikeSerializer() {
            super(Number.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(number.intValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class IntegerSerializer extends NonTypedScalarSerializerBase {
        public IntegerSerializer() {
            super(Integer.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(num.intValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class LongSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        static final LongSerializer instance = new LongSerializer();

        public LongSerializer() {
            super(Long.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(l.longValue());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class NumberSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        public static final NumberSerializer instance = new NumberSerializer();

        public NumberSerializer() {
            super(Number.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("number", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (number instanceof BigDecimal) {
                jsonGenerator.writeNumber((BigDecimal) number);
            } else if (number instanceof BigInteger) {
                jsonGenerator.writeNumber((BigInteger) number);
            } else if (number instanceof Integer) {
                jsonGenerator.writeNumber(number.intValue());
            } else if (number instanceof Long) {
                jsonGenerator.writeNumber(number.longValue());
            } else if (number instanceof Double) {
                jsonGenerator.writeNumber(number.doubleValue());
            } else if (number instanceof Float) {
                jsonGenerator.writeNumber(number.floatValue());
            } else if ((number instanceof Byte) || (number instanceof Short)) {
                jsonGenerator.writeNumber(number.intValue());
            } else {
                jsonGenerator.writeNumber(number.toString());
            }
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class SerializableSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.SerializableSerializer {
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class SerializableWithTypeSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer {
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class SqlDateSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        public SqlDateSerializer() {
            super(Date.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(date.toString());
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class SqlTimeSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase {
        public SqlTimeSerializer() {
            super(Time.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(time.toString());
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class StringSerializer extends NonTypedScalarSerializerBase {
        public StringSerializer() {
            super(String.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(String str, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(str);
        }
    }

    @JacksonStdImpl
    @Deprecated
    /* loaded from: classes.dex */
    public final class UtilDateSerializer extends DateSerializer {
    }

    protected StdSerializers() {
    }
}
