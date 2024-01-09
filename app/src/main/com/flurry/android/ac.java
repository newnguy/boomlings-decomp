package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ac {
    ac() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Context context, int i) {
        return Math.round(context.getResources().getDisplayMetrics().density * i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static bl a(bo boVar, String str) {
        long b = boVar.b();
        boVar.d();
        bl blVar = new bl(b, str);
        boVar.a(blVar);
        return blVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        return str == null ? "" : str.length() > 255 ? str.substring(0, 255) : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HttpResponse a(be beVar, String str, int i, int i2, boolean z) {
        try {
            HttpGet httpGet = new HttpGet(str);
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
            basicHttpParams.setParameter("http.protocol.handle-redirects", Boolean.valueOf(z));
            return beVar.a(basicHttpParams).execute(httpGet);
        } catch (UnknownHostException e) {
            bm.a("FlurryAgent", "Unknown host: " + e.getMessage());
            return null;
        } catch (Exception e2) {
            bm.a("FlurryAgent", "Failed to hit URL: " + str, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(long j) {
        return System.currentTimeMillis() <= j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context, String str) {
        Intent data = new Intent("android.intent.action.VIEW").setData(Uri.parse(str));
        if (a(context, data)) {
            bm.a("FlurryAgent", "Launching intent for " + str);
            context.startActivity(data);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private static int b(Context context, int i) {
        return Math.round(i / context.getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            bm.d("FlurryAgent", "Cannot encode '" + str + "'");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(Context context) {
        return b(context, a(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] c(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes(), 0, str.length());
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            bm.b("FlurryAgent", "Unsupported SHA1: " + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(Context context) {
        return b(context, b(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d(String str) {
        return str.replace("'", "\\'");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map e(String str) {
        HashMap hashMap = new HashMap();
        if (str != null && !str.isEmpty()) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (!split[0].equals("event")) {
                    hashMap.put(f(split[0]), f(split[1]));
                }
            }
        }
        return hashMap;
    }

    private static String f(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            bm.d("FlurryAgent", "Cannot decode '" + str + "'");
            return "";
        }
    }
}
