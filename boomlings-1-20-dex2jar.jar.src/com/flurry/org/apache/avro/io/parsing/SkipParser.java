package com.flurry.org.apache.avro.io.parsing;

public class SkipParser extends Parser {
  static final boolean $assertionsDisabled;
  
  private final SkipParser$SkipHandler skipHandler;
  
  static {
    boolean bool;
    if (!SkipParser.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    $assertionsDisabled = bool;
  }
  
  public SkipParser(Symbol paramSymbol, Parser$ActionHandler paramParser$ActionHandler, SkipParser$SkipHandler paramSkipParser$SkipHandler) {
    super(paramSymbol, paramParser$ActionHandler);
    this.skipHandler = paramSkipParser$SkipHandler;
  }
  
  public final void skipRepeater() {
    int i = this.pos;
    Symbol[] arrayOfSymbol = this.stack;
    int j = this.pos - 1;
    this.pos = j;
    Symbol symbol = arrayOfSymbol[j];
    assert symbol.kind == Symbol$Kind.REPEATER;
    pushProduction(symbol);
    skipTo(i);
  }
  
  public final void skipSymbol(Symbol paramSymbol) {
    int i = this.pos;
    pushSymbol(paramSymbol);
    skipTo(i);
  }
  
  public final void skipTo(int paramInt) {
    while (paramInt < this.pos) {
      Symbol symbol = this.stack[this.pos - 1];
      if (symbol.kind != Symbol$Kind.TERMINAL) {
        if (symbol.kind == Symbol$Kind.IMPLICIT_ACTION || symbol.kind == Symbol$Kind.EXPLICIT_ACTION) {
          this.skipHandler.skipAction();
          continue;
        } 
        this.pos--;
        pushProduction(symbol);
        continue;
      } 
      this.skipHandler.skipTopSymbol();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\SkipParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */