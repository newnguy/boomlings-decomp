package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.Timer;

public class TapjoyVideoView extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
  static int f;
  
  private static boolean o = false;
  
  private static boolean p = false;
  
  private static as q;
  
  Dialog a;
  
  Timer b = null;
  
  int c;
  
  final String d = "VIDEO";
  
  final Handler e = new Handler();
  
  final Runnable g = new av(this);
  
  private VideoView h = null;
  
  private String i = null;
  
  private TextView j = null;
  
  private String k = null;
  
  private RelativeLayout l;
  
  private WebView m;
  
  private Bitmap n;
  
  private boolean r = false;
  
  private boolean s = false;
  
  private boolean t = false;
  
  private boolean u = false;
  
  private int v = 0;
  
  private int w = 0;
  
  private ImageView x;
  
  static {
    f = 16;
  }
  
  private void b() {
    this.l.removeAllViews();
    this.l.setBackgroundColor(-16777216);
    if (this.h == null && this.j == null) {
      this.x = new ImageView((Context)this);
      this.n = ao.g();
      if (this.n != null)
        this.x.setImageBitmap(this.n); 
      RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
      layoutParams.addRule(12);
      layoutParams.addRule(11);
      this.x.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      this.h = new VideoView((Context)this);
      this.h.setOnCompletionListener(this);
      this.h.setOnErrorListener(this);
      this.h.setOnPreparedListener(this);
      if (p) {
        aj.a("VIDEO", "streaming video: " + this.i);
        this.h.setVideoURI(Uri.parse(this.i));
      } else {
        aj.a("VIDEO", "cached video: " + this.i);
        this.h.setVideoPath(this.i);
      } 
      layoutParams = new RelativeLayout.LayoutParams(-2, -2);
      layoutParams.addRule(13);
      this.h.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
      this.v = this.h.getDuration() / 1000;
      aj.a("VIDEO", "videoView.getDuration(): " + this.h.getDuration());
      aj.a("VIDEO", "timeRemaining: " + this.v);
      this.j = new TextView((Context)this);
      this.j.setTextSize(f);
      this.j.setTypeface(Typeface.create("default", 1), 1);
      layoutParams = new RelativeLayout.LayoutParams(-2, -2);
      layoutParams.addRule(12);
      this.j.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    } 
    e();
    this.l.addView((View)this.h);
    this.l.addView((View)this.x);
    this.l.addView((View)this.j);
  }
  
  private void c() {
    this.m = new WebView((Context)this);
    this.m.setWebViewClient(new at(this));
    this.m.getSettings().setJavaScriptEnabled(true);
    this.m.loadUrl(this.k);
  }
  
  private void d() {
    this.l.removeAllViews();
    this.l.addView((View)this.m, -1, -1);
  }
  
  private void e() {
    this.h.requestFocus();
    if (this.r) {
      this.h.seekTo(this.w);
      aj.a("VIDEO", "dialog is showing -- don't start");
    } else {
      aj.a("VIDEO", "start");
      this.h.seekTo(0);
      this.h.start();
      ao.e();
    } 
    if (this.b != null)
      this.b.cancel(); 
    this.b = new Timer();
    this.b.schedule(new ba(this, null), 500L, 100L);
    c();
    this.t = false;
    if (this.s) {
      (new Thread(new au(this))).start();
      this.s = false;
    } 
  }
  
  private int f() {
    int j = (this.h.getDuration() - this.h.getCurrentPosition()) / 1000;
    int i = j;
    if (j < 0)
      i = 0; 
    return i;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    aj.a("VIDEO", "onCompletion");
    if (this.b != null)
      this.b.cancel(); 
    d();
    if (!o) {
      ao.f();
      (new Thread(new aw(this))).start();
    } 
    o = false;
    this.u = true;
  }
  
  protected void onCreate(Bundle paramBundle) {
    aj.a("VIDEO", "onCreate");
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      aj.a("VIDEO", "*** Loading saved data from bundle ***");
      this.w = paramBundle.getInt("seek_time");
      this.r = paramBundle.getBoolean("dialog_showing");
    } 
    aj.a("VIDEO", "dialogShowing: " + this.r + ", seekTime: " + this.w);
    this.s = true;
    p = false;
    if (ao.a() == null) {
      aj.a("VIDEO", "null video");
      finish();
      return;
    } 
    q = ao.a().c();
    this.i = q.i;
    this.k = q.h;
    if (this.i == null || this.i.length() == 0) {
      aj.a("VIDEO", "no cached video, try streaming video at location: " + q.c);
      this.i = q.c;
      p = true;
    } 
    aj.a("VIDEO", "videoPath: " + this.i);
    requestWindowFeature(1);
    this.l = new RelativeLayout((Context)this);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
    this.l.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    setContentView((View)this.l);
    if (Integer.parseInt(Build.VERSION.SDK) > 3) {
      this.c = (new u((Context)this)).b();
      aj.a("VIDEO", "deviceScreenLayoutSize: " + this.c);
      if (this.c == 4)
        f = 32; 
    } 
    aj.a("VIDEO", "textSize: " + f);
    b();
    aj.a("VIDEO", "onCreate DONE");
  }
  
  protected Dialog onCreateDialog(int paramInt) {
    aj.a("VIDEO", "dialog onCreateDialog");
    if (!this.r)
      return this.a; 
    switch (paramInt) {
      default:
        this.a = null;
        return this.a;
      case 0:
        break;
    } 
    this.a = (Dialog)(new AlertDialog.Builder((Context)this)).setTitle("Cancel Video?").setMessage("Currency will not be awarded, are you sure you want to cancel the video?").setNegativeButton("End", new ay(this)).setPositiveButton("Resume", new ax(this)).create();
    this.a.setOnCancelListener(new az(this));
    this.a.show();
    this.r = true;
    return this.a;
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    o = true;
    aj.a("VIDEO", "onError");
    ao.b(3);
    this.u = true;
    if (this.b != null)
      this.b.cancel(); 
    return false;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = true;
    if (paramInt == 4) {
      if (!this.u) {
        this.w = this.h.getCurrentPosition();
        this.h.pause();
        this.r = true;
        showDialog(0);
        aj.a("VIDEO", "PAUSE VIDEO time: " + this.w);
        aj.a("VIDEO", "currentPosition: " + this.h.getCurrentPosition());
        aj.a("VIDEO", "duration: " + this.h.getDuration() + ", elapsed: " + (this.h.getDuration() - this.h.getCurrentPosition()));
        return bool;
      } 
      if (this.h.isPlaying()) {
        this.h.stopPlayback();
        d();
        boolean bool1 = bool;
        if (this.b != null) {
          this.b.cancel();
          bool1 = bool;
        } 
        return bool1;
      } 
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause() {
    super.onPause();
    if (this.h.isPlaying()) {
      aj.a("VIDEO", "onPause");
      this.w = this.h.getCurrentPosition();
      aj.a("VIDEO", "seekTime: " + this.w);
    } 
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer) {
    aj.a("VIDEO", "onPrepared");
  }
  
  protected void onResume() {
    aj.a("VIDEO", "onResume");
    super.onResume();
    setRequestedOrientation(0);
    if (this.w > 0) {
      aj.a("VIDEO", "seekTime: " + this.w);
      this.h.seekTo(this.w);
      if (!this.r || this.a == null || !this.a.isShowing())
        this.h.start(); 
    } 
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    aj.a("VIDEO", "*** onSaveInstanceState ***");
    aj.a("VIDEO", "dialogShowing: " + this.r + ", seekTime: " + this.w);
    paramBundle.putBoolean("dialog_showing", this.r);
    paramBundle.putInt("seek_time", this.w);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    aj.a("VIDEO", "onWindowFocusChanged");
    super.onWindowFocusChanged(paramBoolean);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\TapjoyVideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */