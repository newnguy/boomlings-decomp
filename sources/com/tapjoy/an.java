package com.tapjoy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes.dex */
public class an {
    public static String a(String str) {
        return a("SHA-1", str);
    }

    public static String a(String str, String str2) {
        byte[] bArr = new byte[40];
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(str2.getBytes("iso-8859-1"), 0, str2.length());
        return a(messageDigest.digest());
    }

    public static String a(NodeList nodeList) {
        Node item;
        Element element = (Element) nodeList.item(0);
        if (element != null) {
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            String str = "";
            for (int i = 0; i < length; i++) {
                if (childNodes.item(i) != null) {
                    str = str + item.getNodeValue();
                }
            }
            if (str == null || str.equals("")) {
                return null;
            }
            return str.trim();
        }
        return null;
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >>> 4) & 15;
            int i3 = 0;
            while (true) {
                if (i2 < 0 || i2 > 9) {
                    stringBuffer.append((char) ((i2 - 10) + 97));
                } else {
                    stringBuffer.append((char) (i2 + 48));
                }
                int i4 = bArr[i] & 15;
                int i5 = i3 + 1;
                if (i3 >= 1) {
                    break;
                }
                i3 = i5;
                i2 = i4;
            }
        }
        return stringBuffer.toString();
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2);
            }
        }
        aj.a("TapjoyUtil", "****************************************");
        aj.a("TapjoyUtil", "deleteFileOrDirectory: " + file.getAbsolutePath());
        aj.a("TapjoyUtil", "****************************************");
        file.delete();
    }

    public static String b(String str) {
        return a("SHA-256", str);
    }

    public static Document c(String str) {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            return newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
        } catch (Exception e) {
            aj.b("TapjoyUtil", "buildDocument exception: " + e.toString());
            return null;
        }
    }
}
