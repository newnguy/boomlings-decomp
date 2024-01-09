package org.cocos2dx.lib;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.http.util.ByteArrayBuffer;

class q implements Runnable {
  private final String a;
  
  private final String b;
  
  private final String c;
  
  q(String paramString1, String paramString2, String paramString3) {}
  
  public void run() {
    try {
      File file = new File();
      StringBuilder stringBuilder1 = new StringBuilder();
      this(String.valueOf(this.a));
      this(stringBuilder1.append(this.b).toString());
      if (file.exists())
        file.delete(); 
      URL uRL = new URL();
      this(this.c);
      file.createNewFile();
      Log.d("ImageManager", "download begining");
      StringBuilder stringBuilder2 = new StringBuilder();
      this("download url:");
      Log.d("ImageManager", stringBuilder2.append(uRL).toString());
      stringBuilder2 = new StringBuilder();
      this("downloaded file name:");
      Log.d("ImageManager", stringBuilder2.append(this.b).toString());
      InputStream inputStream = uRL.openConnection().getInputStream();
      BufferedInputStream bufferedInputStream = new BufferedInputStream();
      this(inputStream);
      ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer();
      this(50);
      while (true) {
        int i = bufferedInputStream.read();
        if (i == -1) {
          FileOutputStream fileOutputStream = new FileOutputStream();
          this(file);
          fileOutputStream.write(byteArrayBuffer.toByteArray());
          fileOutputStream.close();
          JniToCpp.promoImageDownloaded();
          return;
        } 
        byteArrayBuffer.append((byte)i);
      } 
    } catch (IOException iOException) {
      Log.d("ImageManager", "Error: " + iOException);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */