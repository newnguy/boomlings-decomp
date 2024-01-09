package com.flurry.android;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n extends AsyncTask {
    private Context a;
    private String b;
    private FlurryAdSize c;
    private ViewGroup d;
    private boolean e;
    private boolean f;
    private /* synthetic */ bo g;

    public n(bo boVar, Context context, String str, FlurryAdSize flurryAdSize, ViewGroup viewGroup, boolean z, boolean z2) {
        this.g = boVar;
        this.a = context;
        this.b = str;
        this.c = flurryAdSize;
        this.d = viewGroup;
        this.e = z2;
        this.f = z;
    }

    private List a() {
        try {
            return this.g.a(this.b, this.d.getWidth(), this.d.getHeight(), false, this.c);
        } catch (Throwable th) {
            th.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Object obj) {
        try {
            this.g.c.a((List) obj);
            if (this.f && !this.e) {
                this.g.a(this.a, this.b);
            } else if (this.f && this.e) {
                this.g.a(this.a, this.b, this.d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
