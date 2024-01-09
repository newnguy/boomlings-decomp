package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.impl.JsonParserMinimalBase;
import com.flurry.org.codehaus.jackson.impl.JsonReadContext;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class TokenBuffer$Parser extends JsonParserMinimalBase {
  protected transient ByteArrayBuilder _byteBuilder;
  
  protected boolean _closed;
  
  protected ObjectCodec _codec;
  
  protected JsonLocation _location = null;
  
  protected JsonReadContext _parsingContext;
  
  protected TokenBuffer$Segment _segment;
  
  protected int _segmentPtr;
  
  public TokenBuffer$Parser(TokenBuffer$Segment paramTokenBuffer$Segment, ObjectCodec paramObjectCodec) {
    super(0);
    this._segment = paramTokenBuffer$Segment;
    this._segmentPtr = -1;
    this._codec = paramObjectCodec;
    this._parsingContext = JsonReadContext.createRootContext(-1, -1);
  }
  
  protected final void _checkIsNumber() {
    if (this._currToken == null || !this._currToken.isNumeric())
      throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors"); 
  }
  
  protected final Object _currentObject() {
    return this._segment.get(this._segmentPtr);
  }
  
  protected void _handleEOF() {
    _throwInternal();
  }
  
  public void close() {
    if (!this._closed)
      this._closed = true; 
  }
  
  public BigInteger getBigIntegerValue() {
    null = getNumberValue();
    if (null instanceof BigInteger)
      return (BigInteger)null; 
    switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()]) {
      default:
        return BigInteger.valueOf(null.longValue());
      case 3:
        break;
    } 
    return ((BigDecimal)SYNTHETIC_LOCAL_VARIABLE_1).toBigInteger();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant) {
    if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
      Object object = _currentObject();
      if (object instanceof byte[])
        return (byte[])object; 
    } 
    if (this._currToken != JsonToken.VALUE_STRING)
      throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary"); 
    String str = getText();
    if (str == null)
      return null; 
    ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
    if (byteArrayBuilder == null) {
      byteArrayBuilder = new ByteArrayBuilder(100);
      this._byteBuilder = byteArrayBuilder;
    } else {
      this._byteBuilder.reset();
    } 
    _decodeBase64(str, byteArrayBuilder, paramBase64Variant);
    return byteArrayBuilder.toByteArray();
  }
  
  public ObjectCodec getCodec() {
    return this._codec;
  }
  
  public JsonLocation getCurrentLocation() {
    return (this._location == null) ? JsonLocation.NA : this._location;
  }
  
  public String getCurrentName() {
    return this._parsingContext.getCurrentName();
  }
  
  public BigDecimal getDecimalValue() {
    null = getNumberValue();
    if (null instanceof BigDecimal)
      return (BigDecimal)null; 
    switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[getNumberType().ordinal()]) {
      default:
        return BigDecimal.valueOf(null.doubleValue());
      case 1:
      case 5:
        return BigDecimal.valueOf(SYNTHETIC_LOCAL_VARIABLE_1.longValue());
      case 2:
        break;
    } 
    return new BigDecimal((BigInteger)SYNTHETIC_LOCAL_VARIABLE_1);
  }
  
  public double getDoubleValue() {
    return getNumberValue().doubleValue();
  }
  
  public Object getEmbeddedObject() {
    return (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) ? _currentObject() : null;
  }
  
  public float getFloatValue() {
    return getNumberValue().floatValue();
  }
  
  public int getIntValue() {
    return (this._currToken == JsonToken.VALUE_NUMBER_INT) ? ((Number)_currentObject()).intValue() : getNumberValue().intValue();
  }
  
  public long getLongValue() {
    return getNumberValue().longValue();
  }
  
  public JsonParser.NumberType getNumberType() {
    null = getNumberValue();
    return (null instanceof Integer) ? JsonParser.NumberType.INT : ((null instanceof Long) ? JsonParser.NumberType.LONG : ((null instanceof Double) ? JsonParser.NumberType.DOUBLE : ((null instanceof BigDecimal) ? JsonParser.NumberType.BIG_DECIMAL : ((null instanceof Float) ? JsonParser.NumberType.FLOAT : ((null instanceof BigInteger) ? JsonParser.NumberType.BIG_INTEGER : null)))));
  }
  
  public final Number getNumberValue() {
    _checkIsNumber();
    return (Number)_currentObject();
  }
  
  public JsonStreamContext getParsingContext() {
    return (JsonStreamContext)this._parsingContext;
  }
  
  public String getText() {
    String str2 = null;
    if (this._currToken == JsonToken.VALUE_STRING || this._currToken == JsonToken.FIELD_NAME) {
      null = _currentObject();
      return (null instanceof String) ? (String)null : ((null == null) ? null : null.toString());
    } 
    String str1 = str2;
    if (this._currToken != null) {
      switch (TokenBuffer$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return this._currToken.asString();
        case 7:
        case 8:
          break;
      } 
      Object object = _currentObject();
      str1 = str2;
      if (object != null)
        str1 = object.toString(); 
    } 
    return str1;
  }
  
  public char[] getTextCharacters() {
    String str = getText();
    return (str == null) ? null : str.toCharArray();
  }
  
  public int getTextLength() {
    String str = getText();
    return (str == null) ? 0 : str.length();
  }
  
  public int getTextOffset() {
    return 0;
  }
  
  public JsonLocation getTokenLocation() {
    return getCurrentLocation();
  }
  
  public boolean hasTextCharacters() {
    return false;
  }
  
  public boolean isClosed() {
    return this._closed;
  }
  
  public JsonToken nextToken() {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_3
    //   3: astore_2
    //   4: aload_0
    //   5: getfield _closed : Z
    //   8: ifne -> 20
    //   11: aload_0
    //   12: getfield _segment : Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   15: ifnonnull -> 22
    //   18: aload_3
    //   19: astore_2
    //   20: aload_2
    //   21: areturn
    //   22: aload_0
    //   23: getfield _segmentPtr : I
    //   26: iconst_1
    //   27: iadd
    //   28: istore_1
    //   29: aload_0
    //   30: iload_1
    //   31: putfield _segmentPtr : I
    //   34: iload_1
    //   35: bipush #16
    //   37: if_icmplt -> 65
    //   40: aload_0
    //   41: iconst_0
    //   42: putfield _segmentPtr : I
    //   45: aload_0
    //   46: aload_0
    //   47: getfield _segment : Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   50: invokevirtual next : ()Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   53: putfield _segment : Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   56: aload_3
    //   57: astore_2
    //   58: aload_0
    //   59: getfield _segment : Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   62: ifnull -> 20
    //   65: aload_0
    //   66: aload_0
    //   67: getfield _segment : Lcom/flurry/org/codehaus/jackson/util/TokenBuffer$Segment;
    //   70: aload_0
    //   71: getfield _segmentPtr : I
    //   74: invokevirtual type : (I)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   77: putfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   80: aload_0
    //   81: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   84: getstatic com/flurry/org/codehaus/jackson/JsonToken.FIELD_NAME : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   87: if_acmpne -> 131
    //   90: aload_0
    //   91: invokevirtual _currentObject : ()Ljava/lang/Object;
    //   94: astore_2
    //   95: aload_2
    //   96: instanceof java/lang/String
    //   99: ifeq -> 123
    //   102: aload_2
    //   103: checkcast java/lang/String
    //   106: astore_2
    //   107: aload_0
    //   108: getfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   111: aload_2
    //   112: invokevirtual setCurrentName : (Ljava/lang/String;)V
    //   115: aload_0
    //   116: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   119: astore_2
    //   120: goto -> 20
    //   123: aload_2
    //   124: invokevirtual toString : ()Ljava/lang/String;
    //   127: astore_2
    //   128: goto -> 107
    //   131: aload_0
    //   132: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   135: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   138: if_acmpne -> 157
    //   141: aload_0
    //   142: aload_0
    //   143: getfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   146: iconst_m1
    //   147: iconst_m1
    //   148: invokevirtual createChildObjectContext : (II)Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   151: putfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   154: goto -> 115
    //   157: aload_0
    //   158: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   161: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_ARRAY : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   164: if_acmpne -> 183
    //   167: aload_0
    //   168: aload_0
    //   169: getfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   172: iconst_m1
    //   173: iconst_m1
    //   174: invokevirtual createChildArrayContext : (II)Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   177: putfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   180: goto -> 115
    //   183: aload_0
    //   184: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   187: getstatic com/flurry/org/codehaus/jackson/JsonToken.END_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   190: if_acmpeq -> 203
    //   193: aload_0
    //   194: getfield _currToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   197: getstatic com/flurry/org/codehaus/jackson/JsonToken.END_ARRAY : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   200: if_acmpne -> 115
    //   203: aload_0
    //   204: aload_0
    //   205: getfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   208: invokevirtual getParent : ()Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   211: putfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   214: aload_0
    //   215: getfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   218: ifnonnull -> 115
    //   221: aload_0
    //   222: iconst_m1
    //   223: iconst_m1
    //   224: invokestatic createRootContext : (II)Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   227: putfield _parsingContext : Lcom/flurry/org/codehaus/jackson/impl/JsonReadContext;
    //   230: goto -> 115
  }
  
  public JsonToken peekNextToken() {
    JsonToken jsonToken = null;
    if (!this._closed) {
      TokenBuffer$Segment tokenBuffer$Segment = this._segment;
      int i = this._segmentPtr + 1;
      if (i >= 16) {
        if (tokenBuffer$Segment == null) {
          tokenBuffer$Segment = null;
        } else {
          tokenBuffer$Segment = tokenBuffer$Segment.next();
        } 
        i = 0;
      } 
      if (tokenBuffer$Segment != null)
        jsonToken = tokenBuffer$Segment.type(i); 
    } 
    return jsonToken;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec) {
    this._codec = paramObjectCodec;
  }
  
  public void setLocation(JsonLocation paramJsonLocation) {
    this._location = paramJsonLocation;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\TokenBuffer$Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */