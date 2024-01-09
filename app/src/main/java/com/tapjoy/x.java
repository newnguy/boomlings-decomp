package com.tapjoy;

/* loaded from: classes.dex */
public class x implements Runnable {
    final /* synthetic */ w a;
    private String b;

    public x(w wVar, String str) {
        this.a = wVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        am amVar;
        amVar = w.a;
        ai a = amVar.a("https://ws.tapjoyads.com/user_events?", this.b, 1);
        if (a == null) {
            aj.b("Event", "Server/network error");
            return;
        }
        switch (a.a) {
            case 200:
                aj.a("Event", "Successfully sent Tapjoy event");
                return;
            case 400:
                aj.b("Event", "Error sending event: " + a.c);
                return;
            default:
                aj.b("Event", "Server/network error: " + a.a);
                return;
        }
    }
}
