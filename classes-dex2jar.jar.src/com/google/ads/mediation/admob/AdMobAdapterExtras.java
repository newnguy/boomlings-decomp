package com.google.ads.mediation.admob;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;
import java.util.Map;

public class AdMobAdapterExtras implements NetworkExtras {
  private boolean a = false;
  
  private boolean b = false;
  
  private Map c;
  
  public AdMobAdapterExtras() {
    c();
  }
  
  public AdMobAdapterExtras(AdMobAdapterExtras paramAdMobAdapterExtras) {
    this();
    if (paramAdMobAdapterExtras != null) {
      this.a = paramAdMobAdapterExtras.a;
      this.b = paramAdMobAdapterExtras.b;
      this.c.putAll(paramAdMobAdapterExtras.c);
    } 
  }
  
  public AdMobAdapterExtras b(String paramString, Object paramObject) {
    this.c.put(paramString, paramObject);
    return this;
  }
  
  public AdMobAdapterExtras c() {
    this.c = new HashMap<Object, Object>();
    return this;
  }
  
  public boolean d() {
    return this.a;
  }
  
  public boolean e() {
    return this.b;
  }
  
  public Map f() {
    return this.c;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\admob\AdMobAdapterExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */