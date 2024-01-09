package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class aq {
    private static final Hashtable cache = new Hashtable();

    public static Typeface get(Context context, String str) {
        Typeface typeface;
        synchronized (cache) {
            if (!cache.containsKey(str)) {
                cache.put(str, Typeface.createFromAsset(context.getAssets(), str));
            }
            typeface = (Typeface) cache.get(str);
        }
        return typeface;
    }
}
