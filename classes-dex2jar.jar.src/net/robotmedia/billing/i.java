package net.robotmedia.billing;

import android.app.PendingIntent;
import android.os.Bundle;

public class i extends d {
  private String a;
  
  private String b;
  
  public i(String paramString1, int paramInt, String paramString2, String paramString3) {
    super(paramString1, paramInt);
    this.a = paramString2;
    this.b = paramString3;
  }
  
  protected void a(Bundle paramBundle) {
    paramBundle.putString("ITEM_ID", this.a);
    if (this.b != null)
      paramBundle.putString("DEVELOPER_PAYLOAD", this.b); 
  }
  
  public void a(k paramk) {
    super.a(paramk);
    a.a(this.a, paramk);
  }
  
  protected void b(Bundle paramBundle) {
    PendingIntent pendingIntent = (PendingIntent)paramBundle.getParcelable("PURCHASE_INTENT");
    a.a(this.a, pendingIntent);
  }
  
  public String c() {
    return "REQUEST_PURCHASE";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */