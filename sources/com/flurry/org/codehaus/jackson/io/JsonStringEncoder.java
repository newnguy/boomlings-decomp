package com.flurry.org.codehaus.jackson.io;

import com.flurry.org.codehaus.jackson.util.BufferRecycler;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import com.flurry.org.codehaus.jackson.util.TextBuffer;
import java.lang.ref.SoftReference;

/* loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    protected static final ThreadLocal _threadEncoder = new ThreadLocal();

    public JsonStringEncoder() {
        this._quoteBuffer[0] = '\\';
        this._quoteBuffer[2] = '0';
        this._quoteBuffer[3] = '0';
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(INT_BACKSLASH);
        if (i2 < 0) {
            byteArrayBuilder.append(INT_U);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(INT_0);
                byteArrayBuilder.append(INT_0);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendSingleEscape(int i, char[] cArr) {
        if (i >= 0) {
            cArr[1] = (char) i;
            return 2;
        }
        int i2 = -(i + 1);
        cArr[1] = 'u';
        cArr[4] = HEX_CHARS[i2 >> 4];
        cArr[5] = HEX_CHARS[i2 & 15];
        return 6;
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 < SURR2_FIRST || i2 > SURR2_LAST) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
        }
        return 65536 + ((i - SURR1_FIRST) << 10) + (i2 - SURR2_FIRST);
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        }
        if (i < SURR1_FIRST) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        }
        if (i > SURR1_LAST) {
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
        throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
    }

    public static JsonStringEncoder getInstance() {
        SoftReference softReference = (SoftReference) _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : (JsonStringEncoder) softReference.get();
        if (jsonStringEncoder == null) {
            JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference(jsonStringEncoder2));
            return jsonStringEncoder2;
        }
        return jsonStringEncoder;
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        int i2;
        int i3;
        char c;
        int i4;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        int i5 = 0;
        int i6 = 0;
        loop0: while (true) {
            if (i6 >= length) {
                i = i5;
                break;
            }
            int i7 = i6 + 1;
            char charAt = str.charAt(i6);
            int i8 = length2;
            byte[] bArr = resetAndGetFirstSegment;
            int i9 = i5;
            int i10 = i8;
            while (charAt <= 127) {
                if (i9 >= i10) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i10 = bArr.length;
                    i9 = 0;
                }
                int i11 = i9 + 1;
                bArr[i9] = (byte) charAt;
                if (i7 >= length) {
                    i = i11;
                    break loop0;
                }
                charAt = str.charAt(i7);
                i7++;
                i9 = i11;
            }
            if (i9 >= i10) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i10 = bArr.length;
                i2 = 0;
            } else {
                i2 = i9;
            }
            if (charAt < 2048) {
                i3 = i2 + 1;
                bArr[i2] = (byte) ((charAt >> 6) | 192);
                c = charAt;
                i6 = i7;
            } else if (charAt < SURR1_FIRST || charAt > SURR2_LAST) {
                int i12 = i2 + 1;
                bArr[i2] = (byte) ((charAt >> '\f') | 224);
                if (i12 >= i10) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i10 = bArr.length;
                    i12 = 0;
                }
                bArr[i12] = (byte) (((charAt >> 6) & 63) | 128);
                i3 = i12 + 1;
                c = charAt;
                i6 = i7;
            } else {
                if (charAt > SURR1_LAST) {
                    _throwIllegalSurrogate(charAt);
                }
                if (i7 >= length) {
                    _throwIllegalSurrogate(charAt);
                }
                int i13 = i7 + 1;
                int _convertSurrogate = _convertSurrogate(charAt, str.charAt(i7));
                if (_convertSurrogate > 1114111) {
                    _throwIllegalSurrogate(_convertSurrogate);
                }
                int i14 = i2 + 1;
                bArr[i2] = (byte) ((_convertSurrogate >> 18) | 240);
                if (i14 >= i10) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i10 = bArr.length;
                    i14 = 0;
                }
                int i15 = i14 + 1;
                bArr[i14] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                if (i15 >= i10) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i10 = bArr.length;
                    i4 = 0;
                } else {
                    i4 = i15;
                }
                bArr[i4] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                i3 = i4 + 1;
                c = _convertSurrogate;
                i6 = i13;
            }
            if (i3 >= i10) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i10 = bArr.length;
                i3 = 0;
            }
            int i16 = i3 + 1;
            bArr[i3] = (byte) ((c & '?') | 128);
            resetAndGetFirstSegment = bArr;
            length2 = i10;
            i5 = i16;
        }
        return this._byteBuilder.completeAndCoalesce(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r4 = r2 + 1;
        r2 = _appendSingleEscape(r6[r12.charAt(r2)], r11._quoteBuffer);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
        if ((r1 + r2) <= r3.length) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003b, code lost:
        r9 = r3.length - r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:
        if (r9 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003f, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r3, r1, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        r3 = r0.finishCurrentSegment();
        r2 = r2 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r3, r1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
        r1 = r1 + r2;
        r2 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r3, r1, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            r5 = 0
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = r11._textBuffer
            if (r0 != 0) goto Ld
            com.flurry.org.codehaus.jackson.util.TextBuffer r0 = new com.flurry.org.codehaus.jackson.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._textBuffer = r0
        Ld:
            char[] r3 = r0.emptyAndGetCurrentSegment()
            int[] r6 = com.flurry.org.codehaus.jackson.util.CharTypes.get7BitOutputEscapes()
            int r7 = r6.length
            int r8 = r12.length()
            r1 = r5
            r2 = r5
        L1c:
            if (r2 >= r8) goto L61
        L1e:
            char r9 = r12.charAt(r2)
            if (r9 >= r7) goto L51
            r4 = r6[r9]
            if (r4 == 0) goto L51
            int r4 = r2 + 1
            char r2 = r12.charAt(r2)
            r2 = r6[r2]
            char[] r9 = r11._quoteBuffer
            int r2 = r11._appendSingleEscape(r2, r9)
            int r9 = r1 + r2
            int r10 = r3.length
            if (r9 <= r10) goto L69
            int r9 = r3.length
            int r9 = r9 - r1
            if (r9 <= 0) goto L44
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r3, r1, r9)
        L44:
            char[] r3 = r0.finishCurrentSegment()
            int r2 = r2 - r9
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r9, r3, r1, r2)
            int r1 = r1 + r2
        L4f:
            r2 = r4
            goto L1c
        L51:
            int r4 = r3.length
            if (r1 < r4) goto L70
            char[] r3 = r0.finishCurrentSegment()
            r4 = r5
        L59:
            int r1 = r4 + 1
            r3[r4] = r9
            int r2 = r2 + 1
            if (r2 < r8) goto L1e
        L61:
            r0.setCurrentLength(r1)
            char[] r0 = r0.contentsAsArray()
            return r0
        L69:
            char[] r9 = r11._quoteBuffer
            java.lang.System.arraycopy(r9, r5, r3, r1, r2)
            int r1 = r1 + r2
            goto L4f
        L70:
            r4 = r1
            goto L59
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        byte[] bArr;
        int i3;
        int i4;
        int i5;
        int i6;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int i7 = 0;
        int i8 = 0;
        loop0: while (i8 < length) {
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i8);
                if (charAt <= 127 && iArr[charAt] == 0) {
                    if (i7 >= resetAndGetFirstSegment.length) {
                        resetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i6 = 0;
                    } else {
                        i6 = i7;
                    }
                    i7 = i6 + 1;
                    resetAndGetFirstSegment[i6] = (byte) charAt;
                    i8++;
                    if (i8 >= length) {
                        break loop0;
                    }
                } else {
                    break;
                }
            }
            if (i7 >= resetAndGetFirstSegment.length) {
                resetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                i7 = 0;
            }
            int i9 = i8 + 1;
            char charAt2 = str.charAt(i8);
            if (charAt2 <= 127) {
                i7 = _appendByteEscape(charAt2, iArr[charAt2], byteArrayBuilder, i7);
                resetAndGetFirstSegment = byteArrayBuilder.getCurrentSegment();
                i8 = i9;
            } else {
                if (charAt2 <= 2047) {
                    i2 = i7 + 1;
                    resetAndGetFirstSegment[i7] = (byte) ((charAt2 >> 6) | 192);
                    bArr = resetAndGetFirstSegment;
                    i3 = (charAt2 & '?') | 128;
                } else if (charAt2 < SURR1_FIRST || charAt2 > SURR2_LAST) {
                    int i10 = i7 + 1;
                    resetAndGetFirstSegment[i7] = (byte) ((charAt2 >> '\f') | 224);
                    if (i10 >= resetAndGetFirstSegment.length) {
                        resetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i = 0;
                    } else {
                        i = i10;
                    }
                    i2 = i + 1;
                    resetAndGetFirstSegment[i] = (byte) (((charAt2 >> 6) & 63) | 128);
                    bArr = resetAndGetFirstSegment;
                    i3 = (charAt2 & '?') | 128;
                } else {
                    if (charAt2 > SURR1_LAST) {
                        _throwIllegalSurrogate(charAt2);
                    }
                    if (i9 >= length) {
                        _throwIllegalSurrogate(charAt2);
                    }
                    int i11 = i9 + 1;
                    int _convertSurrogate = _convertSurrogate(charAt2, str.charAt(i9));
                    if (_convertSurrogate > 1114111) {
                        _throwIllegalSurrogate(_convertSurrogate);
                    }
                    int i12 = i7 + 1;
                    resetAndGetFirstSegment[i7] = (byte) ((_convertSurrogate >> 18) | 240);
                    if (i12 >= resetAndGetFirstSegment.length) {
                        resetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i4 = 0;
                    } else {
                        i4 = i12;
                    }
                    int i13 = i4 + 1;
                    resetAndGetFirstSegment[i4] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                    if (i13 >= resetAndGetFirstSegment.length) {
                        resetAndGetFirstSegment = byteArrayBuilder.finishCurrentSegment();
                        i5 = 0;
                    } else {
                        i5 = i13;
                    }
                    i2 = i5 + 1;
                    resetAndGetFirstSegment[i5] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                    i9 = i11;
                    byte[] bArr2 = resetAndGetFirstSegment;
                    i3 = (_convertSurrogate & 63) | 128;
                    bArr = bArr2;
                }
                if (i2 >= bArr.length) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i2 = 0;
                }
                int i14 = i2 + 1;
                bArr[i2] = (byte) i3;
                resetAndGetFirstSegment = bArr;
                i8 = i9;
                i7 = i14;
            }
        }
        return this._byteBuilder.completeAndCoalesce(i7);
    }
}
