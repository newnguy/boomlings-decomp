package net.robotmedia.billing;

import android.os.Bundle;

public class g extends d {
  private String[] a;
  
  public g(String paramString, int paramInt, String[] paramArrayOfString) {
    super(paramString, paramInt);
    this.a = paramArrayOfString;
  }
  
  protected void a(Bundle paramBundle) {
    paramBundle.putStringArray("NOTIFY_IDS", this.a);
  }
  
  public String c() {
    return "CONFIRM_NOTIFICATIONS";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */