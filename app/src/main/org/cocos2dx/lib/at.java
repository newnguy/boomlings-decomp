package org.cocos2dx.lib;

import android.os.Bundle;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class at implements Runnable {
    final /* synthetic */ as a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public at(as asVar) {
        this.a = asVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Bundle bundle = new Bundle();
        bundle.putString("access_token", this.a.facebook.c());
        try {
            JSONObject c = com.b.a.s.c(this.a.facebook.a("me", bundle));
            this.a.userId_ = c.getString("id");
        } catch (com.b.a.m e) {
            e.printStackTrace();
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
    }
}
