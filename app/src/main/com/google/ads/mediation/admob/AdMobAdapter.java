package com.google.ads.mediation.admob;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.flurry.android.Constants;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.util.AdUtil;

/* loaded from: classes.dex */
public class AdMobAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
    private MediationBannerListener a;
    private MediationInterstitialListener b;
    private AdView c;
    private InterstitialAd d;

    private AdRequest a(Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        AdMobAdapterExtras adMobAdapterExtras2 = new AdMobAdapterExtras(adMobAdapterExtras);
        adMobAdapterExtras2.b("_norefresh", Constants.ALIGN_TOP);
        adMobAdapterExtras2.b("gw", 1);
        if (adMobAdapterServerParameters.b != null) {
            adMobAdapterExtras2.b("mad_hac", adMobAdapterServerParameters.b);
        }
        AdRequest a = new AdRequest().a(mediationAdRequest.b()).a(mediationAdRequest.a()).a(mediationAdRequest.c()).a(mediationAdRequest.d()).a(adMobAdapterExtras2);
        if (mediationAdRequest.e()) {
            a.a(AdUtil.a((Context) activity));
        }
        return a;
    }

    private void f() {
        if (g()) {
            throw new IllegalStateException("Adapter has already been destroyed");
        }
    }

    private boolean g() {
        return this.c == null && this.d == null;
    }

    protected AdView a(Activity activity, AdSize adSize, String str) {
        return new AdView(activity, adSize, str);
    }

    protected InterstitialAd a(Activity activity, String str) {
        return new InterstitialAd(activity, str);
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public void a() {
        f();
        if (this.c != null) {
            this.c.stopLoading();
            this.c.destroy();
            this.c = null;
        }
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public void a(MediationBannerListener mediationBannerListener, Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        this.a = mediationBannerListener;
        if (!adSize.d() && !adSize.c() && ((adMobAdapterExtras == null || !adMobAdapterExtras.e()) && (adSize = adSize.a(AdSize.BANNER, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_MRECT, AdSize.IAB_WIDE_SKYSCRAPER)) == null)) {
            mediationBannerListener.a(this, AdRequest.ErrorCode.NO_FILL);
            return;
        }
        this.c = a(activity, adSize, adMobAdapterServerParameters.a);
        this.c.setAdListener(new b(this));
        this.c.loadAd(a(activity, adMobAdapterServerParameters, mediationAdRequest, adMobAdapterExtras));
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void a(MediationInterstitialListener mediationInterstitialListener, Activity activity, AdMobAdapterServerParameters adMobAdapterServerParameters, MediationAdRequest mediationAdRequest, AdMobAdapterExtras adMobAdapterExtras) {
        this.b = mediationInterstitialListener;
        this.d = a(activity, adMobAdapterServerParameters.a);
        this.d.a(new c(this));
        this.d.a(a(activity, adMobAdapterServerParameters, mediationAdRequest, adMobAdapterExtras));
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class b() {
        return AdMobAdapterExtras.class;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class c() {
        return AdMobAdapterServerParameters.class;
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public View d() {
        return this.c;
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void e() {
        this.d.a();
    }
}
