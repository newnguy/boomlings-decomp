package net.robotmedia.billing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a {
    private static b a = b.UNKNOWN;
    private static b b = b.UNKNOWN;
    private static Set c = new HashSet();
    private static c d = null;
    private static boolean e = false;
    private static net.robotmedia.billing.b.b f = null;
    private static HashMap g = new HashMap();
    private static Set h = new HashSet();
    private static HashMap i = new HashMap();

    private static List a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("orders");
        int length = optJSONArray != null ? optJSONArray.length() : 0;
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(net.robotmedia.billing.a.c.a(optJSONArray.getJSONObject(i2)));
        }
        return arrayList;
    }

    public static b a(Context context) {
        if (a == b.UNKNOWN) {
            BillingService.a(context);
        } else {
            a(a == b.SUPPORTED);
        }
        return a;
    }

    public static void a() {
        for (n nVar : h) {
            nVar.a();
        }
    }

    public static void a(long j, d dVar) {
        a("Request " + j + " of type " + dVar.c() + " sent");
        if (dVar.e()) {
            i.put(Long.valueOf(j), dVar);
        } else if (dVar.d()) {
            net.robotmedia.billing.c.g.b(dVar.b());
        }
    }

    public static void a(Activity activity, PendingIntent pendingIntent, Intent intent) {
        if (net.robotmedia.billing.c.e.a()) {
            net.robotmedia.billing.c.e.a(activity, pendingIntent.getIntentSender(), intent);
            return;
        }
        try {
            pendingIntent.send(activity, 0, intent);
        } catch (PendingIntent.CanceledException e2) {
            Log.e("Billing", "Error starting purchase intent", e2);
        }
    }

    public static void a(Context context, long j, int i2) {
        k b2 = k.b(i2);
        a("Request " + j + " received response " + b2);
        d dVar = (d) i.get(Long.valueOf(j));
        if (dVar != null) {
            i.remove(Long.valueOf(j));
            dVar.a(b2);
        }
    }

    public static void a(Context context, String str) {
        a("Notification " + str + " available");
        d(context, str);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0058 -> B:85:0x0012). Please submit an issue!!! */
    public static void a(Context context, String str, String str2) {
        a("Purchase state changed");
        if (TextUtils.isEmpty(str)) {
            Log.w("Billing", "Signed data is empty");
            return;
        }
        a(str);
        if (!e) {
            if (TextUtils.isEmpty(str2)) {
                Log.w("Billing", "Empty signature requires debug mode");
                return;
            }
            if (!(f != null ? f : new net.robotmedia.billing.b.a(d)).a(str, str2)) {
                Log.w("Billing", "Signature does not match data.");
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (b(jSONObject)) {
                List<net.robotmedia.billing.a.c> a2 = a(jSONObject);
                ArrayList arrayList = new ArrayList();
                for (net.robotmedia.billing.a.c cVar : a2) {
                    if (cVar.b == null || !c.contains(cVar.e)) {
                        a(cVar.e, cVar.b);
                    } else {
                        arrayList.add(cVar.b);
                    }
                    b(context, cVar);
                    a(cVar.e, cVar.f);
                }
                if (!arrayList.isEmpty()) {
                    a(context, (String[]) arrayList.toArray(new String[arrayList.size()]));
                }
            } else {
                Log.w("Billing", "Invalid nonce");
            }
        } catch (JSONException e2) {
            Log.e("Billing", "JSON exception: ", e2);
        }
    }

    public static void a(Context context, String str, boolean z, String str2) {
        if (z) {
            c.add(str);
        }
        BillingService.a(context, str, str2);
    }

    public static void a(Context context, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            d(context, (net.robotmedia.billing.a.c) it.next());
        }
    }

    static void a(Context context, net.robotmedia.billing.a.c cVar) {
        byte[] b2 = b();
        if (b2 == null) {
            return;
        }
        cVar.c = net.robotmedia.billing.c.g.a(context, b2, cVar.c);
        cVar.e = net.robotmedia.billing.c.g.a(context, b2, cVar.e);
        cVar.a = net.robotmedia.billing.c.g.a(context, b2, cVar.a);
    }

    private static void a(Context context, String[] strArr) {
        BillingService.a(context, strArr);
    }

    public static void a(String str) {
        if (e) {
            Log.d("Billing", str);
        }
    }

    public static void a(String str, PendingIntent pendingIntent) {
        for (n nVar : h) {
            nVar.a(str, pendingIntent);
        }
    }

    private static final void a(String str, String str2) {
        Set set = (Set) g.get(str);
        if (set == null) {
            set = new HashSet();
            g.put(str, set);
        }
        set.add(str2);
    }

    private static void a(String str, net.robotmedia.billing.a.d dVar) {
        for (n nVar : h) {
            nVar.a(str, dVar);
        }
    }

    public static void a(String str, k kVar) {
        for (n nVar : h) {
            nVar.a(str, kVar);
        }
    }

    public static void a(c cVar) {
        d = cVar;
    }

    public static void a(boolean z) {
        a = z ? b.SUPPORTED : b.UNSUPPORTED;
        if (a == b.UNSUPPORTED) {
            b = b.UNSUPPORTED;
        }
        for (n nVar : h) {
            nVar.a(z);
        }
    }

    public static boolean a(n nVar) {
        return h.add(nVar);
    }

    public static b b(Context context) {
        if (b == b.UNKNOWN) {
            BillingService.b(context);
        } else {
            b(b == b.SUPPORTED);
        }
        return b;
    }

    public static void b(Context context, String str) {
        a(context, str, false, null);
    }

    public static void b(Context context, String str, boolean z, String str2) {
        if (z) {
            c.add(str);
        }
        BillingService.b(context, str, str2);
    }

    public static void b(Context context, net.robotmedia.billing.a.c cVar) {
        net.robotmedia.billing.a.c clone = cVar.clone();
        a(context, clone);
        net.robotmedia.billing.a.e.a(context, clone);
    }

    public static void b(boolean z) {
        b = z ? b.SUPPORTED : b.UNSUPPORTED;
        if (b == b.SUPPORTED) {
            a = b.SUPPORTED;
        }
        for (n nVar : h) {
            nVar.b(z);
        }
    }

    public static boolean b(n nVar) {
        return h.remove(nVar);
    }

    private static boolean b(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("nonce");
        if (net.robotmedia.billing.c.g.a(optLong)) {
            net.robotmedia.billing.c.g.b(optLong);
            return true;
        }
        return false;
    }

    private static byte[] b() {
        byte[] bArr = null;
        if (d == null || (bArr = d.getObfuscationSalt()) == null) {
            Log.w("Billing", "Can't (un)obfuscate purchases without salt");
        }
        return bArr;
    }

    public static List c(Context context) {
        List a2 = net.robotmedia.billing.a.e.a(context);
        a(context, a2);
        return a2;
    }

    public static void c(Context context, String str) {
        b(context, str, false, null);
    }

    public static void c(Context context, net.robotmedia.billing.a.c cVar) {
        net.robotmedia.billing.a.c clone = cVar.clone();
        a(context, clone);
        net.robotmedia.billing.a.e.b(context, clone);
    }

    public static void d(Context context) {
        BillingService.a(context, net.robotmedia.billing.c.g.a());
    }

    private static void d(Context context, String str) {
        BillingService.a(context, new String[]{str}, net.robotmedia.billing.c.g.a());
    }

    static void d(Context context, net.robotmedia.billing.a.c cVar) {
        byte[] b2 = b();
        if (b2 == null) {
            return;
        }
        cVar.c = net.robotmedia.billing.c.g.b(context, b2, cVar.c);
        cVar.e = net.robotmedia.billing.c.g.b(context, b2, cVar.e);
        cVar.a = net.robotmedia.billing.c.g.b(context, b2, cVar.a);
    }
}
