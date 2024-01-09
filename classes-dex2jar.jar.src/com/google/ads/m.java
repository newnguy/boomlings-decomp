package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.google.ads.internal.h;
import com.google.ads.util.i;

public class m extends i {
  public final i.b a;
  
  public final i.c b = new i.c(this, "currentAd", null);
  
  public final i.c c = new i.c(this, "nextAd", null);
  
  public final i.b d;
  
  public final i.d e;
  
  public final i.b f;
  
  public final i.b g;
  
  public final i.b h;
  
  public final i.b i;
  
  public final i.b j;
  
  public final i.b k;
  
  public final i.c l;
  
  public final i.c m = new i.c(this, "adListener");
  
  public final i.c n = new i.c(this, "appEventListener");
  
  public m(l paraml, Ad paramAd, AdView paramAdView, InterstitialAd paramInterstitialAd, String paramString, Activity paramActivity, Context paramContext, ViewGroup paramViewGroup, h paramh) {
    this.a = new i.b(this, "appState", paraml);
    this.h = new i.b(this, "ad", paramAd);
    this.i = new i.b(this, "adView", paramAdView);
    this.k = new i.b(this, "adType", paramh);
    this.d = new i.b(this, "adUnitId", paramString);
    this.e = new i.d(this, "activity", paramActivity);
    this.j = new i.b(this, "interstitialAd", paramInterstitialAd);
    this.g = new i.b(this, "bannerContainer", paramViewGroup);
    this.f = new i.b(this, "applicationContext", paramContext);
    this.l = new i.c(this, "adSizes", null);
  }
  
  public boolean a() {
    return !b();
  }
  
  public boolean b() {
    return ((h)this.k.a()).a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */