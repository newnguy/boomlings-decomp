package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ao {
  private static ao b = null;
  
  private static ar c;
  
  private static Bitmap o;
  
  Context a;
  
  private String d = null;
  
  private String e = null;
  
  private int f = 5;
  
  private Vector g;
  
  private Hashtable h;
  
  private Hashtable i;
  
  private boolean j = false;
  
  private boolean k = false;
  
  private boolean l = false;
  
  private boolean m = false;
  
  private as n;
  
  public ao(Context paramContext) {
    this.a = paramContext;
    b = this;
    this.d = Environment.getExternalStorageDirectory().toString() + "/tjcache/data/";
    this.e = Environment.getExternalStorageDirectory().toString() + "/tjcache/tmp/";
    an.a(new File(Environment.getExternalStorageDirectory().toString() + "/tapjoy/"));
    an.a(new File(this.e));
    this.g = new Vector();
    this.h = new Hashtable<Object, Object>();
    this.i = new Hashtable<Object, Object>();
    if (h.e("video_cache_count") != null && h.e("video_cache_count").length() > 0)
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        aj.a("TapjoyVideo", stringBuilder.append("Setting video cache count to: ").append(h.e("video_cache_count")).toString());
        a(Integer.parseInt(h.e("video_cache_count")));
      } catch (Exception exception) {
        aj.b("TapjoyVideo", "Error, invalid value for video_cache_count: " + h.e("video_cache_count"));
      }  
    b();
  }
  
  public static ao a() {
    return b;
  }
  
  private boolean a(String paramString) {
    boolean bool;
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    aj.a("TapjoyVideo", "========================================");
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
      this(paramString.getBytes("UTF-8"));
      Document document = documentBuilderFactory.newDocumentBuilder().parse(byteArrayInputStream);
      document.getDocumentElement().normalize();
      NodeList nodeList1 = document.getElementsByTagName("TapjoyVideos");
      NodeList nodeList2 = nodeList1.item(0).getChildNodes();
      NamedNodeMap namedNodeMap = nodeList1.item(0).getAttributes();
      if (namedNodeMap.getNamedItem("cache_auto") != null && namedNodeMap.getNamedItem("cache_auto").getNodeValue() != null)
        this.j = Boolean.valueOf(namedNodeMap.getNamedItem("cache_auto").getNodeValue()).booleanValue(); 
      if (namedNodeMap.getNamedItem("cache_wifi") != null && namedNodeMap.getNamedItem("cache_wifi").getNodeValue() != null)
        this.l = Boolean.valueOf(namedNodeMap.getNamedItem("cache_wifi").getNodeValue()).booleanValue(); 
      if (namedNodeMap.getNamedItem("cache_mobile") != null && namedNodeMap.getNamedItem("cache_mobile").getNodeValue() != null)
        this.m = Boolean.valueOf(namedNodeMap.getNamedItem("cache_mobile").getNodeValue()).booleanValue(); 
      StringBuilder stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyVideo", stringBuilder2.append("cacheAuto: ").append(this.j).toString());
      stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyVideo", stringBuilder2.append("cacheWifi: ").append(this.l).toString());
      stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyVideo", stringBuilder2.append("cache3g: ").append(this.m).toString());
      stringBuilder2 = new StringBuilder();
      this();
      aj.a("TapjoyVideo", stringBuilder2.append("nodelistParent length: ").append(nodeList1.getLength()).toString());
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      aj.a("TapjoyVideo", stringBuilder1.append("nodelist length: ").append(nodeList2.getLength()).toString());
      byte b = 0;
      label81: while (true) {
        if (b < nodeList2.getLength()) {
          Node node = nodeList2.item(b);
          as as1 = new as();
          this();
          if (node != null) {
            if (node.getNodeType() == 1) {
              node = node;
              String str = an.a(node.getElementsByTagName("ClickURL"));
              if (str != null && !str.equals(""))
                as1.b = str; 
              str = an.a(node.getElementsByTagName("OfferID"));
              if (str != null && !str.equals(""))
                as1.a = str; 
              str = an.a(node.getElementsByTagName("Name"));
              if (str != null && !str.equals(""))
                as1.d = str; 
              str = an.a(node.getElementsByTagName("Amount"));
              if (str != null && !str.equals(""))
                as1.f = str; 
              str = an.a(node.getElementsByTagName("CurrencyName"));
              if (str != null && !str.equals(""))
                as1.e = str; 
              str = an.a(node.getElementsByTagName("VideoURL"));
              if (str != null && !str.equals(""))
                as1.c = str; 
              str = an.a(node.getElementsByTagName("IconURL"));
              if (str != null && !str.equals(""))
                as1.g = str; 
              aj.a("TapjoyVideo", "-----");
              StringBuilder stringBuilder = new StringBuilder();
              this();
              aj.a("TapjoyVideo", stringBuilder.append("videoObject.offerID: ").append(as1.a).toString());
              stringBuilder = new StringBuilder();
              this();
              aj.a("TapjoyVideo", stringBuilder.append("videoObject.videoAdName: ").append(as1.d).toString());
              stringBuilder = new StringBuilder();
              this();
              aj.a("TapjoyVideo", stringBuilder.append("videoObject.videoURL: ").append(as1.c).toString());
              NodeList nodeList = node.getElementsByTagName("Buttons").item(0).getChildNodes();
              byte b1 = 0;
              label78: while (true) {
                if (b1 < nodeList.getLength()) {
                  NodeList nodeList3 = nodeList.item(b1).getChildNodes();
                  if (nodeList3.getLength() == 0)
                    continue; 
                  String str1 = "";
                  String str2 = "";
                  byte b2 = 0;
                  while (true) {
                    if (b2 < nodeList3.getLength()) {
                      if ((Element)nodeList3.item(b2) != null) {
                        String str4 = ((Element)nodeList3.item(b2)).getTagName();
                        if (str4.equals("Name") && nodeList3.item(b2).getFirstChild() != null) {
                          str4 = nodeList3.item(b2).getFirstChild().getNodeValue();
                          str1 = str2;
                          str2 = str4;
                          continue;
                        } 
                        if (str4.equals("URL") && nodeList3.item(b2).getFirstChild() != null) {
                          str4 = nodeList3.item(b2).getFirstChild().getNodeValue();
                          str2 = str1;
                          str1 = str4;
                          continue;
                        } 
                      } 
                    } else {
                      StringBuilder stringBuilder3 = new StringBuilder();
                      this();
                      aj.a("TapjoyVideo", stringBuilder3.append("name: ").append(str1).append(", url: ").append(str2).toString());
                      as1.a(str1, str2);
                      b1++;
                      continue label78;
                    } 
                    String str3 = str1;
                    str1 = str2;
                    str2 = str3;
                    continue;
                    b2++;
                    str3 = str2;
                    str2 = str1;
                    str1 = str3;
                  } 
                  break;
                } 
                this.g.addElement(as1.a);
                this.h.put(as1.a, as1);
                b++;
                continue label81;
              } 
              break;
            } 
            continue;
          } 
          continue;
        } 
        aj.a("TapjoyVideo", "========================================");
        return true;
      } 
    } catch (Exception exception) {
      aj.b("TapjoyVideo", "Error parsing XML: " + exception.toString());
      bool = false;
    } 
    return bool;
  }
  
  public static void b(int paramInt) {
    if (c != null)
      c.videoError(paramInt); 
  }
  
  private void b(String paramString) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #11
    //   3: aconst_null
    //   4: astore #10
    //   6: iconst_0
    //   7: istore_3
    //   8: ldc 'TapjoyVideo'
    //   10: new java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial <init> : ()V
    //   17: ldc_w 'download and cache video from: '
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_1
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   33: invokestatic currentTimeMillis : ()J
    //   36: lstore #4
    //   38: new java/net/URL
    //   41: astore #8
    //   43: aload #8
    //   45: aload_1
    //   46: invokespecial <init> : (Ljava/lang/String;)V
    //   49: aload #8
    //   51: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   54: astore #9
    //   56: aload #9
    //   58: sipush #15000
    //   61: invokevirtual setConnectTimeout : (I)V
    //   64: aload #9
    //   66: sipush #30000
    //   69: invokevirtual setReadTimeout : (I)V
    //   72: aload #9
    //   74: invokevirtual connect : ()V
    //   77: new java/io/BufferedInputStream
    //   80: astore #8
    //   82: aload #8
    //   84: aload #9
    //   86: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   89: invokespecial <init> : (Ljava/io/InputStream;)V
    //   92: new java/io/File
    //   95: astore #9
    //   97: aload #9
    //   99: aload_0
    //   100: getfield d : Ljava/lang/String;
    //   103: invokespecial <init> : (Ljava/lang/String;)V
    //   106: aload_1
    //   107: iconst_0
    //   108: aload_1
    //   109: ldc_w '/'
    //   112: invokevirtual lastIndexOf : (Ljava/lang/String;)I
    //   115: iconst_1
    //   116: iadd
    //   117: invokevirtual substring : (II)Ljava/lang/String;
    //   120: astore #13
    //   122: aload_1
    //   123: aload_1
    //   124: ldc_w '/'
    //   127: invokevirtual lastIndexOf : (Ljava/lang/String;)I
    //   130: iconst_1
    //   131: iadd
    //   132: invokevirtual substring : (I)Ljava/lang/String;
    //   135: astore_1
    //   136: aload_1
    //   137: iconst_0
    //   138: aload_1
    //   139: bipush #46
    //   141: invokevirtual indexOf : (I)I
    //   144: invokevirtual substring : (II)Ljava/lang/String;
    //   147: astore #12
    //   149: new java/lang/StringBuilder
    //   152: astore_1
    //   153: aload_1
    //   154: invokespecial <init> : ()V
    //   157: ldc 'TapjoyVideo'
    //   159: aload_1
    //   160: ldc_w 'fileDir: '
    //   163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload #9
    //   168: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   171: invokevirtual toString : ()Ljava/lang/String;
    //   174: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   177: new java/lang/StringBuilder
    //   180: astore_1
    //   181: aload_1
    //   182: invokespecial <init> : ()V
    //   185: ldc 'TapjoyVideo'
    //   187: aload_1
    //   188: ldc_w 'path: '
    //   191: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload #13
    //   196: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: invokevirtual toString : ()Ljava/lang/String;
    //   202: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   205: new java/lang/StringBuilder
    //   208: astore_1
    //   209: aload_1
    //   210: invokespecial <init> : ()V
    //   213: ldc 'TapjoyVideo'
    //   215: aload_1
    //   216: ldc_w 'file name: '
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload #12
    //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual toString : ()Ljava/lang/String;
    //   230: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   233: aload #9
    //   235: invokevirtual mkdirs : ()Z
    //   238: ifeq -> 272
    //   241: new java/lang/StringBuilder
    //   244: astore_1
    //   245: aload_1
    //   246: invokespecial <init> : ()V
    //   249: ldc 'TapjoyVideo'
    //   251: aload_1
    //   252: ldc_w 'created directory at: '
    //   255: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: aload #9
    //   260: invokevirtual getPath : ()Ljava/lang/String;
    //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: invokevirtual toString : ()Ljava/lang/String;
    //   269: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   272: new java/io/File
    //   275: astore_1
    //   276: aload_1
    //   277: aload_0
    //   278: getfield d : Ljava/lang/String;
    //   281: aload #12
    //   283: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   286: new java/io/FileOutputStream
    //   289: astore #11
    //   291: aload #11
    //   293: aload_1
    //   294: invokespecial <init> : (Ljava/io/File;)V
    //   297: new java/lang/StringBuilder
    //   300: astore #9
    //   302: aload #9
    //   304: invokespecial <init> : ()V
    //   307: ldc 'TapjoyVideo'
    //   309: aload #9
    //   311: ldc_w 'downloading video file to: '
    //   314: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: aload_1
    //   318: invokevirtual toString : ()Ljava/lang/String;
    //   321: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: invokevirtual toString : ()Ljava/lang/String;
    //   327: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   330: sipush #1024
    //   333: newarray byte
    //   335: astore #9
    //   337: aload #8
    //   339: aload #9
    //   341: invokevirtual read : ([B)I
    //   344: istore_2
    //   345: iload_2
    //   346: iconst_m1
    //   347: if_icmpeq -> 543
    //   350: aload #11
    //   352: aload #9
    //   354: iconst_0
    //   355: iload_2
    //   356: invokevirtual write : ([BII)V
    //   359: goto -> 337
    //   362: astore #9
    //   364: aload #11
    //   366: astore #10
    //   368: ldc 'TapjoyVideo'
    //   370: new java/lang/StringBuilder
    //   373: dup
    //   374: invokespecial <init> : ()V
    //   377: ldc_w 'Network timeout: '
    //   380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: aload #9
    //   385: invokevirtual toString : ()Ljava/lang/String;
    //   388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: invokevirtual toString : ()Ljava/lang/String;
    //   394: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   397: iconst_1
    //   398: istore_2
    //   399: aload_1
    //   400: astore #9
    //   402: iconst_1
    //   403: istore_3
    //   404: aload #10
    //   406: astore_1
    //   407: iload_2
    //   408: iconst_1
    //   409: if_icmpne -> 429
    //   412: ldc 'TapjoyVideo'
    //   414: ldc_w 'Network timeout'
    //   417: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   420: aload #8
    //   422: invokevirtual close : ()V
    //   425: aload_1
    //   426: invokevirtual close : ()V
    //   429: iload_2
    //   430: ifne -> 693
    //   433: iload_3
    //   434: ifne -> 693
    //   437: aload_0
    //   438: getfield g : Ljava/util/Vector;
    //   441: iconst_0
    //   442: invokevirtual elementAt : (I)Ljava/lang/Object;
    //   445: checkcast java/lang/String
    //   448: astore_1
    //   449: aload_0
    //   450: getfield h : Ljava/util/Hashtable;
    //   453: aload_1
    //   454: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   457: checkcast com/tapjoy/as
    //   460: astore #8
    //   462: aload #8
    //   464: aload #9
    //   466: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   469: putfield i : Ljava/lang/String;
    //   472: aload_0
    //   473: getfield i : Ljava/util/Hashtable;
    //   476: aload_1
    //   477: aload #8
    //   479: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   482: pop
    //   483: aload_0
    //   484: getfield h : Ljava/util/Hashtable;
    //   487: aload_1
    //   488: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   491: pop
    //   492: aload_0
    //   493: getfield g : Ljava/util/Vector;
    //   496: iconst_0
    //   497: invokevirtual removeElementAt : (I)V
    //   500: aload_0
    //   501: invokespecial i : ()V
    //   504: new java/lang/StringBuilder
    //   507: astore_1
    //   508: aload_1
    //   509: invokespecial <init> : ()V
    //   512: ldc 'TapjoyVideo'
    //   514: aload_1
    //   515: ldc_w 'video cached in: '
    //   518: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: invokestatic currentTimeMillis : ()J
    //   524: lload #4
    //   526: lsub
    //   527: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   530: ldc_w 'ms'
    //   533: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   536: invokevirtual toString : ()Ljava/lang/String;
    //   539: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   542: return
    //   543: aload #11
    //   545: invokevirtual close : ()V
    //   548: aload #8
    //   550: invokevirtual close : ()V
    //   553: new java/lang/StringBuilder
    //   556: astore #9
    //   558: aload #9
    //   560: invokespecial <init> : ()V
    //   563: ldc 'TapjoyVideo'
    //   565: aload #9
    //   567: ldc_w 'FILE SIZE: '
    //   570: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: aload_1
    //   574: invokevirtual length : ()J
    //   577: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   580: invokevirtual toString : ()Ljava/lang/String;
    //   583: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)V
    //   586: aload_1
    //   587: invokevirtual length : ()J
    //   590: lstore #6
    //   592: lload #6
    //   594: lconst_0
    //   595: lcmp
    //   596: ifne -> 762
    //   599: iconst_1
    //   600: istore_2
    //   601: aload_1
    //   602: astore #9
    //   604: aload #11
    //   606: astore_1
    //   607: goto -> 407
    //   610: astore #9
    //   612: aconst_null
    //   613: astore_1
    //   614: aconst_null
    //   615: astore #8
    //   617: aload #11
    //   619: astore #10
    //   621: ldc 'TapjoyVideo'
    //   623: new java/lang/StringBuilder
    //   626: dup
    //   627: invokespecial <init> : ()V
    //   630: ldc_w 'Error caching video file: '
    //   633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   636: aload #9
    //   638: invokevirtual toString : ()Ljava/lang/String;
    //   641: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: invokevirtual toString : ()Ljava/lang/String;
    //   647: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   650: aload #10
    //   652: astore #9
    //   654: iconst_0
    //   655: istore_2
    //   656: iconst_1
    //   657: istore_3
    //   658: goto -> 407
    //   661: astore_1
    //   662: ldc 'TapjoyVideo'
    //   664: new java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial <init> : ()V
    //   671: ldc_w 'error caching video ???: '
    //   674: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: aload_1
    //   678: invokevirtual toString : ()Ljava/lang/String;
    //   681: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   684: invokevirtual toString : ()Ljava/lang/String;
    //   687: invokestatic b : (Ljava/lang/String;Ljava/lang/String;)V
    //   690: goto -> 542
    //   693: iconst_2
    //   694: invokestatic b : (I)V
    //   697: goto -> 542
    //   700: astore_1
    //   701: goto -> 429
    //   704: astore #9
    //   706: aconst_null
    //   707: astore_1
    //   708: aload #11
    //   710: astore #10
    //   712: goto -> 621
    //   715: astore #9
    //   717: aconst_null
    //   718: astore #11
    //   720: aload_1
    //   721: astore #10
    //   723: aload #11
    //   725: astore_1
    //   726: goto -> 621
    //   729: astore #9
    //   731: aload_1
    //   732: astore #10
    //   734: aload #11
    //   736: astore_1
    //   737: goto -> 621
    //   740: astore #9
    //   742: aconst_null
    //   743: astore_1
    //   744: aconst_null
    //   745: astore #8
    //   747: goto -> 368
    //   750: astore #9
    //   752: aconst_null
    //   753: astore_1
    //   754: goto -> 368
    //   757: astore #9
    //   759: goto -> 368
    //   762: iconst_0
    //   763: istore_2
    //   764: aload_1
    //   765: astore #9
    //   767: aload #11
    //   769: astore_1
    //   770: goto -> 407
    // Exception table:
    //   from	to	target	type
    //   38	92	740	java/net/SocketTimeoutException
    //   38	92	610	java/lang/Exception
    //   92	272	750	java/net/SocketTimeoutException
    //   92	272	704	java/lang/Exception
    //   272	286	750	java/net/SocketTimeoutException
    //   272	286	704	java/lang/Exception
    //   286	297	757	java/net/SocketTimeoutException
    //   286	297	715	java/lang/Exception
    //   297	337	362	java/net/SocketTimeoutException
    //   297	337	729	java/lang/Exception
    //   337	345	362	java/net/SocketTimeoutException
    //   337	345	729	java/lang/Exception
    //   350	359	362	java/net/SocketTimeoutException
    //   350	359	729	java/lang/Exception
    //   420	429	700	java/lang/Exception
    //   437	542	661	java/lang/Exception
    //   543	592	362	java/net/SocketTimeoutException
    //   543	592	729	java/lang/Exception
  }
  
  public static void e() {
    if (c != null)
      c.videoStart(); 
  }
  
  public static void f() {
    if (c != null)
      c.videoComplete(); 
  }
  
  public static Bitmap g() {
    return o;
  }
  
  private void h() {
    aj.a("TapjoyVideo", "cachedVideos size: " + this.i.size());
    for (Map.Entry entry : this.i.entrySet())
      aj.a("TapjoyVideo", "key: " + (String)entry.getKey() + ", name: " + ((as)entry.getValue()).d); 
  }
  
  private void i() {
    String str2 = "";
    String str1 = str2;
    if (this.i != null) {
      str1 = str2;
      if (this.i.size() > 0) {
        Enumeration<String> enumeration = this.i.keys();
        str1 = "";
        while (enumeration.hasMoreElements()) {
          str2 = enumeration.nextElement();
          str2 = str1 + str2;
          str1 = str2;
          if (enumeration.hasMoreElements())
            str1 = str2 + ","; 
        } 
        aj.a("TapjoyVideo", "cachedVideos size: " + this.i.size());
      } 
    } 
    aj.a("TapjoyVideo", "videoIDs: [" + str1 + "]");
    h.c(str1);
  }
  
  private boolean j() {
    byte b1;
    boolean bool2 = false;
    byte b2 = 0;
    File[] arrayOfFile = (new File(this.d)).listFiles();
    if (this.h == null) {
      aj.b("TapjoyVideo", "Error: uncachedVideos is null");
      b1 = 0;
    } else {
      b1 = 1;
    } 
    if (this.i == null) {
      aj.b("TapjoyVideo", "Error: cachedVideos is null");
      b1 = 0;
    } 
    if (this.g == null) {
      aj.b("TapjoyVideo", "Error: videoQueue is null");
      b1 = 0;
    } 
    boolean bool1 = bool2;
    if (b1) {
      bool1 = bool2;
      if (arrayOfFile != null) {
        for (b1 = b2; b1 < arrayOfFile.length; b1++) {
          String str = arrayOfFile[b1].getName();
          aj.a("TapjoyVideo", "-----");
          aj.a("TapjoyVideo", "Examining cached file[" + b1 + "]: " + arrayOfFile[b1].getAbsolutePath() + " --- " + arrayOfFile[b1].getName());
          if (this.h.containsKey(str)) {
            aj.a("TapjoyVideo", "Local file found");
            as as1 = (as)this.h.get(str);
            if (as1 != null) {
              String str1 = (new am()).b(as1.c);
              aj.a("TapjoyVideo", "local file size: " + arrayOfFile[b1].length() + " vs. target: " + str1);
              if (str1 != null && Integer.parseInt(str1) == arrayOfFile[b1].length()) {
                as1.i = arrayOfFile[b1].getAbsolutePath();
                this.i.put(str, as1);
                this.h.remove(str);
                this.g.remove(str);
                aj.a("TapjoyVideo", "VIDEO PREVIOUSLY CACHED -- " + str + ", location: " + as1.i);
              } else {
                aj.a("TapjoyVideo", "file size mismatch --- deleting video: " + arrayOfFile[b1].getAbsolutePath());
                an.a(arrayOfFile[b1]);
              } 
            } 
          } else {
            aj.a("TapjoyVideo", "VIDEO EXPIRED? removing video from cache: " + str + " --- " + arrayOfFile[b1].getAbsolutePath());
            an.a(arrayOfFile[b1]);
          } 
        } 
        bool1 = true;
      } 
    } 
    return bool1;
  }
  
  public void a(int paramInt) {
    this.f = paramInt;
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    boolean bool;
    null = false;
    aj.a("TapjoyVideo", "Starting video activity with video: " + paramString1);
    if (paramString1 == null || paramString4 == null || paramString5 == null || paramString1.length() == 0 || paramString4.length() == 0 || paramString5.length() == 0) {
      aj.a("TapjoyVideo", "aborting video playback... invalid or missing parameter");
      return null;
    } 
    this.n = (as)this.i.get(paramString1);
    if (!"mounted".equals(Environment.getExternalStorageState())) {
      aj.b("TapjoyVideo", "Cannot access external storage");
      b(1);
      return null;
    } 
    if (this.n == null) {
      aj.a("TapjoyVideo", "video not cached... checking uncached videos");
      this.n = (as)this.h.get(paramString1);
      if (this.n == null)
        if (paramString6 != null && paramString6.length() > 0) {
          as as1 = new as();
          as1.a = paramString1;
          as1.e = paramString2;
          as1.f = paramString3;
          as1.b = paramString4;
          as1.h = paramString5;
          as1.c = paramString6;
          this.h.put(paramString1, as1);
          this.n = (as)this.h.get(paramString1);
        } else {
          aj.b("TapjoyVideo", "no video data and no video url - aborting playback...");
          return null;
        }  
      bool = false;
    } else {
      bool = true;
    } 
    this.n.e = paramString2;
    this.n.f = paramString3;
    this.n.b = paramString4;
    this.n.h = paramString5;
    this.n.c = paramString6;
    aj.a("TapjoyVideo", "videoToPlay: " + this.n.a);
    aj.a("TapjoyVideo", "amount: " + this.n.f);
    aj.a("TapjoyVideo", "currency: " + this.n.e);
    aj.a("TapjoyVideo", "clickURL: " + this.n.b);
    aj.a("TapjoyVideo", "location: " + this.n.i);
    aj.a("TapjoyVideo", "webviewURL: " + this.n.h);
    aj.a("TapjoyVideo", "videoURL: " + this.n.c);
    if (bool && this.n.i != null) {
      File file = new File(this.n.i);
      if (file == null || !file.exists()) {
        aj.b("TapjoyVideo", "video file does not exist.");
        return null;
      } 
    } 
    Intent intent = new Intent(this.a, TapjoyVideoView.class);
    intent.setFlags(268435456);
    intent.putExtra("VIDEO_PATH", paramString1);
    this.a.startActivity(intent);
    return true;
  }
  
  public void b() {
    aj.a("TapjoyVideo", "initVideoAd");
    if (h.e("disable_videos") != null && h.e("disable_videos").equals("true")) {
      aj.a("TapjoyVideo", "disable_videos: " + h.e("disable_videos") + ". Aborting video initializing... ");
      h.a(false);
      return;
    } 
    i();
    (new Thread(new ap(this))).start();
    h.a(true);
  }
  
  public as c() {
    return this.n;
  }
  
  public void d() {
    (new Thread(new aq(this))).start();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */