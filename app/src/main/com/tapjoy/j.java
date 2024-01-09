package com.tapjoy;

/* loaded from: classes.dex */
public class j implements Runnable {
    final /* synthetic */ h a;

    public j(h hVar) {
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        am amVar;
        m mVar;
        m mVar2;
        boolean g;
        m mVar3;
        m mVar4;
        String str;
        String str2;
        String str3;
        am amVar2;
        m mVar5;
        m mVar6;
        aj.a("TapjoyConnect", "starting connect call...");
        String c = h.c();
        amVar = h.c;
        ai a = amVar.a("https://ws.tapjoyads.com/connect?", c);
        if (a == null || a.a != 200) {
            mVar = h.d;
            if (mVar != null) {
                mVar2 = h.d;
                mVar2.b();
                return;
            }
            return;
        }
        g = h.g(a.c);
        if (g) {
            aj.a("TapjoyConnect", "Successfully connected to tapjoy site.");
            mVar5 = h.d;
            if (mVar5 != null) {
                mVar6 = h.d;
                mVar6.a();
            }
        } else {
            mVar3 = h.d;
            if (mVar3 != null) {
                mVar4 = h.d;
                mVar4.b();
            }
        }
        str = h.P;
        if (str.length() > 0) {
            StringBuilder append = new StringBuilder().append(h.d()).append("&").append("package_names").append("=");
            str2 = h.P;
            String sb = append.append(str2).append("&").toString();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            str3 = h.P;
            String str4 = (sb + "timestamp=" + currentTimeMillis + "&") + "verifier=" + h.a(currentTimeMillis, str3);
            amVar2 = h.c;
            ai a2 = amVar2.a("https://ws.tapjoyads.com/apps_installed?", str4);
            if (a2 == null || a2.a != 200) {
                return;
            }
            aj.a("TapjoyConnect", "Successfully pinged sdkless api.");
        }
    }
}
