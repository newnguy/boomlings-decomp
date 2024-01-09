package com.google.ads.internal;

import android.content.DialogInterface;
import android.content.Intent;

class x implements DialogInterface.OnClickListener {
  final String a;
  
  final j$a b;
  
  x(j$a paramj$a, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    j$a.a(this.b).startActivity(Intent.createChooser((new Intent("android.intent.action.SEND")).setType("text/plain").putExtra("android.intent.extra.TEXT", this.a), "Share via"));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */