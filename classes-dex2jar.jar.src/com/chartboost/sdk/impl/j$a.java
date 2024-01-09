package com.chartboost.sdk.impl;

import android.os.AsyncTask;

public class j$a extends AsyncTask {
  final j a;
  
  protected j$a(j paramj) {}
  
  protected j$b a(j$b... paramVarArgs) {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore #4
    //   5: aload #4
    //   7: getfield a : Lcom/chartboost/sdk/impl/k;
    //   10: astore #5
    //   12: new org/apache/http/client/methods/HttpPost
    //   15: dup
    //   16: new java/lang/StringBuilder
    //   19: dup
    //   20: aload_0
    //   21: getfield a : Lcom/chartboost/sdk/impl/j;
    //   24: getfield a : Ljava/lang/String;
    //   27: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   30: invokespecial <init> : (Ljava/lang/String;)V
    //   33: aload #5
    //   35: invokevirtual a : ()Ljava/lang/String;
    //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual toString : ()Ljava/lang/String;
    //   44: invokespecial <init> : (Ljava/lang/String;)V
    //   47: astore #6
    //   49: aload #6
    //   51: ldc 'Content-Type'
    //   53: ldc 'application/json; charset=UTF-8'
    //   55: invokevirtual setHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   58: aload #6
    //   60: ldc 'Accept'
    //   62: ldc 'application/json; charset=UTF-8'
    //   64: invokevirtual setHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   67: aload #6
    //   69: ldc 'X-Chartboost-Client'
    //   71: ldc 'Chartboost-Android-SDK 3.1.5'
    //   73: invokevirtual setHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload #6
    //   78: ldc 'X-Chartboost-API'
    //   80: ldc '3.1.5'
    //   82: invokevirtual setHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   85: aload #5
    //   87: invokevirtual c : ()Ljava/util/Map;
    //   90: astore #7
    //   92: aload #7
    //   94: ifnull -> 119
    //   97: aload #7
    //   99: invokeinterface keySet : ()Ljava/util/Set;
    //   104: invokeinterface iterator : ()Ljava/util/Iterator;
    //   109: astore_3
    //   110: aload_3
    //   111: invokeinterface hasNext : ()Z
    //   116: ifne -> 390
    //   119: aload_0
    //   120: getfield a : Lcom/chartboost/sdk/impl/j;
    //   123: astore_1
    //   124: aload_1
    //   125: monitorenter
    //   126: invokestatic c : ()I
    //   129: iconst_1
    //   130: iadd
    //   131: istore_2
    //   132: iload_2
    //   133: invokestatic a : (I)V
    //   136: aload_1
    //   137: monitorexit
    //   138: aconst_null
    //   139: astore_3
    //   140: aload_3
    //   141: astore_1
    //   142: aload #5
    //   144: getfield e : Lorg/json/JSONObject;
    //   147: ifnull -> 425
    //   150: aload_3
    //   151: astore_1
    //   152: new org/apache/http/entity/StringEntity
    //   155: astore #7
    //   157: aload_3
    //   158: astore_1
    //   159: aload #7
    //   161: aload #5
    //   163: getfield e : Lorg/json/JSONObject;
    //   166: invokevirtual toString : ()Ljava/lang/String;
    //   169: invokespecial <init> : (Ljava/lang/String;)V
    //   172: aload_3
    //   173: astore_1
    //   174: new org/apache/http/message/BasicHeader
    //   177: astore #8
    //   179: aload_3
    //   180: astore_1
    //   181: aload #8
    //   183: ldc 'Content-Type'
    //   185: ldc 'application/json'
    //   187: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   190: aload_3
    //   191: astore_1
    //   192: aload #7
    //   194: aload #8
    //   196: invokevirtual setContentType : (Lorg/apache/http/Header;)V
    //   199: aload_3
    //   200: astore_1
    //   201: aload #6
    //   203: aload #7
    //   205: invokevirtual setEntity : (Lorg/apache/http/HttpEntity;)V
    //   208: aload_3
    //   209: astore_1
    //   210: invokestatic b : ()Lorg/apache/http/client/HttpClient;
    //   213: aload #6
    //   215: invokeinterface execute : (Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   220: astore_3
    //   221: aload_3
    //   222: astore_1
    //   223: aload_3
    //   224: invokeinterface getStatusLine : ()Lorg/apache/http/StatusLine;
    //   229: invokeinterface getStatusCode : ()I
    //   234: istore_2
    //   235: iload_2
    //   236: sipush #300
    //   239: if_icmpge -> 545
    //   242: iload_2
    //   243: sipush #200
    //   246: if_icmplt -> 545
    //   249: aload_3
    //   250: astore_1
    //   251: new java/io/BufferedReader
    //   254: astore #6
    //   256: aload_3
    //   257: astore_1
    //   258: new java/io/InputStreamReader
    //   261: astore #7
    //   263: aload_3
    //   264: astore_1
    //   265: aload #7
    //   267: aload_3
    //   268: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   273: invokeinterface getContent : ()Ljava/io/InputStream;
    //   278: ldc 'UTF-8'
    //   280: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
    //   283: aload_3
    //   284: astore_1
    //   285: aload #6
    //   287: aload #7
    //   289: sipush #2048
    //   292: invokespecial <init> : (Ljava/io/Reader;I)V
    //   295: aload_3
    //   296: astore_1
    //   297: new java/lang/StringBuilder
    //   300: astore #7
    //   302: aload_3
    //   303: astore_1
    //   304: aload #7
    //   306: invokespecial <init> : ()V
    //   309: aload_3
    //   310: astore_1
    //   311: aload #6
    //   313: invokevirtual readLine : ()Ljava/lang/String;
    //   316: astore #8
    //   318: aload #8
    //   320: ifnonnull -> 527
    //   323: aload_3
    //   324: astore_1
    //   325: aload #6
    //   327: invokevirtual close : ()V
    //   330: aload_3
    //   331: astore_1
    //   332: new org/json/JSONTokener
    //   335: astore #6
    //   337: aload_3
    //   338: astore_1
    //   339: aload #6
    //   341: aload #7
    //   343: invokevirtual toString : ()Ljava/lang/String;
    //   346: invokespecial <init> : (Ljava/lang/String;)V
    //   349: aload_3
    //   350: astore_1
    //   351: new org/json/JSONObject
    //   354: astore #7
    //   356: aload_3
    //   357: astore_1
    //   358: aload #7
    //   360: aload #6
    //   362: invokespecial <init> : (Lorg/json/JSONTokener;)V
    //   365: aload_3
    //   366: astore_1
    //   367: aload #5
    //   369: aload #7
    //   371: putfield j : Lorg/json/JSONObject;
    //   374: aload_3
    //   375: astore_1
    //   376: aload_3
    //   377: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   382: invokeinterface consumeContent : ()V
    //   387: aload #4
    //   389: areturn
    //   390: aload_3
    //   391: invokeinterface next : ()Ljava/lang/Object;
    //   396: checkcast java/lang/String
    //   399: astore_1
    //   400: aload #6
    //   402: aload_1
    //   403: aload #7
    //   405: aload_1
    //   406: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   411: checkcast java/lang/String
    //   414: invokevirtual setHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   417: goto -> 110
    //   420: astore_3
    //   421: aload_1
    //   422: monitorexit
    //   423: aload_3
    //   424: athrow
    //   425: aload_3
    //   426: astore_1
    //   427: new java/lang/StringBuilder
    //   430: astore #7
    //   432: aload_3
    //   433: astore_1
    //   434: aload #7
    //   436: ldc 'HTTP Request Body '
    //   438: invokespecial <init> : (Ljava/lang/String;)V
    //   441: aload_3
    //   442: astore_1
    //   443: aload #7
    //   445: iload_2
    //   446: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   449: ldc ' '
    //   451: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: aload #5
    //   456: getfield b : Ljava/lang/String;
    //   459: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   462: invokevirtual toString : ()Ljava/lang/String;
    //   465: ldc '<empty>'
    //   467: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
    //   470: pop
    //   471: goto -> 208
    //   474: astore_3
    //   475: ldc 'Chartboost'
    //   477: new java/lang/StringBuilder
    //   480: dup
    //   481: ldc 'Exception on http request: '
    //   483: invokespecial <init> : (Ljava/lang/String;)V
    //   486: aload_3
    //   487: invokevirtual getLocalizedMessage : ()Ljava/lang/String;
    //   490: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   493: invokevirtual toString : ()Ljava/lang/String;
    //   496: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   499: pop
    //   500: aload_1
    //   501: ifnull -> 524
    //   504: aload_1
    //   505: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   510: ifnull -> 524
    //   513: aload_1
    //   514: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   519: invokeinterface consumeContent : ()V
    //   524: goto -> 387
    //   527: aload_3
    //   528: astore_1
    //   529: aload #7
    //   531: aload #8
    //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: ldc '\\n'
    //   538: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: goto -> 309
    //   545: aload_3
    //   546: astore_1
    //   547: new java/lang/StringBuilder
    //   550: astore #5
    //   552: aload_3
    //   553: astore_1
    //   554: aload #5
    //   556: ldc 'Request failed. Response code: '
    //   558: invokespecial <init> : (Ljava/lang/String;)V
    //   561: aload_3
    //   562: astore_1
    //   563: ldc 'Chartboost'
    //   565: aload #5
    //   567: iload_2
    //   568: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   571: ldc ', body: '
    //   573: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: aload_3
    //   577: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   580: invokevirtual toString : ()Ljava/lang/String;
    //   583: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   586: pop
    //   587: aload_3
    //   588: astore_1
    //   589: aload_3
    //   590: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   595: invokeinterface consumeContent : ()V
    //   600: goto -> 387
    //   603: astore_1
    //   604: goto -> 524
    // Exception table:
    //   from	to	target	type
    //   126	138	420	finally
    //   142	150	474	java/lang/Exception
    //   152	157	474	java/lang/Exception
    //   159	172	474	java/lang/Exception
    //   174	179	474	java/lang/Exception
    //   181	190	474	java/lang/Exception
    //   192	199	474	java/lang/Exception
    //   201	208	474	java/lang/Exception
    //   210	221	474	java/lang/Exception
    //   223	235	474	java/lang/Exception
    //   251	256	474	java/lang/Exception
    //   258	263	474	java/lang/Exception
    //   265	283	474	java/lang/Exception
    //   285	295	474	java/lang/Exception
    //   297	302	474	java/lang/Exception
    //   304	309	474	java/lang/Exception
    //   311	318	474	java/lang/Exception
    //   325	330	474	java/lang/Exception
    //   332	337	474	java/lang/Exception
    //   339	349	474	java/lang/Exception
    //   351	356	474	java/lang/Exception
    //   358	365	474	java/lang/Exception
    //   367	374	474	java/lang/Exception
    //   376	387	474	java/lang/Exception
    //   421	423	420	finally
    //   427	432	474	java/lang/Exception
    //   434	441	474	java/lang/Exception
    //   443	471	474	java/lang/Exception
    //   504	524	603	java/lang/Exception
    //   529	542	474	java/lang/Exception
    //   547	552	474	java/lang/Exception
    //   554	561	474	java/lang/Exception
    //   563	587	474	java/lang/Exception
    //   589	600	474	java/lang/Exception
  }
  
  protected void a(j$b paramj$b) {
    this.a.d.remove(paramj$b.c.intValue());
    if (paramj$b.a.j != null) {
      if (this.a.b != null)
        this.a.b.a(paramj$b.a.j, paramj$b.a); 
      return;
    } 
    j.a(this.a, paramj$b.a);
  }
  
  protected void onPreExecute() {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\j$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */