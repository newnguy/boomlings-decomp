package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class JsonGrammarGenerator extends ValidatingGrammarGenerator {
    @Override // com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator
    public Symbol generate(Schema schema) {
        return Symbol.root(generate(schema, new HashMap()));
    }

    @Override // com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator
    public Symbol generate(Schema schema, Map map) {
        int i = 0;
        switch (schema.getType()) {
            case NULL:
            case BOOLEAN:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STRING:
            case BYTES:
            case FIXED:
            case UNION:
                return super.generate(schema, map);
            case ENUM:
                return Symbol.seq(new Symbol.EnumLabelsAction(schema.getEnumSymbols()), Symbol.ENUM);
            case ARRAY:
                return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, Symbol.ITEM_END, generate(schema.getElementType(), map)), Symbol.ARRAY_START);
            case MAP:
                return Symbol.seq(Symbol.repeat(Symbol.MAP_END, Symbol.ITEM_END, generate(schema.getValueType(), map), Symbol.MAP_KEY_MARKER, Symbol.STRING), Symbol.MAP_START);
            case RECORD:
                ValidatingGrammarGenerator.LitS litS = new ValidatingGrammarGenerator.LitS(schema);
                Symbol symbol = (Symbol) map.get(litS);
                if (symbol == null) {
                    Symbol[] symbolArr = new Symbol[(schema.getFields().size() * 3) + 2];
                    Symbol seq = Symbol.seq(symbolArr);
                    map.put(litS, seq);
                    int length = symbolArr.length - 1;
                    symbolArr[length] = Symbol.RECORD_START;
                    int i2 = length;
                    for (Schema.Field field : schema.getFields()) {
                        int i3 = i2 - 1;
                        symbolArr[i3] = new Symbol.FieldAdjustAction(i, field.name());
                        int i4 = i3 - 1;
                        symbolArr[i4] = generate(field.schema(), map);
                        i2 = i4 - 1;
                        symbolArr[i2] = Symbol.FIELD_END;
                        i++;
                    }
                    symbolArr[i2 - 1] = Symbol.RECORD_END;
                    return seq;
                }
                return symbol;
            default:
                throw new RuntimeException("Unexpected schema type");
        }
    }
}
