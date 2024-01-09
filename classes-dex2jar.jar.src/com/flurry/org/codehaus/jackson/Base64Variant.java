package com.flurry.org.codehaus.jackson;

import java.util.Arrays;

public final class Base64Variant {
  public static final int BASE64_VALUE_INVALID = -1;
  
  public static final int BASE64_VALUE_PADDING = -2;
  
  static final char PADDING_CHAR_NONE = '\000';
  
  private final int[] _asciiToBase64 = new int[128];
  
  private final byte[] _base64ToAsciiB = new byte[64];
  
  private final char[] _base64ToAsciiC = new char[64];
  
  final int _maxLineLength;
  
  final String _name;
  
  final char _paddingChar;
  
  final boolean _usesPadding;
  
  public Base64Variant(Base64Variant paramBase64Variant, String paramString, int paramInt) {
    this(paramBase64Variant, paramString, paramBase64Variant._usesPadding, paramBase64Variant._paddingChar, paramInt);
  }
  
  public Base64Variant(Base64Variant paramBase64Variant, String paramString, boolean paramBoolean, char paramChar, int paramInt) {
    this._name = paramString;
    byte[] arrayOfByte = paramBase64Variant._base64ToAsciiB;
    System.arraycopy(arrayOfByte, 0, this._base64ToAsciiB, 0, arrayOfByte.length);
    char[] arrayOfChar = paramBase64Variant._base64ToAsciiC;
    System.arraycopy(arrayOfChar, 0, this._base64ToAsciiC, 0, arrayOfChar.length);
    int[] arrayOfInt = paramBase64Variant._asciiToBase64;
    System.arraycopy(arrayOfInt, 0, this._asciiToBase64, 0, arrayOfInt.length);
    this._usesPadding = paramBoolean;
    this._paddingChar = paramChar;
    this._maxLineLength = paramInt;
  }
  
  public Base64Variant(String paramString1, String paramString2, boolean paramBoolean, char paramChar, int paramInt) {
    this._name = paramString1;
    this._usesPadding = paramBoolean;
    this._paddingChar = paramChar;
    this._maxLineLength = paramInt;
    int i = paramString2.length();
    if (i != 64)
      throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + i + ")"); 
    paramString2.getChars(0, i, this._base64ToAsciiC, 0);
    Arrays.fill(this._asciiToBase64, -1);
    for (paramInt = c; paramInt < i; paramInt++) {
      c = this._base64ToAsciiC[paramInt];
      this._base64ToAsciiB[paramInt] = (byte)c;
      this._asciiToBase64[c] = paramInt;
    } 
    if (paramBoolean)
      this._asciiToBase64[paramChar] = -2; 
  }
  
  public int decodeBase64Byte(byte paramByte) {
    return (paramByte <= Byte.MAX_VALUE) ? this._asciiToBase64[paramByte] : -1;
  }
  
  public int decodeBase64Char(char paramChar) {
    return (paramChar <= '') ? this._asciiToBase64[paramChar] : -1;
  }
  
  public int decodeBase64Char(int paramInt) {
    return (paramInt <= 127) ? this._asciiToBase64[paramInt] : -1;
  }
  
  public String encode(byte[] paramArrayOfbyte) {
    return encode(paramArrayOfbyte, false);
  }
  
  public String encode(byte[] paramArrayOfbyte, boolean paramBoolean) {
    int k = paramArrayOfbyte.length;
    StringBuilder stringBuilder = new StringBuilder((k >> 2) + k + (k >> 3));
    if (paramBoolean)
      stringBuilder.append('"'); 
    int i = getMaxLineLength();
    int j = 0;
    i >>= 2;
    while (j <= k - 3) {
      int n = j + 1;
      j = paramArrayOfbyte[j];
      int m = n + 1;
      encodeBase64Chunk(stringBuilder, (j << 8 | paramArrayOfbyte[n] & 0xFF) << 8 | paramArrayOfbyte[m] & 0xFF);
      j = i - 1;
      i = j;
      if (j <= 0) {
        stringBuilder.append('\\');
        stringBuilder.append('n');
        i = getMaxLineLength() >> 2;
      } 
      j = m + 1;
    } 
    k -= j;
    if (k > 0) {
      int m = j + 1;
      j = paramArrayOfbyte[j] << 16;
      i = j;
      if (k == 2)
        i = j | (paramArrayOfbyte[m] & 0xFF) << 8; 
      encodeBase64Partial(stringBuilder, i, k);
    } 
    if (paramBoolean)
      stringBuilder.append('"'); 
    return stringBuilder.toString();
  }
  
  public byte encodeBase64BitsAsByte(int paramInt) {
    return this._base64ToAsciiB[paramInt];
  }
  
  public char encodeBase64BitsAsChar(int paramInt) {
    return this._base64ToAsciiC[paramInt];
  }
  
  public int encodeBase64Chunk(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    int i = paramInt2 + 1;
    paramArrayOfbyte[paramInt2] = this._base64ToAsciiB[paramInt1 >> 18 & 0x3F];
    paramInt2 = i + 1;
    paramArrayOfbyte[i] = this._base64ToAsciiB[paramInt1 >> 12 & 0x3F];
    i = paramInt2 + 1;
    paramArrayOfbyte[paramInt2] = this._base64ToAsciiB[paramInt1 >> 6 & 0x3F];
    paramArrayOfbyte[i] = this._base64ToAsciiB[paramInt1 & 0x3F];
    return i + 1;
  }
  
  public int encodeBase64Chunk(int paramInt1, char[] paramArrayOfchar, int paramInt2) {
    int i = paramInt2 + 1;
    paramArrayOfchar[paramInt2] = this._base64ToAsciiC[paramInt1 >> 18 & 0x3F];
    paramInt2 = i + 1;
    paramArrayOfchar[i] = this._base64ToAsciiC[paramInt1 >> 12 & 0x3F];
    i = paramInt2 + 1;
    paramArrayOfchar[paramInt2] = this._base64ToAsciiC[paramInt1 >> 6 & 0x3F];
    paramArrayOfchar[i] = this._base64ToAsciiC[paramInt1 & 0x3F];
    return i + 1;
  }
  
  public void encodeBase64Chunk(StringBuilder paramStringBuilder, int paramInt) {
    paramStringBuilder.append(this._base64ToAsciiC[paramInt >> 18 & 0x3F]);
    paramStringBuilder.append(this._base64ToAsciiC[paramInt >> 12 & 0x3F]);
    paramStringBuilder.append(this._base64ToAsciiC[paramInt >> 6 & 0x3F]);
    paramStringBuilder.append(this._base64ToAsciiC[paramInt & 0x3F]);
  }
  
  public int encodeBase64Partial(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, int paramInt3) {
    int i = paramInt3 + 1;
    paramArrayOfbyte[paramInt3] = this._base64ToAsciiB[paramInt1 >> 18 & 0x3F];
    paramInt3 = i + 1;
    paramArrayOfbyte[i] = this._base64ToAsciiB[paramInt1 >> 12 & 0x3F];
    if (this._usesPadding) {
      byte b;
      byte b1 = (byte)this._paddingChar;
      i = paramInt3 + 1;
      if (paramInt2 == 2) {
        b = this._base64ToAsciiB[paramInt1 >> 6 & 0x3F];
      } else {
        b = b1;
      } 
      paramArrayOfbyte[paramInt3] = b;
      paramInt1 = i + 1;
      paramArrayOfbyte[i] = b1;
      return paramInt1;
    } 
    if (paramInt2 == 2) {
      paramInt2 = paramInt3 + 1;
      paramArrayOfbyte[paramInt3] = this._base64ToAsciiB[paramInt1 >> 6 & 0x3F];
      return paramInt2;
    } 
    return paramInt3;
  }
  
  public int encodeBase64Partial(int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3) {
    int i = paramInt3 + 1;
    paramArrayOfchar[paramInt3] = this._base64ToAsciiC[paramInt1 >> 18 & 0x3F];
    paramInt3 = i + 1;
    paramArrayOfchar[i] = this._base64ToAsciiC[paramInt1 >> 12 & 0x3F];
    if (this._usesPadding) {
      char c;
      i = paramInt3 + 1;
      if (paramInt2 == 2) {
        c = this._base64ToAsciiC[paramInt1 >> 6 & 0x3F];
      } else {
        c = this._paddingChar;
      } 
      paramArrayOfchar[paramInt3] = c;
      paramInt1 = i + 1;
      paramArrayOfchar[i] = this._paddingChar;
      return paramInt1;
    } 
    if (paramInt2 == 2) {
      paramInt2 = paramInt3 + 1;
      paramArrayOfchar[paramInt3] = this._base64ToAsciiC[paramInt1 >> 6 & 0x3F];
      return paramInt2;
    } 
    return paramInt3;
  }
  
  public void encodeBase64Partial(StringBuilder paramStringBuilder, int paramInt1, int paramInt2) {
    paramStringBuilder.append(this._base64ToAsciiC[paramInt1 >> 18 & 0x3F]);
    paramStringBuilder.append(this._base64ToAsciiC[paramInt1 >> 12 & 0x3F]);
    if (this._usesPadding) {
      char c;
      if (paramInt2 == 2) {
        c = this._base64ToAsciiC[paramInt1 >> 6 & 0x3F];
      } else {
        c = this._paddingChar;
      } 
      paramStringBuilder.append(c);
      paramStringBuilder.append(this._paddingChar);
      return;
    } 
    if (paramInt2 == 2)
      paramStringBuilder.append(this._base64ToAsciiC[paramInt1 >> 6 & 0x3F]); 
  }
  
  public int getMaxLineLength() {
    return this._maxLineLength;
  }
  
  public String getName() {
    return this._name;
  }
  
  public byte getPaddingByte() {
    return (byte)this._paddingChar;
  }
  
  public char getPaddingChar() {
    return this._paddingChar;
  }
  
  public String toString() {
    return this._name;
  }
  
  public boolean usesPadding() {
    return this._usesPadding;
  }
  
  public boolean usesPaddingChar(char paramChar) {
    return (paramChar == this._paddingChar);
  }
  
  public boolean usesPaddingChar(int paramInt) {
    return (paramInt == this._paddingChar);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\Base64Variant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */