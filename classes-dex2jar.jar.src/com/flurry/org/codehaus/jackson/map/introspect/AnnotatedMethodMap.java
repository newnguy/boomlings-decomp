package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

public final class AnnotatedMethodMap implements Iterable {
  protected LinkedHashMap _methods;
  
  public void add(AnnotatedMethod paramAnnotatedMethod) {
    if (this._methods == null)
      this._methods = new LinkedHashMap<Object, Object>(); 
    this._methods.put(new MemberKey(paramAnnotatedMethod.getAnnotated()), paramAnnotatedMethod);
  }
  
  public AnnotatedMethod find(String paramString, Class[] paramArrayOfClass) {
    return (this._methods == null) ? null : (AnnotatedMethod)this._methods.get(new MemberKey(paramString, paramArrayOfClass));
  }
  
  public AnnotatedMethod find(Method paramMethod) {
    return (this._methods == null) ? null : (AnnotatedMethod)this._methods.get(new MemberKey(paramMethod));
  }
  
  public boolean isEmpty() {
    return (this._methods == null || this._methods.size() == 0);
  }
  
  public Iterator iterator() {
    return (this._methods != null) ? this._methods.values().iterator() : Collections.emptyList().iterator();
  }
  
  public AnnotatedMethod remove(AnnotatedMethod paramAnnotatedMethod) {
    return remove(paramAnnotatedMethod.getAnnotated());
  }
  
  public AnnotatedMethod remove(Method paramMethod) {
    return (this._methods != null) ? (AnnotatedMethod)this._methods.remove(new MemberKey(paramMethod)) : null;
  }
  
  public int size() {
    return (this._methods == null) ? 0 : this._methods.size();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedMethodMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */