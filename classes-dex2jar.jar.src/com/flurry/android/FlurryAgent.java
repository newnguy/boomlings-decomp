package com.flurry.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ViewGroup;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class FlurryAgent implements LocationListener {
  private static Map Z;
  
  private static final String[] a = new String[] { "9774d56d682e549c", "dead00beef" };
  
  private static volatile String b = null;
  
  private static volatile String c = "http://data.flurry.com/aap.do";
  
  private static volatile String d = "https://data.flurry.com/aap.do";
  
  private static final FlurryAgent e = new FlurryAgent();
  
  private static long f = 10000L;
  
  private static boolean g = true;
  
  private static boolean h = false;
  
  private static boolean i = false;
  
  private static boolean j = true;
  
  private static Criteria k = null;
  
  private static boolean l = false;
  
  private static AtomicInteger n = new AtomicInteger(0);
  
  private static AtomicInteger o = new AtomicInteger(0);
  
  private List A;
  
  private LocationManager B;
  
  private String C;
  
  private Map D = new HashMap<Object, Object>();
  
  private boolean E;
  
  private long F;
  
  private List G = new ArrayList();
  
  private long H;
  
  private long I;
  
  private long J;
  
  private String K = "";
  
  private String L = "";
  
  private byte M = -1;
  
  private String N = "";
  
  private byte O = -1;
  
  private Long P;
  
  private int Q;
  
  private Location R;
  
  private Map S = new HashMap<Object, Object>();
  
  private List T = new ArrayList();
  
  private boolean U;
  
  private int V;
  
  private List W = new ArrayList();
  
  private int X;
  
  private Map Y;
  
  private Map aa;
  
  private bo ab = new bo();
  
  private be ac = new be();
  
  private ah ad = new ah();
  
  private final Handler m;
  
  private File p;
  
  private File q = null;
  
  private File r = null;
  
  private volatile boolean s = false;
  
  private volatile boolean t = false;
  
  private long u;
  
  private Map v = new WeakHashMap<Object, Object>();
  
  private String w;
  
  private String x;
  
  private String y;
  
  private boolean z = true;
  
  private FlurryAgent() {
    HandlerThread handlerThread = new HandlerThread("FlurryAgent");
    handlerThread.start();
    this.m = new Handler(handlerThread.getLooper());
  }
  
  private static double a(double paramDouble) {
    return Math.round(paramDouble * 1000.0D) / 1000.0D;
  }
  
  private static String a(File paramFile) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: new java/io/FileInputStream
    //   6: astore #4
    //   8: aload #4
    //   10: aload_0
    //   11: invokespecial <init> : (Ljava/io/File;)V
    //   14: aload #4
    //   16: astore_2
    //   17: new java/lang/StringBuffer
    //   20: astore_0
    //   21: aload #4
    //   23: astore_2
    //   24: aload_0
    //   25: invokespecial <init> : ()V
    //   28: aload #4
    //   30: astore_2
    //   31: sipush #1024
    //   34: newarray byte
    //   36: astore #6
    //   38: aload #4
    //   40: astore_2
    //   41: aload #4
    //   43: aload #6
    //   45: invokevirtual read : ([B)I
    //   48: istore_1
    //   49: iload_1
    //   50: ifle -> 115
    //   53: aload #4
    //   55: astore_2
    //   56: new java/lang/String
    //   59: astore_3
    //   60: aload #4
    //   62: astore_2
    //   63: aload_3
    //   64: aload #6
    //   66: iconst_0
    //   67: iload_1
    //   68: invokespecial <init> : ([BII)V
    //   71: aload #4
    //   73: astore_2
    //   74: aload_0
    //   75: aload_3
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   79: pop
    //   80: goto -> 38
    //   83: astore_3
    //   84: aload #4
    //   86: astore_2
    //   87: ldc 'FlurryAgent'
    //   89: ldc 'Error when loading persistent file'
    //   91: aload_3
    //   92: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   95: pop
    //   96: aload #4
    //   98: invokestatic a : (Ljava/io/Closeable;)V
    //   101: aload #5
    //   103: astore_2
    //   104: aload_0
    //   105: ifnull -> 113
    //   108: aload_0
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: astore_2
    //   113: aload_2
    //   114: areturn
    //   115: aload #4
    //   117: invokestatic a : (Ljava/io/Closeable;)V
    //   120: goto -> 101
    //   123: astore_0
    //   124: aconst_null
    //   125: astore_2
    //   126: aload_2
    //   127: invokestatic a : (Ljava/io/Closeable;)V
    //   130: aload_0
    //   131: athrow
    //   132: astore_0
    //   133: goto -> 126
    //   136: astore_3
    //   137: aconst_null
    //   138: astore_0
    //   139: aconst_null
    //   140: astore #4
    //   142: goto -> 84
    //   145: astore_3
    //   146: aconst_null
    //   147: astore_0
    //   148: goto -> 84
    // Exception table:
    //   from	to	target	type
    //   3	14	136	java/lang/Throwable
    //   3	14	123	finally
    //   17	21	145	java/lang/Throwable
    //   17	21	132	finally
    //   24	28	145	java/lang/Throwable
    //   24	28	132	finally
    //   31	38	83	java/lang/Throwable
    //   31	38	132	finally
    //   41	49	83	java/lang/Throwable
    //   41	49	132	finally
    //   56	60	83	java/lang/Throwable
    //   56	60	132	finally
    //   63	71	83	java/lang/Throwable
    //   63	71	132	finally
    //   74	80	83	java/lang/Throwable
    //   74	80	132	finally
    //   87	96	132	finally
  }
  
  private void a(Context paramContext) {
    if (!l) {
      if (!this.ab.g()) {
        bm.a("FlurryAgent", "Initializing Flurry Ads");
        p p = new p();
        p.a = this.w;
        p.b = this.ac;
        p.c = this.ad;
        p.d = this.K;
        p.e = this.L;
        this.ab.a(paramContext, p);
        this.ab.l();
        if (Z != null)
          this.ab.c(Z); 
        bm.a("FlurryAgent", "Flurry Ads initialized");
      } 
      this.ab.a(paramContext, this.H, this.I);
      l = true;
    } 
  }
  
  private void a(Context paramContext, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield w : Ljava/lang/String;
    //   6: ifnull -> 64
    //   9: aload_0
    //   10: getfield w : Ljava/lang/String;
    //   13: aload_2
    //   14: invokevirtual equals : (Ljava/lang/Object;)Z
    //   17: ifne -> 64
    //   20: new java/lang/StringBuilder
    //   23: astore #6
    //   25: aload #6
    //   27: invokespecial <init> : ()V
    //   30: ldc 'FlurryAgent'
    //   32: aload #6
    //   34: ldc_w 'onStartSession called with different api keys: '
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: getfield w : Ljava/lang/String;
    //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc_w ' and '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_2
    //   54: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual toString : ()Ljava/lang/String;
    //   60: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   63: pop
    //   64: aload_0
    //   65: getfield v : Ljava/util/Map;
    //   68: aload_1
    //   69: aload_1
    //   70: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   75: checkcast android/content/Context
    //   78: ifnull -> 90
    //   81: ldc 'FlurryAgent'
    //   83: ldc_w 'onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context'
    //   86: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   89: pop
    //   90: aload_0
    //   91: getfield s : Z
    //   94: ifne -> 907
    //   97: ldc 'FlurryAgent'
    //   99: ldc_w 'Initializing Flurry session'
    //   102: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   105: pop
    //   106: getstatic com/flurry/android/FlurryAgent.n : Ljava/util/concurrent/atomic/AtomicInteger;
    //   109: iconst_0
    //   110: invokevirtual set : (I)V
    //   113: getstatic com/flurry/android/FlurryAgent.o : Ljava/util/concurrent/atomic/AtomicInteger;
    //   116: iconst_0
    //   117: invokevirtual set : (I)V
    //   120: aload_0
    //   121: aload_2
    //   122: putfield w : Ljava/lang/String;
    //   125: new java/lang/StringBuilder
    //   128: astore_2
    //   129: aload_2
    //   130: invokespecial <init> : ()V
    //   133: aload_0
    //   134: aload_1
    //   135: aload_2
    //   136: ldc_w '.flurryagent.'
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_0
    //   143: getfield w : Ljava/lang/String;
    //   146: invokevirtual hashCode : ()I
    //   149: bipush #16
    //   151: invokestatic toString : (II)Ljava/lang/String;
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual toString : ()Ljava/lang/String;
    //   160: invokevirtual getFileStreamPath : (Ljava/lang/String;)Ljava/io/File;
    //   163: putfield q : Ljava/io/File;
    //   166: aload_0
    //   167: aload_1
    //   168: ldc_w '.flurryb.'
    //   171: invokevirtual getFileStreamPath : (Ljava/lang/String;)Ljava/io/File;
    //   174: putfield p : Ljava/io/File;
    //   177: aload_0
    //   178: aload_1
    //   179: ldc_w '.flurryinstallreceiver.'
    //   182: invokevirtual getFileStreamPath : (Ljava/lang/String;)Ljava/io/File;
    //   185: putfield r : Ljava/io/File;
    //   188: getstatic com/flurry/android/FlurryAgent.j : Z
    //   191: ifeq -> 206
    //   194: new com/flurry/android/FlurryAgent$FlurryDefaultExceptionHandler
    //   197: astore_2
    //   198: aload_2
    //   199: invokespecial <init> : ()V
    //   202: aload_2
    //   203: invokestatic setDefaultUncaughtExceptionHandler : (Ljava/lang/Thread$UncaughtExceptionHandler;)V
    //   206: aload_1
    //   207: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   210: astore_2
    //   211: aload_0
    //   212: getfield y : Ljava/lang/String;
    //   215: ifnonnull -> 226
    //   218: aload_0
    //   219: aload_2
    //   220: invokestatic d : (Landroid/content/Context;)Ljava/lang/String;
    //   223: putfield y : Ljava/lang/String;
    //   226: aload_2
    //   227: invokevirtual getPackageName : ()Ljava/lang/String;
    //   230: astore #7
    //   232: aload_0
    //   233: getfield x : Ljava/lang/String;
    //   236: ifnull -> 296
    //   239: aload_0
    //   240: getfield x : Ljava/lang/String;
    //   243: aload #7
    //   245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   248: ifne -> 296
    //   251: new java/lang/StringBuilder
    //   254: astore #6
    //   256: aload #6
    //   258: invokespecial <init> : ()V
    //   261: ldc 'FlurryAgent'
    //   263: aload #6
    //   265: ldc_w 'onStartSession called from different application packages: '
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload_0
    //   272: getfield x : Ljava/lang/String;
    //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: ldc_w ' and '
    //   281: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: aload #7
    //   286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual toString : ()Ljava/lang/String;
    //   292: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   295: pop
    //   296: aload_0
    //   297: aload #7
    //   299: putfield x : Ljava/lang/String;
    //   302: invokestatic elapsedRealtime : ()J
    //   305: lstore_3
    //   306: lload_3
    //   307: aload_0
    //   308: getfield u : J
    //   311: lsub
    //   312: getstatic com/flurry/android/FlurryAgent.f : J
    //   315: lcmp
    //   316: ifle -> 846
    //   319: ldc 'FlurryAgent'
    //   321: ldc_w 'New session'
    //   324: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   327: pop
    //   328: aload_0
    //   329: invokestatic currentTimeMillis : ()J
    //   332: putfield H : J
    //   335: aload_0
    //   336: lload_3
    //   337: putfield I : J
    //   340: aload_0
    //   341: ldc2_w -1
    //   344: putfield J : J
    //   347: aload_0
    //   348: ldc ''
    //   350: putfield N : Ljava/lang/String;
    //   353: aload_0
    //   354: iconst_0
    //   355: putfield Q : I
    //   358: aload_0
    //   359: aconst_null
    //   360: putfield R : Landroid/location/Location;
    //   363: aload_0
    //   364: invokestatic getDefault : ()Ljava/util/TimeZone;
    //   367: invokevirtual getID : ()Ljava/lang/String;
    //   370: putfield L : Ljava/lang/String;
    //   373: new java/lang/StringBuilder
    //   376: astore #6
    //   378: aload #6
    //   380: invokespecial <init> : ()V
    //   383: aload_0
    //   384: aload #6
    //   386: invokestatic getDefault : ()Ljava/util/Locale;
    //   389: invokevirtual getLanguage : ()Ljava/lang/String;
    //   392: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: ldc_w '_'
    //   398: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: invokestatic getDefault : ()Ljava/util/Locale;
    //   404: invokevirtual getCountry : ()Ljava/lang/String;
    //   407: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: invokevirtual toString : ()Ljava/lang/String;
    //   413: putfield K : Ljava/lang/String;
    //   416: new java/util/HashMap
    //   419: astore #6
    //   421: aload #6
    //   423: invokespecial <init> : ()V
    //   426: aload_0
    //   427: aload #6
    //   429: putfield S : Ljava/util/Map;
    //   432: new java/util/ArrayList
    //   435: astore #6
    //   437: aload #6
    //   439: invokespecial <init> : ()V
    //   442: aload_0
    //   443: aload #6
    //   445: putfield T : Ljava/util/List;
    //   448: aload_0
    //   449: iconst_1
    //   450: putfield U : Z
    //   453: new java/util/ArrayList
    //   456: astore #6
    //   458: aload #6
    //   460: invokespecial <init> : ()V
    //   463: aload_0
    //   464: aload #6
    //   466: putfield W : Ljava/util/List;
    //   469: aload_0
    //   470: iconst_0
    //   471: putfield V : I
    //   474: aload_0
    //   475: iconst_0
    //   476: putfield X : I
    //   479: getstatic com/flurry/android/FlurryAgent.l : Z
    //   482: ifeq -> 501
    //   485: aload_0
    //   486: getfield ab : Lcom/flurry/android/bo;
    //   489: aload_1
    //   490: aload_0
    //   491: getfield H : J
    //   494: aload_0
    //   495: getfield I : J
    //   498: invokevirtual a : (Landroid/content/Context;JJ)V
    //   501: aload_0
    //   502: getfield z : Z
    //   505: istore #5
    //   507: new com/flurry/android/e
    //   510: astore #6
    //   512: aload #6
    //   514: aload_0
    //   515: aload_2
    //   516: iload #5
    //   518: invokespecial <init> : (Lcom/flurry/android/FlurryAgent;Landroid/content/Context;Z)V
    //   521: aload_0
    //   522: aload #6
    //   524: invokespecial a : (Lcom/flurry/android/an;)V
    //   527: aload_1
    //   528: instanceof android/app/Activity
    //   531: ifeq -> 902
    //   534: aload_1
    //   535: checkcast android/app/Activity
    //   538: invokevirtual getIntent : ()Landroid/content/Intent;
    //   541: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   544: astore_2
    //   545: aload_2
    //   546: ifnull -> 902
    //   549: new java/lang/StringBuilder
    //   552: astore_1
    //   553: aload_1
    //   554: invokespecial <init> : ()V
    //   557: ldc 'FlurryAgent'
    //   559: aload_1
    //   560: ldc_w 'Launch Options Bundle is present '
    //   563: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: aload_2
    //   567: invokevirtual toString : ()Ljava/lang/String;
    //   570: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual toString : ()Ljava/lang/String;
    //   576: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   579: pop
    //   580: new java/util/HashMap
    //   583: astore_1
    //   584: aload_1
    //   585: invokespecial <init> : ()V
    //   588: aload_0
    //   589: aload_1
    //   590: putfield aa : Ljava/util/Map;
    //   593: aload_2
    //   594: invokevirtual keySet : ()Ljava/util/Set;
    //   597: invokeinterface iterator : ()Ljava/util/Iterator;
    //   602: astore #6
    //   604: aload #6
    //   606: invokeinterface hasNext : ()Z
    //   611: ifeq -> 902
    //   614: aload #6
    //   616: invokeinterface next : ()Ljava/lang/Object;
    //   621: checkcast java/lang/String
    //   624: astore #7
    //   626: aload_0
    //   627: getfield aa : Ljava/util/Map;
    //   630: aload #7
    //   632: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   637: ifnonnull -> 666
    //   640: aload_0
    //   641: getfield aa : Ljava/util/Map;
    //   644: astore_1
    //   645: new java/util/ArrayList
    //   648: astore #8
    //   650: aload #8
    //   652: invokespecial <init> : ()V
    //   655: aload_1
    //   656: aload #7
    //   658: aload #8
    //   660: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   665: pop
    //   666: aload_2
    //   667: aload #7
    //   669: invokevirtual get : (Ljava/lang/String;)Ljava/lang/Object;
    //   672: astore_1
    //   673: aload_1
    //   674: invokevirtual getClass : ()Ljava/lang/Class;
    //   677: ldc ''
    //   679: invokevirtual isInstance : (Ljava/lang/Object;)Z
    //   682: ifeq -> 763
    //   685: aload_2
    //   686: aload #7
    //   688: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   691: astore_1
    //   692: aload_0
    //   693: getfield aa : Ljava/util/Map;
    //   696: aload #7
    //   698: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   703: checkcast java/util/List
    //   706: aload_1
    //   707: invokeinterface add : (Ljava/lang/Object;)Z
    //   712: pop
    //   713: new java/lang/StringBuilder
    //   716: astore #8
    //   718: aload #8
    //   720: invokespecial <init> : ()V
    //   723: ldc 'FlurryAgent'
    //   725: aload #8
    //   727: ldc_w 'Launch options Key: '
    //   730: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   733: aload #7
    //   735: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: ldc_w '. Its value: '
    //   741: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: aload_1
    //   745: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   748: invokevirtual toString : ()Ljava/lang/String;
    //   751: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   754: pop
    //   755: goto -> 604
    //   758: astore_1
    //   759: aload_0
    //   760: monitorexit
    //   761: aload_1
    //   762: athrow
    //   763: aload_1
    //   764: invokevirtual getClass : ()Ljava/lang/Class;
    //   767: iconst_0
    //   768: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   771: invokevirtual isInstance : (Ljava/lang/Object;)Z
    //   774: ifeq -> 792
    //   777: aload_2
    //   778: aload #7
    //   780: invokevirtual getInt : (Ljava/lang/String;)I
    //   783: pop
    //   784: iconst_0
    //   785: invokestatic toString : (I)Ljava/lang/String;
    //   788: astore_1
    //   789: goto -> 692
    //   792: aload_1
    //   793: invokevirtual getClass : ()Ljava/lang/Class;
    //   796: lconst_0
    //   797: invokestatic valueOf : (J)Ljava/lang/Long;
    //   800: invokevirtual isInstance : (Ljava/lang/Object;)Z
    //   803: ifeq -> 819
    //   806: aload_2
    //   807: aload #7
    //   809: invokevirtual getLong : (Ljava/lang/String;)J
    //   812: invokestatic toString : (J)Ljava/lang/String;
    //   815: astore_1
    //   816: goto -> 692
    //   819: aload_1
    //   820: invokevirtual getClass : ()Ljava/lang/Class;
    //   823: iconst_0
    //   824: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   827: invokevirtual isInstance : (Ljava/lang/Object;)Z
    //   830: ifeq -> 910
    //   833: aload_2
    //   834: aload #7
    //   836: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   839: invokestatic toString : (Z)Ljava/lang/String;
    //   842: astore_1
    //   843: goto -> 692
    //   846: ldc 'FlurryAgent'
    //   848: ldc_w 'Continuing previous session'
    //   851: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   854: pop
    //   855: aload_0
    //   856: getfield G : Ljava/util/List;
    //   859: invokeinterface isEmpty : ()Z
    //   864: ifne -> 888
    //   867: aload_0
    //   868: getfield G : Ljava/util/List;
    //   871: aload_0
    //   872: getfield G : Ljava/util/List;
    //   875: invokeinterface size : ()I
    //   880: iconst_1
    //   881: isub
    //   882: invokeinterface remove : (I)Ljava/lang/Object;
    //   887: pop
    //   888: getstatic com/flurry/android/FlurryAgent.l : Z
    //   891: ifeq -> 902
    //   894: aload_0
    //   895: getfield ab : Lcom/flurry/android/bo;
    //   898: aload_1
    //   899: invokevirtual a : (Landroid/content/Context;)V
    //   902: aload_0
    //   903: iconst_1
    //   904: putfield s : Z
    //   907: aload_0
    //   908: monitorexit
    //   909: return
    //   910: ldc ''
    //   912: astore_1
    //   913: goto -> 692
    // Exception table:
    //   from	to	target	type
    //   2	64	758	finally
    //   64	90	758	finally
    //   90	206	758	finally
    //   206	226	758	finally
    //   226	296	758	finally
    //   296	501	758	finally
    //   501	545	758	finally
    //   549	604	758	finally
    //   604	666	758	finally
    //   666	692	758	finally
    //   692	755	758	finally
    //   763	789	758	finally
    //   792	816	758	finally
    //   819	843	758	finally
    //   846	888	758	finally
    //   888	902	758	finally
    //   902	907	758	finally
  }
  
  private void a(Context paramContext, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull -> 31
    //   6: aload_0
    //   7: getfield v : Ljava/util/Map;
    //   10: aload_1
    //   11: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   16: checkcast android/content/Context
    //   19: ifnonnull -> 31
    //   22: ldc 'FlurryAgent'
    //   24: ldc_w 'onEndSession called without context from corresponding onStartSession'
    //   27: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   30: pop
    //   31: aload_0
    //   32: getfield s : Z
    //   35: ifeq -> 205
    //   38: aload_0
    //   39: getfield v : Ljava/util/Map;
    //   42: invokeinterface isEmpty : ()Z
    //   47: ifeq -> 205
    //   50: ldc 'FlurryAgent'
    //   52: ldc_w 'Ending session'
    //   55: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   58: pop
    //   59: aload_0
    //   60: invokespecial n : ()V
    //   63: aload_1
    //   64: ifnonnull -> 208
    //   67: aconst_null
    //   68: astore #5
    //   70: aload_1
    //   71: ifnull -> 135
    //   74: aload #5
    //   76: invokevirtual getPackageName : ()Ljava/lang/String;
    //   79: astore #6
    //   81: aload_0
    //   82: getfield x : Ljava/lang/String;
    //   85: aload #6
    //   87: invokevirtual equals : (Ljava/lang/Object;)Z
    //   90: ifne -> 135
    //   93: new java/lang/StringBuilder
    //   96: astore_1
    //   97: aload_1
    //   98: invokespecial <init> : ()V
    //   101: ldc 'FlurryAgent'
    //   103: aload_1
    //   104: ldc_w 'onEndSession called from different application package, expected: '
    //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: aload_0
    //   111: getfield x : Ljava/lang/String;
    //   114: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: ldc_w ' actual: '
    //   120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload #6
    //   125: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual toString : ()Ljava/lang/String;
    //   131: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: invokestatic elapsedRealtime : ()J
    //   138: lstore_3
    //   139: aload_0
    //   140: lload_3
    //   141: putfield u : J
    //   144: aload_0
    //   145: lload_3
    //   146: aload_0
    //   147: getfield I : J
    //   150: lsub
    //   151: putfield J : J
    //   154: aload_0
    //   155: invokespecial o : ()Ljava/lang/String;
    //   158: ifnonnull -> 170
    //   161: ldc 'FlurryAgent'
    //   163: ldc_w 'Not creating report because of bad Android ID or generated ID is null'
    //   166: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   169: pop
    //   170: new com/flurry/android/a
    //   173: astore_1
    //   174: aload_1
    //   175: aload_0
    //   176: iload_2
    //   177: aload #5
    //   179: invokespecial <init> : (Lcom/flurry/android/FlurryAgent;ZLandroid/content/Context;)V
    //   182: aload_0
    //   183: aload_1
    //   184: invokespecial a : (Lcom/flurry/android/an;)V
    //   187: getstatic com/flurry/android/FlurryAgent.l : Z
    //   190: ifeq -> 200
    //   193: aload_0
    //   194: getfield ab : Lcom/flurry/android/bo;
    //   197: invokevirtual a : ()V
    //   200: aload_0
    //   201: iconst_0
    //   202: putfield s : Z
    //   205: aload_0
    //   206: monitorexit
    //   207: return
    //   208: aload_1
    //   209: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   212: astore #5
    //   214: goto -> 70
    //   217: astore_1
    //   218: aload_0
    //   219: monitorexit
    //   220: aload_1
    //   221: athrow
    // Exception table:
    //   from	to	target	type
    //   6	31	217	finally
    //   31	63	217	finally
    //   74	135	217	finally
    //   135	170	217	finally
    //   170	200	217	finally
    //   200	205	217	finally
    //   208	214	217	finally
  }
  
  private void a(an paraman) {
    this.m.post(paraman);
  }
  
  private void a(DataInputStream paramDataInputStream) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_1
    //   5: invokevirtual readUnsignedShort : ()I
    //   8: istore_3
    //   9: iload_3
    //   10: iconst_2
    //   11: if_icmple -> 82
    //   14: new java/lang/StringBuilder
    //   17: astore_1
    //   18: aload_1
    //   19: invokespecial <init> : ()V
    //   22: ldc 'FlurryAgent'
    //   24: aload_1
    //   25: ldc_w 'Unknown agent file version: '
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: iload_3
    //   32: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   35: invokevirtual toString : ()Ljava/lang/String;
    //   38: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   41: pop
    //   42: new java/io/IOException
    //   45: astore #4
    //   47: new java/lang/StringBuilder
    //   50: astore_1
    //   51: aload_1
    //   52: invokespecial <init> : ()V
    //   55: aload #4
    //   57: aload_1
    //   58: ldc_w 'Unknown agent file version: '
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: iload_3
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: invokevirtual toString : ()Ljava/lang/String;
    //   71: invokespecial <init> : (Ljava/lang/String;)V
    //   74: aload #4
    //   76: athrow
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: iload_3
    //   83: iconst_2
    //   84: if_icmplt -> 359
    //   87: aload_1
    //   88: invokevirtual readUTF : ()Ljava/lang/String;
    //   91: astore #4
    //   93: new java/lang/StringBuilder
    //   96: astore #5
    //   98: aload #5
    //   100: invokespecial <init> : ()V
    //   103: ldc 'FlurryAgent'
    //   105: aload #5
    //   107: ldc_w 'Loading API key: '
    //   110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: aload_0
    //   114: getfield w : Ljava/lang/String;
    //   117: invokestatic c : (Ljava/lang/String;)Ljava/lang/String;
    //   120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   129: pop
    //   130: aload #4
    //   132: aload_0
    //   133: getfield w : Ljava/lang/String;
    //   136: invokevirtual equals : (Ljava/lang/Object;)Z
    //   139: ifeq -> 308
    //   142: aload_1
    //   143: invokevirtual readUTF : ()Ljava/lang/String;
    //   146: astore #5
    //   148: aload_0
    //   149: invokespecial o : ()Ljava/lang/String;
    //   152: ifnonnull -> 187
    //   155: new java/lang/StringBuilder
    //   158: astore #4
    //   160: aload #4
    //   162: invokespecial <init> : ()V
    //   165: ldc 'FlurryAgent'
    //   167: aload #4
    //   169: ldc_w 'Loading phoneId: '
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload #5
    //   177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual toString : ()Ljava/lang/String;
    //   183: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: aload_0
    //   188: aload #5
    //   190: invokespecial d : (Ljava/lang/String;)V
    //   193: aload_0
    //   194: aload_1
    //   195: invokevirtual readBoolean : ()Z
    //   198: putfield E : Z
    //   201: aload_0
    //   202: aload_1
    //   203: invokevirtual readLong : ()J
    //   206: putfield F : J
    //   209: ldc 'FlurryAgent'
    //   211: ldc_w 'Loading session reports'
    //   214: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   217: pop
    //   218: aload_1
    //   219: invokevirtual readUnsignedShort : ()I
    //   222: istore_3
    //   223: iload_3
    //   224: ifeq -> 291
    //   227: iload_3
    //   228: newarray byte
    //   230: astore #4
    //   232: aload_1
    //   233: aload #4
    //   235: invokevirtual readFully : ([B)V
    //   238: aload_0
    //   239: getfield G : Ljava/util/List;
    //   242: iconst_0
    //   243: aload #4
    //   245: invokeinterface add : (ILjava/lang/Object;)V
    //   250: new java/lang/StringBuilder
    //   253: astore #4
    //   255: aload #4
    //   257: invokespecial <init> : ()V
    //   260: aload #4
    //   262: ldc_w 'Session report added: '
    //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: astore #4
    //   270: iinc #2, 1
    //   273: ldc 'FlurryAgent'
    //   275: aload #4
    //   277: iload_2
    //   278: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   281: invokevirtual toString : ()Ljava/lang/String;
    //   284: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   287: pop
    //   288: goto -> 218
    //   291: ldc 'FlurryAgent'
    //   293: ldc_w 'Persistent file loaded'
    //   296: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   299: pop
    //   300: aload_0
    //   301: iconst_1
    //   302: putfield t : Z
    //   305: aload_0
    //   306: monitorexit
    //   307: return
    //   308: new java/lang/StringBuilder
    //   311: astore_1
    //   312: aload_1
    //   313: invokespecial <init> : ()V
    //   316: ldc 'FlurryAgent'
    //   318: aload_1
    //   319: ldc_w 'Api keys do not match, old: '
    //   322: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: aload #4
    //   327: invokestatic c : (Ljava/lang/String;)Ljava/lang/String;
    //   330: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: ldc_w ', new: '
    //   336: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: aload_0
    //   340: getfield w : Ljava/lang/String;
    //   343: invokestatic c : (Ljava/lang/String;)Ljava/lang/String;
    //   346: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   355: pop
    //   356: goto -> 305
    //   359: new java/lang/StringBuilder
    //   362: astore_1
    //   363: aload_1
    //   364: invokespecial <init> : ()V
    //   367: ldc 'FlurryAgent'
    //   369: aload_1
    //   370: ldc_w 'Deleting old file version: '
    //   373: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: iload_3
    //   377: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   380: invokevirtual toString : ()Ljava/lang/String;
    //   383: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   386: pop
    //   387: goto -> 305
    // Exception table:
    //   from	to	target	type
    //   4	9	77	finally
    //   14	77	77	finally
    //   87	187	77	finally
    //   187	218	77	finally
    //   218	223	77	finally
    //   227	270	77	finally
    //   273	288	77	finally
    //   291	305	77	finally
    //   308	356	77	finally
    //   359	387	77	finally
  }
  
  private void a(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield T : Ljava/util/List;
    //   6: invokeinterface iterator : ()Ljava/util/Iterator;
    //   11: astore_2
    //   12: aload_2
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 51
    //   21: aload_2
    //   22: invokeinterface next : ()Ljava/lang/Object;
    //   27: checkcast com/flurry/android/aq
    //   30: astore_3
    //   31: aload_3
    //   32: aload_1
    //   33: invokevirtual a : (Ljava/lang/String;)Z
    //   36: ifeq -> 12
    //   39: aload_3
    //   40: invokestatic elapsedRealtime : ()J
    //   43: aload_0
    //   44: getfield I : J
    //   47: lsub
    //   48: invokevirtual a : (J)V
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	54	finally
    //   12	51	54	finally
  }
  
  private void a(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield W : Ljava/util/List;
    //   6: ifnonnull -> 40
    //   9: new java/lang/StringBuilder
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: ldc 'FlurryAgent'
    //   19: aload_2
    //   20: ldc_w 'onError called before onStartSession.  Error: '
    //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_1
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual toString : ()Ljava/lang/String;
    //   33: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: aload_1
    //   41: ifnull -> 165
    //   44: ldc_w 'uncaught'
    //   47: aload_1
    //   48: invokevirtual equals : (Ljava/lang/Object;)Z
    //   51: ifeq -> 165
    //   54: iconst_1
    //   55: istore #4
    //   57: aload_0
    //   58: aload_0
    //   59: getfield Q : I
    //   62: iconst_1
    //   63: iadd
    //   64: putfield Q : I
    //   67: aload_0
    //   68: getfield W : Ljava/util/List;
    //   71: invokeinterface size : ()I
    //   76: bipush #50
    //   78: if_icmpge -> 171
    //   81: invokestatic currentTimeMillis : ()J
    //   84: lstore #5
    //   86: new com/flurry/android/bb
    //   89: astore #7
    //   91: aload #7
    //   93: getstatic com/flurry/android/FlurryAgent.o : Ljava/util/concurrent/atomic/AtomicInteger;
    //   96: invokevirtual incrementAndGet : ()I
    //   99: lload #5
    //   101: invokestatic valueOf : (J)Ljava/lang/Long;
    //   104: invokevirtual longValue : ()J
    //   107: aload_1
    //   108: aload_2
    //   109: aload_3
    //   110: invokespecial <init> : (IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   113: aload_0
    //   114: getfield W : Ljava/util/List;
    //   117: aload #7
    //   119: invokeinterface add : (Ljava/lang/Object;)Z
    //   124: pop
    //   125: new java/lang/StringBuilder
    //   128: astore_1
    //   129: aload_1
    //   130: invokespecial <init> : ()V
    //   133: ldc 'FlurryAgent'
    //   135: aload_1
    //   136: ldc_w 'Error logged: '
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload #7
    //   144: invokevirtual b : ()Ljava/lang/String;
    //   147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual toString : ()Ljava/lang/String;
    //   153: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: goto -> 37
    //   160: astore_1
    //   161: aload_0
    //   162: monitorexit
    //   163: aload_1
    //   164: athrow
    //   165: iconst_0
    //   166: istore #4
    //   168: goto -> 57
    //   171: iload #4
    //   173: ifeq -> 286
    //   176: iconst_0
    //   177: istore #4
    //   179: iload #4
    //   181: aload_0
    //   182: getfield W : Ljava/util/List;
    //   185: invokeinterface size : ()I
    //   190: if_icmpge -> 37
    //   193: aload_0
    //   194: getfield W : Ljava/util/List;
    //   197: iload #4
    //   199: invokeinterface get : (I)Ljava/lang/Object;
    //   204: checkcast com/flurry/android/bb
    //   207: astore #7
    //   209: aload #7
    //   211: invokevirtual b : ()Ljava/lang/String;
    //   214: ifnull -> 280
    //   217: ldc_w 'uncaught'
    //   220: aload #7
    //   222: invokevirtual b : ()Ljava/lang/String;
    //   225: invokevirtual equals : (Ljava/lang/Object;)Z
    //   228: ifne -> 280
    //   231: invokestatic currentTimeMillis : ()J
    //   234: lstore #5
    //   236: new com/flurry/android/bb
    //   239: astore #7
    //   241: aload #7
    //   243: getstatic com/flurry/android/FlurryAgent.o : Ljava/util/concurrent/atomic/AtomicInteger;
    //   246: invokevirtual incrementAndGet : ()I
    //   249: lload #5
    //   251: invokestatic valueOf : (J)Ljava/lang/Long;
    //   254: invokevirtual longValue : ()J
    //   257: aload_1
    //   258: aload_2
    //   259: aload_3
    //   260: invokespecial <init> : (IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   263: aload_0
    //   264: getfield W : Ljava/util/List;
    //   267: iload #4
    //   269: aload #7
    //   271: invokeinterface set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   276: pop
    //   277: goto -> 37
    //   280: iinc #4, 1
    //   283: goto -> 179
    //   286: ldc 'FlurryAgent'
    //   288: ldc_w 'Max errors logged. No more errors logged.'
    //   291: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   294: pop
    //   295: goto -> 37
    // Exception table:
    //   from	to	target	type
    //   2	37	160	finally
    //   44	54	160	finally
    //   57	157	160	finally
    //   179	277	160	finally
    //   286	295	160	finally
  }
  
  private void a(String paramString, Map paramMap, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield T : Ljava/util/List;
    //   6: ifnonnull -> 40
    //   9: new java/lang/StringBuilder
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: ldc 'FlurryAgent'
    //   19: aload_2
    //   20: ldc_w 'onEvent called before onStartSession.  Event: '
    //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_1
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual toString : ()Ljava/lang/String;
    //   33: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: invokestatic elapsedRealtime : ()J
    //   43: lstore #5
    //   45: aload_0
    //   46: getfield I : J
    //   49: lstore #7
    //   51: aload_1
    //   52: invokestatic a : (Ljava/lang/String;)Ljava/lang/String;
    //   55: astore #9
    //   57: aload #9
    //   59: invokevirtual length : ()I
    //   62: ifeq -> 37
    //   65: aload_0
    //   66: getfield S : Ljava/util/Map;
    //   69: aload #9
    //   71: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   76: checkcast com/flurry/android/l
    //   79: astore_1
    //   80: aload_1
    //   81: ifnonnull -> 292
    //   84: aload_0
    //   85: getfield S : Ljava/util/Map;
    //   88: invokeinterface size : ()I
    //   93: bipush #100
    //   95: if_icmpge -> 252
    //   98: new com/flurry/android/l
    //   101: astore_1
    //   102: aload_1
    //   103: invokespecial <init> : ()V
    //   106: aload_1
    //   107: iconst_1
    //   108: putfield a : I
    //   111: aload_0
    //   112: getfield S : Ljava/util/Map;
    //   115: aload #9
    //   117: aload_1
    //   118: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   123: pop
    //   124: new java/lang/StringBuilder
    //   127: astore_1
    //   128: aload_1
    //   129: invokespecial <init> : ()V
    //   132: ldc 'FlurryAgent'
    //   134: aload_1
    //   135: ldc_w 'Event count incremented: '
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload #9
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual toString : ()Ljava/lang/String;
    //   149: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: getstatic com/flurry/android/FlurryAgent.g : Z
    //   156: ifeq -> 458
    //   159: aload_0
    //   160: getfield T : Ljava/util/List;
    //   163: invokeinterface size : ()I
    //   168: sipush #200
    //   171: if_icmpge -> 458
    //   174: aload_0
    //   175: getfield V : I
    //   178: sipush #16000
    //   181: if_icmpge -> 458
    //   184: aload_2
    //   185: ifnonnull -> 466
    //   188: invokestatic emptyMap : ()Ljava/util/Map;
    //   191: astore_1
    //   192: aload_1
    //   193: invokeinterface size : ()I
    //   198: bipush #10
    //   200: if_icmple -> 334
    //   203: ldc 'FlurryAgent'
    //   205: invokestatic a : (Ljava/lang/String;)Z
    //   208: ifeq -> 37
    //   211: new java/lang/StringBuilder
    //   214: astore_2
    //   215: aload_2
    //   216: invokespecial <init> : ()V
    //   219: ldc 'FlurryAgent'
    //   221: aload_2
    //   222: ldc_w 'MaxEventParams exceeded: '
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_1
    //   229: invokeinterface size : ()I
    //   234: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   237: invokevirtual toString : ()Ljava/lang/String;
    //   240: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   243: pop
    //   244: goto -> 37
    //   247: astore_1
    //   248: aload_0
    //   249: monitorexit
    //   250: aload_1
    //   251: athrow
    //   252: ldc 'FlurryAgent'
    //   254: invokestatic a : (Ljava/lang/String;)Z
    //   257: ifeq -> 153
    //   260: new java/lang/StringBuilder
    //   263: astore_1
    //   264: aload_1
    //   265: invokespecial <init> : ()V
    //   268: ldc 'FlurryAgent'
    //   270: aload_1
    //   271: ldc_w 'Too many different events. Event not counted: '
    //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: aload #9
    //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: invokevirtual toString : ()Ljava/lang/String;
    //   285: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   288: pop
    //   289: goto -> 153
    //   292: aload_1
    //   293: aload_1
    //   294: getfield a : I
    //   297: iconst_1
    //   298: iadd
    //   299: putfield a : I
    //   302: new java/lang/StringBuilder
    //   305: astore_1
    //   306: aload_1
    //   307: invokespecial <init> : ()V
    //   310: ldc 'FlurryAgent'
    //   312: aload_1
    //   313: ldc_w 'Event count incremented: '
    //   316: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: aload #9
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: invokevirtual toString : ()Ljava/lang/String;
    //   327: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   330: pop
    //   331: goto -> 153
    //   334: new com/flurry/android/aq
    //   337: astore_2
    //   338: aload_2
    //   339: getstatic com/flurry/android/FlurryAgent.n : Ljava/util/concurrent/atomic/AtomicInteger;
    //   342: invokevirtual incrementAndGet : ()I
    //   345: aload #9
    //   347: aload_1
    //   348: lload #5
    //   350: lload #7
    //   352: lsub
    //   353: iload_3
    //   354: invokespecial <init> : (ILjava/lang/String;Ljava/util/Map;JZ)V
    //   357: aload_2
    //   358: invokevirtual a : ()[B
    //   361: arraylength
    //   362: aload_0
    //   363: getfield V : I
    //   366: iadd
    //   367: sipush #16000
    //   370: if_icmpgt -> 434
    //   373: aload_0
    //   374: getfield T : Ljava/util/List;
    //   377: aload_2
    //   378: invokeinterface add : (Ljava/lang/Object;)Z
    //   383: pop
    //   384: aload_0
    //   385: getfield V : I
    //   388: istore #4
    //   390: aload_0
    //   391: aload_2
    //   392: invokevirtual a : ()[B
    //   395: arraylength
    //   396: iload #4
    //   398: iadd
    //   399: putfield V : I
    //   402: new java/lang/StringBuilder
    //   405: astore_1
    //   406: aload_1
    //   407: invokespecial <init> : ()V
    //   410: ldc 'FlurryAgent'
    //   412: aload_1
    //   413: ldc_w 'Logged event: '
    //   416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: aload #9
    //   421: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: invokevirtual toString : ()Ljava/lang/String;
    //   427: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   430: pop
    //   431: goto -> 37
    //   434: aload_0
    //   435: sipush #16000
    //   438: putfield V : I
    //   441: aload_0
    //   442: iconst_0
    //   443: putfield U : Z
    //   446: ldc 'FlurryAgent'
    //   448: ldc_w 'Event Log size exceeded. No more event details logged.'
    //   451: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   454: pop
    //   455: goto -> 37
    //   458: aload_0
    //   459: iconst_0
    //   460: putfield U : Z
    //   463: goto -> 37
    //   466: aload_2
    //   467: astore_1
    //   468: goto -> 192
    // Exception table:
    //   from	to	target	type
    //   2	37	247	finally
    //   40	80	247	finally
    //   84	153	247	finally
    //   153	184	247	finally
    //   188	192	247	finally
    //   192	244	247	finally
    //   252	289	247	finally
    //   292	331	247	finally
    //   334	431	247	finally
    //   434	455	247	finally
    //   458	463	247	finally
  }
  
  static boolean a() {
    return (e.t && e.s);
  }
  
  private boolean a(byte[] paramArrayOfbyte) {
    boolean bool2;
    String str = j();
    if (str == null)
      return false; 
    try {
      bool2 = a(paramArrayOfbyte, str);
    } catch (Exception exception) {
      bm.a("FlurryAgent", "Sending report exception: " + exception.getMessage());
      bool2 = false;
    } 
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = bool2;
      if (b == null) {
        bool1 = bool2;
        if (h) {
          bool1 = bool2;
          if (!i)
            synchronized (e) {
              i = true;
              String str1 = j();
              if (str1 == null)
                return false; 
              try {
                bool1 = a(paramArrayOfbyte, str1);
              } catch (Exception exception) {
                bool1 = bool2;
              } 
            }  
        } 
      } 
    } 
    return bool1;
  }
  
  private boolean a(byte[] paramArrayOfbyte, String paramString) {
    boolean bool2 = true;
    boolean bool1 = true;
    if ("local".equals(paramString))
      return bool1; 
    bm.a("FlurryAgent", "Sending report to: " + paramString);
    ByteArrayEntity byteArrayEntity = new ByteArrayEntity(paramArrayOfbyte);
    byteArrayEntity.setContentType("application/octet-stream");
    HttpPost httpPost = new HttpPost(paramString);
    httpPost.setEntity((HttpEntity)byteArrayEntity);
    BasicHttpParams basicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 15000);
    httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
    int i = this.ac.a((HttpParams)basicHttpParams).execute((HttpUriRequest)httpPost).getStatusLine().getStatusCode();
    /* monitor enter ThisExpression{ObjectType{com/flurry/android/FlurryAgent}} */
    if (i == 200) {
      try {
        bm.a("FlurryAgent", "Report successful");
        this.E = true;
        this.G.removeAll(this.A);
        bool1 = bool2;
        this.A = null;
        /* monitor exit ThisExpression{ObjectType{com/flurry/android/FlurryAgent}} */
      } finally {}
      return bool1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    this();
    bm.a("FlurryAgent", stringBuilder.append("Report failed. HTTP response: ").append(i).toString());
    bool1 = false;
    this.A = null;
    /* monitor exit ThisExpression{ObjectType{com/flurry/android/FlurryAgent}} */
  }
  
  static bo b() {
    return e.ab;
  }
  
  private static Map b(String paramString) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    String[] arrayOfString = paramString.split("&");
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String[] arrayOfString1 = arrayOfString[b].split("=");
      if (arrayOfString1.length != 2) {
        bm.a("FlurryAgent", "Invalid referrer Element: " + arrayOfString[b] + " in referrer tag " + paramString);
      } else {
        String str1 = URLDecoder.decode(arrayOfString1[0]);
        String str2 = URLDecoder.decode(arrayOfString1[1]);
        if (hashMap.get(str1) == null)
          hashMap.put(str1, new ArrayList()); 
        ((List<String>)hashMap.get(str1)).add(str2);
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (hashMap.get("utm_source") == null)
      stringBuilder.append("Campaign Source is missing.\n"); 
    if (hashMap.get("utm_medium") == null)
      stringBuilder.append("Campaign Medium is missing.\n"); 
    if (hashMap.get("utm_campaign") == null)
      stringBuilder.append("Campaign Name is missing.\n"); 
    if (stringBuilder.length() > 0)
      Log.w("Detected missing referrer keys", stringBuilder.toString()); 
    return hashMap;
  }
  
  private void b(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial c : (Landroid/content/Context;)Ljava/lang/String;
    //   7: astore #11
    //   9: aload_0
    //   10: getfield q : Ljava/io/File;
    //   13: invokevirtual exists : ()Z
    //   16: ifeq -> 390
    //   19: new java/lang/StringBuilder
    //   22: astore #8
    //   24: aload #8
    //   26: invokespecial <init> : ()V
    //   29: ldc 'FlurryAgent'
    //   31: aload #8
    //   33: ldc_w 'loading persistent data: '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_0
    //   40: getfield q : Ljava/io/File;
    //   43: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokestatic c : (Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: new java/io/FileInputStream
    //   59: astore #8
    //   61: aload #8
    //   63: aload_0
    //   64: getfield q : Ljava/io/File;
    //   67: invokespecial <init> : (Ljava/io/File;)V
    //   70: new java/io/DataInputStream
    //   73: astore #9
    //   75: aload #9
    //   77: aload #8
    //   79: invokespecial <init> : (Ljava/io/InputStream;)V
    //   82: aload #9
    //   84: astore #8
    //   86: aload #9
    //   88: invokevirtual readUnsignedShort : ()I
    //   91: ldc_w 46586
    //   94: if_icmpne -> 307
    //   97: aload #9
    //   99: astore #8
    //   101: aload_0
    //   102: aload #9
    //   104: invokespecial a : (Ljava/io/DataInputStream;)V
    //   107: aload #9
    //   109: invokestatic a : (Ljava/io/Closeable;)V
    //   112: aload_0
    //   113: getfield t : Z
    //   116: ifne -> 138
    //   119: aload_0
    //   120: getfield q : Ljava/io/File;
    //   123: invokevirtual delete : ()Z
    //   126: ifeq -> 363
    //   129: ldc 'FlurryAgent'
    //   131: ldc_w 'Deleted persistence file'
    //   134: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   137: pop
    //   138: aload_0
    //   139: getfield t : Z
    //   142: ifne -> 163
    //   145: aload_0
    //   146: iconst_0
    //   147: putfield E : Z
    //   150: aload_0
    //   151: aload_0
    //   152: getfield H : J
    //   155: putfield F : J
    //   158: aload_0
    //   159: iconst_1
    //   160: putfield t : Z
    //   163: aload #11
    //   165: ifnonnull -> 414
    //   168: invokestatic random : ()D
    //   171: invokestatic doubleToLongBits : (D)J
    //   174: lstore #6
    //   176: invokestatic nanoTime : ()J
    //   179: lstore_2
    //   180: aload_0
    //   181: getfield w : Ljava/lang/String;
    //   184: invokevirtual hashCode : ()I
    //   187: bipush #37
    //   189: imul
    //   190: i2l
    //   191: lstore #4
    //   193: new java/lang/StringBuilder
    //   196: astore #8
    //   198: aload #8
    //   200: invokespecial <init> : ()V
    //   203: aload #8
    //   205: ldc_w 'ID'
    //   208: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: lload #6
    //   213: ldc2_w 37
    //   216: lload_2
    //   217: lload #4
    //   219: ladd
    //   220: lmul
    //   221: ladd
    //   222: bipush #16
    //   224: invokestatic toString : (JI)Ljava/lang/String;
    //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual toString : ()Ljava/lang/String;
    //   233: astore #8
    //   235: ldc 'FlurryAgent'
    //   237: ldc_w 'Generated id'
    //   240: invokestatic c : (Ljava/lang/String;Ljava/lang/String;)I
    //   243: pop
    //   244: aload_0
    //   245: aload #8
    //   247: invokespecial d : (Ljava/lang/String;)V
    //   250: aload_0
    //   251: getfield ab : Lcom/flurry/android/bo;
    //   254: aload_0
    //   255: getfield C : Ljava/lang/String;
    //   258: invokevirtual a : (Ljava/lang/String;)V
    //   261: aload_0
    //   262: getfield ab : Lcom/flurry/android/bo;
    //   265: aload_0
    //   266: getfield D : Ljava/util/Map;
    //   269: invokevirtual a : (Ljava/util/Map;)V
    //   272: aload #8
    //   274: ldc_w 'AND'
    //   277: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   280: ifne -> 300
    //   283: aload_0
    //   284: getfield p : Ljava/io/File;
    //   287: invokevirtual exists : ()Z
    //   290: ifne -> 300
    //   293: aload_0
    //   294: aload_1
    //   295: aload #8
    //   297: invokespecial b : (Landroid/content/Context;Ljava/lang/String;)V
    //   300: aload_0
    //   301: invokespecial l : ()V
    //   304: aload_0
    //   305: monitorexit
    //   306: return
    //   307: aload #9
    //   309: astore #8
    //   311: ldc 'FlurryAgent'
    //   313: ldc_w 'Unexpected file type'
    //   316: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   319: pop
    //   320: goto -> 107
    //   323: astore #10
    //   325: aload #9
    //   327: astore #8
    //   329: ldc 'FlurryAgent'
    //   331: ldc 'Error when loading persistent file'
    //   333: aload #10
    //   335: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   338: pop
    //   339: aload #9
    //   341: invokestatic a : (Ljava/io/Closeable;)V
    //   344: goto -> 112
    //   347: astore_1
    //   348: aload_0
    //   349: monitorexit
    //   350: aload_1
    //   351: athrow
    //   352: astore_1
    //   353: aconst_null
    //   354: astore #8
    //   356: aload #8
    //   358: invokestatic a : (Ljava/io/Closeable;)V
    //   361: aload_1
    //   362: athrow
    //   363: ldc 'FlurryAgent'
    //   365: ldc_w 'Cannot delete persistence file'
    //   368: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)I
    //   371: pop
    //   372: goto -> 138
    //   375: astore #8
    //   377: ldc 'FlurryAgent'
    //   379: ldc ''
    //   381: aload #8
    //   383: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   386: pop
    //   387: goto -> 138
    //   390: ldc 'FlurryAgent'
    //   392: ldc_w 'Agent cache file doesn't exist.'
    //   395: invokestatic c : (Ljava/lang/String;Ljava/lang/String;)I
    //   398: pop
    //   399: goto -> 138
    //   402: astore_1
    //   403: goto -> 356
    //   406: astore #10
    //   408: aconst_null
    //   409: astore #9
    //   411: goto -> 325
    //   414: aload #11
    //   416: astore #8
    //   418: goto -> 244
    // Exception table:
    //   from	to	target	type
    //   2	56	347	finally
    //   56	82	406	java/lang/Throwable
    //   56	82	352	finally
    //   86	97	323	java/lang/Throwable
    //   86	97	402	finally
    //   101	107	323	java/lang/Throwable
    //   101	107	402	finally
    //   107	112	347	finally
    //   112	138	375	java/lang/Throwable
    //   112	138	347	finally
    //   138	163	347	finally
    //   168	244	347	finally
    //   244	300	347	finally
    //   300	304	347	finally
    //   311	320	323	java/lang/Throwable
    //   311	320	402	finally
    //   329	339	402	finally
    //   339	344	347	finally
    //   356	363	347	finally
    //   363	372	375	java/lang/Throwable
    //   363	372	347	finally
    //   377	387	347	finally
    //   390	399	347	finally
  }
  
  private void b(Context paramContext, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: ldc_w '.flurryb.'
    //   7: invokevirtual getFileStreamPath : (Ljava/lang/String;)Ljava/io/File;
    //   10: putfield p : Ljava/io/File;
    //   13: aload_0
    //   14: getfield p : Ljava/io/File;
    //   17: invokestatic a : (Ljava/io/File;)Z
    //   20: istore_3
    //   21: iload_3
    //   22: ifne -> 28
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: new java/io/FileOutputStream
    //   31: astore_1
    //   32: aload_1
    //   33: aload_0
    //   34: getfield p : Ljava/io/File;
    //   37: invokespecial <init> : (Ljava/io/File;)V
    //   40: new java/io/DataOutputStream
    //   43: astore #4
    //   45: aload #4
    //   47: aload_1
    //   48: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   51: aload #4
    //   53: astore_1
    //   54: aload #4
    //   56: iconst_1
    //   57: invokevirtual writeInt : (I)V
    //   60: aload #4
    //   62: astore_1
    //   63: aload #4
    //   65: aload_2
    //   66: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   69: aload #4
    //   71: invokestatic a : (Ljava/io/Closeable;)V
    //   74: goto -> 25
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: astore #5
    //   84: aconst_null
    //   85: astore_2
    //   86: aload_2
    //   87: astore_1
    //   88: ldc 'FlurryAgent'
    //   90: ldc_w 'Error when saving b file'
    //   93: aload #5
    //   95: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   98: pop
    //   99: aload_2
    //   100: invokestatic a : (Ljava/io/Closeable;)V
    //   103: goto -> 25
    //   106: astore_1
    //   107: aconst_null
    //   108: astore #4
    //   110: aload_1
    //   111: astore_2
    //   112: aload #4
    //   114: invokestatic a : (Ljava/io/Closeable;)V
    //   117: aload_2
    //   118: athrow
    //   119: astore_2
    //   120: aload_1
    //   121: astore #4
    //   123: goto -> 112
    //   126: astore #5
    //   128: aload #4
    //   130: astore_2
    //   131: goto -> 86
    // Exception table:
    //   from	to	target	type
    //   2	21	77	finally
    //   28	51	82	java/lang/Throwable
    //   28	51	106	finally
    //   54	60	126	java/lang/Throwable
    //   54	60	119	finally
    //   63	69	126	java/lang/Throwable
    //   63	69	119	finally
    //   69	74	77	finally
    //   88	99	119	finally
    //   99	103	77	finally
    //   112	119	77	finally
  }
  
  static String c() {
    return e.w;
  }
  
  private String c(Context paramContext) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_0
    //   4: invokespecial o : ()Ljava/lang/String;
    //   7: astore #8
    //   9: aload #8
    //   11: ifnull -> 19
    //   14: aload #8
    //   16: astore_1
    //   17: aload_1
    //   18: areturn
    //   19: aload_1
    //   20: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   23: ldc_w 'android_id'
    //   26: invokestatic getString : (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   29: astore #6
    //   31: iload #4
    //   33: istore_2
    //   34: aload #6
    //   36: ifnull -> 64
    //   39: iload #4
    //   41: istore_2
    //   42: aload #6
    //   44: invokevirtual length : ()I
    //   47: ifle -> 64
    //   50: aload #6
    //   52: ldc_w 'null'
    //   55: invokevirtual equals : (Ljava/lang/Object;)Z
    //   58: ifeq -> 93
    //   61: iload #4
    //   63: istore_2
    //   64: iload_2
    //   65: ifeq -> 137
    //   68: new java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial <init> : ()V
    //   75: ldc_w 'AND'
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload #6
    //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: invokevirtual toString : ()Ljava/lang/String;
    //   89: astore_1
    //   90: goto -> 17
    //   93: getstatic com/flurry/android/FlurryAgent.a : [Ljava/lang/String;
    //   96: astore #7
    //   98: aload #7
    //   100: arraylength
    //   101: istore #5
    //   103: iconst_0
    //   104: istore_3
    //   105: iload_3
    //   106: iload #5
    //   108: if_icmpge -> 132
    //   111: iload #4
    //   113: istore_2
    //   114: aload #6
    //   116: aload #7
    //   118: iload_3
    //   119: aaload
    //   120: invokevirtual equals : (Ljava/lang/Object;)Z
    //   123: ifne -> 64
    //   126: iinc #3, 1
    //   129: goto -> 105
    //   132: iconst_1
    //   133: istore_2
    //   134: goto -> 64
    //   137: aload_1
    //   138: ldc_w '.flurryb.'
    //   141: invokevirtual getFileStreamPath : (Ljava/lang/String;)Ljava/io/File;
    //   144: astore #6
    //   146: aload #8
    //   148: astore_1
    //   149: aload #6
    //   151: invokevirtual exists : ()Z
    //   154: ifeq -> 17
    //   157: new java/io/FileInputStream
    //   160: astore_1
    //   161: aload_1
    //   162: aload #6
    //   164: invokespecial <init> : (Ljava/io/File;)V
    //   167: new java/io/DataInputStream
    //   170: astore #6
    //   172: aload #6
    //   174: aload_1
    //   175: invokespecial <init> : (Ljava/io/InputStream;)V
    //   178: aload #6
    //   180: astore_1
    //   181: aload #6
    //   183: invokevirtual readInt : ()I
    //   186: pop
    //   187: aload #6
    //   189: astore_1
    //   190: aload #6
    //   192: invokevirtual readUTF : ()Ljava/lang/String;
    //   195: astore #7
    //   197: aload #7
    //   199: astore_1
    //   200: aload #6
    //   202: invokestatic a : (Ljava/io/Closeable;)V
    //   205: goto -> 17
    //   208: astore #7
    //   210: aconst_null
    //   211: astore #6
    //   213: aload #6
    //   215: astore_1
    //   216: ldc 'FlurryAgent'
    //   218: ldc_w 'Error when loading b file'
    //   221: aload #7
    //   223: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   226: pop
    //   227: aload #6
    //   229: invokestatic a : (Ljava/io/Closeable;)V
    //   232: aload #8
    //   234: astore_1
    //   235: goto -> 17
    //   238: astore #6
    //   240: aconst_null
    //   241: astore_1
    //   242: aload_1
    //   243: invokestatic a : (Ljava/io/Closeable;)V
    //   246: aload #6
    //   248: athrow
    //   249: astore #6
    //   251: goto -> 242
    //   254: astore #7
    //   256: goto -> 213
    // Exception table:
    //   from	to	target	type
    //   157	178	208	java/lang/Throwable
    //   157	178	238	finally
    //   181	187	254	java/lang/Throwable
    //   181	187	249	finally
    //   190	197	254	java/lang/Throwable
    //   190	197	249	finally
    //   216	227	249	finally
  }
  
  private static String c(String paramString) {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
      if (paramString.length() > 4) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b = 0; b < paramString.length() - 4; b++)
          stringBuilder.append('*'); 
        stringBuilder.append(paramString.substring(paramString.length() - 4));
        str = stringBuilder.toString();
      } 
    } 
    return str;
  }
  
  public static void clearTargetingKeywords() {
    if (l)
      e.ab.f(); 
  }
  
  public static void clearUserCookies() {
    if (l) {
      e.ab.h();
      return;
    } 
    Z = null;
  }
  
  private static String d(Context paramContext) {
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if (packageInfo.versionName != null)
        return packageInfo.versionName; 
      if (packageInfo.versionCode != 0)
        return Integer.toString(packageInfo.versionCode); 
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
    return "Unknown";
  }
  
  private void d(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull -> 11
    //   6: aload_0
    //   7: aload_1
    //   8: putfield C : Ljava/lang/String;
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   6	11	14	finally
  }
  
  public static void displayAd(Context paramContext, String paramString, ViewGroup paramViewGroup) {
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to displayAd was null.");
      return;
    } 
    if (paramString == null) {
      bm.b("FlurryAgent", "Ad space name passed to displayAd was null.");
      return;
    } 
    if (paramString.length() == 0) {
      bm.b("FlurryAgent", "Ad space name passed to displayAd was empty.");
      return;
    } 
    e.a(paramContext);
    try {
      e.ab.b(paramContext, paramString, paramViewGroup);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  private Location e(Context paramContext) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'android.permission.ACCESS_FINE_LOCATION'
    //   4: invokevirtual checkCallingOrSelfPermission : (Ljava/lang/String;)I
    //   7: ifeq -> 20
    //   10: aload_1
    //   11: ldc_w 'android.permission.ACCESS_COARSE_LOCATION'
    //   14: invokevirtual checkCallingOrSelfPermission : (Ljava/lang/String;)I
    //   17: ifne -> 108
    //   20: aload_1
    //   21: ldc_w 'location'
    //   24: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   27: checkcast android/location/LocationManager
    //   30: astore_1
    //   31: aload_0
    //   32: monitorenter
    //   33: aload_0
    //   34: getfield B : Landroid/location/LocationManager;
    //   37: ifnonnull -> 95
    //   40: aload_0
    //   41: aload_1
    //   42: putfield B : Landroid/location/LocationManager;
    //   45: aload_0
    //   46: monitorexit
    //   47: getstatic com/flurry/android/FlurryAgent.k : Landroid/location/Criteria;
    //   50: astore_3
    //   51: aload_3
    //   52: astore_2
    //   53: aload_3
    //   54: ifnonnull -> 65
    //   57: new android/location/Criteria
    //   60: dup
    //   61: invokespecial <init> : ()V
    //   64: astore_2
    //   65: aload_1
    //   66: aload_2
    //   67: iconst_1
    //   68: invokevirtual getBestProvider : (Landroid/location/Criteria;Z)Ljava/lang/String;
    //   71: astore_2
    //   72: aload_2
    //   73: ifnull -> 108
    //   76: aload_1
    //   77: aload_2
    //   78: lconst_0
    //   79: fconst_0
    //   80: aload_0
    //   81: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   84: invokevirtual requestLocationUpdates : (Ljava/lang/String;JFLandroid/location/LocationListener;Landroid/os/Looper;)V
    //   87: aload_1
    //   88: aload_2
    //   89: invokevirtual getLastKnownLocation : (Ljava/lang/String;)Landroid/location/Location;
    //   92: astore_1
    //   93: aload_1
    //   94: areturn
    //   95: aload_0
    //   96: getfield B : Landroid/location/LocationManager;
    //   99: astore_1
    //   100: goto -> 45
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: athrow
    //   108: aconst_null
    //   109: astore_1
    //   110: goto -> 93
    // Exception table:
    //   from	to	target	type
    //   33	45	103	finally
    //   45	47	103	finally
    //   95	100	103	finally
    //   104	106	103	finally
  }
  
  public static void enableTestAds(boolean paramBoolean) {
    e.ab.a(paramBoolean);
  }
  
  public static void endTimedEvent(String paramString) {
    try {
      e.a(paramString);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "Failed to signify the end of event: " + paramString, throwable);
    } 
  }
  
  private static byte[] f(Context paramContext) {
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
      TelephonyManager telephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (telephonyManager != null) {
        String str = telephonyManager.getDeviceId();
        if (str != null && str.trim().length() > 0)
          try {
            byte[] arrayOfByte = ac.c(str);
            if (arrayOfByte != null && arrayOfByte.length == 20)
              return arrayOfByte; 
            StringBuilder stringBuilder = new StringBuilder();
            this();
            bm.b("FlurryAgent", stringBuilder.append("sha1 is not 20 bytes long: ").append(Arrays.toString(arrayOfByte)).toString());
          } catch (Exception exception) {} 
      } 
    } 
    return null;
  }
  
  public static void fetchAd(Context paramContext, String paramString, ViewGroup paramViewGroup, FlurryAdSize paramFlurryAdSize) {
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to fetchAd was null.");
      return;
    } 
    if (paramString == null) {
      bm.b("FlurryAgent", "Ad space name passed to fetchAd was null.");
      return;
    } 
    if (paramString.length() == 0) {
      bm.b("FlurryAgent", "Ad space name passed to fetchAd was empty.");
      return;
    } 
    e.a(paramContext);
    try {
      e.ab.a(paramContext, paramString, paramViewGroup, paramFlurryAdSize);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  private void g() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore #4
    //   5: new java/io/ByteArrayOutputStream
    //   8: astore #6
    //   10: aload #6
    //   12: invokespecial <init> : ()V
    //   15: new java/io/DataOutputStream
    //   18: astore #5
    //   20: aload #5
    //   22: aload #6
    //   24: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   27: aload #5
    //   29: iconst_1
    //   30: invokevirtual writeShort : (I)V
    //   33: aload #5
    //   35: aload_0
    //   36: getfield y : Ljava/lang/String;
    //   39: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   42: aload #5
    //   44: aload_0
    //   45: getfield H : J
    //   48: invokevirtual writeLong : (J)V
    //   51: aload #5
    //   53: aload_0
    //   54: getfield J : J
    //   57: invokevirtual writeLong : (J)V
    //   60: aload #5
    //   62: lconst_0
    //   63: invokevirtual writeLong : (J)V
    //   66: aload #5
    //   68: aload_0
    //   69: getfield K : Ljava/lang/String;
    //   72: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   75: aload #5
    //   77: aload_0
    //   78: getfield L : Ljava/lang/String;
    //   81: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   84: aload #5
    //   86: aload_0
    //   87: getfield M : B
    //   90: invokevirtual writeByte : (I)V
    //   93: aload_0
    //   94: getfield N : Ljava/lang/String;
    //   97: ifnonnull -> 279
    //   100: ldc ''
    //   102: astore #4
    //   104: aload #5
    //   106: aload #4
    //   108: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   111: aload_0
    //   112: getfield R : Landroid/location/Location;
    //   115: ifnonnull -> 288
    //   118: aload #5
    //   120: iconst_0
    //   121: invokevirtual writeBoolean : (Z)V
    //   124: aload #5
    //   126: aload_0
    //   127: getfield X : I
    //   130: invokevirtual writeInt : (I)V
    //   133: aload #5
    //   135: iconst_m1
    //   136: invokevirtual writeByte : (I)V
    //   139: aload #5
    //   141: iconst_m1
    //   142: invokevirtual writeByte : (I)V
    //   145: aload #5
    //   147: aload_0
    //   148: getfield O : B
    //   151: invokevirtual writeByte : (I)V
    //   154: aload_0
    //   155: getfield P : Ljava/lang/Long;
    //   158: ifnonnull -> 356
    //   161: aload #5
    //   163: iconst_0
    //   164: invokevirtual writeBoolean : (Z)V
    //   167: aload #5
    //   169: aload_0
    //   170: getfield S : Ljava/util/Map;
    //   173: invokeinterface size : ()I
    //   178: invokevirtual writeShort : (I)V
    //   181: aload_0
    //   182: getfield S : Ljava/util/Map;
    //   185: invokeinterface entrySet : ()Ljava/util/Set;
    //   190: invokeinterface iterator : ()Ljava/util/Iterator;
    //   195: astore #4
    //   197: aload #4
    //   199: invokeinterface hasNext : ()Z
    //   204: ifeq -> 377
    //   207: aload #4
    //   209: invokeinterface next : ()Ljava/lang/Object;
    //   214: checkcast java/util/Map$Entry
    //   217: astore #7
    //   219: aload #5
    //   221: aload #7
    //   223: invokeinterface getKey : ()Ljava/lang/Object;
    //   228: checkcast java/lang/String
    //   231: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   234: aload #5
    //   236: aload #7
    //   238: invokeinterface getValue : ()Ljava/lang/Object;
    //   243: checkcast com/flurry/android/l
    //   246: getfield a : I
    //   249: invokevirtual writeInt : (I)V
    //   252: goto -> 197
    //   255: astore #6
    //   257: aload #5
    //   259: astore #4
    //   261: ldc 'FlurryAgent'
    //   263: ldc ''
    //   265: aload #6
    //   267: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   270: pop
    //   271: aload #4
    //   273: invokestatic a : (Ljava/io/Closeable;)V
    //   276: aload_0
    //   277: monitorexit
    //   278: return
    //   279: aload_0
    //   280: getfield N : Ljava/lang/String;
    //   283: astore #4
    //   285: goto -> 104
    //   288: aload #5
    //   290: iconst_1
    //   291: invokevirtual writeBoolean : (Z)V
    //   294: aload #5
    //   296: aload_0
    //   297: getfield R : Landroid/location/Location;
    //   300: invokevirtual getLatitude : ()D
    //   303: invokestatic a : (D)D
    //   306: invokevirtual writeDouble : (D)V
    //   309: aload #5
    //   311: aload_0
    //   312: getfield R : Landroid/location/Location;
    //   315: invokevirtual getLongitude : ()D
    //   318: invokestatic a : (D)D
    //   321: invokevirtual writeDouble : (D)V
    //   324: aload #5
    //   326: aload_0
    //   327: getfield R : Landroid/location/Location;
    //   330: invokevirtual getAccuracy : ()F
    //   333: invokevirtual writeFloat : (F)V
    //   336: goto -> 124
    //   339: astore #4
    //   341: aload #5
    //   343: invokestatic a : (Ljava/io/Closeable;)V
    //   346: aload #4
    //   348: athrow
    //   349: astore #4
    //   351: aload_0
    //   352: monitorexit
    //   353: aload #4
    //   355: athrow
    //   356: aload #5
    //   358: iconst_1
    //   359: invokevirtual writeBoolean : (Z)V
    //   362: aload #5
    //   364: aload_0
    //   365: getfield P : Ljava/lang/Long;
    //   368: invokevirtual longValue : ()J
    //   371: invokevirtual writeLong : (J)V
    //   374: goto -> 167
    //   377: aload #5
    //   379: aload_0
    //   380: getfield T : Ljava/util/List;
    //   383: invokeinterface size : ()I
    //   388: invokevirtual writeShort : (I)V
    //   391: aload_0
    //   392: getfield T : Ljava/util/List;
    //   395: invokeinterface iterator : ()Ljava/util/Iterator;
    //   400: astore #4
    //   402: aload #4
    //   404: invokeinterface hasNext : ()Z
    //   409: ifeq -> 433
    //   412: aload #5
    //   414: aload #4
    //   416: invokeinterface next : ()Ljava/lang/Object;
    //   421: checkcast com/flurry/android/aq
    //   424: invokevirtual a : ()[B
    //   427: invokevirtual write : ([B)V
    //   430: goto -> 402
    //   433: aload #5
    //   435: aload_0
    //   436: getfield U : Z
    //   439: invokevirtual writeBoolean : (Z)V
    //   442: iconst_0
    //   443: istore_3
    //   444: iconst_0
    //   445: istore_1
    //   446: iconst_0
    //   447: istore_2
    //   448: iload_3
    //   449: aload_0
    //   450: getfield W : Ljava/util/List;
    //   453: invokeinterface size : ()I
    //   458: if_icmpge -> 497
    //   461: iload_2
    //   462: aload_0
    //   463: getfield W : Ljava/util/List;
    //   466: iload_3
    //   467: invokeinterface get : (I)Ljava/lang/Object;
    //   472: checkcast com/flurry/android/bb
    //   475: invokevirtual a : ()[B
    //   478: arraylength
    //   479: iadd
    //   480: istore_2
    //   481: iload_2
    //   482: sipush #16000
    //   485: if_icmpgt -> 497
    //   488: iinc #1, 1
    //   491: iinc #3, 1
    //   494: goto -> 448
    //   497: aload #5
    //   499: aload_0
    //   500: getfield Q : I
    //   503: invokevirtual writeInt : (I)V
    //   506: aload #5
    //   508: iload_1
    //   509: invokevirtual writeShort : (I)V
    //   512: iconst_0
    //   513: istore_2
    //   514: iload_2
    //   515: iload_1
    //   516: if_icmpge -> 546
    //   519: aload #5
    //   521: aload_0
    //   522: getfield W : Ljava/util/List;
    //   525: iload_2
    //   526: invokeinterface get : (I)Ljava/lang/Object;
    //   531: checkcast com/flurry/android/bb
    //   534: invokevirtual a : ()[B
    //   537: invokevirtual write : ([B)V
    //   540: iinc #2, 1
    //   543: goto -> 514
    //   546: aload #5
    //   548: iconst_0
    //   549: invokevirtual writeShort : (I)V
    //   552: aload #5
    //   554: iconst_0
    //   555: invokevirtual writeShort : (I)V
    //   558: aload_0
    //   559: getfield G : Ljava/util/List;
    //   562: aload #6
    //   564: invokevirtual toByteArray : ()[B
    //   567: invokeinterface add : (Ljava/lang/Object;)Z
    //   572: pop
    //   573: aload #5
    //   575: invokestatic a : (Ljava/io/Closeable;)V
    //   578: goto -> 276
    //   581: astore #4
    //   583: aconst_null
    //   584: astore #5
    //   586: goto -> 341
    //   589: astore #6
    //   591: aload #4
    //   593: astore #5
    //   595: aload #6
    //   597: astore #4
    //   599: goto -> 341
    //   602: astore #6
    //   604: goto -> 261
    // Exception table:
    //   from	to	target	type
    //   5	27	602	java/io/IOException
    //   5	27	581	finally
    //   27	100	255	java/io/IOException
    //   27	100	339	finally
    //   104	124	255	java/io/IOException
    //   104	124	339	finally
    //   124	167	255	java/io/IOException
    //   124	167	339	finally
    //   167	197	255	java/io/IOException
    //   167	197	339	finally
    //   197	252	255	java/io/IOException
    //   197	252	339	finally
    //   261	271	589	finally
    //   271	276	349	finally
    //   279	285	255	java/io/IOException
    //   279	285	339	finally
    //   288	336	255	java/io/IOException
    //   288	336	339	finally
    //   341	349	349	finally
    //   356	374	255	java/io/IOException
    //   356	374	339	finally
    //   377	402	255	java/io/IOException
    //   377	402	339	finally
    //   402	430	255	java/io/IOException
    //   402	430	339	finally
    //   433	442	255	java/io/IOException
    //   433	442	339	finally
    //   448	481	255	java/io/IOException
    //   448	481	339	finally
    //   497	512	255	java/io/IOException
    //   497	512	339	finally
    //   519	540	255	java/io/IOException
    //   519	540	339	finally
    //   546	573	255	java/io/IOException
    //   546	573	339	finally
    //   573	578	349	finally
  }
  
  public static boolean getAd(Context paramContext, String paramString, ViewGroup paramViewGroup, FlurryAdSize paramFlurryAdSize, long paramLong) {
    boolean bool;
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to getAd was null.");
      return false;
    } 
    if (paramString == null) {
      bm.b("FlurryAgent", "Ad space name passed to getAd was null.");
      return false;
    } 
    if (paramString.length() == 0) {
      bm.b("FlurryAgent", "Ad space name passed to getAd was empty.");
      return false;
    } 
    if (paramViewGroup == null) {
      bm.b("FlurryAgent", "ViewGroup passed to getAd was null.");
      return false;
    } 
    e.a(paramContext);
    try {
      bool = e.ab.a(paramContext, paramString, paramFlurryAdSize, paramViewGroup, paramLong);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
      bool = false;
    } 
    return bool;
  }
  
  public static int getAgentVersion() {
    return 140;
  }
  
  public static boolean getForbidPlaintextFallback() {
    return false;
  }
  
  public static String getPhoneId() {
    return e.o();
  }
  
  public static boolean getUseHttps() {
    return h;
  }
  
  private void h() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield X : I
    //   7: iconst_1
    //   8: iadd
    //   9: putfield X : I
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
  
  private byte[] i() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/android/CrcMessageDigest
    //   5: astore #6
    //   7: aload #6
    //   9: invokespecial <init> : ()V
    //   12: new java/io/ByteArrayOutputStream
    //   15: astore_3
    //   16: aload_3
    //   17: invokespecial <init> : ()V
    //   20: new java/security/DigestOutputStream
    //   23: astore #5
    //   25: aload #5
    //   27: aload_3
    //   28: aload #6
    //   30: invokespecial <init> : (Ljava/io/OutputStream;Ljava/security/MessageDigest;)V
    //   33: new java/io/DataOutputStream
    //   36: astore #4
    //   38: aload #4
    //   40: aload #5
    //   42: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   45: aload #4
    //   47: bipush #25
    //   49: invokevirtual writeShort : (I)V
    //   52: aload #4
    //   54: iconst_0
    //   55: invokevirtual writeShort : (I)V
    //   58: aload #4
    //   60: lconst_0
    //   61: invokevirtual writeLong : (J)V
    //   64: aload #4
    //   66: iconst_0
    //   67: invokevirtual writeShort : (I)V
    //   70: aload #4
    //   72: iconst_3
    //   73: invokevirtual writeShort : (I)V
    //   76: aload #4
    //   78: sipush #140
    //   81: invokevirtual writeShort : (I)V
    //   84: aload #4
    //   86: invokestatic currentTimeMillis : ()J
    //   89: invokevirtual writeLong : (J)V
    //   92: aload #4
    //   94: aload_0
    //   95: getfield w : Ljava/lang/String;
    //   98: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   101: aload #4
    //   103: aload_0
    //   104: getfield y : Ljava/lang/String;
    //   107: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   110: aload #4
    //   112: aload_0
    //   113: getfield D : Ljava/util/Map;
    //   116: invokeinterface size : ()I
    //   121: iconst_1
    //   122: iadd
    //   123: invokevirtual writeShort : (I)V
    //   126: aload #4
    //   128: iconst_0
    //   129: invokevirtual writeShort : (I)V
    //   132: aload #4
    //   134: aload_0
    //   135: invokespecial o : ()Ljava/lang/String;
    //   138: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   141: aload_0
    //   142: getfield D : Ljava/util/Map;
    //   145: invokeinterface isEmpty : ()Z
    //   150: ifne -> 268
    //   153: aload_0
    //   154: getfield D : Ljava/util/Map;
    //   157: invokeinterface entrySet : ()Ljava/util/Set;
    //   162: invokeinterface iterator : ()Ljava/util/Iterator;
    //   167: astore #7
    //   169: aload #7
    //   171: invokeinterface hasNext : ()Z
    //   176: ifeq -> 268
    //   179: aload #7
    //   181: invokeinterface next : ()Ljava/lang/Object;
    //   186: checkcast java/util/Map$Entry
    //   189: astore #8
    //   191: aload #4
    //   193: aload #8
    //   195: invokeinterface getKey : ()Ljava/lang/Object;
    //   200: checkcast java/lang/Integer
    //   203: invokevirtual intValue : ()I
    //   206: invokevirtual writeShort : (I)V
    //   209: aload #8
    //   211: invokeinterface getValue : ()Ljava/lang/Object;
    //   216: checkcast java/nio/ByteBuffer
    //   219: invokevirtual array : ()[B
    //   222: astore #8
    //   224: aload #4
    //   226: aload #8
    //   228: arraylength
    //   229: invokevirtual writeShort : (I)V
    //   232: aload #4
    //   234: aload #8
    //   236: invokevirtual write : ([B)V
    //   239: goto -> 169
    //   242: astore #5
    //   244: aload #4
    //   246: astore_3
    //   247: ldc 'FlurryAgent'
    //   249: ldc_w 'Error when generating report'
    //   252: aload #5
    //   254: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   257: pop
    //   258: aload_3
    //   259: invokestatic a : (Ljava/io/Closeable;)V
    //   262: aconst_null
    //   263: astore_3
    //   264: aload_0
    //   265: monitorexit
    //   266: aload_3
    //   267: areturn
    //   268: aload #4
    //   270: iconst_0
    //   271: invokevirtual writeByte : (I)V
    //   274: aload #4
    //   276: aload_0
    //   277: getfield F : J
    //   280: invokevirtual writeLong : (J)V
    //   283: aload #4
    //   285: aload_0
    //   286: getfield H : J
    //   289: invokevirtual writeLong : (J)V
    //   292: aload #4
    //   294: bipush #6
    //   296: invokevirtual writeShort : (I)V
    //   299: aload #4
    //   301: ldc_w 'device.model'
    //   304: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   307: aload #4
    //   309: getstatic android/os/Build.MODEL : Ljava/lang/String;
    //   312: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   315: aload #4
    //   317: ldc_w 'build.brand'
    //   320: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   323: aload #4
    //   325: getstatic android/os/Build.BRAND : Ljava/lang/String;
    //   328: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   331: aload #4
    //   333: ldc_w 'build.id'
    //   336: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   339: aload #4
    //   341: getstatic android/os/Build.ID : Ljava/lang/String;
    //   344: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   347: aload #4
    //   349: ldc_w 'version.release'
    //   352: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   355: aload #4
    //   357: getstatic android/os/Build$VERSION.RELEASE : Ljava/lang/String;
    //   360: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   363: aload #4
    //   365: ldc_w 'build.device'
    //   368: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   371: aload #4
    //   373: getstatic android/os/Build.DEVICE : Ljava/lang/String;
    //   376: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   379: aload #4
    //   381: ldc_w 'build.product'
    //   384: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   387: aload #4
    //   389: getstatic android/os/Build.PRODUCT : Ljava/lang/String;
    //   392: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   395: aload_0
    //   396: getfield Y : Ljava/util/Map;
    //   399: ifnull -> 741
    //   402: aload_0
    //   403: getfield Y : Ljava/util/Map;
    //   406: invokeinterface keySet : ()Ljava/util/Set;
    //   411: invokeinterface size : ()I
    //   416: istore_1
    //   417: new java/lang/StringBuilder
    //   420: astore #7
    //   422: aload #7
    //   424: invokespecial <init> : ()V
    //   427: aload #7
    //   429: ldc_w 'refMapSize is:  '
    //   432: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: iload_1
    //   436: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   439: invokevirtual toString : ()Ljava/lang/String;
    //   442: pop
    //   443: iload_1
    //   444: ifne -> 480
    //   447: new java/lang/StringBuilder
    //   450: astore #7
    //   452: aload #7
    //   454: invokespecial <init> : ()V
    //   457: aload #7
    //   459: ldc_w 'Referrer file Name if it exists:  '
    //   462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: aload_0
    //   466: getfield r : Ljava/io/File;
    //   469: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   472: invokevirtual toString : ()Ljava/lang/String;
    //   475: pop
    //   476: aload_0
    //   477: invokespecial l : ()V
    //   480: aload #4
    //   482: iload_1
    //   483: invokevirtual writeShort : (I)V
    //   486: aload_0
    //   487: getfield Y : Ljava/util/Map;
    //   490: ifnull -> 746
    //   493: aload_0
    //   494: getfield Y : Ljava/util/Map;
    //   497: invokeinterface entrySet : ()Ljava/util/Set;
    //   502: invokeinterface iterator : ()Ljava/util/Iterator;
    //   507: astore #7
    //   509: aload #7
    //   511: invokeinterface hasNext : ()Z
    //   516: ifeq -> 746
    //   519: aload #7
    //   521: invokeinterface next : ()Ljava/lang/Object;
    //   526: checkcast java/util/Map$Entry
    //   529: astore #8
    //   531: new java/lang/StringBuilder
    //   534: astore #9
    //   536: aload #9
    //   538: invokespecial <init> : ()V
    //   541: aload #9
    //   543: ldc_w 'Referrer Entry:  '
    //   546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: aload #8
    //   551: invokeinterface getKey : ()Ljava/lang/Object;
    //   556: checkcast java/lang/String
    //   559: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: ldc_w '='
    //   565: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: aload #8
    //   570: invokeinterface getValue : ()Ljava/lang/Object;
    //   575: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   578: invokevirtual toString : ()Ljava/lang/String;
    //   581: pop
    //   582: aload #4
    //   584: aload #8
    //   586: invokeinterface getKey : ()Ljava/lang/Object;
    //   591: checkcast java/lang/String
    //   594: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   597: new java/lang/StringBuilder
    //   600: astore #9
    //   602: aload #9
    //   604: invokespecial <init> : ()V
    //   607: aload #9
    //   609: ldc_w 'referrer key is :'
    //   612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: aload #8
    //   617: invokeinterface getKey : ()Ljava/lang/Object;
    //   622: checkcast java/lang/String
    //   625: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: invokevirtual toString : ()Ljava/lang/String;
    //   631: pop
    //   632: aload #4
    //   634: aload #8
    //   636: invokeinterface getValue : ()Ljava/lang/Object;
    //   641: checkcast java/util/List
    //   644: invokeinterface size : ()I
    //   649: invokevirtual writeShort : (I)V
    //   652: aload #8
    //   654: invokeinterface getValue : ()Ljava/lang/Object;
    //   659: checkcast java/util/List
    //   662: invokeinterface iterator : ()Ljava/util/Iterator;
    //   667: astore #8
    //   669: aload #8
    //   671: invokeinterface hasNext : ()Z
    //   676: ifeq -> 509
    //   679: aload #8
    //   681: invokeinterface next : ()Ljava/lang/Object;
    //   686: checkcast java/lang/String
    //   689: astore #10
    //   691: aload #4
    //   693: aload #10
    //   695: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   698: new java/lang/StringBuilder
    //   701: astore #9
    //   703: aload #9
    //   705: invokespecial <init> : ()V
    //   708: aload #9
    //   710: ldc_w 'referrer value is :'
    //   713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   716: aload #10
    //   718: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: invokevirtual toString : ()Ljava/lang/String;
    //   724: pop
    //   725: goto -> 669
    //   728: astore_3
    //   729: aload #4
    //   731: invokestatic a : (Ljava/io/Closeable;)V
    //   734: aload_3
    //   735: athrow
    //   736: astore_3
    //   737: aload_0
    //   738: monitorexit
    //   739: aload_3
    //   740: athrow
    //   741: iconst_0
    //   742: istore_1
    //   743: goto -> 417
    //   746: aload #4
    //   748: iconst_0
    //   749: invokevirtual writeBoolean : (Z)V
    //   752: aload #4
    //   754: iconst_0
    //   755: invokevirtual writeBoolean : (Z)V
    //   758: aload_0
    //   759: getfield aa : Ljava/util/Map;
    //   762: ifnull -> 1003
    //   765: aload_0
    //   766: getfield aa : Ljava/util/Map;
    //   769: invokeinterface keySet : ()Ljava/util/Set;
    //   774: invokeinterface size : ()I
    //   779: istore_1
    //   780: new java/lang/StringBuilder
    //   783: astore #7
    //   785: aload #7
    //   787: invokespecial <init> : ()V
    //   790: aload #7
    //   792: ldc_w 'optionsMapSize is:  '
    //   795: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   798: iload_1
    //   799: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   802: invokevirtual toString : ()Ljava/lang/String;
    //   805: pop
    //   806: aload #4
    //   808: iload_1
    //   809: invokevirtual writeShort : (I)V
    //   812: aload_0
    //   813: getfield aa : Ljava/util/Map;
    //   816: ifnull -> 1008
    //   819: aload_0
    //   820: getfield aa : Ljava/util/Map;
    //   823: invokeinterface entrySet : ()Ljava/util/Set;
    //   828: invokeinterface iterator : ()Ljava/util/Iterator;
    //   833: astore #7
    //   835: aload #7
    //   837: invokeinterface hasNext : ()Z
    //   842: ifeq -> 1008
    //   845: aload #7
    //   847: invokeinterface next : ()Ljava/lang/Object;
    //   852: checkcast java/util/Map$Entry
    //   855: astore #9
    //   857: new java/lang/StringBuilder
    //   860: astore #8
    //   862: aload #8
    //   864: invokespecial <init> : ()V
    //   867: aload #8
    //   869: ldc_w 'Launch Options Key:  '
    //   872: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   875: aload #9
    //   877: invokeinterface getKey : ()Ljava/lang/Object;
    //   882: checkcast java/lang/String
    //   885: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   888: invokevirtual toString : ()Ljava/lang/String;
    //   891: pop
    //   892: aload #4
    //   894: aload #9
    //   896: invokeinterface getKey : ()Ljava/lang/Object;
    //   901: checkcast java/lang/String
    //   904: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   907: aload #4
    //   909: aload #9
    //   911: invokeinterface getValue : ()Ljava/lang/Object;
    //   916: checkcast java/util/List
    //   919: invokeinterface size : ()I
    //   924: invokevirtual writeShort : (I)V
    //   927: aload #9
    //   929: invokeinterface getValue : ()Ljava/lang/Object;
    //   934: checkcast java/util/List
    //   937: invokeinterface iterator : ()Ljava/util/Iterator;
    //   942: astore #8
    //   944: aload #8
    //   946: invokeinterface hasNext : ()Z
    //   951: ifeq -> 835
    //   954: aload #8
    //   956: invokeinterface next : ()Ljava/lang/Object;
    //   961: checkcast java/lang/String
    //   964: astore #10
    //   966: aload #4
    //   968: aload #10
    //   970: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   973: new java/lang/StringBuilder
    //   976: astore #9
    //   978: aload #9
    //   980: invokespecial <init> : ()V
    //   983: aload #9
    //   985: ldc_w 'Launch Options value is :'
    //   988: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   991: aload #10
    //   993: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: invokevirtual toString : ()Ljava/lang/String;
    //   999: pop
    //   1000: goto -> 944
    //   1003: iconst_0
    //   1004: istore_1
    //   1005: goto -> 780
    //   1008: aload_0
    //   1009: getfield G : Ljava/util/List;
    //   1012: invokeinterface size : ()I
    //   1017: istore_2
    //   1018: aload #4
    //   1020: iload_2
    //   1021: invokevirtual writeShort : (I)V
    //   1024: iconst_0
    //   1025: istore_1
    //   1026: iload_1
    //   1027: iload_2
    //   1028: if_icmpge -> 1055
    //   1031: aload #4
    //   1033: aload_0
    //   1034: getfield G : Ljava/util/List;
    //   1037: iload_1
    //   1038: invokeinterface get : (I)Ljava/lang/Object;
    //   1043: checkcast [B
    //   1046: invokevirtual write : ([B)V
    //   1049: iinc #1, 1
    //   1052: goto -> 1026
    //   1055: new java/util/ArrayList
    //   1058: astore #7
    //   1060: aload #7
    //   1062: aload_0
    //   1063: getfield G : Ljava/util/List;
    //   1066: invokespecial <init> : (Ljava/util/Collection;)V
    //   1069: aload_0
    //   1070: aload #7
    //   1072: putfield A : Ljava/util/List;
    //   1075: aload #5
    //   1077: iconst_0
    //   1078: invokevirtual on : (Z)V
    //   1081: aload #4
    //   1083: aload #6
    //   1085: invokevirtual getDigest : ()[B
    //   1088: invokevirtual write : ([B)V
    //   1091: aload #4
    //   1093: invokevirtual close : ()V
    //   1096: aload_3
    //   1097: invokevirtual toByteArray : ()[B
    //   1100: astore_3
    //   1101: aload #4
    //   1103: invokestatic a : (Ljava/io/Closeable;)V
    //   1106: goto -> 264
    //   1109: astore_3
    //   1110: aconst_null
    //   1111: astore #4
    //   1113: goto -> 729
    //   1116: astore #4
    //   1118: aload_3
    //   1119: astore #5
    //   1121: aload #4
    //   1123: astore_3
    //   1124: aload #5
    //   1126: astore #4
    //   1128: goto -> 729
    //   1131: astore #5
    //   1133: aconst_null
    //   1134: astore_3
    //   1135: goto -> 247
    // Exception table:
    //   from	to	target	type
    //   2	45	1131	java/lang/Throwable
    //   2	45	1109	finally
    //   45	169	242	java/lang/Throwable
    //   45	169	728	finally
    //   169	239	242	java/lang/Throwable
    //   169	239	728	finally
    //   247	258	1116	finally
    //   258	262	736	finally
    //   268	417	242	java/lang/Throwable
    //   268	417	728	finally
    //   417	443	242	java/lang/Throwable
    //   417	443	728	finally
    //   447	480	242	java/lang/Throwable
    //   447	480	728	finally
    //   480	509	242	java/lang/Throwable
    //   480	509	728	finally
    //   509	669	242	java/lang/Throwable
    //   509	669	728	finally
    //   669	725	242	java/lang/Throwable
    //   669	725	728	finally
    //   729	736	736	finally
    //   746	780	242	java/lang/Throwable
    //   746	780	728	finally
    //   780	835	242	java/lang/Throwable
    //   780	835	728	finally
    //   835	944	242	java/lang/Throwable
    //   835	944	728	finally
    //   944	1000	242	java/lang/Throwable
    //   944	1000	728	finally
    //   1008	1024	242	java/lang/Throwable
    //   1008	1024	728	finally
    //   1031	1049	242	java/lang/Throwable
    //   1031	1049	728	finally
    //   1055	1101	242	java/lang/Throwable
    //   1055	1101	728	finally
    //   1101	1106	736	finally
  }
  
  public static void initializeAds(Context paramContext) {
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to initializeAds was null.");
      return;
    } 
    e.a(paramContext);
    try {
      e.ab.b(paramContext);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static boolean isAdAvailable(Context paramContext, String paramString, FlurryAdSize paramFlurryAdSize, long paramLong) {
    boolean bool;
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to isAdAvailable was null.");
      return false;
    } 
    if (paramString == null) {
      bm.b("FlurryAgent", "Ad space name passed to isAdAvailable was null.");
      return false;
    } 
    if (paramString.length() == 0) {
      bm.b("FlurryAgent", "Ad space name passed to isAdAvailable was empty.");
      return false;
    } 
    e.a(paramContext);
    try {
      bool = e.ab.a(paramContext, paramString, paramFlurryAdSize, paramLong);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
      bool = false;
    } 
    return bool;
  }
  
  protected static boolean isCaptureUncaughtExceptions() {
    return j;
  }
  
  private static String j() {
    return (b != null) ? b : (i ? c : (h ? d : c));
  }
  
  private void k() {
    try {
      bm.a("FlurryAgent", "generating report");
      byte[] arrayOfByte = i();
      if (arrayOfByte != null) {
        if (a(arrayOfByte)) {
          String str;
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          StringBuilder stringBuilder2 = stringBuilder1.append("Done sending ");
          if (this.s) {
            str = "initial ";
          } else {
            str = "";
          } 
          bm.a("FlurryAgent", stringBuilder2.append(str).append("agent report").toString());
          m();
        } 
        return;
      } 
      bm.a("FlurryAgent", "Error generating report");
    } catch (IOException iOException) {
      bm.a("FlurryAgent", "", iOException);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  private void l() {
    if (this.r.exists()) {
      bm.c("FlurryAgent", "Loading referrer info from file:  " + this.r.getAbsolutePath());
      String str = a(this.r);
      if (str != null)
        this.Y = b(str); 
    } 
  }
  
  public static void logEvent(String paramString) {
    try {
      e.a(paramString, (Map)null, false);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "Failed to log event: " + paramString, throwable);
    } 
  }
  
  public static void logEvent(String paramString, Map paramMap) {
    try {
      e.a(paramString, paramMap, false);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "Failed to log event: " + paramString, throwable);
    } 
  }
  
  public static void logEvent(String paramString, Map paramMap, boolean paramBoolean) {
    try {
      e.a(paramString, paramMap, paramBoolean);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "Failed to log event: " + paramString, throwable);
    } 
  }
  
  public static void logEvent(String paramString, boolean paramBoolean) {
    try {
      e.a(paramString, (Map)null, paramBoolean);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "Failed to log event: " + paramString, throwable);
    } 
  }
  
  private void m() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield q : Ljava/io/File;
    //   6: invokestatic a : (Ljava/io/File;)Z
    //   9: istore_3
    //   10: iload_3
    //   11: ifne -> 21
    //   14: aconst_null
    //   15: invokestatic a : (Ljava/io/Closeable;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: new java/io/FileOutputStream
    //   24: astore #4
    //   26: aload #4
    //   28: aload_0
    //   29: getfield q : Ljava/io/File;
    //   32: invokespecial <init> : (Ljava/io/File;)V
    //   35: new java/io/DataOutputStream
    //   38: astore #5
    //   40: aload #5
    //   42: aload #4
    //   44: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   47: aload #5
    //   49: astore #4
    //   51: aload #5
    //   53: ldc_w 46586
    //   56: invokevirtual writeShort : (I)V
    //   59: aload #5
    //   61: astore #4
    //   63: aload #5
    //   65: iconst_2
    //   66: invokevirtual writeShort : (I)V
    //   69: aload #5
    //   71: astore #4
    //   73: aload #5
    //   75: aload_0
    //   76: getfield w : Ljava/lang/String;
    //   79: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   82: aload #5
    //   84: astore #4
    //   86: aload #5
    //   88: aload_0
    //   89: invokespecial o : ()Ljava/lang/String;
    //   92: invokevirtual writeUTF : (Ljava/lang/String;)V
    //   95: aload #5
    //   97: astore #4
    //   99: aload #5
    //   101: aload_0
    //   102: getfield E : Z
    //   105: invokevirtual writeBoolean : (Z)V
    //   108: aload #5
    //   110: astore #4
    //   112: aload #5
    //   114: aload_0
    //   115: getfield F : J
    //   118: invokevirtual writeLong : (J)V
    //   121: aload #5
    //   123: astore #4
    //   125: aload_0
    //   126: getfield G : Ljava/util/List;
    //   129: invokeinterface size : ()I
    //   134: iconst_1
    //   135: isub
    //   136: istore_1
    //   137: iload_1
    //   138: iflt -> 230
    //   141: aload #5
    //   143: astore #4
    //   145: aload_0
    //   146: getfield G : Ljava/util/List;
    //   149: iload_1
    //   150: invokeinterface get : (I)Ljava/lang/Object;
    //   155: checkcast [B
    //   158: astore #6
    //   160: aload #5
    //   162: astore #4
    //   164: aload #6
    //   166: arraylength
    //   167: istore_2
    //   168: aload #5
    //   170: astore #4
    //   172: iload_2
    //   173: iconst_2
    //   174: iadd
    //   175: aload #5
    //   177: invokevirtual size : ()I
    //   180: iadd
    //   181: ldc_w 50000
    //   184: if_icmple -> 255
    //   187: aload #5
    //   189: astore #4
    //   191: new java/lang/StringBuilder
    //   194: astore #6
    //   196: aload #5
    //   198: astore #4
    //   200: aload #6
    //   202: invokespecial <init> : ()V
    //   205: aload #5
    //   207: astore #4
    //   209: ldc 'FlurryAgent'
    //   211: aload #6
    //   213: ldc_w 'discarded sessions: '
    //   216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: iload_1
    //   220: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   223: invokevirtual toString : ()Ljava/lang/String;
    //   226: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   229: pop
    //   230: aload #5
    //   232: astore #4
    //   234: aload #5
    //   236: iconst_0
    //   237: invokevirtual writeShort : (I)V
    //   240: aload #5
    //   242: invokestatic a : (Ljava/io/Closeable;)V
    //   245: goto -> 18
    //   248: astore #4
    //   250: aload_0
    //   251: monitorexit
    //   252: aload #4
    //   254: athrow
    //   255: aload #5
    //   257: astore #4
    //   259: aload #5
    //   261: iload_2
    //   262: invokevirtual writeShort : (I)V
    //   265: aload #5
    //   267: astore #4
    //   269: aload #5
    //   271: aload #6
    //   273: invokevirtual write : ([B)V
    //   276: iinc #1, -1
    //   279: goto -> 137
    //   282: astore #6
    //   284: aconst_null
    //   285: astore #5
    //   287: aload #5
    //   289: astore #4
    //   291: ldc 'FlurryAgent'
    //   293: ldc ''
    //   295: aload #6
    //   297: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   300: pop
    //   301: aload #5
    //   303: invokestatic a : (Ljava/io/Closeable;)V
    //   306: goto -> 18
    //   309: astore #5
    //   311: aconst_null
    //   312: astore #4
    //   314: aload #4
    //   316: invokestatic a : (Ljava/io/Closeable;)V
    //   319: aload #5
    //   321: athrow
    //   322: astore #5
    //   324: goto -> 314
    //   327: astore #6
    //   329: goto -> 287
    // Exception table:
    //   from	to	target	type
    //   2	10	282	java/lang/Throwable
    //   2	10	309	finally
    //   14	18	248	finally
    //   21	47	282	java/lang/Throwable
    //   21	47	309	finally
    //   51	59	327	java/lang/Throwable
    //   51	59	322	finally
    //   63	69	327	java/lang/Throwable
    //   63	69	322	finally
    //   73	82	327	java/lang/Throwable
    //   73	82	322	finally
    //   86	95	327	java/lang/Throwable
    //   86	95	322	finally
    //   99	108	327	java/lang/Throwable
    //   99	108	322	finally
    //   112	121	327	java/lang/Throwable
    //   112	121	322	finally
    //   125	137	327	java/lang/Throwable
    //   125	137	322	finally
    //   145	160	327	java/lang/Throwable
    //   145	160	322	finally
    //   164	168	327	java/lang/Throwable
    //   164	168	322	finally
    //   172	187	327	java/lang/Throwable
    //   172	187	322	finally
    //   191	196	327	java/lang/Throwable
    //   191	196	322	finally
    //   200	205	327	java/lang/Throwable
    //   200	205	322	finally
    //   209	230	327	java/lang/Throwable
    //   209	230	322	finally
    //   234	240	327	java/lang/Throwable
    //   234	240	322	finally
    //   240	245	248	finally
    //   259	265	327	java/lang/Throwable
    //   259	265	322	finally
    //   269	276	327	java/lang/Throwable
    //   269	276	322	finally
    //   291	301	322	finally
    //   301	306	248	finally
    //   314	322	248	finally
  }
  
  private void n() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield B : Landroid/location/LocationManager;
    //   6: ifnull -> 17
    //   9: aload_0
    //   10: getfield B : Landroid/location/LocationManager;
    //   13: aload_0
    //   14: invokevirtual removeUpdates : (Landroid/location/LocationListener;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  private String o() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield C : Ljava/lang/String;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public static void onEndSession(Context paramContext) {
    if (paramContext == null)
      throw new NullPointerException("Null context"); 
    try {
      e.a(paramContext, false);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void onError(String paramString1, String paramString2, String paramString3) {
    try {
      e.a(paramString1, paramString2, paramString3);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void onEvent(String paramString) {
    try {
      e.a(paramString, (Map)null, false);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void onEvent(String paramString, Map paramMap) {
    try {
      e.a(paramString, paramMap, false);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void onPageView() {
    try {
      e.h();
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void onStartSession(Context paramContext, String paramString) {
    if (paramContext == null)
      throw new NullPointerException("Null context"); 
    if (paramString == null || paramString.length() == 0)
      throw new IllegalArgumentException("Api key not specified"); 
    try {
      e.a(paramContext, paramString);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void removeAd(Context paramContext, String paramString, ViewGroup paramViewGroup) {
    if (paramContext == null) {
      bm.b("FlurryAgent", "Context passed to removeAd was null.");
      return;
    } 
    if (paramString == null) {
      bm.b("FlurryAgent", "Ad space name passed to removeAd was null.");
      return;
    } 
    if (paramString.length() == 0) {
      bm.b("FlurryAgent", "Ad space name passed to removeAd was empty.");
      return;
    } 
    if (paramViewGroup == null) {
      bm.b("FlurryAgent", "ViewGroup passed to removeAd was null.");
      return;
    } 
    try {
      e.ab.b(paramString);
    } catch (Throwable throwable) {
      bm.b("FlurryAgent", "", throwable);
    } 
  }
  
  public static void sendAdLogsToServer() {
    e.ab.j();
  }
  
  public static void setAdListener(FlurryAdListener paramFlurryAdListener) {
    e.ab.a(paramFlurryAdListener);
  }
  
  public static void setAdLogUrl(String paramString) {
    paramString.endsWith(".do");
    e.ab.d(paramString);
  }
  
  public static void setAdServerUrl(String paramString) {
    paramString.endsWith(".do");
    e.ab.c(paramString);
  }
  
  public static void setAge(int paramInt) {
    if (paramInt > 0 && paramInt < 110) {
      Date date = new Date((new Date(System.currentTimeMillis() - paramInt * 31449600000L)).getYear(), 1, 1);
      e.P = Long.valueOf(date.getTime());
    } 
  }
  
  public static void setCaptureUncaughtExceptions(boolean paramBoolean) {
    synchronized (e) {
      if (e.s) {
        bm.b("FlurryAgent", "Cannot setCaptureUncaughtExceptions after onStartSession");
        return;
      } 
      j = paramBoolean;
      return;
    } 
  }
  
  public static void setContinueSessionMillis(long paramLong) {
    if (paramLong < 5000L) {
      bm.b("FlurryAgent", "Invalid time set for session resumption: " + paramLong);
      return;
    } 
    synchronized (e) {
      f = paramLong;
      return;
    } 
  }
  
  public static void setCustomAdNetworkHandler(ICustomAdNetworkHandler paramICustomAdNetworkHandler) {
    e.ab.a(paramICustomAdNetworkHandler);
  }
  
  public static void setGender(byte paramByte) {
    switch (paramByte) {
      default:
        e.O = -1;
        return;
      case 0:
      case 1:
        break;
    } 
    e.O = paramByte;
  }
  
  public static void setLocation(float paramFloat1, float paramFloat2) {
    e.ab.a(paramFloat1, paramFloat2);
  }
  
  public static void setLocationCriteria(Criteria paramCriteria) {
    synchronized (e) {
      k = paramCriteria;
      return;
    } 
  }
  
  public static void setLogEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: getstatic com/flurry/android/FlurryAgent.e : Lcom/flurry/android/FlurryAgent;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: iload_0
    //   7: ifeq -> 16
    //   10: invokestatic b : ()V
    //   13: aload_1
    //   14: monitorexit
    //   15: return
    //   16: invokestatic a : ()V
    //   19: goto -> 13
    //   22: astore_2
    //   23: aload_1
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   10	13	22	finally
    //   13	15	22	finally
    //   16	19	22	finally
    //   23	25	22	finally
  }
  
  public static void setLogEvents(boolean paramBoolean) {
    synchronized (e) {
      g = paramBoolean;
      return;
    } 
  }
  
  public static void setLogLevel(int paramInt) {
    synchronized (e) {
      bm.a(paramInt);
      return;
    } 
  }
  
  public static void setReportLocation(boolean paramBoolean) {
    synchronized (e) {
      e.z = paramBoolean;
      return;
    } 
  }
  
  public static void setReportUrl(String paramString) {
    paramString.endsWith(".do");
    b = paramString;
  }
  
  public static void setTargetingKeywords(Map paramMap) {
    e.ab.b(paramMap);
  }
  
  public static void setUseHttps(boolean paramBoolean) {
    h = paramBoolean;
  }
  
  public static void setUserCookies(Map paramMap) {
    if (l) {
      e.ab.c(paramMap);
      return;
    } 
    Z = paramMap;
  }
  
  public static void setUserId(String paramString) {
    synchronized (e) {
      e.N = ac.a(paramString);
      return;
    } 
  }
  
  public static void setVersionName(String paramString) {
    synchronized (e) {
      e.y = paramString;
      return;
    } 
  }
  
  final void a(Throwable paramThrowable) {
    paramThrowable.printStackTrace();
    String str = "";
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    if (arrayOfStackTraceElement != null && arrayOfStackTraceElement.length > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      if (paramThrowable.getMessage() != null)
        stringBuilder.append(" (" + paramThrowable.getMessage() + ")\n"); 
      for (byte b = 0; b < arrayOfStackTraceElement.length; b++) {
        if (b != 0)
          stringBuilder.append('\n'); 
        StackTraceElement stackTraceElement = arrayOfStackTraceElement[b];
        stringBuilder.append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append(":").append(stackTraceElement.getLineNumber());
      } 
      String str1 = stringBuilder.toString();
    } else if (paramThrowable.getMessage() != null) {
      str = paramThrowable.getMessage();
    } 
    onError("uncaught", str, paramThrowable.getClass().toString());
    this.v.clear();
    a((Context)null, true);
  }
  
  public final void onLocationChanged(Location paramLocation) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield R : Landroid/location/Location;
    //   7: aload_0
    //   8: invokespecial n : ()V
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: ldc 'FlurryAgent'
    //   17: ldc ''
    //   19: aload_1
    //   20: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   23: pop
    //   24: goto -> 11
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	14	java/lang/Throwable
    //   2	11	27	finally
    //   15	24	27	finally
  }
  
  public final void onProviderDisabled(String paramString) {}
  
  public final void onProviderEnabled(String paramString) {}
  
  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\FlurryAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */