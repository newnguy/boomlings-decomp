package com.chartboost.sdk.impl;

import android.content.Context;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.b;
import org.json.JSONObject;

public class b extends b {
  public a.a h = null;
  
  public a.a i = null;
  
  public a.a j = null;
  
  public a.a k = null;
  
  public a.a l = null;
  
  public b(a parama) {
    super(parama);
  }
  
  protected b.b a(Context paramContext) {
    return new b$a(this, paramContext, null);
  }
  
  public void a(JSONObject paramJSONObject) {
    super.a(paramJSONObject);
    b$1 b$1 = new b$1(this);
    b$2 b$2 = new b$2(this);
    b$3 b$3 = new b$3(this);
    b$4 b$4 = new b$4(this);
    b$5 b$5 = new b$5(this);
    a("ad-landscape", b$1, true);
    a("ad-portrait", b$2, true);
    a("frame-landscape", b$3);
    a("frame-portrait", b$4);
    a("close", b$5);
  }
  
  public void c() {
    super.c();
    if (this.i != null) {
      this.i.a();
      this.i = null;
    } 
    if (this.h != null) {
      this.h.a();
      this.h = null;
    } 
    this.k = null;
    this.j = null;
    this.l = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */