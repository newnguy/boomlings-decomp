package org.cocos2dx.lib;

import android.os.Bundle;
import android.util.Log;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class az extends e {
    final /* synthetic */ as this$0;

    public az(as asVar) {
        this.this$0 = asVar;
    }

    @Override // com.b.a.d
    public void onComplete(String str, Object obj) {
        try {
            Log.d("Facebook", "On Complete Request");
            this.this$0.requestQueued_ = false;
            JSONObject jSONObject = new JSONObject(str);
            String str2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            if (!jSONObject.isNull("from")) {
                str2 = jSONObject.getJSONObject("from").getString("name");
            }
            if (!jSONObject.isNull("data")) {
                JniToCpp.onRefIDRecieved(str2, new JSONObject(jSONObject.getString("data")).getString("refID"));
            }
            if (this.this$0.requestID_ != null) {
                Bundle bundle = new Bundle();
                bundle.putString("method", "delete");
                this.this$0.mAsyncRunner.a(this.this$0.requestID_, bundle, new ay(this.this$0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
