package com.chartboost.sdk;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.n;
import com.chartboost.sdk.impl.r;
import com.chartboost.sdk.impl.s;

/* loaded from: classes.dex */
public final class a {
    private Chartboost a;
    private Activity b;
    private r c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private s g;
    private s h;

    /* renamed from: com.chartboost.sdk.a$a */
    /* loaded from: classes.dex */
    public final class C0002a {
        protected boolean a;
        protected com.chartboost.sdk.impl.a b;

        public C0002a(com.chartboost.sdk.impl.a aVar) {
            this.a = false;
            this.b = aVar;
        }

        public C0002a(boolean z, com.chartboost.sdk.impl.a aVar) {
            this.a = z;
            this.b = aVar;
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        private com.chartboost.sdk.impl.a b;
        private boolean c;

        public b(com.chartboost.sdk.impl.a aVar, boolean z) {
            a.this = r1;
            this.b = aVar;
            this.c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.b.c == a.b.CBImpressionStateWaitingForDismissal) {
                this.b.c = a.b.CBImpressionStateOther;
                a.this.b(this.b, this.c);
            }
        }
    }

    public a(Chartboost chartboost, Activity activity) {
        this.a = chartboost;
        this.b = activity;
    }

    private void b() {
        this.c = new r(this.b);
        this.h = new s(this.b, this.c);
        this.h.b().a(true);
        this.b.addContentView(this.h, new FrameLayout.LayoutParams(-1, -1));
        this.h.b().a();
        this.h.b().a((View) this.c.getParent());
        this.d = true;
    }

    private void b(com.chartboost.sdk.impl.a aVar) {
        if (this.e) {
            return;
        }
        aVar.c = a.b.CBImpressionStateWaitingForDisplay;
        if (!aVar.f.b()) {
            if (aVar.f.d != null) {
                aVar.f.d.a();
            }
        } else if (aVar.i) {
            aVar.i = false;
            this.g = new s(this.b, aVar.f.d());
            this.b.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
            aVar.c = a.b.CBImpressionStateDisplayedByDefaultController;
            aVar.h = this.g;
            this.e = true;
        } else {
            this.g = new s(this.b, aVar.f.d());
            this.b.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
            this.g.b().a();
            n.b bVar = n.b.CBAnimationTypePerspectiveRotate;
            if (aVar.d == a.c.CBImpressionTypeMoreApps) {
                bVar = n.b.CBAnimationTypePerspectiveZoom;
            }
            if (aVar.a.optInt("animation") != 0) {
                bVar = n.b.valuesCustom()[aVar.a.optInt("animation")];
            }
            aVar.c = a.b.CBImpressionStateDisplayedByDefaultController;
            aVar.h = this.g;
            n.a(bVar, aVar);
            this.e = true;
            ChartboostDelegate delegate = this.a.getDelegate();
            if (delegate != null) {
                if (aVar.d == a.c.CBImpressionTypeInterstitial) {
                    delegate.didShowInterstitial(aVar.e);
                } else if (aVar.d == a.c.CBImpressionTypeMoreApps) {
                    delegate.didShowMoreApps();
                }
            }
        }
    }

    private n.a c(com.chartboost.sdk.impl.a aVar) {
        return new n.a() { // from class: com.chartboost.sdk.a.1
            @Override // com.chartboost.sdk.impl.n.a
            public void a(com.chartboost.sdk.impl.a aVar2) {
                a.this.a.a.post(new b(aVar2, false));
            }
        };
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public void a(C0002a c0002a) {
        if (c0002a.a) {
            b();
        } else if (c0002a.b != null) {
            b(c0002a.b);
        }
    }

    public void a(com.chartboost.sdk.impl.a aVar) {
        this.e = false;
        new b(aVar, true).run();
        if (aVar.c == a.b.CBImpressionStateDisplayedByDefaultController) {
            aVar.c = a.b.CBImpressionStateWaitingForDisplay;
        } else {
            aVar.c = a.b.CBImpressionStateOther;
        }
        aVar.c();
        try {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        } catch (Exception e) {
        }
    }

    public void a(com.chartboost.sdk.impl.a aVar, boolean z) {
        this.e = false;
        if (!z) {
            this.f = true;
        }
        aVar.c = a.b.CBImpressionStateWaitingForDismissal;
        n.b bVar = n.b.CBAnimationTypePerspectiveRotate;
        if (aVar.d == a.c.CBImpressionTypeMoreApps) {
            bVar = n.b.CBAnimationTypePerspectiveZoom;
        }
        if (aVar.a.optInt("animation") != 0) {
            bVar = n.b.valuesCustom()[aVar.a.optInt("animation")];
        }
        n.b(bVar, aVar, c(aVar));
    }

    public void a(boolean z) {
        if (a()) {
            try {
                ((ViewGroup) this.h.getParent()).removeView(this.h);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.c = null;
            this.h = null;
            this.d = false;
            if (!this.f && z && this.a.getImpressionsUseActivities() && !this.e && (this.b instanceof CBImpressionActivity)) {
                this.a.c();
                this.b.finish();
            }
        }
    }

    public boolean a() {
        return this.d;
    }

    public void b(com.chartboost.sdk.impl.a aVar, boolean z) {
        if (this.g == null) {
            return;
        }
        try {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        aVar.b();
        this.g = null;
        this.f = false;
        if (!this.a.getImpressionsUseActivities() || z || this.d || !(this.b instanceof CBImpressionActivity)) {
            return;
        }
        this.a.c();
        this.b.finish();
    }
}
