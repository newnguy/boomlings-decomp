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
    //   123: astore_2
    //   124: aconst_null
    //   125: astore #4
    //   127: aload_2
    //   128: astore_3
    //   129: aload #4
    //   131: invokestatic a : (Ljava/io/Closeable;)V
    //   134: aload_3
    //   135: athrow
    //   136: astore_2
    //   137: getstatic com/flurry/android/bo.a : Ljava/lang/String;
    //   140: ldc ''
    //   142: aload_2
    //   143: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   146: pop
    //   147: goto -> 89
    //   150: astore_3
    //   151: aload_2
    //   152: astore #4
    //   154: goto -> 129
    //   157: astore #4
    //   159: goto -> 95
    // Exception table:
    //   from	to	target	type
    //   0	11	115	java/lang/Throwable
    //   15	39	91	java/lang/Throwable
    //   15	39	123	finally
    //   41	50	157	java/lang/Throwable
    //   41	50	150	finally
    //   52	60	157	java/lang/Throwable
    //   52	60	150	finally
    //   60	64	115	java/lang/Throwable
    //   64	85	136	java/lang/Throwable
    //   97	108	150	finally
    //   108	112	115	java/lang/Throwable
    //   129	136	115	java/lang/Throwable
    //   137	147	115	java/lang/Throwable
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */