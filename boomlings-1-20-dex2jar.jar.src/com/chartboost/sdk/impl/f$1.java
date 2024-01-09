package com.chartboost.sdk.impl;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

class f$1 extends Drawable {
  boolean a = false;
  
  final f b;
  
  f$1(f paramf) {}
  
  public void draw(Canvas paramCanvas) {
    byte b = 0;
    float f1 = (this.b.getContext().getResources().getDisplayMetrics()).density;
    int[] arrayOfInt = getState();
    boolean bool = false;
    while (true) {
      Shader shader;
      if (b >= arrayOfInt.length) {
        float f2 = 6.0F * f1;
        f.a(this.b).reset();
        f.b(this.b).set(getBounds());
        f.a(this.b).addRoundRect(f.b(this.b), f2, f2, Path.Direction.CW);
        f.c(this.b);
        Paint paint = f.d(this.b);
        if (bool) {
          shader = f.e(this.b);
        } else {
          shader = f.f(this.b);
        } 
        paint.setShader(shader);
        paramCanvas.drawPath(f.a(this.b), f.d(this.b));
        f1 = 1.0F * f1;
        f.g(this.b).reset();
        f.b(this.b).inset(f1 / 2.0F, f1 / 2.0F);
        f.g(this.b).addRoundRect(f.b(this.b), f2, f2, Path.Direction.CW);
        f.h(this.b).reset();
        f.b(this.b).offset(0.0F, f1 / 2.0F);
        f.h(this.b).addRoundRect(f.b(this.b), f2, f2, Path.Direction.CW);
        if (!bool) {
          f.i(this.b).setColor(-1);
          paramCanvas.drawPath(f.h(this.b), f.i(this.b));
        } 
        f.i(this.b).setColor(-4496384);
        paramCanvas.drawPath(f.g(this.b), f.i(this.b));
        return;
      } 
      if (shader[b] == 16842919)
        bool = true; 
      b++;
    } 
  }
  
  public int getOpacity() {
    return 1;
  }
  
  public boolean isStateful() {
    return true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfint) {
    boolean bool1 = true;
    byte b = 0;
    boolean bool = false;
    while (true) {
      if (b >= paramArrayOfint.length) {
        if (this.a != bool) {
          this.a = bool;
          invalidateSelf();
          return bool1;
        } 
      } else {
        if (paramArrayOfint[b] == 16842919)
          bool = true; 
        b++;
        continue;
      } 
      return false;
    } 
  }
  
  public void setAlpha(int paramInt) {
    f.i(this.b).setAlpha(paramInt);
    f.d(this.b).setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    f.i(this.b).setColorFilter(paramColorFilter);
    f.d(this.b).setColorFilter(paramColorFilter);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\f$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */