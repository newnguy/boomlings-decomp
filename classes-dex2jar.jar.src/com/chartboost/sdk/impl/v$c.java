package com.chartboost.sdk.impl;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class v$c extends WebViewClient {
  final v a;
  
  private v b;
  
  public v$c(v paramv1, v paramv2) {
    this.b = paramv2;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    super.onPageFinished(paramWebView, paramString);
    if (this.b != null && this.b.c != null)
      this.b.c.a(); 
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2) {
    if (this.b.d != null)
      this.b.d.a(); 
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: new java/net/URI
    //   6: astore_1
    //   7: aload_1
    //   8: aload_2
    //   9: invokespecial <init> : (Ljava/lang/String;)V
    //   12: aload_1
    //   13: invokevirtual getScheme : ()Ljava/lang/String;
    //   16: astore_1
    //   17: aload_1
    //   18: ldc 'chartboost'
    //   20: invokevirtual equals : (Ljava/lang/Object;)Z
    //   23: ifeq -> 149
    //   26: aload_2
    //   27: ldc '/'
    //   29: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   32: astore #5
    //   34: aload #5
    //   36: arraylength
    //   37: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   40: astore #6
    //   42: aload #6
    //   44: invokevirtual intValue : ()I
    //   47: iconst_3
    //   48: if_icmpge -> 113
    //   51: iload #4
    //   53: istore_3
    //   54: aload_0
    //   55: getfield b : Lcom/chartboost/sdk/impl/v;
    //   58: getfield a : Lcom/chartboost/sdk/b$a;
    //   61: ifnull -> 79
    //   64: aload_0
    //   65: getfield b : Lcom/chartboost/sdk/impl/v;
    //   68: getfield a : Lcom/chartboost/sdk/b$a;
    //   71: invokeinterface a : ()V
    //   76: iload #4
    //   78: istore_3
    //   79: iload_3
    //   80: ireturn
    //   81: astore_1
    //   82: iload #4
    //   84: istore_3
    //   85: aload_0
    //   86: getfield b : Lcom/chartboost/sdk/impl/v;
    //   89: getfield a : Lcom/chartboost/sdk/b$a;
    //   92: ifnull -> 79
    //   95: aload_0
    //   96: getfield b : Lcom/chartboost/sdk/impl/v;
    //   99: getfield a : Lcom/chartboost/sdk/b$a;
    //   102: invokeinterface a : ()V
    //   107: iload #4
    //   109: istore_3
    //   110: goto -> 79
    //   113: aload #5
    //   115: iconst_2
    //   116: aaload
    //   117: astore_1
    //   118: aload_1
    //   119: ldc 'close'
    //   121: invokevirtual equals : (Ljava/lang/Object;)Z
    //   124: ifeq -> 154
    //   127: aload_0
    //   128: getfield b : Lcom/chartboost/sdk/impl/v;
    //   131: getfield a : Lcom/chartboost/sdk/b$a;
    //   134: ifnull -> 149
    //   137: aload_0
    //   138: getfield b : Lcom/chartboost/sdk/impl/v;
    //   141: getfield a : Lcom/chartboost/sdk/b$a;
    //   144: invokeinterface a : ()V
    //   149: iconst_1
    //   150: istore_3
    //   151: goto -> 79
    //   154: aload_1
    //   155: ldc 'link'
    //   157: invokevirtual equals : (Ljava/lang/Object;)Z
    //   160: ifeq -> 149
    //   163: aload #6
    //   165: invokevirtual intValue : ()I
    //   168: iconst_4
    //   169: if_icmpge -> 203
    //   172: iload #4
    //   174: istore_3
    //   175: aload_0
    //   176: getfield b : Lcom/chartboost/sdk/impl/v;
    //   179: getfield a : Lcom/chartboost/sdk/b$a;
    //   182: ifnull -> 79
    //   185: aload_0
    //   186: getfield b : Lcom/chartboost/sdk/impl/v;
    //   189: getfield a : Lcom/chartboost/sdk/b$a;
    //   192: invokeinterface a : ()V
    //   197: iload #4
    //   199: istore_3
    //   200: goto -> 79
    //   203: aload #5
    //   205: iconst_3
    //   206: aaload
    //   207: ldc 'UTF-8'
    //   209: invokestatic decode : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   212: astore_1
    //   213: aload_1
    //   214: astore_2
    //   215: aload #6
    //   217: invokevirtual intValue : ()I
    //   220: iconst_4
    //   221: if_icmpge -> 289
    //   224: new org/json/JSONTokener
    //   227: astore #6
    //   229: aload #6
    //   231: aload #5
    //   233: iconst_4
    //   234: aaload
    //   235: ldc 'UTF-8'
    //   237: invokestatic decode : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   240: invokespecial <init> : (Ljava/lang/String;)V
    //   243: new org/json/JSONObject
    //   246: astore_2
    //   247: aload_2
    //   248: aload #6
    //   250: invokespecial <init> : (Lorg/json/JSONTokener;)V
    //   253: aload_0
    //   254: getfield b : Lcom/chartboost/sdk/impl/v;
    //   257: getfield b : Lcom/chartboost/sdk/b$c;
    //   260: ifnull -> 149
    //   263: aload_0
    //   264: getfield b : Lcom/chartboost/sdk/impl/v;
    //   267: getfield b : Lcom/chartboost/sdk/b$c;
    //   270: aload_1
    //   271: aload_2
    //   272: invokeinterface a : (Ljava/lang/String;Lorg/json/JSONObject;)V
    //   277: goto -> 149
    //   280: astore_2
    //   281: aconst_null
    //   282: astore_1
    //   283: aload_2
    //   284: invokevirtual printStackTrace : ()V
    //   287: aload_1
    //   288: astore_2
    //   289: aconst_null
    //   290: astore #5
    //   292: aload_2
    //   293: astore_1
    //   294: aload #5
    //   296: astore_2
    //   297: goto -> 253
    //   300: astore_2
    //   301: goto -> 283
    // Exception table:
    //   from	to	target	type
    //   3	17	81	java/lang/Exception
    //   203	213	280	java/lang/Exception
    //   215	253	300	java/lang/Exception
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\v$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */