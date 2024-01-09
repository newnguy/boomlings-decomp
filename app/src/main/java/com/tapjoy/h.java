package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
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

/* loaded from: classes.dex */
public class h {
    private long M = 0;
    private Timer N = null;
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
    private static String A = "";
    private static String B = "";
    private static String C = "";
    private static String D = "";
    private static String E = "";
    private static String F = "";
    private static String G = "native";
    private static String H = "";
    private static boolean I = false;
    private static String J = "";
    private static float K = 1.0f;
    private static String L = null;
    private static Hashtable O = null;
    private static String P = "";

    public h(Context context) {
        a = context;
        c = new am();
        o();
        aj.a("TapjoyConnect", "URL parameters: " + c());
        b();
        if (e("user_id") == null || e("user_id").length() <= 0) {
            return;
        }
        aj.a("TapjoyConnect", "Setting userID to: " + e("user_id"));
        b(e("user_id"));
    }

    public static h a() {
        return b;
    }

    public static String a(long j2) {
        try {
            return an.b(q + ":" + f + ":" + j2 + ":" + D);
        } catch (Exception e2) {
            aj.b("TapjoyConnect", "getVerifier ERROR: " + e2.toString());
            return "";
        }
    }

    public static String a(long j2, String str) {
        try {
            return an.b(q + ":" + f + ":" + j2 + ":" + D + ":" + str);
        } catch (Exception e2) {
            aj.b("TapjoyConnect", "getVerifier ERROR: " + e2.toString());
            return "";
        }
    }

    public static void a(int i2) {
        SharedPreferences.Editor edit = a.getSharedPreferences("tjcPrefrences", 0).edit();
        edit.putInt("last_tap_points", i2);
        edit.commit();
    }

    public static void a(Context context, String str, String str2, Hashtable hashtable, m mVar) {
        q = str;
        D = str2;
        O = hashtable;
        d = mVar;
        b = new h(context);
    }

    public static void a(String str) {
        H = str;
    }

    public static void a(boolean z2) {
        I = z2;
    }

    public static void b(String str) {
        v = str;
        aj.a("TapjoyConnect", "URL parameters: " + c());
        new Thread(new i()).start();
    }

    public static String c() {
        String str = d() + "&";
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        return (str + "timestamp=" + currentTimeMillis + "&") + "verifier=" + a(currentTimeMillis);
    }

    public static void c(String str) {
        J = str;
    }

    public static String d() {
        return ("app_id=" + Uri.encode(q) + "&") + n();
    }

    public static String e() {
        return v;
    }

    public static String e(String str) {
        String str2 = O != null ? (String) O.get(str) : "";
        return str2 == null ? "" : str2;
    }

    public static String f() {
        String str = "";
        if (!I) {
            str = "hide_videos=true";
        } else if (J.length() > 0) {
            str = "video_offer_ids=" + J;
        }
        aj.a("TapjoyConnect", "video parameters: " + str);
        return str;
    }

    public static String g() {
        String str = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) a.getSystemService("connectivity");
            if (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null) {
                switch (connectivityManager.getActiveNetworkInfo().getType()) {
                    case 1:
                    case 6:
                        str = "wifi";
                        break;
                    default:
                        str = "mobile";
                        break;
                }
                aj.a("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                aj.a("TapjoyConnect", "connection_type: " + str);
            }
            return str;
        } catch (Exception e2) {
            String str2 = str;
            aj.b("TapjoyConnect", "getConnectionType error: " + e2.toString());
            return str2;
        }
    }

    public static boolean g(String str) {
        Document c2 = an.c(str);
        if (c2 != null) {
            String a2 = an.a(c2.getElementsByTagName("PackageNames"));
            if (a2 != null && a2.length() > 0) {
                Vector vector = new Vector();
                int i2 = 0;
                while (true) {
                    int indexOf = a2.indexOf(44, i2);
                    if (indexOf == -1) {
                        break;
                    }
                    aj.a("TapjoyConnect", "parse: " + a2.substring(i2, indexOf).trim());
                    vector.add(a2.substring(i2, indexOf).trim());
                    i2 = indexOf + 1;
                }
                aj.a("TapjoyConnect", "parse: " + a2.substring(i2).trim());
                vector.add(a2.substring(i2).trim());
                P = "";
                for (ApplicationInfo applicationInfo : a.getPackageManager().getInstalledApplications(0)) {
                    if ((applicationInfo.flags & 1) != 1 && vector.contains(applicationInfo.packageName)) {
                        aj.a("TapjoyConnect", "MATCH: installed packageName: " + applicationInfo.packageName);
                        if (P.length() > 0) {
                            P += ",";
                        }
                        P += applicationInfo.packageName;
                    }
                }
            }
            String a3 = an.a(c2.getElementsByTagName("Success"));
            if (a3 == null || a3.equals("true")) {
            }
        }
        return true;
    }

    public static String h() {
        return E;
    }

    public boolean h(String str) {
        Document c2 = an.c(str);
        if (c2 != null) {
            String a2 = an.a(c2.getElementsByTagName("Success"));
            if (a2 != null && a2.equals("true")) {
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
        String str = "android_id=" + e + "&";
        String str2 = (e("sha_2_udid") == null || !e("sha_2_udid").equals("true")) ? str + "udid=" + Uri.encode(f) + "&" : str + "sha2_udid=" + Uri.encode(g) + "&";
        if (h != null && h.length() > 0) {
            str2 = str2 + "sha1_mac_address=" + Uri.encode(i) + "&";
        }
        if (j != null && j.length() > 0) {
            str2 = str2 + "serial_id=" + Uri.encode(j) + "&";
        }
        String str3 = (((((((((str2 + "device_name=" + Uri.encode(k) + "&") + "device_manufacturer=" + Uri.encode(l) + "&") + "device_type=" + Uri.encode(m) + "&") + "os_version=" + Uri.encode(n) + "&") + "country_code=" + Uri.encode(o) + "&") + "language_code=" + Uri.encode(p) + "&") + "app_version=" + Uri.encode(r) + "&") + "library_version=" + Uri.encode(s) + "&") + "platform=" + Uri.encode(w) + "&") + "display_multiplier=" + Uri.encode(Float.toString(K));
        if (x.length() > 0) {
            str3 = (str3 + "&") + "carrier_name=" + Uri.encode(x);
        }
        if (y.length() > 0) {
            str3 = (str3 + "&") + "carrier_country_code=" + Uri.encode(y);
        }
        if (z.length() > 0) {
            str3 = (str3 + "&") + "mobile_country_code=" + Uri.encode(z);
        }
        if (A.length() > 0) {
            str3 = (str3 + "&") + "mobile_network_code=" + Uri.encode(A);
        }
        if (t.length() > 0 && u.length() > 0) {
            str3 = ((str3 + "&") + "screen_density=" + Uri.encode(t) + "&") + "screen_layout_size=" + Uri.encode(u);
        }
        B = g();
        if (B.length() > 0) {
            str3 = (str3 + "&") + "connection_type=" + Uri.encode(B);
        }
        if (G.length() > 0) {
            str3 = (str3 + "&") + "plugin=" + Uri.encode(G);
        }
        if (H.length() > 0) {
            str3 = (str3 + "&") + "sdk_type=" + Uri.encode(H);
        }
        if (C.length() > 0) {
            return (str3 + "&") + "store_name=" + Uri.encode(C);
        }
        return str3;
    }

    private void o() {
        WifiInfo connectionInfo;
        boolean z2;
        String[] strArr;
        String obj;
        PackageManager packageManager = a.getPackageManager();
        try {
            if (O == null) {
                O = new Hashtable();
            }
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.getPackageName(), 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    aj.a("TapjoyConnect", "No metadata present.");
                } else {
                    for (String str : l.a) {
                        Object obj2 = applicationInfo.metaData.get("tapjoy." + str);
                        if (obj2 != null && (obj = obj2.toString()) != null) {
                            aj.a("TapjoyConnect", "Found manifest flag: " + str + ", " + obj);
                            O.put(str, obj);
                        }
                    }
                    aj.a("TapjoyConnect", "Metadata successfully loaded");
                }
            }
        } catch (Exception e2) {
            aj.b("TapjoyConnect", "Error reading manifest meta-data: " + e2.toString());
        }
        if (e("enable_logging") != null && e("enable_logging").equals("true")) {
            aj.a(true);
        }
        try {
            e = Settings.Secure.getString(a.getContentResolver(), "android_id");
            r = packageManager.getPackageInfo(a.getPackageName(), 0).versionName;
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
                TelephonyManager telephonyManager = (TelephonyManager) a.getSystemService("phone");
                if (telephonyManager != null) {
                    if (e("debug_device_id") == null || e("debug_device_id").length() <= 0) {
                        f = telephonyManager.getDeviceId();
                    } else {
                        f = e("debug_device_id");
                    }
                    x = telephonyManager.getNetworkOperatorName();
                    y = telephonyManager.getNetworkCountryIso();
                    if (telephonyManager.getNetworkOperator() != null && (telephonyManager.getNetworkOperator().length() == 5 || telephonyManager.getNetworkOperator().length() == 6)) {
                        z = telephonyManager.getNetworkOperator().substring(0, 3);
                        A = telephonyManager.getNetworkOperator().substring(3);
                    }
                }
                aj.a("TapjoyConnect", "deviceID: " + f);
                if (f == null) {
                    aj.b("TapjoyConnect", "Device id is null.");
                    z2 = true;
                } else if (f.length() == 0 || f.equals("000000000000000") || f.equals("0")) {
                    aj.b("TapjoyConnect", "Device id is empty or an emulator.");
                    z2 = true;
                } else {
                    f = f.toLowerCase();
                    z2 = false;
                }
                aj.a("TapjoyConnect", "ANDROID SDK VERSION: " + Build.VERSION.SDK);
                if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
                    aj.a("TapjoyConnect", "TRYING TO GET SERIAL OF 2.3+ DEVICE...");
                    j = new ah().a();
                    if (z2) {
                        f = j;
                    }
                    aj.a("TapjoyConnect", "====================");
                    aj.a("TapjoyConnect", "SERIAL: deviceID: [" + f + "]");
                    aj.a("TapjoyConnect", "====================");
                    if (f == null) {
                        aj.b("TapjoyConnect", "SERIAL: Device id is null.");
                        z2 = true;
                    } else if (f.length() == 0 || f.equals("000000000000000") || f.equals("0") || f.equals("unknown")) {
                        aj.b("TapjoyConnect", "SERIAL: Device id is empty or an emulator.");
                        z2 = true;
                    } else {
                        f = f.toLowerCase();
                        z2 = false;
                    }
                }
                if (z2) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("EMULATOR");
                    String string = sharedPreferences.getString("emulatorDeviceId", null);
                    if (string == null || string.equals("")) {
                        for (int i2 = 0; i2 < 32; i2++) {
                            stringBuffer.append("1234567890abcdefghijklmnopqrstuvw".charAt(((int) (Math.random() * 100.0d)) % 30));
                        }
                        f = stringBuffer.toString().toLowerCase();
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("emulatorDeviceId", f);
                        edit.commit();
                    } else {
                        f = string;
                    }
                }
            } catch (Exception e3) {
                aj.b("TapjoyConnect", "Error getting deviceID. e: " + e3.toString());
                f = null;
            }
            if (v.length() == 0) {
                v = f;
            }
            g = an.b(f);
            try {
                if (Integer.parseInt(Build.VERSION.SDK) > 3) {
                    u uVar = new u(a);
                    t = "" + uVar.a();
                    u = "" + uVar.b();
                }
            } catch (Exception e4) {
                aj.b("TapjoyConnect", "Error getting screen density/dimensions/layout: " + e4.toString());
            }
            try {
                WifiManager wifiManager = (WifiManager) a.getSystemService("wifi");
                if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    h = connectionInfo.getMacAddress();
                    if (h != null && h.length() > 0) {
                        h = h.toUpperCase();
                        i = an.a(h);
                    }
                }
            } catch (Exception e5) {
                aj.b("TapjoyConnect", "Error getting device mac address: " + e5.toString());
            }
            if (e("store_name") != null && e("store_name").length() > 0) {
                C = e("store_name");
                if (!new ArrayList(Arrays.asList(l.b)).contains(C)) {
                    Log.w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + C);
                }
            }
            String string2 = sharedPreferences.getString("InstallReferral", null);
            if (string2 != null && !string2.equals("")) {
                F = string2;
            }
            E = a.getPackageName();
            aj.a("TapjoyConnect", "APP_ID = [" + q + "]");
            aj.a("TapjoyConnect", "ANDROID_ID: [" + e + "]");
            aj.a("TapjoyConnect", "CLIENT_PACKAGE = [" + E + "]");
            aj.a("TapjoyConnect", "deviceID: [" + f + "]" + ((e("debug_device_id") == null || e("debug_device_id").length() <= 0) ? "" : " *debug_device_id*"));
            aj.a("TapjoyConnect", "sha2DeviceID: [" + g + "]");
            aj.a("TapjoyConnect", "serial_id: [" + j + "]");
            aj.a("TapjoyConnect", "mac_address: [" + h + "]");
            aj.a("TapjoyConnect", "sha1_mac_address: [" + i + "]");
            aj.a("TapjoyConnect", "deviceName: [" + k + "]");
            aj.a("TapjoyConnect", "deviceManufacturer: [" + l + "]");
            aj.a("TapjoyConnect", "deviceType: [" + m + "]");
            aj.a("TapjoyConnect", "libraryVersion: [" + s + "]");
            aj.a("TapjoyConnect", "deviceOSVersion: [" + n + "]");
            aj.a("TapjoyConnect", "COUNTRY_CODE: [" + o + "]");
            aj.a("TapjoyConnect", "LANGUAGE_CODE: [" + p + "]");
            aj.a("TapjoyConnect", "density: [" + t + "]");
            aj.a("TapjoyConnect", "screen_layout: [" + u + "]");
            aj.a("TapjoyConnect", "carrier_name: [" + x + "]");
            aj.a("TapjoyConnect", "carrier_country_code: [" + y + "]");
            aj.a("TapjoyConnect", "mobile_country_code: [" + z + "]");
            aj.a("TapjoyConnect", "mobile_network_code: [" + A + "]");
            aj.a("TapjoyConnect", "store_name: [" + C + "]");
            aj.a("TapjoyConnect", "referralURL: [" + F + "]");
            if (O != null) {
                aj.a("TapjoyConnect", "Connect Flags:");
                aj.a("TapjoyConnect", "--------------------");
                for (Map.Entry entry : O.entrySet()) {
                    aj.a("TapjoyConnect", "key: " + ((String) entry.getKey()) + ", value: " + Uri.encode((String) entry.getValue()));
                    if (((String) entry.getKey()).equals("sha_2_udid") && !H.equals("connect")) {
                        aj.c("TapjoyConnect", "WARNING -- only the Connect/Advertiser SDK can support sha_2_udid");
                        O.remove("sha_2_udid");
                    }
                }
            }
        } catch (Exception e6) {
            aj.b("TapjoyConnect", "Error initializing Tapjoy parameters.  e=" + e6.toString());
        }
    }

    public void b() {
        new Thread(new j(this)).start();
    }

    public void d(String str) {
        aj.a("TapjoyConnect", "actionComplete: " + str);
        String str2 = ("app_id=" + str + "&") + n();
        if (e("sha_2_udid") == null || !e("sha_2_udid").equals("true")) {
            str2 = str2 + "&publisher_user_id=" + e();
        }
        String str3 = str2 + "&";
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String str4 = (str3 + "timestamp=" + currentTimeMillis + "&") + "verifier=" + a(currentTimeMillis);
        aj.a("TapjoyConnect", "PPA URL parameters: " + str4);
        new Thread(new k(this, str4)).start();
    }
}
