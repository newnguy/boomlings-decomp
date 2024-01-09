package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ValidatingGrammarGenerator {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LitS {
        public final Schema actual;

        public LitS(Schema schema) {
            this.actual = schema;
        }

        public boolean equals(Object obj) {
            return (obj instanceof LitS) && this.actual == ((LitS) obj).actual;
        }

        public int hashCode() {
            return this.actual.hashCode();
        }
    }

    public Symbol generate(Schema schema) {
        return Symbol.root(generate(schema, new HashMap()));
    }

    public Symbol generate(Schema schema, Map map) {
        switch (schema.getType()) {
            case NULL:
                return Symbol.NULL;
            case BOOLEAN:
                return Symbol.BOOLEAN;
            case INT:
                return Symbol.INT;
            case LONG:
                return Symbol.LONG;
            case FLOAT:
                return Symbol.FLOAT;
            case DOUBLE:
                return Symbol.DOUBLE;
            case STRING:
                return Symbol.STRING;
            case BYTES:
                return Symbol.BYTES;
            case FIXED:
                return Symbol.seq(new Symbol.IntCheckAction(schema.getFixedSize()), Symbol.FIXED);
            case ENUM:
                return Symbol.seq(new Symbol.IntCheckAction(schema.getEnumSymbols().size()), Symbol.ENUM);
            case ARRAY:
                return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, generate(schema.getElementType(), map)), Symbol.ARRAY_START);
            case MAP:
                return Symbol.seq(Symbol.repeat(Symbol.MAP_END, generate(schema.getValueType(), map), Symbol.STRING), Symbol.MAP_START);
            case RECORD:
                LitS litS = new LitS(schema);
                Symbol symbol = (Symbol) map.get(litS);
                if (symbol == null) {
                    Symbol[] symbolArr = new Symbol[schema.getFields().size()];
                    Symbol seq = Symbol.seq(symbolArr);
                    map.put(litS, seq);
                    int length = symbolArr.length;
                    int i = length;
                    for (Schema.Field field : schema.getFields()) {
                        i--;
                        symbolArr[i] = generate(field.schema(), map);
                    }
                    return seq;
                }
                return symbol;
            case UNION:
                List types = schema.getTypes();
                Symbol[] symbolArr2 = new Symbol[types.size()];
                String[] strArr = new String[types.size()];
                int i2 = 0;
                for (Schema schema2 : schema.getTypes()) {
                    symbolArr2[i2] = generate(schema2, map);
                    strArr[i2] = schema2.getFullName();
                    i2++;
                }
                return Symbol.seq(Symbol.alt(symbolArr2, strArr), Symbol.UNION);
            default:
                throw new RuntimeException("Unexpected schema type");
        }
    }
}
