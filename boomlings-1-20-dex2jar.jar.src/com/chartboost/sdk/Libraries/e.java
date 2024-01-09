package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.chartboost.sdk.Chartboost;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class e {
  private static e a = null;
  
  private e$d b = new e$d(Chartboost.sharedChartboost().getContext());
  
  private a c = new a();
  
  public static e a() {
    // Byte code:
    //   0: ldc com/chartboost/sdk/Libraries/e
    //   2: monitorenter
    //   3: getstatic com/chartboost/sdk/Libraries/e.a : Lcom/chartboost/sdk/Libraries/e;
    //   6: ifnonnull -> 21
    //   9: new com/chartboost/sdk/Libraries/e
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/chartboost/sdk/Libraries/e.a : Lcom/chartboost/sdk/Libraries/e;
    //   21: getstatic com/chartboost/sdk/Libraries/e.a : Lcom/chartboost/sdk/Libraries/e;
    //   24: astore_0
    //   25: ldc com/chartboost/sdk/Libraries/e
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/chartboost/sdk/Libraries/e
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  private static e$a b(ImageView paramImageView) {
    if (paramImageView != null) {
      Drawable drawable = paramImageView.getDrawable();
      if (drawable instanceof e$c)
        return ((e$c)drawable).a(); 
    } 
    return null;
  }
  
  protected a$a a(String paramString) {
    BitmapFactory.Options options2 = null;
    String str = null;
    File file = this.b.a(String.valueOf(paramString) + ".png");
    if (!file.exists())
      return (a$a)str; 
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
    long l = file.length();
    if (l > 2147483647L)
      throw new IOException("Cannot read files larger than 2147483647 bytes"); 
    int i = (int)l;
    byte[] arrayOfByte = new byte[i];
    bufferedInputStream.read(arrayOfByte, 0, i);
    bufferedInputStream.close();
    BitmapFactory.Options options1 = new BitmapFactory.Options();
    options1.inJustDecodeBounds = true;
    BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, options1);
    BitmapFactory.Options options3 = new BitmapFactory.Options();
    options3.inJustDecodeBounds = false;
    options3.inDither = false;
    options3.inPurgeable = true;
    options3.inInputShareable = true;
    options3.inTempStorage = new byte[32768];
    options3.inSampleSize = 1;
    while (true) {
      String str1;
      if (options3.inSampleSize >= 64) {
        options1 = options2;
      } else {
        try {
          Bitmap bitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, options3);
          a$a a$a1 = new a$a(bitmap, options3.inSampleSize);
        } catch (OutOfMemoryError outOfMemoryError) {
          options3.inSampleSize *= 2;
          continue;
        } catch (Exception exception) {
          str1 = str;
        } 
        return (a$a)str1;
      } 
      a$a a$a = new a$a((Bitmap)str1, options3.inSampleSize);
    } 
  }
  
  public void a(String paramString1, String paramString2, e$b parame$b, ImageView paramImageView, Bundle paramBundle) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #9
    //   3: aconst_null
    //   4: astore #8
    //   6: aload #5
    //   8: ifnull -> 149
    //   11: aload #5
    //   13: ldc 'paramNoMemoryCache'
    //   15: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   18: istore #6
    //   20: iload #6
    //   22: ifne -> 43
    //   25: aload #9
    //   27: astore #8
    //   29: aload_0
    //   30: getfield c : Lcom/chartboost/sdk/Libraries/a;
    //   33: aload_2
    //   34: invokevirtual a : (Ljava/lang/String;)Lcom/chartboost/sdk/Libraries/a$a;
    //   37: astore #9
    //   39: aload #9
    //   41: astore #8
    //   43: aload #8
    //   45: astore #9
    //   47: aload #8
    //   49: ifnonnull -> 114
    //   52: aload_0
    //   53: aload_2
    //   54: invokevirtual a : (Ljava/lang/String;)Lcom/chartboost/sdk/Libraries/a$a;
    //   57: astore #10
    //   59: aload #10
    //   61: astore #9
    //   63: aload #10
    //   65: ifnull -> 114
    //   68: iload #6
    //   70: ifeq -> 155
    //   73: iconst_0
    //   74: istore #7
    //   76: aload #10
    //   78: astore #8
    //   80: aload #10
    //   82: iload #7
    //   84: invokevirtual a : (Z)V
    //   87: aload #10
    //   89: astore #9
    //   91: iload #6
    //   93: ifne -> 114
    //   96: aload #10
    //   98: astore #8
    //   100: aload_0
    //   101: getfield c : Lcom/chartboost/sdk/Libraries/a;
    //   104: aload_2
    //   105: aload #10
    //   107: invokevirtual a : (Ljava/lang/String;Lcom/chartboost/sdk/Libraries/a$a;)V
    //   110: aload #10
    //   112: astore #9
    //   114: aload #9
    //   116: ifnull -> 175
    //   119: aload #4
    //   121: ifnull -> 134
    //   124: aload #4
    //   126: aload #9
    //   128: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   131: invokevirtual setImageBitmap : (Landroid/graphics/Bitmap;)V
    //   134: aload_3
    //   135: ifnull -> 148
    //   138: aload_3
    //   139: aload #9
    //   141: aload #5
    //   143: invokeinterface a : (Lcom/chartboost/sdk/Libraries/a$a;Landroid/os/Bundle;)V
    //   148: return
    //   149: iconst_0
    //   150: istore #6
    //   152: goto -> 20
    //   155: iconst_1
    //   156: istore #7
    //   158: goto -> 76
    //   161: astore #9
    //   163: aload #9
    //   165: invokevirtual printStackTrace : ()V
    //   168: aload #8
    //   170: astore #9
    //   172: goto -> 114
    //   175: new com/chartboost/sdk/Libraries/e$a
    //   178: dup
    //   179: aload_0
    //   180: aload #4
    //   182: aload_3
    //   183: aload_2
    //   184: aload #5
    //   186: invokespecial <init> : (Lcom/chartboost/sdk/Libraries/e;Landroid/widget/ImageView;Lcom/chartboost/sdk/Libraries/e$b;Ljava/lang/String;Landroid/os/Bundle;)V
    //   189: iconst_1
    //   190: anewarray java/lang/String
    //   193: dup
    //   194: iconst_0
    //   195: aload_1
    //   196: aastore
    //   197: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   200: pop
    //   201: goto -> 148
    // Exception table:
    //   from	to	target	type
    //   29	39	161	java/lang/Exception
    //   52	59	161	java/lang/Exception
    //   80	87	161	java/lang/Exception
    //   100	110	161	java/lang/Exception
  }
  
  protected boolean a(String paramString, e$e parame$e) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_0
    //   4: getfield b : Lcom/chartboost/sdk/Libraries/e$d;
    //   7: new java/lang/StringBuilder
    //   10: dup
    //   11: aload_1
    //   12: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   15: invokespecial <init> : (Ljava/lang/String;)V
    //   18: ldc '.png'
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual toString : ()Ljava/lang/String;
    //   26: invokevirtual a : (Ljava/lang/String;)Ljava/io/File;
    //   29: astore #6
    //   31: new java/io/FileOutputStream
    //   34: astore_1
    //   35: aload_1
    //   36: aload #6
    //   38: invokespecial <init> : (Ljava/io/File;)V
    //   41: sipush #4096
    //   44: newarray byte
    //   46: astore #6
    //   48: aload_2
    //   49: aload #6
    //   51: invokevirtual read : ([B)I
    //   54: istore_3
    //   55: iload_3
    //   56: iconst_m1
    //   57: if_icmpne -> 74
    //   60: aload_1
    //   61: ifnull -> 68
    //   64: aload_1
    //   65: invokevirtual close : ()V
    //   68: iconst_1
    //   69: istore #4
    //   71: iload #4
    //   73: ireturn
    //   74: aload_1
    //   75: aload #6
    //   77: iconst_0
    //   78: iload_3
    //   79: invokevirtual write : ([BII)V
    //   82: goto -> 48
    //   85: astore_2
    //   86: iload #5
    //   88: istore #4
    //   90: aload_1
    //   91: ifnull -> 71
    //   94: aload_1
    //   95: invokevirtual close : ()V
    //   98: iload #5
    //   100: istore #4
    //   102: goto -> 71
    //   105: astore_1
    //   106: iload #5
    //   108: istore #4
    //   110: goto -> 71
    //   113: astore_2
    //   114: aconst_null
    //   115: astore_1
    //   116: aload_1
    //   117: ifnull -> 124
    //   120: aload_1
    //   121: invokevirtual close : ()V
    //   124: aload_2
    //   125: athrow
    //   126: astore_1
    //   127: goto -> 68
    //   130: astore_1
    //   131: goto -> 124
    //   134: astore_2
    //   135: goto -> 116
    //   138: astore_1
    //   139: aconst_null
    //   140: astore_1
    //   141: goto -> 86
    // Exception table:
    //   from	to	target	type
    //   31	41	138	java/lang/Exception
    //   31	41	113	finally
    //   41	48	85	java/lang/Exception
    //   41	48	134	finally
    //   48	55	85	java/lang/Exception
    //   48	55	134	finally
    //   64	68	126	java/lang/Exception
    //   74	82	85	java/lang/Exception
    //   74	82	134	finally
    //   94	98	105	java/lang/Exception
    //   120	124	130	java/lang/Exception
  }
  
  public void b() {
    this.b.a();
    this.c.a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */