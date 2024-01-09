package com.b.a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import org.json.JSONException;

public class f {
  public static final Uri a = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  
  protected static String b = "https://m.facebook.com/dialog/";
  
  protected static String c = "https://graph.facebook.com/";
  
  protected static String d = "https://api.facebook.com/restserver.php";
  
  private String e = null;
  
  private long f = 0L;
  
  private long g = 0L;
  
  private String h;
  
  private Activity i;
  
  private String[] j;
  
  private int k;
  
  private i l;
  
  private final long m = 86400000L;
  
  private boolean n = true;
  
  private h o = null;
  
  public f(String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details."); 
    this.h = paramString;
  }
  
  public static String a(ContentResolver paramContentResolver) {
    ContentResolver contentResolver = null;
    Cursor cursor = paramContentResolver.query(a, new String[] { "aid" }, null, null, null);
    paramContentResolver = contentResolver;
    if (cursor != null) {
      if (!cursor.moveToFirst())
        return (String)contentResolver; 
    } else {
      return (String)paramContentResolver;
    } 
    return cursor.getString(cursor.getColumnIndex("aid"));
  }
  
  private void a(Activity paramActivity, String[] paramArrayOfString) {
    Bundle bundle = new Bundle();
    if (paramArrayOfString.length > 0)
      bundle.putString("scope", TextUtils.join(",", (Object[])paramArrayOfString)); 
    CookieSyncManager.createInstance((Context)paramActivity);
    a((Context)paramActivity, "oauth", bundle, new g(this));
  }
  
  private boolean a(Activity paramActivity, String paramString, String[] paramArrayOfString, int paramInt) {
    boolean bool2 = false;
    boolean bool1 = true;
    Intent intent = new Intent();
    intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
    intent.putExtra("client_id", paramString);
    if (paramArrayOfString.length > 0)
      intent.putExtra("scope", TextUtils.join(",", (Object[])paramArrayOfString)); 
    if (!a((Context)paramActivity, intent))
      return bool2; 
    this.i = paramActivity;
    this.j = paramArrayOfString;
    this.k = paramInt;
    try {
      paramActivity.startActivityForResult(intent, paramInt);
    } catch (ActivityNotFoundException activityNotFoundException) {
      bool1 = false;
    } 
    return bool1;
  }
  
  private boolean a(Context paramContext, Intent paramIntent) {
    boolean bool = false;
    ResolveInfo resolveInfo = paramContext.getPackageManager().resolveActivity(paramIntent, 0);
    if (resolveInfo != null)
      bool = a(paramContext, resolveInfo.activityInfo.packageName); 
    return bool;
  }
  
  private boolean a(Context paramContext, String paramString) {
    boolean bool = false;
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 64);
      Signature[] arrayOfSignature = packageInfo.signatures;
      int j = arrayOfSignature.length;
      byte b = 0;
      while (true) {
        if (b < j) {
          if (arrayOfSignature[b].toCharsString().equals("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2"))
            return true; 
          b++;
          continue;
        } 
        return bool;
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return bool;
  }
  
  private static void b(f paramf, String paramString, Context paramContext) {
    String str2 = a(paramContext.getContentResolver());
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
    String str1 = String.valueOf(paramString) + "ping";
    if (sharedPreferences.getLong(str1, 0L) == 0L && str2 != null) {
      Bundle bundle = new Bundle();
      bundle.putString("fields", "supports_attribution");
      Boolean bool = (Boolean)s.c(paramf.a(paramString, bundle)).get("supports_attribution");
      if (!(bool instanceof Boolean))
        throw new JSONException(String.format("%s contains %s instead of a Boolean", new Object[] { "supports_attribution", bool })); 
      if (bool.booleanValue()) {
        Bundle bundle1 = new Bundle();
        bundle1.putString("event", "MOBILE_APP_INSTALL");
        bundle1.putString("attribution", str2);
        paramf.a(String.format("%s/activities", new Object[] { paramString }), bundle1, "POST");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(str1, System.currentTimeMillis());
        editor.commit();
      } 
    } 
  }
  
  private boolean b(Context paramContext, Intent paramIntent) {
    boolean bool = false;
    ResolveInfo resolveInfo = paramContext.getPackageManager().resolveService(paramIntent, 0);
    if (resolveInfo != null)
      bool = a(paramContext, resolveInfo.serviceInfo.packageName); 
    return bool;
  }
  
  public String a(Context paramContext) {
    s.a(paramContext);
    Bundle bundle = new Bundle();
    bundle.putString("method", "auth.expireSession");
    String str = a(bundle);
    a((String)null);
    a(0L);
    return str;
  }
  
  public String a(Bundle paramBundle) {
    if (!paramBundle.containsKey("method"))
      throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/"); 
    return a((String)null, paramBundle, "GET");
  }
  
  public String a(String paramString, Bundle paramBundle) {
    return a(paramString, paramBundle, "GET");
  }
  
  public String a(String paramString1, Bundle paramBundle, String paramString2) {
    paramBundle.putString("format", "json");
    if (b())
      paramBundle.putString("access_token", c()); 
    if (paramString1 != null) {
      paramString1 = String.valueOf(c) + paramString1;
      return s.a(paramString1, paramString2, paramBundle);
    } 
    paramString1 = d;
    return s.a(paramString1, paramString2, paramBundle);
  }
  
  public void a(int paramInt1, int paramInt2, Intent paramIntent) {
    String str;
    if (paramInt1 == this.k) {
      if (paramInt2 == -1) {
        String str2 = paramIntent.getStringExtra("error");
        String str1 = str2;
        if (str2 == null)
          str1 = paramIntent.getStringExtra("error_type"); 
        if (str1 != null) {
          if (str1.equals("service_disabled") || str1.equals("AndroidAuthKillSwitchException")) {
            s.a("Facebook-authorize", "Hosted auth currently disabled. Retrying dialog auth...");
            a(this.i, this.j);
            return;
          } 
          if (str1.equals("access_denied") || str1.equals("OAuthAccessDeniedException")) {
            s.a("Facebook-authorize", "Login canceled by user.");
            this.l.onCancel();
            return;
          } 
          str2 = paramIntent.getStringExtra("error_description");
          str = str1;
          if (str2 != null)
            str = String.valueOf(str1) + ":" + str2; 
          s.a("Facebook-authorize", "Login failed: " + str);
          this.l.onFacebookError(new m(str));
          return;
        } 
        a(str.getStringExtra("access_token"));
        b(str.getStringExtra("expires_in"));
        if (b()) {
          s.a("Facebook-authorize", "Login Success! access_token=" + c() + " expires=" + d());
          this.l.onComplete(str.getExtras());
          return;
        } 
        this.l.onFacebookError(new m("Failed to receive access token."));
        return;
      } 
    } else {
      return;
    } 
    if (paramInt2 == 0) {
      if (str != null) {
        s.a("Facebook-authorize", "Login failed: " + str.getStringExtra("error"));
        this.l.onError(new e(str.getStringExtra("error"), str.getIntExtra("error_code", -1), str.getStringExtra("failing_url")));
        return;
      } 
      s.a("Facebook-authorize", "Login canceled by user.");
      this.l.onCancel();
    } 
  }
  
  public void a(long paramLong) {
    this.g = paramLong;
  }
  
  public void a(Activity paramActivity, String[] paramArrayOfString, int paramInt, i parami) {
    boolean bool = false;
    this.l = parami;
    b(paramActivity.getApplicationContext());
    if (paramInt >= 0)
      bool = a(paramActivity, this.h, paramArrayOfString, paramInt); 
    if (!bool)
      a(paramActivity, paramArrayOfString); 
  }
  
  public void a(Activity paramActivity, String[] paramArrayOfString, i parami) {
    a(paramActivity, paramArrayOfString, 32665, parami);
  }
  
  public void a(Context paramContext, String paramString, Bundle paramBundle, i parami) {
    String str = String.valueOf(b) + paramString;
    paramBundle.putString("display", "touch");
    paramBundle.putString("redirect_uri", "fbconnect://success");
    if (paramString.equals("oauth")) {
      paramBundle.putString("type", "user_agent");
      paramBundle.putString("client_id", this.h);
    } else {
      paramBundle.putString("app_id", this.h);
    } 
    if (b())
      paramBundle.putString("access_token", c()); 
    paramString = String.valueOf(str) + "?" + s.a(paramBundle);
    if (paramContext.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
      s.a(paramContext, "Error", "Application requires permission to access the Internet");
      return;
    } 
    (new n(paramContext, paramString, parami)).show();
  }
  
  public void a(String paramString) {
    this.e = paramString;
    this.f = System.currentTimeMillis();
  }
  
  public boolean a() {
    return (b() && System.currentTimeMillis() - this.f >= 86400000L);
  }
  
  public boolean a(Context paramContext, j paramj) {
    Intent intent = new Intent();
    intent.setClassName("com.facebook.katana", "com.facebook.katana.platform.TokenRefreshService");
    return !b(paramContext, intent) ? false : paramContext.bindService(intent, new k(this, paramContext, paramj), 1);
  }
  
  void b(Context paramContext) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_3
    //   5: astore_2
    //   6: aload_0
    //   7: getfield o : Lcom/b/a/h;
    //   10: ifnonnull -> 52
    //   13: aload_3
    //   14: astore_2
    //   15: aload_0
    //   16: invokevirtual e : ()Z
    //   19: ifeq -> 52
    //   22: aload_0
    //   23: getfield h : Ljava/lang/String;
    //   26: astore #4
    //   28: aload_3
    //   29: astore_2
    //   30: aload #4
    //   32: ifnull -> 52
    //   35: new com/b/a/h
    //   38: astore_2
    //   39: aload_2
    //   40: aload_0
    //   41: aload #4
    //   43: aload_1
    //   44: invokespecial <init> : (Lcom/b/a/f;Ljava/lang/String;Landroid/content/Context;)V
    //   47: aload_0
    //   48: aload_2
    //   49: putfield o : Lcom/b/a/h;
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_2
    //   55: ifnull -> 67
    //   58: aload_2
    //   59: iconst_0
    //   60: anewarray java/lang/Void
    //   63: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   66: pop
    //   67: return
    //   68: astore_1
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_1
    //   72: athrow
    // Exception table:
    //   from	to	target	type
    //   6	13	68	finally
    //   15	28	68	finally
    //   35	52	68	finally
    //   52	54	68	finally
    //   69	71	68	finally
  }
  
  public void b(String paramString) {
    if (paramString != null) {
      long l;
      if (paramString.equals("0")) {
        l = 0L;
      } else {
        l = System.currentTimeMillis() + Long.parseLong(paramString) * 1000L;
      } 
      a(l);
    } 
  }
  
  public boolean b() {
    return (c() != null && (d() == 0L || System.currentTimeMillis() < d()));
  }
  
  public boolean b(Context paramContext, j paramj) {
    return a() ? a(paramContext, paramj) : true;
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\b\a\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */