package com.flurry.android;

import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpParams;

final class be {
  final HttpClient a(HttpParams paramHttpParams) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic getDefaultType : ()Ljava/lang/String;
    //   5: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/KeyStore;
    //   8: astore_3
    //   9: aload_3
    //   10: aconst_null
    //   11: aconst_null
    //   12: invokevirtual load : (Ljava/io/InputStream;[C)V
    //   15: new com/flurry/android/bi
    //   18: astore_2
    //   19: aload_2
    //   20: aload_0
    //   21: aload_3
    //   22: invokespecial <init> : (Lcom/flurry/android/be;Ljava/security/KeyStore;)V
    //   25: new org/apache/http/conn/scheme/SchemeRegistry
    //   28: astore_3
    //   29: aload_3
    //   30: invokespecial <init> : ()V
    //   33: new org/apache/http/conn/scheme/Scheme
    //   36: astore #4
    //   38: aload #4
    //   40: ldc 'http'
    //   42: invokestatic getSocketFactory : ()Lorg/apache/http/conn/scheme/PlainSocketFactory;
    //   45: bipush #80
    //   47: invokespecial <init> : (Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   50: aload_3
    //   51: aload #4
    //   53: invokevirtual register : (Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   56: pop
    //   57: new org/apache/http/conn/scheme/Scheme
    //   60: astore #4
    //   62: aload #4
    //   64: ldc 'https'
    //   66: aload_2
    //   67: sipush #443
    //   70: invokespecial <init> : (Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   73: aload_3
    //   74: aload #4
    //   76: invokevirtual register : (Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   79: pop
    //   80: new org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager
    //   83: astore #4
    //   85: aload #4
    //   87: aload_1
    //   88: aload_3
    //   89: invokespecial <init> : (Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/scheme/SchemeRegistry;)V
    //   92: new org/apache/http/impl/client/DefaultHttpClient
    //   95: astore_2
    //   96: aload_2
    //   97: aload #4
    //   99: aload_1
    //   100: invokespecial <init> : (Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/params/HttpParams;)V
    //   103: aload_2
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: areturn
    //   109: astore_2
    //   110: new org/apache/http/impl/client/DefaultHttpClient
    //   113: dup
    //   114: aload_1
    //   115: invokespecial <init> : (Lorg/apache/http/params/HttpParams;)V
    //   118: astore_1
    //   119: goto -> 105
    //   122: astore_1
    //   123: aload_0
    //   124: monitorexit
    //   125: aload_1
    //   126: athrow
    // Exception table:
    //   from	to	target	type
    //   2	103	109	java/lang/Exception
    //   2	103	122	finally
    //   110	119	122	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */