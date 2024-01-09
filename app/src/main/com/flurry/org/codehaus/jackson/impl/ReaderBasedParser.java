package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import com.google.ads.AdSize;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/* loaded from: classes.dex */
public final class ReaderBasedParser extends JsonParserBase {
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public ReaderBasedParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i);
        this._tokenIncomplete = false;
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
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

    /* JADX WARN: Removed duplicated region for block: B:25:0x008f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String _parseFieldName2(int r7, int r8, int r9) {
        /*
            r6 = this;
            r5 = 92
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            char[] r1 = r6._inputBuffer
            int r2 = r6._inputPtr
            int r2 = r2 - r7
            r0.resetWithShared(r1, r7, r2)
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            char[] r1 = r0.getCurrentSegment()
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            int r0 = r0.getCurrentSegmentSize()
        L18:
            int r2 = r6._inputPtr
            int r3 = r6._inputEnd
            if (r2 < r3) goto L41
            boolean r2 = r6.loadMore()
            if (r2 != 0) goto L41
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ": was expecting closing '"
            java.lang.StringBuilder r2 = r2.append(r3)
            char r3 = (char) r9
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "' for name"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6._reportInvalidEOF(r2)
        L41:
            char[] r2 = r6._inputBuffer
            int r3 = r6._inputPtr
            int r4 = r3 + 1
            r6._inputPtr = r4
            char r3 = r2[r3]
            if (r3 > r5) goto L8d
            if (r3 != r5) goto L66
            char r2 = r6._decodeEscaped()
        L53:
            int r4 = r8 * 31
            int r8 = r4 + r3
            int r3 = r0 + 1
            r1[r0] = r2
            int r0 = r1.length
            if (r3 < r0) goto L8f
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            char[] r1 = r0.finishCurrentSegment()
            r0 = 0
            goto L18
        L66:
            if (r3 > r9) goto L8d
            if (r3 != r9) goto L84
            com.flurry.org.codehaus.jackson.util.TextBuffer r1 = r6._textBuffer
            r1.setCurrentLength(r0)
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r6._textBuffer
            char[] r1 = r0.getTextBuffer()
            int r2 = r0.getTextOffset()
            int r0 = r0.size()
            com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer r3 = r6._symbols
            java.lang.String r0 = r3.findSymbol(r1, r2, r0, r8)
            return r0
        L84:
            r2 = 32
            if (r3 >= r2) goto L8d
            java.lang.String r2 = "name"
            r6._throwUnquotedSpace(r3, r2)
        L8d:
            r2 = r3
            goto L53
        L8f:
            r0 = r3
            goto L18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.ReaderBasedParser._parseFieldName2(int, int, int):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String _parseUnusualFieldName2(int r6, int r7, int[] r8) {
        /*
            r5 = this;
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r5._textBuffer
            char[] r1 = r5._inputBuffer
            int r2 = r5._inputPtr
            int r2 = r2 - r6
            r0.resetWithShared(r1, r6, r2)
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r5._textBuffer
            char[] r1 = r0.getCurrentSegment()
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r5._textBuffer
            int r0 = r0.getCurrentSegmentSize()
            int r3 = r8.length
        L17:
            int r2 = r5._inputPtr
            int r4 = r5._inputEnd
            if (r2 < r4) goto L3d
            boolean r2 = r5.loadMore()
            if (r2 != 0) goto L3d
        L23:
            com.flurry.org.codehaus.jackson.util.TextBuffer r1 = r5._textBuffer
            r1.setCurrentLength(r0)
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r5._textBuffer
            char[] r1 = r0.getTextBuffer()
            int r2 = r0.getTextOffset()
            int r0 = r0.size()
            com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer r3 = r5._symbols
            java.lang.String r0 = r3.findSymbol(r1, r2, r0, r7)
            return r0
        L3d:
            char[] r2 = r5._inputBuffer
            int r4 = r5._inputPtr
            char r4 = r2[r4]
            if (r4 > r3) goto L62
            r2 = r8[r4]
            if (r2 != 0) goto L23
        L49:
            int r2 = r5._inputPtr
            int r2 = r2 + 1
            r5._inputPtr = r2
            int r2 = r7 * 31
            int r7 = r2 + r4
            int r2 = r0 + 1
            r1[r0] = r4
            int r0 = r1.length
            if (r2 < r0) goto L69
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r5._textBuffer
            char[] r1 = r0.finishCurrentSegment()
            r0 = 0
            goto L17
        L62:
            boolean r2 = java.lang.Character.isJavaIdentifierPart(r4)
            if (r2 != 0) goto L49
            goto L23
        L69:
            r0 = r2
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.ReaderBasedParser._parseUnusualFieldName2(int, int, int[]):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
        _reportInvalidEOF(" in a comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _skipCComment() {
        /*
            r4 = this;
            r3 = 42
        L2:
            int r0 = r4._inputPtr
            int r1 = r4._inputEnd
            if (r0 < r1) goto Le
            boolean r0 = r4.loadMore()
            if (r0 == 0) goto L28
        Le:
            char[] r0 = r4._inputBuffer
            int r1 = r4._inputPtr
            int r2 = r1 + 1
            r4._inputPtr = r2
            char r0 = r0[r1]
            if (r0 > r3) goto L2
            if (r0 != r3) goto L3f
            int r0 = r4._inputPtr
            int r1 = r4._inputEnd
            if (r0 < r1) goto L2e
            boolean r0 = r4.loadMore()
            if (r0 != 0) goto L2e
        L28:
            java.lang.String r0 = " in a comment"
            r4._reportInvalidEOF(r0)
        L2d:
            return
        L2e:
            char[] r0 = r4._inputBuffer
            int r1 = r4._inputPtr
            char r0 = r0[r1]
            r1 = 47
            if (r0 != r1) goto L2
            int r0 = r4._inputPtr
            int r0 = r0 + 1
            r4._inputPtr = r0
            goto L2d
        L3f:
            r1 = 32
            if (r0 >= r1) goto L2
            r1 = 10
            if (r0 != r1) goto L4b
            r4._skipLF()
            goto L2
        L4b:
            r1 = 13
            if (r0 != r1) goto L53
            r4._skipCR()
            goto L2
        L53:
            r1 = 9
            if (r0 == r1) goto L2
            r4._throwInvalidSpace(r0)
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.ReaderBasedParser._skipCComment():void");
    }

    private final void _skipComment() {
        if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '/') {
            _skipCppComment();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCppComment() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c < ' ') {
                if (c == '\n') {
                    _skipLF();
                    return;
                } else if (c == '\r') {
                    _skipCR();
                    return;
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
                }
            }
        }
    }

    private final int _skipWS() {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                if (c != '/') {
                    return c;
                }
                _skipComment();
            } else if (c != ' ') {
                if (c == '\n') {
                    _skipLF();
                } else if (c == '\r') {
                    _skipCR();
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
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
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                if (c != '/') {
                    return c;
                }
                _skipComment();
            } else if (c != ' ') {
                if (c == '\n') {
                    _skipLF();
                } else if (c == '\r') {
                    _skipCR();
                } else if (c != '\t') {
                    _throwInvalidSpace(c);
                }
            }
        }
    }

    private final char _verifyNoLeadingZeroes() {
        if (this._inputPtr < this._inputEnd || loadMore()) {
            char c = this._inputBuffer[this._inputPtr];
            if (c < '0' || c > '9') {
                return '0';
            }
            if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            this._inputPtr++;
            if (c == '0') {
                do {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        return c;
                    }
                    c = this._inputBuffer[this._inputPtr];
                    if (c < '0' || c > '9') {
                        return '0';
                    }
                    this._inputPtr++;
                } while (c == '0');
                return c;
            }
            return c;
        }
        return '0';
    }

    private final JsonToken parseNumberText2(boolean z) {
        int i;
        char nextChar;
        boolean z2;
        int i2;
        int i3;
        char c;
        char[] cArr;
        int i4;
        char c2;
        char[] cArr2;
        int i5;
        boolean z3;
        char nextChar2;
        int i6;
        char nextChar3;
        char c3;
        int i7;
        int i8;
        int i9;
        boolean z4;
        boolean z5;
        int i10;
        int i11 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (this._inputPtr < this._inputEnd) {
            char[] cArr3 = this._inputBuffer;
            int i12 = this._inputPtr;
            this._inputPtr = i12 + 1;
            nextChar = cArr3[i12];
        } else {
            nextChar = getNextChar("No digit following minus sign");
        }
        if (nextChar == '0') {
            nextChar = _verifyNoLeadingZeroes();
        }
        int i13 = 0;
        char c4 = nextChar;
        char[] cArr4 = emptyAndGetCurrentSegment;
        char c5 = c4;
        while (c5 >= '0' && c5 <= '9') {
            i13++;
            if (i >= cArr4.length) {
                cArr4 = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i14 = i + 1;
            cArr4[i] = c5;
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                z2 = true;
                c = 0;
                i2 = i13;
                cArr = cArr4;
                i3 = i14;
                break;
            }
            char[] cArr5 = this._inputBuffer;
            int i15 = this._inputPtr;
            this._inputPtr = i15 + 1;
            c5 = cArr5[i15];
            i = i14;
        }
        z2 = false;
        i2 = i13;
        i3 = i;
        c = c5;
        cArr = cArr4;
        if (i2 == 0) {
            reportInvalidNumber("Missing integer part (next char " + _getCharDesc(c) + ")");
        }
        if (c == '.') {
            int i16 = i3 + 1;
            cArr[i3] = c;
            char[] cArr6 = cArr;
            int i17 = i16;
            char c6 = c;
            int i18 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    c2 = c6;
                    z5 = true;
                    break;
                }
                char[] cArr7 = this._inputBuffer;
                int i19 = this._inputPtr;
                this._inputPtr = i19 + 1;
                c6 = cArr7[i19];
                if (c6 < '0') {
                    c2 = c6;
                    z5 = z2;
                    break;
                } else if (c6 > '9') {
                    c2 = c6;
                    z5 = z2;
                    break;
                } else {
                    i18++;
                    if (i17 >= cArr6.length) {
                        cArr6 = this._textBuffer.finishCurrentSegment();
                        i10 = 0;
                    } else {
                        i10 = i17;
                    }
                    i17 = i10 + 1;
                    cArr6[i10] = c6;
                }
            }
            if (i18 == 0) {
                reportUnexpectedNumberChar(c2, "Decimal point not followed by a digit");
            }
            i4 = i18;
            i5 = i17;
            boolean z6 = z5;
            cArr2 = cArr6;
            z3 = z6;
        } else {
            i4 = 0;
            c2 = c;
            cArr2 = cArr;
            i5 = i3;
            z3 = z2;
        }
        if (c2 == 'e' || c2 == 'E') {
            if (i5 >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i5 = 0;
            }
            int i20 = i5 + 1;
            cArr2[i5] = c2;
            if (this._inputPtr < this._inputEnd) {
                char[] cArr8 = this._inputBuffer;
                int i21 = this._inputPtr;
                this._inputPtr = i21 + 1;
                nextChar2 = cArr8[i21];
            } else {
                nextChar2 = getNextChar("expected a digit for number exponent");
            }
            if (nextChar2 == '-' || nextChar2 == '+') {
                if (i20 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i6 = 0;
                } else {
                    i6 = i20;
                }
                int i22 = i6 + 1;
                cArr2[i6] = nextChar2;
                if (this._inputPtr < this._inputEnd) {
                    char[] cArr9 = this._inputBuffer;
                    int i23 = this._inputPtr;
                    this._inputPtr = i23 + 1;
                    nextChar3 = cArr9[i23];
                } else {
                    nextChar3 = getNextChar("expected a digit for number exponent");
                }
                c3 = nextChar3;
                i7 = i22;
                i8 = 0;
            } else {
                c3 = nextChar2;
                i7 = i20;
                i8 = 0;
            }
            while (c3 <= '9' && c3 >= '0') {
                i8++;
                if (i7 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i7 = 0;
                }
                int i24 = i7 + 1;
                cArr2[i7] = c3;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    i11 = i8;
                    z4 = true;
                    i9 = i24;
                    break;
                }
                char[] cArr10 = this._inputBuffer;
                int i25 = this._inputPtr;
                this._inputPtr = i25 + 1;
                c3 = cArr10[i25];
                i7 = i24;
            }
            i11 = i8;
            i9 = i7;
            z4 = z3;
            if (i11 == 0) {
                reportUnexpectedNumberChar(c3, "Exponent indicator not followed by a digit");
            }
        } else {
            i9 = i5;
            z4 = z3;
        }
        if (!z4) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i9);
        return reset(z, i2, i4, i11);
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected void _closeInput() {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected byte[] _decodeBase64(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                char c2 = cArr2[i2];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c2, 1);
                }
                int i3 = decodeBase64Char2 | (decodeBase64Char << 6);
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                char c3 = cArr3[i4];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 == '\"' && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.append(i3 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, c3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i5 = this._inputPtr;
                        this._inputPtr = i5 + 1;
                        char c4 = cArr4[i5];
                        if (!base64Variant.usesPaddingChar(c4)) {
                            throw reportInvalidBase64Char(base64Variant, c4, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i3 >> 4);
                    }
                }
                int i6 = (i3 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                char c5 = cArr5[i7];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 == '\"' && !base64Variant.usesPadding()) {
                            _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, c5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes(decodeBase64Char4 | (i6 << 6));
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected final char _decodeEscaped() {
        int i = 0;
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        char c = cArr[i2];
        switch (c) {
            case '\"':
            case '/':
            case '\\':
                return c;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    char[] cArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    char c2 = cArr2[i4];
                    int charToHex = CharTypes.charToHex(c2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
                    }
                    i = (i << 4) | charToHex;
                }
                return (char) i;
            default:
                return _handleUnrecognizedCharacterEscape(c);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    protected void _finishString() {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        if (i < i2) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char c = this._inputBuffer[i];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } else if (c == '\"') {
                    this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, i - this._inputPtr);
                    this._inputPtr = i + 1;
                    return;
                }
            }
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, i - this._inputPtr);
        this._inputPtr = i;
        _finishString2();
    }

    protected void _finishString2() {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i2 = currentSegmentSize;
            currentSegmentSize = i2 + 1;
            currentSegment[i2] = c;
        }
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

    protected final JsonToken _handleApostropheValue() {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\'') {
                    if (c == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i2 = currentSegmentSize;
            currentSegmentSize = i2 + 1;
            emptyAndGetCurrentSegment[i2] = c;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected com.flurry.org.codehaus.jackson.JsonToken _handleInvalidNumberStart(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:372)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */

    final JsonToken _handleUnexpectedValue(int i) {
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
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(cArr[i2], false);
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

    protected final String _handleUnusualFieldName(int i) {
        int i2 = 0;
        if (i == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        int length = inputCodeLatin1JsNames.length;
        if (!(i < length ? inputCodeLatin1JsNames[i] == 0 && (i < 48 || i > 57) : Character.isJavaIdentifierPart((char) i))) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = this._inputPtr;
        int i4 = this._inputEnd;
        if (i3 >= i4) {
            this._inputPtr = i3;
            return _parseUnusualFieldName2(this._inputPtr - 1, i2, inputCodeLatin1JsNames);
        }
        do {
            char c = this._inputBuffer[i3];
            if (c < length) {
                if (inputCodeLatin1JsNames[c] != 0) {
                    int i5 = this._inputPtr - 1;
                    this._inputPtr = i3;
                    return this._symbols.findSymbol(this._inputBuffer, i5, i3 - i5, i2);
                }
            } else if (!Character.isJavaIdentifierPart(c)) {
                int i6 = this._inputPtr - 1;
                this._inputPtr = i3;
                return this._symbols.findSymbol(this._inputBuffer, i6, i3 - i6, i2);
            }
            i2 = (i2 * 31) + c;
            i3++;
        } while (i3 < i4);
        this._inputPtr = i3;
        return _parseUnusualFieldName2(this._inputPtr - 1, i2, inputCodeLatin1JsNames);
    }

    protected final void _matchToken(String str, int i) {
        char c;
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if ((this._inputPtr < this._inputEnd || loadMore()) && (c = this._inputBuffer[this._inputPtr]) >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
            this._inputPtr++;
            _reportInvalidToken(str.substring(0, i), "'null', 'true', 'false' or NaN");
        }
    }

    protected final String _parseApostropheFieldName() {
        int i = this._inputPtr;
        int i2 = 0;
        int i3 = this._inputEnd;
        if (i < i3) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            do {
                char c = this._inputBuffer[i];
                if (c != '\'') {
                    if (c < length && inputCodeLatin1[c] != 0) {
                        break;
                    }
                    i2 = (i2 * 31) + c;
                    i++;
                } else {
                    int i4 = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i4, i - i4, i2);
                }
            } while (i < i3);
        }
        int i5 = this._inputPtr;
        this._inputPtr = i;
        return _parseFieldName2(i5, i2, 39);
    }

    protected final String _parseFieldName(int i) {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        int i2 = this._inputPtr;
        int i3 = 0;
        int i4 = this._inputEnd;
        if (i2 < i4) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char c = this._inputBuffer[i2];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i3 = (i3 * 31) + c;
                    i2++;
                    if (i2 >= i4) {
                        break;
                    }
                } else if (c == '\"') {
                    int i5 = this._inputPtr;
                    this._inputPtr = i2 + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i5, i2 - i5, i3);
                }
            }
        }
        int i6 = this._inputPtr;
        this._inputPtr = i2;
        return _parseFieldName2(i6, i3, 34);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserBase
    public void _releaseBuffers() {
        super._releaseBuffers();
        char[] cArr = this._inputBuffer;
        if (cArr != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    protected void _reportInvalidToken(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            sb.append(c);
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting ");
    }

    protected final void _skipCR() {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
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
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = i3;
                    _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._inputPtr = i3;
                        return;
                    } else if (c < ' ') {
                        this._inputPtr = i3;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = i3;
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
        return this._reader;
    }

    protected char getNextChar(String str) {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase, com.flurry.org.codehaus.jackson.JsonParser
    public final String getText() {
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
        if (this._reader != null) {
            int read = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
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
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                int _skipWS = _skipWS();
                if (_skipWS != 58) {
                    _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
                }
                _skipWSOrEnd = _skipWS();
            }
            switch (_skipWSOrEnd) {
                case 34:
                    this._tokenIncomplete = true;
                    parseNumberText = JsonToken.VALUE_STRING;
                    break;
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
                    parseNumberText = parseNumberText(_skipWSOrEnd);
                    break;
                case 91:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    parseNumberText = JsonToken.START_ARRAY;
                    break;
                case 93:
                case 125:
                    _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
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
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    parseNumberText = JsonToken.START_OBJECT;
                    break;
                default:
                    parseNumberText = _handleUnexpectedValue(_skipWSOrEnd);
                    break;
            }
            if (inObject) {
                this._nextToken = parseNumberText;
                return this._currToken;
            }
            this._currToken = parseNumberText;
            return parseNumberText;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r14v0 ??, r14v1 ??, r14v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected final com.flurry.org.codehaus.jackson.JsonToken parseNumberText(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r14v0 ??, r14v1 ??, r14v2 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r14v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:372)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int releaseBuffered(Writer writer) {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        writer.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }
}
