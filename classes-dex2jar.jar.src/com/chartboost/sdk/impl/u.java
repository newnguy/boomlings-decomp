package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import java.lang.reflect.Method;

public class u extends View {
  private Handler a;
  
  private float b;
  
  private long c;
  
  private Paint d;
  
  private Paint e;
  
  private Path f;
  
  private Path g;
  
  private RectF h;
  
  private RectF i;
  
  private Bitmap j = null;
  
  private Canvas k = null;
  
  private Runnable l = new u$1(this);
  
  public u(Context paramContext) {
    super(paramContext);
    a(paramContext);
  }
  
  private void a(Context paramContext) {
    float f = (paramContext.getResources().getDisplayMetrics()).density;
    this.b = 0.0F;
    this.a = new Handler();
    this.c = (long)(System.nanoTime() / 1000000.0D);
    this.d = new Paint();
    this.d.setColor(-1);
    this.d.setStyle(Paint.Style.STROKE);
    this.d.setStrokeWidth(f * 3.0F);
    this.d.setAntiAlias(true);
    this.e = new Paint();
    this.e.setColor(-1);
    this.e.setStyle(Paint.Style.FILL);
    this.e.setAntiAlias(true);
    this.f = new Path();
    this.g = new Path();
    this.i = new RectF();
    this.h = new RectF();
    try {
      Method method = getClass().getMethod("setLayerType", new Class[] { int.class, Paint.class });
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(1);
      method.invoke(this, arrayOfObject);
    } catch (Exception exception) {}
  }
  
  protected void onAttachedToWindow() {
    this.a.removeCallbacks(this.l);
    this.a.post(this.l);
  }
  
  protected void onDetachedFromWindow() {
    this.a.removeCallbacks(this.l);
    if (this.j != null && !this.j.isRecycled())
      this.j.recycle(); 
    this.j = null;
  }
  
  protected void onDraw(Canvas paramCanvas) {
    int i;
    float f1 = (getContext().getResources().getDisplayMetrics()).density;
    if (this.j == null || this.j.getWidth() != paramCanvas.getWidth() || this.j.getHeight() != paramCanvas.getHeight()) {
      if (this.j != null && !this.j.isRecycled())
        this.j.recycle(); 
      this.j = Bitmap.createBitmap(paramCanvas.getWidth(), paramCanvas.getHeight(), Bitmap.Config.ARGB_8888);
      this.k = new Canvas(this.j);
    } 
    this.j.eraseColor(0);
    Canvas canvas = this.k;
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    canvas.save();
    if (difference.isReverse())
      canvas.rotate(180.0F, getWidth() / 2.0F, getHeight() / 2.0F); 
    this.h.set(0.0F, 0.0F, getWidth(), getHeight());
    this.h.inset(1.5F * f1, 1.5F * f1);
    if (difference.isOdd()) {
      i = getWidth();
    } else {
      i = getHeight();
    } 
    float f2 = i / 2.0F;
    canvas.drawRoundRect(this.h, f2, f2, this.d);
    this.i.set(this.h);
    this.i.inset(3.0F * f1, f1 * 3.0F);
    if (difference.isOdd()) {
      f1 = this.i.width();
    } else {
      f1 = this.i.height();
    } 
    f1 /= 2.0F;
    this.f.reset();
    this.f.addRoundRect(this.i, f1, f1, Path.Direction.CW);
    if (difference.isOdd()) {
      f1 = this.i.width();
    } else {
      f1 = this.i.height();
    } 
    this.g.reset();
    if (difference.isOdd()) {
      this.g.moveTo(f1, 0.0F);
      this.g.lineTo(f1, f1);
      this.g.lineTo(0.0F, f1 * 2.0F);
      this.g.lineTo(0.0F, f1);
    } else {
      this.g.moveTo(0.0F, f1);
      this.g.lineTo(f1, f1);
      this.g.lineTo(f1 * 2.0F, 0.0F);
      this.g.lineTo(f1, 0.0F);
    } 
    this.g.close();
    canvas.save();
    canvas.clipPath(this.f);
    for (f2 = -f1 + this.b;; f2 += 2.0F * f1) {
      float f;
      if (difference.isOdd()) {
        f = this.i.height();
      } else {
        f = this.i.width();
      } 
      if (f2 >= f + f1) {
        canvas.restore();
        canvas.restore();
        if (paramCanvas != null)
          paramCanvas.drawBitmap(this.j, 0.0F, 0.0F, null); 
        long l = Math.max(0L, 16L - (long)(System.nanoTime() / 1000000.0D) - this.c);
        this.a.removeCallbacks(this.l);
        this.a.postDelayed(this.l, l);
        return;
      } 
      if (difference.isOdd()) {
        f = this.i.top;
      } else {
        f = this.i.left;
      } 
      f += f2;
      canvas.save();
      if (difference.isOdd()) {
        canvas.translate(this.i.left, f);
      } else {
        canvas.translate(f, this.i.top);
      } 
      canvas.drawPath(this.g, this.e);
      canvas.restore();
    } 
  }
  
  protected void onWindowVisibilityChanged(int paramInt) {
    super.onWindowVisibilityChanged(paramInt);
    this.a.removeCallbacks(this.l);
    if (paramInt == 0)
      this.a.post(this.l); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\imp\\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */