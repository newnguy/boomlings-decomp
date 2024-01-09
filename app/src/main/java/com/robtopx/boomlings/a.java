package com.robtopx.boomlings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

/* loaded from: classes.dex */
public class a {
    public static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("apprater", 0);
        if (sharedPreferences.getBoolean("dontshowagain", false)) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong("launch_count", sharedPreferences.getLong("launch_count", 0L) + 1);
        if (Long.valueOf(sharedPreferences.getLong("date_firstlaunch", 0L)).longValue() == 0) {
            edit.putLong("date_firstlaunch", Long.valueOf(System.currentTimeMillis()).longValue());
        }
        edit.commit();
    }

    public static void a(Context context, SharedPreferences.Editor editor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thanks for playing!");
        builder.setMessage("Please take some time to rate Boomlings on Google Play. We would love to hear what you think!").setCancelable(false).setPositiveButton("Rate Boomlings!", new b(editor, context)).setNegativeButton("No Thanks", new c(editor)).setNeutralButton("Remind me later", new d(editor));
        builder.create().show();
    }

    public static void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("apprater", 0);
        if (sharedPreferences.getBoolean("dontshowagain", false)) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        long j = sharedPreferences.getLong("launch_count", 0L);
        Long valueOf = Long.valueOf(sharedPreferences.getLong("date_firstlaunch", 0L));
        if (j < 5 || System.currentTimeMillis() < valueOf.longValue() + 172800000) {
            return;
        }
        a(context, edit);
    }

    public static void c(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("apprater", 0).edit();
        edit.putBoolean("dontshowagain", true);
        edit.commit();
    }

    public static void d(Context context) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
    }
}
