package com.flurry.org.apache.avro.util;

import com.flurry.org.apache.avro.io.BinaryData;
import java.io.UnsupportedEncodingException;

public class Utf8 implements CharSequence, Comparable {
  private static final byte[] EMPTY = new byte[0];
  
  private byte[] bytes = EMPTY;
  
  private int length;
  
  private String string;
  
  public Utf8() {}
  
  public Utf8(Utf8 paramUtf8) {
    this.length = paramUtf8.length;
    this.bytes = new byte[paramUtf8.length];
    System.arraycopy(paramUtf8.bytes, 0, this.bytes, 0, this.length);
    this.string = paramUtf8.string;
  }
  
  public Utf8(String paramString) {
    this.bytes = getBytesFor(paramString);
    this.length = this.bytes.length;
    this.string = paramString;
  }
  
  public Utf8(byte[] paramArrayOfbyte) {
    this.bytes = paramArrayOfbyte;
    this.length = paramArrayOfbyte.length;
  }
  
  public static final byte[] getBytesFor(String paramString) {
    byte[] arrayOfByte;
    try {
      arrayOfByte = paramString.getBytes("UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      unsupportedEncodingException.printStackTrace();
      arrayOfByte = new byte[0];
    } 
    return arrayOfByte;
  }
  
  public char charAt(int paramInt) {
    return toString().charAt(paramInt);
  }
  
  public int compareTo(Utf8 paramUtf8) {
    return BinaryData.compareBytes(this.bytes, 0, this.length, paramUtf8.bytes, 0, paramUtf8.length);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject instanceof Utf8) {
      paramObject = paramObject;
      bool1 = bool2;
      if (this.length == ((Utf8)paramObject).length) {
        paramObject = ((Utf8)paramObject).bytes;
        byte b = 0;
        while (true) {
          if (b >= this.length)
            return true; 
          bool1 = bool2;
          if (this.bytes[b] == paramObject[b]) {
            b++;
            continue;
          } 
          return bool1;
        } 
      } 
    } 
    return bool1;
  }
  
  public int getByteLength() {
    return this.length;
  }
  
  public byte[] getBytes() {
    return this.bytes;
  }
  
  public int getLength() {
    return this.length;
  }
  
  public int hashCode() {
    byte b = 0;
    int i = 0;
    while (true) {
      if (b >= this.length)
        return i; 
      i = i * 31 + this.bytes[b];
      b++;
    } 
  }
  
  public int length() {
    return toString().length();
  }
  
  public Utf8 setByteLength(int paramInt) {
    if (this.length < paramInt) {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.length);
      this.bytes = arrayOfByte;
    } 
    this.length = paramInt;
    this.string = null;
    return this;
  }
  
  public Utf8 setLength(int paramInt) {
    return setByteLength(paramInt);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return toString().subSequence(paramInt1, paramInt2);
  }
  
  public String toString() {
    if (this.string == null)
      try {
        String str = new String();
        this(this.bytes, 0, this.length, "UTF-8");
        this.string = str;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        unsupportedEncodingException.printStackTrace();
      }  
    return this.string;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avr\\util\Utf8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */