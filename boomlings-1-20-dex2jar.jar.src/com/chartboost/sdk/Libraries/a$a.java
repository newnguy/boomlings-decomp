package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;

public class a$a {
  private Bitmap a;
  
  private int b;
  
  private boolean c;
  
  public a$a(Bitmap paramBitmap, int paramInt) {
    a(paramBitmap);
    a(paramInt);
    a(true);
  }
  
  public void a() {
    if (!this.c)
      try {
        if (this.a != null && !this.a.isRecycled())
          this.a.recycle(); 
      } catch (Exception exception) {} 
  }
  
  public void a(int paramInt) {
    this.b = paramInt;
  }
  
  public void a(Bitmap paramBitmap) {
    this.a = paramBitmap;
  }
  
  public void a(boolean paramBoolean) {
    this.c = paramBoolean;
  }
  
  public Bitmap b() {
    return this.a;
  }
  
  public int c() {
    return this.a.getWidth() * this.b;
  }
  
  public int d() {
    return this.a.getHeight() * this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\a$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */