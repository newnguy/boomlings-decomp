package com.chartboost.sdk.impl;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.List;

class q$1 extends DataSetObserver {
  final q a;
  
  q$1(q paramq) {}
  
  public void onChanged() {
    int i = q.a(this.a).getChildCount();
    for (byte b = 0;; b++) {
      if (b >= i) {
        q.c(this.a).clear();
        q.a(this.a).removeAllViews();
        i = q.e(this.a).getCount();
        for (b = 0;; b++) {
          LinearLayout.LayoutParams layoutParams;
          if (b >= i) {
            q.a(this.a).requestLayout();
            return;
          } 
          int j = q.e(this.a).getItemViewType(b);
          List<View> list2 = q.b(this.a).get(j);
          List<View> list3 = q.d(this.a).get(j);
          q.c(this.a).add(Integer.valueOf(j));
          View view1 = null;
          if (!list3.isEmpty()) {
            view1 = list3.get(0);
            list3.remove(0);
          } 
          View view2 = q.e(this.a).getView(b, view1, (ViewGroup)q.a(this.a));
          list2.add(view2);
          if (q.f(this.a) == 0) {
            layoutParams = new LinearLayout.LayoutParams(-2, -1);
          } else {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
          } 
          if (b < i - 1)
            layoutParams.setMargins(0, 0, 0, 1); 
          q.a(this.a).addView(view2, (ViewGroup.LayoutParams)layoutParams);
        } 
        break;
      } 
      List list = q.b(this.a).get(((Integer)q.c(this.a).get(b)).intValue());
      List<View> list1 = q.d(this.a).get(((Integer)q.c(this.a).get(b)).intValue());
      View view = q.a(this.a).getChildAt(b);
      list.remove(view);
      list1.add(view);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\q$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */