package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.Context;

class u implements Runnable {
  public void run() {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)Cocos2dxActivity.access$1());
    builder.setTitle("Loading Failed");
    builder.setMessage("There was a problem loading your save file. Please contact support to restore your progress. Sorry for the inconvenience.").setCancelable(false).setPositiveButton("Email Support", new v(this)).setNegativeButton("Ok", new w(this));
    builder.create().show();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\li\\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */