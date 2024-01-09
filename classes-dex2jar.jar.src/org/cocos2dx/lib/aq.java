package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;

public class aq {
  private static final Hashtable cache = new Hashtable<Object, Object>();
  
  public static Typeface get(Context paramContext, String paramString) {
    synchronized (cache) {
      if (!cache.containsKey(paramString)) {
        Typeface typeface = Typeface.createFromAsset(paramContext.getAssets(), paramString);
        cache.put(paramString, typeface);
      } 
      return (Typeface)cache.get(paramString);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */