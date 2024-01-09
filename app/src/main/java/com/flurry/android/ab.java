package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

/* loaded from: classes.dex */
public final class ab extends AdNetworkView {
    private static boolean e;
    private InterstitialAd f;

    public ab(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar, adCreative);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            Log.d("FlurryAgent", "Cannot find manifest for app");
        }
        Bundle bundle = applicationInfo.metaData;
        sAdNetworkApiKey = bundle.getString("com.flurry.admob.MY_AD_UNIT_ID");
        sAdNetworkApiKey2 = bundle.getString("com.flurry.admob.MYTEST_AD_DEVICE_ID");
        e = bundle.getBoolean("com.flurry.admob.test");
        if (sAdNetworkApiKey == null) {
            Log.d("FlurryAgent", "com.flurry.admob.MY_AD_UNIT_ID not set in manifest");
        }
        setFocusable(true);
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        AdSize adSize;
        int i = 320;
        int i2 = 50;
        if (this.fAdCreative.getFormat().equals(AdCreative.kFormatTakeover)) {
            this.f = new InterstitialAd((Activity) context, sAdNetworkApiKey);
            this.f.a(new j(this));
            com.google.ads.AdRequest adRequest = new com.google.ads.AdRequest();
            if (e) {
                Log.d("FlurryAgent", "Admob AdView set to Test Mode.");
                adRequest.a(com.google.ads.AdRequest.a);
                if (sAdNetworkApiKey2 != null && sAdNetworkApiKey2 != "") {
                    adRequest.a(sAdNetworkApiKey2);
                }
            }
            this.f.a(adRequest);
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i3 = (int) (displayMetrics.heightPixels / displayMetrics.density);
        int i4 = (int) (displayMetrics.widthPixels / displayMetrics.density);
        if (i4 >= AdSize.IAB_LEADERBOARD.a() && i3 >= AdSize.IAB_LEADERBOARD.b()) {
            Log.d("FlurryAgent", "Determined Admob AdSize as IAB_LEADERBOARD");
            adSize = AdSize.IAB_LEADERBOARD;
        } else if (i4 >= AdSize.IAB_BANNER.a() && i3 >= AdSize.IAB_BANNER.b()) {
            Log.d("FlurryAgent", "Determined Admob AdSize as IAB_BANNER");
            adSize = AdSize.IAB_BANNER;
        } else if (i4 >= AdSize.BANNER.a() && i3 >= AdSize.BANNER.b()) {
            Log.d("FlurryAgent", "Determined Admob AdSize as BANNER");
            adSize = AdSize.BANNER;
        } else if (i4 < AdSize.IAB_MRECT.a() || i3 < AdSize.IAB_MRECT.b()) {
            Log.d("FlurryAgent", "Could not find Admob AdSize that matches size");
            adSize = null;
        } else {
            Log.d("FlurryAgent", "Determined Admob AdSize as IAB_MRECT");
            adSize = AdSize.IAB_MRECT;
        }
        if (adSize == null) {
            Log.d("FlurryAgent", "**********Could not load Admob Ad");
            return;
        }
        AdView adView = new AdView((Activity) context, adSize, sAdNetworkApiKey);
        if (adSize.equals(AdSize.BANNER)) {
        }
        if (adSize.equals(AdSize.IAB_MRECT)) {
            i = 300;
            i2 = 250;
        }
        float f = getResources().getDisplayMetrics().density;
        adView.setLayoutParams(new LinearLayout.LayoutParams((int) ((i * f) + 0.5f), (int) ((i2 * f) + 0.5f)));
        adView.setGravity(14);
        adView.setAdListener(new bg(this));
        addView(adView);
        com.google.ads.AdRequest adRequest2 = new com.google.ads.AdRequest();
        if (e) {
            Log.d("FlurryAgent", "Admob AdView set to Test Mode.");
            adRequest2.a(com.google.ads.AdRequest.a);
            if (sAdNetworkApiKey2 != null && sAdNetworkApiKey2 != "") {
                adRequest2.a(sAdNetworkApiKey2);
            }
        }
        adView.loadAd(adRequest2);
    }
}
