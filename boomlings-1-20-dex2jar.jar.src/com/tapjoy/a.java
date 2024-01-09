package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import org.w3c.dom.Document;

public class a {
  private static ak d;
  
  private static al e;
  
  private static v f;
  
  String a = null;
  
  int b = 0;
  
  Context c;
  
  private String g = "";
  
  private String h = "";
  
  public a(Context paramContext) {
    this.c = paramContext;
  }
  
  private boolean a(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic c : (Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   6: astore #5
    //   8: aload #5
    //   10: ifnull -> 181
    //   13: aload #5
    //   15: ldc 'Success'
    //   17: invokeinterface getElementsByTagName : (Ljava/lang/String;)Lorg/w3c/dom/NodeList;
    //   22: invokestatic a : (Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   25: astore_1
    //   26: aload_1
    //   27: ifnull -> 202
    //   30: aload_1
    //   31: ldc 'true'
    //   33: invokevirtual equals : (Ljava/lang/Object;)Z
    //   36: ifeq -> 202
    //   39: aload #5
    //   41: ldc 'TapPoints'
    //   43: invokeinterface getElementsByTagName : (Ljava/lang/String;)Lorg/w3c/dom/NodeList;
    //   48: invokestatic a : (Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   51: astore_1
    //   52: aload #5
    //   54: ldc 'CurrencyName'
    //   56: invokeinterface getElementsByTagName : (Ljava/lang/String;)Lorg/w3c/dom/NodeList;
    //   61: invokestatic a : (Lorg/w3c/dom/NodeList;)Ljava/lang/String;
    //   64: astore #5
    //   66: aload_1
    //   67: ifnull -> 187
    //   70: aload #5
    //   72: ifnull -> 187
    //   75: aload_1
    //   76: invokestatic parseInt : (Ljava/lang/String;)I
    //   79: istore_2
    //   80: invokestatic i : ()I
    //   83: istore_3
    //   84: getstatic com/tapjoy/a.f : Lcom/tapjoy/v;
    //   87: ifnull -> 144
    //   90: iload_3
    //   91: sipush #-9999
    //   94: if_icmpeq -> 144
    //   97: iload_2
    //   98: iload_3
    //   99: if_icmple -> 144
    //   102: new java/lang/StringBuilder
    //   105: astore #6
    //   107: aload #6
    //   109: invokespecial <init> : ()V
    //   112: ldc 'TapjoyPoints'
    //   114: aload #6
    //   116: ldc 'earned: '
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: iload_2
    //   122: iload_3
    //   123: isub
    //   124: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   127: invokevirtual toString : ()Ljava/lang/String;
    //   130: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   133: getstatic com/tapjoy/a.f : Lcom/tapjoy/v;
    //   136: iload_2
    //   137: iload_3
    //   138: isub
    //   139: invokeinterface earnedTapPoints : (I)V
    //   144: aload_1
    //   145: invokestatic parseInt : (Ljava/lang/String;)I
    //   148: invokestatic a : (I)V
    //   151: getstatic com/tapjoy/a.d : Lcom/tapjoy/ak;
    //   154: aload #5
    //   156: aload_1
    //   157: invokestatic parseInt : (Ljava/lang/String;)I
    //   160: invokeinterface getUpdatePoints : (Ljava/lang/String;I)V
    //   165: iconst_1
    //   166: istore #4
    //   168: aload_0
    //   169: monitorexit
    //   170: iload #4
    //   172: ireturn
    //   173: astore_1
    //   174: ldc 'TapjoyPoints'
    //   176: ldc 'Error parsing XML.'
    //   178: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   181: iconst_0
    //   182: istore #4
    //   184: goto -> 168
    //   187: ldc 'TapjoyPoints'
    //   189: ldc 'Invalid XML: Missing tags.'
    //   191: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   194: goto -> 181
    //   197: astore_1
    //   198: aload_0
    //   199: monitorexit
    //   200: aload_1
    //   201: athrow
    //   202: ldc 'TapjoyPoints'
    //   204: ldc 'Invalid XML: Missing <Success> tag.'
    //   206: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   209: goto -> 181
    // Exception table:
    //   from	to	target	type
    //   2	8	197	finally
    //   13	26	197	finally
    //   30	66	197	finally
    //   75	90	173	java/lang/Exception
    //   75	90	197	finally
    //   102	144	173	java/lang/Exception
    //   102	144	197	finally
    //   144	165	173	java/lang/Exception
    //   144	165	197	finally
    //   174	181	197	finally
    //   187	194	197	finally
    //   202	209	197	finally
  }
  
  private boolean b(String paramString) {
    null = true;
    Document document = an.c(paramString);
    if (document != null) {
      String str;
      paramString = an.a(document.getElementsByTagName("Success"));
      if (paramString != null && paramString.equals("true")) {
        paramString = an.a(document.getElementsByTagName("TapPoints"));
        str = an.a(document.getElementsByTagName("CurrencyName"));
        if (paramString != null && str != null) {
          h.a(Integer.parseInt(paramString));
          e.getSpendPointsResponse(str, Integer.parseInt(paramString));
          return null;
        } 
        aj.b("TapjoyPoints", "Invalid XML: Missing tags.");
      } else {
        if (paramString != null && paramString.endsWith("false")) {
          paramString = an.a(str.getElementsByTagName("Message"));
          aj.a("TapjoyPoints", paramString);
          e.getSpendPointsResponseFailed(paramString);
          return null;
        } 
        aj.b("TapjoyPoints", "Invalid XML: Missing <Success> tag.");
      } 
    } 
    return false;
  }
  
  public void a() {
    aj.a("TapjoyOffers", "Showing offers with userID: " + h.e());
    Intent intent = new Intent(this.c, TJCOffersWebView.class);
    intent.setFlags(268435456);
    intent.putExtra("USER_ID", h.e());
    intent.putExtra("URL_PARAMS", h.c());
    this.c.startActivity(intent);
  }
  
  public void a(int paramInt, al paramal) {
    if (paramInt < 0) {
      aj.b("TapjoyPoints", "spendTapPoints error: amount must be a positive number");
      return;
    } 
    this.a = "" + paramInt;
    e = paramal;
    (new Thread(new c(this))).start();
  }
  
  public void a(ak paramak) {
    d = paramak;
    (new Thread(new b(this))).start();
  }
  
  public void a(v paramv) {
    f = paramv;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */