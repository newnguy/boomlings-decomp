package org.cocos2dx.lib;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;

public class Cocos2dxAccelerometer implements SensorEventListener {
  private static final String TAG = "Cocos2dxAccelerometer";
  
  private Sensor mAccelerometer;
  
  private Context mContext;
  
  private int mNaturalOrientation;
  
  private SensorManager mSensorManager;
  
  public Cocos2dxAccelerometer(Context paramContext) {
    this.mContext = paramContext;
    this.mSensorManager = (SensorManager)this.mContext.getSystemService("sensor");
    this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
    this.mNaturalOrientation = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getOrientation();
  }
  
  private static native void onSensorChanged(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong);
  
  public void disable() {
    this.mSensorManager.unregisterListener(this);
  }
  
  public void enable() {
    this.mSensorManager.registerListener(this, this.mAccelerometer, 1);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    if (paramSensorEvent.sensor.getType() == 1) {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      int i = (this.mContext.getResources().getConfiguration()).orientation;
      if (i == 2 && this.mNaturalOrientation != 0) {
        f2 = -f2;
      } else if (i == 1 && this.mNaturalOrientation != 0) {
        f1 = -f1;
      } else {
        float f = f1;
        f1 = f2;
        f2 = f;
      } 
      onSensorChanged(f2, f1, f3, paramSensorEvent.timestamp);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxAccelerometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */