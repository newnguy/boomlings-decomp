package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class k implements DialogInterface.OnCancelListener {
  final JsResult a;
  
  k(JsResult paramJsResult) {}
  
  public void onCancel(DialogInterface paramDialogInterface) {
    this.a.cancel();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */