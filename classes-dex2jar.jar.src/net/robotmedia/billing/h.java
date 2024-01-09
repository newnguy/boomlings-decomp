package net.robotmedia.billing;

import android.os.Bundle;

public class h extends d {
  private String[] a;
  
  public h(String paramString, int paramInt, String[] paramArrayOfString) {
    super(paramString, paramInt);
    this.a = paramArrayOfString;
  }
  
  protected void a(Bundle paramBundle) {
    paramBundle.putStringArray("NOTIFY_IDS", this.a);
  }
  
  public String c() {
    return "GET_PURCHASE_INFORMATION";
  }
  
  public boolean d() {
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */