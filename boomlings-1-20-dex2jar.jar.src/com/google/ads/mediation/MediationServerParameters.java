package com.google.ads.mediation;

import com.google.ads.util.b;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class MediationServerParameters {
  protected void a() {}
  
  public void a(Map paramMap) {
    String str;
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (Field field : getClass().getFields()) {
      MediationServerParameters$Parameter mediationServerParameters$Parameter = field.<MediationServerParameters$Parameter>getAnnotation(MediationServerParameters$Parameter.class);
      if (mediationServerParameters$Parameter != null)
        hashMap.put(mediationServerParameters$Parameter.a(), field); 
    } 
    if (hashMap.isEmpty())
      b.e("No server options fields detected.  To suppress this message either add a field with the @Parameter annotation, or override the load() method"); 
    for (Map.Entry entry : paramMap.entrySet()) {
      Field field = (Field)hashMap.remove(entry.getKey());
      if (field != null) {
        try {
          field.set(this, entry.getValue());
        } catch (IllegalAccessException illegalAccessException) {
          b.b("Server Option '" + (String)entry.getKey() + "' could not be set: Illegal Access");
        } catch (IllegalArgumentException illegalArgumentException) {
          b.b("Server Option '" + (String)entry.getKey() + "' could not be set: Bad Type");
        } 
        continue;
      } 
      b.e("Unexpected Server Option: " + (String)entry.getKey() + " = '" + (String)entry.getValue() + "'");
    } 
    paramMap = null;
    for (Field field : hashMap.values()) {
      if (((MediationServerParameters$Parameter)field.<MediationServerParameters$Parameter>getAnnotation(MediationServerParameters$Parameter.class)).b()) {
        b.b("Required Server Option missing: " + ((MediationServerParameters$Parameter)field.<MediationServerParameters$Parameter>getAnnotation(MediationServerParameters$Parameter.class)).a());
        StringBuilder stringBuilder = new StringBuilder();
        if (paramMap == null) {
          str = "";
        } else {
          str = str + ", ";
        } 
        str = stringBuilder.append(str).append(((MediationServerParameters$Parameter)field.<MediationServerParameters$Parameter>getAnnotation(MediationServerParameters$Parameter.class)).a()).toString();
      } 
    } 
    if (str != null)
      throw new MediationServerParameters$MappingException("Required Server Option(s) missing: " + str); 
    a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\MediationServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */