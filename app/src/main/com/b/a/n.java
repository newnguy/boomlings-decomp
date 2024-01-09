package com.b.a;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class n extends Dialog {
    static final float[] a = {20.0f, 60.0f};
    static final float[] b = {40.0f, 60.0f};
    static final FrameLayout.LayoutParams c = new FrameLayout.LayoutParams(-1, -1);
    private String d;
    private i e;
    private ProgressDialog f;
    private ImageView g;
    private WebView h;
    private FrameLayout i;

    public n(Context context, String str, i iVar) {
        super(context, 16973840);
        this.d = str;
        this.e = iVar;
    }

    private void a() {
        this.g = new ImageView(getContext());
        this.g.setOnClickListener(new o(this));
        this.g.setImageDrawable(getContext().getResources().getDrawable(r.close));
        this.g.setVisibility(4);
    }

    private void a(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.h = new WebView(getContext());
        this.h.setVerticalScrollBarEnabled(false);
        this.h.setHorizontalScrollBarEnabled(false);
        this.h.setWebViewClient(new p(this, null));
        this.h.getSettings().setJavaScriptEnabled(true);
        this.h.loadUrl(this.d);
        this.h.setLayoutParams(c);
        this.h.setVisibility(4);
        this.h.getSettings().setSavePassword(false);
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.h);
        this.i.addView(linearLayout);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new ProgressDialog(getContext());
        this.f.requestWindowFeature(1);
        this.f.setMessage("Loading...");
        requestWindowFeature(1);
        this.i = new FrameLayout(getContext());
        a();
        a(this.g.getDrawable().getIntrinsicWidth() / 2);
        this.i.addView(this.g, new ViewGroup.LayoutParams(-2, -2));
        addContentView(this.i, new ViewGroup.LayoutParams(-1, -1));
    }
}
