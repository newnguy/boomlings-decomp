package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

final class o implements DialogInterface.OnClickListener {
  final JsPromptResult a;
  
  o(JsPromptResult paramJsPromptResult) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    this.a.cancel();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */