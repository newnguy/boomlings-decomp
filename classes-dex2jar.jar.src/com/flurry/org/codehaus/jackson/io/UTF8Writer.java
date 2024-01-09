package com.flurry.org.codehaus.jackson.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer extends Writer {
  static final int SURR1_FIRST = 55296;
  
  static final int SURR1_LAST = 56319;
  
  static final int SURR2_FIRST = 56320;
  
  static final int SURR2_LAST = 57343;
  
  protected final IOContext _context;
  
  OutputStream _out;
  
  byte[] _outBuffer;
  
  final int _outBufferEnd;
  
  int _outPtr;
  
  int _surrogate = 0;
  
  public UTF8Writer(IOContext paramIOContext, OutputStream paramOutputStream) {
    this._context = paramIOContext;
    this._out = paramOutputStream;
    this._outBuffer = paramIOContext.allocWriteEncodingBuffer();
    this._outBufferEnd = this._outBuffer.length - 4;
    this._outPtr = 0;
  }
  
  private int convertSurrogate(int paramInt) {
    int i = this._surrogate;
    this._surrogate = 0;
    if (paramInt < 56320 || paramInt > 57343)
      throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(paramInt) + "; illegal combination"); 
    return (i - 55296 << 10) + 65536 + paramInt - 56320;
  }
  
  private void throwIllegal(int paramInt) {
    if (paramInt > 1114111)
      throw new IOException("Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output; max is 0x10FFFF as per RFC 4627"); 
    if (paramInt >= 55296) {
      if (paramInt <= 56319)
        throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")"); 
      throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")");
    } 
    throw new IOException("Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output");
  }
  
  public Writer append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public void close() {
    if (this._out != null) {
      if (this._outPtr > 0) {
        this._out.write(this._outBuffer, 0, this._outPtr);
        this._outPtr = 0;
      } 
      OutputStream outputStream = this._out;
      this._out = null;
      byte[] arrayOfByte = this._outBuffer;
      if (arrayOfByte != null) {
        this._outBuffer = null;
        this._context.releaseWriteEncodingBuffer(arrayOfByte);
      } 
      outputStream.close();
      int i = this._surrogate;
      this._surrogate = 0;
      if (i > 0)
        throwIllegal(i); 
    } 
  }
  
  public void flush() {
    if (this._out != null) {
      if (this._outPtr > 0) {
        this._out.write(this._outBuffer, 0, this._outPtr);
        this._outPtr = 0;
      } 
      this._out.flush();
    } 
  }
  
  public void write(int paramInt) {
    int i;
    if (this._surrogate > 0) {
      i = convertSurrogate(paramInt);
    } else {
      i = paramInt;
      if (paramInt >= 55296) {
        i = paramInt;
        if (paramInt <= 57343) {
          if (paramInt > 56319)
            throwIllegal(paramInt); 
          this._surrogate = paramInt;
          return;
        } 
      } 
    } 
    if (this._outPtr >= this._outBufferEnd) {
      this._out.write(this._outBuffer, 0, this._outPtr);
      this._outPtr = 0;
    } 
    if (i < 128) {
      byte[] arrayOfByte = this._outBuffer;
      paramInt = this._outPtr;
      this._outPtr = paramInt + 1;
      arrayOfByte[paramInt] = (byte)i;
      return;
    } 
    paramInt = this._outPtr;
    if (i < 2048) {
      byte[] arrayOfByte = this._outBuffer;
      int j = paramInt + 1;
      arrayOfByte[paramInt] = (byte)(i >> 6 | 0xC0);
      arrayOfByte = this._outBuffer;
      paramInt = j + 1;
      arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
    } else if (i <= 65535) {
      byte[] arrayOfByte = this._outBuffer;
      int k = paramInt + 1;
      arrayOfByte[paramInt] = (byte)(i >> 12 | 0xE0);
      arrayOfByte = this._outBuffer;
      int j = k + 1;
      arrayOfByte[k] = (byte)(i >> 6 & 0x3F | 0x80);
      arrayOfByte = this._outBuffer;
      paramInt = j + 1;
      arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
    } else {
      if (i > 1114111)
        throwIllegal(i); 
      byte[] arrayOfByte = this._outBuffer;
      int j = paramInt + 1;
      arrayOfByte[paramInt] = (byte)(i >> 18 | 0xF0);
      arrayOfByte = this._outBuffer;
      paramInt = j + 1;
      arrayOfByte[j] = (byte)(i >> 12 & 0x3F | 0x80);
      arrayOfByte = this._outBuffer;
      j = paramInt + 1;
      arrayOfByte[paramInt] = (byte)(i >> 6 & 0x3F | 0x80);
      arrayOfByte = this._outBuffer;
      paramInt = j + 1;
      arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
    } 
    this._outPtr = paramInt;
  }
  
  public void write(String paramString) {
    write(paramString, 0, paramString.length());
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) {
    if (paramInt2 < 2) {
      if (paramInt2 == 1)
        write(paramString.charAt(paramInt1)); 
      return;
    } 
    int i = paramInt1;
    int j = paramInt2;
    if (this._surrogate > 0) {
      i = paramString.charAt(paramInt1);
      j = paramInt2 - 1;
      write(convertSurrogate(i));
      i = paramInt1 + 1;
    } 
    paramInt1 = this._outPtr;
    byte[] arrayOfByte = this._outBuffer;
    int k = this._outBufferEnd;
    int m = j + i;
    paramInt2 = i;
    while (true) {
      int n;
      i = paramInt1;
      if (paramInt2 < m) {
        i = paramInt1;
        if (paramInt1 >= k) {
          this._out.write(arrayOfByte, 0, paramInt1);
          i = 0;
        } 
        j = paramInt2 + 1;
        n = paramString.charAt(paramInt2);
        if (n < 128) {
          paramInt1 = i + 1;
          arrayOfByte[i] = (byte)n;
          i = m - j;
          paramInt2 = k - paramInt1;
          if (i > paramInt2)
            i = paramInt2; 
          label49: for (paramInt2 = j; paramInt2 < i + j; paramInt2++) {
            n = paramInt2 + 1;
            char c = paramString.charAt(paramInt2);
            if (c >= '') {
              paramInt2 = n;
              i = c;
              if (i < 2048) {
                j = paramInt1 + 1;
                arrayOfByte[paramInt1] = (byte)(i >> 6 | 0xC0);
                paramInt1 = j + 1;
                arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
                break;
              } 
            } else {
              arrayOfByte[paramInt1] = (byte)c;
              paramInt1++;
              paramInt2 = n;
              continue;
            } 
            if (i >= 55296) {
              if (i > 57343)
                continue; 
            } else {
              j = paramInt1 + 1;
              arrayOfByte[paramInt1] = (byte)(i >> 12 | 0xE0);
              n = j + 1;
              arrayOfByte[j] = (byte)(i >> 6 & 0x3F | 0x80);
              paramInt1 = n + 1;
              arrayOfByte[n] = (byte)(i & 0x3F | 0x80);
              continue;
            } 
            if (i > 56319) {
              this._outPtr = paramInt1;
              throwIllegal(i);
            } 
            this._surrogate = i;
            if (paramInt2 >= m) {
              i = paramInt1;
              continue;
            } 
            i = convertSurrogate(paramString.charAt(paramInt2));
            if (i > 1114111) {
              this._outPtr = paramInt1;
              throwIllegal(i);
              break label49;
            } 
            j = paramInt1 + 1;
            arrayOfByte[paramInt1] = (byte)(i >> 18 | 0xF0);
            paramInt1 = j + 1;
            arrayOfByte[j] = (byte)(i >> 12 & 0x3F | 0x80);
            j = paramInt1 + 1;
            arrayOfByte[paramInt1] = (byte)(i >> 6 & 0x3F | 0x80);
            paramInt1 = j + 1;
            arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
          } 
          continue;
        } 
      } else {
        this._outPtr = i;
        return;
      } 
      paramInt2 = j;
      j = n;
      paramInt1 = i;
      i = j;
      continue;
    } 
  }
  
  public void write(char[] paramArrayOfchar) {
    write(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramInt2 < 2) {
      if (paramInt2 == 1)
        write(paramArrayOfchar[paramInt1]); 
      return;
    } 
    int i = paramInt1;
    int j = paramInt2;
    if (this._surrogate > 0) {
      i = paramArrayOfchar[paramInt1];
      j = paramInt2 - 1;
      write(convertSurrogate(i));
      i = paramInt1 + 1;
    } 
    paramInt1 = this._outPtr;
    byte[] arrayOfByte = this._outBuffer;
    int k = this._outBufferEnd;
    int m = j + i;
    paramInt2 = i;
    while (true) {
      int n;
      i = paramInt1;
      if (paramInt2 < m) {
        i = paramInt1;
        if (paramInt1 >= k) {
          this._out.write(arrayOfByte, 0, paramInt1);
          i = 0;
        } 
        j = paramInt2 + 1;
        n = paramArrayOfchar[paramInt2];
        if (n < 128) {
          paramInt1 = i + 1;
          arrayOfByte[i] = (byte)n;
          paramInt2 = m - j;
          i = k - paramInt1;
          if (paramInt2 <= i)
            i = paramInt2; 
          label49: for (paramInt2 = j; paramInt2 < i + j; paramInt2++) {
            n = paramInt2 + 1;
            char c = paramArrayOfchar[paramInt2];
            if (c >= '') {
              paramInt2 = n;
              i = c;
              if (i < 2048) {
                j = paramInt1 + 1;
                arrayOfByte[paramInt1] = (byte)(i >> 6 | 0xC0);
                paramInt1 = j + 1;
                arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
                break;
              } 
            } else {
              arrayOfByte[paramInt1] = (byte)c;
              paramInt1++;
              paramInt2 = n;
              continue;
            } 
            if (i >= 55296) {
              if (i > 57343)
                continue; 
            } else {
              n = paramInt1 + 1;
              arrayOfByte[paramInt1] = (byte)(i >> 12 | 0xE0);
              j = n + 1;
              arrayOfByte[n] = (byte)(i >> 6 & 0x3F | 0x80);
              paramInt1 = j + 1;
              arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
              continue;
            } 
            if (i > 56319) {
              this._outPtr = paramInt1;
              throwIllegal(i);
            } 
            this._surrogate = i;
            if (paramInt2 >= m) {
              i = paramInt1;
              continue;
            } 
            i = convertSurrogate(paramArrayOfchar[paramInt2]);
            if (i > 1114111) {
              this._outPtr = paramInt1;
              throwIllegal(i);
              break label49;
            } 
            j = paramInt1 + 1;
            arrayOfByte[paramInt1] = (byte)(i >> 18 | 0xF0);
            paramInt1 = j + 1;
            arrayOfByte[j] = (byte)(i >> 12 & 0x3F | 0x80);
            j = paramInt1 + 1;
            arrayOfByte[paramInt1] = (byte)(i >> 6 & 0x3F | 0x80);
            paramInt1 = j + 1;
            arrayOfByte[j] = (byte)(i & 0x3F | 0x80);
          } 
          continue;
        } 
      } else {
        this._outPtr = i;
        return;
      } 
      paramInt2 = j;
      j = n;
      paramInt1 = i;
      i = j;
      continue;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\UTF8Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */