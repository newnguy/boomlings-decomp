package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Collections;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bh extends aj {
    private String b;
    private WeakReference c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bh(r rVar, AdUnit adUnit) {
        super(adUnit);
        this.b = getClass().getSimpleName();
        this.c = new WeakReference(rVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.flurry.android.aj
    public final void a(Context context, ViewGroup viewGroup) {
        int i;
        r rVar = (r) this.c.get();
        if (rVar == null) {
            return;
        }
        bo b = FlurryAgent.b();
        x a = b.d.a(b, context, viewGroup, this.a);
        a.removeAllViews();
        rVar.initLayout(context);
        if (viewGroup != a.getParent() && a.getParent() == null) {
            int childCount = viewGroup.getChildCount();
            if (rVar.c == null || rVar.c.c().size() < 1) {
                i = childCount;
            } else {
                String obj = ((AdFrame) rVar.c.c().get(0)).d().e().toString();
                if (obj.length() == 3) {
                    if (obj.charAt(0) == 't') {
                        i = 0;
                    } else if (obj.charAt(0) == 'b') {
                        i = viewGroup.getChildCount();
                    }
                }
                i = childCount;
            }
            viewGroup.addView(a, i);
        }
        if (rVar.getParent() == null) {
            a.addView(rVar);
        }
        rVar.a(viewGroup, a);
        rVar.a("rendered", Collections.emptyMap());
    }
}
