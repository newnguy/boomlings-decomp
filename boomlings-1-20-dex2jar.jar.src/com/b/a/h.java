package com.b.a;

import android.content.Context;
import android.os.AsyncTask;

class h extends AsyncTask {
  final f a;
  
  private final String b;
  
  private final Context c;
  
  public h(f paramf, String paramString, Context paramContext) {
    this.b = paramString;
    this.c = paramContext.getApplicationContext();
  }
  
  protected Void a(Void... paramVarArgs) {
    try {
      f.a(this.a, this.b, this.c);
    } catch (Exception exception) {
      s.a("Facebook-publish", exception.getMessage());
    } 
    return null;
  }
  
  protected void a(Void paramVoid) {
    synchronized (this.a) {
      f.a(this.a, (h)null);
      return;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */