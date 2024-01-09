package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class l implements DialogInterface.OnClickListener {
  final JsResult a;
  
  l(JsResult paramJsResult) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    this.a.cancel();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */