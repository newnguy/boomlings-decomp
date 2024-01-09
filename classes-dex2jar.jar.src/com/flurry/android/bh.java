package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Collections;

final class bh extends aj {
  private String b = getClass().getSimpleName();
  
  private WeakReference c;
  
  bh(r paramr, AdUnit paramAdUnit) {
    super(paramAdUnit);
    this.c = new WeakReference<r>(paramr);
  }
  
  final void a(Context paramContext, ViewGroup paramViewGroup) {
    r r = this.c.get();
    if (r != null) {
      bo bo = FlurryAgent.b();
      x x = bo.d.a(bo, paramContext, paramViewGroup, this.a);
      x.removeAllViews();
      r.initLayout(paramContext);
      if (paramViewGroup != x.getParent() && x.getParent() == null) {
        int i = paramViewGroup.getChildCount();
        if (r.c != null && r.c.c().size() >= 1) {
          String str = ((AdFrame)r.c.c().get(0)).d().e().toString();
          if (str.length() == 3)
            if (str.charAt(0) == 't') {
              i = 0;
            } else if (str.charAt(0) == 'b') {
              i = paramViewGroup.getChildCount();
            }  
        } 
        paramViewGroup.addView((View)x, i);
      } 
      if (r.getParent() == null)
        x.addView((View)r); 
      r.a(paramViewGroup, x);
      r.a("rendered", Collections.emptyMap());
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\bh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */