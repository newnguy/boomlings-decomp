package com.flurry.android;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class aq {
  private int a;
  
  private String b;
  
  private Map c;
  
  private long d;
  
  private boolean e;
  
  private long f;
  
  public aq(int paramInt, String paramString, Map paramMap, long paramLong, boolean paramBoolean) {
    this.a = paramInt;
    this.b = paramString;
    this.c = paramMap;
    this.d = paramLong;
    this.e = paramBoolean;
  }
  
  public final void a(long paramLong) {
    this.f = paramLong - this.d;
    bm.a("FlurryAgent", "Ended event '" + this.b + "' (" + this.d + ") after " + this.f + "ms");
  }
  
  public final boolean a(String paramString) {
    return (this.e && this.f == 0L && this.b.equals(paramString));
  }
  
  public final byte[] a() {
    byte[] arrayOfByte;
    try {
      byte[] arrayOfByte1;
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this();
      DataOutputStream dataOutputStream = new DataOutputStream();
      this(byteArrayOutputStream);
      try {
        dataOutputStream.writeShort(this.a);
        dataOutputStream.writeUTF(this.b);
        if (this.c == null) {
          dataOutputStream.writeShort(0);
        } else {
          dataOutputStream.writeShort(this.c.size());
          Iterator<Map.Entry> iterator = this.c.entrySet().iterator();
          while (true) {
            if (iterator.hasNext()) {
              Map.Entry entry = iterator.next();
              dataOutputStream.writeUTF(ac.a((String)entry.getKey()));
              dataOutputStream.writeUTF(ac.a((String)entry.getValue()));
              continue;
            } 
            dataOutputStream.writeLong(this.d);
            dataOutputStream.writeLong(this.f);
            dataOutputStream.flush();
            return arrayOfByte;
          } 
        } 
        dataOutputStream.writeLong(this.d);
        dataOutputStream.writeLong(this.f);
        dataOutputStream.flush();
        return arrayOfByte;
      } catch (IOException iOException) {
        DataOutputStream dataOutputStream1 = dataOutputStream;
        try {
          arrayOfByte1 = new byte[0];
        } finally {
          Exception exception1;
          Exception exception2 = null;
          arrayOfByte1 = arrayOfByte;
        } 
      } finally {}
      ac.a((Closeable)arrayOfByte1);
      throw byteArrayOutputStream;
    } catch (IOException iOException) {
    
    } finally {
      Closeable closeable = null;
      ac.a(closeable);
    } 
    try {
      byte[] arrayOfByte1 = new byte[0];
    } finally {
      Exception exception1;
      Exception exception2 = null;
      byte[] arrayOfByte1 = arrayOfByte;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */