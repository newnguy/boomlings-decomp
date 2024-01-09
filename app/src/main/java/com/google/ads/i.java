package com.google.ads;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i implements Runnable {
    private final WeakReference a;
    private final SharedPreferences.Editor b;

    public i(Activity activity) {
        this(activity, null);
    }

    i(Activity activity, SharedPreferences.Editor editor) {
        this.a = new WeakReference(activity);
        this.b = editor;
    }

    private SharedPreferences.Editor a(Activity activity) {
        return this.b == null ? PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).edit() : this.b;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        try {
            Activity activity = (Activity) this.a.get();
            if (activity == null) {
                com.google.ads.util.b.a("Activity was null while making a doritos cookie request.");
                return;
            }
            Cursor query = activity.getContentResolver().query(af.b, af.d, null, null, null);
            if (query == null || !query.moveToFirst() || query.getColumnNames().length <= 0) {
                com.google.ads.util.b.a("Google+ app not installed, not storing doritos cookie");
                str = null;
            } else {
                str = query.getString(query.getColumnIndex(query.getColumnName(0)));
            }
            SharedPreferences.Editor a = a(activity);
            if (TextUtils.isEmpty(str)) {
                a.putString("drt", "");
                a.putLong("drt_ts", 0L);
            } else {
                a.putString("drt", str);
                a.putLong("drt_ts", new Date().getTime());
            }
            a.commit();
        } catch (Throwable th) {
            com.google.ads.util.b.b("An unknown error occurred while sending a doritos request.", th);
        }
    }
}
