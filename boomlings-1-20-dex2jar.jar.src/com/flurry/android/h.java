package com.flurry.android;

import android.content.Context;
import java.util.List;

final class h extends an {
  private Context a;
  
  private String b;
  
  private FlurryAdSize c;
  
  private bo d;
  
  h(bo parambo, Context paramContext, String paramString, FlurryAdSize paramFlurryAdSize) {}
  
  public final void a() {
    int j = ac.c(this.a);
    int i = ac.d(this.a);
    List list = this.d.a(this.b, j, i, false, this.c);
    this.d.b(list);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */