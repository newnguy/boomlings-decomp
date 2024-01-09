package com.flurry.android;

import android.content.DialogInterface;

final class q implements DialogInterface.OnDismissListener {
  private bf a;
  
  q(bf parambf) {}
  
  public final void onDismiss(DialogInterface paramDialogInterface) {
    if (ap.h(this.a.a) != null && ap.i(this.a.a) != null)
      ap.i(this.a.a).onHideCustomView(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */