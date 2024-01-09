package com.flurry.android;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import org.apache.http.HttpResponse;

final class ai extends AsyncTask {
  private final String a = getClass().getSimpleName();
  
  private Context b;
  
  private String c;
  
  private bo d;
  
  public ai(bo parambo, Context paramContext, String paramString) {
    this.b = paramContext;
    this.c = paramString;
  }
  
  private String a() {
    StringBuilder stringBuilder = null;
    byte b = 0;
    while (true) {
      StringBuilder stringBuilder1 = stringBuilder;
      if (b < 5)
        try {
          if (Uri.parse(this.c).getScheme().equals("http")) {
            if (bo.c(this.b) == true && !bo.d(this.b)) {
              HttpResponse httpResponse = ac.a(bo.c(this.d), this.c, 10000, 15000, false);
              if (httpResponse != null) {
                int i = httpResponse.getStatusLine().getStatusCode();
                if (i == 200) {
                  stringBuilder1 = new StringBuilder();
                  this();
                  stringBuilder1.append("Redirect URL found for: ").append(this.c).toString();
                  return this.c;
                } 
                if (i >= 300 && i < 400) {
                  stringBuilder1 = new StringBuilder();
                  this();
                  stringBuilder1.append("NumRedirects: ").append(b + 1).toString();
                  if (httpResponse.containsHeader("Location"))
                    this.c = httpResponse.getFirstHeader("Location").getValue(); 
                } else {
                  stringBuilder1 = new StringBuilder();
                  this();
                  stringBuilder1.append("Bad Response status code: ").append(i).toString();
                  return (String)stringBuilder;
                } 
              } 
            } else {
              try {
                Thread.sleep(100L);
              } catch (InterruptedException interruptedException) {}
            } 
          } else {
            String str;
            stringBuilder1 = stringBuilder;
            if (bo.a(this.b, this.c, "android.intent.action.VIEW"))
              str = this.c; 
            return str;
          } 
          b++;
          continue;
        } catch (Throwable throwable) {
          throwable.printStackTrace();
          stringBuilder1 = stringBuilder;
        }  
      return (String)stringBuilder1;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */