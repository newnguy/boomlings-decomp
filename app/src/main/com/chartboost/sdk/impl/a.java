package com.chartboost.sdk.impl;

import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.b;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    public JSONObject a;
    public Date b;
    public b c;
    public c d;
    public String e;
    public com.chartboost.sdk.b f;
    public InterfaceC0004a g;
    public s h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* renamed from: com.chartboost.sdk.impl.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0004a {
        void a(a aVar);

        void a(a aVar, String str, JSONObject jSONObject);

        void b(a aVar);

        void c(a aVar);
    }

    /* loaded from: classes.dex */
    public enum b {
        CBImpressionStateOther,
        CBImpressionStateWaitingForDisplay,
        CBImpressionStateDisplayedByDefaultController,
        CBImpressionStateWaitingForDismissal,
        CBImpressionStateWaitingForCaching,
        CBImpressionStateCached;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static b[] valuesCustom() {
            b[] valuesCustom = values();
            int length = valuesCustom.length;
            b[] bVarArr = new b[length];
            System.arraycopy(valuesCustom, 0, bVarArr, 0, length);
            return bVarArr;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        CBImpressionTypeOther,
        CBImpressionTypeInterstitial,
        CBImpressionTypeMoreApps;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static c[] valuesCustom() {
            c[] valuesCustom = values();
            int length = valuesCustom.length;
            c[] cVarArr = new c[length];
            System.arraycopy(valuesCustom, 0, cVarArr, 0, length);
            return cVarArr;
        }
    }

    public a(JSONObject jSONObject, c cVar, InterfaceC0004a interfaceC0004a, b bVar, String str) {
        jSONObject = jSONObject == null ? new JSONObject() : jSONObject;
        this.c = bVar;
        this.e = str;
        this.a = jSONObject;
        this.b = new Date();
        this.g = interfaceC0004a;
        this.d = cVar;
        this.i = false;
        this.j = false;
        this.k = false;
        boolean equals = jSONObject.optString("type", "").equals("native");
        if (equals && this.d == c.CBImpressionTypeInterstitial) {
            this.f = new com.chartboost.sdk.impl.b(this);
        } else if (equals && this.d == c.CBImpressionTypeMoreApps) {
            this.f = new h(this);
        } else {
            this.f = new v(this);
        }
        this.f.c = new b.a() { // from class: com.chartboost.sdk.impl.a.1
            @Override // com.chartboost.sdk.b.a
            public void a() {
                if (this.g != null) {
                    this.g.a(this);
                }
            }
        };
        this.f.a = new b.a() { // from class: com.chartboost.sdk.impl.a.2
            @Override // com.chartboost.sdk.b.a
            public void a() {
                if (this.g != null) {
                    this.g.b(this);
                }
            }
        };
        this.f.b = new b.c() { // from class: com.chartboost.sdk.impl.a.3
            @Override // com.chartboost.sdk.b.c
            public void a(String str2, JSONObject jSONObject2) {
                if (str2 == null) {
                    str2 = this.a.optString("link");
                }
                String optString = this.a.optString("deep-link");
                if (optString != null && !optString.equals("")) {
                    try {
                        if (Chartboost.sharedChartboost().getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 65536).size() > 0) {
                            str2 = optString;
                        }
                    } catch (Exception e) {
                    }
                }
                this.g.a(this, str2, jSONObject2);
            }
        };
        this.f.d = new b.a() { // from class: com.chartboost.sdk.impl.a.4
            @Override // com.chartboost.sdk.b.a
            public void a() {
                this.g.c(this);
            }
        };
        this.f.a(jSONObject);
    }

    public boolean a() {
        this.i = true;
        this.j = true;
        this.k = true;
        this.f.a();
        if (this.f.d() != null) {
            return true;
        }
        this.i = false;
        this.j = false;
        this.k = false;
        return false;
    }

    public void b() {
        if (this.h != null) {
            this.h.a();
            try {
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e) {
            }
        }
        if (this.f != null) {
            this.f.c();
        }
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
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e) {
            }
        }
        if (this.f != null) {
            this.f.e();
        }
    }
}
