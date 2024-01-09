package com.google.ads;

import android.app.Activity;
import com.google.ads.internal.d;

public class InterstitialAd implements Ad {
  private d a;
  
  public InterstitialAd(Activity paramActivity, String paramString) {
    this(paramActivity, paramString, false);
  }
  
  public InterstitialAd(Activity paramActivity, String paramString, boolean paramBoolean) {
    this.a = new d(this, paramActivity, null, paramString, null, paramBoolean);
  }
  
  public void a() {
    this.a.y();
  }
  
  public void a(AdListener paramAdListener) {
    (this.a.h()).m.a(paramAdListener);
  }
  
  public void a(AdRequest paramAdRequest) {
    this.a.a(paramAdRequest);
  }
  
  public void b() {
    this.a.z();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\InterstitialAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */