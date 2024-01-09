package com.flurry.org.codehaus.jackson.impl;

import com.flurry.android.Constants;
import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.io.CharacterEscapes;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class WriterBasedGenerator extends JsonGeneratorBase {
    protected static final int SHORT_WRITE = 32;
    protected CharacterEscapes _characterEscapes;
    protected SerializableString _currentEscape;
    protected char[] _entityBuffer;
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected char[] _outputBuffer;
    protected int _outputEnd;
    protected int[] _outputEscapes;
    protected int _outputHead;
    protected int _outputTail;
    protected final Writer _writer;
    protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
    protected static final int[] sOutputEscapes = CharTypes.get7BitOutputEscapes();

    public WriterBasedGenerator(IOContext iOContext, int i, ObjectCodec objectCodec, Writer writer) {
        super(i, objectCodec);
        this._outputEscapes = sOutputEscapes;
        this._outputHead = 0;
        this._outputTail = 0;
        this._ioContext = iOContext;
        this._writer = writer;
        this._outputBuffer = iOContext.allocConcatBuffer();
        this._outputEnd = this._outputBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    private char[] _allocateEntityBuffer() {
        char[] cArr = {'\\', 0, '\\', 'u', '0', '0', 0, 0, '\\', 'u'};
        this._entityBuffer = cArr;
        return cArr;
    }

    private final void _appendCharacterEscape(char c, int i) {
        String value;
        int i2;
        if (i >= 0) {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr[i3] = '\\';
            char[] cArr2 = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            cArr2[i4] = (char) i;
        } else if (i == -2) {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (this._outputTail + length > this._outputEnd) {
                _flushBuffer();
                if (length > this._outputEnd) {
                    this._writer.write(value);
                    return;
                }
            }
            value.getChars(0, length, this._outputBuffer, this._outputTail);
            this._outputTail += length;
        } else {
            if (this._outputTail + 2 > this._outputEnd) {
                _flushBuffer();
            }
            int i5 = this._outputTail;
            char[] cArr3 = this._outputBuffer;
            int i6 = i5 + 1;
            cArr3[i5] = '\\';
            int i7 = i6 + 1;
            cArr3[i6] = 'u';
            if (c > 255) {
                int i8 = (c >> '\b') & 255;
                int i9 = i7 + 1;
                cArr3[i7] = HEX_CHARS[i8 >> 4];
                i2 = i9 + 1;
                cArr3[i9] = HEX_CHARS[i8 & 15];
                c = (char) (c & 255);
            } else {
                int i10 = i7 + 1;
                cArr3[i7] = '0';
                i2 = i10 + 1;
                cArr3[i10] = '0';
            }
            int i11 = i2 + 1;
            cArr3[i2] = HEX_CHARS[c >> 4];
            cArr3[i11] = HEX_CHARS[c & 15];
            this._outputTail = i11;
        }
    }

    private final int _prependOrWriteCharacterEscape(char[] cArr, int i, int i2, char c, int i3) {
        String value;
        int i4;
        if (i3 >= 0) {
            if (i > 1 && i < i2) {
                int i5 = i - 2;
                cArr[i5] = '\\';
                cArr[i5 + 1] = (char) i3;
                return i5;
            }
            char[] cArr2 = this._entityBuffer;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            cArr2[1] = (char) i3;
            this._writer.write(cArr2, 0, 2);
            return i;
        } else if (i3 == -2) {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (i < length || i >= i2) {
                this._writer.write(value);
                return i;
            }
            int i6 = i - length;
            value.getChars(0, length, cArr, i6);
            return i6;
        } else if (i <= 5 || i >= i2) {
            char[] cArr3 = this._entityBuffer;
            if (cArr3 == null) {
                cArr3 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c <= 255) {
                cArr3[6] = HEX_CHARS[c >> 4];
                cArr3[7] = HEX_CHARS[c & 15];
                this._writer.write(cArr3, 2, 6);
                return i;
            }
            int i7 = (c >> '\b') & 255;
            int i8 = c & 255;
            cArr3[10] = HEX_CHARS[i7 >> 4];
            cArr3[11] = HEX_CHARS[i7 & 15];
            cArr3[12] = HEX_CHARS[i8 >> 4];
            cArr3[13] = HEX_CHARS[i8 & 15];
            this._writer.write(cArr3, 8, 6);
            return i;
        } else {
            int i9 = i - 6;
            int i10 = i9 + 1;
            cArr[i9] = '\\';
            int i11 = i10 + 1;
            cArr[i10] = 'u';
            if (c > 255) {
                int i12 = (c >> '\b') & 255;
                int i13 = i11 + 1;
                cArr[i11] = HEX_CHARS[i12 >> 4];
                i4 = i13 + 1;
                cArr[i13] = HEX_CHARS[i12 & 15];
                c = (char) (c & 255);
            } else {
                int i14 = i11 + 1;
                cArr[i11] = '0';
                i4 = i14 + 1;
                cArr[i14] = '0';
            }
            int i15 = i4 + 1;
            cArr[i4] = HEX_CHARS[c >> 4];
            cArr[i15] = HEX_CHARS[c & 15];
            return i15 - 5;
        }
    }

    private final void _prependOrWriteCharacterEscape(char c, int i) {
        String value;
        int i2;
        if (i >= 0) {
            if (this._outputTail >= 2) {
                int i3 = this._outputTail - 2;
                this._outputHead = i3;
                this._outputBuffer[i3] = '\\';
                this._outputBuffer[i3 + 1] = (char) i;
                return;
            }
            char[] cArr = this._entityBuffer;
            if (cArr == null) {
                cArr = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            cArr[1] = (char) i;
            this._writer.write(cArr, 0, 2);
        } else if (i == -2) {
            if (this._currentEscape == null) {
                value = this._characterEscapes.getEscapeSequence(c).getValue();
            } else {
                value = this._currentEscape.getValue();
                this._currentEscape = null;
            }
            int length = value.length();
            if (this._outputTail < length) {
                this._outputHead = this._outputTail;
                this._writer.write(value);
                return;
            }
            int i4 = this._outputTail - length;
            this._outputHead = i4;
            value.getChars(0, length, this._outputBuffer, i4);
        } else if (this._outputTail < 6) {
            char[] cArr2 = this._entityBuffer;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            this._outputHead = this._outputTail;
            if (c <= 255) {
                cArr2[6] = HEX_CHARS[c >> 4];
                cArr2[7] = HEX_CHARS[c & 15];
                this._writer.write(cArr2, 2, 6);
                return;
            }
            int i5 = (c >> '\b') & 255;
            int i6 = c & 255;
            cArr2[10] = HEX_CHARS[i5 >> 4];
            cArr2[11] = HEX_CHARS[i5 & 15];
            cArr2[12] = HEX_CHARS[i6 >> 4];
            cArr2[13] = HEX_CHARS[i6 & 15];
            this._writer.write(cArr2, 8, 6);
        } else {
            char[] cArr3 = this._outputBuffer;
            int i7 = this._outputTail - 6;
            this._outputHead = i7;
            cArr3[i7] = '\\';
            int i8 = i7 + 1;
            cArr3[i8] = 'u';
            if (c > 255) {
                int i9 = (c >> '\b') & 255;
                int i10 = i8 + 1;
                cArr3[i10] = HEX_CHARS[i9 >> 4];
                i2 = i10 + 1;
                cArr3[i2] = HEX_CHARS[i9 & 15];
                c = (char) (c & 255);
            } else {
                int i11 = i8 + 1;
                cArr3[i11] = '0';
                i2 = i11 + 1;
                cArr3[i2] = '0';
            }
            int i12 = i2 + 1;
            cArr3[i12] = HEX_CHARS[c >> 4];
            cArr3[i12 + 1] = HEX_CHARS[c & 15];
        }
    }

    private void _writeLongString(String str) {
        _flushBuffer();
        int length = str.length();
        int i = 0;
        do {
            int i2 = this._outputEnd;
            if (i + i2 > length) {
                i2 = length - i;
            }
            str.getChars(i, i + i2, this._outputBuffer, 0);
            if (this._characterEscapes != null) {
                _writeSegmentCustom(i2);
            } else if (this._maximumNonEscapedChar != 0) {
                _writeSegmentASCII(i2, this._maximumNonEscapedChar);
            } else {
                _writeSegment(i2);
            }
            i += i2;
        } while (i < length);
    }

    private final void _writeNull() {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        int i = this._outputTail;
        char[] cArr = this._outputBuffer;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        this._outputTail = i4 + 1;
    }

    private final void _writeQuotedInt(int i) {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr[i2] = '\"';
        this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr2[i3] = '\"';
    }

    private final void _writeQuotedLong(long j) {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    private final void _writeQuotedRaw(Object obj) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        writeRaw(obj.toString());
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    private final void _writeSegment(int i) {
        char c;
        int[] iArr = this._outputEscapes;
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            do {
                c = this._outputBuffer[i2];
                if (c < length && iArr[c] != 0) {
                    break;
                }
                i2++;
            } while (i2 < i);
            int i4 = i2 - i3;
            if (i4 > 0) {
                this._writer.write(this._outputBuffer, i3, i4);
                if (i2 >= i) {
                    return;
                }
            }
            int i5 = i2 + 1;
            i3 = _prependOrWriteCharacterEscape(this._outputBuffer, i5, i, c, iArr[c]);
            i2 = i5;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0031 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _writeSegmentASCII(int r10, int r11) {
        /*
            r9 = this;
            r0 = 0
            int[] r6 = r9._outputEscapes
            int r1 = r6.length
            int r2 = r9._maximumNonEscapedChar
            int r2 = r2 + 1
            int r7 = java.lang.Math.min(r1, r2)
            r2 = r0
            r1 = r0
        Le:
            if (r1 >= r10) goto L27
        L10:
            char[] r3 = r9._outputBuffer
            char r4 = r3[r1]
            if (r4 >= r7) goto L28
            r5 = r6[r4]
            if (r5 == 0) goto L2c
        L1a:
            int r0 = r1 - r2
            if (r0 <= 0) goto L33
            java.io.Writer r3 = r9._writer
            char[] r8 = r9._outputBuffer
            r3.write(r8, r2, r0)
            if (r1 < r10) goto L33
        L27:
            return
        L28:
            if (r4 <= r11) goto L2d
            r5 = -1
            goto L1a
        L2c:
            r0 = r5
        L2d:
            int r1 = r1 + 1
            if (r1 < r10) goto L10
            r5 = r0
            goto L1a
        L33:
            int r2 = r1 + 1
            char[] r1 = r9._outputBuffer
            r0 = r9
            r3 = r10
            int r0 = r0._prependOrWriteCharacterEscape(r1, r2, r3, r4, r5)
            r1 = r2
            r2 = r0
            r0 = r5
            goto Le
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeSegmentASCII(int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x004a A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _writeSegmentCustom(int r12) {
        /*
            r11 = this;
            r1 = 0
            int[] r7 = r11._outputEscapes
            int r0 = r11._maximumNonEscapedChar
            r2 = 1
            if (r0 >= r2) goto L33
            r0 = 65535(0xffff, float:9.1834E-41)
            r6 = r0
        Lc:
            int r0 = r7.length
            int r2 = r11._maximumNonEscapedChar
            int r2 = r2 + 1
            int r8 = java.lang.Math.min(r0, r2)
            com.flurry.org.codehaus.jackson.io.CharacterEscapes r9 = r11._characterEscapes
            r2 = r1
            r0 = r1
        L19:
            if (r1 >= r12) goto L32
        L1b:
            char[] r3 = r11._outputBuffer
            char r4 = r3[r1]
            if (r4 >= r8) goto L37
            r5 = r7[r4]
            if (r5 == 0) goto L45
        L25:
            int r0 = r1 - r2
            if (r0 <= 0) goto L4c
            java.io.Writer r3 = r11._writer
            char[] r10 = r11._outputBuffer
            r3.write(r10, r2, r0)
            if (r1 < r12) goto L4c
        L32:
            return
        L33:
            int r0 = r11._maximumNonEscapedChar
            r6 = r0
            goto Lc
        L37:
            if (r4 <= r6) goto L3b
            r5 = -1
            goto L25
        L3b:
            com.flurry.org.codehaus.jackson.SerializableString r3 = r9.getEscapeSequence(r4)
            r11._currentEscape = r3
            if (r3 == 0) goto L46
            r5 = -2
            goto L25
        L45:
            r0 = r5
        L46:
            int r1 = r1 + 1
            if (r1 < r12) goto L1b
            r5 = r0
            goto L25
        L4c:
            int r2 = r1 + 1
            char[] r1 = r11._outputBuffer
            r0 = r11
            r3 = r12
            int r0 = r0._prependOrWriteCharacterEscape(r1, r2, r3, r4, r5)
            r1 = r2
            r2 = r0
            r0 = r5
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeSegmentCustom(int):void");
    }

    private void _writeString(String str) {
        int length = str.length();
        if (length > this._outputEnd) {
            _writeLongString(str);
            return;
        }
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
        }
        str.getChars(0, length, this._outputBuffer, this._outputTail);
        if (this._characterEscapes != null) {
            _writeStringCustom(length);
        } else if (this._maximumNonEscapedChar != 0) {
            _writeStringASCII(length, this._maximumNonEscapedChar);
        } else {
            _writeString2(length);
        }
    }

    private final void _writeString(char[] cArr, int i, int i2) {
        if (this._characterEscapes != null) {
            _writeStringCustom(cArr, i, i2);
        } else if (this._maximumNonEscapedChar != 0) {
            _writeStringASCII(cArr, i, i2, this._maximumNonEscapedChar);
        } else {
            int i3 = i2 + i;
            int[] iArr = this._outputEscapes;
            int length = iArr.length;
            int i4 = i;
            while (i4 < i3) {
                int i5 = i4;
                do {
                    char c = cArr[i5];
                    if (c < length && iArr[c] != 0) {
                        break;
                    }
                    i5++;
                } while (i5 < i3);
                int i6 = i5 - i4;
                if (i6 < 32) {
                    if (this._outputTail + i6 > this._outputEnd) {
                        _flushBuffer();
                    }
                    if (i6 > 0) {
                        System.arraycopy(cArr, i4, this._outputBuffer, this._outputTail, i6);
                        this._outputTail += i6;
                    }
                } else {
                    _flushBuffer();
                    this._writer.write(cArr, i4, i6);
                }
                if (i5 >= i3) {
                    return;
                }
                i4 = i5 + 1;
                char c2 = cArr[i5];
                _appendCharacterEscape(c2, iArr[c2]);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r3 <= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001d, code lost:
        r7._writer.write(r7._outputBuffer, r7._outputHead, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
        r3 = r7._outputBuffer;
        r4 = r7._outputTail;
        r7._outputTail = r4 + 1;
        r3 = r3[r4];
        _prependOrWriteCharacterEscape(r3, r1[r3]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r3 = r7._outputTail - r7._outputHead;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeString2(int r8) {
        /*
            r7 = this;
            int r0 = r7._outputTail
            int r0 = r0 + r8
            int[] r1 = r7._outputEscapes
            int r2 = r1.length
        L6:
            int r3 = r7._outputTail
            if (r3 >= r0) goto L3e
        La:
            char[] r3 = r7._outputBuffer
            int r4 = r7._outputTail
            char r3 = r3[r4]
            if (r3 >= r2) goto L36
            r3 = r1[r3]
            if (r3 == 0) goto L36
            int r3 = r7._outputTail
            int r4 = r7._outputHead
            int r3 = r3 - r4
            if (r3 <= 0) goto L26
            java.io.Writer r4 = r7._writer
            char[] r5 = r7._outputBuffer
            int r6 = r7._outputHead
            r4.write(r5, r6, r3)
        L26:
            char[] r3 = r7._outputBuffer
            int r4 = r7._outputTail
            int r5 = r4 + 1
            r7._outputTail = r5
            char r3 = r3[r4]
            r4 = r1[r3]
            r7._prependOrWriteCharacterEscape(r3, r4)
            goto L6
        L36:
            int r3 = r7._outputTail
            int r3 = r3 + 1
            r7._outputTail = r3
            if (r3 < r0) goto La
        L3e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeString2(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringASCII(int r10, int r11) {
        /*
            r9 = this;
            int r0 = r9._outputTail
            int r1 = r0 + r10
            int[] r2 = r9._outputEscapes
            int r0 = r2.length
            int r3 = r9._maximumNonEscapedChar
            int r3 = r3 + 1
            int r3 = java.lang.Math.min(r0, r3)
        Lf:
            int r0 = r9._outputTail
            if (r0 >= r1) goto L45
        L13:
            char[] r0 = r9._outputBuffer
            int r4 = r9._outputTail
            char r4 = r0[r4]
            if (r4 >= r3) goto L39
            r0 = r2[r4]
            if (r0 == 0) goto L3d
        L1f:
            int r5 = r9._outputTail
            int r6 = r9._outputHead
            int r5 = r5 - r6
            if (r5 <= 0) goto L2f
            java.io.Writer r6 = r9._writer
            char[] r7 = r9._outputBuffer
            int r8 = r9._outputHead
            r6.write(r7, r8, r5)
        L2f:
            int r5 = r9._outputTail
            int r5 = r5 + 1
            r9._outputTail = r5
            r9._prependOrWriteCharacterEscape(r4, r0)
            goto Lf
        L39:
            if (r4 <= r11) goto L3d
            r0 = -1
            goto L1f
        L3d:
            int r0 = r9._outputTail
            int r0 = r0 + 1
            r9._outputTail = r0
            if (r0 < r1) goto L13
        L45:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeStringASCII(int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0018 A[EDGE_INSN: B:29:0x0018->B:9:0x0018 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _writeStringASCII(char[] r11, int r12, int r13, int r14) {
        /*
            r10 = this;
            int r3 = r13 + r12
            int[] r4 = r10._outputEscapes
            int r0 = r4.length
            int r1 = r14 + 1
            int r5 = java.lang.Math.min(r0, r1)
            r0 = 0
            r2 = r12
        Ld:
            if (r2 >= r3) goto L38
            r1 = r2
        L10:
            char r6 = r11[r1]
            if (r6 >= r5) goto L39
            r0 = r4[r6]
            if (r0 == 0) goto L3d
        L18:
            int r7 = r1 - r2
            r8 = 32
            if (r7 >= r8) goto L42
            int r8 = r10._outputTail
            int r8 = r8 + r7
            int r9 = r10._outputEnd
            if (r8 <= r9) goto L28
            r10._flushBuffer()
        L28:
            if (r7 <= 0) goto L36
            char[] r8 = r10._outputBuffer
            int r9 = r10._outputTail
            java.lang.System.arraycopy(r11, r2, r8, r9, r7)
            int r2 = r10._outputTail
            int r2 = r2 + r7
            r10._outputTail = r2
        L36:
            if (r1 < r3) goto L4b
        L38:
            return
        L39:
            if (r6 <= r14) goto L3d
            r0 = -1
            goto L18
        L3d:
            int r1 = r1 + 1
            if (r1 < r3) goto L10
            goto L18
        L42:
            r10._flushBuffer()
            java.io.Writer r8 = r10._writer
            r8.write(r11, r2, r7)
            goto L36
        L4b:
            int r2 = r1 + 1
            r10._appendCharacterEscape(r6, r0)
            goto Ld
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeStringASCII(char[], int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _writeStringCustom(int r12) {
        /*
            r11 = this;
            int r0 = r11._outputTail
            int r2 = r0 + r12
            int[] r3 = r11._outputEscapes
            int r0 = r11._maximumNonEscapedChar
            r1 = 1
            if (r0 >= r1) goto L41
            r0 = 65535(0xffff, float:9.1834E-41)
        Le:
            int r1 = r3.length
            int r4 = r0 + 1
            int r4 = java.lang.Math.min(r1, r4)
            com.flurry.org.codehaus.jackson.io.CharacterEscapes r5 = r11._characterEscapes
        L17:
            int r1 = r11._outputTail
            if (r1 >= r2) goto L5a
        L1b:
            char[] r1 = r11._outputBuffer
            int r6 = r11._outputTail
            char r6 = r1[r6]
            if (r6 >= r4) goto L44
            r1 = r3[r6]
            if (r1 == 0) goto L52
        L27:
            int r7 = r11._outputTail
            int r8 = r11._outputHead
            int r7 = r7 - r8
            if (r7 <= 0) goto L37
            java.io.Writer r8 = r11._writer
            char[] r9 = r11._outputBuffer
            int r10 = r11._outputHead
            r8.write(r9, r10, r7)
        L37:
            int r7 = r11._outputTail
            int r7 = r7 + 1
            r11._outputTail = r7
            r11._prependOrWriteCharacterEscape(r6, r1)
            goto L17
        L41:
            int r0 = r11._maximumNonEscapedChar
            goto Le
        L44:
            if (r6 <= r0) goto L48
            r1 = -1
            goto L27
        L48:
            com.flurry.org.codehaus.jackson.SerializableString r1 = r5.getEscapeSequence(r6)
            r11._currentEscape = r1
            if (r1 == 0) goto L52
            r1 = -2
            goto L27
        L52:
            int r1 = r11._outputTail
            int r1 = r1 + 1
            r11._outputTail = r1
            if (r1 < r2) goto L1b
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeStringCustom(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0022 A[EDGE_INSN: B:36:0x0022->B:12:0x0022 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void _writeStringCustom(char[] r13, int r14, int r15) {
        /*
            r12 = this;
            int r4 = r15 + r14
            int[] r5 = r12._outputEscapes
            int r0 = r12._maximumNonEscapedChar
            r1 = 1
            if (r0 >= r1) goto L43
            r0 = 65535(0xffff, float:9.1834E-41)
        Lc:
            int r1 = r5.length
            int r2 = r0 + 1
            int r6 = java.lang.Math.min(r1, r2)
            com.flurry.org.codehaus.jackson.io.CharacterEscapes r7 = r12._characterEscapes
            r1 = 0
            r3 = r14
        L17:
            if (r3 >= r4) goto L42
            r2 = r3
        L1a:
            char r8 = r13[r2]
            if (r8 >= r6) goto L46
            r1 = r5[r8]
            if (r1 == 0) goto L54
        L22:
            int r9 = r2 - r3
            r10 = 32
            if (r9 >= r10) goto L59
            int r10 = r12._outputTail
            int r10 = r10 + r9
            int r11 = r12._outputEnd
            if (r10 <= r11) goto L32
            r12._flushBuffer()
        L32:
            if (r9 <= 0) goto L40
            char[] r10 = r12._outputBuffer
            int r11 = r12._outputTail
            java.lang.System.arraycopy(r13, r3, r10, r11, r9)
            int r3 = r12._outputTail
            int r3 = r3 + r9
            r12._outputTail = r3
        L40:
            if (r2 < r4) goto L62
        L42:
            return
        L43:
            int r0 = r12._maximumNonEscapedChar
            goto Lc
        L46:
            if (r8 <= r0) goto L4a
            r1 = -1
            goto L22
        L4a:
            com.flurry.org.codehaus.jackson.SerializableString r9 = r7.getEscapeSequence(r8)
            r12._currentEscape = r9
            if (r9 == 0) goto L54
            r1 = -2
            goto L22
        L54:
            int r2 = r2 + 1
            if (r2 < r4) goto L1a
            goto L22
        L59:
            r12._flushBuffer()
            java.io.Writer r10 = r12._writer
            r10.write(r13, r3, r9)
            goto L40
        L62:
            int r3 = r2 + 1
            r12._appendCharacterEscape(r8, r1)
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator._writeStringCustom(char[], int, int):void");
    }

    private void writeRawLong(String str) {
        int i = this._outputEnd - this._outputTail;
        str.getChars(0, i, this._outputBuffer, this._outputTail);
        this._outputTail += i;
        _flushBuffer();
        int length = str.length() - i;
        while (length > this._outputEnd) {
            int i2 = this._outputEnd;
            str.getChars(i, i + i2, this._outputBuffer, 0);
            this._outputHead = 0;
            this._outputTail = i2;
            _flushBuffer();
            i += i2;
            length -= i2;
        }
        str.getChars(i, i + length, this._outputBuffer, 0);
        this._outputHead = 0;
        this._outputTail = length;
    }

    protected final void _flushBuffer() {
        int i = this._outputTail - this._outputHead;
        if (i > 0) {
            int i2 = this._outputHead;
            this._outputHead = 0;
            this._outputTail = 0;
            this._writer.write(this._outputBuffer, i2, i);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase
    protected void _releaseBuffers() {
        char[] cArr = this._outputBuffer;
        if (cArr != null) {
            this._outputBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    protected final void _verifyPrettyValueWrite(String str, int i) {
        switch (i) {
            case 0:
                if (this._writeContext.inArray()) {
                    this._cfgPrettyPrinter.beforeArrayValues(this);
                    return;
                } else if (this._writeContext.inObject()) {
                    this._cfgPrettyPrinter.beforeObjectEntries(this);
                    return;
                } else {
                    return;
                }
            case 1:
                this._cfgPrettyPrinter.writeArrayValueSeparator(this);
                return;
            case 2:
                this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
                return;
            case 3:
                this._cfgPrettyPrinter.writeRootValueSeparator(this);
                return;
            default:
                _cantHappen();
                return;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase
    protected final void _verifyValueWrite(String str) {
        char c;
        int writeValue = this._writeContext.writeValue();
        if (writeValue == 5) {
            _reportError("Can not " + str + ", expecting field name");
        }
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(str, writeValue);
            return;
        }
        switch (writeValue) {
            case 1:
                c = ',';
                break;
            case 2:
                c = ':';
                break;
            case 3:
                c = ' ';
                break;
            default:
                return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputBuffer[this._outputTail] = c;
        this._outputTail++;
    }

    protected void _writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        int i3 = i2 - 3;
        int i4 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i <= i3) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i5 = i + 1;
            int i6 = i5 + 1;
            i = i6 + 1;
            this._outputTail = base64Variant.encodeBase64Chunk((((bArr[i5] & Constants.UNKNOWN) | (bArr[i] << 8)) << 8) | (bArr[i6] & Constants.UNKNOWN), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this._outputBuffer;
                int i7 = this._outputTail;
                this._outputTail = i7 + 1;
                cArr[i7] = '\\';
                char[] cArr2 = this._outputBuffer;
                int i8 = this._outputTail;
                this._outputTail = i8 + 1;
                cArr2[i8] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        int i9 = i2 - i;
        if (i9 > 0) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i10 = i + 1;
            int i11 = bArr[i] << 16;
            if (i9 == 2) {
                int i12 = i10 + 1;
                i11 |= (bArr[i10] & Constants.UNKNOWN) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(i11, i9, this._outputBuffer, this._outputTail);
        }
    }

    public void _writeFieldName(SerializableString serializableString, boolean z) {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(serializableString, z);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z) {
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            cArr[i] = ',';
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
        int length = asQuotedChars.length;
        if (this._outputTail + length + 1 < this._outputEnd) {
            System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
            char[] cArr3 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            cArr3[i3] = '\"';
            return;
        }
        writeRaw(asQuotedChars, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr4 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr4[i4] = '\"';
    }

    protected void _writeFieldName(String str, boolean z) {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(str, z);
            return;
        }
        if (this._outputTail + 1 >= this._outputEnd) {
            _flushBuffer();
        }
        if (z) {
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            cArr[i] = ',';
        }
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            _writeString(str);
            return;
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr3[i3] = '\"';
    }

    protected final void _writePPFieldName(SerializableString serializableString, boolean z) {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    protected final void _writePPFieldName(String str, boolean z) {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
            _writeString(str);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonWriteContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        _flushBuffer();
        if (this._writer != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this._writer.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this._writer.flush();
            }
        }
        _releaseBuffers();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator
    public final void flush() {
        _flushBuffer();
        if (this._writer == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this._writer.flush();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public int getHighestEscapedChar() {
        return this._maximumNonEscapedChar;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public Object getOutputTarget() {
        return this._writer;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        if (characterEscapes == null) {
            this._outputEscapes = sOutputEscapes;
        } else {
            this._outputEscapes = characterEscapes.getEscapeCodesForAscii();
        }
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setHighestNonEscapedChar(int i) {
        if (i < 0) {
            i = 0;
        }
        this._maximumNonEscapedChar = i;
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        _verifyValueWrite("write binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr[i3] = '\"';
        _writeBinary(base64Variant, bArr, i, i + i2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr2[i4] = '\"';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) {
        int i;
        _verifyValueWrite("write boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        int i2 = this._outputTail;
        char[] cArr = this._outputBuffer;
        if (z) {
            cArr[i2] = 't';
            int i3 = i2 + 1;
            cArr[i3] = 'r';
            int i4 = i3 + 1;
            cArr[i4] = 'u';
            i = i4 + 1;
            cArr[i] = 'e';
        } else {
            cArr[i2] = 'f';
            int i5 = i2 + 1;
            cArr[i5] = 'a';
            int i6 = i5 + 1;
            cArr[i6] = 'l';
            int i7 = i6 + 1;
            cArr[i7] = 's';
            i = i7 + 1;
            cArr[i] = 'e';
        }
        this._outputTail = i + 1;
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeEndArray() {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            char[] cArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            cArr[i] = ']';
        }
        this._writeContext = this._writeContext.getParent();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeEndObject() {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
        }
        this._writeContext = this._writeContext.getParent();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '}';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(SerializableString serializableString) {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializableString, writeFieldName == 1);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(SerializedString serializedString) {
        int writeFieldName = this._writeContext.writeFieldName(serializedString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(serializedString, writeFieldName == 1);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeFieldName(String str) {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        _writeFieldName(str, writeFieldName == 1);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNull() {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) {
        if (this._cfgNumbersAsStrings || ((Double.isNaN(d) || Double.isInfinite(d)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(d));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(d));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) {
        if (this._cfgNumbersAsStrings || ((Float.isNaN(f) || Float.isInfinite(f)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(f));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(f));
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) {
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i);
            return;
        }
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) {
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) {
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        _verifyValueWrite("write number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigDecimal);
        } else {
            writeRaw(bigDecimal.toString());
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        _verifyValueWrite("write number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger);
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = c;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) {
        int length = str.length();
        int i = this._outputEnd - this._outputTail;
        if (i == 0) {
            _flushBuffer();
            i = this._outputEnd - this._outputTail;
        }
        if (i < length) {
            writeRawLong(str);
            return;
        }
        str.getChars(0, length, this._outputBuffer, this._outputTail);
        this._outputTail += length;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) {
        int i3 = this._outputEnd - this._outputTail;
        if (i3 < i2) {
            _flushBuffer();
            i3 = this._outputEnd - this._outputTail;
        }
        if (i3 < i2) {
            writeRawLong(str.substring(i, i + i2));
            return;
        }
        str.getChars(i, i + i2, this._outputBuffer, this._outputTail);
        this._outputTail += i2;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) {
        if (i2 >= 32) {
            _flushBuffer();
            this._writer.write(cArr, i, i2);
            return;
        }
        if (i2 > this._outputEnd - this._outputTail) {
            _flushBuffer();
        }
        System.arraycopy(cArr, i, this._outputBuffer, this._outputTail, i2);
        this._outputTail += i2;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) {
        _reportUnsupportedOperation();
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeStartArray() {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '[';
    }

    @Override // com.flurry.org.codehaus.jackson.impl.JsonGeneratorBase, com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeStartObject() {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '{';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeString(SerializableString serializableString) {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        char[] asQuotedChars = serializableString.asQuotedChars();
        int length = asQuotedChars.length;
        if (length < 32) {
            if (length > this._outputEnd - this._outputTail) {
                _flushBuffer();
            }
            System.arraycopy(asQuotedChars, 0, this._outputBuffer, this._outputTail, length);
            this._outputTail += length;
        } else {
            _flushBuffer();
            this._writer.write(asQuotedChars, 0, length);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(String str) {
        _verifyValueWrite("write text value");
        if (str == null) {
            _writeNull();
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        cArr[i] = '\"';
        _writeString(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        cArr2[i3] = '\"';
        _writeString(cArr, i, i2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        char[] cArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        cArr3[i4] = '\"';
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public final void writeStringField(String str, String str2) {
        writeFieldName(str);
        writeString(str2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) {
        _reportUnsupportedOperation();
    }
}
