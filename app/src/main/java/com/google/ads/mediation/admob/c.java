package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationInterstitialListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements AdListener {
    final /* synthetic */ AdMobAdapter a;

    /* JADX INFO: Access modifiers changed from: private */
    public c(AdMobAdapter adMobAdapter) {
        this.a = adMobAdapter;
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
        MediationInterstitialListener mediationInterstitialListener;
        mediationInterstitialListener = this.a.b;
        mediationInterstitialListener.c(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        MediationInterstitialListener mediationInterstitialListener;
        mediationInterstitialListener = this.a.b;
        mediationInterstitialListener.a(this.a, errorCode);
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
        MediationInterstitialListener mediationInterstitialListener;
        mediationInterstitialListener = this.a.b;
        mediationInterstitialListener.d(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
        MediationInterstitialListener mediationInterstitialListener;
        mediationInterstitialListener = this.a.b;
        mediationInterstitialListener.b(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        MediationInterstitialListener mediationInterstitialListener;
        mediationInterstitialListener = this.a.b;
        mediationInterstitialListener.a(this.a);
    }
}
