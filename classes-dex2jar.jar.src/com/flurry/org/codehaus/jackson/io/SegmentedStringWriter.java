package com.flurry.org.codehaus.jackson.io;

import com.flurry.org.codehaus.jackson.util.BufferRecycler;
import com.flurry.org.codehaus.jackson.util.TextBuffer;
import java.io.Writer;

public final class SegmentedStringWriter extends Writer {
  protected final TextBuffer _buffer;
  
  public SegmentedStringWriter(BufferRecycler paramBufferRecycler) {
    this._buffer = new TextBuffer(paramBufferRecycler);
  }
  
  public Writer append(char paramChar) {
    write(paramChar);
    return this;
  }
  
  public Writer append(CharSequence paramCharSequence) {
    paramCharSequence = paramCharSequence.toString();
    this._buffer.append((String)paramCharSequence, 0, paramCharSequence.length());
    return this;
  }
  
  public Writer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    paramCharSequence = paramCharSequence.subSequence(paramInt1, paramInt2).toString();
    this._buffer.append((String)paramCharSequence, 0, paramCharSequence.length());
    return this;
  }
  
  public void close() {}
  
  public void flush() {}
  
  public String getAndClear() {
    String str = this._buffer.contentsAsString();
    this._buffer.releaseBuffers();
    return str;
  }
  
  public void write(int paramInt) {
    this._buffer.append((char)paramInt);
  }
  
  public void write(String paramString) {
    this._buffer.append(paramString, 0, paramString.length());
  }
  
  public void write(String paramString, int paramInt1, int paramInt2) {
    this._buffer.append(paramString, 0, paramString.length());
  }
  
  public void write(char[] paramArrayOfchar) {
    this._buffer.append(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this._buffer.append(paramArrayOfchar, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\SegmentedStringWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */