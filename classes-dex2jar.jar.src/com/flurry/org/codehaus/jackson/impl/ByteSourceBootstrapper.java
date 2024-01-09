package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonEncoding;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.format.InputAccessor;
import com.flurry.org.codehaus.jackson.format.MatchStrength;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.MergedStream;
import com.flurry.org.codehaus.jackson.io.UTF32Reader;
import com.flurry.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ByteSourceBootstrapper {
  static final byte UTF8_BOM_1 = -17;
  
  static final byte UTF8_BOM_2 = -69;
  
  static final byte UTF8_BOM_3 = -65;
  
  protected boolean _bigEndian = true;
  
  private final boolean _bufferRecyclable;
  
  protected int _bytesPerChar = 0;
  
  protected final IOContext _context;
  
  protected final InputStream _in;
  
  protected final byte[] _inputBuffer;
  
  private int _inputEnd;
  
  protected int _inputProcessed;
  
  private int _inputPtr;
  
  public ByteSourceBootstrapper(IOContext paramIOContext, InputStream paramInputStream) {
    this._context = paramIOContext;
    this._in = paramInputStream;
    this._inputBuffer = paramIOContext.allocReadIOBuffer();
    this._inputPtr = 0;
    this._inputEnd = 0;
    this._inputProcessed = 0;
    this._bufferRecyclable = true;
  }
  
  public ByteSourceBootstrapper(IOContext paramIOContext, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this._context = paramIOContext;
    this._in = null;
    this._inputBuffer = paramArrayOfbyte;
    this._inputPtr = paramInt1;
    this._inputEnd = paramInt1 + paramInt2;
    this._inputProcessed = -paramInt1;
    this._bufferRecyclable = false;
  }
  
  private boolean checkUTF16(int paramInt) {
    null = false;
    if ((0xFF00 & paramInt) == 0) {
      this._bigEndian = true;
    } else if ((paramInt & 0xFF) == 0) {
      this._bigEndian = false;
    } else {
      return null;
    } 
    this._bytesPerChar = 2;
    return true;
  }
  
  private boolean checkUTF32(int paramInt) {
    null = false;
    if (paramInt >> 8 == 0) {
      this._bigEndian = true;
    } else if ((0xFFFFFF & paramInt) == 0) {
      this._bigEndian = false;
    } else if ((0xFF00FFFF & paramInt) == 0) {
      reportWeirdUCS4("3412");
    } else if ((0xFFFF00FF & paramInt) == 0) {
      reportWeirdUCS4("2143");
    } else {
      return null;
    } 
    this._bytesPerChar = 4;
    return true;
  }
  
  private boolean handleBOM(int paramInt) {
    int i;
    null = true;
    switch (paramInt) {
      default:
        i = paramInt >>> 16;
        if (i == 65279) {
          this._inputPtr += 2;
          this._bytesPerChar = 2;
          this._bigEndian = true;
          return null;
        } 
        break;
      case 65279:
        this._bigEndian = true;
        this._inputPtr += 4;
        this._bytesPerChar = 4;
        return null;
      case -131072:
        this._inputPtr += 4;
        this._bytesPerChar = 4;
        this._bigEndian = false;
        return null;
      case 65534:
        reportWeirdUCS4("2143");
      case -16842752:
        reportWeirdUCS4("3412");
    } 
    if (i == 65534) {
      this._inputPtr += 2;
      this._bytesPerChar = 2;
      this._bigEndian = false;
      return null;
    } 
    if (paramInt >>> 8 == 15711167) {
      this._inputPtr += 3;
      this._bytesPerChar = 1;
      this._bigEndian = true;
      return null;
    } 
    return false;
  }
  
  public static MatchStrength hasJSONFormat(InputAccessor paramInputAccessor) {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface hasMoreBytes : ()Z
    //   6: ifne -> 17
    //   9: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   12: astore #4
    //   14: aload #4
    //   16: areturn
    //   17: aload_0
    //   18: invokeinterface nextByte : ()B
    //   23: istore_2
    //   24: iload_2
    //   25: istore_1
    //   26: iload_2
    //   27: bipush #-17
    //   29: if_icmpne -> 128
    //   32: aload_0
    //   33: invokeinterface hasMoreBytes : ()Z
    //   38: ifne -> 49
    //   41: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   44: astore #4
    //   46: goto -> 14
    //   49: aload_0
    //   50: invokeinterface nextByte : ()B
    //   55: bipush #-69
    //   57: if_icmpeq -> 68
    //   60: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.NO_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   63: astore #4
    //   65: goto -> 14
    //   68: aload_0
    //   69: invokeinterface hasMoreBytes : ()Z
    //   74: ifne -> 85
    //   77: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   80: astore #4
    //   82: goto -> 14
    //   85: aload_0
    //   86: invokeinterface nextByte : ()B
    //   91: bipush #-65
    //   93: if_icmpeq -> 104
    //   96: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.NO_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   99: astore #4
    //   101: goto -> 14
    //   104: aload_0
    //   105: invokeinterface hasMoreBytes : ()Z
    //   110: ifne -> 121
    //   113: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   116: astore #4
    //   118: goto -> 14
    //   121: aload_0
    //   122: invokeinterface nextByte : ()B
    //   127: istore_1
    //   128: aload_0
    //   129: iload_1
    //   130: invokestatic skipSpace : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;B)I
    //   133: istore_3
    //   134: iload_3
    //   135: ifge -> 146
    //   138: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   141: astore #4
    //   143: goto -> 14
    //   146: iload_3
    //   147: bipush #123
    //   149: if_icmpne -> 197
    //   152: aload_0
    //   153: invokestatic skipSpace : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;)I
    //   156: istore_3
    //   157: iload_3
    //   158: ifge -> 169
    //   161: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   164: astore #4
    //   166: goto -> 14
    //   169: iload_3
    //   170: bipush #34
    //   172: if_icmpeq -> 181
    //   175: iload_3
    //   176: bipush #125
    //   178: if_icmpne -> 189
    //   181: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.SOLID_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   184: astore #4
    //   186: goto -> 14
    //   189: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.NO_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   192: astore #4
    //   194: goto -> 14
    //   197: iload_3
    //   198: bipush #91
    //   200: if_icmpne -> 248
    //   203: aload_0
    //   204: invokestatic skipSpace : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;)I
    //   207: istore_3
    //   208: iload_3
    //   209: ifge -> 220
    //   212: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   215: astore #4
    //   217: goto -> 14
    //   220: iload_3
    //   221: bipush #93
    //   223: if_icmpeq -> 232
    //   226: iload_3
    //   227: bipush #91
    //   229: if_icmpne -> 240
    //   232: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.SOLID_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   235: astore #4
    //   237: goto -> 14
    //   240: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.SOLID_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   243: astore #4
    //   245: goto -> 14
    //   248: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.WEAK_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   251: astore #5
    //   253: aload #5
    //   255: astore #4
    //   257: iload_3
    //   258: bipush #34
    //   260: if_icmpeq -> 14
    //   263: iload_3
    //   264: bipush #57
    //   266: if_icmpgt -> 279
    //   269: aload #5
    //   271: astore #4
    //   273: iload_3
    //   274: bipush #48
    //   276: if_icmpge -> 14
    //   279: iload_3
    //   280: bipush #45
    //   282: if_icmpne -> 326
    //   285: aload_0
    //   286: invokestatic skipSpace : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;)I
    //   289: istore_3
    //   290: iload_3
    //   291: ifge -> 302
    //   294: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.INCONCLUSIVE : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   297: astore #4
    //   299: goto -> 14
    //   302: iload_3
    //   303: bipush #57
    //   305: if_icmpgt -> 318
    //   308: aload #5
    //   310: astore #4
    //   312: iload_3
    //   313: bipush #48
    //   315: if_icmpge -> 14
    //   318: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.NO_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   321: astore #4
    //   323: goto -> 14
    //   326: iload_3
    //   327: bipush #110
    //   329: if_icmpne -> 345
    //   332: aload_0
    //   333: ldc 'ull'
    //   335: aload #5
    //   337: invokestatic tryMatch : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/format/MatchStrength;)Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   340: astore #4
    //   342: goto -> 14
    //   345: iload_3
    //   346: bipush #116
    //   348: if_icmpne -> 364
    //   351: aload_0
    //   352: ldc 'rue'
    //   354: aload #5
    //   356: invokestatic tryMatch : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/format/MatchStrength;)Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   359: astore #4
    //   361: goto -> 14
    //   364: iload_3
    //   365: bipush #102
    //   367: if_icmpne -> 383
    //   370: aload_0
    //   371: ldc 'alse'
    //   373: aload #5
    //   375: invokestatic tryMatch : (Lcom/flurry/org/codehaus/jackson/format/InputAccessor;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/format/MatchStrength;)Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   378: astore #4
    //   380: goto -> 14
    //   383: getstatic com/flurry/org/codehaus/jackson/format/MatchStrength.NO_MATCH : Lcom/flurry/org/codehaus/jackson/format/MatchStrength;
    //   386: astore #4
    //   388: goto -> 14
  }
  
  private void reportWeirdUCS4(String paramString) {
    throw new CharConversionException("Unsupported UCS-4 endianness (" + paramString + ") detected");
  }
  
  private static final int skipSpace(InputAccessor paramInputAccessor) {
    return !paramInputAccessor.hasMoreBytes() ? -1 : skipSpace(paramInputAccessor, paramInputAccessor.nextByte());
  }
  
  private static final int skipSpace(InputAccessor paramInputAccessor, byte paramByte) {
    while (true) {
      int i = paramByte & 0xFF;
      if (i == 32 || i == 13 || i == 10 || i == 9) {
        if (!paramInputAccessor.hasMoreBytes())
          return -1; 
        i = paramInputAccessor.nextByte();
        continue;
      } 
      return i;
    } 
  }
  
  private static final MatchStrength tryMatch(InputAccessor paramInputAccessor, String paramString, MatchStrength paramMatchStrength) {
    byte b = 0;
    int i = paramString.length();
    while (true) {
      MatchStrength matchStrength = paramMatchStrength;
      if (b < i) {
        if (!paramInputAccessor.hasMoreBytes())
          return MatchStrength.INCONCLUSIVE; 
      } else {
        return matchStrength;
      } 
      if (paramInputAccessor.nextByte() != paramString.charAt(b))
        return MatchStrength.NO_MATCH; 
      b++;
    } 
  }
  
  public JsonParser constructParser(int paramInt, ObjectCodec paramObjectCodec, BytesToNameCanonicalizer paramBytesToNameCanonicalizer, CharsToNameCanonicalizer paramCharsToNameCanonicalizer) {
    JsonEncoding jsonEncoding = detectEncoding();
    boolean bool1 = JsonParser.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(paramInt);
    boolean bool2 = JsonParser.Feature.INTERN_FIELD_NAMES.enabledIn(paramInt);
    if (jsonEncoding == JsonEncoding.UTF8 && bool1) {
      paramBytesToNameCanonicalizer = paramBytesToNameCanonicalizer.makeChild(bool1, bool2);
      return new Utf8StreamParser(this._context, paramInt, this._in, paramObjectCodec, paramBytesToNameCanonicalizer, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
    } 
    return new ReaderBasedParser(this._context, paramInt, constructReader(), paramObjectCodec, paramCharsToNameCanonicalizer.makeChild(bool1, bool2));
  }
  
  public Reader constructReader() {
    MergedStream mergedStream;
    JsonEncoding jsonEncoding = this._context.getEncoding();
    switch (ByteSourceBootstrapper$1.$SwitchMap$org$codehaus$jackson$JsonEncoding[jsonEncoding.ordinal()]) {
      default:
        throw new RuntimeException("Internal error");
      case 1:
      case 2:
        return (Reader)new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian());
      case 3:
      case 4:
      case 5:
        break;
    } 
    InputStream inputStream = this._in;
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
    } else if (this._inputPtr < this._inputEnd) {
      mergedStream = new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
    } 
    return new InputStreamReader((InputStream)mergedStream, jsonEncoding.getJavaName());
  }
  
  public JsonEncoding detectEncoding() {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: iconst_4
    //   4: invokevirtual ensureLoaded : (I)Z
    //   7: ifeq -> 136
    //   10: aload_0
    //   11: getfield _inputBuffer : [B
    //   14: aload_0
    //   15: getfield _inputPtr : I
    //   18: baload
    //   19: bipush #24
    //   21: ishl
    //   22: aload_0
    //   23: getfield _inputBuffer : [B
    //   26: aload_0
    //   27: getfield _inputPtr : I
    //   30: iconst_1
    //   31: iadd
    //   32: baload
    //   33: sipush #255
    //   36: iand
    //   37: bipush #16
    //   39: ishl
    //   40: ior
    //   41: aload_0
    //   42: getfield _inputBuffer : [B
    //   45: aload_0
    //   46: getfield _inputPtr : I
    //   49: iconst_2
    //   50: iadd
    //   51: baload
    //   52: sipush #255
    //   55: iand
    //   56: bipush #8
    //   58: ishl
    //   59: ior
    //   60: aload_0
    //   61: getfield _inputBuffer : [B
    //   64: aload_0
    //   65: getfield _inputPtr : I
    //   68: iconst_3
    //   69: iadd
    //   70: baload
    //   71: sipush #255
    //   74: iand
    //   75: ior
    //   76: istore_3
    //   77: aload_0
    //   78: iload_3
    //   79: invokespecial handleBOM : (I)Z
    //   82: ifeq -> 108
    //   85: iload_2
    //   86: istore_1
    //   87: iload_1
    //   88: ifne -> 188
    //   91: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF8 : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   94: astore #4
    //   96: aload_0
    //   97: getfield _context : Lcom/flurry/org/codehaus/jackson/io/IOContext;
    //   100: aload #4
    //   102: invokevirtual setEncoding : (Lcom/flurry/org/codehaus/jackson/JsonEncoding;)V
    //   105: aload #4
    //   107: areturn
    //   108: iload_2
    //   109: istore_1
    //   110: aload_0
    //   111: iload_3
    //   112: invokespecial checkUTF32 : (I)Z
    //   115: ifne -> 87
    //   118: iload_2
    //   119: istore_1
    //   120: aload_0
    //   121: iload_3
    //   122: bipush #16
    //   124: iushr
    //   125: invokespecial checkUTF16 : (I)Z
    //   128: ifne -> 87
    //   131: iconst_0
    //   132: istore_1
    //   133: goto -> 87
    //   136: aload_0
    //   137: iconst_2
    //   138: invokevirtual ensureLoaded : (I)Z
    //   141: ifeq -> 131
    //   144: aload_0
    //   145: aload_0
    //   146: getfield _inputBuffer : [B
    //   149: aload_0
    //   150: getfield _inputPtr : I
    //   153: baload
    //   154: sipush #255
    //   157: iand
    //   158: bipush #8
    //   160: ishl
    //   161: aload_0
    //   162: getfield _inputBuffer : [B
    //   165: aload_0
    //   166: getfield _inputPtr : I
    //   169: iconst_1
    //   170: iadd
    //   171: baload
    //   172: sipush #255
    //   175: iand
    //   176: ior
    //   177: invokespecial checkUTF16 : (I)Z
    //   180: ifeq -> 131
    //   183: iload_2
    //   184: istore_1
    //   185: goto -> 87
    //   188: aload_0
    //   189: getfield _bytesPerChar : I
    //   192: tableswitch default -> 224, 1 -> 234, 2 -> 242, 3 -> 224, 4 -> 265
    //   224: new java/lang/RuntimeException
    //   227: dup
    //   228: ldc 'Internal error'
    //   230: invokespecial <init> : (Ljava/lang/String;)V
    //   233: athrow
    //   234: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF8 : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   237: astore #4
    //   239: goto -> 96
    //   242: aload_0
    //   243: getfield _bigEndian : Z
    //   246: ifeq -> 257
    //   249: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF16_BE : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   252: astore #4
    //   254: goto -> 96
    //   257: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF16_LE : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   260: astore #4
    //   262: goto -> 96
    //   265: aload_0
    //   266: getfield _bigEndian : Z
    //   269: ifeq -> 280
    //   272: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF32_BE : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   275: astore #4
    //   277: goto -> 96
    //   280: getstatic com/flurry/org/codehaus/jackson/JsonEncoding.UTF32_LE : Lcom/flurry/org/codehaus/jackson/JsonEncoding;
    //   283: astore #4
    //   285: goto -> 96
  }
  
  protected boolean ensureLoaded(int paramInt) {
    boolean bool = true;
    for (int i = this._inputEnd - this._inputPtr;; i = j + i) {
      int j;
      boolean bool1 = bool;
      if (i < paramInt) {
        if (this._in == null) {
          j = -1;
        } else {
          j = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
        } 
        if (j < 1)
          return false; 
      } else {
        return bool1;
      } 
      this._inputEnd += j;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\ByteSourceBootstrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */