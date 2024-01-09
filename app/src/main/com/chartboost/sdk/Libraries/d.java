package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {
    private static String a = null;
    private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");

    public static int a(int i, Context context) {
        return Math.round(i * b(context));
    }

    public static SharedPreferences a() {
        return Chartboost.sharedChartboost().getContext().getSharedPreferences("cbPrefs", 0);
    }

    public static String a(Map map) {
        String encode;
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            sb.append("?");
        }
        for (String str : map.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String str2 = (String) map.get(str);
            if (str != null) {
                try {
                    encode = URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("This method requires UTF-8 encoding support", e);
                }
            } else {
                encode = "";
            }
            sb.append(encode);
            sb.append("=");
            sb.append(str2 != null ? URLEncoder.encode(str2, "UTF-8") : "");
        }
        return sb.toString();
    }

    public static List a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    public static Map a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            } catch (Exception e) {
            }
        }
        return hashMap;
    }

    public static JSONArray a(List list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                jSONArray.put(list.get(i));
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    public static boolean a(Context context) {
        boolean z;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            int i = 0;
            boolean z2 = false;
            while (true) {
                try {
                    if (i < signatureArr.length) {
                        z = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(b);
                        if (z) {
                            break;
                        }
                        i++;
                        z2 = z;
                    } else {
                        z = z2;
                        break;
                    }
                } catch (Exception e) {
                    z = z2;
                }
            }
        } catch (Exception e2) {
            z = false;
        }
        return z | ((context.getApplicationInfo().flags & 2) != 0);
    }

    public static float b(int i, Context context) {
        return i * b(context);
    }

    public static float b(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String b() {
        if (c()) {
            return null;
        }
        if (a != null) {
            return a;
        }
        a = c.a();
        return a;
    }

    public static JSONObject b(Map map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (Exception e) {
            }
        }
        return jSONObject;
    }

    public static CBOrientation c(Context context) {
        boolean z;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int i = context.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        boolean z2 = defaultDisplay.getWidth() == defaultDisplay.getHeight() ? true : defaultDisplay.getWidth() < defaultDisplay.getHeight() ? true : true;
        if (z2) {
            z = true;
        } else if (z2) {
            z = false;
        } else {
            if (z2) {
                if (i == 1) {
                    z = true;
                } else if (i == 2) {
                    z = false;
                }
            }
            z = true;
        }
        if (rotation != 0 && rotation != 2) {
            z = !z;
        }
        if (z) {
            switch (rotation) {
                case 1:
                    return CBOrientation.LANDSCAPE_LEFT;
                case 2:
                    return CBOrientation.PORTRAIT_REVERSE;
                case 3:
                    return CBOrientation.LANDSCAPE_RIGHT;
                default:
                    return CBOrientation.PORTRAIT;
            }
        }
        switch (rotation) {
            case 1:
                return CBOrientation.PORTRAIT_LEFT;
            case 2:
                return CBOrientation.LANDSCAPE_REVERSE;
            case 3:
                return CBOrientation.PORTRAIT_RIGHT;
            default:
                return CBOrientation.LANDSCAPE;
        }
    }

    public static boolean c() {
        return a().getBoolean("cbIdentityTrackingDisabled", false);
    }

    public static boolean d() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
