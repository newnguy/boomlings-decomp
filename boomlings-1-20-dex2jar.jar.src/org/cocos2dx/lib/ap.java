package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ap {
  private static final int INVALID_SOUND_ID = -1;
  
  private static final int INVALID_STREAM_ID = -1;
  
  private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 10;
  
  private static final int SOUND_PRIORITY = 1;
  
  private static final int SOUND_QUALITY = 5;
  
  private static final float SOUND_RATE = 1.0F;
  
  private static final String TAG = "Cocos2dxSound";
  
  public Context mContext;
  
  private float mLeftVolume;
  
  private HashMap mPathSoundIdMap;
  
  private HashMap mPathStreamIDsMap;
  
  private float mRightVolume;
  
  private HashMap mSoundIdStreamIdMap;
  
  private SoundPool mSoundPool;
  
  public ap(Context paramContext) {
    this.mContext = paramContext;
    initData();
  }
  
  private void initData() {
    this.mSoundIdStreamIdMap = new HashMap<Object, Object>();
    this.mPathStreamIDsMap = new HashMap<Object, Object>();
    this.mPathSoundIdMap = new HashMap<Object, Object>();
    this.mSoundPool = new SoundPool(10, 3, 5);
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
  }
  
  public int createSoundIdFromAsset(String paramString) {
    byte b;
    try {
      if (paramString.startsWith("/"))
        return this.mSoundPool.load(paramString, 0); 
      b = this.mSoundPool.load(this.mContext.getAssets().openFd(paramString), 0);
    } catch (Exception exception) {
      b = -1;
      Log.e("Cocos2dxSound", "error: " + exception.getMessage(), exception);
    } 
    return b;
  }
  
  public void end() {
    this.mSoundPool.release();
    this.mPathStreamIDsMap.clear();
    this.mPathSoundIdMap.clear();
    initData();
  }
  
  public float getEffectsVolume() {
    return (this.mLeftVolume + this.mRightVolume) / 2.0F;
  }
  
  public void pauseAllEffects() {
    this.mSoundPool.autoPause();
  }
  
  public void pauseEffect(int paramInt) {
    this.mSoundPool.pause(paramInt);
  }
  
  public int playEffect(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3) {
    int i = -1;
    Integer integer = (Integer)this.mPathSoundIdMap.get(paramString);
    if (integer != null) {
      float f = Math.max(0.0F, Math.min(1.0F, this.mLeftVolume * paramFloat3 * Math.max(0.0F, Math.min(1.0F, 1.0F - paramFloat2))));
      paramFloat2 = Math.max(0.0F, Math.min(1.0F, this.mRightVolume * paramFloat3 * Math.max(0.0F, Math.min(1.0F, 1.0F + paramFloat2))));
      paramFloat1 = Math.max(0.5F, Math.min(2.0F, 1.0F * paramFloat1));
      byte b = 1;
      if (paramBoolean)
        b = 2; 
      SoundPool soundPool = this.mSoundPool;
      int j = integer.intValue();
      if (!paramBoolean)
        i = 0; 
      i = soundPool.play(j, f, paramFloat2, b, i, paramFloat1);
      ArrayList<Integer> arrayList2 = (ArrayList)this.mPathStreamIDsMap.get(paramString);
      ArrayList<Integer> arrayList1 = arrayList2;
      if (arrayList2 == null) {
        arrayList1 = new ArrayList();
        this.mPathStreamIDsMap.put(paramString, arrayList1);
      } 
      arrayList1.add(Integer.valueOf(i));
      return i;
    } 
    if (Integer.valueOf(preloadEffect(paramString)).intValue() != -1)
      i = playEffect(paramString, paramBoolean, paramFloat1, paramFloat2, paramFloat3); 
    return i;
  }
  
  public int preloadEffect(String paramString) {
    Integer integer2 = (Integer)this.mPathSoundIdMap.get(paramString);
    Integer integer1 = integer2;
    if (integer2 == null) {
      integer1 = Integer.valueOf(createSoundIdFromAsset(paramString));
      this.mPathSoundIdMap.put(paramString, integer1);
    } 
    return integer1.intValue();
  }
  
  public void resumeAllEffects() {
    this.mSoundPool.autoResume();
  }
  
  public void resumeEffect(int paramInt) {
    this.mSoundPool.resume(paramInt);
  }
  
  public void setEffectsVolume(float paramFloat) {
    float f1 = 1.0F;
    float f2 = 0.0F;
    if (paramFloat < 0.0F)
      paramFloat = f2; 
    if (paramFloat > 1.0F)
      paramFloat = f1; 
    this.mRightVolume = paramFloat;
    this.mLeftVolume = paramFloat;
    if (!this.mPathStreamIDsMap.isEmpty()) {
      Iterator<Map.Entry> iterator = this.mPathStreamIDsMap.entrySet().iterator();
      while (true) {
        if (iterator.hasNext()) {
          Iterator<Integer> iterator1 = ((ArrayList)((Map.Entry)iterator.next()).getValue()).iterator();
          while (iterator1.hasNext()) {
            int i = ((Integer)iterator1.next()).intValue();
            this.mSoundPool.setVolume(i, this.mLeftVolume, this.mRightVolume);
          } 
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void stopAllEffects() {
    if (!this.mPathStreamIDsMap.isEmpty()) {
      Iterator<Map.Entry> iterator = this.mPathStreamIDsMap.entrySet().iterator();
      while (true) {
        if (iterator.hasNext()) {
          Iterator<Integer> iterator1 = ((ArrayList)((Map.Entry)iterator.next()).getValue()).iterator();
          while (iterator1.hasNext()) {
            int i = ((Integer)iterator1.next()).intValue();
            this.mSoundPool.stop(i);
          } 
          continue;
        } 
        this.mPathStreamIDsMap.clear();
        return;
      } 
    } 
    this.mPathStreamIDsMap.clear();
  }
  
  public void stopEffect(int paramInt) {
    this.mSoundPool.stop(paramInt);
    Iterator<String> iterator = this.mPathStreamIDsMap.keySet().iterator();
    while (true) {
      if (iterator.hasNext()) {
        String str = iterator.next();
        if (((ArrayList)this.mPathStreamIDsMap.get(str)).contains(Integer.valueOf(paramInt))) {
          ((ArrayList)this.mPathStreamIDsMap.get(str)).remove(((ArrayList)this.mPathStreamIDsMap.get(str)).indexOf(Integer.valueOf(paramInt)));
          return;
        } 
        continue;
      } 
      return;
    } 
  }
  
  public void unloadEffect(String paramString) {
    ArrayList arrayList = (ArrayList)this.mPathStreamIDsMap.get(paramString);
    if (arrayList != null) {
      Iterator<Integer> iterator = arrayList.iterator();
      while (true) {
        if (iterator.hasNext()) {
          Integer integer2 = iterator.next();
          this.mSoundPool.stop(integer2.intValue());
          continue;
        } 
        this.mPathStreamIDsMap.remove(paramString);
        Integer integer1 = (Integer)this.mPathSoundIdMap.get(paramString);
        this.mSoundPool.unload(integer1.intValue());
        this.mPathSoundIdMap.remove(paramString);
        return;
      } 
    } 
    this.mPathStreamIDsMap.remove(paramString);
    Integer integer = (Integer)this.mPathSoundIdMap.get(paramString);
    this.mSoundPool.unload(integer.intValue());
    this.mPathSoundIdMap.remove(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */