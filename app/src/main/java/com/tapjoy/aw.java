package com.tapjoy;

/* loaded from: classes.dex */
class aw implements Runnable {
    final /* synthetic */ TapjoyVideoView a;

    public aw(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        as asVar;
        z = this.a.t;
        if (z) {
            h a = h.a();
            asVar = TapjoyVideoView.q;
            a.d(asVar.a);
        }
    }
}
