package org.cocos2dx.lib;

import android.app.AlertDialog;

/* loaded from: classes.dex */
public class u implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity cocos2dxActivity;
        cocos2dxActivity = Cocos2dxActivity.me;
        AlertDialog.Builder builder = new AlertDialog.Builder(cocos2dxActivity);
        builder.setTitle("Loading Failed");
        builder.setMessage("There was a problem loading your save file. Please contact support to restore your progress. Sorry for the inconvenience.").setCancelable(false).setPositiveButton("Email Support", new v(this)).setNegativeButton("Ok", new w(this));
        builder.create().show();
    }
}
