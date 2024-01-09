package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonGenerationException;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.io.CharacterEscapes;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Utf8Generator extends JsonGeneratorBase {
  private static final byte BYTE_0 = 48;
  
  private static final byte BYTE_BACKSLASH = 92;
  
  private static final byte BYTE_COLON = 58;
  
  private static final byte BYTE_COMMA = 44;
  
  private static final byte BYTE_LBRACKET = 91;
  
  private static final byte BYTE_LCURLY = 123;
  
  private static final byte BYTE_QUOTE = 34;
  
  private static final byte BYTE_RBRACKET = 93;
  
  private static final byte BYTE_RCURLY = 125;
  
  private static final byte BYTE_SPACE = 32;
  
  private static final byte BYTE_u = 117;
  
  private static final byte[] FALSE_BYTES;
  
  static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
  
  private static final int MAX_BYTES_TO_BUFFER = 512;
  
  private static final byte[] NULL_BYTES = new byte[] { 110, 117, 108, 108 };
  
  protected static final int SURR1_FIRST = 55296;
  
  protected static final int SURR1_LAST = 56319;
  
  protected static final int SURR2_FIRST = 56320;
  
  protected static final int SURR2_LAST = 57343;
  
  private static final byte[] TRUE_BYTES = new byte[] { 116, 114, 117, 101 };
  
  protected static final int[] sOutputEscapes;
  
  protected boolean _bufferRecyclable;
  
  protected char[] _charBuffer;
  
  protected final int _charBufferLength;
  
  protected CharacterEscapes _characterEscapes;
  
  protected byte[] _entityBuffer;
  
  protected final IOContext _ioContext;
  
  protected int _maximumNonEscapedChar;
  
  protected byte[] _outputBuffer;
  
  protected final int _outputEnd;
  
  protected int[] _outputEscapes = sOutputEscapes;
  
  protected final int _outputMaxContiguous;
  
  protected final OutputStream _outputStream;
  
  protected int _outputTail = 0;
  
  static {
    FALSE_BYTES = new byte[] { 102, 97, 108, 115, 101 };
    sOutputEscapes = CharTypes.get7BitOutputEscapes();
  }
  
  public Utf8Generator(IOContext paramIOContext, int paramInt, ObjectCodec paramObjectCodec, OutputStream paramOutputStream) {
    super(paramInt, paramObjectCodec);
    this._ioContext = paramIOContext;
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = true;
    this._outputBuffer = paramIOContext.allocWriteEncodingBuffer();
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = this._outputEnd >> 3;
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII))
      setHighestNonEscapedChar(127); 
  }
  
  public Utf8Generator(IOContext paramIOContext, int paramInt1, ObjectCodec paramObjectCodec, OutputStream paramOutputStream, byte[] paramArrayOfbyte, int paramInt2, boolean paramBoolean) {
    super(paramInt1, paramObjectCodec);
    this._ioContext = paramIOContext;
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = paramBoolean;
    this._outputTail = paramInt2;
    this._outputBuffer = paramArrayOfbyte;
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = this._outputEnd >> 3;
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII))
      setHighestNonEscapedChar(127); 
  }
  
  private int _handleLongCustomEscape(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3) {
    int i = paramArrayOfbyte2.length;
    if (paramInt1 + i > paramInt2) {
      this._outputTail = paramInt1;
      _flushBuffer();
      paramInt1 = this._outputTail;
      if (i > paramArrayOfbyte1.length) {
        this._outputStream.write(paramArrayOfbyte2, 0, i);
        return paramInt1;
      } 
      System.arraycopy(paramArrayOfbyte2, 0, paramArrayOfbyte1, paramInt1, i);
      i = paramInt1 + i;
    } else {
      i = paramInt1;
    } 
    paramInt1 = i;
    if (paramInt3 * 6 + i > paramInt2) {
      _flushBuffer();
      paramInt1 = this._outputTail;
    } 
    return paramInt1;
  }
  
  private final int _outputMultiByteChar(int paramInt1, int paramInt2) {
    byte[] arrayOfByte = this._outputBuffer;
    if (paramInt1 >= 55296 && paramInt1 <= 57343) {
      int k = paramInt2 + 1;
      arrayOfByte[paramInt2] = 92;
      paramInt2 = k + 1;
      arrayOfByte[k] = 117;
      k = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[paramInt1 >> 12 & 0xF];
      paramInt2 = k + 1;
      arrayOfByte[k] = HEX_CHARS[paramInt1 >> 8 & 0xF];
      k = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[paramInt1 >> 4 & 0xF];
      paramInt2 = k + 1;
      arrayOfByte[k] = HEX_CHARS[paramInt1 & 0xF];
      return paramInt2;
    } 
    int i = paramInt2 + 1;
    arrayOfByte[paramInt2] = (byte)(paramInt1 >> 12 | 0xE0);
    int j = i + 1;
    arrayOfByte[i] = (byte)(paramInt1 >> 6 & 0x3F | 0x80);
    paramInt2 = j + 1;
    arrayOfByte[j] = (byte)(paramInt1 & 0x3F | 0x80);
    return paramInt2;
  }
  
  private final int _outputRawMultiByteChar(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3) {
    if (paramInt1 >= 55296 && paramInt1 <= 57343) {
      if (paramInt2 >= paramInt3)
        _reportError("Split surrogate on writeRaw() input (last character)"); 
      _outputSurrogates(paramInt1, paramArrayOfchar[paramInt2]);
      return ++paramInt2;
    } 
    byte[] arrayOfByte = this._outputBuffer;
    paramInt3 = this._outputTail;
    this._outputTail = paramInt3 + 1;
    arrayOfByte[paramInt3] = (byte)(paramInt1 >> 12 | 0xE0);
    paramInt3 = this._outputTail;
    this._outputTail = paramInt3 + 1;
    arrayOfByte[paramInt3] = (byte)(paramInt1 >> 6 & 0x3F | 0x80);
    paramInt3 = this._outputTail;
    this._outputTail = paramInt3 + 1;
    arrayOfByte[paramInt3] = (byte)(paramInt1 & 0x3F | 0x80);
    return paramInt2;
  }
  
  private final void _writeBytes(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    if (this._outputTail + i > this._outputEnd) {
      _flushBuffer();
      if (i > 512) {
        this._outputStream.write(paramArrayOfbyte, 0, i);
        return;
      } 
    } 
    System.arraycopy(paramArrayOfbyte, 0, this._outputBuffer, this._outputTail, i);
    this._outputTail = i + this._outputTail;
  }
  
  private final void _writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this._outputTail + paramInt2 > this._outputEnd) {
      _flushBuffer();
      if (paramInt2 > 512) {
        this._outputStream.write(paramArrayOfbyte, paramInt1, paramInt2);
        return;
      } 
    } 
    System.arraycopy(paramArrayOfbyte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
    this._outputTail += paramInt2;
  }
  
  private int _writeCustomEscape(byte[] paramArrayOfbyte, int paramInt1, SerializableString paramSerializableString, int paramInt2) {
    byte[] arrayOfByte = paramSerializableString.asUnquotedUTF8();
    int i = arrayOfByte.length;
    if (i > 6)
      return _handleLongCustomEscape(paramArrayOfbyte, paramInt1, this._outputEnd, arrayOfByte, paramInt2); 
    System.arraycopy(arrayOfByte, 0, paramArrayOfbyte, paramInt1, i);
    return i + paramInt1;
  }
  
  private final void _writeCustomStringSegment2(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int k;
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd)
      _flushBuffer(); 
    int j = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    if (this._maximumNonEscapedChar <= 0) {
      k = 65535;
    } else {
      k = this._maximumNonEscapedChar;
    } 
    CharacterEscapes characterEscapes = this._characterEscapes;
    int i = paramInt1;
    paramInt1 = j;
    while (i < paramInt2) {
      j = i + 1;
      int m = paramArrayOfchar[i];
      if (m <= 127) {
        if (arrayOfInt[m] == 0) {
          arrayOfByte[paramInt1] = (byte)m;
          paramInt1++;
          i = j;
          continue;
        } 
        i = arrayOfInt[m];
        if (i > 0) {
          m = paramInt1 + 1;
          arrayOfByte[paramInt1] = 92;
          paramInt1 = m + 1;
          arrayOfByte[m] = (byte)i;
          i = j;
          continue;
        } 
        if (i == -2) {
          SerializableString serializableString1 = characterEscapes.getEscapeSequence(m);
          if (serializableString1 == null)
            throw new JsonGenerationException("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(m) + ", although was supposed to have one"); 
          paramInt1 = _writeCustomEscape(arrayOfByte, paramInt1, serializableString1, paramInt2 - j);
          i = j;
          continue;
        } 
        paramInt1 = _writeGenericEscape(m, paramInt1);
        i = j;
        continue;
      } 
      if (m > k) {
        paramInt1 = _writeGenericEscape(m, paramInt1);
        i = j;
        continue;
      } 
      SerializableString serializableString = characterEscapes.getEscapeSequence(m);
      if (serializableString != null) {
        paramInt1 = _writeCustomEscape(arrayOfByte, paramInt1, serializableString, paramInt2 - j);
        i = j;
        continue;
      } 
      if (m <= 2047) {
        i = paramInt1 + 1;
        arrayOfByte[paramInt1] = (byte)(m >> 6 | 0xC0);
        paramInt1 = i + 1;
        arrayOfByte[i] = (byte)(m & 0x3F | 0x80);
      } else {
        paramInt1 = _outputMultiByteChar(m, paramInt1);
      } 
      i = j;
    } 
    this._outputTail = paramInt1;
  }
  
  private int _writeGenericEscape(int paramInt1, int paramInt2) {
    byte[] arrayOfByte = this._outputBuffer;
    int i = paramInt2 + 1;
    arrayOfByte[paramInt2] = 92;
    paramInt2 = i + 1;
    arrayOfByte[i] = 117;
    if (paramInt1 > 255) {
      int j = paramInt1 >> 8 & 0xFF;
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[j >> 4];
      paramInt2 = i + 1;
      arrayOfByte[i] = HEX_CHARS[j & 0xF];
      paramInt1 &= 0xFF;
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[paramInt1 >> 4];
      arrayOfByte[i] = HEX_CHARS[paramInt1 & 0xF];
      return i + 1;
    } 
    i = paramInt2 + 1;
    arrayOfByte[paramInt2] = 48;
    paramInt2 = i + 1;
    arrayOfByte[i] = 48;
    i = paramInt2 + 1;
    arrayOfByte[paramInt2] = HEX_CHARS[paramInt1 >> 4];
    arrayOfByte[i] = HEX_CHARS[paramInt1 & 0xF];
    return i + 1;
  }
  
  private final void _writeLongString(String paramString) {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    _writeStringSegments(paramString);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte1[i] = 34;
  }
  
  private final void _writeLongString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    arrayOfByte[paramInt1] = 34;
    _writeStringSegments(this._charBuffer, 0, paramInt2);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    arrayOfByte = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    arrayOfByte[paramInt1] = 34;
  }
  
  private final void _writeNull() {
    if (this._outputTail + 4 >= this._outputEnd)
      _flushBuffer(); 
    System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
    this._outputTail += 4;
  }
  
  private final void _writeQuotedInt(int paramInt) {
    if (this._outputTail + 13 >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    arrayOfByte = this._outputBuffer;
    paramInt = this._outputTail;
    this._outputTail = paramInt + 1;
    arrayOfByte[paramInt] = 34;
  }
  
  private final void _writeQuotedLong(long paramLong) {
    if (this._outputTail + 23 >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    arrayOfByte = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
  }
  
  private final void _writeQuotedRaw(Object paramObject) {
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    writeRaw(paramObject.toString());
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    paramObject = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    paramObject[i] = 34;
  }
  
  private final void _writeSegmentedRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = this._outputEnd;
    byte[] arrayOfByte = this._outputBuffer;
    label22: while (true) {
      if (paramInt1 < paramInt2) {
        while (true) {
          int j = paramArrayOfchar[paramInt1];
          if (j >= 128) {
            if (this._outputTail + 3 >= this._outputEnd)
              _flushBuffer(); 
            j = paramInt1 + 1;
            paramInt1 = paramArrayOfchar[paramInt1];
            if (paramInt1 < 2048) {
              int m = this._outputTail;
              this._outputTail = m + 1;
              arrayOfByte[m] = (byte)(paramInt1 >> 6 | 0xC0);
              m = this._outputTail;
              this._outputTail = m + 1;
              arrayOfByte[m] = (byte)(paramInt1 & 0x3F | 0x80);
            } else {
              _outputRawMultiByteChar(paramInt1, paramArrayOfchar, j, paramInt2);
            } 
            paramInt1 = j;
            continue label22;
          } 
          if (this._outputTail >= i)
            _flushBuffer(); 
          int k = this._outputTail;
          this._outputTail = k + 1;
          arrayOfByte[k] = (byte)j;
          j = paramInt1 + 1;
          paramInt1 = j;
          if (j >= paramInt2)
            continue; 
        } 
        break;
      } 
      return;
    } 
  }
  
  private final void _writeStringSegment(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = paramInt2 + paramInt1;
    paramInt2 = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    while (true) {
      if (paramInt1 < i) {
        char c = paramArrayOfchar[paramInt1];
        if (c <= '' && arrayOfInt[c] == 0) {
          arrayOfByte[paramInt2] = (byte)c;
          paramInt1++;
          paramInt2++;
          continue;
        } 
      } 
      this._outputTail = paramInt2;
      if (paramInt1 < i) {
        if (this._characterEscapes != null) {
          _writeCustomStringSegment2(paramArrayOfchar, paramInt1, i);
          return;
        } 
      } else {
        return;
      } 
      if (this._maximumNonEscapedChar == 0) {
        _writeStringSegment2(paramArrayOfchar, paramInt1, i);
        return;
      } 
      _writeStringSegmentASCII2(paramArrayOfchar, paramInt1, i);
      return;
    } 
  }
  
  private final void _writeStringSegment2(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd)
      _flushBuffer(); 
    int j = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int i = paramInt1;
    paramInt1 = j;
    while (i < paramInt2) {
      j = i + 1;
      i = paramArrayOfchar[i];
      if (i <= 127) {
        if (arrayOfInt[i] == 0) {
          arrayOfByte[paramInt1] = (byte)i;
          paramInt1++;
          i = j;
          continue;
        } 
        int k = arrayOfInt[i];
        if (k > 0) {
          i = paramInt1 + 1;
          arrayOfByte[paramInt1] = 92;
          paramInt1 = i + 1;
          arrayOfByte[i] = (byte)k;
          i = j;
          continue;
        } 
        paramInt1 = _writeGenericEscape(i, paramInt1);
        i = j;
        continue;
      } 
      if (i <= 2047) {
        int k = paramInt1 + 1;
        arrayOfByte[paramInt1] = (byte)(i >> 6 | 0xC0);
        paramInt1 = k + 1;
        arrayOfByte[k] = (byte)(i & 0x3F | 0x80);
      } else {
        paramInt1 = _outputMultiByteChar(i, paramInt1);
      } 
      i = j;
    } 
    this._outputTail = paramInt1;
  }
  
  private final void _writeStringSegmentASCII2(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd)
      _flushBuffer(); 
    int j = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k = this._maximumNonEscapedChar;
    int i = paramInt1;
    paramInt1 = j;
    while (i < paramInt2) {
      j = i + 1;
      i = paramArrayOfchar[i];
      if (i <= 127) {
        if (arrayOfInt[i] == 0) {
          arrayOfByte[paramInt1] = (byte)i;
          paramInt1++;
          i = j;
          continue;
        } 
        int m = arrayOfInt[i];
        if (m > 0) {
          i = paramInt1 + 1;
          arrayOfByte[paramInt1] = 92;
          paramInt1 = i + 1;
          arrayOfByte[i] = (byte)m;
          i = j;
          continue;
        } 
        paramInt1 = _writeGenericEscape(i, paramInt1);
        i = j;
        continue;
      } 
      if (i > k) {
        paramInt1 = _writeGenericEscape(i, paramInt1);
        i = j;
        continue;
      } 
      if (i <= 2047) {
        int m = paramInt1 + 1;
        arrayOfByte[paramInt1] = (byte)(i >> 6 | 0xC0);
        paramInt1 = m + 1;
        arrayOfByte[m] = (byte)(i & 0x3F | 0x80);
      } else {
        paramInt1 = _outputMultiByteChar(i, paramInt1);
      } 
      i = j;
    } 
    this._outputTail = paramInt1;
  }
  
  private final void _writeStringSegments(String paramString) {
    int i = paramString.length();
    char[] arrayOfChar = this._charBuffer;
    int j = 0;
    while (i > 0) {
      int k = Math.min(this._outputMaxContiguous, i);
      paramString.getChars(j, j + k, arrayOfChar, 0);
      if (this._outputTail + k > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(arrayOfChar, 0, k);
      j += k;
      i -= k;
    } 
  }
  
  private final void _writeStringSegments(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i;
    do {
      i = Math.min(this._outputMaxContiguous, paramInt2);
      if (this._outputTail + i > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(paramArrayOfchar, paramInt1, i);
      paramInt1 += i;
      i = paramInt2 - i;
      paramInt2 = i;
    } while (i > 0);
  }
  
  private final void _writeUTF8Segment(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int[] arrayOfInt = this._outputEscapes;
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++) {
      byte b = paramArrayOfbyte[i];
      if (b >= 0 && arrayOfInt[b] != 0) {
        _writeUTF8Segment2(paramArrayOfbyte, paramInt1, paramInt2);
        return;
      } 
    } 
    if (this._outputTail + paramInt2 > this._outputEnd)
      _flushBuffer(); 
    System.arraycopy(paramArrayOfbyte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
    this._outputTail += paramInt2;
  }
  
  private final void _writeUTF8Segment2(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = this._outputTail;
    int j = i;
    if (paramInt2 * 6 + i > this._outputEnd) {
      _flushBuffer();
      j = this._outputTail;
    } 
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    i = paramInt1;
    while (true) {
      int k = i;
      if (k < paramInt2 + paramInt1) {
        i = k + 1;
        byte b = paramArrayOfbyte[k];
        if (b < 0 || arrayOfInt[b] == 0) {
          arrayOfByte[j] = b;
          j++;
          continue;
        } 
        k = arrayOfInt[b];
        if (k > 0) {
          int m = j + 1;
          arrayOfByte[j] = 92;
          j = m + 1;
          arrayOfByte[m] = (byte)k;
          continue;
        } 
        j = _writeGenericEscape(b, j);
        continue;
      } 
      this._outputTail = j;
      return;
    } 
  }
  
  private final void _writeUTF8Segments(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i;
    do {
      i = Math.min(this._outputMaxContiguous, paramInt2);
      _writeUTF8Segment(paramArrayOfbyte, paramInt1, i);
      paramInt1 += i;
      i = paramInt2 - i;
      paramInt2 = i;
    } while (i > 0);
  }
  
  protected final int _decodeSurrogate(int paramInt1, int paramInt2) {
    if (paramInt2 < 56320 || paramInt2 > 57343)
      _reportError("Incomplete surrogate pair: first char 0x" + Integer.toHexString(paramInt1) + ", second 0x" + Integer.toHexString(paramInt2)); 
    return 65536 + (paramInt1 - 55296 << 10) + paramInt2 - 56320;
  }
  
  protected final void _flushBuffer() {
    int i = this._outputTail;
    if (i > 0) {
      this._outputTail = 0;
      this._outputStream.write(this._outputBuffer, 0, i);
    } 
  }
  
  protected final void _outputSurrogates(int paramInt1, int paramInt2) {
    paramInt1 = _decodeSurrogate(paramInt1, paramInt2);
    if (this._outputTail + 4 > this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    paramInt2 = this._outputTail;
    this._outputTail = paramInt2 + 1;
    arrayOfByte[paramInt2] = (byte)(paramInt1 >> 18 | 0xF0);
    paramInt2 = this._outputTail;
    this._outputTail = paramInt2 + 1;
    arrayOfByte[paramInt2] = (byte)(paramInt1 >> 12 & 0x3F | 0x80);
    paramInt2 = this._outputTail;
    this._outputTail = paramInt2 + 1;
    arrayOfByte[paramInt2] = (byte)(paramInt1 >> 6 & 0x3F | 0x80);
    paramInt2 = this._outputTail;
    this._outputTail = paramInt2 + 1;
    arrayOfByte[paramInt2] = (byte)(paramInt1 & 0x3F | 0x80);
  }
  
  protected void _releaseBuffers() {
    byte[] arrayOfByte = this._outputBuffer;
    if (arrayOfByte != null && this._bufferRecyclable) {
      this._outputBuffer = null;
      this._ioContext.releaseWriteEncodingBuffer(arrayOfByte);
    } 
    char[] arrayOfChar = this._charBuffer;
    if (arrayOfChar != null) {
      this._charBuffer = null;
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
      byte b1 = paramArrayOfbyte[i];
      i = j + 1;
      byte b2 = paramArrayOfbyte[j];
      j = i + 1;
      this._outputTail = paramBase64Variant.encodeBase64Chunk((b2 & 0xFF | b1 << 8) << 8 | paramArrayOfbyte[i] & 0xFF, this._outputBuffer, this._outputTail);
      int m = paramInt1 - 1;
      paramInt1 = m;
      i = j;
      if (m <= 0) {
        byte[] arrayOfByte = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = paramInt1 + 1;
        arrayOfByte[paramInt1] = 92;
        arrayOfByte = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = paramInt1 + 1;
        arrayOfByte[paramInt1] = 110;
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
  
  protected final void _writeFieldName(SerializableString paramSerializableString) {
    byte[] arrayOfByte1 = paramSerializableString.asQuotedUTF8();
    if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      _writeBytes(arrayOfByte1);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    i = arrayOfByte1.length;
    if (this._outputTail + i + 1 < this._outputEnd) {
      System.arraycopy(arrayOfByte1, 0, this._outputBuffer, this._outputTail, i);
      this._outputTail += i;
      arrayOfByte1 = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte1[i] = 34;
      return;
    } 
    _writeBytes(arrayOfByte1);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    arrayOfByte1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte1[i] = 34;
  }
  
  protected final void _writeFieldName(String paramString) {
    if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      _writeStringSegments(paramString);
      return;
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    i = paramString.length();
    if (i <= this._charBufferLength) {
      paramString.getChars(0, i, this._charBuffer, 0);
      if (i <= this._outputMaxContiguous) {
        if (this._outputTail + i > this._outputEnd)
          _flushBuffer(); 
        _writeStringSegment(this._charBuffer, 0, i);
      } else {
        _writeStringSegments(this._charBuffer, 0, i);
      } 
    } else {
      _writeStringSegments(paramString);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte1[i] = 34;
  }
  
  protected final void _writePPFieldName(SerializableString paramSerializableString, boolean paramBoolean) {
    if (paramBoolean) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries(this);
    } 
    paramBoolean = isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES);
    if (paramBoolean) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 34;
    } 
    _writeBytes(paramSerializableString.asQuotedUTF8());
    if (paramBoolean) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 34;
    } 
  }
  
  protected final void _writePPFieldName(String paramString, boolean paramBoolean) {
    byte[] arrayOfByte;
    if (paramBoolean) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
    } else {
      this._cfgPrettyPrinter.beforeObjectEntries(this);
    } 
    if (isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte1 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte1[i] = 34;
      i = paramString.length();
      if (i <= this._charBufferLength) {
        paramString.getChars(0, i, this._charBuffer, 0);
        if (i <= this._outputMaxContiguous) {
          if (this._outputTail + i > this._outputEnd)
            _flushBuffer(); 
          _writeStringSegment(this._charBuffer, 0, i);
        } else {
          _writeStringSegments(this._charBuffer, 0, i);
        } 
      } else {
        _writeStringSegments(paramString);
      } 
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 34;
      return;
    } 
    _writeStringSegments((String)arrayOfByte);
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
    if (this._outputStream != null)
      if (this._ioContext.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
        this._outputStream.close();
      } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
        this._outputStream.flush();
      }  
    _releaseBuffers();
  }
  
  public final void flush() {
    _flushBuffer();
    if (this._outputStream != null && isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))
      this._outputStream.flush(); 
  }
  
  public CharacterEscapes getCharacterEscapes() {
    return this._characterEscapes;
  }
  
  public int getHighestEscapedChar() {
    return this._maximumNonEscapedChar;
  }
  
  public Object getOutputTarget() {
    return this._outputStream;
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
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    _writeBinary(paramBase64Variant, paramArrayOfbyte, paramInt1, paramInt1 + paramInt2);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte1 = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    arrayOfByte1[paramInt1] = 34;
  }
  
  public void writeBoolean(boolean paramBoolean) {
    byte[] arrayOfByte;
    _verifyValueWrite("write boolean value");
    if (this._outputTail + 5 >= this._outputEnd)
      _flushBuffer(); 
    if (paramBoolean) {
      arrayOfByte = TRUE_BYTES;
    } else {
      arrayOfByte = FALSE_BYTES;
    } 
    int i = arrayOfByte.length;
    System.arraycopy(arrayOfByte, 0, this._outputBuffer, this._outputTail, i);
    this._outputTail += i;
  }
  
  public final void writeEndArray() {
    if (!this._writeContext.inArray())
      _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc()); 
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
    } else {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 93;
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
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 125;
  }
  
  public final void writeFieldName(SerializableString paramSerializableString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (this._cfgPrettyPrinter != null) {
      if (i != 1)
        bool = false; 
      _writePPFieldName(paramSerializableString, bool);
      return;
    } 
    if (i == 1) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 44;
    } 
    _writeFieldName(paramSerializableString);
  }
  
  public final void writeFieldName(SerializedString paramSerializedString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramSerializedString.getValue());
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (this._cfgPrettyPrinter != null) {
      if (i != 1)
        bool = false; 
      _writePPFieldName((SerializableString)paramSerializedString, bool);
      return;
    } 
    if (i == 1) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 44;
    } 
    _writeFieldName((SerializableString)paramSerializedString);
  }
  
  public final void writeFieldName(String paramString) {
    boolean bool = true;
    int i = this._writeContext.writeFieldName(paramString);
    if (i == 4)
      _reportError("Can not write a field name, expecting a value"); 
    if (this._cfgPrettyPrinter != null) {
      if (i != 1)
        bool = false; 
      _writePPFieldName(paramString, bool);
      return;
    } 
    if (i == 1) {
      if (this._outputTail >= this._outputEnd)
        _flushBuffer(); 
      byte[] arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = 44;
    } 
    _writeFieldName(paramString);
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
    if (this._outputTail + 11 >= this._outputEnd)
      _flushBuffer(); 
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(paramInt);
      return;
    } 
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
    if (this._outputTail + 3 >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    if (paramChar <= '') {
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = (byte)paramChar;
      return;
    } 
    if (paramChar < 'ࠀ') {
      int i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = (byte)(paramChar >> 6 | 0xC0);
      i = this._outputTail;
      this._outputTail = i + 1;
      arrayOfByte[i] = (byte)(paramChar & 0x3F | 0x80);
      return;
    } 
    _outputRawMultiByteChar(paramChar, (char[])null, 0, 0);
  }
  
  public void writeRaw(String paramString) {
    int i = paramString.length();
    int j = 0;
    while (i > 0) {
      char[] arrayOfChar = this._charBuffer;
      int m = arrayOfChar.length;
      int k = m;
      if (i < m)
        k = i; 
      paramString.getChars(j, j + k, arrayOfChar, 0);
      writeRaw(arrayOfChar, 0, k);
      j += k;
      i -= k;
    } 
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2) {
    int i = paramInt2;
    paramInt2 = paramInt1;
    for (paramInt1 = i; paramInt1 > 0; paramInt1 -= i) {
      char[] arrayOfChar = this._charBuffer;
      int j = arrayOfChar.length;
      i = j;
      if (paramInt1 < j)
        i = paramInt1; 
      paramString.getChars(paramInt2, paramInt2 + i, arrayOfChar, 0);
      writeRaw(arrayOfChar, 0, i);
      paramInt2 += i;
    } 
  }
  
  public final void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = paramInt2 + paramInt2 + paramInt2;
    if (this._outputTail + i > this._outputEnd) {
      if (this._outputEnd < i) {
        _writeSegmentedRaw(paramArrayOfchar, paramInt1, paramInt2);
        return;
      } 
      _flushBuffer();
    } 
    i = paramInt2 + paramInt1;
    label23: while (true) {
      if (paramInt1 < i) {
        while (true) {
          paramInt2 = paramArrayOfchar[paramInt1];
          if (paramInt2 > 127) {
            paramInt2 = paramInt1 + 1;
            paramInt1 = paramArrayOfchar[paramInt1];
            if (paramInt1 < 2048) {
              byte[] arrayOfByte1 = this._outputBuffer;
              int k = this._outputTail;
              this._outputTail = k + 1;
              arrayOfByte1[k] = (byte)(paramInt1 >> 6 | 0xC0);
              arrayOfByte1 = this._outputBuffer;
              k = this._outputTail;
              this._outputTail = k + 1;
              arrayOfByte1[k] = (byte)(paramInt1 & 0x3F | 0x80);
            } else {
              _outputRawMultiByteChar(paramInt1, paramArrayOfchar, paramInt2, i);
            } 
            paramInt1 = paramInt2;
            continue label23;
          } 
          byte[] arrayOfByte = this._outputBuffer;
          int j = this._outputTail;
          this._outputTail = j + 1;
          arrayOfByte[j] = (byte)paramInt2;
          paramInt2 = paramInt1 + 1;
          paramInt1 = paramInt2;
          if (paramInt2 >= i)
            return; 
        } 
        break;
      } 
      // Byte code: goto -> 37
    } 
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    _writeBytes(paramArrayOfbyte, paramInt1, paramInt2);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    paramArrayOfbyte = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    paramArrayOfbyte[paramInt1] = 34;
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
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 91;
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
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 123;
  }
  
  public final void writeString(SerializableString paramSerializableString) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    _writeBytes(paramSerializableString.asQuotedUTF8());
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte1 = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte1[i] = 34;
  }
  
  public void writeString(String paramString) {
    _verifyValueWrite("write text value");
    if (paramString == null) {
      _writeNull();
      return;
    } 
    int j = paramString.length();
    if (j > this._charBufferLength) {
      _writeLongString(paramString);
      return;
    } 
    paramString.getChars(0, j, this._charBuffer, 0);
    if (j > this._outputMaxContiguous) {
      _writeLongString(this._charBuffer, 0, j);
      return;
    } 
    if (this._outputTail + j >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    _writeStringSegment(this._charBuffer, 0, j);
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    arrayOfByte = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
  }
  
  public void writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte2 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte2[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous) {
      if (this._outputTail + paramInt2 > this._outputEnd)
        _flushBuffer(); 
      _writeStringSegment(paramArrayOfchar, paramInt1, paramInt2);
    } else {
      _writeStringSegments(paramArrayOfchar, paramInt1, paramInt2);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte1 = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    arrayOfByte1[paramInt1] = 34;
  }
  
  public final void writeStringField(String paramString1, String paramString2) {
    writeFieldName(paramString1);
    writeString(paramString2);
  }
  
  public void writeUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    _verifyValueWrite("write text value");
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = i + 1;
    arrayOfByte[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous) {
      _writeUTF8Segment(paramArrayOfbyte, paramInt1, paramInt2);
    } else {
      _writeUTF8Segments(paramArrayOfbyte, paramInt1, paramInt2);
    } 
    if (this._outputTail >= this._outputEnd)
      _flushBuffer(); 
    paramArrayOfbyte = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = paramInt1 + 1;
    paramArrayOfbyte[paramInt1] = 34;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\Utf8Generator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */