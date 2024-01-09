package com.flurry.org.codehaus.jackson.impl;

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

public final class WriterBasedGenerator extends JsonGeneratorBase {
  protected static final char[] HEX_CHARS = CharTypes.copyHexChars();
  
  protected static final int SHORT_WRITE = 32;
  
  protected static final int[] sOutputEscapes = CharTypes.get7BitOutputEscapes();
  
  protected CharacterEscapes _characterEscapes;
  
  protected SerializableString _currentEscape;
  
  protected char[] _entityBuffer;
  
  protected final IOContext _ioContext;
  
  protected int _maximumNonEscapedChar;
  
  protected char[] _outputBuffer;
  
  protected int _outputEnd;
  
  protected int[] _outputEscapes = sOutputEscapes;
  
  protected int _outputHead = 0;
  
  protected int _outputTail = 0;
  
  protected final Writer _writer;
  
  public WriterBasedGenerator(IOContext paramIOContext, int paramInt, ObjectCodec paramObjectCodec, Writer paramWriter) {
    super(paramInt, paramObjectCodec);
    this._ioContext = paramIOContext;
    this._writer = paramWriter;
    this._outputBuffer = paramIOContext.allocConcatBuffer();
    this._outputEnd = this._outputBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII))
      setHighestNonEscapedChar(127); 
  }
  
  private char[] _allocateEntityBuffer() {
    char[] arrayOfChar = new char[14];
    arrayOfChar[0] = '\\';
    arrayOfChar[2] = '\\';
    arrayOfChar[3] = 'u';
    arrayOfChar[4] = '0';
    arrayOfChar[5] = '0';
    arrayOfChar[8] = '\\';
    arrayOfChar[9] = 'u';
    this._entityBuffer = arrayOfChar;
    return arrayOfChar;
  }
  
  private final void _appendCharacterEscape(char paramChar, int paramInt) {
    String str;
    if (paramInt >= 0) {
      if (this._outputTail + 2 > this._outputEnd)
        _flushBuffer(); 
      char[] arrayOfChar = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar[i] = '\\';
      arrayOfChar = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar[i] = (char)paramInt;
      return;
    } 
    if (paramInt != -2) {
      if (this._outputTail + 2 > this._outputEnd)
        _flushBuffer(); 
      paramInt = this._outputTail;
      char[] arrayOfChar = this._outputBuffer;
      int j = paramInt + 1;
      arrayOfChar[paramInt] = '\\';
      paramInt = j + 1;
      arrayOfChar[j] = 'u';
      if (i > 255) {
        int k = i >> 8 & 0xFF;
        j = paramInt + 1;
        arrayOfChar[paramInt] = HEX_CHARS[k >> 4];
        paramInt = j + 1;
        arrayOfChar[j] = HEX_CHARS[k & 0xF];
        i = (char)(i & 0xFF);
      } else {
        j = paramInt + 1;
        arrayOfChar[paramInt] = '0';
        paramInt = j + 1;
        arrayOfChar[j] = '0';
      } 
      j = paramInt + 1;
      arrayOfChar[paramInt] = HEX_CHARS[i >> 4];
      arrayOfChar[j] = HEX_CHARS[i & 0xF];
      this._outputTail = j;
      return;
    } 
    if (this._currentEscape == null) {
      str = this._characterEscapes.getEscapeSequence(i).getValue();
    } else {
      str = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int i = str.length();
    if (this._outputTail + i > this._outputEnd) {
      _flushBuffer();
      if (i > this._outputEnd) {
        this._writer.write(str);
        return;
      } 
    } 
    str.getChars(0, i, this._outputBuffer, this._outputTail);
    this._outputTail += i;
  }
  
  private final int _prependOrWriteCharacterEscape(char[] paramArrayOfchar, int paramInt1, int paramInt2, char paramChar, int paramInt3) {
    String str;
    if (paramInt3 >= 0) {
      if (paramInt1 > 1 && paramInt1 < paramInt2) {
        paramInt1 -= 2;
        paramArrayOfchar[paramInt1] = '\\';
        paramArrayOfchar[paramInt1 + 1] = (char)paramInt3;
        return paramInt1;
      } 
      char[] arrayOfChar = this._entityBuffer;
      paramArrayOfchar = arrayOfChar;
      if (arrayOfChar == null)
        paramArrayOfchar = _allocateEntityBuffer(); 
      paramArrayOfchar[1] = (char)paramInt3;
      this._writer.write(paramArrayOfchar, 0, 2);
      return paramInt1;
    } 
    if (paramInt3 != -2) {
      if (paramInt1 > 5 && paramInt1 < paramInt2) {
        paramInt1 -= 6;
        paramInt2 = paramInt1 + 1;
        paramArrayOfchar[paramInt1] = '\\';
        paramInt1 = paramInt2 + 1;
        paramArrayOfchar[paramInt2] = 'u';
        if (paramChar > 'ÿ') {
          paramInt3 = paramChar >> 8 & 0xFF;
          paramInt2 = paramInt1 + 1;
          paramArrayOfchar[paramInt1] = HEX_CHARS[paramInt3 >> 4];
          paramInt1 = paramInt2 + 1;
          paramArrayOfchar[paramInt2] = HEX_CHARS[paramInt3 & 0xF];
          paramChar = (char)(paramChar & 0xFF);
        } else {
          paramInt2 = paramInt1 + 1;
          paramArrayOfchar[paramInt1] = '0';
          paramInt1 = paramInt2 + 1;
          paramArrayOfchar[paramInt2] = '0';
        } 
        paramInt2 = paramInt1 + 1;
        paramArrayOfchar[paramInt1] = HEX_CHARS[paramChar >> 4];
        paramArrayOfchar[paramInt2] = HEX_CHARS[paramChar & 0xF];
        return paramInt2 - 5;
      } 
      char[] arrayOfChar = this._entityBuffer;
      paramArrayOfchar = arrayOfChar;
      if (arrayOfChar == null)
        paramArrayOfchar = _allocateEntityBuffer(); 
      this._outputHead = this._outputTail;
      if (paramChar > 'ÿ') {
        paramInt2 = paramChar >> 8 & 0xFF;
        i = paramChar & 0xFF;
        paramArrayOfchar[10] = HEX_CHARS[paramInt2 >> 4];
        paramArrayOfchar[11] = HEX_CHARS[paramInt2 & 0xF];
        paramArrayOfchar[12] = HEX_CHARS[i >> 4];
        paramArrayOfchar[13] = HEX_CHARS[i & 0xF];
        this._writer.write(paramArrayOfchar, 8, 6);
        return paramInt1;
      } 
      paramArrayOfchar[6] = HEX_CHARS[i >> 4];
      paramArrayOfchar[7] = HEX_CHARS[i & 0xF];
      this._writer.write(paramArrayOfchar, 2, 6);
      return paramInt1;
    } 
    if (this._currentEscape == null) {
      str = this._characterEscapes.getEscapeSequence(i).getValue();
    } else {
      str = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int i = str.length();
    if (paramInt1 >= i && paramInt1 < paramInt2) {
      paramInt1 -= i;
      str.getChars(0, i, paramArrayOfchar, paramInt1);
      return paramInt1;
    } 
    this._writer.write(str);
    return paramInt1;
  }
  
  private final void _prependOrWriteCharacterEscape(char paramChar, int paramInt) {
    String str;
    if (paramInt >= 0) {
      if (this._outputTail >= 2) {
        i = this._outputTail - 2;
        this._outputHead = i;
        this._outputBuffer[i] = '\\';
        this._outputBuffer[i + 1] = (char)paramInt;
        return;
      } 
      char[] arrayOfChar2 = this._entityBuffer;
      char[] arrayOfChar1 = arrayOfChar2;
      if (arrayOfChar2 == null)
        arrayOfChar1 = _allocateEntityBuffer(); 
      this._outputHead = this._outputTail;
      arrayOfChar1[1] = (char)paramInt;
      this._writer.write(arrayOfChar1, 0, 2);
      return;
    } 
    if (paramInt != -2) {
      if (this._outputTail >= 6) {
        char[] arrayOfChar = this._outputBuffer;
        paramInt = this._outputTail - 6;
        this._outputHead = paramInt;
        arrayOfChar[paramInt] = '\\';
        arrayOfChar[++paramInt] = 'u';
        if (i > 255) {
          int j = i >> 8 & 0xFF;
          arrayOfChar[++paramInt] = HEX_CHARS[j >> 4];
          arrayOfChar[++paramInt] = HEX_CHARS[j & 0xF];
          i = (char)(i & 0xFF);
        } else {
          arrayOfChar[++paramInt] = '0';
          arrayOfChar[++paramInt] = '0';
        } 
        arrayOfChar[++paramInt] = HEX_CHARS[i >> 4];
        arrayOfChar[paramInt + 1] = HEX_CHARS[i & 0xF];
        return;
      } 
      char[] arrayOfChar2 = this._entityBuffer;
      char[] arrayOfChar1 = arrayOfChar2;
      if (arrayOfChar2 == null)
        arrayOfChar1 = _allocateEntityBuffer(); 
      this._outputHead = this._outputTail;
      if (i > 255) {
        paramInt = i >> 8 & 0xFF;
        i &= 0xFF;
        arrayOfChar1[10] = HEX_CHARS[paramInt >> 4];
        arrayOfChar1[11] = HEX_CHARS[paramInt & 0xF];
        arrayOfChar1[12] = HEX_CHARS[i >> 4];
        arrayOfChar1[13] = HEX_CHARS[i & 0xF];
        this._writer.write(arrayOfChar1, 8, 6);
        return;
      } 
      arrayOfChar1[6] = HEX_CHARS[i >> 4];
      arrayOfChar1[7] = HEX_CHARS[i & 0xF];
      this._writer.write(arrayOfChar1, 2, 6);
      return;
    } 
    if (this._currentEscape == null) {
      str = this._characterEscapes.getEscapeSequence(i).getValue();
    } else {
      str = this._currentEscape.getValue();
      this._currentEscape = null;
    } 
    int i = str.length();
    if (this._outputTail >= i) {
      paramInt = this._outputTail - i;
      this._outputHead = paramInt;
      str.getChars(0, i, this._outputBuffer, paramInt);
      return;
    } 
    this._outputHead = this._outputTail;
    this._writer.write(str);
  }
  
  private void _writeLongString(String paramString) {
    _flushBuffer();
    int j = paramString.length();
    int i = 0;
    while (true) {
      int m = this._outputEnd;
      int k = m;
      if (i + m > j)
        k = j - i; 
      paramString.getChars(i, i + k, this._outputBuffer, 0);
      if (this._characterEscapes != null) {
        _writeSegmentCustom(k);
      } else if (this._maximumNonEscapedChar != 0) {
        _writeSegmentASCII(k, this._maximumNonEscapedChar);
      } else {
        _writeSegment(k);
      } 
      k = i + k;
      i = k;
      if (k >= j)
        return; 
    } 
  }
  
  private final void _writeNull() {
    if (this._outputTail + 4 >= this._outputEnd)
      _flushBuffer(); 
    int i = this._outputTail;
    char[] arrayOfChar = this._outputBuffer;
    arrayOfChar[i] = 'n';
    arrayOfChar[++i] = 'u';
    arrayOfChar[++i] = 'l';
    arrayOfChar[++i] = 'l';
    this._outputTail = i + 1;
  }
  
  private final void _writeQuotedInt(int paramInt) {
    if (this._outputTail + 13 >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '"';
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    arrayOfChar = this._outputBuffer;
    paramInt = this._outputTail;
    this._outputTail = paramInt + 1;
    arrayOfChar[paramInt] = '"';
  }
  
  private final void _writeQuotedLong(long paramLong) {
    if (this._outputTail + 23 >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '"';
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    arrayOfChar = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '"';
  }
  
  private final void _writeQuotedRaw(Object paramObject) {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '"';
    writeRaw(paramObject.toString());
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    paramObject = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    paramObject[i] = 34;
  }
  
  private final void _writeSegment(int paramInt) {
    int[] arrayOfInt = this._outputEscapes;
    int k = arrayOfInt.length;
    int i = 0;
    int j = 0;
    while (true) {
      if (i < paramInt) {
        char c;
        while (true) {
          c = this._outputBuffer[i];
          if (c >= k || arrayOfInt[c] == 0) {
            int n = i + 1;
            i = n;
            if (n >= paramInt) {
              i = n;
            } else {
              continue;
            } 
          } 
          int m = i - j;
          if (m > 0) {
            this._writer.write(this._outputBuffer, j, m);
            if (i >= paramInt)
              return; 
          } 
          break;
        } 
        j = _prependOrWriteCharacterEscape(this._outputBuffer, ++i, paramInt, c, arrayOfInt[c]);
        continue;
      } 
      continue;
    } 
  }
  
  private final void _writeSegmentASCII(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_0
    //   4: getfield _outputEscapes : [I
    //   7: astore #10
    //   9: aload #10
    //   11: arraylength
    //   12: aload_0
    //   13: getfield _maximumNonEscapedChar : I
    //   16: iconst_1
    //   17: iadd
    //   18: invokestatic min : (II)I
    //   21: istore #9
    //   23: iconst_0
    //   24: istore #6
    //   26: iconst_0
    //   27: istore #5
    //   29: iload #5
    //   31: iload_1
    //   32: if_icmpge -> 97
    //   35: iload #4
    //   37: istore #8
    //   39: aload_0
    //   40: getfield _outputBuffer : [C
    //   43: iload #5
    //   45: caload
    //   46: istore_3
    //   47: iload_3
    //   48: iload #9
    //   50: if_icmpge -> 98
    //   53: aload #10
    //   55: iload_3
    //   56: iaload
    //   57: istore #4
    //   59: iload #4
    //   61: ifeq -> 113
    //   64: iload #5
    //   66: iload #6
    //   68: isub
    //   69: istore #7
    //   71: iload #7
    //   73: ifle -> 140
    //   76: aload_0
    //   77: getfield _writer : Ljava/io/Writer;
    //   80: aload_0
    //   81: getfield _outputBuffer : [C
    //   84: iload #6
    //   86: iload #7
    //   88: invokevirtual write : ([CII)V
    //   91: iload #5
    //   93: iload_1
    //   94: if_icmplt -> 140
    //   97: return
    //   98: iload #8
    //   100: istore #4
    //   102: iload_3
    //   103: iload_2
    //   104: if_icmple -> 113
    //   107: iconst_m1
    //   108: istore #4
    //   110: goto -> 64
    //   113: iload #5
    //   115: iconst_1
    //   116: iadd
    //   117: istore #7
    //   119: iload #4
    //   121: istore #8
    //   123: iload #7
    //   125: istore #5
    //   127: iload #7
    //   129: iload_1
    //   130: if_icmplt -> 39
    //   133: iload #7
    //   135: istore #5
    //   137: goto -> 64
    //   140: iinc #5, 1
    //   143: aload_0
    //   144: aload_0
    //   145: getfield _outputBuffer : [C
    //   148: iload #5
    //   150: iload_1
    //   151: iload_3
    //   152: iload #4
    //   154: invokespecial _prependOrWriteCharacterEscape : ([CIICI)I
    //   157: istore #6
    //   159: goto -> 29
  }
  
  private final void _writeSegmentCustom(int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_0
    //   4: getfield _outputEscapes : [I
    //   7: astore #10
    //   9: aload_0
    //   10: getfield _maximumNonEscapedChar : I
    //   13: iconst_1
    //   14: if_icmpge -> 112
    //   17: ldc 65535
    //   19: istore #5
    //   21: aload #10
    //   23: arraylength
    //   24: aload_0
    //   25: getfield _maximumNonEscapedChar : I
    //   28: iconst_1
    //   29: iadd
    //   30: invokestatic min : (II)I
    //   33: istore #9
    //   35: aload_0
    //   36: getfield _characterEscapes : Lcom/flurry/org/codehaus/jackson/io/CharacterEscapes;
    //   39: astore #12
    //   41: iconst_0
    //   42: istore #6
    //   44: iconst_0
    //   45: istore_3
    //   46: iload #4
    //   48: iload_1
    //   49: if_icmpge -> 111
    //   52: iload_3
    //   53: istore #8
    //   55: aload_0
    //   56: getfield _outputBuffer : [C
    //   59: iload #4
    //   61: caload
    //   62: istore_2
    //   63: iload_2
    //   64: iload #9
    //   66: if_icmpge -> 121
    //   69: aload #10
    //   71: iload_2
    //   72: iaload
    //   73: istore_3
    //   74: iload_3
    //   75: ifeq -> 160
    //   78: iload #4
    //   80: iload #6
    //   82: isub
    //   83: istore #7
    //   85: iload #7
    //   87: ifle -> 186
    //   90: aload_0
    //   91: getfield _writer : Ljava/io/Writer;
    //   94: aload_0
    //   95: getfield _outputBuffer : [C
    //   98: iload #6
    //   100: iload #7
    //   102: invokevirtual write : ([CII)V
    //   105: iload #4
    //   107: iload_1
    //   108: if_icmplt -> 186
    //   111: return
    //   112: aload_0
    //   113: getfield _maximumNonEscapedChar : I
    //   116: istore #5
    //   118: goto -> 21
    //   121: iload_2
    //   122: iload #5
    //   124: if_icmple -> 132
    //   127: iconst_m1
    //   128: istore_3
    //   129: goto -> 78
    //   132: aload #12
    //   134: iload_2
    //   135: invokevirtual getEscapeSequence : (I)Lcom/flurry/org/codehaus/jackson/SerializableString;
    //   138: astore #11
    //   140: aload_0
    //   141: aload #11
    //   143: putfield _currentEscape : Lcom/flurry/org/codehaus/jackson/SerializableString;
    //   146: iload #8
    //   148: istore_3
    //   149: aload #11
    //   151: ifnull -> 160
    //   154: bipush #-2
    //   156: istore_3
    //   157: goto -> 78
    //   160: iload #4
    //   162: iconst_1
    //   163: iadd
    //   164: istore #7
    //   166: iload_3
    //   167: istore #8
    //   169: iload #7
    //   171: istore #4
    //   173: iload #7
    //   175: iload_1
    //   176: if_icmplt -> 55
    //   179: iload #7
    //   181: istore #4
    //   183: goto -> 78
    //   186: iinc #4, 1
    //   189: aload_0
    //   190: aload_0
    //   191: getfield _outputBuffer : [C
    //   194: iload #4
    //   196: iload_1
    //   197: iload_2
    //   198: iload_3
    //   199: invokespecial _prependOrWriteCharacterEscape : ([CIICI)I
    //   202: istore #6
    //   204: goto -> 46
  }
  
  private void _writeString(String paramString) {
    int i = paramString.length();
    if (i > this._outputEnd) {
      _writeLongString(paramString);
      return;
    } 
    if (this._outputTail + i > this._outputEnd)
      _flushBuffer(); 
    paramString.getChars(0, i, this._outputBuffer, this._outputTail);
    if (this._characterEscapes != null) {
      _writeStringCustom(i);
      return;
    } 
    if (this._maximumNonEscapedChar != 0) {
      _writeStringASCII(i, this._maximumNonEscapedChar);
      return;
    } 
    _writeString2(i);
  }
  
  private final void _writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this._characterEscapes != null) {
      _writeStringCustom(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
    if (this._maximumNonEscapedChar != 0) {
      _writeStringASCII(paramArrayOfchar, paramInt1, paramInt2, this._maximumNonEscapedChar);
      return;
    } 
    int i = paramInt2 + paramInt1;
    int[] arrayOfInt = this._outputEscapes;
    int j = arrayOfInt.length;
    label30: while (true) {
      if (paramInt1 < i) {
        paramInt2 = paramInt1;
        while (true) {
          char c = paramArrayOfchar[paramInt2];
          if (c < j && arrayOfInt[c] != 0) {
            int m = paramInt2 - paramInt1;
            if (m < 32) {
              if (this._outputTail + m > this._outputEnd)
                _flushBuffer(); 
              if (m > 0) {
                System.arraycopy(paramArrayOfchar, paramInt1, this._outputBuffer, this._outputTail, m);
                this._outputTail += m;
              } 
            } else {
              _flushBuffer();
              this._writer.write(paramArrayOfchar, paramInt1, m);
            } 
            if (paramInt2 < i) {
              paramInt1 = paramInt2 + 1;
              char c1 = paramArrayOfchar[paramInt2];
              _appendCharacterEscape(c1, arrayOfInt[c1]);
              continue label30;
            } 
            return;
          } 
          int k = paramInt2 + 1;
          paramInt2 = k;
          if (k >= i) {
            paramInt2 = k;
            continue;
          } 
        } 
        break;
      } 
      // Byte code: goto -> 14
    } 
  }
  
  private void _writeString2(int paramInt) {
    int i = this._outputTail + paramInt;
    int[] arrayOfInt = this._outputEscapes;
    paramInt = arrayOfInt.length;
    label16: while (this._outputTail < i) {
      int j;
      do {
        char c = this._outputBuffer[this._outputTail];
        if (c < paramInt && arrayOfInt[c] != 0) {
          int k = this._outputTail - this._outputHead;
          if (k > 0)
            this._writer.write(this._outputBuffer, this._outputHead, k); 
          char[] arrayOfChar = this._outputBuffer;
          k = this._outputTail;
          this._outputTail = k + 1;
          char c1 = arrayOfChar[k];
          _prependOrWriteCharacterEscape(c1, arrayOfInt[c1]);
          continue label16;
        } 
        j = this._outputTail + 1;
        this._outputTail = j;
      } while (j < i);
    } 
  }
  
  private void _writeStringASCII(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _outputTail : I
    //   4: iload_1
    //   5: iadd
    //   6: istore #4
    //   8: aload_0
    //   9: getfield _outputEscapes : [I
    //   12: astore #7
    //   14: aload #7
    //   16: arraylength
    //   17: aload_0
    //   18: getfield _maximumNonEscapedChar : I
    //   21: iconst_1
    //   22: iadd
    //   23: invokestatic min : (II)I
    //   26: istore #5
    //   28: aload_0
    //   29: getfield _outputTail : I
    //   32: iload #4
    //   34: if_icmpge -> 142
    //   37: aload_0
    //   38: getfield _outputBuffer : [C
    //   41: aload_0
    //   42: getfield _outputTail : I
    //   45: caload
    //   46: istore_3
    //   47: iload_3
    //   48: iload #5
    //   50: if_icmpge -> 114
    //   53: aload #7
    //   55: iload_3
    //   56: iaload
    //   57: istore_1
    //   58: iload_1
    //   59: ifeq -> 124
    //   62: aload_0
    //   63: getfield _outputTail : I
    //   66: aload_0
    //   67: getfield _outputHead : I
    //   70: isub
    //   71: istore #6
    //   73: iload #6
    //   75: ifle -> 95
    //   78: aload_0
    //   79: getfield _writer : Ljava/io/Writer;
    //   82: aload_0
    //   83: getfield _outputBuffer : [C
    //   86: aload_0
    //   87: getfield _outputHead : I
    //   90: iload #6
    //   92: invokevirtual write : ([CII)V
    //   95: aload_0
    //   96: aload_0
    //   97: getfield _outputTail : I
    //   100: iconst_1
    //   101: iadd
    //   102: putfield _outputTail : I
    //   105: aload_0
    //   106: iload_3
    //   107: iload_1
    //   108: invokespecial _prependOrWriteCharacterEscape : (CI)V
    //   111: goto -> 28
    //   114: iload_3
    //   115: iload_2
    //   116: if_icmple -> 124
    //   119: iconst_m1
    //   120: istore_1
    //   121: goto -> 62
    //   124: aload_0
    //   125: getfield _outputTail : I
    //   128: iconst_1
    //   129: iadd
    //   130: istore_1
    //   131: aload_0
    //   132: iload_1
    //   133: putfield _outputTail : I
    //   136: iload_1
    //   137: iload #4
    //   139: if_icmplt -> 37
    //   142: return
  }
  
  private final void _writeStringASCII(char[] paramArrayOfchar, int paramInt1, int paramInt2, int paramInt3) {
    int k = paramInt2 + paramInt1;
    int[] arrayOfInt = this._outputEscapes;
    int j = Math.min(arrayOfInt.length, paramInt3 + 1);
    int i = 0;
    paramInt2 = paramInt1;
    paramInt1 = i;
    while (true) {
      if (paramInt2 < k) {
        i = paramInt2;
        int m = paramInt1;
        while (true) {
          while (true) {
            char c = paramArrayOfchar[i];
            break;
          } 
          if (SYNTHETIC_LOCAL_VARIABLE_7 >= k) {
            Object object = SYNTHETIC_LOCAL_VARIABLE_7;
            continue;
          } 
        } 
        break;
      } 
      continue;
    } 
  }
  
  private void _writeStringCustom(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _outputTail : I
    //   4: iload_1
    //   5: iadd
    //   6: istore #4
    //   8: aload_0
    //   9: getfield _outputEscapes : [I
    //   12: astore #9
    //   14: aload_0
    //   15: getfield _maximumNonEscapedChar : I
    //   18: iconst_1
    //   19: if_icmpge -> 128
    //   22: ldc 65535
    //   24: istore_3
    //   25: aload #9
    //   27: arraylength
    //   28: iload_3
    //   29: iconst_1
    //   30: iadd
    //   31: invokestatic min : (II)I
    //   34: istore #5
    //   36: aload_0
    //   37: getfield _characterEscapes : Lcom/flurry/org/codehaus/jackson/io/CharacterEscapes;
    //   40: astore #8
    //   42: aload_0
    //   43: getfield _outputTail : I
    //   46: iload #4
    //   48: if_icmpge -> 189
    //   51: aload_0
    //   52: getfield _outputBuffer : [C
    //   55: aload_0
    //   56: getfield _outputTail : I
    //   59: caload
    //   60: istore_2
    //   61: iload_2
    //   62: iload #5
    //   64: if_icmpge -> 136
    //   67: aload #9
    //   69: iload_2
    //   70: iaload
    //   71: istore_1
    //   72: iload_1
    //   73: ifeq -> 171
    //   76: aload_0
    //   77: getfield _outputTail : I
    //   80: aload_0
    //   81: getfield _outputHead : I
    //   84: isub
    //   85: istore #6
    //   87: iload #6
    //   89: ifle -> 109
    //   92: aload_0
    //   93: getfield _writer : Ljava/io/Writer;
    //   96: aload_0
    //   97: getfield _outputBuffer : [C
    //   100: aload_0
    //   101: getfield _outputHead : I
    //   104: iload #6
    //   106: invokevirtual write : ([CII)V
    //   109: aload_0
    //   110: aload_0
    //   111: getfield _outputTail : I
    //   114: iconst_1
    //   115: iadd
    //   116: putfield _outputTail : I
    //   119: aload_0
    //   120: iload_2
    //   121: iload_1
    //   122: invokespecial _prependOrWriteCharacterEscape : (CI)V
    //   125: goto -> 42
    //   128: aload_0
    //   129: getfield _maximumNonEscapedChar : I
    //   132: istore_3
    //   133: goto -> 25
    //   136: iload_2
    //   137: iload_3
    //   138: if_icmple -> 146
    //   141: iconst_m1
    //   142: istore_1
    //   143: goto -> 76
    //   146: aload #8
    //   148: iload_2
    //   149: invokevirtual getEscapeSequence : (I)Lcom/flurry/org/codehaus/jackson/SerializableString;
    //   152: astore #7
    //   154: aload_0
    //   155: aload #7
    //   157: putfield _currentEscape : Lcom/flurry/org/codehaus/jackson/SerializableString;
    //   160: aload #7
    //   162: ifnull -> 171
    //   165: bipush #-2
    //   167: istore_1
    //   168: goto -> 76
    //   171: aload_0
    //   172: getfield _outputTail : I
    //   175: iconst_1
    //   176: iadd
    //   177: istore_1
    //   178: aload_0
    //   179: iload_1
    //   180: putfield _outputTail : I
    //   183: iload_1
    //   184: iload #4
    //   186: if_icmplt -> 51
    //   189: return
  }
  
  private final void _writeStringCustom(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int j;
    int k = paramInt2 + paramInt1;
    int[] arrayOfInt = this._outputEscapes;
    if (this._maximumNonEscapedChar < 1) {
      j = 65535;
    } else {
      j = this._maximumNonEscapedChar;
    } 
    int m = Math.min(arrayOfInt.length, j + 1);
    CharacterEscapes characterEscapes = this._characterEscapes;
    int i = 0;
    paramInt2 = paramInt1;
    paramInt1 = i;
    while (true) {
      if (paramInt2 < k) {
        i = paramInt2;
        int n = paramInt1;
        while (true) {
          while (true) {
            char c = paramArrayOfchar[i];
            break;
          } 
          if (SYNTHETIC_LOCAL_VARIABLE_7 >= k) {
            Object object = SYNTHETIC_LOCAL_VARIABLE_7;
            continue;
          } 
        } 
        break;
      } 
      continue;
    } 
  }
  
  private void writeRawLong(String paramString) {
    int j = this._outputEnd - this._outputTail;
    paramString.getChars(0, j, this._outputBuffer, this._outputTail);
    this._outputTail += j;
    _flushBuffer();
    int i;
    for (i = paramString.length() - j; i > this._outputEnd; i -= k) {
      int k = this._outputEnd;
      paramString.getChars(j, j + k, this._outputBuffer, 0);
      this._outputHead = 0;
      this._outputTail = k;
      _flushBuffer();
      j += k;
    } 
    paramString.getChars(j, j + i, this._outputBuffer, 0);
    this._outputHead = 0;
    this._outputTail = i;
  }
  
  protected final void _flushBuffer() {
    int i = this._outputTail - this._outputHead;
    if (i > 0) {
      int j = this._outputHead;
      this._outputHead = 0;
      this._outputTail = 0;
      this._writer.write(this._outputBuffer, j, i);
    } 
  }
  
  protected void _releaseBuffers() {
    char[] arrayOfChar = this._outputBuffer;
    if (arrayOfChar != null) {
      this._outputBuffer = null;
      this._ioContext.releaseConcatBuffer(arrayOfChar);
    } 
  }
  
  protected final void _verifyPrettyValueWrite(String paramString, int paramInt) {
    switch (paramInt) {
      default:
        _cantHappen();
        return;
      case 1:
        this._cfgPrettyPrinter.writeArrayValueSeparator(this);
        return;
      case 2:
        this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
        return;
      case 3:
        this._cfgPrettyPrinter.writeRootValueSeparator(this);
        return;
      case 0:
        break;
    } 
    if (this._writeContext.inArray()) {
      this._cfgPrettyPrinter.beforeArrayValues(this);
      return;
    } 
    if (this._writeContext.inObject())
      this._cfgPrettyPrinter.beforeObjectEntries(this); 
  }
  
  protected final void _verifyValueWrite(String paramString) {
    byte b;
    int i = this._writeContext.writeValue();
    if (i == 5)
      _reportError("Can not " + paramString + ", expecting field name"); 
    if (this._cfgPrettyPrinter == null) {
      switch (i) {
        default:
          return;
        case 1:
          b = 44;
          if (this._outputTail >= this._outputEnd)
            _flushBuffer(); 
          this._outputBuffer[this._outputTail] = b;
          this._outputTail++;
        case 2:
          b = 58;
          if (this._outputTail >= this._outputEnd)
            _flushBuffer(); 
          this._outputBuffer[this._outputTail] = b;
          this._outputTail++;
        case 3:
          break;
      } 
      b = 32;
    } else {
      _verifyPrettyValueWrite(paramString, i);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    this._outputBuffer[this._outputTail] = b;
    this._outputTail++;
  }
  
  protected void _writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int k = this._outputEnd - 6;
    int j = paramBase64Variant.getMaxLineLength() >> 2;
    int i = paramInt1;
    paramInt1 = j;
    while (i <= paramInt2 - 3) {
      if (this._outputTail > k)
        _flushBuffer(); 
      j = i + 1;
      i = paramArrayOfbyte[i];
      int m = j + 1;
      byte b = paramArrayOfbyte[j];
      j = m + 1;
      this._outputTail = paramBase64Variant.encodeBase64Chunk((b & 0xFF | i << 8) << 8 | paramArrayOfbyte[m] & 0xFF, this._outputBuffer, this._outputTail);
      m = paramInt1 - 1;
      paramInt1 = m;
      i = j;
      if (m <= 0) {
        char[] arrayOfChar = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = paramInt1 + 1;
        arrayOfChar[paramInt1] = '\\';
        arrayOfChar = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = paramInt1 + 1;
        arrayOfChar[paramInt1] = 'n';
        paramInt1 = paramBase64Variant.getMaxLineLength() >> 2;
        i = j;
      } 
    } 
    j = paramInt2 - i;
    if (j > 0) {
      if (this._outputTail > k)
        _flushBuffer(); 
      int m = i + 1;
      paramInt2 = paramArrayOfbyte[i] << 16;
      paramInt1 = paramInt2;
      if (j == 2)
        paramInt1 = paramInt2 | (paramArrayOfbyte[m] & 0xFF) << 8; 
      this._outputTail = paramBase64Variant.encodeBase64Partial(paramInt1, j, this._outputBuffer, this._outputTail);
    } 
  }
  
  public void _writeFieldName(SerializableString paramSerializableString, boolean paramBoolean) {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramSerializableString, paramBoolean);
      return;
    } 
    if (this._outputTail + 1 >= this._outputEnd)
      _flushBuffer(); 
    if (paramBoolean) {
      char[] arrayOfChar = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = j + 1;
      arrayOfChar[j] = ',';
    } 
    char[] arrayOfChar1 = paramSerializableString.asQuotedChars();
    if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      writeRaw(arrayOfChar1, 0, arrayOfChar1.length);
      return;
    } 
    char[] arrayOfChar2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar2[i] = '"';
    i = arrayOfChar1.length;
    if (this._outputTail + i + 1 >= this._outputEnd) {
      writeRaw(arrayOfChar1, 0, i);
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      arrayOfChar1 = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar1[i] = '"';
      return;
    } 
    System.arraycopy(arrayOfChar1, 0, this._outputBuffer, this._outputTail, i);
    this._outputTail += i;
    arrayOfChar1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar1[i] = '"';
  }
  
  protected void _writeFieldName(String paramString, boolean paramBoolean) {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramString, paramBoolean);
      return;
    } 
    if (this._outputTail + 1 >= this._outputEnd)
      _flushBuffer(); 
    if (paramBoolean) {
      char[] arrayOfChar = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = j + 1;
      arrayOfChar[j] = ',';
    } 
    if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      _writeString(paramString);
      return;
    } 
    char[] arrayOfChar2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar2[i] = '"';
    _writeString(paramString);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar1[i] = '"';
  }
  
  protected final void _writePPFieldName(SerializableString paramSerializableString, boolean paramBoolean) {
    if (paramBoolean) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries(this);
    } 
    char[] arrayOfChar = paramSerializableString.asQuotedChars();
    if (isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      char[] arrayOfChar1 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar1[i] = '"';
      writeRaw(arrayOfChar, 0, arrayOfChar.length);
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      arrayOfChar = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar[i] = '"';
      return;
    } 
    writeRaw(arrayOfChar, 0, arrayOfChar.length);
  }
  
  protected final void _writePPFieldName(String paramString, boolean paramBoolean) {
    char[] arrayOfChar;
    if (paramBoolean) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries(this);
    } 
    if (isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      char[] arrayOfChar1 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar1[i] = '"';
      _writeString(paramString);
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      arrayOfChar = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar[i] = '"';
      return;
    } 
    _writeString((String)arrayOfChar);
  }
  
  public void close() {
    super.close();
    if (this._outputBuffer != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))
      while (true) {
        JsonWriteContext jsonWriteContext = getOutputContext();
        if (jsonWriteContext.inArray()) {
          writeEndArray();
          continue;
        } 
        if (jsonWriteContext.inObject()) {
          writeEndObject();
          continue;
        } 
        break;
      }  
    _flushBuffer();
    if (this._writer != null)
      if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
        this._writer.close();
      } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
        this._writer.flush();
      }  
    _releaseBuffers();
  }
  
  public final void flush() {
    _flushBuffer();
    if (this._writer != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
      this._writer.flush(); 
  }
  
  public CharacterEscapes getCharacterEscapes() {
    return this._characterEscapes;
  }
  
  public int getHighestEscapedChar() {
    return this._maximumNonEscapedChar;
  }
  
  public Object getOutputTarget() {
    return this._writer;
  }
  
  public JsonGenerator setCharacterEscapes(CharacterEscapes paramCharacterEscapes) {
    this._characterEscapes = paramCharacterEscapes;
    if (paramCharacterEscapes == null) {
      this._outputEscapes = sOutputEscapes;
      return this;
    } 
    this._outputEscapes = paramCharacterEscapes.getEscapeCodesForAscii();
    return this;
  }
  
  public JsonGenerator setHighestNonEscapedChar(int paramInt) {
    int i = paramInt;
    if (paramInt < 0)
      i = 0; 
    this._maximumNonEscapedChar = i;
    return this;
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _verifyValueWrite("write binary value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar2[i] = '"';
    _writeBinary(paramBase64Variant, paramArrayOfbyte, paramInt1, paramInt1 + paramInt2);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar1 = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    arrayOfChar1[paramInt1] = '"';
  }
  
  public void writeBoolean(boolean paramBoolean) {
    _verifyValueWrite("write boolean value");
    if (this._outputTail + 5 >= this._outputEnd)
      _flushBuffer(); 
    int i = this._outputTail;
    char[] arrayOfChar = this._outputBuffer;
    if (paramBoolean) {
      arrayOfChar[i] = 't';
      arrayOfChar[++i] = 'r';
      arrayOfChar[++i] = 'u';
      arrayOfChar[++i] = 'e';
    } else {
      arrayOfChar[i] = 'f';
      arrayOfChar[++i] = 'a';
      arrayOfChar[++i] = 'l';
      arrayOfChar[++i] = 's';
      arrayOfChar[++i] = 'e';
    } 
    this._outputTail = i + 1;
  }
  
  public final void writeEndArray() {
    if (!this._writeContext.inArray())
      _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      char[] arrayOfChar = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfChar[i] = ']';
    } 
    this._writeContext = this._writeContext.getParent();
  }
  
  public final void writeEndObject() {
    if (!this._writeContext.inObject())
      _reportError("Current context not an object but " + this._writeContext.getTypeDesc()); 
    this._writeContext = this._writeContext.getParent();
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '}';
  }
  
  public final void writeFieldName(SerializableString paramSerializableString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (i != 1)
      bool = false; 
    _writeFieldName(paramSerializableString, bool);
  }
  
  public final void writeFieldName(SerializedString paramSerializedString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramSerializedString.getValue());
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (i != 1)
      bool = false; 
    _writeFieldName((SerializableString)paramSerializedString, bool);
  }
  
  public final void writeFieldName(String paramString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramString);
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (i != 1)
      bool = false; 
    _writeFieldName(paramString, bool);
  }
  
  public void writeNull() {
    _verifyValueWrite("write null value");
    _writeNull();
  }
  
  public void writeNumber(double paramDouble) {
    if (this._cfgNumbersAsStrings || ((Double.isNaN(paramDouble) || Double.isInfinite(paramDouble)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
      writeString(String.valueOf(paramDouble));
      return;
    } 
    _verifyValueWrite("write number");
    writeRaw(String.valueOf(paramDouble));
  }
  
  public void writeNumber(float paramFloat) {
    if (this._cfgNumbersAsStrings || ((Float.isNaN(paramFloat) || Float.isInfinite(paramFloat)) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
      writeString(String.valueOf(paramFloat));
      return;
    } 
    _verifyValueWrite("write number");
    writeRaw(String.valueOf(paramFloat));
  }
  
  public void writeNumber(int paramInt) {
    _verifyValueWrite("write number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(paramInt);
      return;
    } 
    if (this._outputTail + 11 >= this._outputEnd)
      _flushBuffer(); 
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
  }
  
  public void writeNumber(long paramLong) {
    _verifyValueWrite("write number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedLong(paramLong);
      return;
    } 
    if (this._outputTail + 21 >= this._outputEnd)
      _flushBuffer(); 
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
  }
  
  public void writeNumber(String paramString) {
    _verifyValueWrite("write number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(paramString);
      return;
    } 
    writeRaw(paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal) {
    _verifyValueWrite("write number");
    if (paramBigDecimal == null) {
      _writeNull();
      return;
    } 
    if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(paramBigDecimal);
      return;
    } 
    writeRaw(paramBigDecimal.toString());
  }
  
  public void writeNumber(BigInteger paramBigInteger) {
    _verifyValueWrite("write number");
    if (paramBigInteger == null) {
      _writeNull();
      return;
    } 
    if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(paramBigInteger);
      return;
    } 
    writeRaw(paramBigInteger.toString());
  }
  
  public void writeRaw(char paramChar) {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = paramChar;
  }
  
  public void writeRaw(String paramString) {
    int k = paramString.length();
    int j = this._outputEnd - this._outputTail;
    int i = j;
    if (j == 0) {
      _flushBuffer();
      i = this._outputEnd - this._outputTail;
    } 
    if (i >= k) {
      paramString.getChars(0, k, this._outputBuffer, this._outputTail);
      this._outputTail += k;
      return;
    } 
    writeRawLong(paramString);
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2) {
    int j = this._outputEnd - this._outputTail;
    int i = j;
    if (j < paramInt2) {
      _flushBuffer();
      i = this._outputEnd - this._outputTail;
    } 
    if (i >= paramInt2) {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, this._outputBuffer, this._outputTail);
      this._outputTail += paramInt2;
      return;
    } 
    writeRawLong(paramString.substring(paramInt1, paramInt1 + paramInt2));
  }
  
  public void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt2 < 32) {
      if (paramInt2 > this._outputEnd - this._outputTail)
        _flushBuffer(); 
      System.arraycopy(paramArrayOfchar, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
      this._outputTail += paramInt2;
      return;
    } 
    _flushBuffer();
    this._writer.write(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
  
  public final void writeStartArray() {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext();
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray(this);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '[';
  }
  
  public final void writeStartObject() {
    _verifyValueWrite("start an object");
    this._writeContext = this._writeContext.createChildObjectContext();
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject(this);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '{';
  }
  
  public final void writeString(SerializableString paramSerializableString) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar2[i] = '"';
    char[] arrayOfChar1 = paramSerializableString.asQuotedChars();
    i = arrayOfChar1.length;
    if (i < 32) {
      if (i > this._outputEnd - this._outputTail)
        _flushBuffer(); 
      System.arraycopy(arrayOfChar1, 0, this._outputBuffer, this._outputTail, i);
      this._outputTail += i;
    } else {
      _flushBuffer();
      this._writer.write(arrayOfChar1, 0, i);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    arrayOfChar1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar1[i] = '"';
  }
  
  public void writeString(String paramString) {
    _verifyValueWrite("write text value");
    if (paramString == null) {
      _writeNull();
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar2[i] = '"';
    _writeString(paramString);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar1[i] = '"';
  }
  
  public void writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfChar[i] = '"';
    _writeString(paramArrayOfchar, paramInt1, paramInt2);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    paramArrayOfchar = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    paramArrayOfchar[paramInt1] = '"';
  }
  
  public final void writeStringField(String paramString1, String paramString2) {
    writeFieldName(paramString1);
    writeString(paramString2);
  }
  
  public void writeUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _reportUnsupportedOperation();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\WriterBasedGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */