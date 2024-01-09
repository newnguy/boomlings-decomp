package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.LinearLayout;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import com.millennialmedia.android.MMAdView;
import java.util.Hashtable;

/* loaded from: classes.dex */
public final class al extends AdNetworkView {
    private boolean e;

    public al(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar, adCreative);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("FlurryAgent", "Cannot find manifest for app");
        }
        String string = applicationInfo.metaData.getString("com.flurry.millennial.MYAPID");
        sAdNetworkApiKey = string;
        if (string == null) {
            Log.d("FlurryAgent", "com.flurry.millennial.MYAPID not set in manifest");
        }
        setFocusable(true);
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        String str;
        int i;
        int i2 = 250;
        if (this.fAdCreative.getFormat().equals(AdCreative.kFormatTakeover)) {
            MMAdView mMAdView = new MMAdView((Activity) context, sAdNetworkApiKey, "MMFullScreenAdTransition", true, (Hashtable) null);
            mMAdView.setId(1897808290);
            mMAdView.setListener(new ay(this));
            mMAdView.fetch();
            this.e = mMAdView.display();
            if (this.e) {
                Log.d("FlurryAgent", "Millennial MMAdView Interstitial ad displayed immediately:" + System.currentTimeMillis() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.e);
                return;
            } else {
                Log.d("FlurryAgent", "Millennial MMAdView Interstitial ad did not display immediately:" + System.currentTimeMillis() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.e);
                return;
            }
        }
        int height = this.fAdCreative.getHeight();
        int width = this.fAdCreative.getWidth();
        if (width >= 320 && height >= 50) {
            Log.d("FlurryAgent", "Determined Millennial AdSize as MMBannerAdBottom");
            str = "MMBannerAdBottom";
        } else if (width < 300 || height < 250) {
            Log.d("FlurryAgent", "Could not find Millennial AdSize that matches size");
            str = null;
        } else {
            Log.d("FlurryAgent", "Determined Millennial AdSize as MMBannerAdRectangle");
            str = "MMBannerAdRectangle";
        }
        if (str == null) {
            Log.d("FlurryAgent", "**********Could not load Millennial Ad");
            return;
        }
        MMAdView mMAdView2 = new MMAdView((Activity) context, sAdNetworkApiKey, str, 0);
        mMAdView2.setId(1897808289);
        if (str.equals("MMBannerAdBottom")) {
        }
        if (str.equals("MMBannerAdRectangle")) {
            i = 300;
        } else {
            i2 = 50;
            i = 320;
        }
        float f = getResources().getDisplayMetrics().density;
        mMAdView2.setLayoutParams(new LinearLayout.LayoutParams((int) ((i * f) + 0.5f), (int) ((i2 * f) + 0.5f)));
        mMAdView2.setListener(new ak(this));
        addView(mMAdView2);
    }
}
