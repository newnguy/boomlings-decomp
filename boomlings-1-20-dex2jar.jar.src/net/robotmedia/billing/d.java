package net.robotmedia.billing;

import android.os.Bundle;
import android.util.Log;
import com.a.a.a.a;

public abstract class d {
  private String a;
  
  private int b;
  
  private boolean c;
  
  private long d;
  
  public d(String paramString, int paramInt) {
    this.a = paramString;
    this.b = paramInt;
  }
  
  protected int a() {
    return 1;
  }
  
  public long a(a parama) {
    long l = -1L;
    Bundle bundle = f();
    a(bundle);
    try {
      Bundle bundle1 = parama.a(bundle);
      if (c(bundle1)) {
        b(bundle1);
        l = bundle1.getLong("REQUEST_ID", -1L);
      } 
    } catch (NullPointerException nullPointerException) {
      Log.e(getClass().getSimpleName(), "Known IAB bug. See: http://code.google.com/p/marketbilling/issues/detail?id=25", nullPointerException);
    } 
    return l;
  }
  
  public void a(long paramLong) {
    this.d = paramLong;
  }
  
  protected void a(Bundle paramBundle) {}
  
  public void a(k paramk) {}
  
  public long b() {
    return this.d;
  }
  
  protected void b(Bundle paramBundle) {}
  
  public abstract String c();
  
  protected boolean c(Bundle paramBundle) {
    int i = paramBundle.getInt("RESPONSE_CODE");
    this.c = k.a(i);
    if (!this.c)
      Log.w(getClass().getSimpleName(), "Error with response code " + k.b(i)); 
    return this.c;
  }
  
  public boolean d() {
    return false;
  }
  
  public boolean e() {
    return this.c;
  }
  
  protected Bundle f() {
    Bundle bundle = new Bundle();
    bundle.putString("BILLING_REQUEST", c());
    bundle.putInt("API_VERSION", a());
    bundle.putString("PACKAGE_NAME", this.a);
    if (d())
      bundle.putLong("NONCE", this.d); 
    return bundle;
  }
  
  public int g() {
    return this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */