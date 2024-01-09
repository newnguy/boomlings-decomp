package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class a {
  private static String TAG = "AdMobDelegate";
  
  AdView adview_banner;
  
  private boolean bannerEnabled_ = false;
  
  private boolean bannerWasEnabled_ = false;
  
  public Cocos2dxActivity cAct_;
  
  private static String admobKey() {
    return "aea549867f5b4aea";
  }
  
  public void createBanner() {
    RelativeLayout relativeLayout = new RelativeLayout((Context)this.cAct_);
    this.adview_banner = new AdView((Activity)this.cAct_, AdSize.BANNER, admobKey());
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(12);
    layoutParams.addRule(14);
    relativeLayout.addView((View)this.adview_banner, (ViewGroup.LayoutParams)layoutParams);
    layoutParams = new RelativeLayout.LayoutParams(-1, -1);
    this.cAct_.addContentView((View)relativeLayout, (ViewGroup.LayoutParams)layoutParams);
    this.adview_banner.loadAd(new AdRequest());
    disableBanner();
  }
  
  public void disableBanner() {
    this.cAct_.runOnUiThread(new c(this));
  }
  
  public void enableBanner() {
    this.cAct_.runOnUiThread(new b(this));
  }
  
  public void onDestroy() {
    if (this.adview_banner != null)
      this.adview_banner.destroy(); 
  }
  
  public void pauseBanner() {
    this.bannerWasEnabled_ = this.bannerEnabled_;
    disableBanner();
  }
  
  public void resumeBanner() {
    if (this.bannerWasEnabled_)
      enableBanner(); 
  }
  
  protected void setup(Cocos2dxActivity paramCocos2dxActivity) {
    this.cAct_ = paramCocos2dxActivity;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */