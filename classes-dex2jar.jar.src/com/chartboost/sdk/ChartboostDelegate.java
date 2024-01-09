package com.chartboost.sdk;

public interface ChartboostDelegate {
  void didCacheInterstitial(String paramString);
  
  void didCacheMoreApps();
  
  void didClickInterstitial(String paramString);
  
  void didClickMoreApps();
  
  void didCloseInterstitial(String paramString);
  
  void didCloseMoreApps();
  
  void didDismissInterstitial(String paramString);
  
  void didDismissMoreApps();
  
  void didFailToLoadInterstitial(String paramString);
  
  void didFailToLoadMoreApps();
  
  void didShowInterstitial(String paramString);
  
  void didShowMoreApps();
  
  boolean shouldDisplayInterstitial(String paramString);
  
  boolean shouldDisplayLoadingViewForMoreApps();
  
  boolean shouldDisplayMoreApps();
  
  boolean shouldRequestInterstitial(String paramString);
  
  boolean shouldRequestInterstitialsInFirstSession();
  
  boolean shouldRequestMoreApps();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\ChartboostDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */