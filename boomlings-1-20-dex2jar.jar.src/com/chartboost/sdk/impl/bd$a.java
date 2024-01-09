package com.chartboost.sdk.impl;

final class bd$a implements bg {
  final bd a;
  
  private bd$a(bd parambd) {}
  
  public Object a(Class paramClass) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic a : (Ljava/lang/Class;)Ljava/util/List;
    //   4: invokeinterface iterator : ()Ljava/util/Iterator;
    //   9: astore_2
    //   10: aload_2
    //   11: invokeinterface hasNext : ()Z
    //   16: ifeq -> 49
    //   19: aload_2
    //   20: invokeinterface next : ()Ljava/lang/Object;
    //   25: checkcast java/lang/Class
    //   28: astore_1
    //   29: aload_0
    //   30: getfield a : Lcom/chartboost/sdk/impl/bd;
    //   33: invokestatic a : (Lcom/chartboost/sdk/impl/bd;)Ljava/util/Map;
    //   36: aload_1
    //   37: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   42: astore_1
    //   43: aload_1
    //   44: ifnull -> 10
    //   47: aload_1
    //   48: areturn
    //   49: aconst_null
    //   50: astore_1
    //   51: goto -> 47
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\bd$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */