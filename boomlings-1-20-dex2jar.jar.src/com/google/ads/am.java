package com.google.ads;

class am implements Runnable {
  final c a;
  
  final AdRequest b;
  
  final e c;
  
  am(e parame, c paramc, AdRequest paramAdRequest) {}
  
  public void run() {
    e.a(this.c, this.a, this.b);
    synchronized (e.a(this.c)) {
      e.a(this.c, (Thread)null);
      return;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */