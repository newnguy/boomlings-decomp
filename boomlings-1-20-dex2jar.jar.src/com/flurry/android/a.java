package com.flurry.android;

import android.content.Context;

final class a extends an {
  final Context a;
  
  final FlurryAgent b;
  
  private boolean c;
  
  a(FlurryAgent paramFlurryAgent, boolean paramBoolean, Context paramContext) {}
  
  public final void a() {
    FlurryAgent.b(this.b);
    FlurryAgent.c(this.b);
    if (!this.c)
      FlurryAgent.d(this.b).postDelayed(new u(this), FlurryAgent.e()); 
    if (FlurryAgent.f()) {
      bm.a("FlurryAgent", "Attempting to persist AdLogs");
      FlurryAgent.e(this.b).k();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */