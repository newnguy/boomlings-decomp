package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

public class t extends AbsoluteLayout {
  private Matrix a = new Matrix();
  
  private Matrix b = new Matrix();
  
  private float[] c = new float[2];
  
  private View d;
  
  private RelativeLayout e;
  
  public t(Context paramContext) {
    super(paramContext);
    this.e = new RelativeLayout(paramContext);
    addView((View)this.e, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    this.d = (View)this.e;
  }
  
  public t(Context paramContext, View paramView) {
    super(paramContext);
    addView(paramView, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    this.d = paramView;
  }
  
  public View a() {
    return this.d;
  }
  
  public void a(View paramView) {
    a(paramView, new RelativeLayout.LayoutParams(-2, -2));
  }
  
  public void a(View paramView, RelativeLayout.LayoutParams paramLayoutParams) {
    if (this.e != null) {
      this.e.addView(paramView, (ViewGroup.LayoutParams)paramLayoutParams);
      return;
    } 
    throw new IllegalStateException("cannot call addViewToContainer() on CBRotatableContainer that was set up with a default view");
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {
    // Byte code:
    //   0: invokestatic sharedChartboost : ()Lcom/chartboost/sdk/Chartboost;
    //   3: invokevirtual getForcedOrientationDifference : ()Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   6: astore #8
    //   8: aload #8
    //   10: invokevirtual getAsInt : ()I
    //   13: istore_3
    //   14: aload #8
    //   16: getstatic com/chartboost/sdk/Libraries/CBOrientation$Difference.ANGLE_0 : Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   19: if_acmpne -> 28
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial dispatchDraw : (Landroid/graphics/Canvas;)V
    //   27: return
    //   28: aload_1
    //   29: invokevirtual save : ()I
    //   32: pop
    //   33: aload_1
    //   34: fconst_0
    //   35: fconst_0
    //   36: aload_0
    //   37: invokevirtual getWidth : ()I
    //   40: i2f
    //   41: aload_0
    //   42: invokevirtual getHeight : ()I
    //   45: i2f
    //   46: getstatic android/graphics/Region$Op.REPLACE : Landroid/graphics/Region$Op;
    //   49: invokevirtual clipRect : (FFFFLandroid/graphics/Region$Op;)Z
    //   52: pop
    //   53: aload_0
    //   54: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   57: checkcast android/view/ViewGroup
    //   60: astore #6
    //   62: aload #6
    //   64: invokevirtual getParent : ()Landroid/view/ViewParent;
    //   67: checkcast android/view/ViewGroup
    //   70: astore #7
    //   72: aload #7
    //   74: instanceof android/widget/ScrollView
    //   77: ifne -> 92
    //   80: aload #7
    //   82: instanceof android/widget/HorizontalScrollView
    //   85: istore #5
    //   87: iload #5
    //   89: ifeq -> 96
    //   92: aload #7
    //   94: astore #6
    //   96: aload_0
    //   97: invokevirtual getLeft : ()I
    //   100: aload #6
    //   102: invokevirtual getScrollX : ()I
    //   105: isub
    //   106: istore #4
    //   108: aload_0
    //   109: invokevirtual getTop : ()I
    //   112: aload #6
    //   114: invokevirtual getScrollY : ()I
    //   117: isub
    //   118: istore_2
    //   119: aload_1
    //   120: iconst_0
    //   121: iload #4
    //   123: isub
    //   124: i2f
    //   125: iconst_0
    //   126: iload_2
    //   127: isub
    //   128: i2f
    //   129: aload #6
    //   131: invokevirtual getWidth : ()I
    //   134: iload #4
    //   136: isub
    //   137: i2f
    //   138: aload #6
    //   140: invokevirtual getHeight : ()I
    //   143: iload_2
    //   144: isub
    //   145: i2f
    //   146: getstatic android/graphics/Region$Op.INTERSECT : Landroid/graphics/Region$Op;
    //   149: invokevirtual clipRect : (FFFFLandroid/graphics/Region$Op;)Z
    //   152: pop
    //   153: aload_1
    //   154: aload_0
    //   155: invokevirtual getWidth : ()I
    //   158: i2f
    //   159: fconst_2
    //   160: fdiv
    //   161: aload_0
    //   162: invokevirtual getHeight : ()I
    //   165: i2f
    //   166: fconst_2
    //   167: fdiv
    //   168: invokevirtual translate : (FF)V
    //   171: aload_1
    //   172: iload_3
    //   173: i2f
    //   174: invokevirtual rotate : (F)V
    //   177: aload #8
    //   179: invokevirtual isOdd : ()Z
    //   182: ifeq -> 237
    //   185: aload_1
    //   186: aload_0
    //   187: invokevirtual getHeight : ()I
    //   190: ineg
    //   191: i2f
    //   192: fconst_2
    //   193: fdiv
    //   194: aload_0
    //   195: invokevirtual getWidth : ()I
    //   198: ineg
    //   199: i2f
    //   200: fconst_2
    //   201: fdiv
    //   202: invokevirtual translate : (FF)V
    //   205: aload_0
    //   206: aload_1
    //   207: invokevirtual getMatrix : ()Landroid/graphics/Matrix;
    //   210: putfield a : Landroid/graphics/Matrix;
    //   213: aload_0
    //   214: getfield a : Landroid/graphics/Matrix;
    //   217: aload_0
    //   218: getfield b : Landroid/graphics/Matrix;
    //   221: invokevirtual invert : (Landroid/graphics/Matrix;)Z
    //   224: pop
    //   225: aload_0
    //   226: aload_1
    //   227: invokespecial dispatchDraw : (Landroid/graphics/Canvas;)V
    //   230: aload_1
    //   231: invokevirtual restore : ()V
    //   234: goto -> 27
    //   237: aload_1
    //   238: aload_0
    //   239: invokevirtual getWidth : ()I
    //   242: ineg
    //   243: i2f
    //   244: fconst_2
    //   245: fdiv
    //   246: aload_0
    //   247: invokevirtual getHeight : ()I
    //   250: ineg
    //   251: i2f
    //   252: fconst_2
    //   253: fdiv
    //   254: invokevirtual translate : (FF)V
    //   257: goto -> 205
    //   260: astore #6
    //   262: goto -> 153
    //   265: astore #7
    //   267: goto -> 96
    // Exception table:
    //   from	to	target	type
    //   53	62	260	java/lang/Exception
    //   62	87	265	java/lang/Exception
    //   96	153	260	java/lang/Exception
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (Chartboost.sharedChartboost().getForcedOrientationDifference() == CBOrientation.Difference.ANGLE_0)
      return super.dispatchTouchEvent(paramMotionEvent); 
    float[] arrayOfFloat = this.c;
    arrayOfFloat[0] = paramMotionEvent.getRawX();
    arrayOfFloat[1] = paramMotionEvent.getRawY();
    this.b.mapPoints(arrayOfFloat);
    paramMotionEvent.setLocation(arrayOfFloat[0], arrayOfFloat[1]);
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
      super.onMeasure(paramInt2, paramInt1);
      setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
      return;
    } 
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
    if (difference.isOdd()) {
      paramInt3 = paramInt2;
    } else {
      paramInt3 = paramInt1;
    } 
    layoutParams.width = paramInt3;
    if (!difference.isOdd())
      paramInt1 = paramInt2; 
    layoutParams.height = paramInt1;
    this.d.setLayoutParams(layoutParams);
    this.d.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
    this.d.layout(0, 0, layoutParams.width, layoutParams.height);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */