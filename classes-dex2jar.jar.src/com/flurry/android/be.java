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
    //   8: astore_2
    //   9: aload_2
    //   10: aconst_null
    //   11: aconst_null
    //   12: invokevirtual load : (Ljava/io/InputStream;[C)V
    //   15: new com/flurry/android/bi
    //   18: astore_3
    //   19: aload_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokespecial <init> : (Lcom/flurry/android/be;Ljava/security/KeyStore;)V
    //   25: new org/apache/http/conn/scheme/SchemeRegistry
    //   28: astore_2
    //   29: aload_2
    //   30: invokespecial <init> : ()V
    //   33: new org/apache/http/conn/scheme/Scheme
    //   36: astore #4
    //   38: aload #4
    //   40: ldc 'http'
    //   42: invokestatic getSocketFactory : ()Lorg/apache/http/conn/scheme/PlainSocketFactory;
    //   45: bipush #80
    //   47: invokespecial <init> : (Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   50: aload_2
    //   51: aload #4
    //   53: invokevirtual register : (Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   56: pop
    //   57: new org/apache/http/conn/scheme/Scheme
    //   60: astore #4
    //   62: aload #4
    //   64: ldc 'https'
    //   66: aload_3
    //   67: sipush #443
    //   70: invokespecial <init> : (Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   73: aload_2
    //   74: aload #4
    //   76: invokevirtual register : (Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   79: pop
    //   80: new org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager
    //   83: astore_3
    //   84: aload_3
    //   85: aload_1
    //   86: aload_2
    //   87: invokespecial <init> : (Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/scheme/SchemeRegistry;)V
    //   90: new org/apache/http/impl/client/DefaultHttpClient
    //   93: astore_2
    //   94: aload_2
    //   95: aload_3
    //   96: aload_1
    //   97: invokespecial <init> : (Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/params/HttpParams;)V
    //   100: aload_2
    //   101: astore_1
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: areturn
    //   106: astore_2
    //   107: new org/apache/http/impl/client/DefaultHttpClient
    //   110: dup
    //   111: aload_1
    //   112: invokespecial <init> : (Lorg/apache/http/params/HttpParams;)V
    //   115: astore_1
    //   116: goto -> 102
    //   119: astore_1
    //   120: aload_0
    //   121: monitorexit
    //   122: aload_1
    //   123: athrow
    // Exception table:
    //   from	to	target	type
    //   2	100	106	java/lang/Exception
    //   2	100	119	finally
    //   107	116	119	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */