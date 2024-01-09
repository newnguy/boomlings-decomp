package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;

class c implements AdListener {
  final AdMobAdapter a;
  
  private c(AdMobAdapter paramAdMobAdapter) {}
  
  public void onDismissScreen(Ad paramAd) {
    AdMobAdapter.b(this.a).c(this.a);
  }
  
  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode) {
    AdMobAdapter.b(this.a).a(this.a, paramErrorCode);
  }
  
  public void onLeaveApplication(Ad paramAd) {
    AdMobAdapter.b(this.a).d(this.a);
  }
  
  public void onPresentScreen(Ad paramAd) {
    AdMobAdapter.b(this.a).b(this.a);
  }
  
  public void onReceiveAd(Ad paramAd) {
    AdMobAdapter.b(this.a).a(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\admob\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */