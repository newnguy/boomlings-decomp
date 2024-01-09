package com.google.ads.util;

import android.content.Context;
import android.util.DisplayMetrics;
import com.flurry.org.apache.avro.util.ByteBufferOutputStream;

/* loaded from: classes.dex */
public final class e {
    private static int a(Context context, float f, int i) {
        return (context.getApplicationInfo().flags & ByteBufferOutputStream.BUFFER_SIZE) != 0 ? (int) (i / f) : i;
    }

    public static int a(Context context, DisplayMetrics displayMetrics) {
        return a(context, displayMetrics.density, displayMetrics.heightPixels);
    }

    public static int b(Context context, DisplayMetrics displayMetrics) {
        return a(context, displayMetrics.density, displayMetrics.widthPixels);
    }
}
