package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;

public final class TextNode extends ValueNode {
  static final TextNode EMPTY_STRING_NODE = new TextNode("");
  
  static final int INT_SPACE = 32;
  
  final String _value;
  
  public TextNode(String paramString) {
    this._value = paramString;
  }
  
  protected static void appendQuoted(StringBuilder paramStringBuilder, String paramString) {
    paramStringBuilder.append('"');
    CharTypes.appendQuoted(paramStringBuilder, paramString);
    paramStringBuilder.append('"');
  }
  
  public static TextNode valueOf(String paramString) {
    return (paramString == null) ? null : ((paramString.length() == 0) ? EMPTY_STRING_NODE : new TextNode(paramString));
  }
  
  protected void _reportBase64EOF() {
    throw new JsonParseException("Unexpected end-of-String when base64 content", JsonLocation.NA);
  }
  
  protected void _reportInvalidBase64(Base64Variant paramBase64Variant, char paramChar, int paramInt) {
    _reportInvalidBase64(paramBase64Variant, paramChar, paramInt, null);
  }
  
  protected void _reportInvalidBase64(Base64Variant paramBase64Variant, char paramChar, int paramInt, String paramString) {
    String str1;
    if (paramChar <= ' ') {
      str1 = "Illegal white space character (code 0x" + Integer.toHexString(paramChar) + ") as character #" + (paramInt + 1) + " of 4-char base64 unit: can only used between units";
    } else if (str1.usesPaddingChar(paramChar)) {
      str1 = "Unexpected padding character ('" + str1.getPaddingChar() + "') as character #" + (paramInt + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
    } else if (!Character.isDefined(paramChar) || Character.isISOControl(paramChar)) {
      str1 = "Illegal character (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
    } else {
      str1 = "Illegal character '" + paramChar + "' (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
    } 
    String str2 = str1;
    if (paramString != null)
      str2 = str1 + ": " + paramString; 
    throw new JsonParseException(str2, JsonLocation.NA);
  }
  
  public boolean asBoolean(boolean paramBoolean) {
    boolean bool = paramBoolean;
    if (this._value != null) {
      bool = paramBoolean;
      if ("true".equals(this._value.trim()))
        bool = true; 
    } 
    return bool;
  }
  
  public double asDouble(double paramDouble) {
    return NumberInput.parseAsDouble(this._value, paramDouble);
  }
  
  public int asInt(int paramInt) {
    return NumberInput.parseAsInt(this._value, paramInt);
  }
  
  public long asLong(long paramLong) {
    return NumberInput.parseAsLong(this._value, paramLong);
  }
  
  public String asText() {
    return this._value;
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_STRING;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject != null) {
      bool1 = bool2;
      if (paramObject.getClass() == getClass())
        bool1 = ((TextNode)paramObject)._value.equals(this._value); 
    } 
    return bool1;
  }
  
  public byte[] getBinaryValue() {
    return getBinaryValue(Base64Variants.getDefaultVariant());
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant) {
    ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(100);
    String str = this._value;
    int j = str.length();
    int i = 0;
    label52: while (true) {
      if (i < j) {
        while (true) {
          int k = i + 1;
          char c = str.charAt(i);
          if (k < j) {
            if (c > ' ') {
              int m = paramBase64Variant.decodeBase64Char(c);
              if (m < 0)
                _reportInvalidBase64(paramBase64Variant, c, 0); 
              if (k >= j)
                _reportBase64EOF(); 
              i = k + 1;
              c = str.charAt(k);
              k = paramBase64Variant.decodeBase64Char(c);
              if (k < 0)
                _reportInvalidBase64(paramBase64Variant, c, 1); 
              m = m << 6 | k;
              if (i >= j) {
                if (!paramBase64Variant.usesPadding()) {
                  byteArrayBuilder.append(m >> 4);
                  return byteArrayBuilder.toByteArray();
                } 
                _reportBase64EOF();
              } 
              k = i + 1;
              c = str.charAt(i);
              i = paramBase64Variant.decodeBase64Char(c);
              if (i < 0) {
                if (i != -2)
                  _reportInvalidBase64(paramBase64Variant, c, 2); 
                if (k >= j)
                  _reportBase64EOF(); 
                i = k + 1;
                c = str.charAt(k);
                if (!paramBase64Variant.usesPaddingChar(c))
                  _reportInvalidBase64(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'"); 
                byteArrayBuilder.append(m >> 4);
                continue label52;
              } 
              m = m << 6 | i;
              if (k >= j) {
                if (!paramBase64Variant.usesPadding()) {
                  byteArrayBuilder.appendTwoBytes(m >> 2);
                  return byteArrayBuilder.toByteArray();
                } 
                _reportBase64EOF();
              } 
              i = k + 1;
              c = str.charAt(k);
              k = paramBase64Variant.decodeBase64Char(c);
              if (k < 0) {
                if (k != -2)
                  _reportInvalidBase64(paramBase64Variant, c, 3); 
                byteArrayBuilder.appendTwoBytes(m >> 2);
                continue label52;
              } 
              byteArrayBuilder.appendThreeBytes(m << 6 | k);
              continue label52;
            } 
            i = k;
            continue;
          } 
          return byteArrayBuilder.toByteArray();
        } 
        break;
      } 
      continue;
    } 
  }
  
  public String getTextValue() {
    return this._value;
  }
  
  public int hashCode() {
    return this._value.hashCode();
  }
  
  public boolean isTextual() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._value == null) {
      paramJsonGenerator.writeNull();
      return;
    } 
    paramJsonGenerator.writeString(this._value);
  }
  
  public String toString() {
    int i = this._value.length();
    StringBuilder stringBuilder = new StringBuilder((i >> 4) + i + 2);
    appendQuoted(stringBuilder, this._value);
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\TextNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */