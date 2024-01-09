package com.google.ads;

import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes.dex */
public class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    private final int a;
    private final int b;
    private boolean c;
    private boolean d;
    private boolean e;
    private String f;
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    public static final AdSize BANNER = new AdSize(320, 50, "mb");
    public static final AdSize IAB_MRECT = new AdSize(300, 250, "as");
    public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");

    public AdSize(int i, int i2) {
        this(i, i2, null);
        if (!f()) {
            this.e = true;
            return;
        }
        this.e = false;
        this.f = "mb";
    }

    private AdSize(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.f = str;
        this.c = i == -1;
        this.d = i2 == -2;
        this.e = false;
    }

    private static int a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }

    public static AdSize a(AdSize adSize, Context context) {
        if (context == null || !adSize.f()) {
            return adSize.f() ? BANNER : adSize;
        }
        AdSize adSize2 = new AdSize(adSize.c ? a(context) : adSize.a(), adSize.d ? b(context) : adSize.b(), adSize.f);
        adSize2.d = adSize.d;
        adSize2.c = adSize.c;
        adSize2.e = adSize.e;
        return adSize2;
    }

    private static int b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.heightPixels / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    private boolean f() {
        return this.a < 0 || this.b < 0;
    }

    public int a() {
        if (this.a < 0) {
            throw new UnsupportedOperationException("Ad size was not set before getWidth() was called.");
        }
        return this.a;
    }

    public AdSize a(AdSize... adSizeArr) {
        double d;
        AdSize adSize;
        AdSize adSize2 = null;
        double d2 = 0.0d;
        if (adSizeArr != null) {
            int length = adSizeArr.length;
            int i = 0;
            while (i < length) {
                AdSize adSize3 = adSizeArr[i];
                if (a(adSize3.a, adSize3.b)) {
                    d = (adSize3.a * adSize3.b) / (this.a * this.b);
                    if (d > 1.0d) {
                        d = 1.0d / d;
                    }
                    if (d > d2) {
                        adSize = adSize3;
                        i++;
                        adSize2 = adSize;
                        d2 = d;
                    }
                }
                d = d2;
                adSize = adSize2;
                i++;
                adSize2 = adSize;
                d2 = d;
            }
        }
        return adSize2;
    }

    public boolean a(int i, int i2) {
        return ((double) i) <= ((double) this.a) * 1.25d && ((double) i) >= ((double) this.a) * 0.8d && ((double) i2) <= ((double) this.b) * 1.25d && ((double) i2) >= ((double) this.b) * 0.8d;
    }

    public int b() {
        if (this.b < 0) {
            throw new UnsupportedOperationException("Ad size was not set before getHeight() was called.");
        }
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AdSize) {
            AdSize adSize = (AdSize) obj;
            return this.a == adSize.a && this.b == adSize.b;
        }
        return false;
    }

    public int hashCode() {
        return (Integer.valueOf(this.a).hashCode() << 16) | (Integer.valueOf(this.b).hashCode() & 65535);
    }

    public String toString() {
        return a() + "x" + b() + (this.f == null ? "" : "_" + this.f);
    }
}
