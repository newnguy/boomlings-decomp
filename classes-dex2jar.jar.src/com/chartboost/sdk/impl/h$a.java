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
    //   34: astore #4
    //   36: new android/widget/RelativeLayout$LayoutParams
    //   39: dup
    //   40: bipush #-2
    //   42: bipush #-2
    //   44: invokespecial <init> : (II)V
    //   47: astore #7
    //   49: new android/widget/RelativeLayout$LayoutParams
    //   52: dup
    //   53: bipush #-2
    //   55: bipush #-2
    //   57: invokespecial <init> : (II)V
    //   60: astore #6
    //   62: new android/widget/RelativeLayout$LayoutParams
    //   65: dup
    //   66: bipush #-2
    //   68: bipush #-2
    //   70: invokespecial <init> : (II)V
    //   73: astore #5
    //   75: invokestatic sharedChartboost : ()Lcom/chartboost/sdk/Chartboost;
    //   78: invokevirtual getForcedOrientationDifference : ()Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   81: astore_3
    //   82: aload_3
    //   83: invokevirtual isOdd : ()Z
    //   86: ifeq -> 610
    //   89: invokestatic f : ()I
    //   92: aload_0
    //   93: invokevirtual getContext : ()Landroid/content/Context;
    //   96: invokestatic a : (ILandroid/content/Context;)I
    //   99: istore_1
    //   100: aload #7
    //   102: iload_1
    //   103: putfield width : I
    //   106: aload_3
    //   107: invokevirtual isOdd : ()Z
    //   110: ifeq -> 615
    //   113: iconst_m1
    //   114: istore_1
    //   115: aload #7
    //   117: iload_1
    //   118: putfield height : I
    //   121: invokestatic c : ()[I
    //   124: aload_3
    //   125: invokevirtual ordinal : ()I
    //   128: iaload
    //   129: tableswitch default -> 152, 2 -> 629, 3 -> 639
    //   152: new android/graphics/drawable/BitmapDrawable
    //   155: dup
    //   156: aload_0
    //   157: getfield c : Lcom/chartboost/sdk/impl/h;
    //   160: invokestatic b : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   163: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   166: invokespecial <init> : (Landroid/graphics/Bitmap;)V
    //   169: astore #8
    //   171: aload #8
    //   173: getstatic android/graphics/Shader$TileMode.REPEAT : Landroid/graphics/Shader$TileMode;
    //   176: invokevirtual setTileModeX : (Landroid/graphics/Shader$TileMode;)V
    //   179: aload #8
    //   181: getstatic android/graphics/Shader$TileMode.CLAMP : Landroid/graphics/Shader$TileMode;
    //   184: invokevirtual setTileModeY : (Landroid/graphics/Shader$TileMode;)V
    //   187: aload_0
    //   188: getfield f : Landroid/widget/FrameLayout;
    //   191: aload #8
    //   193: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   196: aload_0
    //   197: getfield c : Lcom/chartboost/sdk/impl/h;
    //   200: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   203: ifnull -> 273
    //   206: aload_0
    //   207: getfield e : Landroid/widget/ImageView;
    //   210: aload_0
    //   211: getfield c : Lcom/chartboost/sdk/impl/h;
    //   214: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   217: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   220: invokevirtual setImageBitmap : (Landroid/graphics/Bitmap;)V
    //   223: aload #4
    //   225: aload_0
    //   226: getfield c : Lcom/chartboost/sdk/impl/h;
    //   229: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   232: invokevirtual c : ()I
    //   235: aload_0
    //   236: invokevirtual getContext : ()Landroid/content/Context;
    //   239: invokestatic a : (ILandroid/content/Context;)I
    //   242: putfield width : I
    //   245: aload #4
    //   247: invokestatic f : ()I
    //   250: aload_0
    //   251: getfield c : Lcom/chartboost/sdk/impl/h;
    //   254: invokestatic c : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   257: invokevirtual d : ()I
    //   260: invokestatic min : (II)I
    //   263: aload_0
    //   264: invokevirtual getContext : ()Landroid/content/Context;
    //   267: invokestatic a : (ILandroid/content/Context;)I
    //   270: putfield height : I
    //   273: aload_0
    //   274: getfield d : Landroid/widget/ImageView;
    //   277: aload_0
    //   278: getfield c : Lcom/chartboost/sdk/impl/h;
    //   281: invokestatic d : (Lcom/chartboost/sdk/impl/h;)Lcom/chartboost/sdk/Libraries/a$a;
    //   284: invokevirtual b : ()Landroid/graphics/Bitmap;
    //   287: invokevirtual setImageBitmap : (Landroid/graphics/Bitmap;)V
    //   290: aload_3
    //   291: invokevirtual isOdd : ()Z
    //   294: ifeq -> 649
    //   297: invokestatic g : ()I
    //   300: istore_1
    //   301: aload #6
    //   303: iload_1
    //   304: aload_0
    //   305: invokevirtual getContext : ()Landroid/content/Context;
    //   308: invokestatic a : (ILandroid/content/Context;)I
    //   311: putfield width : I
    //   314: aload_3
    //   315: invokevirtual isOdd : ()Z
    //   318: ifeq -> 656
    //   321: invokestatic h : ()I
    //   324: istore_1
    //   325: aload #6
    //   327: iload_1
    //   328: aload_0
    //   329: invokevirtual getContext : ()Landroid/content/Context;
    //   332: invokestatic a : (ILandroid/content/Context;)I
    //   335: putfield height : I
    //   338: invokestatic c : ()[I
    //   341: aload_3
    //   342: invokevirtual ordinal : ()I
    //   345: iaload
    //   346: tableswitch default -> 372, 2 -> 663, 3 -> 715, 4 -> 760
    //   372: aload #6
    //   374: bipush #10
    //   376: aload_0
    //   377: invokevirtual getContext : ()Landroid/content/Context;
    //   380: invokestatic a : (ILandroid/content/Context;)I
    //   383: putfield rightMargin : I
    //   386: aload #6
    //   388: invokestatic f : ()I
    //   391: invokestatic g : ()I
    //   394: isub
    //   395: iconst_2
    //   396: idiv
    //   397: aload_0
    //   398: invokevirtual getContext : ()Landroid/content/Context;
    //   401: invokestatic a : (ILandroid/content/Context;)I
    //   404: putfield topMargin : I
    //   407: aload #6
    //   409: bipush #11
    //   411: invokevirtual addRule : (I)V
    //   414: aload #5
    //   416: iconst_m1
    //   417: putfield width : I
    //   420: aload #5
    //   422: iconst_m1
    //   423: putfield height : I
    //   426: invokestatic c : ()[I
    //   429: aload_3
    //   430: invokevirtual ordinal : ()I
    //   433: iaload
    //   434: tableswitch default -> 460, 2 -> 798, 3 -> 814, 4 -> 830
    //   460: aload #5
    //   462: iconst_3
    //   463: aload_0
    //   464: getfield i : Lcom/chartboost/sdk/impl/t;
    //   467: invokevirtual getId : ()I
    //   470: invokevirtual addRule : (II)V
    //   473: aload_0
    //   474: getfield g : Lcom/chartboost/sdk/impl/q;
    //   477: astore #8
    //   479: aload_3
    //   480: invokevirtual isOdd : ()Z
    //   483: ifeq -> 846
    //   486: iconst_0
    //   487: istore_1
    //   488: aload #8
    //   490: iload_1
    //   491: invokevirtual a : (I)V
    //   494: aload_0
    //   495: aload_0
    //   496: getfield g : Lcom/chartboost/sdk/impl/q;
    //   499: invokevirtual a : ()Landroid/view/ViewGroup;
    //   502: invokespecial a : (Landroid/view/View;)V
    //   505: aload_0
    //   506: getfield g : Lcom/chartboost/sdk/impl/q;
    //   509: aload_0
    //   510: getfield j : Lcom/chartboost/sdk/impl/h$a$a;
    //   513: invokevirtual a : (Landroid/widget/BaseAdapter;)V
    //   516: aload_0
    //   517: aload_0
    //   518: getfield g : Lcom/chartboost/sdk/impl/q;
    //   521: invokevirtual a : ()Landroid/view/ViewGroup;
    //   524: aload #5
    //   526: invokevirtual addView : (Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V
    //   529: aload_3
    //   530: getstatic com/chartboost/sdk/Libraries/CBOrientation$Difference.ANGLE_180 : Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   533: if_acmpne -> 851
    //   536: aload_0
    //   537: getfield g : Lcom/chartboost/sdk/impl/q;
    //   540: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   543: bipush #80
    //   545: invokevirtual setGravity : (I)V
    //   548: aload_0
    //   549: getfield i : Lcom/chartboost/sdk/impl/t;
    //   552: aload #7
    //   554: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   557: aload_0
    //   558: getfield e : Landroid/widget/ImageView;
    //   561: aload #4
    //   563: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   566: aload_0
    //   567: getfield e : Landroid/widget/ImageView;
    //   570: getstatic android/widget/ImageView$ScaleType.FIT_CENTER : Landroid/widget/ImageView$ScaleType;
    //   573: invokevirtual setScaleType : (Landroid/widget/ImageView$ScaleType;)V
    //   576: aload_0
    //   577: getfield h : Lcom/chartboost/sdk/impl/t;
    //   580: aload #6
    //   582: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   585: aload_0
    //   586: getfield d : Landroid/widget/ImageView;
    //   589: getstatic android/widget/ImageView$ScaleType.FIT_CENTER : Landroid/widget/ImageView$ScaleType;
    //   592: invokevirtual setScaleType : (Landroid/widget/ImageView$ScaleType;)V
    //   595: aload_0
    //   596: new com/chartboost/sdk/impl/h$a$2
    //   599: dup
    //   600: aload_0
    //   601: aload_3
    //   602: invokespecial <init> : (Lcom/chartboost/sdk/impl/h$a;Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;)V
    //   605: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   608: pop
    //   609: return
    //   610: iconst_m1
    //   611: istore_1
    //   612: goto -> 100
    //   615: invokestatic f : ()I
    //   618: aload_0
    //   619: invokevirtual getContext : ()Landroid/content/Context;
    //   622: invokestatic a : (ILandroid/content/Context;)I
    //   625: istore_1
    //   626: goto -> 115
    //   629: aload #7
    //   631: bipush #11
    //   633: invokevirtual addRule : (I)V
    //   636: goto -> 152
    //   639: aload #7
    //   641: bipush #12
    //   643: invokevirtual addRule : (I)V
    //   646: goto -> 152
    //   649: invokestatic h : ()I
    //   652: istore_1
    //   653: goto -> 301
    //   656: invokestatic g : ()I
    //   659: istore_1
    //   660: goto -> 325
    //   663: aload #6
    //   665: bipush #10
    //   667: aload_0
    //   668: invokevirtual getContext : ()Landroid/content/Context;
    //   671: invokestatic a : (ILandroid/content/Context;)I
    //   674: putfield bottomMargin : I
    //   677: aload #6
    //   679: invokestatic f : ()I
    //   682: invokestatic g : ()I
    //   685: isub
    //   686: iconst_2
    //   687: idiv
    //   688: aload_0
    //   689: invokevirtual getContext : ()Landroid/content/Context;
    //   692: invokestatic a : (ILandroid/content/Context;)I
    //   695: putfield rightMargin : I
    //   698: aload #6
    //   700: bipush #11
    //   702: invokevirtual addRule : (I)V
    //   705: aload #6
    //   707: bipush #12
    //   709: invokevirtual addRule : (I)V
    //   712: goto -> 414
    //   715: aload #6
    //   717: bipush #10
    //   719: aload_0
    //   720: invokevirtual getContext : ()Landroid/content/Context;
    //   723: invokestatic a : (ILandroid/content/Context;)I
    //   726: putfield leftMargin : I
    //   729: aload #6
    //   731: invokestatic f : ()I
    //   734: invokestatic g : ()I
    //   737: isub
    //   738: iconst_2
    //   739: idiv
    //   740: aload_0
    //   741: invokevirtual getContext : ()Landroid/content/Context;
    //   744: invokestatic a : (ILandroid/content/Context;)I
    //   747: putfield bottomMargin : I
    //   750: aload #6
    //   752: bipush #12
    //   754: invokevirtual addRule : (I)V
    //   757: goto -> 414
    //   760: aload #6
    //   762: bipush #10
    //   764: aload_0
    //   765: invokevirtual getContext : ()Landroid/content/Context;
    //   768: invokestatic a : (ILandroid/content/Context;)I
    //   771: putfield topMargin : I
    //   774: aload #6
    //   776: invokestatic f : ()I
    //   779: invokestatic g : ()I
    //   782: isub
    //   783: iconst_2
    //   784: idiv
    //   785: aload_0
    //   786: invokevirtual getContext : ()Landroid/content/Context;
    //   789: invokestatic a : (ILandroid/content/Context;)I
    //   792: putfield leftMargin : I
    //   795: goto -> 414
    //   798: aload #5
    //   800: iconst_0
    //   801: aload_0
    //   802: getfield i : Lcom/chartboost/sdk/impl/t;
    //   805: invokevirtual getId : ()I
    //   808: invokevirtual addRule : (II)V
    //   811: goto -> 473
    //   814: aload #5
    //   816: iconst_2
    //   817: aload_0
    //   818: getfield i : Lcom/chartboost/sdk/impl/t;
    //   821: invokevirtual getId : ()I
    //   824: invokevirtual addRule : (II)V
    //   827: goto -> 473
    //   830: aload #5
    //   832: iconst_1
    //   833: aload_0
    //   834: getfield i : Lcom/chartboost/sdk/impl/t;
    //   837: invokevirtual getId : ()I
    //   840: invokevirtual addRule : (II)V
    //   843: goto -> 473
    //   846: iconst_1
    //   847: istore_1
    //   848: goto -> 488
    //   851: aload_3
    //   852: getstatic com/chartboost/sdk/Libraries/CBOrientation$Difference.ANGLE_90 : Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   855: if_acmpne -> 872
    //   858: aload_0
    //   859: getfield g : Lcom/chartboost/sdk/impl/q;
    //   862: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   865: iconst_5
    //   866: invokevirtual setGravity : (I)V
    //   869: goto -> 548
    //   872: aload_0
    //   873: getfield g : Lcom/chartboost/sdk/impl/q;
    //   876: invokevirtual b : ()Landroid/widget/LinearLayout;
    //   879: iconst_0
    //   880: invokevirtual setGravity : (I)V
    //   883: goto -> 548
  }
  
  public void b() {
    super.b();
    this.d = null;
    this.e = null;
    this.g = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\h$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */