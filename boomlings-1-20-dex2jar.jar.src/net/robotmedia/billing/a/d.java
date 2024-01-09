package net.robotmedia.billing.a;

public enum d {
  a, b, c, d;
  
  private static final d[] e = new d[] { a, b, c, d };
  
  public static d a(int paramInt) {
    d[] arrayOfD = values();
    return (paramInt < 0 || paramInt >= arrayOfD.length) ? b : arrayOfD[paramInt];
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */