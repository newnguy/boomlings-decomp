package com.flurry.android;

import android.util.Log;
import com.mobclix.android.sdk.MobclixFullScreenAdView;
import com.mobclix.android.sdk.MobclixFullScreenAdViewListener;
import java.util.Collections;

final class ad implements MobclixFullScreenAdViewListener {
  private ag a;
  
  ad(ag paramag) {}
  
  public final String keywords() {
    Log.d("FlurryAgent", "Mobclix keyword callback.");
    return null;
  }
  
  public final void onDismissAd(MobclixFullScreenAdView paramMobclixFullScreenAdView) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix Interstitial ad dismissed.");
  }
  
  public final void onFailedLoad(MobclixFullScreenAdView paramMobclixFullScreenAdView, int paramInt) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix Interstitial ad failed to load.");
  }
  
  public final void onFinishLoad(MobclixFullScreenAdView paramMobclixFullScreenAdView) {
    this.a.onAdFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix Interstitial ad successfully loaded.");
  }
  
  public final void onPresentAd(MobclixFullScreenAdView paramMobclixFullScreenAdView) {
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix Interstitial ad successfully shown.");
  }
  
  public final String query() {
    Log.d("FlurryAgent", "Mobclix query callback.");
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */