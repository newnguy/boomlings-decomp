package com.google.ads.internal;

import android.os.SystemClock;
import com.google.ads.util.b;
import java.util.LinkedList;

public class g {
  private static long f = 0L;
  
  private static long g = 0L;
  
  private static long h = 0L;
  
  private static long i = 0L;
  
  private static long j = -1L;
  
  private final LinkedList a = new LinkedList();
  
  private long b;
  
  private long c;
  
  private long d;
  
  private final LinkedList e = new LinkedList();
  
  private boolean k = false;
  
  private boolean l = false;
  
  private String m;
  
  private long n;
  
  private final LinkedList o = new LinkedList();
  
  private final LinkedList p = new LinkedList();
  
  public g() {
    a();
  }
  
  public static long E() {
    if (j == -1L) {
      j = SystemClock.elapsedRealtime();
      return 0L;
    } 
    return SystemClock.elapsedRealtime() - j;
  }
  
  protected boolean A() {
    return this.l;
  }
  
  protected void B() {
    b.d("Interstitial no fill.");
    this.l = true;
  }
  
  public void C() {
    b.d("Landing page dismissed.");
    this.e.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }
  
  protected String D() {
    return this.m;
  }
  
  protected void a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield a : Ljava/util/LinkedList;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: lconst_0
    //   11: putfield b : J
    //   14: aload_0
    //   15: lconst_0
    //   16: putfield c : J
    //   19: aload_0
    //   20: lconst_0
    //   21: putfield d : J
    //   24: aload_0
    //   25: getfield e : Ljava/util/LinkedList;
    //   28: invokevirtual clear : ()V
    //   31: aload_0
    //   32: ldc2_w -1
    //   35: putfield n : J
    //   38: aload_0
    //   39: getfield o : Ljava/util/LinkedList;
    //   42: invokevirtual clear : ()V
    //   45: aload_0
    //   46: getfield p : Ljava/util/LinkedList;
    //   49: invokevirtual clear : ()V
    //   52: aload_0
    //   53: iconst_0
    //   54: putfield k : Z
    //   57: aload_0
    //   58: iconst_0
    //   59: putfield l : Z
    //   62: aload_0
    //   63: monitorexit
    //   64: return
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    // Exception table:
    //   from	to	target	type
    //   2	62	65	finally
  }
  
  public void a(com.google.ads.g.a parama) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield o : Ljava/util/LinkedList;
    //   6: invokestatic elapsedRealtime : ()J
    //   9: aload_0
    //   10: getfield n : J
    //   13: lsub
    //   14: invokestatic valueOf : (J)Ljava/lang/Long;
    //   17: invokevirtual add : (Ljava/lang/Object;)Z
    //   20: pop
    //   21: aload_0
    //   22: getfield p : Ljava/util/LinkedList;
    //   25: aload_1
    //   26: invokevirtual add : (Ljava/lang/Object;)Z
    //   29: pop
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	33	finally
  }
  
  public void a(String paramString) {
    b.d("Prior impression ticket = " + paramString);
    this.m = paramString;
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield o : Ljava/util/LinkedList;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: getfield p : Ljava/util/LinkedList;
    //   13: invokevirtual clear : ()V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  public void c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokestatic elapsedRealtime : ()J
    //   6: putfield n : J
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
  
  public String d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: astore_3
    //   6: aload_3
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: getfield o : Ljava/util/LinkedList;
    //   14: invokevirtual iterator : ()Ljava/util/Iterator;
    //   17: astore #4
    //   19: aload #4
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 71
    //   29: aload #4
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast java/lang/Long
    //   39: invokevirtual longValue : ()J
    //   42: lstore_1
    //   43: aload_3
    //   44: invokevirtual length : ()I
    //   47: ifle -> 57
    //   50: aload_3
    //   51: ldc ','
    //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_3
    //   58: lload_1
    //   59: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: goto -> 19
    //   66: astore_3
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_3
    //   70: athrow
    //   71: aload_3
    //   72: invokevirtual toString : ()Ljava/lang/String;
    //   75: astore_3
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_3
    //   79: areturn
    // Exception table:
    //   from	to	target	type
    //   2	19	66	finally
    //   19	57	66	finally
    //   57	63	66	finally
    //   71	76	66	finally
  }
  
  public String e() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/StringBuilder
    //   5: astore_2
    //   6: aload_2
    //   7: invokespecial <init> : ()V
    //   10: aload_0
    //   11: getfield p : Ljava/util/LinkedList;
    //   14: invokevirtual iterator : ()Ljava/util/Iterator;
    //   17: astore_3
    //   18: aload_3
    //   19: invokeinterface hasNext : ()Z
    //   24: ifeq -> 68
    //   27: aload_3
    //   28: invokeinterface next : ()Ljava/lang/Object;
    //   33: checkcast com/google/ads/g$a
    //   36: astore_1
    //   37: aload_2
    //   38: invokevirtual length : ()I
    //   41: ifle -> 51
    //   44: aload_2
    //   45: ldc ','
    //   47: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: aload_2
    //   52: aload_1
    //   53: invokevirtual ordinal : ()I
    //   56: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: goto -> 18
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    //   68: aload_2
    //   69: invokevirtual toString : ()Ljava/lang/String;
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: areturn
    // Exception table:
    //   from	to	target	type
    //   2	18	63	finally
    //   18	51	63	finally
    //   51	60	63	finally
    //   68	73	63	finally
  }
  
  protected void f() {
    b.d("Ad clicked.");
    this.a.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }
  
  protected void g() {
    b.d("Ad request loaded.");
    this.b = SystemClock.elapsedRealtime();
  }
  
  protected void h() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'Ad request before rendering.'
    //   4: invokestatic d : (Ljava/lang/String;)V
    //   7: aload_0
    //   8: invokestatic elapsedRealtime : ()J
    //   11: putfield c : J
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	17	finally
  }
  
  protected void i() {
    b.d("Ad request started.");
    this.d = SystemClock.elapsedRealtime();
    f++;
  }
  
  protected long j() {
    return (this.a.size() != this.e.size()) ? -1L : this.a.size();
  }
  
  protected String k() {
    if (this.a.isEmpty() || this.a.size() != this.e.size())
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < this.a.size(); b++) {
      if (b != 0)
        stringBuilder.append(","); 
      stringBuilder.append(Long.toString(((Long)this.e.get(b)).longValue() - ((Long)this.a.get(b)).longValue()));
    } 
    return stringBuilder.toString();
  }
  
  protected String l() {
    if (this.a.isEmpty())
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < this.a.size(); b++) {
      if (b != 0)
        stringBuilder.append(","); 
      stringBuilder.append(Long.toString(((Long)this.a.get(b)).longValue() - this.b));
    } 
    return stringBuilder.toString();
  }
  
  protected long m() {
    return this.b - this.d;
  }
  
  protected long n() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : J
    //   6: lstore_3
    //   7: aload_0
    //   8: getfield d : J
    //   11: lstore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: lload_3
    //   15: lload_1
    //   16: lsub
    //   17: lreturn
    //   18: astore #5
    //   20: aload_0
    //   21: monitorexit
    //   22: aload #5
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	18	finally
  }
  
  protected long o() {
    return f;
  }
  
  protected long p() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/ads/internal/g.g : J
    //   5: lstore_1
    //   6: aload_0
    //   7: monitorexit
    //   8: lload_1
    //   9: lreturn
    //   10: astore_3
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_3
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	10	finally
  }
  
  protected void q() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'Ad request network error'
    //   4: invokestatic d : (Ljava/lang/String;)V
    //   7: getstatic com/google/ads/internal/g.g : J
    //   10: lconst_1
    //   11: ladd
    //   12: putstatic com/google/ads/internal/g.g : J
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  protected void r() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lconst_0
    //   3: putstatic com/google/ads/internal/g.g : J
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	9	finally
  }
  
  protected long s() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/ads/internal/g.h : J
    //   5: lstore_1
    //   6: aload_0
    //   7: monitorexit
    //   8: lload_1
    //   9: lreturn
    //   10: astore_3
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_3
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	10	finally
  }
  
  protected void t() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/ads/internal/g.h : J
    //   5: lconst_1
    //   6: ladd
    //   7: putstatic com/google/ads/internal/g.h : J
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	13	finally
  }
  
  protected void u() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lconst_0
    //   3: putstatic com/google/ads/internal/g.h : J
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	9	finally
  }
  
  protected long v() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/ads/internal/g.i : J
    //   5: lstore_1
    //   6: aload_0
    //   7: monitorexit
    //   8: lload_1
    //   9: lreturn
    //   10: astore_3
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_3
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	10	finally
  }
  
  protected void w() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/ads/internal/g.i : J
    //   5: lconst_1
    //   6: ladd
    //   7: putstatic com/google/ads/internal/g.i : J
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	13	finally
  }
  
  protected void x() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: lconst_0
    //   3: putstatic com/google/ads/internal/g.i : J
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: athrow
    // Exception table:
    //   from	to	target	type
    //   2	6	9	finally
  }
  
  protected boolean y() {
    return this.k;
  }
  
  protected void z() {
    b.d("Interstitial network error.");
    this.k = true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */