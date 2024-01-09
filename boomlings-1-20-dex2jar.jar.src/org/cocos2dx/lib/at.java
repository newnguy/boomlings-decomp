package org.cocos2dx.lib;

import android.os.Bundle;
import com.b.a.m;
import com.b.a.s;
import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;

class at implements Runnable {
  final as a;
  
  at(as paramas) {}
  
  public void run() {
    Bundle bundle = new Bundle();
    bundle.putString("access_token", this.a.facebook.c());
    try {
      JSONObject jSONObject = s.c(this.a.facebook.a("me", bundle));
      as.access$0(this.a, jSONObject.getString("id"));
    } catch (m m) {
      m.printStackTrace();
    } catch (MalformedURLException malformedURLException) {
      malformedURLException.printStackTrace();
    } catch (JSONException jSONException) {
      jSONException.printStackTrace();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */