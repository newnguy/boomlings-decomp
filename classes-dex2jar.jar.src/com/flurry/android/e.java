package com.flurry.android;

import android.content.Context;

final class e extends an {
  private Context a;
  
  private boolean b;
  
  private FlurryAgent c;
  
  e(FlurryAgent paramFlurryAgent, Context paramContext, boolean paramBoolean) {}
  
  public final void a() {
    if (!FlurryAgent.a(this.c))
      FlurryAgent.a(this.c, this.a); 
    FlurryAgent.a(this.c, this.a, this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */