package com.b.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import org.json.JSONException;

/* loaded from: classes.dex */
public class f {
    public static final Uri a = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    protected static String b = "https://m.facebook.com/dialog/";
    protected static String c = "https://graph.facebook.com/";
    protected static String d = "https://api.facebook.com/restserver.php";
    private String h;
    private Activity i;
    private String[] j;
    private int k;
    private i l;
    private String e = null;
    private long f = 0;
    private long g = 0;
    private final long m = 86400000;
    private boolean n = true;
    private h o = null;

    public f(String str) {
        if (str == null) {
            throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details.");
        }
        this.h = str;
    }

    public static String a(ContentResolver contentResolver) {
        Cursor query = contentResolver.query(a, new String[]{"aid"}, null, null, null);
        if (query == null || !query.moveToFirst()) {
            return null;
        }
        return query.getString(query.getColumnIndex("aid"));
    }

    private void a(Activity activity, String[] strArr) {
        Bundle bundle = new Bundle();
        if (strArr.length > 0) {
            bundle.putString("scope", TextUtils.join(",", strArr));
        }
        CookieSyncManager.createInstance(activity);
        a(activity, "oauth", bundle, new g(this));
    }

    private boolean a(Activity activity, String str, String[] strArr, int i) {
        boolean z = true;
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", str);
        if (strArr.length > 0) {
            intent.putExtra("scope", TextUtils.join(",", strArr));
        }
        if (a(activity, intent)) {
            this.i = activity;
            this.j = strArr;
            this.k = i;
            try {
                activity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                z = false;
            }
            return z;
        }
        return false;
    }

    private boolean a(Context context, Intent intent) {
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        return a(context, resolveActivity.activityInfo.packageName);
    }

    private boolean a(Context context, String str) {
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (signature.toCharsString().equals("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2")) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(f fVar, String str, Context context) {
        String a2 = a(context.getContentResolver());
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
        String str2 = String.valueOf(str) + "ping";
        if (sharedPreferences.getLong(str2, 0L) != 0 || a2 == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("fields", "supports_attribution");
        Boolean bool = (Boolean) s.c(fVar.a(str, bundle)).get("supports_attribution");
        if (!(bool instanceof Boolean)) {
            throw new JSONException(String.format("%s contains %s instead of a Boolean", "supports_attribution", bool));
        }
        if (bool.booleanValue()) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("event", "MOBILE_APP_INSTALL");
            bundle2.putString("attribution", a2);
            fVar.a(String.format("%s/activities", str), bundle2, "POST");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(str2, System.currentTimeMillis());
            edit.commit();
        }
    }

    private boolean b(Context context, Intent intent) {
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null) {
            return false;
        }
        return a(context, resolveService.serviceInfo.packageName);
    }

    public String a(Context context) {
        s.a(context);
        Bundle bundle = new Bundle();
        bundle.putString("method", "auth.expireSession");
        String a2 = a(bundle);
        a((String) null);
        a(0L);
        return a2;
    }

    public String a(Bundle bundle) {
        if (bundle.containsKey("method")) {
            return a((String) null, bundle, "GET");
        }
        throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/");
    }

    public String a(String str, Bundle bundle) {
        return a(str, bundle, "GET");
    }

    public String a(String str, Bundle bundle, String str2) {
        bundle.putString("format", "json");
        if (b()) {
            bundle.putString("access_token", c());
        }
        return s.a(str != null ? String.valueOf(c) + str : d, str2, bundle);
    }

    public void a(int i, int i2, Intent intent) {
        if (i == this.k) {
            if (i2 != -1) {
                if (i2 == 0) {
                    if (intent != null) {
                        s.a("Facebook-authorize", "Login failed: " + intent.getStringExtra("error"));
                        this.l.onError(new e(intent.getStringExtra("error"), intent.getIntExtra("error_code", -1), intent.getStringExtra("failing_url")));
                        return;
                    }
                    s.a("Facebook-authorize", "Login canceled by user.");
                    this.l.onCancel();
                    return;
                }
                return;
            }
            String stringExtra = intent.getStringExtra("error");
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra("error_type");
            }
            if (stringExtra == null) {
                a(intent.getStringExtra("access_token"));
                b(intent.getStringExtra("expires_in"));
                if (!b()) {
                    this.l.onFacebookError(new m("Failed to receive access token."));
                    return;
                }
                s.a("Facebook-authorize", "Login Success! access_token=" + c() + " expires=" + d());
                this.l.onComplete(intent.getExtras());
            } else if (stringExtra.equals("service_disabled") || stringExtra.equals("AndroidAuthKillSwitchException")) {
                s.a("Facebook-authorize", "Hosted auth currently disabled. Retrying dialog auth...");
                a(this.i, this.j);
            } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                s.a("Facebook-authorize", "Login canceled by user.");
                this.l.onCancel();
            } else {
                String stringExtra2 = intent.getStringExtra("error_description");
                if (stringExtra2 != null) {
                    stringExtra = String.valueOf(stringExtra) + ":" + stringExtra2;
                }
                s.a("Facebook-authorize", "Login failed: " + stringExtra);
                this.l.onFacebookError(new m(stringExtra));
            }
        }
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(Activity activity, String[] strArr, int i, i iVar) {
        this.l = iVar;
        b(activity.getApplicationContext());
        if (i >= 0 ? a(activity, this.h, strArr, i) : false) {
            return;
        }
        a(activity, strArr);
    }

    public void a(Activity activity, String[] strArr, i iVar) {
        a(activity, strArr, 32665, iVar);
    }

    public void a(Context context, String str, Bundle bundle, i iVar) {
        String str2 = String.valueOf(b) + str;
        bundle.putString("display", "touch");
        bundle.putString("redirect_uri", "fbconnect://success");
        if (str.equals("oauth")) {
            bundle.putString("type", "user_agent");
            bundle.putString("client_id", this.h);
        } else {
            bundle.putString("app_id", this.h);
        }
        if (b()) {
            bundle.putString("access_token", c());
        }
        String str3 = String.valueOf(str2) + "?" + s.a(bundle);
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            s.a(context, "Error", "Application requires permission to access the Internet");
        } else {
            new n(context, str3, iVar).show();
        }
    }

    public void a(String str) {
        this.e = str;
        this.f = System.currentTimeMillis();
    }

    public boolean a() {
        return b() && System.currentTimeMillis() - this.f >= 86400000;
    }

    public boolean a(Context context, j jVar) {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.platform.TokenRefreshService");
        if (b(context, intent)) {
            return context.bindService(intent, new k(this, context, jVar), 1);
        }
        return false;
    }

    void b(Context context) {
        String str;
        h hVar = null;
        synchronized (this) {
            if (this.o == null && e() && (str = this.h) != null) {
                hVar = new h(this, str, context);
                this.o = hVar;
            }
        }
        if (hVar != null) {
            hVar.execute(new Void[0]);
        }
    }

    public void b(String str) {
        if (str != null) {
            a(str.equals("0") ? 0L : System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    public boolean b() {
        return c() != null && (d() == 0 || System.currentTimeMillis() < d());
    }

    public boolean b(Context context, j jVar) {
        if (a()) {
            return a(context, jVar);
        }
        return true;
    }

    public String c() {
        return this.e;
    }

    public long d() {
        return this.g;
    }

    public boolean e() {
        return this.n;
    }
}
