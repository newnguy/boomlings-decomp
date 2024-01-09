package com.flurry.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public final class bf extends WebChromeClient {
    final /* synthetic */ ap a;

    public /* synthetic */ bf(ap apVar) {
        this(apVar, (byte) 0);
    }

    private bf(ap apVar, byte b) {
        this.a = apVar;
    }

    @Override // android.webkit.WebChromeClient
    public final void onHideCustomView() {
        Activity activity;
        View view;
        Dialog dialog;
        Activity activity2;
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        View view2;
        Dialog dialog2;
        WebChromeClient.CustomViewCallback customViewCallback;
        Activity activity3;
        int i;
        Dialog dialog3;
        Dialog dialog4;
        Dialog dialog5;
        Dialog dialog6;
        Dialog dialog7;
        activity = this.a.w;
        if (activity == null) {
            return;
        }
        view = this.a.j;
        if (view != null) {
            dialog = this.a.o;
            if (dialog != null) {
                dialog7 = this.a.o;
                dialog7.show();
            }
            activity2 = this.a.w;
            frameLayout = this.a.n;
            ((FrameLayout) activity2.getWindow().getDecorView()).removeView(frameLayout);
            frameLayout2 = this.a.n;
            view2 = this.a.j;
            frameLayout2.removeView(view2);
            dialog2 = this.a.m;
            if (dialog2 != null) {
                dialog3 = this.a.m;
                if (dialog3.isShowing()) {
                    dialog4 = this.a.m;
                    dialog4.hide();
                    dialog5 = this.a.m;
                    dialog5.setOnDismissListener(null);
                    dialog6 = this.a.m;
                    dialog6.dismiss();
                }
            }
            this.a.m = null;
            customViewCallback = this.a.l;
            customViewCallback.onCustomViewHidden();
            activity3 = this.a.w;
            i = this.a.k;
            activity3.setRequestedOrientation(i);
            this.a.l = null;
            this.a.n = null;
            this.a.j = null;
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        Activity activity;
        View view2;
        Context context;
        FrameLayout frameLayout;
        FrameLayout frameLayout2;
        View view3;
        Activity activity2;
        FrameLayout frameLayout3;
        Dialog dialog;
        Activity activity3;
        Context context2;
        Dialog dialog2;
        Dialog dialog3;
        Dialog dialog4;
        Dialog dialog5;
        Dialog dialog6;
        WebChromeClient webChromeClient;
        WebChromeClient webChromeClient2;
        activity = this.a.w;
        if (activity == null) {
            return;
        }
        view2 = this.a.j;
        if (view2 != null) {
            webChromeClient = this.a.i;
            if (webChromeClient != null) {
                webChromeClient2 = this.a.i;
                webChromeClient2.onHideCustomView();
                return;
            }
        }
        this.a.j = view;
        this.a.k = i;
        this.a.l = customViewCallback;
        ap apVar = this.a;
        context = this.a.v;
        apVar.n = new FrameLayout(context);
        frameLayout = this.a.n;
        frameLayout.setBackgroundColor(-16777216);
        frameLayout2 = this.a.n;
        view3 = this.a.j;
        frameLayout2.addView(view3, new FrameLayout.LayoutParams(-1, -1, 17));
        activity2 = this.a.w;
        frameLayout3 = this.a.n;
        ((FrameLayout) activity2.getWindow().getDecorView()).addView(frameLayout3, -1, -1);
        dialog = this.a.m;
        if (dialog == null) {
            ap apVar2 = this.a;
            context2 = this.a.v;
            apVar2.m = new t(this, context2, 16973841);
            dialog2 = this.a.m;
            dialog2.getWindow().setType(1000);
            dialog3 = this.a.m;
            dialog3.setOnShowListener(new s(this));
            dialog4 = this.a.m;
            dialog4.setOnDismissListener(new q(this));
            dialog5 = this.a.m;
            dialog5.setCancelable(true);
            dialog6 = this.a.m;
            dialog6.show();
        }
        activity3 = this.a.w;
        activity3.setRequestedOrientation(i);
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Activity activity;
        Activity activity2;
        activity = this.a.w;
        if (activity == null) {
            return;
        }
        activity2 = this.a.w;
        onShowCustomView(view, activity2.getRequestedOrientation(), customViewCallback);
    }
}
