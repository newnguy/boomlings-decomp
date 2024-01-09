package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PrimitiveArrayDeserializers {
    static final PrimitiveArrayDeserializers instance = new PrimitiveArrayDeserializers();
    HashMap _allDeserializers = new HashMap();

    /* loaded from: classes.dex */
    abstract class Base extends StdDeserializer {
        protected Base(Class cls) {
            super(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class BooleanDeser extends Base {
        public BooleanDeser() {
            super(boolean[].class);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
                boolean[] zArr = (boolean[]) booleanBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                    if (i2 >= zArr.length) {
                        i = 0;
                        zArr = (boolean[]) booleanBuilder.appendCompletedChunk(zArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    zArr[i] = _parseBooleanPrimitive;
                }
                return (boolean[]) booleanBuilder.completeAndClearBuffer(zArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class ByteDeser extends Base {
        public ByteDeser() {
            super(byte[].class);
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            byte byteValue;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                JsonToken currentToken = jsonParser.getCurrentToken();
                if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    byteValue = jsonParser.getByteValue();
                } else if (currentToken != JsonToken.VALUE_NULL) {
                    throw deserializationContext.mappingException(this._valueClass.getComponentType());
                } else {
                    byteValue = 0;
                }
                return new byte[]{byteValue};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            byte byteValue;
            int i;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser.getBinaryValue(deserializationContext.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext.getArrayBuilders().getByteBuilder();
            byte[] bArr = (byte[]) byteBuilder.resetAndStart();
            int i2 = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    return (byte[]) byteBuilder.completeAndClearBuffer(bArr, i2);
                }
                if (nextToken == JsonToken.VALUE_NUMBER_INT || nextToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    byteValue = jsonParser.getByteValue();
                } else if (nextToken != JsonToken.VALUE_NULL) {
                    throw deserializationContext.mappingException(this._valueClass.getComponentType());
                } else {
                    byteValue = 0;
                }
                if (i2 >= bArr.length) {
                    i = 0;
                    bArr = (byte[]) byteBuilder.appendCompletedChunk(bArr, i2);
                } else {
                    i = i2;
                }
                i2 = i + 1;
                bArr[i] = byteValue;
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class CharDeser extends Base {
        public CharDeser() {
            super(char[].class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (!jsonParser.isExpectedStartArrayToken()) {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext.mappingException(this._valueClass);
            } else {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken == JsonToken.END_ARRAY) {
                        return sb.toString().toCharArray();
                    }
                    if (nextToken != JsonToken.VALUE_STRING) {
                        throw deserializationContext.mappingException(Character.TYPE);
                    }
                    String text = jsonParser.getText();
                    if (text.length() != 1) {
                        throw JsonMappingException.from(jsonParser, "Can not convert a JSON String of length " + text.length() + " into a char element of char array");
                    }
                    sb.append(text.charAt(0));
                }
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class DoubleDeser extends Base {
        public DoubleDeser() {
            super(double[].class);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
                double[] dArr = (double[]) doubleBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                    if (i2 >= dArr.length) {
                        i = 0;
                        dArr = (double[]) doubleBuilder.appendCompletedChunk(dArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    dArr[i] = _parseDoublePrimitive;
                }
                return (double[]) doubleBuilder.completeAndClearBuffer(dArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class FloatDeser extends Base {
        public FloatDeser() {
            super(float[].class);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
                float[] fArr = (float[]) floatBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                    if (i2 >= fArr.length) {
                        i = 0;
                        fArr = (float[]) floatBuilder.appendCompletedChunk(fArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    fArr[i] = _parseFloatPrimitive;
                }
                return (float[]) floatBuilder.completeAndClearBuffer(fArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class IntDeser extends Base {
        public IntDeser() {
            super(int[].class);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
                int[] iArr = (int[]) intBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                    if (i2 >= iArr.length) {
                        i = 0;
                        iArr = (int[]) intBuilder.appendCompletedChunk(iArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    iArr[i] = _parseIntPrimitive;
                }
                return (int[]) intBuilder.completeAndClearBuffer(iArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class LongDeser extends Base {
        public LongDeser() {
            super(long[].class);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
                long[] jArr = (long[]) longBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                    if (i2 >= jArr.length) {
                        i = 0;
                        jArr = (long[]) longBuilder.appendCompletedChunk(jArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    jArr[i] = _parseLongPrimitive;
                }
                return (long[]) longBuilder.completeAndClearBuffer(jArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class ShortDeser extends Base {
        public ShortDeser() {
            super(short[].class);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            }
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
            }
            throw deserializationContext.mappingException(this._valueClass);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
                short[] sArr = (short[]) shortBuilder.resetAndStart();
                int i2 = 0;
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                    if (i2 >= sArr.length) {
                        i = 0;
                        sArr = (short[]) shortBuilder.appendCompletedChunk(sArr, i2);
                    } else {
                        i = i2;
                    }
                    i2 = i + 1;
                    sArr[i] = _parseShortPrimitive;
                }
                return (short[]) shortBuilder.completeAndClearBuffer(sArr, i2);
            }
            return handleNonArray(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    final class StringDeser extends Base {
        public StringDeser() {
            super(String[].class);
        }

        private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) {
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                String[] strArr = new String[1];
                strArr[0] = jsonParser.getCurrentToken() != JsonToken.VALUE_NULL ? jsonParser.getText() : null;
                return strArr;
            } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
                return null;
            } else {
                throw deserializationContext.mappingException(this._valueClass);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            int i;
            if (!jsonParser.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser, deserializationContext);
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i2 = 0;
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY) {
                    String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2, String.class);
                    deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                    return strArr;
                }
                String text = nextToken == JsonToken.VALUE_NULL ? null : jsonParser.getText();
                if (i2 >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i = 0;
                } else {
                    i = i2;
                }
                i2 = i + 1;
                resetAndStart[i] = text;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrimitiveArrayDeserializers() {
        add(Boolean.TYPE, new BooleanDeser());
        add(Byte.TYPE, new ByteDeser());
        add(Short.TYPE, new ShortDeser());
        add(Integer.TYPE, new IntDeser());
        add(Long.TYPE, new LongDeser());
        add(Float.TYPE, new FloatDeser());
        add(Double.TYPE, new DoubleDeser());
        add(String.class, new StringDeser());
        add(Character.TYPE, new CharDeser());
    }

    private void add(Class cls, JsonDeserializer jsonDeserializer) {
        this._allDeserializers.put(TypeFactory.defaultInstance().constructType(cls), jsonDeserializer);
    }

    public static HashMap getAll() {
        return instance._allDeserializers;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }
}
