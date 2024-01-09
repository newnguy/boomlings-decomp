package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import com.inmobi.androidsdk.IMAdInterstitial;
import com.inmobi.androidsdk.IMAdRequest;
import com.inmobi.androidsdk.IMAdView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ae extends AdNetworkView {
    private static boolean e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ae(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar, adCreative);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            Log.d("FlurryAgent", "Cannot find manifest for app");
        }
        Bundle bundle = applicationInfo.metaData;
        sAdNetworkApiKey = bundle.getString("com.flurry.inmobi.MY_APP_ID");
        e = bundle.getBoolean("com.flurry.inmobi.test");
        if (sAdNetworkApiKey == null) {
            Log.d("FlurryAgent", "com.flurry.inmobi.MY_APP_ID not set in manifest");
        }
        setFocusable(true);
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        int i;
        int i2 = 320;
        int i3 = 50;
        if (this.fAdCreative.getFormat().equals(AdCreative.kFormatTakeover)) {
            IMAdInterstitial iMAdInterstitial = new IMAdInterstitial((Activity) context, sAdNetworkApiKey);
            iMAdInterstitial.setImAdInterstitialListener(new m(this));
            IMAdRequest iMAdRequest = new IMAdRequest();
            if (e) {
                Log.d("FlurryAgent", "InMobi Interstitial set to Test Mode.");
                iMAdRequest.setTestMode(true);
            }
            iMAdInterstitial.loadNewAd(iMAdRequest);
            return;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i4 = (int) (displayMetrics.heightPixels / displayMetrics.density);
        int i5 = (int) (displayMetrics.widthPixels / displayMetrics.density);
        if (i5 >= 728 && i4 >= 90) {
            Log.d("FlurryAgent", "Determined InMobi AdSize as 728x90");
            i = 11;
        } else if (i5 >= 468 && i4 >= 60) {
            Log.d("FlurryAgent", "Determined InMobi AdSize as 468x60");
            i = 12;
        } else if (i5 >= 320 && i4 >= 50) {
            Log.d("FlurryAgent", "Determined InMobi AdSize as 320x50");
            i = 15;
        } else if (i5 >= 300 && i4 >= 250) {
            Log.d("FlurryAgent", "Determined InMobi AdSize as 300x250");
            i = 10;
        } else if (i5 < 120 || i4 < 600) {
            Log.d("FlurryAgent", "Could not find InMobi AdSize that matches size");
            i = -1;
        } else {
            Log.d("FlurryAgent", "Determined InMobi AdSize as 120x600");
            i = 13;
        }
        if (i == -1) {
            Log.d("FlurryAgent", "**********Could not load InMobi Ad");
            return;
        }
        IMAdView iMAdView = new IMAdView((Activity) context, i, sAdNetworkApiKey);
        if (i == 15) {
        }
        if (i == 11) {
            i2 = 728;
            i3 = 90;
        }
        if (i == 12) {
            i2 = 468;
            i3 = 60;
        }
        if (i == 10) {
            i2 = 300;
            i3 = 250;
        }
        if (i == 13) {
            i2 = 120;
            i3 = 600;
        }
        float f = getResources().getDisplayMetrics().density;
        iMAdView.setLayoutParams(new LinearLayout.LayoutParams((int) ((i2 * f) + 0.5f), (int) ((i3 * f) + 0.5f)));
        iMAdView.setGravity(14);
        iMAdView.setIMAdListener(new af(this));
        addView(iMAdView);
        IMAdRequest iMAdRequest2 = new IMAdRequest();
        if (e) {
            Log.d("FlurryAgent", "InMobi AdView set to Test Mode.");
            iMAdRequest2.setTestMode(true);
        }
        iMAdView.setIMAdRequest(iMAdRequest2);
        iMAdView.setRefreshInterval(-1);
        iMAdView.loadNewAd();
    }
}
