package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class at {
  private static List a = Arrays.asList(new String[] { 
        "requested", "filled", "unfilled", "rendered", "clicked", "videoStart", "videoCompleted", "videoProgressed", "sentToUrl", "adClosed", 
        "adWillClose", "renderFailed", "requestAdComponents" });
  
  private final String b;
  
  private final boolean c;
  
  private final long d;
  
  private final Map e;
  
  at(DataInput paramDataInput) {
    this.b = paramDataInput.readUTF();
    this.c = paramDataInput.readBoolean();
    this.d = paramDataInput.readLong();
    this.e = new HashMap<Object, Object>();
    short s1 = paramDataInput.readShort();
    for (short s = 0; s < s1; s = (short)(s + 1))
      this.e.put(paramDataInput.readUTF(), paramDataInput.readUTF()); 
  }
  
  at(String paramString, boolean paramBoolean, long paramLong, Map paramMap) {
    if (!a.contains(paramString))
      bm.a("FlurryAgent", "AdEvent initialized with unrecognized type: " + paramString); 
    this.b = paramString;
    this.c = paramBoolean;
    this.d = paramLong;
    if (paramMap == null) {
      this.e = new HashMap<Object, Object>();
      return;
    } 
    this.e = paramMap;
  }
  
  final String a() {
    return this.b;
  }
  
  final void a(DataOutput paramDataOutput) {
    paramDataOutput.writeUTF(this.b);
    paramDataOutput.writeBoolean(this.c);
    paramDataOutput.writeLong(this.d);
    paramDataOutput.writeShort(this.e.size());
    for (Map.Entry entry : this.e.entrySet()) {
      paramDataOutput.writeUTF((String)entry.getKey());
      paramDataOutput.writeUTF((String)entry.getValue());
    } 
  }
  
  final boolean b() {
    return this.c;
  }
  
  final long c() {
    return this.d;
  }
  
  final Map d() {
    return this.e;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */