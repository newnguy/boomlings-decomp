package com.flurry.org.apache.avro.io.parsing;

public class Symbol$ImplicitAction extends Symbol {
  public final boolean isTrailing;
  
  private Symbol$ImplicitAction() {
    this(false);
  }
  
  private Symbol$ImplicitAction(boolean paramBoolean) {
    super(Symbol$Kind.IMPLICIT_ACTION);
    this.isTrailing = paramBoolean;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$ImplicitAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */