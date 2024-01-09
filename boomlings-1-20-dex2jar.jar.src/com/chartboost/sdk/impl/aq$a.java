package com.chartboost.sdk.impl;

class aq$a {
  int a;
  
  int b;
  
  aq$a() {
    a();
  }
  
  void a() {
    this.a = -1;
    this.b = 0;
  }
  
  void a(int paramInt) {
    this.a = paramInt / 16384 - 1;
    this.b = paramInt % 16384;
  }
  
  void a(aq$a paramaq$a) {
    this.a = paramaq$a.a;
    this.b = paramaq$a.b;
  }
  
  int b() {
    return (this.a + 1) * 16384 + this.b;
  }
  
  void b(int paramInt) {
    this.b += paramInt;
    if (this.b > 16384)
      throw new IllegalArgumentException("something is wrong"); 
  }
  
  int c() {
    int i = this.b;
    this.b = i + 1;
    return i;
  }
  
  int c(int paramInt) {
    return (paramInt < this.a) ? 16384 : this.b;
  }
  
  void d() {
    if (this.b != 16384)
      throw new IllegalArgumentException("broken"); 
    this.a++;
    this.b = 0;
  }
  
  public String toString() {
    return this.a + "," + this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\aq$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */