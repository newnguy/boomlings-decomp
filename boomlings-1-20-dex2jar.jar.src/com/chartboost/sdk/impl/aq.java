package com.chartboost.sdk.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class aq extends ap {
  private static bh g = new aq$1(640);
  
  final byte[] a = new byte[16384];
  
  final char[] b = new char[16384];
  
  final List c = new ArrayList();
  
  final ar d = new ar();
  
  private final aq$a e = new aq$a();
  
  private final aq$a f = new aq$a();
  
  public aq() {
    d();
  }
  
  public int a() {
    return this.e.b();
  }
  
  public int a(OutputStream paramOutputStream) {
    if (paramOutputStream == null)
      throw new NullPointerException("out is null"); 
    byte b = -1;
    int i = 0;
    while (b < this.c.size()) {
      byte[] arrayOfByte = b(b);
      int j = this.f.c(b);
      paramOutputStream.write(arrayOfByte, 0, j);
      i += j;
      b++;
    } 
    return i;
  }
  
  public void a(int paramInt) {
    this.e.a(paramInt);
  }
  
  public int b() {
    return this.f.b();
  }
  
  byte[] b(int paramInt) {
    return (paramInt < 0) ? this.a : this.c.get(paramInt);
  }
  
  public void d() {
    this.e.a();
    this.f.a();
    for (byte b = 0; b < this.c.size(); b++)
      g.b(this.c.get(b)); 
    this.c.clear();
  }
  
  void e() {
    if (this.e.b() < this.f.b()) {
      if (this.e.b == 16384)
        this.e.d(); 
      return;
    } 
    this.f.a(this.e);
    if (this.f.b >= 16384) {
      this.c.add(g.c());
      this.f.d();
      this.e.a(this.f);
    } 
  }
  
  byte[] f() {
    return b(this.e.a);
  }
  
  public void write(int paramInt) {
    f()[this.e.c()] = (byte)(paramInt & 0xFF);
    e();
  }
  
  public void write(byte[] paramArrayOfbyte) {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    while (paramInt2 > 0) {
      byte[] arrayOfByte = f();
      int i = Math.min(arrayOfByte.length - this.e.b, paramInt2);
      System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, this.e.b, i);
      this.e.b(i);
      paramInt2 -= i;
      paramInt1 += i;
      e();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */