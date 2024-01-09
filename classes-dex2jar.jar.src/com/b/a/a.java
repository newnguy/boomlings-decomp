package com.b.a;

import android.content.Context;
import android.os.Bundle;

public class a {
  f a;
  
  public a(f paramf) {
    this.a = paramf;
  }
  
  public void a(Context paramContext, d paramd) {
    a(paramContext, paramd, (Object)null);
  }
  
  public void a(Context paramContext, d paramd, Object paramObject) {
    (new b(this, paramContext, paramd, paramObject)).start();
  }
  
  public void a(String paramString, Bundle paramBundle, d paramd) {
    a(paramString, paramBundle, "GET", paramd, null);
  }
  
  public void a(String paramString1, Bundle paramBundle, String paramString2, d paramd, Object paramObject) {
    (new c(this, paramString1, paramBundle, paramString2, paramd, paramObject)).start();
  }
  
  public void a(String paramString, d paramd) {
    a(paramString, new Bundle(), "GET", paramd, null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\b\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */