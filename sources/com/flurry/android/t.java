package com.flurry.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class t extends Dialog {
    private /* synthetic */ bf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(bf bfVar, Context context, int i) {
        super(context, i);
        this.a = bfVar;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Activity activity;
        Activity activity2;
        activity = this.a.a.w;
        if (activity != null) {
            activity2 = this.a.a.w;
            return activity2.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Activity activity;
        Activity activity2;
        activity = this.a.a.w;
        if (activity != null) {
            activity2 = this.a.a.w;
            return activity2.dispatchTrackballEvent(motionEvent);
        }
        return false;
    }
}
