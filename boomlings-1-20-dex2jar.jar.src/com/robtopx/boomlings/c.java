package com.robtopx.boomlings;

import android.content.DialogInterface;
import android.content.SharedPreferences;

class c implements DialogInterface.OnClickListener {
  private final SharedPreferences.Editor a;
  
  c(SharedPreferences.Editor paramEditor) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    if (this.a != null) {
      this.a.putBoolean("dontshowagain", true);
      this.a.commit();
    } 
    paramDialogInterface.cancel();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\robtopx\boomlings\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */