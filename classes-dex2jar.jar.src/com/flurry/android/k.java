package com.flurry.android;

import android.os.AsyncTask;

final class k extends AsyncTask {
  private bo a;
  
  k(bo parambo) {}
  
  private Void a() {
    // Byte code:
    //   0: aload_0
    //   1: getfield a : Lcom/flurry/android/bo;
    //   4: invokestatic a : (Lcom/flurry/android/bo;)Ljava/io/File;
    //   7: invokevirtual exists : ()Z
    //   10: istore_1
    //   11: iload_1
    //   12: ifeq -> 89
    //   15: new java/io/FileInputStream
    //   18: astore_2
    //   19: aload_2
    //   20: aload_0
    //   21: getfield a : Lcom/flurry/android/bo;
    //   24: invokestatic a : (Lcom/flurry/android/bo;)Ljava/io/File;
    //   27: invokespecial <init> : (Ljava/io/File;)V
    //   30: new java/io/DataInputStream
    //   33: astore_3
    //   34: aload_3
    //   35: aload_2
    //   36: invokespecial <init> : (Ljava/io/InputStream;)V
    //   39: aload_3
    //   40: astore_2
    //   41: aload_3
    //   42: invokevirtual readUnsignedShort : ()I
    //   45: ldc 46586
    //   47: if_icmpne -> 60
    //   50: aload_3
    //   51: astore_2
    //   52: aload_0
    //   53: getfield a : Lcom/flurry/android/bo;
    //   56: aload_3
    //   57: invokevirtual a : (Ljava/io/DataInputStream;)V
    //   60: aload_3
    //   61: invokestatic a : (Ljava/io/Closeable;)V
    //   64: aload_0
    //   65: getfield a : Lcom/flurry/android/bo;
    //   68: invokestatic b : (Lcom/flurry/android/bo;)Z
    //   71: ifne -> 89
    //   74: aload_0
    //   75: getfield a : Lcom/flurry/android/bo;
    //   78: invokestatic a : (Lcom/flurry/android/bo;)Ljava/io/File;
    //   81: invokevirtual delete : ()Z
    //   84: istore_1
    //   85: iload_1
    //   86: ifne -> 89
    //   89: aconst_null
    //   90: areturn
    //   91: astore #4
    //   93: aconst_null
    //   94: astore_3
    //   95: aload_3
    //   96: astore_2
    //   97: getstatic com/flurry/android/bo.a : Ljava/lang/String;
    //   100: ldc 'Error when loading persistent file'
    //   102: aload #4
    //   104: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   107: pop
    //   108: aload_3
    //   109: invokestatic a : (Ljava/io/Closeable;)V
    //   112: goto -> 64
    //   115: astore_2
    //   116: aload_2
    //   117: invokevirtual printStackTrace : ()V
    //   120: goto -> 89
    //   123: astore_3
    //   124: aconst_null
    //   125: astore_2
    //   126: aload_2
    //   127: invokestatic a : (Ljava/io/Closeable;)V
    //   130: aload_3
    //   131: athrow
    //   132: astore_2
    //   133: getstatic com/flurry/android/bo.a : Ljava/lang/String;
    //   136: ldc ''
    //   138: aload_2
    //   139: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   142: pop
    //   143: goto -> 89
    //   146: astore_3
    //   147: goto -> 126
    //   150: astore #4
    //   152: goto -> 95
    // Exception table:
    //   from	to	target	type
    //   0	11	115	java/lang/Throwable
    //   15	39	91	java/lang/Throwable
    //   15	39	123	finally
    //   41	50	150	java/lang/Throwable
    //   41	50	146	finally
    //   52	60	150	java/lang/Throwable
    //   52	60	146	finally
    //   60	64	115	java/lang/Throwable
    //   64	85	132	java/lang/Throwable
    //   97	108	146	finally
    //   108	112	115	java/lang/Throwable
    //   126	132	115	java/lang/Throwable
    //   133	143	115	java/lang/Throwable
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */