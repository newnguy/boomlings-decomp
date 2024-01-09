package com.flurry.org.codehaus.jackson.util;

import java.util.Arrays;

public final class CharTypes {
  private static final byte[] HEX_BYTES;
  
  private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
  
  static final int[] sHexValues;
  
  static final int[] sInputCodes;
  
  static final int[] sInputCodesComment;
  
  static final int[] sInputCodesJsNames;
  
  static final int[] sInputCodesUtf8;
  
  static final int[] sInputCodesUtf8JsNames;
  
  static final int[] sOutputEscapes128;
  
  static {
    int i = HEX_CHARS.length;
    HEX_BYTES = new byte[i];
    byte b;
    for (b = 0; b < i; b++)
      HEX_BYTES[b] = (byte)HEX_CHARS[b]; 
    int[] arrayOfInt = new int[256];
    for (b = 0; b < 32; b++)
      arrayOfInt[b] = -1; 
    arrayOfInt[34] = 1;
    arrayOfInt[92] = 1;
    sInputCodes = arrayOfInt;
    arrayOfInt = new int[sInputCodes.length];
    System.arraycopy(sInputCodes, 0, arrayOfInt, 0, sInputCodes.length);
    for (i = 128; i < 256; i++) {
      if ((i & 0xE0) == 192) {
        b = 2;
      } else if ((i & 0xF0) == 224) {
        b = 3;
      } else if ((i & 0xF8) == 240) {
        b = 4;
      } else {
        b = -1;
      } 
      arrayOfInt[i] = b;
    } 
    sInputCodesUtf8 = arrayOfInt;
    arrayOfInt = new int[256];
    Arrays.fill(arrayOfInt, -1);
    for (b = 33; b < 256; b++) {
      if (Character.isJavaIdentifierPart((char)b))
        arrayOfInt[b] = 0; 
    } 
    arrayOfInt[64] = 0;
    arrayOfInt[35] = 0;
    arrayOfInt[42] = 0;
    arrayOfInt[45] = 0;
    arrayOfInt[43] = 0;
    sInputCodesJsNames = arrayOfInt;
    arrayOfInt = new int[256];
    System.arraycopy(sInputCodesJsNames, 0, arrayOfInt, 0, sInputCodesJsNames.length);
    Arrays.fill(arrayOfInt, 128, 128, 0);
    sInputCodesUtf8JsNames = arrayOfInt;
    sInputCodesComment = new int[256];
    System.arraycopy(sInputCodesUtf8, 128, sInputCodesComment, 128, 128);
    Arrays.fill(sInputCodesComment, 0, 32, -1);
    sInputCodesComment[9] = 0;
    sInputCodesComment[10] = 10;
    sInputCodesComment[13] = 13;
    sInputCodesComment[42] = 42;
    arrayOfInt = new int[128];
    for (b = 0; b < 32; b++)
      arrayOfInt[b] = -1; 
    arrayOfInt[34] = 34;
    arrayOfInt[92] = 92;
    arrayOfInt[8] = 98;
    arrayOfInt[9] = 116;
    arrayOfInt[12] = 102;
    arrayOfInt[10] = 110;
    arrayOfInt[13] = 114;
    sOutputEscapes128 = arrayOfInt;
    sHexValues = new int[128];
    Arrays.fill(sHexValues, -1);
    for (b = 0; b < 10; b++)
      sHexValues[b + 48] = b; 
    for (b = 0; b < 6; b++) {
      sHexValues[b + 97] = b + 10;
      sHexValues[b + 65] = b + 10;
    } 
  }
  
  public static void appendQuoted(StringBuilder paramStringBuilder, String paramString) {
    int[] arrayOfInt = sOutputEscapes128;
    int i = arrayOfInt.length;
    byte b = 0;
    int j = paramString.length();
    while (b < j) {
      char c = paramString.charAt(b);
      if (c >= i || arrayOfInt[c] == 0) {
        paramStringBuilder.append(c);
      } else {
        paramStringBuilder.append('\\');
        int k = arrayOfInt[c];
        if (k < 0) {
          paramStringBuilder.append('u');
          paramStringBuilder.append('0');
          paramStringBuilder.append('0');
          k = -(k + 1);
          paramStringBuilder.append(HEX_CHARS[k >> 4]);
          paramStringBuilder.append(HEX_CHARS[k & 0xF]);
        } else {
          paramStringBuilder.append((char)k);
        } 
      } 
      b++;
    } 
  }
  
  public static int charToHex(int paramInt) {
    return (paramInt > 127) ? -1 : sHexValues[paramInt];
  }
  
  public static byte[] copyHexBytes() {
    return (byte[])HEX_BYTES.clone();
  }
  
  public static char[] copyHexChars() {
    return (char[])HEX_CHARS.clone();
  }
  
  public static final int[] get7BitOutputEscapes() {
    return sOutputEscapes128;
  }
  
  public static final int[] getInputCodeComment() {
    return sInputCodesComment;
  }
  
  public static final int[] getInputCodeLatin1() {
    return sInputCodes;
  }
  
  public static final int[] getInputCodeLatin1JsNames() {
    return sInputCodesJsNames;
  }
  
  public static final int[] getInputCodeUtf8() {
    return sInputCodesUtf8;
  }
  
  public static final int[] getInputCodeUtf8JsNames() {
    return sInputCodesUtf8JsNames;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\CharTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */