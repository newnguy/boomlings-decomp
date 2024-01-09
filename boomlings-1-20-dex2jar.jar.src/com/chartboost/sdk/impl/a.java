package com.chartboost.sdk.impl;

import android.view.View;
import com.chartboost.sdk.b;
import java.util.Date;
import org.json.JSONObject;

public class a {
  public JSONObject a;
  
  public Date b;
  
  public a$b c;
  
  public a$c d;
  
  public String e;
  
  public b f;
  
  public a$a g;
  
  public s h;
  
  public boolean i;
  
  public boolean j;
  
  public boolean k;
  
  public a(JSONObject paramJSONObject, a$c parama$c, a$a parama$a, a$b parama$b, String paramString) {
    JSONObject jSONObject = paramJSONObject;
    if (paramJSONObject == null)
      jSONObject = new JSONObject(); 
    this.c = parama$b;
    this.e = paramString;
    this.a = jSONObject;
    this.b = new Date();
    this.g = parama$a;
    this.d = parama$c;
    this.i = false;
    this.j = false;
    this.k = false;
    boolean bool = jSONObject.optString("type", "").equals("native");
    if (bool && this.d == a$c.b) {
      this.f = new b(this);
    } else if (bool && this.d == a$c.c) {
      this.f = new h(this);
    } else {
      this.f = new v(this);
    } 
    this.f.c = new a$1(this, this);
    this.f.a = new a$2(this, this);
    this.f.b = new a$3(this, this);
    this.f.d = new a$4(this, this);
    this.f.a(jSONObject);
  }
  
  public boolean a() {
    boolean bool = true;
    this.i = true;
    this.j = true;
    this.k = true;
    this.f.a();
    if (this.f.d() == null) {
      this.i = false;
      this.j = false;
      this.k = false;
      bool = false;
    } 
    return bool;
  }
  
  public void b() {
    if (this.h != null) {
      this.h.a();
      try {
        if (this.f.d().getParent() != null)
          this.h.removeView((View)this.f.d()); 
      } catch (Exception exception) {}
    } 
    if (this.f != null)
      this.f.c(); 
    this.a = null;
    this.b = null;
    this.g = null;
    this.f = null;
    this.h = null;
  }
  
  public void c() {
    if (this.h != null) {
      this.h.a();
      try {
        if (this.f.d().getParent() != null)
          this.h.removeView((View)this.f.d()); 
      } catch (Exception exception) {}
    } 
    if (this.f != null)
      this.f.e(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */