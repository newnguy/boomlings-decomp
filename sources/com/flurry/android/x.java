package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class x extends RelativeLayout {
    private bo a;
    private Context b;
    private String c;
    private FlurryAdSize d;
    private ViewGroup e;
    private int f;
    private boolean g;

    /* JADX INFO: Access modifiers changed from: package-private */
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.f = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.g = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ViewGroup c() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String d() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f() {
        r e = e();
        if (e == null || !e.a()) {
            return;
        }
        this.a.a(this.b, this.c, this.d, this.e, 1L);
    }
}
