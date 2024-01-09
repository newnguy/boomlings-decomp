package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ResolvingGrammarGenerator extends ValidatingGrammarGenerator {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$apache$avro$Schema$Type;
    private static EncoderFactory factory = new EncoderFactory().configureBufferSize(32);

    /* renamed from: com.flurry.org.apache.avro.io.parsing.ResolvingGrammarGenerator$1 */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$avro$Schema$Type = new int[Schema.Type.values().length];

        static {
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.INT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.LONG.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.FLOAT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.DOUBLE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.BYTES.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.FIXED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.ENUM.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.MAP.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.RECORD.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$org$apache$avro$Schema$Type[Schema.Type.UNION.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class LitS2 extends ValidatingGrammarGenerator.LitS {
        public Schema expected;

        public LitS2(Schema schema, Schema schema2) {
            super(schema);
            this.expected = schema2;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator.LitS
        public boolean equals(Object obj) {
            if (obj instanceof LitS2) {
                LitS2 litS2 = (LitS2) obj;
                return this.actual == litS2.actual && this.expected == litS2.expected;
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator.LitS
        public int hashCode() {
            return super.hashCode() + this.expected.hashCode();
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$apache$avro$Schema$Type() {
        int[] iArr = $SWITCH_TABLE$org$apache$avro$Schema$Type;
        if (iArr == null) {
            iArr = new int[Schema.Type.values().length];
            try {
                iArr[Schema.Type.ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Schema.Type.BOOLEAN.ordinal()] = 13;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Schema.Type.BYTES.ordinal()] = 8;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Schema.Type.DOUBLE.ordinal()] = 12;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Schema.Type.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Schema.Type.FIXED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Schema.Type.FLOAT.ordinal()] = 11;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[Schema.Type.INT.ordinal()] = 9;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[Schema.Type.LONG.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[Schema.Type.MAP.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[Schema.Type.NULL.ordinal()] = 14;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[Schema.Type.RECORD.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[Schema.Type.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[Schema.Type.UNION.ordinal()] = 5;
            } catch (NoSuchFieldError e14) {
            }
            $SWITCH_TABLE$org$apache$avro$Schema$Type = iArr;
        }
        return iArr;
    }

    private static int bestBranch(Schema schema, Schema schema2) {
        int i = 0;
        Schema.Type type = schema2.getType();
        int i2 = 0;
        for (Schema schema3 : schema.getTypes()) {
            if (type == schema3.getType()) {
                if (type != Schema.Type.RECORD && type != Schema.Type.ENUM && type != Schema.Type.FIXED) {
                    return i2;
                }
                String fullName = schema2.getFullName();
                String fullName2 = schema3.getFullName();
                if (fullName != null && fullName.equals(fullName2)) {
                    return i2;
                }
                if (fullName == fullName2 && type == Schema.Type.RECORD) {
                    return i2;
                }
            }
            i2++;
        }
        for (Schema schema4 : schema.getTypes()) {
            switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type.ordinal()]) {
                case 9:
                    switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[schema4.getType().ordinal()]) {
                        case 10:
                        case 12:
                            return i;
                    }
                case 10:
                case 11:
                    switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[schema4.getType().ordinal()]) {
                        case 12:
                            return i;
                    }
            }
            i++;
        }
        return -1;
    }

    public static void encode(Encoder encoder, Schema schema, JsonNode jsonNode) {
        byte[] bArr;
        switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[schema.getType().ordinal()]) {
            case 1:
                for (Schema.Field field : schema.getFields()) {
                    String name = field.name();
                    JsonNode jsonNode2 = jsonNode.get(name);
                    if (jsonNode2 == null) {
                        jsonNode2 = field.defaultValue();
                    }
                    if (jsonNode2 == null) {
                        throw new AvroTypeException("No default value for: " + name);
                    }
                    encode(encoder, field.schema(), jsonNode2);
                }
                return;
            case 2:
                encoder.writeEnum(schema.getEnumOrdinal(jsonNode.getTextValue()));
                return;
            case 3:
                encoder.writeArrayStart();
                encoder.setItemCount(jsonNode.size());
                Schema elementType = schema.getElementType();
                Iterator it = jsonNode.iterator();
                while (it.hasNext()) {
                    encoder.startItem();
                    encode(encoder, elementType, (JsonNode) it.next());
                }
                encoder.writeArrayEnd();
                return;
            case 4:
                encoder.writeMapStart();
                encoder.setItemCount(jsonNode.size());
                Schema valueType = schema.getValueType();
                Iterator fieldNames = jsonNode.getFieldNames();
                while (fieldNames.hasNext()) {
                    encoder.startItem();
                    String str = (String) fieldNames.next();
                    encoder.writeString(str);
                    encode(encoder, valueType, jsonNode.get(str));
                }
                encoder.writeMapEnd();
                return;
            case 5:
                encoder.writeIndex(0);
                encode(encoder, (Schema) schema.getTypes().get(0), jsonNode);
                return;
            case 6:
                if (!jsonNode.isTextual()) {
                    throw new AvroTypeException("Non-string default value for fixed: " + jsonNode);
                }
                byte[] bytes = jsonNode.getTextValue().getBytes("ISO-8859-1");
                if (bytes.length != schema.getFixedSize()) {
                    byte[] bArr2 = new byte[schema.getFixedSize()];
                    System.arraycopy(bytes, 0, bArr2, 0, schema.getFixedSize() > bytes.length ? bytes.length : schema.getFixedSize());
                    bArr = bArr2;
                } else {
                    bArr = bytes;
                }
                encoder.writeFixed(bArr);
                return;
            case 7:
                if (!jsonNode.isTextual()) {
                    throw new AvroTypeException("Non-string default value for string: " + jsonNode);
                }
                encoder.writeString(jsonNode.getTextValue());
                return;
            case 8:
                if (!jsonNode.isTextual()) {
                    throw new AvroTypeException("Non-string default value for bytes: " + jsonNode);
                }
                encoder.writeBytes(jsonNode.getTextValue().getBytes("ISO-8859-1"));
                return;
            case 9:
                if (!jsonNode.isNumber()) {
                    throw new AvroTypeException("Non-numeric default value for int: " + jsonNode);
                }
                encoder.writeInt(jsonNode.getIntValue());
                return;
            case 10:
                if (!jsonNode.isNumber()) {
                    throw new AvroTypeException("Non-numeric default value for long: " + jsonNode);
                }
                encoder.writeLong(jsonNode.getLongValue());
                return;
            case 11:
                if (!jsonNode.isNumber()) {
                    throw new AvroTypeException("Non-numeric default value for float: " + jsonNode);
                }
                encoder.writeFloat((float) jsonNode.getDoubleValue());
                return;
            case 12:
                if (!jsonNode.isNumber()) {
                    throw new AvroTypeException("Non-numeric default value for double: " + jsonNode);
                }
                encoder.writeDouble(jsonNode.getDoubleValue());
                return;
            case 13:
                if (!jsonNode.isBoolean()) {
                    throw new AvroTypeException("Non-boolean default for boolean: " + jsonNode);
                }
                encoder.writeBoolean(jsonNode.getBooleanValue());
                return;
            case 14:
                if (!jsonNode.isNull()) {
                    throw new AvroTypeException("Non-null default value for null type: " + jsonNode);
                }
                encoder.writeNull();
                return;
            default:
                return;
        }
    }

    private static byte[] getBinary(Schema schema, JsonNode jsonNode) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = factory.binaryEncoder(byteArrayOutputStream, null);
        encode(binaryEncoder, schema, jsonNode);
        binaryEncoder.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private static Symbol mkEnumAdjust(List list, List list2) {
        Object[] objArr = new Object[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr.length) {
                return new Symbol.EnumAdjustAction(list2.size(), objArr);
            }
            int indexOf = list2.indexOf(list.get(i2));
            objArr[i2] = indexOf == -1 ? "No match for " + ((String) list.get(i2)) : new Integer(indexOf);
            i = i2 + 1;
        }
    }

    private Symbol resolveRecords(Schema schema, Schema schema2, Map map) {
        LitS2 litS2 = new LitS2(schema, schema2);
        Symbol symbol = (Symbol) map.get(litS2);
        if (symbol == null) {
            List<Schema.Field> fields = schema.getFields();
            List<Schema.Field> fields2 = schema2.getFields();
            Schema.Field[] fieldArr = new Schema.Field[fields2.size()];
            int size = fields.size() + 1;
            int i = 0;
            for (Schema.Field field : fields) {
                Schema.Field field2 = schema2.getField(field.name());
                if (field2 != null) {
                    fieldArr[i] = field2;
                    i++;
                }
            }
            int i2 = i;
            int i3 = size;
            for (Schema.Field field3 : fields2) {
                if (schema.getField(field3.name()) == null) {
                    if (field3.defaultValue() == null) {
                        Symbol error = Symbol.error("Found " + schema.toString(true) + ", expecting " + schema2.toString(true));
                        map.put(litS2, error);
                        return error;
                    }
                    fieldArr[i2] = field3;
                    i3 += 3;
                    i2++;
                }
            }
            Symbol[] symbolArr = new Symbol[i3];
            int i4 = i3 - 1;
            symbolArr[i4] = new Symbol.FieldOrderAction(fieldArr);
            Symbol seq = Symbol.seq(symbolArr);
            map.put(litS2, seq);
            int i5 = i4;
            for (Schema.Field field4 : fields) {
                Schema.Field field5 = schema2.getField(field4.name());
                if (field5 == null) {
                    i5--;
                    symbolArr[i5] = new Symbol.SkipAction(generate(field4.schema(), field4.schema(), map));
                } else {
                    i5--;
                    symbolArr[i5] = generate(field4.schema(), field5.schema(), map);
                }
            }
            for (Schema.Field field6 : fields2) {
                if (schema.getField(field6.name()) == null) {
                    int i6 = i5 - 1;
                    symbolArr[i6] = new Symbol.DefaultStartAction(getBinary(field6.schema(), field6.defaultValue()));
                    int i7 = i6 - 1;
                    symbolArr[i7] = generate(field6.schema(), field6.schema(), map);
                    i5 = i7 - 1;
                    symbolArr[i5] = Symbol.DEFAULT_END_ACTION;
                }
            }
            return seq;
        }
        return symbol;
    }

    private Symbol resolveUnion(Schema schema, Schema schema2, Map map) {
        List<Schema> types = schema.getTypes();
        int size = types.size();
        Symbol[] symbolArr = new Symbol[size];
        String[] strArr = new String[size];
        int i = 0;
        for (Schema schema3 : types) {
            symbolArr[i] = generate(schema3, schema2, map);
            strArr[i] = schema3.getFullName();
            i++;
        }
        return Symbol.seq(Symbol.alt(symbolArr, strArr), new Symbol.WriterUnionAction());
    }

    public final Symbol generate(Schema schema, Schema schema2) {
        return Symbol.root(generate(schema, schema2, new HashMap()));
    }

    public Symbol generate(Schema schema, Schema schema2, Map map) {
        Schema.Type type = schema.getType();
        Schema.Type type2 = schema2.getType();
        if (type == type2) {
            switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type.ordinal()]) {
                case 1:
                    return resolveRecords(schema, schema2, map);
                case 2:
                    if (schema.getFullName() == null || schema.getFullName().equals(schema2.getFullName())) {
                        return Symbol.seq(mkEnumAdjust(schema.getEnumSymbols(), schema2.getEnumSymbols()), Symbol.ENUM);
                    }
                    break;
                case 3:
                    return Symbol.seq(Symbol.repeat(Symbol.ARRAY_END, generate(schema.getElementType(), schema2.getElementType(), map)), Symbol.ARRAY_START);
                case 4:
                    return Symbol.seq(Symbol.repeat(Symbol.MAP_END, generate(schema.getValueType(), schema2.getValueType(), map), Symbol.STRING), Symbol.MAP_START);
                case 5:
                    return resolveUnion(schema, schema2, map);
                case 6:
                    if (schema.getFullName().equals(schema2.getFullName()) && schema.getFixedSize() == schema2.getFixedSize()) {
                        return Symbol.seq(new Symbol.IntCheckAction(schema.getFixedSize()), Symbol.FIXED);
                    }
                    break;
                case 7:
                    return Symbol.STRING;
                case 8:
                    return Symbol.BYTES;
                case 9:
                    return Symbol.INT;
                case 10:
                    return Symbol.LONG;
                case 11:
                    return Symbol.FLOAT;
                case 12:
                    return Symbol.DOUBLE;
                case 13:
                    return Symbol.BOOLEAN;
                case 14:
                    return Symbol.NULL;
                default:
                    throw new AvroTypeException("Unkown type for schema: " + type);
            }
        } else if (type == Schema.Type.UNION) {
            return resolveUnion(schema, schema2, map);
        } else {
            switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type2.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 7:
                case 8:
                case 9:
                case 13:
                case 14:
                    break;
                case 5:
                    int bestBranch = bestBranch(schema2, schema);
                    if (bestBranch >= 0) {
                        return Symbol.seq(new Symbol.UnionAdjustAction(bestBranch, generate(schema, (Schema) schema2.getTypes().get(bestBranch), map)), Symbol.UNION);
                    }
                    break;
                case 6:
                default:
                    throw new RuntimeException("Unexpected schema type: " + type2);
                case 10:
                    switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type.ordinal()]) {
                        case 9:
                            return Symbol.resolve(super.generate(schema, map), Symbol.LONG);
                    }
                case 11:
                    switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type.ordinal()]) {
                        case 9:
                        case 10:
                            return Symbol.resolve(super.generate(schema, map), Symbol.FLOAT);
                    }
                case 12:
                    switch ($SWITCH_TABLE$org$apache$avro$Schema$Type()[type.ordinal()]) {
                        case 9:
                        case 10:
                        case 11:
                            return Symbol.resolve(super.generate(schema, map), Symbol.DOUBLE);
                    }
            }
        }
        return Symbol.error("Found " + schema.toString(true) + ", expecting " + schema2.toString(true));
    }
}
