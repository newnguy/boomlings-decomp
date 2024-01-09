package com.flurry.android;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

final class n extends AsyncTask {
  private Context a;
  
  private String b;
  
  private FlurryAdSize c;
  
  private ViewGroup d;
  
  private boolean e;
  
  private boolean f;
  
  private bo g;
  
  public n(bo parambo, Context paramContext, String paramString, FlurryAdSize paramFlurryAdSize, ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2) {
    this.a = paramContext;
    this.b = paramString;
    this.c = paramFlurryAdSize;
    this.d = paramViewGroup;
    this.e = paramBoolean2;
    this.f = paramBoolean1;
  }
  
  private List a() {
    List<?> list;
    try {
      int i = this.d.getWidth();
      int j = this.d.getHeight();
      list = this.g.a(this.b, i, j, false, this.c);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      list = Collections.emptyList();
    } 
    return list;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */