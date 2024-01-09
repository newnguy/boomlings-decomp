package com.tapjoy;

import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class am {
    public ai a(String str, String str2) {
        return a(str, str2, 0);
    }

    public ai a(String str, String str2, int i) {
        HttpURLConnection httpURLConnection;
        ai aiVar = new ai();
        HttpURLConnection httpURLConnection2 = null;
        try {
            String replaceAll = (str + str2).replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
            aj.a("TapjoyURLConnection", "baseURL: " + str);
            aj.a("TapjoyURLConnection", "requestURL: " + replaceAll);
            aj.a("TapjoyURLConnection", "type: " + i);
            httpURLConnection = (HttpURLConnection) new URL(replaceAll).openConnection();
        } catch (Exception e) {
            e = e;
        }
        try {
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            if (i == 1) {
                httpURLConnection.setRequestMethod("POST");
            }
            httpURLConnection.connect();
            aiVar.a = httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine + '\n');
            }
            aiVar.c = sb.toString();
            String headerField = httpURLConnection.getHeaderField("content-length");
            if (headerField != null) {
                try {
                    aiVar.b = Integer.valueOf(headerField).intValue();
                } catch (Exception e2) {
                    aj.b("TapjoyURLConnection", "Exception: " + e2.toString());
                }
            }
        } catch (Exception e3) {
            httpURLConnection2 = httpURLConnection;
            e = e3;
            aj.b("TapjoyURLConnection", "Exception: " + e.toString());
            if (httpURLConnection2 != null) {
                try {
                    if (aiVar.c == null) {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                        StringBuilder sb2 = new StringBuilder();
                        while (true) {
                            String readLine2 = bufferedReader2.readLine();
                            if (readLine2 == null) {
                                break;
                            }
                            sb2.append(readLine2 + '\n');
                        }
                        aiVar.c = sb2.toString();
                    }
                } catch (Exception e4) {
                    aj.b("TapjoyURLConnection", "Exception trying to get error code/content: " + e4.toString());
                }
            }
            aj.a("TapjoyURLConnection", "--------------------");
            aj.a("TapjoyURLConnection", "response status: " + aiVar.a);
            aj.a("TapjoyURLConnection", "response size: " + aiVar.b);
            aj.a("TapjoyURLConnection", "response: ");
            aj.a("TapjoyURLConnection", "" + aiVar.c);
            aj.a("TapjoyURLConnection", "--------------------");
            return aiVar;
        }
        aj.a("TapjoyURLConnection", "--------------------");
        aj.a("TapjoyURLConnection", "response status: " + aiVar.a);
        aj.a("TapjoyURLConnection", "response size: " + aiVar.b);
        aj.a("TapjoyURLConnection", "response: ");
        aj.a("TapjoyURLConnection", "" + aiVar.c);
        aj.a("TapjoyURLConnection", "--------------------");
        return aiVar;
    }

    public String a(String str) {
        return b(str, "");
    }

    public String b(String str) {
        String str2;
        try {
            String replaceAll = str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
            aj.a("TapjoyURLConnection", "requestURL: " + replaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(replaceAll).openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            str2 = httpURLConnection.getHeaderField("content-length");
        } catch (Exception e) {
            aj.b("TapjoyURLConnection", "Exception: " + e.toString());
            str2 = null;
        }
        aj.a("TapjoyURLConnection", "content-length: " + str2);
        return str2;
    }

    public String b(String str, String str2) {
        String str3;
        Exception e;
        try {
            String replaceAll = (str + str2).replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
            aj.a("TapjoyURLConnection", "baseURL: " + str);
            aj.a("TapjoyURLConnection", "requestURL: " + replaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(replaceAll).openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            String responseMessage = httpURLConnection.getResponseMessage();
            try {
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine + '\n');
                }
                str3 = sb.toString();
                try {
                    aj.a("TapjoyURLConnection", "--------------------");
                    aj.a("TapjoyURLConnection", "response size: " + str3.length());
                    aj.a("TapjoyURLConnection", "response: ");
                    aj.a("TapjoyURLConnection", "" + str3);
                    aj.a("TapjoyURLConnection", "--------------------");
                } catch (Exception e2) {
                    e = e2;
                    aj.b("TapjoyURLConnection", "Exception: " + e.toString());
                    return str3;
                }
            } catch (Exception e3) {
                str3 = responseMessage;
                e = e3;
            }
        } catch (Exception e4) {
            str3 = null;
            e = e4;
        }
        return str3;
    }
}
