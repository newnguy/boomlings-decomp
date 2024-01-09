package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

final class m implements DialogInterface.OnClickListener {
  final JsResult a;
  
  m(JsResult paramJsResult) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    this.a.confirm();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */