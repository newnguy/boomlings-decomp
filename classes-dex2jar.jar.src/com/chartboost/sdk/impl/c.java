package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.widget.LinearLayout;

public abstract class c extends LinearLayout implements h$b {
  protected View.OnClickListener a = null;
  
  private RectF b = new RectF();
  
  private Paint c = null;
  
  private Paint d = null;
  
  private RectF e = null;
  
  public c(Context paramContext) {
    super(paramContext);
  }
  
  private void a(Canvas paramCanvas, RectF paramRectF) {
    if (this.d == null) {
      this.d = new Paint();
      this.d.setStyle(Paint.Style.FILL);
      this.d.setAntiAlias(true);
    } 
    if (this.e == null || Math.abs(paramRectF.top - this.e.top) > 0.1F || Math.abs(paramRectF.bottom - this.e.bottom) > 0.1F)
      this.d.setShader((Shader)new LinearGradient(0.0F, paramRectF.top, 0.0F, paramRectF.bottom, -1447447, -2302756, Shader.TileMode.CLAMP)); 
    paramCanvas.drawRect(paramRectF, this.d);
  }
  
  private void b(Canvas paramCanvas, RectF paramRectF) {
    if (this.c == null) {
      this.c = new Paint();
      this.c.setStyle(Paint.Style.FILL);
      this.c.setAntiAlias(true);
    } 
    this.c.setColor(-723724);
    paramCanvas.drawRect(paramRectF.left, paramRectF.top, paramRectF.right, paramRectF.top + 1.0F, this.c);
    this.c.setColor(-3355444);
    paramCanvas.drawRect(paramRectF.left, paramRectF.bottom - 1.0F, paramRectF.right, paramRectF.bottom, this.c);
  }
  
  protected void onDraw(Canvas paramCanvas) {
    this.b.set(0.0F, 0.0F, getWidth(), a());
    a(paramCanvas, this.b);
    b(paramCanvas, this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */