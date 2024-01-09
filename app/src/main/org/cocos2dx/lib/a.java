package org.cocos2dx.lib;

import android.widget.RelativeLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

/* loaded from: classes.dex */
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
        RelativeLayout relativeLayout = new RelativeLayout(this.cAct_);
        this.adview_banner = new AdView(this.cAct_, AdSize.BANNER, admobKey());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        relativeLayout.addView(this.adview_banner, layoutParams);
        this.cAct_.addContentView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
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
        if (this.adview_banner != null) {
            this.adview_banner.destroy();
        }
    }

    public void pauseBanner() {
        this.bannerWasEnabled_ = this.bannerEnabled_;
        disableBanner();
    }

    public void resumeBanner() {
        if (this.bannerWasEnabled_) {
            enableBanner();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setup(Cocos2dxActivity cocos2dxActivity) {
        this.cAct_ = cocos2dxActivity;
    }
}
