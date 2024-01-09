package com.b.a;

import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

class c extends Thread {
  final a a;
  
  private final String b;
  
  private final Bundle c;
  
  private final String d;
  
  private final d e;
  
  private final Object f;
  
  c(a parama, String paramString1, Bundle paramBundle, String paramString2, d paramd, Object paramObject) {}
  
  public void run() {
    try {
      String str = this.a.a.a(this.b, this.c, this.d);
      this.e.onComplete(str, this.f);
    } catch (FileNotFoundException fileNotFoundException) {
      this.e.onFileNotFoundException(fileNotFoundException, this.f);
    } catch (MalformedURLException malformedURLException) {
      this.e.onMalformedURLException(malformedURLException, this.f);
    } catch (IOException iOException) {
      this.e.onIOException(iOException, this.f);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */