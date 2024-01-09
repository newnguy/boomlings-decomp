package com.chartboost.sdk;

import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import org.json.JSONObject;

class Chartboost$3 implements j.c {
  final Chartboost a;
  
  Chartboost$3(Chartboost paramChartboost) {}
  
  public void a(k paramk) {
    if (paramk.b.equals("get")) {
      Chartboost.a(this.a, a.c.b, paramk.e.optString("location", "Default"));
    } else if (paramk.b.equals("more")) {
      Chartboost.a(this.a, a.c.c, paramk.e.optString("location", "Default"));
    } 
    a a = Chartboost.a(this.a);
    if (a != null && a.a())
      a.a(true); 
  }
  
  public void a(JSONObject paramJSONObject, k paramk) {
    if (paramk.h != null)
      paramk.h.a(paramJSONObject); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Chartboost$3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */