package com.chartboost.sdk.impl;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class p extends Animation {
  private final float a;
  
  private final float b;
  
  private final float c;
  
  private final float d;
  
  private boolean e = true;
  
  private Camera f;
  
  public p(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) {
    this.a = paramFloat1;
    this.b = paramFloat2;
    this.c = paramFloat3;
    this.d = paramFloat4;
    this.e = paramBoolean;
  }
  
  protected void applyTransformation(float paramFloat, Transformation paramTransformation) {
    paramFloat = this.a + (this.b - this.a) * paramFloat;
    Camera camera = this.f;
    Matrix matrix = paramTransformation.getMatrix();
    camera.save();
    if (this.e) {
      camera.rotateY(paramFloat);
    } else {
      camera.rotateX(paramFloat);
    } 
    camera.getMatrix(matrix);
    camera.restore();
    matrix.preTranslate(-this.c, -this.d);
    matrix.postTranslate(this.c, this.d);
  }
  
  public void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.initialize(paramInt1, paramInt2, paramInt3, paramInt4);
    this.f = new Camera();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */