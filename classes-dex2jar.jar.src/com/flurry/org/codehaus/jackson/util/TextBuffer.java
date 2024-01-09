package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class TextBuffer {
  static final int MAX_SEGMENT_LEN = 262144;
  
  static final int MIN_SEGMENT_LEN = 1000;
  
  static final char[] NO_CHARS = new char[0];
  
  private final BufferRecycler _allocator;
  
  private char[] _currentSegment;
  
  private int _currentSize;
  
  private boolean _hasSegments = false;
  
  private char[] _inputBuffer;
  
  private int _inputLen;
  
  private int _inputStart;
  
  private char[] _resultArray;
  
  private String _resultString;
  
  private int _segmentSize;
  
  private ArrayList _segments;
  
  public TextBuffer(BufferRecycler paramBufferRecycler) {
    this._allocator = paramBufferRecycler;
  }
  
  private final char[] _charArray(int paramInt) {
    return new char[paramInt];
  }
  
  private char[] buildResultArray() {
    if (this._resultString != null)
      return this._resultString.toCharArray(); 
    if (this._inputStart >= 0) {
      if (this._inputLen < 1)
        return NO_CHARS; 
      char[] arrayOfChar1 = _charArray(this._inputLen);
      System.arraycopy(this._inputBuffer, this._inputStart, arrayOfChar1, 0, this._inputLen);
      return arrayOfChar1;
    } 
    int i = size();
    if (i < 1)
      return NO_CHARS; 
    char[] arrayOfChar = _charArray(i);
    if (this._segments != null) {
      int j = this._segments.size();
      byte b = 0;
      i = 0;
      while (b < j) {
        char[] arrayOfChar1 = this._segments.get(b);
        int k = arrayOfChar1.length;
        System.arraycopy(arrayOfChar1, 0, arrayOfChar, i, k);
        i += k;
        b++;
      } 
    } else {
      i = 0;
    } 
    System.arraycopy(this._currentSegment, 0, arrayOfChar, i, this._currentSize);
    return arrayOfChar;
  }
  
  private final void clearSegments() {
    this._hasSegments = false;
    this._segments.clear();
    this._segmentSize = 0;
    this._currentSize = 0;
  }
  
  private void expand(int paramInt) {
    if (this._segments == null)
      this._segments = new ArrayList(); 
    char[] arrayOfChar = this._currentSegment;
    this._hasSegments = true;
    this._segments.add(arrayOfChar);
    this._segmentSize += arrayOfChar.length;
    int j = arrayOfChar.length;
    int i = j >> 1;
    if (i >= paramInt)
      paramInt = i; 
    arrayOfChar = _charArray(Math.min(262144, j + paramInt));
    this._currentSize = 0;
    this._currentSegment = arrayOfChar;
  }
  
  private final char[] findBuffer(int paramInt) {
    return (this._allocator != null) ? this._allocator.allocCharBuffer(BufferRecycler$CharBufferType.TEXT_BUFFER, paramInt) : new char[Math.max(paramInt, 1000)];
  }
  
  private void unshare(int paramInt) {
    int i = this._inputLen;
    this._inputLen = 0;
    char[] arrayOfChar = this._inputBuffer;
    this._inputBuffer = null;
    int j = this._inputStart;
    this._inputStart = -1;
    paramInt = i + paramInt;
    if (this._currentSegment == null || paramInt > this._currentSegment.length)
      this._currentSegment = findBuffer(paramInt); 
    if (i > 0)
      System.arraycopy(arrayOfChar, j, this._currentSegment, 0, i); 
    this._segmentSize = 0;
    this._currentSize = i;
  }
  
  public void append(char paramChar) {
    if (this._inputStart >= 0)
      unshare(16); 
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar2 = this._currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (this._currentSize >= arrayOfChar2.length) {
      expand(1);
      arrayOfChar1 = this._currentSegment;
    } 
    int i = this._currentSize;
    this._currentSize = i + 1;
    arrayOfChar1[i] = paramChar;
  }
  
  public void append(String paramString, int paramInt1, int paramInt2) {
    if (this._inputStart >= 0)
      unshare(paramInt2); 
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int k = arrayOfChar.length - this._currentSize;
    if (k >= paramInt2) {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, this._currentSize);
      this._currentSize += paramInt2;
      return;
    } 
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0) {
      paramString.getChars(paramInt1, paramInt1 + k, arrayOfChar, this._currentSize);
      i = paramInt2 - k;
      j = paramInt1 + k;
    } 
    expand(i);
    paramString.getChars(j, j + i, this._currentSegment, 0);
    this._currentSize = i;
  }
  
  public void append(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this._inputStart >= 0)
      unshare(paramInt2); 
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int k = arrayOfChar.length - this._currentSize;
    if (k >= paramInt2) {
      System.arraycopy(paramArrayOfchar, paramInt1, arrayOfChar, this._currentSize, paramInt2);
      this._currentSize += paramInt2;
      return;
    } 
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0) {
      System.arraycopy(paramArrayOfchar, paramInt1, arrayOfChar, this._currentSize, k);
      j = paramInt1 + k;
      i = paramInt2 - k;
    } 
    expand(i);
    System.arraycopy(paramArrayOfchar, j, this._currentSegment, 0, i);
    this._currentSize = i;
  }
  
  public char[] contentsAsArray() {
    char[] arrayOfChar2 = this._resultArray;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null) {
      arrayOfChar1 = buildResultArray();
      this._resultArray = arrayOfChar1;
    } 
    return arrayOfChar1;
  }
  
  public BigDecimal contentsAsDecimal() {
    return (this._resultArray != null) ? new BigDecimal(this._resultArray) : ((this._inputStart >= 0) ? new BigDecimal(this._inputBuffer, this._inputStart, this._inputLen) : ((this._segmentSize == 0) ? new BigDecimal(this._currentSegment, 0, this._currentSize) : new BigDecimal(contentsAsArray())));
  }
  
  public double contentsAsDouble() {
    return NumberInput.parseDouble(contentsAsString());
  }
  
  public String contentsAsString() {
    if (this._resultString == null)
      if (this._resultArray != null) {
        this._resultString = new String(this._resultArray);
      } else if (this._inputStart >= 0) {
        if (this._inputLen < 1) {
          String str = "";
          this._resultString = "";
          return str;
        } 
        this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
      } else {
        int i = this._segmentSize;
        int j = this._currentSize;
        if (i == 0) {
          String str;
          if (j == 0) {
            str = "";
          } else {
            str = new String(this._currentSegment, 0, j);
          } 
          this._resultString = str;
        } else {
          StringBuilder stringBuilder = new StringBuilder(i + j);
          if (this._segments != null) {
            j = this._segments.size();
            for (i = 0; i < j; i++) {
              char[] arrayOfChar = this._segments.get(i);
              stringBuilder.append(arrayOfChar, 0, arrayOfChar.length);
            } 
          } 
          stringBuilder.append(this._currentSegment, 0, this._currentSize);
          this._resultString = stringBuilder.toString();
        } 
      }  
    return this._resultString;
  }
  
  public final char[] emptyAndGetCurrentSegment() {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
    char[] arrayOfChar2 = this._currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null) {
      arrayOfChar1 = findBuffer(0);
      this._currentSegment = arrayOfChar1;
    } 
    return arrayOfChar1;
  }
  
  public void ensureNotShared() {
    if (this._inputStart >= 0)
      unshare(16); 
  }
  
  public char[] expandCurrentSegment() {
    char[] arrayOfChar = this._currentSegment;
    int j = arrayOfChar.length;
    if (j == 262144) {
      int k = 262145;
      this._currentSegment = _charArray(k);
      System.arraycopy(arrayOfChar, 0, this._currentSegment, 0, j);
      return this._currentSegment;
    } 
    int i = Math.min(262144, (j >> 1) + j);
    this._currentSegment = _charArray(i);
    System.arraycopy(arrayOfChar, 0, this._currentSegment, 0, j);
    return this._currentSegment;
  }
  
  public char[] finishCurrentSegment() {
    if (this._segments == null)
      this._segments = new ArrayList(); 
    this._hasSegments = true;
    this._segments.add(this._currentSegment);
    int i = this._currentSegment.length;
    this._segmentSize += i;
    char[] arrayOfChar = _charArray(Math.min(i + (i >> 1), 262144));
    this._currentSize = 0;
    this._currentSegment = arrayOfChar;
    return arrayOfChar;
  }
  
  public char[] getCurrentSegment() {
    if (this._inputStart >= 0) {
      unshare(1);
      return this._currentSegment;
    } 
    char[] arrayOfChar = this._currentSegment;
    if (arrayOfChar == null) {
      this._currentSegment = findBuffer(0);
      return this._currentSegment;
    } 
    if (this._currentSize >= arrayOfChar.length)
      expand(1); 
    return this._currentSegment;
  }
  
  public int getCurrentSegmentSize() {
    return this._currentSize;
  }
  
  public char[] getTextBuffer() {
    if (this._inputStart >= 0)
      return this._inputBuffer; 
    if (this._resultArray != null)
      return this._resultArray; 
    if (this._resultString != null) {
      char[] arrayOfChar = this._resultString.toCharArray();
      this._resultArray = arrayOfChar;
      return arrayOfChar;
    } 
    return !this._hasSegments ? this._currentSegment : contentsAsArray();
  }
  
  public int getTextOffset() {
    return (this._inputStart >= 0) ? this._inputStart : 0;
  }
  
  public boolean hasTextAsCharacters() {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (this._inputStart < 0) {
      if (this._resultArray != null)
        return bool2; 
    } else {
      return bool1;
    } 
    bool1 = bool2;
    if (this._resultString != null)
      bool1 = false; 
    return bool1;
  }
  
  public void releaseBuffers() {
    if (this._allocator == null) {
      resetWithEmpty();
      return;
    } 
    if (this._currentSegment != null) {
      resetWithEmpty();
      char[] arrayOfChar = this._currentSegment;
      this._currentSegment = null;
      this._allocator.releaseCharBuffer(BufferRecycler$CharBufferType.TEXT_BUFFER, arrayOfChar);
    } 
  }
  
  public void resetWithCopy(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    } else if (this._currentSegment == null) {
      this._currentSegment = findBuffer(paramInt2);
    } 
    this._segmentSize = 0;
    this._currentSize = 0;
    append(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void resetWithEmpty() {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
  }
  
  public void resetWithShared(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this._resultString = null;
    this._resultArray = null;
    this._inputBuffer = paramArrayOfchar;
    this._inputStart = paramInt1;
    this._inputLen = paramInt2;
    if (this._hasSegments)
      clearSegments(); 
  }
  
  public void resetWithString(String paramString) {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = paramString;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
    this._currentSize = 0;
  }
  
  public void setCurrentLength(int paramInt) {
    this._currentSize = paramInt;
  }
  
  public int size() {
    return (this._inputStart >= 0) ? this._inputLen : ((this._resultArray != null) ? this._resultArray.length : ((this._resultString != null) ? this._resultString.length() : (this._segmentSize + this._currentSize)));
  }
  
  public String toString() {
    return contentsAsString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\TextBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */