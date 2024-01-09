package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

public class Cocos2dxBitmap {
  private static final int HALIGNCENTER = 3;
  
  private static final int HALIGNLEFT = 1;
  
  private static final int HALIGNRIGHT = 2;
  
  private static final int VALIGNBOTTOM = 2;
  
  private static final int VALIGNCENTER = 3;
  
  private static final int VALIGNTOP = 1;
  
  private static Context context;
  
  private static ab computeTextProperty(String paramString, Paint paramPaint, int paramInt1, int paramInt2) {
    Paint.FontMetricsInt fontMetricsInt = paramPaint.getFontMetricsInt();
    int i = (int)Math.ceil((fontMetricsInt.bottom - fontMetricsInt.top));
    String[] arrayOfString = splitString(paramString, paramInt2, paramInt1, paramPaint);
    if (paramInt1 == 0) {
      int j = arrayOfString.length;
      paramInt2 = 0;
      paramInt1 = 0;
      while (true) {
        if (paramInt2 < j) {
          String str = arrayOfString[paramInt2];
          int k = (int)Math.ceil(paramPaint.measureText(str, 0, str.length()));
          if (k > paramInt1)
            paramInt1 = k; 
          paramInt2++;
          continue;
        } 
        return new ab(paramInt1, i, arrayOfString);
      } 
    } 
    return new ab(paramInt1, i, arrayOfString);
  }
  
  private static int computeX(Paint paramPaint, String paramString, int paramInt1, int paramInt2) {
    int i = paramInt1;
    switch (paramInt2 & 0xF) {
      default:
        i = 0;
      case 2:
        return i;
      case 3:
        i = paramInt1 / 2;
      case 1:
        break;
    } 
    i = 0;
  }
  
  private static int computeY(Paint.FontMetricsInt paramFontMetricsInt, int paramInt1, int paramInt2, int paramInt3) {
    int i = -paramFontMetricsInt.top;
    null = i;
    if (paramInt1 > paramInt2) {
      switch (paramInt3 >> 4 & 0xF) {
        default:
          return i;
        case 1:
          return -paramFontMetricsInt.top;
        case 3:
          return -paramFontMetricsInt.top + (paramInt1 - paramInt2) / 2;
        case 2:
          break;
      } 
    } else {
      return null;
    } 
    return -paramFontMetricsInt.top + paramInt1 - paramInt2;
  }
  
  public static void createTextBitmap(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    String str = refactorString(paramString1);
    Paint paint = newPaint(paramString2, paramInt1, paramInt2);
    ab ab = computeTextProperty(str, paint, paramInt3, paramInt4);
    if (paramInt4 == 0) {
      paramInt1 = ab.b;
    } else {
      paramInt1 = paramInt4;
    } 
    Bitmap bitmap = Bitmap.createBitmap(ab.a, paramInt1, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    paramInt1 = computeY(paint.getFontMetricsInt(), paramInt4, ab.b, paramInt2);
    String[] arrayOfString = ab.d;
    paramInt4 = arrayOfString.length;
    for (paramInt3 = 0;; paramInt3++) {
      if (paramInt3 >= paramInt4) {
        initNativeObject(bitmap);
        return;
      } 
      String str1 = arrayOfString[paramInt3];
      canvas.drawText(str1, computeX(paint, str1, ab.a, paramInt2), paramInt1, paint);
      paramInt1 += ab.c;
    } 
  }
  
  private static LinkedList divideStringWithMaxWidth(Paint paramPaint, String paramString, int paramInt) {
    int k = paramString.length();
    LinkedList<String> linkedList = new LinkedList();
    int i = 1;
    int j = 0;
    while (true) {
      int m;
      if (i > k) {
        if (j < k)
          linkedList.add(paramString.substring(j)); 
        return linkedList;
      } 
      int n = (int)Math.ceil(paramPaint.measureText(paramString, j, i));
      if (n >= paramInt) {
        m = paramString.substring(0, i).lastIndexOf(" ");
        if (m != -1 && m > j) {
          linkedList.add(paramString.substring(j, m));
          j = m;
        } else if (n > paramInt) {
          linkedList.add(paramString.substring(j, i - 1));
          j = i - 1;
        } else {
          linkedList.add(paramString.substring(j, i));
          m = i;
          j = m;
        } 
        i = j + 1;
        m = i;
        if (paramString.indexOf(j) != 32) {
          j = i;
          continue;
        } 
      } else {
        continue;
      } 
      j = m;
      i++;
    } 
  }
  
  private static byte[] getPixels(Bitmap paramBitmap) {
    if (paramBitmap != null) {
      byte[] arrayOfByte = new byte[paramBitmap.getWidth() * paramBitmap.getHeight() * 4];
      ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
      byteBuffer.order(ByteOrder.nativeOrder());
      paramBitmap.copyPixelsToBuffer(byteBuffer);
      return arrayOfByte;
    } 
    return null;
  }
  
  private static void initNativeObject(Bitmap paramBitmap) {
    byte[] arrayOfByte = getPixels(paramBitmap);
    if (arrayOfByte != null)
      nativeInitBitmapDC(paramBitmap.getWidth(), paramBitmap.getHeight(), arrayOfByte); 
  }
  
  private static native void nativeInitBitmapDC(int paramInt1, int paramInt2, byte[] paramArrayOfbyte);
  
  private static Paint newPaint(String paramString, int paramInt1, int paramInt2) {
    Paint paint = new Paint();
    paint.setColor(-1);
    paint.setTextSize(paramInt1);
    paint.setAntiAlias(true);
    if (paramString.endsWith(".ttf")) {
      try {
        paint.setTypeface(aq.get(context, paramString));
        switch (paramInt2 & 0xF) {
          default:
            paint.setTextAlign(Paint.Align.LEFT);
            return paint;
          case 3:
            paint.setTextAlign(Paint.Align.CENTER);
            return paint;
          case 1:
            paint.setTextAlign(Paint.Align.LEFT);
            return paint;
          case 2:
            break;
        } 
      } catch (Exception exception) {
        Log.e("Cocos2dxBitmap", "error to create ttf type face: " + paramString);
        paint.setTypeface(Typeface.create(paramString, 0));
        switch (paramInt2 & 0xF) {
          default:
            paint.setTextAlign(Paint.Align.LEFT);
            return paint;
          case 3:
            paint.setTextAlign(Paint.Align.CENTER);
            return paint;
          case 1:
            paint.setTextAlign(Paint.Align.LEFT);
            return paint;
          case 2:
            break;
        } 
      } 
    } else {
      paint.setTypeface(Typeface.create(paramString, 0));
      switch (paramInt2 & 0xF) {
        default:
          paint.setTextAlign(Paint.Align.LEFT);
          return paint;
        case 3:
          paint.setTextAlign(Paint.Align.CENTER);
          return paint;
        case 1:
          paint.setTextAlign(Paint.Align.LEFT);
          return paint;
        case 2:
          break;
      } 
    } 
    paint.setTextAlign(Paint.Align.RIGHT);
    return paint;
  }
  
  private static String refactorString(String paramString) {
    if (paramString.compareTo("") == 0)
      return " "; 
    StringBuilder stringBuilder = new StringBuilder(paramString);
    int j = stringBuilder.indexOf("\n");
    int i = 0;
    while (true) {
      if (j != -1) {
        if (j == 0 || stringBuilder.charAt(j - 1) == '\n') {
          stringBuilder.insert(i, " ");
          i = j + 2;
        } else {
          i = j + 1;
        } 
        if (i <= stringBuilder.length() && j != stringBuilder.length()) {
          j = stringBuilder.indexOf("\n", i);
          continue;
        } 
      } 
      return stringBuilder.toString();
    } 
  }
  
  public static void setContext(Context paramContext) {
    context = paramContext;
  }
  
  private static String[] splitString(String paramString, int paramInt1, int paramInt2, Paint paramPaint) {
    int i = 0;
    boolean bool = false;
    String[] arrayOfString2 = paramString.split("\\n");
    Paint.FontMetricsInt fontMetricsInt = paramPaint.getFontMetricsInt();
    int j = paramInt1 / (int)Math.ceil((fontMetricsInt.bottom - fontMetricsInt.top));
    if (paramInt2 != 0) {
      LinkedList<String> linkedList = new LinkedList();
      i = arrayOfString2.length;
      paramInt1 = bool;
      while (true) {
        if (paramInt1 < i) {
          String str = arrayOfString2[paramInt1];
          if ((int)Math.ceil(paramPaint.measureText(str)) > paramInt2) {
            linkedList.addAll(divideStringWithMaxWidth(paramPaint, str, paramInt2));
          } else {
            linkedList.add(str);
          } 
          if (j <= 0 || linkedList.size() < j) {
            paramInt1++;
            continue;
          } 
        } 
        if (j > 0) {
          if (linkedList.size() > j) {
            while (true) {
              if (linkedList.size() <= j) {
                String[] arrayOfString = new String[linkedList.size()];
                linkedList.toArray(arrayOfString);
                return arrayOfString;
              } 
              linkedList.removeLast();
            } 
            break;
          } 
          continue;
        } 
        continue;
      } 
    } 
    String[] arrayOfString1 = arrayOfString2;
    if (paramInt1 != 0) {
      arrayOfString1 = arrayOfString2;
      if (arrayOfString2.length > j) {
        LinkedList<String> linkedList = new LinkedList();
        for (paramInt1 = i;; paramInt1++) {
          if (paramInt1 >= j) {
            arrayOfString1 = new String[linkedList.size()];
            linkedList.toArray((Object[])arrayOfString1);
            return arrayOfString1;
          } 
          linkedList.add(arrayOfString2[paramInt1]);
        } 
      } 
    } 
    return arrayOfString1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */