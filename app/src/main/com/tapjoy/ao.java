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

/* loaded from: classes.dex */
public class ao {
    private static ao b = null;
    private static ar c;
    private static Bitmap o;
    Context a;
    private String d;
    private String e;
    private Vector g;
    private Hashtable h;
    private Hashtable i;
    private as n;
    private int f = 5;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;

    public ao(Context context) {
        this.d = null;
        this.e = null;
        this.a = context;
        b = this;
        this.d = Environment.getExternalStorageDirectory().toString() + "/tjcache/data/";
        this.e = Environment.getExternalStorageDirectory().toString() + "/tjcache/tmp/";
        an.a(new File(Environment.getExternalStorageDirectory().toString() + "/tapjoy/"));
        an.a(new File(this.e));
        this.g = new Vector();
        this.h = new Hashtable();
        this.i = new Hashtable();
        if (h.e("video_cache_count") != null && h.e("video_cache_count").length() > 0) {
            try {
                aj.a("TapjoyVideo", "Setting video cache count to: " + h.e("video_cache_count"));
                a(Integer.parseInt(h.e("video_cache_count")));
            } catch (Exception e) {
                aj.b("TapjoyVideo", "Error, invalid value for video_cache_count: " + h.e("video_cache_count"));
            }
        }
        b();
    }

    public static ao a() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        String str2;
        String str3;
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        aj.a("TapjoyVideo", "========================================");
        try {
            Document parse = newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
            parse.getDocumentElement().normalize();
            NodeList elementsByTagName = parse.getElementsByTagName("TapjoyVideos");
            NodeList childNodes = elementsByTagName.item(0).getChildNodes();
            NamedNodeMap attributes = elementsByTagName.item(0).getAttributes();
            if (attributes.getNamedItem("cache_auto") != null && attributes.getNamedItem("cache_auto").getNodeValue() != null) {
                this.j = Boolean.valueOf(attributes.getNamedItem("cache_auto").getNodeValue()).booleanValue();
            }
            if (attributes.getNamedItem("cache_wifi") != null && attributes.getNamedItem("cache_wifi").getNodeValue() != null) {
                this.l = Boolean.valueOf(attributes.getNamedItem("cache_wifi").getNodeValue()).booleanValue();
            }
            if (attributes.getNamedItem("cache_mobile") != null && attributes.getNamedItem("cache_mobile").getNodeValue() != null) {
                this.m = Boolean.valueOf(attributes.getNamedItem("cache_mobile").getNodeValue()).booleanValue();
            }
            aj.a("TapjoyVideo", "cacheAuto: " + this.j);
            aj.a("TapjoyVideo", "cacheWifi: " + this.l);
            aj.a("TapjoyVideo", "cache3g: " + this.m);
            aj.a("TapjoyVideo", "nodelistParent length: " + elementsByTagName.getLength());
            aj.a("TapjoyVideo", "nodelist length: " + childNodes.getLength());
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                as asVar = new as();
                if (item != null && item.getNodeType() == 1) {
                    Element element = (Element) item;
                    String a = an.a(element.getElementsByTagName("ClickURL"));
                    if (a != null && !a.equals("")) {
                        asVar.b = a;
                    }
                    String a2 = an.a(element.getElementsByTagName("OfferID"));
                    if (a2 != null && !a2.equals("")) {
                        asVar.a = a2;
                    }
                    String a3 = an.a(element.getElementsByTagName("Name"));
                    if (a3 != null && !a3.equals("")) {
                        asVar.d = a3;
                    }
                    String a4 = an.a(element.getElementsByTagName("Amount"));
                    if (a4 != null && !a4.equals("")) {
                        asVar.f = a4;
                    }
                    String a5 = an.a(element.getElementsByTagName("CurrencyName"));
                    if (a5 != null && !a5.equals("")) {
                        asVar.e = a5;
                    }
                    String a6 = an.a(element.getElementsByTagName("VideoURL"));
                    if (a6 != null && !a6.equals("")) {
                        asVar.c = a6;
                    }
                    String a7 = an.a(element.getElementsByTagName("IconURL"));
                    if (a7 != null && !a7.equals("")) {
                        asVar.g = a7;
                    }
                    aj.a("TapjoyVideo", "-----");
                    aj.a("TapjoyVideo", "videoObject.offerID: " + asVar.a);
                    aj.a("TapjoyVideo", "videoObject.videoAdName: " + asVar.d);
                    aj.a("TapjoyVideo", "videoObject.videoURL: " + asVar.c);
                    NodeList childNodes2 = element.getElementsByTagName("Buttons").item(0).getChildNodes();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        NodeList childNodes3 = childNodes2.item(i2).getChildNodes();
                        if (childNodes3.getLength() != 0) {
                            String str4 = "";
                            String str5 = "";
                            int i3 = 0;
                            while (i3 < childNodes3.getLength()) {
                                if (((Element) childNodes3.item(i3)) != null) {
                                    String tagName = ((Element) childNodes3.item(i3)).getTagName();
                                    if (tagName.equals("Name") && childNodes3.item(i3).getFirstChild() != null) {
                                        String str6 = str5;
                                        str3 = childNodes3.item(i3).getFirstChild().getNodeValue();
                                        str2 = str6;
                                    } else if (tagName.equals("URL") && childNodes3.item(i3).getFirstChild() != null) {
                                        str2 = childNodes3.item(i3).getFirstChild().getNodeValue();
                                        str3 = str4;
                                    }
                                    i3++;
                                    str4 = str3;
                                    str5 = str2;
                                }
                                str2 = str5;
                                str3 = str4;
                                i3++;
                                str4 = str3;
                                str5 = str2;
                            }
                            aj.a("TapjoyVideo", "name: " + str4 + ", url: " + str5);
                            asVar.a(str4, str5);
                        }
                    }
                    this.g.addElement(asVar.a);
                    this.h.put(asVar.a, asVar);
                }
            }
            aj.a("TapjoyVideo", "========================================");
            return true;
        } catch (Exception e) {
            aj.b("TapjoyVideo", "Error parsing XML: " + e.toString());
            return false;
        }
    }

    public static void b(int i) {
        if (c != null) {
            c.videoError(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0141 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.ao.b(java.lang.String):void");
    }

    public static void e() {
        if (c != null) {
            c.videoStart();
        }
    }

    public static void f() {
        if (c != null) {
            c.videoComplete();
        }
    }

    public static Bitmap g() {
        return o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        aj.a("TapjoyVideo", "cachedVideos size: " + this.i.size());
        for (Map.Entry entry : this.i.entrySet()) {
            aj.a("TapjoyVideo", "key: " + ((String) entry.getKey()) + ", name: " + ((as) entry.getValue()).d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        String str;
        String str2 = "";
        if (this.i != null && this.i.size() > 0) {
            Enumeration keys = this.i.keys();
            while (true) {
                str = str2;
                if (!keys.hasMoreElements()) {
                    break;
                }
                str2 = str + ((String) keys.nextElement());
                if (keys.hasMoreElements()) {
                    str2 = str2 + ",";
                }
            }
            aj.a("TapjoyVideo", "cachedVideos size: " + this.i.size());
            str2 = str;
        }
        aj.a("TapjoyVideo", "videoIDs: [" + str2 + "]");
        h.c(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        boolean z;
        File[] listFiles = new File(this.d).listFiles();
        if (this.h == null) {
            aj.b("TapjoyVideo", "Error: uncachedVideos is null");
            z = false;
        } else {
            z = true;
        }
        if (this.i == null) {
            aj.b("TapjoyVideo", "Error: cachedVideos is null");
            z = false;
        }
        if (this.g == null) {
            aj.b("TapjoyVideo", "Error: videoQueue is null");
            z = false;
        }
        if (!z || listFiles == null) {
            return false;
        }
        for (int i = 0; i < listFiles.length; i++) {
            String name = listFiles[i].getName();
            aj.a("TapjoyVideo", "-----");
            aj.a("TapjoyVideo", "Examining cached file[" + i + "]: " + listFiles[i].getAbsolutePath() + " --- " + listFiles[i].getName());
            if (this.h.containsKey(name)) {
                aj.a("TapjoyVideo", "Local file found");
                as asVar = (as) this.h.get(name);
                if (asVar != null) {
                    String b2 = new am().b(asVar.c);
                    aj.a("TapjoyVideo", "local file size: " + listFiles[i].length() + " vs. target: " + b2);
                    if (b2 == null || Integer.parseInt(b2) != listFiles[i].length()) {
                        aj.a("TapjoyVideo", "file size mismatch --- deleting video: " + listFiles[i].getAbsolutePath());
                        an.a(listFiles[i]);
                    } else {
                        asVar.i = listFiles[i].getAbsolutePath();
                        this.i.put(name, asVar);
                        this.h.remove(name);
                        this.g.remove(name);
                        aj.a("TapjoyVideo", "VIDEO PREVIOUSLY CACHED -- " + name + ", location: " + asVar.i);
                    }
                }
            } else {
                aj.a("TapjoyVideo", "VIDEO EXPIRED? removing video from cache: " + name + " --- " + listFiles[i].getAbsolutePath());
                an.a(listFiles[i]);
            }
        }
        return true;
    }

    public void a(int i) {
        this.f = i;
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, String str6) {
        boolean z;
        File file;
        aj.a("TapjoyVideo", "Starting video activity with video: " + str);
        if (str == null || str4 == null || str5 == null || str.length() == 0 || str4.length() == 0 || str5.length() == 0) {
            aj.a("TapjoyVideo", "aborting video playback... invalid or missing parameter");
            return false;
        }
        this.n = (as) this.i.get(str);
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            aj.b("TapjoyVideo", "Cannot access external storage");
            b(1);
            return false;
        }
        if (this.n == null) {
            aj.a("TapjoyVideo", "video not cached... checking uncached videos");
            this.n = (as) this.h.get(str);
            if (this.n == null) {
                if (str6 == null || str6.length() <= 0) {
                    aj.b("TapjoyVideo", "no video data and no video url - aborting playback...");
                    return false;
                }
                as asVar = new as();
                asVar.a = str;
                asVar.e = str2;
                asVar.f = str3;
                asVar.b = str4;
                asVar.h = str5;
                asVar.c = str6;
                this.h.put(str, asVar);
                this.n = (as) this.h.get(str);
            }
            z = false;
        } else {
            z = true;
        }
        this.n.e = str2;
        this.n.f = str3;
        this.n.b = str4;
        this.n.h = str5;
        this.n.c = str6;
        aj.a("TapjoyVideo", "videoToPlay: " + this.n.a);
        aj.a("TapjoyVideo", "amount: " + this.n.f);
        aj.a("TapjoyVideo", "currency: " + this.n.e);
        aj.a("TapjoyVideo", "clickURL: " + this.n.b);
        aj.a("TapjoyVideo", "location: " + this.n.i);
        aj.a("TapjoyVideo", "webviewURL: " + this.n.h);
        aj.a("TapjoyVideo", "videoURL: " + this.n.c);
        if (z && this.n.i != null && ((file = new File(this.n.i)) == null || !file.exists())) {
            aj.b("TapjoyVideo", "video file does not exist.");
            return false;
        }
        Intent intent = new Intent(this.a, TapjoyVideoView.class);
        intent.setFlags(268435456);
        intent.putExtra("VIDEO_PATH", str);
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
        new Thread(new ap(this)).start();
        h.a(true);
    }

    public as c() {
        return this.n;
    }

    public void d() {
        new Thread(new aq(this)).start();
    }
}
