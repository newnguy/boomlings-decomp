package com.flurry.org.codehaus.jackson.impl;

import com.flurry.android.Constants;
import com.flurry.org.codehaus.jackson.JsonEncoding;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.format.InputAccessor;
import com.flurry.org.codehaus.jackson.format.MatchStrength;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.MergedStream;
import com.flurry.org.codehaus.jackson.io.UTF32Reader;
import com.flurry.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: classes.dex */
public final class ByteSourceBootstrapper {
    static final byte UTF8_BOM_1 = -17;
    static final byte UTF8_BOM_2 = -69;
    static final byte UTF8_BOM_3 = -65;
    protected boolean _bigEndian;
    private final boolean _bufferRecyclable;
    protected int _bytesPerChar;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    private int _inputEnd;
    protected int _inputProcessed;
    private int _inputPtr;

    public ByteSourceBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceBootstrapper(IOContext iOContext, byte[] bArr, int i, int i2) {
        this._bigEndian = true;
        this._bytesPerChar = 0;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i + i2;
        this._inputProcessed = -i;
        this._bufferRecyclable = false;
    }

    private boolean checkUTF16(int i) {
        if ((65280 & i) == 0) {
            this._bigEndian = true;
        } else if ((i & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private boolean checkUTF32(int i) {
        if ((i >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i) == 0) {
            this._bigEndian = false;
        } else if (((-16711681) & i) == 0) {
            reportWeirdUCS4("3412");
        } else if (((-65281) & i) != 0) {
            return false;
        } else {
            reportWeirdUCS4("2143");
        }
        this._bytesPerChar = 4;
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean handleBOM(int i) {
        switch (i) {
            case -16842752:
                reportWeirdUCS4("3412");
                break;
            case -131072:
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            case 65279:
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            case 65534:
                reportWeirdUCS4("2143");
                reportWeirdUCS4("3412");
                break;
        }
        int i2 = i >>> 16;
        if (i2 == 65279) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = true;
            return true;
        } else if (i2 == 65534) {
            this._inputPtr += 2;
            this._bytesPerChar = 2;
            this._bigEndian = false;
            return true;
        } else if ((i >>> 8) == 15711167) {
            this._inputPtr += 3;
            this._bytesPerChar = 1;
            this._bigEndian = true;
            return true;
        } else {
            return false;
        }
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) {
        if (inputAccessor.hasMoreBytes()) {
            byte nextByte = inputAccessor.nextByte();
            if (nextByte == -17) {
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputAccessor.nextByte() != -69) {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputAccessor.nextByte() != -65) {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                nextByte = inputAccessor.nextByte();
            }
            int skipSpace = skipSpace(inputAccessor, nextByte);
            if (skipSpace < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace == 123) {
                int skipSpace2 = skipSpace(inputAccessor);
                return skipSpace2 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace2 == 34 || skipSpace2 == 125) ? MatchStrength.SOLID_MATCH : MatchStrength.NO_MATCH;
            } else if (skipSpace == 91) {
                int skipSpace3 = skipSpace(inputAccessor);
                return skipSpace3 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace3 == 93 || skipSpace3 == 91) ? MatchStrength.SOLID_MATCH : MatchStrength.SOLID_MATCH;
            } else {
                MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
                if (skipSpace != 34) {
                    if (skipSpace > 57 || skipSpace < 48) {
                        if (skipSpace != 45) {
                            return skipSpace == 110 ? tryMatch(inputAccessor, "ull", matchStrength) : skipSpace == 116 ? tryMatch(inputAccessor, "rue", matchStrength) : skipSpace == 102 ? tryMatch(inputAccessor, "alse", matchStrength) : MatchStrength.NO_MATCH;
                        }
                        int skipSpace4 = skipSpace(inputAccessor);
                        return skipSpace4 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
                    }
                    return matchStrength;
                }
                return matchStrength;
            }
        }
        return MatchStrength.INCONCLUSIVE;
    }

    private void reportWeirdUCS4(String str) {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    private static final int skipSpace(InputAccessor inputAccessor) {
        if (inputAccessor.hasMoreBytes()) {
            return skipSpace(inputAccessor, inputAccessor.nextByte());
        }
        return -1;
    }

    private static final int skipSpace(InputAccessor inputAccessor, byte b) {
        while (true) {
            int i = b & Constants.UNKNOWN;
            if (i != 32 && i != 13 && i != 10 && i != 9) {
                return i;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b = inputAccessor.nextByte();
            int i2 = b & Constants.UNKNOWN;
        }
    }

    private static final MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    public JsonParser constructParser(int i, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        JsonEncoding detectEncoding = detectEncoding();
        boolean enabledIn = JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i);
        boolean enabledIn2 = JsonParser.Feature.INTERN_FIELD_NAMES.enabledIn(i);
        if (detectEncoding == JsonEncoding.UTF8 && enabledIn) {
            return new Utf8StreamParser(this._context, i, this._in, objectCodec, bytesToNameCanonicalizer.makeChild(enabledIn, enabledIn2), this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
        }
        return new ReaderBasedParser(this._context, i, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(enabledIn, enabledIn2));
    }

    public Reader constructReader() {
        JsonEncoding encoding = this._context.getEncoding();
        switch (encoding) {
            case UTF32_BE:
            case UTF32_LE:
                return new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
            case UTF16_BE:
            case UTF16_LE:
            case UTF8:
                InputStream inputStream = this._in;
                return new InputStreamReader(inputStream == null ? new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd) : this._inputPtr < this._inputEnd ? new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd) : inputStream, encoding.getJavaName());
            default:
                throw new RuntimeException("Internal error");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0052, code lost:
        if (checkUTF16(r2 >>> 16) != false) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0076, code lost:
        if (checkUTF16(((r5._inputBuffer[r5._inputPtr] & com.flurry.android.Constants.UNKNOWN) << 8) | (r5._inputBuffer[r5._inputPtr + 1] & com.flurry.android.Constants.UNKNOWN)) != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.flurry.org.codehaus.jackson.JsonEncoding detectEncoding() {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            r2 = 4
            boolean r2 = r5.ensureLoaded(r2)
            if (r2 == 0) goto L56
            byte[] r2 = r5._inputBuffer
            int r3 = r5._inputPtr
            r2 = r2[r3]
            int r2 = r2 << 24
            byte[] r3 = r5._inputBuffer
            int r4 = r5._inputPtr
            int r4 = r4 + 1
            r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 16
            r2 = r2 | r3
            byte[] r3 = r5._inputBuffer
            int r4 = r5._inputPtr
            int r4 = r4 + 2
            r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r2 = r2 | r3
            byte[] r3 = r5._inputBuffer
            int r4 = r5._inputPtr
            int r4 = r4 + 3
            r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = r2 | r3
            boolean r3 = r5.handleBOM(r2)
            if (r3 == 0) goto L46
        L3c:
            if (r0 != 0) goto L79
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF8
        L40:
            com.flurry.org.codehaus.jackson.io.IOContext r1 = r5._context
            r1.setEncoding(r0)
            return r0
        L46:
            boolean r3 = r5.checkUTF32(r2)
            if (r3 != 0) goto L3c
            int r2 = r2 >>> 16
            boolean r2 = r5.checkUTF16(r2)
            if (r2 != 0) goto L3c
        L54:
            r0 = r1
            goto L3c
        L56:
            r2 = 2
            boolean r2 = r5.ensureLoaded(r2)
            if (r2 == 0) goto L54
            byte[] r2 = r5._inputBuffer
            int r3 = r5._inputPtr
            r2 = r2[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            byte[] r3 = r5._inputBuffer
            int r4 = r5._inputPtr
            int r4 = r4 + 1
            r3 = r3[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = r2 | r3
            boolean r2 = r5.checkUTF16(r2)
            if (r2 == 0) goto L54
            goto L3c
        L79:
            int r0 = r5._bytesPerChar
            switch(r0) {
                case 1: goto L86;
                case 2: goto L89;
                case 3: goto L7e;
                case 4: goto L93;
                default: goto L7e;
            }
        L7e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Internal error"
            r0.<init>(r1)
            throw r0
        L86:
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF8
            goto L40
        L89:
            boolean r0 = r5._bigEndian
            if (r0 == 0) goto L90
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF16_BE
            goto L40
        L90:
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF16_LE
            goto L40
        L93:
            boolean r0 = r5._bigEndian
            if (r0 == 0) goto L9a
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF32_BE
            goto L40
        L9a:
            com.flurry.org.codehaus.jackson.JsonEncoding r0 = com.flurry.org.codehaus.jackson.JsonEncoding.UTF32_LE
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.ByteSourceBootstrapper.detectEncoding():com.flurry.org.codehaus.jackson.JsonEncoding");
    }

    protected boolean ensureLoaded(int i) {
        int i2 = this._inputEnd - this._inputPtr;
        while (i2 < i) {
            int read = this._in == null ? -1 : this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i2 = read + i2;
        }
        return true;
    }
}
