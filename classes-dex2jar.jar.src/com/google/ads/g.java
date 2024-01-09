package com.google.ads;

public final class g {
  public static Object a(String paramString, Class paramClass) {
    return paramClass.cast(Class.forName(paramString).newInstance());
  }
  
  public static String a(String paramString1, String paramString2, Boolean paramBoolean, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10) {
    paramString2 = paramString1.replaceAll("@gw_adlocid@", paramString2).replaceAll("@gw_qdata@", paramString6).replaceAll("@gw_sdkver@", "afma-sdk-a-v6.2.1").replaceAll("@gw_sessid@", paramString7).replaceAll("@gw_seqnum@", paramString8).replaceAll("@gw_devid@", paramString3);
    paramString1 = paramString2;
    if (paramString5 != null)
      paramString1 = paramString2.replaceAll("@gw_adnetid@", paramString5); 
    paramString2 = paramString1;
    if (paramString4 != null)
      paramString2 = paramString1.replaceAll("@gw_allocid@", paramString4); 
    paramString3 = paramString2;
    if (paramString9 != null)
      paramString3 = paramString2.replaceAll("@gw_adt@", paramString9); 
    paramString1 = paramString3;
    if (paramString10 != null)
      paramString1 = paramString3.replaceAll("@gw_aec@", paramString10); 
    if (paramBoolean != null) {
      if (paramBoolean.booleanValue()) {
        paramString2 = "1";
      } else {
        paramString2 = "0";
      } 
      paramString1 = paramString1.replaceAll("@gw_adnetrefresh@", paramString2);
    } 
    return paramString1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */