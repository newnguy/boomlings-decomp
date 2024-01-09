package com.flurry.org.codehaus.jackson.io;

public final class NumberInput {
  static final long L_BILLION = 1000000000L;
  
  static final String MAX_LONG_STR;
  
  static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
  
  public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
  
  static {
    MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
  }
  
  public static final boolean inLongRange(String paramString, boolean paramBoolean) {
    String str;
    boolean bool = true;
    if (paramBoolean) {
      str = MIN_LONG_STR_NO_SIGN;
    } else {
      str = MAX_LONG_STR;
    } 
    int j = str.length();
    int i = paramString.length();
    if (i < j)
      return bool; 
    if (i > j)
      return false; 
    i = 0;
    while (true) {
      paramBoolean = bool;
      if (i < j) {
        int k = paramString.charAt(i) - str.charAt(i);
        if (k != 0)
          return (k < 0); 
        i++;
        continue;
      } 
      return paramBoolean;
    } 
  }
  
  public static final boolean inLongRange(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean) {
    String str;
    boolean bool = true;
    if (paramBoolean) {
      str = MIN_LONG_STR_NO_SIGN;
    } else {
      str = MAX_LONG_STR;
    } 
    int i = str.length();
    if (paramInt2 < i)
      return bool; 
    if (paramInt2 > i)
      return false; 
    paramInt2 = 0;
    while (true) {
      paramBoolean = bool;
      if (paramInt2 < i) {
        int j = paramArrayOfchar[paramInt1 + paramInt2] - str.charAt(paramInt2);
        if (j != 0)
          return (j < 0); 
        paramInt2++;
        continue;
      } 
      return paramBoolean;
    } 
  }
  
  public static double parseAsDouble(String paramString, double paramDouble) {
    if (paramString == null)
      return paramDouble; 
    paramString = paramString.trim();
    double d = paramDouble;
    if (paramString.length() != 0)
      try {
        d = parseDouble(paramString);
      } catch (NumberFormatException numberFormatException) {
        d = paramDouble;
      }  
    return d;
  }
  
  public static int parseAsInt(String paramString, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #6
    //   3: aload_0
    //   4: ifnonnull -> 13
    //   7: iload_1
    //   8: istore #4
    //   10: iload #4
    //   12: ireturn
    //   13: aload_0
    //   14: invokevirtual trim : ()Ljava/lang/String;
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual length : ()I
    //   22: istore #5
    //   24: iload_1
    //   25: istore #4
    //   27: iload #5
    //   29: ifeq -> 10
    //   32: iload #5
    //   34: ifge -> 150
    //   37: aload_0
    //   38: iconst_0
    //   39: invokevirtual charAt : (I)C
    //   42: istore #4
    //   44: iload #4
    //   46: bipush #43
    //   48: if_icmpne -> 108
    //   51: aload_0
    //   52: iconst_1
    //   53: invokevirtual substring : (I)Ljava/lang/String;
    //   56: astore_0
    //   57: aload_0
    //   58: invokevirtual length : ()I
    //   61: istore #5
    //   63: iload #6
    //   65: istore #4
    //   67: iload #4
    //   69: iload #5
    //   71: if_icmpge -> 127
    //   74: aload_0
    //   75: iload #4
    //   77: invokevirtual charAt : (I)C
    //   80: istore #6
    //   82: iload #6
    //   84: bipush #57
    //   86: if_icmpgt -> 96
    //   89: iload #6
    //   91: bipush #48
    //   93: if_icmpge -> 121
    //   96: aload_0
    //   97: invokestatic parseDouble : (Ljava/lang/String;)D
    //   100: dstore_2
    //   101: dload_2
    //   102: d2i
    //   103: istore #4
    //   105: goto -> 10
    //   108: iload #4
    //   110: bipush #45
    //   112: if_icmpne -> 150
    //   115: iconst_1
    //   116: istore #4
    //   118: goto -> 67
    //   121: iinc #4, 1
    //   124: goto -> 67
    //   127: aload_0
    //   128: invokestatic parseInt : (Ljava/lang/String;)I
    //   131: istore #4
    //   133: goto -> 10
    //   136: astore_0
    //   137: iload_1
    //   138: istore #4
    //   140: goto -> 10
    //   143: astore_0
    //   144: iload_1
    //   145: istore #4
    //   147: goto -> 10
    //   150: iload #6
    //   152: istore #4
    //   154: goto -> 67
    // Exception table:
    //   from	to	target	type
    //   96	101	136	java/lang/NumberFormatException
    //   127	133	143	java/lang/NumberFormatException
  }
  
  public static long parseAsLong(String paramString, long paramLong) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: aload_0
    //   4: ifnonnull -> 13
    //   7: lload_1
    //   8: lstore #8
    //   10: lload #8
    //   12: lreturn
    //   13: aload_0
    //   14: invokevirtual trim : ()Ljava/lang/String;
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual length : ()I
    //   22: istore #5
    //   24: lload_1
    //   25: lstore #8
    //   27: iload #5
    //   29: ifeq -> 10
    //   32: iload #5
    //   34: ifge -> 158
    //   37: aload_0
    //   38: iconst_0
    //   39: invokevirtual charAt : (I)C
    //   42: istore #6
    //   44: iload #6
    //   46: bipush #43
    //   48: if_icmpne -> 108
    //   51: aload_0
    //   52: iconst_1
    //   53: invokevirtual substring : (I)Ljava/lang/String;
    //   56: astore_0
    //   57: aload_0
    //   58: invokevirtual length : ()I
    //   61: istore #6
    //   63: iload #7
    //   65: istore #5
    //   67: iload #5
    //   69: iload #6
    //   71: if_icmpge -> 135
    //   74: aload_0
    //   75: iload #5
    //   77: invokevirtual charAt : (I)C
    //   80: istore #7
    //   82: iload #7
    //   84: bipush #57
    //   86: if_icmpgt -> 96
    //   89: iload #7
    //   91: bipush #48
    //   93: if_icmpge -> 129
    //   96: aload_0
    //   97: invokestatic parseDouble : (Ljava/lang/String;)D
    //   100: dstore_3
    //   101: dload_3
    //   102: d2l
    //   103: lstore #8
    //   105: goto -> 10
    //   108: iload #6
    //   110: bipush #45
    //   112: if_icmpne -> 158
    //   115: iconst_1
    //   116: istore #7
    //   118: iload #5
    //   120: istore #6
    //   122: iload #7
    //   124: istore #5
    //   126: goto -> 67
    //   129: iinc #5, 1
    //   132: goto -> 67
    //   135: aload_0
    //   136: invokestatic parseLong : (Ljava/lang/String;)J
    //   139: lstore #8
    //   141: goto -> 10
    //   144: astore_0
    //   145: lload_1
    //   146: lstore #8
    //   148: goto -> 10
    //   151: astore_0
    //   152: lload_1
    //   153: lstore #8
    //   155: goto -> 10
    //   158: iload #5
    //   160: istore #6
    //   162: iload #7
    //   164: istore #5
    //   166: goto -> 67
    // Exception table:
    //   from	to	target	type
    //   96	101	144	java/lang/NumberFormatException
    //   135	141	151	java/lang/NumberFormatException
  }
  
  public static final double parseDouble(String paramString) {
    return "2.2250738585072012e-308".equals(paramString) ? 2.2250738585072014E-308D : Double.parseDouble(paramString);
  }
  
  public static final int parseInt(String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: iconst_0
    //   4: invokevirtual charAt : (I)C
    //   7: istore_2
    //   8: aload_0
    //   9: invokevirtual length : ()I
    //   12: istore #5
    //   14: iload_2
    //   15: bipush #45
    //   17: if_icmpne -> 46
    //   20: iconst_1
    //   21: istore_3
    //   22: iload_3
    //   23: ifeq -> 79
    //   26: iload #5
    //   28: iconst_1
    //   29: if_icmpeq -> 39
    //   32: iload #5
    //   34: bipush #10
    //   36: if_icmple -> 51
    //   39: aload_0
    //   40: invokestatic parseInt : (Ljava/lang/String;)I
    //   43: istore_1
    //   44: iload_1
    //   45: ireturn
    //   46: iconst_0
    //   47: istore_3
    //   48: goto -> 22
    //   51: aload_0
    //   52: iconst_1
    //   53: invokevirtual charAt : (I)C
    //   56: istore_2
    //   57: iconst_2
    //   58: istore_1
    //   59: iload_2
    //   60: bipush #57
    //   62: if_icmpgt -> 71
    //   65: iload_2
    //   66: bipush #48
    //   68: if_icmpge -> 94
    //   71: aload_0
    //   72: invokestatic parseInt : (Ljava/lang/String;)I
    //   75: istore_1
    //   76: goto -> 44
    //   79: iload #5
    //   81: bipush #9
    //   83: if_icmple -> 59
    //   86: aload_0
    //   87: invokestatic parseInt : (Ljava/lang/String;)I
    //   90: istore_1
    //   91: goto -> 44
    //   94: iload_2
    //   95: bipush #48
    //   97: isub
    //   98: istore #4
    //   100: iload #4
    //   102: istore_2
    //   103: iload_1
    //   104: iload #5
    //   106: if_icmpge -> 265
    //   109: iload_1
    //   110: iconst_1
    //   111: iadd
    //   112: istore #6
    //   114: aload_0
    //   115: iload_1
    //   116: invokevirtual charAt : (I)C
    //   119: istore_1
    //   120: iload_1
    //   121: bipush #57
    //   123: if_icmpgt -> 132
    //   126: iload_1
    //   127: bipush #48
    //   129: if_icmpge -> 140
    //   132: aload_0
    //   133: invokestatic parseInt : (Ljava/lang/String;)I
    //   136: istore_1
    //   137: goto -> 44
    //   140: iload #4
    //   142: bipush #10
    //   144: imul
    //   145: iload_1
    //   146: bipush #48
    //   148: isub
    //   149: iadd
    //   150: istore_1
    //   151: iload_1
    //   152: istore_2
    //   153: iload #6
    //   155: iload #5
    //   157: if_icmpge -> 265
    //   160: iload #6
    //   162: iconst_1
    //   163: iadd
    //   164: istore #4
    //   166: aload_0
    //   167: iload #6
    //   169: invokevirtual charAt : (I)C
    //   172: istore_2
    //   173: iload_2
    //   174: bipush #57
    //   176: if_icmpgt -> 185
    //   179: iload_2
    //   180: bipush #48
    //   182: if_icmpge -> 193
    //   185: aload_0
    //   186: invokestatic parseInt : (Ljava/lang/String;)I
    //   189: istore_1
    //   190: goto -> 44
    //   193: iload_1
    //   194: bipush #10
    //   196: imul
    //   197: iload_2
    //   198: bipush #48
    //   200: isub
    //   201: iadd
    //   202: istore_1
    //   203: iload_1
    //   204: istore_2
    //   205: iload #4
    //   207: iload #5
    //   209: if_icmpge -> 265
    //   212: iload #4
    //   214: istore_2
    //   215: iload_2
    //   216: iconst_1
    //   217: iadd
    //   218: istore #4
    //   220: aload_0
    //   221: iload_2
    //   222: invokevirtual charAt : (I)C
    //   225: istore_2
    //   226: iload_2
    //   227: bipush #57
    //   229: if_icmpgt -> 238
    //   232: iload_2
    //   233: bipush #48
    //   235: if_icmpge -> 246
    //   238: aload_0
    //   239: invokestatic parseInt : (Ljava/lang/String;)I
    //   242: istore_1
    //   243: goto -> 44
    //   246: iload_1
    //   247: bipush #10
    //   249: imul
    //   250: iload_2
    //   251: bipush #48
    //   253: isub
    //   254: iadd
    //   255: istore_1
    //   256: iload #4
    //   258: iload #5
    //   260: if_icmplt -> 277
    //   263: iload_1
    //   264: istore_2
    //   265: iload_2
    //   266: istore_1
    //   267: iload_3
    //   268: ifeq -> 44
    //   271: iload_2
    //   272: ineg
    //   273: istore_1
    //   274: goto -> 44
    //   277: iload #4
    //   279: istore_2
    //   280: goto -> 215
  }
  
  public static final int parseInt(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int i = paramArrayOfchar[paramInt1] - 48;
    int j = paramInt2 + paramInt1;
    int k = paramInt1 + 1;
    paramInt1 = i;
    if (k < j) {
      paramInt2 = i * 10 + paramArrayOfchar[k] - 48;
      i = k + 1;
      paramInt1 = paramInt2;
      if (i < j) {
        paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
        i++;
        paramInt1 = paramInt2;
        if (i < j) {
          paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
          i++;
          paramInt1 = paramInt2;
          if (i < j) {
            paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
            i++;
            paramInt1 = paramInt2;
            if (i < j) {
              paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
              i++;
              paramInt1 = paramInt2;
              if (i < j) {
                paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
                i++;
                paramInt1 = paramInt2;
                if (i < j) {
                  paramInt2 = paramInt2 * 10 + paramArrayOfchar[i] - 48;
                  i++;
                  paramInt1 = paramInt2;
                  if (i < j)
                    paramInt1 = paramInt2 * 10 + paramArrayOfchar[i] - 48; 
                } 
              } 
            } 
          } 
        } 
      } 
    } 
    return paramInt1;
  }
  
  public static final long parseLong(String paramString) {
    return (paramString.length() <= 9) ? parseInt(paramString) : Long.parseLong(paramString);
  }
  
  public static final long parseLong(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    paramInt2 -= 9;
    return parseInt(paramArrayOfchar, paramInt1, paramInt2) * 1000000000L + parseInt(paramArrayOfchar, paramInt2 + paramInt1, 9);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\io\NumberInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */