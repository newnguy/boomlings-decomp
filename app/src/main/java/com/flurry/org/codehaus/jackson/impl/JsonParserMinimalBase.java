package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;

/* loaded from: classes.dex */
public abstract class JsonParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;

    public JsonParserMinimalBase() {
    }

    public JsonParserMinimalBase(int i) {
        super(i);
    }

    public static final String _getCharDesc(int i) {
        char c = (char) i;
        return Character.isISOControl(c) ? "(CTRL-CHAR, code " + i + ")" : i > 255 ? "'" + c + "' (code " + i + " / 0x" + Integer.toHexString(i) + ")" : "'" + c + "' (code " + i + ")";
    }

    protected final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x00a7, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x00aa, code lost:
        r0 = r4 + 1;
        r4 = r11.charAt(r4);
        r5 = r13.decodeBase64Char(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00b4, code lost:
        if (r5 >= 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x00b6, code lost:
        if (r5 == (-2)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00b8, code lost:
        _reportInvalidBase64(r13, r4, 3, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x00bb, code lost:
        r12.appendTwoBytes(r1 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00c2, code lost:
        r12.appendThreeBytes((r1 << 6) | r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0013, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0018, code lost:
        r4 = r13.decodeBase64Char(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x001c, code lost:
        if (r4 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x001e, code lost:
        _reportInvalidBase64(r13, r0, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0021, code lost:
        if (r1 < r3) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0023, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0026, code lost:
        r0 = r1 + 1;
        r1 = r11.charAt(r1);
        r5 = r13.decodeBase64Char(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0030, code lost:
        if (r5 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0032, code lost:
        _reportInvalidBase64(r13, r1, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0036, code lost:
        r1 = (r4 << 6) | r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0039, code lost:
        if (r0 < r3) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x003f, code lost:
        if (r13.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0041, code lost:
        r12.append(r1 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0047, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x004a, code lost:
        r4 = r0 + 1;
        r0 = r11.charAt(r0);
        r5 = r13.decodeBase64Char(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0054, code lost:
        if (r5 >= 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0056, code lost:
        if (r5 == (-2)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0058, code lost:
        _reportInvalidBase64(r13, r0, 2, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x005c, code lost:
        if (r4 < r3) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x005e, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0061, code lost:
        r0 = r4 + 1;
        r4 = r11.charAt(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x006b, code lost:
        if (r13.usesPaddingChar(r4) != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x006d, code lost:
        _reportInvalidBase64(r13, r4, 3, "expected padding character '" + r13.getPaddingChar() + "'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x008d, code lost:
        r12.append(r1 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0094, code lost:
        r1 = (r1 << 6) | r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0098, code lost:
        if (r4 < r3) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x009e, code lost:
        if (r13.usesPadding() != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00a0, code lost:
        r12.appendTwoBytes(r1 >> 2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void _decodeBase64(java.lang.String r11, com.flurry.org.codehaus.jackson.util.ByteArrayBuilder r12, com.flurry.org.codehaus.jackson.Base64Variant r13) {
        /*
            r10 = this;
            r9 = 3
            r2 = 0
            r8 = -2
            r7 = 0
            int r3 = r11.length()
            r0 = r2
        L9:
            if (r0 >= r3) goto L13
        Lb:
            int r1 = r0 + 1
            char r0 = r11.charAt(r0)
            if (r1 < r3) goto L14
        L13:
            return
        L14:
            r4 = 32
            if (r0 <= r4) goto Lca
            int r4 = r13.decodeBase64Char(r0)
            if (r4 >= 0) goto L21
            r10._reportInvalidBase64(r13, r0, r2, r7)
        L21:
            if (r1 < r3) goto L26
            r10._reportBase64EOF()
        L26:
            int r0 = r1 + 1
            char r1 = r11.charAt(r1)
            int r5 = r13.decodeBase64Char(r1)
            if (r5 >= 0) goto L36
            r6 = 1
            r10._reportInvalidBase64(r13, r1, r6, r7)
        L36:
            int r1 = r4 << 6
            r1 = r1 | r5
            if (r0 < r3) goto L4a
            boolean r4 = r13.usesPadding()
            if (r4 != 0) goto L47
            int r0 = r1 >> 4
            r12.append(r0)
            goto L13
        L47:
            r10._reportBase64EOF()
        L4a:
            int r4 = r0 + 1
            char r0 = r11.charAt(r0)
            int r5 = r13.decodeBase64Char(r0)
            if (r5 >= 0) goto L94
            if (r5 == r8) goto L5c
            r5 = 2
            r10._reportInvalidBase64(r13, r0, r5, r7)
        L5c:
            if (r4 < r3) goto L61
            r10._reportBase64EOF()
        L61:
            int r0 = r4 + 1
            char r4 = r11.charAt(r4)
            boolean r5 = r13.usesPaddingChar(r4)
            if (r5 != 0) goto L8d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "expected padding character '"
            java.lang.StringBuilder r5 = r5.append(r6)
            char r6 = r13.getPaddingChar()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "'"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r10._reportInvalidBase64(r13, r4, r9, r5)
        L8d:
            int r1 = r1 >> 4
            r12.append(r1)
            goto L9
        L94:
            int r0 = r1 << 6
            r1 = r0 | r5
            if (r4 < r3) goto Laa
            boolean r0 = r13.usesPadding()
            if (r0 != 0) goto La7
            int r0 = r1 >> 2
            r12.appendTwoBytes(r0)
            goto L13
        La7:
            r10._reportBase64EOF()
        Laa:
            int r0 = r4 + 1
            char r4 = r11.charAt(r4)
            int r5 = r13.decodeBase64Char(r4)
            if (r5 >= 0) goto Lc2
            if (r5 == r8) goto Lbb
            r10._reportInvalidBase64(r13, r4, r9, r7)
        Lbb:
            int r1 = r1 >> 2
            r12.appendTwoBytes(r1)
            goto L9
        Lc2:
            int r1 = r1 << 6
            r1 = r1 | r5
            r12.appendThreeBytes(r1)
            goto L9
        Lca:
            r0 = r1
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase._decodeBase64(java.lang.String, com.flurry.org.codehaus.jackson.util.ByteArrayBuilder, com.flurry.org.codehaus.jackson.Base64Variant):void");
    }

    protected abstract void _handleEOF();

    public char _handleUnrecognizedCharacterEscape(char c) {
        if (!isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (c != INT_APOSTROPHE || !isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
            _reportError("Unrecognized character escape " + _getCharDesc(c));
        }
        return c;
    }

    protected void _reportBase64EOF() {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    public final void _reportError(String str) {
        throw _constructError(str);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) {
        String str2 = c <= ' ' ? "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units" : base64Variant.usesPaddingChar(c) ? "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character" : (!Character.isDefined(c) || Character.isISOControl(c)) ? "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content" : "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw _constructError(str2);
    }

    public void _reportInvalidEOF() {
        _reportInvalidEOF(" in " + this._currToken);
    }

    public void _reportInvalidEOF(String str) {
        _reportError("Unexpected end-of-input" + str);
    }

    public void _reportInvalidEOFInValue() {
        _reportInvalidEOF(" in a value");
    }

    public void _reportUnexpectedChar(int i, String str) {
        String str2 = "Unexpected character (" + _getCharDesc(i) + ")";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        _reportError(str2);
    }

    public final void _throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    public void _throwInvalidSpace(int i) {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    public void _throwUnquotedSpace(int i, String str) {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    public final void _wrapError(String str, Throwable th) {
        throw _constructError(str, th);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract String getCurrentName();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract JsonStreamContext getParsingContext();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract String getText();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract char[] getTextCharacters();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract int getTextLength();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract int getTextOffset();

    /* JADX WARN: Removed duplicated region for block: B:41:0x003e  */
    @Override // com.flurry.org.codehaus.jackson.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean getValueAsBoolean(boolean r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            com.flurry.org.codehaus.jackson.JsonToken r2 = r4._currToken
            if (r2 == 0) goto L13
            int[] r2 = com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase.AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken
            com.flurry.org.codehaus.jackson.JsonToken r3 = r4._currToken
            int r3 = r3.ordinal()
            r2 = r2[r3]
            switch(r2) {
                case 5: goto L15;
                case 6: goto L1d;
                case 7: goto L14;
                case 8: goto L14;
                case 9: goto L1f;
                case 10: goto L2e;
                default: goto L13;
            }
        L13:
            r0 = r5
        L14:
            return r0
        L15:
            int r2 = r4.getIntValue()
            if (r2 == 0) goto L14
            r0 = r1
            goto L14
        L1d:
            r0 = r1
            goto L14
        L1f:
            java.lang.Object r0 = r4.getEmbeddedObject()
            boolean r2 = r0 instanceof java.lang.Boolean
            if (r2 == 0) goto L2e
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L14
        L2e:
            java.lang.String r0 = r4.getText()
            java.lang.String r0 = r0.trim()
            java.lang.String r2 = "true"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L13
            r0 = r1
            goto L14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase.getValueAsBoolean(boolean):boolean");
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public double getValueAsDouble(double d) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case 11:
                    return getDoubleValue();
                case 6:
                    return 1.0d;
                case 7:
                case 8:
                    return 0.0d;
                case INT_TAB /* 9 */:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d;
                case INT_LF /* 10 */:
                    return NumberInput.parseAsDouble(getText(), d);
                default:
                    return d;
            }
        }
        return d;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getValueAsInt(int i) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case 11:
                    return getIntValue();
                case 6:
                    return 1;
                case 7:
                case 8:
                    return 0;
                case INT_TAB /* 9 */:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i;
                case INT_LF /* 10 */:
                    return NumberInput.parseAsInt(getText(), i);
                default:
                    return i;
            }
        }
        return i;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public long getValueAsLong(long j) {
        if (this._currToken != null) {
            switch (AnonymousClass1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
                case 5:
                case 11:
                    return getLongValue();
                case 6:
                    return 1L;
                case 7:
                case 8:
                    return 0L;
                case INT_TAB /* 9 */:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j;
                case INT_LF /* 10 */:
                    return NumberInput.parseAsLong(getText(), j);
                default:
                    return j;
            }
        }
        return j;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract boolean isClosed();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public abstract JsonToken nextToken();

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken != null) {
                    switch (nextToken) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                                break;
                            } else {
                                break;
                            }
                    }
                } else {
                    _handleEOF();
                }
            }
        }
        return this;
    }
}
