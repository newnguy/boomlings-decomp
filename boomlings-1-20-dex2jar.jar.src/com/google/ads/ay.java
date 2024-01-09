package com.google.ads;

import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.util.a;
import com.google.ads.util.b;

class ay implements MediationBannerListener {
  private final h a;
  
  private boolean b;
  
  public ay(h paramh) {
    this.a = paramh;
  }
  
  public void a(MediationBannerAdapter paramMediationBannerAdapter) {
    synchronized (this.a) {
      a.a(paramMediationBannerAdapter, this.a.i());
      try {
        this.a.a(paramMediationBannerAdapter.d());
        if (!this.a.c()) {
          this.b = false;
          this.a.a(true, g$a.a);
          return;
        } 
      } catch (Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        b.b(stringBuilder.append("Error while getting banner View from adapter (").append(this.a.h()).append("): ").toString(), throwable);
        if (!this.a.c())
          this.a.a(false, g$a.f); 
        return;
      } 
    } 
    this.b = true;
    this.a.j().a(this.a, this.a.f());
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_2} */
  }
  
  public void a(MediationBannerAdapter paramMediationBannerAdapter, AdRequest$ErrorCode paramAdRequest$ErrorCode) {
    synchronized (this.a) {
      a.a(paramMediationBannerAdapter, this.a.i());
      StringBuilder stringBuilder = new StringBuilder();
      this();
      b.a(stringBuilder.append("Mediation adapter ").append(paramMediationBannerAdapter.getClass().getName()).append(" failed to receive ad with error code: ").append(paramAdRequest$ErrorCode).toString());
      if (!this.a.c()) {
        g$a g$a;
        h h1 = this.a;
        if (paramAdRequest$ErrorCode == AdRequest$ErrorCode.b) {
          g$a = g$a.b;
        } else {
          g$a = g$a.c;
        } 
        h1.a(false, g$a);
      } 
      return;
    } 
  }
  
  public void b(MediationBannerAdapter paramMediationBannerAdapter) {
    synchronized (this.a) {
      this.a.j().a(this.a);
      return;
    } 
  }
  
  public void c(MediationBannerAdapter paramMediationBannerAdapter) {
    synchronized (this.a) {
      this.a.j().b(this.a);
      return;
    } 
  }
  
  public void d(MediationBannerAdapter paramMediationBannerAdapter) {
    synchronized (this.a) {
      this.a.j().c(this.a);
      return;
    } 
  }
  
  public void e(MediationBannerAdapter paramMediationBannerAdapter) {
    synchronized (this.a) {
      a.a(this.a.c());
      this.a.j().a(this.a, this.b);
      return;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */