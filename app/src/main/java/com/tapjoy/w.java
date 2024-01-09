package com.tapjoy;

import android.content.Context;

/* loaded from: classes.dex */
public class w {
    private static am a = null;
    private Context b;

    public w(Context context) {
        this.b = context;
        a = new am();
    }

    public void a() {
        a(2, null);
    }

    public void a(int i, String str) {
        aj.a("Event", "sendEvent type: " + i);
        String str2 = (h.c() + "&publisher_user_id=" + h.e()) + "&event_type_id=" + i;
        if (str != null && str.length() > 0) {
            str2 = str2 + "&" + str;
        }
        new Thread(new x(this, str2)).start();
    }
}
