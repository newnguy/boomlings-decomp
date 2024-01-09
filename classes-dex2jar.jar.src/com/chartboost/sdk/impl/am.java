package com.chartboost.sdk.impl;

import java.util.LinkedHashMap;
import java.util.Map;

public class am extends LinkedHashMap implements aj {
  public am() {}
  
  public am(String paramString, Object paramObject) {
    a(paramString, paramObject);
  }
  
  public Object a(String paramString) {
    return get(paramString);
  }
  
  public Object a(String paramString, Object paramObject) {
    return super.put(paramString, paramObject);
  }
  
  public boolean b(String paramString) {
    return containsKey(paramString);
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof com/chartboost/sdk/impl/aj
    //   4: ifne -> 11
    //   7: iconst_0
    //   8: istore_2
    //   9: iload_2
    //   10: ireturn
    //   11: aload_1
    //   12: checkcast com/chartboost/sdk/impl/aj
    //   15: astore_1
    //   16: aload_0
    //   17: invokevirtual keySet : ()Ljava/util/Set;
    //   20: aload_1
    //   21: invokeinterface keySet : ()Ljava/util/Set;
    //   26: invokevirtual equals : (Ljava/lang/Object;)Z
    //   29: ifne -> 37
    //   32: iconst_0
    //   33: istore_2
    //   34: goto -> 9
    //   37: aload_0
    //   38: invokevirtual keySet : ()Ljava/util/Set;
    //   41: invokeinterface iterator : ()Ljava/util/Iterator;
    //   46: astore_3
    //   47: aload_3
    //   48: invokeinterface hasNext : ()Z
    //   53: ifeq -> 235
    //   56: aload_3
    //   57: invokeinterface next : ()Ljava/lang/Object;
    //   62: checkcast java/lang/String
    //   65: astore #4
    //   67: aload_0
    //   68: aload #4
    //   70: invokevirtual a : (Ljava/lang/String;)Ljava/lang/Object;
    //   73: astore #5
    //   75: aload_1
    //   76: aload #4
    //   78: invokeinterface a : (Ljava/lang/String;)Ljava/lang/Object;
    //   83: astore #4
    //   85: aload #5
    //   87: ifnonnull -> 100
    //   90: aload #4
    //   92: ifnull -> 100
    //   95: iconst_0
    //   96: istore_2
    //   97: goto -> 9
    //   100: aload #4
    //   102: ifnonnull -> 115
    //   105: aload #5
    //   107: ifnull -> 47
    //   110: iconst_0
    //   111: istore_2
    //   112: goto -> 9
    //   115: aload #5
    //   117: instanceof java/lang/Number
    //   120: ifeq -> 156
    //   123: aload #4
    //   125: instanceof java/lang/Number
    //   128: ifeq -> 156
    //   131: aload #5
    //   133: checkcast java/lang/Number
    //   136: invokevirtual doubleValue : ()D
    //   139: aload #4
    //   141: checkcast java/lang/Number
    //   144: invokevirtual doubleValue : ()D
    //   147: dcmpl
    //   148: ifeq -> 47
    //   151: iconst_0
    //   152: istore_2
    //   153: goto -> 9
    //   156: aload #5
    //   158: instanceof java/util/regex/Pattern
    //   161: ifeq -> 220
    //   164: aload #4
    //   166: instanceof java/util/regex/Pattern
    //   169: ifeq -> 220
    //   172: aload #5
    //   174: checkcast java/util/regex/Pattern
    //   177: astore #5
    //   179: aload #4
    //   181: checkcast java/util/regex/Pattern
    //   184: astore #4
    //   186: aload #5
    //   188: invokevirtual pattern : ()Ljava/lang/String;
    //   191: aload #4
    //   193: invokevirtual pattern : ()Ljava/lang/String;
    //   196: invokevirtual equals : (Ljava/lang/Object;)Z
    //   199: ifeq -> 215
    //   202: aload #5
    //   204: invokevirtual flags : ()I
    //   207: aload #4
    //   209: invokevirtual flags : ()I
    //   212: if_icmpeq -> 47
    //   215: iconst_0
    //   216: istore_2
    //   217: goto -> 9
    //   220: aload #5
    //   222: aload #4
    //   224: invokevirtual equals : (Ljava/lang/Object;)Z
    //   227: ifne -> 47
    //   230: iconst_0
    //   231: istore_2
    //   232: goto -> 9
    //   235: iconst_1
    //   236: istore_2
    //   237: goto -> 9
  }
  
  public void putAll(Map paramMap) {
    for (Map.Entry entry : paramMap.entrySet())
      a(entry.getKey().toString(), entry.getValue()); 
  }
  
  public String toString() {
    return ac.a(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */