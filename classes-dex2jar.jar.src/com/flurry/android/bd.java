package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

final class bd extends RelativeLayout implements View.OnClickListener {
  private final String a = getClass().getSimpleName();
  
  private WebView b;
  
  private ImageView c;
  
  private ImageView d;
  
  private ImageView e;
  
  public bd(Context paramContext, String paramString) {
    super(paramContext);
    this.b = new WebView(paramContext);
    this.b.getSettings().setJavaScriptEnabled(true);
    this.b.getSettings().setUseWideViewPort(true);
    if (VersionUtil.SDK_INT >= 7)
      this.b.getSettings().setLoadWithOverviewMode(true); 
    this.b.getSettings().setBuiltInZoomControls(true);
    if (VersionUtil.SDK_INT >= 11)
      this.b.getSettings().setDisplayZoomControls(false); 
    this.b.setWebViewClient(new b(this));
    this.b.loadUrl(paramString);
    this.c = new ImageView(paramContext);
    this.c.setId(0);
    this.c.setImageDrawable(getResources().getDrawable(17301560));
    this.c.setOnClickListener(this);
    this.d = new ImageView(paramContext);
    this.d.setId(1);
    this.d.setImageDrawable(getResources().getDrawable(17301580));
    this.d.setOnClickListener(this);
    this.e = new ImageView(paramContext);
    this.e.setId(2);
    this.e.setImageDrawable(getResources().getDrawable(17301565));
    this.e.setOnClickListener(this);
    setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    addView((View)this.b);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(14);
    addView((View)this.c, (ViewGroup.LayoutParams)layoutParams);
    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(9);
    addView((View)this.d, (ViewGroup.LayoutParams)layoutParams);
    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(11);
    addView((View)this.e, (ViewGroup.LayoutParams)layoutParams);
  }
  
  public final void onClick(View paramView) {
    switch (paramView.getId()) {
      default:
        return;
      case 0:
        try {
          ((Activity)getContext()).finish();
        } catch (ClassCastException classCastException) {
          classCastException.toString();
        } 
      case 2:
        this.b.goForward();
      case 1:
        break;
    } 
    this.b.goBack();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\bd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */