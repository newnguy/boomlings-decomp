package com.tapjoy;

import android.util.Log;

/* loaded from: classes.dex */
public class aj {
    private static boolean a = false;

    public static void a(String str, String str2) {
        if (a) {
            if (str2.length() <= 4096) {
                Log.i(str, str2);
                return;
            }
            for (int i = 0; i <= str2.length() / 4096; i++) {
                int i2 = i * 4096;
                int i3 = (i + 1) * 4096;
                if (i3 > str2.length()) {
                    i3 = str2.length();
                }
                Log.i(str, str2.substring(i2, i3));
            }
        }
    }

    public static void a(boolean z) {
        Log.i("TapjoyLog", "enableLogging: " + z);
        a = z;
    }

    public static void b(String str, String str2) {
        if (a) {
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            Log.w(str, str2);
        }
    }
}
