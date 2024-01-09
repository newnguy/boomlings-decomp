package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import org.json.JSONObject;

public class h$a$a extends ArrayAdapter {
  Context a;
  
  final h$a b;
  
  public h$a$a(h$a paramh$a, Context paramContext) {
    super(paramContext, 0, h.a(h$a.b(paramh$a)));
    this.a = paramContext;
  }
  
  public JSONObject a(int paramInt) {
    return h.a(h$a.b(this.b)).get(paramInt);
  }
  
  public int getCount() {
    return h.a(h$a.b(this.b)).size();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    h$b h$b;
    t t;
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    int i = paramInt;
    if (difference.isReverse())
      i = getCount() - 1 - paramInt; 
    JSONObject jSONObject = a(i);
    String str = jSONObject.optString("type", "");
    paramViewGroup = null;
    if (paramView == null) {
      if (str.equals("featured")) {
        d d = new d(this.a);
      } else if (str.equals("regular")) {
        e e = new e(this.a);
      } else {
        ViewGroup viewGroup = paramViewGroup;
        if (str.equals("webview"))
          h$b = new i(this.a); 
      } 
      t = new t(this.a, (View)h$b);
    } else {
      t = (t)h$b;
      h$b = (h$b)t.a();
    } 
    h$b.a(jSONObject, i);
    c c = (c)h$b;
    if (difference.isOdd()) {
      t.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(h$b.a(), -1));
    } else {
      t.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, h$b.a()));
    } 
    h$a$a$1 h$a$a$1 = new h$a$a$1(this, jSONObject);
    c.a = h$a$a$1;
    c.setOnClickListener(h$a$a$1);
    if (h$b instanceof e)
      ((e)h$b).b.setOnClickListener(h$a$a$1); 
    return (View)t;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\h$a$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */