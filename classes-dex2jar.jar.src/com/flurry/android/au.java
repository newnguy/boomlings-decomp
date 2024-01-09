package com.flurry.android;

import android.content.DialogInterface;

final class au implements DialogInterface.OnDismissListener {
  private ap a;
  
  au(ap paramap) {}
  
  public final void onDismiss(DialogInterface paramDialogInterface) {
    if (ap.b(this.a) != null)
      ap.b(this.a).loadUrl("javascript:if(window.mraid){window.mraid.close();};"); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */