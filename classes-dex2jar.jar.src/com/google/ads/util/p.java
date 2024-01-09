package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.widget.EditText;

final class p implements DialogInterface.OnClickListener {
  final JsPromptResult a;
  
  final EditText b;
  
  p(JsPromptResult paramJsPromptResult, EditText paramEditText) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    this.a.confirm(this.b.getText().toString());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */