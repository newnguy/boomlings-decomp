package com.flurry.org.codehaus.jackson.io;

import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.CharTypes;
import com.flurry.org.codehaus.jackson.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
  private static final byte[] HEX_BYTES;
  
  private static final char[] HEX_CHARS = CharTypes.copyHexChars();
  
  private static final int INT_0 = 48;
  
  private static final int INT_BACKSLASH = 92;
  
  private static final int INT_U = 117;
  
  private static final int SURR1_FIRST = 55296;
  
  private static final int SURR1_LAST = 56319;
  
  private static final int SURR2_FIRST = 56320;
  
  private static final int SURR2_LAST = 57343;
  
  protected static final ThreadLocal _threadEncoder;
  
  protected ByteArrayBuilder _byteBuilder;
  
  protected final char[] _quoteBuffer = new char[6];
  
  protected TextBuffer _textBuffer;
  
  static {
    HEX_BYTES = CharTypes.copyHexBytes();
    _threadEncoder = new ThreadLocal();
  }
  
  public JsonStringEncoder() {
    this._quoteBuffer[0] = '\\';
    this._quoteBuffer[2] = '0';
    this._quoteBuffer[3] = '0';
  }
  
  private int _appendByteEscape(int paramInt1, int paramInt2, ByteArrayBuilder paramByteArrayBuilder, int paramInt3) {
    paramByteArrayBuilder.setCurrentSegmentLength(paramInt3);
    paramByteArrayBuilder.append(92);
    if (paramInt2 < 0) {
      paramByteArrayBuilder.append(117);
      if (paramInt1 > 255) {
        paramInt2 = paramInt1 >> 8;
        paramByteArrayBuilder.append(HEX_BYTES[paramInt2 >> 4]);
        paramByteArrayBuilder.append(HEX_BYTES[paramInt2 & 0xF]);
        paramInt1 &= 0xFF;
      } else {
        paramByteArrayBuilder.append(48);
        paramByteArrayBuilder.append(48);
      } 
      paramByteArrayBuilder.append(HEX_BYTES[paramInt1 >> 4]);
      paramByteArrayBuilder.append(HEX_BYTES[paramInt1 & 0xF]);
      return paramByteArrayBuilder.getCurrentSegmentLength();
    } 
    paramByteArrayBuilder.append((byte)paramInt2);
    return paramByteArrayBuilder.getCurrentSegmentLength();
  }
  
  private int _appendSingleEscape(int paramInt, char[] paramArrayOfchar) {
    if (paramInt < 0) {
      paramInt = -(paramInt + 1);
      paramArrayOfchar[1] = 'u';
      paramArrayOfchar[4] = HEX_CHARS[paramInt >> 4];
      paramArrayOfchar[5] = HEX_CHARS[paramInt & 0xF];
      return 6;
    } 
    paramArrayOfchar[1] = (char)paramInt;
    return 2;
  }
  
  private int _convertSurrogate(int paramInt1, int paramInt2) {
    if (paramInt2 < 56320 || paramInt2 > 57343)
      throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(paramInt1) + ", second 0x" + Integer.toHexString(paramInt2) + "; illegal combination"); 
    return 65536 + (paramInt1 - 55296 << 10) + paramInt2 - 56320;
  }
  
  private void _throwIllegalSurrogate(int paramInt) {
    if (paramInt > 1114111)
      throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output; max is 0x10FFFF as per RFC 4627"); 
    if (paramInt >= 55296) {
      if (paramInt <= 56319)
        throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")"); 
      throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")");
    } 
    throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output");
  }
  
  public static JsonStringEncoder getInstance() {
    JsonStringEncoder jsonStringEncoder1;
    SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
    if (softReference == null) {
      softReference = null;
    } else {
      jsonStringEncoder1 = softReference.get();
    } 
    JsonStringEncoder jsonStringEncoder2 = jsonStringEncoder1;
    if (jsonStringEncoder1 == null) {
      jsonStringEncoder2 = new JsonStringEncoder();
      _threadEncoder.set(new SoftReference<JsonStringEncoder>(jsonStringEncoder2));
    } 
    return jsonStringEncoder2;
  }
  
  public byte[] encodeAsUTF8(String paramString) {
    ByteArrayBuilder byteArrayBuilder1 = this._byteBuilder;
    ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder1;
    if (byteArrayBuilder1 == null) {
      byteArrayBuilder2 = new ByteArrayBuilder(null);
      this._byteBuilder = byteArrayBuilder2;
    } 
    int m = paramString.length();
    byte[] arrayOfByte = byteArrayBuilder2.resetAndGetFirstSegment();
    int k = arrayOfByte.length;
    int i = 0;
    int j = 0;
    while (true) {
      if (j < m) {
        int i2 = j + 1;
        char c = paramString.charAt(j);
        int n = k;
        j = i2;
        k = c;
        while (k <= 127) {
          int i3 = n;
          i2 = i;
          if (i >= n) {
            arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
            i3 = arrayOfByte.length;
            i2 = 0;
          } 
          i = i2 + 1;
          arrayOfByte[i2] = (byte)k;
          if (j >= m)
            return this._byteBuilder.completeAndCoalesce(i); 
          k = paramString.charAt(j);
          j++;
          n = i3;
        } 
        if (i >= n) {
          arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
          i = arrayOfByte.length;
          n = 0;
        } else {
          int i3 = i;
          i = n;
          n = i3;
        } 
        if (k < 2048) {
          int i3 = n + 1;
          arrayOfByte[n] = (byte)(k >> 6 | 0xC0);
          n = k;
          k = i3;
        } else if (k < 55296 || k > 57343) {
          i2 = n + 1;
          arrayOfByte[n] = (byte)(k >> 12 | 0xE0);
          n = i;
          int i3 = i2;
          if (i2 >= i) {
            arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
            n = arrayOfByte.length;
            i3 = 0;
          } 
          arrayOfByte[i3] = (byte)(k >> 6 & 0x3F | 0x80);
          i2 = i3 + 1;
          i3 = k;
          i = n;
          k = i2;
          n = i3;
        } else {
          if (k > 56319)
            _throwIllegalSurrogate(k); 
          if (j >= m)
            _throwIllegalSurrogate(k); 
          int i3 = _convertSurrogate(k, paramString.charAt(j));
          if (i3 > 1114111)
            _throwIllegalSurrogate(i3); 
          i2 = n + 1;
          arrayOfByte[n] = (byte)(i3 >> 18 | 0xF0);
          k = i;
          n = i2;
          if (i2 >= i) {
            arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
            k = arrayOfByte.length;
            n = 0;
          } 
          i = n + 1;
          arrayOfByte[n] = (byte)(i3 >> 12 & 0x3F | 0x80);
          if (i >= k) {
            arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
            i = arrayOfByte.length;
            k = 0;
          } else {
            n = i;
            i = k;
            k = n;
          } 
          arrayOfByte[k] = (byte)(i3 >> 6 & 0x3F | 0x80);
          k++;
          n = i3;
          j++;
        } 
        int i1 = i;
        i2 = k;
        if (k >= i) {
          arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
          i1 = arrayOfByte.length;
          i2 = 0;
        } 
        arrayOfByte[i2] = (byte)(n & 0x3F | 0x80);
        k = i1;
        i = i2 + 1;
        continue;
      } 
      continue;
    } 
  }
  
  public char[] quoteAsString(String paramString) {
    TextBuffer textBuffer1 = this._textBuffer;
    TextBuffer textBuffer2 = textBuffer1;
    if (textBuffer1 == null) {
      textBuffer2 = new TextBuffer(null);
      this._textBuffer = textBuffer2;
    } 
    char[] arrayOfChar = textBuffer2.emptyAndGetCurrentSegment();
    int[] arrayOfInt = CharTypes.get7BitOutputEscapes();
    int k = arrayOfInt.length;
    int m = paramString.length();
    int i = 0;
    int j = 0;
    label29: while (true) {
      int n = i;
      if (j < m) {
        while (true) {
          char c = paramString.charAt(j);
          if (c < k && arrayOfInt[c] != 0) {
            int i2 = _appendSingleEscape(arrayOfInt[paramString.charAt(j)], this._quoteBuffer);
            if (i + i2 > arrayOfChar.length) {
              n = arrayOfChar.length - i;
              if (n > 0)
                System.arraycopy(this._quoteBuffer, 0, arrayOfChar, i, n); 
              arrayOfChar = textBuffer2.finishCurrentSegment();
              i2 -= n;
              System.arraycopy(this._quoteBuffer, n, arrayOfChar, i, i2);
              i += i2;
            } else {
              System.arraycopy(this._quoteBuffer, 0, arrayOfChar, i, i2);
              i += i2;
            } 
            j++;
            continue label29;
          } 
          if (i >= arrayOfChar.length) {
            arrayOfChar = textBuffer2.finishCurrentSegment();
            i = 0;
          } 
          n = i + 1;
          arrayOfChar[i] = c;
          int i1 = j + 1;
          i = n;
          j = i1;
          if (i1 >= m)
            continue; 
        } 
        break;
      } 
      textBuffer2.setCurrentLength(n);
      return textBuffer2.contentsAsArray();
    } 
  }
  
  public byte[] quoteAsUTF8(String paramString) {
    ByteArrayBuilder byteArrayBuilder1 = this._byteBuilder;
    ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder1;
    if (byteArrayBuilder1 == null) {
      byteArrayBuilder2 = new ByteArrayBuilder(null);
      this._byteBuilder = byteArrayBuilder2;
    } 
    int j = paramString.length();
    byte[] arrayOfByte = byteArrayBuilder2.resetAndGetFirstSegment();
    int i = 0;
    char c = Character.MIN_VALUE;
    label60: while (true) {
      int n;
      int i2;
      byte[] arrayOfByte1;
      int i1 = i;
      if (c < j) {
        int[] arrayOfInt = CharTypes.get7BitOutputEscapes();
        arrayOfByte1 = arrayOfByte;
        while (true) {
          int i3;
          int i4;
          int i5 = paramString.charAt(c);
          if (i5 > 127 || arrayOfInt[i5] != 0) {
            arrayOfByte = arrayOfByte1;
            i5 = i;
            if (i >= arrayOfByte1.length) {
              arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
              i5 = 0;
            } 
            i1 = c + 1;
            c = paramString.charAt(c);
            if (c <= '') {
              i = _appendByteEscape(c, arrayOfInt[c], byteArrayBuilder2, i5);
              arrayOfByte = byteArrayBuilder2.getCurrentSegment();
              i4 = i1;
              continue label60;
            } 
          } else {
            if (i >= arrayOfByte1.length) {
              arrayOfByte1 = byteArrayBuilder2.finishCurrentSegment();
              i3 = 0;
            } 
            n = i3 + 1;
            arrayOfByte1[i3] = (byte)i5;
            i2 = i4 + 1;
            i3 = n;
            m = i2;
            if (i2 >= j)
              continue; 
            continue;
          } 
          if (m <= 2047) {
            i3 = i2 + 1;
            arrayOfByte[i2] = (byte)(m >> 6 | 0xC0);
            m = m & 0x3F | 0x80;
          } else if (m < 55296 || m > 57343) {
            i3 = i2 + 1;
            arrayOfByte[i2] = (byte)(m >> 12 | 0xE0);
            if (i3 >= arrayOfByte.length) {
              arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
              i3 = 0;
            } 
            i2 = i3 + 1;
            arrayOfByte[i3] = (byte)(m >> 6 & 0x3F | 0x80);
            m = m & 0x3F | 0x80;
            i3 = i2;
          } else {
            if (m > 56319)
              _throwIllegalSurrogate(m); 
            if (n >= j)
              _throwIllegalSurrogate(m); 
            int i6 = _convertSurrogate(m, paramString.charAt(n));
            if (i6 > 1114111)
              _throwIllegalSurrogate(i6); 
            i3 = i2 + 1;
            arrayOfByte[i2] = (byte)(i6 >> 18 | 0xF0);
            if (i3 >= arrayOfByte.length) {
              arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
              i3 = 0;
            } 
            m = i3 + 1;
            arrayOfByte[i3] = (byte)(i6 >> 12 & 0x3F | 0x80);
            if (m >= arrayOfByte.length) {
              arrayOfByte = byteArrayBuilder2.finishCurrentSegment();
              i3 = 0;
            } else {
              i3 = m;
            } 
            i2 = i3 + 1;
            arrayOfByte[i3] = (byte)(i6 >> 6 & 0x3F | 0x80);
            n++;
            m = i6 & 0x3F | 0x80;
            i3 = i2;
          } 
          arrayOfByte1 = arrayOfByte;
          i2 = i3;
          if (i3 >= arrayOfByte.length) {
            arrayOfByte1 = byteArrayBuilder2.finishCurrentSegment();
            i2 = 0;
          } 
          break;
        } 
      } else {
        return this._byteBuilder.completeAndCoalesce(n);
      } 
      arrayOfByte1[i2] = (byte)m;
      arrayOfByte = arrayOfByte1;
      int m = n;
      int k = i2 + 1;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\JsonStringEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */