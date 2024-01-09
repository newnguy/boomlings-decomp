package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.io.IOContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public abstract class StreamBasedParserBase extends JsonParserBase {
  protected boolean _bufferRecyclable;
  
  protected byte[] _inputBuffer;
  
  protected InputStream _inputStream;
  
  protected StreamBasedParserBase(IOContext paramIOContext, int paramInt1, InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt2, int paramInt3, boolean paramBoolean) {
    super(paramIOContext, paramInt1);
    this._inputStream = paramInputStream;
    this._inputBuffer = paramArrayOfbyte;
    this._inputPtr = paramInt2;
    this._inputEnd = paramInt3;
    this._bufferRecyclable = paramBoolean;
  }
  
  protected void _closeInput() {
    if (this._inputStream != null) {
      if (this._ioContext.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))
        this._inputStream.close(); 
      this._inputStream = null;
    } 
  }
  
  protected final boolean _loadToHaveAtLeast(int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_0
    //   4: getfield _inputStream : Ljava/io/InputStream;
    //   7: ifnonnull -> 13
    //   10: iload #4
    //   12: ireturn
    //   13: aload_0
    //   14: getfield _inputEnd : I
    //   17: aload_0
    //   18: getfield _inputPtr : I
    //   21: isub
    //   22: istore_3
    //   23: iload_3
    //   24: ifle -> 167
    //   27: aload_0
    //   28: getfield _inputPtr : I
    //   31: ifle -> 167
    //   34: aload_0
    //   35: aload_0
    //   36: getfield _currInputProcessed : J
    //   39: aload_0
    //   40: getfield _inputPtr : I
    //   43: i2l
    //   44: ladd
    //   45: putfield _currInputProcessed : J
    //   48: aload_0
    //   49: aload_0
    //   50: getfield _currInputRowStart : I
    //   53: aload_0
    //   54: getfield _inputPtr : I
    //   57: isub
    //   58: putfield _currInputRowStart : I
    //   61: aload_0
    //   62: getfield _inputBuffer : [B
    //   65: aload_0
    //   66: getfield _inputPtr : I
    //   69: aload_0
    //   70: getfield _inputBuffer : [B
    //   73: iconst_0
    //   74: iload_3
    //   75: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: iload_3
    //   80: putfield _inputEnd : I
    //   83: aload_0
    //   84: iconst_0
    //   85: putfield _inputPtr : I
    //   88: aload_0
    //   89: getfield _inputEnd : I
    //   92: iload_1
    //   93: if_icmpge -> 188
    //   96: aload_0
    //   97: getfield _inputStream : Ljava/io/InputStream;
    //   100: aload_0
    //   101: getfield _inputBuffer : [B
    //   104: aload_0
    //   105: getfield _inputEnd : I
    //   108: aload_0
    //   109: getfield _inputBuffer : [B
    //   112: arraylength
    //   113: aload_0
    //   114: getfield _inputEnd : I
    //   117: isub
    //   118: invokevirtual read : ([BII)I
    //   121: istore_2
    //   122: iload_2
    //   123: iconst_1
    //   124: if_icmpge -> 175
    //   127: aload_0
    //   128: invokevirtual _closeInput : ()V
    //   131: iload_2
    //   132: ifne -> 10
    //   135: new java/io/IOException
    //   138: dup
    //   139: new java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial <init> : ()V
    //   146: ldc 'InputStream.read() returned 0 characters when trying to read '
    //   148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: iload_3
    //   152: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   155: ldc ' bytes'
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual toString : ()Ljava/lang/String;
    //   163: invokespecial <init> : (Ljava/lang/String;)V
    //   166: athrow
    //   167: aload_0
    //   168: iconst_0
    //   169: putfield _inputEnd : I
    //   172: goto -> 83
    //   175: aload_0
    //   176: iload_2
    //   177: aload_0
    //   178: getfield _inputEnd : I
    //   181: iadd
    //   182: putfield _inputEnd : I
    //   185: goto -> 88
    //   188: iconst_1
    //   189: istore #4
    //   191: goto -> 10
  }
  
  protected void _releaseBuffers() {
    super._releaseBuffers();
    if (this._bufferRecyclable) {
      byte[] arrayOfByte = this._inputBuffer;
      if (arrayOfByte != null) {
        this._inputBuffer = null;
        this._ioContext.releaseReadIOBuffer(arrayOfByte);
      } 
    } 
  }
  
  public Object getInputSource() {
    return this._inputStream;
  }
  
  protected final boolean loadMore() {
    int i;
    boolean bool2 = false;
    this._currInputProcessed += this._inputEnd;
    this._currInputRowStart -= this._inputEnd;
    boolean bool1 = bool2;
    if (this._inputStream != null) {
      i = this._inputStream.read(this._inputBuffer, 0, this._inputBuffer.length);
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
      throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes"); 
    return bool1;
  }
  
  public int releaseBuffered(OutputStream paramOutputStream) {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1)
      return 0; 
    int j = this._inputPtr;
    paramOutputStream.write(this._inputBuffer, j, i);
    return i;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\StreamBasedParserBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */