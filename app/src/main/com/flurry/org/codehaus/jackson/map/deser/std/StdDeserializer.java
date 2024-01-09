package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

/* loaded from: classes.dex */
public abstract class StdDeserializer extends JsonDeserializer {
    protected final Class _valueClass;

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public class BigDecimalDeserializer extends StdScalarDeserializer {
        public BigDecimalDeserializer() {
            super(BigDecimal.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                try {
                    return new BigDecimal(trim);
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
                }
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public class BigIntegerDeserializer extends StdScalarDeserializer {
        public BigIntegerDeserializer() {
            super(BigInteger.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (jsonParser.getNumberType()) {
                    case INT:
                    case LONG:
                        return BigInteger.valueOf(jsonParser.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser.getDecimalValue().toBigInteger();
            } else {
                if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext.mappingException(this._valueClass, currentToken);
                }
            }
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                return new BigInteger(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation");
            }
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer {
        public BooleanDeserializer(Class cls, Boolean bool) {
            super(cls, bool);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class ByteDeserializer extends PrimitiveOrWrapperDeserializer {
        public ByteDeserializer(Class cls, Byte b) {
            super(cls, b);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer {
        public CharacterDeserializer(Class cls, Character ch) {
            super(cls, ch);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser.getIntValue();
                if (intValue >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
                if (text.length() == 0) {
                    return (Character) getEmptyValue();
                }
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer {
        public DoubleDeserializer(Class cls, Double d) {
            super(cls, d);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseDouble(jsonParser, deserializationContext);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class FloatDeserializer extends PrimitiveOrWrapperDeserializer {
        public FloatDeserializer(Class cls, Float f) {
            super(cls, f);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer {
        public IntegerDeserializer(Class cls, Integer num) {
            super(cls, num);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseInteger(jsonParser, deserializationContext);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class LongDeserializer extends PrimitiveOrWrapperDeserializer {
        public LongDeserializer(Class cls, Long l) {
            super(cls, l);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class NumberDeserializer extends StdScalarDeserializer {
        public NumberDeserializer() {
            super(Number.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Number valueOf;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : jsonParser.getNumberValue();
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : Double.valueOf(jsonParser.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                try {
                    if (trim.indexOf(46) >= 0) {
                        valueOf = deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? new BigDecimal(trim) : new Double(trim);
                    } else if (deserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS)) {
                        valueOf = new BigInteger(trim);
                    } else {
                        long parseLong = Long.parseLong(trim);
                        valueOf = (parseLong > 2147483647L || parseLong < -2147483648L) ? Long.valueOf(parseLong) : Integer.valueOf((int) parseLong);
                    }
                    return valueOf;
                } catch (IllegalArgumentException e) {
                    throw deserializationContext.weirdStringException(this._valueClass, "not a valid number");
                }
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                case VALUE_STRING:
                    return deserialize(jsonParser, deserializationContext);
                default:
                    return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class PrimitiveOrWrapperDeserializer extends StdScalarDeserializer {
        final Object _nullValue;

        protected PrimitiveOrWrapperDeserializer(Class cls, Object obj) {
            super(cls);
            this._nullValue = obj;
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public final Object getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    /* loaded from: classes.dex */
    public final class ShortDeserializer extends PrimitiveOrWrapperDeserializer {
        public ShortDeserializer(Class cls, Short sh) {
            super(cls, sh);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    /* loaded from: classes.dex */
    public class SqlDateDeserializer extends StdScalarDeserializer {
        public SqlDateDeserializer() {
            super(Date.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            java.util.Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            return new Date(_parseDate.getTime());
        }
    }

    /* loaded from: classes.dex */
    public class StackTraceElementDeserializer extends StdScalarDeserializer {
        public StackTraceElementDeserializer() {
            super(StackTraceElement.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.START_OBJECT) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            String str = "";
            String str2 = "";
            String str3 = "";
            int i = -1;
            while (true) {
                JsonToken nextValue = jsonParser.nextValue();
                if (nextValue == JsonToken.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String currentName = jsonParser.getCurrentName();
                if ("className".equals(currentName)) {
                    str = jsonParser.getText();
                } else if ("fileName".equals(currentName)) {
                    str3 = jsonParser.getText();
                } else if ("lineNumber".equals(currentName)) {
                    if (!nextValue.isNumeric()) {
                        throw JsonMappingException.from(jsonParser, "Non-numeric token (" + nextValue + ") for property 'lineNumber'");
                    }
                    i = jsonParser.getIntValue();
                } else if ("methodName".equals(currentName)) {
                    str2 = jsonParser.getText();
                } else if (!"nativeMethod".equals(currentName)) {
                    handleUnknownProperty(jsonParser, deserializationContext, this._valueClass, currentName);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? null : javaType.getRawClass();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(Class cls) {
        this._valueClass = cls;
    }

    protected static final double parseDouble(String str) {
        if (NumberInput.NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    protected final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (currentToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return jsonParser.getNumberType() == JsonParser.NumberType.INT ? jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE : Boolean.valueOf(_parseBooleanFromNumber(jsonParser, deserializationContext));
        } else if (currentToken == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue();
        } else {
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(trim)) {
                    return Boolean.FALSE;
                }
                if (trim.length() == 0) {
                    return (Boolean) getEmptyValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
    }

    protected final boolean _parseBooleanFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getNumberType() == JsonParser.NumberType.LONG) {
            return (jsonParser.getLongValue() == 0 ? Boolean.FALSE : Boolean.TRUE).booleanValue();
        }
        String text = jsonParser.getText();
        return ("0.0".equals(text) || "0".equals(text)) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (currentToken != JsonToken.VALUE_FALSE && currentToken != JsonToken.VALUE_NULL) {
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return jsonParser.getNumberType() == JsonParser.NumberType.INT ? jsonParser.getIntValue() != 0 : _parseBooleanFromNumber(jsonParser, deserializationContext);
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if ("true".equals(trim)) {
                    return true;
                }
                if ("false".equals(trim) || trim.length() == 0) {
                    return Boolean.FALSE.booleanValue();
                }
                throw deserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
            } else {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
        }
        return false;
    }

    protected Byte _parseByte(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Byte valueOf;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Byte.valueOf(jsonParser.getByteValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Byte) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        try {
            if (trim.length() == 0) {
                valueOf = (Byte) getEmptyValue();
            } else {
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt < -128 || parseInt > 127) {
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value");
                }
                valueOf = Byte.valueOf((byte) parseInt);
            }
            return valueOf;
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Byte value");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public java.util.Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            return new java.util.Date(jsonParser.getLongValue());
        }
        if (currentToken == JsonToken.VALUE_NULL) {
            return (java.util.Date) getNullValue();
        }
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                String trim = jsonParser.getText().trim();
                return trim.length() == 0 ? (java.util.Date) getEmptyValue() : deserializationContext.parseDate(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid representation (error: " + e.getMessage() + ")");
            }
        }
        throw deserializationContext.mappingException(this._valueClass, currentToken);
    }

    protected final Double _parseDouble(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(jsonParser.getDoubleValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Double) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() == 0) {
            return (Double) getEmptyValue();
        }
        switch (trim.charAt(0)) {
            case '-':
                if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Double.valueOf(Double.NEGATIVE_INFINITY);
                }
                break;
            case 'I':
                if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Double.valueOf(Double.POSITIVE_INFINITY);
                }
                break;
            case 'N':
                if ("NaN".equals(trim)) {
                    return Double.valueOf(Double.NaN);
                }
                break;
        }
        try {
            return Double.valueOf(parseDouble(trim));
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getDoubleValue();
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            return 0.0d;
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() != 0) {
            switch (trim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Double.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Double.NaN;
                    }
                    break;
            }
            try {
                return parseDouble(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid double value");
            }
        }
        return 0.0d;
    }

    protected final Float _parseFloat(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(jsonParser.getFloatValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Float) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() == 0) {
            return (Float) getEmptyValue();
        }
        switch (trim.charAt(0)) {
            case '-':
                if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Float.valueOf(Float.NEGATIVE_INFINITY);
                }
                break;
            case 'I':
                if ("Infinity".equals(trim) || "INF".equals(trim)) {
                    return Float.valueOf(Float.POSITIVE_INFINITY);
                }
                break;
            case 'N':
                if ("NaN".equals(trim)) {
                    return Float.valueOf(Float.NaN);
                }
                break;
        }
        try {
            return Float.valueOf(Float.parseFloat(trim));
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getFloatValue();
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            return 0.0f;
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() != 0) {
            switch (trim.charAt(0)) {
                case '-':
                    if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        return Float.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if ("NaN".equals(trim)) {
                        return Float.NaN;
                    }
                    break;
            }
            try {
                return Float.parseFloat(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid float value");
            }
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getIntValue();
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            return 0;
        }
        String trim = jsonParser.getText().trim();
        try {
            int length = trim.length();
            if (length <= 9) {
                if (length != 0) {
                    return NumberInput.parseInt(trim);
                }
                return 0;
            }
            long parseLong = Long.parseLong(trim);
            if (parseLong < -2147483648L || parseLong > 2147483647L) {
                throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + trim + ") out of range of int (-2147483648 - 2147483647)");
            }
            return (int) parseLong;
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid int value");
        }
    }

    protected final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(jsonParser.getIntValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Integer) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        try {
            int length = trim.length();
            if (length <= 9) {
                return length == 0 ? (Integer) getEmptyValue() : Integer.valueOf(NumberInput.parseInt(trim));
            }
            long parseLong = Long.parseLong(trim);
            if (parseLong < -2147483648L || parseLong > 2147483647L) {
                throw deserializationContext.weirdStringException(this._valueClass, "Overflow: numeric value (" + trim + ") out of range of Integer (-2147483648 - 2147483647)");
            }
            return Integer.valueOf((int) parseLong);
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
        }
    }

    protected final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Long) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() == 0) {
            return (Long) getEmptyValue();
        }
        try {
            return Long.valueOf(NumberInput.parseLong(trim));
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return jsonParser.getLongValue();
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext.mappingException(this._valueClass, currentToken);
            }
            return 0L;
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() != 0) {
            try {
                return NumberInput.parseLong(trim);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.weirdStringException(this._valueClass, "not a valid long value");
            }
        }
        return 0L;
    }

    protected Short _parseShort(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Short valueOf;
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return Short.valueOf(jsonParser.getShortValue());
        }
        if (currentToken != JsonToken.VALUE_STRING) {
            if (currentToken == JsonToken.VALUE_NULL) {
                return (Short) getNullValue();
            }
            throw deserializationContext.mappingException(this._valueClass, currentToken);
        }
        String trim = jsonParser.getText().trim();
        try {
            if (trim.length() == 0) {
                valueOf = (Short) getEmptyValue();
            } else {
                int parseInt = NumberInput.parseInt(trim);
                if (parseInt < -32768 || parseInt > 32767) {
                    throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
                }
                valueOf = Short.valueOf((short) parseInt);
            }
            return valueOf;
        } catch (IllegalArgumentException e) {
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid Short value");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
        if (_parseIntPrimitive < -32768 || _parseIntPrimitive > 32767) {
            throw deserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
        return (short) _parseIntPrimitive;
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer findDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        return deserializerProvider.findValueDeserializer(deserializationConfig, javaType, beanProperty);
    }

    public Class getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        if (obj == null) {
            obj = getValueClass();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        reportUnknownProperty(deserializationContext, obj, str);
        jsonParser.skipChildren();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonDeserializer jsonDeserializer) {
        return (jsonDeserializer == null || jsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    protected void reportUnknownProperty(DeserializationContext deserializationContext, Object obj, String str) {
        if (deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw deserializationContext.unknownFieldException(obj, str);
        }
    }
}
