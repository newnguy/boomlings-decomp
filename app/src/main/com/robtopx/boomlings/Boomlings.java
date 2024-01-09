package com.robtopx.boomlings;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.flurry.android.FlurryAgent;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxEditText;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxRenderer;
import org.cocos2dx.lib.JniToCpp;

/* loaded from: classes.dex */
public class Boomlings extends Cocos2dxActivity {
    public static BroadcastReceiver receiver_;
    public Cocos2dxEditText edittext = null;
    private Cocos2dxGLSurfaceView mGLView;
    private static Boomlings me = null;
    public static boolean keyboardActive_ = false;
    public static boolean blockBackButton_ = false;
    public static boolean focusState_ = true;
    public static boolean shouldResumeSound_ = true;
    public static boolean isPaused_ = false;

    /* loaded from: classes.dex */
    public class receiverScreen extends BroadcastReceiver {
        public receiverScreen() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                Log.d("TAG", "ACTION_SCREEN_ON");
                if (!((KeyguardManager) Boomlings.this.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                    Boomlings.shouldResumeSound_ = true;
                }
                if (!Boomlings.isPaused_ && Boomlings.shouldResumeSound_) {
                    JniToCpp.resumeSound();
                }
            }
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                Boomlings.shouldResumeSound_ = false;
            }
            if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                Boomlings.shouldResumeSound_ = true;
                if (Boomlings.isPaused_) {
                    return;
                }
                JniToCpp.resumeSound();
            }
        }
    }

    static {
        System.loadLibrary("game");
    }

    private boolean detectOpenGLES20() {
        return ((ActivityManager) getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public static void onToggleKeyboard() {
        me.runOnUiThread(new e());
    }

    public static void setBlockBackButton(boolean z) {
        blockBackButton_ = z;
    }

    public static void setKeyboardState(boolean z) {
        keyboardActive_ = z;
    }

    public static boolean shouldResumeSound() {
        return shouldResumeSound_;
    }

    public static void showDialog(String str, String str2, String str3) {
        me.runOnUiThread(new f(str, str2, str3));
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, net.robotmedia.billing.helper.AbstractBillingActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (detectOpenGLES20()) {
            super.setPackageName(getApplication().getPackageName());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            FrameLayout frameLayout = new FrameLayout(this);
            frameLayout.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
            this.edittext = new Cocos2dxEditText(this);
            this.edittext.setLayoutParams(layoutParams2);
            frameLayout.addView(this.edittext);
            this.mGLView = new Cocos2dxGLSurfaceView(this);
            frameLayout.addView(this.mGLView);
            this.mGLView.setEGLContextClientVersion(2);
            this.mGLView.setCocos2dxRenderer(new Cocos2dxRenderer());
            this.mGLView.setTextField(this.edittext);
            setContentView(frameLayout);
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

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mGLView.onPause();
        isPaused_ = true;
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mGLView.onResume();
        isPaused_ = false;
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this, "YB99JKNDXFQQ5V7HHYXR");
    }

    @Override // org.cocos2dx.lib.Cocos2dxActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }

    void registerReceiver() {
        if (receiver_ != null) {
            me.unregisterReceiver(receiver_);
            receiver_ = null;
        }
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            receiver_ = new receiverScreen();
            registerReceiver(receiver_, intentFilter);
        } catch (Exception e) {
        }
    }
}
