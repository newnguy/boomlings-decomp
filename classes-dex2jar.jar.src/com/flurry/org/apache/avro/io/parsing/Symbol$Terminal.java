package com.flurry.org.apache.avro.io.parsing;

class Symbol$Terminal extends Symbol {
  private final String printName;
  
  public Symbol$Terminal(String paramString) {
    super(Symbol$Kind.TERMINAL);
    this.printName = paramString;
  }
  
  public String toString() {
    return this.printName;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Terminal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */