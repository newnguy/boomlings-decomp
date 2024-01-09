package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.b;
import com.google.ads.internal.d;
import com.google.ads.internal.j;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.util.HashSet;
import java.util.Set;

public class AdView extends RelativeLayout implements Ad {
  private d a;
  
  public AdView(Activity paramActivity, AdSize paramAdSize, String paramString) {
    super(paramActivity.getApplicationContext());
    try {
      a((Context)paramActivity, paramAdSize, (AttributeSet)null);
      b((Context)paramActivity, paramAdSize, (AttributeSet)null);
      a(paramActivity, paramAdSize, paramString);
    } catch (b b) {
      a((Context)paramActivity, b.c("Could not initialize AdView"), paramAdSize, (AttributeSet)null);
      b.a("Could not initialize AdView");
    } 
  }
  
  protected AdView(Activity paramActivity, AdSize[] paramArrayOfAdSize, String paramString) {
    this(paramActivity, new AdSize(0, 0), paramString);
    a(paramArrayOfAdSize);
  }
  
  public AdView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }
  
  public AdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet);
  }
  
  private int a(Context paramContext, int paramInt) {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  private void a(Activity paramActivity, AdSize paramAdSize, String paramString) {
    FrameLayout frameLayout = new FrameLayout((Context)paramActivity);
    frameLayout.setFocusable(false);
    this.a = new d(this, paramActivity, paramAdSize, paramString, (ViewGroup)frameLayout, false);
    setGravity(17);
    try {
      ViewGroup viewGroup = j.a(paramActivity, this.a);
      if (viewGroup != null) {
        viewGroup.addView((View)frameLayout, -2, -2);
        addView((View)viewGroup, -2, -2);
        return;
      } 
      addView((View)frameLayout, -2, -2);
    } catch (VerifyError verifyError) {
      b.a("Gestures disabled: Not supported on this version of Android.", verifyError);
      addView((View)frameLayout, -2, -2);
    } 
  }
  
  private void a(Context paramContext, AttributeSet paramAttributeSet) {
    if (paramAttributeSet != null) {
      AdSize adSize;
      try {
        String str1 = b("adSize", paramContext, paramAttributeSet, true);
        adSize = (AdSize)a(str1);
        if (adSize != null) {
          try {
            if (adSize.length != 0) {
              if (!a("adUnitId", paramAttributeSet)) {
                b b2 = new b();
                this("Required XML attribute \"adUnitId\" missing", true);
                throw b2;
              } 
              if (isInEditMode()) {
                a(paramContext, "Ads by Google", -1, adSize[0], paramAttributeSet);
                return;
              } 
              str1 = b("adUnitId", paramContext, paramAttributeSet, true);
              boolean bool = a("loadAdOnCreate", paramContext, paramAttributeSet, false);
              if (paramContext instanceof Activity) {
                Activity activity = (Activity)paramContext;
                a((Context)activity, adSize[0], paramAttributeSet);
                b((Context)activity, adSize[0], paramAttributeSet);
                if (adSize.length == 1) {
                  a(activity, adSize[0], str1);
                } else {
                  AdSize adSize1 = new AdSize();
                  this(0, 0);
                  a(activity, adSize1, str1);
                  a((AdSize[])adSize);
                } 
                if (bool) {
                  Set<String> set = c("testDevices", paramContext, paramAttributeSet, false);
                  if (set.contains("TEST_EMULATOR")) {
                    set.remove("TEST_EMULATOR");
                    set.add(AdRequest.a);
                  } 
                  AdRequest adRequest = new AdRequest();
                  this();
                  loadAd(adRequest.b(set).a(c("keywords", paramContext, paramAttributeSet, false)));
                } 
                return;
              } 
              b = new b();
              this("AdView was initialized with a Context that wasn't an Activity.", true);
              throw b;
            } 
            b b1 = new b();
            StringBuilder stringBuilder = new StringBuilder();
            this();
            this(stringBuilder.append("Attribute \"adSize\" invalid: ").append((String)b).toString(), true);
            throw b1;
          } catch (b null) {}
        } else {
          b b1 = new b();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          this(stringBuilder.append("Attribute \"adSize\" invalid: ").append((String)b).toString(), true);
          throw b1;
        } 
      } catch (b b) {
        adSize = null;
      } 
      String str = b.c("Could not initialize AdView");
      if (adSize != null && adSize.length > 0) {
        adSize = (AdSize)adSize[0];
      } else {
        adSize = AdSize.BANNER;
      } 
      a(paramContext, str, adSize, paramAttributeSet);
      b.a("Could not initialize AdView");
      if (!isInEditMode())
        b.b("Could not initialize AdView"); 
    } 
  }
  
  private void a(Context paramContext, String paramString, AdSize paramAdSize, AttributeSet paramAttributeSet) {
    b.b(paramString);
    a(paramContext, paramString, -65536, paramAdSize, paramAttributeSet);
  }
  
  private void a(AdSize... paramVarArgs) {
    AdSize[] arrayOfAdSize = new AdSize[paramVarArgs.length];
    for (byte b = 0; b < paramVarArgs.length; b++)
      arrayOfAdSize[b] = AdSize.a(paramVarArgs[b], getContext()); 
    (this.a.h()).l.a(arrayOfAdSize);
  }
  
  private boolean a(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet) {
    if (!AdUtil.c(paramContext)) {
      a(paramContext, "You must have AdActivity declared in AndroidManifest.xml with configChanges.", paramAdSize, paramAttributeSet);
      return false;
    } 
    return true;
  }
  
  private boolean a(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean) {
    TypedValue typedValue;
    String str = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", paramString);
    paramBoolean = paramAttributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.google.ads", paramString, paramBoolean);
    if (str != null) {
      String str2 = paramContext.getPackageName();
      String str1 = str;
      if (str.matches("^@([^:]+)\\:(.*)$")) {
        str2 = str.replaceFirst("^@([^:]+)\\:(.*)$", "$1");
        str1 = str.replaceFirst("^@([^:]+)\\:(.*)$", "@$2");
      } 
      if (str1.startsWith("@bool/")) {
        String str3 = str1.substring("@bool/".length());
        typedValue = new TypedValue();
        try {
          Resources resources = getResources();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          resources.getValue(stringBuilder.append(str2).append(":bool/").append(str3).toString(), typedValue, true);
          if (typedValue.type == 18)
            return (typedValue.data != 0); 
        } catch (android.content.res.Resources.NotFoundException notFoundException) {
          throw new b("Could not find resource for " + paramString + ": " + str1, true, notFoundException);
        } 
      } else {
        return paramBoolean;
      } 
    } else {
      return paramBoolean;
    } 
    throw new b("Resource " + paramString + " was not a boolean: " + typedValue, true);
  }
  
  private boolean a(String paramString, AttributeSet paramAttributeSet) {
    return (paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", paramString) != null);
  }
  
  private String b(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean) {
    // Byte code:
    //   0: aload_3
    //   1: ldc 'http://schemas.android.com/apk/lib/com.google.ads'
    //   3: aload_1
    //   4: invokeinterface getAttributeValue : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   9: astore #6
    //   11: aload #6
    //   13: astore_3
    //   14: aload #6
    //   16: ifnull -> 157
    //   19: aload_2
    //   20: invokevirtual getPackageName : ()Ljava/lang/String;
    //   23: astore #5
    //   25: aload #6
    //   27: astore_2
    //   28: aload #6
    //   30: ldc_w '^@([^:]+)\:(.*)$'
    //   33: invokevirtual matches : (Ljava/lang/String;)Z
    //   36: ifeq -> 64
    //   39: aload #6
    //   41: ldc_w '^@([^:]+)\:(.*)$'
    //   44: ldc_w '$1'
    //   47: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   50: astore #5
    //   52: aload #6
    //   54: ldc_w '^@([^:]+)\:(.*)$'
    //   57: ldc_w '@$2'
    //   60: invokevirtual replaceFirst : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_3
    //   66: aload_2
    //   67: ldc_w '@string/'
    //   70: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   73: ifeq -> 157
    //   76: aload_2
    //   77: ldc_w '@string/'
    //   80: invokevirtual length : ()I
    //   83: invokevirtual substring : (I)Ljava/lang/String;
    //   86: astore #8
    //   88: new android/util/TypedValue
    //   91: dup
    //   92: invokespecial <init> : ()V
    //   95: astore #6
    //   97: aload_0
    //   98: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   101: astore_3
    //   102: new java/lang/StringBuilder
    //   105: astore #7
    //   107: aload #7
    //   109: invokespecial <init> : ()V
    //   112: aload_3
    //   113: aload #7
    //   115: aload #5
    //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: ldc_w ':string/'
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload #8
    //   128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual toString : ()Ljava/lang/String;
    //   134: aload #6
    //   136: iconst_1
    //   137: invokevirtual getValue : (Ljava/lang/String;Landroid/util/TypedValue;Z)V
    //   140: aload #6
    //   142: getfield string : Ljava/lang/CharSequence;
    //   145: ifnull -> 242
    //   148: aload #6
    //   150: getfield string : Ljava/lang/CharSequence;
    //   153: invokevirtual toString : ()Ljava/lang/String;
    //   156: astore_3
    //   157: iload #4
    //   159: ifeq -> 282
    //   162: aload_3
    //   163: ifnonnull -> 282
    //   166: new com/google/ads/internal/b
    //   169: dup
    //   170: new java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial <init> : ()V
    //   177: ldc_w 'Required XML attribute "'
    //   180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload_1
    //   184: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: ldc_w '" missing'
    //   190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual toString : ()Ljava/lang/String;
    //   196: iconst_1
    //   197: invokespecial <init> : (Ljava/lang/String;Z)V
    //   200: athrow
    //   201: astore_3
    //   202: new com/google/ads/internal/b
    //   205: dup
    //   206: new java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial <init> : ()V
    //   213: ldc_w 'Could not find resource for '
    //   216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload_1
    //   220: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: ldc_w ': '
    //   226: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: aload_2
    //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual toString : ()Ljava/lang/String;
    //   236: iconst_1
    //   237: aload_3
    //   238: invokespecial <init> : (Ljava/lang/String;ZLjava/lang/Throwable;)V
    //   241: athrow
    //   242: new com/google/ads/internal/b
    //   245: dup
    //   246: new java/lang/StringBuilder
    //   249: dup
    //   250: invokespecial <init> : ()V
    //   253: ldc_w 'Resource '
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: aload_1
    //   260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: ldc_w ' was not a string: '
    //   266: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: aload #6
    //   271: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   274: invokevirtual toString : ()Ljava/lang/String;
    //   277: iconst_1
    //   278: invokespecial <init> : (Ljava/lang/String;Z)V
    //   281: athrow
    //   282: aload_3
    //   283: areturn
    // Exception table:
    //   from	to	target	type
    //   97	140	201	android/content/res/Resources$NotFoundException
  }
  
  private boolean b(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet) {
    if (!AdUtil.b(paramContext)) {
      a(paramContext, "You must have INTERNET and ACCESS_NETWORK_STATE permissions in AndroidManifest.xml.", paramAdSize, paramAttributeSet);
      return false;
    } 
    return true;
  }
  
  private Set c(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean) {
    String str = b(paramString, paramContext, paramAttributeSet, paramBoolean);
    HashSet<String> hashSet = new HashSet();
    if (str != null) {
      String[] arrayOfString = str.split(",");
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++) {
        str = arrayOfString[b].trim();
        if (str.length() != 0)
          hashSet.add(str); 
      } 
    } 
    return hashSet;
  }
  
  void a(Context paramContext, String paramString, int paramInt, AdSize paramAdSize, AttributeSet paramAttributeSet) {
    AdSize adSize = paramAdSize;
    if (paramAdSize == null)
      adSize = AdSize.BANNER; 
    adSize = AdSize.a(adSize, paramContext.getApplicationContext());
    if (getChildCount() == 0) {
      LinearLayout linearLayout1;
      TextView textView;
      LinearLayout linearLayout2;
      if (paramAttributeSet == null) {
        textView = new TextView(paramContext);
      } else {
        textView = new TextView(paramContext, paramAttributeSet);
      } 
      textView.setGravity(17);
      textView.setText(paramString);
      textView.setTextColor(paramInt);
      textView.setBackgroundColor(-16777216);
      if (paramAttributeSet == null) {
        linearLayout1 = new LinearLayout(paramContext);
      } else {
        linearLayout1 = new LinearLayout(paramContext, paramAttributeSet);
      } 
      linearLayout1.setGravity(17);
      if (paramAttributeSet == null) {
        linearLayout2 = new LinearLayout(paramContext);
      } else {
        linearLayout2 = new LinearLayout(paramContext, (AttributeSet)linearLayout2);
      } 
      linearLayout2.setGravity(17);
      linearLayout2.setBackgroundColor(paramInt);
      int i = a(paramContext, adSize.a());
      paramInt = a(paramContext, adSize.b());
      linearLayout1.addView((View)textView, i - 2, paramInt - 2);
      linearLayout2.addView((View)linearLayout1);
      addView((View)linearLayout2, i, paramInt);
    } 
  }
  
  public boolean a() {
    return (this.a == null) ? false : this.a.r();
  }
  
  AdSize[] a(String paramString) {
    AdSize adSize = null;
    String[] arrayOfString = paramString.split(",");
    AdSize[] arrayOfAdSize = new AdSize[arrayOfString.length];
    for (byte b = 0;; b++) {
      if (b < arrayOfString.length) {
        AdSize adSize1;
        paramString = arrayOfString[b].trim();
        if (paramString.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
          String[] arrayOfString1 = paramString.split("[xX]");
          arrayOfString1[0] = arrayOfString1[0].trim();
          arrayOfString1[1] = arrayOfString1[1].trim();
          try {
            int i;
            int j;
            if ("FULL_WIDTH".equals(arrayOfString1[0])) {
              i = -1;
            } else {
              i = Integer.parseInt(arrayOfString1[0]);
            } 
            boolean bool = "AUTO_HEIGHT".equals(arrayOfString1[1]);
            if (bool) {
              j = -2;
            } else {
              j = Integer.parseInt(arrayOfString1[1]);
            } 
            adSize1 = new AdSize(i, j);
            if (adSize1 == null)
              return (AdSize[])adSize; 
            arrayOfAdSize[b] = adSize1;
            b++;
            continue;
          } catch (NumberFormatException numberFormatException) {
            adSize1 = adSize;
          } 
          return (AdSize[])adSize1;
        } 
        if ("BANNER".equals(adSize1)) {
          adSize1 = AdSize.BANNER;
        } else if ("SMART_BANNER".equals(adSize1)) {
          adSize1 = AdSize.SMART_BANNER;
        } else if ("IAB_MRECT".equals(adSize1)) {
          adSize1 = AdSize.IAB_MRECT;
        } else if ("IAB_BANNER".equals(adSize1)) {
          adSize1 = AdSize.IAB_BANNER;
        } else if ("IAB_LEADERBOARD".equals(adSize1)) {
          adSize1 = AdSize.IAB_LEADERBOARD;
        } else if ("IAB_WIDE_SKYSCRAPER".equals(adSize1)) {
          adSize1 = AdSize.IAB_WIDE_SKYSCRAPER;
        } else {
          adSize1 = null;
        } 
      } else {
        return arrayOfAdSize;
      } 
      if (paramString == null)
        return (AdSize[])adSize; 
      arrayOfAdSize[b] = (AdSize)paramString;
    } 
  }
  
  public void destroy() {
    this.a.b();
  }
  
  public void loadAd(AdRequest paramAdRequest) {
    if (this.a != null) {
      if (a())
        this.a.e(); 
      this.a.a(paramAdRequest);
    } 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    AdWebView adWebView = this.a.k();
    if (adWebView != null)
      adWebView.setVisibility(0); 
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setAdListener(AdListener paramAdListener) {
    (this.a.h()).m.a(paramAdListener);
  }
  
  protected void setAppEventListener(AppEventListener paramAppEventListener) {
    (this.a.h()).n.a(paramAppEventListener);
  }
  
  protected void setSupportedAdSizes(AdSize... paramVarArgs) {
    if ((this.a.h()).l.a() == null) {
      b.b("Error: Tried to set supported ad sizes on a single-size AdView.");
      return;
    } 
    a(paramVarArgs);
  }
  
  public void stopLoading() {
    if (this.a != null)
      this.a.z(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\AdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */