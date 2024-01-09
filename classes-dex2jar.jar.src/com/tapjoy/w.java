package com.tapjoy;

import android.content.Context;

public class w {
  private static am a = null;
  
  private Context b;
  
  public w(Context paramContext) {
    this.b = paramContext;
    a = new am();
  }
  
  public void a() {
    a(2, null);
  }
  
  public void a(int paramInt, String paramString) {
    aj.a("Event", "sendEvent type: " + paramInt);
    String str1 = h.c();
    str1 = str1 + "&publisher_user_id=" + h.e();
    String str2 = str1 + "&event_type_id=" + paramInt;
    str1 = str2;
    if (paramString != null) {
      str1 = str2;
      if (paramString.length() > 0)
        str1 = str2 + "&" + paramString; 
    } 
    (new Thread(new x(this, str1))).start();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */