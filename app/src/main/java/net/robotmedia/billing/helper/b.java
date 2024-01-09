package net.robotmedia.billing.helper;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import net.robotmedia.billing.n;

/* loaded from: classes.dex */
public abstract class b implements n {
    protected Activity b;

    public b(Activity activity) {
        this.b = activity;
    }

    @Override // net.robotmedia.billing.n
    public void a() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this.b).edit();
        edit.putBoolean("net.robotmedia.billing.transactionsRestored", true);
        edit.commit();
    }

    @Override // net.robotmedia.billing.n
    public void a(String str, PendingIntent pendingIntent) {
        net.robotmedia.billing.a.a(this.b, pendingIntent, (Intent) null);
    }

    public boolean b() {
        return PreferenceManager.getDefaultSharedPreferences(this.b).getBoolean("net.robotmedia.billing.transactionsRestored", false);
    }
}
