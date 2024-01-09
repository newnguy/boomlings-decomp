package org.cocos2dx.lib;

import android.os.Bundle;
import android.util.Log;
import com.b.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class az extends e {
  final as this$0;
  
  public void onComplete(String paramString, Object paramObject) {
    try {
      Log.d("Facebook", "On Complete Request");
      as.this.requestQueued_ = false;
      paramObject = new JSONObject();
      super(paramString);
      paramString = " ";
      if (!paramObject.isNull("from"))
        paramString = paramObject.getJSONObject("from").getString("name"); 
      if (!paramObject.isNull("data")) {
        paramObject = paramObject.getString("data");
        JSONObject jSONObject = new JSONObject();
        this((String)paramObject);
        JniToCpp.onRefIDRecieved(paramString, jSONObject.getString("refID"));
      } 
      if (as.this.requestID_ != null) {
        paramObject = new Bundle();
        super();
        paramObject.putString("method", "delete");
        a a = as.this.mAsyncRunner;
        paramString = as.this.requestID_;
        ay ay = new ay();
        this();
        a.a(paramString, (Bundle)paramObject, ay);
      } 
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */