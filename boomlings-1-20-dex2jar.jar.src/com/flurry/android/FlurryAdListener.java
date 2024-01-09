package com.flurry.android;

public interface FlurryAdListener {
  void onAdClicked(String paramString);
  
  void onAdClosed(String paramString);
  
  void onAdOpened(String paramString);
  
  void onApplicationExit(String paramString);
  
  void onRenderFailed(String paramString);
  
  boolean shouldDisplayAd(String paramString, FlurryAdType paramFlurryAdType);
  
  void spaceDidFailToReceiveAd(String paramString);
  
  void spaceDidReceiveAd(String paramString);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\FlurryAdListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */