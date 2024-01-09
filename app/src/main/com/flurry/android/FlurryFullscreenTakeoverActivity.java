package com.flurry.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.util.Collections;

/* loaded from: classes.dex */
public final class FlurryFullscreenTakeoverActivity extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final String a = FlurryFullscreenTakeoverActivity.class.getSimpleName();
    private ap b;
    private bd c;
    private ProgressDialog d;
    private az e;
    private MediaController f;
    private boolean g;

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        setTheme(16973831);
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("url");
        if (stringExtra == null) {
            int i = bundle != null ? bundle.getInt("frameIndex", -1) : -1;
            if (i < 0) {
                i = intent.getIntExtra("frameIndex", 0);
            }
            bo b = FlurryAgent.b();
            if (b.n() != null) {
                this.b = new ap(this, b, b.m(), b.n(), i);
                this.b.initLayout(this);
                if (i == 0) {
                    this.b.a("rendered", Collections.emptyMap());
                }
                setContentView(this.b);
                return;
            }
            return;
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(stringExtra));
        if (mimeTypeFromExtension == null || !mimeTypeFromExtension.startsWith("video/")) {
            this.c = new bd(this, stringExtra);
            setContentView(this.c);
            return;
        }
        this.f = new MediaController(this);
        this.e = new az(this);
        this.e.setOnPreparedListener(this);
        this.e.setOnCompletionListener(this);
        this.e.setOnErrorListener(this);
        this.e.setMediaController(this.f);
        this.e.setVideoURI(Uri.parse(stringExtra));
        FrameLayout frameLayout = new FrameLayout(this);
        setContentView(frameLayout);
        this.e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        frameLayout.addView(this.e);
        this.d = new ProgressDialog(this);
        this.d.setProgressStyle(0);
        this.d.setMessage("Loading...");
        this.d.setCancelable(true);
        this.d.show();
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        if (this.e != null && this.e.isPlaying()) {
            this.e.stopPlayback();
        }
        super.onDestroy();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        bm.b(a, "error occurs during video playback");
        finish();
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.b != null) {
                this.b.a("adWillClose", Collections.emptyMap(), this.b.c, this.b.b, this.b.d, 0);
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected final void onPause() {
        super.onPause();
        this.g = false;
        if (this.e == null || !this.e.isPlaying()) {
            return;
        }
        this.e.pause();
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        if (this.e == null || !this.g) {
            return;
        }
        this.e.start();
    }

    @Override // android.app.Activity
    protected final void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        this.g = true;
        if (this.f != null) {
            this.f.show(0);
        }
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.b != null) {
            bundle.putInt("frameIndex", this.b.d);
        }
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        if (FlurryAgent.c() != null) {
            FlurryAgent.onStartSession(this, FlurryAgent.c());
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        if (this.b != null) {
            this.b.stop();
        }
        FlurryAgent.onEndSession(this);
    }
}
