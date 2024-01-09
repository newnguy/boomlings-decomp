package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;

class ResolvingGrammarGenerator$LitS2 extends ValidatingGrammarGenerator$LitS {
  public Schema expected;
  
  public ResolvingGrammarGenerator$LitS2(Schema paramSchema1, Schema paramSchema2) {
    super(paramSchema1);
    this.expected = paramSchema2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (!(paramObject instanceof ResolvingGrammarGenerator$LitS2))
      return bool2; 
    paramObject = paramObject;
    boolean bool1 = bool2;
    if (this.actual == ((ResolvingGrammarGenerator$LitS2)paramObject).actual) {
      bool1 = bool2;
      if (this.expected == ((ResolvingGrammarGenerator$LitS2)paramObject).expected)
        bool1 = true; 
    } 
    return bool1;
  }
  
  public int hashCode() {
    return super.hashCode() + this.expected.hashCode();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\ResolvingGrammarGenerator$LitS2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */