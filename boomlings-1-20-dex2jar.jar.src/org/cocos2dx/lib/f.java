package org.cocos2dx.lib;

import android.app.Activity;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;

public class f implements ChartboostDelegate {
  final String TAG = "ChartBoost";
  
  Cocos2dxActivity cAct_;
  
  private Chartboost cb_;
  
  private boolean tryingToDisplay_ = false;
  
  public void cacheInterstitial() {
    if (!hasCachedInterstitial())
      this.cb_.cacheInterstitial(); 
  }
  
  public void cacheInterstitial(String paramString) {
    if (!hasCachedInterstitial(paramString))
      this.cb_.cacheInterstitial(paramString); 
  }
  
  public void didCacheInterstitial(String paramString) {
    Log.d("CB", "Did Cache");
    JniToCpp.didCacheInterstitial(paramString);
  }
  
  public void didCacheMoreApps() {}
  
  public void didClickInterstitial(String paramString) {
    Log.d("CB", "Did click");
    JniToCpp.didClickInterstitial();
  }
  
  public void didClickMoreApps() {}
  
  public void didCloseInterstitial(String paramString) {
    Log.d("CB", "Did close");
    JniToCpp.didCloseInterstitial();
  }
  
  public void didCloseMoreApps() {}
  
  public void didDismissInterstitial(String paramString) {
    Log.d("CB", "Did dismiss");
    this.cb_.cacheInterstitial();
  }
  
  public void didDismissMoreApps() {}
  
  public void didFailToLoadInterstitial(String paramString) {
    if (this.tryingToDisplay_) {
      this.tryingToDisplay_ = false;
      JniToCpp.didDismissInterstitial();
    } 
  }
  
  public void didFailToLoadMoreApps() {}
  
  public void didShowInterstitial(String paramString) {
    Log.d("CB", "Did show");
  }
  
  public void didShowMoreApps() {}
  
  public boolean hasCachedInterstitial() {
    return this.cb_.hasCachedInterstitial();
  }
  
  public boolean hasCachedInterstitial(String paramString) {
    return this.cb_.hasCachedInterstitial(paramString);
  }
  
  public boolean onBackPressed() {
    if (this.cb_.onBackPressed()) {
      JniToCpp.didCloseInterstitial();
      return true;
    } 
    return false;
  }
  
  public void onDestroy() {
    this.cb_.onDestroy((Activity)this.cAct_);
  }
  
  public void onStart() {
    this.cb_.onStart((Activity)this.cAct_);
  }
  
  public void onStop() {
    this.cb_.onStop((Activity)this.cAct_);
  }
  
  protected void setup(Cocos2dxActivity paramCocos2dxActivity) {
    this.cAct_ = paramCocos2dxActivity;
    this.cb_ = Chartboost.sharedChartboost();
    this.cb_.onCreate((Activity)this.cAct_, "509f13cb16ba472f49000000", "848f8f92794ce2964202312ef657a79dda391c70", this);
    this.cb_.startSession();
  }
  
  public boolean shouldDisplayInterstitial(String paramString) {
    boolean bool = false;
    Log.d("CB", "Should display");
    if (this.tryingToDisplay_) {
      this.tryingToDisplay_ = false;
      bool = true;
    } 
    return bool;
  }
  
  public boolean shouldDisplayLoadingViewForMoreApps() {
    return false;
  }
  
  public boolean shouldDisplayMoreApps() {
    return false;
  }
  
  public boolean shouldRequestInterstitial(String paramString) {
    Log.d("CB", "Should request");
    return true;
  }
  
  public boolean shouldRequestInterstitialsInFirstSession() {
    Log.d("CB", "Should request in first");
    return true;
  }
  
  public boolean shouldRequestMoreApps() {
    return false;
  }
  
  public void showInterstitial() {
    this.cAct_.runOnUiThread(new g(this));
  }
  
  public void showInterstitial(String paramString) {
    this.cAct_.runOnUiThread(new h(this, paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */