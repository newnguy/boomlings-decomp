package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;

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
  
  protected JsonParserMinimalBase() {}
  
  protected JsonParserMinimalBase(int paramInt) {
    super(paramInt);
  }
  
  protected static final String _getCharDesc(int paramInt) {
    char c = (char)paramInt;
    return Character.isISOControl(c) ? ("(CTRL-CHAR, code " + paramInt + ")") : ((paramInt > 255) ? ("'" + c + "' (code " + paramInt + " / 0x" + Integer.toHexString(paramInt) + ")") : ("'" + c + "' (code " + paramInt + ")"));
  }
  
  protected final JsonParseException _constructError(String paramString, Throwable paramThrowable) {
    return new JsonParseException(paramString, getCurrentLocation(), paramThrowable);
  }
  
  protected void _decodeBase64(String paramString, ByteArrayBuilder paramByteArrayBuilder, Base64Variant paramBase64Variant) {
    int j = paramString.length();
    int i = 0;
    label52: while (true) {
      if (i < j) {
        while (true) {
          int k = i + 1;
          char c = paramString.charAt(i);
          if (k < j) {
            if (c > ' ') {
              int m = paramBase64Variant.decodeBase64Char(c);
              if (m < 0)
                _reportInvalidBase64(paramBase64Variant, c, 0, (String)null); 
              if (k >= j)
                _reportBase64EOF(); 
              i = k + 1;
              c = paramString.charAt(k);
              k = paramBase64Variant.decodeBase64Char(c);
              if (k < 0)
                _reportInvalidBase64(paramBase64Variant, c, 1, (String)null); 
              m = m << 6 | k;
              if (i >= j) {
                if (!paramBase64Variant.usesPadding()) {
                  paramByteArrayBuilder.append(m >> 4);
                  return;
                } 
                _reportBase64EOF();
              } 
              k = i + 1;
              c = paramString.charAt(i);
              i = paramBase64Variant.decodeBase64Char(c);
              if (i < 0) {
                if (i != -2)
                  _reportInvalidBase64(paramBase64Variant, c, 2, (String)null); 
                if (k >= j)
                  _reportBase64EOF(); 
                i = k + 1;
                c = paramString.charAt(k);
                if (!paramBase64Variant.usesPaddingChar(c))
                  _reportInvalidBase64(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'"); 
                paramByteArrayBuilder.append(m >> 4);
                continue label52;
              } 
              m = m << 6 | i;
              if (k >= j) {
                if (!paramBase64Variant.usesPadding()) {
                  paramByteArrayBuilder.appendTwoBytes(m >> 2);
                  return;
                } 
                _reportBase64EOF();
              } 
              i = k + 1;
              c = paramString.charAt(k);
              k = paramBase64Variant.decodeBase64Char(c);
              if (k < 0) {
                if (k != -2)
                  _reportInvalidBase64(paramBase64Variant, c, 3, (String)null); 
                paramByteArrayBuilder.appendTwoBytes(m >> 2);
                continue label52;
              } 
              paramByteArrayBuilder.appendThreeBytes(m << 6 | k);
              continue label52;
            } 
            i = k;
            continue;
          } 
          return;
        } 
        break;
      } 
      continue;
    } 
  }
  
  protected abstract void _handleEOF();
  
  protected char _handleUnrecognizedCharacterEscape(char paramChar) {
    if (!isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (paramChar != '\'' || !isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)))
      _reportError("Unrecognized character escape " + _getCharDesc(paramChar)); 
    return paramChar;
  }
  
  protected void _reportBase64EOF() {
    throw _constructError("Unexpected end-of-String in base64 content");
  }
  
  protected final void _reportError(String paramString) {
    throw _constructError(paramString);
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
    throw _constructError(str2);
  }
  
  protected void _reportInvalidEOF() {
    _reportInvalidEOF(" in " + this._currToken);
  }
  
  protected void _reportInvalidEOF(String paramString) {
    _reportError("Unexpected end-of-input" + paramString);
  }
  
  protected void _reportInvalidEOFInValue() {
    _reportInvalidEOF(" in a value");
  }
  
  protected void _reportUnexpectedChar(int paramInt, String paramString) {
    String str2 = "Unexpected character (" + _getCharDesc(paramInt) + ")";
    String str1 = str2;
    if (paramString != null)
      str1 = str2 + ": " + paramString; 
    _reportError(str1);
  }
  
  protected final void _throwInternal() {
    throw new RuntimeException("Internal error: this code path should never get executed");
  }
  
  protected void _throwInvalidSpace(int paramInt) {
    paramInt = (char)paramInt;
    _reportError("Illegal character (" + _getCharDesc(paramInt) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
  }
  
  protected void _throwUnquotedSpace(int paramInt, String paramString) {
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || paramInt >= 32) {
      paramInt = (char)paramInt;
      _reportError("Illegal unquoted character (" + _getCharDesc(paramInt) + "): has to be escaped using backslash to be included in " + paramString);
    } 
  }
  
  protected final void _wrapError(String paramString, Throwable paramThrowable) {
    throw _constructError(paramString, paramThrowable);
  }
  
  public abstract void close();
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant);
  
  public abstract String getCurrentName();
  
  public abstract JsonStreamContext getParsingContext();
  
  public abstract String getText();
  
  public abstract char[] getTextCharacters();
  
  public abstract int getTextLength();
  
  public abstract int getTextOffset();
  
  public boolean getValueAsBoolean(boolean paramBoolean) {
    boolean bool = false;
    if (this._currToken != null) {
      Object object;
      boolean bool1 = bool;
      switch (JsonParserMinimalBase$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          bool1 = paramBoolean;
        case 7:
        case 8:
          return bool1;
        case 5:
          bool1 = bool;
          if (getIntValue() != 0)
            bool1 = true; 
        case 6:
          bool1 = true;
        case 9:
          object = getEmbeddedObject();
          if (object instanceof Boolean)
            bool1 = ((Boolean)object).booleanValue(); 
          break;
        case 10:
          break;
      } 
      if ("true".equals(getText().trim()))
        bool1 = true; 
    } 
  }
  
  public double getValueAsDouble(double paramDouble) {
    double d = paramDouble;
    if (this._currToken != null) {
      switch (JsonParserMinimalBase$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return paramDouble;
        case 5:
        case 11:
          return getDoubleValue();
        case 6:
          return 1.0D;
        case 7:
        case 8:
          return 0.0D;
        case 10:
          return NumberInput.parseAsDouble(getText(), paramDouble);
        case 9:
          break;
      } 
    } else {
      return d;
    } 
    Object object = getEmbeddedObject();
    d = paramDouble;
    if (object instanceof Number)
      d = ((Number)object).doubleValue(); 
    return d;
  }
  
  public int getValueAsInt(int paramInt) {
    int i = paramInt;
    if (this._currToken != null) {
      switch (JsonParserMinimalBase$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return paramInt;
        case 5:
        case 11:
          return getIntValue();
        case 6:
          return 1;
        case 7:
        case 8:
          return 0;
        case 10:
          return NumberInput.parseAsInt(getText(), paramInt);
        case 9:
          break;
      } 
    } else {
      return i;
    } 
    Object object = getEmbeddedObject();
    i = paramInt;
    if (object instanceof Number)
      i = ((Number)object).intValue(); 
    return i;
  }
  
  public long getValueAsLong(long paramLong) {
    long l = paramLong;
    if (this._currToken != null) {
      switch (JsonParserMinimalBase$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return paramLong;
        case 5:
        case 11:
          return getLongValue();
        case 6:
          return 1L;
        case 7:
        case 8:
          return 0L;
        case 10:
          return NumberInput.parseAsLong(getText(), paramLong);
        case 9:
          break;
      } 
    } else {
      return l;
    } 
    Object object = getEmbeddedObject();
    l = paramLong;
    if (object instanceof Number)
      l = ((Number)object).longValue(); 
    return l;
  }
  
  public abstract boolean hasTextCharacters();
  
  public abstract boolean isClosed();
  
  public abstract JsonToken nextToken();
  
  public JsonParser skipChildren() {
    if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
      int i = 1;
      while (true) {
        int j;
        JsonToken jsonToken = nextToken();
        if (jsonToken == null) {
          _handleEOF();
          return this;
        } 
        switch (JsonParserMinimalBase$1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken.ordinal()]) {
          case 1:
          case 2:
            i++;
            break;
          case 3:
          case 4:
            j = i - 1;
            i = j;
            if (j == 0)
              return this; 
            break;
        } 
      } 
    } 
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\JsonParserMinimalBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */