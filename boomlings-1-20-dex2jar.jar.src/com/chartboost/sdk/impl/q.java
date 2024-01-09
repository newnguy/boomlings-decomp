package com.chartboost.sdk.impl;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class q {
  private ScrollView a;
  
  private HorizontalScrollView b;
  
  private ViewGroup c;
  
  private LinearLayout d;
  
  private BaseAdapter e;
  
  private List f;
  
  private List g;
  
  private List h;
  
  private int i;
  
  private DataSetObserver j = new q$1(this);
  
  public q(Context paramContext, int paramInt) {
    this.d = new LinearLayout(paramContext);
    this.i = paramInt;
    this.d.setOrientation(paramInt);
    if (paramInt == 0) {
      this.c = (ViewGroup)a(paramContext);
    } else {
      this.c = (ViewGroup)b(paramContext);
    } 
    this.c.addView((View)this.d);
    this.f = new ArrayList();
    this.g = new ArrayList();
    this.h = new ArrayList();
    this.d.setOnTouchListener(new q$2(this));
  }
  
  private HorizontalScrollView a(Context paramContext) {
    if (this.b == null) {
      this.b = new HorizontalScrollView(paramContext);
      this.b.setFillViewport(true);
    } 
    return this.b;
  }
  
  private ScrollView b(Context paramContext) {
    if (this.a == null) {
      this.a = new ScrollView(paramContext);
      this.a.setFillViewport(true);
    } 
    return this.a;
  }
  
  private Context d() {
    return this.d.getContext();
  }
  
  public ViewGroup a() {
    return this.c;
  }
  
  public void a(int paramInt) {
    if (paramInt != this.i) {
      this.i = paramInt;
      this.d.setOrientation(paramInt);
      this.c.removeView((View)this.d);
      if (paramInt == 0) {
        this.c = (ViewGroup)a(d());
      } else {
        this.c = (ViewGroup)b(d());
      } 
      this.c.addView((View)this.d);
    } 
  }
  
  public void a(BaseAdapter paramBaseAdapter) {
    if (this.e != null)
      this.e.unregisterDataSetObserver(this.j); 
    this.e = paramBaseAdapter;
    this.e.registerDataSetObserver(this.j);
    this.d.removeAllViews();
    this.f.clear();
    this.g.clear();
    for (byte b = 0;; b++) {
      if (b >= this.e.getViewTypeCount()) {
        this.h.clear();
        this.e.notifyDataSetChanged();
        return;
      } 
      this.f.add(new ArrayList());
      this.g.add(new ArrayList());
    } 
  }
  
  public LinearLayout b() {
    return this.d;
  }
  
  public void c() {
    if (this.c == this.a && this.a != null) {
      this.a.fullScroll(130);
      return;
    } 
    if (this.c == this.b && this.b != null)
      this.b.fullScroll(66); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */