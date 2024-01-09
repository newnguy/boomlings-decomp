package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.MediationServerParameters.Parameter;

public class CustomEventServerParameters extends MediationServerParameters {
  @Parameter(a = "label", b = true)
  public String a;
  
  @Parameter(a = "class_name", b = true)
  public String b;
  
  @Parameter(a = "parameter", b = false)
  public String c = null;
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\customevent\CustomEventServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */