package com.flurry.android;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;

final class t extends Dialog {
  private bf a;
  
  t(bf parambf, Context paramContext, int paramInt) {
    super(paramContext, paramInt);
  }
  
  public final boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    boolean bool = false;
    if (ap.g(this.a.a) != null)
      bool = ap.g(this.a.a).dispatchTouchEvent(paramMotionEvent); 
    return bool;
  }
  
  public final boolean dispatchTrackballEvent(MotionEvent paramMotionEvent) {
    boolean bool = false;
    if (ap.g(this.a.a) != null)
      bool = ap.g(this.a.a).dispatchTrackballEvent(paramMotionEvent); 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */