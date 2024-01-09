package com.chartboost.sdk.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

class bc {
  private static final ConcurrentMap a = bf.c();
  
  public static List a(Class<?> paramClass) {
    ConcurrentMap<Class<?>, List> concurrentMap = a();
    while (true) {
      List list = (List)concurrentMap.get(paramClass);
      if (list != null)
        return list; 
      concurrentMap.putIfAbsent(paramClass, b(paramClass));
    } 
  }
  
  private static ConcurrentMap a() {
    return a;
  }
  
  private static void a(Class<Object> paramClass, List<Class<Object>> paramList) {
    if (paramClass != null && paramClass != Object.class) {
      Class[] arrayOfClass = paramClass.getInterfaces();
      for (int i = arrayOfClass.length - 1; i >= 0; i--)
        a(arrayOfClass[i], paramList); 
      a(paramClass.getSuperclass(), paramList);
      if (!paramList.contains(paramClass))
        paramList.add(paramClass); 
    } 
  }
  
  private static List b(Class paramClass) {
    ArrayList<Class<Object>> arrayList = new ArrayList();
    arrayList.add(Object.class);
    a(paramClass, arrayList);
    Collections.reverse(arrayList);
    return Collections.unmodifiableList(new ArrayList(arrayList));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */