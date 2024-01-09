package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.jumptap.adtag.JtAdInterstitial;
import com.jumptap.adtag.JtAdView;
import com.jumptap.adtag.JtAdWidgetSettings;
import com.jumptap.adtag.JtAdWidgetSettingsFactory;
import com.jumptap.adtag.utils.JtException;

/* loaded from: classes.dex */
public final class g extends AdNetworkView {
    public g(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar, adCreative);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("FlurryAgent", "Cannot find manifest for app");
        }
        String string = applicationInfo.metaData.getString("com.flurry.jumptap.PUBLISHER_ID");
        sAdNetworkApiKey = string;
        if (string == null) {
            Log.d("FlurryAgent", "com.flurry.jumptap.PUBLISHER_ID not set in manifest");
        }
        setFocusable(true);
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        JtAdInterstitial jtAdInterstitial;
        JtAdView jtAdView = null;
        if (!this.fAdCreative.getFormat().equals(AdCreative.kFormatTakeover)) {
            JtAdWidgetSettings createWidgetSettings = JtAdWidgetSettingsFactory.createWidgetSettings();
            createWidgetSettings.setPublisherId(sAdNetworkApiKey);
            createWidgetSettings.setRefreshPeriod(0);
            createWidgetSettings.setShouldSendLocation(false);
            try {
                jtAdView = new JtAdView((Activity) context, createWidgetSettings);
            } catch (JtException e) {
                Log.d("FlurryAgent", "Jumptap JtException when creating ad object.");
            }
            jtAdView.setAdViewListener(new w(this));
            addView(jtAdView);
            return;
        }
        JtAdWidgetSettings createWidgetSettings2 = JtAdWidgetSettingsFactory.createWidgetSettings();
        createWidgetSettings2.setPublisherId(sAdNetworkApiKey);
        createWidgetSettings2.setRefreshPeriod(0);
        createWidgetSettings2.setShouldSendLocation(false);
        try {
            jtAdInterstitial = new JtAdInterstitial((Activity) context, createWidgetSettings2);
        } catch (JtException e2) {
            Log.d("FlurryAgent", "Jumptap JtException when creating ad object.");
            jtAdInterstitial = null;
        }
        jtAdInterstitial.setAdViewListener(new aw(this));
        jtAdInterstitial.showAsPopup();
    }
}
