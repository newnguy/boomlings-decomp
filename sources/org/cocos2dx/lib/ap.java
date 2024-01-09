package org.cocos2dx.lib;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class ap {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 10;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "Cocos2dxSound";
    public Context mContext;
    private float mLeftVolume;
    private HashMap mPathSoundIdMap;
    private HashMap mPathStreamIDsMap;
    private float mRightVolume;
    private HashMap mSoundIdStreamIdMap;
    private SoundPool mSoundPool;

    public ap(Context context) {
        this.mContext = context;
        initData();
    }

    private void initData() {
        this.mSoundIdStreamIdMap = new HashMap();
        this.mPathStreamIDsMap = new HashMap();
        this.mPathSoundIdMap = new HashMap();
        this.mSoundPool = new SoundPool(MAX_SIMULTANEOUS_STREAMS_DEFAULT, 3, 5);
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
    }

    public int createSoundIdFromAsset(String str) {
        try {
            return str.startsWith("/") ? this.mSoundPool.load(str, 0) : this.mSoundPool.load(this.mContext.getAssets().openFd(str), 0);
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            return -1;
        }
    }

    public void end() {
        this.mSoundPool.release();
        this.mPathStreamIDsMap.clear();
        this.mPathSoundIdMap.clear();
        initData();
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void pauseAllEffects() {
        this.mSoundPool.autoPause();
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        int playEffect;
        Integer num = (Integer) this.mPathSoundIdMap.get(str);
        if (num != null) {
            float max = Math.max(0.0f, Math.min((float) SOUND_RATE, this.mLeftVolume * f3 * Math.max(0.0f, Math.min((float) SOUND_RATE, SOUND_RATE - f2))));
            float max2 = Math.max(0.0f, Math.min((float) SOUND_RATE, this.mRightVolume * f3 * Math.max(0.0f, Math.min((float) SOUND_RATE, SOUND_RATE + f2))));
            float max3 = Math.max(0.5f, Math.min(2.0f, SOUND_RATE * f));
            int play = this.mSoundPool.play(num.intValue(), max, max2, z ? 2 : 1, z ? -1 : 0, max3);
            ArrayList arrayList = (ArrayList) this.mPathStreamIDsMap.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.mPathStreamIDsMap.put(str, arrayList);
            }
            arrayList.add(Integer.valueOf(play));
            playEffect = play;
        } else if (Integer.valueOf(preloadEffect(str)).intValue() == -1) {
            return -1;
        } else {
            playEffect = playEffect(str, z, f, f2, f3);
        }
        return playEffect;
    }

    public int preloadEffect(String str) {
        Integer num = (Integer) this.mPathSoundIdMap.get(str);
        if (num == null) {
            num = Integer.valueOf(createSoundIdFromAsset(str));
            this.mPathSoundIdMap.put(str, num);
        }
        return num.intValue();
    }

    public void resumeAllEffects() {
        this.mSoundPool.autoResume();
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    public void setEffectsVolume(float f) {
        float f2 = SOUND_RATE;
        float f3 = f >= 0.0f ? f : 0.0f;
        if (f3 <= SOUND_RATE) {
            f2 = f3;
        }
        this.mRightVolume = f2;
        this.mLeftVolume = f2;
        if (this.mPathStreamIDsMap.isEmpty()) {
            return;
        }
        for (Map.Entry entry : this.mPathStreamIDsMap.entrySet()) {
            Iterator it = ((ArrayList) entry.getValue()).iterator();
            while (it.hasNext()) {
                this.mSoundPool.setVolume(((Integer) it.next()).intValue(), this.mLeftVolume, this.mRightVolume);
            }
        }
    }

    public void stopAllEffects() {
        if (!this.mPathStreamIDsMap.isEmpty()) {
            for (Map.Entry entry : this.mPathStreamIDsMap.entrySet()) {
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(((Integer) it.next()).intValue());
                }
            }
        }
        this.mPathStreamIDsMap.clear();
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        for (String str : this.mPathStreamIDsMap.keySet()) {
            if (((ArrayList) this.mPathStreamIDsMap.get(str)).contains(Integer.valueOf(i))) {
                ((ArrayList) this.mPathStreamIDsMap.get(str)).remove(((ArrayList) this.mPathStreamIDsMap.get(str)).indexOf(Integer.valueOf(i)));
                return;
            }
        }
    }

    public void unloadEffect(String str) {
        ArrayList arrayList = (ArrayList) this.mPathStreamIDsMap.get(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mSoundPool.stop(((Integer) it.next()).intValue());
            }
        }
        this.mPathStreamIDsMap.remove(str);
        this.mSoundPool.unload(((Integer) this.mPathSoundIdMap.get(str)).intValue());
        this.mPathSoundIdMap.remove(str);
    }
}
