package com.flurry.android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

final class z {
  private bo a;
  
  z(bo parambo) {
    this.a = parambo;
  }
  
  final List a(List paramList) {
    CopyOnWriteArrayList<SdkAdLog> copyOnWriteArrayList = new CopyOnWriteArrayList();
    for (bl bl : paramList) {
      SdkAdLog sdkAdLog = new SdkAdLog();
      sdkAdLog.a(Long.valueOf(bl.c()));
      sdkAdLog.a(bl.b());
      synchronized (new CopyOnWriteArrayList()) {
        for (at at : bl.d()) {
          if (at.b()) {
            SdkAdEvent sdkAdEvent = new SdkAdEvent();
            this();
            sdkAdEvent.a(at.a());
            sdkAdEvent.a(Long.valueOf(at.c()));
            Map map = at.d();
            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            this();
            for (Map.Entry entry : map.entrySet())
              hashMap.put(entry.getKey(), entry.getValue()); 
            sdkAdEvent.a(hashMap);
            null.add(sdkAdEvent);
          } 
        } 
      } 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{com/flurry/android/bl}, name=null} */
      sdkAdLog.a((List)SYNTHETIC_LOCAL_VARIABLE_4);
      copyOnWriteArrayList.add(sdkAdLog);
    } 
    this.a.a(paramList);
    return copyOnWriteArrayList;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */