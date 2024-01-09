package com.tapjoy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class au implements Runnable {
    final /* synthetic */ TapjoyVideoView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public au(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // java.lang.Runnable
    public void run() {
        as asVar;
        aj.a("VIDEO", "SENDING CLICK...");
        am amVar = new am();
        asVar = TapjoyVideoView.q;
        String a = amVar.a(asVar.b);
        if (a == null || !a.contains("OK")) {
            return;
        }
        aj.a("VIDEO", "CLICK REQUEST SUCCESS!");
        this.a.t = true;
    }
}
