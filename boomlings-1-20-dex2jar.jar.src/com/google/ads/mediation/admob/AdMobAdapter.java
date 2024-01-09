package com.google.ads.mediation.admob;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.util.AdUtil;

public class AdMobAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
  private MediationBannerListener a;
  
  private MediationInterstitialListener b;
  
  private AdView c;
  
  private InterstitialAd d;
  
  private AdRequest a(Activity paramActivity, AdMobAdapterServerParameters paramAdMobAdapterServerParameters, MediationAdRequest paramMediationAdRequest, AdMobAdapterExtras paramAdMobAdapterExtras) {
    paramAdMobAdapterExtras = new AdMobAdapterExtras(paramAdMobAdapterExtras);
    paramAdMobAdapterExtras.b("_norefresh", "t");
    paramAdMobAdapterExtras.b("gw", Integer.valueOf(1));
    if (paramAdMobAdapterServerParameters.b != null)
      paramAdMobAdapterExtras.b("mad_hac", paramAdMobAdapterServerParameters.b); 
    AdRequest adRequest = (new AdRequest()).a(paramMediationAdRequest.b()).a(paramMediationAdRequest.a()).a(paramMediationAdRequest.c()).a(paramMediationAdRequest.d()).a(paramAdMobAdapterExtras);
    if (paramMediationAdRequest.e())
      adRequest.a(AdUtil.a((Context)paramActivity)); 
    return adRequest;
  }
  
  private void f() {
    if (g())
      throw new IllegalStateException("Adapter has already been destroyed"); 
  }
  
  private boolean g() {
    return (this.c == null && this.d == null);
  }
  
  protected AdView a(Activity paramActivity, AdSize paramAdSize, String paramString) {
    return new AdView(paramActivity, paramAdSize, paramString);
  }
  
  protected InterstitialAd a(Activity paramActivity, String paramString) {
    return new InterstitialAd(paramActivity, paramString);
  }
  
  public void a() {
    f();
    if (this.c != null) {
      this.c.stopLoading();
      this.c.destroy();
      this.c = null;
    } 
    if (this.d != null) {
      this.d.b();
      this.d = null;
    } 
  }
  
  public void a(MediationBannerListener paramMediationBannerListener, Activity paramActivity, AdMobAdapterServerParameters paramAdMobAdapterServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, AdMobAdapterExtras paramAdMobAdapterExtras) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield a : Lcom/google/ads/mediation/MediationBannerListener;
    //   5: aload #4
    //   7: astore #7
    //   9: aload #4
    //   11: invokevirtual d : ()Z
    //   14: ifne -> 29
    //   17: aload #4
    //   19: invokevirtual c : ()Z
    //   22: ifeq -> 78
    //   25: aload #4
    //   27: astore #7
    //   29: aload_0
    //   30: aload_0
    //   31: aload_2
    //   32: aload #7
    //   34: aload_3
    //   35: getfield a : Ljava/lang/String;
    //   38: invokevirtual a : (Landroid/app/Activity;Lcom/google/ads/AdSize;Ljava/lang/String;)Lcom/google/ads/AdView;
    //   41: putfield c : Lcom/google/ads/AdView;
    //   44: aload_0
    //   45: getfield c : Lcom/google/ads/AdView;
    //   48: new com/google/ads/mediation/admob/b
    //   51: dup
    //   52: aload_0
    //   53: aconst_null
    //   54: invokespecial <init> : (Lcom/google/ads/mediation/admob/AdMobAdapter;Lcom/google/ads/mediation/admob/a;)V
    //   57: invokevirtual setAdListener : (Lcom/google/ads/AdListener;)V
    //   60: aload_0
    //   61: getfield c : Lcom/google/ads/AdView;
    //   64: aload_0
    //   65: aload_2
    //   66: aload_3
    //   67: aload #5
    //   69: aload #6
    //   71: invokespecial a : (Landroid/app/Activity;Lcom/google/ads/mediation/admob/AdMobAdapterServerParameters;Lcom/google/ads/mediation/MediationAdRequest;Lcom/google/ads/mediation/admob/AdMobAdapterExtras;)Lcom/google/ads/AdRequest;
    //   74: invokevirtual loadAd : (Lcom/google/ads/AdRequest;)V
    //   77: return
    //   78: aload #6
    //   80: ifnull -> 95
    //   83: aload #4
    //   85: astore #7
    //   87: aload #6
    //   89: invokevirtual e : ()Z
    //   92: ifne -> 29
    //   95: aload #4
    //   97: iconst_5
    //   98: anewarray com/google/ads/AdSize
    //   101: dup
    //   102: iconst_0
    //   103: getstatic com/google/ads/AdSize.BANNER : Lcom/google/ads/AdSize;
    //   106: aastore
    //   107: dup
    //   108: iconst_1
    //   109: getstatic com/google/ads/AdSize.IAB_BANNER : Lcom/google/ads/AdSize;
    //   112: aastore
    //   113: dup
    //   114: iconst_2
    //   115: getstatic com/google/ads/AdSize.IAB_LEADERBOARD : Lcom/google/ads/AdSize;
    //   118: aastore
    //   119: dup
    //   120: iconst_3
    //   121: getstatic com/google/ads/AdSize.IAB_MRECT : Lcom/google/ads/AdSize;
    //   124: aastore
    //   125: dup
    //   126: iconst_4
    //   127: getstatic com/google/ads/AdSize.IAB_WIDE_SKYSCRAPER : Lcom/google/ads/AdSize;
    //   130: aastore
    //   131: invokevirtual a : ([Lcom/google/ads/AdSize;)Lcom/google/ads/AdSize;
    //   134: astore #4
    //   136: aload #4
    //   138: astore #7
    //   140: aload #4
    //   142: ifnonnull -> 29
    //   145: aload_1
    //   146: aload_0
    //   147: getstatic com/google/ads/AdRequest$ErrorCode.b : Lcom/google/ads/AdRequest$ErrorCode;
    //   150: invokeinterface a : (Lcom/google/ads/mediation/MediationBannerAdapter;Lcom/google/ads/AdRequest$ErrorCode;)V
    //   155: goto -> 77
  }
  
  public void a(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, AdMobAdapterServerParameters paramAdMobAdapterServerParameters, MediationAdRequest paramMediationAdRequest, AdMobAdapterExtras paramAdMobAdapterExtras) {
    this.b = paramMediationInterstitialListener;
    this.d = a(paramActivity, paramAdMobAdapterServerParameters.a);
    this.d.a(new c(this, null));
    this.d.a(a(paramActivity, paramAdMobAdapterServerParameters, paramMediationAdRequest, paramAdMobAdapterExtras));
  }
  
  public Class b() {
    return AdMobAdapterExtras.class;
  }
  
  public Class c() {
    return AdMobAdapterServerParameters.class;
  }
  
  public View d() {
    return (View)this.c;
  }
  
  public void e() {
    this.d.a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\admob\AdMobAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */