package com.flurry.android;

import java.util.Iterator;
import java.util.Map;

final class v {
  private static String a = "FlurryAgent";
  
  private bo b;
  
  v(bo parambo) {
    this.b = parambo;
  }
  
  private static boolean a(String paramString1, String paramString2) {
    return paramString2.equals("%{" + paramString1 + "}");
  }
  
  final String a(bl parambl, AdUnit paramAdUnit, String paramString1, String paramString2) {
    if (a("fids", paramString2)) {
      null = Character.MIN_VALUE + ":" + this.b.e();
      bm.a(a, "Replacing param fids with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("sid", paramString2)) {
      null = String.valueOf(this.b.b());
      bm.a(a, "Replacing param sid with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("lid", paramString2)) {
      null = String.valueOf(parambl.a());
      bm.a(a, "Replacing param lid with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("guid", paramString2)) {
      null = parambl.b();
      bm.a(a, "Replacing param guid with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("ats", paramString2)) {
      null = String.valueOf(System.currentTimeMillis());
      bm.a(a, "Replacing param ats with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("apik", paramString2)) {
      null = this.b.c();
      bm.a(a, "Replacing param apik with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("hid", paramString2)) {
      null = paramAdUnit.a().toString();
      bm.a(a, "Replacing param hid with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("eso", paramString2)) {
      null = Long.toString(System.currentTimeMillis() - this.b.b());
      bm.a(a, "Replacing param eso with: " + null);
      return paramString1.replace(paramString2, ac.b(null));
    } 
    if (a("uc", paramString2)) {
      Iterator<Map.Entry> iterator = this.b.i().entrySet().iterator();
      String str2;
      for (str2 = ""; iterator.hasNext(); str2 = str2 + "c_" + ac.b((String)entry.getKey()) + "=" + ac.b((String)entry.getValue()) + "&")
        Map.Entry entry = iterator.next(); 
      bm.a(a, "Replacing param uc with: " + str2);
      paramString1 = paramString1.replace(paramString2, str2);
      String str1 = paramString1;
      if (str2.equals("")) {
        str1 = paramString1;
        if (paramString1.length() > 0)
          str1 = paramString1.substring(0, paramString1.length() - 1); 
      } 
      return str1;
    } 
    bm.a(a, "Unknown param: " + paramString2);
    return paramString1.replace(paramString2, "");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */