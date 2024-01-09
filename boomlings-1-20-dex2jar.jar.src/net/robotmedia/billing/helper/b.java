package net.robotmedia.billing.helper;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import net.robotmedia.billing.a;
import net.robotmedia.billing.n;

public abstract class b implements n {
  protected Activity b;
  
  public b(Activity paramActivity) {
    this.b = paramActivity;
  }
  
  public void a() {
    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences((Context)this.b).edit();
    editor.putBoolean("net.robotmedia.billing.transactionsRestored", true);
    editor.commit();
  }
  
  public void a(String paramString, PendingIntent paramPendingIntent) {
    a.a(this.b, paramPendingIntent, null);
  }
  
  public boolean b() {
    return PreferenceManager.getDefaultSharedPreferences((Context)this.b).getBoolean("net.robotmedia.billing.transactionsRestored", false);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\helper\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */