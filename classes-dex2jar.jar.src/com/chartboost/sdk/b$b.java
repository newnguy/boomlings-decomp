package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;
import com.chartboost.sdk.impl.s;

public abstract class b$b extends RelativeLayout implements s.a {
  protected boolean a = false;
  
  final b b;
  
  public b$b(b paramb, Context paramContext) {
    super(paramContext);
    setFocusableInTouchMode(true);
    requestFocus();
    setOnTouchListener(new b$b$1(this));
  }
  
  private boolean b(int paramInt1, int paramInt2) {
    boolean bool;
    try {
      a(paramInt1, paramInt2);
      bool = true;
    } catch (Exception exception) {
      bool = false;
    } 
    return bool;
  }
  
  public void a() {
    a((Activity)getContext());
  }
  
  protected abstract void a(int paramInt1, int paramInt2);
  
  public boolean a(Activity paramActivity) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getWidth : ()I
    //   4: istore #4
    //   6: aload_0
    //   7: invokevirtual getHeight : ()I
    //   10: istore_3
    //   11: iload #4
    //   13: ifeq -> 22
    //   16: iload_3
    //   17: istore_2
    //   18: iload_3
    //   19: ifne -> 64
    //   22: aload_1
    //   23: invokevirtual getWindow : ()Landroid/view/Window;
    //   26: ldc 16908290
    //   28: invokevirtual findViewById : (I)Landroid/view/View;
    //   31: astore #6
    //   33: aload #6
    //   35: astore #5
    //   37: aload #6
    //   39: ifnonnull -> 51
    //   42: aload_1
    //   43: invokevirtual getWindow : ()Landroid/view/Window;
    //   46: invokevirtual getDecorView : ()Landroid/view/View;
    //   49: astore #5
    //   51: aload #5
    //   53: invokevirtual getWidth : ()I
    //   56: istore #4
    //   58: aload #5
    //   60: invokevirtual getHeight : ()I
    //   63: istore_2
    //   64: iload #4
    //   66: ifeq -> 75
    //   69: iload_2
    //   70: istore_3
    //   71: iload_2
    //   72: ifne -> 111
    //   75: new android/util/DisplayMetrics
    //   78: dup
    //   79: invokespecial <init> : ()V
    //   82: astore #5
    //   84: aload_1
    //   85: invokevirtual getWindowManager : ()Landroid/view/WindowManager;
    //   88: invokeinterface getDefaultDisplay : ()Landroid/view/Display;
    //   93: aload #5
    //   95: invokevirtual getMetrics : (Landroid/util/DisplayMetrics;)V
    //   98: aload #5
    //   100: getfield widthPixels : I
    //   103: istore #4
    //   105: aload #5
    //   107: getfield heightPixels : I
    //   110: istore_3
    //   111: invokestatic sharedChartboost : ()Lcom/chartboost/sdk/Chartboost;
    //   114: invokevirtual getForcedOrientationDifference : ()Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   117: invokevirtual isOdd : ()Z
    //   120: ifeq -> 143
    //   123: iload #4
    //   125: istore_2
    //   126: aload_0
    //   127: iload_3
    //   128: iload_2
    //   129: invokespecial b : (II)Z
    //   132: ireturn
    //   133: astore #5
    //   135: iconst_0
    //   136: istore_2
    //   137: iconst_0
    //   138: istore #4
    //   140: goto -> 64
    //   143: iload_3
    //   144: istore_2
    //   145: iload #4
    //   147: istore_3
    //   148: goto -> 126
    // Exception table:
    //   from	to	target	type
    //   0	11	133	java/lang/Exception
    //   22	33	133	java/lang/Exception
    //   42	51	133	java/lang/Exception
    //   51	64	133	java/lang/Exception
  }
  
  public void b() {}
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (!this.a) {
      if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
        b(paramInt2, paramInt1);
        return;
      } 
      b(paramInt1, paramInt2);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\b$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */