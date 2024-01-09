package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

/* loaded from: classes.dex */
public class n {
    private static /* synthetic */ int[] a;
    private static /* synthetic */ int[] b;

    /* loaded from: classes.dex */
    public interface a {
        void a(com.chartboost.sdk.impl.a aVar);
    }

    /* loaded from: classes.dex */
    public enum b {
        CBAnimationTypeNone,
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeSlideFromTop;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static b[] valuesCustom() {
            b[] valuesCustom = values();
            int length = valuesCustom.length;
            b[] bVarArr = new b[length];
            System.arraycopy(valuesCustom, 0, bVarArr, 0, length);
            return bVarArr;
        }
    }

    public static void a(b bVar, com.chartboost.sdk.impl.a aVar) {
        a(bVar, aVar, null);
    }

    public static void a(b bVar, com.chartboost.sdk.impl.a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    static /* synthetic */ int[] a() {
        int[] iArr = a;
        if (iArr == null) {
            iArr = new int[CBOrientation.Difference.valuesCustom().length];
            try {
                iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            a = iArr;
        }
        return iArr;
    }

    public static void b(b bVar, com.chartboost.sdk.impl.a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    private static void b(final b bVar, final com.chartboost.sdk.impl.a aVar, final a aVar2, final Boolean bool) {
        final View c;
        if (aVar == null || aVar.h == null || (c = aVar.h.c()) == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = c.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.chartboost.sdk.impl.n.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    n.c(bVar, aVar, aVar2, bool);
                }
            });
        }
    }

    static /* synthetic */ int[] b() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[b.valuesCustom().length];
            try {
                iArr[b.CBAnimationTypeBounce.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[b.CBAnimationTypeNone.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveRotate.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[b.CBAnimationTypePerspectiveZoom.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromBottom.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[b.CBAnimationTypeSlideFromTop.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            b = iArr;
        }
        return iArr;
    }

    public static void c(b bVar, final com.chartboost.sdk.impl.a aVar, final a aVar2, Boolean bool) {
        View c;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        p pVar;
        TranslateAnimation translateAnimation;
        p pVar2;
        TranslateAnimation translateAnimation2;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (aVar == null || aVar.h == null || (c = aVar.h.c()) == null) {
            return;
        }
        float width = c.getWidth();
        float height = c.getHeight();
        float f7 = (1.0f - 0.4f) / 2.0f;
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        switch (b()[bVar.ordinal()]) {
            case 2:
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 2:
                        if (!bool.booleanValue()) {
                            pVar = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            pVar = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    case 3:
                        if (!bool.booleanValue()) {
                            pVar = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            pVar = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    case 4:
                        if (!bool.booleanValue()) {
                            pVar = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            pVar = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    default:
                        if (!bool.booleanValue()) {
                            pVar = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            pVar = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                }
                pVar.setDuration(600L);
                pVar.setFillAfter(true);
                animationSet.addAnimation(pVar);
                ScaleAnimation scaleAnimation = bool.booleanValue() ? new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f) : new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                scaleAnimation.setDuration(600L);
                scaleAnimation.setFillAfter(true);
                animationSet.addAnimation(scaleAnimation);
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 2:
                        if (!bool.booleanValue()) {
                            translateAnimation = new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                            break;
                        } else {
                            translateAnimation = new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f);
                            break;
                        }
                    case 3:
                        if (!bool.booleanValue()) {
                            translateAnimation = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7);
                            break;
                        } else {
                            translateAnimation = new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                            break;
                        }
                    case 4:
                        if (!bool.booleanValue()) {
                            translateAnimation = new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f);
                            break;
                        } else {
                            translateAnimation = new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                            break;
                        }
                    default:
                        if (!bool.booleanValue()) {
                            translateAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                            break;
                        } else {
                            translateAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f);
                            break;
                        }
                }
                translateAnimation.setDuration(600L);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                break;
            case 3:
                if (!bool.booleanValue()) {
                    ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation2.setDuration(600L);
                    scaleAnimation2.setStartOffset(0L);
                    scaleAnimation2.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation2);
                    break;
                } else {
                    ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation3.setDuration(Math.round(((float) 600) * 0.6f));
                    scaleAnimation3.setStartOffset(0L);
                    scaleAnimation3.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation3);
                    ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation4.setDuration(Math.round(((float) 600) * 0.19999999f));
                    scaleAnimation4.setStartOffset(Math.round(((float) 600) * 0.6f));
                    scaleAnimation4.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation4);
                    ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation5.setDuration(Math.round(((float) 600) * 0.099999964f));
                    scaleAnimation5.setStartOffset(Math.round(((float) 600) * 0.8f));
                    scaleAnimation5.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation5);
                    break;
                }
            case 4:
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 2:
                        if (!bool.booleanValue()) {
                            pVar2 = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            pVar2 = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    case 3:
                        if (!bool.booleanValue()) {
                            pVar2 = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            pVar2 = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                    case 4:
                        if (!bool.booleanValue()) {
                            pVar2 = new p(0.0f, -60.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        } else {
                            pVar2 = new p(60.0f, 0.0f, width / 2.0f, height / 2.0f, true);
                            break;
                        }
                    default:
                        if (!bool.booleanValue()) {
                            pVar2 = new p(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        } else {
                            pVar2 = new p(-60.0f, 0.0f, width / 2.0f, height / 2.0f, false);
                            break;
                        }
                }
                pVar2.setDuration(600L);
                pVar2.setFillAfter(true);
                animationSet.addAnimation(pVar2);
                ScaleAnimation scaleAnimation6 = bool.booleanValue() ? new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f) : new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                scaleAnimation6.setDuration(600L);
                scaleAnimation6.setFillAfter(true);
                animationSet.addAnimation(scaleAnimation6);
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 2:
                        if (!bool.booleanValue()) {
                            translateAnimation2 = new TranslateAnimation(0.0f, (-width) * 0.4f, 0.0f, height * f7);
                            break;
                        } else {
                            translateAnimation2 = new TranslateAnimation(width, 0.0f, height * f7, 0.0f);
                            break;
                        }
                    case 3:
                        if (!bool.booleanValue()) {
                            translateAnimation2 = new TranslateAnimation(0.0f, width * f7, 0.0f, (-height) * 0.4f);
                            break;
                        } else {
                            translateAnimation2 = new TranslateAnimation(width * f7, 0.0f, height, 0.0f);
                            break;
                        }
                    case 4:
                        if (!bool.booleanValue()) {
                            translateAnimation2 = new TranslateAnimation(0.0f, width, 0.0f, height * f7);
                            break;
                        } else {
                            translateAnimation2 = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f7, 0.0f);
                            break;
                        }
                    default:
                        if (!bool.booleanValue()) {
                            translateAnimation2 = new TranslateAnimation(0.0f, width * f7, 0.0f, height);
                            break;
                        } else {
                            translateAnimation2 = new TranslateAnimation(width * f7, 0.0f, (-height) * 0.4f, 0.0f);
                            break;
                        }
                }
                translateAnimation2.setDuration(600L);
                translateAnimation2.setFillAfter(true);
                animationSet.addAnimation(translateAnimation2);
                break;
            case 5:
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 1:
                        f6 = bool.booleanValue() ? height : 0.0f;
                        if (bool.booleanValue()) {
                            height = 0.0f;
                        }
                        f4 = 0.0f;
                        f5 = 0.0f;
                        break;
                    case 2:
                        float f8 = bool.booleanValue() ? -width : 0.0f;
                        f4 = bool.booleanValue() ? 0.0f : -width;
                        height = 0.0f;
                        f5 = f8;
                        f6 = 0.0f;
                        break;
                    case 3:
                        f6 = bool.booleanValue() ? -height : 0.0f;
                        height = bool.booleanValue() ? 0.0f : -height;
                        f5 = 0.0f;
                        f4 = 0.0f;
                        break;
                    case 4:
                        float f9 = bool.booleanValue() ? width : 0.0f;
                        if (bool.booleanValue()) {
                            width = 0.0f;
                        }
                        height = 0.0f;
                        f4 = width;
                        f5 = f9;
                        f6 = 0.0f;
                        break;
                    default:
                        height = 0.0f;
                        f6 = 0.0f;
                        f4 = 0.0f;
                        f5 = 0.0f;
                        break;
                }
                TranslateAnimation translateAnimation3 = new TranslateAnimation(f5, f4, f6, height);
                translateAnimation3.setDuration(600L);
                translateAnimation3.setFillAfter(true);
                animationSet.addAnimation(translateAnimation3);
                break;
            case 6:
                switch (a()[forcedOrientationDifference.ordinal()]) {
                    case 1:
                        f3 = bool.booleanValue() ? -height : 0.0f;
                        f = bool.booleanValue() ? 0.0f : -height;
                        width = 0.0f;
                        f2 = 0.0f;
                        break;
                    case 2:
                        float f10 = bool.booleanValue() ? width : 0.0f;
                        if (bool.booleanValue()) {
                            width = 0.0f;
                        }
                        f = 0.0f;
                        f2 = f10;
                        f3 = 0.0f;
                        break;
                    case 3:
                        f3 = bool.booleanValue() ? height : 0.0f;
                        if (bool.booleanValue()) {
                            height = 0.0f;
                        }
                        f = height;
                        width = 0.0f;
                        f2 = 0.0f;
                        break;
                    case 4:
                        float f11 = bool.booleanValue() ? -width : 0.0f;
                        width = bool.booleanValue() ? 0.0f : -width;
                        f = 0.0f;
                        f2 = f11;
                        f3 = 0.0f;
                        break;
                    default:
                        f = 0.0f;
                        f3 = 0.0f;
                        width = 0.0f;
                        f2 = 0.0f;
                        break;
                }
                TranslateAnimation translateAnimation4 = new TranslateAnimation(f2, width, f3, f);
                translateAnimation4.setDuration(600L);
                translateAnimation4.setFillAfter(true);
                animationSet.addAnimation(translateAnimation4);
                break;
        }
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.chartboost.sdk.impl.n.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (aVar2 != null) {
                    aVar2.a(aVar);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        c.startAnimation(animationSet);
    }
}
