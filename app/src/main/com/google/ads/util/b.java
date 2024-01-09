package com.google.ads.util;

import android.util.Log;

/* loaded from: classes.dex */
public final class b {
    public static InterfaceC0007b a = null;
    private static int b = 5;

    /* loaded from: classes.dex */
    public enum a {
        VERBOSE(2),
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6);
        
        public final int f;

        a(int i) {
            this.f = i;
        }
    }

    /* renamed from: com.google.ads.util.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0007b {
        void a(a aVar, String str, Throwable th);
    }

    private static void a(a aVar, String str) {
        a(aVar, str, null);
    }

    private static void a(a aVar, String str, Throwable th) {
        if (a != null) {
            a.a(aVar, str, th);
        }
    }

    public static void a(String str) {
        if (a("Ads", 3)) {
            Log.d("Ads", str);
        }
        a(a.DEBUG, str);
    }

    public static void a(String str, Throwable th) {
        if (a("Ads", 3)) {
            Log.d("Ads", str, th);
        }
        a(a.DEBUG, str, th);
    }

    private static boolean a(int i) {
        return i >= b;
    }

    public static boolean a(String str, int i) {
        return a(i) || Log.isLoggable(str, i);
    }

    public static void b(String str) {
        if (a("Ads", 6)) {
            Log.e("Ads", str);
        }
        a(a.ERROR, str);
    }

    public static void b(String str, Throwable th) {
        if (a("Ads", 6)) {
            Log.e("Ads", str, th);
        }
        a(a.ERROR, str, th);
    }

    public static void c(String str) {
        if (a("Ads", 4)) {
            Log.i("Ads", str);
        }
        a(a.INFO, str);
    }

    public static void c(String str, Throwable th) {
        if (a("Ads", 4)) {
            Log.i("Ads", str, th);
        }
        a(a.INFO, str, th);
    }

    public static void d(String str) {
        if (a("Ads", 2)) {
            Log.v("Ads", str);
        }
        a(a.VERBOSE, str);
    }

    public static void d(String str, Throwable th) {
        if (a("Ads", 5)) {
            Log.w("Ads", str, th);
        }
        a(a.WARN, str, th);
    }

    public static void e(String str) {
        if (a("Ads", 5)) {
            Log.w("Ads", str);
        }
        a(a.WARN, str);
    }
}
