package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

final class x extends RelativeLayout {
  private bo a;
  
  private Context b;
  
  private String c;
  
  private FlurryAdSize d;
  
  private ViewGroup e;
  
  private int f;
  
  private boolean g;
  
  x(bo parambo, Context paramContext, String paramString, ViewGroup paramViewGroup) {
    this(parambo, paramContext, paramString, paramViewGroup, FlurryAdSize.BANNER_BOTTOM);
  }
  
  private x(bo parambo, Context paramContext, String paramString, ViewGroup paramViewGroup, FlurryAdSize paramFlurryAdSize) {
    super(paramContext);
    this.a = parambo;
    this.b = paramContext;
    this.c = paramString;
    this.e = paramViewGroup;
    this.f = 0;
    this.g = false;
    this.d = paramFlurryAdSize;
  }
  
  final int a() {
    return this.f;
  }
  
  final void a(int paramInt) {
    this.f = paramInt;
  }
  
  final void a(boolean paramBoolean) {
    this.g = paramBoolean;
  }
  
  final boolean b() {
    return this.g;
  }
  
  final ViewGroup c() {
    return this.e;
  }
  
  final String d() {
    return this.c;
  }
  
  final r e() {
    if (getChildCount() < 1)
      return null; 
    View view = getChildAt(0);
    try {
      r r = (r)view;
    } catch (ClassCastException classCastException) {
      classCastException = null;
    } 
    return (r)classCastException;
  }
  
  final void f() {
    r r = e();
    if (r != null && r.a())
      this.a.a(this.b, this.c, this.d, this.e, 1L); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */