package com.b.a;

import android.content.Context;
import android.os.Bundle;

/* loaded from: classes.dex */
public class a {
    f a;

    public a(f fVar) {
        this.a = fVar;
    }

    public void a(Context context, d dVar) {
        a(context, dVar, (Object) null);
    }

    public void a(Context context, d dVar, Object obj) {
        new b(this, context, dVar, obj).start();
    }

    public void a(String str, Bundle bundle, d dVar) {
        a(str, bundle, "GET", dVar, null);
    }

    public void a(String str, Bundle bundle, String str2, d dVar, Object obj) {
        new c(this, str, bundle, str2, dVar, obj).start();
    }

    public void a(String str, d dVar) {
        a(str, new Bundle(), "GET", dVar, null);
    }
}
