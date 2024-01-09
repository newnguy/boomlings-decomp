package com.robtopx.boomlings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

class b implements DialogInterface.OnClickListener {
  private final SharedPreferences.Editor a;
  
  private final Context b;
  
  b(SharedPreferences.Editor paramEditor, Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    if (this.a != null) {
      this.a.putBoolean("dontshowagain", true);
      this.a.commit();
    } 
    a.d(this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\robtopx\boomlings\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */