package com.google.ads;

import android.content.DialogInterface;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import java.util.HashMap;

class ak implements DialogInterface.OnClickListener {
  private d a;
  
  public ak(d paramd) {
    this.a = paramd;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("u", "market://details?id=com.google.android.apps.plus");
    AdActivity.a(this.a, new e("intent", hashMap));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */