package com.google.ads;

import com.google.ads.internal.h;
import com.google.ads.util.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
  private static final Map a = Collections.unmodifiableMap(new al());
  
  private final String b;
  
  private final String c;
  
  private final List d;
  
  private final Integer e;
  
  private final Integer f;
  
  private final List g;
  
  private final List h;
  
  private final List i;
  
  private c(String paramString1, String paramString2, List paramList1, Integer paramInteger1, Integer paramInteger2, List paramList2, List paramList3, List paramList4) {
    a.a(paramString1);
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramList1;
    this.e = paramInteger1;
    this.f = paramInteger2;
    this.g = paramList2;
    this.h = paramList3;
    this.i = paramList4;
  }
  
  private static a a(JSONObject paramJSONObject) {
    String str1;
    String str2 = paramJSONObject.getString("id");
    String str3 = paramJSONObject.optString("allocation_id", null);
    JSONArray jSONArray = paramJSONObject.getJSONArray("adapters");
    ArrayList<String> arrayList = new ArrayList(jSONArray.length());
    for (byte b = 0; b < jSONArray.length(); b++)
      arrayList.add(jSONArray.getString(b)); 
    List list = a(paramJSONObject, "imp_urls");
    JSONObject jSONObject = paramJSONObject.optJSONObject("data");
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>(0);
    if (jSONObject != null) {
      HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>(jSONObject.length());
      Iterator<String> iterator = jSONObject.keys();
      while (true) {
        hashMap = hashMap1;
        if (iterator.hasNext()) {
          str1 = iterator.next();
          hashMap1.put(str1, jSONObject.getString(str1));
          continue;
        } 
        break;
      } 
    } 
    return new a(str3, str2, arrayList, list, (HashMap)str1);
  }
  
  public static c a(String paramString) {
    String str1;
    Integer integer = null;
    JSONObject jSONObject1 = new JSONObject(paramString);
    String str2 = jSONObject1.getString("qdata");
    if (jSONObject1.has("ad_type")) {
      str1 = jSONObject1.getString("ad_type");
    } else {
      str1 = null;
    } 
    JSONArray jSONArray = jSONObject1.getJSONArray("ad_networks");
    ArrayList<a> arrayList = new ArrayList(jSONArray.length());
    for (byte b = 0; b < jSONArray.length(); b++)
      arrayList.add(a(jSONArray.getJSONObject(b))); 
    JSONObject jSONObject2 = jSONObject1.optJSONObject("settings");
    if (jSONObject2 != null) {
      if (jSONObject2.has("refresh")) {
        Integer integer2 = Integer.valueOf(jSONObject2.getInt("refresh"));
      } else {
        jSONObject1 = null;
      } 
      if (jSONObject2.has("ad_network_timeout_millis"))
        integer = Integer.valueOf(jSONObject2.getInt("ad_network_timeout_millis")); 
      List list2 = a(jSONObject2, "imp_urls");
      List list3 = a(jSONObject2, "click_urls");
      List list4 = a(jSONObject2, "nofill_urls");
      Integer integer1 = integer;
      List list1 = list4;
      return new c(str2, str1, arrayList, (Integer)jSONObject1, integer1, list2, list3, list1);
    } 
    integer = null;
    jSONArray = null;
    List list = null;
    jSONObject2 = null;
    jSONObject1 = null;
    return new c(str2, str1, arrayList, (Integer)jSONObject1, (Integer)jSONObject2, (List)jSONArray, list, (List)integer);
  }
  
  private static List a(JSONObject paramJSONObject, String paramString) {
    JSONArray jSONArray = paramJSONObject.optJSONArray(paramString);
    if (jSONArray != null) {
      ArrayList<String> arrayList = new ArrayList(jSONArray.length());
      for (byte b = 0; b < jSONArray.length(); b++)
        arrayList.add(jSONArray.getString(b)); 
      return arrayList;
    } 
    return null;
  }
  
  public boolean a() {
    return (this.f != null);
  }
  
  public int b() {
    return this.f.intValue();
  }
  
  public String c() {
    return this.b;
  }
  
  public boolean d() {
    return (this.e != null);
  }
  
  public int e() {
    return this.e.intValue();
  }
  
  public List f() {
    return this.d;
  }
  
  public List g() {
    return this.g;
  }
  
  public List h() {
    return this.h;
  }
  
  public List i() {
    return this.i;
  }
  
  public h j() {
    if (this.c == null)
      return null; 
    if ("interstitial".equals(this.c))
      return h.a; 
    null = (AdSize)a.get(this.c);
    return (null != null) ? h.a(null) : null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */