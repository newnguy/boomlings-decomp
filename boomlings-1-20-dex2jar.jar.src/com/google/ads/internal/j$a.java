package com.google.ads.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GestureStore;
import android.gesture.Prediction;
import com.google.ads.util.b;
import java.util.ArrayList;

public class j$a implements GestureOverlayView.OnGesturePerformedListener {
  private final GestureStore a;
  
  private Activity b;
  
  private d c;
  
  public j$a(Activity paramActivity, d paramd, GestureStore paramGestureStore) {
    this.b = paramActivity;
    this.c = paramd;
    this.a = paramGestureStore;
  }
  
  public void onGesturePerformed(GestureOverlayView paramGestureOverlayView, Gesture paramGesture) {
    ArrayList arrayList = this.a.recognize(paramGesture);
    for (Prediction prediction : arrayList)
      b.a("Gesture: '" + prediction.name + "' = " + prediction.score); 
    if (arrayList.size() == 0) {
      b.a("Gesture: No remotely reasonable predictions");
      return;
    } 
    if (((Prediction)arrayList.get(0)).score > 2.0D && "debug".equals(((Prediction)arrayList.get(0)).name) && this.c != null) {
      String str;
      if (this.c.c() == null) {
        str = "[No diagnostics available]";
      } else {
        str = this.c.c();
      } 
      (new AlertDialog.Builder((Context)this.b)).setMessage(str).setTitle("Ad Information").setPositiveButton("Share", new x(this, str)).setNegativeButton("Close", new w(this)).create().show();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\j$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */