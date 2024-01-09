package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

public class ao {
  private static final String TAG = "Cocos2dxMusic";
  
  private MediaPlayer mBackgroundMediaPlayer;
  
  public Context mContext;
  
  private String mCurrentPath;
  
  private boolean mIsPaused;
  
  private float mLeftVolume;
  
  private float mRightVolume;
  
  public ao(Context paramContext) {
    this.mContext = paramContext;
    initData();
  }
  
  private MediaPlayer createMediaplayerFromAssets(String paramString) {
    MediaPlayer mediaPlayer = new MediaPlayer();
    try {
      if (paramString.startsWith("/")) {
        mediaPlayer.setDataSource(paramString);
      } else {
        AssetFileDescriptor assetFileDescriptor = this.mContext.getAssets().openFd(paramString);
        mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
      } 
      mediaPlayer.prepare();
      mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
      MediaPlayer mediaPlayer1 = mediaPlayer;
    } catch (Exception exception) {
      paramString = null;
      Log.e("Cocos2dxMusic", "error: " + exception.getMessage(), exception);
    } 
    return (MediaPlayer)paramString;
  }
  
  private void initData() {
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
    this.mBackgroundMediaPlayer = null;
    this.mIsPaused = false;
    this.mCurrentPath = null;
  }
  
  public void end() {
    if (this.mBackgroundMediaPlayer != null)
      this.mBackgroundMediaPlayer.release(); 
    initData();
  }
  
  public float getBackgroundVolume() {
    return (this.mBackgroundMediaPlayer != null) ? ((this.mLeftVolume + this.mRightVolume) / 2.0F) : 0.0F;
  }
  
  public boolean isBackgroundMusicPlaying() {
    return (this.mBackgroundMediaPlayer == null) ? false : this.mBackgroundMediaPlayer.isPlaying();
  }
  
  public void pauseBackgroundMusic() {
    if (this.mBackgroundMediaPlayer != null && this.mBackgroundMediaPlayer.isPlaying()) {
      this.mBackgroundMediaPlayer.pause();
      this.mIsPaused = true;
    } 
  }
  
  public void playBackgroundMusic(String paramString, boolean paramBoolean) {
    if (this.mCurrentPath == null) {
      this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
      this.mCurrentPath = paramString;
    } else if (!this.mCurrentPath.equals(paramString)) {
      if (this.mBackgroundMediaPlayer != null)
        this.mBackgroundMediaPlayer.release(); 
      this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
      this.mCurrentPath = paramString;
    } 
    if (this.mBackgroundMediaPlayer == null) {
      Log.e("Cocos2dxMusic", "playBackgroundMusic: background media player is null");
      return;
    } 
    this.mBackgroundMediaPlayer.stop();
    this.mBackgroundMediaPlayer.setLooping(paramBoolean);
    try {
      this.mBackgroundMediaPlayer.prepare();
      this.mBackgroundMediaPlayer.seekTo(0);
      this.mBackgroundMediaPlayer.start();
      this.mIsPaused = false;
    } catch (Exception exception) {
      Log.e("Cocos2dxMusic", "playBackgroundMusic: error state");
    } 
  }
  
  public void preloadBackgroundMusic(String paramString) {
    if (this.mCurrentPath == null || !this.mCurrentPath.equals(paramString)) {
      if (this.mBackgroundMediaPlayer != null)
        this.mBackgroundMediaPlayer.release(); 
      this.mBackgroundMediaPlayer = createMediaplayerFromAssets(paramString);
      this.mCurrentPath = paramString;
    } 
  }
  
  public void resumeBackgroundMusic() {
    if (this.mBackgroundMediaPlayer != null && this.mIsPaused) {
      this.mBackgroundMediaPlayer.start();
      this.mIsPaused = false;
    } 
  }
  
  public void rewindBackgroundMusic() {
    if (this.mBackgroundMediaPlayer != null) {
      this.mBackgroundMediaPlayer.stop();
      try {
        this.mBackgroundMediaPlayer.prepare();
        this.mBackgroundMediaPlayer.seekTo(0);
        this.mBackgroundMediaPlayer.start();
        this.mIsPaused = false;
      } catch (Exception exception) {
        Log.e("Cocos2dxMusic", "rewindBackgroundMusic: error state");
      } 
    } 
  }
  
  public void setBackgroundVolume(float paramFloat) {
    float f1 = 1.0F;
    float f2 = 0.0F;
    if (paramFloat < 0.0F)
      paramFloat = f2; 
    if (paramFloat > 1.0F)
      paramFloat = f1; 
    this.mRightVolume = paramFloat;
    this.mLeftVolume = paramFloat;
    if (this.mBackgroundMediaPlayer != null)
      this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume); 
  }
  
  public void stopBackgroundMusic() {
    if (this.mBackgroundMediaPlayer != null) {
      this.mBackgroundMediaPlayer.stop();
      this.mIsPaused = false;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */