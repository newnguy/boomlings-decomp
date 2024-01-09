package com.google.ads;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.google.ads.doubleclick.DfpExtras;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import com.google.ads.util.AdUtil;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class AdRequest {
    public static final String a;
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyyMMdd");
    private static Method c;
    private static Method d;
    private Gender e = null;
    private Date f = null;
    private Set g = null;
    private Map h = null;
    private final Map i = new HashMap();
    private Location j = null;
    private boolean k = false;
    private boolean l = false;
    private Set m = null;

    /* loaded from: classes.dex */
    public enum ErrorCode {
        INVALID_REQUEST("Invalid Ad request."),
        NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory."),
        NETWORK_ERROR("A network error occurred."),
        INTERNAL_ERROR("There was an internal error.");
        
        private final String e;

        ErrorCode(String str) {
            this.e = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.e;
        }
    }

    /* loaded from: classes.dex */
    public enum Gender {
        UNKNOWN,
        MALE,
        FEMALE
    }

    static {
        Method[] methods;
        c = null;
        d = null;
        try {
            for (Method method : Class.forName("com.google.analytics.tracking.android.AdMobInfo").getMethods()) {
                if (method.getName().equals("getInstance") && method.getParameterTypes().length == 0) {
                    c = method;
                } else if (method.getName().equals("getJoinIds") && method.getParameterTypes().length == 0) {
                    d = method;
                }
            }
            if (c == null || d == null) {
                c = null;
                d = null;
                com.google.ads.util.b.e("No Google Analytics: Library Incompatible.");
            }
        } catch (ClassNotFoundException e) {
            com.google.ads.util.b.a("No Google Analytics: Library Not Found.");
        } catch (Throwable th) {
            com.google.ads.util.b.a("No Google Analytics: Error Loading Library");
        }
        a = AdUtil.b("emulator");
    }

    public Gender a() {
        return this.e;
    }

    public AdRequest a(Location location) {
        this.j = location;
        return this;
    }

    public AdRequest a(Gender gender) {
        this.e = gender;
        return this;
    }

    public AdRequest a(NetworkExtras networkExtras) {
        if (networkExtras != null) {
            this.i.put(networkExtras.getClass(), networkExtras);
        }
        return this;
    }

    public AdRequest a(String str) {
        if (this.m == null) {
            this.m = new HashSet();
        }
        this.m.add(str);
        return this;
    }

    public AdRequest a(Date date) {
        if (date == null) {
            this.f = null;
        } else {
            this.f = new Date(date.getTime());
        }
        return this;
    }

    public AdRequest a(Set set) {
        this.g = set;
        return this;
    }

    public Object a(Class cls) {
        return this.i.get(cls);
    }

    public Map a(Context context) {
        HashMap hashMap = new HashMap();
        if (this.g != null) {
            hashMap.put("kw", this.g);
        }
        if (this.e != null) {
            hashMap.put("cust_gender", Integer.valueOf(this.e.ordinal()));
        }
        if (this.f != null) {
            hashMap.put("cust_age", b.format(this.f));
        }
        if (this.j != null) {
            hashMap.put("uule", AdUtil.a(this.j));
        }
        if (this.k) {
            hashMap.put("testing", 1);
        }
        if (b(context)) {
            hashMap.put("adtest", "on");
        } else if (!this.l) {
            com.google.ads.util.b.c("To get test ads on this device, call adRequest.addTestDevice(" + (AdUtil.c() ? "AdRequest.TEST_EMULATOR" : "\"" + AdUtil.a(context) + "\"") + ");");
            this.l = true;
        }
        AdMobAdapterExtras adMobAdapterExtras = (AdMobAdapterExtras) a(AdMobAdapterExtras.class);
        if (adMobAdapterExtras == null || !adMobAdapterExtras.d()) {
            hashMap.put("cipa", Integer.valueOf(ah.a(context) ? 1 : 0));
        } else {
            hashMap.put("pto", 1);
        }
        DfpExtras dfpExtras = (DfpExtras) a(DfpExtras.class);
        if (dfpExtras != null && dfpExtras.f() != null && !dfpExtras.f().isEmpty()) {
            hashMap.put("extras", dfpExtras.f());
        } else if (adMobAdapterExtras != null && adMobAdapterExtras.f() != null && !adMobAdapterExtras.f().isEmpty()) {
            hashMap.put("extras", adMobAdapterExtras.f());
        }
        if (dfpExtras != null) {
            String a2 = dfpExtras.a();
            if (!TextUtils.isEmpty(a2)) {
                hashMap.put("ppid", a2);
            }
        }
        if (this.h != null) {
            hashMap.put("mediation_extras", this.h);
        }
        try {
            if (c != null) {
                Map map = (Map) d.invoke(c.invoke(null, new Object[0]), new Object[0]);
                if (map != null && map.size() > 0) {
                    hashMap.put("analytics_join_id", map);
                }
            }
        } catch (Throwable th) {
            com.google.ads.util.b.c("Internal Analytics Error:", th);
        }
        return hashMap;
    }

    public AdRequest b(Set set) {
        this.m = set;
        return this;
    }

    public Date b() {
        return this.f;
    }

    public boolean b(Context context) {
        String a2;
        return (this.m == null || (a2 = AdUtil.a(context)) == null || !this.m.contains(a2)) ? false : true;
    }

    public Set c() {
        if (this.g == null) {
            return null;
        }
        return Collections.unmodifiableSet(this.g);
    }

    public Location d() {
        return this.j;
    }
}
