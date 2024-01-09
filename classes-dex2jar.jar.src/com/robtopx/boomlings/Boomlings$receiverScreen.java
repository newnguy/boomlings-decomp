package com.robtopx.boomlings;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.cocos2dx.lib.JniToCpp;

public class Boomlings$receiverScreen extends BroadcastReceiver {
  final Boomlings a;
  
  public Boomlings$receiverScreen(Boomlings paramBoomlings) {}
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent.getAction().equals("android.intent.action.SCREEN_ON")) {
      Log.d("TAG", "ACTION_SCREEN_ON");
      if (!((KeyguardManager)this.a.getSystemService("keyguard")).inKeyguardRestrictedInputMode())
        Boomlings.shouldResumeSound_ = true; 
      if (!Boomlings.isPaused_ && Boomlings.shouldResumeSound_)
        JniToCpp.resumeSound(); 
    } 
    if (paramIntent.getAction().equals("android.intent.action.SCREEN_OFF"))
      Boomlings.shouldResumeSound_ = false; 
    if (paramIntent.getAction().equals("android.intent.action.USER_PRESENT")) {
      Boomlings.shouldResumeSound_ = true;
      if (!Boomlings.isPaused_)
        JniToCpp.resumeSound(); 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\robtopx\boomlings\Boomlings$receiverScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */