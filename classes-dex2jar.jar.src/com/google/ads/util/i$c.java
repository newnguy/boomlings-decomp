package com.google.ads.util;

public final class i$c extends i$a {
  final i d;
  
  private boolean e = false;
  
  public i$c(i parami, String paramString) {
    super(parami, paramString, (q)null);
    this.e = false;
  }
  
  public i$c(i parami, String paramString, Object paramObject) {
    super(parami, paramString, paramObject, null);
    this.e = false;
  }
  
  public Object a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield a : Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void a(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: aload_2
    //   11: ldc 'State changed - '
    //   13: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: aload_0
    //   17: getfield d : Lcom/google/ads/util/i;
    //   20: invokevirtual toString : ()Ljava/lang/String;
    //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: ldc '.'
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_0
    //   32: getfield b : Ljava/lang/String;
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: ldc ': ''
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_1
    //   44: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   47: ldc '' <-- ''
    //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: aload_0
    //   53: getfield a : Ljava/lang/Object;
    //   56: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   59: ldc ''.'
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual toString : ()Ljava/lang/String;
    //   67: invokestatic d : (Ljava/lang/String;)V
    //   70: aload_0
    //   71: aload_1
    //   72: putfield a : Ljava/lang/Object;
    //   75: aload_0
    //   76: iconst_1
    //   77: putfield e : Z
    //   80: aload_0
    //   81: monitorexit
    //   82: return
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	80	83	finally
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append(super.toString());
    if (this.e) {
      String str1 = " (*)";
      return stringBuilder.append(str1).toString();
    } 
    String str = "";
    return stringBuilder.append(str).toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\i$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */