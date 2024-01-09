package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;
import java.util.HashMap;
import java.util.Map;

public class JsonGrammarGenerator extends ValidatingGrammarGenerator {
  public Symbol generate(Schema paramSchema) {
    return Symbol.root(new Symbol[] { generate(paramSchema, new HashMap<Object, Object>()) });
  }
  
  public Symbol generate(Schema paramSchema, Map<ValidatingGrammarGenerator$LitS, Symbol> paramMap) {
    byte b = 0;
    switch (JsonGrammarGenerator$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new RuntimeException("Unexpected schema type");
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
        return super.generate(paramSchema, paramMap);
      case 11:
        return Symbol.seq(new Symbol[] { new Symbol$EnumLabelsAction(paramSchema.getEnumSymbols()), Symbol.ENUM });
      case 12:
        return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.ARRAY_END, new Symbol[] { Symbol.ITEM_END, generate(paramSchema.getElementType(), paramMap) }), Symbol.ARRAY_START });
      case 13:
        return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.MAP_END, new Symbol[] { Symbol.ITEM_END, generate(paramSchema.getValueType(), paramMap), Symbol.MAP_KEY_MARKER, Symbol.STRING }), Symbol.MAP_START });
      case 14:
        break;
    } 
    ValidatingGrammarGenerator$LitS validatingGrammarGenerator$LitS = new ValidatingGrammarGenerator$LitS(paramSchema);
    Symbol symbol2 = (Symbol)paramMap.get(validatingGrammarGenerator$LitS);
    Symbol symbol1 = symbol2;
    if (symbol2 == null) {
      Symbol[] arrayOfSymbol = new Symbol[paramSchema.getFields().size() * 3 + 2];
      symbol1 = Symbol.seq(arrayOfSymbol);
      paramMap.put(validatingGrammarGenerator$LitS, symbol1);
      int i = arrayOfSymbol.length - 1;
      arrayOfSymbol[i] = Symbol.RECORD_START;
      for (Schema.Field field : paramSchema.getFields()) {
        arrayOfSymbol[--i] = new Symbol$FieldAdjustAction(b, field.name());
        arrayOfSymbol[--i] = generate(field.schema(), paramMap);
        arrayOfSymbol[--i] = Symbol.FIELD_END;
        b++;
      } 
      arrayOfSymbol[i - 1] = Symbol.RECORD_END;
    } 
    return symbol1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\JsonGrammarGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */