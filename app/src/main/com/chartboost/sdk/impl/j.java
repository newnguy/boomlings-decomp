package com.chartboost.sdk.impl;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import com.chartboost.sdk.Chartboost;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes.dex */
public class j {
    private static int g = 0;
    private static HttpClient h = null;
    public String a;
    public c b;
    public String c;
    public SparseArray d;
    public int e = 1;
    public String f = "Loading...";

    /* loaded from: classes.dex */
    public class a extends AsyncTask {
        protected a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public b doInBackground(b... bVarArr) {
            int i;
            b bVar = bVarArr[0];
            k kVar = bVar.a;
            HttpPost httpPost = new HttpPost(String.valueOf(j.this.a) + kVar.a());
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json; charset=UTF-8");
            httpPost.setHeader("X-Chartboost-Client", "Chartboost-Android-SDK 3.1.5");
            httpPost.setHeader("X-Chartboost-API", "3.1.5");
            Map c = kVar.c();
            if (c != null) {
                for (String str : c.keySet()) {
                    httpPost.setHeader(str, (String) c.get(str));
                }
            }
            synchronized (j.this) {
                i = j.g + 1;
                j.g = i;
            }
            HttpResponse httpResponse = null;
            try {
                if (kVar.e != null) {
                    StringEntity stringEntity = new StringEntity(kVar.e.toString());
                    stringEntity.setContentType(new BasicHeader("Content-Type", "application/json"));
                    httpPost.setEntity(stringEntity);
                } else {
                    Log.i("HTTP Request Body " + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + kVar.b, "<empty>");
                }
                HttpResponse execute = j.b().execute(httpPost);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode >= 300 || statusCode < 200) {
                    Log.w("Chartboost", "Request failed. Response code: " + statusCode + ", body: " + execute);
                    execute.getEntity().consumeContent();
                    return bVar;
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(execute.getEntity().getContent(), "UTF-8"), 2048);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        kVar.j = new JSONObject(new JSONTokener(sb.toString()));
                        execute.getEntity().consumeContent();
                        return bVar;
                    }
                    sb.append(readLine).append("\n");
                }
            } catch (Exception e) {
                Log.e("Chartboost", "Exception on http request: " + e.getLocalizedMessage());
                if (0 != 0) {
                    try {
                        if (httpResponse.getEntity() != null) {
                            httpResponse.getEntity().consumeContent();
                        }
                    } catch (Exception e2) {
                    }
                }
                return bVar;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(b bVar) {
            j.this.d.remove(bVar.c.intValue());
            if (bVar.a.j == null) {
                j.this.b(bVar.a);
            } else if (j.this.b != null) {
                j.this.b.a(bVar.a.j, bVar.a);
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }
    }

    /* loaded from: classes.dex */
    public class b implements Serializable {
        public k a;
        public JSONObject b;
        public Integer c;

        public b(k kVar, JSONObject jSONObject) {
            this.a = kVar;
            this.b = jSONObject;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(k kVar);

        void a(JSONObject jSONObject, k kVar);
    }

    public j(String str, c cVar, String str2) {
        this.a = str == null ? "https://www.chartboost.com" : str;
        this.b = cVar;
        this.c = str2;
        this.d = new SparseArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Application application, String str) {
        try {
            String str2 = application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName;
            StringBuilder sb = new StringBuilder();
            sb.append(application.getPackageName());
            sb.append("/");
            sb.append(str2);
            sb.append(" (");
            sb.append("Linux; U; Android ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("; ");
            sb.append(Locale.getDefault());
            sb.append("; ");
            sb.append(Build.PRODUCT);
            sb.append(")");
            if (str != null) {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(str);
            }
            return sb.toString();
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpClient b() {
        if (h != null) {
            return h;
        }
        try {
            final Application application = (Application) Chartboost.sharedChartboost().getContext().getApplicationContext();
            h = new DefaultHttpClient() { // from class: com.chartboost.sdk.impl.j.1
                protected SocketFactory a() {
                    try {
                        Class<?> cls = Class.forName("android.net.SSLSessionCache");
                        return (SocketFactory) Class.forName("android.net.SSLCertificateSocketFactory").getMethod("getHttpSocketFactory", Integer.TYPE, cls).invoke(null, Integer.valueOf(Chartboost.sharedChartboost().getTimeout()), cls.getConstructor(Context.class).newInstance(application));
                    } catch (Exception e) {
                        return SSLSocketFactory.getSocketFactory();
                    }
                }

                protected ClientConnectionManager createClientConnectionManager() {
                    SchemeRegistry schemeRegistry = new SchemeRegistry();
                    schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                    schemeRegistry.register(new Scheme("https", a(), 443));
                    HttpParams params = getParams();
                    HttpConnectionParams.setConnectionTimeout(params, Chartboost.sharedChartboost().getTimeout());
                    HttpConnectionParams.setSoTimeout(params, Chartboost.sharedChartboost().getTimeout());
                    HttpProtocolParams.setUserAgent(params, j.b(application, HttpProtocolParams.getUserAgent(params)));
                    return new ThreadSafeClientConnManager(params, schemeRegistry);
                }
            };
            return h;
        } catch (Exception e) {
            try {
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                ClientConnectionManager connectionManager = defaultHttpClient.getConnectionManager();
                HttpParams params = defaultHttpClient.getParams();
                h = new DefaultHttpClient(new ThreadSafeClientConnManager(params, connectionManager.getSchemeRegistry()), params);
                return h;
            } catch (Exception e2) {
                h = new DefaultHttpClient();
                return h;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(k kVar) {
        JSONArray jSONArray;
        if (!kVar.i || this.c == null) {
            if (this.b != null) {
                this.b.a(kVar);
                return;
            }
            return;
        }
        SharedPreferences a2 = com.chartboost.sdk.Libraries.d.a();
        String str = "CBQueuedRequests-" + this.c;
        try {
            JSONObject d = kVar.d();
            if (d != null) {
                String string = a2.getString(str, null);
                if (string != null) {
                    try {
                        jSONArray = new JSONArray(new JSONTokener(string));
                    } catch (Exception e) {
                        Log.w("Chartboost", "Failure reading saved request list");
                        jSONArray = new JSONArray();
                    }
                } else {
                    jSONArray = new JSONArray();
                }
                jSONArray.put(d);
                SharedPreferences.Editor edit = a2.edit();
                edit.putString(str, jSONArray.toString());
                edit.commit();
            }
        } catch (Exception e2) {
            Log.w("Chartboost", "Unable to save failed request");
        }
    }

    public void a() {
        SharedPreferences a2;
        String str;
        String string;
        if (l.a() && (string = (a2 = com.chartboost.sdk.Libraries.d.a()).getString(("CBQueuedRequests-" + this.c), null)) != null) {
            SharedPreferences.Editor edit = a2.edit();
            edit.putString(str, null);
            edit.commit();
            try {
                JSONArray jSONArray = new JSONArray(new JSONTokener(string));
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        k a3 = k.a(jSONArray.getJSONObject(i));
                        if (a3 != null) {
                            a(a3);
                        }
                    } catch (Exception e) {
                        Log.w("Chartboost", "Retrying request failed");
                    }
                }
            } catch (Exception e2) {
                Log.w("Chartboost", "Retrying request list failed");
            }
        }
    }

    public void a(k kVar) {
        if (!l.a()) {
            b(kVar);
            return;
        }
        int i = this.e;
        this.e = i + 1;
        b bVar = new b(kVar, null);
        bVar.c = Integer.valueOf(i);
        this.d.put(i, bVar);
        new a().execute(bVar);
    }
}
