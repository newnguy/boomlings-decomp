package com.flurry.android;

final class bb {
  private int a;
  
  private long b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  public bb(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3) {
    this.a = paramInt;
    this.b = paramLong;
    this.c = paramString1;
    this.d = paramString2;
    this.e = paramString3;
  }
  
  public final byte[] a() {
    // Byte code:
    //   0: new java/io/ByteArrayOutputStream
    //   3: astore_3
    //   4: aload_3
    //   5: invokespecial <init> : ()V
    //   8: new java/io/DataOutputStream
    //   11: astore_2
    //   12: aload_2
    //   13: aload_3
    //   14: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   17: aload_2
    //   18: astore_1
    //   19: aload_2
    //   20: aload_0
    //   21: getfield a : I
    //   24: invokevirtual writeShort : (I)V
    //   27: aload_2
    //   28: astore_1
    //   29: aload_2
    //   30: aload_0
    //   31: getfield b : J
    //   34: invokevirtual writeLong : (J)V
    //   37: aload_2
    //   38: astore_1
    //   39: aload_2
    //   40: aload_0
    //   41: getfield c : Ljava/lang/String;
    //   44: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: aload_0
    //   51: getfield d : Ljava/lang/String;
    //   54: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   57: aload_2
    //   58: astore_1
    //   59: aload_2
    //   60: aload_0
    //   61: getfield e : Ljava/lang/String;
    //   64: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   67: aload_2
    //   68: astore_1
    //   69: aload_2
    //   70: invokevirtual flush : ()V
    //   73: aload_2
    //   74: astore_1
    //   75: aload_3
    //   76: invokevirtual toByteArray : ()[B
    //   79: astore_3
    //   80: aload_3
    //   81: astore_1
    //   82: aload_2
    //   83: invokestatic a : (Ljava/io/Closeable;)V
    //   86: aload_1
    //   87: areturn
    //   88: astore_1
    //   89: aconst_null
    //   90: astore_2
    //   91: aload_2
    //   92: astore_1
    //   93: iconst_0
    //   94: newarray byte
    //   96: astore_3
    //   97: aload_2
    //   98: invokestatic a : (Ljava/io/Closeable;)V
    //   101: aload_3
    //   102: astore_1
    //   103: goto -> 86
    //   106: astore_1
    //   107: aconst_null
    //   108: astore_2
    //   109: aload_1
    //   110: astore_3
    //   111: aload_2
    //   112: invokestatic a : (Ljava/io/Closeable;)V
    //   115: aload_3
    //   116: athrow
    //   117: astore_3
    //   118: aload_1
    //   119: astore_2
    //   120: goto -> 111
    //   123: astore_1
    //   124: goto -> 91
    // Exception table:
    //   from	to	target	type
    //   0	17	88	java/io/IOException
    //   0	17	106	finally
    //   19	27	123	java/io/IOException
    //   19	27	117	finally
    //   29	37	123	java/io/IOException
    //   29	37	117	finally
    //   39	47	123	java/io/IOException
    //   39	47	117	finally
    //   49	57	123	java/io/IOException
    //   49	57	117	finally
    //   59	67	123	java/io/IOException
    //   59	67	117	finally
    //   69	73	123	java/io/IOException
    //   69	73	117	finally
    //   75	80	123	java/io/IOException
    //   75	80	117	finally
    //   93	97	117	finally
  }
  
  public final String b() {
    return this.c;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */