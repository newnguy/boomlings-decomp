package org.cocos2dx.lib;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.robtopx.boomlings.Boomlings;

/* loaded from: classes.dex */
public class bb implements View.OnClickListener, com.tapjoy.ac, com.tapjoy.ak, com.tapjoy.al, com.tapjoy.ar, com.tapjoy.p, com.tapjoy.v {
    LinearLayout adLinearLayout;
    View adView;
    public Cocos2dxActivity cAct_;
    String currency_name;
    int point_total;
    final String TAG = "TapJoy";
    int pointsToSpend_ = 0;
    boolean spendingPoints_ = false;
    boolean queuedSpendPoints_ = false;
    String displayText = "";
    boolean update_text = false;
    boolean earnedPoints = false;
    final Handler mHandler = new Handler();
    boolean update_display_ad = false;
    boolean interstitialReady_ = false;
    boolean checkingDaily_ = false;
    final Runnable mUpdateResults = new bc(this);

    public void updateResultsInUi() {
        if (this.update_display_ad) {
            this.adLinearLayout.removeAllViews();
            this.adLinearLayout.addView(this.adView);
            this.update_display_ad = false;
        }
    }

    public void cacheInterstitial() {
        if (hasCachedInterstitial()) {
            return;
        }
        com.tapjoy.g.a().a((com.tapjoy.ac) this);
    }

    @Override // com.tapjoy.v
    public void earnedTapPoints(int i) {
        this.earnedPoints = true;
        this.update_text = true;
    }

    public void getAwardPointsResponse(String str, int i) {
        this.update_text = true;
        this.displayText = String.valueOf(str) + ": " + i;
    }

    public void getAwardPointsResponseFailed(String str) {
        this.update_text = true;
        this.displayText = "Award Points: " + str;
    }

    @Override // com.tapjoy.p
    public void getDailyRewardAdResponse() {
        this.checkingDaily_ = false;
        JniToCpp.hideLoadingCircle();
        com.tapjoy.g.a().d();
    }

    @Override // com.tapjoy.p
    public void getDailyRewardAdResponseFailed(int i) {
        this.checkingDaily_ = false;
        JniToCpp.hideLoadingCircle();
        if (i == 1) {
            Boomlings.showDialog("Unavailable", "Check back tomorrow for a new Daily Reward!", "OK");
        } else {
            Toast.makeText(this.cAct_.getApplicationContext(), "Something went wrong...", 0).show();
        }
    }

    public void getDisplayAdResponse(View view) {
    }

    public void getDisplayAdResponseFailed(String str) {
        Log.d("EASY_APP", "getDisplayAd error: " + str);
        this.update_text = true;
        this.displayText = "Banner Ads: " + str;
    }

    @Override // com.tapjoy.ac
    public void getFullScreenAdResponse() {
        this.interstitialReady_ = true;
    }

    @Override // com.tapjoy.ac
    public void getFullScreenAdResponseFailed(int i) {
        this.interstitialReady_ = false;
    }

    @Override // com.tapjoy.al
    public void getSpendPointsResponse(String str, int i) {
        Log.d("EASY_APP", "currencyName: " + str);
        Log.d("EASY_APP", "pointTotal: " + i);
        this.update_text = true;
        this.displayText = String.valueOf(str) + ": " + i;
        this.mHandler.post(this.mUpdateResults);
        Log.d("TY", "Spent Points: " + this.pointsToSpend_);
        JniToCpp.awardGold(this.pointsToSpend_);
        spendPointsFinished();
    }

    @Override // com.tapjoy.al
    public void getSpendPointsResponseFailed(String str) {
        Log.d("EASY_APP", "spendTapPoints error: " + str);
        this.update_text = true;
        this.displayText = "Spend Tap Points: " + str;
        this.mHandler.post(this.mUpdateResults);
        spendPointsFinished();
    }

    @Override // com.tapjoy.ak
    public void getUpdatePoints(String str, int i) {
        Log.d("EASY_APP", "currencyName: " + str);
        Log.d("EASY_APP", "pointTotal: " + i);
        this.currency_name = str;
        this.point_total = i;
        if (i > 0) {
            if (this.spendingPoints_) {
                Log.d("TY", "Already spending points, abort and queue");
                this.queuedSpendPoints_ = true;
                return;
            }
            Log.d("TY", "Spending Points: " + i);
            this.queuedSpendPoints_ = false;
            this.spendingPoints_ = true;
            this.pointsToSpend_ = i;
            com.tapjoy.g.a().a(i, this);
        }
    }

    @Override // com.tapjoy.ak
    public void getUpdatePointsFailed(String str) {
        Log.d("EASY_APP", "getTapPoints error: " + str);
    }

    public boolean hasCachedInterstitial() {
        return this.interstitialReady_;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public void setup(Cocos2dxActivity cocos2dxActivity) {
        this.cAct_ = cocos2dxActivity;
        com.tapjoy.g.a(this.cAct_, "7313e37b-2bd3-4ff6-b1e1-5e990c3e5d18", "NYHpVj8HpHblDRrreKbR");
        com.tapjoy.aj.a(false);
        com.tapjoy.g.a().a((com.tapjoy.v) this);
        this.interstitialReady_ = false;
    }

    public void showDailyReward() {
        if (this.checkingDaily_) {
            return;
        }
        this.checkingDaily_ = true;
        com.tapjoy.g.a().a((com.tapjoy.p) this);
    }

    public void showInterstitial() {
        com.tapjoy.g.a().c();
    }

    public void spendPointsFinished() {
        this.pointsToSpend_ = 0;
        this.spendingPoints_ = false;
        if (this.queuedSpendPoints_) {
            this.queuedSpendPoints_ = false;
            Cocos2dxActivity.getTapPoints();
        }
    }

    @Override // com.tapjoy.ar
    public void videoComplete() {
        Log.d("EASY_APP", "VIDEO COMPLETE");
    }

    @Override // com.tapjoy.ar
    public void videoError(int i) {
        Log.d("EASY_APP", "VIDEO ERROR: " + i);
        switch (i) {
            case 1:
                this.displayText = "VIDEO ERROR: No SD card or external media storage mounted on device";
                break;
            case 2:
                this.displayText = "VIDEO ERROR: Network error on init videos";
                break;
            case 3:
                this.displayText = "VIDEO ERROR: Error playing video";
                break;
        }
        this.update_text = true;
        this.mHandler.post(this.mUpdateResults);
    }

    @Override // com.tapjoy.ar
    public void videoStart() {
    }
}
