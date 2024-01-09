package org.cocos2dx.lib;

public class JniToCpp {
  public static native void adColonyV4VCReward();
  
  public static native void awardGold(int paramInt);
  
  public static native void didCacheInterstitial(String paramString);
  
  public static native void didClickInterstitial();
  
  public static native void didCloseInterstitial();
  
  public static native void didDismissInterstitial();
  
  public static native boolean gameLoaded();
  
  public static native void hideLoadingCircle();
  
  public static void invokeNativeCode() {
    nativeAdd();
  }
  
  public static native void itemPurchased(String paramString);
  
  public static native void itemRefunded(String paramString);
  
  private static native void nativeAdd();
  
  public static native void onFacebookError();
  
  public static native void onFacebookLogin();
  
  public static native void onFacebookLogout();
  
  public static native void onFacebookScoreSubmitted();
  
  public static native void onFacebookScoresRecieved(String paramString);
  
  public static native void onRefIDRecieved(String paramString1, String paramString2);
  
  public static native void openSupportMail();
  
  public static native void promoImageDownloaded();
  
  public static native void resumeSound();
  
  public static native void setLoadError();
  
  public static native void setV4VCReward(int paramInt);
  
  public static native void showInterstitialFailed();
  
  public static native void updateDeviceID(String paramString);
  
  public static native void updateUserID(String paramString);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\JniToCpp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */