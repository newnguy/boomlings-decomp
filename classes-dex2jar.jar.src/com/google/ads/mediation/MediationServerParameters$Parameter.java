package com.google.ads.mediation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MediationServerParameters$Parameter {
  String a();
  
  boolean b() default true;
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\MediationServerParameters$Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */