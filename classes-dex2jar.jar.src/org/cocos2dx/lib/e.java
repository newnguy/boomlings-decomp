package org.cocos2dx.lib;

import android.util.Log;
import com.b.a.d;
import com.b.a.m;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public abstract class e implements d {
  public void onFacebookError(m paramm, Object paramObject) {
    Log.e("Facebook", paramm.getMessage());
    paramm.printStackTrace();
  }
  
  public void onFileNotFoundException(FileNotFoundException paramFileNotFoundException, Object paramObject) {
    Log.e("Facebook", paramFileNotFoundException.getMessage());
    paramFileNotFoundException.printStackTrace();
  }
  
  public void onIOException(IOException paramIOException, Object paramObject) {
    Log.e("Facebook", paramIOException.getMessage());
    paramIOException.printStackTrace();
  }
  
  public void onMalformedURLException(MalformedURLException paramMalformedURLException, Object paramObject) {
    Log.e("Facebook", paramMalformedURLException.getMessage());
    paramMalformedURLException.printStackTrace();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */