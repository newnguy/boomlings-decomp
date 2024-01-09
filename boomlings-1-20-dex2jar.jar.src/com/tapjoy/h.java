package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;
import org.w3c.dom.Document;

public class h {
  private static String A;
  
  private static String B;
  
  private static String C;
  
  private static String D;
  
  private static String E;
  
  private static String F;
  
  private static String G;
  
  private static String H;
  
  private static boolean I;
  
  private static String J;
  
  private static float K;
  
  private static String L;
  
  private static Hashtable O;
  
  private static String P;
  
  private static Context a = null;
  
  private static h b = null;
  
  private static am c = null;
  
  private static m d = null;
  
  private static String e = "";
  
  private static String f = "";
  
  private static String g = "";
  
  private static String h = "";
  
  private static String i = "";
  
  private static String j = "";
  
  private static String k = "";
  
  private static String l = "";
  
  private static String m = "";
  
  private static String n = "";
  
  private static String o = "";
  
  private static String p = "";
  
  private static String q = "";
  
  private static String r = "";
  
  private static String s = "";
  
  private static String t = "";
  
  private static String u = "";
  
  private static String v = "";
  
  private static String w = "";
  
  private static String x = "";
  
  private static String y = "";
  
  private static String z = "";
  
  private long M = 0L;
  
  private Timer N = null;
  
  static {
    A = "";
    B = "";
    C = "";
    D = "";
    E = "";
    F = "";
    G = "native";
    H = "";
    I = false;
    J = "";
    K = 1.0F;
    L = null;
    O = null;
    P = "";
  }
  
  public h(Context paramContext) {
    a = paramContext;
    c = new am();
    o();
    aj.a("TapjoyConnect", "URL parameters: " + c());
    b();
    if (e("user_id") != null && e("user_id").length() > 0) {
      aj.a("TapjoyConnect", "Setting userID to: " + e("user_id"));
      b(e("user_id"));
    } 
  }
  
  public static h a() {
    return b;
  }
  
  public static String a(long paramLong) {
    String str = "";
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      String str1 = an.b(stringBuilder.append(q).append(":").append(f).append(":").append(paramLong).append(":").append(D).toString());
      str = str1;
    } catch (Exception exception) {
      aj.b("TapjoyConnect", "getVerifier ERROR: " + exception.toString());
    } 
    return str;
  }
  
  public static String a(long paramLong, String paramString) {
    String str1;
    String str2 = "";
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      paramString = an.b(stringBuilder.append(q).append(":").append(f).append(":").append(paramLong).append(":").append(D).append(":").append(paramString).toString());
    } catch (Exception exception) {
      aj.b("TapjoyConnect", "getVerifier ERROR: " + exception.toString());
      str1 = str2;
    } 
    return str1;
  }
  
  public static void a(int paramInt) {
    SharedPreferences.Editor editor = a.getSharedPreferences("tjcPrefrences", 0).edit();
    editor.putInt("last_tap_points", paramInt);
    editor.commit();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable, m paramm) {
    q = paramString1;
    D = paramString2;
    O = paramHashtable;
    d = paramm;
    b = new h(paramContext);
  }
  
  public static void a(String paramString) {
    H = paramString;
  }
  
  public static void a(boolean paramBoolean) {
    I = paramBoolean;
  }
  
  public static void b(String paramString) {
    v = paramString;
    aj.a("TapjoyConnect", "URL parameters: " + c());
    (new Thread(new i())).start();
  }
  
  public static String c() {
    String str2 = d() + "&";
    long l = System.currentTimeMillis() / 1000L;
    String str1 = a(l);
    str2 = str2 + "timestamp=" + l + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static void c(String paramString) {
    J = paramString;
  }
  
  public static String d() {
    String str = "" + "app_id=" + Uri.encode(q) + "&";
    return str + n();
  }
  
  public static String e() {
    return v;
  }
  
  public static String e(String paramString) {
    String str = "";
    if (O != null)
      str = (String)O.get(paramString); 
    paramString = str;
    if (str == null)
      paramString = ""; 
    return paramString;
  }
  
  public static String f() {
    String str = "";
    if (I) {
      if (J.length() > 0)
        str = "video_offer_ids=" + J; 
      aj.a("TapjoyConnect", "video parameters: " + str);
      return str;
    } 
    str = "hide_videos=true";
    aj.a("TapjoyConnect", "video parameters: " + str);
    return str;
  }
  
  public static String g() {
    // Byte code:
    //   0: ldc ''
    //   2: astore_2
    //   3: aload_2
    //   4: astore_1
    //   5: getstatic com/tapjoy/h.a : Landroid/content/Context;
    //   8: ldc_w 'connectivity'
    //   11: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   14: checkcast android/net/ConnectivityManager
    //   17: astore_3
    //   18: aload_2
    //   19: astore_0
    //   20: aload_3
    //   21: ifnull -> 148
    //   24: aload_2
    //   25: astore_0
    //   26: aload_2
    //   27: astore_1
    //   28: aload_3
    //   29: invokevirtual getActiveNetworkInfo : ()Landroid/net/NetworkInfo;
    //   32: ifnull -> 148
    //   35: aload_2
    //   36: astore_1
    //   37: aload_3
    //   38: invokevirtual getActiveNetworkInfo : ()Landroid/net/NetworkInfo;
    //   41: invokevirtual getType : ()I
    //   44: lookupswitch default -> 72, 1 -> 150, 6 -> 150
    //   72: ldc_w 'mobile'
    //   75: astore_0
    //   76: aload_0
    //   77: astore_1
    //   78: new java/lang/StringBuilder
    //   81: astore_2
    //   82: aload_0
    //   83: astore_1
    //   84: aload_2
    //   85: invokespecial <init> : ()V
    //   88: aload_0
    //   89: astore_1
    //   90: ldc 'TapjoyConnect'
    //   92: aload_2
    //   93: ldc_w 'connectivity: '
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_3
    //   100: invokevirtual getActiveNetworkInfo : ()Landroid/net/NetworkInfo;
    //   103: invokevirtual getType : ()I
    //   106: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   115: aload_0
    //   116: astore_1
    //   117: new java/lang/StringBuilder
    //   120: astore_2
    //   121: aload_0
    //   122: astore_1
    //   123: aload_2
    //   124: invokespecial <init> : ()V
    //   127: aload_0
    //   128: astore_1
    //   129: ldc 'TapjoyConnect'
    //   131: aload_2
    //   132: ldc_w 'connection_type: '
    //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: aload_0
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual toString : ()Ljava/lang/String;
    //   145: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   148: aload_0
    //   149: areturn
    //   150: ldc_w 'wifi'
    //   153: astore_0
    //   154: goto -> 76
    //   157: astore_0
    //   158: ldc 'TapjoyConnect'
    //   160: new java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial <init> : ()V
    //   167: ldc_w 'getConnectionType error: '
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload_0
    //   174: invokevirtual toString : ()Ljava/lang/String;
    //   177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual toString : ()Ljava/lang/String;
    //   183: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   186: aload_1
    //   187: astore_0
    //   188: goto -> 148
    // Exception table:
    //   from	to	target	type
    //   5	18	157	java/lang/Exception
    //   28	35	157	java/lang/Exception
    //   37	72	157	java/lang/Exception
    //   78	82	157	java/lang/Exception
    //   84	88	157	java/lang/Exception
    //   90	115	157	java/lang/Exception
    //   117	121	157	java/lang/Exception
    //   123	127	157	java/lang/Exception
    //   129	148	157	java/lang/Exception
  }
  
  private static boolean g(String paramString) {
    Document document = an.c(paramString);
    if (document != null) {
      String str2 = an.a(document.getElementsByTagName("PackageNames"));
      if (str2 != null && str2.length() > 0) {
        Vector<String> vector = new Vector();
        for (int i = 0;; i = j + 1) {
          int j = str2.indexOf(',', i);
          if (j == -1) {
            aj.a("TapjoyConnect", "parse: " + str2.substring(i).trim());
            vector.add(str2.substring(i).trim());
            P = "";
            for (ApplicationInfo applicationInfo : a.getPackageManager().getInstalledApplications(0)) {
              if ((applicationInfo.flags & 0x1) != 1 && vector.contains(applicationInfo.packageName)) {
                aj.a("TapjoyConnect", "MATCH: installed packageName: " + applicationInfo.packageName);
                if (P.length() > 0)
                  P += ","; 
                P += applicationInfo.packageName;
              } 
            } 
            break;
          } 
          aj.a("TapjoyConnect", "parse: " + str2.substring(i, j).trim());
          vector.add(str2.substring(i, j).trim());
        } 
      } 
      String str1 = an.a(document.getElementsByTagName("Success"));
      if (str1 == null || str1.equals("true"));
    } 
    return true;
  }
  
  public static String h() {
    return E;
  }
  
  private boolean h(String paramString) {
    Document document = an.c(paramString);
    if (document != null) {
      String str = an.a(document.getElementsByTagName("Success"));
      if (str != null && str.equals("true")) {
        aj.a("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
        return true;
      } 
      aj.b("TapjoyConnect", "Completed Pay-Per-Action call failed.");
    } 
    return false;
  }
  
  public static int i() {
    return a.getSharedPreferences("tjcPrefrences", 0).getInt("last_tap_points", -9999);
  }
  
  private static String n() {
    String str1 = "" + "android_id=" + e + "&";
    if (e("sha_2_udid") != null && e("sha_2_udid").equals("true")) {
      str2 = str1 + "sha2_udid=" + Uri.encode(g) + "&";
    } else {
      str2 = str1 + "udid=" + Uri.encode(f) + "&";
    } 
    str1 = str2;
    if (h != null) {
      str1 = str2;
      if (h.length() > 0)
        str1 = str2 + "sha1_mac_address=" + Uri.encode(i) + "&"; 
    } 
    String str2 = str1;
    if (j != null) {
      str2 = str1;
      if (j.length() > 0)
        str2 = str1 + "serial_id=" + Uri.encode(j) + "&"; 
    } 
    str1 = str2 + "device_name=" + Uri.encode(k) + "&";
    str1 = str1 + "device_manufacturer=" + Uri.encode(l) + "&";
    str1 = str1 + "device_type=" + Uri.encode(m) + "&";
    str1 = str1 + "os_version=" + Uri.encode(n) + "&";
    str1 = str1 + "country_code=" + Uri.encode(o) + "&";
    str1 = str1 + "language_code=" + Uri.encode(p) + "&";
    str1 = str1 + "app_version=" + Uri.encode(r) + "&";
    str1 = str1 + "library_version=" + Uri.encode(s) + "&";
    str1 = str1 + "platform=" + Uri.encode(w) + "&";
    str1 = str1 + "display_multiplier=" + Uri.encode(Float.toString(K));
    str2 = str1;
    if (x.length() > 0) {
      str1 = str1 + "&";
      str2 = str1 + "carrier_name=" + Uri.encode(x);
    } 
    str1 = str2;
    if (y.length() > 0) {
      str1 = str2 + "&";
      str1 = str1 + "carrier_country_code=" + Uri.encode(y);
    } 
    String str3 = str1;
    if (z.length() > 0) {
      str1 = str1 + "&";
      str3 = str1 + "mobile_country_code=" + Uri.encode(z);
    } 
    str2 = str3;
    if (A.length() > 0) {
      str1 = str3 + "&";
      str2 = str1 + "mobile_network_code=" + Uri.encode(A);
    } 
    str1 = str2;
    if (t.length() > 0) {
      str1 = str2;
      if (u.length() > 0) {
        str1 = str2 + "&";
        str1 = str1 + "screen_density=" + Uri.encode(t) + "&";
        str1 = str1 + "screen_layout_size=" + Uri.encode(u);
      } 
    } 
    B = g();
    str2 = str1;
    if (B.length() > 0) {
      str1 = str1 + "&";
      str2 = str1 + "connection_type=" + Uri.encode(B);
    } 
    str1 = str2;
    if (G.length() > 0) {
      str1 = str2 + "&";
      str1 = str1 + "plugin=" + Uri.encode(G);
    } 
    str2 = str1;
    if (H.length() > 0) {
      str1 = str1 + "&";
      str2 = str1 + "sdk_type=" + Uri.encode(H);
    } 
    str1 = str2;
    if (C.length() > 0) {
      str1 = str2 + "&";
      str1 = str1 + "store_name=" + Uri.encode(C);
    } 
    return str1;
  }
  
  private void o() {
    PackageManager packageManager = a.getPackageManager();
    try {
      if (O == null) {
        Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        this();
        O = hashtable;
      } 
      if (packageManager != null) {
        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.getPackageName(), 128);
        if (applicationInfo != null && applicationInfo.metaData != null) {
          for (String str : l.a) {
            Bundle bundle = applicationInfo.metaData;
            StringBuilder stringBuilder = new StringBuilder();
            this();
            Object object = bundle.get(stringBuilder.append("tapjoy.").append(str).toString());
            if (object != null) {
              String str1 = object.toString();
              if (str1 != null) {
                object = new StringBuilder();
                super();
                aj.a("TapjoyConnect", object.append("Found manifest flag: ").append(str).append(", ").append(str1).toString());
                O.put(str, str1);
              } 
            } 
          } 
          aj.a("TapjoyConnect", "Metadata successfully loaded");
        } else {
          aj.a("TapjoyConnect", "No metadata present.");
        } 
      } 
    } catch (Exception exception) {
      aj.b("TapjoyConnect", "Error reading manifest meta-data: " + exception.toString());
    } 
    if (e("enable_logging") != null && e("enable_logging").equals("true"))
      aj.a(true); 
    try {
      String str1;
      e = Settings.Secure.getString(a.getContentResolver(), "android_id");
      r = (packageManager.getPackageInfo(a.getPackageName(), 0)).versionName;
      m = "android";
      w = "android";
      k = Build.MODEL;
      l = Build.MANUFACTURER;
      n = Build.VERSION.RELEASE;
      o = Locale.getDefault().getCountry();
      p = Locale.getDefault().getLanguage();
      s = "9.0.0";
      SharedPreferences sharedPreferences = a.getSharedPreferences("tjcPrefrences", 0);
      try {
        byte b2;
        TelephonyManager telephonyManager = (TelephonyManager)a.getSystemService("phone");
        if (telephonyManager != null) {
          if (e("debug_device_id") != null && e("debug_device_id").length() > 0) {
            f = e("debug_device_id");
          } else {
            f = telephonyManager.getDeviceId();
          } 
          x = telephonyManager.getNetworkOperatorName();
          y = telephonyManager.getNetworkCountryIso();
          if (telephonyManager.getNetworkOperator() != null && (telephonyManager.getNetworkOperator().length() == 5 || telephonyManager.getNetworkOperator().length() == 6)) {
            z = telephonyManager.getNetworkOperator().substring(0, 3);
            A = telephonyManager.getNetworkOperator().substring(3);
          } 
        } 
        StringBuilder stringBuilder = new StringBuilder();
        this();
        aj.a("TapjoyConnect", stringBuilder.append("deviceID: ").append(f).toString());
        if (f == null) {
          aj.b("TapjoyConnect", "Device id is null.");
          b2 = 1;
        } else if (f.length() == 0 || f.equals("000000000000000") || f.equals("0")) {
          aj.b("TapjoyConnect", "Device id is empty or an emulator.");
          b2 = 1;
        } else {
          f = f.toLowerCase();
          b2 = 0;
        } 
        stringBuilder = new StringBuilder();
        this();
        aj.a("TapjoyConnect", stringBuilder.append("ANDROID SDK VERSION: ").append(Build.VERSION.SDK).toString());
        byte b1 = b2;
        if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
          aj.a("TapjoyConnect", "TRYING TO GET SERIAL OF 2.3+ DEVICE...");
          ah ah = new ah();
          this();
          j = ah.a();
          if (b2)
            f = j; 
          aj.a("TapjoyConnect", "====================");
          StringBuilder stringBuilder4 = new StringBuilder();
          this();
          aj.a("TapjoyConnect", stringBuilder4.append("SERIAL: deviceID: [").append(f).append("]").toString());
          aj.a("TapjoyConnect", "====================");
          if (f == null) {
            aj.b("TapjoyConnect", "SERIAL: Device id is null.");
            b1 = 1;
          } else if (f.length() == 0 || f.equals("000000000000000") || f.equals("0") || f.equals("unknown")) {
            aj.b("TapjoyConnect", "SERIAL: Device id is empty or an emulator.");
            b1 = 1;
          } else {
            f = f.toLowerCase();
            b1 = 0;
          } 
        } 
        if (b1) {
          StringBuffer stringBuffer = new StringBuffer();
          this();
          stringBuffer.append("EMULATOR");
          String str = sharedPreferences.getString("emulatorDeviceId", null);
          if (str != null && !str.equals("")) {
            f = str;
          } else {
            for (b1 = 0; b1 < 32; b1++)
              stringBuffer.append("1234567890abcdefghijklmnopqrstuvw".charAt((int)(Math.random() * 100.0D) % 30)); 
            f = stringBuffer.toString().toLowerCase();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("emulatorDeviceId", f);
            editor.commit();
          } 
        } 
      } catch (Exception exception) {}
      if (v.length() == 0)
        v = f; 
      g = an.b(f);
      try {
        if (Integer.parseInt(Build.VERSION.SDK) > 3) {
          u u = new u();
          this(a);
          StringBuilder stringBuilder = new StringBuilder();
          this();
          t = stringBuilder.append("").append(u.a()).toString();
          stringBuilder = new StringBuilder();
          this();
          u = stringBuilder.append("").append(u.b()).toString();
        } 
      } catch (Exception exception) {}
      try {
        WifiManager wifiManager = (WifiManager)a.getSystemService("wifi");
        if (wifiManager != null) {
          WifiInfo wifiInfo = wifiManager.getConnectionInfo();
          if (wifiInfo != null) {
            h = wifiInfo.getMacAddress();
            if (h != null && h.length() > 0) {
              h = h.toUpperCase();
              i = an.a(h);
            } 
          } 
        } 
      } catch (Exception exception) {}
      if (e("store_name") != null && e("store_name").length() > 0) {
        C = e("store_name");
        ArrayList arrayList = new ArrayList();
        this(Arrays.asList((E[])l.b));
        if (!arrayList.contains(C)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          Log.w("TapjoyConnect", stringBuilder.append("Warning -- undefined STORE_NAME: ").append(C).toString());
        } 
      } 
      String str2 = sharedPreferences.getString("InstallReferral", null);
      if (str2 != null && !str2.equals(""))
        F = str2; 
      E = a.getPackageName();
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder2.append("APP_ID = [").append(q).append("]").toString());
      stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder2.append("ANDROID_ID: [").append(e).append("]").toString());
      stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder2.append("CLIENT_PACKAGE = [").append(E).append("]").toString());
      stringBuilder2 = new StringBuilder();
      this();
      StringBuilder stringBuilder3 = stringBuilder2.append("deviceID: [").append(f).append("]");
      if (e("debug_device_id") != null && e("debug_device_id").length() > 0) {
        str1 = " *debug_device_id*";
      } else {
        str1 = "";
      } 
      aj.a("TapjoyConnect", stringBuilder3.append(str1).toString());
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("sha2DeviceID: [").append(g).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("serial_id: [").append(j).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("mac_address: [").append(h).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("sha1_mac_address: [").append(i).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("deviceName: [").append(k).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("deviceManufacturer: [").append(l).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("deviceType: [").append(m).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("libraryVersion: [").append(s).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("deviceOSVersion: [").append(n).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("COUNTRY_CODE: [").append(o).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("LANGUAGE_CODE: [").append(p).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("density: [").append(t).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("screen_layout: [").append(u).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("carrier_name: [").append(x).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("carrier_country_code: [").append(y).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("mobile_country_code: [").append(z).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("mobile_network_code: [").append(A).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("store_name: [").append(C).append("]").toString());
      stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyConnect", stringBuilder1.append("referralURL: [").append(F).append("]").toString());
      if (O != null) {
        aj.a("TapjoyConnect", "Connect Flags:");
        aj.a("TapjoyConnect", "--------------------");
        for (Map.Entry entry : O.entrySet()) {
          stringBuilder1 = new StringBuilder();
          this();
          aj.a("TapjoyConnect", stringBuilder1.append("key: ").append((String)entry.getKey()).append(", value: ").append(Uri.encode((String)entry.getValue())).toString());
          if (((String)entry.getKey()).equals("sha_2_udid") && !H.equals("connect")) {
            aj.c("TapjoyConnect", "WARNING -- only the Connect/Advertiser SDK can support sha_2_udid");
            O.remove("sha_2_udid");
          } 
        } 
      } 
    } catch (Exception exception) {
      aj.b("TapjoyConnect", "Error initializing Tapjoy parameters.  e=" + exception.toString());
    } 
  }
  
  public void b() {
    (new Thread(new j(this))).start();
  }
  
  public void d(String paramString) {
    // Byte code:
    //   0: ldc 'TapjoyConnect'
    //   2: new java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial <init> : ()V
    //   9: ldc_w 'actionComplete: '
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_1
    //   16: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual toString : ()Ljava/lang/String;
    //   22: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   25: new java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial <init> : ()V
    //   32: ldc_w 'app_id='
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: ldc_w '&'
    //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual toString : ()Ljava/lang/String;
    //   51: astore_1
    //   52: new java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial <init> : ()V
    //   59: aload_1
    //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokestatic n : ()Ljava/lang/String;
    //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual toString : ()Ljava/lang/String;
    //   72: astore #4
    //   74: ldc_w 'sha_2_udid'
    //   77: invokestatic e : (Ljava/lang/String;)Ljava/lang/String;
    //   80: ifnull -> 101
    //   83: aload #4
    //   85: astore_1
    //   86: ldc_w 'sha_2_udid'
    //   89: invokestatic e : (Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 'true'
    //   95: invokevirtual equals : (Ljava/lang/Object;)Z
    //   98: ifne -> 129
    //   101: new java/lang/StringBuilder
    //   104: dup
    //   105: invokespecial <init> : ()V
    //   108: aload #4
    //   110: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: ldc_w '&publisher_user_id='
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: invokestatic e : ()Ljava/lang/String;
    //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual toString : ()Ljava/lang/String;
    //   128: astore_1
    //   129: new java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial <init> : ()V
    //   136: aload_1
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc_w '&'
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual toString : ()Ljava/lang/String;
    //   149: astore_1
    //   150: invokestatic currentTimeMillis : ()J
    //   153: ldc2_w 1000
    //   156: ldiv
    //   157: lstore_2
    //   158: new java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial <init> : ()V
    //   165: aload_1
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc_w 'timestamp='
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: lload_2
    //   176: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   179: ldc_w '&'
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual toString : ()Ljava/lang/String;
    //   188: astore_1
    //   189: new java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial <init> : ()V
    //   196: aload_1
    //   197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: ldc_w 'verifier='
    //   203: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: lload_2
    //   207: invokestatic a : (J)Ljava/lang/String;
    //   210: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual toString : ()Ljava/lang/String;
    //   216: astore_1
    //   217: ldc 'TapjoyConnect'
    //   219: new java/lang/StringBuilder
    //   222: dup
    //   223: invokespecial <init> : ()V
    //   226: ldc_w 'PPA URL parameters: '
    //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: aload_1
    //   233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: invokevirtual toString : ()Ljava/lang/String;
    //   239: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   242: new java/lang/Thread
    //   245: dup
    //   246: new com/tapjoy/k
    //   249: dup
    //   250: aload_0
    //   251: aload_1
    //   252: invokespecial <init> : (Lcom/tapjoy/h;Ljava/lang/String;)V
    //   255: invokespecial <init> : (Ljava/lang/Runnable;)V
    //   258: invokevirtual start : ()V
    //   261: return
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */