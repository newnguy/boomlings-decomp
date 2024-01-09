package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;

public class AdVideoView extends FrameLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
  private static final a b = (a)a.a.b();
  
  public MediaController a;
  
  private WeakReference c;
  
  private AdWebView d;
  
  private long e;
  
  private VideoView f;
  
  private String g;
  
  public AdVideoView(Activity paramActivity, AdWebView paramAdWebView) {
    super((Context)paramActivity);
    this.c = new WeakReference<Activity>(paramActivity);
    this.d = paramAdWebView;
    this.f = new VideoView((Context)paramActivity);
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
    addView((View)this.f, (ViewGroup.LayoutParams)layoutParams);
    this.a = null;
    this.g = null;
    this.e = 0L;
    a();
    this.f.setOnCompletionListener(this);
    this.f.setOnPreparedListener(this);
    this.f.setOnErrorListener(this);
  }
  
  protected void a() {
    (new k(this)).a();
  }
  
  public void a(int paramInt) {
    this.f.seekTo(paramInt);
  }
  
  public void a(MotionEvent paramMotionEvent) {
    this.f.onTouchEvent(paramMotionEvent);
  }
  
  public void b() {
    if (!TextUtils.isEmpty(this.g)) {
      this.f.setVideoPath(this.g);
      return;
    } 
    b.a(this.d, "onVideoEvent", "{'event': 'error', 'what': 'no_src'}");
  }
  
  public void c() {
    this.f.pause();
  }
  
  public void d() {
    this.f.start();
  }
  
  public void e() {
    this.f.stopPlayback();
  }
  
  void f() {
    long l = this.f.getCurrentPosition();
    if (this.e != l) {
      float f = (float)l / 1000.0F;
      b.a(this.d, "onVideoEvent", "{'event': 'timeupdate', 'time': " + f + "}");
      this.e = l;
    } 
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    b.a(this.d, "onVideoEvent", "{'event': 'ended'}");
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    b.e("Video threw error! <what:" + paramInt1 + ", extra:" + paramInt2 + ">");
    b.a(this.d, "onVideoEvent", "{'event': 'error', 'what': '" + paramInt1 + "', 'extra': '" + paramInt2 + "'}");
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer) {
    float f = this.f.getDuration() / 1000.0F;
    b.a(this.d, "onVideoEvent", "{'event': 'canplaythrough', 'duration': '" + f + "'}");
  }
  
  public void setMediaControllerEnabled(boolean paramBoolean) {
    Activity activity = this.c.get();
    if (activity == null) {
      b.e("adActivity was null while trying to enable controls on a video.");
      return;
    } 
    if (paramBoolean) {
      if (this.a == null)
        this.a = new MediaController((Context)activity); 
      this.f.setMediaController(this.a);
      return;
    } 
    if (this.a != null)
      this.a.hide(); 
    this.f.setMediaController(null);
  }
  
  public void setSrc(String paramString) {
    this.g = paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\AdVideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */