package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;

/* loaded from: classes.dex */
class b implements CustomEventInterstitialListener {
    final /* synthetic */ CustomEventAdapter a;
    private final MediationInterstitialListener b;

    public b(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this.a = customEventAdapter;
        this.b = mediationInterstitialListener;
    }
}
