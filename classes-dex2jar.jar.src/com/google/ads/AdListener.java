package com.google.ads;

public interface AdListener {
  void onDismissScreen(Ad paramAd);
  
  void onFailedToReceiveAd(Ad paramAd, AdRequest$ErrorCode paramAdRequest$ErrorCode);
  
  void onLeaveApplication(Ad paramAd);
  
  void onPresentScreen(Ad paramAd);
  
  void onReceiveAd(Ad paramAd);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\AdListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */