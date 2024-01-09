package com.chartboost.sdk.Libraries;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class e$a extends AsyncTask {
  final e a;
  
  private String b;
  
  private final WeakReference c;
  
  private e$b d;
  
  private String e;
  
  private Bundle f;
  
  public e$a(e parame, ImageView paramImageView, e$b parame$b, String paramString, Bundle paramBundle) {
    this.c = new WeakReference<ImageView>(paramImageView);
    e$c e$c = new e$c(this);
    if (paramImageView != null)
      paramImageView.setImageDrawable((Drawable)e$c); 
    this.e = paramString;
    this.d = parame$b;
    this.f = paramBundle;
  }
  
  protected a$a a(String... paramVarArgs) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #10
    //   3: iconst_0
    //   4: istore #5
    //   6: aload_0
    //   7: aload_1
    //   8: iconst_0
    //   9: aaload
    //   10: putfield b : Ljava/lang/String;
    //   13: aload_0
    //   14: getfield f : Landroid/os/Bundle;
    //   17: ifnull -> 68
    //   20: aload_0
    //   21: getfield f : Landroid/os/Bundle;
    //   24: ldc 'paramNoMemoryCache'
    //   26: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   29: istore_3
    //   30: aload_0
    //   31: getfield a : Lcom/chartboost/sdk/Libraries/e;
    //   34: aload_0
    //   35: getfield e : Ljava/lang/String;
    //   38: invokevirtual a : (Ljava/lang/String;)Lcom/chartboost/sdk/Libraries/a$a;
    //   41: astore_1
    //   42: aload_1
    //   43: astore #6
    //   45: aload_1
    //   46: ifnull -> 91
    //   49: iload_3
    //   50: ifeq -> 73
    //   53: iconst_0
    //   54: istore #4
    //   56: aload_1
    //   57: iload #4
    //   59: invokevirtual a : (Z)V
    //   62: aload_1
    //   63: ifnull -> 97
    //   66: aload_1
    //   67: areturn
    //   68: iconst_0
    //   69: istore_3
    //   70: goto -> 30
    //   73: iconst_1
    //   74: istore #4
    //   76: goto -> 56
    //   79: astore #6
    //   81: aconst_null
    //   82: astore_1
    //   83: aload #6
    //   85: invokevirtual printStackTrace : ()V
    //   88: aload_1
    //   89: astore #6
    //   91: aload #6
    //   93: astore_1
    //   94: goto -> 62
    //   97: invokestatic b : ()Lorg/apache/http/client/HttpClient;
    //   100: astore #6
    //   102: new org/apache/http/client/methods/HttpGet
    //   105: dup
    //   106: aload_0
    //   107: getfield b : Ljava/lang/String;
    //   110: invokespecial <init> : (Ljava/lang/String;)V
    //   113: astore #11
    //   115: aload_1
    //   116: astore #7
    //   118: aload_1
    //   119: astore #9
    //   121: aload_1
    //   122: astore #8
    //   124: aload #6
    //   126: aload #11
    //   128: invokeinterface execute : (Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   133: astore #6
    //   135: aload_1
    //   136: astore #7
    //   138: aload_1
    //   139: astore #9
    //   141: aload_1
    //   142: astore #8
    //   144: aload #6
    //   146: invokeinterface getStatusLine : ()Lorg/apache/http/StatusLine;
    //   151: invokeinterface getStatusCode : ()I
    //   156: istore_2
    //   157: iload_2
    //   158: sipush #200
    //   161: if_icmpeq -> 235
    //   164: aload_1
    //   165: astore #7
    //   167: aload_1
    //   168: astore #9
    //   170: aload_1
    //   171: astore #8
    //   173: new java/lang/StringBuilder
    //   176: astore #6
    //   178: aload_1
    //   179: astore #7
    //   181: aload_1
    //   182: astore #9
    //   184: aload_1
    //   185: astore #8
    //   187: aload #6
    //   189: ldc 'Error '
    //   191: invokespecial <init> : (Ljava/lang/String;)V
    //   194: aload_1
    //   195: astore #7
    //   197: aload_1
    //   198: astore #9
    //   200: aload_1
    //   201: astore #8
    //   203: ldc 'ImageDownloader'
    //   205: aload #6
    //   207: iload_2
    //   208: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   211: ldc ' while retrieving bitmap from '
    //   213: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload_0
    //   217: getfield b : Ljava/lang/String;
    //   220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual toString : ()Ljava/lang/String;
    //   226: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   229: pop
    //   230: aconst_null
    //   231: astore_1
    //   232: goto -> 66
    //   235: aload_1
    //   236: astore #7
    //   238: aload_1
    //   239: astore #9
    //   241: aload_1
    //   242: astore #8
    //   244: aload #6
    //   246: invokeinterface getEntity : ()Lorg/apache/http/HttpEntity;
    //   251: astore #12
    //   253: aload #12
    //   255: ifnull -> 663
    //   258: aload_1
    //   259: astore #6
    //   261: aload #12
    //   263: invokeinterface getContent : ()Ljava/io/InputStream;
    //   268: astore #8
    //   270: aload_1
    //   271: astore #6
    //   273: aload #8
    //   275: astore #10
    //   277: aload_0
    //   278: getfield a : Lcom/chartboost/sdk/Libraries/e;
    //   281: astore #13
    //   283: aload_1
    //   284: astore #6
    //   286: aload #8
    //   288: astore #10
    //   290: aload_0
    //   291: getfield e : Ljava/lang/String;
    //   294: astore #9
    //   296: aload_1
    //   297: astore #6
    //   299: aload #8
    //   301: astore #10
    //   303: new com/chartboost/sdk/Libraries/e$e
    //   306: astore #7
    //   308: aload_1
    //   309: astore #6
    //   311: aload #8
    //   313: astore #10
    //   315: aload #7
    //   317: aload #8
    //   319: invokespecial <init> : (Ljava/io/InputStream;)V
    //   322: aload_1
    //   323: astore #6
    //   325: aload #8
    //   327: astore #10
    //   329: aload #13
    //   331: aload #9
    //   333: aload #7
    //   335: invokevirtual a : (Ljava/lang/String;Lcom/chartboost/sdk/Libraries/e$e;)Z
    //   338: pop
    //   339: aload_1
    //   340: astore #7
    //   342: aload_1
    //   343: astore #6
    //   345: aload #8
    //   347: astore #10
    //   349: aload_0
    //   350: getfield a : Lcom/chartboost/sdk/Libraries/e;
    //   353: aload_0
    //   354: getfield e : Ljava/lang/String;
    //   357: invokevirtual a : (Ljava/lang/String;)Lcom/chartboost/sdk/Libraries/a$a;
    //   360: astore_1
    //   361: aload_1
    //   362: ifnull -> 660
    //   365: iload_3
    //   366: ifeq -> 438
    //   369: iload #5
    //   371: istore #4
    //   373: aload_1
    //   374: astore #7
    //   376: aload_1
    //   377: astore #6
    //   379: aload #8
    //   381: astore #10
    //   383: aload_1
    //   384: iload #4
    //   386: invokevirtual a : (Z)V
    //   389: iload_3
    //   390: ifne -> 660
    //   393: aload_1
    //   394: astore #7
    //   396: aload_1
    //   397: astore #6
    //   399: aload #8
    //   401: astore #10
    //   403: aload_0
    //   404: getfield a : Lcom/chartboost/sdk/Libraries/e;
    //   407: invokestatic a : (Lcom/chartboost/sdk/Libraries/e;)Lcom/chartboost/sdk/Libraries/a;
    //   410: aload_0
    //   411: getfield e : Ljava/lang/String;
    //   414: aload_1
    //   415: invokevirtual a : (Ljava/lang/String;Lcom/chartboost/sdk/Libraries/a$a;)V
    //   418: aload #8
    //   420: ifnull -> 428
    //   423: aload #8
    //   425: invokevirtual close : ()V
    //   428: aload #12
    //   430: invokeinterface consumeContent : ()V
    //   435: goto -> 66
    //   438: iconst_1
    //   439: istore #4
    //   441: goto -> 373
    //   444: astore_1
    //   445: aload #7
    //   447: astore #6
    //   449: aload #8
    //   451: astore #10
    //   453: aload_1
    //   454: invokevirtual printStackTrace : ()V
    //   457: aload #7
    //   459: astore_1
    //   460: goto -> 418
    //   463: astore_1
    //   464: aload #10
    //   466: ifnull -> 486
    //   469: aload #6
    //   471: astore #7
    //   473: aload #6
    //   475: astore #9
    //   477: aload #6
    //   479: astore #8
    //   481: aload #10
    //   483: invokevirtual close : ()V
    //   486: aload #6
    //   488: astore #7
    //   490: aload #6
    //   492: astore #9
    //   494: aload #6
    //   496: astore #8
    //   498: aload #12
    //   500: invokeinterface consumeContent : ()V
    //   505: aload #6
    //   507: astore #7
    //   509: aload #6
    //   511: astore #9
    //   513: aload #6
    //   515: astore #8
    //   517: aload_1
    //   518: athrow
    //   519: astore_1
    //   520: aload_1
    //   521: astore #6
    //   523: aload #7
    //   525: astore_1
    //   526: aload #11
    //   528: invokevirtual abort : ()V
    //   531: ldc 'CBWebImageCache'
    //   533: new java/lang/StringBuilder
    //   536: dup
    //   537: ldc 'I/O error while retrieving bitmap from '
    //   539: invokespecial <init> : (Ljava/lang/String;)V
    //   542: aload_0
    //   543: getfield b : Ljava/lang/String;
    //   546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: invokevirtual toString : ()Ljava/lang/String;
    //   552: aload #6
    //   554: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   557: pop
    //   558: goto -> 435
    //   561: astore_1
    //   562: aload #9
    //   564: astore_1
    //   565: aload #11
    //   567: invokevirtual abort : ()V
    //   570: ldc 'CBWebImageCache'
    //   572: new java/lang/StringBuilder
    //   575: dup
    //   576: ldc 'Incorrect URL: '
    //   578: invokespecial <init> : (Ljava/lang/String;)V
    //   581: aload_0
    //   582: getfield b : Ljava/lang/String;
    //   585: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   588: invokevirtual toString : ()Ljava/lang/String;
    //   591: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   594: pop
    //   595: goto -> 435
    //   598: astore_1
    //   599: aload_1
    //   600: astore #6
    //   602: aload #8
    //   604: astore_1
    //   605: aload #11
    //   607: invokevirtual abort : ()V
    //   610: ldc 'CBWebImageCache'
    //   612: new java/lang/StringBuilder
    //   615: dup
    //   616: ldc 'Error while retrieving bitmap from '
    //   618: invokespecial <init> : (Ljava/lang/String;)V
    //   621: aload_0
    //   622: getfield b : Ljava/lang/String;
    //   625: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: invokevirtual toString : ()Ljava/lang/String;
    //   631: aload #6
    //   633: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   636: pop
    //   637: goto -> 435
    //   640: astore #6
    //   642: goto -> 605
    //   645: astore #6
    //   647: goto -> 565
    //   650: astore #6
    //   652: goto -> 526
    //   655: astore #6
    //   657: goto -> 83
    //   660: goto -> 418
    //   663: goto -> 435
    // Exception table:
    //   from	to	target	type
    //   30	42	79	java/lang/Exception
    //   56	62	655	java/lang/Exception
    //   124	135	519	java/io/IOException
    //   124	135	561	java/lang/IllegalStateException
    //   124	135	598	java/lang/Exception
    //   144	157	519	java/io/IOException
    //   144	157	561	java/lang/IllegalStateException
    //   144	157	598	java/lang/Exception
    //   173	178	519	java/io/IOException
    //   173	178	561	java/lang/IllegalStateException
    //   173	178	598	java/lang/Exception
    //   187	194	519	java/io/IOException
    //   187	194	561	java/lang/IllegalStateException
    //   187	194	598	java/lang/Exception
    //   203	230	519	java/io/IOException
    //   203	230	561	java/lang/IllegalStateException
    //   203	230	598	java/lang/Exception
    //   244	253	519	java/io/IOException
    //   244	253	561	java/lang/IllegalStateException
    //   244	253	598	java/lang/Exception
    //   261	270	463	finally
    //   277	283	463	finally
    //   290	296	463	finally
    //   303	308	463	finally
    //   315	322	463	finally
    //   329	339	463	finally
    //   349	361	444	java/lang/Exception
    //   349	361	463	finally
    //   383	389	444	java/lang/Exception
    //   383	389	463	finally
    //   403	418	444	java/lang/Exception
    //   403	418	463	finally
    //   423	428	650	java/io/IOException
    //   423	428	645	java/lang/IllegalStateException
    //   423	428	640	java/lang/Exception
    //   428	435	650	java/io/IOException
    //   428	435	645	java/lang/IllegalStateException
    //   428	435	640	java/lang/Exception
    //   453	457	463	finally
    //   481	486	519	java/io/IOException
    //   481	486	561	java/lang/IllegalStateException
    //   481	486	598	java/lang/Exception
    //   498	505	519	java/io/IOException
    //   498	505	561	java/lang/IllegalStateException
    //   498	505	598	java/lang/Exception
    //   517	519	519	java/io/IOException
    //   517	519	561	java/lang/IllegalStateException
    //   517	519	598	java/lang/Exception
  }
  
  protected void a(a$a parama$a) {
    if (!isCancelled()) {
      if (parama$a != null)
        try {
          e.a(this.a).a(this.e, parama$a);
        } catch (Exception exception) {
          exception.printStackTrace();
        }  
      if (this.c != null) {
        ImageView imageView = this.c.get();
        if (this == e.a(imageView))
          imageView.setImageBitmap(parama$a.b()); 
      } 
      if (this.d != null)
        this.d.a(parama$a, this.f); 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\e$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */