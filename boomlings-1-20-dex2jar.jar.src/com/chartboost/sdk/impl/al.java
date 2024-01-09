package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class al implements ai {
  protected ap a;
  
  private void a(String paramString, int paramInt, byte[] paramArrayOfbyte) {
    a((byte)5, paramString);
    int j = paramArrayOfbyte.length;
    int i = j;
    if (paramInt == 2)
      i = j + 4; 
    this.a.c(i);
    this.a.write(paramInt);
    if (paramInt == 2)
      this.a.c(i - 4); 
    paramInt = this.a.a();
    this.a.write(paramArrayOfbyte);
    ae.a(this.a.a() - paramInt, paramArrayOfbyte.length);
  }
  
  private void a(String paramString, Iterable paramIterable) {
    a((byte)4, paramString);
    int i = this.a.a();
    this.a.c(0);
    Iterator iterator = paramIterable.iterator();
    for (byte b = 0; iterator.hasNext(); b++)
      b(String.valueOf(b), iterator.next()); 
    this.a.write(0);
    this.a.a(i, this.a.a() - i);
  }
  
  private void a(String paramString1, String paramString2, byte paramByte) {
    a(paramByte, paramString1);
    b(paramString2);
  }
  
  private void a(String paramString, Map paramMap) {
    a((byte)3, paramString);
    int i = this.a.a();
    this.a.c(0);
    for (Map.Entry entry : paramMap.entrySet())
      b(entry.getKey().toString(), entry.getValue()); 
    this.a.write(0);
    this.a.a(i, this.a.a() - i);
  }
  
  private void a(String paramString, Pattern paramPattern) {
    a((byte)11, paramString);
    c(paramPattern.pattern());
    c(ag.a(paramPattern.flags()));
  }
  
  private void c(String paramString, Object paramObject) {
    a((byte)4, paramString);
    int j = this.a.a();
    this.a.c(0);
    int i = Array.getLength(paramObject);
    for (byte b = 0; b < i; b++)
      b(String.valueOf(b), Array.get(paramObject, b)); 
    this.a.write(0);
    this.a.a(j, this.a.a() - j);
  }
  
  private void d(String paramString) {
    a((byte)-1, paramString);
  }
  
  private void e(String paramString) {
    a(127, paramString);
  }
  
  public void a() {
    this.a = null;
  }
  
  protected void a(byte paramByte, String paramString) {
    this.a.write(paramByte);
    c(paramString);
  }
  
  public void a(ap paramap) {
    if (this.a != null)
      throw new IllegalStateException("in the middle of something"); 
    this.a = paramap;
  }
  
  protected void a(String paramString) {
    a((byte)10, paramString);
  }
  
  protected void a(String paramString, as paramas) {
    a((byte)17, paramString);
    this.a.c(paramas.b());
    this.a.c(paramas.a());
  }
  
  protected void a(String paramString, at paramat) {
    a(paramString, paramat.a(), paramat.b());
  }
  
  protected void a(String paramString, au paramau) {
    a((byte)13, paramString);
    this.a.a();
    b(paramau.a());
  }
  
  protected void a(String paramString, av paramav) {
    a((byte)15, paramString);
    int i = this.a.a();
    this.a.c(0);
    b(paramav.a());
    b(paramav.b());
    this.a.a(i, this.a.a() - i);
  }
  
  protected void a(String paramString, ay paramay) {
    a((byte)7, paramString);
    this.a.d(paramay.c());
    this.a.d(paramay.d());
    this.a.d(paramay.e());
  }
  
  protected void a(String paramString, az paramaz) {
    a(paramString, paramaz.a(), (byte)14);
  }
  
  protected void a(String paramString, Boolean paramBoolean) {
    boolean bool;
    a((byte)8, paramString);
    ap ap1 = this.a;
    if (paramBoolean.booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    ap1.write(bool);
  }
  
  protected void a(String paramString, Number paramNumber) {
    if (paramNumber instanceof Integer || paramNumber instanceof Short || paramNumber instanceof Byte || paramNumber instanceof java.util.concurrent.atomic.AtomicInteger) {
      a((byte)16, paramString);
      this.a.c(paramNumber.intValue());
      return;
    } 
    if (paramNumber instanceof Long || paramNumber instanceof java.util.concurrent.atomic.AtomicLong) {
      a((byte)18, paramString);
      this.a.a(paramNumber.longValue());
      return;
    } 
    if (paramNumber instanceof Float || paramNumber instanceof Double) {
      a((byte)1, paramString);
      this.a.a(paramNumber.doubleValue());
      return;
    } 
    throw new IllegalArgumentException("can't serialize " + paramNumber.getClass());
  }
  
  protected void a(String paramString1, String paramString2) {
    a(paramString1, paramString2, (byte)2);
  }
  
  protected void a(String paramString, Date paramDate) {
    a((byte)9, paramString);
    this.a.a(paramDate.getTime());
  }
  
  protected void a(String paramString, UUID paramUUID) {
    a((byte)5, paramString);
    this.a.c(16);
    this.a.write(3);
    this.a.a(paramUUID.getMostSignificantBits());
    this.a.a(paramUUID.getLeastSignificantBits());
  }
  
  protected void a(String paramString, byte[] paramArrayOfbyte) {
    a(paramString, 0, paramArrayOfbyte);
  }
  
  protected boolean a(String paramString, aj paramaj) {
    return false;
  }
  
  protected boolean a(String paramString, Object paramObject) {
    return false;
  }
  
  public byte[] a(aj paramaj) {
    ao ao = new ao();
    a(ao);
    b(paramaj);
    a();
    return ao.c();
  }
  
  public int b(aj paramaj) {
    return b((String)null, paramaj);
  }
  
  protected int b(String paramString, aj paramaj) {
    List list;
    byte b;
    if (paramaj == null)
      throw new NullPointerException("can't save a null object"); 
    int j = this.a.a();
    if (paramaj instanceof List) {
      b = 4;
    } else {
      b = 3;
    } 
    if (a(paramString, paramaj))
      return this.a.a() - j; 
    if (paramString != null)
      a(b, paramString); 
    int i = this.a.a();
    this.a.c(0);
    String str = null;
    if (b == 3 && paramString == null) {
      null = 1;
    } else {
      null = 0;
    } 
    paramString = str;
    if (b == 3) {
      if (null && paramaj.b("_id"))
        b("_id", paramaj.a("_id")); 
      Object object = paramaj.a("_transientFields");
      paramString = str;
      if (object instanceof List)
        list = (List)object; 
    } 
    if (paramaj instanceof Map) {
      for (Map.Entry entry : ((Map)paramaj).entrySet()) {
        if ((!null || !((String)entry.getKey()).equals("_id")) && (list == null || !list.contains(entry.getKey())))
          b((String)entry.getKey(), entry.getValue()); 
      } 
    } else {
      for (String str1 : entry.keySet()) {
        if ((!null || !str1.equals("_id")) && (list == null || !list.contains(str1)))
          b(str1, entry.a(str1)); 
      } 
    } 
    this.a.write(0);
    this.a.a(i, this.a.a() - i);
    return this.a.a() - j;
  }
  
  protected void b(String paramString) {
    int j = this.a.a();
    this.a.c(0);
    int i = c(paramString);
    this.a.a(j, i);
  }
  
  protected void b(String paramString, Object paramObject) {
    if (!paramString.equals("_transientFields")) {
      if (paramString.equals("$where") && paramObject instanceof String) {
        a((byte)13, paramString);
        b(paramObject.toString());
        return;
      } 
      paramObject = ag.a(paramObject);
      if (paramObject == null) {
        a(paramString);
        return;
      } 
      if (paramObject instanceof Date) {
        a(paramString, (Date)paramObject);
        return;
      } 
      if (paramObject instanceof Number) {
        a(paramString, (Number)paramObject);
        return;
      } 
      if (paramObject instanceof Character) {
        a(paramString, paramObject.toString());
        return;
      } 
      if (paramObject instanceof String) {
        a(paramString, paramObject.toString());
        return;
      } 
      if (paramObject instanceof ay) {
        a(paramString, (ay)paramObject);
        return;
      } 
      if (paramObject instanceof aj) {
        b(paramString, (aj)paramObject);
        return;
      } 
      if (paramObject instanceof Boolean) {
        a(paramString, (Boolean)paramObject);
        return;
      } 
      if (paramObject instanceof Pattern) {
        a(paramString, (Pattern)paramObject);
        return;
      } 
      if (paramObject instanceof Map) {
        a(paramString, (Map)paramObject);
        return;
      } 
      if (paramObject instanceof Iterable) {
        a(paramString, (Iterable)paramObject);
        return;
      } 
      if (paramObject instanceof byte[]) {
        a(paramString, (byte[])paramObject);
        return;
      } 
      if (paramObject instanceof at) {
        a(paramString, (at)paramObject);
        return;
      } 
      if (paramObject instanceof UUID) {
        a(paramString, (UUID)paramObject);
        return;
      } 
      if (paramObject.getClass().isArray()) {
        c(paramString, paramObject);
        return;
      } 
      if (paramObject instanceof az) {
        a(paramString, (az)paramObject);
        return;
      } 
      if (paramObject instanceof as) {
        a(paramString, (as)paramObject);
        return;
      } 
      if (paramObject instanceof av) {
        a(paramString, (av)paramObject);
        return;
      } 
      if (paramObject instanceof au) {
        a(paramString, (au)paramObject);
        return;
      } 
      if (paramObject instanceof z) {
        am am = new am();
        am.a("$ref", ((z)paramObject).b());
        am.a("$id", ((z)paramObject).a());
        b(paramString, am);
        return;
      } 
      if (paramObject instanceof ax) {
        d(paramString);
        return;
      } 
      if (paramObject instanceof aw) {
        e(paramString);
        return;
      } 
      if (!a(paramString, paramObject))
        throw new IllegalArgumentException("can't serialize " + paramObject.getClass()); 
    } 
  }
  
  protected int c(String paramString) {
    int j = paramString.length();
    int i = 0;
    byte b = 0;
    while (i < j) {
      int k = Character.codePointAt(paramString, i);
      if (k < 128) {
        this.a.write((byte)k);
        b++;
      } else if (k < 2048) {
        this.a.write((byte)((k >> 6) + 192));
        this.a.write((byte)((k & 0x3F) + 128));
        b += 2;
      } else if (k < 65536) {
        this.a.write((byte)((k >> 12) + 224));
        this.a.write((byte)((k >> 6 & 0x3F) + 128));
        this.a.write((byte)((k & 0x3F) + 128));
        b += 3;
      } else {
        this.a.write((byte)((k >> 18) + 240));
        this.a.write((byte)((k >> 12 & 0x3F) + 128));
        this.a.write((byte)((k >> 6 & 0x3F) + 128));
        this.a.write((byte)((k & 0x3F) + 128));
        b += 4;
      } 
      i += Character.charCount(k);
    } 
    this.a.write(0);
    return b + 1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */