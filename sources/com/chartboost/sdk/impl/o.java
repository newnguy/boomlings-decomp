package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class o extends RelativeLayout {
    public a a;
    private boolean b;

    /* loaded from: classes.dex */
    class a extends View {
        public a(Context context) {
            super(context);
            setFocusable(false);
        }

        public void a() {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{o.this.b ? -872415232 : -2013265920, o.this.b ? -2013265920 : -872415232});
            gradientDrawable.setGradientType(1);
            gradientDrawable.setGradientRadius(Math.min(getWidth(), getHeight()) / 2.0f);
            gradientDrawable.setGradientCenter(getWidth() / 2.0f, getHeight() / 2.0f);
            setBackgroundDrawable(gradientDrawable);
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            a();
        }
    }

    public o(Context context) {
        super(context);
        this.a = new a(context);
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.a);
        setFocusable(false);
    }

    public void a() {
        a(this.a);
    }

    public void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(510L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public void a(boolean z) {
        this.b = z;
        this.a.a();
    }
}
