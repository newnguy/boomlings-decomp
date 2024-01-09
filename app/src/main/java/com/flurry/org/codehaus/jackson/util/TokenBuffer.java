package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonGenerationException;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase;
import com.flurry.org.codehaus.jackson.impl.JsonReadContext;
import com.flurry.org.codehaus.jackson.impl.JsonWriteContext;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class TokenBuffer extends JsonGenerator {
    protected static final int DEFAULT_PARSER_FEATURES = JsonParser.Feature.collectDefaults();
    protected int _appendOffset;
    protected boolean _closed;
    protected Segment _first;
    protected Segment _last;
    protected ObjectCodec _objectCodec;
    protected int _generatorFeatures = DEFAULT_PARSER_FEATURES;
    protected JsonWriteContext _writeContext = JsonWriteContext.createRootContext();

    /* loaded from: classes.dex */
    public final class Parser extends JsonParserMinimalBase {
        protected transient ByteArrayBuilder _byteBuilder;
        protected boolean _closed;
        protected ObjectCodec _codec;
        protected JsonLocation _location;
        protected JsonReadContext _parsingContext;
        protected Segment _segment;
        protected int _segmentPtr;

        public Parser(Segment segment, ObjectCodec objectCodec) {
            super(0);
            this._location = null;
            this._segment = segment;
            this._segmentPtr = -1;
            this._codec = objectCodec;
            this._parsingContext = JsonReadContext.createRootContext(-1, -1);
        }

        protected final void _checkIsNumber() {
            if (this._currToken == null || !this._currToken.isNumeric()) {
                throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
            }
        }

        protected final Object _currentObject() {
            return this._segment.get(this._segmentPtr);
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase
        protected void _handleEOF() {
            _throwInternal();
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this._closed) {
                return;
            }
            this._closed = true;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public BigInteger getBigIntegerValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigInteger) {
                return (BigInteger) numberValue;
            }
            switch (getNumberType()) {
                case BIG_DECIMAL:
                    return ((BigDecimal) numberValue).toBigInteger();
                default:
                    return BigInteger.valueOf(numberValue.longValue());
            }
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public byte[] getBinaryValue(Base64Variant base64Variant) {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof byte[]) {
                    return (byte[]) _currentObject;
                }
            }
            if (this._currToken != JsonToken.VALUE_STRING) {
                throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
            }
            String text = getText();
            if (text == null) {
                return null;
            }
            ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
            if (byteArrayBuilder == null) {
                byteArrayBuilder = new ByteArrayBuilder(100);
                this._byteBuilder = byteArrayBuilder;
            } else {
                this._byteBuilder.reset();
            }
            _decodeBase64(text, byteArrayBuilder, base64Variant);
            return byteArrayBuilder.toByteArray();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public ObjectCodec getCodec() {
            return this._codec;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public JsonLocation getCurrentLocation() {
            return this._location == null ? JsonLocation.NA : this._location;
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public String getCurrentName() {
            return this._parsingContext.getCurrentName();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public BigDecimal getDecimalValue() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof BigDecimal) {
                return (BigDecimal) numberValue;
            }
            switch (getNumberType()) {
                case INT:
                case LONG:
                    return BigDecimal.valueOf(numberValue.longValue());
                case BIG_INTEGER:
                    return new BigDecimal((BigInteger) numberValue);
                case BIG_DECIMAL:
                case FLOAT:
                default:
                    return BigDecimal.valueOf(numberValue.doubleValue());
            }
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public double getDoubleValue() {
            return getNumberValue().doubleValue();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public Object getEmbeddedObject() {
            if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return _currentObject();
            }
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public float getFloatValue() {
            return getNumberValue().floatValue();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public int getIntValue() {
            return this._currToken == JsonToken.VALUE_NUMBER_INT ? ((Number) _currentObject()).intValue() : getNumberValue().intValue();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public long getLongValue() {
            return getNumberValue().longValue();
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public JsonParser.NumberType getNumberType() {
            Number numberValue = getNumberValue();
            if (numberValue instanceof Integer) {
                return JsonParser.NumberType.INT;
            }
            if (numberValue instanceof Long) {
                return JsonParser.NumberType.LONG;
            }
            if (numberValue instanceof Double) {
                return JsonParser.NumberType.DOUBLE;
            }
            if (numberValue instanceof BigDecimal) {
                return JsonParser.NumberType.BIG_DECIMAL;
            }
            if (numberValue instanceof Float) {
                return JsonParser.NumberType.FLOAT;
            }
            if (numberValue instanceof BigInteger) {
                return JsonParser.NumberType.BIG_INTEGER;
            }
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public final Number getNumberValue() {
            _checkIsNumber();
            return (Number) _currentObject();
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public JsonStreamContext getParsingContext() {
            return this._parsingContext;
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public String getText() {
            if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                if (_currentObject instanceof String) {
                    return (String) _currentObject;
                }
                return _currentObject == null ? null : _currentObject.toString();
            } else if (this._currToken != null) {
                switch (this._currToken) {
                    case VALUE_NUMBER_INT:
                    case VALUE_NUMBER_FLOAT:
                        Object _currentObject2 = _currentObject();
                        if (_currentObject2 != null) {
                            return _currentObject2.toString();
                        }
                        return null;
                    default:
                        return this._currToken.asString();
                }
            } else {
                return null;
            }
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public char[] getTextCharacters() {
            String text = getText();
            if (text == null) {
                return null;
            }
            return text.toCharArray();
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public int getTextLength() {
            String text = getText();
            if (text == null) {
                return 0;
            }
            return text.length();
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public int getTextOffset() {
            return 0;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public JsonLocation getTokenLocation() {
            return getCurrentLocation();
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public boolean hasTextCharacters() {
            return false;
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public boolean isClosed() {
            return this._closed;
        }

        @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
        public JsonToken nextToken() {
            if (this._closed || this._segment == null) {
                return null;
            }
            int i = this._segmentPtr + 1;
            this._segmentPtr = i;
            if (i >= 16) {
                this._segmentPtr = 0;
                this._segment = this._segment.next();
                if (this._segment == null) {
                    return null;
                }
            }
            this._currToken = this._segment.type(this._segmentPtr);
            if (this._currToken == JsonToken.FIELD_NAME) {
                Object _currentObject = _currentObject();
                this._parsingContext.setCurrentName(_currentObject instanceof String ? (String) _currentObject : _currentObject.toString());
            } else if (this._currToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
            } else if (this._currToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
            } else if (this._currToken == JsonToken.END_OBJECT || this._currToken == JsonToken.END_ARRAY) {
                this._parsingContext = this._parsingContext.getParent();
                if (this._parsingContext == null) {
                    this._parsingContext = JsonReadContext.createRootContext(-1, -1);
                }
            }
            return this._currToken;
        }

        public JsonToken peekNextToken() {
            Segment segment;
            if (this._closed) {
                return null;
            }
            Segment segment2 = this._segment;
            int i = this._segmentPtr + 1;
            if (i >= 16) {
                segment = segment2 == null ? null : segment2.next();
                i = 0;
            } else {
                segment = segment2;
            }
            if (segment != null) {
                return segment.type(i);
            }
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.JsonParser
        public void setCodec(ObjectCodec objectCodec) {
            this._codec = objectCodec;
        }

        public void setLocation(JsonLocation jsonLocation) {
            this._location = jsonLocation;
        }
    }

    /* loaded from: classes.dex */
    public final class Segment {
        public static final int TOKENS_PER_SEGMENT = 16;
        private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
        protected Segment _next;
        protected long _tokenTypes;
        protected final Object[] _tokens = new Object[16];

        static {
            JsonToken[] values;
            System.arraycopy(JsonToken.values(), 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, values.length - 1));
        }

        public Segment append(int i, JsonToken jsonToken) {
            if (i < 16) {
                set(i, jsonToken);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken);
            return this._next;
        }

        public Segment append(int i, JsonToken jsonToken, Object obj) {
            if (i < 16) {
                set(i, jsonToken, obj);
                return null;
            }
            this._next = new Segment();
            this._next.set(0, jsonToken, obj);
            return this._next;
        }

        public Object get(int i) {
            return this._tokens[i];
        }

        public Segment next() {
            return this._next;
        }

        public void set(int i, JsonToken jsonToken) {
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
        }

        public void set(int i, JsonToken jsonToken, Object obj) {
            this._tokens[i] = obj;
            long ordinal = jsonToken.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            this._tokenTypes = ordinal | this._tokenTypes;
        }

        public JsonToken type(int i) {
            long j = this._tokenTypes;
            if (i > 0) {
                j >>= i << 2;
            }
            return TOKEN_TYPES_BY_INDEX[((int) j) & 15];
        }
    }

    public TokenBuffer(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        Segment segment = new Segment();
        this._last = segment;
        this._first = segment;
        this._appendOffset = 0;
    }

    protected final void _append(JsonToken jsonToken) {
        Segment append = this._last.append(this._appendOffset, jsonToken);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    protected final void _append(JsonToken jsonToken, Object obj) {
        Segment append = this._last.append(this._appendOffset, jsonToken, obj);
        if (append == null) {
            this._appendOffset++;
            return;
        }
        this._last = append;
        this._appendOffset = 1;
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public JsonParser asParser() {
        return asParser(this._objectCodec);
    }

    public JsonParser asParser(JsonParser jsonParser) {
        Parser parser = new Parser(this._first, jsonParser.getCodec());
        parser.setLocation(jsonParser.getTokenLocation());
        return parser;
    }

    public JsonParser asParser(ObjectCodec objectCodec) {
        return new Parser(this._first, objectCodec);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this._closed = true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void copyCurrentEvent(JsonParser jsonParser) {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                writeStartObject();
                return;
            case END_OBJECT:
                writeEndObject();
                return;
            case START_ARRAY:
                writeStartArray();
                return;
            case END_ARRAY:
                writeEndArray();
                return;
            case FIELD_NAME:
                writeFieldName(jsonParser.getCurrentName());
                return;
            case VALUE_STRING:
                if (jsonParser.hasTextCharacters()) {
                    writeString(jsonParser.getTextCharacters(), jsonParser.getTextOffset(), jsonParser.getTextLength());
                    return;
                } else {
                    writeString(jsonParser.getText());
                    return;
                }
            case VALUE_NUMBER_INT:
                switch (jsonParser.getNumberType()) {
                    case INT:
                        writeNumber(jsonParser.getIntValue());
                        return;
                    case BIG_INTEGER:
                        writeNumber(jsonParser.getBigIntegerValue());
                        return;
                    default:
                        writeNumber(jsonParser.getLongValue());
                        return;
                }
            case VALUE_NUMBER_FLOAT:
                switch (jsonParser.getNumberType()) {
                    case BIG_DECIMAL:
                        writeNumber(jsonParser.getDecimalValue());
                        return;
                    case FLOAT:
                        writeNumber(jsonParser.getFloatValue());
                        return;
                    default:
                        writeNumber(jsonParser.getDoubleValue());
                        return;
                }
            case VALUE_TRUE:
                writeBoolean(true);
                return;
            case VALUE_FALSE:
                writeBoolean(false);
                return;
            case VALUE_NULL:
                writeNull();
                return;
            case VALUE_EMBEDDED_OBJECT:
                writeObject(jsonParser.getEmbeddedObject());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void copyCurrentStructure(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.FIELD_NAME) {
            writeFieldName(jsonParser.getCurrentName());
            currentToken = jsonParser.nextToken();
        }
        switch (currentToken) {
            case START_OBJECT:
                writeStartObject();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    copyCurrentStructure(jsonParser);
                }
                writeEndObject();
                return;
            case END_OBJECT:
            default:
                copyCurrentEvent(jsonParser);
                return;
            case START_ARRAY:
                writeStartArray();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    copyCurrentStructure(jsonParser);
                }
                writeEndArray();
                return;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        this._generatorFeatures &= feature.getMask() ^ (-1);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        this._generatorFeatures |= feature.getMask();
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void flush() {
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0;
    }

    public void serialize(JsonGenerator jsonGenerator) {
        Segment segment = this._first;
        int i = -1;
        while (true) {
            int i2 = i;
            Segment segment2 = segment;
            int i3 = i2 + 1;
            if (i3 >= 16) {
                Segment next = segment2.next();
                if (next == null) {
                    return;
                }
                i = 0;
                segment = next;
            } else {
                segment = segment2;
                i = i3;
            }
            JsonToken type = segment.type(i);
            if (type != null) {
                switch (type) {
                    case START_OBJECT:
                        jsonGenerator.writeStartObject();
                        break;
                    case END_OBJECT:
                        jsonGenerator.writeEndObject();
                        break;
                    case START_ARRAY:
                        jsonGenerator.writeStartArray();
                        break;
                    case END_ARRAY:
                        jsonGenerator.writeEndArray();
                        break;
                    case FIELD_NAME:
                        Object obj = segment.get(i);
                        if (!(obj instanceof SerializableString)) {
                            jsonGenerator.writeFieldName((String) obj);
                            break;
                        } else {
                            jsonGenerator.writeFieldName((SerializableString) obj);
                            break;
                        }
                    case VALUE_STRING:
                        Object obj2 = segment.get(i);
                        if (!(obj2 instanceof SerializableString)) {
                            jsonGenerator.writeString((String) obj2);
                            break;
                        } else {
                            jsonGenerator.writeString((SerializableString) obj2);
                            break;
                        }
                    case VALUE_NUMBER_INT:
                        Number number = (Number) segment.get(i);
                        if (!(number instanceof BigInteger)) {
                            if (!(number instanceof Long)) {
                                jsonGenerator.writeNumber(number.intValue());
                                break;
                            } else {
                                jsonGenerator.writeNumber(number.longValue());
                                break;
                            }
                        } else {
                            jsonGenerator.writeNumber((BigInteger) number);
                            break;
                        }
                    case VALUE_NUMBER_FLOAT:
                        Object obj3 = segment.get(i);
                        if (obj3 instanceof BigDecimal) {
                            jsonGenerator.writeNumber((BigDecimal) obj3);
                            break;
                        } else if (obj3 instanceof Float) {
                            jsonGenerator.writeNumber(((Float) obj3).floatValue());
                            break;
                        } else if (obj3 instanceof Double) {
                            jsonGenerator.writeNumber(((Double) obj3).doubleValue());
                            break;
                        } else if (obj3 == null) {
                            jsonGenerator.writeNull();
                            break;
                        } else if (!(obj3 instanceof String)) {
                            throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + obj3.getClass().getName() + ", can not serialize");
                        } else {
                            jsonGenerator.writeNumber((String) obj3);
                            break;
                        }
                    case VALUE_TRUE:
                        jsonGenerator.writeBoolean(true);
                        break;
                    case VALUE_FALSE:
                        jsonGenerator.writeBoolean(false);
                        break;
                    case VALUE_NULL:
                        jsonGenerator.writeNull();
                        break;
                    case VALUE_EMBEDDED_OBJECT:
                        jsonGenerator.writeObject(segment.get(i));
                        break;
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
            } else {
                return;
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TokenBuffer: ");
        JsonParser asParser = asParser();
        int i = 0;
        while (true) {
            try {
                JsonToken nextToken = asParser.nextToken();
                if (nextToken == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(nextToken.toString());
                }
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (i >= 100) {
            sb.append(" ... (truncated ").append(i - 100).append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        writeObject(bArr2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) {
        _append(z ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeEndArray() {
        _append(JsonToken.END_ARRAY);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeEndObject() {
        _append(JsonToken.END_OBJECT);
        JsonWriteContext parent = this._writeContext.getParent();
        if (parent != null) {
            this._writeContext = parent;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        _append(JsonToken.FIELD_NAME, serializableString);
        this._writeContext.writeFieldName(serializableString.getValue());
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializedString serializedString) {
        _append(JsonToken.FIELD_NAME, serializedString);
        this._writeContext.writeFieldName(serializedString.getValue());
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(String str) {
        _append(JsonToken.FIELD_NAME, str);
        this._writeContext.writeFieldName(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNull() {
        _append(JsonToken.VALUE_NULL);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) {
        _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) {
        _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) {
        _append(JsonToken.VALUE_NUMBER_FLOAT, str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        if (bigInteger == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeStartArray() {
        _append(JsonToken.START_ARRAY);
        this._writeContext = this._writeContext.createChildArrayContext();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeStartObject() {
        _append(JsonToken.START_OBJECT);
        this._writeContext = this._writeContext.createChildObjectContext();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(SerializableString serializableString) {
        if (serializableString == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, serializableString);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(String str) {
        if (str == null) {
            writeNull();
        } else {
            _append(JsonToken.VALUE_STRING, str);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        writeString(new String(cArr, i, i2));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, jsonNode);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) {
        _reportUnsupportedOperation();
    }
}
