package com.google.ads;

import com.google.ads.AdRequest;
import com.google.ads.g;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;

/* loaded from: classes.dex */
public class ay implements MediationBannerListener {
    private final h a;
    private boolean b;

    public ay(h hVar) {
        this.a = hVar;
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void a(MediationBannerAdapter mediationBannerAdapter) {
        synchronized (this.a) {
            com.google.ads.util.a.a(mediationBannerAdapter, this.a.i());
            this.a.a(mediationBannerAdapter.d());
            if (this.a.c()) {
                this.b = true;
                this.a.j().a(this.a, this.a.f());
                return;
            }
            this.b = false;
            this.a.a(true, g.a.AD);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void a(MediationBannerAdapter mediationBannerAdapter, AdRequest.ErrorCode errorCode) {
        synchronized (this.a) {
            com.google.ads.util.a.a(mediationBannerAdapter, this.a.i());
            com.google.ads.util.b.a("Mediation adapter " + mediationBannerAdapter.getClass().getName() + " failed to receive ad with error code: " + errorCode);
            if (!this.a.c()) {
                this.a.a(false, errorCode == AdRequest.ErrorCode.NO_FILL ? g.a.NO_FILL : g.a.ERROR);
            }
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void b(MediationBannerAdapter mediationBannerAdapter) {
        synchronized (this.a) {
            this.a.j().a(this.a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void c(MediationBannerAdapter mediationBannerAdapter) {
        synchronized (this.a) {
            this.a.j().b(this.a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void d(MediationBannerAdapter mediationBannerAdapter) {
        synchronized (this.a) {
            this.a.j().c(this.a);
        }
    }

    @Override // com.google.ads.mediation.MediationBannerListener
    public void e(MediationBannerAdapter mediationBannerAdapter) {
        synchronized (this.a) {
            com.google.ads.util.a.a(this.a.c());
            this.a.j().a(this.a, this.b);
        }
    }
}
