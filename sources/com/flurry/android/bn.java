package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bn {
    private static String a = "FlurryAgent";
    private static Runnable f;
    private Map b = new HashMap();
    private Map c = new HashMap();
    private Map d = new HashMap();
    private Context e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bn(Context context) {
        this.e = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized x a(bo boVar, Context context, ViewGroup viewGroup, String str) {
        x xVar;
        WeakReference weakReference = (WeakReference) this.c.get(str);
        if (weakReference == null) {
            xVar = new x(boVar, context, str, viewGroup);
            this.c.put(str, new WeakReference(xVar));
        } else {
            xVar = (x) weakReference.get();
        }
        return xVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized x a(String str) {
        return this.c.containsKey(str) ? (x) ((WeakReference) this.c.get(str)).get() : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a() {
        List<x> list = (List) this.d.get(this.e);
        if (list != null) {
            for (x xVar : list) {
                if (xVar.a() > 0 && !xVar.b()) {
                    a(xVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(Context context) {
        this.e = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(x xVar) {
        f = new as(this, xVar);
        if (!xVar.b()) {
            xVar.postDelayed(f, xVar.a());
            xVar.a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(String str, aj ajVar) {
        this.b.put(str, ajVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void b(x xVar) {
        xVar.a(false);
        String str = "Rotating banner for adSpace: " + xVar.d();
        xVar.f();
        if (xVar.a() > 0) {
            a(xVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void b(String str) {
        this.c.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized aj c(String str) {
        aj ajVar;
        ajVar = (aj) this.b.get(str);
        if (ajVar == null) {
            ajVar = new ba();
        }
        return ajVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void d(String str) {
        if (this.b.containsKey(str)) {
            this.b.remove(str);
        }
    }
}
