package com.tapjoy;

/* loaded from: classes.dex */
public class k implements Runnable {
    final /* synthetic */ h a;
    private String b;

    public k(h hVar, String str) {
        this.a = hVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        am amVar;
        amVar = h.c;
        String b = amVar.b("https://ws.tapjoyads.com/connect?", this.b);
        if (b != null) {
            this.a.h(b);
        }
    }
}
