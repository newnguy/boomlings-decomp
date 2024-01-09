package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.Timer;

/* loaded from: classes.dex */
public class TapjoyVideoView extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static as q;
    Dialog a;
    int c;
    private RelativeLayout l;
    private WebView m;
    private Bitmap n;
    private ImageView x;
    private static boolean o = false;
    private static boolean p = false;
    static int f = 16;
    private VideoView h = null;
    private String i = null;
    private TextView j = null;
    private String k = null;
    Timer b = null;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private int v = 0;
    private int w = 0;
    final String d = "VIDEO";
    final Handler e = new Handler();
    final Runnable g = new av(this);

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.l.removeAllViews();
        this.l.setBackgroundColor(-16777216);
        if (this.h == null && this.j == null) {
            this.x = new ImageView(this);
            this.n = ao.g();
            if (this.n != null) {
                this.x.setImageBitmap(this.n);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(11);
            this.x.setLayoutParams(layoutParams);
            this.h = new VideoView(this);
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
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            this.h.setLayoutParams(layoutParams2);
            this.v = this.h.getDuration() / 1000;
            aj.a("VIDEO", "videoView.getDuration(): " + this.h.getDuration());
            aj.a("VIDEO", "timeRemaining: " + this.v);
            this.j = new TextView(this);
            this.j.setTextSize(f);
            this.j.setTypeface(Typeface.create("default", 1), 1);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(12);
            this.j.setLayoutParams(layoutParams3);
        }
        e();
        this.l.addView(this.h);
        this.l.addView(this.x);
        this.l.addView(this.j);
    }

    private void c() {
        this.m = new WebView(this);
        this.m.setWebViewClient(new at(this));
        this.m.getSettings().setJavaScriptEnabled(true);
        this.m.loadUrl(this.k);
    }

    private void d() {
        this.l.removeAllViews();
        this.l.addView(this.m, -1, -1);
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
        if (this.b != null) {
            this.b.cancel();
        }
        this.b = new Timer();
        this.b.schedule(new ba(this, null), 500L, 100L);
        c();
        this.t = false;
        if (this.s) {
            new Thread(new au(this)).start();
            this.s = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int f() {
        int duration = (this.h.getDuration() - this.h.getCurrentPosition()) / 1000;
        if (duration < 0) {
            return 0;
        }
        return duration;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        aj.a("VIDEO", "onCompletion");
        if (this.b != null) {
            this.b.cancel();
        }
        d();
        if (!o) {
            ao.f();
            new Thread(new aw(this)).start();
        }
        o = false;
        this.u = true;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        aj.a("VIDEO", "onCreate");
        super.onCreate(bundle);
        if (bundle != null) {
            aj.a("VIDEO", "*** Loading saved data from bundle ***");
            this.w = bundle.getInt("seek_time");
            this.r = bundle.getBoolean("dialog_showing");
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
        this.l = new RelativeLayout(this);
        this.l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        setContentView(this.l);
        if (Integer.parseInt(Build.VERSION.SDK) > 3) {
            this.c = new u(this).b();
            aj.a("VIDEO", "deviceScreenLayoutSize: " + this.c);
            if (this.c == 4) {
                f = 32;
            }
        }
        aj.a("VIDEO", "textSize: " + f);
        b();
        aj.a("VIDEO", "onCreate DONE");
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        aj.a("VIDEO", "dialog onCreateDialog");
        if (this.r) {
            switch (i) {
                case 0:
                    this.a = new AlertDialog.Builder(this).setTitle("Cancel Video?").setMessage("Currency will not be awarded, are you sure you want to cancel the video?").setNegativeButton("End", new ay(this)).setPositiveButton("Resume", new ax(this)).create();
                    this.a.setOnCancelListener(new az(this));
                    this.a.show();
                    this.r = true;
                    break;
                default:
                    this.a = null;
                    break;
            }
            return this.a;
        }
        return this.a;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        o = true;
        aj.a("VIDEO", "onError");
        ao.b(3);
        this.u = true;
        if (this.b != null) {
            this.b.cancel();
            return false;
        }
        return false;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (!this.u) {
                this.w = this.h.getCurrentPosition();
                this.h.pause();
                this.r = true;
                showDialog(0);
                aj.a("VIDEO", "PAUSE VIDEO time: " + this.w);
                aj.a("VIDEO", "currentPosition: " + this.h.getCurrentPosition());
                aj.a("VIDEO", "duration: " + this.h.getDuration() + ", elapsed: " + (this.h.getDuration() - this.h.getCurrentPosition()));
                return true;
            } else if (this.h.isPlaying()) {
                this.h.stopPlayback();
                d();
                if (this.b != null) {
                    this.b.cancel();
                    return true;
                }
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.h.isPlaying()) {
            aj.a("VIDEO", "onPause");
            this.w = this.h.getCurrentPosition();
            aj.a("VIDEO", "seekTime: " + this.w);
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        aj.a("VIDEO", "onPrepared");
    }

    @Override // android.app.Activity
    protected void onResume() {
        aj.a("VIDEO", "onResume");
        super.onResume();
        setRequestedOrientation(0);
        if (this.w > 0) {
            aj.a("VIDEO", "seekTime: " + this.w);
            this.h.seekTo(this.w);
            if (this.r && this.a != null && this.a.isShowing()) {
                return;
            }
            this.h.start();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        aj.a("VIDEO", "*** onSaveInstanceState ***");
        aj.a("VIDEO", "dialogShowing: " + this.r + ", seekTime: " + this.w);
        bundle.putBoolean("dialog_showing", this.r);
        bundle.putInt("seek_time", this.w);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        aj.a("VIDEO", "onWindowFocusChanged");
        super.onWindowFocusChanged(z);
    }
}
