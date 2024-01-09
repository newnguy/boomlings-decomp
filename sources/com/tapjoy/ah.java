package com.tapjoy;

import android.os.Build;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class ah {
    public String a() {
        String str;
        Exception e;
        try {
            Field declaredField = Class.forName("android.os.Build").getDeclaredField("SERIAL");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            str = declaredField.get(Build.class).toString();
            try {
                aj.a("TapjoyHardwareUtil", "serial: " + str);
            } catch (Exception e2) {
                e = e2;
                aj.b("TapjoyHardwareUtil", e.toString());
                return str;
            }
        } catch (Exception e3) {
            str = null;
            e = e3;
        }
        return str;
    }
}
