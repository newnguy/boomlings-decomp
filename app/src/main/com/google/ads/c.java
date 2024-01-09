package com.google.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
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

    private c(String str, String str2, List list, Integer num, Integer num2, List list2, List list3, List list4) {
        com.google.ads.util.a.a(str);
        this.b = str;
        this.c = str2;
        this.d = list;
        this.e = num;
        this.f = num2;
        this.g = list2;
        this.h = list3;
        this.i = list4;
    }

    private static a a(JSONObject jSONObject) {
        HashMap hashMap;
        String string = jSONObject.getString("id");
        String optString = jSONObject.optString("allocation_id", null);
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        List a2 = a(jSONObject, "imp_urls");
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        HashMap hashMap2 = new HashMap(0);
        if (optJSONObject != null) {
            hashMap = new HashMap(optJSONObject.length());
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, optJSONObject.getString(next));
            }
        } else {
            hashMap = hashMap2;
        }
        return new a(optString, string, arrayList, a2, hashMap);
    }

    public static c a(String str) {
        List list;
        List list2;
        List list3;
        Integer num;
        Integer num2;
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("qdata");
        String string2 = jSONObject.has("ad_type") ? jSONObject.getString("ad_type") : null;
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.getJSONObject(i)));
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            num2 = optJSONObject.has("refresh") ? Integer.valueOf(optJSONObject.getInt("refresh")) : null;
            Integer valueOf = optJSONObject.has("ad_network_timeout_millis") ? Integer.valueOf(optJSONObject.getInt("ad_network_timeout_millis")) : null;
            list2 = a(optJSONObject, "imp_urls");
            list3 = a(optJSONObject, "click_urls");
            list = a(optJSONObject, "nofill_urls");
            num = valueOf;
        } else {
            list = null;
            list2 = null;
            list3 = null;
            num = null;
            num2 = null;
        }
        return new c(string, string2, arrayList, num2, num, list2, list3, list);
    }

    private static List a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.getString(i));
            }
            return arrayList;
        }
        return null;
    }

    public boolean a() {
        return this.f != null;
    }

    public int b() {
        return this.f.intValue();
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.e != null;
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

    public com.google.ads.internal.h j() {
        if (this.c == null) {
            return null;
        }
        if ("interstitial".equals(this.c)) {
            return com.google.ads.internal.h.a;
        }
        AdSize adSize = (AdSize) a.get(this.c);
        if (adSize != null) {
            return com.google.ads.internal.h.a(adSize);
        }
        return null;
    }
}
