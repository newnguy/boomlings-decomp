package org.cocos2dx.lib;

import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;

/* loaded from: classes.dex */
public class f implements ChartboostDelegate {
    Cocos2dxActivity cAct_;
    private Chartboost cb_;
    final String TAG = "ChartBoost";
    private boolean tryingToDisplay_ = false;

    public void cacheInterstitial() {
        if (hasCachedInterstitial()) {
            return;
        }
        this.cb_.cacheInterstitial();
    }

    public void cacheInterstitial(String str) {
        if (hasCachedInterstitial(str)) {
            return;
        }
        this.cb_.cacheInterstitial(str);
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCacheInterstitial(String str) {
        Log.d("CB", "Did Cache");
        JniToCpp.didCacheInterstitial(str);
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCacheMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didClickInterstitial(String str) {
        Log.d("CB", "Did click");
        JniToCpp.didClickInterstitial();
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didClickMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCloseInterstitial(String str) {
        Log.d("CB", "Did close");
        JniToCpp.didCloseInterstitial();
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didCloseMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didDismissInterstitial(String str) {
        Log.d("CB", "Did dismiss");
        this.cb_.cacheInterstitial();
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didDismissMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didFailToLoadInterstitial(String str) {
        if (this.tryingToDisplay_) {
            this.tryingToDisplay_ = false;
            JniToCpp.didDismissInterstitial();
        }
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didFailToLoadMoreApps() {
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didShowInterstitial(String str) {
        Log.d("CB", "Did show");
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public void didShowMoreApps() {
    }

    public boolean hasCachedInterstitial() {
        return this.cb_.hasCachedInterstitial();
    }

    public boolean hasCachedInterstitial(String str) {
        return this.cb_.hasCachedInterstitial(str);
    }

    public boolean onBackPressed() {
        if (this.cb_.onBackPressed()) {
            JniToCpp.didCloseInterstitial();
            return true;
        }
        return false;
    }

    public void onDestroy() {
        this.cb_.onDestroy(this.cAct_);
    }

    public void onStart() {
        this.cb_.onStart(this.cAct_);
    }

    public void onStop() {
        this.cb_.onStop(this.cAct_);
    }

    public void setup(Cocos2dxActivity cocos2dxActivity) {
        this.cAct_ = cocos2dxActivity;
        this.cb_ = Chartboost.sharedChartboost();
        this.cb_.onCreate(this.cAct_, "509f13cb16ba472f49000000", "848f8f92794ce2964202312ef657a79dda391c70", this);
        this.cb_.startSession();
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayInterstitial(String str) {
        Log.d("CB", "Should display");
        if (this.tryingToDisplay_) {
            this.tryingToDisplay_ = false;
            return true;
        }
        return false;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayLoadingViewForMoreApps() {
        return false;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldDisplayMoreApps() {
        return false;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestInterstitial(String str) {
        Log.d("CB", "Should request");
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestInterstitialsInFirstSession() {
        Log.d("CB", "Should request in first");
        return true;
    }

    @Override // com.chartboost.sdk.ChartboostDelegate
    public boolean shouldRequestMoreApps() {
        return false;
    }

    public void showInterstitial() {
        this.cAct_.runOnUiThread(new g(this));
    }

    public void showInterstitial(String str) {
        this.cAct_.runOnUiThread(new h(this, str));
    }
}
