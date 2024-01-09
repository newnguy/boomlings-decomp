package com.chartboost.sdk.Libraries;

import android.graphics.drawable.BitmapDrawable;
import java.lang.ref.WeakReference;

class e$c extends BitmapDrawable {
  private final WeakReference a;
  
  public e$c(e$a parame$a) {
    this.a = new WeakReference<e$a>(parame$a);
  }
  
  public e$a a() {
    return this.a.get();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\e$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */