package net.robotmedia.billing.c;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class e {
    public static int a;
    private static Method b;
    private static final Class[] c = {IntentSender.class, Intent.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};

    static {
        b();
    }

    public static void a(Activity activity, IntentSender intentSender, Intent intent) {
        if (b != null) {
            try {
                b.invoke(activity, intentSender, intent, 0, 0, 0);
            } catch (Exception e) {
                Log.e(e.class.getSimpleName(), "startIntentSender", e);
            }
        }
    }

    public static boolean a() {
        return b != null;
    }

    private static void b() {
        try {
            a = Service.class.getField("START_NOT_STICKY").getInt(null);
        } catch (Exception e) {
            a = 2;
        }
        try {
            b = Activity.class.getMethod("startIntentSender", c);
        } catch (NoSuchMethodException e2) {
            b = null;
        } catch (SecurityException e3) {
            b = null;
        }
    }
}
