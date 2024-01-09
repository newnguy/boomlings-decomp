package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

/* loaded from: classes.dex */
public class ao {
    private static final String TAG = "Cocos2dxMusic";
    private MediaPlayer mBackgroundMediaPlayer;
    public Context mContext;
    private String mCurrentPath;
    private boolean mIsPaused;
    private float mLeftVolume;
    private float mRightVolume;

    public ao(Context context) {
        this.mContext = context;
        initData();
    }

    private MediaPlayer createMediaplayerFromAssets(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (str.startsWith("/")) {
                mediaPlayer.setDataSource(str);
            } else {
                AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str);
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            return null;
        }
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mIsPaused = false;
        this.mCurrentPath = null;
    }

    public void end() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.release();
        }
        initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public boolean isBackgroundMusicPlaying() {
        if (this.mBackgroundMediaPlayer == null) {
            return false;
        }
        return this.mBackgroundMediaPlayer.isPlaying();
    }

    public void pauseBackgroundMusic() {
        if (this.mBackgroundMediaPlayer == null || !this.mBackgroundMediaPlayer.isPlaying()) {
            return;
        }
        this.mBackgroundMediaPlayer.pause();
        this.mIsPaused = true;
    }

    public void playBackgroundMusic(String str, boolean z) {
        if (this.mCurrentPath == null) {
            this.mBackgroundMediaPlayer = createMediaplayerFromAssets(str);
            this.mCurrentPath = str;
        } else if (!this.mCurrentPath.equals(str)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayerFromAssets(str);
            this.mCurrentPath = str;
        }
        if (this.mBackgroundMediaPlayer == null) {
            Log.e(TAG, "playBackgroundMusic: background media player is null");
            return;
        }
        this.mBackgroundMediaPlayer.stop();
        this.mBackgroundMediaPlayer.setLooping(z);
        try {
            this.mBackgroundMediaPlayer.prepare();
            this.mBackgroundMediaPlayer.seekTo(0);
            this.mBackgroundMediaPlayer.start();
            this.mIsPaused = false;
        } catch (Exception e) {
            Log.e(TAG, "playBackgroundMusic: error state");
        }
    }

    public void preloadBackgroundMusic(String str) {
        if (this.mCurrentPath == null || !this.mCurrentPath.equals(str)) {
            if (this.mBackgroundMediaPlayer != null) {
                this.mBackgroundMediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayerFromAssets(str);
            this.mCurrentPath = str;
        }
    }

    public void resumeBackgroundMusic() {
        if (this.mBackgroundMediaPlayer == null || !this.mIsPaused) {
            return;
        }
        this.mBackgroundMediaPlayer.start();
        this.mIsPaused = false;
    }

    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.stop();
            try {
                this.mBackgroundMediaPlayer.prepare();
                this.mBackgroundMediaPlayer.seekTo(0);
                this.mBackgroundMediaPlayer.start();
                this.mIsPaused = false;
            } catch (Exception e) {
                Log.e(TAG, "rewindBackgroundMusic: error state");
            }
        }
    }

    public void setBackgroundVolume(float f) {
        float f2 = f >= 0.0f ? f : 0.0f;
        float f3 = f2 <= 1.0f ? f2 : 1.0f;
        this.mRightVolume = f3;
        this.mLeftVolume = f3;
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public void stopBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            this.mBackgroundMediaPlayer.stop();
            this.mIsPaused = false;
        }
    }
}
