package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public final class CBImpressionActivity extends Activity {
  public static final String PARAM_FULLSCREEN = "paramFullscreen";
  
  protected Chartboost a;
  
  public void onBackPressed() {
    if (!this.a.a())
      super.onBackPressed(); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getIntent().getBooleanExtra("paramFullscreen", false))
      getWindow().addFlags(1024); 
    getWindow().setWindowAnimations(0);
    setContentView((View)new RelativeLayout((Context)this));
    this.a = Chartboost.sharedChartboost();
    this.a.a(this);
  }
  
  protected void onDestroy() {
    super.onDestroy();
  }
  
  protected void onStart() {
    super.onStart();
    this.a.a(this);
  }
  
  protected void onStop() {
    super.onStop();
    this.a.b(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\CBImpressionActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */