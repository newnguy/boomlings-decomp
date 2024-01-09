package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.google.ads.Ad;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
import com.google.ads.ac;
import com.google.ads.ae;
import com.google.ads.b;
import com.google.ads.c;
import com.google.ads.e;
import com.google.ads.f;
import com.google.ads.g;
import com.google.ads.h;
import com.google.ads.l;
import com.google.ads.m;
import com.google.ads.util.AdUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class d {
  private static final Object a = new Object();
  
  private final m b;
  
  private c c;
  
  private AdRequest d;
  
  private g e;
  
  private AdWebView f;
  
  private i g;
  
  private Handler h;
  
  private long i;
  
  private boolean j;
  
  private boolean k;
  
  private boolean l;
  
  private boolean m;
  
  private boolean n;
  
  private SharedPreferences o;
  
  private long p;
  
  private ae q;
  
  private boolean r;
  
  private LinkedList s;
  
  private LinkedList t;
  
  private int u;
  
  private Boolean v;
  
  private com.google.ads.d w;
  
  private e x;
  
  private f y;
  
  private String z;
  
  public d(Ad paramAd, Activity paramActivity, AdSize paramAdSize, String paramString, ViewGroup paramViewGroup, boolean paramBoolean) {
    h h;
    this.u = -1;
    this.z = null;
    this.r = paramBoolean;
    this.e = new g();
    this.c = null;
    this.d = null;
    this.k = false;
    this.h = new Handler();
    this.p = 60000L;
    this.l = false;
    this.n = false;
    this.m = true;
    if (paramActivity == null) {
      InterstitialAd interstitialAd;
      l l = l.a();
      if (paramAd instanceof AdView) {
        AdView adView = (AdView)paramAd;
      } else {
        paramActivity = null;
      } 
      if (paramAd instanceof InterstitialAd) {
        interstitialAd = (InterstitialAd)paramAd;
      } else {
        interstitialAd = null;
      } 
      if (paramAdSize == null) {
        h = h.a;
      } else {
        h = h.a((AdSize)h);
      } 
      this.b = new m(l, paramAd, (AdView)paramActivity, interstitialAd, paramString, null, null, paramViewGroup, h);
      return;
    } 
    synchronized (a) {
      InterstitialAd interstitialAd;
      this.o = paramActivity.getApplicationContext().getSharedPreferences("GoogleAdMobAdsPrefs", 0);
      if (paramBoolean) {
        SharedPreferences sharedPreferences = this.o;
        interstitialAd = (InterstitialAd)new StringBuilder();
        this();
        long l1 = sharedPreferences.getLong(interstitialAd.append("Timeout").append(paramString).toString(), -1L);
        if (l1 < 0L) {
          this.i = 5000L;
        } else {
          this.i = l1;
        } 
      } else {
        this.i = 60000L;
      } 
      l l = l.a();
      if (paramAd instanceof AdView) {
        null = paramAd;
      } else {
        null = null;
      } 
      if (paramAd instanceof InterstitialAd) {
        interstitialAd = (InterstitialAd)paramAd;
      } else {
        interstitialAd = null;
      } 
      Context context = paramActivity.getApplicationContext();
      if (h == null) {
        h = h.a;
      } else {
        h = h.a((AdSize)h, paramActivity.getApplicationContext());
      } 
      this.b = new m(l, paramAd, (AdView)null, interstitialAd, paramString, paramActivity, context, paramViewGroup, h);
      this.q = new ae(this);
      this.s = new LinkedList();
      this.t = new LinkedList();
      a();
      AdUtil.h((Context)this.b.f.a());
      this.w = new com.google.ads.d();
      this.x = new e(this);
      this.v = null;
      this.y = null;
      return;
    } 
  }
  
  private void a(f paramf, Boolean paramBoolean) {
    List<String> list2 = paramf.d();
    List<String> list1 = list2;
    if (list2 == null) {
      list1 = new ArrayList();
      list1.add("http://e.admob.com/imp?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@&adt=@gw_adt@&aec=@gw_aec@");
    } 
    String str = paramf.b();
    a(list1, paramf.a(), str, paramf.c(), paramBoolean, this.e.d(), this.e.e());
  }
  
  private void a(List<String> paramList, String paramString) {
    if (paramList == null) {
      paramList = new ArrayList();
      paramList.add("http://e.admob.com/nofill?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&adt=@gw_adt@&aec=@gw_aec@");
    } 
    a(paramList, null, null, paramString, null, this.e.d(), this.e.e());
  }
  
  private void a(List paramList, String paramString1, String paramString2, String paramString3, Boolean paramBoolean, String paramString4, String paramString5) {
    String str2 = AdUtil.a((Context)this.b.f.a());
    b b = b.a();
    String str1 = b.b().toString();
    String str3 = b.c().toString();
    Iterator<String> iterator = paramList.iterator();
    while (iterator.hasNext())
      (new Thread((Runnable)new ac(g.a(iterator.next(), (String)this.b.d.a(), paramBoolean, str2, paramString1, paramString2, paramString3, str1, str3, paramString4, paramString5), (Context)this.b.f.a()))).start(); 
    this.e.b();
  }
  
  private void b(f paramf, Boolean paramBoolean) {
    List<String> list2 = paramf.e();
    List<String> list1 = list2;
    if (list2 == null) {
      list1 = new ArrayList();
      list1.add("http://e.admob.com/clk?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@");
    } 
    String str = paramf.b();
    a(list1, paramf.a(), str, paramf.c(), paramBoolean, null, null);
  }
  
  protected void A() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Lcom/google/ads/m;
    //   6: getfield e : Lcom/google/ads/util/i$d;
    //   9: invokevirtual a : ()Ljava/lang/Object;
    //   12: checkcast android/app/Activity
    //   15: astore #5
    //   17: aload #5
    //   19: ifnonnull -> 31
    //   22: ldc_w 'activity was null while trying to ping click tracking URLs.'
    //   25: invokestatic e : (Ljava/lang/String;)V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: aload_0
    //   32: getfield t : Ljava/util/LinkedList;
    //   35: invokevirtual iterator : ()Ljava/util/Iterator;
    //   38: astore_3
    //   39: aload_3
    //   40: invokeinterface hasNext : ()Z
    //   45: ifeq -> 28
    //   48: aload_3
    //   49: invokeinterface next : ()Ljava/lang/Object;
    //   54: checkcast java/lang/String
    //   57: astore_2
    //   58: new java/lang/Thread
    //   61: astore #4
    //   63: new com/google/ads/ac
    //   66: astore_1
    //   67: aload_1
    //   68: aload_2
    //   69: aload #5
    //   71: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   74: invokespecial <init> : (Ljava/lang/String;Landroid/content/Context;)V
    //   77: aload #4
    //   79: aload_1
    //   80: invokespecial <init> : (Ljava/lang/Runnable;)V
    //   83: aload #4
    //   85: invokevirtual start : ()V
    //   88: goto -> 39
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	91	finally
    //   22	28	91	finally
    //   31	39	91	finally
    //   39	88	91	finally
  }
  
  protected void B() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield c : Lcom/google/ads/internal/c;
    //   7: aload_0
    //   8: iconst_1
    //   9: putfield k : Z
    //   12: aload_0
    //   13: getfield f : Lcom/google/ads/internal/AdWebView;
    //   16: iconst_0
    //   17: invokevirtual setVisibility : (I)V
    //   20: aload_0
    //   21: getfield b : Lcom/google/ads/m;
    //   24: invokevirtual a : ()Z
    //   27: ifeq -> 38
    //   30: aload_0
    //   31: aload_0
    //   32: getfield f : Lcom/google/ads/internal/AdWebView;
    //   35: invokevirtual a : (Landroid/view/View;)V
    //   38: aload_0
    //   39: getfield e : Lcom/google/ads/internal/g;
    //   42: invokevirtual g : ()V
    //   45: aload_0
    //   46: getfield b : Lcom/google/ads/m;
    //   49: invokevirtual a : ()Z
    //   52: ifeq -> 59
    //   55: aload_0
    //   56: invokevirtual w : ()V
    //   59: ldc_w 'onReceiveAd()'
    //   62: invokestatic c : (Ljava/lang/String;)V
    //   65: aload_0
    //   66: getfield b : Lcom/google/ads/m;
    //   69: getfield m : Lcom/google/ads/util/i$c;
    //   72: invokevirtual a : ()Ljava/lang/Object;
    //   75: checkcast com/google/ads/AdListener
    //   78: astore_1
    //   79: aload_1
    //   80: ifnull -> 102
    //   83: aload_1
    //   84: aload_0
    //   85: getfield b : Lcom/google/ads/m;
    //   88: getfield h : Lcom/google/ads/util/i$b;
    //   91: invokevirtual a : ()Ljava/lang/Object;
    //   94: checkcast com/google/ads/Ad
    //   97: invokeinterface onReceiveAd : (Lcom/google/ads/Ad;)V
    //   102: aload_0
    //   103: monitorexit
    //   104: return
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    // Exception table:
    //   from	to	target	type
    //   2	38	105	finally
    //   38	59	105	finally
    //   59	79	105	finally
    //   83	102	105	finally
  }
  
  public void a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/google/ads/internal/AdWebView
    //   5: astore_1
    //   6: aload_1
    //   7: aload_0
    //   8: getfield b : Lcom/google/ads/m;
    //   11: aload_0
    //   12: getfield b : Lcom/google/ads/m;
    //   15: getfield k : Lcom/google/ads/util/i$b;
    //   18: invokevirtual a : ()Ljava/lang/Object;
    //   21: checkcast com/google/ads/internal/h
    //   24: invokevirtual b : ()Lcom/google/ads/AdSize;
    //   27: invokespecial <init> : (Lcom/google/ads/m;Lcom/google/ads/AdSize;)V
    //   30: aload_0
    //   31: aload_1
    //   32: putfield f : Lcom/google/ads/internal/AdWebView;
    //   35: aload_0
    //   36: getfield f : Lcom/google/ads/internal/AdWebView;
    //   39: bipush #8
    //   41: invokevirtual setVisibility : (I)V
    //   44: aload_0
    //   45: aload_0
    //   46: getstatic com/google/ads/internal/a.c : Ljava/util/Map;
    //   49: iconst_1
    //   50: aload_0
    //   51: getfield b : Lcom/google/ads/m;
    //   54: invokevirtual b : ()Z
    //   57: invokestatic a : (Lcom/google/ads/internal/d;Ljava/util/Map;ZZ)Lcom/google/ads/internal/i;
    //   60: putfield g : Lcom/google/ads/internal/i;
    //   63: aload_0
    //   64: getfield f : Lcom/google/ads/internal/AdWebView;
    //   67: aload_0
    //   68: getfield g : Lcom/google/ads/internal/i;
    //   71: invokevirtual setWebViewClient : (Landroid/webkit/WebViewClient;)V
    //   74: aload_0
    //   75: getfield b : Lcom/google/ads/m;
    //   78: getfield a : Lcom/google/ads/util/i$b;
    //   81: invokevirtual a : ()Ljava/lang/Object;
    //   84: checkcast com/google/ads/l
    //   87: getfield a : Lcom/google/ads/util/i$b;
    //   90: invokevirtual a : ()Ljava/lang/Object;
    //   93: checkcast com/google/ads/l$a
    //   96: astore_1
    //   97: getstatic com/google/ads/util/AdUtil.a : I
    //   100: aload_1
    //   101: getfield a : Lcom/google/ads/util/i$c;
    //   104: invokevirtual a : ()Ljava/lang/Object;
    //   107: checkcast java/lang/Integer
    //   110: invokevirtual intValue : ()I
    //   113: if_icmpge -> 148
    //   116: aload_0
    //   117: getfield b : Lcom/google/ads/m;
    //   120: getfield k : Lcom/google/ads/util/i$b;
    //   123: invokevirtual a : ()Ljava/lang/Object;
    //   126: checkcast com/google/ads/internal/h
    //   129: invokevirtual a : ()Z
    //   132: ifne -> 148
    //   135: ldc_w 'Disabling hardware acceleration for a banner.'
    //   138: invokestatic a : (Ljava/lang/String;)V
    //   141: aload_0
    //   142: getfield f : Lcom/google/ads/internal/AdWebView;
    //   145: invokevirtual b : ()V
    //   148: aload_0
    //   149: monitorexit
    //   150: return
    //   151: astore_1
    //   152: aload_0
    //   153: monitorexit
    //   154: aload_1
    //   155: athrow
    // Exception table:
    //   from	to	target	type
    //   2	148	151	finally
  }
  
  public void a(float paramFloat) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield p : J
    //   6: lstore_2
    //   7: aload_0
    //   8: ldc_w 1000.0
    //   11: fload_1
    //   12: fmul
    //   13: f2l
    //   14: putfield p : J
    //   17: aload_0
    //   18: invokevirtual r : ()Z
    //   21: ifeq -> 41
    //   24: aload_0
    //   25: getfield p : J
    //   28: lload_2
    //   29: lcmp
    //   30: ifeq -> 41
    //   33: aload_0
    //   34: invokevirtual e : ()V
    //   37: aload_0
    //   38: invokevirtual f : ()V
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore #4
    //   46: aload_0
    //   47: monitorexit
    //   48: aload #4
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	41	44	finally
  }
  
  public void a(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield u : I
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void a(long paramLong) {
    synchronized (a) {
      SharedPreferences.Editor editor = this.o.edit();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      editor.putLong(stringBuilder.append("Timeout").append(this.b.d).toString(), paramLong);
      editor.commit();
      if (this.r)
        this.i = paramLong; 
      return;
    } 
  }
  
  public void a(View paramView) {
    ((ViewGroup)this.b.g.a()).removeAllViews();
    ((ViewGroup)this.b.g.a()).addView(paramView);
  }
  
  public void a(View paramView, h paramh, f paramf, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 'AdManager.onReceiveGWhirlAd() called.'
    //   5: invokestatic a : (Ljava/lang/String;)V
    //   8: aload_0
    //   9: iconst_1
    //   10: putfield k : Z
    //   13: aload_0
    //   14: aload_3
    //   15: putfield y : Lcom/google/ads/f;
    //   18: aload_0
    //   19: getfield b : Lcom/google/ads/m;
    //   22: invokevirtual a : ()Z
    //   25: ifeq -> 43
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual a : (Landroid/view/View;)V
    //   33: aload_0
    //   34: aload_3
    //   35: iload #4
    //   37: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   40: invokespecial a : (Lcom/google/ads/f;Ljava/lang/Boolean;)V
    //   43: aload_0
    //   44: getfield x : Lcom/google/ads/e;
    //   47: aload_2
    //   48: invokevirtual d : (Lcom/google/ads/h;)V
    //   51: aload_0
    //   52: getfield b : Lcom/google/ads/m;
    //   55: getfield m : Lcom/google/ads/util/i$c;
    //   58: invokevirtual a : ()Ljava/lang/Object;
    //   61: checkcast com/google/ads/AdListener
    //   64: astore_1
    //   65: aload_1
    //   66: ifnull -> 88
    //   69: aload_1
    //   70: aload_0
    //   71: getfield b : Lcom/google/ads/m;
    //   74: getfield h : Lcom/google/ads/util/i$b;
    //   77: invokevirtual a : ()Ljava/lang/Object;
    //   80: checkcast com/google/ads/Ad
    //   83: invokeinterface onReceiveAd : (Lcom/google/ads/Ad;)V
    //   88: aload_0
    //   89: monitorexit
    //   90: return
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    // Exception table:
    //   from	to	target	type
    //   2	43	91	finally
    //   43	65	91	finally
    //   69	88	91	finally
  }
  
  public void a(AdRequest.ErrorCode paramErrorCode) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield c : Lcom/google/ads/internal/c;
    //   7: aload_1
    //   8: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   11: if_acmpne -> 32
    //   14: aload_0
    //   15: ldc_w 60.0
    //   18: invokevirtual a : (F)V
    //   21: aload_0
    //   22: invokevirtual r : ()Z
    //   25: ifne -> 32
    //   28: aload_0
    //   29: invokevirtual g : ()V
    //   32: aload_0
    //   33: getfield b : Lcom/google/ads/m;
    //   36: invokevirtual b : ()Z
    //   39: ifeq -> 56
    //   42: aload_1
    //   43: getstatic com/google/ads/AdRequest$ErrorCode.b : Lcom/google/ads/AdRequest$ErrorCode;
    //   46: if_acmpne -> 128
    //   49: aload_0
    //   50: getfield e : Lcom/google/ads/internal/g;
    //   53: invokevirtual B : ()V
    //   56: new java/lang/StringBuilder
    //   59: astore_2
    //   60: aload_2
    //   61: invokespecial <init> : ()V
    //   64: aload_2
    //   65: ldc_w 'onFailedToReceiveAd('
    //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: aload_1
    //   72: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   75: ldc_w ')'
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual toString : ()Ljava/lang/String;
    //   84: invokestatic c : (Ljava/lang/String;)V
    //   87: aload_0
    //   88: getfield b : Lcom/google/ads/m;
    //   91: getfield m : Lcom/google/ads/util/i$c;
    //   94: invokevirtual a : ()Ljava/lang/Object;
    //   97: checkcast com/google/ads/AdListener
    //   100: astore_2
    //   101: aload_2
    //   102: ifnull -> 125
    //   105: aload_2
    //   106: aload_0
    //   107: getfield b : Lcom/google/ads/m;
    //   110: getfield h : Lcom/google/ads/util/i$b;
    //   113: invokevirtual a : ()Ljava/lang/Object;
    //   116: checkcast com/google/ads/Ad
    //   119: aload_1
    //   120: invokeinterface onFailedToReceiveAd : (Lcom/google/ads/Ad;Lcom/google/ads/AdRequest$ErrorCode;)V
    //   125: aload_0
    //   126: monitorexit
    //   127: return
    //   128: aload_1
    //   129: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   132: if_acmpne -> 56
    //   135: aload_0
    //   136: getfield e : Lcom/google/ads/internal/g;
    //   139: invokevirtual z : ()V
    //   142: goto -> 56
    //   145: astore_1
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	145	finally
    //   32	56	145	finally
    //   56	101	145	finally
    //   105	125	145	finally
    //   128	142	145	finally
  }
  
  public void a(AdRequest paramAdRequest) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual p : ()Z
    //   6: ifeq -> 18
    //   9: ldc_w 'loadAd called while the ad is already loading, so aborting.'
    //   12: invokestatic e : (Ljava/lang/String;)V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: invokestatic c : ()Z
    //   21: ifeq -> 38
    //   24: ldc_w 'loadAd called while an interstitial or landing page is displayed, so aborting'
    //   27: invokestatic e : (Ljava/lang/String;)V
    //   30: goto -> 15
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    //   38: aload_0
    //   39: getfield b : Lcom/google/ads/m;
    //   42: getfield f : Lcom/google/ads/util/i$b;
    //   45: invokevirtual a : ()Ljava/lang/Object;
    //   48: checkcast android/content/Context
    //   51: invokestatic c : (Landroid/content/Context;)Z
    //   54: ifeq -> 15
    //   57: aload_0
    //   58: getfield b : Lcom/google/ads/m;
    //   61: getfield f : Lcom/google/ads/util/i$b;
    //   64: invokevirtual a : ()Ljava/lang/Object;
    //   67: checkcast android/content/Context
    //   70: invokestatic b : (Landroid/content/Context;)Z
    //   73: ifeq -> 15
    //   76: aload_0
    //   77: getfield o : Landroid/content/SharedPreferences;
    //   80: ldc_w 'GoogleAdMobDoritosLife'
    //   83: ldc2_w 60000
    //   86: invokeinterface getLong : (Ljava/lang/String;J)J
    //   91: lstore_2
    //   92: aload_0
    //   93: getfield b : Lcom/google/ads/m;
    //   96: getfield f : Lcom/google/ads/util/i$b;
    //   99: invokevirtual a : ()Ljava/lang/Object;
    //   102: checkcast android/content/Context
    //   105: lload_2
    //   106: invokestatic a : (Landroid/content/Context;J)Z
    //   109: ifeq -> 128
    //   112: aload_0
    //   113: getfield b : Lcom/google/ads/m;
    //   116: getfield e : Lcom/google/ads/util/i$d;
    //   119: invokevirtual a : ()Ljava/lang/Object;
    //   122: checkcast android/app/Activity
    //   125: invokestatic a : (Landroid/app/Activity;)V
    //   128: aload_0
    //   129: iconst_0
    //   130: putfield k : Z
    //   133: aload_0
    //   134: getfield s : Ljava/util/LinkedList;
    //   137: invokevirtual clear : ()V
    //   140: aload_0
    //   141: aload_1
    //   142: putfield d : Lcom/google/ads/AdRequest;
    //   145: aload_0
    //   146: getfield w : Lcom/google/ads/d;
    //   149: invokevirtual a : ()Z
    //   152: ifeq -> 173
    //   155: aload_0
    //   156: getfield x : Lcom/google/ads/e;
    //   159: aload_0
    //   160: getfield w : Lcom/google/ads/d;
    //   163: invokevirtual b : ()Lcom/google/ads/c;
    //   166: aload_1
    //   167: invokevirtual a : (Lcom/google/ads/c;Lcom/google/ads/AdRequest;)V
    //   170: goto -> 15
    //   173: new com/google/ads/internal/c
    //   176: astore #4
    //   178: aload #4
    //   180: aload_0
    //   181: invokespecial <init> : (Lcom/google/ads/internal/d;)V
    //   184: aload_0
    //   185: aload #4
    //   187: putfield c : Lcom/google/ads/internal/c;
    //   190: aload_0
    //   191: getfield c : Lcom/google/ads/internal/c;
    //   194: aload_1
    //   195: invokevirtual a : (Lcom/google/ads/AdRequest;)V
    //   198: goto -> 15
    // Exception table:
    //   from	to	target	type
    //   2	15	33	finally
    //   18	30	33	finally
    //   38	128	33	finally
    //   128	170	33	finally
    //   173	198	33	finally
  }
  
  public void a(c paramc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aconst_null
    //   4: putfield c : Lcom/google/ads/internal/c;
    //   7: aload_1
    //   8: invokevirtual d : ()Z
    //   11: ifeq -> 49
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual e : ()I
    //   19: i2f
    //   20: invokevirtual a : (F)V
    //   23: aload_0
    //   24: getfield l : Z
    //   27: ifne -> 34
    //   30: aload_0
    //   31: invokevirtual f : ()V
    //   34: aload_0
    //   35: getfield x : Lcom/google/ads/e;
    //   38: aload_1
    //   39: aload_0
    //   40: getfield d : Lcom/google/ads/AdRequest;
    //   43: invokevirtual a : (Lcom/google/ads/c;Lcom/google/ads/AdRequest;)V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: aload_0
    //   50: getfield l : Z
    //   53: ifeq -> 34
    //   56: aload_0
    //   57: invokevirtual e : ()V
    //   60: goto -> 34
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	63	finally
    //   34	46	63	finally
    //   49	60	63	finally
  }
  
  public void a(f paramf, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic java/util/Locale.US : Ljava/util/Locale;
    //   5: ldc_w 'AdManager.onGWhirlAdClicked(%b) called.'
    //   8: iconst_1
    //   9: anewarray java/lang/Object
    //   12: dup
    //   13: iconst_0
    //   14: iload_2
    //   15: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   18: aastore
    //   19: invokestatic format : (Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   22: invokestatic a : (Ljava/lang/String;)V
    //   25: aload_0
    //   26: aload_1
    //   27: iload_2
    //   28: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   31: invokespecial b : (Lcom/google/ads/f;Ljava/lang/Boolean;)V
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	37	finally
  }
  
  public void a(Runnable paramRunnable) {
    this.h.post(paramRunnable);
  }
  
  public void a(String paramString) {
    Uri uri = (new Uri.Builder()).encodedQuery(paramString).build();
    StringBuilder stringBuilder = new StringBuilder();
    HashMap hashMap = AdUtil.b(uri);
    for (String str : hashMap.keySet())
      stringBuilder.append(str).append(" = ").append((String)hashMap.get(str)).append("\n"); 
    this.z = stringBuilder.toString().trim();
    if (TextUtils.isEmpty(this.z))
      this.z = null; 
  }
  
  public void a(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Lcom/google/ads/m;
    //   6: getfield n : Lcom/google/ads/util/i$c;
    //   9: invokevirtual a : ()Ljava/lang/Object;
    //   12: checkcast com/google/ads/AppEventListener
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull -> 41
    //   20: aload_3
    //   21: aload_0
    //   22: getfield b : Lcom/google/ads/m;
    //   25: getfield h : Lcom/google/ads/util/i$b;
    //   28: invokevirtual a : ()Ljava/lang/Object;
    //   31: checkcast com/google/ads/Ad
    //   34: aload_1
    //   35: aload_2
    //   36: invokeinterface a : (Lcom/google/ads/Ad;Ljava/lang/String;Ljava/lang/String;)V
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	44	finally
    //   20	41	44	finally
  }
  
  protected void a(LinkedList paramLinkedList) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual iterator : ()Ljava/util/Iterator;
    //   6: astore_3
    //   7: aload_3
    //   8: invokeinterface hasNext : ()Z
    //   13: ifeq -> 61
    //   16: aload_3
    //   17: invokeinterface next : ()Ljava/lang/Object;
    //   22: checkcast java/lang/String
    //   25: astore #4
    //   27: new java/lang/StringBuilder
    //   30: astore_2
    //   31: aload_2
    //   32: invokespecial <init> : ()V
    //   35: aload_2
    //   36: ldc_w 'Adding a click tracking URL: '
    //   39: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload #4
    //   44: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual toString : ()Ljava/lang/String;
    //   50: invokestatic a : (Ljava/lang/String;)V
    //   53: goto -> 7
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    //   61: aload_0
    //   62: aload_1
    //   63: putfield t : Ljava/util/LinkedList;
    //   66: aload_0
    //   67: monitorexit
    //   68: return
    // Exception table:
    //   from	to	target	type
    //   2	7	56	finally
    //   7	53	56	finally
    //   61	66	56	finally
  }
  
  public void a(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield j : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield x : Lcom/google/ads/e;
    //   6: ifnull -> 16
    //   9: aload_0
    //   10: getfield x : Lcom/google/ads/e;
    //   13: invokevirtual b : ()V
    //   16: aload_0
    //   17: getfield b : Lcom/google/ads/m;
    //   20: getfield m : Lcom/google/ads/util/i$c;
    //   23: aconst_null
    //   24: invokevirtual a : (Ljava/lang/Object;)V
    //   27: aload_0
    //   28: getfield b : Lcom/google/ads/m;
    //   31: getfield n : Lcom/google/ads/util/i$c;
    //   34: aconst_null
    //   35: invokevirtual a : (Ljava/lang/Object;)V
    //   38: aload_0
    //   39: invokevirtual z : ()V
    //   42: aload_0
    //   43: getfield f : Lcom/google/ads/internal/AdWebView;
    //   46: ifnull -> 56
    //   49: aload_0
    //   50: getfield f : Lcom/google/ads/internal/AdWebView;
    //   53: invokevirtual destroy : ()V
    //   56: aload_0
    //   57: monitorexit
    //   58: return
    //   59: astore_1
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_1
    //   63: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	59	finally
    //   16	56	59	finally
  }
  
  public void b(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lload_1
    //   3: lconst_0
    //   4: lcmp
    //   5: ifle -> 32
    //   8: aload_0
    //   9: getfield o : Landroid/content/SharedPreferences;
    //   12: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   17: ldc_w 'GoogleAdMobDoritosLife'
    //   20: lload_1
    //   21: invokeinterface putLong : (Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;
    //   26: invokeinterface commit : ()Z
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_3
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_3
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   8	32	35	finally
  }
  
  public void b(c paramc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 'AdManager.onGWhirlNoFill() called.'
    //   5: invokestatic a : (Ljava/lang/String;)V
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual i : ()Ljava/util/List;
    //   13: aload_1
    //   14: invokevirtual c : ()Ljava/lang/String;
    //   17: invokespecial a : (Ljava/util/List;Ljava/lang/String;)V
    //   20: aload_0
    //   21: getfield b : Lcom/google/ads/m;
    //   24: getfield m : Lcom/google/ads/util/i$c;
    //   27: invokevirtual a : ()Ljava/lang/Object;
    //   30: checkcast com/google/ads/AdListener
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull -> 60
    //   38: aload_1
    //   39: aload_0
    //   40: getfield b : Lcom/google/ads/m;
    //   43: getfield h : Lcom/google/ads/util/i$b;
    //   46: invokevirtual a : ()Ljava/lang/Object;
    //   49: checkcast com/google/ads/Ad
    //   52: getstatic com/google/ads/AdRequest$ErrorCode.b : Lcom/google/ads/AdRequest$ErrorCode;
    //   55: invokeinterface onFailedToReceiveAd : (Lcom/google/ads/Ad;Lcom/google/ads/AdRequest$ErrorCode;)V
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	63	finally
    //   38	60	63	finally
  }
  
  protected void b(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: aload_2
    //   11: ldc_w 'Adding a tracking URL: '
    //   14: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_1
    //   18: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual toString : ()Ljava/lang/String;
    //   24: invokestatic a : (Ljava/lang/String;)V
    //   27: aload_0
    //   28: getfield s : Ljava/util/LinkedList;
    //   31: aload_1
    //   32: invokevirtual add : (Ljava/lang/Object;)Z
    //   35: pop
    //   36: aload_0
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	39	finally
  }
  
  public void b(boolean paramBoolean) {
    this.v = Boolean.valueOf(paramBoolean);
  }
  
  public String c() {
    return this.z;
  }
  
  public void d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield m : Z
    //   7: ldc_w 'Refreshing is no longer allowed on this AdView.'
    //   10: invokestatic a : (Ljava/lang/String;)V
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
  }
  
  public void e() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield l : Z
    //   6: ifeq -> 34
    //   9: ldc_w 'Disabling refreshing.'
    //   12: invokestatic a : (Ljava/lang/String;)V
    //   15: aload_0
    //   16: getfield h : Landroid/os/Handler;
    //   19: aload_0
    //   20: getfield q : Lcom/google/ads/ae;
    //   23: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   26: aload_0
    //   27: iconst_0
    //   28: putfield l : Z
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: ldc_w 'Refreshing is already disabled.'
    //   37: invokestatic a : (Ljava/lang/String;)V
    //   40: goto -> 31
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	43	finally
    //   34	40	43	finally
  }
  
  public void f() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield n : Z
    //   7: aload_0
    //   8: getfield b : Lcom/google/ads/m;
    //   11: invokevirtual a : ()Z
    //   14: ifeq -> 112
    //   17: aload_0
    //   18: getfield m : Z
    //   21: ifeq -> 103
    //   24: aload_0
    //   25: getfield l : Z
    //   28: ifne -> 89
    //   31: new java/lang/StringBuilder
    //   34: astore_1
    //   35: aload_1
    //   36: invokespecial <init> : ()V
    //   39: aload_1
    //   40: ldc_w 'Enabling refreshing every '
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_0
    //   47: getfield p : J
    //   50: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   53: ldc_w ' milliseconds.'
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokestatic a : (Ljava/lang/String;)V
    //   65: aload_0
    //   66: getfield h : Landroid/os/Handler;
    //   69: aload_0
    //   70: getfield q : Lcom/google/ads/ae;
    //   73: aload_0
    //   74: getfield p : J
    //   77: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   80: pop
    //   81: aload_0
    //   82: iconst_1
    //   83: putfield l : Z
    //   86: aload_0
    //   87: monitorexit
    //   88: return
    //   89: ldc_w 'Refreshing is already enabled.'
    //   92: invokestatic a : (Ljava/lang/String;)V
    //   95: goto -> 86
    //   98: astore_1
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    //   103: ldc_w 'Refreshing disabled on this AdView'
    //   106: invokestatic a : (Ljava/lang/String;)V
    //   109: goto -> 86
    //   112: ldc_w 'Tried to enable refreshing on something other than an AdView.'
    //   115: invokestatic a : (Ljava/lang/String;)V
    //   118: goto -> 86
    // Exception table:
    //   from	to	target	type
    //   2	86	98	finally
    //   89	95	98	finally
    //   103	109	98	finally
    //   112	118	98	finally
  }
  
  public void g() {
    f();
    this.n = true;
  }
  
  public m h() {
    return this.b;
  }
  
  public com.google.ads.d i() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield w : Lcom/google/ads/d;
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
  
  public c j() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Lcom/google/ads/internal/c;
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
  
  public AdWebView k() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield f : Lcom/google/ads/internal/AdWebView;
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
  
  public i l() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Lcom/google/ads/internal/i;
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
  
  public g m() {
    return this.e;
  }
  
  public int n() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield u : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public long o() {
    return this.i;
  }
  
  public boolean p() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Lcom/google/ads/internal/c;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public boolean q() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield j : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public boolean r() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield l : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void s() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield e : Lcom/google/ads/internal/g;
    //   6: invokevirtual C : ()V
    //   9: ldc_w 'onDismissScreen()'
    //   12: invokestatic c : (Ljava/lang/String;)V
    //   15: aload_0
    //   16: getfield b : Lcom/google/ads/m;
    //   19: getfield m : Lcom/google/ads/util/i$c;
    //   22: invokevirtual a : ()Ljava/lang/Object;
    //   25: checkcast com/google/ads/AdListener
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull -> 52
    //   33: aload_1
    //   34: aload_0
    //   35: getfield b : Lcom/google/ads/m;
    //   38: getfield h : Lcom/google/ads/util/i$b;
    //   41: invokevirtual a : ()Ljava/lang/Object;
    //   44: checkcast com/google/ads/Ad
    //   47: invokeinterface onDismissScreen : (Lcom/google/ads/Ad;)V
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	55	finally
    //   33	52	55	finally
  }
  
  public void t() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 'onPresentScreen()'
    //   5: invokestatic c : (Ljava/lang/String;)V
    //   8: aload_0
    //   9: getfield b : Lcom/google/ads/m;
    //   12: getfield m : Lcom/google/ads/util/i$c;
    //   15: invokevirtual a : ()Ljava/lang/Object;
    //   18: checkcast com/google/ads/AdListener
    //   21: astore_1
    //   22: aload_1
    //   23: ifnull -> 45
    //   26: aload_1
    //   27: aload_0
    //   28: getfield b : Lcom/google/ads/m;
    //   31: getfield h : Lcom/google/ads/util/i$b;
    //   34: invokevirtual a : ()Ljava/lang/Object;
    //   37: checkcast com/google/ads/Ad
    //   40: invokeinterface onPresentScreen : (Lcom/google/ads/Ad;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	48	finally
    //   26	45	48	finally
  }
  
  public void u() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 'onLeaveApplication()'
    //   5: invokestatic c : (Ljava/lang/String;)V
    //   8: aload_0
    //   9: getfield b : Lcom/google/ads/m;
    //   12: getfield m : Lcom/google/ads/util/i$c;
    //   15: invokevirtual a : ()Ljava/lang/Object;
    //   18: checkcast com/google/ads/AdListener
    //   21: astore_1
    //   22: aload_1
    //   23: ifnull -> 45
    //   26: aload_1
    //   27: aload_0
    //   28: getfield b : Lcom/google/ads/m;
    //   31: getfield h : Lcom/google/ads/util/i$b;
    //   34: invokevirtual a : ()Ljava/lang/Object;
    //   37: checkcast com/google/ads/Ad
    //   40: invokeinterface onLeaveApplication : (Lcom/google/ads/Ad;)V
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	48	finally
    //   26	45	48	finally
  }
  
  public void v() {
    this.e.f();
    A();
  }
  
  public void w() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Lcom/google/ads/m;
    //   6: getfield e : Lcom/google/ads/util/i$d;
    //   9: invokevirtual a : ()Ljava/lang/Object;
    //   12: checkcast android/app/Activity
    //   15: astore #4
    //   17: aload #4
    //   19: ifnonnull -> 31
    //   22: ldc_w 'activity was null while trying to ping tracking URLs.'
    //   25: invokestatic e : (Ljava/lang/String;)V
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: aload_0
    //   32: getfield s : Ljava/util/LinkedList;
    //   35: invokevirtual iterator : ()Ljava/util/Iterator;
    //   38: astore_1
    //   39: aload_1
    //   40: invokeinterface hasNext : ()Z
    //   45: ifeq -> 28
    //   48: aload_1
    //   49: invokeinterface next : ()Ljava/lang/Object;
    //   54: checkcast java/lang/String
    //   57: astore #5
    //   59: new java/lang/Thread
    //   62: astore_3
    //   63: new com/google/ads/ac
    //   66: astore_2
    //   67: aload_2
    //   68: aload #5
    //   70: aload #4
    //   72: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   75: invokespecial <init> : (Ljava/lang/String;Landroid/content/Context;)V
    //   78: aload_3
    //   79: aload_2
    //   80: invokespecial <init> : (Ljava/lang/Runnable;)V
    //   83: aload_3
    //   84: invokevirtual start : ()V
    //   87: goto -> 39
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	90	finally
    //   22	28	90	finally
    //   31	39	90	finally
    //   39	87	90	finally
  }
  
  public void x() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield d : Lcom/google/ads/AdRequest;
    //   6: ifnull -> 114
    //   9: aload_0
    //   10: getfield b : Lcom/google/ads/m;
    //   13: invokevirtual a : ()Z
    //   16: ifeq -> 105
    //   19: aload_0
    //   20: getfield b : Lcom/google/ads/m;
    //   23: getfield i : Lcom/google/ads/util/i$b;
    //   26: invokevirtual a : ()Ljava/lang/Object;
    //   29: checkcast com/google/ads/AdView
    //   32: invokevirtual isShown : ()Z
    //   35: ifeq -> 72
    //   38: invokestatic d : ()Z
    //   41: ifeq -> 72
    //   44: ldc_w 'Refreshing ad.'
    //   47: invokestatic c : (Ljava/lang/String;)V
    //   50: aload_0
    //   51: aload_0
    //   52: getfield d : Lcom/google/ads/AdRequest;
    //   55: invokevirtual a : (Lcom/google/ads/AdRequest;)V
    //   58: aload_0
    //   59: getfield n : Z
    //   62: ifeq -> 86
    //   65: aload_0
    //   66: invokevirtual e : ()V
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: ldc_w 'Not refreshing because the ad is not visible.'
    //   75: invokestatic a : (Ljava/lang/String;)V
    //   78: goto -> 58
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    //   86: aload_0
    //   87: getfield h : Landroid/os/Handler;
    //   90: aload_0
    //   91: getfield q : Lcom/google/ads/ae;
    //   94: aload_0
    //   95: getfield p : J
    //   98: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   101: pop
    //   102: goto -> 69
    //   105: ldc_w 'Tried to refresh an ad that wasn't an AdView.'
    //   108: invokestatic a : (Ljava/lang/String;)V
    //   111: goto -> 69
    //   114: ldc_w 'Tried to refresh before calling loadAd().'
    //   117: invokestatic a : (Ljava/lang/String;)V
    //   120: goto -> 69
    // Exception table:
    //   from	to	target	type
    //   2	58	81	finally
    //   58	69	81	finally
    //   72	78	81	finally
    //   86	102	81	finally
    //   105	111	81	finally
    //   114	120	81	finally
  }
  
  public void y() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Lcom/google/ads/m;
    //   6: invokevirtual b : ()Z
    //   9: invokestatic a : (Z)V
    //   12: aload_0
    //   13: getfield k : Z
    //   16: ifeq -> 103
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield k : Z
    //   24: aload_0
    //   25: getfield v : Ljava/lang/Boolean;
    //   28: ifnonnull -> 40
    //   31: ldc_w 'isMediationFlag is null in show() with isReady() true. we should have an ad and know whether this is a mediation request or not. '
    //   34: invokestatic b : (Ljava/lang/String;)V
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: aload_0
    //   41: getfield v : Ljava/lang/Boolean;
    //   44: invokevirtual booleanValue : ()Z
    //   47: ifeq -> 80
    //   50: aload_0
    //   51: getfield x : Lcom/google/ads/e;
    //   54: invokevirtual c : ()Z
    //   57: ifeq -> 37
    //   60: aload_0
    //   61: aload_0
    //   62: getfield y : Lcom/google/ads/f;
    //   65: iconst_0
    //   66: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   69: invokespecial a : (Lcom/google/ads/f;Ljava/lang/Boolean;)V
    //   72: goto -> 37
    //   75: astore_1
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    //   80: new com/google/ads/internal/e
    //   83: astore_1
    //   84: aload_1
    //   85: ldc_w 'interstitial'
    //   88: invokespecial <init> : (Ljava/lang/String;)V
    //   91: aload_0
    //   92: aload_1
    //   93: invokestatic a : (Lcom/google/ads/internal/d;Lcom/google/ads/internal/e;)V
    //   96: aload_0
    //   97: invokevirtual w : ()V
    //   100: goto -> 37
    //   103: ldc_w 'Cannot show interstitial because it is not loaded and ready.'
    //   106: invokestatic c : (Ljava/lang/String;)V
    //   109: goto -> 37
    // Exception table:
    //   from	to	target	type
    //   2	37	75	finally
    //   40	72	75	finally
    //   80	100	75	finally
    //   103	109	75	finally
  }
  
  public void z() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Lcom/google/ads/internal/c;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield c : Lcom/google/ads/internal/c;
    //   13: invokevirtual a : ()V
    //   16: aload_0
    //   17: aconst_null
    //   18: putfield c : Lcom/google/ads/internal/c;
    //   21: aload_0
    //   22: getfield f : Lcom/google/ads/internal/AdWebView;
    //   25: ifnull -> 35
    //   28: aload_0
    //   29: getfield f : Lcom/google/ads/internal/AdWebView;
    //   32: invokevirtual stopLoading : ()V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	38	finally
    //   21	35	38	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */