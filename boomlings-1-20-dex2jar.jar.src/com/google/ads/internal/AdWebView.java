package com.google.ads.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.AdSize;
import com.google.ads.m;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.g;
import com.google.ads.util.h;
import java.lang.ref.WeakReference;

public class AdWebView extends WebView {
  private WeakReference a;
  
  private AdSize b;
  
  private boolean c;
  
  private boolean d;
  
  private boolean e;
  
  public AdWebView(m paramm, AdSize paramAdSize) {
    super((Context)paramm.f.a());
    this.b = paramAdSize;
    this.a = null;
    this.c = false;
    this.d = false;
    this.e = false;
    setBackgroundColor(0);
    AdUtil.a(this);
    WebSettings webSettings = getSettings();
    webSettings.setSupportMultipleWindows(false);
    webSettings.setJavaScriptEnabled(true);
    webSettings.setSavePassword(false);
    setDownloadListener(new l(this));
    if (AdUtil.a >= 11)
      g.a(webSettings, paramm); 
    setScrollBarStyle(33554432);
    if (AdUtil.a >= 14) {
      setWebChromeClient((WebChromeClient)new h.a(paramm));
      return;
    } 
    if (AdUtil.a >= 11)
      setWebChromeClient((WebChromeClient)new g.a(paramm)); 
  }
  
  public void a() {
    AdActivity adActivity = d();
    if (adActivity != null)
      adActivity.finish(); 
  }
  
  public void b() {
    if (AdUtil.a >= 11)
      g.a((View)this); 
    this.d = true;
  }
  
  public void c() {
    if (this.d && AdUtil.a >= 11)
      g.b((View)this); 
    this.d = false;
  }
  
  public AdActivity d() {
    return (this.a != null) ? this.a.get() : null;
  }
  
  public void destroy() {
    try {
      super.destroy();
      WebViewClient webViewClient = new WebViewClient();
      this();
      setWebViewClient(webViewClient);
    } catch (Throwable throwable) {
      b.b("An error occurred while destroying an AdWebView:", throwable);
    } 
  }
  
  public boolean e() {
    return this.e;
  }
  
  public boolean f() {
    return this.d;
  }
  
  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    try {
      super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
    } catch (Throwable throwable) {
      b.b("An error occurred while loading data in AdWebView:", throwable);
    } 
  }
  
  public void loadUrl(String paramString) {
    try {
      super.loadUrl(paramString);
    } catch (Throwable throwable) {
      b.b("An error occurred while loading a URL in AdWebView:", throwable);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: ldc 2147483647
    //   2: istore #5
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: invokevirtual isInEditMode : ()Z
    //   10: ifeq -> 22
    //   13: aload_0
    //   14: iload_1
    //   15: iload_2
    //   16: invokespecial onMeasure : (II)V
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield b : Lcom/google/ads/AdSize;
    //   26: ifnull -> 36
    //   29: aload_0
    //   30: getfield c : Z
    //   33: ifeq -> 52
    //   36: aload_0
    //   37: iload_1
    //   38: iload_2
    //   39: invokespecial onMeasure : (II)V
    //   42: goto -> 19
    //   45: astore #11
    //   47: aload_0
    //   48: monitorexit
    //   49: aload #11
    //   51: athrow
    //   52: iload_1
    //   53: invokestatic getMode : (I)I
    //   56: istore #10
    //   58: iload_1
    //   59: invokestatic getSize : (I)I
    //   62: istore #4
    //   64: iload_2
    //   65: invokestatic getMode : (I)I
    //   68: istore #7
    //   70: iload_2
    //   71: invokestatic getSize : (I)I
    //   74: istore #6
    //   76: aload_0
    //   77: invokevirtual getContext : ()Landroid/content/Context;
    //   80: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   83: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   86: getfield density : F
    //   89: fstore_3
    //   90: aload_0
    //   91: getfield b : Lcom/google/ads/AdSize;
    //   94: invokevirtual a : ()I
    //   97: i2f
    //   98: fload_3
    //   99: fmul
    //   100: f2i
    //   101: istore #9
    //   103: aload_0
    //   104: getfield b : Lcom/google/ads/AdSize;
    //   107: invokevirtual b : ()I
    //   110: i2f
    //   111: fload_3
    //   112: fmul
    //   113: f2i
    //   114: istore #8
    //   116: iload #10
    //   118: ldc -2147483648
    //   120: if_icmpeq -> 130
    //   123: iload #10
    //   125: ldc 1073741824
    //   127: if_icmpne -> 264
    //   130: iload #4
    //   132: istore_1
    //   133: iload #7
    //   135: ldc -2147483648
    //   137: if_icmpeq -> 150
    //   140: iload #5
    //   142: istore_2
    //   143: iload #7
    //   145: ldc 1073741824
    //   147: if_icmpne -> 153
    //   150: iload #6
    //   152: istore_2
    //   153: iload #9
    //   155: i2f
    //   156: fload_3
    //   157: ldc 6.0
    //   159: fmul
    //   160: fsub
    //   161: iload_1
    //   162: i2f
    //   163: fcmpl
    //   164: ifgt -> 173
    //   167: iload #8
    //   169: iload_2
    //   170: if_icmple -> 253
    //   173: new java/lang/StringBuilder
    //   176: astore #11
    //   178: aload #11
    //   180: invokespecial <init> : ()V
    //   183: aload #11
    //   185: ldc 'Not enough space to show ad! Wants: <'
    //   187: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: iload #9
    //   192: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   195: ldc ', '
    //   197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: iload #8
    //   202: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   205: ldc '>, Has: <'
    //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: iload #4
    //   212: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   215: ldc ', '
    //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: iload #6
    //   222: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   225: ldc '>'
    //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual toString : ()Ljava/lang/String;
    //   233: invokestatic e : (Ljava/lang/String;)V
    //   236: aload_0
    //   237: bipush #8
    //   239: invokevirtual setVisibility : (I)V
    //   242: aload_0
    //   243: iload #4
    //   245: iload #6
    //   247: invokevirtual setMeasuredDimension : (II)V
    //   250: goto -> 19
    //   253: aload_0
    //   254: iload #9
    //   256: iload #8
    //   258: invokevirtual setMeasuredDimension : (II)V
    //   261: goto -> 19
    //   264: ldc 2147483647
    //   266: istore_1
    //   267: goto -> 133
    // Exception table:
    //   from	to	target	type
    //   6	19	45	finally
    //   22	36	45	finally
    //   36	42	45	finally
    //   52	116	45	finally
    //   173	250	45	finally
    //   253	261	45	finally
  }
  
  public void setAdActivity(AdActivity paramAdActivity) {
    this.a = new WeakReference<AdActivity>(paramAdActivity);
  }
  
  public void setAdSize(AdSize paramAdSize) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield b : Lcom/google/ads/AdSize;
    //   7: aload_0
    //   8: invokevirtual requestLayout : ()V
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
  
  public void setCustomClose(boolean paramBoolean) {
    this.e = paramBoolean;
    if (this.a != null) {
      AdActivity adActivity = this.a.get();
      if (adActivity != null)
        adActivity.a(paramBoolean); 
    } 
  }
  
  public void setIsExpandedMraid(boolean paramBoolean) {
    this.c = paramBoolean;
  }
  
  public void stopLoading() {
    try {
      super.stopLoading();
    } catch (Throwable throwable) {
      b.d("An error occurred while stopping loading in AdWebView:", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\AdWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */