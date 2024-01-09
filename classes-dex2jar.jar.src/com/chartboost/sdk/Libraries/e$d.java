package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.os.Environment;
import java.io.File;

class e$d {
  private File a = null;
  
  public e$d(Context paramContext) {
    try {
      boolean bool;
      if (paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool && Environment.getExternalStorageState().equals("mounted"))
        this.a = paramContext.getExternalFilesDir("cache"); 
      if (this.a != null && !this.a.exists())
        this.a.mkdirs(); 
    } catch (Exception exception) {
      this.a = null;
    } 
    if (this.a == null) {
      this.a = paramContext.getCacheDir();
      if (!this.a.exists())
        this.a.mkdirs(); 
    } 
  }
  
  public File a(String paramString) {
    return new File(this.a, paramString);
  }
  
  public void a() {
    try {
      File[] arrayOfFile = this.a.listFiles();
      if (arrayOfFile != null) {
        int i = arrayOfFile.length;
        byte b = 0;
        while (true) {
          if (b < i) {
            arrayOfFile[b].delete();
            b++;
            continue;
          } 
          return;
        } 
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\e$d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */