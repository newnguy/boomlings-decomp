package net.robotmedia.billing;

import android.os.Bundle;

public class f extends d {
  public f(String paramString, int paramInt) {
    super(paramString, paramInt);
  }
  
  protected int a() {
    return 2;
  }
  
  protected void a(Bundle paramBundle) {
    paramBundle.putString("ITEM_TYPE", "subs");
  }
  
  protected void b(Bundle paramBundle) {
    a.b(e());
  }
  
  public String c() {
    return "CHECK_BILLING_SUPPORTED";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */