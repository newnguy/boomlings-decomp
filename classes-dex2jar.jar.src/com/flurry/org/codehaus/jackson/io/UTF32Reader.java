package com.flurry.org.codehaus.jackson.io;

import java.io.CharConversionException;
import java.io.InputStream;

public final class UTF32Reader extends BaseReader {
  final boolean mBigEndian;
  
  int mByteCount = 0;
  
  int mCharCount = 0;
  
  char mSurrogate = Character.MIN_VALUE;
  
  public UTF32Reader(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(paramIOContext, paramInputStream, paramArrayOfbyte, paramInt1, paramInt2);
    this.mBigEndian = paramBoolean;
  }
  
  private boolean loadMore(int paramInt) {
    null = false;
    this.mByteCount += this._length - paramInt;
    if (paramInt > 0) {
      if (this._ptr > 0) {
        for (byte b = 0; b < paramInt; b++)
          this._buffer[b] = this._buffer[this._ptr + b]; 
        this._ptr = 0;
      } 
      this._length = paramInt;
    } else {
      this._ptr = 0;
      paramInt = this._in.read(this._buffer);
      if (paramInt < 1) {
        this._length = 0;
        if (paramInt < 0) {
          freeBuffers();
          return null;
        } 
        reportStrangeStream();
      } 
      this._length = paramInt;
    } 
    while (this._length < 4) {
      paramInt = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
      if (paramInt < 1) {
        if (paramInt < 0) {
          freeBuffers();
          reportUnexpectedEOF(this._length, 4);
        } 
        reportStrangeStream();
      } 
      this._length = paramInt + this._length;
    } 
    return true;
  }
  
  private void reportInvalid(int paramInt1, int paramInt2, String paramString) {
    int k = this.mByteCount;
    int i = this._ptr;
    int j = this.mCharCount;
    throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(paramInt1) + paramString + " at char #" + (j + paramInt2) + ", byte #" + (k + i - 1) + ")");
  }
  
  private void reportUnexpectedEOF(int paramInt1, int paramInt2) {
    int j = this.mByteCount;
    int i = this.mCharCount;
    throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + paramInt1 + ", needed " + paramInt2 + ", at char #" + i + ", byte #" + (j + paramInt1) + ")");
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _buffer : [B
    //   4: ifnonnull -> 13
    //   7: iconst_m1
    //   8: istore #4
    //   10: iload #4
    //   12: ireturn
    //   13: iload_3
    //   14: istore #4
    //   16: iload_3
    //   17: iconst_1
    //   18: if_icmplt -> 10
    //   21: iload_2
    //   22: iflt -> 33
    //   25: iload_2
    //   26: iload_3
    //   27: iadd
    //   28: aload_1
    //   29: arraylength
    //   30: if_icmple -> 40
    //   33: aload_0
    //   34: aload_1
    //   35: iload_2
    //   36: iload_3
    //   37: invokevirtual reportBounds : ([CII)V
    //   40: iload_3
    //   41: iload_2
    //   42: iadd
    //   43: istore #7
    //   45: aload_0
    //   46: getfield mSurrogate : C
    //   49: ifeq -> 298
    //   52: iload_2
    //   53: iconst_1
    //   54: iadd
    //   55: istore_3
    //   56: aload_1
    //   57: iload_2
    //   58: aload_0
    //   59: getfield mSurrogate : C
    //   62: castore
    //   63: aload_0
    //   64: iconst_0
    //   65: putfield mSurrogate : C
    //   68: iload_3
    //   69: iload #7
    //   71: if_icmpge -> 437
    //   74: aload_0
    //   75: getfield _ptr : I
    //   78: istore #4
    //   80: aload_0
    //   81: getfield mBigEndian : Z
    //   84: ifeq -> 327
    //   87: aload_0
    //   88: getfield _buffer : [B
    //   91: iload #4
    //   93: baload
    //   94: istore #8
    //   96: aload_0
    //   97: getfield _buffer : [B
    //   100: iload #4
    //   102: iconst_1
    //   103: iadd
    //   104: baload
    //   105: istore #5
    //   107: aload_0
    //   108: getfield _buffer : [B
    //   111: iload #4
    //   113: iconst_2
    //   114: iadd
    //   115: baload
    //   116: istore #6
    //   118: aload_0
    //   119: getfield _buffer : [B
    //   122: iload #4
    //   124: iconst_3
    //   125: iadd
    //   126: baload
    //   127: sipush #255
    //   130: iand
    //   131: iload #8
    //   133: bipush #24
    //   135: ishl
    //   136: iload #5
    //   138: sipush #255
    //   141: iand
    //   142: bipush #16
    //   144: ishl
    //   145: ior
    //   146: iload #6
    //   148: sipush #255
    //   151: iand
    //   152: bipush #8
    //   154: ishl
    //   155: ior
    //   156: ior
    //   157: istore #4
    //   159: aload_0
    //   160: aload_0
    //   161: getfield _ptr : I
    //   164: iconst_4
    //   165: iadd
    //   166: putfield _ptr : I
    //   169: iload #4
    //   171: ldc 65535
    //   173: if_icmple -> 402
    //   176: iload #4
    //   178: ldc 1114111
    //   180: if_icmple -> 220
    //   183: aload_0
    //   184: iload #4
    //   186: iload_3
    //   187: iload_2
    //   188: isub
    //   189: new java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial <init> : ()V
    //   196: ldc '(above '
    //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: ldc 1114111
    //   203: invokestatic toHexString : (I)Ljava/lang/String;
    //   206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc ') '
    //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual toString : ()Ljava/lang/String;
    //   217: invokespecial reportInvalid : (IILjava/lang/String;)V
    //   220: iload #4
    //   222: ldc 65536
    //   224: isub
    //   225: istore #5
    //   227: iload_3
    //   228: iconst_1
    //   229: iadd
    //   230: istore #4
    //   232: aload_1
    //   233: iload_3
    //   234: ldc 55296
    //   236: iload #5
    //   238: bipush #10
    //   240: ishr
    //   241: iadd
    //   242: i2c
    //   243: castore
    //   244: iload #5
    //   246: sipush #1023
    //   249: iand
    //   250: ldc 56320
    //   252: ior
    //   253: istore #6
    //   255: iload #6
    //   257: istore #5
    //   259: iload #4
    //   261: istore_3
    //   262: iload #4
    //   264: iload #7
    //   266: if_icmplt -> 406
    //   269: aload_0
    //   270: iload #6
    //   272: i2c
    //   273: putfield mSurrogate : C
    //   276: iload #4
    //   278: istore_3
    //   279: iload_3
    //   280: iload_2
    //   281: isub
    //   282: istore #4
    //   284: aload_0
    //   285: aload_0
    //   286: getfield mCharCount : I
    //   289: iload #4
    //   291: iadd
    //   292: putfield mCharCount : I
    //   295: goto -> 10
    //   298: aload_0
    //   299: getfield _length : I
    //   302: aload_0
    //   303: getfield _ptr : I
    //   306: isub
    //   307: istore_3
    //   308: iload_3
    //   309: iconst_4
    //   310: if_icmpge -> 440
    //   313: aload_0
    //   314: iload_3
    //   315: invokespecial loadMore : (I)Z
    //   318: ifne -> 440
    //   321: iconst_m1
    //   322: istore #4
    //   324: goto -> 10
    //   327: aload_0
    //   328: getfield _buffer : [B
    //   331: iload #4
    //   333: baload
    //   334: istore #5
    //   336: aload_0
    //   337: getfield _buffer : [B
    //   340: iload #4
    //   342: iconst_1
    //   343: iadd
    //   344: baload
    //   345: istore #8
    //   347: aload_0
    //   348: getfield _buffer : [B
    //   351: iload #4
    //   353: iconst_2
    //   354: iadd
    //   355: baload
    //   356: istore #6
    //   358: aload_0
    //   359: getfield _buffer : [B
    //   362: iload #4
    //   364: iconst_3
    //   365: iadd
    //   366: baload
    //   367: bipush #24
    //   369: ishl
    //   370: iload #5
    //   372: sipush #255
    //   375: iand
    //   376: iload #8
    //   378: sipush #255
    //   381: iand
    //   382: bipush #8
    //   384: ishl
    //   385: ior
    //   386: iload #6
    //   388: sipush #255
    //   391: iand
    //   392: bipush #16
    //   394: ishl
    //   395: ior
    //   396: ior
    //   397: istore #4
    //   399: goto -> 159
    //   402: iload #4
    //   404: istore #5
    //   406: iload_3
    //   407: iconst_1
    //   408: iadd
    //   409: istore #4
    //   411: aload_1
    //   412: iload_3
    //   413: iload #5
    //   415: i2c
    //   416: castore
    //   417: iload #4
    //   419: istore_3
    //   420: aload_0
    //   421: getfield _ptr : I
    //   424: aload_0
    //   425: getfield _length : I
    //   428: if_icmplt -> 68
    //   431: iload #4
    //   433: istore_3
    //   434: goto -> 279
    //   437: goto -> 279
    //   440: iload_2
    //   441: istore_3
    //   442: goto -> 68
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\UTF32Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */