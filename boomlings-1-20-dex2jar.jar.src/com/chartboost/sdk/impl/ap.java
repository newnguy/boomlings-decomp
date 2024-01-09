package com.chartboost.sdk.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class ap extends OutputStream {
  public abstract int a();
  
  public abstract int a(OutputStream paramOutputStream);
  
  public void a(double paramDouble) {
    a(Double.doubleToRawLongBits(paramDouble));
  }
  
  public abstract void a(int paramInt);
  
  public void a(int paramInt1, int paramInt2) {
    int i = a();
    a(paramInt1);
    c(paramInt2);
    a(i);
  }
  
  public void a(long paramLong) {
    write((byte)(int)(paramLong >> 0L & 0xFFL));
    write((byte)(int)(paramLong >> 8L & 0xFFL));
    write((byte)(int)(paramLong >> 16L & 0xFFL));
    write((byte)(int)(paramLong >> 24L & 0xFFL));
    write((byte)(int)(paramLong >> 32L & 0xFFL));
    write((byte)(int)(paramLong >> 40L & 0xFFL));
    write((byte)(int)(paramLong >> 48L & 0xFFL));
    write((byte)(int)(paramLong >> 56L & 0xFFL));
  }
  
  public abstract int b();
  
  public void c(int paramInt) {
    write(paramInt >> 0);
    write(paramInt >> 8);
    write(paramInt >> 16);
    write(paramInt >> 24);
  }
  
  public byte[] c() {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      this(b());
      a(byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      throw new RuntimeException("should be impossible", iOException);
    } 
  }
  
  public void d(int paramInt) {
    write(paramInt >> 24);
    write(paramInt >> 16);
    write(paramInt >> 8);
    write(paramInt);
  }
  
  public String toString() {
    return getClass().getName() + " size: " + b() + " pos: " + a();
  }
  
  public abstract void write(int paramInt);
  
  public abstract void write(byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */