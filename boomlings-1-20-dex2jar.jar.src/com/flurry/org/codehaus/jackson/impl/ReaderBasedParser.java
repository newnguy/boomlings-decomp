package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import com.flurry.org.codehaus.jackson.util.TextBuffer;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class ReaderBasedParser extends JsonParserBase {
  protected char[] _inputBuffer;
  
  protected ObjectCodec _objectCodec;
  
  protected Reader _reader;
  
  protected final CharsToNameCanonicalizer _symbols;
  
  protected boolean _tokenIncomplete = false;
  
  public ReaderBasedParser(IOContext paramIOContext, int paramInt, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer) {
    super(paramIOContext, paramInt);
    this._reader = paramReader;
    this._inputBuffer = paramIOContext.allocTokenBuffer();
    this._objectCodec = paramObjectCodec;
    this._symbols = paramCharsToNameCanonicalizer;
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
  
  private String _parseFieldName2(int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   4: aload_0
    //   5: getfield _inputBuffer : [C
    //   8: iload_1
    //   9: aload_0
    //   10: getfield _inputPtr : I
    //   13: iload_1
    //   14: isub
    //   15: invokevirtual resetWithShared : ([CII)V
    //   18: aload_0
    //   19: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   22: invokevirtual getCurrentSegment : ()[C
    //   25: astore #7
    //   27: aload_0
    //   28: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   31: invokevirtual getCurrentSegmentSize : ()I
    //   34: istore_1
    //   35: aload_0
    //   36: getfield _inputPtr : I
    //   39: aload_0
    //   40: getfield _inputEnd : I
    //   43: if_icmplt -> 82
    //   46: aload_0
    //   47: invokevirtual loadMore : ()Z
    //   50: ifne -> 82
    //   53: aload_0
    //   54: new java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial <init> : ()V
    //   61: ldc ': was expecting closing ''
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: iload_3
    //   67: i2c
    //   68: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   71: ldc '' for name'
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: invokevirtual _reportInvalidEOF : (Ljava/lang/String;)V
    //   82: aload_0
    //   83: getfield _inputBuffer : [C
    //   86: astore #8
    //   88: aload_0
    //   89: getfield _inputPtr : I
    //   92: istore #6
    //   94: aload_0
    //   95: iload #6
    //   97: iconst_1
    //   98: iadd
    //   99: putfield _inputPtr : I
    //   102: aload #8
    //   104: iload #6
    //   106: caload
    //   107: istore #5
    //   109: iload #5
    //   111: bipush #92
    //   113: if_icmpgt -> 243
    //   116: iload #5
    //   118: bipush #92
    //   120: if_icmpne -> 170
    //   123: aload_0
    //   124: invokevirtual _decodeEscaped : ()C
    //   127: istore #4
    //   129: iload_2
    //   130: bipush #31
    //   132: imul
    //   133: iload #5
    //   135: iadd
    //   136: istore_2
    //   137: iload_1
    //   138: iconst_1
    //   139: iadd
    //   140: istore #6
    //   142: aload #7
    //   144: iload_1
    //   145: iload #4
    //   147: castore
    //   148: iload #6
    //   150: aload #7
    //   152: arraylength
    //   153: if_icmplt -> 250
    //   156: aload_0
    //   157: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   160: invokevirtual finishCurrentSegment : ()[C
    //   163: astore #7
    //   165: iconst_0
    //   166: istore_1
    //   167: goto -> 35
    //   170: iload #5
    //   172: iload_3
    //   173: if_icmpgt -> 243
    //   176: iload #5
    //   178: iload_3
    //   179: if_icmpne -> 228
    //   182: aload_0
    //   183: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   186: iload_1
    //   187: invokevirtual setCurrentLength : (I)V
    //   190: aload_0
    //   191: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   194: astore #8
    //   196: aload #8
    //   198: invokevirtual getTextBuffer : ()[C
    //   201: astore #7
    //   203: aload #8
    //   205: invokevirtual getTextOffset : ()I
    //   208: istore_3
    //   209: aload #8
    //   211: invokevirtual size : ()I
    //   214: istore_1
    //   215: aload_0
    //   216: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer;
    //   219: aload #7
    //   221: iload_3
    //   222: iload_1
    //   223: iload_2
    //   224: invokevirtual findSymbol : ([CIII)Ljava/lang/String;
    //   227: areturn
    //   228: iload #5
    //   230: bipush #32
    //   232: if_icmpge -> 243
    //   235: aload_0
    //   236: iload #5
    //   238: ldc 'name'
    //   240: invokevirtual _throwUnquotedSpace : (ILjava/lang/String;)V
    //   243: iload #5
    //   245: istore #4
    //   247: goto -> 129
    //   250: iload #6
    //   252: istore_1
    //   253: goto -> 35
  }
  
  private String _parseUnusualFieldName2(int paramInt1, int paramInt2, int[] paramArrayOfint) {
    this._textBuffer.resetWithShared(this._inputBuffer, paramInt1, this._inputPtr - paramInt1);
    char[] arrayOfChar = this._textBuffer.getCurrentSegment();
    paramInt1 = this._textBuffer.getCurrentSegmentSize();
    int i = paramArrayOfint.length;
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char c = this._inputBuffer[this._inputPtr];
        if ((c <= i) ? (paramArrayOfint[c] == 0) : Character.isJavaIdentifierPart(c)) {
          this._inputPtr++;
          paramInt2 = paramInt2 * 31 + c;
          int k = paramInt1 + 1;
          arrayOfChar[paramInt1] = c;
          if (k >= arrayOfChar.length) {
            arrayOfChar = this._textBuffer.finishCurrentSegment();
            paramInt1 = 0;
            continue;
          } 
          paramInt1 = k;
          continue;
        } 
      } 
      this._textBuffer.setCurrentLength(paramInt1);
      TextBuffer textBuffer = this._textBuffer;
      arrayOfChar = textBuffer.getTextBuffer();
      int j = textBuffer.getTextOffset();
      paramInt1 = textBuffer.size();
      return this._symbols.findSymbol(arrayOfChar, j, paramInt1, paramInt2);
    } 
  }
  
  private final void _skipCComment() {
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfChar[i];
        if (i <= 42) {
          if (i == 42) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
              if (this._inputBuffer[this._inputPtr] == '/') {
                this._inputPtr++;
                return;
              } 
              continue;
            } 
          } else {
            if (i < 32) {
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
        } else {
          continue;
        } 
      } 
      _reportInvalidEOF(" in a comment");
      return;
    } 
  }
  
  private final void _skipComment() {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS))
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)"); 
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(" in a comment"); 
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    i = arrayOfChar[i];
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
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfChar[i];
        if (i < 32) {
          if (i == 10) {
            _skipLF();
            return;
          } 
          if (i == 13) {
            _skipCR();
            return;
          } 
          if (i != 9)
            _throwInvalidSpace(i); 
        } 
        continue;
      } 
      return;
    } 
  }
  
  private final int _skipWS() {
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        i = arrayOfChar[i];
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
    byte b;
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = arrayOfChar[i];
        if (c > ' ') {
          i = c;
          if (c == '/') {
            _skipComment();
            continue;
          } 
          break;
        } 
        if (c != ' ') {
          if (c == '\n') {
            _skipLF();
            continue;
          } 
          if (c == '\r') {
            _skipCR();
            continue;
          } 
          if (c != '\t')
            _throwInvalidSpace(c); 
        } 
        continue;
      } 
      _handleEOF();
      b = -1;
      break;
    } 
    return b;
  }
  
  private final char _verifyNoLeadingZeroes() {
    if (this._inputPtr >= this._inputEnd && !loadMore())
      return '0'; 
    char c1 = this._inputBuffer[this._inputPtr];
    if (c1 < '0' || c1 > '9')
      return '0'; 
    if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS))
      reportInvalidNumber("Leading zeroes not allowed"); 
    this._inputPtr++;
    char c = c1;
    if (c1 == '0') {
      c = c1;
      while (true) {
        if (this._inputPtr < this._inputEnd || loadMore()) {
          c1 = this._inputBuffer[this._inputPtr];
          if (c1 < '0' || c1 > '9')
            return '0'; 
          this._inputPtr++;
          c = c1;
          if (c1 != '0')
            return c1; 
          continue;
        } 
        return c;
      } 
    } 
    return c;
  }
  
  private final JsonToken parseNumberText2(boolean paramBoolean) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #11
    //   3: aload_0
    //   4: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   7: invokevirtual emptyAndGetCurrentSegment : ()[C
    //   10: astore #13
    //   12: iload_1
    //   13: ifeq -> 876
    //   16: aload #13
    //   18: iconst_0
    //   19: bipush #45
    //   21: castore
    //   22: iconst_1
    //   23: istore #4
    //   25: aload_0
    //   26: getfield _inputPtr : I
    //   29: aload_0
    //   30: getfield _inputEnd : I
    //   33: if_icmpge -> 605
    //   36: aload_0
    //   37: getfield _inputBuffer : [C
    //   40: astore #12
    //   42: aload_0
    //   43: getfield _inputPtr : I
    //   46: istore #5
    //   48: aload_0
    //   49: iload #5
    //   51: iconst_1
    //   52: iadd
    //   53: putfield _inputPtr : I
    //   56: aload #12
    //   58: iload #5
    //   60: caload
    //   61: istore_3
    //   62: iload_3
    //   63: istore_2
    //   64: iload_3
    //   65: bipush #48
    //   67: if_icmpne -> 75
    //   70: aload_0
    //   71: invokespecial _verifyNoLeadingZeroes : ()C
    //   74: istore_2
    //   75: iconst_0
    //   76: istore #5
    //   78: iload_2
    //   79: bipush #48
    //   81: if_icmplt -> 854
    //   84: iload_2
    //   85: bipush #57
    //   87: if_icmpgt -> 854
    //   90: iinc #5, 1
    //   93: iload #4
    //   95: istore #6
    //   97: aload #13
    //   99: astore #12
    //   101: iload #4
    //   103: aload #13
    //   105: arraylength
    //   106: if_icmplt -> 121
    //   109: aload_0
    //   110: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   113: invokevirtual finishCurrentSegment : ()[C
    //   116: astore #12
    //   118: iconst_0
    //   119: istore #6
    //   121: iload #6
    //   123: iconst_1
    //   124: iadd
    //   125: istore #4
    //   127: aload #12
    //   129: iload #6
    //   131: iload_2
    //   132: castore
    //   133: aload_0
    //   134: getfield _inputPtr : I
    //   137: aload_0
    //   138: getfield _inputEnd : I
    //   141: if_icmplt -> 616
    //   144: aload_0
    //   145: invokevirtual loadMore : ()Z
    //   148: ifne -> 616
    //   151: iconst_1
    //   152: istore #6
    //   154: iconst_0
    //   155: istore_2
    //   156: iload #5
    //   158: istore #7
    //   160: iload #4
    //   162: istore #5
    //   164: iload #6
    //   166: istore #4
    //   168: iload #7
    //   170: ifne -> 204
    //   173: aload_0
    //   174: new java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial <init> : ()V
    //   181: ldc 'Missing integer part (next char '
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: iload_2
    //   187: invokestatic _getCharDesc : (I)Ljava/lang/String;
    //   190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: ldc ')'
    //   195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual toString : ()Ljava/lang/String;
    //   201: invokevirtual reportInvalidNumber : (Ljava/lang/String;)V
    //   204: iload_2
    //   205: bipush #46
    //   207: if_icmpne -> 832
    //   210: aload #12
    //   212: iload #5
    //   214: iload_2
    //   215: castore
    //   216: iinc #5, 1
    //   219: iconst_0
    //   220: istore #6
    //   222: aload_0
    //   223: getfield _inputPtr : I
    //   226: aload_0
    //   227: getfield _inputEnd : I
    //   230: if_icmplt -> 649
    //   233: aload_0
    //   234: invokevirtual loadMore : ()Z
    //   237: ifne -> 649
    //   240: iconst_1
    //   241: istore #4
    //   243: iload #6
    //   245: ifne -> 255
    //   248: aload_0
    //   249: iload_2
    //   250: ldc 'Decimal point not followed by a digit'
    //   252: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   255: iload #6
    //   257: istore #8
    //   259: iload #5
    //   261: istore #6
    //   263: iload #4
    //   265: istore #5
    //   267: aload #12
    //   269: astore #13
    //   271: iload #6
    //   273: istore #4
    //   275: iload_2
    //   276: bipush #101
    //   278: if_icmpeq -> 287
    //   281: iload_2
    //   282: bipush #69
    //   284: if_icmpne -> 815
    //   287: iload #4
    //   289: istore #6
    //   291: aload #13
    //   293: astore #12
    //   295: iload #4
    //   297: aload #13
    //   299: arraylength
    //   300: if_icmplt -> 315
    //   303: aload_0
    //   304: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   307: invokevirtual finishCurrentSegment : ()[C
    //   310: astore #12
    //   312: iconst_0
    //   313: istore #6
    //   315: iload #6
    //   317: iconst_1
    //   318: iadd
    //   319: istore #4
    //   321: aload #12
    //   323: iload #6
    //   325: iload_2
    //   326: castore
    //   327: aload_0
    //   328: getfield _inputPtr : I
    //   331: aload_0
    //   332: getfield _inputEnd : I
    //   335: if_icmpge -> 732
    //   338: aload_0
    //   339: getfield _inputBuffer : [C
    //   342: astore #13
    //   344: aload_0
    //   345: getfield _inputPtr : I
    //   348: istore #6
    //   350: aload_0
    //   351: iload #6
    //   353: iconst_1
    //   354: iadd
    //   355: putfield _inputPtr : I
    //   358: aload #13
    //   360: iload #6
    //   362: caload
    //   363: istore_2
    //   364: iload_2
    //   365: bipush #45
    //   367: if_icmpeq -> 376
    //   370: iload_2
    //   371: bipush #43
    //   373: if_icmpne -> 809
    //   376: iload #4
    //   378: aload #12
    //   380: arraylength
    //   381: if_icmplt -> 806
    //   384: aload_0
    //   385: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   388: invokevirtual finishCurrentSegment : ()[C
    //   391: astore #12
    //   393: iconst_0
    //   394: istore #4
    //   396: aload #12
    //   398: iload #4
    //   400: iload_2
    //   401: castore
    //   402: aload_0
    //   403: getfield _inputPtr : I
    //   406: aload_0
    //   407: getfield _inputEnd : I
    //   410: if_icmpge -> 743
    //   413: aload_0
    //   414: getfield _inputBuffer : [C
    //   417: astore #13
    //   419: aload_0
    //   420: getfield _inputPtr : I
    //   423: istore #6
    //   425: aload_0
    //   426: iload #6
    //   428: iconst_1
    //   429: iadd
    //   430: putfield _inputPtr : I
    //   433: aload #13
    //   435: iload #6
    //   437: caload
    //   438: istore_2
    //   439: iinc #4, 1
    //   442: iconst_0
    //   443: istore #6
    //   445: iload_2
    //   446: bipush #57
    //   448: if_icmpgt -> 787
    //   451: iload_2
    //   452: bipush #48
    //   454: if_icmplt -> 787
    //   457: iinc #6, 1
    //   460: iload #4
    //   462: istore #9
    //   464: aload #12
    //   466: astore #13
    //   468: iload #4
    //   470: aload #12
    //   472: arraylength
    //   473: if_icmplt -> 488
    //   476: aload_0
    //   477: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   480: invokevirtual finishCurrentSegment : ()[C
    //   483: astore #13
    //   485: iconst_0
    //   486: istore #9
    //   488: iload #9
    //   490: iconst_1
    //   491: iadd
    //   492: istore #4
    //   494: aload #13
    //   496: iload #9
    //   498: iload_2
    //   499: castore
    //   500: aload_0
    //   501: getfield _inputPtr : I
    //   504: aload_0
    //   505: getfield _inputEnd : I
    //   508: if_icmplt -> 754
    //   511: aload_0
    //   512: invokevirtual loadMore : ()Z
    //   515: ifne -> 754
    //   518: iload #6
    //   520: istore #5
    //   522: iconst_1
    //   523: istore #9
    //   525: iload #4
    //   527: istore #6
    //   529: iload #9
    //   531: istore #4
    //   533: iload #4
    //   535: istore #9
    //   537: iload #6
    //   539: istore #10
    //   541: iload #5
    //   543: istore #11
    //   545: iload #5
    //   547: ifne -> 569
    //   550: aload_0
    //   551: iload_2
    //   552: ldc 'Exponent indicator not followed by a digit'
    //   554: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   557: iload #5
    //   559: istore #11
    //   561: iload #6
    //   563: istore #10
    //   565: iload #4
    //   567: istore #9
    //   569: iload #9
    //   571: ifne -> 584
    //   574: aload_0
    //   575: aload_0
    //   576: getfield _inputPtr : I
    //   579: iconst_1
    //   580: isub
    //   581: putfield _inputPtr : I
    //   584: aload_0
    //   585: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   588: iload #10
    //   590: invokevirtual setCurrentLength : (I)V
    //   593: aload_0
    //   594: iload_1
    //   595: iload #7
    //   597: iload #8
    //   599: iload #11
    //   601: invokevirtual reset : (ZIII)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   604: areturn
    //   605: aload_0
    //   606: ldc_w 'No digit following minus sign'
    //   609: invokevirtual getNextChar : (Ljava/lang/String;)C
    //   612: istore_3
    //   613: goto -> 62
    //   616: aload_0
    //   617: getfield _inputBuffer : [C
    //   620: astore #13
    //   622: aload_0
    //   623: getfield _inputPtr : I
    //   626: istore #6
    //   628: aload_0
    //   629: iload #6
    //   631: iconst_1
    //   632: iadd
    //   633: putfield _inputPtr : I
    //   636: aload #13
    //   638: iload #6
    //   640: caload
    //   641: istore_2
    //   642: aload #12
    //   644: astore #13
    //   646: goto -> 78
    //   649: aload_0
    //   650: getfield _inputBuffer : [C
    //   653: astore #13
    //   655: aload_0
    //   656: getfield _inputPtr : I
    //   659: istore #8
    //   661: aload_0
    //   662: iload #8
    //   664: iconst_1
    //   665: iadd
    //   666: putfield _inputPtr : I
    //   669: aload #13
    //   671: iload #8
    //   673: caload
    //   674: istore_2
    //   675: iload_2
    //   676: bipush #48
    //   678: if_icmplt -> 829
    //   681: iload_2
    //   682: bipush #57
    //   684: if_icmple -> 690
    //   687: goto -> 243
    //   690: iinc #6, 1
    //   693: iload #5
    //   695: aload #12
    //   697: arraylength
    //   698: if_icmplt -> 826
    //   701: aload_0
    //   702: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   705: invokevirtual finishCurrentSegment : ()[C
    //   708: astore #12
    //   710: iconst_0
    //   711: istore #5
    //   713: iload #5
    //   715: iconst_1
    //   716: iadd
    //   717: istore #8
    //   719: aload #12
    //   721: iload #5
    //   723: iload_2
    //   724: castore
    //   725: iload #8
    //   727: istore #5
    //   729: goto -> 222
    //   732: aload_0
    //   733: ldc_w 'expected a digit for number exponent'
    //   736: invokevirtual getNextChar : (Ljava/lang/String;)C
    //   739: istore_2
    //   740: goto -> 364
    //   743: aload_0
    //   744: ldc_w 'expected a digit for number exponent'
    //   747: invokevirtual getNextChar : (Ljava/lang/String;)C
    //   750: istore_2
    //   751: goto -> 439
    //   754: aload_0
    //   755: getfield _inputBuffer : [C
    //   758: astore #12
    //   760: aload_0
    //   761: getfield _inputPtr : I
    //   764: istore #9
    //   766: aload_0
    //   767: iload #9
    //   769: iconst_1
    //   770: iadd
    //   771: putfield _inputPtr : I
    //   774: aload #12
    //   776: iload #9
    //   778: caload
    //   779: istore_2
    //   780: aload #13
    //   782: astore #12
    //   784: goto -> 445
    //   787: iload #6
    //   789: istore #9
    //   791: iload #4
    //   793: istore #6
    //   795: iload #5
    //   797: istore #4
    //   799: iload #9
    //   801: istore #5
    //   803: goto -> 533
    //   806: goto -> 396
    //   809: iconst_0
    //   810: istore #6
    //   812: goto -> 445
    //   815: iload #5
    //   817: istore #9
    //   819: iload #4
    //   821: istore #10
    //   823: goto -> 569
    //   826: goto -> 713
    //   829: goto -> 243
    //   832: iconst_0
    //   833: istore #8
    //   835: iload #4
    //   837: istore #6
    //   839: iload #5
    //   841: istore #4
    //   843: aload #12
    //   845: astore #13
    //   847: iload #6
    //   849: istore #5
    //   851: goto -> 275
    //   854: iconst_0
    //   855: istore #6
    //   857: iload #5
    //   859: istore #7
    //   861: aload #13
    //   863: astore #12
    //   865: iload #4
    //   867: istore #5
    //   869: iload #6
    //   871: istore #4
    //   873: goto -> 168
    //   876: iconst_0
    //   877: istore #4
    //   879: goto -> 25
  }
  
  protected void _closeInput() {
    if (this._reader != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._reader.close(); 
      this._reader = null;
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
    //   22: getfield _inputBuffer : [C
    //   25: astore #8
    //   27: aload_0
    //   28: getfield _inputPtr : I
    //   31: istore_3
    //   32: aload_0
    //   33: iload_3
    //   34: iconst_1
    //   35: iadd
    //   36: putfield _inputPtr : I
    //   39: aload #8
    //   41: iload_3
    //   42: caload
    //   43: istore_2
    //   44: iload_2
    //   45: bipush #32
    //   47: if_icmple -> 6
    //   50: aload_1
    //   51: iload_2
    //   52: invokevirtual decodeBase64Char : (C)I
    //   55: istore #4
    //   57: iload #4
    //   59: istore_3
    //   60: iload #4
    //   62: ifge -> 91
    //   65: iload_2
    //   66: bipush #34
    //   68: if_icmpne -> 79
    //   71: aload #7
    //   73: invokevirtual toByteArray : ()[B
    //   76: astore_1
    //   77: aload_1
    //   78: areturn
    //   79: aload_0
    //   80: aload_1
    //   81: iload_2
    //   82: iconst_0
    //   83: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;CI)I
    //   86: istore_3
    //   87: iload_3
    //   88: iflt -> 6
    //   91: aload_0
    //   92: getfield _inputPtr : I
    //   95: aload_0
    //   96: getfield _inputEnd : I
    //   99: if_icmplt -> 106
    //   102: aload_0
    //   103: invokevirtual loadMoreGuaranteed : ()V
    //   106: aload_0
    //   107: getfield _inputBuffer : [C
    //   110: astore #8
    //   112: aload_0
    //   113: getfield _inputPtr : I
    //   116: istore #4
    //   118: aload_0
    //   119: iload #4
    //   121: iconst_1
    //   122: iadd
    //   123: putfield _inputPtr : I
    //   126: aload #8
    //   128: iload #4
    //   130: caload
    //   131: istore_2
    //   132: aload_1
    //   133: iload_2
    //   134: invokevirtual decodeBase64Char : (C)I
    //   137: istore #5
    //   139: iload #5
    //   141: istore #4
    //   143: iload #5
    //   145: ifge -> 157
    //   148: aload_0
    //   149: aload_1
    //   150: iload_2
    //   151: iconst_1
    //   152: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;CI)I
    //   155: istore #4
    //   157: iload #4
    //   159: iload_3
    //   160: bipush #6
    //   162: ishl
    //   163: ior
    //   164: istore #6
    //   166: aload_0
    //   167: getfield _inputPtr : I
    //   170: aload_0
    //   171: getfield _inputEnd : I
    //   174: if_icmplt -> 181
    //   177: aload_0
    //   178: invokevirtual loadMoreGuaranteed : ()V
    //   181: aload_0
    //   182: getfield _inputBuffer : [C
    //   185: astore #8
    //   187: aload_0
    //   188: getfield _inputPtr : I
    //   191: istore_3
    //   192: aload_0
    //   193: iload_3
    //   194: iconst_1
    //   195: iadd
    //   196: putfield _inputPtr : I
    //   199: aload #8
    //   201: iload_3
    //   202: caload
    //   203: istore_2
    //   204: aload_1
    //   205: iload_2
    //   206: invokevirtual decodeBase64Char : (C)I
    //   209: istore #4
    //   211: iload #4
    //   213: istore #5
    //   215: iload #4
    //   217: ifge -> 373
    //   220: iload #4
    //   222: istore_3
    //   223: iload #4
    //   225: bipush #-2
    //   227: if_icmpeq -> 269
    //   230: iload_2
    //   231: bipush #34
    //   233: if_icmpne -> 261
    //   236: aload_1
    //   237: invokevirtual usesPadding : ()Z
    //   240: ifne -> 261
    //   243: aload #7
    //   245: iload #6
    //   247: iconst_4
    //   248: ishr
    //   249: invokevirtual append : (I)V
    //   252: aload #7
    //   254: invokevirtual toByteArray : ()[B
    //   257: astore_1
    //   258: goto -> 77
    //   261: aload_0
    //   262: aload_1
    //   263: iload_2
    //   264: iconst_2
    //   265: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;CI)I
    //   268: istore_3
    //   269: iload_3
    //   270: istore #5
    //   272: iload_3
    //   273: bipush #-2
    //   275: if_icmpne -> 373
    //   278: aload_0
    //   279: getfield _inputPtr : I
    //   282: aload_0
    //   283: getfield _inputEnd : I
    //   286: if_icmplt -> 293
    //   289: aload_0
    //   290: invokevirtual loadMoreGuaranteed : ()V
    //   293: aload_0
    //   294: getfield _inputBuffer : [C
    //   297: astore #8
    //   299: aload_0
    //   300: getfield _inputPtr : I
    //   303: istore_3
    //   304: aload_0
    //   305: iload_3
    //   306: iconst_1
    //   307: iadd
    //   308: putfield _inputPtr : I
    //   311: aload #8
    //   313: iload_3
    //   314: caload
    //   315: istore_2
    //   316: aload_1
    //   317: iload_2
    //   318: invokevirtual usesPaddingChar : (C)Z
    //   321: ifne -> 361
    //   324: aload_0
    //   325: aload_1
    //   326: iload_2
    //   327: iconst_3
    //   328: new java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial <init> : ()V
    //   335: ldc_w 'expected padding character ''
    //   338: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: aload_1
    //   342: invokevirtual getPaddingChar : ()C
    //   345: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   348: ldc_w '''
    //   351: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   354: invokevirtual toString : ()Ljava/lang/String;
    //   357: invokevirtual reportInvalidBase64Char : (Lcom/flurry/org/codehaus/jackson/Base64Variant;IILjava/lang/String;)Ljava/lang/IllegalArgumentException;
    //   360: athrow
    //   361: aload #7
    //   363: iload #6
    //   365: iconst_4
    //   366: ishr
    //   367: invokevirtual append : (I)V
    //   370: goto -> 6
    //   373: iload #6
    //   375: bipush #6
    //   377: ishl
    //   378: iload #5
    //   380: ior
    //   381: istore #6
    //   383: aload_0
    //   384: getfield _inputPtr : I
    //   387: aload_0
    //   388: getfield _inputEnd : I
    //   391: if_icmplt -> 398
    //   394: aload_0
    //   395: invokevirtual loadMoreGuaranteed : ()V
    //   398: aload_0
    //   399: getfield _inputBuffer : [C
    //   402: astore #8
    //   404: aload_0
    //   405: getfield _inputPtr : I
    //   408: istore_3
    //   409: aload_0
    //   410: iload_3
    //   411: iconst_1
    //   412: iadd
    //   413: putfield _inputPtr : I
    //   416: aload #8
    //   418: iload_3
    //   419: caload
    //   420: istore_2
    //   421: aload_1
    //   422: iload_2
    //   423: invokevirtual decodeBase64Char : (C)I
    //   426: istore #5
    //   428: iload #5
    //   430: istore #4
    //   432: iload #5
    //   434: ifge -> 507
    //   437: iload #5
    //   439: istore_3
    //   440: iload #5
    //   442: bipush #-2
    //   444: if_icmpeq -> 486
    //   447: iload_2
    //   448: bipush #34
    //   450: if_icmpne -> 478
    //   453: aload_1
    //   454: invokevirtual usesPadding : ()Z
    //   457: ifne -> 478
    //   460: aload #7
    //   462: iload #6
    //   464: iconst_2
    //   465: ishr
    //   466: invokevirtual appendTwoBytes : (I)V
    //   469: aload #7
    //   471: invokevirtual toByteArray : ()[B
    //   474: astore_1
    //   475: goto -> 77
    //   478: aload_0
    //   479: aload_1
    //   480: iload_2
    //   481: iconst_3
    //   482: invokevirtual _decodeBase64Escape : (Lcom/flurry/org/codehaus/jackson/Base64Variant;CI)I
    //   485: istore_3
    //   486: iload_3
    //   487: istore #4
    //   489: iload_3
    //   490: bipush #-2
    //   492: if_icmpne -> 507
    //   495: aload #7
    //   497: iload #6
    //   499: iconst_2
    //   500: ishr
    //   501: invokevirtual appendTwoBytes : (I)V
    //   504: goto -> 6
    //   507: aload #7
    //   509: iload #4
    //   511: iload #6
    //   513: bipush #6
    //   515: ishl
    //   516: ior
    //   517: invokevirtual appendThreeBytes : (I)V
    //   520: goto -> 6
  }
  
  protected final char _decodeEscaped() {
    int i = 0;
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(" in character escape sequence"); 
    char[] arrayOfChar = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = j + 1;
    char c1 = arrayOfChar[j];
    char c = c1;
    switch (c1) {
      default:
        c = _handleUnrecognizedCharacterEscape(c1);
      case '"':
      case '/':
      case '\\':
        return c;
      case 'b':
        c = '\b';
      case 't':
        c = '\t';
      case 'n':
        c = '\n';
      case 'f':
        c = '\f';
      case 'r':
        c = '\r';
      case 'u':
        break;
    } 
    for (j = 0; j < 4; j++) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(" in character escape sequence"); 
      arrayOfChar = this._inputBuffer;
      int k = this._inputPtr;
      this._inputPtr = k + 1;
      char c2 = arrayOfChar[k];
      k = CharTypes.charToHex(c2);
      if (k < 0)
        _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence"); 
      i = i << 4 | k;
    } 
    c = (char)i;
  }
  
  protected void _finishString() {
    // Byte code:
    //   0: aload_0
    //   1: getfield _inputPtr : I
    //   4: istore_1
    //   5: aload_0
    //   6: getfield _inputEnd : I
    //   9: istore_3
    //   10: iload_1
    //   11: istore_2
    //   12: iload_1
    //   13: iload_3
    //   14: if_icmpge -> 99
    //   17: invokestatic getInputCodeLatin1 : ()[I
    //   20: astore #6
    //   22: aload #6
    //   24: arraylength
    //   25: istore #4
    //   27: aload_0
    //   28: getfield _inputBuffer : [C
    //   31: iload_1
    //   32: caload
    //   33: istore #5
    //   35: iload #5
    //   37: iload #4
    //   39: if_icmpge -> 88
    //   42: aload #6
    //   44: iload #5
    //   46: iaload
    //   47: ifeq -> 88
    //   50: iload_1
    //   51: istore_2
    //   52: iload #5
    //   54: bipush #34
    //   56: if_icmpne -> 99
    //   59: aload_0
    //   60: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   63: aload_0
    //   64: getfield _inputBuffer : [C
    //   67: aload_0
    //   68: getfield _inputPtr : I
    //   71: iload_1
    //   72: aload_0
    //   73: getfield _inputPtr : I
    //   76: isub
    //   77: invokevirtual resetWithShared : ([CII)V
    //   80: aload_0
    //   81: iload_1
    //   82: iconst_1
    //   83: iadd
    //   84: putfield _inputPtr : I
    //   87: return
    //   88: iload_1
    //   89: iconst_1
    //   90: iadd
    //   91: istore_2
    //   92: iload_2
    //   93: istore_1
    //   94: iload_2
    //   95: iload_3
    //   96: if_icmplt -> 27
    //   99: aload_0
    //   100: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   103: aload_0
    //   104: getfield _inputBuffer : [C
    //   107: aload_0
    //   108: getfield _inputPtr : I
    //   111: iload_2
    //   112: aload_0
    //   113: getfield _inputPtr : I
    //   116: isub
    //   117: invokevirtual resetWithCopy : ([CII)V
    //   120: aload_0
    //   121: iload_2
    //   122: putfield _inputPtr : I
    //   125: aload_0
    //   126: invokevirtual _finishString2 : ()V
    //   129: goto -> 87
  }
  
  protected void _finishString2() {
    char[] arrayOfChar = this._textBuffer.getCurrentSegment();
    for (int i = this._textBuffer.getCurrentSegmentSize();; i = j) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(": was expecting closing quote for a string value"); 
      char[] arrayOfChar1 = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = j + 1;
      char c1 = arrayOfChar1[j];
      char c = c1;
      if (c1 <= '\\')
        if (c1 == '\\') {
          c = _decodeEscaped();
        } else {
          c = c1;
          if (c1 <= '"') {
            if (c1 == '"') {
              this._textBuffer.setCurrentLength(i);
              return;
            } 
            c = c1;
            if (c1 < ' ') {
              _throwUnquotedSpace(c1, "string value");
              c = c1;
            } 
          } 
        }  
      if (i >= arrayOfChar.length) {
        arrayOfChar = this._textBuffer.finishCurrentSegment();
        i = 0;
      } 
      j = i + 1;
      arrayOfChar[i] = c;
    } 
  }
  
  protected final String _getText2(JsonToken paramJsonToken) {
    if (paramJsonToken == null)
      return null; 
    switch (ReaderBasedParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonToken.ordinal()]) {
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
  
  protected final JsonToken _handleApostropheValue() {
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    for (int i = this._textBuffer.getCurrentSegmentSize();; i = j) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOF(": was expecting closing quote for a string value"); 
      char[] arrayOfChar1 = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = j + 1;
      char c1 = arrayOfChar1[j];
      char c = c1;
      if (c1 <= '\\')
        if (c1 == '\\') {
          c = _decodeEscaped();
        } else {
          c = c1;
          if (c1 <= '\'') {
            if (c1 == '\'') {
              this._textBuffer.setCurrentLength(i);
              return JsonToken.VALUE_STRING;
            } 
            c = c1;
            if (c1 < ' ') {
              _throwUnquotedSpace(c1, "string value");
              c = c1;
            } 
          } 
        }  
      if (i >= arrayOfChar.length) {
        arrayOfChar = this._textBuffer.finishCurrentSegment();
        i = 0;
      } 
      j = i + 1;
      arrayOfChar[i] = c;
    } 
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean) {
    double d = Double.NEGATIVE_INFINITY;
    int i = paramInt;
    if (paramInt == 73) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOFInValue(); 
      char[] arrayOfChar = this._inputBuffer;
      paramInt = this._inputPtr;
      this._inputPtr = paramInt + 1;
      paramInt = arrayOfChar[paramInt];
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
  
  protected final JsonToken _handleUnexpectedValue(int paramInt) {
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
    char[] arrayOfChar = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = paramInt + 1;
    return _handleInvalidNumberStart(arrayOfChar[paramInt], false);
  }
  
  protected final String _handleUnusualFieldName(int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iload_1
    //   6: bipush #39
    //   8: if_icmpne -> 30
    //   11: aload_0
    //   12: getstatic com/flurry/org/codehaus/jackson/JsonParser$Feature.ALLOW_SINGLE_QUOTES : Lcom/flurry/org/codehaus/jackson/JsonParser$Feature;
    //   15: invokevirtual isEnabled : (Lcom/flurry/org/codehaus/jackson/JsonParser$Feature;)Z
    //   18: ifeq -> 30
    //   21: aload_0
    //   22: invokevirtual _parseApostropheFieldName : ()Ljava/lang/String;
    //   25: astore #8
    //   27: aload #8
    //   29: areturn
    //   30: aload_0
    //   31: getstatic com/flurry/org/codehaus/jackson/JsonParser$Feature.ALLOW_UNQUOTED_FIELD_NAMES : Lcom/flurry/org/codehaus/jackson/JsonParser$Feature;
    //   34: invokevirtual isEnabled : (Lcom/flurry/org/codehaus/jackson/JsonParser$Feature;)Z
    //   37: ifne -> 48
    //   40: aload_0
    //   41: iload_1
    //   42: ldc_w 'was expecting double-quote to start field name'
    //   45: invokevirtual _reportUnexpectedChar : (ILjava/lang/String;)V
    //   48: invokestatic getInputCodeLatin1JsNames : ()[I
    //   51: astore #8
    //   53: aload #8
    //   55: arraylength
    //   56: istore #5
    //   58: iload_1
    //   59: iload #5
    //   61: if_icmpge -> 180
    //   64: aload #8
    //   66: iload_1
    //   67: iaload
    //   68: ifne -> 174
    //   71: iload_1
    //   72: bipush #48
    //   74: if_icmplt -> 83
    //   77: iload_1
    //   78: bipush #57
    //   80: if_icmple -> 174
    //   83: iconst_1
    //   84: istore #7
    //   86: iload #7
    //   88: ifne -> 99
    //   91: aload_0
    //   92: iload_1
    //   93: ldc_w 'was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name'
    //   96: invokevirtual _reportUnexpectedChar : (ILjava/lang/String;)V
    //   99: aload_0
    //   100: getfield _inputPtr : I
    //   103: istore_2
    //   104: aload_0
    //   105: getfield _inputEnd : I
    //   108: istore #6
    //   110: iload_2
    //   111: istore_1
    //   112: iload_2
    //   113: iload #6
    //   115: if_icmpge -> 258
    //   118: iload #4
    //   120: istore_1
    //   121: aload_0
    //   122: getfield _inputBuffer : [C
    //   125: iload_2
    //   126: caload
    //   127: istore_3
    //   128: iload_3
    //   129: iload #5
    //   131: if_icmpge -> 190
    //   134: aload #8
    //   136: iload_3
    //   137: iaload
    //   138: ifeq -> 231
    //   141: aload_0
    //   142: getfield _inputPtr : I
    //   145: iconst_1
    //   146: isub
    //   147: istore_3
    //   148: aload_0
    //   149: iload_2
    //   150: putfield _inputPtr : I
    //   153: aload_0
    //   154: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer;
    //   157: aload_0
    //   158: getfield _inputBuffer : [C
    //   161: iload_3
    //   162: iload_2
    //   163: iload_3
    //   164: isub
    //   165: iload_1
    //   166: invokevirtual findSymbol : ([CIII)Ljava/lang/String;
    //   169: astore #8
    //   171: goto -> 27
    //   174: iconst_0
    //   175: istore #7
    //   177: goto -> 86
    //   180: iload_1
    //   181: i2c
    //   182: invokestatic isJavaIdentifierPart : (C)Z
    //   185: istore #7
    //   187: goto -> 86
    //   190: iload_3
    //   191: i2c
    //   192: invokestatic isJavaIdentifierPart : (C)Z
    //   195: ifne -> 231
    //   198: aload_0
    //   199: getfield _inputPtr : I
    //   202: iconst_1
    //   203: isub
    //   204: istore_3
    //   205: aload_0
    //   206: iload_2
    //   207: putfield _inputPtr : I
    //   210: aload_0
    //   211: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer;
    //   214: aload_0
    //   215: getfield _inputBuffer : [C
    //   218: iload_3
    //   219: iload_2
    //   220: iload_3
    //   221: isub
    //   222: iload_1
    //   223: invokevirtual findSymbol : ([CIII)Ljava/lang/String;
    //   226: astore #8
    //   228: goto -> 27
    //   231: iload_1
    //   232: bipush #31
    //   234: imul
    //   235: iload_3
    //   236: iadd
    //   237: istore_3
    //   238: iload_2
    //   239: iconst_1
    //   240: iadd
    //   241: istore #4
    //   243: iload #4
    //   245: istore_2
    //   246: iload_3
    //   247: istore_1
    //   248: iload #4
    //   250: iload #6
    //   252: if_icmplt -> 121
    //   255: iload #4
    //   257: istore_1
    //   258: aload_0
    //   259: getfield _inputPtr : I
    //   262: istore_2
    //   263: aload_0
    //   264: iload_1
    //   265: putfield _inputPtr : I
    //   268: aload_0
    //   269: iload_2
    //   270: iconst_1
    //   271: isub
    //   272: iload_3
    //   273: aload #8
    //   275: invokespecial _parseUnusualFieldName2 : (II[I)Ljava/lang/String;
    //   278: astore #8
    //   280: goto -> 27
  }
  
  protected final void _matchToken(String paramString, int paramInt) {
    int i = paramString.length();
    while (true) {
      if (this._inputPtr >= this._inputEnd && !loadMore())
        _reportInvalidEOFInValue(); 
      if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt))
        _reportInvalidToken(paramString.substring(0, paramInt), "'null', 'true', 'false' or NaN"); 
      this._inputPtr++;
      int j = paramInt + 1;
      paramInt = j;
      if (j >= i) {
        if (this._inputPtr < this._inputEnd || loadMore()) {
          char c = this._inputBuffer[this._inputPtr];
          if (c >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
            this._inputPtr++;
            _reportInvalidToken(paramString.substring(0, j), "'null', 'true', 'false' or NaN");
          } 
        } 
        return;
      } 
    } 
  }
  
  protected final String _parseApostropheFieldName() {
    int k = this._inputPtr;
    int i = 0;
    int m = 0;
    int n = this._inputEnd;
    int j = k;
    if (k < n) {
      int[] arrayOfInt = CharTypes.getInputCodeLatin1();
      int i1 = arrayOfInt.length;
      j = k;
      i = m;
      while (true) {
        k = this._inputBuffer[j];
        if (k == 39) {
          k = this._inputPtr;
          this._inputPtr = j + 1;
          return this._symbols.findSymbol(this._inputBuffer, k, j - k, i);
        } 
        if (k >= i1 || arrayOfInt[k] == 0) {
          m = i * 31 + k;
          k = j + 1;
          i = m;
          j = k;
          if (k >= n) {
            i = m;
            j = k;
          } else {
            continue;
          } 
        } 
        k = this._inputPtr;
        this._inputPtr = j;
        return _parseFieldName2(k, i, 39);
      } 
    } 
    k = this._inputPtr;
    this._inputPtr = j;
    return _parseFieldName2(k, i, 39);
  }
  
  protected final String _parseFieldName(int paramInt) {
    // Byte code:
    //   0: iload_1
    //   1: bipush #34
    //   3: if_icmpeq -> 16
    //   6: aload_0
    //   7: iload_1
    //   8: invokevirtual _handleUnusualFieldName : (I)Ljava/lang/String;
    //   11: astore #8
    //   13: aload #8
    //   15: areturn
    //   16: aload_0
    //   17: getfield _inputPtr : I
    //   20: istore_1
    //   21: iconst_0
    //   22: istore #4
    //   24: iconst_0
    //   25: istore_3
    //   26: aload_0
    //   27: getfield _inputEnd : I
    //   30: istore #5
    //   32: iload_1
    //   33: istore_2
    //   34: iload_1
    //   35: iload #5
    //   37: if_icmpge -> 142
    //   40: invokestatic getInputCodeLatin1 : ()[I
    //   43: astore #8
    //   45: aload #8
    //   47: arraylength
    //   48: istore #6
    //   50: aload_0
    //   51: getfield _inputBuffer : [C
    //   54: iload_1
    //   55: caload
    //   56: istore #7
    //   58: iload #7
    //   60: iload #6
    //   62: if_icmpge -> 118
    //   65: aload #8
    //   67: iload #7
    //   69: iaload
    //   70: ifeq -> 118
    //   73: iload_3
    //   74: istore #4
    //   76: iload_1
    //   77: istore_2
    //   78: iload #7
    //   80: bipush #34
    //   82: if_icmpne -> 142
    //   85: aload_0
    //   86: getfield _inputPtr : I
    //   89: istore_2
    //   90: aload_0
    //   91: iload_1
    //   92: iconst_1
    //   93: iadd
    //   94: putfield _inputPtr : I
    //   97: aload_0
    //   98: getfield _symbols : Lcom/flurry/org/codehaus/jackson/sym/CharsToNameCanonicalizer;
    //   101: aload_0
    //   102: getfield _inputBuffer : [C
    //   105: iload_2
    //   106: iload_1
    //   107: iload_2
    //   108: isub
    //   109: iload_3
    //   110: invokevirtual findSymbol : ([CIII)Ljava/lang/String;
    //   113: astore #8
    //   115: goto -> 13
    //   118: iload_3
    //   119: bipush #31
    //   121: imul
    //   122: iload #7
    //   124: iadd
    //   125: istore #4
    //   127: iload_1
    //   128: iconst_1
    //   129: iadd
    //   130: istore_2
    //   131: iload #4
    //   133: istore_3
    //   134: iload_2
    //   135: istore_1
    //   136: iload_2
    //   137: iload #5
    //   139: if_icmplt -> 50
    //   142: aload_0
    //   143: getfield _inputPtr : I
    //   146: istore_1
    //   147: aload_0
    //   148: iload_2
    //   149: putfield _inputPtr : I
    //   152: aload_0
    //   153: iload_1
    //   154: iload #4
    //   156: bipush #34
    //   158: invokespecial _parseFieldName2 : (III)Ljava/lang/String;
    //   161: astore #8
    //   163: goto -> 13
  }
  
  protected void _releaseBuffers() {
    super._releaseBuffers();
    char[] arrayOfChar = this._inputBuffer;
    if (arrayOfChar != null) {
      this._inputBuffer = null;
      this._ioContext.releaseTokenBuffer(arrayOfChar);
    } 
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(paramString1);
    while (true) {
      if (this._inputPtr < this._inputEnd || loadMore()) {
        char c = this._inputBuffer[this._inputPtr];
        if (Character.isJavaIdentifierPart(c)) {
          this._inputPtr++;
          stringBuilder.append(c);
          continue;
        } 
      } 
      _reportError("Unrecognized token '" + stringBuilder.toString() + "': was expecting ");
      return;
    } 
  }
  
  protected final void _skipCR() {
    if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == '\n')
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
    int i = this._inputPtr;
    int j = this._inputEnd;
    char[] arrayOfChar = this._inputBuffer;
    while (true) {
      int k = j;
      int m = i;
      if (i >= j) {
        this._inputPtr = i;
        if (!loadMore())
          _reportInvalidEOF(": was expecting closing quote for a string value"); 
        m = this._inputPtr;
        k = this._inputEnd;
      } 
      i = m + 1;
      j = arrayOfChar[m];
      if (j <= 92) {
        if (j == 92) {
          this._inputPtr = i;
          _decodeEscaped();
          i = this._inputPtr;
          j = this._inputEnd;
          continue;
        } 
        if (j <= 34) {
          if (j == 34) {
            this._inputPtr = i;
            return;
          } 
          if (j < 32) {
            this._inputPtr = i;
            _throwUnquotedSpace(j, "string value");
          } 
        } 
      } 
      j = k;
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
    return this._reader;
  }
  
  protected char getNextChar(String paramString) {
    if (this._inputPtr >= this._inputEnd && !loadMore())
      _reportInvalidEOF(paramString); 
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = i + 1;
    return arrayOfChar[i];
  }
  
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
  
  public char[] getTextCharacters() {
    if (this._currToken != null) {
      switch (ReaderBasedParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
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
      switch (ReaderBasedParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
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
      switch (ReaderBasedParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[this._currToken.ordinal()]) {
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
    if (this._reader != null) {
      i = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
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
      throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd); 
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
    switch (ReaderBasedParser$1.$SwitchMap$org$codehaus$jackson$JsonToken[nextToken().ordinal()]) {
      default:
        return bool;
      case 5:
        return Boolean.TRUE;
      case 6:
        break;
    } 
    return Boolean.FALSE;
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
    JsonToken jsonToken;
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
      JsonToken jsonToken1 = JsonToken.END_ARRAY;
      this._currToken = jsonToken1;
      return jsonToken1;
    } 
    if (j == 125) {
      if (!this._parsingContext.inObject())
        _reportMismatchedEndMarker(j, ']'); 
      this._parsingContext = this._parsingContext.getParent();
      JsonToken jsonToken1 = JsonToken.END_OBJECT;
      this._currToken = jsonToken1;
      return jsonToken1;
    } 
    int i = j;
    if (this._parsingContext.expectComma()) {
      if (j != 44)
        _reportUnexpectedChar(j, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries"); 
      i = _skipWS();
    } 
    boolean bool = this._parsingContext.inObject();
    j = i;
    if (bool) {
      String str = _parseFieldName(i);
      this._parsingContext.setCurrentName(str);
      this._currToken = JsonToken.FIELD_NAME;
      i = _skipWS();
      if (i != 58)
        _reportUnexpectedChar(i, "was expecting a colon to separate field name and value"); 
      j = _skipWS();
    } 
    switch (j) {
      default:
        jsonToken = _handleUnexpectedValue(j);
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 34:
        this._tokenIncomplete = true;
        jsonToken = JsonToken.VALUE_STRING;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 91:
        if (!bool)
          this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol); 
        jsonToken = JsonToken.START_ARRAY;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 123:
        if (!bool)
          this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol); 
        jsonToken = JsonToken.START_OBJECT;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 93:
      case 125:
        _reportUnexpectedChar(j, "expected a value");
      case 116:
        _matchToken("true", 1);
        jsonToken = JsonToken.VALUE_TRUE;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 102:
        _matchToken("false", 1);
        jsonToken = JsonToken.VALUE_FALSE;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
      case 110:
        _matchToken("null", 1);
        jsonToken = JsonToken.VALUE_NULL;
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
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
        jsonToken = parseNumberText(j);
        if (bool) {
          this._nextToken = jsonToken;
          return this._currToken;
        } 
        break;
    } 
    this._currToken = jsonToken;
    return jsonToken;
  }
  
  protected final JsonToken parseNumberText(int paramInt) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #4
    //   3: iconst_0
    //   4: istore #6
    //   6: iconst_0
    //   7: istore #9
    //   9: iload_1
    //   10: bipush #45
    //   12: if_icmpne -> 73
    //   15: iconst_1
    //   16: istore #11
    //   18: aload_0
    //   19: getfield _inputPtr : I
    //   22: istore_2
    //   23: iload_2
    //   24: iconst_1
    //   25: isub
    //   26: istore #8
    //   28: aload_0
    //   29: getfield _inputEnd : I
    //   32: istore #10
    //   34: iload #11
    //   36: ifeq -> 131
    //   39: iload_2
    //   40: aload_0
    //   41: getfield _inputEnd : I
    //   44: if_icmplt -> 79
    //   47: iload #11
    //   49: ifeq -> 430
    //   52: iload #8
    //   54: iconst_1
    //   55: iadd
    //   56: istore_1
    //   57: aload_0
    //   58: iload_1
    //   59: putfield _inputPtr : I
    //   62: aload_0
    //   63: iload #11
    //   65: invokespecial parseNumberText2 : (Z)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   68: astore #12
    //   70: aload #12
    //   72: areturn
    //   73: iconst_0
    //   74: istore #11
    //   76: goto -> 18
    //   79: aload_0
    //   80: getfield _inputBuffer : [C
    //   83: astore #12
    //   85: iload_2
    //   86: iconst_1
    //   87: iadd
    //   88: istore_3
    //   89: aload #12
    //   91: iload_2
    //   92: caload
    //   93: istore #5
    //   95: iload #5
    //   97: bipush #57
    //   99: if_icmpgt -> 114
    //   102: iload_3
    //   103: istore_1
    //   104: iload #5
    //   106: istore_2
    //   107: iload #5
    //   109: bipush #48
    //   111: if_icmpge -> 137
    //   114: aload_0
    //   115: iload_3
    //   116: putfield _inputPtr : I
    //   119: aload_0
    //   120: iload #5
    //   122: iconst_1
    //   123: invokevirtual _handleInvalidNumberStart : (IZ)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   126: astore #12
    //   128: goto -> 70
    //   131: iload_2
    //   132: istore_3
    //   133: iload_1
    //   134: istore_2
    //   135: iload_3
    //   136: istore_1
    //   137: iload_2
    //   138: bipush #48
    //   140: if_icmpeq -> 47
    //   143: iload_1
    //   144: istore_2
    //   145: iload_2
    //   146: aload_0
    //   147: getfield _inputEnd : I
    //   150: if_icmpge -> 47
    //   153: aload_0
    //   154: getfield _inputBuffer : [C
    //   157: astore #12
    //   159: iload_2
    //   160: iconst_1
    //   161: iadd
    //   162: istore_1
    //   163: aload #12
    //   165: iload_2
    //   166: caload
    //   167: istore_2
    //   168: iload_2
    //   169: bipush #48
    //   171: if_icmplt -> 180
    //   174: iload_2
    //   175: bipush #57
    //   177: if_icmple -> 347
    //   180: iload_2
    //   181: bipush #46
    //   183: if_icmpne -> 444
    //   186: iconst_0
    //   187: istore_3
    //   188: iload_1
    //   189: istore_2
    //   190: iload_3
    //   191: istore_1
    //   192: iload_2
    //   193: iload #10
    //   195: if_icmpge -> 47
    //   198: aload_0
    //   199: getfield _inputBuffer : [C
    //   202: astore #12
    //   204: iload_2
    //   205: iconst_1
    //   206: iadd
    //   207: istore_3
    //   208: aload #12
    //   210: iload_2
    //   211: caload
    //   212: istore_2
    //   213: iload_2
    //   214: bipush #48
    //   216: if_icmplt -> 225
    //   219: iload_2
    //   220: bipush #57
    //   222: if_icmple -> 355
    //   225: iload_1
    //   226: ifne -> 236
    //   229: aload_0
    //   230: iload_2
    //   231: ldc 'Decimal point not followed by a digit'
    //   233: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   236: iload_1
    //   237: istore #5
    //   239: iload_3
    //   240: istore_1
    //   241: iload_2
    //   242: bipush #101
    //   244: if_icmpeq -> 256
    //   247: iload_1
    //   248: istore #7
    //   250: iload_2
    //   251: bipush #69
    //   253: if_icmpne -> 386
    //   256: iload_1
    //   257: iload #10
    //   259: if_icmpge -> 47
    //   262: aload_0
    //   263: getfield _inputBuffer : [C
    //   266: astore #12
    //   268: iload_1
    //   269: iconst_1
    //   270: iadd
    //   271: istore_2
    //   272: aload #12
    //   274: iload_1
    //   275: caload
    //   276: istore_3
    //   277: iload_3
    //   278: bipush #45
    //   280: if_icmpeq -> 289
    //   283: iload_3
    //   284: bipush #43
    //   286: if_icmpne -> 436
    //   289: iload_2
    //   290: iload #10
    //   292: if_icmpge -> 47
    //   295: aload_0
    //   296: getfield _inputBuffer : [C
    //   299: astore #12
    //   301: iload_2
    //   302: iconst_1
    //   303: iadd
    //   304: istore_1
    //   305: aload #12
    //   307: iload_2
    //   308: caload
    //   309: istore_3
    //   310: iload #9
    //   312: istore_2
    //   313: iload_3
    //   314: bipush #57
    //   316: if_icmpgt -> 363
    //   319: iload_3
    //   320: bipush #48
    //   322: if_icmplt -> 363
    //   325: iinc #2, 1
    //   328: iload_1
    //   329: iload #10
    //   331: if_icmpge -> 47
    //   334: aload_0
    //   335: getfield _inputBuffer : [C
    //   338: iload_1
    //   339: caload
    //   340: istore_3
    //   341: iinc #1, 1
    //   344: goto -> 313
    //   347: iinc #4, 1
    //   350: iload_1
    //   351: istore_2
    //   352: goto -> 145
    //   355: iinc #1, 1
    //   358: iload_3
    //   359: istore_2
    //   360: goto -> 192
    //   363: iload_2
    //   364: istore #6
    //   366: iload_1
    //   367: istore #7
    //   369: iload_2
    //   370: ifne -> 386
    //   373: aload_0
    //   374: iload_3
    //   375: ldc 'Exponent indicator not followed by a digit'
    //   377: invokevirtual reportUnexpectedNumberChar : (ILjava/lang/String;)V
    //   380: iload_1
    //   381: istore #7
    //   383: iload_2
    //   384: istore #6
    //   386: iload #7
    //   388: iconst_1
    //   389: isub
    //   390: istore_1
    //   391: aload_0
    //   392: iload_1
    //   393: putfield _inputPtr : I
    //   396: aload_0
    //   397: getfield _textBuffer : Lcom/flurry/org/codehaus/jackson/util/TextBuffer;
    //   400: aload_0
    //   401: getfield _inputBuffer : [C
    //   404: iload #8
    //   406: iload_1
    //   407: iload #8
    //   409: isub
    //   410: invokevirtual resetWithShared : ([CII)V
    //   413: aload_0
    //   414: iload #11
    //   416: iload #4
    //   418: iload #5
    //   420: iload #6
    //   422: invokevirtual reset : (ZIII)Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   425: astore #12
    //   427: goto -> 70
    //   430: iload #8
    //   432: istore_1
    //   433: goto -> 57
    //   436: iload_2
    //   437: istore_1
    //   438: iload #9
    //   440: istore_2
    //   441: goto -> 313
    //   444: iconst_0
    //   445: istore #5
    //   447: goto -> 241
  }
  
  public int releaseBuffered(Writer paramWriter) {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1)
      return 0; 
    int j = this._inputPtr;
    paramWriter.write(this._inputBuffer, j, i);
    return i;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\ReaderBasedParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */