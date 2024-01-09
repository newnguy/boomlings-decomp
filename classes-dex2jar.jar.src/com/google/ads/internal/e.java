package com.google.ads.internal;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

public class e {
  private String a;
  
  private HashMap b;
  
  public e(Bundle paramBundle) {
    this.a = paramBundle.getString("action");
    this.b = a(paramBundle.getSerializable("params"));
  }
  
  public e(String paramString) {
    this.a = paramString;
  }
  
  public e(String paramString, HashMap paramHashMap) {
    this(paramString);
    this.b = paramHashMap;
  }
  
  private HashMap a(Serializable paramSerializable) {
    return (paramSerializable instanceof HashMap) ? (HashMap)paramSerializable : null;
  }
  
  public Bundle a() {
    Bundle bundle = new Bundle();
    bundle.putString("action", this.a);
    bundle.putSerializable("params", this.b);
    return bundle;
  }
  
  public String b() {
    return this.a;
  }
  
  public HashMap c() {
    return this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */