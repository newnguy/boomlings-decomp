package com.robtopx.boomlings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class a {
  public static void a(Context paramContext) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("apprater", 0);
    if (!sharedPreferences.getBoolean("dontshowagain", false)) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putLong("launch_count", sharedPreferences.getLong("launch_count", 0L) + 1L);
      if (Long.valueOf(sharedPreferences.getLong("date_firstlaunch", 0L)).longValue() == 0L)
        editor.putLong("date_firstlaunch", Long.valueOf(System.currentTimeMillis()).longValue()); 
      editor.commit();
    } 
  }
  
  public static void a(Context paramContext, SharedPreferences.Editor paramEditor) {
    AlertDialog.Builder builder = new AlertDialog.Builder(paramContext);
    builder.setTitle("Thanks for playing!");
    builder.setMessage("Please take some time to rate Boomlings on Google Play. We would love to hear what you think!").setCancelable(false).setPositiveButton("Rate Boomlings!", new b(paramEditor, paramContext)).setNegativeButton("No Thanks", new c(paramEditor)).setNeutralButton("Remind me later", new d(paramEditor));
    builder.create().show();
  }
  
  public static void b(Context paramContext) {
    SharedPreferences sharedPreferences = paramContext.getSharedPreferences("apprater", 0);
    if (!sharedPreferences.getBoolean("dontshowagain", false)) {
      SharedPreferences.Editor editor = sharedPreferences.edit();
      long l1 = sharedPreferences.getLong("launch_count", 0L);
      long l2 = sharedPreferences.getLong("date_firstlaunch", 0L);
      if (l1 >= 5L && System.currentTimeMillis() >= Long.valueOf(l2).longValue() + 172800000L)
        a(paramContext, editor); 
    } 
  }
  
  public static void c(Context paramContext) {
    SharedPreferences.Editor editor = paramContext.getSharedPreferences("apprater", 0).edit();
    editor.putBoolean("dontshowagain", true);
    editor.commit();
  }
  
  public static void d(Context paramContext) {
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName())));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\robtopx\boomlings\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */