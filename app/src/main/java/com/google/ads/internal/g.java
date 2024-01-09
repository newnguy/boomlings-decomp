package com.google.ads.internal;

import android.os.SystemClock;
import com.google.ads.g;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class g {
    private static long f = 0;
    private static long g = 0;
    private static long h = 0;
    private static long i = 0;
    private static long j = -1;
    private long b;
    private long c;
    private long d;
    private String m;
    private long n;
    private boolean k = false;
    private boolean l = false;
    private final LinkedList a = new LinkedList();
    private final LinkedList e = new LinkedList();
    private final LinkedList o = new LinkedList();
    private final LinkedList p = new LinkedList();

    public g() {
        a();
    }

    public static long E() {
        if (j == -1) {
            j = SystemClock.elapsedRealtime();
            return 0L;
        }
        return SystemClock.elapsedRealtime() - j;
    }

    public boolean A() {
        return this.l;
    }

    public void B() {
        com.google.ads.util.b.d("Interstitial no fill.");
        this.l = true;
    }

    public void C() {
        com.google.ads.util.b.d("Landing page dismissed.");
        this.e.add(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public String D() {
        return this.m;
    }

    public synchronized void a() {
        this.a.clear();
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e.clear();
        this.n = -1L;
        this.o.clear();
        this.p.clear();
        this.k = false;
        this.l = false;
    }

    public synchronized void a(g.a aVar) {
        this.o.add(Long.valueOf(SystemClock.elapsedRealtime() - this.n));
        this.p.add(aVar);
    }

    public void a(String str) {
        com.google.ads.util.b.d("Prior impression ticket = " + str);
        this.m = str;
    }

    public synchronized void b() {
        this.o.clear();
        this.p.clear();
    }

    public synchronized void c() {
        this.n = SystemClock.elapsedRealtime();
    }

    public synchronized String d() {
        StringBuilder sb;
        sb = new StringBuilder();
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            long longValue = ((Long) it.next()).longValue();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(longValue);
        }
        return sb.toString();
    }

    public synchronized String e() {
        StringBuilder sb;
        sb = new StringBuilder();
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            g.a aVar = (g.a) it.next();
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(aVar.ordinal());
        }
        return sb.toString();
    }

    public void f() {
        com.google.ads.util.b.d("Ad clicked.");
        this.a.add(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public void g() {
        com.google.ads.util.b.d("Ad request loaded.");
        this.b = SystemClock.elapsedRealtime();
    }

    public synchronized void h() {
        com.google.ads.util.b.d("Ad request before rendering.");
        this.c = SystemClock.elapsedRealtime();
    }

    public void i() {
        com.google.ads.util.b.d("Ad request started.");
        this.d = SystemClock.elapsedRealtime();
        f++;
    }

    public long j() {
        if (this.a.size() != this.e.size()) {
            return -1L;
        }
        return this.a.size();
    }

    public String k() {
        if (this.a.isEmpty() || this.a.size() != this.e.size()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.a.size()) {
                return sb.toString();
            }
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(((Long) this.e.get(i3)).longValue() - ((Long) this.a.get(i3)).longValue()));
            i2 = i3 + 1;
        }
    }

    public String l() {
        if (this.a.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.a.size()) {
                return sb.toString();
            }
            if (i3 != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(((Long) this.a.get(i3)).longValue() - this.b));
            i2 = i3 + 1;
        }
    }

    public long m() {
        return this.b - this.d;
    }

    public synchronized long n() {
        return this.c - this.d;
    }

    public long o() {
        return f;
    }

    public synchronized long p() {
        return g;
    }

    public synchronized void q() {
        com.google.ads.util.b.d("Ad request network error");
        g++;
    }

    public synchronized void r() {
        g = 0L;
    }

    public synchronized long s() {
        return h;
    }

    public synchronized void t() {
        h++;
    }

    public synchronized void u() {
        h = 0L;
    }

    public synchronized long v() {
        return i;
    }

    public synchronized void w() {
        i++;
    }

    public synchronized void x() {
        i = 0L;
    }

    public boolean y() {
        return this.k;
    }

    public void z() {
        com.google.ads.util.b.d("Interstitial network error.");
        this.k = true;
    }
}
