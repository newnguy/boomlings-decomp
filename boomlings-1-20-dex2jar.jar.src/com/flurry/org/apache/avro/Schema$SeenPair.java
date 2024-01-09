package com.flurry.org.apache.avro;

class Schema$SeenPair {
  private Object s1;
  
  private Object s2;
  
  private Schema$SeenPair(Object paramObject1, Object paramObject2) {
    this.s1 = paramObject1;
    this.s2 = paramObject2;
  }
  
  public boolean equals(Object paramObject) {
    return (this.s1 == ((Schema$SeenPair)paramObject).s1 && this.s2 == ((Schema$SeenPair)paramObject).s2);
  }
  
  public int hashCode() {
    return System.identityHashCode(this.s1) + System.identityHashCode(this.s2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$SeenPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */