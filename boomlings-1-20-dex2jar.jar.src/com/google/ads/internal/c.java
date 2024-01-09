package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.b;
import com.google.ads.d;
import com.google.ads.l;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;

public class c implements Runnable {
  boolean a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String e;
  
  private boolean f;
  
  private f g;
  
  private d h;
  
  private AdRequest i;
  
  private WebView j;
  
  private String k;
  
  private LinkedList l;
  
  private String m;
  
  private AdSize n;
  
  private volatile boolean o;
  
  private boolean p;
  
  private AdRequest.ErrorCode q;
  
  private boolean r;
  
  private int s;
  
  private Thread t;
  
  private boolean u;
  
  private c$d v = c$d.b;
  
  protected c() {}
  
  public c(d paramd) {
    this.h = paramd;
    this.k = null;
    this.b = null;
    this.c = null;
    this.d = null;
    this.l = new LinkedList();
    this.q = null;
    this.r = false;
    this.s = -1;
    this.f = false;
    this.p = false;
    this.m = null;
    this.n = null;
    if ((Activity)(paramd.h()).e.a() != null) {
      this.j = new AdWebView(paramd.h(), null);
      this.j.setWebViewClient(i.a(paramd, a.b, false, false));
      this.j.setVisibility(8);
      this.j.setWillNotDraw(true);
      this.g = new f(this, paramd);
      return;
    } 
    this.j = null;
    this.g = null;
    b.e("activity was null while trying to create an AdLoader.");
  }
  
  static void a(String paramString, com.google.ads.c paramc, d paramd) {
    if (paramString != null && !paramString.contains("no-store") && !paramString.contains("no-cache")) {
      Matcher matcher = Pattern.compile("max-age\\s*=\\s*(\\d+)").matcher(paramString);
      if (matcher.find()) {
        try {
          int i = Integer.parseInt(matcher.group(1));
          paramd.a(paramc, i);
          b.c(String.format(Locale.US, "Caching gWhirl configuration for: %d seconds", new Object[] { Integer.valueOf(i) }));
        } catch (NumberFormatException numberFormatException) {
          b.b("Caught exception trying to parse cache control directive. Overflow?", numberFormatException);
        } 
        return;
      } 
      b.c("Unrecognized cacheControlDirective: '" + numberFormatException + "'. Not caching configuration.");
    } 
  }
  
  private void b(String paramString1, String paramString2) {
    this.h.a(new t(this, this.j, paramString2, paramString1));
  }
  
  private String d() {
    if (this.i instanceof com.google.ads.searchads.SearchAdRequest);
    return "AFMA_buildAdURL";
  }
  
  private String e() {
    if (this.i instanceof com.google.ads.searchads.SearchAdRequest);
    return "AFMA_getSdkConstants();";
  }
  
  private String f() {
    return (this.i instanceof com.google.ads.searchads.SearchAdRequest) ? "http://www.gstatic.com/safa/" : "http://media.admob.com/";
  }
  
  private String g() {
    return (this.i instanceof com.google.ads.searchads.SearchAdRequest) ? "<html><head><script src=\"http://www.gstatic.com/safa/sdk-core-v40.js\"></script><script>" : "<html><head><script src=\"http://media.admob.com/sdk-core-v40.js\"></script><script>";
  }
  
  private String h() {
    if (this.i instanceof com.google.ads.searchads.SearchAdRequest);
    return "</script></head><body></body></html>";
  }
  
  private void i() {
    AdWebView adWebView = this.h.k();
    this.h.l().c(true);
    this.h.m().h();
    this.h.a(new t(this, adWebView, this.b, this.c));
  }
  
  private void j() {
    this.h.a(new u(this.h, this.j, this.l, this.s, this.p, this.m, this.n));
  }
  
  public String a(Map<String, Long> paramMap, Activity paramActivity) {
    boolean bool = false;
    Context context = paramActivity.getApplicationContext();
    g g = this.h.m();
    long l = g.m();
    if (l > 0L)
      paramMap.put("prl", Long.valueOf(l)); 
    l = g.n();
    if (l > 0L)
      paramMap.put("prnl", Long.valueOf(l)); 
    String str3 = g.l();
    if (str3 != null)
      paramMap.put("ppcl", str3); 
    str3 = g.k();
    if (str3 != null)
      paramMap.put("pcl", str3); 
    l = g.j();
    if (l > 0L)
      paramMap.put("pcc", Long.valueOf(l)); 
    paramMap.put("preqs", Long.valueOf(g.o()));
    paramMap.put("oar", Long.valueOf(g.p()));
    paramMap.put("bas_on", Long.valueOf(g.s()));
    paramMap.put("bas_off", Long.valueOf(g.v()));
    if (g.y())
      paramMap.put("aoi_timeout", "true"); 
    if (g.A())
      paramMap.put("aoi_nofill", "true"); 
    str3 = g.D();
    if (str3 != null)
      paramMap.put("pit", str3); 
    paramMap.put("ptime", Long.valueOf(g.E()));
    g.a();
    g.i();
    if (this.h.h().b()) {
      paramMap.put("format", "interstitial_mb");
    } else {
      AdSize adSize = ((h)(this.h.h()).k.a()).b();
      if (adSize.c())
        paramMap.put("smart_w", "full"); 
      if (adSize.d())
        paramMap.put("smart_h", "auto"); 
      if (!adSize.e()) {
        paramMap.put("format", adSize.toString());
      } else {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put("w", Integer.valueOf(adSize.a()));
        hashMap.put("h", Integer.valueOf(adSize.b()));
        paramMap.put("ad_frame", hashMap);
      } 
    } 
    paramMap.put("slotname", (this.h.h()).d.a());
    paramMap.put("js", "afma-sdk-a-v6.2.1");
    String str2 = context.getPackageName();
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str2, 0);
      int i = packageInfo.versionCode;
      String str = AdUtil.f(context);
      if (!TextUtils.isEmpty(str))
        paramMap.put("mv", str); 
      paramMap.put("msid", context.getPackageName());
      paramMap.put("app_name", i + ".android." + context.getPackageName());
      paramMap.put("isu", AdUtil.a(context));
      str3 = AdUtil.d(context);
      str = str3;
      if (str3 == null)
        str = "null"; 
      paramMap.put("net", str);
      str = AdUtil.e(context);
      if (str != null && str.length() != 0)
        paramMap.put("cap", str); 
      paramMap.put("u_audio", Integer.valueOf(AdUtil.g(context).ordinal()));
      DisplayMetrics displayMetrics = AdUtil.a(paramActivity);
      paramMap.put("u_sd", Float.valueOf(displayMetrics.density));
      paramMap.put("u_h", Integer.valueOf(AdUtil.a(context, displayMetrics)));
      paramMap.put("u_w", Integer.valueOf(AdUtil.b(context, displayMetrics)));
      paramMap.put("hl", Locale.getDefault().getLanguage());
      if ((this.h.h()).i != null && (this.h.h()).i.a() != null) {
        AdView adView = (AdView)(this.h.h()).i.a();
        if (adView.getParent() != null) {
          int[] arrayOfInt = new int[2];
          adView.getLocationOnScreen(arrayOfInt);
          int k = arrayOfInt[0];
          int j = arrayOfInt[1];
          DisplayMetrics displayMetrics1 = ((Context)(this.h.h()).f.a()).getResources().getDisplayMetrics();
          int m = displayMetrics1.widthPixels;
          i = displayMetrics1.heightPixels;
          if (adView.isShown() && adView.getWidth() + k > 0 && adView.getHeight() + j > 0 && k <= m && j <= i) {
            i = 1;
          } else {
            i = 0;
          } 
          HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
          hashMap.put("x", Integer.valueOf(k));
          hashMap.put("y", Integer.valueOf(j));
          hashMap.put("width", Integer.valueOf(adView.getWidth()));
          hashMap.put("height", Integer.valueOf(adView.getHeight()));
          hashMap.put("visible", Integer.valueOf(i));
          paramMap.put("ad_pos", hashMap);
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      AdSize[] arrayOfAdSize = (AdSize[])(this.h.h()).l.a();
      if (arrayOfAdSize != null) {
        int j = arrayOfAdSize.length;
        for (i = bool; i < j; i++) {
          AdSize adSize = arrayOfAdSize[i];
          if (stringBuilder.length() != 0)
            stringBuilder.append("|"); 
          stringBuilder.append(adSize.a() + "x" + adSize.b());
        } 
        paramMap.put("sz", stringBuilder.toString());
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new s(this, "NameNotFoundException");
    } 
    TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
    nameNotFoundException.put("carrier", telephonyManager.getNetworkOperator());
    nameNotFoundException.put("gnt", Integer.valueOf(telephonyManager.getNetworkType()));
    if (AdUtil.c())
      nameNotFoundException.put("simulator", Integer.valueOf(1)); 
    nameNotFoundException.put("session_id", b.a().b().toString());
    nameNotFoundException.put("seq_num", b.a().c().toString());
    String str1 = AdUtil.a((Map)nameNotFoundException);
    if (((Boolean)((l.a)((l)(this.h.h()).a.a()).a.a()).l.a()).booleanValue()) {
      str1 = g() + d() + "(" + str1 + ");" + h();
      b.c("adRequestUrlHtml: " + str1);
      return str1;
    } 
    str1 = g() + e() + d() + "(" + str1 + ");" + h();
    b.c("adRequestUrlHtml: " + str1);
    return str1;
  }
  
  protected void a() {
    b.a("AdLoader cancelled.");
    if (this.j != null) {
      this.j.stopLoading();
      this.j.destroy();
    } 
    if (this.t != null) {
      this.t.interrupt();
      this.t = null;
    } 
    if (this.g != null)
      this.g.a(); 
    this.o = true;
  }
  
  public void a(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield s : I
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
  
  public void a(AdRequest.ErrorCode paramErrorCode) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield q : Lcom/google/ads/AdRequest$ErrorCode;
    //   7: aload_0
    //   8: invokevirtual notify : ()V
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
    //   2	11	14	finally
  }
  
  protected void a(AdRequest.ErrorCode paramErrorCode, boolean paramBoolean) {
    this.h.a(new r(this.h, this.j, this.g, paramErrorCode, paramBoolean));
  }
  
  protected void a(AdRequest paramAdRequest) {
    this.i = paramAdRequest;
    this.o = false;
    this.t = new Thread(this);
    this.t.start();
  }
  
  public void a(AdSize paramAdSize) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield n : Lcom/google/ads/AdSize;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void a(c$d paramc$d) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield v : Lcom/google/ads/internal/c$d;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  protected void a(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield l : Ljava/util/LinkedList;
    //   6: aload_1
    //   7: invokevirtual add : (Ljava/lang/Object;)Z
    //   10: pop
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
    //   2	11	14	finally
  }
  
  protected void a(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: putfield b : Ljava/lang/String;
    //   7: aload_0
    //   8: aload_1
    //   9: putfield c : Ljava/lang/String;
    //   12: aload_0
    //   13: invokevirtual notify : ()V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  protected void a(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield f : Z
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
  
  protected void b() {
    try {
      if (TextUtils.isEmpty(this.e)) {
        b.b("Got a mediation response with no content type. Aborting mediation.");
        a(AdRequest.ErrorCode.d, false);
        return;
      } 
      if (!this.e.startsWith("application/json")) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        b.b(stringBuilder.append("Got a mediation response with a content type: '").append(this.e).append("'. Expected something starting with 'application/json'. Aborting mediation.").toString());
        a(AdRequest.ErrorCode.d, false);
        return;
      } 
    } catch (JSONException jSONException) {
      b.b("AdLoader can't parse gWhirl server configuration.", (Throwable)jSONException);
      a(AdRequest.ErrorCode.d, false);
      return;
    } 
    com.google.ads.c c1 = com.google.ads.c.a(this.c);
    a(this.d, c1, this.h.i());
    d d1 = this.h;
    p p = new p();
    this(this, c1);
    d1.a(p);
  }
  
  protected void b(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield e : Ljava/lang/String;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void b(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield p : Z
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
  
  protected void c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield r : Z
    //   7: aload_0
    //   8: invokevirtual notify : ()V
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
    //   2	11	14	finally
  }
  
  protected void c(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield d : Ljava/lang/String;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void c(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield u : Z
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
  
  public void d(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield k : Ljava/lang/String;
    //   7: aload_0
    //   8: invokevirtual notify : ()V
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
    //   2	11	14	finally
  }
  
  public void d(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield a : Z
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
  
  public void e(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield m : Ljava/lang/String;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield j : Landroid/webkit/WebView;
    //   6: ifnull -> 16
    //   9: aload_0
    //   10: getfield g : Lcom/google/ads/internal/f;
    //   13: ifnonnull -> 33
    //   16: ldc_w 'adRequestWebView was null while trying to load an ad.'
    //   19: invokestatic e : (Ljava/lang/String;)V
    //   22: aload_0
    //   23: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   26: iconst_0
    //   27: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: aload_0
    //   34: getfield h : Lcom/google/ads/internal/d;
    //   37: invokevirtual h : ()Lcom/google/ads/m;
    //   40: getfield e : Lcom/google/ads/util/i$d;
    //   43: invokevirtual a : ()Ljava/lang/Object;
    //   46: checkcast android/app/Activity
    //   49: astore #9
    //   51: aload #9
    //   53: ifnonnull -> 82
    //   56: ldc_w 'activity was null while forming an ad request.'
    //   59: invokestatic e : (Ljava/lang/String;)V
    //   62: aload_0
    //   63: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   66: iconst_0
    //   67: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   70: aload_0
    //   71: monitorexit
    //   72: goto -> 32
    //   75: astore #8
    //   77: aload_0
    //   78: monitorexit
    //   79: aload #8
    //   81: athrow
    //   82: aload_0
    //   83: getfield h : Lcom/google/ads/internal/d;
    //   86: invokevirtual o : ()J
    //   89: lstore_1
    //   90: invokestatic elapsedRealtime : ()J
    //   93: lstore_3
    //   94: aload_0
    //   95: getfield i : Lcom/google/ads/AdRequest;
    //   98: aload_0
    //   99: getfield h : Lcom/google/ads/internal/d;
    //   102: invokevirtual h : ()Lcom/google/ads/m;
    //   105: getfield f : Lcom/google/ads/util/i$b;
    //   108: invokevirtual a : ()Ljava/lang/Object;
    //   111: checkcast android/content/Context
    //   114: invokevirtual a : (Landroid/content/Context;)Ljava/util/Map;
    //   117: astore #8
    //   119: aload #8
    //   121: ldc_w 'extras'
    //   124: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   129: astore #10
    //   131: aload #10
    //   133: instanceof java/util/Map
    //   136: ifeq -> 278
    //   139: aload #10
    //   141: checkcast java/util/Map
    //   144: astore #10
    //   146: aload #10
    //   148: ldc_w '_adUrl'
    //   151: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   156: astore #11
    //   158: aload #11
    //   160: instanceof java/lang/String
    //   163: ifeq -> 175
    //   166: aload_0
    //   167: aload #11
    //   169: checkcast java/lang/String
    //   172: putfield b : Ljava/lang/String;
    //   175: aload #10
    //   177: ldc_w '_requestUrl'
    //   180: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   185: astore #11
    //   187: aload #11
    //   189: instanceof java/lang/String
    //   192: ifeq -> 204
    //   195: aload_0
    //   196: aload #11
    //   198: checkcast java/lang/String
    //   201: putfield k : Ljava/lang/String;
    //   204: aload #10
    //   206: ldc_w '_orientation'
    //   209: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   214: astore #11
    //   216: aload #11
    //   218: instanceof java/lang/String
    //   221: ifeq -> 240
    //   224: aload #11
    //   226: ldc_w 'p'
    //   229: invokevirtual equals : (Ljava/lang/Object;)Z
    //   232: ifeq -> 358
    //   235: aload_0
    //   236: iconst_1
    //   237: putfield s : I
    //   240: aload #10
    //   242: ldc_w '_norefresh'
    //   245: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   250: astore #10
    //   252: aload #10
    //   254: instanceof java/lang/String
    //   257: ifeq -> 278
    //   260: aload #10
    //   262: ldc_w 't'
    //   265: invokevirtual equals : (Ljava/lang/Object;)Z
    //   268: ifeq -> 278
    //   271: aload_0
    //   272: getfield h : Lcom/google/ads/internal/d;
    //   275: invokevirtual d : ()V
    //   278: aload_0
    //   279: getfield b : Ljava/lang/String;
    //   282: ifnonnull -> 918
    //   285: aload_0
    //   286: getfield k : Ljava/lang/String;
    //   289: astore #10
    //   291: aload #10
    //   293: ifnonnull -> 555
    //   296: aload_0
    //   297: aload #8
    //   299: aload #9
    //   301: invokevirtual a : (Ljava/util/Map;Landroid/app/Activity;)Ljava/lang/String;
    //   304: astore #8
    //   306: aload_0
    //   307: aload #8
    //   309: aload_0
    //   310: invokespecial f : ()Ljava/lang/String;
    //   313: invokespecial b : (Ljava/lang/String;Ljava/lang/String;)V
    //   316: invokestatic elapsedRealtime : ()J
    //   319: lstore #5
    //   321: lload_1
    //   322: lload #5
    //   324: lload_3
    //   325: lsub
    //   326: lsub
    //   327: lstore #5
    //   329: lload #5
    //   331: lconst_0
    //   332: lcmp
    //   333: ifle -> 342
    //   336: aload_0
    //   337: lload #5
    //   339: invokevirtual wait : (J)V
    //   342: aload_0
    //   343: getfield o : Z
    //   346: istore #7
    //   348: iload #7
    //   350: ifeq -> 480
    //   353: aload_0
    //   354: monitorexit
    //   355: goto -> 32
    //   358: aload #11
    //   360: ldc_w 'l'
    //   363: invokevirtual equals : (Ljava/lang/Object;)Z
    //   366: ifeq -> 240
    //   369: aload_0
    //   370: iconst_0
    //   371: putfield s : I
    //   374: goto -> 240
    //   377: astore #8
    //   379: ldc_w 'An unknown error occurred in AdLoader.'
    //   382: aload #8
    //   384: invokestatic b : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   387: aload_0
    //   388: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   391: iconst_1
    //   392: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   395: aload_0
    //   396: monitorexit
    //   397: goto -> 32
    //   400: astore #8
    //   402: new java/lang/StringBuilder
    //   405: astore #9
    //   407: aload #9
    //   409: invokespecial <init> : ()V
    //   412: aload #9
    //   414: ldc_w 'Caught internal exception: '
    //   417: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: aload #8
    //   422: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   425: invokevirtual toString : ()Ljava/lang/String;
    //   428: invokestatic c : (Ljava/lang/String;)V
    //   431: aload_0
    //   432: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   435: iconst_0
    //   436: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   439: aload_0
    //   440: monitorexit
    //   441: goto -> 32
    //   444: astore #9
    //   446: new java/lang/StringBuilder
    //   449: astore #8
    //   451: aload #8
    //   453: invokespecial <init> : ()V
    //   456: aload #8
    //   458: ldc_w 'AdLoader InterruptedException while getting the URL: '
    //   461: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: aload #9
    //   466: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   469: invokevirtual toString : ()Ljava/lang/String;
    //   472: invokestatic a : (Ljava/lang/String;)V
    //   475: aload_0
    //   476: monitorexit
    //   477: goto -> 32
    //   480: aload_0
    //   481: getfield q : Lcom/google/ads/AdRequest$ErrorCode;
    //   484: ifnull -> 501
    //   487: aload_0
    //   488: aload_0
    //   489: getfield q : Lcom/google/ads/AdRequest$ErrorCode;
    //   492: iconst_0
    //   493: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   496: aload_0
    //   497: monitorexit
    //   498: goto -> 32
    //   501: aload_0
    //   502: getfield k : Ljava/lang/String;
    //   505: ifnonnull -> 555
    //   508: new java/lang/StringBuilder
    //   511: astore #8
    //   513: aload #8
    //   515: invokespecial <init> : ()V
    //   518: aload #8
    //   520: ldc_w 'AdLoader timed out after '
    //   523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: lload_1
    //   527: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   530: ldc_w 'ms while getting the URL.'
    //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: invokevirtual toString : ()Ljava/lang/String;
    //   539: invokestatic c : (Ljava/lang/String;)V
    //   542: aload_0
    //   543: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   546: iconst_0
    //   547: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   550: aload_0
    //   551: monitorexit
    //   552: goto -> 32
    //   555: aload_0
    //   556: getfield h : Lcom/google/ads/internal/d;
    //   559: invokevirtual m : ()Lcom/google/ads/internal/g;
    //   562: astore #8
    //   564: getstatic com/google/ads/internal/q.a : [I
    //   567: aload_0
    //   568: getfield v : Lcom/google/ads/internal/c$d;
    //   571: invokevirtual ordinal : ()I
    //   574: iaload
    //   575: tableswitch default -> 604, 1 -> 681, 2 -> 705, 3 -> 719, 4 -> 738
    //   604: aload_0
    //   605: getfield a : Z
    //   608: ifne -> 879
    //   611: ldc_w 'Not using loadUrl().'
    //   614: invokestatic a : (Ljava/lang/String;)V
    //   617: aload_0
    //   618: getfield g : Lcom/google/ads/internal/f;
    //   621: aload_0
    //   622: getfield u : Z
    //   625: invokevirtual a : (Z)V
    //   628: aload_0
    //   629: getfield g : Lcom/google/ads/internal/f;
    //   632: aload_0
    //   633: getfield k : Ljava/lang/String;
    //   636: invokevirtual a : (Ljava/lang/String;)V
    //   639: invokestatic elapsedRealtime : ()J
    //   642: lstore #5
    //   644: lload_1
    //   645: lload #5
    //   647: lload_3
    //   648: lsub
    //   649: lsub
    //   650: lstore #5
    //   652: lload #5
    //   654: lconst_0
    //   655: lcmp
    //   656: ifle -> 665
    //   659: aload_0
    //   660: lload #5
    //   662: invokevirtual wait : (J)V
    //   665: aload_0
    //   666: getfield o : Z
    //   669: istore #7
    //   671: iload #7
    //   673: ifeq -> 804
    //   676: aload_0
    //   677: monitorexit
    //   678: goto -> 32
    //   681: aload #8
    //   683: invokevirtual r : ()V
    //   686: aload #8
    //   688: invokevirtual u : ()V
    //   691: aload #8
    //   693: invokevirtual x : ()V
    //   696: ldc_w 'Request scenario: Online server request.'
    //   699: invokestatic c : (Ljava/lang/String;)V
    //   702: goto -> 604
    //   705: aload #8
    //   707: invokevirtual t : ()V
    //   710: ldc_w 'Request scenario: Online using buffered ads.'
    //   713: invokestatic c : (Ljava/lang/String;)V
    //   716: goto -> 604
    //   719: aload #8
    //   721: invokevirtual w : ()V
    //   724: aload #8
    //   726: invokevirtual q : ()V
    //   729: ldc_w 'Request scenario: Offline using buffered ads.'
    //   732: invokestatic c : (Ljava/lang/String;)V
    //   735: goto -> 604
    //   738: aload #8
    //   740: invokevirtual q : ()V
    //   743: ldc_w 'Request scenario: Offline with no buffered ads.'
    //   746: invokestatic c : (Ljava/lang/String;)V
    //   749: ldc_w 'Network is unavailable.  Aborting ad request.'
    //   752: invokestatic c : (Ljava/lang/String;)V
    //   755: aload_0
    //   756: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   759: iconst_0
    //   760: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   763: aload_0
    //   764: monitorexit
    //   765: goto -> 32
    //   768: astore #9
    //   770: new java/lang/StringBuilder
    //   773: astore #8
    //   775: aload #8
    //   777: invokespecial <init> : ()V
    //   780: aload #8
    //   782: ldc_w 'AdLoader InterruptedException while getting the ad server's response: '
    //   785: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: aload #9
    //   790: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   793: invokevirtual toString : ()Ljava/lang/String;
    //   796: invokestatic a : (Ljava/lang/String;)V
    //   799: aload_0
    //   800: monitorexit
    //   801: goto -> 32
    //   804: aload_0
    //   805: getfield q : Lcom/google/ads/AdRequest$ErrorCode;
    //   808: ifnull -> 825
    //   811: aload_0
    //   812: aload_0
    //   813: getfield q : Lcom/google/ads/AdRequest$ErrorCode;
    //   816: iconst_0
    //   817: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   820: aload_0
    //   821: monitorexit
    //   822: goto -> 32
    //   825: aload_0
    //   826: getfield c : Ljava/lang/String;
    //   829: ifnonnull -> 918
    //   832: new java/lang/StringBuilder
    //   835: astore #8
    //   837: aload #8
    //   839: invokespecial <init> : ()V
    //   842: aload #8
    //   844: ldc_w 'AdLoader timed out after '
    //   847: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   850: lload_1
    //   851: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   854: ldc_w 'ms while waiting for the ad server's response.'
    //   857: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   860: invokevirtual toString : ()Ljava/lang/String;
    //   863: invokestatic c : (Ljava/lang/String;)V
    //   866: aload_0
    //   867: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   870: iconst_0
    //   871: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   874: aload_0
    //   875: monitorexit
    //   876: goto -> 32
    //   879: aload_0
    //   880: aload_0
    //   881: getfield k : Ljava/lang/String;
    //   884: putfield b : Ljava/lang/String;
    //   887: new java/lang/StringBuilder
    //   890: astore #8
    //   892: aload #8
    //   894: invokespecial <init> : ()V
    //   897: aload #8
    //   899: ldc_w 'Using loadUrl.  adBaseUrl: '
    //   902: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   905: aload_0
    //   906: getfield b : Ljava/lang/String;
    //   909: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   912: invokevirtual toString : ()Ljava/lang/String;
    //   915: invokestatic a : (Ljava/lang/String;)V
    //   918: aload_0
    //   919: getfield a : Z
    //   922: ifne -> 1167
    //   925: aload_0
    //   926: getfield f : Z
    //   929: ifeq -> 949
    //   932: aload_0
    //   933: getfield h : Lcom/google/ads/internal/d;
    //   936: iconst_1
    //   937: invokevirtual b : (Z)V
    //   940: aload_0
    //   941: invokevirtual b : ()V
    //   944: aload_0
    //   945: monitorexit
    //   946: goto -> 32
    //   949: aload_0
    //   950: getfield e : Ljava/lang/String;
    //   953: ifnull -> 1032
    //   956: aload_0
    //   957: getfield e : Ljava/lang/String;
    //   960: ldc_w 'application/json'
    //   963: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   966: ifne -> 982
    //   969: aload_0
    //   970: getfield e : Ljava/lang/String;
    //   973: ldc_w 'text/javascript'
    //   976: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   979: ifeq -> 1032
    //   982: new java/lang/StringBuilder
    //   985: astore #8
    //   987: aload #8
    //   989: invokespecial <init> : ()V
    //   992: aload #8
    //   994: ldc_w 'Expected HTML but received '
    //   997: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: aload_0
    //   1001: getfield e : Ljava/lang/String;
    //   1004: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1007: ldc_w '.'
    //   1010: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1013: invokevirtual toString : ()Ljava/lang/String;
    //   1016: invokestatic b : (Ljava/lang/String;)V
    //   1019: aload_0
    //   1020: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   1023: iconst_0
    //   1024: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1027: aload_0
    //   1028: monitorexit
    //   1029: goto -> 32
    //   1032: aload_0
    //   1033: getfield h : Lcom/google/ads/internal/d;
    //   1036: invokevirtual h : ()Lcom/google/ads/m;
    //   1039: getfield l : Lcom/google/ads/util/i$c;
    //   1042: invokevirtual a : ()Ljava/lang/Object;
    //   1045: ifnull -> 1149
    //   1048: aload_0
    //   1049: getfield n : Lcom/google/ads/AdSize;
    //   1052: ifnonnull -> 1074
    //   1055: ldc_w 'Multiple supported ad sizes were specified, but the server did not respond with a size.'
    //   1058: invokestatic b : (Ljava/lang/String;)V
    //   1061: aload_0
    //   1062: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   1065: iconst_0
    //   1066: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1069: aload_0
    //   1070: monitorexit
    //   1071: goto -> 32
    //   1074: aload_0
    //   1075: getfield h : Lcom/google/ads/internal/d;
    //   1078: invokevirtual h : ()Lcom/google/ads/m;
    //   1081: getfield l : Lcom/google/ads/util/i$c;
    //   1084: invokevirtual a : ()Ljava/lang/Object;
    //   1087: checkcast [Ljava/lang/Object;
    //   1090: invokestatic asList : ([Ljava/lang/Object;)Ljava/util/List;
    //   1093: aload_0
    //   1094: getfield n : Lcom/google/ads/AdSize;
    //   1097: invokeinterface contains : (Ljava/lang/Object;)Z
    //   1102: ifne -> 1167
    //   1105: new java/lang/StringBuilder
    //   1108: astore #8
    //   1110: aload #8
    //   1112: invokespecial <init> : ()V
    //   1115: aload #8
    //   1117: ldc_w 'The ad server did not respond with a supported AdSize: '
    //   1120: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1123: aload_0
    //   1124: getfield n : Lcom/google/ads/AdSize;
    //   1127: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1130: invokevirtual toString : ()Ljava/lang/String;
    //   1133: invokestatic b : (Ljava/lang/String;)V
    //   1136: aload_0
    //   1137: getstatic com/google/ads/AdRequest$ErrorCode.d : Lcom/google/ads/AdRequest$ErrorCode;
    //   1140: iconst_0
    //   1141: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1144: aload_0
    //   1145: monitorexit
    //   1146: goto -> 32
    //   1149: aload_0
    //   1150: getfield n : Lcom/google/ads/AdSize;
    //   1153: ifnull -> 1167
    //   1156: ldc_w 'adSize was expected to be null in AdLoader.'
    //   1159: invokestatic e : (Ljava/lang/String;)V
    //   1162: aload_0
    //   1163: aconst_null
    //   1164: putfield n : Lcom/google/ads/AdSize;
    //   1167: aload_0
    //   1168: getfield h : Lcom/google/ads/internal/d;
    //   1171: iconst_0
    //   1172: invokevirtual b : (Z)V
    //   1175: aload_0
    //   1176: invokespecial i : ()V
    //   1179: invokestatic elapsedRealtime : ()J
    //   1182: lstore #5
    //   1184: lload_1
    //   1185: lload #5
    //   1187: lload_3
    //   1188: lsub
    //   1189: lsub
    //   1190: lstore_3
    //   1191: lload_3
    //   1192: lconst_0
    //   1193: lcmp
    //   1194: ifle -> 1202
    //   1197: aload_0
    //   1198: lload_3
    //   1199: invokevirtual wait : (J)V
    //   1202: aload_0
    //   1203: getfield r : Z
    //   1206: ifeq -> 1252
    //   1209: aload_0
    //   1210: invokespecial j : ()V
    //   1213: goto -> 395
    //   1216: astore #8
    //   1218: new java/lang/StringBuilder
    //   1221: astore #9
    //   1223: aload #9
    //   1225: invokespecial <init> : ()V
    //   1228: aload #9
    //   1230: ldc_w 'AdLoader InterruptedException while loading the HTML: '
    //   1233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1236: aload #8
    //   1238: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1241: invokevirtual toString : ()Ljava/lang/String;
    //   1244: invokestatic a : (Ljava/lang/String;)V
    //   1247: aload_0
    //   1248: monitorexit
    //   1249: goto -> 32
    //   1252: new java/lang/StringBuilder
    //   1255: astore #8
    //   1257: aload #8
    //   1259: invokespecial <init> : ()V
    //   1262: aload #8
    //   1264: ldc_w 'AdLoader timed out after '
    //   1267: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: lload_1
    //   1271: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1274: ldc_w 'ms while loading the HTML.'
    //   1277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1280: invokevirtual toString : ()Ljava/lang/String;
    //   1283: invokestatic c : (Ljava/lang/String;)V
    //   1286: aload_0
    //   1287: getstatic com/google/ads/AdRequest$ErrorCode.c : Lcom/google/ads/AdRequest$ErrorCode;
    //   1290: iconst_1
    //   1291: invokevirtual a : (Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1294: goto -> 395
    // Exception table:
    //   from	to	target	type
    //   2	16	377	java/lang/Throwable
    //   2	16	75	finally
    //   16	30	377	java/lang/Throwable
    //   16	30	75	finally
    //   30	32	75	finally
    //   33	51	377	java/lang/Throwable
    //   33	51	75	finally
    //   56	70	377	java/lang/Throwable
    //   56	70	75	finally
    //   70	72	75	finally
    //   77	79	75	finally
    //   82	175	377	java/lang/Throwable
    //   82	175	75	finally
    //   175	204	377	java/lang/Throwable
    //   175	204	75	finally
    //   204	240	377	java/lang/Throwable
    //   204	240	75	finally
    //   240	278	377	java/lang/Throwable
    //   240	278	75	finally
    //   278	291	377	java/lang/Throwable
    //   278	291	75	finally
    //   296	306	400	com/google/ads/internal/s
    //   296	306	377	java/lang/Throwable
    //   296	306	75	finally
    //   306	321	377	java/lang/Throwable
    //   306	321	75	finally
    //   336	342	444	java/lang/InterruptedException
    //   336	342	377	java/lang/Throwable
    //   336	342	75	finally
    //   342	348	377	java/lang/Throwable
    //   342	348	75	finally
    //   353	355	75	finally
    //   358	374	377	java/lang/Throwable
    //   358	374	75	finally
    //   379	395	75	finally
    //   395	397	75	finally
    //   402	439	377	java/lang/Throwable
    //   402	439	75	finally
    //   439	441	75	finally
    //   446	475	377	java/lang/Throwable
    //   446	475	75	finally
    //   475	477	75	finally
    //   480	496	377	java/lang/Throwable
    //   480	496	75	finally
    //   496	498	75	finally
    //   501	550	377	java/lang/Throwable
    //   501	550	75	finally
    //   550	552	75	finally
    //   555	604	377	java/lang/Throwable
    //   555	604	75	finally
    //   604	644	377	java/lang/Throwable
    //   604	644	75	finally
    //   659	665	768	java/lang/InterruptedException
    //   659	665	377	java/lang/Throwable
    //   659	665	75	finally
    //   665	671	377	java/lang/Throwable
    //   665	671	75	finally
    //   676	678	75	finally
    //   681	702	377	java/lang/Throwable
    //   681	702	75	finally
    //   705	716	377	java/lang/Throwable
    //   705	716	75	finally
    //   719	735	377	java/lang/Throwable
    //   719	735	75	finally
    //   738	763	377	java/lang/Throwable
    //   738	763	75	finally
    //   763	765	75	finally
    //   770	799	377	java/lang/Throwable
    //   770	799	75	finally
    //   799	801	75	finally
    //   804	820	377	java/lang/Throwable
    //   804	820	75	finally
    //   820	822	75	finally
    //   825	874	377	java/lang/Throwable
    //   825	874	75	finally
    //   874	876	75	finally
    //   879	918	377	java/lang/Throwable
    //   879	918	75	finally
    //   918	944	377	java/lang/Throwable
    //   918	944	75	finally
    //   944	946	75	finally
    //   949	982	377	java/lang/Throwable
    //   949	982	75	finally
    //   982	1027	377	java/lang/Throwable
    //   982	1027	75	finally
    //   1027	1029	75	finally
    //   1032	1069	377	java/lang/Throwable
    //   1032	1069	75	finally
    //   1069	1071	75	finally
    //   1074	1144	377	java/lang/Throwable
    //   1074	1144	75	finally
    //   1144	1146	75	finally
    //   1149	1167	377	java/lang/Throwable
    //   1149	1167	75	finally
    //   1167	1184	377	java/lang/Throwable
    //   1167	1184	75	finally
    //   1197	1202	1216	java/lang/InterruptedException
    //   1197	1202	377	java/lang/Throwable
    //   1197	1202	75	finally
    //   1202	1213	377	java/lang/Throwable
    //   1202	1213	75	finally
    //   1218	1247	377	java/lang/Throwable
    //   1218	1247	75	finally
    //   1247	1249	75	finally
    //   1252	1294	377	java/lang/Throwable
    //   1252	1294	75	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */