package com.google.ads;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;
import java.util.Date;

class i implements Runnable {
  private final WeakReference a;
  
  private final SharedPreferences.Editor b;
  
  public i(Activity paramActivity) {
    this(paramActivity, null);
  }
  
  i(Activity paramActivity, SharedPreferences.Editor paramEditor) {
    this.a = new WeakReference<Activity>(paramActivity);
    this.b = paramEditor;
  }
  
  private SharedPreferences.Editor a(Activity paramActivity) {
    return (this.b == null) ? PreferenceManager.getDefaultSharedPreferences(paramActivity.getApplicationContext()).edit() : this.b;
  }
  
  public void run() {
    try {
      Activity activity = this.a.get();
      if (activity == null) {
        b.a("Activity was null while making a doritos cookie request.");
        return;
      } 
      Cursor cursor = activity.getContentResolver().query(af.b, af.d, null, null, null);
      if (cursor != null && cursor.moveToFirst() && (cursor.getColumnNames()).length > 0) {
        String str = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
      } else {
        b.a("Google+ app not installed, not storing doritos cookie");
        cursor = null;
      } 
      SharedPreferences.Editor editor = a(activity);
      if (!TextUtils.isEmpty((CharSequence)cursor)) {
        editor.putString("drt", (String)cursor);
        Date date = new Date();
        this();
        editor.putLong("drt_ts", date.getTime());
      } else {
        editor.putString("drt", "");
        editor.putLong("drt_ts", 0L);
      } 
      editor.commit();
    } catch (Throwable throwable) {
      b.b("An unknown error occurred while sending a doritos request.", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */