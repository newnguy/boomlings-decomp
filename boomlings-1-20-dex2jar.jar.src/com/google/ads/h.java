package com.google.ads;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.google.ads.internal.h;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.util.a;
import java.util.HashMap;

public class h {
  final h a;
  
  private final f b;
  
  private boolean c;
  
  private boolean d;
  
  private g$a e;
  
  private final e f;
  
  private MediationAdapter g;
  
  private boolean h;
  
  private boolean i;
  
  private View j;
  
  private final Handler k;
  
  private final String l;
  
  private final AdRequest m;
  
  private final HashMap n;
  
  public h(e parame, h paramh, f paramf, String paramString, AdRequest paramAdRequest, HashMap paramHashMap) {
    a.b(TextUtils.isEmpty(paramString));
    this.f = parame;
    this.a = paramh;
    this.b = paramf;
    this.l = paramString;
    this.m = paramAdRequest;
    this.n = paramHashMap;
    this.c = false;
    this.d = false;
    this.e = null;
    this.g = null;
    this.h = false;
    this.i = false;
    this.j = null;
    this.k = new Handler(Looper.getMainLooper());
  }
  
  public f a() {
    return this.b;
  }
  
  public void a(Activity paramActivity) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield h : Z
    //   6: ldc 'startLoadAdTask has already been called.'
    //   8: invokestatic b : (ZLjava/lang/String;)V
    //   11: aload_0
    //   12: iconst_1
    //   13: putfield h : Z
    //   16: aload_0
    //   17: getfield k : Landroid/os/Handler;
    //   20: astore_3
    //   21: new com/google/ads/aw
    //   24: astore_2
    //   25: aload_2
    //   26: aload_0
    //   27: aload_1
    //   28: aload_0
    //   29: getfield l : Ljava/lang/String;
    //   32: aload_0
    //   33: getfield m : Lcom/google/ads/AdRequest;
    //   36: aload_0
    //   37: getfield n : Ljava/util/HashMap;
    //   40: invokespecial <init> : (Lcom/google/ads/h;Landroid/app/Activity;Ljava/lang/String;Lcom/google/ads/AdRequest;Ljava/util/HashMap;)V
    //   43: aload_3
    //   44: aload_2
    //   45: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   48: pop
    //   49: aload_0
    //   50: monitorexit
    //   51: return
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Exception table:
    //   from	to	target	type
    //   2	49	52	finally
  }
  
  void a(View paramView) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield j : Landroid/view/View;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  void a(MediationAdapter paramMediationAdapter) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield g : Lcom/google/ads/mediation/MediationAdapter;
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  void a(boolean paramBoolean, g$a paramg$a) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield d : Z
    //   7: aload_0
    //   8: iconst_1
    //   9: putfield c : Z
    //   12: aload_0
    //   13: aload_2
    //   14: putfield e : Lcom/google/ads/g$a;
    //   17: aload_0
    //   18: invokevirtual notify : ()V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: astore_2
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	24	finally
  }
  
  public void b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield h : Z
    //   6: ldc 'destroy() called but startLoadAdTask has not been called.'
    //   8: invokestatic a : (ZLjava/lang/String;)V
    //   11: aload_0
    //   12: getfield k : Landroid/os/Handler;
    //   15: astore_1
    //   16: new com/google/ads/au
    //   19: astore_2
    //   20: aload_2
    //   21: aload_0
    //   22: invokespecial <init> : (Lcom/google/ads/h;)V
    //   25: aload_1
    //   26: aload_2
    //   27: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   30: pop
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   2	31	34	finally
  }
  
  public boolean c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public boolean d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Z
    //   6: ldc 'isLoadAdTaskSuccessful() called when isLoadAdTaskDone() is false.'
    //   8: invokestatic a : (ZLjava/lang/String;)V
    //   11: aload_0
    //   12: getfield d : Z
    //   15: istore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: iload_1
    //   19: ireturn
    //   20: astore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_2
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	20	finally
  }
  
  public g$a e() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield e : Lcom/google/ads/g$a;
    //   6: ifnonnull -> 17
    //   9: getstatic com/google/ads/g$a.d : Lcom/google/ads/g$a;
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: getfield e : Lcom/google/ads/g$a;
    //   21: astore_1
    //   22: goto -> 13
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	25	finally
    //   17	22	25	finally
  }
  
  public View f() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield c : Z
    //   6: ldc 'getAdView() called when isLoadAdTaskDone() is false.'
    //   8: invokestatic a : (ZLjava/lang/String;)V
    //   11: aload_0
    //   12: getfield j : Landroid/view/View;
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	20	finally
  }
  
  public void g() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield a : Lcom/google/ads/internal/h;
    //   6: invokevirtual a : ()Z
    //   9: invokestatic a : (Z)V
    //   12: aload_0
    //   13: getfield g : Lcom/google/ads/mediation/MediationAdapter;
    //   16: checkcast com/google/ads/mediation/MediationInterstitialAdapter
    //   19: astore_1
    //   20: aload_0
    //   21: getfield k : Landroid/os/Handler;
    //   24: astore_2
    //   25: new com/google/ads/av
    //   28: astore_3
    //   29: aload_3
    //   30: aload_0
    //   31: aload_1
    //   32: invokespecial <init> : (Lcom/google/ads/h;Lcom/google/ads/mediation/MediationInterstitialAdapter;)V
    //   35: aload_2
    //   36: aload_3
    //   37: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   40: pop
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: ldc 'In Ambassador.show(): ambassador.adapter does not implement the MediationInterstitialAdapter interface.'
    //   47: aload_1
    //   48: invokestatic b : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   51: goto -> 41
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	54	finally
    //   12	41	44	java/lang/ClassCastException
    //   12	41	54	finally
    //   45	51	54	finally
  }
  
  public String h() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Lcom/google/ads/mediation/MediationAdapter;
    //   6: ifnull -> 24
    //   9: aload_0
    //   10: getfield g : Lcom/google/ads/mediation/MediationAdapter;
    //   13: invokevirtual getClass : ()Ljava/lang/Class;
    //   16: invokevirtual getName : ()Ljava/lang/String;
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: areturn
    //   24: ldc '"adapter was not created."'
    //   26: astore_1
    //   27: goto -> 20
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	30	finally
  }
  
  MediationAdapter i() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield g : Lcom/google/ads/mediation/MediationAdapter;
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
  
  e j() {
    return this.f;
  }
  
  void k() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield i : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  boolean l() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield i : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */