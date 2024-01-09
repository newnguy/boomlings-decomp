package org.cocos2dx.lib;

import android.os.Bundle;
import android.widget.Toast;
import com.b.a.m;

public class ba extends d {
  final as this$0;
  
  public void onCancel() {
    Toast.makeText(as.this.cAct_.getApplicationContext(), "Update status cancelled", 0).show();
  }
  
  public void onComplete(Bundle paramBundle) {
    if (paramBundle.getString("post_id") != null) {
      Toast.makeText(as.this.cAct_.getApplicationContext(), "Update Status executed", 0).show();
      return;
    } 
    Toast.makeText(as.this.cAct_.getApplicationContext(), "No wall post made", 0).show();
  }
  
  public void onFacebookError(m paramm) {
    Toast.makeText(as.this.cAct_.getApplicationContext(), "Facebook Error: " + paramm.getMessage(), 0).show();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */