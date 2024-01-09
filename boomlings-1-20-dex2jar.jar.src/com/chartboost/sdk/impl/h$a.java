package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.b;

public class h$a extends b.b {
  private static int[] k;
  
  final h c;
  
  private ImageView d;
  
  private ImageView e;
  
  private FrameLayout f;
  
  private q g;
  
  private t h;
  
  private t i;
  
  private h$a$a j;
  
  private h$a(h paramh, Context paramContext) {
    super(paramh, paramContext);
    boolean bool;
    setBackgroundColor(-1842205);
    this.f = new FrameLayout(paramContext);
    this.e = new ImageView(paramContext);
    this.d = new ImageView(paramContext);
    if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
      bool = false;
    } else {
      bool = true;
    } 
    this.g = new q(paramContext, bool);
    this.g.b().setBackgroundColor(-1842205);
    this.f.setFocusable(false);
    this.e.setFocusable(false);
    this.d.setFocusable(false);
    this.d.setClickable(true);
    this.h = new t(paramContext, (View)this.d);
    this.i = new t(paramContext, (View)this.f);
    addView((View)this.i);
    this.f.addView((View)this.e);
    addView((View)this.h);
    a((View)this.e);
    a((View)this.f);
    a((View)this.d);
    a((View)this.i);
    a((View)this.h);
    this.d.setOnClickListener(new h$a$1(this));
    this.j = new h$a$a(this, paramContext);
  }
  
  private void a(View paramView) {
    char c = 'È';
    if (200 == getId())
      c = 'É'; 
    for (View view = findViewById(c);; view = findViewById(++c)) {
      if (view == null) {
        paramView.setId(c);
        paramView.setSaveEnabled(false);
        return;
      } 
    } 
  }
  
  protected void a(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: getfield g : Lcom/chartboost/sdk/impl/q;
    //   4: invokevirtual a : ()Landroid/view/ViewGroup;
    //   7: ifnull -> 21
    //   10: aload_0
    //   11: aload_0
    //   12: getfield g : Lcom/chartboost/sdk/impl/q;
    //   15: invokevirtual a : ()Landroid/view/ViewGroup;
    //   18: invokevirtual removeView : (Landroid/view/View;)V
    //   21: new android/widget/FrameLayout$LayoutParams
    //   24: dup
    //   25: bipush #-2
    //   27: bipush #-2
    //   29: bipush #17
    //   31: invokespecial <init> : (III)V
    //   34: astore_3
    //   35: new android/widget/RelativeLayout$LayoutParams
    //   38: dup
    //   39: bipush #-2
    //   41: bipush #-2
    //   43: invokespecial <init> : (II)V
    //   46: astore #6
    //   48: new android/widget/RelativeLayout$LayoutParams
    //   51: dup
    //   52: bipush #-2
    //   54: bipush #-2
    //   56: invokespecial <init> : (II)V
    //   59: astore #7
    //   61: new android/widget/RelativeLayout$LayoutParams
    //   64: dup
    //   65: bipush #-2
    //   67: bipush #-2
    //   69: invokespecial <init> : (II)V
    //   72: astore #4
    //   74: invokestatic sharedChartboost : ()Lcom/chartboost/sdk/Chartboost;
    //   77: invokevirtual getForcedOrientationDifference : ()Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   80: astore #5
    //   82: aload #5
    //   84: invokevirtual isOdd : ()Z
    //   87: ifeq -> 616
    //   90: invokestatic f : ()I
    //   93: aload_0
    //   94: invokevirtual getContext : ()Landroid/content/Context;
    //   97: invokestatic a : (ILandroid/content/Context;)I
    //   100: istore_1
    //   101: aload #6
    //   103: iload_1
    //   104: putfield width : I
    //   107: aload #5
    //   109: invokevirtual isOdd : ()Z
    //   112: ifeq -> 621
    //   115: iconst_m1
    //   116: istore_1
    //   117: aload #6
    //   119: iload_1
    //   120: putfield height : I
    //   123: invokestatic c : ()[I
    //   126: aload #5
    //   128: invokevirtual ordinal : ()I
    //   131: iaload
    //   132: tableswitch default -> 156, 2 -> 635, 3 -> 645
    //   156: new android/graphics/drawable/BitmapDrawable
    //   159: dup
    //   160: aload_0
    //   161: getfield c : Lcom/chartboost/sdk/impl/h;
    //   164: invokestatic b : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   167: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   170: invokespecial <init> : (Landroid/graphics/Bitmap;)V
    //   173: astore #8
    //   175: aload #8
    //   177: getstatic android/graphics/Shader$TileMode.REPEAT : Landroid/graphics/Shader$TileMode;
    //   180: invokevirtual setTileModeX : (Landroid/graphics/Shader$TileMode;)V
    //   183: aload #8
    //   185: getstatic android/graphics/Shader$TileMode.CLAMP : Landroid/graphics/Shader$TileMode;
    //   188: invokevirtual setTileModeY : (Landroid/graphics/Shader$TileMode;)V
    //   191: aload_0
    //   192: getfield f : Landroid/widget/FrameLayout;
    //   195: aload #8
    //   197: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   200: aload_0
    //   201: getfield c : Lcom/chartboost/sdk/impl/h;
    //   204: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   207: ifnull -> 275
    //   210: aload_0
    //   211: getfield e : Landroid/widget/ImageView;
    //   214: aload_0
    //   215: getfield c : Lcom/chartboost/sdk/impl/h;
    //   218: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   221: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   224: invokevirtual setImageBitmap : (Landroid/graphics/Bitmap;)V
    //   227: aload_3
    //   228: aload_0
    //   229: getfield c : Lcom/chartboost/sdk/impl/h;
    //   232: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   235: invokevirtual c : ()I
    //   238: aload_0
    //   239: invokevirtual getContext : ()Landroid/content/Context;
    //   242: invokestatic a : (ILandroid/content/Context;)I
    //   245: putfield width : I
    //   248: aload_3
    //   249: invokestatic f : ()I
    //   252: aload_0
    //   253: getfield c : Lcom/chartboost/sdk/impl/h;
    //   256: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   259: invokevirtual d : ()I
    //   262: invokestatic min : (II)I
    //   265: aload_0
    //   266: invokevirtual getContext : ()Landroid/content/Context;
    //   269: invokestatic a : (ILandroid/content/Context;)I
    //   272: putfield height : I
    //   275: aload_0
    //   276: getfield d : Landroid/widget/ImageView;
    //   279: aload_0
    //   280: getfield c : Lcom/chartboost/sdk/impl/h;
    //   283: invokestatic d : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   286: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   289: invokevirtual setImageBitmap : (Landroid/graphics/Bitmap;)V
    //   292: aload #5
    //   294: invokevirtual isOdd : ()Z
    //   297: ifeq -> 655
    //   300: invokestatic g : ()I
    //   303: istore_1
    //   304: aload #7
    //   306: iload_1
    //   307: aload_0
    //   308: invokevirtual getContext : ()Landroid/content/Context;
    //   311: invokestatic a : (ILandroid/content/Context;)I
    //   314: putfield width : I
    //   317: aload #5
    //   319: invokevirtual isOdd : ()Z
    //   322: ifeq -> 662
    //   325: invokestatic h : ()I
    //   328: istore_1
    //   329: aload #7
    //   331: iload_1
    //   332: aload_0
    //   333: invokevirtual getContext : ()Landroid/content/Context;
    //   336: invokestatic a : (ILandroid/content/Context;)I
    //   339: putfield height : I
    //   342: invokestatic c : ()[I
    //   345: aload #5
    //   347: invokevirtual ordinal : ()I
    //   350: iaload
    //   351: tableswitch default -> 376, 2 -> 669, 3 -> 721, 4 -> 766
    //   376: aload #7
    //   378: bipush #10
    //   380: aload_0
    //   381: invokevirtual getContext : ()Landroid/content/Context;
    //   384: invokestatic a : (ILandroid/content/Context;)I
    //   387: putfield rightMargin : I
    //   390: aload #7
    //   392: invokestatic f : ()I
    //   395: invokestatic g : ()I
    //   398: isub
    //   399: iconst_2
    //   400: idiv
    //   401: aload_0
    //   402: invokevirtual getContext : ()Landroid/content/Context;
    //   405: invokestatic a : (ILandroid/content/Context;)I
    //   408: putfield topMargin : I
    //   411: aload #7
    //   413: bipush #11
    //   415: invokevirtual addRule : (I)V
    //   418: aload #4
    //   420: iconst_m1
    //   421: putfield width : I
    //   424: aload #4
    //   426: iconst_m1
    //   427: putfield height : I
    //   430: invokestatic c : ()[I
    //   433: aload #5
    //   435: invokevirtual ordinal : ()I
    //   438: iaload
    //   439: tableswitch default -> 464, 2 -> 804, 3 -> 820, 4 -> 836
    //   464: aload #4
    //   466: iconst_3
    //   467: aload_0
    //   468: getfield i : Lcom/chartboost/sdk/impl/t;
    //   471: invokevirtual getId : ()I
    //   474: invokevirtual addRule : (II)V
    //   477: aload_0
    //   478: getfield g : Lcom/chartboost/sdk/impl/q;
    //   481: astore #8
    //   483: aload #5
    //   485: invokevirtual isOdd : ()Z
    //   488: ifeq -> 852
    //   491: iconst_0
    //   492: istore_1
    //   493: aload #8
    //   495: iload_1
    //   496: invokevirtual a : (I)V
    //   499: aload_0
    //   500: aload_0
    //   501: getfield g : Lcom/chartboost/sdk/impl/q;
    //   504: invokevirtual a : ()Landroid/view/ViewGroup;
    //   507: invokespecial a : (Landroid/view/View;)V
    //   510: aload_0
    //   511: getfield g : Lcom/chartboost/sdk/impl/q;
    //   514: aload_0
    //   515: getfield j : Lcom/chartboost/sdk/impl/h$a$a;
    //   518: invokevirtual a : (Landroid/widget/BaseAdapter;)V
    //   521: aload_0
    //   522: aload_0
    //   523: getfield g : Lcom/chartboost/sdk/impl/q;
    //   526: invokevirtual a : ()Landroid/view/ViewGroup;
    //   529: aload #4
    //   531: invokevirtual addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   534: aload #5
    //   536: getstatic com/chartboost/sdk/Libraries/CBOrientation$Difference.ANGLE_180 : Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   539: if_acmpne -> 857
    //   542: aload_0
    //   543: getfield g : Lcom/chartboost/sdk/impl/q;
    //   546: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   549: bipush #80
    //   551: invokevirtual setGravity : (I)V
    //   554: aload_0
    //   555: getfield i : Lcom/chartboost/sdk/impl/t;
    //   558: aload #6
    //   560: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   563: aload_0
    //   564: getfield e : Landroid/widget/ImageView;
    //   567: aload_3
    //   568: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   571: aload_0
    //   572: getfield e : Landroid/widget/ImageView;
    //   575: getstatic android/widget/ImageView$ScaleType.FIT_CENTER : Landroid/widget/ImageView$ScaleType;
    //   578: invokevirtual setScaleType : (Landroid/widget/ImageView$ScaleType;)V
    //   581: aload_0
    //   582: getfield h : Lcom/chartboost/sdk/impl/t;
    //   585: aload #7
    //   587: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   590: aload_0
    //   591: getfield d : Landroid/widget/ImageView;
    //   594: getstatic android/widget/ImageView$ScaleType.FIT_CENTER : Landroid/widget/ImageView$ScaleType;
    //   597: invokevirtual setScaleType : (Landroid/widget/ImageView$ScaleType;)V
    //   600: aload_0
    //   601: new com/chartboost/sdk/impl/h$a$2
    //   604: dup
    //   605: aload_0
    //   606: aload #5
    //   608: invokespecial <init> : (Lcom/chartboost/sdk/impl/h$a;Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;)V
    //   611: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   614: pop
    //   615: return
    //   616: iconst_m1
    //   617: istore_1
    //   618: goto -> 101
    //   621: invokestatic f : ()I
    //   624: aload_0
    //   625: invokevirtual getContext : ()Landroid/content/Context;
    //   628: invokestatic a : (ILandroid/content/Context;)I
    //   631: istore_1
    //   632: goto -> 117
    //   635: aload #6
    //   637: bipush #11
    //   639: invokevirtual addRule : (I)V
    //   642: goto -> 156
    //   645: aload #6
    //   647: bipush #12
    //   649: invokevirtual addRule : (I)V
    //   652: goto -> 156
    //   655: invokestatic h : ()I
    //   658: istore_1
    //   659: goto -> 304
    //   662: invokestatic g : ()I
    //   665: istore_1
    //   666: goto -> 329
    //   669: aload #7
    //   671: bipush #10
    //   673: aload_0
    //   674: invokevirtual getContext : ()Landroid/content/Context;
    //   677: invokestatic a : (ILandroid/content/Context;)I
    //   680: putfield bottomMargin : I
    //   683: aload #7
    //   685: invokestatic f : ()I
    //   688: invokestatic g : ()I
    //   691: isub
    //   692: iconst_2
    //   693: idiv
    //   694: aload_0
    //   695: invokevirtual getContext : ()Landroid/content/Context;
    //   698: invokestatic a : (ILandroid/content/Context;)I
    //   701: putfield rightMargin : I
    //   704: aload #7
    //   706: bipush #11
    //   708: invokevirtual addRule : (I)V
    //   711: aload #7
    //   713: bipush #12
    //   715: invokevirtual addRule : (I)V
    //   718: goto -> 418
    //   721: aload #7
    //   723: bipush #10
    //   725: aload_0
    //   726: invokevirtual getContext : ()Landroid/content/Context;
    //   729: invokestatic a : (ILandroid/content/Context;)I
    //   732: putfield leftMargin : I
    //   735: aload #7
    //   737: invokestatic f : ()I
    //   740: invokestatic g : ()I
    //   743: isub
    //   744: iconst_2
    //   745: idiv
    //   746: aload_0
    //   747: invokevirtual getContext : ()Landroid/content/Context;
    //   750: invokestatic a : (ILandroid/content/Context;)I
    //   753: putfield bottomMargin : I
    //   756: aload #7
    //   758: bipush #12
    //   760: invokevirtual addRule : (I)V
    //   763: goto -> 418
    //   766: aload #7
    //   768: bipush #10
    //   770: aload_0
    //   771: invokevirtual getContext : ()Landroid/content/Context;
    //   774: invokestatic a : (ILandroid/content/Context;)I
    //   777: putfield topMargin : I
    //   780: aload #7
    //   782: invokestatic f : ()I
    //   785: invokestatic g : ()I
    //   788: isub
    //   789: iconst_2
    //   790: idiv
    //   791: aload_0
    //   792: invokevirtual getContext : ()Landroid/content/Context;
    //   795: invokestatic a : (ILandroid/content/Context;)I
    //   798: putfield leftMargin : I
    //   801: goto -> 418
    //   804: aload #4
    //   806: iconst_0
    //   807: aload_0
    //   808: getfield i : Lcom/chartboost/sdk/impl/t;
    //   811: invokevirtual getId : ()I
    //   814: invokevirtual addRule : (II)V
    //   817: goto -> 477
    //   820: aload #4
    //   822: iconst_2
    //   823: aload_0
    //   824: getfield i : Lcom/chartboost/sdk/impl/t;
    //   827: invokevirtual getId : ()I
    //   830: invokevirtual addRule : (II)V
    //   833: goto -> 477
    //   836: aload #4
    //   838: iconst_1
    //   839: aload_0
    //   840: getfield i : Lcom/chartboost/sdk/impl/t;
    //   843: invokevirtual getId : ()I
    //   846: invokevirtual addRule : (II)V
    //   849: goto -> 477
    //   852: iconst_1
    //   853: istore_1
    //   854: goto -> 493
    //   857: aload #5
    //   859: getstatic com/chartboost/sdk/Libraries/CBOrientation$Difference.ANGLE_90 : Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   862: if_acmpne -> 879
    //   865: aload_0
    //   866: getfield g : Lcom/chartboost/sdk/impl/q;
    //   869: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   872: iconst_5
    //   873: invokevirtual setGravity : (I)V
    //   876: goto -> 554
    //   879: aload_0
    //   880: getfield g : Lcom/chartboost/sdk/impl/q;
    //   883: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   886: iconst_0
    //   887: invokevirtual setGravity : (I)V
    //   890: goto -> 554
  }
  
  public void b() {
    super.b();
    this.d = null;
    this.e = null;
    this.g = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\h$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */