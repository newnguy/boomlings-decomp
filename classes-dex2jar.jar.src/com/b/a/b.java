package com.b.a;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

class b extends Thread {
  final a a;
  
  private final Context b;
  
  private final d c;
  
  private final Object d;
  
  b(a parama, Context paramContext, d paramd, Object paramObject) {}
  
  public void run() {
    try {
      m m;
      String str = this.a.a.a(this.b);
      if (str.length() == 0 || str.equals("false")) {
        d d1 = this.c;
        m = new m();
        this("auth.expireSession failed");
        d1.onFacebookError(m, this.d);
        return;
      } 
      this.c.onComplete((String)m, this.d);
    } catch (FileNotFoundException fileNotFoundException) {
      this.c.onFileNotFoundException(fileNotFoundException, this.d);
    } catch (MalformedURLException malformedURLException) {
      this.c.onMalformedURLException(malformedURLException, this.d);
    } catch (IOException iOException) {
      this.c.onIOException(iOException, this.d);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\b\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */