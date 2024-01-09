package com.tapjoy;

import java.net.HttpURLConnection;
import java.net.URL;

public class am {
  public ai a(String paramString1, String paramString2) {
    return a(paramString1, paramString2, 0);
  }
  
  public ai a(String paramString1, String paramString2, int paramInt) {
    // Byte code:
    //   0: new com/tapjoy/ai
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aconst_null
    //   10: astore #4
    //   12: new java/lang/StringBuilder
    //   15: astore #6
    //   17: aload #6
    //   19: invokespecial <init> : ()V
    //   22: aload #6
    //   24: aload_1
    //   25: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_2
    //   29: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual toString : ()Ljava/lang/String;
    //   35: ldc ' '
    //   37: ldc '%20'
    //   39: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   42: astore_2
    //   43: new java/lang/StringBuilder
    //   46: astore #6
    //   48: aload #6
    //   50: invokespecial <init> : ()V
    //   53: ldc 'TapjoyURLConnection'
    //   55: aload #6
    //   57: ldc 'baseURL: '
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_1
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   72: new java/lang/StringBuilder
    //   75: astore_1
    //   76: aload_1
    //   77: invokespecial <init> : ()V
    //   80: ldc 'TapjoyURLConnection'
    //   82: aload_1
    //   83: ldc 'requestURL: '
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_2
    //   89: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual toString : ()Ljava/lang/String;
    //   95: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   98: new java/lang/StringBuilder
    //   101: astore_1
    //   102: aload_1
    //   103: invokespecial <init> : ()V
    //   106: ldc 'TapjoyURLConnection'
    //   108: aload_1
    //   109: ldc 'type: '
    //   111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: iload_3
    //   115: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   118: invokevirtual toString : ()Ljava/lang/String;
    //   121: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   124: new java/net/URL
    //   127: astore_1
    //   128: aload_1
    //   129: aload_2
    //   130: invokespecial <init> : (Ljava/lang/String;)V
    //   133: aload_1
    //   134: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   137: checkcast java/net/HttpURLConnection
    //   140: astore_1
    //   141: aload_1
    //   142: sipush #15000
    //   145: invokevirtual setConnectTimeout : (I)V
    //   148: aload_1
    //   149: sipush #30000
    //   152: invokevirtual setReadTimeout : (I)V
    //   155: iload_3
    //   156: iconst_1
    //   157: if_icmpne -> 166
    //   160: aload_1
    //   161: ldc 'POST'
    //   163: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   166: aload_1
    //   167: invokevirtual connect : ()V
    //   170: aload #5
    //   172: aload_1
    //   173: invokevirtual getResponseCode : ()I
    //   176: putfield a : I
    //   179: new java/io/BufferedReader
    //   182: astore_2
    //   183: new java/io/InputStreamReader
    //   186: astore #4
    //   188: aload #4
    //   190: aload_1
    //   191: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   194: invokespecial <init> : (Ljava/io/InputStream;)V
    //   197: aload_2
    //   198: aload #4
    //   200: invokespecial <init> : (Ljava/io/Reader;)V
    //   203: new java/lang/StringBuilder
    //   206: astore #7
    //   208: aload #7
    //   210: invokespecial <init> : ()V
    //   213: aload_2
    //   214: invokevirtual readLine : ()Ljava/lang/String;
    //   217: astore #6
    //   219: aload #6
    //   221: ifnull -> 510
    //   224: new java/lang/StringBuilder
    //   227: astore #4
    //   229: aload #4
    //   231: invokespecial <init> : ()V
    //   234: aload #7
    //   236: aload #4
    //   238: aload #6
    //   240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: bipush #10
    //   245: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   248: invokevirtual toString : ()Ljava/lang/String;
    //   251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: goto -> 213
    //   258: astore_2
    //   259: ldc 'TapjoyURLConnection'
    //   261: new java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial <init> : ()V
    //   268: ldc 'Exception: '
    //   270: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: aload_2
    //   274: invokevirtual toString : ()Ljava/lang/String;
    //   277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokevirtual toString : ()Ljava/lang/String;
    //   283: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   286: aload_1
    //   287: ifnull -> 402
    //   290: aload #5
    //   292: getfield c : Ljava/lang/String;
    //   295: ifnonnull -> 402
    //   298: new java/io/BufferedReader
    //   301: astore_2
    //   302: new java/io/InputStreamReader
    //   305: astore #4
    //   307: aload #4
    //   309: aload_1
    //   310: invokevirtual getErrorStream : ()Ljava/io/InputStream;
    //   313: invokespecial <init> : (Ljava/io/InputStream;)V
    //   316: aload_2
    //   317: aload #4
    //   319: invokespecial <init> : (Ljava/io/Reader;)V
    //   322: new java/lang/StringBuilder
    //   325: astore #4
    //   327: aload #4
    //   329: invokespecial <init> : ()V
    //   332: aload_2
    //   333: invokevirtual readLine : ()Ljava/lang/String;
    //   336: astore_1
    //   337: aload_1
    //   338: ifnull -> 582
    //   341: new java/lang/StringBuilder
    //   344: astore #6
    //   346: aload #6
    //   348: invokespecial <init> : ()V
    //   351: aload #4
    //   353: aload #6
    //   355: aload_1
    //   356: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: bipush #10
    //   361: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   364: invokevirtual toString : ()Ljava/lang/String;
    //   367: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: pop
    //   371: goto -> 332
    //   374: astore_1
    //   375: ldc 'TapjoyURLConnection'
    //   377: new java/lang/StringBuilder
    //   380: dup
    //   381: invokespecial <init> : ()V
    //   384: ldc 'Exception trying to get error code/content: '
    //   386: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload_1
    //   390: invokevirtual toString : ()Ljava/lang/String;
    //   393: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: invokevirtual toString : ()Ljava/lang/String;
    //   399: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   402: ldc 'TapjoyURLConnection'
    //   404: ldc '--------------------'
    //   406: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   409: ldc 'TapjoyURLConnection'
    //   411: new java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial <init> : ()V
    //   418: ldc 'response status: '
    //   420: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: aload #5
    //   425: getfield a : I
    //   428: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   431: invokevirtual toString : ()Ljava/lang/String;
    //   434: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   437: ldc 'TapjoyURLConnection'
    //   439: new java/lang/StringBuilder
    //   442: dup
    //   443: invokespecial <init> : ()V
    //   446: ldc 'response size: '
    //   448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: aload #5
    //   453: getfield b : I
    //   456: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   459: invokevirtual toString : ()Ljava/lang/String;
    //   462: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   465: ldc 'TapjoyURLConnection'
    //   467: ldc 'response: '
    //   469: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   472: ldc 'TapjoyURLConnection'
    //   474: new java/lang/StringBuilder
    //   477: dup
    //   478: invokespecial <init> : ()V
    //   481: ldc ''
    //   483: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload #5
    //   488: getfield c : Ljava/lang/String;
    //   491: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: invokevirtual toString : ()Ljava/lang/String;
    //   497: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   500: ldc 'TapjoyURLConnection'
    //   502: ldc '--------------------'
    //   504: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   507: aload #5
    //   509: areturn
    //   510: aload #5
    //   512: aload #7
    //   514: invokevirtual toString : ()Ljava/lang/String;
    //   517: putfield c : Ljava/lang/String;
    //   520: aload_1
    //   521: ldc 'content-length'
    //   523: invokevirtual getHeaderField : (Ljava/lang/String;)Ljava/lang/String;
    //   526: astore_2
    //   527: aload_2
    //   528: ifnull -> 402
    //   531: aload #5
    //   533: aload_2
    //   534: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Integer;
    //   537: invokevirtual intValue : ()I
    //   540: putfield b : I
    //   543: goto -> 402
    //   546: astore_2
    //   547: new java/lang/StringBuilder
    //   550: astore #4
    //   552: aload #4
    //   554: invokespecial <init> : ()V
    //   557: ldc 'TapjoyURLConnection'
    //   559: aload #4
    //   561: ldc 'Exception: '
    //   563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: aload_2
    //   567: invokevirtual toString : ()Ljava/lang/String;
    //   570: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual toString : ()Ljava/lang/String;
    //   576: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   579: goto -> 402
    //   582: aload #5
    //   584: aload #4
    //   586: invokevirtual toString : ()Ljava/lang/String;
    //   589: putfield c : Ljava/lang/String;
    //   592: goto -> 402
    //   595: astore_2
    //   596: aload #4
    //   598: astore_1
    //   599: goto -> 259
    // Exception table:
    //   from	to	target	type
    //   12	141	595	java/lang/Exception
    //   141	155	258	java/lang/Exception
    //   160	166	258	java/lang/Exception
    //   166	213	258	java/lang/Exception
    //   213	219	258	java/lang/Exception
    //   224	255	258	java/lang/Exception
    //   290	332	374	java/lang/Exception
    //   332	337	374	java/lang/Exception
    //   341	371	374	java/lang/Exception
    //   510	527	258	java/lang/Exception
    //   531	543	546	java/lang/Exception
    //   547	579	258	java/lang/Exception
    //   582	592	374	java/lang/Exception
  }
  
  public String a(String paramString) {
    return b(paramString, "");
  }
  
  public String b(String paramString) {
    try {
      paramString = paramString.replaceAll(" ", "%20");
      StringBuilder stringBuilder = new StringBuilder();
      this();
      aj.a("TapjoyURLConnection", stringBuilder.append("requestURL: ").append(paramString).toString());
      URL uRL = new URL();
      this(paramString);
      HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
      httpURLConnection.setConnectTimeout(15000);
      httpURLConnection.setReadTimeout(30000);
      String str = httpURLConnection.getHeaderField("content-length");
    } catch (Exception exception) {
      aj.b("TapjoyURLConnection", "Exception: " + exception.toString());
      exception = null;
    } 
    aj.a("TapjoyURLConnection", "content-length: " + exception);
    return (String)exception;
  }
  
  public String b(String paramString1, String paramString2) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: astore_3
    //   4: aload_3
    //   5: invokespecial <init> : ()V
    //   8: aload_3
    //   9: aload_1
    //   10: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_2
    //   14: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: invokevirtual toString : ()Ljava/lang/String;
    //   20: ldc ' '
    //   22: ldc '%20'
    //   24: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   27: astore_2
    //   28: new java/lang/StringBuilder
    //   31: astore_3
    //   32: aload_3
    //   33: invokespecial <init> : ()V
    //   36: ldc 'TapjoyURLConnection'
    //   38: aload_3
    //   39: ldc 'baseURL: '
    //   41: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual toString : ()Ljava/lang/String;
    //   51: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   54: new java/lang/StringBuilder
    //   57: astore_1
    //   58: aload_1
    //   59: invokespecial <init> : ()V
    //   62: ldc 'TapjoyURLConnection'
    //   64: aload_1
    //   65: ldc 'requestURL: '
    //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_2
    //   71: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual toString : ()Ljava/lang/String;
    //   77: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   80: new java/net/URL
    //   83: astore_1
    //   84: aload_1
    //   85: aload_2
    //   86: invokespecial <init> : (Ljava/lang/String;)V
    //   89: aload_1
    //   90: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   93: checkcast java/net/HttpURLConnection
    //   96: astore_3
    //   97: aload_3
    //   98: sipush #15000
    //   101: invokevirtual setConnectTimeout : (I)V
    //   104: aload_3
    //   105: sipush #30000
    //   108: invokevirtual setReadTimeout : (I)V
    //   111: aload_3
    //   112: invokevirtual getResponseMessage : ()Ljava/lang/String;
    //   115: astore_1
    //   116: aload_3
    //   117: invokevirtual connect : ()V
    //   120: new java/io/BufferedReader
    //   123: astore_2
    //   124: new java/io/InputStreamReader
    //   127: astore #4
    //   129: aload #4
    //   131: aload_3
    //   132: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   135: invokespecial <init> : (Ljava/io/InputStream;)V
    //   138: aload_2
    //   139: aload #4
    //   141: invokespecial <init> : (Ljava/io/Reader;)V
    //   144: new java/lang/StringBuilder
    //   147: astore #4
    //   149: aload #4
    //   151: invokespecial <init> : ()V
    //   154: aload_2
    //   155: invokevirtual readLine : ()Ljava/lang/String;
    //   158: astore #5
    //   160: aload #5
    //   162: ifnull -> 226
    //   165: new java/lang/StringBuilder
    //   168: astore_3
    //   169: aload_3
    //   170: invokespecial <init> : ()V
    //   173: aload #4
    //   175: aload_3
    //   176: aload #5
    //   178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: bipush #10
    //   183: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   186: invokevirtual toString : ()Ljava/lang/String;
    //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: goto -> 154
    //   196: astore_2
    //   197: ldc 'TapjoyURLConnection'
    //   199: new java/lang/StringBuilder
    //   202: dup
    //   203: invokespecial <init> : ()V
    //   206: ldc 'Exception: '
    //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: aload_2
    //   212: invokevirtual toString : ()Ljava/lang/String;
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: invokevirtual toString : ()Ljava/lang/String;
    //   221: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   224: aload_1
    //   225: areturn
    //   226: aload #4
    //   228: invokevirtual toString : ()Ljava/lang/String;
    //   231: astore_2
    //   232: aload_2
    //   233: astore_1
    //   234: ldc 'TapjoyURLConnection'
    //   236: ldc '--------------------'
    //   238: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   241: new java/lang/StringBuilder
    //   244: astore_2
    //   245: aload_2
    //   246: invokespecial <init> : ()V
    //   249: ldc 'TapjoyURLConnection'
    //   251: aload_2
    //   252: ldc 'response size: '
    //   254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: aload_1
    //   258: invokevirtual length : ()I
    //   261: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   264: invokevirtual toString : ()Ljava/lang/String;
    //   267: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   270: ldc 'TapjoyURLConnection'
    //   272: ldc 'response: '
    //   274: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   277: new java/lang/StringBuilder
    //   280: astore_2
    //   281: aload_2
    //   282: invokespecial <init> : ()V
    //   285: ldc 'TapjoyURLConnection'
    //   287: aload_2
    //   288: ldc ''
    //   290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload_1
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: invokevirtual toString : ()Ljava/lang/String;
    //   300: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   303: ldc 'TapjoyURLConnection'
    //   305: ldc '--------------------'
    //   307: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   310: goto -> 224
    //   313: astore_2
    //   314: goto -> 197
    //   317: astore_2
    //   318: aconst_null
    //   319: astore_1
    //   320: goto -> 197
    // Exception table:
    //   from	to	target	type
    //   0	116	317	java/lang/Exception
    //   116	154	196	java/lang/Exception
    //   154	160	196	java/lang/Exception
    //   165	193	196	java/lang/Exception
    //   226	232	196	java/lang/Exception
    //   234	310	313	java/lang/Exception
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */