package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ValidatingGrammarGenerator {
  public Symbol generate(Schema paramSchema) {
    return Symbol.root(new Symbol[] { generate(paramSchema, new HashMap<Object, Object>()) });
  }
  
  public Symbol generate(Schema paramSchema, Map<ValidatingGrammarGenerator$LitS, Symbol> paramMap) {
    Symbol symbol1;
    Symbol symbol2;
    ValidatingGrammarGenerator$LitS validatingGrammarGenerator$LitS;
    switch (ValidatingGrammarGenerator$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new RuntimeException("Unexpected schema type");
      case 1:
        return Symbol.NULL;
      case 2:
        return Symbol.BOOLEAN;
      case 3:
        return Symbol.INT;
      case 4:
        return Symbol.LONG;
      case 5:
        return Symbol.FLOAT;
      case 6:
        return Symbol.DOUBLE;
      case 7:
        return Symbol.STRING;
      case 8:
        return Symbol.BYTES;
      case 9:
        return Symbol.seq(new Symbol[] { new Symbol$IntCheckAction(paramSchema.getFixedSize()), Symbol.FIXED });
      case 10:
        return Symbol.seq(new Symbol[] { new Symbol$IntCheckAction(paramSchema.getEnumSymbols().size()), Symbol.ENUM });
      case 11:
        return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.ARRAY_END, new Symbol[] { generate(paramSchema.getElementType(), paramMap) }), Symbol.ARRAY_START });
      case 12:
        return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.MAP_END, new Symbol[] { generate(paramSchema.getValueType(), paramMap), Symbol.STRING }), Symbol.MAP_START });
      case 13:
        validatingGrammarGenerator$LitS = new ValidatingGrammarGenerator$LitS(paramSchema);
        symbol2 = (Symbol)paramMap.get(validatingGrammarGenerator$LitS);
        symbol1 = symbol2;
        if (symbol2 == null) {
          Symbol[] arrayOfSymbol1 = new Symbol[paramSchema.getFields().size()];
          symbol1 = Symbol.seq(arrayOfSymbol1);
          paramMap.put(validatingGrammarGenerator$LitS, symbol1);
          int i = arrayOfSymbol1.length;
          iterator = paramSchema.getFields().iterator();
          while (true) {
            if (iterator.hasNext()) {
              Schema.Field field = iterator.next();
              arrayOfSymbol1[--i] = generate(field.schema(), paramMap);
              continue;
            } 
            return symbol1;
          } 
        } 
        return symbol1;
      case 14:
        break;
    } 
    List list = iterator.getTypes();
    Symbol[] arrayOfSymbol = new Symbol[list.size()];
    String[] arrayOfString = new String[list.size()];
    Iterator<Schema.Field> iterator = iterator.getTypes().iterator();
    for (byte b = 0; iterator.hasNext(); b++) {
      Schema schema = (Schema)iterator.next();
      arrayOfSymbol[b] = generate(schema, paramMap);
      arrayOfString[b] = schema.getFullName();
    } 
    return Symbol.seq(new Symbol[] { Symbol.alt(arrayOfSymbol, arrayOfString), Symbol.UNION });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\ValidatingGrammarGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */