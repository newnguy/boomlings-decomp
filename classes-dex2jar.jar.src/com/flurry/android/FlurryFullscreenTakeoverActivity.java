package com.flurry.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.util.Collections;

public final class FlurryFullscreenTakeoverActivity extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
  private static final String a = FlurryFullscreenTakeoverActivity.class.getSimpleName();
  
  private ap b;
  
  private bd c;
  
  private ProgressDialog d;
  
  private az e;
  
  private MediaController f;
  
  private boolean g;
  
  public final void onCompletion(MediaPlayer paramMediaPlayer) {}
  
  public final void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public final void onCreate(Bundle paramBundle) {
    int i = -1;
    setTheme(16973831);
    super.onCreate(paramBundle);
    Intent intent = getIntent();
    String str2 = intent.getStringExtra("url");
    if (str2 == null) {
      if (paramBundle != null)
        i = paramBundle.getInt("frameIndex", -1); 
      int j = i;
      if (i < 0)
        j = intent.getIntExtra("frameIndex", 0); 
      bo bo = FlurryAgent.b();
      if (bo.n() != null) {
        this.b = new ap((Context)this, bo, bo.m(), bo.n(), j);
        this.b.initLayout((Context)this);
        if (j == 0)
          this.b.a("rendered", Collections.emptyMap()); 
        setContentView((View)this.b);
      } 
      return;
    } 
    String str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str2));
    if (str1 != null && str1.startsWith("video/")) {
      this.f = new MediaController((Context)this);
      this.e = new az((Context)this);
      this.e.setOnPreparedListener(this);
      this.e.setOnCompletionListener(this);
      this.e.setOnErrorListener(this);
      this.e.setMediaController(this.f);
      this.e.setVideoURI(Uri.parse(str2));
      FrameLayout frameLayout = new FrameLayout((Context)this);
      setContentView((View)frameLayout);
      this.e.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1, 17));
      frameLayout.addView((View)this.e);
      this.d = new ProgressDialog((Context)this);
      this.d.setProgressStyle(0);
      this.d.setMessage("Loading...");
      this.d.setCancelable(true);
      this.d.show();
      return;
    } 
    this.c = new bd((Context)this, str2);
    setContentView((View)this.c);
  }
  
  protected final void onDestroy() {
    if (this.d != null && this.d.isShowing())
      this.d.dismiss(); 
    if (this.e != null && this.e.isPlaying())
      this.e.stopPlayback(); 
    super.onDestroy();
  }
  
  public final boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    if (this.d != null && this.d.isShowing())
      this.d.dismiss(); 
    bm.b(a, "error occurs during video playback");
    finish();
    return true;
  }
  
  public final boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4) {
      if (this.b != null) {
        this.b.a("adWillClose", Collections.emptyMap(), this.b.c, this.b.b, this.b.d, 0);
        return true;
      } 
      return super.onKeyDown(paramInt, paramKeyEvent);
    } 
    return onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected final void onPause() {
    super.onPause();
    this.g = false;
    if (this.e != null && this.e.isPlaying())
      this.e.pause(); 
  }
  
  public final void onPrepared(MediaPlayer paramMediaPlayer) {
    if (this.d != null && this.d.isShowing())
      this.d.dismiss(); 
    if (this.e != null && this.g)
      this.e.start(); 
  }
  
  protected final void onRestart() {
    super.onRestart();
  }
  
  protected final void onResume() {
    super.onResume();
    this.g = true;
    if (this.f != null)
      this.f.show(0); 
  }
  
  public final void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    if (this.b != null)
      paramBundle.putInt("frameIndex", this.b.d); 
  }
  
  public final void onStart() {
    super.onStart();
    if (FlurryAgent.c() != null) {
      FlurryAgent.onStartSession((Context)this, FlurryAgent.c());
      return;
    } 
    finish();
  }
  
  public final void onStop() {
    super.onStop();
    if (this.b != null)
      this.b.stop(); 
    FlurryAgent.onEndSession((Context)this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\FlurryFullscreenTakeoverActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */