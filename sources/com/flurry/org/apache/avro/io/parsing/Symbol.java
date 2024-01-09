package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.file.DataFileConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public abstract class Symbol {
    public final Kind kind;
    public final Symbol[] production;
    public static final Symbol NULL = new Terminal(DataFileConstants.NULL_CODEC);
    public static final Symbol BOOLEAN = new Terminal("boolean");
    public static final Symbol INT = new Terminal("int");
    public static final Symbol LONG = new Terminal("long");
    public static final Symbol FLOAT = new Terminal("float");
    public static final Symbol DOUBLE = new Terminal("double");
    public static final Symbol STRING = new Terminal("string");
    public static final Symbol BYTES = new Terminal("bytes");
    public static final Symbol FIXED = new Terminal("fixed");
    public static final Symbol ENUM = new Terminal("enum");
    public static final Symbol UNION = new Terminal("union");
    public static final Symbol ARRAY_START = new Terminal("array-start");
    public static final Symbol ARRAY_END = new Terminal("array-end");
    public static final Symbol MAP_START = new Terminal("map-start");
    public static final Symbol MAP_END = new Terminal("map-end");
    public static final Symbol ITEM_END = new Terminal("item-end");
    public static final Symbol FIELD_ACTION = new Terminal("field-action");
    public static final Symbol RECORD_START = new ImplicitAction(false);
    public static final Symbol RECORD_END = new ImplicitAction(true);
    public static final Symbol UNION_END = new ImplicitAction(true);
    public static final Symbol FIELD_END = new ImplicitAction(true);
    public static final Symbol DEFAULT_END_ACTION = new ImplicitAction(true);
    public static final Symbol MAP_KEY_MARKER = new Terminal("map-key-marker");

    /* loaded from: classes.dex */
    public class Alternative extends Symbol {
        public final String[] labels;
        public final Symbol[] symbols;

        private Alternative(Symbol[] symbolArr, String[] strArr) {
            super(Kind.ALTERNATIVE);
            this.symbols = symbolArr;
            this.labels = strArr;
        }

        public int findLabel(String str) {
            if (str != null) {
                for (int i = 0; i < this.labels.length; i++) {
                    if (str.equals(this.labels[i])) {
                        return i;
                    }
                }
            }
            return -1;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public Alternative flatten(Map map, Map map2) {
            Symbol[] symbolArr = new Symbol[this.symbols.length];
            for (int i = 0; i < symbolArr.length; i++) {
                symbolArr[i] = this.symbols[i].flatten(map, map2);
            }
            return new Alternative(symbolArr, this.labels);
        }

        public String getLabel(int i) {
            return this.labels[i];
        }

        public Symbol getSymbol(int i) {
            return this.symbols[i];
        }

        public int size() {
            return this.symbols.length;
        }
    }

    /* loaded from: classes.dex */
    public class DefaultStartAction extends ImplicitAction {
        public final byte[] contents;

        public DefaultStartAction(byte[] bArr) {
            super();
            this.contents = bArr;
        }
    }

    /* loaded from: classes.dex */
    public class EnumAdjustAction extends IntCheckAction {
        public final Object[] adjustments;

        public EnumAdjustAction(int i, Object[] objArr) {
            super(i);
            this.adjustments = objArr;
        }
    }

    /* loaded from: classes.dex */
    public class EnumLabelsAction extends IntCheckAction {
        public final List symbols;

        public EnumLabelsAction(List list) {
            super(list.size());
            this.symbols = list;
        }

        public int findLabel(String str) {
            if (str != null) {
                for (int i = 0; i < this.symbols.size(); i++) {
                    if (str.equals(this.symbols.get(i))) {
                        return i;
                    }
                }
            }
            return -1;
        }

        public String getLabel(int i) {
            return (String) this.symbols.get(i);
        }
    }

    /* loaded from: classes.dex */
    public class ErrorAction extends ImplicitAction {
        public final String msg;

        private ErrorAction(String str) {
            super();
            this.msg = str;
        }
    }

    /* loaded from: classes.dex */
    public class FieldAdjustAction extends ImplicitAction {
        public final String fname;
        public final int rindex;

        public FieldAdjustAction(int i, String str) {
            super();
            this.rindex = i;
            this.fname = str;
        }
    }

    /* loaded from: classes.dex */
    public final class FieldOrderAction extends ImplicitAction {
        public final Schema.Field[] fields;

        public FieldOrderAction(Schema.Field[] fieldArr) {
            super();
            this.fields = fieldArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Fixup {
        public final int pos;
        public final Symbol[] symbols;

        public Fixup(Symbol[] symbolArr, int i) {
            this.symbols = symbolArr;
            this.pos = i;
        }
    }

    /* loaded from: classes.dex */
    public class ImplicitAction extends Symbol {
        public final boolean isTrailing;

        private ImplicitAction() {
            this(false);
        }

        private ImplicitAction(boolean z) {
            super(Kind.IMPLICIT_ACTION);
            this.isTrailing = z;
        }
    }

    /* loaded from: classes.dex */
    public class IntCheckAction extends Symbol {
        public final int size;

        public IntCheckAction(int i) {
            super(Kind.EXPLICIT_ACTION);
            this.size = i;
        }
    }

    /* loaded from: classes.dex */
    public enum Kind {
        TERMINAL,
        ROOT,
        SEQUENCE,
        REPEATER,
        ALTERNATIVE,
        IMPLICIT_ACTION,
        EXPLICIT_ACTION
    }

    /* loaded from: classes.dex */
    public class Repeater extends Symbol {
        public final Symbol end;

        private Repeater(Symbol symbol, Symbol... symbolArr) {
            super(Kind.REPEATER, makeProduction(symbolArr));
            this.end = symbol;
            this.production[0] = this;
        }

        private static Symbol[] makeProduction(Symbol[] symbolArr) {
            Symbol[] symbolArr2 = new Symbol[symbolArr.length + 1];
            System.arraycopy(symbolArr, 0, symbolArr2, 1, symbolArr.length);
            return symbolArr2;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public Repeater flatten(Map map, Map map2) {
            Repeater repeater = new Repeater(this.end, new Symbol[flattenedSize(this.production, 1)]);
            flatten(this.production, 1, repeater.production, 1, map, map2);
            return repeater;
        }
    }

    /* loaded from: classes.dex */
    public class ResolvingAction extends ImplicitAction {
        public final Symbol reader;
        public final Symbol writer;

        private ResolvingAction(Symbol symbol, Symbol symbol2) {
            super();
            this.writer = symbol;
            this.reader = symbol2;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public ResolvingAction flatten(Map map, Map map2) {
            return new ResolvingAction(this.writer.flatten(map, map2), this.reader.flatten(map, map2));
        }
    }

    /* loaded from: classes.dex */
    public class Root extends Symbol {
        private Root(Symbol... symbolArr) {
            super(Kind.ROOT, makeProduction(symbolArr));
            this.production[0] = this;
        }

        private static Symbol[] makeProduction(Symbol[] symbolArr) {
            Symbol[] symbolArr2 = new Symbol[flattenedSize(symbolArr, 0) + 1];
            flatten(symbolArr, 0, symbolArr2, 1, new HashMap(), new HashMap());
            return symbolArr2;
        }
    }

    /* loaded from: classes.dex */
    public class Sequence extends Symbol implements Iterable {
        private Sequence(Symbol[] symbolArr) {
            super(Kind.SEQUENCE, symbolArr);
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public Sequence flatten(Map map, Map map2) {
            Sequence sequence = (Sequence) map.get(this);
            if (sequence == null) {
                Sequence sequence2 = new Sequence(new Symbol[flattenedSize()]);
                map.put(this, sequence2);
                ArrayList<Fixup> arrayList = new ArrayList();
                map2.put(sequence2, arrayList);
                flatten(this.production, 0, sequence2.production, 0, map, map2);
                for (Fixup fixup : arrayList) {
                    System.arraycopy(sequence2.production, 0, fixup.symbols, fixup.pos, sequence2.production.length);
                }
                map2.remove(sequence2);
                return sequence2;
            }
            return sequence;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public final int flattenedSize() {
            return flattenedSize(this.production, 0);
        }

        public Symbol get(int i) {
            return this.production[i];
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return new Iterator() { // from class: com.flurry.org.apache.avro.io.parsing.Symbol.Sequence.1
                private int pos;

                {
                    this.pos = Sequence.this.production.length;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.pos > 0;
                }

                @Override // java.util.Iterator
                public Symbol next() {
                    if (this.pos > 0) {
                        Symbol[] symbolArr = Sequence.this.production;
                        int i = this.pos - 1;
                        this.pos = i;
                        return symbolArr[i];
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        public int size() {
            return this.production.length;
        }
    }

    /* loaded from: classes.dex */
    public class SkipAction extends ImplicitAction {
        public final Symbol symToSkip;

        public SkipAction(Symbol symbol) {
            super(true);
            this.symToSkip = symbol;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public SkipAction flatten(Map map, Map map2) {
            return new SkipAction(this.symToSkip.flatten(map, map2));
        }
    }

    /* loaded from: classes.dex */
    class Terminal extends Symbol {
        private final String printName;

        public Terminal(String str) {
            super(Kind.TERMINAL);
            this.printName = str;
        }

        public String toString() {
            return this.printName;
        }
    }

    /* loaded from: classes.dex */
    public class UnionAdjustAction extends ImplicitAction {
        public final int rindex;
        public final Symbol symToParse;

        public UnionAdjustAction(int i, Symbol symbol) {
            super();
            this.rindex = i;
            this.symToParse = symbol;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.Symbol
        public UnionAdjustAction flatten(Map map, Map map2) {
            return new UnionAdjustAction(this.rindex, this.symToParse.flatten(map, map2));
        }
    }

    /* loaded from: classes.dex */
    public class WriterUnionAction extends ImplicitAction {
        public WriterUnionAction() {
            super();
        }
    }

    protected Symbol(Kind kind) {
        this(kind, null);
    }

    protected Symbol(Kind kind, Symbol[] symbolArr) {
        this.production = symbolArr;
        this.kind = kind;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol alt(Symbol[] symbolArr, String[] strArr) {
        return new Alternative(symbolArr, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol error(String str) {
        return new ErrorAction(str);
    }

    static void flatten(Symbol[] symbolArr, int i, Symbol[] symbolArr2, int i2, Map map, Map map2) {
        while (i < symbolArr.length) {
            Symbol flatten = symbolArr[i].flatten(map, map2);
            if (flatten instanceof Sequence) {
                Symbol[] symbolArr3 = flatten.production;
                List list = (List) map2.get(flatten);
                if (list == null) {
                    System.arraycopy(symbolArr3, 0, symbolArr2, i2, symbolArr3.length);
                } else {
                    list.add(new Fixup(symbolArr2, i2));
                }
                i2 += symbolArr3.length;
            } else {
                symbolArr2[i2] = flatten;
                i2++;
            }
            i++;
        }
    }

    protected static int flattenedSize(Symbol[] symbolArr, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= symbolArr.length) {
                return i3;
            }
            i2 = symbolArr[i] instanceof Sequence ? ((Sequence) symbolArr[i]).flattenedSize() + i3 : i3 + 1;
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol repeat(Symbol symbol, Symbol... symbolArr) {
        return new Repeater(symbolArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol resolve(Symbol symbol, Symbol symbol2) {
        return new ResolvingAction(symbol2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol root(Symbol... symbolArr) {
        return new Root(symbolArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Symbol seq(Symbol... symbolArr) {
        return new Sequence(symbolArr);
    }

    public Symbol flatten(Map map, Map map2) {
        return this;
    }

    public int flattenedSize() {
        return 1;
    }
}
