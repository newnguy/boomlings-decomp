package com.google.ads.mediation.admob;

import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.MediationServerParameters.Parameter;

public final class AdMobAdapterServerParameters extends MediationServerParameters {
  @Parameter(a = "pubid")
  public String a;
  
  @Parameter(a = "mad_hac", b = false)
  public String b = null;
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\admob\AdMobAdapterServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */