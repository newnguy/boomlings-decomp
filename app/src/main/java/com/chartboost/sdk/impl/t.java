package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* loaded from: classes.dex */
public class t extends AbsoluteLayout {
    private Matrix a;
    private Matrix b;
    private float[] c;
    private View d;
    private RelativeLayout e;

    public t(Context context) {
        super(context);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        this.e = new RelativeLayout(context);
        addView(this.e, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = this.e;
    }

    public t(Context context, View view) {
        super(context);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new float[2];
        addView(view, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = view;
    }

    public View a() {
        return this.d;
    }

    public void a(View view) {
        a(view, new RelativeLayout.LayoutParams(-2, -2));
    }

    public void a(View view, RelativeLayout.LayoutParams layoutParams) {
        if (this.e == null) {
            throw new IllegalStateException("cannot call addViewToContainer() on CBRotatableContainer that was set up with a default view");
        }
        this.e.addView(view, layoutParams);
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x003d, code lost:
        if ((r1 instanceof android.widget.HorizontalScrollView) != false) goto L23;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void dispatchDraw(android.graphics.Canvas r10) {
        /*
            r9 = this;
            r1 = 0
            r8 = 1073741824(0x40000000, float:2.0)
            com.chartboost.sdk.Chartboost r0 = com.chartboost.sdk.Chartboost.sharedChartboost()
            com.chartboost.sdk.Libraries.CBOrientation$Difference r6 = r0.getForcedOrientationDifference()
            int r7 = r6.getAsInt()
            com.chartboost.sdk.Libraries.CBOrientation$Difference r0 = com.chartboost.sdk.Libraries.CBOrientation.Difference.ANGLE_0
            if (r6 != r0) goto L17
            super.dispatchDraw(r10)
        L16:
            return
        L17:
            r10.save()
            int r0 = r9.getWidth()
            float r3 = (float) r0
            int r0 = r9.getHeight()
            float r4 = (float) r0
            android.graphics.Region$Op r5 = android.graphics.Region.Op.REPLACE
            r0 = r10
            r2 = r1
            r0.clipRect(r1, r2, r3, r4, r5)
            android.view.ViewParent r0 = r9.getParent()     // Catch: java.lang.Exception -> Lbe
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0     // Catch: java.lang.Exception -> Lbe
            android.view.ViewParent r1 = r0.getParent()     // Catch: java.lang.Exception -> Lc0
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1     // Catch: java.lang.Exception -> Lc0
            boolean r2 = r1 instanceof android.widget.ScrollView     // Catch: java.lang.Exception -> Lc0
            if (r2 != 0) goto L3f
            boolean r2 = r1 instanceof android.widget.HorizontalScrollView     // Catch: java.lang.Exception -> Lc0
            if (r2 == 0) goto L40
        L3f:
            r0 = r1
        L40:
            int r1 = r9.getLeft()     // Catch: java.lang.Exception -> Lbe
            int r2 = r0.getScrollX()     // Catch: java.lang.Exception -> Lbe
            int r3 = r1 - r2
            int r1 = r9.getTop()     // Catch: java.lang.Exception -> Lbe
            int r2 = r0.getScrollY()     // Catch: java.lang.Exception -> Lbe
            int r4 = r1 - r2
            int r1 = 0 - r3
            float r1 = (float) r1     // Catch: java.lang.Exception -> Lbe
            int r2 = 0 - r4
            float r2 = (float) r2     // Catch: java.lang.Exception -> Lbe
            int r5 = r0.getWidth()     // Catch: java.lang.Exception -> Lbe
            int r3 = r5 - r3
            float r3 = (float) r3     // Catch: java.lang.Exception -> Lbe
            int r0 = r0.getHeight()     // Catch: java.lang.Exception -> Lbe
            int r0 = r0 - r4
            float r4 = (float) r0     // Catch: java.lang.Exception -> Lbe
            android.graphics.Region$Op r5 = android.graphics.Region.Op.INTERSECT     // Catch: java.lang.Exception -> Lbe
            r0 = r10
            r0.clipRect(r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> Lbe
        L6d:
            int r0 = r9.getWidth()
            float r0 = (float) r0
            float r0 = r0 / r8
            int r1 = r9.getHeight()
            float r1 = (float) r1
            float r1 = r1 / r8
            r10.translate(r0, r1)
            float r0 = (float) r7
            r10.rotate(r0)
            boolean r0 = r6.isOdd()
            if (r0 == 0) goto Lac
            int r0 = r9.getHeight()
            int r0 = -r0
            float r0 = (float) r0
            float r0 = r0 / r8
            int r1 = r9.getWidth()
            int r1 = -r1
            float r1 = (float) r1
            float r1 = r1 / r8
            r10.translate(r0, r1)
        L97:
            android.graphics.Matrix r0 = r10.getMatrix()
            r9.a = r0
            android.graphics.Matrix r0 = r9.a
            android.graphics.Matrix r1 = r9.b
            r0.invert(r1)
            super.dispatchDraw(r10)
            r10.restore()
            goto L16
        Lac:
            int r0 = r9.getWidth()
            int r0 = -r0
            float r0 = (float) r0
            float r0 = r0 / r8
            int r1 = r9.getHeight()
            int r1 = -r1
            float r1 = (float) r1
            float r1 = r1 / r8
            r10.translate(r0, r1)
            goto L97
        Lbe:
            r0 = move-exception
            goto L6d
        Lc0:
            r1 = move-exception
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.t.dispatchDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (Chartboost.sharedChartboost().getForcedOrientationDifference() == CBOrientation.Difference.ANGLE_0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        float[] fArr = this.c;
        fArr[0] = motionEvent.getRawX();
        fArr[1] = motionEvent.getRawY();
        this.b.mapPoints(fArr);
        motionEvent.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (!Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.width = forcedOrientationDifference.isOdd() ? i2 : i;
        if (!forcedOrientationDifference.isOdd()) {
            i = i2;
        }
        layoutParams.height = i;
        this.d.setLayoutParams(layoutParams);
        this.d.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
        this.d.layout(0, 0, layoutParams.width, layoutParams.height);
    }
}
