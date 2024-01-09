package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class Cocos2dxBitmap {
    private static final int HALIGNCENTER = 3;
    private static final int HALIGNLEFT = 1;
    private static final int HALIGNRIGHT = 2;
    private static final int VALIGNBOTTOM = 2;
    private static final int VALIGNCENTER = 3;
    private static final int VALIGNTOP = 1;
    private static Context context;

    private static ab computeTextProperty(String str, Paint paint, int i, int i2) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = (int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top);
        String[] splitString = splitString(str, i2, i, paint);
        if (i == 0) {
            int length = splitString.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                String str2 = splitString[i3];
                int ceil2 = (int) Math.ceil(paint.measureText(str2, 0, str2.length()));
                if (ceil2 <= i4) {
                    ceil2 = i4;
                }
                i3++;
                i4 = ceil2;
            }
            i = i4;
        }
        return new ab(i, ceil, splitString);
    }

    private static int computeX(Paint paint, String str, int i, int i2) {
        switch (i2 & 15) {
            case 1:
                return 0;
            case 2:
                return i;
            case 3:
                return i / 2;
            default:
                return 0;
        }
    }

    private static int computeY(Paint.FontMetricsInt fontMetricsInt, int i, int i2, int i3) {
        int i4 = -fontMetricsInt.top;
        if (i > i2) {
            switch ((i3 >> 4) & 15) {
                case 1:
                    return -fontMetricsInt.top;
                case 2:
                    return (-fontMetricsInt.top) + (i - i2);
                case 3:
                    return (-fontMetricsInt.top) + ((i - i2) / 2);
                default:
                    return i4;
            }
        }
        return i4;
    }

    public static void createTextBitmap(String str, String str2, int i, int i2, int i3, int i4) {
        String[] strArr;
        String refactorString = refactorString(str);
        Paint newPaint = newPaint(str2, i, i2);
        ab computeTextProperty = computeTextProperty(refactorString, newPaint, i3, i4);
        Bitmap createBitmap = Bitmap.createBitmap(computeTextProperty.a, i4 == 0 ? computeTextProperty.b : i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int computeY = computeY(newPaint.getFontMetricsInt(), i4, computeTextProperty.b, i2);
        for (String str3 : computeTextProperty.d) {
            canvas.drawText(str3, computeX(newPaint, str3, computeTextProperty.a, i2), computeY, newPaint);
            computeY += computeTextProperty.c;
        }
        initNativeObject(createBitmap);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x005e -> B:13:0x003a). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.LinkedList divideStringWithMaxWidth(android.graphics.Paint r8, java.lang.String r9, int r10) {
        /*
            r3 = 0
            int r4 = r9.length()
            java.util.LinkedList r5 = new java.util.LinkedList
            r5.<init>()
            r1 = 1
            r2 = r3
        Lc:
            if (r1 <= r4) goto L18
            if (r2 >= r4) goto L17
            java.lang.String r0 = r9.substring(r2)
            r5.add(r0)
        L17:
            return r5
        L18:
            float r0 = r8.measureText(r9, r2, r1)
            double r6 = (double) r0
            double r6 = java.lang.Math.ceil(r6)
            int r6 = (int) r6
            if (r6 < r10) goto L60
            java.lang.String r0 = r9.substring(r3, r1)
            java.lang.String r7 = " "
            int r0 = r0.lastIndexOf(r7)
            r7 = -1
            if (r0 == r7) goto L49
            if (r0 <= r2) goto L49
            java.lang.String r1 = r9.substring(r2, r0)
            r5.add(r1)
        L3a:
            int r1 = r0 + 1
            int r0 = r9.indexOf(r0)
            r2 = 32
            if (r0 == r2) goto L5e
            r0 = r1
        L45:
            int r1 = r1 + 1
            r2 = r0
            goto Lc
        L49:
            if (r6 <= r10) goto L57
            int r0 = r1 + (-1)
            java.lang.String r0 = r9.substring(r2, r0)
            r5.add(r0)
            int r0 = r1 + (-1)
            goto L3a
        L57:
            java.lang.String r0 = r9.substring(r2, r1)
            r5.add(r0)
        L5e:
            r0 = r1
            goto L3a
        L60:
            r0 = r2
            goto L45
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.Cocos2dxBitmap.divideStringWithMaxWidth(android.graphics.Paint, java.lang.String, int):java.util.LinkedList");
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap != null) {
            byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.order(ByteOrder.nativeOrder());
            bitmap.copyPixelsToBuffer(wrap);
            return bArr;
        }
        return null;
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels == null) {
            return;
        }
        nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
    }

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    private static Paint newPaint(String str, int i, int i2) {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setTextSize(i);
        paint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                paint.setTypeface(aq.get(context, str));
            } catch (Exception e) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                paint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            paint.setTypeface(Typeface.create(str, 0));
        }
        switch (i2 & 15) {
            case 1:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
            case 2:
                paint.setTextAlign(Paint.Align.RIGHT);
                break;
            case 3:
                paint.setTextAlign(Paint.Align.CENTER);
                break;
            default:
                paint.setTextAlign(Paint.Align.LEFT);
                break;
        }
        return paint;
    }

    private static String refactorString(String str) {
        if (str.compareTo("") == 0) {
            return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        for (int indexOf = sb.indexOf("\n"); indexOf != -1; indexOf = sb.indexOf("\n", i)) {
            if (indexOf == 0 || sb.charAt(indexOf - 1) == '\n') {
                sb.insert(i, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                i = indexOf + 2;
            } else {
                i = indexOf + 1;
            }
            if (i > sb.length() || indexOf == sb.length()) {
                break;
            }
        }
        return sb.toString();
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    private static String[] splitString(String str, int i, int i2, Paint paint) {
        int i3 = 0;
        String[] split = str.split("\\n");
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int ceil = i / ((int) Math.ceil(fontMetricsInt.bottom - fontMetricsInt.top));
        if (i2 == 0) {
            if (i == 0 || split.length <= ceil) {
                return split;
            }
            LinkedList linkedList = new LinkedList();
            while (i3 < ceil) {
                linkedList.add(split[i3]);
                i3++;
            }
            String[] strArr = new String[linkedList.size()];
            linkedList.toArray(strArr);
            return strArr;
        }
        LinkedList linkedList2 = new LinkedList();
        int length = split.length;
        while (i3 < length) {
            String str2 = split[i3];
            if (((int) Math.ceil(paint.measureText(str2))) > i2) {
                linkedList2.addAll(divideStringWithMaxWidth(paint, str2, i2));
            } else {
                linkedList2.add(str2);
            }
            if (ceil > 0 && linkedList2.size() >= ceil) {
                break;
            }
            i3++;
        }
        if (ceil > 0 && linkedList2.size() > ceil) {
            while (linkedList2.size() > ceil) {
                linkedList2.removeLast();
            }
        }
        String[] strArr2 = new String[linkedList2.size()];
        linkedList2.toArray(strArr2);
        return strArr2;
    }
}
