package com.flurry.android;

import android.util.Log;
import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import java.util.Collections;

final class am implements MobclixAdViewListener {
  private ag a;
  
  am(ag paramag) {}
  
  public final String keywords() {
    Log.d("FlurryAgent", "Mobclix keyword callback.");
    return null;
  }
  
  public final void onAdClick(MobclixAdView paramMobclixAdView) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix AdView ad clicked.");
  }
  
  public final void onCustomAdTouchThrough(MobclixAdView paramMobclixAdView, String paramString) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix AdView custom ad clicked.");
  }
  
  public final void onFailedLoad(MobclixAdView paramMobclixAdView, int paramInt) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix AdView ad failed to load.");
  }
  
  public final boolean onOpenAllocationLoad(MobclixAdView paramMobclixAdView, int paramInt) {
    Log.d("FlurryAgent", "Mobclix AdView loaded an open allocation ad.");
    return true;
  }
  
  public final void onSuccessfulLoad(MobclixAdView paramMobclixAdView) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "Mobclix AdView ad successfully loaded.");
  }
  
  public final String query() {
    Log.d("FlurryAgent", "Mobclix query callback.");
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */