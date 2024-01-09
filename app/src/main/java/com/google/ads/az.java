package com.google.ads;

import com.google.ads.AdRequest;
import com.google.ads.g;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;

/* loaded from: classes.dex */
public class az implements MediationInterstitialListener {
    private final h a;

    public az(h hVar) {
        this.a = hVar;
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void a(MediationInterstitialAdapter mediationInterstitialAdapter) {
        synchronized (this.a) {
            com.google.ads.util.a.a(mediationInterstitialAdapter, this.a.i());
            if (this.a.c()) {
                com.google.ads.util.b.b("Got an onReceivedAd() callback after loadAdTask is done from an interstitial adapter. Ignoring callback.");
            } else {
                this.a.a(true, g.a.AD);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void a(MediationInterstitialAdapter mediationInterstitialAdapter, AdRequest.ErrorCode errorCode) {
        synchronized (this.a) {
            com.google.ads.util.a.a(mediationInterstitialAdapter, this.a.i());
            com.google.ads.util.b.a("Mediation adapter " + mediationInterstitialAdapter.getClass().getName() + " failed to receive ad with error code: " + errorCode);
            if (this.a.c()) {
                com.google.ads.util.b.b("Got an onFailedToReceiveAd() callback after loadAdTask is done from an interstitial adapter.  Ignoring callback.");
            } else {
                this.a.a(false, errorCode == AdRequest.ErrorCode.NO_FILL ? g.a.NO_FILL : g.a.ERROR);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void b(MediationInterstitialAdapter mediationInterstitialAdapter) {
        synchronized (this.a) {
            this.a.j().a(this.a);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void c(MediationInterstitialAdapter mediationInterstitialAdapter) {
        synchronized (this.a) {
            this.a.j().b(this.a);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialListener
    public void d(MediationInterstitialAdapter mediationInterstitialAdapter) {
        synchronized (this.a) {
            this.a.j().c(this.a);
        }
    }
}
