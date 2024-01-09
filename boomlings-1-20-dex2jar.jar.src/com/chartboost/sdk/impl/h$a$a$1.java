package com.chartboost.sdk.impl;

import android.view.View;
import org.json.JSONObject;

class h$a$a$1 implements View.OnClickListener {
  final h$a$a a;
  
  private final JSONObject b;
  
  h$a$a$1(h$a$a paramh$a$a, JSONObject paramJSONObject) {}
  
  public void onClick(View paramView) {
    // Byte code:
    //   0: aload_0
    //   1: getfield b : Lorg/json/JSONObject;
    //   4: ldc 'deep-link'
    //   6: invokevirtual optString : (Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_2
    //   11: ifnull -> 25
    //   14: aload_2
    //   15: astore_1
    //   16: aload_2
    //   17: ldc ''
    //   19: invokevirtual equals : (Ljava/lang/Object;)Z
    //   22: ifeq -> 35
    //   25: aload_0
    //   26: getfield b : Lorg/json/JSONObject;
    //   29: ldc 'link'
    //   31: invokevirtual optString : (Ljava/lang/String;)Ljava/lang/String;
    //   34: astore_1
    //   35: aload_0
    //   36: getfield a : Lcom/chartboost/sdk/impl/h$a$a;
    //   39: invokestatic a : (Lcom/chartboost/sdk/impl/h$a$a;)Lcom/chartboost/sdk/impl/h$a;
    //   42: invokestatic b : (Lcom/chartboost/sdk/impl/h$a;)Lcom/chartboost/sdk/impl/h;
    //   45: getfield b : Lcom/chartboost/sdk/b$c;
    //   48: ifnull -> 74
    //   51: aload_0
    //   52: getfield a : Lcom/chartboost/sdk/impl/h$a$a;
    //   55: invokestatic a : (Lcom/chartboost/sdk/impl/h$a$a;)Lcom/chartboost/sdk/impl/h$a;
    //   58: invokestatic b : (Lcom/chartboost/sdk/impl/h$a;)Lcom/chartboost/sdk/impl/h;
    //   61: getfield b : Lcom/chartboost/sdk/b$c;
    //   64: aload_1
    //   65: aload_0
    //   66: getfield b : Lorg/json/JSONObject;
    //   69: invokeinterface a : (Ljava/lang/String;Lorg/json/JSONObject;)V
    //   74: return
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\h$a$a$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */