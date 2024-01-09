package com.b.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class l extends Handler {
  final k a;
  
  l(k paramk) {}
  
  public void handleMessage(Message paramMessage) {
    String str = paramMessage.getData().getString("access_token");
    long l1 = paramMessage.getData().getLong("expires_in") * 1000L;
    Bundle bundle = (Bundle)paramMessage.getData().clone();
    bundle.putLong("expires_in", l1);
    if (str != null) {
      k.a(this.a).a(str);
      k.a(this.a).a(l1);
      if (this.a.b != null)
        this.a.b.a(bundle); 
    } else if (this.a.b != null) {
      str = paramMessage.getData().getString("error");
      if (paramMessage.getData().containsKey("error_code")) {
        int i = paramMessage.getData().getInt("error_code");
        this.a.b.a(new m(str, null, i));
      } else {
        String str1;
        j j = this.a.b;
        if (str != null) {
          str1 = str;
        } else {
          str1 = "Unknown service error";
        } 
        j.a(new Error(str1));
      } 
    } 
    this.a.c.unbindService(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */