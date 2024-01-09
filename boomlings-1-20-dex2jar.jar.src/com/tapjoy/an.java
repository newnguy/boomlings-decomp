package com.tapjoy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class an {
  public static String a(String paramString) {
    return a("SHA-1", paramString);
  }
  
  public static String a(String paramString1, String paramString2) {
    byte[] arrayOfByte = new byte[40];
    MessageDigest messageDigest = MessageDigest.getInstance(paramString1);
    messageDigest.update(paramString2.getBytes("iso-8859-1"), 0, paramString2.length());
    return a(messageDigest.digest());
  }
  
  public static String a(NodeList paramNodeList) {
    byte b = 0;
    null = (Element)paramNodeList.item(0);
    if (null != null) {
      NodeList nodeList = null.getChildNodes();
      int i = nodeList.getLength();
      String str;
      for (str = ""; b < i; str = str1) {
        Node node = nodeList.item(b);
        String str1 = str;
        if (node != null)
          str1 = str + node.getNodeValue(); 
        b++;
      } 
      return (str != null && !str.equals("")) ? str.trim() : null;
    } 
    return null;
  }
  
  private static String a(byte[] paramArrayOfbyte) {
    StringBuffer stringBuffer = new StringBuffer();
    byte b = 0;
    label18: while (true) {
      if (b < paramArrayOfbyte.length) {
        int i = paramArrayOfbyte[b] >>> 4 & 0xF;
        byte b1 = 0;
        while (true) {
          if (i >= 0 && i <= 9) {
            stringBuffer.append((char)(i + 48));
          } else {
            stringBuffer.append((char)(i - 10 + 97));
          } 
          i = paramArrayOfbyte[b];
          if (b1 >= 1) {
            b++;
            continue label18;
          } 
          b1++;
          i &= 0xF;
        } 
        break;
      } 
      return stringBuffer.toString();
    } 
  }
  
  public static void a(File paramFile) {
    if (paramFile.isDirectory()) {
      File[] arrayOfFile = paramFile.listFiles();
      int i = arrayOfFile.length;
      for (byte b = 0; b < i; b++)
        a(arrayOfFile[b]); 
    } 
    aj.a("TapjoyUtil", "****************************************");
    aj.a("TapjoyUtil", "deleteFileOrDirectory: " + paramFile.getAbsolutePath());
    aj.a("TapjoyUtil", "****************************************");
    paramFile.delete();
  }
  
  public static String b(String paramString) {
    return a("SHA-256", paramString);
  }
  
  public static Document c(String paramString) {
    Exception exception2 = null;
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
      this(paramString.getBytes("UTF-8"));
      Document document = documentBuilderFactory.newDocumentBuilder().parse(byteArrayInputStream);
    } catch (Exception exception1) {
      aj.b("TapjoyUtil", "buildDocument exception: " + exception1.toString());
      exception1 = exception2;
    } 
    return (Document)exception1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */