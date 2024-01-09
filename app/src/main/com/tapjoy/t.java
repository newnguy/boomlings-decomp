package com.tapjoy;

import android.content.Context;

/* loaded from: classes.dex */
public class t {
    private static am a = null;
    private static String d;
    private static int e;
    private static int f;
    private Context b;
    private boolean c;

    public t(Context context) {
        a("640x100");
        this.b = context;
        a = new am();
    }

    public void a(String str) {
        d = str;
        if (str.equals("320x50")) {
            e = 320;
            f = 50;
        } else if (str.equals("640x100")) {
            e = 640;
            f = 100;
        } else if (str.equals("768x90")) {
            e = 768;
            f = 90;
        }
    }

    public void a(boolean z) {
        this.c = z;
    }
}
