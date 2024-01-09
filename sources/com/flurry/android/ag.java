package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.LinearLayout;
import com.mobclix.android.sdk.MobclixFullScreenAdView;
import com.mobclix.android.sdk.MobclixIABRectangleMAdView;
import com.mobclix.android.sdk.MobclixMMABannerXLAdView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ag extends AdNetworkView {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ag(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar, adCreative);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("FlurryAgent", "Cannot find manifest for app");
        }
        String string = applicationInfo.metaData.getString("com.mobclix.APPLICATION_ID");
        sAdNetworkApiKey = string;
        if (string == null) {
            Log.d("MobclixTestApp", "com.mobclix.APPLICATION_ID not set in manifest");
        }
        setFocusable(true);
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        MobclixMMABannerXLAdView mobclixMMABannerXLAdView;
        int i;
        int i2 = 250;
        if (this.fAdCreative.getFormat().equals(AdCreative.kFormatTakeover)) {
            MobclixFullScreenAdView mobclixFullScreenAdView = new MobclixFullScreenAdView((Activity) context);
            mobclixFullScreenAdView.addMobclixAdViewListener(new ad(this));
            mobclixFullScreenAdView.requestAndDisplayAd();
            return;
        }
        int height = this.fAdCreative.getHeight();
        int width = this.fAdCreative.getWidth();
        if (width >= 320 && height >= 50) {
            Log.d("FlurryAgent", "Determined Mobclix AdSize as BANNER");
            mobclixMMABannerXLAdView = new MobclixMMABannerXLAdView((Activity) context);
        } else if (width < 300 || height < 250) {
            Log.d("FlurryAgent", "Could not find Mobclix AdSize that matches size");
            mobclixMMABannerXLAdView = null;
        } else {
            Log.d("FlurryAgent", "Determined Mobclix AdSize as IAB_RECT");
            mobclixMMABannerXLAdView = new MobclixIABRectangleMAdView((Activity) context);
        }
        mobclixMMABannerXLAdView.addMobclixAdViewListener(new am(this));
        if (mobclixMMABannerXLAdView instanceof MobclixMMABannerXLAdView) {
        }
        if (mobclixMMABannerXLAdView instanceof MobclixIABRectangleMAdView) {
            i = 320;
        } else {
            i2 = 50;
            i = 300;
        }
        float f = getResources().getDisplayMetrics().density;
        mobclixMMABannerXLAdView.setLayoutParams(new LinearLayout.LayoutParams((int) ((i * f) + 0.5f), (int) ((i2 * f) + 0.5f)));
        mobclixMMABannerXLAdView.setForegroundGravity(14);
        addView(mobclixMMABannerXLAdView);
        mobclixMMABannerXLAdView.setRefreshTime(-1L);
    }
}
