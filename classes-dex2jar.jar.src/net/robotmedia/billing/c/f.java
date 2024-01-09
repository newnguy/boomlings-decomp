package net.robotmedia.billing.c;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

public class f {
  private static String a = null;
  
  public static String a(Context paramContext) {
    // Byte code:
    //   0: ldc net/robotmedia/billing/c/f
    //   2: monitorenter
    //   3: getstatic net/robotmedia/billing/c/f.a : Ljava/lang/String;
    //   6: ifnonnull -> 41
    //   9: new java/io/File
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokevirtual getFilesDir : ()Ljava/io/File;
    //   18: ldc 'INSTALLATION'
    //   20: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   23: aload_1
    //   24: invokevirtual exists : ()Z
    //   27: ifne -> 34
    //   30: aload_1
    //   31: invokestatic b : (Ljava/io/File;)V
    //   34: aload_1
    //   35: invokestatic a : (Ljava/io/File;)Ljava/lang/String;
    //   38: putstatic net/robotmedia/billing/c/f.a : Ljava/lang/String;
    //   41: getstatic net/robotmedia/billing/c/f.a : Ljava/lang/String;
    //   44: astore_0
    //   45: ldc net/robotmedia/billing/c/f
    //   47: monitorexit
    //   48: aload_0
    //   49: areturn
    //   50: astore_0
    //   51: new java/lang/RuntimeException
    //   54: astore_1
    //   55: aload_1
    //   56: aload_0
    //   57: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   60: aload_1
    //   61: athrow
    //   62: astore_0
    //   63: ldc net/robotmedia/billing/c/f
    //   65: monitorexit
    //   66: aload_0
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   3	23	62	finally
    //   23	34	50	java/lang/Exception
    //   23	34	62	finally
    //   34	41	50	java/lang/Exception
    //   34	41	62	finally
    //   41	45	62	finally
    //   51	62	62	finally
  }
  
  private static String a(File paramFile) {
    RandomAccessFile randomAccessFile = new RandomAccessFile(paramFile, "r");
    byte[] arrayOfByte = new byte[(int)randomAccessFile.length()];
    randomAccessFile.readFully(arrayOfByte);
    randomAccessFile.close();
    return new String(arrayOfByte);
  }
  
  private static void b(File paramFile) {
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
    fileOutputStream.write(UUID.randomUUID().toString().getBytes());
    fileOutputStream.close();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\c\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */