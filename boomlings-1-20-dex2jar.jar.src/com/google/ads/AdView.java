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
        str = str1.substring("@bool/".length());
        typedValue = new TypedValue();
        try {
          Resources resources = getResources();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          resources.getValue(stringBuilder.append(str2).append(":bool/").append(str).toString(), typedValue, true);
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
    //   16: ifnull -> 156
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
    //   73: ifeq -> 156
    //   76: aload_2
    //   77: ldc_w '@string/'
    //   80: invokevirtual length : ()I
    //   83: invokevirtual substring : (I)Ljava/lang/String;
    //   86: astore #8
    //   88: new android/util/TypedValue
    //   91: dup
    //   92: invokespecial <init> : ()V
    //   95: astore #7
    //   97: aload_0
    //   98: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   101: astore #6
    //   103: new java/lang/StringBuilder
    //   106: astore_3
    //   107: aload_3
    //   108: invokespecial <init> : ()V
    //   111: aload #6
    //   113: aload_3
    //   114: aload #5
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc_w ':string/'
    //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload #8
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual toString : ()Ljava/lang/String;
    //   133: aload #7
    //   135: iconst_1
    //   136: invokevirtual getValue : (Ljava/lang/String;Landroid/util/TypedValue;Z)V
    //   139: aload #7
    //   141: getfield string : Ljava/lang/CharSequence;
    //   144: ifnull -> 241
    //   147: aload #7
    //   149: getfield string : Ljava/lang/CharSequence;
    //   152: invokevirtual toString : ()Ljava/lang/String;
    //   155: astore_3
    //   156: iload #4
    //   158: ifeq -> 281
    //   161: aload_3
    //   162: ifnonnull -> 281
    //   165: new com/google/ads/internal/b
    //   168: dup
    //   169: new java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial <init> : ()V
    //   176: ldc_w 'Required XML attribute "'
    //   179: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_1
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: ldc_w '" missing'
    //   189: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual toString : ()Ljava/lang/String;
    //   195: iconst_1
    //   196: invokespecial <init> : (Ljava/lang/String;Z)V
    //   199: athrow
    //   200: astore_3
    //   201: new com/google/ads/internal/b
    //   204: dup
    //   205: new java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial <init> : ()V
    //   212: ldc_w 'Could not find resource for '
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc_w ': '
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_2
    //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual toString : ()Ljava/lang/String;
    //   235: iconst_1
    //   236: aload_3
    //   237: invokespecial <init> : (Ljava/lang/String;ZLjava/lang/Throwable;)V
    //   240: athrow
    //   241: new com/google/ads/internal/b
    //   244: dup
    //   245: new java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial <init> : ()V
    //   252: ldc_w 'Resource '
    //   255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: aload_1
    //   259: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: ldc_w ' was not a string: '
    //   265: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   268: aload #7
    //   270: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   273: invokevirtual toString : ()Ljava/lang/String;
    //   276: iconst_1
    //   277: invokespecial <init> : (Ljava/lang/String;Z)V
    //   280: athrow
    //   281: aload_3
    //   282: areturn
    // Exception table:
    //   from	to	target	type
    //   97	139	200	android/content/res/Resources$NotFoundException
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
      paramInt = a(paramContext, adSize.a());
      int i = a(paramContext, adSize.b());
      linearLayout1.addView((View)textView, paramInt - 2, i - 2);
      linearLayout2.addView((View)linearLayout1);
      addView((View)linearLayout2, paramInt, i);
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\AdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */