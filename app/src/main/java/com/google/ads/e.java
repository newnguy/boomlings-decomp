package com.google.ads;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import com.google.ads.g;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class e {
    private final com.google.ads.internal.d a;
    private h b;
    private Object c;
    private Thread d;
    private Object e;
    private boolean f;
    private Object g;

    protected e() {
        this.b = null;
        this.c = new Object();
        this.d = null;
        this.e = new Object();
        this.f = false;
        this.g = new Object();
        this.a = null;
    }

    public e(com.google.ads.internal.d dVar) {
        this.b = null;
        this.c = new Object();
        this.d = null;
        this.e = new Object();
        this.f = false;
        this.g = new Object();
        com.google.ads.util.a.b(dVar);
        this.a = dVar;
    }

    public static boolean a(c cVar, com.google.ads.internal.d dVar) {
        if (cVar.j() == null) {
            return true;
        }
        if (dVar.h().b()) {
            if (cVar.j().a()) {
                return true;
            }
            com.google.ads.util.b.e("InterstitialAd received a mediation response corresponding to a non-interstitial ad. Make sure you specify 'interstitial' as the ad-type in the mediation UI.");
            return false;
        }
        AdSize b = ((com.google.ads.internal.h) dVar.h().k.a()).b();
        if (cVar.j().a()) {
            com.google.ads.util.b.e("AdView received a mediation response corresponding to an interstitial ad. Make sure you specify the banner ad size corresponding to the AdSize you used in your AdView  (" + b + ") in the ad-type field in the mediation UI.");
            return false;
        }
        AdSize b2 = cVar.j().b();
        if (b2 != b) {
            com.google.ads.util.b.e("Mediation server returned ad size: '" + b2 + "', while the AdView was created with ad size: '" + b + "'. Using the ad-size passed to the AdView on creation.");
            return false;
        }
        return true;
    }

    private boolean a(h hVar, String str) {
        if (e() != hVar) {
            com.google.ads.util.b.c("GWController: ignoring callback to " + str + " from non showing ambassador with adapter class: '" + hVar.h() + "'.");
            return false;
        }
        return true;
    }

    private boolean a(String str, Activity activity, AdRequest adRequest, f fVar, HashMap hashMap, long j) {
        h hVar = new h(this, (com.google.ads.internal.h) this.a.h().k.a(), fVar, str, adRequest, hashMap);
        synchronized (hVar) {
            hVar.a(activity);
            while (!hVar.c() && j > 0) {
                try {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    hVar.wait(j);
                    j -= SystemClock.elapsedRealtime() - elapsedRealtime;
                } catch (InterruptedException e) {
                    com.google.ads.util.b.a("Interrupted while waiting for ad network to load ad using adapter class: " + str);
                }
            }
            this.a.m().a(hVar.e());
            if (hVar.c() && hVar.d()) {
                this.a.a(new at(this, hVar, this.a.h().b() ? null : hVar.f(), fVar));
                return true;
            }
            hVar.b();
            return false;
        }
    }

    public void b(c cVar, AdRequest adRequest) {
        synchronized (this.e) {
            com.google.ads.util.a.a(Thread.currentThread(), this.d);
        }
        List<a> f = cVar.f();
        long b = cVar.a() ? cVar.b() : 10000L;
        for (a aVar : f) {
            com.google.ads.util.b.a("Looking to fetch ads from network: " + aVar.b());
            List<String> c = aVar.c();
            HashMap e = aVar.e();
            List d = aVar.d();
            String a = aVar.a();
            String b2 = aVar.b();
            String c2 = cVar.c();
            if (d == null) {
                d = cVar.g();
            }
            f fVar = new f(a, b2, c2, d, cVar.h(), cVar.i());
            for (String str : c) {
                Activity activity = (Activity) this.a.h().e.a();
                if (activity == null) {
                    com.google.ads.util.b.a("Activity is null while mediating.  Terminating mediation thread.");
                    return;
                }
                this.a.m().c();
                if (a(str, activity, adRequest, fVar, e, b)) {
                    return;
                }
                if (d()) {
                    com.google.ads.util.b.a("GWController.destroy() called. Terminating mediation thread.");
                    return;
                }
            }
        }
        this.a.a(new as(this, cVar));
    }

    private boolean d() {
        boolean z;
        synchronized (this.g) {
            z = this.f;
        }
        return z;
    }

    private h e() {
        h hVar;
        synchronized (this.c) {
            hVar = this.b;
        }
        return hVar;
    }

    public boolean e(h hVar) {
        boolean z;
        synchronized (this.g) {
            if (d()) {
                hVar.b();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public void a(c cVar, AdRequest adRequest) {
        synchronized (this.e) {
            if (a()) {
                com.google.ads.util.b.c("Mediation thread is not done executing previous mediation  request. Ignoring new mediation request");
                return;
            }
            a(cVar, this.a);
            this.d = new Thread(new am(this, cVar, adRequest));
            this.d.start();
        }
    }

    public void a(h hVar) {
        if (a(hVar, "onPresentScreen")) {
            this.a.a(new ap(this));
        }
    }

    public void a(h hVar, View view) {
        if (e() != hVar) {
            com.google.ads.util.b.c("GWController: ignoring onAdRefreshed() callback from non-showing ambassador (adapter class name is '" + hVar.h() + "').");
            return;
        }
        this.a.m().a(g.a.AD);
        this.a.a(new ao(this, view, this.b.a()));
    }

    public void a(h hVar, boolean z) {
        if (a(hVar, "onAdClicked()")) {
            this.a.a(new an(this, hVar.a(), z));
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.e) {
            z = this.d != null;
        }
        return z;
    }

    public void b() {
        synchronized (this.g) {
            this.f = true;
            d(null);
            synchronized (this.e) {
                if (this.d != null) {
                    this.d.interrupt();
                }
            }
        }
    }

    public void b(h hVar) {
        if (a(hVar, "onDismissScreen")) {
            this.a.a(new aq(this));
        }
    }

    public void c(h hVar) {
        if (a(hVar, "onLeaveApplication")) {
            this.a.a(new ar(this));
        }
    }

    public boolean c() {
        com.google.ads.util.a.a(this.a.h().b());
        h e = e();
        if (e != null) {
            e.g();
            return true;
        }
        com.google.ads.util.b.b("There is no ad ready to show.");
        return false;
    }

    public void d(h hVar) {
        synchronized (this.c) {
            if (this.b != hVar) {
                if (this.b != null) {
                    this.b.b();
                }
                this.b = hVar;
            }
        }
    }
}
