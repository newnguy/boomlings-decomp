package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
final class bd extends RelativeLayout implements View.OnClickListener {
    private final String a;
    private WebView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;

    public bd(Context context, String str) {
        super(context);
        this.a = getClass().getSimpleName();
        this.b = new WebView(context);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.getSettings().setUseWideViewPort(true);
        if (VersionUtil.SDK_INT >= 7) {
            this.b.getSettings().setLoadWithOverviewMode(true);
        }
        this.b.getSettings().setBuiltInZoomControls(true);
        if (VersionUtil.SDK_INT >= 11) {
            this.b.getSettings().setDisplayZoomControls(false);
        }
        this.b.setWebViewClient(new b(this));
        this.b.loadUrl(str);
        this.c = new ImageView(context);
        this.c.setId(0);
        this.c.setImageDrawable(getResources().getDrawable(17301560));
        this.c.setOnClickListener(this);
        this.d = new ImageView(context);
        this.d.setId(1);
        this.d.setImageDrawable(getResources().getDrawable(17301580));
        this.d.setOnClickListener(this);
        this.e = new ImageView(context);
        this.e.setId(2);
        this.e.setImageDrawable(getResources().getDrawable(17301565));
        this.e.setOnClickListener(this);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(this.b);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        addView(this.c, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(9);
        addView(this.d, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(11);
        addView(this.e, layoutParams3);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (view.getId()) {
            case 0:
                try {
                    ((Activity) getContext()).finish();
                    return;
                } catch (ClassCastException e) {
                    e.toString();
                    return;
                }
            case 1:
                this.b.goBack();
                return;
            case 2:
                this.b.goForward();
                return;
            default:
                return;
        }
    }
}
