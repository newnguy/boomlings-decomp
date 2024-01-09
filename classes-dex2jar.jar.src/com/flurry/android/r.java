package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.Map;

abstract class r extends RelativeLayout {
  bo a;
  
  bl b;
  
  AdUnit c;
  
  int d;
  
  private Context e;
  
  r(Context paramContext) {
    super(paramContext);
    a(paramContext, null, null);
  }
  
  r(Context paramContext, bo parambo, bl parambl) {
    super(paramContext);
    a(paramContext, parambo, parambl);
  }
  
  private void a(Context paramContext, bo parambo, bl parambl) {
    this.e = paramContext;
    this.a = parambo;
    this.b = parambl;
  }
  
  final void a(ViewGroup paramViewGroup, x paramx) {
    if (this.c != null && this.c.c().size() >= 1) {
      RelativeLayout.LayoutParams layoutParams;
      AdSpaceLayout adSpaceLayout = ((AdFrame)this.c.c().get(0)).d();
      LinearLayout.LayoutParams layoutParams1 = null;
      if (paramViewGroup instanceof LinearLayout) {
        layoutParams1 = new LinearLayout.LayoutParams(ac.a(this.e, adSpaceLayout.a().intValue()), ac.a(this.e, adSpaceLayout.b().intValue()));
      } else if (paramViewGroup instanceof AbsoluteLayout) {
        AbsoluteLayout.LayoutParams layoutParams2 = new AbsoluteLayout.LayoutParams(ac.a(this.e, adSpaceLayout.a().intValue()), ac.a(this.e, adSpaceLayout.b().intValue()), 0, 0);
      } else if (paramViewGroup instanceof FrameLayout) {
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ac.a(this.e, adSpaceLayout.a().intValue()), ac.a(this.e, adSpaceLayout.b().intValue()), 0);
      } else if (paramViewGroup instanceof RelativeLayout) {
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ac.a(this.e, adSpaceLayout.a().intValue()), ac.a(this.e, adSpaceLayout.b().intValue()));
        String[] arrayOfString = adSpaceLayout.e().toString().split("-");
        layoutParams = layoutParams2;
        if (arrayOfString.length == 2) {
          if (arrayOfString[0].equals("b")) {
            layoutParams2.addRule(12);
          } else if (arrayOfString[0].equals("t")) {
            layoutParams2.addRule(10);
          } else if (arrayOfString[0].equals("m")) {
            layoutParams2.addRule(15);
          } 
          if (arrayOfString[1].equals("c")) {
            layoutParams2.addRule(14);
            layoutParams = layoutParams2;
          } else if (arrayOfString[1].equals("l")) {
            layoutParams2.addRule(9);
            layoutParams = layoutParams2;
          } else {
            layoutParams = layoutParams2;
            if (arrayOfString[1].equals("r")) {
              layoutParams2.addRule(11);
              layoutParams = layoutParams2;
            } 
          } 
        } 
      } 
      if (layoutParams != null) {
        paramx.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        return;
      } 
      "Ad space layout and alignment from the server is being ignored for ViewGroup subclass " + paramViewGroup.getClass().getSimpleName();
    } 
  }
  
  final void a(String paramString, Map paramMap) {
    "AppSpotBannerView.onEvent " + paramString;
    this.a.a(this.b, paramString, true, paramMap);
    if (this.c != null)
      this.a.a(new bj(paramString, paramMap, this.e, this.c, this.b, this.d), this.a, 0); 
  }
  
  boolean a() {
    return !((AdFrame)this.c.c().get(this.d)).d().d().toString().equals("takeover");
  }
  
  public abstract void initLayout(Context paramContext);
  
  public void stop() {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */