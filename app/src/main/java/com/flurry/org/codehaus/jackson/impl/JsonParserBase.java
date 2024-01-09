package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.TextBuffer;
import com.flurry.org.codehaus.jackson.util.VersionUtil;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public abstract class JsonParserBase extends JsonParserMinimalBase {
    protected static final char CHAR_NULL = 0;
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2.147483647E9d;
    static final long MAX_INT_L = 2147483647L;
    static final double MAX_LONG_D = 9.223372036854776E18d;
    static final double MIN_INT_D = -2.147483648E9d;
    static final long MIN_INT_L = -2147483648L;
    static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected byte[] _binaryValue;
    protected boolean _closed;
    protected int _expLength;
    protected int _fractLength;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected JsonToken _nextToken;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    static final BigDecimal BD_MIN_LONG = new BigDecimal(Long.MIN_VALUE);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(Long.MAX_VALUE);
    static final BigDecimal BD_MIN_INT = new BigDecimal(Long.MIN_VALUE);
    static final BigDecimal BD_MAX_INT = new BigDecimal(Long.MAX_VALUE);
    protected int _inputPtr = 0;
    protected int _inputEnd = 0;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected long _tokenInputTotal = 0;
    protected int _tokenInputRow = 1;
    protected int _tokenInputCol = 0;
    protected char[] _nameCopyBuffer = null;
    protected boolean _nameCopied = false;
    protected ByteArrayBuilder _byteArrayBuilder = null;
    protected int _numTypesValid = 0;

    public JsonParserBase(IOContext iOContext, int i) {
        this._features = i;
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext();
    }

    private final void _parseSlowFloatValue(int i) {
        try {
            if (i == 16) {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            } else {
                this._numberDouble = this._textBuffer.contentsAsDouble();
                this._numTypesValid = NR_DOUBLE;
            }
        } catch (NumberFormatException e) {
            _wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", e);
        }
    }

    private final void _parseSlowIntValue(int i, char[] cArr, int i2, int i3) {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
            } else {
                this._numberBigInt = new BigInteger(contentsAsString);
                this._numTypesValid = 4;
            }
        } catch (NumberFormatException e) {
            _wrapError("Malformed numeric value '" + contentsAsString + "'", e);
        }
    }

    protected abstract void _closeInput();

    public final int _decodeBase64Escape(Base64Variant base64Variant, char c, int i) {
        if (c != '\\') {
            throw reportInvalidBase64Char(base64Variant, c, i);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped > ' ' || i != 0) {
            int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
            if (decodeBase64Char < 0) {
                throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i);
            }
            return decodeBase64Char;
        }
        return -1;
    }

    public final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) {
        if (i != 92) {
            throw reportInvalidBase64Char(base64Variant, i, i2);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped > ' ' || i2 != 0) {
            int decodeBase64Char = base64Variant.decodeBase64Char((int) _decodeEscaped);
            if (decodeBase64Char < 0) {
                throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i2);
            }
            return decodeBase64Char;
        }
        return -1;
    }

    protected char _decodeEscaped() {
        throw new UnsupportedOperationException();
    }

    protected abstract void _finishString();

    public ByteArrayBuilder _getByteArrayBuilder() {
        if (this._byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase
    public void _handleEOF() {
        if (this._parsingContext.inRoot()) {
            return;
        }
        _reportInvalidEOF(": expected close marker for " + this._parsingContext.getTypeDesc() + " (from " + this._parsingContext.getStartLocation(this._ioContext.getSourceReference()) + ")");
    }

    protected void _parseNumericValue(int i) {
        if (this._currToken != JsonToken.VALUE_NUMBER_INT) {
            if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
                _parseSlowFloatValue(i);
                return;
            } else {
                _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
                return;
            }
        }
        char[] textBuffer = this._textBuffer.getTextBuffer();
        int textOffset = this._textBuffer.getTextOffset();
        int i2 = this._intLength;
        if (this._numberNegative) {
            textOffset++;
        }
        if (i2 <= 9) {
            int parseInt = NumberInput.parseInt(textBuffer, textOffset, i2);
            if (this._numberNegative) {
                parseInt = -parseInt;
            }
            this._numberInt = parseInt;
            this._numTypesValid = 1;
        } else if (i2 > 18) {
            _parseSlowIntValue(i, textBuffer, textOffset, i2);
        } else {
            long parseLong = NumberInput.parseLong(textBuffer, textOffset, i2);
            if (this._numberNegative) {
                parseLong = -parseLong;
            }
            if (i2 == 10) {
                if (this._numberNegative) {
                    if (parseLong >= MIN_INT_L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = 1;
                        return;
                    }
                } else if (parseLong <= MAX_INT_L) {
                    this._numberInt = (int) parseLong;
                    this._numTypesValid = 1;
                    return;
                }
            }
            this._numberLong = parseLong;
            this._numTypesValid = 2;
        }
    }

    public void _releaseBuffers() {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    public void _reportMismatchedEndMarker(int i, char c) {
        _reportError("Unexpected close marker '" + ((char) i) + "': expected '" + c + "' (for " + this._parsingContext.getTypeDesc() + " starting at " + ("" + this._parsingContext.getStartLocation(this._ioContext.getSourceReference())) + ")");
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this._closed) {
            return;
        }
        this._closed = true;
        try {
            _closeInput();
        } finally {
            _releaseBuffers();
        }
    }

    protected void convertNumberToBigDecimal() {
        if ((this._numTypesValid & NR_DOUBLE) != 0) {
            this._numberBigDecimal = new BigDecimal(getText());
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    protected void convertNumberToBigInteger() {
        if ((this._numTypesValid & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberInt);
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    protected void convertNumberToDouble() {
        if ((this._numTypesValid & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberDouble = this._numberLong;
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberDouble = this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= NR_DOUBLE;
    }

    protected void convertNumberToInt() {
        if ((this._numTypesValid & 2) != 0) {
            int i = (int) this._numberLong;
            if (i != this._numberLong) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = i;
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            if (this._numberDouble < MIN_INT_D || this._numberDouble > MAX_INT_D) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    protected void convertNumberToLong() {
        if ((this._numTypesValid & 1) != 0) {
            this._numberLong = this._numberInt;
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & NR_DOUBLE) != 0) {
            if (this._numberDouble < MIN_LONG_D || this._numberDouble > MAX_LONG_D) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() {
        if ((this._numTypesValid & 4) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), (this._currInputProcessed + this._inputPtr) - 1, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public String getCurrentName() {
        return (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) ? this._parsingContext.getParent().getCurrentName() : this._parsingContext.getCurrentName();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() {
        if ((this._numTypesValid & 16) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public double getDoubleValue() {
        if ((this._numTypesValid & NR_DOUBLE) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(NR_DOUBLE);
            }
            if ((this._numTypesValid & NR_DOUBLE) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public float getFloatValue() {
        return (float) getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getIntValue() {
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public long getLongValue() {
        if ((this._numTypesValid & 2) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        return this._currToken == JsonToken.VALUE_NUMBER_INT ? (this._numTypesValid & 1) != 0 ? JsonParser.NumberType.INT : (this._numTypesValid & 2) != 0 ? JsonParser.NumberType.LONG : JsonParser.NumberType.BIG_INTEGER : (this._numTypesValid & 16) != 0 ? JsonParser.NumberType.BIG_DECIMAL : JsonParser.NumberType.DOUBLE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Number getNumberValue() {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            return (this._numTypesValid & 1) != 0 ? Integer.valueOf(this._numberInt) : (this._numTypesValid & 2) != 0 ? Long.valueOf(this._numberLong) : (this._numTypesValid & 4) != 0 ? this._numberBigInt : this._numberBigDecimal;
        } else if ((this._numTypesValid & 16) != 0) {
            return this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & NR_DOUBLE) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }

    public final long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public final int getTokenColumnNr() {
        int i = this._tokenInputCol;
        return i < 0 ? i : i + 1;
    }

    public final int getTokenLineNr() {
        return this._tokenInputRow;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    protected abstract boolean loadMore();

    public final void loadMoreGuaranteed() {
        if (loadMore()) {
            return;
        }
        _reportInvalidEOF();
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2) {
        return reportInvalidBase64Char(base64Variant, i, i2, null);
    }

    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2, String str) {
        String str2 = i <= 32 ? "Illegal white space character (code 0x" + Integer.toHexString(i) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units" : base64Variant.usesPaddingChar(i) ? "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character" : (!Character.isDefined(i) || Character.isISOControl(i)) ? "Illegal character (code 0x" + Integer.toHexString(i) + ") in base64 content" : "Illegal character '" + ((char) i) + "' (code 0x" + Integer.toHexString(i) + ") in base64 content";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        return new IllegalArgumentException(str2);
    }

    public void reportInvalidNumber(String str) {
        _reportError("Invalid numeric value: " + str);
    }

    protected void reportOverflowInt() {
        _reportError("Numeric value (" + getText() + ") out of range of int (-2147483648 - 2147483647)");
    }

    protected void reportOverflowLong() {
        _reportError("Numeric value (" + getText() + ") out of range of long (-9223372036854775808 - 9223372036854775807)");
    }

    public void reportUnexpectedNumberChar(int i, String str) {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ") in numeric value";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    public final JsonToken reset(boolean z, int i, int i2, int i3) {
        return (i2 >= 1 || i3 >= 1) ? resetFloat(z, i, i2, i3) : resetInt(z, i);
    }

    public final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = NR_DOUBLE;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser, com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }
}
