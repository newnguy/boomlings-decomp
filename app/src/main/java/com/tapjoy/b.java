package com.tapjoy;

/* loaded from: classes.dex */
public class b implements Runnable {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ak akVar;
        String b = new am().b("https://ws.tapjoyads.com/get_vg_store_items/user_account?", h.c() + "&publisher_user_id=" + h.e());
        if (b != null ? this.a.a(b) : false) {
            return;
        }
        akVar = a.d;
        akVar.getUpdatePointsFailed("Failed to retrieve points from server");
    }
}
