package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Message;

class i extends Handler {
  final Cocos2dxActivity a;
  
  i(Cocos2dxActivity paramCocos2dxActivity) {}
  
  public void handleMessage(Message paramMessage) {
    switch (paramMessage.what) {
      default:
        return;
      case 1:
        break;
    } 
    Cocos2dxActivity.access$0(this.a, ((ar)paramMessage.obj).a, ((ar)paramMessage.obj).b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */