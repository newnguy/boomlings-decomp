package com.tapjoy;

import android.content.Context;
import android.util.Log;
import java.util.Hashtable;

/* loaded from: classes.dex */
public final class g {
    private static g a = null;
    private static a b = null;
    private static aa c = null;
    private static t d = null;
    private static ao e = null;
    private static w f = null;
    private static n g = null;
    private static Hashtable h = null;

    private g(Context context, String str, String str2, Hashtable hashtable, m mVar) {
        h.a(context, str, str2, hashtable, mVar);
    }

    public static g a() {
        if (a == null) {
            Log.e("TapjoyConnect", "----------------------------------------");
            Log.e("TapjoyConnect", "ERROR -- call requestTapjoyConnect before any other Tapjoy methods");
            Log.e("TapjoyConnect", "----------------------------------------");
        }
        return a;
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, h);
    }

    public static void a(Context context, String str, String str2, Hashtable hashtable) {
        a(context, str, str2, hashtable, null);
    }

    public static void a(Context context, String str, String str2, Hashtable hashtable, m mVar) {
        h.a("offers");
        a = new g(context, str, str2, hashtable, mVar);
        b = new a(context);
        c = new aa(context);
        d = new t(context);
        e = new ao(context);
        f = new w(context);
        g = new n(context);
        h = null;
    }

    public void a(int i, al alVar) {
        b.a(i, alVar);
    }

    public void a(ac acVar) {
        c.a(acVar);
    }

    public void a(ak akVar) {
        b.a(akVar);
    }

    public void a(p pVar) {
        g.a(pVar);
    }

    public void a(v vVar) {
        b.a(vVar);
    }

    public void a(boolean z) {
        d.a(z);
    }

    public void b() {
        b.a();
    }

    public void c() {
        c.a();
    }

    public void d() {
        g.a();
    }

    public void e() {
        f.a();
    }
}
