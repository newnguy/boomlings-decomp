package com.chartboost.sdk.impl;

import java.io.OutputStream;

public class ao extends ap {
  private int a;
  
  private int b;
  
  private byte[] c = new byte[512];
  
  public int a() {
    return this.a;
  }
  
  public int a(OutputStream paramOutputStream) {
    paramOutputStream.write(this.c, 0, this.b);
    return this.b;
  }
  
  public void a(int paramInt) {
    this.a = paramInt;
  }
  
  public int b() {
    return this.b;
  }
  
  void b(int paramInt) {
    int i = this.a + paramInt;
    if (i >= this.c.length) {
      int j = this.c.length * 2;
      paramInt = j;
      if (j <= i)
        paramInt = i + 128; 
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.c, 0, arrayOfByte, 0, this.b);
      this.c = arrayOfByte;
    } 
  }
  
  public void write(int paramInt) {
    b(1);
    byte[] arrayOfByte = this.c;
    int i = this.a;
    this.a = i + 1;
    arrayOfByte[i] = (byte)(paramInt & 0xFF);
    this.b = Math.max(this.a, this.b);
  }
  
  public void write(byte[] paramArrayOfbyte) {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    b(paramInt2);
    System.arraycopy(paramArrayOfbyte, paramInt1, this.c, this.a, paramInt2);
    this.a += paramInt2;
    this.b = Math.max(this.a, this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */