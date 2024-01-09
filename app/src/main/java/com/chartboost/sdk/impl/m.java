package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.chartboost.sdk.Chartboost;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* loaded from: classes.dex */
public class m {
    public a a;

    /* loaded from: classes.dex */
    public interface a {
        void a(String str);
    }

    public m(a aVar) {
        this.a = aVar;
    }

    public void b(String str, Context context) {
        if (this.a != null) {
            this.a.a(str);
        }
        if (context == null) {
            context = Chartboost.sharedChartboost().getContext();
        }
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (Exception e) {
            try {
                if (str.startsWith("market://")) {
                    String str2 = "http://market.android.com/" + str.substring(9);
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    if (!(context instanceof Activity)) {
                        intent2.addFlags(268435456);
                    }
                    intent2.setData(Uri.parse(str2));
                    context.startActivity(intent2);
                }
            } catch (Exception e2) {
            }
        }
    }

    public void a(final String str, final Context context) {
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                return;
            }
            if (scheme.equals("http") || scheme.equals("https")) {
                new AsyncTask() { // from class: com.chartboost.sdk.impl.m.1
                    @Override // android.os.AsyncTask
                    /* renamed from: a */
                    public String doInBackground(Void... voidArr) {
                        HttpURLConnection httpURLConnection;
                        HttpURLConnection httpURLConnection2 = null;
                        try {
                            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                        } catch (Exception e) {
                            httpURLConnection = null;
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            httpURLConnection.setInstanceFollowRedirects(false);
                            String headerField = httpURLConnection.getHeaderField("Location");
                            if (headerField == null) {
                                headerField = str;
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                                return headerField;
                            }
                            return headerField;
                        } catch (Exception e2) {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return null;
                        } catch (Throwable th2) {
                            httpURLConnection2 = httpURLConnection;
                            th = th2;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    }

                    @Override // android.os.AsyncTask
                    /* renamed from: a */
                    public void onPostExecute(String str2) {
                        m.this.b(str2, context);
                    }
                }.execute(new Void[0]);
            } else {
                b(str, context);
            }
        } catch (URISyntaxException e) {
        }
    }
}
