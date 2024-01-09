package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.widget.Button;

public class f extends Button {
  private Path a;
  
  private Path b;
  
  private Path c;
  
  private RectF d;
  
  private Paint e;
  
  private Paint f;
  
  private Shader g;
  
  private Shader h;
  
  private int i;
  
  public f(Context paramContext) {
    super(paramContext);
    a(paramContext);
  }
  
  private void a() {
    int i = getHeight();
    if (i != this.i) {
      this.i = i;
      float f1 = getHeight();
      Shader.TileMode tileMode = Shader.TileMode.CLAMP;
      this.g = (Shader)new LinearGradient(0.0F, 0.0F, 0.0F, f1, new int[] { -81366, -89600, -97280 }, null, tileMode);
      f1 = getHeight();
      tileMode = Shader.TileMode.CLAMP;
      this.h = (Shader)new LinearGradient(0.0F, 0.0F, 0.0F, f1, new int[] { -97280, -89600, -81366 }, null, tileMode);
    } 
  }
  
  private void a(Context paramContext) {
    float f1 = (paramContext.getResources().getDisplayMetrics()).density;
    setTextSize(2, 13.0F);
    setShadowLayer(1.0F * f1, 1.0F * f1, 1.0F * f1, -16757901);
    setTypeface(null, 1);
    setTextColor(-1);
    setClickable(true);
    setGravity(17);
    setPadding(Math.round(12.0F * f1), Math.round(5.0F * f1), Math.round(12.0F * f1), Math.round(f1 * 5.0F));
    this.a = new Path();
    this.b = new Path();
    this.c = new Path();
    this.d = new RectF();
    this.e = new Paint();
    this.e.setStyle(Paint.Style.STROKE);
    this.e.setColor(-4496384);
    this.e.setAntiAlias(true);
    this.i = -1;
    this.f = new Paint();
    this.f.setStyle(Paint.Style.FILL);
    this.f.setAntiAlias(true);
    setBackgroundDrawable(new f$1(this));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */