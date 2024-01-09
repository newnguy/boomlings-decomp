package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class MediationAdRequest {
  private AdRequest a;
  
  private boolean b;
  
  private boolean c;
  
  public MediationAdRequest(AdRequest paramAdRequest, Context paramContext, boolean paramBoolean) {
    this.a = paramAdRequest;
    this.c = paramBoolean;
    if (paramContext == null) {
      this.b = true;
      return;
    } 
    this.b = paramAdRequest.b(paramContext);
  }
  
  public AdRequest.Gender a() {
    return this.a.a();
  }
  
  public Date b() {
    return this.a.b();
  }
  
  public Set c() {
    return (this.a.c() == null) ? null : Collections.unmodifiableSet(this.a.c());
  }
  
  public Location d() {
    return (this.a.d() == null || !this.c) ? null : new Location(this.a.d());
  }
  
  public boolean e() {
    return this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\MediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */