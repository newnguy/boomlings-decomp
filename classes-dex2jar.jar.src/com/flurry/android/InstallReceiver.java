package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InstallReceiver extends BroadcastReceiver {
  private final Handler a;
  
  private File b = null;
  
  public InstallReceiver() {
    HandlerThread handlerThread = new HandlerThread("InstallReceiver");
    handlerThread.start();
    this.a = new Handler(handlerThread.getLooper());
  }
  
  static Map a(String paramString) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    String[] arrayOfString = paramString.split("&");
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String[] arrayOfString1 = arrayOfString[b].split("=");
      if (arrayOfString1.length != 2) {
        bm.a("InstallReceiver", "Invalid referrer Element: " + arrayOfString[b] + " in referrer tag " + paramString);
      } else {
        String str1 = URLDecoder.decode(arrayOfString1[0]);
        String str2 = URLDecoder.decode(arrayOfString1[1]);
        if (hashMap.get(str1) == null)
          hashMap.put(str1, new ArrayList()); 
        ((List<String>)hashMap.get(str1)).add(str2);
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (hashMap.get("utm_source") == null)
      stringBuilder.append("Campaign Source is missing.\n"); 
    if (hashMap.get("utm_medium") == null)
      stringBuilder.append("Campaign Medium is missing.\n"); 
    if (hashMap.get("utm_campaign") == null)
      stringBuilder.append("Campaign Name is missing.\n"); 
    if (stringBuilder.length() > 0)
      Log.w("Detected missing referrer keys", stringBuilder.toString()); 
    return hashMap;
  }
  
  private static String b(File paramFile) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: new java/io/FileInputStream
    //   6: astore #4
    //   8: aload #4
    //   10: aload_0
    //   11: invokespecial <init> : (Ljava/io/File;)V
    //   14: aload #4
    //   16: astore_2
    //   17: new java/lang/StringBuffer
    //   20: astore_0
    //   21: aload #4
    //   23: astore_2
    //   24: aload_0
    //   25: invokespecial <init> : ()V
    //   28: aload #4
    //   30: astore_2
    //   31: sipush #1024
    //   34: newarray byte
    //   36: astore #6
    //   38: aload #4
    //   40: astore_2
    //   41: aload #4
    //   43: aload #6
    //   45: invokevirtual read : ([B)I
    //   48: istore_1
    //   49: iload_1
    //   50: ifle -> 115
    //   53: aload #4
    //   55: astore_2
    //   56: new java/lang/String
    //   59: astore_3
    //   60: aload #4
    //   62: astore_2
    //   63: aload_3
    //   64: aload #6
    //   66: iconst_0
    //   67: iload_1
    //   68: invokespecial <init> : ([BII)V
    //   71: aload #4
    //   73: astore_2
    //   74: aload_0
    //   75: aload_3
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   79: pop
    //   80: goto -> 38
    //   83: astore_3
    //   84: aload #4
    //   86: astore_2
    //   87: ldc 'InstallReceiver'
    //   89: ldc 'Error when loading persistent file'
    //   91: aload_3
    //   92: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   95: pop
    //   96: aload #4
    //   98: invokestatic a : (Ljava/io/Closeable;)V
    //   101: aload #5
    //   103: astore_2
    //   104: aload_0
    //   105: ifnull -> 113
    //   108: aload_0
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: astore_2
    //   113: aload_2
    //   114: areturn
    //   115: aload #4
    //   117: invokestatic a : (Ljava/io/Closeable;)V
    //   120: goto -> 101
    //   123: astore_0
    //   124: aconst_null
    //   125: astore_2
    //   126: aload_2
    //   127: invokestatic a : (Ljava/io/Closeable;)V
    //   130: aload_0
    //   131: athrow
    //   132: astore_0
    //   133: goto -> 126
    //   136: astore_3
    //   137: aconst_null
    //   138: astore_0
    //   139: aconst_null
    //   140: astore #4
    //   142: goto -> 84
    //   145: astore_3
    //   146: aconst_null
    //   147: astore_0
    //   148: goto -> 84
    // Exception table:
    //   from	to	target	type
    //   3	14	136	java/lang/Throwable
    //   3	14	123	finally
    //   17	21	145	java/lang/Throwable
    //   17	21	132	finally
    //   24	28	145	java/lang/Throwable
    //   24	28	132	finally
    //   31	38	83	java/lang/Throwable
    //   31	38	132	finally
    //   41	49	83	java/lang/Throwable
    //   41	49	132	finally
    //   56	60	83	java/lang/Throwable
    //   56	60	132	finally
    //   63	71	83	java/lang/Throwable
    //   63	71	132	finally
    //   74	80	83	java/lang/Throwable
    //   74	80	132	finally
    //   87	96	132	finally
  }
  
  private void b(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/android/bc
    //   5: astore_2
    //   6: aload_2
    //   7: aload_0
    //   8: aload_1
    //   9: invokespecial <init> : (Lcom/flurry/android/InstallReceiver;Ljava/lang/String;)V
    //   12: aload_0
    //   13: getfield a : Landroid/os/Handler;
    //   16: aload_2
    //   17: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   20: pop
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_1
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	24	finally
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    bm.c("InstallReceiver", "Received an Install nofication of " + paramIntent.getAction());
    this.b = paramContext.getFileStreamPath(".flurryinstallreceiver.");
    bm.c("InstallReceiver", "fInstallReceiverFile is " + this.b);
    if (FlurryAgent.isCaptureUncaughtExceptions())
      Thread.setDefaultUncaughtExceptionHandler(new FlurryAgent$FlurryDefaultExceptionHandler()); 
    String str2 = paramIntent.getExtras().getString("referrer");
    bm.c("InstallReceiver", "Received an Install referrer of " + str2);
    if (str2 == null || !"com.android.vending.INSTALL_REFERRER".equals(paramIntent.getAction())) {
      bm.c("InstallReceiver", "referrer is null");
      return;
    } 
    String str1 = str2;
    if (!str2.contains("=")) {
      bm.c("InstallReceiver", "referrer is before decoding: " + str2);
      str1 = URLDecoder.decode(str2);
      bm.c("InstallReceiver", "referrer is: " + str1);
    } 
    b(str1);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\InstallReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */