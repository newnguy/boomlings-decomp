package org.cocos2dx.lib;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.robtopx.boomlings.Boomlings;
import com.tapjoy.ac;
import com.tapjoy.aj;
import com.tapjoy.ak;
import com.tapjoy.al;
import com.tapjoy.ar;
import com.tapjoy.g;
import com.tapjoy.p;
import com.tapjoy.v;

public class bb implements View.OnClickListener, ac, ak, al, ar, p, v {
  final String TAG = "TapJoy";
  
  LinearLayout adLinearLayout;
  
  View adView;
  
  public Cocos2dxActivity cAct_;
  
  boolean checkingDaily_ = false;
  
  String currency_name;
  
  String displayText = "";
  
  boolean earnedPoints = false;
  
  boolean interstitialReady_ = false;
  
  final Handler mHandler = new Handler();
  
  final Runnable mUpdateResults = new bc(this);
  
  int point_total;
  
  int pointsToSpend_ = 0;
  
  boolean queuedSpendPoints_ = false;
  
  boolean spendingPoints_ = false;
  
  boolean update_display_ad = false;
  
  boolean update_text = false;
  
  private void updateResultsInUi() {
    if (this.update_display_ad) {
      this.adLinearLayout.removeAllViews();
      this.adLinearLayout.addView(this.adView);
      this.update_display_ad = false;
    } 
  }
  
  public void cacheInterstitial() {
    if (!hasCachedInterstitial())
      g.a().a(this); 
  }
  
  public void earnedTapPoints(int paramInt) {
    this.earnedPoints = true;
    this.update_text = true;
  }
  
  public void getAwardPointsResponse(String paramString, int paramInt) {
    this.update_text = true;
    this.displayText = String.valueOf(paramString) + ": " + paramInt;
  }
  
  public void getAwardPointsResponseFailed(String paramString) {
    this.update_text = true;
    this.displayText = "Award Points: " + paramString;
  }
  
  public void getDailyRewardAdResponse() {
    this.checkingDaily_ = false;
    JniToCpp.hideLoadingCircle();
    g.a().d();
  }
  
  public void getDailyRewardAdResponseFailed(int paramInt) {
    this.checkingDaily_ = false;
    JniToCpp.hideLoadingCircle();
    if (paramInt == 1) {
      Boomlings.showDialog("Unavailable", "Check back tomorrow for a new Daily Reward!", "OK");
      return;
    } 
    Toast.makeText(this.cAct_.getApplicationContext(), "Something went wrong...", 0).show();
  }
  
  public void getDisplayAdResponse(View paramView) {}
  
  public void getDisplayAdResponseFailed(String paramString) {
    Log.d("EASY_APP", "getDisplayAd error: " + paramString);
    this.update_text = true;
    this.displayText = "Banner Ads: " + paramString;
  }
  
  public void getFullScreenAdResponse() {
    this.interstitialReady_ = true;
  }
  
  public void getFullScreenAdResponseFailed(int paramInt) {
    this.interstitialReady_ = false;
  }
  
  public void getSpendPointsResponse(String paramString, int paramInt) {
    Log.d("EASY_APP", "currencyName: " + paramString);
    Log.d("EASY_APP", "pointTotal: " + paramInt);
    this.update_text = true;
    this.displayText = String.valueOf(paramString) + ": " + paramInt;
    this.mHandler.post(this.mUpdateResults);
    Log.d("TY", "Spent Points: " + this.pointsToSpend_);
    JniToCpp.awardGold(this.pointsToSpend_);
    spendPointsFinished();
  }
  
  public void getSpendPointsResponseFailed(String paramString) {
    Log.d("EASY_APP", "spendTapPoints error: " + paramString);
    this.update_text = true;
    this.displayText = "Spend Tap Points: " + paramString;
    this.mHandler.post(this.mUpdateResults);
    spendPointsFinished();
  }
  
  public void getUpdatePoints(String paramString, int paramInt) {
    Log.d("EASY_APP", "currencyName: " + paramString);
    Log.d("EASY_APP", "pointTotal: " + paramInt);
    this.currency_name = paramString;
    this.point_total = paramInt;
    if (paramInt > 0) {
      if (this.spendingPoints_) {
        Log.d("TY", "Already spending points, abort and queue");
        this.queuedSpendPoints_ = true;
        return;
      } 
    } else {
      return;
    } 
    Log.d("TY", "Spending Points: " + paramInt);
    this.queuedSpendPoints_ = false;
    this.spendingPoints_ = true;
    this.pointsToSpend_ = paramInt;
    g.a().a(paramInt, this);
  }
  
  public void getUpdatePointsFailed(String paramString) {
    Log.d("EASY_APP", "getTapPoints error: " + paramString);
  }
  
  public boolean hasCachedInterstitial() {
    return this.interstitialReady_;
  }
  
  public void onClick(View paramView) {}
  
  protected void setup(Cocos2dxActivity paramCocos2dxActivity) {
    this.cAct_ = paramCocos2dxActivity;
    g.a((Context)this.cAct_, "7313e37b-2bd3-4ff6-b1e1-5e990c3e5d18", "NYHpVj8HpHblDRrreKbR");
    aj.a(false);
    g.a().a(this);
    this.interstitialReady_ = false;
  }
  
  public void showDailyReward() {
    if (!this.checkingDaily_) {
      this.checkingDaily_ = true;
      g.a().a(this);
    } 
  }
  
  public void showInterstitial() {
    g.a().c();
  }
  
  public void spendPointsFinished() {
    this.pointsToSpend_ = 0;
    this.spendingPoints_ = false;
    if (this.queuedSpendPoints_) {
      this.queuedSpendPoints_ = false;
      Cocos2dxActivity.getTapPoints();
    } 
  }
  
  public void videoComplete() {
    Log.d("EASY_APP", "VIDEO COMPLETE");
  }
  
  public void videoError(int paramInt) {
    Log.d("EASY_APP", "VIDEO ERROR: " + paramInt);
    switch (paramInt) {
      default:
        this.update_text = true;
        this.mHandler.post(this.mUpdateResults);
        return;
      case 1:
        this.displayText = "VIDEO ERROR: No SD card or external media storage mounted on device";
      case 2:
        this.displayText = "VIDEO ERROR: Network error on init videos";
      case 3:
        break;
    } 
    this.displayText = "VIDEO ERROR: Error playing video";
  }
  
  public void videoStart() {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */