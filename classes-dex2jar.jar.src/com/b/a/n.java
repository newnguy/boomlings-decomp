package com.b.a;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class n extends Dialog {
  static final float[] a = new float[] { 20.0F, 60.0F };
  
  static final float[] b = new float[] { 40.0F, 60.0F };
  
  static final FrameLayout.LayoutParams c = new FrameLayout.LayoutParams(-1, -1);
  
  private String d;
  
  private i e;
  
  private ProgressDialog f;
  
  private ImageView g;
  
  private WebView h;
  
  private FrameLayout i;
  
  public n(Context paramContext, String paramString, i parami) {
    super(paramContext, 16973840);
    this.d = paramString;
    this.e = parami;
  }
  
  private void a() {
    this.g = new ImageView(getContext());
    this.g.setOnClickListener(new o(this));
    Drawable drawable = getContext().getResources().getDrawable(r.close);
    this.g.setImageDrawable(drawable);
    this.g.setVisibility(4);
  }
  
  private void a(int paramInt) {
    LinearLayout linearLayout = new LinearLayout(getContext());
    this.h = new WebView(getContext());
    this.h.setVerticalScrollBarEnabled(false);
    this.h.setHorizontalScrollBarEnabled(false);
    this.h.setWebViewClient(new p(this, null));
    this.h.getSettings().setJavaScriptEnabled(true);
    this.h.loadUrl(this.d);
    this.h.setLayoutParams((ViewGroup.LayoutParams)c);
    this.h.setVisibility(4);
    this.h.getSettings().setSavePassword(false);
    linearLayout.setPadding(paramInt, paramInt, paramInt, paramInt);
    linearLayout.addView((View)this.h);
    this.i.addView((View)linearLayout);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.f = new ProgressDialog(getContext());
    this.f.requestWindowFeature(1);
    this.f.setMessage("Loading...");
    requestWindowFeature(1);
    this.i = new FrameLayout(getContext());
    a();
    a(this.g.getDrawable().getIntrinsicWidth() / 2);
    this.i.addView((View)this.g, new ViewGroup.LayoutParams(-2, -2));
    addContentView((View)this.i, new ViewGroup.LayoutParams(-1, -1));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\b\a\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */