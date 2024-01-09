package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.ResolvingGrammarGenerator;
import com.flurry.org.apache.avro.io.parsing.Symbol;

/* loaded from: classes.dex */
public class ResolvingDecoder extends ValidatingDecoder {
    static final /* synthetic */ boolean $assertionsDisabled;
    private Decoder backup;

    static {
        $assertionsDisabled = !ResolvingDecoder.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolvingDecoder(Schema schema, Schema schema2, Decoder decoder) {
        this(resolve(schema, schema2), decoder);
    }

    private ResolvingDecoder(Object obj, Decoder decoder) {
        super((Symbol) obj, decoder);
    }

    public static Object resolve(Schema schema, Schema schema2) {
        if (schema == null) {
            throw new NullPointerException("writer cannot be null!");
        }
        if (schema2 == null) {
            throw new NullPointerException("reader cannot be null!");
        }
        return new ResolvingGrammarGenerator().generate(schema, schema2);
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.parsing.Parser.ActionHandler
    public Symbol doAction(Symbol symbol, Symbol symbol2) {
        if (symbol2 instanceof Symbol.FieldOrderAction) {
            if (symbol == Symbol.FIELD_ACTION) {
                return symbol2;
            }
            return null;
        } else if (symbol2 instanceof Symbol.ResolvingAction) {
            Symbol.ResolvingAction resolvingAction = (Symbol.ResolvingAction) symbol2;
            if (resolvingAction.reader != symbol) {
                throw new AvroTypeException("Found " + resolvingAction.reader + " while looking for " + symbol);
            }
            return resolvingAction.writer;
        } else {
            if (symbol2 instanceof Symbol.SkipAction) {
                this.parser.skipSymbol(((Symbol.SkipAction) symbol2).symToSkip);
            } else if (symbol2 instanceof Symbol.WriterUnionAction) {
                this.parser.pushSymbol(((Symbol.Alternative) this.parser.popSymbol()).getSymbol(this.in.readIndex()));
            } else if (symbol2 instanceof Symbol.ErrorAction) {
                throw new AvroTypeException(((Symbol.ErrorAction) symbol2).msg);
            } else {
                if (symbol2 instanceof Symbol.DefaultStartAction) {
                    this.backup = this.in;
                    this.in = DecoderFactory.get().binaryDecoder(((Symbol.DefaultStartAction) symbol2).contents, (BinaryDecoder) null);
                } else if (symbol2 != Symbol.DEFAULT_END_ACTION) {
                    throw new AvroTypeException("Unknown action: " + symbol2);
                } else {
                    this.in = this.backup;
                }
            }
            return null;
        }
    }

    public final void drain() {
        this.parser.processImplicitActions();
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.Decoder
    public double readDouble() {
        Symbol advance = this.parser.advance(Symbol.DOUBLE);
        if (advance == Symbol.INT) {
            return this.in.readInt();
        }
        if (advance == Symbol.LONG) {
            return this.in.readLong();
        }
        if (advance == Symbol.FLOAT) {
            return this.in.readFloat();
        }
        if ($assertionsDisabled || advance == Symbol.DOUBLE) {
            return this.in.readDouble();
        }
        throw new AssertionError();
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.Decoder
    public int readEnum() {
        this.parser.advance(Symbol.ENUM);
        Object obj = ((Symbol.EnumAdjustAction) this.parser.popSymbol()).adjustments[this.in.readEnum()];
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        throw new AvroTypeException((String) obj);
    }

    public final Schema.Field[] readFieldOrder() {
        return ((Symbol.FieldOrderAction) this.parser.advance(Symbol.FIELD_ACTION)).fields;
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.Decoder
    public float readFloat() {
        Symbol advance = this.parser.advance(Symbol.FLOAT);
        if (advance == Symbol.INT) {
            return this.in.readInt();
        }
        if (advance == Symbol.LONG) {
            return (float) this.in.readLong();
        }
        if ($assertionsDisabled || advance == Symbol.FLOAT) {
            return this.in.readFloat();
        }
        throw new AssertionError();
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.Decoder
    public int readIndex() {
        this.parser.advance(Symbol.UNION);
        Symbol.UnionAdjustAction unionAdjustAction = (Symbol.UnionAdjustAction) this.parser.popSymbol();
        this.parser.pushSymbol(unionAdjustAction.symToParse);
        return unionAdjustAction.rindex;
    }

    @Override // com.flurry.org.apache.avro.io.ValidatingDecoder, com.flurry.org.apache.avro.io.Decoder
    public long readLong() {
        Symbol advance = this.parser.advance(Symbol.LONG);
        if (advance == Symbol.INT) {
            return this.in.readInt();
        }
        if (advance == Symbol.DOUBLE) {
            return (long) this.in.readDouble();
        }
        if ($assertionsDisabled || advance == Symbol.LONG) {
            return this.in.readLong();
        }
        throw new AssertionError();
    }

    @Override // com.flurry.org.apache.avro.io.ParsingDecoder, com.flurry.org.apache.avro.io.parsing.SkipParser.SkipHandler
    public void skipAction() {
        Symbol popSymbol = this.parser.popSymbol();
        if (popSymbol instanceof Symbol.ResolvingAction) {
            this.parser.pushSymbol(((Symbol.ResolvingAction) popSymbol).writer);
        } else if (popSymbol instanceof Symbol.SkipAction) {
            this.parser.pushSymbol(((Symbol.SkipAction) popSymbol).symToSkip);
        } else if (popSymbol instanceof Symbol.WriterUnionAction) {
            this.parser.pushSymbol(((Symbol.Alternative) this.parser.popSymbol()).getSymbol(this.in.readIndex()));
        } else if (popSymbol instanceof Symbol.ErrorAction) {
            throw new AvroTypeException(((Symbol.ErrorAction) popSymbol).msg);
        } else {
            if (popSymbol instanceof Symbol.DefaultStartAction) {
                this.backup = this.in;
                this.in = DecoderFactory.get().binaryDecoder(((Symbol.DefaultStartAction) popSymbol).contents, (BinaryDecoder) null);
            } else if (popSymbol == Symbol.DEFAULT_END_ACTION) {
                this.in = this.backup;
            }
        }
    }
}
