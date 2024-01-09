package com.robtopx.boomlings;

import android.content.DialogInterface;
import android.content.SharedPreferences;

class d implements DialogInterface.OnClickListener {
  private final SharedPreferences.Editor a;
  
  d(SharedPreferences.Editor paramEditor) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    if (this.a != null) {
      this.a.putLong("launch_count", 2L);
      this.a.commit();
    } 
    paramDialogInterface.cancel();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\robtopx\boomlings\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */