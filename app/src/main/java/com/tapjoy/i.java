package com.tapjoy;

/* loaded from: classes.dex */
public final class i implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        String str;
        am amVar;
        boolean g;
        String str2;
        aj.a("TapjoyConnect", "setUserID...");
        String str3 = h.c() + "&publisher_user_id=" + h.e();
        str = h.F;
        if (!str.equals("")) {
            StringBuilder append = new StringBuilder().append(str3).append("&");
            str2 = h.F;
            str3 = append.append(str2).toString();
        }
        amVar = h.c;
        String b = amVar.b("https://ws.tapjoyads.com/set_publisher_user_id?", str3);
        if (b != null) {
            g = h.g(b);
            if (g) {
            }
            aj.a("TapjoyConnect", "setUserID successful...");
        }
    }
}
