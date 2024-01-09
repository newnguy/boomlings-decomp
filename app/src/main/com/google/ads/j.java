package com.google.ads;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements Runnable {
    private final WeakReference a;
    private final WebView b;
    private final String c;

    public j(Activity activity, WebView webView, String str) {
        this.a = new WeakReference(activity);
        this.c = str;
        this.b = webView;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        try {
            Uri withAppendedPath = Uri.withAppendedPath(af.a, this.c);
            Activity activity = (Activity) this.a.get();
            if (activity == null) {
                com.google.ads.util.b.a("Activity was null while getting the +1 button state.");
                return;
            }
            Cursor query = activity.getContentResolver().query(withAppendedPath, af.c, null, null, null);
            if (query == null || !query.moveToFirst()) {
                com.google.ads.util.b.a("Google+ app not installed, showing ad as not +1'd");
                z = false;
            } else {
                z = query.getInt(query.getColumnIndex("has_plus1")) == 1;
            }
            this.b.post(new k(this.b, z));
        } catch (Throwable th) {
            com.google.ads.util.b.b("An unknown error occurred while updating the +1 state.", th);
        }
    }
}
