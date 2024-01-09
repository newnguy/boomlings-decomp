package com.flurry.org.codehaus.jackson.format;

import com.flurry.org.codehaus.jackson.JsonFactory;
import java.io.EOFException;
import java.io.InputStream;

public class InputAccessor$Std implements InputAccessor {
  protected final byte[] _buffer;
  
  protected int _bufferedAmount;
  
  protected final InputStream _in;
  
  protected int _ptr;
  
  public InputAccessor$Std(InputStream paramInputStream, byte[] paramArrayOfbyte) {
    this._in = paramInputStream;
    this._buffer = paramArrayOfbyte;
    this._bufferedAmount = 0;
  }
  
  public InputAccessor$Std(byte[] paramArrayOfbyte) {
    this._in = null;
    this._buffer = paramArrayOfbyte;
    this._bufferedAmount = paramArrayOfbyte.length;
  }
  
  public DataFormatMatcher createMatcher(JsonFactory paramJsonFactory, MatchStrength paramMatchStrength) {
    return new DataFormatMatcher(this._in, this._buffer, this._bufferedAmount, paramJsonFactory, paramMatchStrength);
  }
  
  public boolean hasMoreBytes() {
    boolean bool = true;
    if (this._ptr >= this._bufferedAmount) {
      int i = this._buffer.length - this._ptr;
      if (i < 1)
        return false; 
      i = this._in.read(this._buffer, this._ptr, i);
      if (i <= 0)
        return false; 
      this._bufferedAmount += i;
    } 
    return bool;
  }
  
  public byte nextByte() {
    if (this._ptr > -this._bufferedAmount && !hasMoreBytes())
      throw new EOFException("Could not read more than " + this._ptr + " bytes (max buffer size: " + this._buffer.length + ")"); 
    byte[] arrayOfByte = this._buffer;
    int i = this._ptr;
    this._ptr = i + 1;
    return arrayOfByte[i];
  }
  
  public void reset() {
    this._ptr = 0;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\format\InputAccessor$Std.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */