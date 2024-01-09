package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class k {
    public String a;
    public String b;
    public Map d;
    public JSONObject e;
    public Map f;
    public List g;
    public a h;
    public boolean i;
    public JSONObject j = null;
    public String c = "GET";

    /* loaded from: classes.dex */
    public interface a {
        void a(JSONObject jSONObject);
    }

    public k(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public static k a(JSONObject jSONObject) {
        try {
            k kVar = new k(jSONObject.getString("controller"), jSONObject.getString("action"));
            kVar.g = com.chartboost.sdk.Libraries.d.a(jSONObject.optJSONArray("params"));
            kVar.f = com.chartboost.sdk.Libraries.d.a(jSONObject.optJSONObject("query"));
            kVar.e = jSONObject.optJSONObject("body");
            kVar.i = jSONObject.getBoolean("ensureDelivery");
            kVar.d = com.chartboost.sdk.Libraries.d.a(jSONObject.optJSONObject("headers"));
            return kVar;
        } catch (Exception e) {
            Log.w("Chartboost", "Unable to deserialize failed request");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.content.Context r6) {
        /*
            r5 = this;
            r2 = 0
            boolean r1 = r6 instanceof android.app.Activity     // Catch: java.lang.Exception -> L60
            if (r1 == 0) goto L71
            r0 = r6
            android.app.Activity r0 = (android.app.Activity) r0     // Catch: java.lang.Exception -> L60
            r1 = r0
            android.graphics.Rect r4 = new android.graphics.Rect     // Catch: java.lang.Exception -> L60
            r4.<init>()     // Catch: java.lang.Exception -> L60
            android.view.Window r1 = r1.getWindow()     // Catch: java.lang.Exception -> L60
            android.view.View r1 = r1.getDecorView()     // Catch: java.lang.Exception -> L60
            r1.getWindowVisibleDisplayFrame(r4)     // Catch: java.lang.Exception -> L60
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L60
            r3 = 9
            if (r1 >= r3) goto L22
            r1 = 0
            r4.top = r1     // Catch: java.lang.Exception -> L60
        L22:
            int r3 = r4.width()     // Catch: java.lang.Exception -> L60
            int r1 = r4.height()     // Catch: java.lang.Exception -> L6e
            r2 = r1
        L2b:
            java.lang.String r1 = "window"
            java.lang.Object r1 = r6.getSystemService(r1)
            android.view.WindowManager r1 = (android.view.WindowManager) r1
            android.view.Display r1 = r1.getDefaultDisplay()
            if (r3 <= 0) goto L64
        L39:
            if (r2 <= 0) goto L69
        L3b:
            java.lang.String r1 = "w"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r5.a(r1, r3)
            java.lang.String r1 = "h"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            r5.a(r1, r2)
            return
        L60:
            r1 = move-exception
            r1 = r2
        L62:
            r3 = r1
            goto L2b
        L64:
            int r3 = r1.getWidth()
            goto L39
        L69:
            int r2 = r1.getHeight()
            goto L3b
        L6e:
            r1 = move-exception
            r1 = r3
            goto L62
        L71:
            r3 = r2
            goto L2b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.k.b(android.content.Context):void");
    }

    public String a() {
        return "/" + this.a + "/" + this.b + com.chartboost.sdk.Libraries.d.a(this.f);
    }

    public void a(Context context) {
        a("app", Chartboost.sharedChartboost().getAppID());
        if (Build.PRODUCT.equals("sdk")) {
            a("model", "Android Simulator");
            a("identity", com.chartboost.sdk.Libraries.d.b());
        } else {
            a("model", Build.MODEL);
            a("identity", com.chartboost.sdk.Libraries.d.b());
        }
        a("device_type", String.valueOf(Build.MANUFACTURER) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Build.MODEL);
        a("os", "Android " + Build.VERSION.RELEASE);
        a("country", Locale.getDefault().getCountry());
        a("language", Locale.getDefault().getLanguage());
        a("sdk", "3.1.5");
        a("timestamp", new StringBuilder(String.valueOf(new Date().getTime())).toString());
        b(context);
        a("scale", new StringBuilder().append(context.getResources().getDisplayMetrics().density).toString());
        try {
            a("bundle", context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName);
        } catch (Exception e) {
        }
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new JSONObject();
            this.c = "POST";
        }
        try {
            this.e.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String b() {
        return this.e.toString();
    }

    public void b(String str, String str2) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.put(str, str2);
    }

    public Map c() {
        return this.d;
    }

    public void c(String str, String str2) {
        String b = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a((String.valueOf(this.c) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a() + "\n" + str2 + "\n" + b()).getBytes()));
        b("X-Chartboost-App", str);
        b("X-Chartboost-Signature", b);
    }

    public JSONObject d() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("controller", this.a);
            jSONObject.put("action", this.b);
            jSONObject.put("params", com.chartboost.sdk.Libraries.d.a(this.g));
            jSONObject.put("query", com.chartboost.sdk.Libraries.d.b(this.f));
            jSONObject.put("body", this.e);
            jSONObject.put("ensureDelivery", this.i);
            jSONObject.put("headers", com.chartboost.sdk.Libraries.d.b(this.d));
            return jSONObject;
        } catch (Exception e) {
            Log.w("Chartboost", "Unable to serialize failed request");
            return null;
        }
    }
}
