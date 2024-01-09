package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;

class b implements AdListener {
  final AdMobAdapter a;
  
  private b(AdMobAdapter paramAdMobAdapter) {}
  
  public void onDismissScreen(Ad paramAd) {
    AdMobAdapter.a(this.a).c(this.a);
  }
  
  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode) {
    AdMobAdapter.a(this.a).a(this.a, paramErrorCode);
  }
  
  public void onLeaveApplication(Ad paramAd) {
    AdMobAdapter.a(this.a).d(this.a);
  }
  
  public void onPresentScreen(Ad paramAd) {
    AdMobAdapter.a(this.a).e(this.a);
    AdMobAdapter.a(this.a).b(this.a);
  }
  
  public void onReceiveAd(Ad paramAd) {
    AdMobAdapter.a(this.a).a(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\admob\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */