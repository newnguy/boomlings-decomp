package net.robotmedia.billing;

public enum k {
  a, b, c, d, e, f, g;
  
  private static final k[] h = new k[] { a, b, c, d, e, f, g };
  
  public static boolean a(int paramInt) {
    return (a.ordinal() == paramInt);
  }
  
  public static k b(int paramInt) {
    k[] arrayOfK = values();
    return (paramInt < 0 || paramInt >= arrayOfK.length) ? g : arrayOfK[paramInt];
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */