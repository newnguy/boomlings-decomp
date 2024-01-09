package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.b;
import org.json.JSONObject;

public class b$a extends b.b {
  public ImageView c;
  
  public ImageView d;
  
  public Button e;
  
  public t f;
  
  final b g;
  
  private b$a(b paramb, Context paramContext) {
    super(paramb, paramContext);
    setBackgroundColor(0);
    setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    this.f = new t(paramContext);
    this.d = new ImageView(paramContext);
    this.e = new Button(paramContext);
    this.c = new ImageView(paramContext);
    this.e.setOnClickListener(new b$a$1(this));
    this.d.setClickable(true);
    this.d.setOnClickListener(new b$a$2(this));
    this.f.a((View)this.c);
    this.f.a((View)this.d);
    this.f.a((View)this.e);
    addView((View)this.f, (ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
  }
  
  private Point a(String paramString) {
    JSONObject jSONObject = b.a(this.g).optJSONObject(paramString);
    if (jSONObject != null) {
      jSONObject = jSONObject.optJSONObject("offset");
      if (jSONObject != null)
        return new Point(jSONObject.optInt("x", 0), jSONObject.optInt("y", 0)); 
    } 
    return new Point(0, 0);
  }
  
  protected void a(int paramInt1, int paramInt2) {
    float f1;
    com.chartboost.sdk.Libraries.a.a a1;
    com.chartboost.sdk.Libraries.a.a a2;
    String str2;
    String str1;
    boolean bool = Chartboost.sharedChartboost().orientation().isPortrait();
    if (bool) {
      a1 = this.g.h;
    } else {
      a1 = this.g.i;
    } 
    if (bool) {
      a2 = this.g.j;
    } else {
      a2 = this.g.k;
    } 
    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
    RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    if (bool) {
      f1 = Math.max(320.0F / paramInt1, 480.0F / paramInt2);
    } else {
      f1 = Math.max(320.0F / paramInt2, 480.0F / paramInt1);
    } 
    layoutParams2.width = (int)(a2.c() / f1);
    layoutParams2.height = (int)(a2.d() / f1);
    if (bool) {
      str2 = "frame-portrait";
    } else {
      str2 = "frame-landscape";
    } 
    Point point2 = a(str2);
    layoutParams2.leftMargin = Math.round((paramInt1 - layoutParams2.width) / 2.0F + point2.x / f1);
    float f2 = (paramInt2 - layoutParams2.height) / 2.0F;
    layoutParams2.topMargin = Math.round(point2.y / f1 + f2);
    this.d.setId(100);
    layoutParams3.width = (int)(a1.c() / f1);
    layoutParams3.height = (int)(a1.d() / f1);
    if (bool) {
      str1 = "ad-portrait";
    } else {
      str1 = "ad-landscape";
    } 
    Point point1 = a(str1);
    layoutParams3.leftMargin = Math.round((paramInt1 - layoutParams3.width) / 2.0F + point1.x / f1);
    f2 = (paramInt2 - layoutParams3.height) / 2.0F;
    layoutParams3.topMargin = Math.round(point1.y / f1 + f2);
    layoutParams1.width = (int)(this.g.l.c() / f1);
    layoutParams1.height = (int)(this.g.l.d() / f1);
    point1 = a("close");
    layoutParams1.leftMargin = layoutParams3.leftMargin + layoutParams3.width + Math.round(point1.x / f1 - d.b(10, getContext()));
    paramInt1 = layoutParams3.topMargin;
    paramInt2 = layoutParams1.height;
    layoutParams1.topMargin = Math.round(point1.y / f1 - d.b(10, getContext())) + paramInt1 - paramInt2;
    this.c.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
    this.d.setLayoutParams((ViewGroup.LayoutParams)layoutParams3);
    this.e.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(a2.b());
    this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
    this.c.setImageDrawable((Drawable)bitmapDrawable2);
    BitmapDrawable bitmapDrawable1 = new BitmapDrawable(a1.b());
    this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
    this.d.setImageDrawable((Drawable)bitmapDrawable1);
    bitmapDrawable1 = new BitmapDrawable(this.g.l.b());
    this.e.setBackgroundDrawable((Drawable)bitmapDrawable1);
  }
  
  public void b() {
    super.b();
    this.d = null;
    this.c = null;
    this.e = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\b$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */