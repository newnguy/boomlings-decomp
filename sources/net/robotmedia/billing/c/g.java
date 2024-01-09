package net.robotmedia.billing.c;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.security.SecureRandom;
import java.util.HashSet;

/* loaded from: classes.dex */
public class g {
    private static HashSet a = new HashSet();
    private static final SecureRandom b = new SecureRandom();
    private static final String c = g.class.getSimpleName();
    private static a d = null;

    public static long a() {
        long nextLong = b.nextLong();
        a.add(Long.valueOf(nextLong));
        return nextLong;
    }

    public static String a(Context context, byte[] bArr, String str) {
        return a(context, bArr).a(str);
    }

    private static a a(Context context, byte[] bArr) {
        if (d == null) {
            String a2 = f.a(context);
            d = new a(bArr, String.valueOf(a2) + Settings.Secure.getString(context.getContentResolver(), "android_id") + context.getPackageName());
        }
        return d;
    }

    public static boolean a(long j) {
        return a.contains(Long.valueOf(j));
    }

    public static String b(Context context, byte[] bArr, String str) {
        try {
            return a(context, bArr).b(str);
        } catch (b e) {
            Log.w(c, "Invalid obfuscated data or key");
            return null;
        }
    }

    public static void b(long j) {
        a.remove(Long.valueOf(j));
    }
}
