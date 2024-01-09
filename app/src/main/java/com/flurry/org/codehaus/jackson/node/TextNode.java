package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.util.CharTypes;

/* loaded from: classes.dex */
public final class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    static final int INT_SPACE = 32;
    final String _value;

    public TextNode(String str) {
        this._value = str;
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, str);
        sb.append('\"');
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        return str.length() == 0 ? EMPTY_STRING_NODE : new TextNode(str);
    }

    protected void _reportBase64EOF() {
        throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.NA);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i) {
        _reportInvalidBase64(base64Variant, c, i, null);
    }

    protected void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) {
        String str2 = c <= ' ' ? "Illegal white space character (code 0x" + Integer.toHexString(c) + ") as character #" + (i + 1) + " of 4-char base64 unit: can only used between units" : base64Variant.usesPaddingChar(c) ? "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character" : (!Character.isDefined(c) || Character.isISOControl(c)) ? "Illegal character (code 0x" + Integer.toHexString(c) + ") in base64 content" : "Illegal character '" + c + "' (code 0x" + Integer.toHexString(c) + ") in base64 content";
        if (str != null) {
            str2 = str2 + ": " + str;
        }
        throw new JsonParseException(str2, JsonLocation.NA);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        if (this._value == null || !"true".equals(this._value.trim())) {
            return z;
        }
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return NumberInput.parseAsDouble(this._value, d);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return NumberInput.parseAsInt(this._value, i);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return NumberInput.parseAsLong(this._value, j);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((TextNode) obj)._value.equals(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x00b6, code lost:
        r0 = r6 + 1;
        r6 = r4.charAt(r6);
        r7 = r12.decodeBase64Char(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x00c0, code lost:
        if (r7 >= 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x00c2, code lost:
        if (r7 == (-2)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x00c4, code lost:
        _reportInvalidBase64(r12, r6, 3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00c7, code lost:
        r3.appendTwoBytes(r1 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x00ce, code lost:
        r3.appendThreeBytes((r1 << 6) | r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0024, code lost:
        r6 = r12.decodeBase64Char(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0028, code lost:
        if (r6 >= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x002a, code lost:
        _reportInvalidBase64(r12, r0, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x002d, code lost:
        if (r1 < r5) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x002f, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0032, code lost:
        r0 = r1 + 1;
        r1 = r4.charAt(r1);
        r7 = r12.decodeBase64Char(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x003c, code lost:
        if (r7 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x003e, code lost:
        _reportInvalidBase64(r12, r1, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0042, code lost:
        r1 = (r6 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0045, code lost:
        if (r0 < r5) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x004b, code lost:
        if (r12.usesPadding() != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x004d, code lost:
        r3.append(r1 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0053, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0056, code lost:
        r6 = r0 + 1;
        r0 = r4.charAt(r0);
        r7 = r12.decodeBase64Char(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0060, code lost:
        if (r7 >= 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0062, code lost:
        if (r7 == (-2)) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0064, code lost:
        _reportInvalidBase64(r12, r0, 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0068, code lost:
        if (r6 < r5) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x006a, code lost:
        _reportBase64EOF();
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x006d, code lost:
        r0 = r6 + 1;
        r6 = r4.charAt(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0077, code lost:
        if (r12.usesPaddingChar(r6) != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0079, code lost:
        _reportInvalidBase64(r12, r6, 3, "expected padding character '" + r12.getPaddingChar() + "'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0099, code lost:
        r3.append(r1 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00a0, code lost:
        r1 = (r1 << 6) | r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00a4, code lost:
        if (r6 < r5) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x00aa, code lost:
        if (r12.usesPadding() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x00ac, code lost:
        r3.appendTwoBytes(r1 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00b3, code lost:
        _reportBase64EOF();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getBinaryValue(com.flurry.org.codehaus.jackson.Base64Variant r12) {
        /*
            r11 = this;
            r10 = 3
            r2 = 0
            r9 = -2
            com.flurry.org.codehaus.jackson.util.ByteArrayBuilder r3 = new com.flurry.org.codehaus.jackson.util.ByteArrayBuilder
            r0 = 100
            r3.<init>(r0)
            java.lang.String r4 = r11._value
            int r5 = r4.length()
            r0 = r2
        L11:
            if (r0 >= r5) goto L1b
        L13:
            int r1 = r0 + 1
            char r0 = r4.charAt(r0)
            if (r1 < r5) goto L20
        L1b:
            byte[] r0 = r3.toByteArray()
            return r0
        L20:
            r6 = 32
            if (r0 <= r6) goto Ld6
            int r6 = r12.decodeBase64Char(r0)
            if (r6 >= 0) goto L2d
            r11._reportInvalidBase64(r12, r0, r2)
        L2d:
            if (r1 < r5) goto L32
            r11._reportBase64EOF()
        L32:
            int r0 = r1 + 1
            char r1 = r4.charAt(r1)
            int r7 = r12.decodeBase64Char(r1)
            if (r7 >= 0) goto L42
            r8 = 1
            r11._reportInvalidBase64(r12, r1, r8)
        L42:
            int r1 = r6 << 6
            r1 = r1 | r7
            if (r0 < r5) goto L56
            boolean r6 = r12.usesPadding()
            if (r6 != 0) goto L53
            int r0 = r1 >> 4
            r3.append(r0)
            goto L1b
        L53:
            r11._reportBase64EOF()
        L56:
            int r6 = r0 + 1
            char r0 = r4.charAt(r0)
            int r7 = r12.decodeBase64Char(r0)
            if (r7 >= 0) goto La0
            if (r7 == r9) goto L68
            r7 = 2
            r11._reportInvalidBase64(r12, r0, r7)
        L68:
            if (r6 < r5) goto L6d
            r11._reportBase64EOF()
        L6d:
            int r0 = r6 + 1
            char r6 = r4.charAt(r6)
            boolean r7 = r12.usesPaddingChar(r6)
            if (r7 != 0) goto L99
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "expected padding character '"
            java.lang.StringBuilder r7 = r7.append(r8)
            char r8 = r12.getPaddingChar()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = "'"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11._reportInvalidBase64(r12, r6, r10, r7)
        L99:
            int r1 = r1 >> 4
            r3.append(r1)
            goto L11
        La0:
            int r0 = r1 << 6
            r1 = r0 | r7
            if (r6 < r5) goto Lb6
            boolean r0 = r12.usesPadding()
            if (r0 != 0) goto Lb3
            int r0 = r1 >> 2
            r3.appendTwoBytes(r0)
            goto L1b
        Lb3:
            r11._reportBase64EOF()
        Lb6:
            int r0 = r6 + 1
            char r6 = r4.charAt(r6)
            int r7 = r12.decodeBase64Char(r6)
            if (r7 >= 0) goto Lce
            if (r7 == r9) goto Lc7
            r11._reportInvalidBase64(r12, r6, r10)
        Lc7:
            int r1 = r1 >> 2
            r3.appendTwoBytes(r1)
            goto L11
        Lce:
            int r1 = r1 << 6
            r1 = r1 | r7
            r3.appendThreeBytes(r1)
            goto L11
        Ld6:
            r0 = r1
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.node.TextNode.getBinaryValue(com.flurry.org.codehaus.jackson.Base64Variant):byte[]");
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String getTextValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isTextual() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(this._value);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.JsonNode
    public String toString() {
        int length = this._value.length();
        StringBuilder sb = new StringBuilder((length >> 4) + length + 2);
        appendQuoted(sb, this._value);
        return sb.toString();
    }
}
