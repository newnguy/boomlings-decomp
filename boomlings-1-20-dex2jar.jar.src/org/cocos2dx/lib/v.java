package org.cocos2dx.lib;

import android.content.DialogInterface;

class v implements DialogInterface.OnClickListener {
  final u a;
  
  v(u paramu) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    JniToCpp.openSupportMail();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */