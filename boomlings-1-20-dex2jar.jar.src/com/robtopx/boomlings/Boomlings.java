package com.robtopx.boomlings;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.flurry.android.FlurryAgent;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxEditText;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxRenderer;

public class Boomlings extends Cocos2dxActivity {
  public static boolean blockBackButton_;
  
  public static boolean focusState_;
  
  public static boolean isPaused_;
  
  public static boolean keyboardActive_;
  
  private static Boomlings me = null;
  
  public static BroadcastReceiver receiver_;
  
  public static boolean shouldResumeSound_;
  
  public Cocos2dxEditText edittext = null;
  
  private Cocos2dxGLSurfaceView mGLView;
  
  static {
    keyboardActive_ = false;
    blockBackButton_ = false;
    focusState_ = true;
    shouldResumeSound_ = true;
    isPaused_ = false;
    System.loadLibrary("game");
  }
  
  private boolean detectOpenGLES20() {
    return ((((ActivityManager)getSystemService("activity")).getDeviceConfigurationInfo()).reqGlEsVersion >= 131072);
  }
  
  public static void onToggleKeyboard() {
    me.runOnUiThread(new e());
  }
  
  public static void setBlockBackButton(boolean paramBoolean) {
    blockBackButton_ = paramBoolean;
  }
  
  public static void setKeyboardState(boolean paramBoolean) {
    keyboardActive_ = paramBoolean;
  }
  
  public static boolean shouldResumeSound() {
    return shouldResumeSound_;
  }
  
  public static void showDialog(String paramString1, String paramString2, String paramString3) {
    me.runOnUiThread(new f(paramString1, paramString2, paramString3));
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    if (detectOpenGLES20()) {
      setPackageName(getApplication().getPackageName());
      ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
      FrameLayout frameLayout = new FrameLayout((Context)this);
      frameLayout.setLayoutParams(layoutParams);
      layoutParams = new ViewGroup.LayoutParams(-1, -2);
      this.edittext = new Cocos2dxEditText((Context)this);
      this.edittext.setLayoutParams(layoutParams);
      frameLayout.addView((View)this.edittext);
      this.mGLView = new Cocos2dxGLSurfaceView((Context)this);
      frameLayout.addView((View)this.mGLView);
      this.mGLView.setEGLContextClientVersion(2);
      this.mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());
      this.mGLView.setTextField(this.edittext);
      setContentView((View)frameLayout);
      getWindow().setSoftInputMode(1);
    } else {
      Log.d("activity", "don't support gles2.0");
      finish();
    } 
    if (me != null && me != this) {
      me.unregisterReceiver(receiver_);
      receiver_ = null;
      me.finish();
      me = null;
    } 
    me = this;
    registerReceiver();
  }
  
  protected void onPause() {
    super.onPause();
    this.mGLView.onPause();
    isPaused_ = true;
  }
  
  protected void onResume() {
    super.onResume();
    this.mGLView.onResume();
    isPaused_ = false;
  }
  
  public void onStart() {
    super.onStart();
    FlurryAgent.onStartSession((Context)this, "YB99JKNDXFQQ5V7HHYXR");
  }
  
  public void onStop() {
    super.onStop();
    FlurryAgent.onEndSession((Context)this);
  }
  
  void registerReceiver() {
    if (receiver_ != null) {
      me.unregisterReceiver(receiver_);
      receiver_ = null;
    } 
    try {
      IntentFilter intentFilter = new IntentFilter();
      this("android.intent.action.SCREEN_ON");
      intentFilter.addAction("android.intent.action.SCREEN_OFF");
      intentFilter.addAction("android.intent.action.USER_PRESENT");
      Boomlings$receiverScreen boomlings$receiverScreen = new Boomlings$receiverScreen();
      this(this);
      receiver_ = boomlings$receiverScreen;
      registerReceiver(receiver_, intentFilter);
    } catch (Exception exception) {}
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\robtopx\boomlings\Boomlings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */