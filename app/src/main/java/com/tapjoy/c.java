package com.tapjoy;

/* loaded from: classes.dex */
public class c implements Runnable {
    final /* synthetic */ a a;

    public c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        al alVar;
        String b = new am().b("https://ws.tapjoyads.com/points/spend?", (h.c() + "&tap_points=" + this.a.a) + "&publisher_user_id=" + h.e());
        if (b != null ? this.a.b(b) : false) {
            return;
        }
        alVar = a.e;
        alVar.getSpendPointsResponseFailed("Failed to spend points.");
    }
}
