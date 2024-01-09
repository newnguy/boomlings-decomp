package com.flurry.org.codehaus.jackson.impl;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.sym.Name;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import com.google.ads.AdSize;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class Utf8StreamParser extends JsonParserBase {
    static final byte BYTE_LF = 10;
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();

    public Utf8StreamParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._tokenIncomplete = false;
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
        if (JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i)) {
            return;
        }
        _throwInternal();
    }

    private final int _decodeUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        return (b & 63) | ((i & 31) << 6);
    }

    private final int _decodeUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_3fast(int i) {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i4 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        return (i4 << 6) | (b2 & 63);
    }

    private final int _decodeUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        int i3 = (b & 63) | ((i & 7) << 6);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        int i5 = (i3 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        this._inputPtr = i6 + 1;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & Constants.UNKNOWN, this._inputPtr);
        }
        return ((i5 << 6) | (b3 & 63)) - 65536;
    }

    private final void _finishString2(char[] cArr, int i) {
        int i2;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i3 = this._inputPtr;
            if (i3 >= this._inputEnd) {
                loadMoreGuaranteed();
                i3 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i3);
            while (true) {
                if (i3 < min) {
                    int i4 = i3 + 1;
                    int i5 = bArr[i3] & Constants.UNKNOWN;
                    if (iArr[i5] != 0) {
                        this._inputPtr = i4;
                        if (i5 == 34) {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                        switch (iArr[i5]) {
                            case 1:
                                i5 = _decodeEscaped();
                                break;
                            case 2:
                                i5 = _decodeUtf8_2(i5);
                                break;
                            case 3:
                                if (this._inputEnd - this._inputPtr < 2) {
                                    i5 = _decodeUtf8_3(i5);
                                    break;
                                } else {
                                    i5 = _decodeUtf8_3fast(i5);
                                    break;
                                }
                            case 4:
                                int _decodeUtf8_4 = _decodeUtf8_4(i5);
                                int i6 = i + 1;
                                cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                                if (i6 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    i6 = 0;
                                }
                                i = i6;
                                i5 = (_decodeUtf8_4 & 1023) | 56320;
                                break;
                            default:
                                if (i5 >= 32) {
                                    _reportInvalidChar(i5);
                                    break;
                                } else {
                                    _throwUnquotedSpace(i5, "string value");
                                    break;
                                }
                        }
                        if (i >= cArr.length) {
                            cArr = this._textBuffer.finishCurrentSegment();
                            i2 = 0;
                        } else {
                            i2 = i;
                        }
                        i = i2 + 1;
                        cArr[i2] = (char) i5;
                    } else {
                        cArr[i] = (char) i5;
                        i3 = i4;
                        i++;
                    }
                } else {
                    this._inputPtr = i3;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void _isNextTokenNameNo(int i) {
        JsonToken parseNumberText;
        this._parsingContext.setCurrentName(_parseFieldName(i).getName());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return;
        }
        switch (_skipWS2) {
            case 45:
            case 48:
            case 49:
            case AdSize.PORTRAIT_AD_HEIGHT /* 50 */:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                parseNumberText = parseNumberText(_skipWS2);
                break;
            case 91:
                parseNumberText = JsonToken.START_ARRAY;
                break;
            case 93:
            case 125:
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
                break;
            case 102:
                _matchToken("false", 1);
                parseNumberText = JsonToken.VALUE_FALSE;
                break;
            case 110:
                _matchToken(DataFileConstants.NULL_CODEC, 1);
                parseNumberText = JsonToken.VALUE_NULL;
                break;
            case 116:
                _matchToken("true", 1);
                parseNumberText = JsonToken.VALUE_TRUE;
                break;
            case 123:
                parseNumberText = JsonToken.START_OBJECT;
                break;
            default:
                parseNumberText = _handleUnexpectedValue(_skipWS2);
                break;
        }
        this._nextToken = parseNumberText;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void _isNextTokenNameYes() {
        int _skipColon;
        if (this._inputPtr >= this._inputEnd || this._inputBuffer[this._inputPtr] != 58) {
            _skipColon = _skipColon();
        } else {
            this._inputPtr++;
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i];
            if (b == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            } else if (b == 123) {
                this._nextToken = JsonToken.START_OBJECT;
                return;
            } else if (b == 91) {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            } else {
                _skipColon = b & Constants.UNKNOWN;
                if (_skipColon <= 32 || _skipColon == 47) {
                    this._inputPtr--;
                    _skipColon = _skipWS();
                }
            }
        }
        switch (_skipColon) {
            case 34:
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            case 45:
            case 48:
            case 49:
            case AdSize.PORTRAIT_AD_HEIGHT /* 50 */:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                this._nextToken = parseNumberText(_skipColon);
                return;
            case 91:
                this._nextToken = JsonToken.START_ARRAY;
                return;
            case 93:
            case 125:
                _reportUnexpectedChar(_skipColon, "expected a value");
                break;
            case 102:
                _matchToken("false", 1);
                this._nextToken = JsonToken.VALUE_FALSE;
                return;
            case 110:
                _matchToken(DataFileConstants.NULL_CODEC, 1);
                this._nextToken = JsonToken.VALUE_NULL;
                return;
            case 116:
                break;
            case 123:
                this._nextToken = JsonToken.START_OBJECT;
                return;
            default:
                this._nextToken = _handleUnexpectedValue(_skipColon);
                return;
        }
        _matchToken("true", 1);
        this._nextToken = JsonToken.VALUE_TRUE;
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final JsonToken _nextTokenNotInObject(int i) {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        switch (i) {
            case 45:
            case 48:
            case 49:
            case AdSize.PORTRAIT_AD_HEIGHT /* 50 */:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                JsonToken parseNumberText = parseNumberText(i);
                this._currToken = parseNumberText;
                return parseNumberText;
            case 91:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            case 93:
            case 125:
                _reportUnexpectedChar(i, "expected a value");
                break;
            case 102:
                _matchToken("false", 1);
                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken3;
                return jsonToken3;
            case 110:
                _matchToken(DataFileConstants.NULL_CODEC, 1);
                JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken4;
                return jsonToken4;
            case 116:
                break;
            case 123:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken5 = JsonToken.START_OBJECT;
                this._currToken = jsonToken5;
                return jsonToken5;
            default:
                JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                this._currToken = _handleUnexpectedValue;
                return _handleUnexpectedValue;
        }
        _matchToken("true", 1);
        JsonToken jsonToken6 = JsonToken.VALUE_TRUE;
        this._currToken = jsonToken6;
        return jsonToken6;
    }

    private final JsonToken _parseFloatText(char[] cArr, int i, int i2, boolean z, int i3) {
        int i4;
        char[] cArr2;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z2;
        int i9;
        int i10;
        int i11 = 0;
        boolean z3 = false;
        if (i2 == 46) {
            int i12 = i + 1;
            cArr[i] = (char) i2;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z3 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i13 = this._inputPtr;
                this._inputPtr = i13 + 1;
                i2 = bArr[i13] & Constants.UNKNOWN;
                if (i2 < 48 || i2 > 57) {
                    break;
                }
                i11++;
                if (i12 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i12 = 0;
                }
                int i14 = i12;
                i12 = i14 + 1;
                cArr[i14] = (char) i2;
            }
            if (i11 == 0) {
                reportUnexpectedNumberChar(i2, "Decimal point not followed by a digit");
            }
            i4 = i11;
            i5 = i12;
            cArr2 = cArr;
        } else {
            i4 = 0;
            cArr2 = cArr;
            i5 = i;
        }
        if (i2 == 101 || i2 == 69) {
            if (i5 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i5 = 0;
            }
            int i15 = i5 + 1;
            cArr2[i5] = (char) i2;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i16 = this._inputPtr;
            this._inputPtr = i16 + 1;
            int i17 = bArr2[i16] & Constants.UNKNOWN;
            if (i17 == 45 || i17 == 43) {
                if (i15 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i6 = 0;
                } else {
                    i6 = i15;
                }
                int i18 = i6 + 1;
                cArr2[i6] = (char) i17;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                i17 = bArr3[i19] & Constants.UNKNOWN;
                i7 = i18;
                i8 = 0;
            } else {
                i7 = i15;
                i8 = 0;
            }
            while (i17 <= 57 && i17 >= 48) {
                i8++;
                if (i7 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i7 = 0;
                }
                int i20 = i7 + 1;
                cArr2[i7] = (char) i17;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    i10 = i8;
                    z2 = true;
                    i9 = i20;
                    break;
                }
                byte[] bArr4 = this._inputBuffer;
                int i21 = this._inputPtr;
                this._inputPtr = i21 + 1;
                i17 = bArr4[i21] & Constants.UNKNOWN;
                i7 = i20;
            }
            z2 = z3;
            int i22 = i8;
            i9 = i7;
            i10 = i22;
            if (i10 == 0) {
                reportUnexpectedNumberChar(i17, "Exponent indicator not followed by a digit");
            }
        } else {
            z2 = z3;
            i9 = i5;
            i10 = 0;
        }
        if (!z2) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i9);
        return resetFloat(z, i3, i4, i10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        if (r3 == 46) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r3 == 101) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
        if (r3 != 69) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
        r6._inputPtr--;
        r6._textBuffer.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
        return _parseFloatText(r1, r2, r3, r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
        return resetInt(r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.flurry.org.codehaus.jackson.JsonToken _parserNumber2(char[] r7, int r8, boolean r9, int r10) {
        /*
            r6 = this;
            r5 = r10
            r2 = r8
            r1 = r7
        L3:
            int r0 = r6._inputPtr
            int r3 = r6._inputEnd
            if (r0 < r3) goto L19
            boolean r0 = r6.loadMore()
            if (r0 != 0) goto L19
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            r0.setCurrentLength(r2)
            com.flurry.org.codehaus.jackson.JsonToken r0 = r6.resetInt(r9, r5)
        L18:
            return r0
        L19:
            byte[] r0 = r6._inputBuffer
            int r3 = r6._inputPtr
            int r4 = r3 + 1
            r6._inputPtr = r4
            r0 = r0[r3]
            r3 = r0 & 255(0xff, float:3.57E-43)
            r0 = 57
            if (r3 > r0) goto L2d
            r0 = 48
            if (r3 >= r0) goto L40
        L2d:
            r0 = 46
            if (r3 == r0) goto L39
            r0 = 101(0x65, float:1.42E-43)
            if (r3 == r0) goto L39
            r0 = 69
            if (r3 != r0) goto L53
        L39:
            r0 = r6
            r4 = r9
            com.flurry.org.codehaus.jackson.JsonToken r0 = r0._parseFloatText(r1, r2, r3, r4, r5)
            goto L18
        L40:
            int r0 = r1.length
            if (r2 < r0) goto L63
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            char[] r1 = r0.finishCurrentSegment()
            r2 = 0
            r0 = r2
        L4b:
            int r2 = r0 + 1
            char r3 = (char) r3
            r1[r0] = r3
            int r5 = r5 + 1
            goto L3
        L53:
            int r0 = r6._inputPtr
            int r0 = r0 + (-1)
            r6._inputPtr = r0
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            r0.setCurrentLength(r2)
            com.flurry.org.codehaus.jackson.JsonToken r0 = r6.resetInt(r9, r5)
            goto L18
        L63:
            r0 = r2
            goto L4b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.Utf8StreamParser._parserNumber2(char[], int, boolean, int):com.flurry.org.codehaus.jackson.JsonToken");
    }

    private final void _skipCComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a comment");
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            int i3 = inputCodeComment[i2];
            if (i3 != 0) {
                switch (i3) {
                    case 2:
                        _skipUtf8_2(i2);
                        continue;
                    case 3:
                        _skipUtf8_3(i2);
                        continue;
                    case 4:
                        _skipUtf8_4(i2);
                        continue;
                    case 10:
                        _skipLF();
                        continue;
                    case 13:
                        _skipCR();
                        continue;
                    case 42:
                        if (this._inputBuffer[this._inputPtr] == 47) {
                            this._inputPtr++;
                            return;
                        }
                        continue;
                    default:
                        _reportInvalidChar(i2);
                        continue;
                }
            }
        }
    }

    private final int _skipColon() {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if (b != 58) {
            int i3 = b & Constants.UNKNOWN;
            while (true) {
                switch (i3) {
                    case 9:
                    case 13:
                    case AdSize.LANDSCAPE_AD_HEIGHT /* 32 */:
                        _skipCR();
                    case 10:
                        _skipLF();
                    case 47:
                        _skipComment();
                    default:
                        if (i3 < 32) {
                            _throwInvalidSpace(i3);
                        }
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr2 = this._inputBuffer;
                        int i4 = this._inputPtr;
                        this._inputPtr = i4 + 1;
                        int i5 = bArr2[i4] & Constants.UNKNOWN;
                        if (i5 != 58) {
                            _reportUnexpectedChar(i5, "was expecting a colon to separate field name and value");
                            break;
                        }
                        break;
                }
            }
        } else if (this._inputPtr < this._inputEnd && (i = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN) > 32 && i != 47) {
            this._inputPtr++;
            return i;
        }
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
            byte[] bArr3 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            i = bArr3[i6] & Constants.UNKNOWN;
            if (i > 32) {
                if (i == 47) {
                    _skipComment();
                }
            } else if (i != 32) {
                if (i == 10) {
                    _skipLF();
                } else if (i == 13) {
                    _skipCR();
                } else if (i != 9) {
                    _throwInvalidSpace(i);
                }
            }
        }
        return i;
    }

    private final void _skipComment() {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Constants.UNKNOWN;
        if (i2 == 47) {
            _skipCppComment();
        } else if (i2 == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(i2, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCppComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            int i3 = inputCodeComment[i2];
            if (i3 != 0) {
                switch (i3) {
                    case 2:
                        _skipUtf8_2(i2);
                        continue;
                    case 3:
                        _skipUtf8_3(i2);
                        continue;
                    case 4:
                        _skipUtf8_4(i2);
                        continue;
                    case 10:
                        _skipLF();
                        return;
                    case 13:
                        _skipCR();
                        return;
                    case 42:
                        break;
                    default:
                        _reportInvalidChar(i2);
                        continue;
                }
            }
        }
    }

    private final void _skipUtf8_2(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
    }

    private final void _skipUtf8_3(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int i) {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & Constants.UNKNOWN, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & Constants.UNKNOWN, this._inputPtr);
        }
    }

    private final int _skipWS() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            if (i2 > 32) {
                if (i2 != 47) {
                    return i2;
                }
                _skipComment();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    _skipLF();
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private final int _skipWSOrEnd() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _handleEOF();
                return -1;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            if (i2 > 32) {
                if (i2 != 47) {
                    return i2;
                }
                _skipComment();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    _skipLF();
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private final int _verifyNoLeadingZeroes() {
        if (this._inputPtr < this._inputEnd || loadMore()) {
            int i = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
            if (i < 48 || i > 57) {
                return 48;
            }
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (i == 48) {
                do {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        return i;
                    }
                    i = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
                    if (i < 48 || i > 57) {
                        return 48;
                    }
                    this._inputPtr++;
                } while (i == 48);
                return i;
            }
            return i;
        }
        return 48;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.flurry.org.codehaus.jackson.sym.Name addName(int[] r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.Utf8StreamParser.addName(int[], int, int):com.flurry.org.codehaus.jackson.sym.Name");
    }

    private final Name findName(int i, int i2) {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        return addName(this._quadBuffer, 1, i2);
    }

    private final Name findName(int i, int i2, int i3) {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        this._quadBuffer[1] = i2;
        return addName(this._quadBuffer, 2, i3);
    }

    private final Name findName(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[length + i];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    private int nextByte() {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & Constants.UNKNOWN;
    }

    private final Name parseFieldName(int i, int i2, int i3) {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private final Name parseFieldName(int i, int i2, int i3, int i4) {
        this._quadBuffer[0] = i;
        return parseEscapedFieldName(this._quadBuffer, 1, i2, i3, i4);
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & Constants.UNKNOWN;
            if (i2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i2);
                if (decodeBase64Char < 0) {
                    if (i2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, i2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & Constants.UNKNOWN;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i4);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, i4, 1);
                }
                int i5 = decodeBase64Char2 | (decodeBase64Char << 6);
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                int i7 = bArr3[i6] & Constants.UNKNOWN;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i7 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i5 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, i7, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i8 = this._inputPtr;
                        this._inputPtr = i8 + 1;
                        int i9 = bArr4[i8] & Constants.UNKNOWN;
                        if (!base64Variant.usesPaddingChar(i9)) {
                            throw reportInvalidBase64Char(base64Variant, i9, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i5 >> 4);
                    }
                }
                int i10 = (i5 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                int i12 = bArr5[i11] & Constants.UNKNOWN;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i12 == 34 && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, i12, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i10 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes(decodeBase64Char4 | (i10 << 6));
            }
        }
    }

    protected int _decodeCharForError(int i) {
        char c;
        if (i < 0) {
            if ((i & 224) == 192) {
                i &= 31;
                c = 1;
            } else if ((i & 240) == 224) {
                i &= 15;
                c = 2;
            } else if ((i & 248) == 240) {
                i &= 7;
                c = 3;
            } else {
                _reportInvalidInitial(i & 255);
                c = 1;
            }
            int nextByte = nextByte();
            if ((nextByte & 192) != 128) {
                _reportInvalidOther(nextByte & 255);
            }
            int i2 = (i << 6) | (nextByte & 63);
            if (c > 1) {
                int nextByte2 = nextByte();
                if ((nextByte2 & 192) != 128) {
                    _reportInvalidOther(nextByte2 & 255);
                }
                int i3 = (i2 << 6) | (nextByte2 & 63);
                if (c > 2) {
                    int nextByte3 = nextByte();
                    if ((nextByte3 & 192) != 128) {
                        _reportInvalidOther(nextByte3 & 255);
                    }
                    return (i3 << 6) | (nextByte3 & 63);
                }
                return i3;
            }
            return i2;
        }
        return i;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected final char _decodeEscaped() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        switch (b) {
            case 34:
            case 47:
            case 92:
                return (char) b;
            case 98:
                return '\b';
            case 102:
                return '\f';
            case 110:
                return '\n';
            case 114:
                return '\r';
            case 116:
                return '\t';
            case 117:
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b2 = bArr2[i4];
                    int charToHex = CharTypes.charToHex(b2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | charToHex;
                }
                return (char) i2;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        int i2 = i;
        int i3 = 0;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = bArr[i2] & Constants.UNKNOWN;
            if (iArr[i4] == 0) {
                emptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
                i2++;
            } else if (i4 == 34) {
                this._inputPtr = i2 + 1;
                this._textBuffer.setCurrentLength(i3);
                return;
            }
        }
        this._inputPtr = i2;
        _finishString2(emptyAndGetCurrentSegment, i3);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    protected JsonToken _handleApostropheValue() {
        int i;
        int i2;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                i3 = 0;
            }
            int i4 = this._inputEnd;
            int length = this._inputPtr + (emptyAndGetCurrentSegment.length - i3);
            if (length >= i4) {
                length = i4;
            }
            while (this._inputPtr < length) {
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = bArr[i5] & Constants.UNKNOWN;
                if (i6 != 39 && iArr[i6] == 0) {
                    emptyAndGetCurrentSegment[i3] = (char) i6;
                    i3++;
                } else if (i6 == 39) {
                    this._textBuffer.setCurrentLength(i3);
                    return JsonToken.VALUE_STRING;
                } else {
                    switch (iArr[i6]) {
                        case 1:
                            if (i6 != 34) {
                                i = _decodeEscaped();
                                break;
                            }
                            i = i6;
                            break;
                        case 2:
                            i = _decodeUtf8_2(i6);
                            break;
                        case 3:
                            if (this._inputEnd - this._inputPtr < 2) {
                                i = _decodeUtf8_3(i6);
                                break;
                            } else {
                                i = _decodeUtf8_3fast(i6);
                                break;
                            }
                        case 4:
                            int _decodeUtf8_4 = _decodeUtf8_4(i6);
                            int i7 = i3 + 1;
                            emptyAndGetCurrentSegment[i3] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i7 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                i3 = 0;
                            } else {
                                i3 = i7;
                            }
                            i = 56320 | (_decodeUtf8_4 & 1023);
                            break;
                        default:
                            if (i6 < 32) {
                                _throwUnquotedSpace(i6, "string value");
                            }
                            _reportInvalidChar(i6);
                            i = i6;
                            break;
                    }
                    if (i3 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                        i2 = 0;
                    } else {
                        i2 = i3;
                    }
                    i3 = i2 + 1;
                    emptyAndGetCurrentSegment[i2] = (char) i;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) {
        if (i == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                String str = z ? "-INF" : "+INF";
                _matchToken(str, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (i == 110) {
                String str2 = z ? "-Infinity" : "+Infinity";
                _matchToken(str2, 3);
                if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN(str2, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token '" + str2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected JsonToken _handleUnexpectedValue(int i) {
        switch (i) {
            case 39:
                if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApostropheValue();
                }
                break;
            case 43:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & Constants.UNKNOWN, false);
            case 78:
                _matchToken("NaN", 1);
                if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                } else {
                    return resetAsNaN("NaN", Double.NaN);
                }
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected final Name _handleUnusualFieldName(int i) {
        int[] iArr;
        int i2;
        int i3;
        int i4;
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = i;
        int i8 = 0;
        int[] iArr2 = this._quadBuffer;
        while (true) {
            if (i5 < 4) {
                int i9 = i5 + 1;
                i3 = i7 | (i6 << 8);
                i4 = i8;
                iArr = iArr2;
                i2 = i9;
            } else {
                if (i8 >= iArr2.length) {
                    iArr2 = growArrayBy(iArr2, iArr2.length);
                    this._quadBuffer = iArr2;
                }
                int i10 = i8 + 1;
                iArr2[i8] = i6;
                iArr = iArr2;
                i2 = 1;
                i3 = i7;
                i4 = i10;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            int i11 = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN;
            if (inputCodeUtf8JsNames[i11] != 0) {
                break;
            }
            this._inputPtr++;
            i6 = i3;
            i5 = i2;
            iArr2 = iArr;
            i8 = i4;
            i7 = i11;
        }
        if (i2 > 0) {
            if (i4 >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i4] = i3;
            i4++;
        }
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i2) : findName;
    }

    protected final boolean _loadToHaveAtLeast(int i) {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 <= 0 || this._inputPtr <= 0) {
            this._inputEnd = 0;
        } else {
            this._currInputProcessed += this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read == 0) {
                    throw new IOException("InputStream.read() returned 0 characters when trying to read " + i2 + " bytes");
                }
                return false;
            }
            this._inputEnd = read + this._inputEnd;
        }
        return true;
    }

    protected final void _matchToken(String str, int i) {
        int i2;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in a value");
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (i2 = this._inputBuffer[this._inputPtr] & Constants.UNKNOWN) >= 48 && i2 != 93 && i2 != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(i2))) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.flurry.org.codehaus.jackson.sym.Name _parseApostropheFieldName() {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.Utf8StreamParser._parseApostropheFieldName():com.flurry.org.codehaus.jackson.sym.Name");
    }

    protected final Name _parseFieldName(int i) {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & Constants.UNKNOWN;
        if (iArr[i3] != 0) {
            return i3 == 34 ? BytesToNameCanonicalizer.getEmptyName() : parseFieldName(0, i3, 0);
        }
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        int i5 = bArr[i4] & Constants.UNKNOWN;
        if (iArr[i5] != 0) {
            return i5 == 34 ? findName(i3, 1) : parseFieldName(i3, i5, 1);
        }
        int i6 = (i3 << 8) | i5;
        int i7 = this._inputPtr;
        this._inputPtr = i7 + 1;
        int i8 = bArr[i7] & Constants.UNKNOWN;
        if (iArr[i8] != 0) {
            return i8 == 34 ? findName(i6, 2) : parseFieldName(i6, i8, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr[i10] & Constants.UNKNOWN;
        if (iArr[i11] != 0) {
            return i11 == 34 ? findName(i9, 3) : parseFieldName(i9, i11, 3);
        }
        int i12 = (i9 << 8) | i11;
        int i13 = this._inputPtr;
        this._inputPtr = i13 + 1;
        int i14 = bArr[i13] & Constants.UNKNOWN;
        if (iArr[i14] != 0) {
            return i14 == 34 ? findName(i12, 4) : parseFieldName(i12, i14, 4);
        }
        this._quad1 = i12;
        return parseMediumFieldName(i14, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() {
        byte[] bArr;
        super._releaseBuffers();
        if (!this._bufferRecyclable || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(bArr);
    }

    protected void _reportInvalidChar(int i) {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            this._inputPtr++;
            sb.append(_decodeCharForError);
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
    }

    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected final void _skipLF() {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    protected void _skipString() {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    int i4 = bArr[i] & Constants.UNKNOWN;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            return;
                        }
                        switch (iArr[i4]) {
                            case 1:
                                _decodeEscaped();
                                continue;
                            case 2:
                                _skipUtf8_2(i4);
                                continue;
                            case 3:
                                _skipUtf8_3(i4);
                                continue;
                            case 4:
                                _skipUtf8_4(i4);
                                continue;
                            default:
                                if (i4 < 32) {
                                    _throwUnquotedSpace(i4, "string value");
                                    break;
                                } else {
                                    _reportInvalidChar(i4);
                                    continue;
                                }
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                }
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase, com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        this._symbols.release();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this._inputStream;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public String getText() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(jsonToken);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    if (!this._nameCopied) {
                        String currentName = this._parsingContext.getCurrentName();
                        int length = currentName.length();
                        if (this._nameCopyBuffer == null) {
                            this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                        } else if (this._nameCopyBuffer.length < length) {
                            this._nameCopyBuffer = new char[length];
                        }
                        currentName.getChars(0, length, this._nameCopyBuffer, 0);
                        this._nameCopied = true;
                    }
                    return this._nameCopyBuffer;
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                        break;
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
                default:
                    return this._currToken.asCharArray();
            }
            return this._textBuffer.getTextBuffer();
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public int getTextLength() {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                    return this._parsingContext.getCurrentName().length();
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                        break;
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
                default:
                    return this._currToken.asCharArray().length;
            }
            return this._textBuffer.size();
        }
        return 0;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public int getTextOffset() {
        if (this._currToken != null) {
            switch (this._currToken) {
                case FIELD_NAME:
                default:
                    return 0;
                case VALUE_STRING:
                    if (this._tokenIncomplete) {
                        this._tokenIncomplete = false;
                        _finishString();
                        break;
                    }
                    break;
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    break;
            }
            return this._textBuffer.getTextOffset();
        }
        return 0;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected final boolean loadMore() {
        this._currInputProcessed += this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._inputStream != null) {
            int read = this._inputStream.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
            }
            return false;
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Boolean nextBooleanValue() {
        if (this._currToken != JsonToken.FIELD_NAME) {
            switch (nextToken()) {
                case VALUE_TRUE:
                    return Boolean.TRUE;
                case VALUE_FALSE:
                    return Boolean.FALSE;
                default:
                    return null;
            }
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (jsonToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            return null;
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            return null;
        } else {
            return null;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean nextFieldName(SerializableString serializableString) {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                if (this._inputPtr + length < this._inputEnd) {
                    int i = this._inputPtr + length;
                    if (this._inputBuffer[i] == 34) {
                        int i2 = this._inputPtr;
                        for (int i3 = 0; i3 != length; i3++) {
                            if (asQuotedUTF8[i3] == this._inputBuffer[i2 + i3]) {
                            }
                        }
                        this._inputPtr = i + 1;
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        _isNextTokenNameYes();
                        return true;
                    }
                }
            }
            _isNextTokenNameNo(_skipWSOrEnd);
            return false;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int nextIntValue(int i) {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            return i;
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            return i;
        } else {
            return i;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public long nextLongValue(long j) {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            return j;
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            return j;
        } else {
            return j;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public String nextTextValue() {
        if (this._currToken != JsonToken.FIELD_NAME) {
            if (nextToken() == JsonToken.VALUE_STRING) {
                return getText();
            }
            return null;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsAsString();
        } else if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            return null;
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            return null;
        } else {
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() {
        JsonToken parseNumberText;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (this._parsingContext.inObject()) {
                this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
                this._currToken = JsonToken.FIELD_NAME;
                int _skipWS = _skipWS();
                if (_skipWS != 58) {
                    _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
                }
                int _skipWS2 = _skipWS();
                if (_skipWS2 == 34) {
                    this._tokenIncomplete = true;
                    this._nextToken = JsonToken.VALUE_STRING;
                    return this._currToken;
                }
                switch (_skipWS2) {
                    case 45:
                    case 48:
                    case 49:
                    case AdSize.PORTRAIT_AD_HEIGHT /* 50 */:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        parseNumberText = parseNumberText(_skipWS2);
                        break;
                    case 91:
                        parseNumberText = JsonToken.START_ARRAY;
                        break;
                    case 93:
                    case 125:
                        _reportUnexpectedChar(_skipWS2, "expected a value");
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                        break;
                    case 102:
                        _matchToken("false", 1);
                        parseNumberText = JsonToken.VALUE_FALSE;
                        break;
                    case 110:
                        _matchToken(DataFileConstants.NULL_CODEC, 1);
                        parseNumberText = JsonToken.VALUE_NULL;
                        break;
                    case 116:
                        _matchToken("true", 1);
                        parseNumberText = JsonToken.VALUE_TRUE;
                        break;
                    case 123:
                        parseNumberText = JsonToken.START_OBJECT;
                        break;
                    default:
                        parseNumberText = _handleUnexpectedValue(_skipWS2);
                        break;
                }
                this._nextToken = parseNumberText;
                return this._currToken;
            }
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.flurry.org.codehaus.jackson.sym.Name parseEscapedFieldName(int[] r10, int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.Utf8StreamParser.parseEscapedFieldName(int[], int, int, int, int):com.flurry.org.codehaus.jackson.sym.Name");
    }

    protected Name parseLongFieldName(int i) {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        int i3 = i;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            int i5 = bArr[i4] & Constants.UNKNOWN;
            if (iArr[i5] != 0) {
                return i5 == 34 ? findName(this._quadBuffer, i2, i3, 1) : parseEscapedFieldName(this._quadBuffer, i2, i3, i5, 1);
            }
            int i6 = (i3 << 8) | i5;
            byte[] bArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            int i8 = bArr2[i7] & Constants.UNKNOWN;
            if (iArr[i8] != 0) {
                return i8 == 34 ? findName(this._quadBuffer, i2, i6, 2) : parseEscapedFieldName(this._quadBuffer, i2, i6, i8, 2);
            }
            int i9 = (i6 << 8) | i8;
            byte[] bArr3 = this._inputBuffer;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            int i11 = bArr3[i10] & Constants.UNKNOWN;
            if (iArr[i11] != 0) {
                return i11 == 34 ? findName(this._quadBuffer, i2, i9, 3) : parseEscapedFieldName(this._quadBuffer, i2, i9, i11, 3);
            }
            int i12 = (i9 << 8) | i11;
            byte[] bArr4 = this._inputBuffer;
            int i13 = this._inputPtr;
            this._inputPtr = i13 + 1;
            i3 = bArr4[i13] & Constants.UNKNOWN;
            if (iArr[i3] != 0) {
                return i3 == 34 ? findName(this._quadBuffer, i2, i12, 4) : parseEscapedFieldName(this._quadBuffer, i2, i12, i3, 4);
            }
            if (i2 >= this._quadBuffer.length) {
                this._quadBuffer = growArrayBy(this._quadBuffer, i2);
            }
            this._quadBuffer[i2] = i12;
            i2++;
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i3, 0);
    }

    protected final Name parseMediumFieldName(int i, int[] iArr) {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr[i2] & Constants.UNKNOWN;
        if (iArr[i3] != 0) {
            return i3 == 34 ? findName(this._quad1, i, 1) : parseFieldName(this._quad1, i, i3, 1);
        }
        int i4 = i3 | (i << 8);
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        this._inputPtr = i5 + 1;
        int i6 = bArr2[i5] & Constants.UNKNOWN;
        if (iArr[i6] != 0) {
            return i6 == 34 ? findName(this._quad1, i4, 2) : parseFieldName(this._quad1, i4, i6, 2);
        }
        int i7 = (i4 << 8) | i6;
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        this._inputPtr = i8 + 1;
        int i9 = bArr3[i8] & Constants.UNKNOWN;
        if (iArr[i9] != 0) {
            return i9 == 34 ? findName(this._quad1, i7, 3) : parseFieldName(this._quad1, i7, i9, 3);
        }
        int i10 = (i7 << 8) | i9;
        byte[] bArr4 = this._inputBuffer;
        int i11 = this._inputPtr;
        this._inputPtr = i11 + 1;
        int i12 = bArr4[i11] & Constants.UNKNOWN;
        if (iArr[i12] != 0) {
            return i12 == 34 ? findName(this._quad1, i10, 4) : parseFieldName(this._quad1, i10, i12, 4);
        }
        this._quadBuffer[0] = this._quad1;
        this._quadBuffer[1] = i10;
        return parseLongFieldName(i12);
    }

    protected final JsonToken parseNumberText(int i) {
        int i2;
        int i3;
        int i4 = 1;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        boolean z = i == 45;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            i3 = bArr[i5] & Constants.UNKNOWN;
            if (i3 < 48 || i3 > 57) {
                return _handleInvalidNumberStart(i3, true);
            }
            i2 = 1;
        } else {
            i2 = 0;
            i3 = i;
        }
        if (i3 == 48) {
            i3 = _verifyNoLeadingZeroes();
        }
        int i6 = i2 + 1;
        emptyAndGetCurrentSegment[i2] = (char) i3;
        int length = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i7 = this._inputPtr;
            this._inputPtr = i7 + 1;
            int i8 = bArr2[i7] & Constants.UNKNOWN;
            if (i8 < 48 || i8 > 57) {
                if (i8 == 46 || i8 == 101 || i8 == 69) {
                    return _parseFloatText(emptyAndGetCurrentSegment, i6, i8, z, i4);
                }
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i6);
                return resetInt(z, i4);
            }
            i4++;
            emptyAndGetCurrentSegment[i6] = (char) i8;
            i6++;
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i6, z, i4);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int releaseBuffered(OutputStream outputStream) {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    protected Name slowParseFieldName() {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & Constants.UNKNOWN;
        return i2 == 34 ? BytesToNameCanonicalizer.getEmptyName() : parseEscapedFieldName(this._quadBuffer, 0, 0, i2, 0);
    }
}
