package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class g extends ImageView {
  private RectF a;
  
  private Paint b;
  
  private Xfermode c;
  
  public g(Context paramContext) {
    super(paramContext);
    a(paramContext);
  }
  
  private void a(Context paramContext) {
    float f = (getContext().getResources().getDisplayMetrics()).density;
    this.c = (Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    this.a = new RectF();
    this.b = new Paint();
    this.b.setStyle(Paint.Style.STROKE);
    this.b.setColor(-1509949440);
    this.b.setStrokeWidth(Math.max(1.0F, f * 1.0F));
    this.b.setAntiAlias(true);
  }
  
  protected void onDraw(Canvas paramCanvas) {
    float f2 = (getContext().getResources().getDisplayMetrics()).density;
    float f1 = 10.0F * f2;
    f2 = 1.0F * f2;
    Drawable drawable = getDrawable();
    if (drawable instanceof BitmapDrawable) {
      Paint paint = ((BitmapDrawable)drawable).getPaint();
      Rect rect = drawable.getBounds();
      this.a.set(rect);
      if (getImageMatrix() != null)
        getImageMatrix().mapRect(this.a); 
      int i = paramCanvas.saveLayer(this.a, null, 31);
      paint.setAntiAlias(true);
      paramCanvas.drawARGB(0, 0, 0, 0);
      paint.setColor(-16777216);
      paramCanvas.drawRoundRect(this.a, f1, f1, paint);
      Xfermode xfermode = paint.getXfermode();
      paint.setXfermode(this.c);
      super.onDraw(paramCanvas);
      paint.setXfermode(xfermode);
      paramCanvas.restoreToCount(i);
    } else {
      super.onDraw(paramCanvas);
    } 
    this.a.set(0.0F, 0.0F, getWidth(), getHeight());
    this.a.inset(f2 / 2.0F, f2 / 2.0F);
    paramCanvas.drawRoundRect(this.a, f1, f1, this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */