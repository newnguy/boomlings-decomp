package net.robotmedia.billing.a;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class e {
  public static List a(Context paramContext) {
    // Byte code:
    //   0: ldc net/robotmedia/billing/a/e
    //   2: monitorenter
    //   3: new net/robotmedia/billing/a/a
    //   6: astore_1
    //   7: aload_1
    //   8: aload_0
    //   9: invokespecial <init> : (Landroid/content/Context;)V
    //   12: aload_1
    //   13: invokevirtual b : ()Landroid/database/Cursor;
    //   16: invokestatic a : (Landroid/database/Cursor;)Ljava/util/List;
    //   19: astore_0
    //   20: aload_1
    //   21: invokevirtual a : ()V
    //   24: ldc net/robotmedia/billing/a/e
    //   26: monitorexit
    //   27: aload_0
    //   28: areturn
    //   29: astore_0
    //   30: ldc net/robotmedia/billing/a/e
    //   32: monitorexit
    //   33: aload_0
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   3	24	29	finally
  }
  
  private static List a(Cursor paramCursor) {
    ArrayList<c> arrayList = new ArrayList();
    if (paramCursor != null)
      while (true) {
        if (!paramCursor.moveToNext()) {
          paramCursor.close();
          return arrayList;
        } 
        arrayList.add(a.a(paramCursor));
      }  
    return arrayList;
  }
  
  public static void a(Context paramContext, c paramc) {
    // Byte code:
    //   0: ldc net/robotmedia/billing/a/e
    //   2: monitorenter
    //   3: new net/robotmedia/billing/a/a
    //   6: astore_2
    //   7: aload_2
    //   8: aload_0
    //   9: invokespecial <init> : (Landroid/content/Context;)V
    //   12: aload_2
    //   13: aload_1
    //   14: invokevirtual a : (Lnet/robotmedia/billing/a/c;)V
    //   17: aload_2
    //   18: invokevirtual a : ()V
    //   21: ldc net/robotmedia/billing/a/e
    //   23: monitorexit
    //   24: return
    //   25: astore_0
    //   26: ldc net/robotmedia/billing/a/e
    //   28: monitorexit
    //   29: aload_0
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	25	finally
  }
  
  public static void b(Context paramContext, c paramc) {
    // Byte code:
    //   0: ldc net/robotmedia/billing/a/e
    //   2: monitorenter
    //   3: new net/robotmedia/billing/a/a
    //   6: astore_2
    //   7: aload_2
    //   8: aload_0
    //   9: invokespecial <init> : (Landroid/content/Context;)V
    //   12: aload_2
    //   13: aload_1
    //   14: invokevirtual b : (Lnet/robotmedia/billing/a/c;)V
    //   17: aload_2
    //   18: invokevirtual a : ()V
    //   21: ldc net/robotmedia/billing/a/e
    //   23: monitorexit
    //   24: return
    //   25: astore_0
    //   26: ldc net/robotmedia/billing/a/e
    //   28: monitorexit
    //   29: aload_0
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	25	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\a\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */