package com.b.a;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import org.json.JSONObject;

public final class s {
  private static boolean a = true;
  
  public static Bundle a(String paramString) {
    Bundle bundle = new Bundle();
    if (paramString != null) {
      String[] arrayOfString = paramString.split("&");
      int i = arrayOfString.length;
      byte b = 0;
      while (true) {
        if (b < i) {
          String[] arrayOfString1 = arrayOfString[b].split("=");
          if (arrayOfString1.length == 2)
            bundle.putString(URLDecoder.decode(arrayOfString1[0]), URLDecoder.decode(arrayOfString1[1])); 
          b++;
          continue;
        } 
        return bundle;
      } 
    } 
    return bundle;
  }
  
  public static String a(Bundle paramBundle) {
    if (paramBundle == null)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder();
    Iterator<String> iterator = paramBundle.keySet().iterator();
    boolean bool = true;
    while (true) {
      if (!iterator.hasNext())
        return stringBuilder.toString(); 
      String str = iterator.next();
      if (paramBundle.get(str) instanceof String) {
        if (bool) {
          bool = false;
        } else {
          stringBuilder.append("&");
        } 
        stringBuilder.append(String.valueOf(URLEncoder.encode(str)) + "=" + URLEncoder.encode(paramBundle.getString(str)));
      } 
    } 
  }
  
  public static String a(Bundle paramBundle, String paramString) {
    if (paramBundle == null)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder();
    Iterator<String> iterator = paramBundle.keySet().iterator();
    while (true) {
      if (!iterator.hasNext())
        return stringBuilder.toString(); 
      String str = iterator.next();
      Object object = paramBundle.get(str);
      if (object instanceof String) {
        stringBuilder.append("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n" + (String)object);
        stringBuilder.append("\r\n--" + paramString + "\r\n");
      } 
    } 
  }
  
  private static String a(InputStream paramInputStream) {
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1000);
    for (String str = bufferedReader.readLine();; str = bufferedReader.readLine()) {
      if (str == null) {
        paramInputStream.close();
        return stringBuilder.toString();
      } 
      stringBuilder.append(str);
    } 
  }
  
  public static String a(String paramString1, String paramString2, Bundle paramBundle) {
    String str1;
    String str2 = paramString1;
    if (paramString2.equals("GET"))
      str2 = String.valueOf(paramString1) + "?" + a(paramBundle); 
    a("Facebook-Util", String.valueOf(paramString2) + " URL: " + str2);
    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(str2)).openConnection();
    httpURLConnection.setRequestProperty("User-Agent", String.valueOf(System.getProperties().getProperty("http.agent")) + " FacebookAndroidSDK");
    if (!paramString2.equals("GET")) {
      Bundle bundle = new Bundle();
      Iterator<String> iterator = paramBundle.keySet().iterator();
      while (true) {
        String str3;
        if (!iterator.hasNext()) {
          if (!paramBundle.containsKey("method"))
            paramBundle.putString("method", paramString2); 
          if (paramBundle.containsKey("access_token"))
            paramBundle.putString("access_token", URLDecoder.decode(paramBundle.getString("access_token"))); 
          httpURLConnection.setRequestMethod("POST");
          httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
          httpURLConnection.setDoOutput(true);
          httpURLConnection.setDoInput(true);
          httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
          httpURLConnection.connect();
          BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
          bufferedOutputStream.write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
          bufferedOutputStream.write(a(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
          bufferedOutputStream.write((String.valueOf("\r\n") + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
          if (!bundle.isEmpty()) {
            iterator = bundle.keySet().iterator();
            while (true) {
              if (!iterator.hasNext()) {
                bufferedOutputStream.flush();
              } else {
                str3 = iterator.next();
                bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str3 + "\"" + "\r\n").getBytes());
                bufferedOutputStream.write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
                bufferedOutputStream.write(bundle.getByteArray(str3));
                bufferedOutputStream.write((String.valueOf("\r\n") + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
                continue;
              } 
              try {
                str1 = a(httpURLConnection.getInputStream());
              } catch (FileNotFoundException fileNotFoundException) {
                str1 = a(httpURLConnection.getErrorStream());
              } 
              return str1;
            } 
            break;
          } 
          continue;
        } 
        String str4 = iterator.next();
        Object object = str3.get(str4);
        if (object instanceof byte[])
          str1.putByteArray(str4, (byte[])object); 
      } 
    } 
    try {
      paramString1 = a(httpURLConnection.getInputStream());
    } catch (FileNotFoundException fileNotFoundException) {
      str1 = a(httpURLConnection.getErrorStream());
    } 
    return str1;
  }
  
  public static void a(Context paramContext) {
    CookieSyncManager.createInstance(paramContext);
    CookieManager.getInstance().removeAllCookie();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2) {
    AlertDialog.Builder builder = new AlertDialog.Builder(paramContext);
    builder.setTitle(paramString1);
    builder.setMessage(paramString2);
    builder.create().show();
  }
  
  public static void a(String paramString1, String paramString2) {
    if (a)
      Log.d(paramString1, paramString2); 
  }
  
  public static Bundle b(String paramString) {
    Bundle bundle;
    paramString = paramString.replace("fbconnect", "http");
    try {
      URL uRL = new URL();
      this(paramString);
      bundle = a(uRL.getQuery());
      bundle.putAll(a(uRL.getRef()));
    } catch (MalformedURLException malformedURLException) {
      bundle = new Bundle();
    } 
    return bundle;
  }
  
  public static JSONObject c(String paramString) {
    if (paramString.equals("false"))
      throw new m("request failed"); 
    String str = paramString;
    if (paramString.equals("true"))
      str = "{value : true}"; 
    JSONObject jSONObject = new JSONObject(str);
    if (jSONObject.has("error")) {
      jSONObject = jSONObject.getJSONObject("error");
      throw new m(jSONObject.getString("message"), jSONObject.getString("type"), 0);
    } 
    if (jSONObject.has("error_code") && jSONObject.has("error_msg"))
      throw new m(jSONObject.getString("error_msg"), "", Integer.parseInt(jSONObject.getString("error_code"))); 
    if (jSONObject.has("error_code"))
      throw new m("request failed", "", Integer.parseInt(jSONObject.getString("error_code"))); 
    if (jSONObject.has("error_msg"))
      throw new m(jSONObject.getString("error_msg")); 
    if (jSONObject.has("error_reason"))
      throw new m(jSONObject.getString("error_reason")); 
    return jSONObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */