package com.chartboost.sdk.Analytics;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import java.math.BigDecimal;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CBAnalytics {
    public static final String TAG = "Chartboost Analytics";
    private static CBAnalytics a = null;
    private j b = new j(null, null, "CBAnalytics");

    private CBAnalytics() {
        this.b.a();
    }

    private String a(double d, int i, int i2) {
        return new StringBuilder(String.valueOf(new BigDecimal(d).setScale(i, i2).doubleValue())).toString();
    }

    public static synchronized CBAnalytics sharedAnalytics() {
        CBAnalytics cBAnalytics;
        synchronized (CBAnalytics.class) {
            if (a == null) {
                a = new CBAnalytics();
            }
            cBAnalytics = a;
        }
        return cBAnalytics;
    }

    public Boolean recordPaymentTransaction(String str, String str2, double d, String str3, int i, Map map) {
        Chartboost sharedChartboost = Chartboost.sharedChartboost();
        if (sharedChartboost.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling recordPaymentTransaction().");
        }
        if (sharedChartboost.getAppID() == null || sharedChartboost.getAppSignature() == null) {
            return false;
        }
        k kVar = new k("api", "purchase");
        kVar.a(sharedChartboost.getContext());
        kVar.a("product_id", str);
        kVar.a("title", str2);
        kVar.a("price", a(d, 2, 4));
        kVar.a("currency", str3);
        kVar.a("quantity", new StringBuilder(String.valueOf(i)).toString());
        kVar.a("timestamp", new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000.0d)).toString());
        if (map != null) {
            kVar.a("meta", new JSONObject(map).toString());
        }
        kVar.c(sharedChartboost.getAppID(), sharedChartboost.getAppSignature());
        this.b.a(kVar);
        return true;
    }

    public Boolean trackEvent(String str) {
        return trackEvent(str, 1.0d, null);
    }

    public Boolean trackEvent(String str, double d) {
        return trackEvent(str, d, null);
    }

    public Boolean trackEvent(String str, double d, Map map) {
        Chartboost sharedChartboost = Chartboost.sharedChartboost();
        if (sharedChartboost.getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling trackEvent().");
        }
        if (sharedChartboost.getAppID() == null || sharedChartboost.getAppSignature() == null) {
            return false;
        }
        k kVar = new k("api", "event");
        kVar.a(sharedChartboost.getContext());
        kVar.a("key", str);
        kVar.a("value", new StringBuilder(String.valueOf(d)).toString());
        kVar.a("timestamp", new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000.0d)).toString());
        if (map != null) {
            kVar.a("meta", new JSONObject(map).toString());
        }
        kVar.c(sharedChartboost.getAppID(), sharedChartboost.getAppSignature());
        kVar.i = true;
        this.b.a(kVar);
        return true;
    }
}
