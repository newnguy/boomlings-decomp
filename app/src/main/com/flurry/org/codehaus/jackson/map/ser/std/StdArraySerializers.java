package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class StdArraySerializers {

    /* loaded from: classes.dex */
    public abstract class ArraySerializerBase extends ContainerSerializerBase {
        protected final BeanProperty _property;
        protected final TypeSerializer _valueTypeSerializer;

        /* JADX INFO: Access modifiers changed from: protected */
        public ArraySerializerBase(Class cls, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(cls);
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeStartArray();
            serializeContents(obj, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        protected abstract void serializeContents(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForArray(obj, jsonGenerator);
            serializeContents(obj, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(obj, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class BooleanArraySerializer extends ArraySerializerBase {
        public BooleanArraySerializer() {
            super(boolean[].class, null, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("boolean"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (boolean z : zArr) {
                jsonGenerator.writeBoolean(z);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class ByteArraySerializer extends SerializerBase {
        public ByteArraySerializer() {
            super(byte[].class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBinary(bArr);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForScalar(bArr, jsonGenerator);
            jsonGenerator.writeBinary(bArr);
            typeSerializer.writeTypeSuffixForScalar(bArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class CharArraySerializer extends SerializerBase {
        public CharArraySerializer() {
            super(char[].class);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            ObjectNode createSchemaNode2 = createSchemaNode("string");
            createSchemaNode2.put("type", "string");
            createSchemaNode.put("items", createSchemaNode2);
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (!serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeString(cArr, 0, cArr.length);
                return;
            }
            jsonGenerator.writeStartArray();
            _writeArrayContents(jsonGenerator, cArr);
            jsonGenerator.writeEndArray();
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class DoubleArraySerializer extends ArraySerializerBase {
        public DoubleArraySerializer() {
            super(double[].class, null, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (double d : dArr) {
                jsonGenerator.writeNumber(d);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class FloatArraySerializer extends ArraySerializerBase {
        public FloatArraySerializer() {
            this(null);
        }

        public FloatArraySerializer(TypeSerializer typeSerializer) {
            super(float[].class, typeSerializer, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(typeSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (float f : fArr) {
                jsonGenerator.writeNumber(f);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class IntArraySerializer extends ArraySerializerBase {
        public IntArraySerializer() {
            super(int[].class, null, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (int i : iArr) {
                jsonGenerator.writeNumber(i);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class LongArraySerializer extends ArraySerializerBase {
        public LongArraySerializer() {
            this(null);
        }

        public LongArraySerializer(TypeSerializer typeSerializer) {
            super(long[].class, typeSerializer, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(typeSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("number", true));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (long j : jArr) {
                jsonGenerator.writeNumber(j);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class ShortArraySerializer extends ArraySerializerBase {
        public ShortArraySerializer() {
            this(null);
        }

        public ShortArraySerializer(TypeSerializer typeSerializer) {
            super(short[].class, typeSerializer, null);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(typeSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("integer"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            for (short s : sArr) {
                jsonGenerator.writeNumber((int) s);
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class StringArraySerializer extends ArraySerializerBase implements ResolvableSerializer {
        protected JsonSerializer _elementSerializer;

        public StringArraySerializer(BeanProperty beanProperty) {
            super(String[].class, null, beanProperty);
        }

        private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer jsonSerializer) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
                }
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
        public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.put("items", createSchemaNode("string"));
            return createSchemaNode;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
        public void resolve(SerializerProvider serializerProvider) {
            JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(String.class, this._property);
            if (findValueSerializer == null || findValueSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null) {
                return;
            }
            this._elementSerializer = findValueSerializer;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
        public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int length = strArr.length;
            if (length == 0) {
                return;
            }
            if (this._elementSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }
    }
}
