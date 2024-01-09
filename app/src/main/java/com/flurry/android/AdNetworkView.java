package com.flurry.android;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AdNetworkView extends r {
    public static String sAdNetworkApiKey;
    public static String sAdNetworkApiKey2;
    public AdCreative fAdCreative;

    public AdNetworkView(Context context, AdCreative adCreative) {
        super(context);
        this.fAdCreative = adCreative;
    }

    public AdNetworkView(Context context, bo boVar, bl blVar, AdCreative adCreative) {
        super(context, boVar, blVar);
        this.fAdCreative = adCreative;
    }

    public void onAdClicked(Map map) {
        super.a("clicked", map);
    }

    public void onAdClosed(Map map) {
        super.a("adClosed", map);
    }

    public void onAdFilled(Map map) {
        super.a("filled", map);
    }

    public void onAdShown(Map map) {
        super.a("rendered", map);
    }

    public void onAdUnFilled(Map map) {
        super.a("unfilled", map);
    }

    @Override // com.flurry.android.r
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
