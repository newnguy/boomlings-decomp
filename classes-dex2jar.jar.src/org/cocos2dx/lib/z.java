package org.cocos2dx.lib;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

class z implements Runnable {
  private final String a;
  
  private final String b;
  
  private final String c;
  
  z(String paramString1, String paramString2, String paramString3) {}
  
  public void run() {
    Intent intent = new Intent("android.intent.action.SEND");
    intent.setType("message/rfc822");
    intent.putExtra("android.intent.extra.EMAIL", new String[] { this.a });
    intent.putExtra("android.intent.extra.SUBJECT", this.b);
    intent.putExtra("android.intent.extra.TEXT", this.c);
    try {
      Cocos2dxActivity.access$1().startActivity(Intent.createChooser(intent, "Send mail..."));
    } catch (ActivityNotFoundException activityNotFoundException) {
      Toast.makeText((Context)Cocos2dxActivity.access$1(), "There are no email clients installed.", 0).show();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */