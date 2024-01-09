package net.robotmedia.billing.a;

import org.json.JSONObject;

public class c {
  public String a;
  
  public String b;
  
  public String c;
  
  public String d;
  
  public String e;
  
  public d f;
  
  public long g;
  
  public c() {}
  
  public c(String paramString1, String paramString2, String paramString3, d paramd, String paramString4, long paramLong, String paramString5) {
    this.c = paramString1;
    this.e = paramString2;
    this.d = paramString3;
    this.f = paramd;
    this.b = paramString4;
    this.g = paramLong;
    this.a = paramString5;
  }
  
  public static c a(JSONObject paramJSONObject) {
    c c1 = new c();
    c1.f = d.a(paramJSONObject.getInt("purchaseState"));
    c1.e = paramJSONObject.getString("productId");
    c1.d = paramJSONObject.getString("packageName");
    c1.g = paramJSONObject.getLong("purchaseTime");
    c1.c = paramJSONObject.optString("orderId", null);
    c1.b = paramJSONObject.optString("notificationId", null);
    c1.a = paramJSONObject.optString("developerPayload", null);
    return c1;
  }
  
  public c a() {
    return new c(this.c, this.e, this.d, this.f, this.b, this.g, this.a);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (paramObject == null)
        return false; 
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      if (this.a == null) {
        if (((c)paramObject).a != null)
          return false; 
      } else if (!this.a.equals(((c)paramObject).a)) {
        return false;
      } 
      if (this.b == null) {
        if (((c)paramObject).b != null)
          return false; 
      } else if (!this.b.equals(((c)paramObject).b)) {
        return false;
      } 
      if (this.c == null) {
        if (((c)paramObject).c != null)
          return false; 
      } else if (!this.c.equals(((c)paramObject).c)) {
        return false;
      } 
      if (this.d == null) {
        if (((c)paramObject).d != null)
          return false; 
      } else if (!this.d.equals(((c)paramObject).d)) {
        return false;
      } 
      if (this.e == null) {
        if (((c)paramObject).e != null)
          return false; 
      } else if (!this.e.equals(((c)paramObject).e)) {
        return false;
      } 
      if (this.f != ((c)paramObject).f)
        return false; 
      if (this.g != ((c)paramObject).g)
        bool = false; 
    } 
    return bool;
  }
  
  public String toString() {
    return String.valueOf(this.c);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */