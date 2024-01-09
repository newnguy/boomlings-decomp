package com.flurry.android;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

final class bf extends WebChromeClient {
  final ap a;
  
  private bf(ap paramap, byte paramByte) {}
  
  public final void onHideCustomView() {
    if (ap.g(this.a) != null && ap.h(this.a) != null) {
      if (ap.l(this.a) != null)
        ap.l(this.a).show(); 
      ((FrameLayout)ap.g(this.a).getWindow().getDecorView()).removeView((View)ap.j(this.a));
      ap.j(this.a).removeView(ap.h(this.a));
      if (ap.k(this.a) != null && ap.k(this.a).isShowing()) {
        ap.k(this.a).hide();
        ap.k(this.a).setOnDismissListener(null);
        ap.k(this.a).dismiss();
      } 
      ap.a(this.a, (Dialog)null);
      ap.m(this.a).onCustomViewHidden();
      ap.g(this.a).setRequestedOrientation(ap.n(this.a));
      ap.a(this.a, (WebChromeClient.CustomViewCallback)null);
      ap.a(this.a, (FrameLayout)null);
      ap.a(this.a, (View)null);
    } 
  }
  
  public final void onShowCustomView(View paramView, int paramInt, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
    if (ap.g(this.a) != null) {
      if (ap.h(this.a) != null && ap.i(this.a) != null) {
        ap.i(this.a).onHideCustomView();
        return;
      } 
      ap.a(this.a, paramView);
      ap.a(this.a, paramInt);
      ap.a(this.a, paramCustomViewCallback);
      ap.a(this.a, new FrameLayout(ap.f(this.a)));
      ap.j(this.a).setBackgroundColor(-16777216);
      ap.j(this.a).addView(ap.h(this.a), (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1, 17));
      ((FrameLayout)ap.g(this.a).getWindow().getDecorView()).addView((View)ap.j(this.a), -1, -1);
      if (ap.k(this.a) == null) {
        ap.a(this.a, new t(this, ap.f(this.a), 16973841));
        ap.k(this.a).getWindow().setType(1000);
        ap.k(this.a).setOnShowListener(new s(this));
        ap.k(this.a).setOnDismissListener(new q(this));
        ap.k(this.a).setCancelable(true);
        ap.k(this.a).show();
      } 
      ap.g(this.a).setRequestedOrientation(paramInt);
    } 
  }
  
  public final void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
    if (ap.g(this.a) != null)
      onShowCustomView(paramView, ap.g(this.a).getRequestedOrientation(), paramCustomViewCallback); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */