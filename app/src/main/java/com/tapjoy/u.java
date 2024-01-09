package com.tapjoy;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class u {
    private Context a;
    private Configuration b;
    private DisplayMetrics c = new DisplayMetrics();

    public u(Context context) {
        this.a = context;
        ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getMetrics(this.c);
        this.b = this.a.getResources().getConfiguration();
    }

    public int a() {
        return this.c.densityDpi;
    }

    public int b() {
        return this.b.screenLayout & 15;
    }
}
