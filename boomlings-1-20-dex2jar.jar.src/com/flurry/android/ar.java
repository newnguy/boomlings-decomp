package com.flurry.android;

import android.content.DialogInterface;
import java.util.HashMap;

final class ar implements DialogInterface.OnClickListener {
  private bj a;
  
  private int b;
  
  private ap c;
  
  ar(ap paramap, bj parambj, int paramInt) {}
  
  public final void onClick(DialogInterface paramDialogInterface, int paramInt) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("sourceEvent", this.a.a);
    this.c.a("userCanceled", hashMap, this.c.c, this.c.b, this.c.d, this.b + 1);
    paramDialogInterface.dismiss();
    if (ap.o(this.c) != null && this.c.b() == 3)
      ap.o(this.c).start(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */