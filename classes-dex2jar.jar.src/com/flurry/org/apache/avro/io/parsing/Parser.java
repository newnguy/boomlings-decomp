package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.AvroTypeException;

public class Parser {
  protected int pos;
  
  protected Symbol[] stack;
  
  protected final Parser$ActionHandler symbolHandler;
  
  public Parser(Symbol paramSymbol, Parser$ActionHandler paramParser$ActionHandler) {
    this.symbolHandler = paramParser$ActionHandler;
    this.stack = new Symbol[5];
    this.stack[0] = paramSymbol;
    this.pos = 1;
  }
  
  private void expandStack() {
    Symbol[] arrayOfSymbol = new Symbol[this.stack.length + Math.max(this.stack.length, 1024)];
    System.arraycopy(this.stack, 0, arrayOfSymbol, 0, this.stack.length);
    this.stack = arrayOfSymbol;
  }
  
  public final Symbol advance(Symbol paramSymbol) {
    while (true) {
      Symbol[] arrayOfSymbol = this.stack;
      int i = this.pos - 1;
      this.pos = i;
      Symbol symbol = arrayOfSymbol[i];
      if (symbol == paramSymbol)
        return symbol; 
      Symbol$Kind symbol$Kind = symbol.kind;
      if (symbol$Kind == Symbol$Kind.IMPLICIT_ACTION) {
        symbol = this.symbolHandler.doAction(paramSymbol, symbol);
        if (symbol != null)
          return symbol; 
        continue;
      } 
      if (symbol$Kind == Symbol$Kind.TERMINAL)
        throw new AvroTypeException("Attempt to process a " + paramSymbol + " when a " + symbol + " was expected."); 
      if (symbol$Kind != Symbol$Kind.REPEATER || paramSymbol != ((Symbol$Repeater)symbol).end) {
        pushProduction(symbol);
        continue;
      } 
      return paramSymbol;
    } 
  }
  
  public int depth() {
    return this.pos;
  }
  
  public Symbol popSymbol() {
    Symbol[] arrayOfSymbol = this.stack;
    int i = this.pos - 1;
    this.pos = i;
    return arrayOfSymbol[i];
  }
  
  public final void processImplicitActions() {
    while (true) {
      if (this.pos > 1) {
        Symbol symbol = this.stack[this.pos - 1];
        if (symbol.kind == Symbol$Kind.IMPLICIT_ACTION) {
          this.pos--;
          this.symbolHandler.doAction(null, symbol);
          continue;
        } 
        if (symbol.kind != Symbol$Kind.TERMINAL) {
          this.pos--;
          pushProduction(symbol);
          continue;
        } 
      } 
      return;
    } 
  }
  
  public final void processTrailingImplicitActions() {
    while (true) {
      if (this.pos >= 1) {
        Symbol symbol = this.stack[this.pos - 1];
        if (symbol.kind == Symbol$Kind.IMPLICIT_ACTION && ((Symbol$ImplicitAction)symbol).isTrailing) {
          this.pos--;
          this.symbolHandler.doAction(null, symbol);
          continue;
        } 
      } 
      return;
    } 
  }
  
  public final void pushProduction(Symbol paramSymbol) {
    Symbol[] arrayOfSymbol = paramSymbol.production;
    while (true) {
      if (this.pos + arrayOfSymbol.length <= this.stack.length) {
        System.arraycopy(arrayOfSymbol, 0, this.stack, this.pos, arrayOfSymbol.length);
        int i = this.pos;
        this.pos = arrayOfSymbol.length + i;
        return;
      } 
      expandStack();
    } 
  }
  
  public void pushSymbol(Symbol paramSymbol) {
    if (this.pos == this.stack.length)
      expandStack(); 
    Symbol[] arrayOfSymbol = this.stack;
    int i = this.pos;
    this.pos = i + 1;
    arrayOfSymbol[i] = paramSymbol;
  }
  
  public void reset() {
    this.pos = 1;
  }
  
  public Symbol topSymbol() {
    return this.stack[this.pos - 1];
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */