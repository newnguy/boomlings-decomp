package org.cocos2dx.lib;

import android.content.Intent;
import android.net.Uri;

class s implements Runnable {
  private final String a;
  
  s(String paramString) {}
  
  public void run() {
    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
    Cocos2dxActivity.access$1().startActivity(intent);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */