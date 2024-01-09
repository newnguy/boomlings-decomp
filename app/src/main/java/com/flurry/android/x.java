package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public final class x extends RelativeLayout {
    private bo a;
    private Context b;
    private String c;
    private FlurryAdSize d;
    private ViewGroup e;
    private int f;
    private boolean g;

    public x(bo boVar, Context context, String str, ViewGroup viewGroup) {
        this(boVar, context, str, viewGroup, FlurryAdSize.BANNER_BOTTOM);
    }

    private x(bo boVar, Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize) {
        super(context);
        this.a = boVar;
        this.b = context;
        this.c = str;
        this.e = viewGroup;
        this.f = 0;
        this.g = false;
        this.d = flurryAdSize;
    }

    public final int a() {
        return this.f;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final boolean b() {
        return this.g;
    }

    public final ViewGroup c() {
        return this.e;
    }

    public final String d() {
        return this.c;
    }

    public final r e() {
        if (getChildCount() < 1) {
            return null;
        }
        try {
            return (r) getChildAt(0);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public final void f() {
        r e = e();
        if (e == null || !e.a()) {
            return;
        }
        this.a.a(this.b, this.c, this.d, this.e, 1L);
    }
}
