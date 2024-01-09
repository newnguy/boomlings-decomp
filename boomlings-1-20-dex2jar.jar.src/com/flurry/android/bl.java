package com.flurry.android;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class bl {
  private static int a = 1;
  
  private final int b;
  
  private final long c;
  
  private final String d;
  
  private List e;
  
  bl(long paramLong, String paramString) {
    int i = a;
    a = i + 1;
    this.b = i;
    this.c = paramLong;
    this.d = paramString;
    this.e = new ArrayList();
  }
  
  bl(DataInput paramDataInput) {
    this.b = paramDataInput.readInt();
    this.c = paramDataInput.readLong();
    this.d = paramDataInput.readUTF();
    this.e = new ArrayList();
    short s1 = paramDataInput.readShort();
    for (short s = 0; s < s1; s = (short)(s + 1))
      this.e.add(new at(paramDataInput)); 
  }
  
  final int a() {
    return this.b;
  }
  
  final void a(at paramat) {
    this.e.add(paramat);
  }
  
  final void a(DataOutput paramDataOutput) {
    paramDataOutput.writeInt(this.b);
    paramDataOutput.writeLong(this.c);
    paramDataOutput.writeUTF(this.d);
    paramDataOutput.writeShort(this.e.size());
    Iterator<at> iterator = this.e.iterator();
    while (iterator.hasNext())
      ((at)iterator.next()).a(paramDataOutput); 
  }
  
  final String b() {
    return this.d;
  }
  
  final long c() {
    return this.c;
  }
  
  final List d() {
    return this.e;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */