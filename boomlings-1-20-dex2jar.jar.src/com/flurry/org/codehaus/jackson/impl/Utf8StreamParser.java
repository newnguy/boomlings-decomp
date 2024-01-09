package com.flurry.org.codehaus.jackson.impl;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Utf8StreamParser extends JsonParserBase {
  static final byte BYTE_LF = 10;
  
  private static final int[] sInputCodesLatin1;
  
  private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
  
  protected boolean _bufferRecyclable;
  
  protected byte[] _inputBuffer;
  
  protected InputStream _inputStream;
  
  protected ObjectCodec _objectCodec;
  
  private int _quad1;
  
  protected int[] _quadBuffer = new int[16];
  
  protected final BytesToNameCanonicalizer _symbols;
  
  protected boolean _tokenIncomplete = false;
  
  static {
    sInputCodesLatin1 = CharTypes.getInputCodeLatin1();
  }
  
  public Utf8StreamParser(IOContext paramIOContext, int paramInt1, InputStream paramInputStream, ObjectCodec paramObjectCodec, BytesToNameCanonicalizer paramBytesToNameCanonicalizer, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, boolean paramBoolean) {
    super(paramIOContext, paramInt1);
    this._inputStream = paramInputStream;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramBytesToNameCanonicalizer;
    this._inputBuffer = paramArrayOfbyte;
    this._inputPtr = paramInt2;
    this._inputEnd = paramInt3;
    this._bufferRecyclable = paramBoolean;
    if (!JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(paramInt1))
      _throwInternal(); 
  }
  
  private final int _decodeUtf8_2(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128)
      _reportInvalidOther(i & 0xFF, this._inputPtr); 
    return i & 0x3F | (paramInt & 0x1F) << 6;
  }
  
  private final int _decodeUtf8_3(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128)
      _reportInvalidOther(i & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = j + 1;
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128)
      _reportInvalidOther(j & 0xFF, this._inputPtr); 
    return ((paramInt & 0xF) << 6 | i & 0x3F) << 6 | j & 0x3F;
  }
  
  private final int _decodeUtf8_3fast(int paramInt) {
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128)
      _reportInvalidOther(i & 0xFF, this._inputPtr); 
    arrayOfByte = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = j + 1;
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128)
      _reportInvalidOther(j & 0xFF, this._inputPtr); 
    return ((paramInt & 0xF) << 6 | i & 0x3F) << 6 | j & 0x3F;
  }
  
  private final int _decodeUtf8_4(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128)
      _reportInvalidOther(i & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = j + 1;
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128)
      _reportInvalidOther(j & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    int k = this._inputPtr;
    this._inputPtr = k + 1;
    k = arrayOfByte[k];
    if ((k & 0xC0) != 128)
      _reportInvalidOther(k & 0xFF, this._inputPtr); 
    return (((i & 0x3F | (paramInt & 0x7) << 6) << 6 | j & 0x3F) << 6 | k & 0x3F) - 65536;
  }
  
  private final void _finishString2(char[] paramArrayOfchar, int paramInt) {
    // Byte code:
    //   0: getstatic com/flurry/org/codehaus/jackson/impl/Utf8StreamParser.sInputCodesUtf8 : [I
    //   3: astore #9
    //   5: aload_0
    //   6: getfield _inputBuffer : [B
    //   9: astore #8
    //   11: aload_1
    //   12: astore #7
    //   14: aload_0
    //   15: getfield _inputPtr : I
    //   18: istore_3
    //   19: iload_3
    //   20: istore #4
    //   22: iload_3
    //   23: aload_0
    //   24: getfield _inputEnd : I
    //   27: if_icmplt -> 40
    //   30: aload_0
    //   31: invokevirtual loadMoreGuaranteed : ()V
    //   34: aload_0
    //   35: getfield _inputPtr : I
    //   38: istore #4
    //   40: aload #7
    //   42: astore_1
    //   43: iload_2
    //   44: istore_3
    //   45: iload_2
    //   46: aload #7
    //   48: arraylength
    //   49: if_icmplt -> 62
    //   52: aload_0
    //   53: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   56: invokevirtual finishCurrentSegment : ()[C
    //   59: astore_1
    //   60: iconst_0
    //   61: istore_3
    //   62: aload_0
    //   63: getfield _inputEnd : I
    //   66: aload_1
    //   67: arraylength
    //   68: iload_3
    //   69: isub
    //   70: iload #4
    //   72: iadd
    //   73: invokestatic min : (II)I
    //   76: istore #6
    //   78: iload #4
    //   80: iload #6
    //   82: if_icmpge -> 144
    //   85: iload #4
    //   87: iconst_1
    //   88: iadd
    //   89: istore #5
    //   91: aload #8
    //   93: iload #4
    //   95: baload
    //   96: sipush #255
    //   99: iand
    //   100: istore_2
    //   101: aload #9
    //   103: iload_2
    //   104: iaload
    //   105: ifeq -> 129
    //   108: aload_0
    //   109: iload #5
    //   111: putfield _inputPtr : I
    //   114: iload_2
    //   115: bipush #34
    //   117: if_icmpne -> 158
    //   120: aload_0
    //   121: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   124: iload_3
    //   125: invokevirtual setCurrentLength : (I)V
    //   128: return
    //   129: aload_1
    //   130: iload_3
    //   131: iload_2
    //   132: i2c
    //   133: castore
    //   134: iload #5
    //   136: istore #4
    //   138: iinc #3, 1
    //   141: goto -> 78
    //   144: aload_0
    //   145: iload #4
    //   147: putfield _inputPtr : I
    //   150: aload_1
    //   151: astore #7
    //   153: iload_3
    //   154: istore_2
    //   155: goto -> 14
    //   158: aload #9
    //   160: iload_2
    //   161: iaload
    //   162: tableswitch default -> 192, 1 -> 239, 2 -> 247, 3 -> 256, 4 -> 287
    //   192: iload_2
    //   193: bipush #32
    //   195: if_icmpge -> 353
    //   198: aload_0
    //   199: iload_2
    //   200: ldc 'string value'
    //   202: invokevirtual _throwUnquotedSpace : (ILjava/lang/String;)V
    //   205: iload_3
    //   206: aload_1
    //   207: arraylength
    //   208: if_icmplt -> 361
    //   211: aload_0
    //   212: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   215: invokevirtual finishCurrentSegment : ()[C
    //   218: astore #7
    //   220: iconst_0
    //   221: istore_3
    //   222: iload_3
    //   223: iconst_1
    //   224: iadd
    //   225: istore #4
    //   227: aload #7
    //   229: iload_3
    //   230: iload_2
    //   231: i2c
    //   232: castore
    //   233: iload #4
    //   235: istore_2
    //   236: goto -> 14
    //   239: aload_0
    //   240: invokevirtual _decodeEscaped : ()C
    //   243: istore_2
    //   244: goto -> 205
    //   247: aload_0
    //   248: iload_2
    //   249: invokespecial _decodeUtf8_2 : (I)I
    //   252: istore_2
    //   253: goto -> 205
    //   256: aload_0
    //   257: getfield _inputEnd : I
    //   260: aload_0
    //   261: getfield _inputPtr : I
    //   264: isub
    //   265: iconst_2
    //   266: if_icmplt -> 278
    //   269: aload_0
    //   270: iload_2
    //   271: invokespecial _decodeUtf8_3fast : (I)I
    //   274: istore_2
    //   275: goto -> 205
    //   278: aload_0
    //   279: iload_2
    //   280: invokespecial _decodeUtf8_3 : (I)I
    //   283: istore_2
    //   284: goto -> 205
    //   287: aload_0
    //   288: iload_2
    //   289: invokespecial _decodeUtf8_4 : (I)I
    //   292: istore #5
    //   294: iload_3
    //   295: iconst_1
    //   296: iadd
    //   297: istore #4
    //   299: aload_1
    //   300: iload_3
    //   301: ldc 55296
    //   303: iload #5
    //   305: bipush #10
    //   307: ishr
    //   308: ior
    //   309: i2c
    //   310: castore
    //   311: iload #4
    //   313: istore_2
    //   314: aload_1
    //   315: astore #7
    //   317: iload #4
    //   319: aload_1
    //   320: arraylength
    //   321: if_icmplt -> 335
    //   324: aload_0
    //   325: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   328: invokevirtual finishCurrentSegment : ()[C
    //   331: astore #7
    //   333: iconst_0
    //   334: istore_2
    //   335: iload_2
    //   336: istore_3
    //   337: iload #5
    //   339: sipush #1023
    //   342: iand
    //   343: ldc 56320
    //   345: ior
    //   346: istore_2
    //   347: aload #7
    //   349: astore_1
    //   350: goto -> 205
    //   353: aload_0
    //   354: iload_2
    //   355: invokevirtual _reportInvalidChar : (I)V
    //   358: goto -> 205
    //   361: aload_1
    //   362: astore #7
    //   364: goto -> 222
  }
  
  private final void _isNextTokenNameNo(int paramInt) {
    Name name = _parseFieldName(paramInt);
    this._parsingContext.setCurrentName(name.getName());
    this._currToken = JsonToken.FIELD_NAME;
    paramInt = _skipWS();
    if (paramInt != 58)
      _reportUnexpectedChar(paramInt, "was expecting a colon to separate field name and value"); 
    paramInt = _skipWS();
    if (paramInt == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return;
    } 
    switch (paramInt) {
      default:
        jsonToken = _handleUnexpectedValue(paramInt);
        this._nextToken = jsonToken;
        return;
      case 91:
        jsonToken = JsonToken.START_ARRAY;
        this._nextToken = jsonToken;
        return;
      case 123:
        jsonToken = JsonToken.START_OBJECT;
        this._nextToken = jsonToken;
        return;
      case 93:
      case 125:
        _reportUnexpectedChar(paramInt, "expected a value");
      case 116:
        _matchToken("true", 1);
        jsonToken = JsonToken.VALUE_TRUE;
        this._nextToken = jsonToken;
        return;
      case 102:
        _matchToken("false", 1);
        jsonToken = JsonToken.VALUE_FALSE;
        this._nextToken = jsonToken;
        return;
      case 110:
        _matchToken("null", 1);
        jsonToken = JsonToken.VALUE_NULL;
        this._nextToken = jsonToken;
        return;
      case 45:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        break;
    } 
    JsonToken jsonToken = parseNumberText(paramInt);
    this._nextToken = jsonToken;
  }
  
  private final void _isNextTokenNameYes() {
    // Byte code:
    //   0: aload_0
    //   1: getfield _inputPtr : I
    //   4: aload_0
    //   5: getfield _inputEnd : I
    //   8: if_icmpge -> 316
    //   11: aload_0
    //   12: getfield _inputBuffer : [B
    //   15: aload_0
    //   16: getfield _inputPtr : I
    //   19: baload
    //   20: bipush #58
    //   22: if_icmpne -> 316
    //   25: aload_0
    //   26: aload_0
    //   27: getfield _inputPtr : I
    //   30: iconst_1
    //   31: iadd
    //   32: putfield _inputPtr : I
    //   35: aload_0
    //   36: getfield _inputBuffer : [B
    //   39: astore_3
    //   40: aload_0
    //   41: getfield _inputPtr : I
    //   44: istore_1
    //   45: aload_0
    //   46: iload_1
    //   47: iconst_1
    //   48: iadd
    //   49: putfield _inputPtr : I
    //   52: aload_3
    //   53: iload_1
    //   54: baload
    //   55: istore_1
    //   56: iload_1
    //   57: bipush #34
    //   59: if_icmpne -> 75
    //   62: aload_0
    //   63: iconst_1
    //   64: putfield _tokenIncomplete : Z
    //   67: aload_0
    //   68: getstatic com/flurry/org/codehaus/jackson/JsonToken.VALUE_STRING : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   71: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   74: return
    //   75: iload_1
    //   76: bipush #123
    //   78: if_icmpne -> 91
    //   81: aload_0
    //   82: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   85: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   88: goto -> 74
    //   91: iload_1
    //   92: bipush #91
    //   94: if_icmpne -> 107
    //   97: aload_0
    //   98: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_ARRAY : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   101: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   104: goto -> 74
    //   107: iload_1
    //   108: sipush #255
    //   111: iand
    //   112: istore_2
    //   113: iload_2
    //   114: bipush #32
    //   116: if_icmple -> 127
    //   119: iload_2
    //   120: istore_1
    //   121: iload_2
    //   122: bipush #47
    //   124: if_icmpne -> 142
    //   127: aload_0
    //   128: aload_0
    //   129: getfield _inputPtr : I
    //   132: iconst_1
    //   133: isub
    //   134: putfield _inputPtr : I
    //   137: aload_0
    //   138: invokespecial _skipWS : ()I
    //   141: istore_1
    //   142: iload_1
    //   143: lookupswitch default -> 304, 34 -> 324, 45 -> 417, 48 -> 417, 49 -> 417, 50 -> 417, 51 -> 417, 52 -> 417, 53 -> 417, 54 -> 417, 55 -> 417, 56 -> 417, 57 -> 417, 91 -> 339, 93 -> 359, 102 -> 383, 110 -> 400, 116 -> 366, 123 -> 349, 125 -> 359
    //   304: aload_0
    //   305: aload_0
    //   306: iload_1
    //   307: invokevirtual _handleUnexpectedValue : (I)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   310: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   313: goto -> 74
    //   316: aload_0
    //   317: invokespecial _skipColon : ()I
    //   320: istore_1
    //   321: goto -> 142
    //   324: aload_0
    //   325: iconst_1
    //   326: putfield _tokenIncomplete : Z
    //   329: aload_0
    //   330: getstatic com/flurry/org/codehaus/jackson/JsonToken.VALUE_STRING : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   333: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   336: goto -> 74
    //   339: aload_0
    //   340: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_ARRAY : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   343: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   346: goto -> 74
    //   349: aload_0
    //   350: getstatic com/flurry/org/codehaus/jackson/JsonToken.START_OBJECT : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   353: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   356: goto -> 74
    //   359: aload_0
    //   360: iload_1
    //   361: ldc 'expected a value'
    //   363: invokevirtual _reportUnexpectedChar : (ILjava/lang/String;)V
    //   366: aload_0
    //   367: ldc 'true'
    //   369: iconst_1
    //   370: invokevirtual _matchToken : (Ljava/lang/String;I)V
    //   373: aload_0
    //   374: getstatic com/flurry/org/codehaus/jackson/JsonToken.VALUE_TRUE : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   377: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   380: goto -> 74
    //   383: aload_0
    //   384: ldc 'false'
    //   386: iconst_1
    //   387: invokevirtual _matchToken : (Ljava/lang/String;I)V
    //   390: aload_0
    //   391: getstatic com/flurry/org/codehaus/jackson/JsonToken.VALUE_FALSE : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   394: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   397: goto -> 74
    //   400: aload_0
    //   401: ldc 'null'
    //   403: iconst_1
    //   404: invokevirtual _matchToken : (Ljava/lang/String;I)V
    //   407: aload_0
    //   408: getstatic com/flurry/org/codehaus/jackson/JsonToken.VALUE_NULL : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   411: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   414: goto -> 74
    //   417: aload_0
    //   418: aload_0
    //   419: iload_1
    //   420: invokevirtual parseNumberText : (I)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   423: putfield _nextToken : Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   426: goto -> 74
  }
  
  private final JsonToken _nextAfterName() {
    this._nameCopied = false;
    JsonToken jsonToken = this._nextToken;
    this._nextToken = null;
    if (jsonToken == JsonToken.START_ARRAY) {
      this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      this._currToken = jsonToken;
      return jsonToken;
    } 
    if (jsonToken == JsonToken.START_OBJECT)
      this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol); 
    this._currToken = jsonToken;
    return jsonToken;
  }
  
  private final JsonToken _nextTokenNotInObject(int paramInt) {
    if (paramInt == 34) {
      this._tokenIncomplete = true;
      JsonToken jsonToken1 = JsonToken.VALUE_STRING;
      this._currToken = jsonToken1;
      return jsonToken1;
    } 
    switch (paramInt) {
      default:
        jsonToken = _handleUnexpectedValue(paramInt);
        this._currToken = jsonToken;
        return jsonToken;
      case 91:
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        jsonToken = JsonToken.START_ARRAY;
        this._currToken = jsonToken;
        return jsonToken;
      case 123:
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        jsonToken = JsonToken.START_OBJECT;
        this._currToken = jsonToken;
        return jsonToken;
      case 93:
      case 125:
        _reportUnexpectedChar(paramInt, "expected a value");
      case 116:
        _matchToken("true", 1);
        jsonToken = JsonToken.VALUE_TRUE;
        this._currToken = jsonToken;
        return jsonToken;
      case 102:
        _matchToken("false", 1);
        jsonToken = JsonToken.VALUE_FALSE;
        this._currToken = jsonToken;
        return jsonToken;
      case 110:
        _matchToken("null", 1);
        jsonToken = JsonToken.VALUE_NULL;
        this._currToken = jsonToken;
        return jsonToken;
      case 45:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        break;
    } 
    JsonToken jsonToken = parseNumberText(paramInt);
    this._currToken = jsonToken;
    return jsonToken;
  }
  
  private final JsonToken _parseFloatText(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #8
    //   3: iconst_0
    //   4: istore #6
    //   6: iconst_0
    //   7: istore #9
    //   9: iload_3
    //   10: bipush #46
    //   12: if_icmpne -> 573
    //   15: iload_2
    //   16: iconst_1
    //   17: iadd
    //   18: istore #6
    //   20: aload_1
    //   21: iload_2
    //   22: iload_3
    //   23: i2c
    //   24: castore
    //   25: iload_3
    //   26: istore #7
    //   28: iload #6
    //   30: istore_2
    //   31: iload #8
    //   33: istore_3
    //   34: aload_0
    //   35: getfield _inputPtr : I
    //   38: aload_0
    //   39: getfield _inputEnd : I
    //   42: if_icmplt -> 400
    //   45: aload_0
    //   46: invokevirtual loadMore : ()Z
    //   49: ifne -> 400
    //   52: iconst_1
    //   53: istore #6
    //   55: iload_3
    //   56: ifne -> 67
    //   59: aload_0
    //   60: iload #7
    //   62: ldc 'Decimal point not followed by a digit'
    //   64: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   67: iload_3
    //   68: istore #8
    //   70: iload #7
    //   72: istore_3
    //   73: aload_1
    //   74: astore #12
    //   76: iload_3
    //   77: bipush #101
    //   79: if_icmpeq -> 88
    //   82: iload_3
    //   83: bipush #69
    //   85: if_icmpne -> 557
    //   88: iload_2
    //   89: istore #7
    //   91: aload #12
    //   93: astore_1
    //   94: iload_2
    //   95: aload #12
    //   97: arraylength
    //   98: if_icmplt -> 112
    //   101: aload_0
    //   102: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   105: invokevirtual finishCurrentSegment : ()[C
    //   108: astore_1
    //   109: iconst_0
    //   110: istore #7
    //   112: iload #7
    //   114: iconst_1
    //   115: iadd
    //   116: istore_2
    //   117: aload_1
    //   118: iload #7
    //   120: iload_3
    //   121: i2c
    //   122: castore
    //   123: aload_0
    //   124: getfield _inputPtr : I
    //   127: aload_0
    //   128: getfield _inputEnd : I
    //   131: if_icmplt -> 138
    //   134: aload_0
    //   135: invokevirtual loadMoreGuaranteed : ()V
    //   138: aload_0
    //   139: getfield _inputBuffer : [B
    //   142: astore #12
    //   144: aload_0
    //   145: getfield _inputPtr : I
    //   148: istore_3
    //   149: aload_0
    //   150: iload_3
    //   151: iconst_1
    //   152: iadd
    //   153: putfield _inputPtr : I
    //   156: aload #12
    //   158: iload_3
    //   159: baload
    //   160: sipush #255
    //   163: iand
    //   164: istore #7
    //   166: iload #7
    //   168: bipush #45
    //   170: if_icmpeq -> 180
    //   173: iload #7
    //   175: bipush #43
    //   177: if_icmpne -> 552
    //   180: iload_2
    //   181: aload_1
    //   182: arraylength
    //   183: if_icmplt -> 549
    //   186: aload_0
    //   187: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   190: invokevirtual finishCurrentSegment : ()[C
    //   193: astore_1
    //   194: iconst_0
    //   195: istore_2
    //   196: aload_1
    //   197: iload_2
    //   198: iload #7
    //   200: i2c
    //   201: castore
    //   202: aload_0
    //   203: getfield _inputPtr : I
    //   206: aload_0
    //   207: getfield _inputEnd : I
    //   210: if_icmplt -> 217
    //   213: aload_0
    //   214: invokevirtual loadMoreGuaranteed : ()V
    //   217: aload_0
    //   218: getfield _inputBuffer : [B
    //   221: astore #12
    //   223: aload_0
    //   224: getfield _inputPtr : I
    //   227: istore_3
    //   228: aload_0
    //   229: iload_3
    //   230: iconst_1
    //   231: iadd
    //   232: putfield _inputPtr : I
    //   235: aload #12
    //   237: iload_3
    //   238: baload
    //   239: sipush #255
    //   242: iand
    //   243: istore #7
    //   245: iinc #2, 1
    //   248: iconst_0
    //   249: istore_3
    //   250: iload #7
    //   252: bipush #57
    //   254: if_icmpgt -> 536
    //   257: iload #7
    //   259: bipush #48
    //   261: if_icmplt -> 536
    //   264: iinc #3, 1
    //   267: iload_2
    //   268: istore #9
    //   270: aload_1
    //   271: astore #12
    //   273: iload_2
    //   274: aload_1
    //   275: arraylength
    //   276: if_icmplt -> 291
    //   279: aload_0
    //   280: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   283: invokevirtual finishCurrentSegment : ()[C
    //   286: astore #12
    //   288: iconst_0
    //   289: istore #9
    //   291: iload #9
    //   293: iconst_1
    //   294: iadd
    //   295: istore_2
    //   296: aload #12
    //   298: iload #9
    //   300: iload #7
    //   302: i2c
    //   303: castore
    //   304: aload_0
    //   305: getfield _inputPtr : I
    //   308: aload_0
    //   309: getfield _inputEnd : I
    //   312: if_icmplt -> 501
    //   315: aload_0
    //   316: invokevirtual loadMore : ()Z
    //   319: ifne -> 501
    //   322: iconst_1
    //   323: istore #9
    //   325: iload_2
    //   326: istore #6
    //   328: iload #9
    //   330: istore_2
    //   331: iload_3
    //   332: istore #9
    //   334: iload_2
    //   335: istore #10
    //   337: iload #6
    //   339: istore #11
    //   341: iload_3
    //   342: ifne -> 363
    //   345: aload_0
    //   346: iload #7
    //   348: ldc 'Exponent indicator not followed by a digit'
    //   350: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   353: iload #6
    //   355: istore #11
    //   357: iload_2
    //   358: istore #10
    //   360: iload_3
    //   361: istore #9
    //   363: iload #10
    //   365: ifne -> 378
    //   368: aload_0
    //   369: aload_0
    //   370: getfield _inputPtr : I
    //   373: iconst_1
    //   374: isub
    //   375: putfield _inputPtr : I
    //   378: aload_0
    //   379: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   382: iload #11
    //   384: invokevirtual setCurrentLength : (I)V
    //   387: aload_0
    //   388: iload #4
    //   390: iload #5
    //   392: iload #8
    //   394: iload #9
    //   396: invokevirtual resetFloat : (ZIII)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   399: areturn
    //   400: aload_0
    //   401: getfield _inputBuffer : [B
    //   404: astore #12
    //   406: aload_0
    //   407: getfield _inputPtr : I
    //   410: istore #6
    //   412: aload_0
    //   413: iload #6
    //   415: iconst_1
    //   416: iadd
    //   417: putfield _inputPtr : I
    //   420: aload #12
    //   422: iload #6
    //   424: baload
    //   425: sipush #255
    //   428: iand
    //   429: istore #8
    //   431: iload #9
    //   433: istore #6
    //   435: iload #8
    //   437: istore #7
    //   439: iload #8
    //   441: bipush #48
    //   443: if_icmplt -> 55
    //   446: iload #9
    //   448: istore #6
    //   450: iload #8
    //   452: istore #7
    //   454: iload #8
    //   456: bipush #57
    //   458: if_icmpgt -> 55
    //   461: iinc #3, 1
    //   464: iload_2
    //   465: aload_1
    //   466: arraylength
    //   467: if_icmplt -> 570
    //   470: aload_0
    //   471: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   474: invokevirtual finishCurrentSegment : ()[C
    //   477: astore_1
    //   478: iconst_0
    //   479: istore_2
    //   480: iload_2
    //   481: iconst_1
    //   482: iadd
    //   483: istore #6
    //   485: aload_1
    //   486: iload_2
    //   487: iload #8
    //   489: i2c
    //   490: castore
    //   491: iload #6
    //   493: istore_2
    //   494: iload #8
    //   496: istore #7
    //   498: goto -> 34
    //   501: aload_0
    //   502: getfield _inputBuffer : [B
    //   505: astore_1
    //   506: aload_0
    //   507: getfield _inputPtr : I
    //   510: istore #7
    //   512: aload_0
    //   513: iload #7
    //   515: iconst_1
    //   516: iadd
    //   517: putfield _inputPtr : I
    //   520: aload_1
    //   521: iload #7
    //   523: baload
    //   524: sipush #255
    //   527: iand
    //   528: istore #7
    //   530: aload #12
    //   532: astore_1
    //   533: goto -> 250
    //   536: iload_2
    //   537: istore #9
    //   539: iload #6
    //   541: istore_2
    //   542: iload #9
    //   544: istore #6
    //   546: goto -> 331
    //   549: goto -> 196
    //   552: iconst_0
    //   553: istore_3
    //   554: goto -> 250
    //   557: iconst_0
    //   558: istore #9
    //   560: iload #6
    //   562: istore #10
    //   564: iload_2
    //   565: istore #11
    //   567: goto -> 363
    //   570: goto -> 480
    //   573: iconst_0
    //   574: istore #8
    //   576: aload_1
    //   577: astore #12
    //   579: goto -> 76
  }
  
  private final JsonToken _parserNumber2(char[] paramArrayOfchar, int paramInt1, boolean paramBoolean, int paramInt2) {
    while (true) {
      if (this._inputPtr >= this._inputEnd && !loadMore()) {
        this._textBuffer.setCurrentLength(paramInt1);
        return resetInt(paramBoolean, paramInt2);
      } 
      byte[] arrayOfByte = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = i + 1;
      int j = arrayOfByte[i] & 0xFF;
      if (j > 57 || j < 48) {
        if (j == 46 || j == 101 || j == 69)
          return _parseFloatText(paramArrayOfchar, paramInt1, j, paramBoolean, paramInt2); 
      } else {
        if (paramInt1 >= paramArrayOfchar.length) {
          paramArrayOfchar = this._textBuffer.finishCurrentSegment();
          paramInt1 = 0;
        } 
        i = paramInt1 + 1;
        paramArrayOfchar[paramInt1] = (char)j;
        paramInt2++;
        paramInt1 = i;
        continue;
      } 
      this._inputPtr--;
      this._textBuffer.setCurrentLength(paramInt1);
      return resetInt(paramBoolean, paramInt2);
    } 
  }
  
  private final void _skipCComment() {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfByte[i] & 0xFF;
        int j = arrayOfInt[i];
        if (j != 0) {
          switch (j) {
            default:
              _reportInvalidChar(i);
              continue;
            case 42:
              if (this._inputBuffer[this._inputPtr] == 47) {
                this._inputPtr++;
                return;
              } 
              continue;
            case 10:
              _skipLF();
              continue;
            case 13:
              _skipCR();
              continue;
            case 2:
              _skipUtf8_2(i);
              continue;
            case 3:
              _skipUtf8_3(i);
              continue;
            case 4:
              break;
          } 
          _skipUtf8_4(i);
        } 
        continue;
      } 
      _reportInvalidEOF(" in a comment");
      return;
    } 
  }
  
  private final int _skipColon() {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    if (i == 58) {
      if (this._inputPtr < this._inputEnd) {
        i = this._inputBuffer[this._inputPtr] & 0xFF;
        if (i > 32 && i != 47) {
          this._inputPtr++;
          return i;
        } 
      } 
    } else {
      i &= 0xFF;
      while (true) {
        switch (i) {
          case 9:
          case 13:
          case 32:
            _skipCR();
            break;
          case 10:
            _skipLF();
            break;
          case 47:
            _skipComment();
            break;
        } 
      } 
    } 
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        arrayOfByte = this._inputBuffer;
        i = this._inputPtr;
        this._inputPtr = i + 1;
        int j = arrayOfByte[i] & 0xFF;
        if (j > 32) {
          i = j;
          if (j == 47) {
            _skipComment();
            continue;
          } 
          return i;
        } 
        if (j != 32) {
          if (j == 10) {
            _skipLF();
            continue;
          } 
          if (j == 13) {
            _skipCR();
            continue;
          } 
          if (j != 9)
            _throwInvalidSpace(j); 
        } 
        continue;
      } 
      throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
    } 
    break;
  }
  
  private final void _skipComment() {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS))
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)"); 
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(" in a comment"); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    if (i == 47) {
      _skipCppComment();
      return;
    } 
    if (i == 42) {
      _skipCComment();
      return;
    } 
    _reportUnexpectedChar(i, "was expecting either '*' or '/' for a comment");
  }
  
  private final void _skipCppComment() {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfByte[i] & 0xFF;
        int j = arrayOfInt[i];
        if (j != 0) {
          switch (j) {
            case 42:
              continue;
            default:
              _reportInvalidChar(i);
              continue;
            case 10:
              _skipLF();
              return;
            case 13:
              _skipCR();
              return;
            case 2:
              _skipUtf8_2(i);
              continue;
            case 3:
              _skipUtf8_3(i);
              continue;
            case 4:
              break;
          } 
          _skipUtf8_4(i);
        } 
        continue;
      } 
      return;
    } 
  }
  
  private final void _skipUtf8_2(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
  }
  
  private final void _skipUtf8_3(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
  }
  
  private final void _skipUtf8_4(int paramInt) {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128)
      _reportInvalidOther(paramInt & 0xFF, this._inputPtr); 
  }
  
  private final int _skipWS() {
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfByte[i] & 0xFF;
        if (i > 32) {
          if (i != 47)
            return i; 
          _skipComment();
          continue;
        } 
        if (i != 32) {
          if (i == 10) {
            _skipLF();
            continue;
          } 
          if (i == 13) {
            _skipCR();
            continue;
          } 
          if (i != 9)
            _throwInvalidSpace(i); 
        } 
        continue;
      } 
      throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
    } 
  }
  
  private final int _skipWSOrEnd() {
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfByte[i] & 0xFF;
        if (i > 32) {
          if (i == 47) {
            _skipComment();
            continue;
          } 
          return i;
        } 
        if (i != 32) {
          if (i == 10) {
            _skipLF();
            continue;
          } 
          if (i == 13) {
            _skipCR();
            continue;
          } 
          if (i != 9)
            _throwInvalidSpace(i); 
        } 
        continue;
      } 
      _handleEOF();
      return -1;
    } 
  }
  
  private final int _verifyNoLeadingZeroes() {
    if (this._inputPtr >= this._inputEnd && !loadMore())
      return 48; 
    int j = this._inputBuffer[this._inputPtr] & 0xFF;
    if (j < 48 || j > 57)
      return 48; 
    if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
      reportInvalidNumber("Leading zeroes not allowed"); 
    this._inputPtr++;
    int i = j;
    if (j == 48) {
      i = j;
      while (true) {
        if (this._inputPtr < this._inputEnd || loadMore()) {
          j = this._inputBuffer[this._inputPtr] & 0xFF;
          if (j < 48 || j > 57)
            return 48; 
          this._inputPtr++;
          i = j;
          if (j != 48)
            return j; 
          continue;
        } 
        return i;
      } 
    } 
    return i;
  }
  
  private final Name addName(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_2
    //   1: iconst_2
    //   2: ishl
    //   3: iconst_4
    //   4: isub
    //   5: iload_3
    //   6: iadd
    //   7: istore #11
    //   9: iload_3
    //   10: iconst_4
    //   11: if_icmpge -> 447
    //   14: aload_1
    //   15: iload_2
    //   16: iconst_1
    //   17: isub
    //   18: iaload
    //   19: istore #10
    //   21: aload_1
    //   22: iload_2
    //   23: iconst_1
    //   24: isub
    //   25: iload #10
    //   27: iconst_4
    //   28: iload_3
    //   29: isub
    //   30: iconst_3
    //   31: ishl
    //   32: ishl
    //   33: iastore
    //   34: aload_0
    //   35: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   38: invokevirtual emptyAndGetCurrentSegment : ()[C
    //   41: astore #12
    //   43: iconst_0
    //   44: istore #7
    //   46: iconst_0
    //   47: istore #4
    //   49: iload #4
    //   51: iload #11
    //   53: if_icmpge -> 518
    //   56: aload_1
    //   57: iload #4
    //   59: iconst_2
    //   60: ishr
    //   61: iaload
    //   62: iconst_3
    //   63: iload #4
    //   65: iconst_3
    //   66: iand
    //   67: isub
    //   68: iconst_3
    //   69: ishl
    //   70: ishr
    //   71: sipush #255
    //   74: iand
    //   75: istore #5
    //   77: iload #4
    //   79: iconst_1
    //   80: iadd
    //   81: istore #6
    //   83: iload #5
    //   85: istore #9
    //   87: iload #6
    //   89: istore #8
    //   91: iload #5
    //   93: bipush #127
    //   95: if_icmple -> 556
    //   98: iload #5
    //   100: sipush #224
    //   103: iand
    //   104: sipush #192
    //   107: if_icmpne -> 453
    //   110: iload #5
    //   112: bipush #31
    //   114: iand
    //   115: istore #4
    //   117: iconst_1
    //   118: istore #5
    //   120: iload #6
    //   122: iload #5
    //   124: iadd
    //   125: iload #11
    //   127: if_icmple -> 137
    //   130: aload_0
    //   131: ldc_w ' in field name'
    //   134: invokevirtual _reportInvalidEOF : (Ljava/lang/String;)V
    //   137: aload_1
    //   138: iload #6
    //   140: iconst_2
    //   141: ishr
    //   142: iaload
    //   143: iconst_3
    //   144: iload #6
    //   146: iconst_3
    //   147: iand
    //   148: isub
    //   149: iconst_3
    //   150: ishl
    //   151: ishr
    //   152: istore #9
    //   154: iload #6
    //   156: iconst_1
    //   157: iadd
    //   158: istore #8
    //   160: iload #9
    //   162: sipush #192
    //   165: iand
    //   166: sipush #128
    //   169: if_icmpeq -> 178
    //   172: aload_0
    //   173: iload #9
    //   175: invokevirtual _reportInvalidOther : (I)V
    //   178: iload #4
    //   180: bipush #6
    //   182: ishl
    //   183: iload #9
    //   185: bipush #63
    //   187: iand
    //   188: ior
    //   189: istore #9
    //   191: iload #9
    //   193: istore #6
    //   195: iload #8
    //   197: istore #4
    //   199: iload #5
    //   201: iconst_1
    //   202: if_icmple -> 328
    //   205: aload_1
    //   206: iload #8
    //   208: iconst_2
    //   209: ishr
    //   210: iaload
    //   211: iconst_3
    //   212: iload #8
    //   214: iconst_3
    //   215: iand
    //   216: isub
    //   217: iconst_3
    //   218: ishl
    //   219: ishr
    //   220: istore #4
    //   222: iinc #8, 1
    //   225: iload #4
    //   227: sipush #192
    //   230: iand
    //   231: sipush #128
    //   234: if_icmpeq -> 243
    //   237: aload_0
    //   238: iload #4
    //   240: invokevirtual _reportInvalidOther : (I)V
    //   243: iload #9
    //   245: bipush #6
    //   247: ishl
    //   248: iload #4
    //   250: bipush #63
    //   252: iand
    //   253: ior
    //   254: istore #9
    //   256: iload #9
    //   258: istore #6
    //   260: iload #8
    //   262: istore #4
    //   264: iload #5
    //   266: iconst_2
    //   267: if_icmple -> 328
    //   270: aload_1
    //   271: iload #8
    //   273: iconst_2
    //   274: ishr
    //   275: iaload
    //   276: iconst_3
    //   277: iload #8
    //   279: iconst_3
    //   280: iand
    //   281: isub
    //   282: iconst_3
    //   283: ishl
    //   284: ishr
    //   285: istore #6
    //   287: iload #8
    //   289: iconst_1
    //   290: iadd
    //   291: istore #4
    //   293: iload #6
    //   295: sipush #192
    //   298: iand
    //   299: sipush #128
    //   302: if_icmpeq -> 315
    //   305: aload_0
    //   306: iload #6
    //   308: sipush #255
    //   311: iand
    //   312: invokevirtual _reportInvalidOther : (I)V
    //   315: iload #9
    //   317: bipush #6
    //   319: ishl
    //   320: iload #6
    //   322: bipush #63
    //   324: iand
    //   325: ior
    //   326: istore #6
    //   328: iload #6
    //   330: istore #9
    //   332: iload #4
    //   334: istore #8
    //   336: iload #5
    //   338: iconst_2
    //   339: if_icmple -> 556
    //   342: iload #6
    //   344: ldc 65536
    //   346: isub
    //   347: istore #6
    //   349: aload #12
    //   351: astore #13
    //   353: iload #7
    //   355: aload #12
    //   357: arraylength
    //   358: if_icmplt -> 370
    //   361: aload_0
    //   362: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   365: invokevirtual expandCurrentSegment : ()[C
    //   368: astore #13
    //   370: aload #13
    //   372: iload #7
    //   374: ldc 55296
    //   376: iload #6
    //   378: bipush #10
    //   380: ishr
    //   381: iadd
    //   382: i2c
    //   383: castore
    //   384: iload #7
    //   386: iconst_1
    //   387: iadd
    //   388: istore #5
    //   390: aload #13
    //   392: astore #12
    //   394: iload #6
    //   396: sipush #1023
    //   399: iand
    //   400: ldc 56320
    //   402: ior
    //   403: istore #6
    //   405: aload #12
    //   407: astore #13
    //   409: iload #5
    //   411: aload #12
    //   413: arraylength
    //   414: if_icmplt -> 426
    //   417: aload_0
    //   418: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   421: invokevirtual expandCurrentSegment : ()[C
    //   424: astore #13
    //   426: iload #5
    //   428: iconst_1
    //   429: iadd
    //   430: istore #7
    //   432: aload #13
    //   434: iload #5
    //   436: iload #6
    //   438: i2c
    //   439: castore
    //   440: aload #13
    //   442: astore #12
    //   444: goto -> 49
    //   447: iconst_0
    //   448: istore #10
    //   450: goto -> 34
    //   453: iload #5
    //   455: sipush #240
    //   458: iand
    //   459: sipush #224
    //   462: if_icmpne -> 478
    //   465: iload #5
    //   467: bipush #15
    //   469: iand
    //   470: istore #4
    //   472: iconst_2
    //   473: istore #5
    //   475: goto -> 120
    //   478: iload #5
    //   480: sipush #248
    //   483: iand
    //   484: sipush #240
    //   487: if_icmpne -> 503
    //   490: iload #5
    //   492: bipush #7
    //   494: iand
    //   495: istore #4
    //   497: iconst_3
    //   498: istore #5
    //   500: goto -> 120
    //   503: aload_0
    //   504: iload #5
    //   506: invokevirtual _reportInvalidInitial : (I)V
    //   509: iconst_1
    //   510: istore #4
    //   512: iconst_1
    //   513: istore #5
    //   515: goto -> 120
    //   518: new java/lang/String
    //   521: dup
    //   522: aload #12
    //   524: iconst_0
    //   525: iload #7
    //   527: invokespecial <init> : ([CII)V
    //   530: astore #12
    //   532: iload_3
    //   533: iconst_4
    //   534: if_icmpge -> 544
    //   537: aload_1
    //   538: iload_2
    //   539: iconst_1
    //   540: isub
    //   541: iload #10
    //   543: iastore
    //   544: aload_0
    //   545: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer;
    //   548: aload #12
    //   550: aload_1
    //   551: iload_2
    //   552: invokevirtual addName : (Ljava/lang/String;[II)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   555: areturn
    //   556: iload #9
    //   558: istore #6
    //   560: iload #8
    //   562: istore #4
    //   564: iload #7
    //   566: istore #5
    //   568: goto -> 405
  }
  
  private final Name findName(int paramInt1, int paramInt2) {
    Name name = this._symbols.findName(paramInt1);
    if (name == null) {
      this._quadBuffer[0] = paramInt1;
      name = addName(this._quadBuffer, 1, paramInt2);
    } 
    return name;
  }
  
  private final Name findName(int paramInt1, int paramInt2, int paramInt3) {
    Name name = this._symbols.findName(paramInt1, paramInt2);
    if (name == null) {
      this._quadBuffer[0] = paramInt1;
      this._quadBuffer[1] = paramInt2;
      name = addName(this._quadBuffer, 2, paramInt3);
    } 
    return name;
  }
  
  private final Name findName(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3) {
    int[] arrayOfInt = paramArrayOfint;
    if (paramInt1 >= paramArrayOfint.length) {
      arrayOfInt = growArrayBy(paramArrayOfint, paramArrayOfint.length);
      this._quadBuffer = arrayOfInt;
    } 
    int i = paramInt1 + 1;
    arrayOfInt[paramInt1] = paramInt2;
    Name name2 = this._symbols.findName(arrayOfInt, i);
    Name name1 = name2;
    if (name2 == null)
      name1 = addName(arrayOfInt, i, paramInt3); 
    return name1;
  }
  
  public static int[] growArrayBy(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return new int[paramInt]; 
    int i = paramArrayOfint.length;
    int[] arrayOfInt = new int[i + paramInt];
    System.arraycopy(paramArrayOfint, 0, arrayOfInt, 0, i);
    return arrayOfInt;
  }
  
  private int nextByte() {
    if (this._inputPtr >= this._inputEnd)
      loadMoreGuaranteed(); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    return arrayOfByte[i] & 0xFF;
  }
  
  private final Name parseFieldName(int paramInt1, int paramInt2, int paramInt3) {
    return parseEscapedFieldName(this._quadBuffer, 0, paramInt1, paramInt2, paramInt3);
  }
  
  private final Name parseFieldName(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this._quadBuffer[0] = paramInt1;
    return parseEscapedFieldName(this._quadBuffer, 1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void _closeInput() {
    if (this._inputStream != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._inputStream.close(); 
      this._inputStream = null;
    } 
  }
  
  protected byte[] _decodeBase64(Base64Variant paramBase64Variant) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual _getByteArrayBuilder : ()Lcom/flurry/org/codehaus/jackson/util/ByteArrayBuilder;
    //   4: astore #7
    //   6: aload_0
    //   7: getfield _inputPtr : I
    //   10: aload_0
    //   11: getfield _inputEnd : I
    //   14: if_icmplt -> 21
    //   17: aload_0
    //   18: invokevirtual loadMoreGuaranteed : ()V
    //   21: aload_0
    //   22: getfield _inputBuffer : [B
    //   25: astore #8
    //   27: aload_0
    //   28: getfield _inputPtr : I
    //   31: istore_2
    //   32: aload_0
    //   33: iload_2
    //   34: iconst_1
    //   35: iadd
    //   36: putfield _inputPtr : I
    //   39: aload #8
    //   41: iload_2
    //   42: baload
    //   43: sipush #255
    //   46: iand
    //   47: istore #4
    //   49: iload #4
    //   51: bipush #32
    //   53: if_icmple -> 6
    //   56: aload_1
    //   57: iload #4
    //   59: invokevirtual decodeBase64Char : (I)I
    //   62: istore_3
    //   63: iload_3
    //   64: istore_2
    //   65: iload_3
    //   66: ifge -> 97
    //   69: iload #4
    //   71: bipush #34
    //   73: if_icmpne -> 84
    //   76: aload #7
    //   78: invokevirtual toByteArray : ()[B
    //   81: astore_1
    //   82: aload_1
    //   83: areturn
    //   84: aload_0
    //   85: aload_1
    //   86: iload #4
    //   88: iconst_0
    //   89: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;II)I
    //   92: istore_2
    //   93: iload_2
    //   94: iflt -> 6
    //   97: aload_0
    //   98: getfield _inputPtr : I
    //   101: aload_0
    //   102: getfield _inputEnd : I
    //   105: if_icmplt -> 112
    //   108: aload_0
    //   109: invokevirtual loadMoreGuaranteed : ()V
    //   112: aload_0
    //   113: getfield _inputBuffer : [B
    //   116: astore #8
    //   118: aload_0
    //   119: getfield _inputPtr : I
    //   122: istore_3
    //   123: aload_0
    //   124: iload_3
    //   125: iconst_1
    //   126: iadd
    //   127: putfield _inputPtr : I
    //   130: aload #8
    //   132: iload_3
    //   133: baload
    //   134: sipush #255
    //   137: iand
    //   138: istore #5
    //   140: aload_1
    //   141: iload #5
    //   143: invokevirtual decodeBase64Char : (I)I
    //   146: istore #4
    //   148: iload #4
    //   150: istore_3
    //   151: iload #4
    //   153: ifge -> 165
    //   156: aload_0
    //   157: aload_1
    //   158: iload #5
    //   160: iconst_1
    //   161: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;II)I
    //   164: istore_3
    //   165: iload_3
    //   166: iload_2
    //   167: bipush #6
    //   169: ishl
    //   170: ior
    //   171: istore #5
    //   173: aload_0
    //   174: getfield _inputPtr : I
    //   177: aload_0
    //   178: getfield _inputEnd : I
    //   181: if_icmplt -> 188
    //   184: aload_0
    //   185: invokevirtual loadMoreGuaranteed : ()V
    //   188: aload_0
    //   189: getfield _inputBuffer : [B
    //   192: astore #8
    //   194: aload_0
    //   195: getfield _inputPtr : I
    //   198: istore_2
    //   199: aload_0
    //   200: iload_2
    //   201: iconst_1
    //   202: iadd
    //   203: putfield _inputPtr : I
    //   206: aload #8
    //   208: iload_2
    //   209: baload
    //   210: sipush #255
    //   213: iand
    //   214: istore #6
    //   216: aload_1
    //   217: iload #6
    //   219: invokevirtual decodeBase64Char : (I)I
    //   222: istore_3
    //   223: iload_3
    //   224: istore #4
    //   226: iload_3
    //   227: ifge -> 387
    //   230: iload_3
    //   231: istore_2
    //   232: iload_3
    //   233: bipush #-2
    //   235: if_icmpeq -> 279
    //   238: iload #6
    //   240: bipush #34
    //   242: if_icmpne -> 270
    //   245: aload_1
    //   246: invokevirtual usesPadding : ()Z
    //   249: ifne -> 270
    //   252: aload #7
    //   254: iload #5
    //   256: iconst_4
    //   257: ishr
    //   258: invokevirtual append : (I)V
    //   261: aload #7
    //   263: invokevirtual toByteArray : ()[B
    //   266: astore_1
    //   267: goto -> 82
    //   270: aload_0
    //   271: aload_1
    //   272: iload #6
    //   274: iconst_2
    //   275: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;II)I
    //   278: istore_2
    //   279: iload_2
    //   280: istore #4
    //   282: iload_2
    //   283: bipush #-2
    //   285: if_icmpne -> 387
    //   288: aload_0
    //   289: getfield _inputPtr : I
    //   292: aload_0
    //   293: getfield _inputEnd : I
    //   296: if_icmplt -> 303
    //   299: aload_0
    //   300: invokevirtual loadMoreGuaranteed : ()V
    //   303: aload_0
    //   304: getfield _inputBuffer : [B
    //   307: astore #8
    //   309: aload_0
    //   310: getfield _inputPtr : I
    //   313: istore_2
    //   314: aload_0
    //   315: iload_2
    //   316: iconst_1
    //   317: iadd
    //   318: putfield _inputPtr : I
    //   321: aload #8
    //   323: iload_2
    //   324: baload
    //   325: sipush #255
    //   328: iand
    //   329: istore_2
    //   330: aload_1
    //   331: iload_2
    //   332: invokevirtual usesPaddingChar : (I)Z
    //   335: ifne -> 375
    //   338: aload_0
    //   339: aload_1
    //   340: iload_2
    //   341: iconst_3
    //   342: new java/lang/StringBuilder
    //   345: dup
    //   346: invokespecial <init> : ()V
    //   349: ldc_w 'expected padding character ''
    //   352: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: aload_1
    //   356: invokevirtual getPaddingChar : ()C
    //   359: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   362: ldc_w '''
    //   365: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: invokevirtual toString : ()Ljava/lang/String;
    //   371: invokevirtual reportInvalidBase64Char : (Lcom/flurry/org/codehaus/jackson/Base64Variant;IILjava/lang/String;)Ljava/lang/IllegalArgumentException;
    //   374: athrow
    //   375: aload #7
    //   377: iload #5
    //   379: iconst_4
    //   380: ishr
    //   381: invokevirtual append : (I)V
    //   384: goto -> 6
    //   387: iload #5
    //   389: bipush #6
    //   391: ishl
    //   392: iload #4
    //   394: ior
    //   395: istore #5
    //   397: aload_0
    //   398: getfield _inputPtr : I
    //   401: aload_0
    //   402: getfield _inputEnd : I
    //   405: if_icmplt -> 412
    //   408: aload_0
    //   409: invokevirtual loadMoreGuaranteed : ()V
    //   412: aload_0
    //   413: getfield _inputBuffer : [B
    //   416: astore #8
    //   418: aload_0
    //   419: getfield _inputPtr : I
    //   422: istore_2
    //   423: aload_0
    //   424: iload_2
    //   425: iconst_1
    //   426: iadd
    //   427: putfield _inputPtr : I
    //   430: aload #8
    //   432: iload_2
    //   433: baload
    //   434: sipush #255
    //   437: iand
    //   438: istore #6
    //   440: aload_1
    //   441: iload #6
    //   443: invokevirtual decodeBase64Char : (I)I
    //   446: istore #4
    //   448: iload #4
    //   450: istore_3
    //   451: iload #4
    //   453: ifge -> 527
    //   456: iload #4
    //   458: istore_2
    //   459: iload #4
    //   461: bipush #-2
    //   463: if_icmpeq -> 507
    //   466: iload #6
    //   468: bipush #34
    //   470: if_icmpne -> 498
    //   473: aload_1
    //   474: invokevirtual usesPadding : ()Z
    //   477: ifne -> 498
    //   480: aload #7
    //   482: iload #5
    //   484: iconst_2
    //   485: ishr
    //   486: invokevirtual appendTwoBytes : (I)V
    //   489: aload #7
    //   491: invokevirtual toByteArray : ()[B
    //   494: astore_1
    //   495: goto -> 82
    //   498: aload_0
    //   499: aload_1
    //   500: iload #6
    //   502: iconst_3
    //   503: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;II)I
    //   506: istore_2
    //   507: iload_2
    //   508: istore_3
    //   509: iload_2
    //   510: bipush #-2
    //   512: if_icmpne -> 527
    //   515: aload #7
    //   517: iload #5
    //   519: iconst_2
    //   520: ishr
    //   521: invokevirtual appendTwoBytes : (I)V
    //   524: goto -> 6
    //   527: aload #7
    //   529: iload_3
    //   530: iload #5
    //   532: bipush #6
    //   534: ishl
    //   535: ior
    //   536: invokevirtual appendThreeBytes : (I)V
    //   539: goto -> 6
  }
  
  protected int _decodeCharForError(int paramInt) {
    int i = paramInt;
    if (paramInt < 0) {
      int j;
      if ((paramInt & 0xE0) == 192) {
        paramInt &= 0x1F;
        j = 1;
      } else if ((paramInt & 0xF0) == 224) {
        paramInt &= 0xF;
        j = 2;
      } else if ((paramInt & 0xF8) == 240) {
        paramInt &= 0x7;
        j = 3;
      } else {
        _reportInvalidInitial(paramInt & 0xFF);
        j = 1;
      } 
      i = nextByte();
      if ((i & 0xC0) != 128)
        _reportInvalidOther(i & 0xFF); 
      paramInt = paramInt << 6 | i & 0x3F;
      i = paramInt;
      if (j > 1) {
        i = nextByte();
        if ((i & 0xC0) != 128)
          _reportInvalidOther(i & 0xFF); 
        paramInt = paramInt << 6 | i & 0x3F;
        i = paramInt;
        if (j > 2) {
          j = nextByte();
          if ((j & 0xC0) != 128)
            _reportInvalidOther(j & 0xFF); 
          i = paramInt << 6 | j & 0x3F;
        } 
      } 
    } 
    return i;
  }
  
  protected final char _decodeEscaped() {
    byte b = 0;
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(" in character escape sequence"); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i];
    switch (i) {
      default:
        return _handleUnrecognizedCharacterEscape((char)_decodeCharForError(i));
      case 98:
        return '\b';
      case 116:
        return '\t';
      case 110:
        return '\n';
      case 102:
        return '\f';
      case 114:
        return '\r';
      case 34:
      case 47:
      case 92:
        return (char)i;
      case 117:
        break;
    } 
    i = 0;
    while (b < 4) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(" in character escape sequence"); 
      arrayOfByte = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = j + 1;
      j = arrayOfByte[j];
      int k = CharTypes.charToHex(j);
      if (k < 0)
        _reportUnexpectedChar(j, "expected a hex-digit for character escape sequence"); 
      i = i << 4 | k;
      b++;
    } 
    return (char)i;
  }
  
  protected void _finishString() {
    // Byte code:
    //   0: aload_0
    //   1: getfield _inputPtr : I
    //   4: istore_2
    //   5: iload_2
    //   6: istore_1
    //   7: iload_2
    //   8: aload_0
    //   9: getfield _inputEnd : I
    //   12: if_icmplt -> 24
    //   15: aload_0
    //   16: invokevirtual loadMoreGuaranteed : ()V
    //   19: aload_0
    //   20: getfield _inputPtr : I
    //   23: istore_1
    //   24: aload_0
    //   25: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   28: invokevirtual emptyAndGetCurrentSegment : ()[C
    //   31: astore #7
    //   33: getstatic com/flurry/org/codehaus/jackson/impl/Utf8StreamParser.sInputCodesUtf8 : [I
    //   36: astore #6
    //   38: aload_0
    //   39: getfield _inputEnd : I
    //   42: aload #7
    //   44: arraylength
    //   45: iload_1
    //   46: iadd
    //   47: invokestatic min : (II)I
    //   50: istore_3
    //   51: aload_0
    //   52: getfield _inputBuffer : [B
    //   55: astore #5
    //   57: iconst_0
    //   58: istore_2
    //   59: iload_1
    //   60: iload_3
    //   61: if_icmpge -> 121
    //   64: aload #5
    //   66: iload_1
    //   67: baload
    //   68: sipush #255
    //   71: iand
    //   72: istore #4
    //   74: aload #6
    //   76: iload #4
    //   78: iaload
    //   79: ifeq -> 105
    //   82: iload #4
    //   84: bipush #34
    //   86: if_icmpne -> 121
    //   89: aload_0
    //   90: iload_1
    //   91: iconst_1
    //   92: iadd
    //   93: putfield _inputPtr : I
    //   96: aload_0
    //   97: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   100: iload_2
    //   101: invokevirtual setCurrentLength : (I)V
    //   104: return
    //   105: aload #7
    //   107: iload_2
    //   108: iload #4
    //   110: i2c
    //   111: castore
    //   112: iinc #2, 1
    //   115: iinc #1, 1
    //   118: goto -> 59
    //   121: aload_0
    //   122: iload_1
    //   123: putfield _inputPtr : I
    //   126: aload_0
    //   127: aload #7
    //   129: iload_2
    //   130: invokespecial _finishString2 : ([CI)V
    //   133: goto -> 104
  }
  
  protected final String _getText2(JsonToken paramJsonToken) {
    if (paramJsonToken == null)
      return null; 
    switch (Utf8StreamParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonToken.ordinal()]) {
      default:
        return paramJsonToken.asString();
      case 1:
        return this._parsingContext.getCurrentName();
      case 2:
      case 3:
      case 4:
        break;
    } 
    return this._textBuffer.contentsAsString();
  }
  
  protected JsonToken _handleApostropheValue() {
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = sInputCodesUtf8;
    byte[] arrayOfByte = this._inputBuffer;
    int i = 0;
    label48: while (true) {
      if (this._inputPtr >= this._inputEnd)
        loadMoreGuaranteed(); 
      char[] arrayOfChar1 = arrayOfChar;
      int j = i;
      if (i >= arrayOfChar.length) {
        arrayOfChar1 = this._textBuffer.finishCurrentSegment();
        j = 0;
      } 
      int k = this._inputEnd;
      i = this._inputPtr + arrayOfChar1.length - j;
      if (i < k)
        k = i; 
      while (true) {
        arrayOfChar = arrayOfChar1;
        i = j;
        if (this._inputPtr < k) {
          i = this._inputPtr;
          this._inputPtr = i + 1;
          i = arrayOfByte[i] & 0xFF;
          if (i == 39 || arrayOfInt[i] != 0) {
            if (i == 39) {
              this._textBuffer.setCurrentLength(j);
              return JsonToken.VALUE_STRING;
            } 
            break;
          } 
          arrayOfChar1[j] = (char)i;
          j++;
          continue;
        } 
        continue label48;
      } 
      switch (arrayOfInt[i]) {
        case 1:
          if (i != 34)
            i = _decodeEscaped(); 
          if (j >= arrayOfChar1.length) {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            j = 0;
          } 
          k = j + 1;
          arrayOfChar1[j] = (char)i;
          arrayOfChar = arrayOfChar1;
          i = k;
          break;
        case 2:
          i = _decodeUtf8_2(i);
          if (j >= arrayOfChar1.length) {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            j = 0;
          } 
          k = j + 1;
          arrayOfChar1[j] = (char)i;
          arrayOfChar = arrayOfChar1;
          i = k;
          break;
        case 3:
          if (this._inputEnd - this._inputPtr >= 2) {
            i = _decodeUtf8_3fast(i);
          } else {
            i = _decodeUtf8_3(i);
          } 
          if (j >= arrayOfChar1.length) {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            j = 0;
          } 
          k = j + 1;
          arrayOfChar1[j] = (char)i;
          arrayOfChar = arrayOfChar1;
          i = k;
          break;
        case 4:
          k = _decodeUtf8_4(i);
          i = j + 1;
          arrayOfChar1[j] = (char)(0xD800 | k >> 10);
          if (i >= arrayOfChar1.length) {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            j = 0;
          } else {
            j = i;
          } 
          i = 0xDC00 | k & 0x3FF;
          if (j >= arrayOfChar1.length) {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            j = 0;
          } 
          k = j + 1;
          arrayOfChar1[j] = (char)i;
          arrayOfChar = arrayOfChar1;
          i = k;
          break;
      } 
    } 
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean) {
    double d = Double.NEGATIVE_INFINITY;
    int i = paramInt;
    if (paramInt == 73) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOFInValue(); 
      byte[] arrayOfByte = this._inputBuffer;
      paramInt = this._inputPtr;
      this._inputPtr = paramInt + 1;
      paramInt = arrayOfByte[paramInt];
      if (paramInt == 78) {
        String str;
        if (paramBoolean) {
          str = "-INF";
        } else {
          str = "+INF";
        } 
        _matchToken(str, 3);
        if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
          if (!paramBoolean)
            d = Double.POSITIVE_INFINITY; 
          return resetAsNaN(str, d);
        } 
        _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        i = paramInt;
      } else {
        i = paramInt;
        if (paramInt == 110) {
          String str;
          if (paramBoolean) {
            str = "-Infinity";
          } else {
            str = "+Infinity";
          } 
          _matchToken(str, 3);
          if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            if (!paramBoolean)
              d = Double.POSITIVE_INFINITY; 
            return resetAsNaN(str, d);
          } 
          _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
          i = paramInt;
        } 
      } 
    } 
    reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
    return null;
  }
  
  protected JsonToken _handleUnexpectedValue(int paramInt) {
    switch (paramInt) {
      default:
        _reportUnexpectedChar(paramInt, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
      case 39:
        if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))
          return _handleApostropheValue(); 
      case 78:
        _matchToken("NaN", 1);
        if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
          return resetAsNaN("NaN", Double.NaN); 
        _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
      case 43:
        break;
    } 
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOFInValue(); 
    byte[] arrayOfByte = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    return _handleInvalidNumberStart(arrayOfByte[paramInt] & 0xFF, false);
  }
  
  protected final Name _handleUnusualFieldName(int paramInt) {
    if (paramInt == 39 && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))
      return _parseApostropheFieldName(); 
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES))
      _reportUnexpectedChar(paramInt, "was expecting double-quote to start field name"); 
    int[] arrayOfInt2 = CharTypes.getInputCodeUtf8JsNames();
    if (arrayOfInt2[paramInt] != 0)
      _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name"); 
    int[] arrayOfInt1 = this._quadBuffer;
    int k = 0;
    int j = 0;
    int i = paramInt;
    paramInt = 0;
    while (true) {
      if (k < 4) {
        j = i | j << 8;
        i = k + 1;
      } else {
        int[] arrayOfInt = arrayOfInt1;
        if (paramInt >= arrayOfInt1.length) {
          arrayOfInt = growArrayBy(arrayOfInt1, arrayOfInt1.length);
          this._quadBuffer = arrayOfInt;
        } 
        arrayOfInt[paramInt] = j;
        arrayOfInt1 = arrayOfInt;
        k = 1;
        j = i;
        paramInt++;
        i = k;
      } 
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(" in field name"); 
      int m = this._inputBuffer[this._inputPtr] & 0xFF;
      if (arrayOfInt2[m] != 0) {
        k = paramInt;
        int[] arrayOfInt = arrayOfInt1;
        if (i > 0) {
          arrayOfInt = arrayOfInt1;
          if (paramInt >= arrayOfInt1.length) {
            arrayOfInt = growArrayBy(arrayOfInt1, arrayOfInt1.length);
            this._quadBuffer = arrayOfInt;
          } 
          arrayOfInt[paramInt] = j;
          k = paramInt + 1;
        } 
        Name name = this._symbols.findName(arrayOfInt, k);
        if (name == null)
          name = addName(arrayOfInt, k, i); 
        return name;
      } 
      this._inputPtr++;
      k = i;
      i = m;
    } 
  }
  
  protected final boolean _loadToHaveAtLeast(int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_0
    //   4: getfield _inputStream : Ljava/io/InputStream;
    //   7: ifnonnull -> 13
    //   10: iload #4
    //   12: ireturn
    //   13: aload_0
    //   14: getfield _inputEnd : I
    //   17: aload_0
    //   18: getfield _inputPtr : I
    //   21: isub
    //   22: istore_2
    //   23: iload_2
    //   24: ifle -> 169
    //   27: aload_0
    //   28: getfield _inputPtr : I
    //   31: ifle -> 169
    //   34: aload_0
    //   35: aload_0
    //   36: getfield _currInputProcessed : J
    //   39: aload_0
    //   40: getfield _inputPtr : I
    //   43: i2l
    //   44: ladd
    //   45: putfield _currInputProcessed : J
    //   48: aload_0
    //   49: aload_0
    //   50: getfield _currInputRowStart : I
    //   53: aload_0
    //   54: getfield _inputPtr : I
    //   57: isub
    //   58: putfield _currInputRowStart : I
    //   61: aload_0
    //   62: getfield _inputBuffer : [B
    //   65: aload_0
    //   66: getfield _inputPtr : I
    //   69: aload_0
    //   70: getfield _inputBuffer : [B
    //   73: iconst_0
    //   74: iload_2
    //   75: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: iload_2
    //   80: putfield _inputEnd : I
    //   83: aload_0
    //   84: iconst_0
    //   85: putfield _inputPtr : I
    //   88: aload_0
    //   89: getfield _inputEnd : I
    //   92: iload_1
    //   93: if_icmpge -> 190
    //   96: aload_0
    //   97: getfield _inputStream : Ljava/io/InputStream;
    //   100: aload_0
    //   101: getfield _inputBuffer : [B
    //   104: aload_0
    //   105: getfield _inputEnd : I
    //   108: aload_0
    //   109: getfield _inputBuffer : [B
    //   112: arraylength
    //   113: aload_0
    //   114: getfield _inputEnd : I
    //   117: isub
    //   118: invokevirtual read : ([BII)I
    //   121: istore_3
    //   122: iload_3
    //   123: iconst_1
    //   124: if_icmpge -> 177
    //   127: aload_0
    //   128: invokevirtual _closeInput : ()V
    //   131: iload_3
    //   132: ifne -> 10
    //   135: new java/io/IOException
    //   138: dup
    //   139: new java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial <init> : ()V
    //   146: ldc_w 'InputStream.read() returned 0 characters when trying to read '
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: iload_2
    //   153: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   156: ldc_w ' bytes'
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: invokespecial <init> : (Ljava/lang/String;)V
    //   168: athrow
    //   169: aload_0
    //   170: iconst_0
    //   171: putfield _inputEnd : I
    //   174: goto -> 83
    //   177: aload_0
    //   178: iload_3
    //   179: aload_0
    //   180: getfield _inputEnd : I
    //   183: iadd
    //   184: putfield _inputEnd : I
    //   187: goto -> 88
    //   190: iconst_1
    //   191: istore #4
    //   193: goto -> 10
  }
  
  protected final void _matchToken(String paramString, int paramInt) {
    int i = paramString.length();
    while (true) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(" in a value"); 
      if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt))
        _reportInvalidToken(paramString.substring(0, paramInt), "'null', 'true', 'false' or NaN"); 
      this._inputPtr++;
      int j = paramInt + 1;
      paramInt = j;
      if (j >= i) {
        if (this._inputPtr < this._inputEnd || loadMore()) {
          paramInt = this._inputBuffer[this._inputPtr] & 0xFF;
          if (paramInt >= 48 && paramInt != 93 && paramInt != 125 && Character.isJavaIdentifierPart((char)_decodeCharForError(paramInt))) {
            this._inputPtr++;
            _reportInvalidToken(paramString.substring(0, j), "'null', 'true', 'false' or NaN");
          } 
        } 
        return;
      } 
    } 
  }
  
  protected final Name _parseApostropheFieldName() {
    // Byte code:
    //   0: aload_0
    //   1: getfield _inputPtr : I
    //   4: aload_0
    //   5: getfield _inputEnd : I
    //   8: if_icmplt -> 25
    //   11: aload_0
    //   12: invokevirtual loadMore : ()Z
    //   15: ifne -> 25
    //   18: aload_0
    //   19: ldc_w ': was expecting closing ''' for name'
    //   22: invokevirtual _reportInvalidEOF : (Ljava/lang/String;)V
    //   25: aload_0
    //   26: getfield _inputBuffer : [B
    //   29: astore #6
    //   31: aload_0
    //   32: getfield _inputPtr : I
    //   35: istore_1
    //   36: aload_0
    //   37: iload_1
    //   38: iconst_1
    //   39: iadd
    //   40: putfield _inputPtr : I
    //   43: aload #6
    //   45: iload_1
    //   46: baload
    //   47: sipush #255
    //   50: iand
    //   51: istore #4
    //   53: iload #4
    //   55: bipush #39
    //   57: if_icmpne -> 68
    //   60: invokestatic getEmptyName : ()Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   63: astore #6
    //   65: aload #6
    //   67: areturn
    //   68: aload_0
    //   69: getfield _quadBuffer : [I
    //   72: astore #6
    //   74: getstatic com/flurry/org/codehaus/jackson/impl/Utf8StreamParser.sInputCodesLatin1 : [I
    //   77: astore #8
    //   79: iconst_0
    //   80: istore_2
    //   81: iconst_0
    //   82: istore_3
    //   83: iconst_0
    //   84: istore_1
    //   85: iload #4
    //   87: bipush #39
    //   89: if_icmpne -> 165
    //   92: iload_2
    //   93: ifle -> 568
    //   96: aload #6
    //   98: astore #7
    //   100: iload_1
    //   101: aload #6
    //   103: arraylength
    //   104: if_icmplt -> 123
    //   107: aload #6
    //   109: aload #6
    //   111: arraylength
    //   112: invokestatic growArrayBy : ([II)[I
    //   115: astore #7
    //   117: aload_0
    //   118: aload #7
    //   120: putfield _quadBuffer : [I
    //   123: aload #7
    //   125: iload_1
    //   126: iload_3
    //   127: iastore
    //   128: aload #7
    //   130: astore #6
    //   132: iinc #1, 1
    //   135: aload_0
    //   136: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer;
    //   139: aload #6
    //   141: iload_1
    //   142: invokevirtual findName : ([II)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   145: astore #7
    //   147: aload #7
    //   149: ifnonnull -> 561
    //   152: aload_0
    //   153: aload #6
    //   155: iload_1
    //   156: iload_2
    //   157: invokespecial addName : ([III)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   160: astore #6
    //   162: goto -> 65
    //   165: iload #4
    //   167: istore #5
    //   169: iload #4
    //   171: bipush #34
    //   173: if_icmpeq -> 580
    //   176: iload #4
    //   178: istore #5
    //   180: aload #8
    //   182: iload #4
    //   184: iaload
    //   185: ifeq -> 580
    //   188: iload #4
    //   190: bipush #92
    //   192: if_icmpeq -> 400
    //   195: aload_0
    //   196: iload #4
    //   198: ldc_w 'name'
    //   201: invokevirtual _throwUnquotedSpace : (ILjava/lang/String;)V
    //   204: iload #4
    //   206: istore #5
    //   208: iload #4
    //   210: bipush #127
    //   212: if_icmple -> 580
    //   215: iload_2
    //   216: iconst_4
    //   217: if_icmplt -> 577
    //   220: aload #6
    //   222: astore #7
    //   224: iload_1
    //   225: aload #6
    //   227: arraylength
    //   228: if_icmplt -> 247
    //   231: aload #6
    //   233: aload #6
    //   235: arraylength
    //   236: invokestatic growArrayBy : ([II)[I
    //   239: astore #7
    //   241: aload_0
    //   242: aload #7
    //   244: putfield _quadBuffer : [I
    //   247: aload #7
    //   249: iload_1
    //   250: iload_3
    //   251: iastore
    //   252: iconst_0
    //   253: istore_2
    //   254: iinc #1, 1
    //   257: iconst_0
    //   258: istore_3
    //   259: aload #7
    //   261: astore #6
    //   263: iload #4
    //   265: sipush #2048
    //   268: if_icmpge -> 409
    //   271: iload_3
    //   272: bipush #8
    //   274: ishl
    //   275: iload #4
    //   277: bipush #6
    //   279: ishr
    //   280: sipush #192
    //   283: ior
    //   284: ior
    //   285: istore_3
    //   286: iinc #2, 1
    //   289: iload_3
    //   290: istore #5
    //   292: iload_2
    //   293: istore_3
    //   294: iload #4
    //   296: bipush #63
    //   298: iand
    //   299: sipush #128
    //   302: ior
    //   303: istore_2
    //   304: iload_3
    //   305: istore #4
    //   307: iload #4
    //   309: iconst_4
    //   310: if_icmpge -> 507
    //   313: iload_2
    //   314: iload #5
    //   316: bipush #8
    //   318: ishl
    //   319: ior
    //   320: istore_3
    //   321: iinc #4, 1
    //   324: iload_1
    //   325: istore_2
    //   326: iload #4
    //   328: istore_1
    //   329: aload_0
    //   330: getfield _inputPtr : I
    //   333: aload_0
    //   334: getfield _inputEnd : I
    //   337: if_icmplt -> 354
    //   340: aload_0
    //   341: invokevirtual loadMore : ()Z
    //   344: ifne -> 354
    //   347: aload_0
    //   348: ldc_w ' in field name'
    //   351: invokevirtual _reportInvalidEOF : (Ljava/lang/String;)V
    //   354: aload_0
    //   355: getfield _inputBuffer : [B
    //   358: astore #7
    //   360: aload_0
    //   361: getfield _inputPtr : I
    //   364: istore #4
    //   366: aload_0
    //   367: iload #4
    //   369: iconst_1
    //   370: iadd
    //   371: putfield _inputPtr : I
    //   374: aload #7
    //   376: iload #4
    //   378: baload
    //   379: sipush #255
    //   382: iand
    //   383: istore #5
    //   385: iload_2
    //   386: istore #4
    //   388: iload_1
    //   389: istore_2
    //   390: iload #4
    //   392: istore_1
    //   393: iload #5
    //   395: istore #4
    //   397: goto -> 85
    //   400: aload_0
    //   401: invokevirtual _decodeEscaped : ()C
    //   404: istore #4
    //   406: goto -> 204
    //   409: iload_3
    //   410: bipush #8
    //   412: ishl
    //   413: iload #4
    //   415: bipush #12
    //   417: ishr
    //   418: sipush #224
    //   421: ior
    //   422: ior
    //   423: istore #5
    //   425: iload_2
    //   426: iconst_1
    //   427: iadd
    //   428: istore_3
    //   429: iload_3
    //   430: iconst_4
    //   431: if_icmplt -> 571
    //   434: aload #6
    //   436: astore #7
    //   438: iload_1
    //   439: aload #6
    //   441: arraylength
    //   442: if_icmplt -> 461
    //   445: aload #6
    //   447: aload #6
    //   449: arraylength
    //   450: invokestatic growArrayBy : ([II)[I
    //   453: astore #7
    //   455: aload_0
    //   456: aload #7
    //   458: putfield _quadBuffer : [I
    //   461: aload #7
    //   463: iload_1
    //   464: iload #5
    //   466: iastore
    //   467: iinc #1, 1
    //   470: aload #7
    //   472: astore #6
    //   474: iconst_0
    //   475: istore_3
    //   476: iconst_0
    //   477: istore_2
    //   478: iload_2
    //   479: bipush #8
    //   481: ishl
    //   482: iload #4
    //   484: bipush #6
    //   486: ishr
    //   487: bipush #63
    //   489: iand
    //   490: sipush #128
    //   493: ior
    //   494: ior
    //   495: istore #5
    //   497: iload_3
    //   498: iconst_1
    //   499: iadd
    //   500: istore_2
    //   501: iload #5
    //   503: istore_3
    //   504: goto -> 289
    //   507: aload #6
    //   509: astore #7
    //   511: iload_1
    //   512: aload #6
    //   514: arraylength
    //   515: if_icmplt -> 534
    //   518: aload #6
    //   520: aload #6
    //   522: arraylength
    //   523: invokestatic growArrayBy : ([II)[I
    //   526: astore #7
    //   528: aload_0
    //   529: aload #7
    //   531: putfield _quadBuffer : [I
    //   534: aload #7
    //   536: iload_1
    //   537: iload #5
    //   539: iastore
    //   540: aload #7
    //   542: astore #6
    //   544: iconst_1
    //   545: istore_3
    //   546: iload_1
    //   547: iconst_1
    //   548: iadd
    //   549: istore #4
    //   551: iload_3
    //   552: istore_1
    //   553: iload_2
    //   554: istore_3
    //   555: iload #4
    //   557: istore_2
    //   558: goto -> 329
    //   561: aload #7
    //   563: astore #6
    //   565: goto -> 65
    //   568: goto -> 135
    //   571: iload #5
    //   573: istore_2
    //   574: goto -> 478
    //   577: goto -> 263
    //   580: iload_2
    //   581: istore #4
    //   583: iload #5
    //   585: istore_2
    //   586: iload_3
    //   587: istore #5
    //   589: goto -> 307
  }
  
  protected final Name _parseFieldName(int paramInt) {
    if (paramInt != 34)
      return _handleUnusualFieldName(paramInt); 
    if (this._inputPtr + 9 > this._inputEnd)
      return slowParseFieldName(); 
    byte[] arrayOfByte = this._inputBuffer;
    int[] arrayOfInt = sInputCodesLatin1;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    paramInt = arrayOfByte[paramInt] & 0xFF;
    if (arrayOfInt[paramInt] == 0) {
      int i = this._inputPtr;
      this._inputPtr = i + 1;
      i = arrayOfByte[i] & 0xFF;
      if (arrayOfInt[i] == 0) {
        paramInt = paramInt << 8 | i;
        i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfByte[i] & 0xFF;
        if (arrayOfInt[i] == 0) {
          paramInt = paramInt << 8 | i;
          i = this._inputPtr;
          this._inputPtr = i + 1;
          i = arrayOfByte[i] & 0xFF;
          if (arrayOfInt[i] == 0) {
            paramInt = paramInt << 8 | i;
            i = this._inputPtr;
            this._inputPtr = i + 1;
            i = arrayOfByte[i] & 0xFF;
            if (arrayOfInt[i] == 0) {
              this._quad1 = paramInt;
              return parseMediumFieldName(i, arrayOfInt);
            } 
            return (i == 34) ? findName(paramInt, 4) : parseFieldName(paramInt, i, 4);
          } 
          return (i == 34) ? findName(paramInt, 3) : parseFieldName(paramInt, i, 3);
        } 
        return (i == 34) ? findName(paramInt, 2) : parseFieldName(paramInt, i, 2);
      } 
      return (i == 34) ? findName(paramInt, 1) : parseFieldName(paramInt, i, 1);
    } 
    return (paramInt == 34) ? BytesToNameCanonicalizer.getEmptyName() : parseFieldName(0, paramInt, 0);
  }
  
  protected void _releaseBuffers() {
    super._releaseBuffers();
    if (this._bufferRecyclable) {
      byte[] arrayOfByte = this._inputBuffer;
      if (arrayOfByte != null) {
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(arrayOfByte);
      } 
    } 
  }
  
  protected void _reportInvalidChar(int paramInt) {
    if (paramInt < 32)
      _throwInvalidSpace(paramInt); 
    _reportInvalidInitial(paramInt);
  }
  
  protected void _reportInvalidInitial(int paramInt) {
    _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt) {
    _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt1, int paramInt2) {
    this._inputPtr = paramInt2;
    _reportInvalidOther(paramInt1);
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(paramString1);
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = (char)_decodeCharForError(arrayOfByte[i]);
        if (Character.isJavaIdentifierPart(c)) {
          this._inputPtr++;
          stringBuilder.append(c);
          continue;
        } 
      } 
      _reportError("Unrecognized token '" + stringBuilder.toString() + "': was expecting " + paramString2);
      return;
    } 
  }
  
  protected final void _skipCR() {
    if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10)
      this._inputPtr++; 
    this._currInputRow++;
    this._currInputRowStart = this._inputPtr;
  }
  
  protected final void _skipLF() {
    this._currInputRow++;
    this._currInputRowStart = this._inputPtr;
  }
  
  protected void _skipString() {
    this._tokenIncomplete = false;
    int[] arrayOfInt = sInputCodesUtf8;
    byte[] arrayOfByte = this._inputBuffer;
    label37: while (true) {
      int k = this._inputPtr;
      int m = this._inputEnd;
      int j = m;
      int i = k;
      if (k >= m) {
        loadMoreGuaranteed();
        i = this._inputPtr;
        j = this._inputEnd;
      } 
      while (true) {
        if (i < j) {
          k = i + 1;
          i = arrayOfByte[i] & 0xFF;
          if (arrayOfInt[i] != 0) {
            this._inputPtr = k;
            if (i == 34)
              return; 
            switch (arrayOfInt[i]) {
              default:
                if (i < 32) {
                  _throwUnquotedSpace(i, "string value");
                  continue label37;
                } 
                break;
              case 1:
                _decodeEscaped();
                continue label37;
              case 2:
                _skipUtf8_2(i);
                continue label37;
              case 3:
                _skipUtf8_3(i);
                continue label37;
              case 4:
                _skipUtf8_4(i);
                continue label37;
            } 
            _reportInvalidChar(i);
            continue label37;
          } 
        } else {
          this._inputPtr = i;
          continue label37;
        } 
        i = k;
      } 
      break;
    } 
  }
  
  public void close() {
    super.close();
    this._symbols.release();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant) {
    if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null))
      _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary"); 
    if (this._tokenIncomplete)
      try {
        this._binaryValue = _decodeBase64(paramBase64Variant);
        this._tokenIncomplete = false;
        return this._binaryValue;
      } catch (IllegalArgumentException illegalArgumentException) {
        throw _constructError("Failed to decode VALUE_STRING as base64 (" + paramBase64Variant + "): " + illegalArgumentException.getMessage());
      }  
    if (this._binaryValue == null) {
      ByteArrayBuilder byteArrayBuilder = _getByteArrayBuilder();
      _decodeBase64(getText(), byteArrayBuilder, paramBase64Variant);
      this._binaryValue = byteArrayBuilder.toByteArray();
    } 
    return this._binaryValue;
  }
  
  public ObjectCodec getCodec() {
    return this._objectCodec;
  }
  
  public Object getInputSource() {
    return this._inputStream;
  }
  
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
  
  public char[] getTextCharacters() {
    if (this._currToken != null) {
      switch (Utf8StreamParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return this._currToken.asCharArray();
        case 1:
          if (!this._nameCopied) {
            String str = this._parsingContext.getCurrentName();
            int i = str.length();
            if (this._nameCopyBuffer == null) {
              this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(i);
            } else if (this._nameCopyBuffer.length < i) {
              this._nameCopyBuffer = new char[i];
            } 
            str.getChars(0, i, this._nameCopyBuffer, 0);
            this._nameCopied = true;
          } 
          return this._nameCopyBuffer;
        case 2:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
          break;
        case 3:
        case 4:
          break;
      } 
      return this._textBuffer.getTextBuffer();
    } 
    return null;
  }
  
  public int getTextLength() {
    null = 0;
    if (this._currToken != null) {
      switch (Utf8StreamParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          return (this._currToken.asCharArray()).length;
        case 1:
          return this._parsingContext.getCurrentName().length();
        case 2:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
          break;
        case 3:
        case 4:
          break;
      } 
    } else {
      return null;
    } 
    return this._textBuffer.size();
  }
  
  public int getTextOffset() {
    byte b = 0;
    int i = b;
    if (this._currToken != null) {
      i = b;
      switch (Utf8StreamParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
        default:
          i = b;
        case 1:
          return i;
        case 2:
          if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
          } 
          break;
        case 3:
        case 4:
          break;
      } 
      i = this._textBuffer.getTextOffset();
    } 
  }
  
  protected final boolean loadMore() {
    int i;
    boolean bool2 = false;
    this._currInputProcessed += this._inputEnd;
    this._currInputRowStart -= this._inputEnd;
    boolean bool1 = bool2;
    if (this._inputStream != null) {
      i = this._inputStream.read(this._inputBuffer, 0, this._inputBuffer.length);
      if (i > 0) {
        this._inputPtr = 0;
        this._inputEnd = i;
        return true;
      } 
    } else {
      return bool1;
    } 
    _closeInput();
    bool1 = bool2;
    if (i == 0)
      throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes"); 
    return bool1;
  }
  
  public Boolean nextBooleanValue() {
    Boolean bool = null;
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken jsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = jsonToken;
      if (jsonToken == JsonToken.VALUE_TRUE)
        return Boolean.TRUE; 
      if (jsonToken == JsonToken.VALUE_FALSE)
        return Boolean.FALSE; 
      if (jsonToken == JsonToken.START_ARRAY) {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        return bool;
      } 
      Boolean bool1 = bool;
      if (jsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        bool1 = bool;
      } 
      return bool1;
    } 
    switch (Utf8StreamParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[nextToken().ordinal()]) {
      default:
        return bool;
      case 5:
        return Boolean.TRUE;
      case 6:
        break;
    } 
    return Boolean.FALSE;
  }
  
  public boolean nextFieldName(SerializableString paramSerializableString) {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      _nextAfterName();
      return false;
    } 
    if (this._tokenIncomplete)
      _skipString(); 
    int j = _skipWSOrEnd();
    if (j < 0) {
      close();
      this._currToken = null;
      return false;
    } 
    this._tokenInputTotal = this._currInputProcessed + this._inputPtr - 1L;
    this._tokenInputRow = this._currInputRow;
    this._tokenInputCol = this._inputPtr - this._currInputRowStart - 1;
    this._binaryValue = null;
    if (j == 93) {
      if (!this._parsingContext.inArray())
        _reportMismatchedEndMarker(j, '}'); 
      this._parsingContext = this._parsingContext.getParent();
      this._currToken = JsonToken.END_ARRAY;
      return false;
    } 
    if (j == 125) {
      if (!this._parsingContext.inObject())
        _reportMismatchedEndMarker(j, ']'); 
      this._parsingContext = this._parsingContext.getParent();
      this._currToken = JsonToken.END_OBJECT;
      return false;
    } 
    int i = j;
    if (this._parsingContext.expectComma()) {
      if (j != 44)
        _reportUnexpectedChar(j, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries"); 
      i = _skipWS();
    } 
    if (!this._parsingContext.inObject()) {
      _nextTokenNotInObject(i);
      return false;
    } 
    if (i == 34) {
      byte[] arrayOfByte = paramSerializableString.asQuotedUTF8();
      int k = arrayOfByte.length;
      if (this._inputPtr + k < this._inputEnd) {
        int m = this._inputPtr + k;
        if (this._inputBuffer[m] == 34) {
          int n = this._inputPtr;
          j = 0;
          while (true) {
            if (j == k) {
              this._inputPtr = m + 1;
              this._parsingContext.setCurrentName(paramSerializableString.getValue());
              this._currToken = JsonToken.FIELD_NAME;
              _isNextTokenNameYes();
              return true;
            } 
            if (arrayOfByte[j] == this._inputBuffer[n + j]) {
              j++;
              continue;
            } 
            _isNextTokenNameNo(i);
            return false;
          } 
        } 
      } 
    } 
    _isNextTokenNameNo(i);
    return false;
  }
  
  public int nextIntValue(int paramInt) {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken jsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = jsonToken;
      if (jsonToken == JsonToken.VALUE_NUMBER_INT)
        return getIntValue(); 
      if (jsonToken == JsonToken.START_ARRAY) {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        return paramInt;
      } 
      int j = paramInt;
      if (jsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        j = paramInt;
      } 
      return j;
    } 
    int i = paramInt;
    if (nextToken() == JsonToken.VALUE_NUMBER_INT)
      i = getIntValue(); 
    return i;
  }
  
  public long nextLongValue(long paramLong) {
    if (this._currToken == JsonToken.FIELD_NAME) {
      this._nameCopied = false;
      JsonToken jsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = jsonToken;
      if (jsonToken == JsonToken.VALUE_NUMBER_INT)
        return getLongValue(); 
      if (jsonToken == JsonToken.START_ARRAY) {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        return paramLong;
      } 
      long l1 = paramLong;
      if (jsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        l1 = paramLong;
      } 
      return l1;
    } 
    long l = paramLong;
    if (nextToken() == JsonToken.VALUE_NUMBER_INT)
      l = getLongValue(); 
    return l;
  }
  
  public String nextTextValue() {
    String str2 = null;
    if (this._currToken == JsonToken.FIELD_NAME) {
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
      } 
      if (jsonToken == JsonToken.START_ARRAY) {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        return str2;
      } 
      String str = str2;
      if (jsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        str = str2;
      } 
      return str;
    } 
    String str1 = str2;
    if (nextToken() == JsonToken.VALUE_STRING)
      str1 = getText(); 
    return str1;
  }
  
  public JsonToken nextToken() {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME)
      return _nextAfterName(); 
    if (this._tokenIncomplete)
      _skipString(); 
    int j = _skipWSOrEnd();
    if (j < 0) {
      close();
      this._currToken = null;
      return null;
    } 
    this._tokenInputTotal = this._currInputProcessed + this._inputPtr - 1L;
    this._tokenInputRow = this._currInputRow;
    this._tokenInputCol = this._inputPtr - this._currInputRowStart - 1;
    this._binaryValue = null;
    if (j == 93) {
      if (!this._parsingContext.inArray())
        _reportMismatchedEndMarker(j, '}'); 
      this._parsingContext = this._parsingContext.getParent();
      JsonToken jsonToken = JsonToken.END_ARRAY;
      this._currToken = jsonToken;
      return jsonToken;
    } 
    if (j == 125) {
      if (!this._parsingContext.inObject())
        _reportMismatchedEndMarker(j, ']'); 
      this._parsingContext = this._parsingContext.getParent();
      JsonToken jsonToken = JsonToken.END_OBJECT;
      this._currToken = jsonToken;
      return jsonToken;
    } 
    int i = j;
    if (this._parsingContext.expectComma()) {
      if (j != 44)
        _reportUnexpectedChar(j, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries"); 
      i = _skipWS();
    } 
    if (!this._parsingContext.inObject())
      return _nextTokenNotInObject(i); 
    Name name = _parseFieldName(i);
    this._parsingContext.setCurrentName(name.getName());
    this._currToken = JsonToken.FIELD_NAME;
    i = _skipWS();
    if (i != 58)
      _reportUnexpectedChar(i, "was expecting a colon to separate field name and value"); 
    i = _skipWS();
    if (i == 34) {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return this._currToken;
    } 
    switch (i) {
      default:
        null = _handleUnexpectedValue(i);
        this._nextToken = null;
        return this._currToken;
      case 91:
        null = JsonToken.START_ARRAY;
        this._nextToken = null;
        return this._currToken;
      case 123:
        null = JsonToken.START_OBJECT;
        this._nextToken = null;
        return this._currToken;
      case 93:
      case 125:
        _reportUnexpectedChar(i, "expected a value");
      case 116:
        _matchToken("true", 1);
        null = JsonToken.VALUE_TRUE;
        this._nextToken = null;
        return this._currToken;
      case 102:
        _matchToken("false", 1);
        null = JsonToken.VALUE_FALSE;
        this._nextToken = null;
        return this._currToken;
      case 110:
        _matchToken("null", 1);
        null = JsonToken.VALUE_NULL;
        this._nextToken = null;
        return this._currToken;
      case 45:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        break;
    } 
    null = parseNumberText(i);
    this._nextToken = null;
    return this._currToken;
  }
  
  protected Name parseEscapedFieldName(int[] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: getstatic com/flurry/org/codehaus/jackson/impl/Utf8StreamParser.sInputCodesLatin1 : [I
    //   3: astore #9
    //   5: iload #4
    //   7: istore #6
    //   9: aload #9
    //   11: iload #4
    //   13: iaload
    //   14: ifeq -> 470
    //   17: iload #4
    //   19: bipush #34
    //   21: if_icmpne -> 102
    //   24: aload_1
    //   25: astore #8
    //   27: iload_2
    //   28: istore #4
    //   30: iload #5
    //   32: ifle -> 68
    //   35: aload_1
    //   36: astore #8
    //   38: iload_2
    //   39: aload_1
    //   40: arraylength
    //   41: if_icmplt -> 58
    //   44: aload_1
    //   45: aload_1
    //   46: arraylength
    //   47: invokestatic growArrayBy : ([II)[I
    //   50: astore #8
    //   52: aload_0
    //   53: aload #8
    //   55: putfield _quadBuffer : [I
    //   58: aload #8
    //   60: iload_2
    //   61: iload_3
    //   62: iastore
    //   63: iload_2
    //   64: iconst_1
    //   65: iadd
    //   66: istore #4
    //   68: aload_0
    //   69: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/BytesToNameCanonicalizer;
    //   72: aload #8
    //   74: iload #4
    //   76: invokevirtual findName : ([II)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   79: astore #9
    //   81: aload #9
    //   83: astore_1
    //   84: aload #9
    //   86: ifnonnull -> 100
    //   89: aload_0
    //   90: aload #8
    //   92: iload #4
    //   94: iload #5
    //   96: invokespecial addName : ([III)Lcom/flurry/org/codehaus/jackson/sym/Name;
    //   99: astore_1
    //   100: aload_1
    //   101: areturn
    //   102: iload #4
    //   104: bipush #92
    //   106: if_icmpeq -> 321
    //   109: aload_0
    //   110: iload #4
    //   112: ldc_w 'name'
    //   115: invokevirtual _throwUnquotedSpace : (ILjava/lang/String;)V
    //   118: iload #4
    //   120: istore #6
    //   122: iload #4
    //   124: bipush #127
    //   126: if_icmple -> 470
    //   129: iload #5
    //   131: iconst_4
    //   132: if_icmplt -> 467
    //   135: aload_1
    //   136: astore #8
    //   138: iload_2
    //   139: aload_1
    //   140: arraylength
    //   141: if_icmplt -> 158
    //   144: aload_1
    //   145: aload_1
    //   146: arraylength
    //   147: invokestatic growArrayBy : ([II)[I
    //   150: astore #8
    //   152: aload_0
    //   153: aload #8
    //   155: putfield _quadBuffer : [I
    //   158: iload_2
    //   159: iconst_1
    //   160: iadd
    //   161: istore #6
    //   163: aload #8
    //   165: iload_2
    //   166: iload_3
    //   167: iastore
    //   168: iconst_0
    //   169: istore #5
    //   171: iconst_0
    //   172: istore_3
    //   173: aload #8
    //   175: astore_1
    //   176: iload #6
    //   178: istore_2
    //   179: iload #4
    //   181: sipush #2048
    //   184: if_icmpge -> 330
    //   187: iload #4
    //   189: bipush #6
    //   191: ishr
    //   192: sipush #192
    //   195: ior
    //   196: iload_3
    //   197: bipush #8
    //   199: ishl
    //   200: ior
    //   201: istore #6
    //   203: iinc #5, 1
    //   206: iload_2
    //   207: istore_3
    //   208: iload #6
    //   210: istore_2
    //   211: iload #4
    //   213: bipush #63
    //   215: iand
    //   216: sipush #128
    //   219: ior
    //   220: istore #7
    //   222: iload #5
    //   224: istore #6
    //   226: iload_3
    //   227: istore #4
    //   229: iload_2
    //   230: istore #5
    //   232: iload #7
    //   234: istore_3
    //   235: iload #4
    //   237: istore_2
    //   238: iload #5
    //   240: istore #4
    //   242: iload #6
    //   244: iconst_4
    //   245: if_icmpge -> 423
    //   248: iload #6
    //   250: iconst_1
    //   251: iadd
    //   252: istore #5
    //   254: iload_3
    //   255: iload #4
    //   257: bipush #8
    //   259: ishl
    //   260: ior
    //   261: istore_3
    //   262: aload_0
    //   263: getfield _inputPtr : I
    //   266: aload_0
    //   267: getfield _inputEnd : I
    //   270: if_icmplt -> 287
    //   273: aload_0
    //   274: invokevirtual loadMore : ()Z
    //   277: ifne -> 287
    //   280: aload_0
    //   281: ldc_w ' in field name'
    //   284: invokevirtual _reportInvalidEOF : (Ljava/lang/String;)V
    //   287: aload_0
    //   288: getfield _inputBuffer : [B
    //   291: astore #8
    //   293: aload_0
    //   294: getfield _inputPtr : I
    //   297: istore #4
    //   299: aload_0
    //   300: iload #4
    //   302: iconst_1
    //   303: iadd
    //   304: putfield _inputPtr : I
    //   307: aload #8
    //   309: iload #4
    //   311: baload
    //   312: sipush #255
    //   315: iand
    //   316: istore #4
    //   318: goto -> 5
    //   321: aload_0
    //   322: invokevirtual _decodeEscaped : ()C
    //   325: istore #4
    //   327: goto -> 118
    //   330: iload #4
    //   332: bipush #12
    //   334: ishr
    //   335: sipush #224
    //   338: ior
    //   339: iload_3
    //   340: bipush #8
    //   342: ishl
    //   343: ior
    //   344: istore_3
    //   345: iinc #5, 1
    //   348: iload #5
    //   350: iconst_4
    //   351: if_icmplt -> 464
    //   354: aload_1
    //   355: astore #8
    //   357: iload_2
    //   358: aload_1
    //   359: arraylength
    //   360: if_icmplt -> 377
    //   363: aload_1
    //   364: aload_1
    //   365: arraylength
    //   366: invokestatic growArrayBy : ([II)[I
    //   369: astore #8
    //   371: aload_0
    //   372: aload #8
    //   374: putfield _quadBuffer : [I
    //   377: aload #8
    //   379: iload_2
    //   380: iload_3
    //   381: iastore
    //   382: iinc #2, 1
    //   385: aload #8
    //   387: astore_1
    //   388: iconst_0
    //   389: istore #5
    //   391: iconst_0
    //   392: istore_3
    //   393: iload_3
    //   394: bipush #8
    //   396: ishl
    //   397: iload #4
    //   399: bipush #6
    //   401: ishr
    //   402: bipush #63
    //   404: iand
    //   405: sipush #128
    //   408: ior
    //   409: ior
    //   410: istore #6
    //   412: iinc #5, 1
    //   415: iload_2
    //   416: istore_3
    //   417: iload #6
    //   419: istore_2
    //   420: goto -> 211
    //   423: aload_1
    //   424: astore #8
    //   426: iload_2
    //   427: aload_1
    //   428: arraylength
    //   429: if_icmplt -> 446
    //   432: aload_1
    //   433: aload_1
    //   434: arraylength
    //   435: invokestatic growArrayBy : ([II)[I
    //   438: astore #8
    //   440: aload_0
    //   441: aload #8
    //   443: putfield _quadBuffer : [I
    //   446: aload #8
    //   448: iload_2
    //   449: iload #4
    //   451: iastore
    //   452: iconst_1
    //   453: istore #5
    //   455: iinc #2, 1
    //   458: aload #8
    //   460: astore_1
    //   461: goto -> 262
    //   464: goto -> 393
    //   467: goto -> 179
    //   470: iload_3
    //   471: istore #4
    //   473: iload #6
    //   475: istore_3
    //   476: iload #5
    //   478: istore #6
    //   480: goto -> 242
  }
  
  protected Name parseLongFieldName(int paramInt) {
    int[] arrayOfInt = sInputCodesLatin1;
    int j = 2;
    int i = paramInt;
    for (paramInt = j;; paramInt++) {
      if (this._inputEnd - this._inputPtr < 4)
        return parseEscapedFieldName(this._quadBuffer, paramInt, 0, i, 0); 
      byte[] arrayOfByte = this._inputBuffer;
      j = this._inputPtr;
      this._inputPtr = j + 1;
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
        return (j == 34) ? findName(this._quadBuffer, paramInt, i, 1) : parseEscapedFieldName(this._quadBuffer, paramInt, i, j, 1); 
      i = i << 8 | j;
      arrayOfByte = this._inputBuffer;
      j = this._inputPtr;
      this._inputPtr = j + 1;
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
        return (j == 34) ? findName(this._quadBuffer, paramInt, i, 2) : parseEscapedFieldName(this._quadBuffer, paramInt, i, j, 2); 
      i = i << 8 | j;
      arrayOfByte = this._inputBuffer;
      j = this._inputPtr;
      this._inputPtr = j + 1;
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
        return (j == 34) ? findName(this._quadBuffer, paramInt, i, 3) : parseEscapedFieldName(this._quadBuffer, paramInt, i, j, 3); 
      j = i << 8 | j;
      arrayOfByte = this._inputBuffer;
      i = this._inputPtr;
      this._inputPtr = i + 1;
      i = arrayOfByte[i] & 0xFF;
      if (arrayOfInt[i] != 0)
        return (i == 34) ? findName(this._quadBuffer, paramInt, j, 4) : parseEscapedFieldName(this._quadBuffer, paramInt, j, i, 4); 
      if (paramInt >= this._quadBuffer.length)
        this._quadBuffer = growArrayBy(this._quadBuffer, paramInt); 
      this._quadBuffer[paramInt] = j;
    } 
  }
  
  protected final Name parseMediumFieldName(int paramInt, int[] paramArrayOfint) {
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfint[i] != 0)
      return (i == 34) ? findName(this._quad1, paramInt, 1) : parseFieldName(this._quad1, paramInt, i, 1); 
    paramInt = i | paramInt << 8;
    arrayOfByte = this._inputBuffer;
    i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfint[i] != 0)
      return (i == 34) ? findName(this._quad1, paramInt, 2) : parseFieldName(this._quad1, paramInt, i, 2); 
    paramInt = paramInt << 8 | i;
    arrayOfByte = this._inputBuffer;
    i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfint[i] != 0)
      return (i == 34) ? findName(this._quad1, paramInt, 3) : parseFieldName(this._quad1, paramInt, i, 3); 
    paramInt = paramInt << 8 | i;
    arrayOfByte = this._inputBuffer;
    i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfint[i] != 0)
      return (i == 34) ? findName(this._quad1, paramInt, 4) : parseFieldName(this._quad1, paramInt, i, 4); 
    this._quadBuffer[0] = this._quad1;
    this._quadBuffer[1] = paramInt;
    return parseLongFieldName(i);
  }
  
  protected final JsonToken parseNumberText(int paramInt) {
    boolean bool;
    int k = 1;
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    if (paramInt == 45) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      arrayOfChar[0] = '-';
      if (this._inputPtr >= this._inputEnd)
        loadMoreGuaranteed(); 
      byte[] arrayOfByte = this._inputBuffer;
      paramInt = this._inputPtr;
      this._inputPtr = paramInt + 1;
      paramInt = arrayOfByte[paramInt] & 0xFF;
      if (paramInt < 48 || paramInt > 57)
        return _handleInvalidNumberStart(paramInt, true); 
      i = 1;
    } else {
      i = 0;
    } 
    int j = paramInt;
    if (paramInt == 48)
      j = _verifyNoLeadingZeroes(); 
    int m = i + 1;
    arrayOfChar[i] = (char)j;
    int n = this._inputPtr + arrayOfChar.length;
    paramInt = n;
    int i = m;
    j = k;
    if (n > this._inputEnd) {
      paramInt = this._inputEnd;
      j = k;
      i = m;
    } 
    while (true) {
      if (this._inputPtr >= paramInt)
        return _parserNumber2(arrayOfChar, i, bool, j); 
      byte[] arrayOfByte = this._inputBuffer;
      k = this._inputPtr;
      this._inputPtr = k + 1;
      k = arrayOfByte[k] & 0xFF;
      if (k < 48 || k > 57) {
        if (k == 46 || k == 101 || k == 69)
          return _parseFloatText(arrayOfChar, i, k, bool, j); 
      } else {
        j++;
        arrayOfChar[i] = (char)k;
        i++;
        continue;
      } 
      this._inputPtr--;
      this._textBuffer.setCurrentLength(i);
      return resetInt(bool, j);
    } 
  }
  
  public int releaseBuffered(OutputStream paramOutputStream) {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1)
      return 0; 
    int j = this._inputPtr;
    paramOutputStream.write(this._inputBuffer, j, i);
    return i;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
  }
  
  protected Name slowParseFieldName() {
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(": was expecting closing '\"' for name"); 
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfByte[i] & 0xFF;
    return (i == 34) ? BytesToNameCanonicalizer.getEmptyName() : parseEscapedFieldName(this._quadBuffer, 0, 0, i, 0);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\Utf8StreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */