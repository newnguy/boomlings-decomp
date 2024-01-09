package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.io.IOContext;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

@Deprecated
public abstract class ReaderBasedParserBase extends JsonParserBase {
  protected char[] _inputBuffer;
  
  protected Reader _reader;
  
  protected ReaderBasedParserBase(IOContext paramIOContext, int paramInt, Reader paramReader) {
    super(paramIOContext, paramInt);
    this._reader = paramReader;
    this._inputBuffer = paramIOContext.allocTokenBuffer();
  }
  
  protected void _closeInput() {
    if (this._reader != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._reader.close(); 
      this._reader = null;
    } 
  }
  
  protected final boolean _matchToken(String paramString, int paramInt) {
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
        if ((this._inputPtr < this._inputEnd || loadMore()) && Character.isJavaIdentifierPart(this._inputBuffer[this._inputPtr])) {
          this._inputPtr++;
          _reportInvalidToken(paramString.substring(0, j), "'null', 'true', 'false' or NaN");
        } 
        return true;
      } 
    } 
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
  
  public int releaseBuffered(Writer paramWriter) {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1)
      return 0; 
    int j = this._inputPtr;
    paramWriter.write(this._inputBuffer, j, i);
    return i;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\ReaderBasedParserBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */