package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.n;
import com.chartboost.sdk.impl.r;
import com.chartboost.sdk.impl.s;

public final class a {
  private Chartboost a;
  
  private Activity b;
  
  private r c;
  
  private boolean d = false;
  
  private boolean e = false;
  
  private boolean f = false;
  
  private s g;
  
  private s h;
  
  protected a(Chartboost paramChartboost, Activity paramActivity) {
    this.a = paramChartboost;
    this.b = paramActivity;
  }
  
  private void b() {
    this.c = new r((Context)this.b);
    this.h = new s((Context)this.b, (s.a)this.c);
    this.h.b().a(true);
    this.b.addContentView((View)this.h, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    this.h.b().a();
    this.h.b().a((View)this.c.getParent());
    this.d = true;
  }
  
  private void b(a parama) {
    if (!this.e) {
      parama.c = a.b.b;
      if (!parama.f.b()) {
        if (parama.f.d != null)
          parama.f.d.a(); 
        return;
      } 
      if (parama.i) {
        parama.i = false;
        this.g = new s((Context)this.b, parama.f.d());
        this.b.addContentView((View)this.g, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        parama.c = a.b.c;
        parama.h = this.g;
        this.e = true;
        return;
      } 
      this.g = new s((Context)this.b, parama.f.d());
      this.b.addContentView((View)this.g, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
      this.g.b().a();
      n.b b = n.b.b;
      if (parama.d == a.c.c)
        b = n.b.d; 
      if (parama.a.optInt("animation") != 0)
        b = n.b.values()[parama.a.optInt("animation")]; 
      parama.c = a.b.c;
      parama.h = this.g;
      n.a(b, parama);
      this.e = true;
      ChartboostDelegate chartboostDelegate = this.a.getDelegate();
      if (chartboostDelegate != null) {
        if (parama.d == a.c.b) {
          chartboostDelegate.didShowInterstitial(parama.e);
          return;
        } 
        if (parama.d == a.c.c)
          chartboostDelegate.didShowMoreApps(); 
      } 
    } 
  }
  
  private n.a c(a parama) {
    return new a$1(this);
  }
  
  protected void a(Activity paramActivity) {
    this.b = paramActivity;
  }
  
  protected void a(a$a parama$a) {
    if (parama$a.a) {
      b();
      return;
    } 
    if (parama$a.b != null)
      b(parama$a.b); 
  }
  
  public void a(a parama) {
    this.e = false;
    (new a$b(this, parama, true)).run();
    if (parama.c == a.b.c) {
      parama.c = a.b.b;
    } else {
      parama.c = a.b.a;
    } 
    parama.c();
    try {
      ((ViewGroup)this.g.getParent()).removeView((View)this.g);
    } catch (Exception exception) {}
  }
  
  public void a(a parama, boolean paramBoolean) {
    this.e = false;
    if (!paramBoolean)
      this.f = true; 
    parama.c = a.b.d;
    n.b b = n.b.b;
    if (parama.d == a.c.c)
      b = n.b.d; 
    if (parama.a.optInt("animation") != 0)
      b = n.b.values()[parama.a.optInt("animation")]; 
    n.b(b, parama, c(parama));
  }
  
  public void a(boolean paramBoolean) {
    if (a()) {
      try {
        ((ViewGroup)this.h.getParent()).removeView((View)this.h);
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
      this.c = null;
      this.h = null;
      this.d = false;
      if (!this.f && paramBoolean && this.a.getImpressionsUseActivities() && !this.e && this.b instanceof CBImpressionActivity) {
        this.a.c();
        this.b.finish();
      } 
    } 
  }
  
  protected boolean a() {
    return this.d;
  }
  
  public void b(a parama, boolean paramBoolean) {
    if (this.g != null) {
      try {
        ((ViewGroup)this.g.getParent()).removeView((View)this.g);
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
      parama.b();
      this.g = null;
      this.f = false;
      if (this.a.getImpressionsUseActivities() && !paramBoolean && !this.d && this.b instanceof CBImpressionActivity) {
        this.a.c();
        this.b.finish();
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */