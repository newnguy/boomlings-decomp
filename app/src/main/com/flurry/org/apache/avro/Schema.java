package com.flurry.org.apache.avro;

import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.ObjectMapper;
import com.flurry.org.codehaus.jackson.node.DoubleNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class Schema {
    private static final Set FIELD_RESERVED;
    private static final int NO_HASHCODE = Integer.MIN_VALUE;
    static final Map PRIMITIVES;
    private static final Set SCHEMA_RESERVED;
    private static final ThreadLocal SEEN_EQUALS;
    private static final ThreadLocal SEEN_HASHCODE;
    private static ThreadLocal validateNames;
    private final Type type;
    static final JsonFactory FACTORY = new JsonFactory();
    static final ObjectMapper MAPPER = new ObjectMapper(FACTORY);
    Props props = new Props(SCHEMA_RESERVED);
    int hashCode = NO_HASHCODE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ArraySchema extends Schema {
        private final Schema elementType;

        public ArraySchema(Schema schema) {
            super(Type.ARRAY);
            this.elementType = schema;
        }

        @Override // com.flurry.org.apache.avro.Schema
        int computeHash() {
            return super.computeHash() + this.elementType.computeHash();
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ArraySchema) {
                ArraySchema arraySchema = (ArraySchema) obj;
                return equalCachedHash(arraySchema) && this.elementType.equals(arraySchema.elementType) && this.props.equals(arraySchema.props);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public Schema getElementType() {
            return this.elementType;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "array");
            jsonGenerator.writeFieldName("items");
            this.elementType.toJson(names, jsonGenerator);
            this.props.write(jsonGenerator);
            jsonGenerator.writeEndObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BooleanSchema extends Schema {
        public BooleanSchema() {
            super(Type.BOOLEAN);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BytesSchema extends Schema {
        public BytesSchema() {
            super(Type.BYTES);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DoubleSchema extends Schema {
        public DoubleSchema() {
            super(Type.DOUBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class EnumSchema extends NamedSchema {
        private final Map ordinals;
        private final List symbols;

        public EnumSchema(Name name, String str, LockableArrayList lockableArrayList) {
            super(Type.ENUM, name, str);
            this.symbols = lockableArrayList.lock();
            this.ordinals = new HashMap();
            Iterator it = lockableArrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                String str2 = (String) it.next();
                int i2 = i + 1;
                if (this.ordinals.put(Schema.validateName(str2), Integer.valueOf(i)) != null) {
                    throw new SchemaParseException("Duplicate enum symbol: " + str2);
                }
                i = i2;
            }
        }

        @Override // com.flurry.org.apache.avro.Schema.NamedSchema, com.flurry.org.apache.avro.Schema
        int computeHash() {
            return super.computeHash() + this.symbols.hashCode();
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof EnumSchema) {
                EnumSchema enumSchema = (EnumSchema) obj;
                return equalCachedHash(enumSchema) && equalNames(enumSchema) && this.symbols.equals(enumSchema.symbols) && this.props.equals(enumSchema.props);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public int getEnumOrdinal(String str) {
            return ((Integer) this.ordinals.get(str)).intValue();
        }

        @Override // com.flurry.org.apache.avro.Schema
        public List getEnumSymbols() {
            return this.symbols;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean hasEnumSymbol(String str) {
            return this.ordinals.containsKey(str);
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            if (writeNameRef(names, jsonGenerator)) {
                return;
            }
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "enum");
            writeName(names, jsonGenerator);
            if (getDoc() != null) {
                jsonGenerator.writeStringField("doc", getDoc());
            }
            jsonGenerator.writeArrayFieldStart("symbols");
            for (String str : this.symbols) {
                jsonGenerator.writeString(str);
            }
            jsonGenerator.writeEndArray();
            this.props.write(jsonGenerator);
            aliasesToJson(jsonGenerator);
            jsonGenerator.writeEndObject();
        }
    }

    /* loaded from: classes.dex */
    public class Field {
        private Set aliases;
        private final JsonNode defaultValue;
        private final String doc;
        private final String name;
        private final Order order;
        private transient int position;
        private final Props props;
        private final Schema schema;

        /* loaded from: classes.dex */
        public enum Order {
            ASCENDING,
            DESCENDING,
            IGNORE;
            
            private String name = name().toLowerCase();

            Order() {
            }
        }

        public Field(String str, Schema schema, String str2, JsonNode jsonNode) {
            this(str, schema, str2, jsonNode, Order.ASCENDING);
        }

        public Field(String str, Schema schema, String str2, JsonNode jsonNode, Order order) {
            this.position = -1;
            this.props = new Props(Schema.FIELD_RESERVED);
            this.name = Schema.validateName(str);
            this.schema = schema;
            this.doc = str2;
            this.defaultValue = jsonNode;
            this.order = order;
        }

        private boolean defaultValueEquals(JsonNode jsonNode) {
            return this.defaultValue == null ? jsonNode == null : Double.isNaN(this.defaultValue.getValueAsDouble()) ? Double.isNaN(jsonNode.getValueAsDouble()) : this.defaultValue.equals(jsonNode);
        }

        public void addAlias(String str) {
            if (this.aliases == null) {
                this.aliases = new LinkedHashSet();
            }
            this.aliases.add(str);
        }

        public synchronized void addProp(String str, String str2) {
            this.props.add(str, str2);
        }

        public Set aliases() {
            return this.aliases == null ? Collections.emptySet() : Collections.unmodifiableSet(this.aliases);
        }

        public JsonNode defaultValue() {
            return this.defaultValue;
        }

        public String doc() {
            return this.doc;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Field) {
                Field field = (Field) obj;
                return this.name.equals(field.name) && this.schema.equals(field.schema) && defaultValueEquals(field.defaultValue) && this.props.equals(field.props);
            }
            return false;
        }

        public synchronized String getProp(String str) {
            return (String) this.props.get(str);
        }

        public int hashCode() {
            return this.name.hashCode() + this.schema.computeHash();
        }

        public String name() {
            return this.name;
        }

        public Order order() {
            return this.order;
        }

        public int pos() {
            return this.position;
        }

        public Map props() {
            return Collections.unmodifiableMap(this.props);
        }

        public Schema schema() {
            return this.schema;
        }

        public String toString() {
            return this.name + " type:" + this.schema.type + " pos:" + this.position;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FixedSchema extends NamedSchema {
        private final int size;

        public FixedSchema(Name name, String str, int i) {
            super(Type.FIXED, name, str);
            if (i < 0) {
                throw new IllegalArgumentException("Invalid fixed size: " + i);
            }
            this.size = i;
        }

        @Override // com.flurry.org.apache.avro.Schema.NamedSchema, com.flurry.org.apache.avro.Schema
        int computeHash() {
            return super.computeHash() + this.size;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FixedSchema) {
                FixedSchema fixedSchema = (FixedSchema) obj;
                return equalCachedHash(fixedSchema) && equalNames(fixedSchema) && this.size == fixedSchema.size && this.props.equals(fixedSchema.props);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public int getFixedSize() {
            return this.size;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            if (writeNameRef(names, jsonGenerator)) {
                return;
            }
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "fixed");
            writeName(names, jsonGenerator);
            if (getDoc() != null) {
                jsonGenerator.writeStringField("doc", getDoc());
            }
            jsonGenerator.writeNumberField("size", this.size);
            this.props.write(jsonGenerator);
            aliasesToJson(jsonGenerator);
            jsonGenerator.writeEndObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class FloatSchema extends Schema {
        public FloatSchema() {
            super(Type.FLOAT);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class IntSchema extends Schema {
        public IntSchema() {
            super(Type.INT);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LockableArrayList extends ArrayList {
        private static final long serialVersionUID = 1;
        private boolean locked;

        public LockableArrayList() {
            this.locked = false;
        }

        public LockableArrayList(int i) {
            super(i);
            this.locked = false;
        }

        public LockableArrayList(List list) {
            super(list);
            this.locked = false;
        }

        private void ensureUnlocked() {
            if (this.locked) {
                throw new IllegalStateException();
            }
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(Object obj) {
            ensureUnlocked();
            return super.add(obj);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection collection) {
            ensureUnlocked();
            return super.addAll(i, collection);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection collection) {
            ensureUnlocked();
            return super.addAll(collection);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            ensureUnlocked();
            super.clear();
        }

        public List lock() {
            this.locked = true;
            return this;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public Object remove(int i) {
            ensureUnlocked();
            return super.remove(i);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            ensureUnlocked();
            return super.remove(obj);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection collection) {
            ensureUnlocked();
            return super.removeAll(collection);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection collection) {
            ensureUnlocked();
            return super.retainAll(collection);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class LongSchema extends Schema {
        public LongSchema() {
            super(Type.LONG);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MapSchema extends Schema {
        private final Schema valueType;

        public MapSchema(Schema schema) {
            super(Type.MAP);
            this.valueType = schema;
        }

        @Override // com.flurry.org.apache.avro.Schema
        int computeHash() {
            return super.computeHash() + this.valueType.computeHash();
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MapSchema) {
                MapSchema mapSchema = (MapSchema) obj;
                return equalCachedHash(mapSchema) && this.valueType.equals(mapSchema.valueType) && this.props.equals(mapSchema.props);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public Schema getValueType() {
            return this.valueType;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", "map");
            jsonGenerator.writeFieldName("values");
            this.valueType.toJson(names, jsonGenerator);
            this.props.write(jsonGenerator);
            jsonGenerator.writeEndObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Name {
        private final String full;
        private final String name;
        private final String space;

        public Name(String str, String str2) {
            if (str == null) {
                this.full = null;
                this.space = null;
                this.name = null;
                return;
            }
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf < 0) {
                this.space = str2;
                this.name = Schema.validateName(str);
            } else {
                this.space = str.substring(0, lastIndexOf);
                this.name = Schema.validateName(str.substring(lastIndexOf + 1, str.length()));
            }
            this.full = this.space == null ? this.name : this.space + "." + this.name;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Name) {
                Name name = (Name) obj;
                return this.full == null ? name.full == null : this.full.equals(name.full);
            }
            return false;
        }

        public String getQualified(String str) {
            return (this.space == null || this.space.equals(str)) ? this.name : this.full;
        }

        public int hashCode() {
            if (this.full == null) {
                return 0;
            }
            return this.full.hashCode();
        }

        public String toString() {
            return this.full;
        }

        public void writeName(Names names, JsonGenerator jsonGenerator) {
            if (this.name != null) {
                jsonGenerator.writeStringField("name", this.name);
            }
            if (this.space != null) {
                if (!this.space.equals(names.space())) {
                    jsonGenerator.writeStringField("namespace", this.space);
                }
                if (names.space() == null) {
                    names.space(this.space);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public abstract class NamedSchema extends Schema {
        Set aliases;
        final String doc;
        final Name name;

        public NamedSchema(Type type, Name name, String str) {
            super(type);
            this.name = name;
            this.doc = str;
            if (PRIMITIVES.containsKey(name.full)) {
                throw new AvroTypeException("Schemas may not be named after primitives: " + name.full);
            }
        }

        @Override // com.flurry.org.apache.avro.Schema
        public void addAlias(String str) {
            if (this.aliases == null) {
                this.aliases = new LinkedHashSet();
            }
            this.aliases.add(new Name(str, this.name.space));
        }

        public void aliasesToJson(JsonGenerator jsonGenerator) {
            if (this.aliases == null || this.aliases.size() == 0) {
                return;
            }
            jsonGenerator.writeFieldName("aliases");
            jsonGenerator.writeStartArray();
            for (Name name : this.aliases) {
                jsonGenerator.writeString(name.getQualified(this.name.space));
            }
            jsonGenerator.writeEndArray();
        }

        @Override // com.flurry.org.apache.avro.Schema
        int computeHash() {
            return super.computeHash() + this.name.hashCode();
        }

        public boolean equalNames(NamedSchema namedSchema) {
            return this.name.equals(namedSchema.name);
        }

        @Override // com.flurry.org.apache.avro.Schema
        public Set getAliases() {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (this.aliases != null) {
                for (Name name : this.aliases) {
                    linkedHashSet.add(name.full);
                }
            }
            return linkedHashSet;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public String getDoc() {
            return this.doc;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public String getFullName() {
            return this.name.full;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public String getName() {
            return this.name.name;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public String getNamespace() {
            return this.name.space;
        }

        public void writeName(Names names, JsonGenerator jsonGenerator) {
            this.name.writeName(names, jsonGenerator);
        }

        public boolean writeNameRef(Names names, JsonGenerator jsonGenerator) {
            if (equals(names.get((Object) this.name))) {
                jsonGenerator.writeString(this.name.getQualified(names.space()));
                return true;
            }
            if (this.name.name != null) {
                names.put(this.name, (Schema) this);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Names extends LinkedHashMap {
        private String space;

        public Names() {
        }

        public Names(String str) {
            this.space = str;
        }

        public void add(Schema schema) {
            put(((NamedSchema) schema).name, schema);
        }

        public boolean contains(Schema schema) {
            return get((Object) ((NamedSchema) schema).name) != null;
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public Schema get(Object obj) {
            Name name;
            if (obj instanceof String) {
                Type type = (Type) Schema.PRIMITIVES.get((String) obj);
                if (type != null) {
                    return Schema.create(type);
                }
                name = new Name((String) obj, this.space);
            } else {
                name = (Name) obj;
            }
            return (Schema) super.get((Object) name);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public Schema put(Name name, Schema schema) {
            if (containsKey(name)) {
                throw new SchemaParseException("Can't redefine: " + name);
            }
            return (Schema) super.put((Names) name, (Name) schema);
        }

        public String space() {
            return this.space;
        }

        public void space(String str) {
            this.space = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class NullSchema extends Schema {
        public NullSchema() {
            super(Type.NULL);
        }
    }

    /* loaded from: classes.dex */
    public class Parser {
        private Names names = new Names();
        private boolean validate = true;

        private Schema parse(JsonParser jsonParser) {
            boolean booleanValue = ((Boolean) Schema.validateNames.get()).booleanValue();
            try {
                try {
                    Schema.validateNames.set(Boolean.valueOf(this.validate));
                    return Schema.parse(Schema.MAPPER.readTree(jsonParser), this.names);
                } catch (JsonParseException e) {
                    throw new SchemaParseException(e);
                }
            } finally {
                Schema.validateNames.set(Boolean.valueOf(booleanValue));
            }
        }

        public Parser addTypes(Map map) {
            for (Schema schema : map.values()) {
                this.names.add(schema);
            }
            return this;
        }

        public Map getTypes() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Schema schema : this.names.values()) {
                linkedHashMap.put(schema.getFullName(), schema);
            }
            return linkedHashMap;
        }

        public boolean getValidate() {
            return this.validate;
        }

        public Schema parse(File file) {
            return parse(Schema.FACTORY.createJsonParser(file));
        }

        public Schema parse(InputStream inputStream) {
            return parse(Schema.FACTORY.createJsonParser(inputStream));
        }

        public Schema parse(String str) {
            try {
                return parse(Schema.FACTORY.createJsonParser(new StringReader(str)));
            } catch (IOException e) {
                throw new SchemaParseException(e);
            }
        }

        public Parser setValidate(boolean z) {
            this.validate = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class Props extends LinkedHashMap {
        private Set reserved;

        public Props(Set set) {
            super(1);
            this.reserved = set;
        }

        public void add(String str, String str2) {
            if (this.reserved.contains(str)) {
                throw new AvroRuntimeException("Can't set reserved property: " + str);
            }
            if (str2 == null) {
                throw new AvroRuntimeException("Can't set a property to null: " + str);
            }
            String str3 = (String) get(str);
            if (str3 == null) {
                put(str, str2);
            } else if (!str3.equals(str2)) {
                throw new AvroRuntimeException("Can't overwrite property: " + str);
            }
        }

        public void write(JsonGenerator jsonGenerator) {
            for (Map.Entry entry : entrySet()) {
                jsonGenerator.writeStringField((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class RecordSchema extends NamedSchema {
        private Map fieldMap;
        private List fields;
        private final boolean isError;

        public RecordSchema(Name name, String str, boolean z) {
            super(Type.RECORD, name, str);
            this.isError = z;
        }

        @Override // com.flurry.org.apache.avro.Schema.NamedSchema, com.flurry.org.apache.avro.Schema
        int computeHash() {
            Map map = (Map) Schema.SEEN_HASHCODE.get();
            if (map.containsKey(this)) {
                return 0;
            }
            boolean isEmpty = map.isEmpty();
            try {
                map.put(this, this);
                return super.computeHash() + this.fields.hashCode();
            } finally {
                if (isEmpty) {
                    map.clear();
                }
            }
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof RecordSchema) {
                RecordSchema recordSchema = (RecordSchema) obj;
                if (equalCachedHash(recordSchema) && equalNames(recordSchema) && this.props.equals(recordSchema.props)) {
                    Set set = (Set) Schema.SEEN_EQUALS.get();
                    SeenPair seenPair = new SeenPair(this, obj);
                    if (set.contains(seenPair)) {
                        return true;
                    }
                    boolean isEmpty = set.isEmpty();
                    try {
                        set.add(seenPair);
                        return this.fields.equals(((RecordSchema) obj).fields);
                    } finally {
                        if (isEmpty) {
                            set.clear();
                        }
                    }
                }
                return false;
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void fieldsToJson(Names names, JsonGenerator jsonGenerator) {
            jsonGenerator.writeStartArray();
            for (Field field : this.fields) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("name", field.name());
                jsonGenerator.writeFieldName("type");
                field.schema().toJson(names, jsonGenerator);
                if (field.doc() != null) {
                    jsonGenerator.writeStringField("doc", field.doc());
                }
                if (field.defaultValue() != null) {
                    jsonGenerator.writeFieldName("default");
                    jsonGenerator.writeTree(field.defaultValue());
                }
                if (field.order() != Field.Order.ASCENDING) {
                    jsonGenerator.writeStringField("order", field.order().name);
                }
                if (field.aliases != null && field.aliases.size() != 0) {
                    jsonGenerator.writeFieldName("aliases");
                    jsonGenerator.writeStartArray();
                    for (String str : field.aliases) {
                        jsonGenerator.writeString(str);
                    }
                    jsonGenerator.writeEndArray();
                }
                field.props.write(jsonGenerator);
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }

        @Override // com.flurry.org.apache.avro.Schema
        public Field getField(String str) {
            if (this.fieldMap == null) {
                throw new AvroRuntimeException("Schema fields not set yet");
            }
            return (Field) this.fieldMap.get(str);
        }

        @Override // com.flurry.org.apache.avro.Schema
        public List getFields() {
            if (this.fields == null) {
                throw new AvroRuntimeException("Schema fields not set yet");
            }
            return this.fields;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean isError() {
            return this.isError;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public void setFields(List list) {
            if (this.fields != null) {
                throw new AvroRuntimeException("Fields are already set");
            }
            this.fieldMap = new HashMap();
            LockableArrayList lockableArrayList = new LockableArrayList();
            Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (field.position != -1) {
                    throw new AvroRuntimeException("Field already used: " + field);
                }
                field.position = i;
                this.fieldMap.put(field.name(), field);
                lockableArrayList.add(field);
                i++;
            }
            this.fields = lockableArrayList.lock();
            this.hashCode = Schema.NO_HASHCODE;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            if (writeNameRef(names, jsonGenerator)) {
                return;
            }
            String str = names.space;
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("type", this.isError ? "error" : "record");
            writeName(names, jsonGenerator);
            names.space = this.name.space;
            if (getDoc() != null) {
                jsonGenerator.writeStringField("doc", getDoc());
            }
            jsonGenerator.writeFieldName("fields");
            fieldsToJson(names, jsonGenerator);
            this.props.write(jsonGenerator);
            aliasesToJson(jsonGenerator);
            jsonGenerator.writeEndObject();
            names.space = str;
        }
    }

    /* loaded from: classes.dex */
    class SeenPair {
        private Object s1;
        private Object s2;

        private SeenPair(Object obj, Object obj2) {
            this.s1 = obj;
            this.s2 = obj2;
        }

        public boolean equals(Object obj) {
            return this.s1 == ((SeenPair) obj).s1 && this.s2 == ((SeenPair) obj).s2;
        }

        public int hashCode() {
            return System.identityHashCode(this.s1) + System.identityHashCode(this.s2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class StringSchema extends Schema {
        public StringSchema() {
            super(Type.STRING);
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        RECORD,
        ENUM,
        ARRAY,
        MAP,
        UNION,
        FIXED,
        STRING,
        BYTES,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        NULL;
        
        private String name = name().toLowerCase();

        Type() {
        }

        public String getName() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class UnionSchema extends Schema {
        private final Map indexByName;
        private final List types;

        public UnionSchema(LockableArrayList lockableArrayList) {
            super(Type.UNION);
            this.indexByName = new HashMap();
            this.types = lockableArrayList.lock();
            int i = 0;
            Iterator it = lockableArrayList.iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return;
                }
                Schema schema = (Schema) it.next();
                if (schema.getType() == Type.UNION) {
                    throw new AvroRuntimeException("Nested union: " + this);
                }
                String fullName = schema.getFullName();
                if (fullName == null) {
                    throw new AvroRuntimeException("Nameless in union:" + this);
                }
                i = i2 + 1;
                if (this.indexByName.put(fullName, Integer.valueOf(i2)) != null) {
                    throw new AvroRuntimeException("Duplicate in union:" + fullName);
                }
            }
        }

        @Override // com.flurry.org.apache.avro.Schema
        public void addProp(String str, String str2) {
            throw new AvroRuntimeException("Can't set properties on a union: " + this);
        }

        @Override // com.flurry.org.apache.avro.Schema
        int computeHash() {
            int computeHash = super.computeHash();
            Iterator it = this.types.iterator();
            while (true) {
                int i = computeHash;
                if (!it.hasNext()) {
                    return i;
                }
                computeHash = ((Schema) it.next()).computeHash() + i;
            }
        }

        @Override // com.flurry.org.apache.avro.Schema
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof UnionSchema) {
                UnionSchema unionSchema = (UnionSchema) obj;
                return equalCachedHash(unionSchema) && this.types.equals(unionSchema.types) && this.props.equals(unionSchema.props);
            }
            return false;
        }

        @Override // com.flurry.org.apache.avro.Schema
        public Integer getIndexNamed(String str) {
            return (Integer) this.indexByName.get(str);
        }

        @Override // com.flurry.org.apache.avro.Schema
        public List getTypes() {
            return this.types;
        }

        @Override // com.flurry.org.apache.avro.Schema
        void toJson(Names names, JsonGenerator jsonGenerator) {
            jsonGenerator.writeStartArray();
            for (Schema schema : this.types) {
                schema.toJson(names, jsonGenerator);
            }
            jsonGenerator.writeEndArray();
        }
    }

    static {
        FACTORY.enable(JsonParser.Feature.ALLOW_COMMENTS);
        FACTORY.setCodec(MAPPER);
        SCHEMA_RESERVED = new HashSet();
        Collections.addAll(SCHEMA_RESERVED, "doc", "fields", "items", "name", "namespace", "size", "symbols", "values", "type");
        FIELD_RESERVED = new HashSet();
        Collections.addAll(FIELD_RESERVED, "default", "doc", "name", "order", "type");
        SEEN_EQUALS = new ThreadLocal() { // from class: com.flurry.org.apache.avro.Schema.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Set initialValue() {
                return new HashSet();
            }
        };
        SEEN_HASHCODE = new ThreadLocal() { // from class: com.flurry.org.apache.avro.Schema.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Map initialValue() {
                return new IdentityHashMap();
            }
        };
        PRIMITIVES = new HashMap();
        PRIMITIVES.put("string", Type.STRING);
        PRIMITIVES.put("bytes", Type.BYTES);
        PRIMITIVES.put("int", Type.INT);
        PRIMITIVES.put("long", Type.LONG);
        PRIMITIVES.put("float", Type.FLOAT);
        PRIMITIVES.put("double", Type.DOUBLE);
        PRIMITIVES.put("boolean", Type.BOOLEAN);
        PRIMITIVES.put(DataFileConstants.NULL_CODEC, Type.NULL);
        validateNames = new ThreadLocal() { // from class: com.flurry.org.apache.avro.Schema.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public Boolean initialValue() {
                return true;
            }
        };
    }

    Schema(Type type) {
        this.type = type;
    }

    public static Schema applyAliases(Schema schema, Schema schema2) {
        if (schema == schema2) {
            return schema;
        }
        IdentityHashMap identityHashMap = new IdentityHashMap(1);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        getAliases(schema2, identityHashMap, hashMap, hashMap2);
        if (hashMap.size() == 0 && hashMap2.size() == 0) {
            return schema;
        }
        identityHashMap.clear();
        return applyAliases(schema, identityHashMap, hashMap, hashMap2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Schema applyAliases(Schema schema, Map map, Map map2, Map map3) {
        Schema createFixed;
        Name name = schema instanceof NamedSchema ? ((NamedSchema) schema).name : null;
        switch (schema.getType()) {
            case RECORD:
                if (!map.containsKey(schema)) {
                    Name name2 = map2.containsKey(name) ? (Name) map2.get(name) : name;
                    Schema createRecord = createRecord(name2.full, schema.getDoc(), null, schema.isError());
                    map.put(schema, createRecord);
                    ArrayList arrayList = new ArrayList();
                    for (Field field : schema.getFields()) {
                        Field field2 = new Field(getFieldAlias(name2, field.name, map3), applyAliases(field.schema, map, map2, map3), field.doc, field.defaultValue, field.order);
                        field2.props.putAll(field.props);
                        arrayList.add(field2);
                    }
                    createRecord.setFields(arrayList);
                    createFixed = createRecord;
                    break;
                } else {
                    return (Schema) map.get(schema);
                }
            case ENUM:
                if (map2.containsKey(name)) {
                    createFixed = createEnum(((Name) map2.get(name)).full, schema.getDoc(), null, schema.getEnumSymbols());
                    break;
                }
                createFixed = schema;
                break;
            case ARRAY:
                Schema applyAliases = applyAliases(schema.getElementType(), map, map2, map3);
                if (applyAliases != schema.getElementType()) {
                    createFixed = createArray(applyAliases);
                    break;
                }
                createFixed = schema;
                break;
            case MAP:
                Schema applyAliases2 = applyAliases(schema.getValueType(), map, map2, map3);
                if (applyAliases2 != schema.getValueType()) {
                    createFixed = createMap(applyAliases2);
                    break;
                }
                createFixed = schema;
                break;
            case UNION:
                ArrayList arrayList2 = new ArrayList();
                for (Schema schema2 : schema.getTypes()) {
                    arrayList2.add(applyAliases(schema2, map, map2, map3));
                }
                createFixed = createUnion(arrayList2);
                break;
            case FIXED:
                if (map2.containsKey(name)) {
                    createFixed = createFixed(((Name) map2.get(name)).full, schema.getDoc(), null, schema.getFixedSize());
                    break;
                }
                createFixed = schema;
                break;
            default:
                createFixed = schema;
                break;
        }
        if (createFixed != schema) {
            createFixed.props.putAll(schema.props);
            return createFixed;
        }
        return createFixed;
    }

    public static Schema create(Type type) {
        switch (type) {
            case STRING:
                return new StringSchema();
            case BYTES:
                return new BytesSchema();
            case INT:
                return new IntSchema();
            case LONG:
                return new LongSchema();
            case FLOAT:
                return new FloatSchema();
            case DOUBLE:
                return new DoubleSchema();
            case BOOLEAN:
                return new BooleanSchema();
            case NULL:
                return new NullSchema();
            default:
                throw new AvroRuntimeException("Can't create a: " + type);
        }
    }

    public static Schema createArray(Schema schema) {
        return new ArraySchema(schema);
    }

    public static Schema createEnum(String str, String str2, String str3, List list) {
        return new EnumSchema(new Name(str, str3), str2, new LockableArrayList(list));
    }

    public static Schema createFixed(String str, String str2, String str3, int i) {
        return new FixedSchema(new Name(str, str3), str2, i);
    }

    public static Schema createMap(Schema schema) {
        return new MapSchema(schema);
    }

    public static Schema createRecord(String str, String str2, String str3, boolean z) {
        return new RecordSchema(new Name(str, str3), str2, z);
    }

    public static Schema createRecord(List list) {
        Schema createRecord = createRecord(null, null, null, false);
        createRecord.setFields(list);
        return createRecord;
    }

    public static Schema createUnion(List list) {
        return new UnionSchema(new LockableArrayList(list));
    }

    private static void getAliases(Schema schema, Map map, Map map2, Map map3) {
        if (schema instanceof NamedSchema) {
            NamedSchema namedSchema = (NamedSchema) schema;
            if (namedSchema.aliases != null) {
                for (Name name : namedSchema.aliases) {
                    map2.put(name, namedSchema.name);
                }
            }
        }
        switch (schema.getType()) {
            case RECORD:
                if (map.containsKey(schema)) {
                    return;
                }
                map.put(schema, schema);
                RecordSchema recordSchema = (RecordSchema) schema;
                for (Field field : schema.getFields()) {
                    if (field.aliases != null) {
                        for (String str : field.aliases) {
                            Map map4 = (Map) map3.get(recordSchema.name);
                            if (map4 == null) {
                                Name name2 = recordSchema.name;
                                map4 = new HashMap();
                                map3.put(name2, map4);
                            }
                            map4.put(str, field.name);
                        }
                    }
                    getAliases(field.schema, map, map2, map3);
                }
                if (recordSchema.aliases == null || !map3.containsKey(recordSchema.name)) {
                    return;
                }
                for (Name name3 : recordSchema.aliases) {
                    map3.put(name3, map3.get(recordSchema.name));
                }
                return;
            case ENUM:
            default:
                return;
            case ARRAY:
                getAliases(schema.getElementType(), map, map2, map3);
                return;
            case MAP:
                getAliases(schema.getValueType(), map, map2, map3);
                return;
            case UNION:
                for (Schema schema2 : schema.getTypes()) {
                    getAliases(schema2, map, map2, map3);
                }
                return;
        }
    }

    private static String getFieldAlias(Name name, String str, Map map) {
        String str2;
        Map map2 = (Map) map.get(name);
        return (map2 == null || (str2 = (String) map2.get(str)) == null) ? str : str2;
    }

    private static String getOptionalText(JsonNode jsonNode, String str) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.getTextValue();
        }
        return null;
    }

    private static String getRequiredText(JsonNode jsonNode, String str, String str2) {
        String optionalText = getOptionalText(jsonNode, str);
        if (optionalText == null) {
            throw new SchemaParseException(str2 + ": " + jsonNode);
        }
        return optionalText;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Schema parse(JsonNode jsonNode, Names names) {
        String str;
        Name name;
        EnumSchema enumSchema;
        Set<String> parseAliases;
        String str2 = null;
        if (jsonNode.isTextual()) {
            Schema schema = names.get((Object) jsonNode.getTextValue());
            if (schema == null) {
                throw new SchemaParseException("Undefined name: " + jsonNode);
            }
            return schema;
        } else if (!jsonNode.isObject()) {
            if (jsonNode.isArray()) {
                LockableArrayList lockableArrayList = new LockableArrayList(jsonNode.size());
                Iterator it = jsonNode.iterator();
                while (it.hasNext()) {
                    lockableArrayList.add(parse((JsonNode) it.next(), names));
                }
                return new UnionSchema(lockableArrayList);
            }
            throw new SchemaParseException("Schema not yet supported: " + jsonNode);
        } else {
            String requiredText = getRequiredText(jsonNode, "type", "No type");
            if (requiredText.equals("record") || requiredText.equals("error") || requiredText.equals("enum") || requiredText.equals("fixed")) {
                String optionalText = getOptionalText(jsonNode, "namespace");
                String optionalText2 = getOptionalText(jsonNode, "doc");
                if (optionalText == null) {
                    optionalText = names.space();
                }
                Name name2 = new Name(getRequiredText(jsonNode, "name", "No name in schema"), optionalText);
                if (name2.space != null) {
                    String space = names.space();
                    names.space(name2.space);
                    str = space;
                    str2 = optionalText2;
                    name = name2;
                } else {
                    str = null;
                    str2 = optionalText2;
                    name = name2;
                }
            } else {
                str = null;
                name = null;
            }
            if (PRIMITIVES.containsKey(requiredText)) {
                enumSchema = create((Type) PRIMITIVES.get(requiredText));
            } else if (requiredText.equals("record") || requiredText.equals("error")) {
                ArrayList arrayList = new ArrayList();
                RecordSchema recordSchema = new RecordSchema(name, str2, requiredText.equals("error"));
                if (name != null) {
                    names.add(recordSchema);
                }
                JsonNode jsonNode2 = jsonNode.get("fields");
                if (jsonNode2 == null || !jsonNode2.isArray()) {
                    throw new SchemaParseException("Record has no fields: " + jsonNode);
                }
                Iterator it2 = jsonNode2.iterator();
                while (it2.hasNext()) {
                    JsonNode jsonNode3 = (JsonNode) it2.next();
                    String requiredText2 = getRequiredText(jsonNode3, "name", "No field name");
                    String optionalText3 = getOptionalText(jsonNode3, "doc");
                    JsonNode jsonNode4 = jsonNode3.get("type");
                    if (jsonNode4 == null) {
                        throw new SchemaParseException("No field type: " + jsonNode3);
                    }
                    if (jsonNode4.isTextual() && names.get((Object) jsonNode4.getTextValue()) == null) {
                        throw new SchemaParseException(jsonNode4 + " is not a defined name. The type of the \"" + requiredText2 + "\" field must be a defined name or a {\"type\": ...} expression.");
                    }
                    Schema parse = parse(jsonNode4, names);
                    Field.Order order = Field.Order.ASCENDING;
                    JsonNode jsonNode5 = jsonNode3.get("order");
                    if (jsonNode5 != null) {
                        order = Field.Order.valueOf(jsonNode5.getTextValue().toUpperCase());
                    }
                    JsonNode jsonNode6 = jsonNode3.get("default");
                    if (jsonNode6 != null && ((Type.FLOAT.equals(parse.getType()) || Type.DOUBLE.equals(parse.getType())) && jsonNode6.isTextual())) {
                        jsonNode6 = new DoubleNode(Double.valueOf(jsonNode6.getTextValue()).doubleValue());
                    }
                    Field field = new Field(requiredText2, parse, optionalText3, jsonNode6, order);
                    Iterator fieldNames = jsonNode3.getFieldNames();
                    while (fieldNames.hasNext()) {
                        String str3 = (String) fieldNames.next();
                        String textValue = jsonNode3.get(str3).getTextValue();
                        if (!FIELD_RESERVED.contains(str3) && textValue != null) {
                            field.addProp(str3, textValue);
                        }
                    }
                    field.aliases = parseAliases(jsonNode3);
                    arrayList.add(field);
                }
                recordSchema.setFields(arrayList);
                enumSchema = recordSchema;
            } else if (requiredText.equals("enum")) {
                JsonNode jsonNode7 = jsonNode.get("symbols");
                if (jsonNode7 == null || !jsonNode7.isArray()) {
                    throw new SchemaParseException("Enum has no symbols: " + jsonNode);
                }
                LockableArrayList lockableArrayList2 = new LockableArrayList();
                Iterator it3 = jsonNode7.iterator();
                while (it3.hasNext()) {
                    lockableArrayList2.add(((JsonNode) it3.next()).getTextValue());
                }
                EnumSchema enumSchema2 = new EnumSchema(name, str2, lockableArrayList2);
                if (name != null) {
                    names.add(enumSchema2);
                }
                enumSchema = enumSchema2;
            } else if (requiredText.equals("array")) {
                JsonNode jsonNode8 = jsonNode.get("items");
                if (jsonNode8 == null) {
                    throw new SchemaParseException("Array has no items type: " + jsonNode);
                }
                enumSchema = new ArraySchema(parse(jsonNode8, names));
            } else if (requiredText.equals("map")) {
                JsonNode jsonNode9 = jsonNode.get("values");
                if (jsonNode9 == null) {
                    throw new SchemaParseException("Map has no values type: " + jsonNode);
                }
                enumSchema = new MapSchema(parse(jsonNode9, names));
            } else if (!requiredText.equals("fixed")) {
                throw new SchemaParseException("Type not supported: " + requiredText);
            } else {
                JsonNode jsonNode10 = jsonNode.get("size");
                if (jsonNode10 == null || !jsonNode10.isInt()) {
                    throw new SchemaParseException("Invalid or no size: " + jsonNode);
                }
                FixedSchema fixedSchema = new FixedSchema(name, str2, jsonNode10.getIntValue());
                if (name != null) {
                    names.add(fixedSchema);
                }
                enumSchema = fixedSchema;
            }
            Iterator fieldNames2 = jsonNode.getFieldNames();
            while (fieldNames2.hasNext()) {
                String str4 = (String) fieldNames2.next();
                String textValue2 = jsonNode.get(str4).getTextValue();
                if (!SCHEMA_RESERVED.contains(str4) && textValue2 != null) {
                    enumSchema.addProp(str4, textValue2);
                }
            }
            if (str != null) {
                names.space(str);
            }
            if (!(enumSchema instanceof NamedSchema) || (parseAliases = parseAliases(jsonNode)) == null) {
                return enumSchema;
            }
            for (String str5 : parseAliases) {
                enumSchema.addAlias(str5);
            }
            return enumSchema;
        }
    }

    public static Schema parse(File file) {
        return new Parser().parse(file);
    }

    public static Schema parse(InputStream inputStream) {
        return new Parser().parse(inputStream);
    }

    public static Schema parse(String str) {
        return new Parser().parse(str);
    }

    public static Schema parse(String str, boolean z) {
        return new Parser().setValidate(z).parse(str);
    }

    private static Set parseAliases(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("aliases");
        if (jsonNode2 == null) {
            return null;
        }
        if (jsonNode2.isArray()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = jsonNode2.iterator();
            while (it.hasNext()) {
                JsonNode jsonNode3 = (JsonNode) it.next();
                if (!jsonNode3.isTextual()) {
                    throw new SchemaParseException("alias not a string: " + jsonNode3);
                }
                linkedHashSet.add(jsonNode3.getTextValue());
            }
            return linkedHashSet;
        }
        throw new SchemaParseException("aliases not an array: " + jsonNode);
    }

    static JsonNode parseJson(String str) {
        try {
            return MAPPER.readTree(FACTORY.createJsonParser(new StringReader(str)));
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String validateName(String str) {
        if (((Boolean) validateNames.get()).booleanValue()) {
            int length = str.length();
            if (length == 0) {
                throw new SchemaParseException("Empty name");
            }
            char charAt = str.charAt(0);
            if (!Character.isLetter(charAt) && charAt != '_') {
                throw new SchemaParseException("Illegal initial character: " + str);
            }
            for (int i = 1; i < length; i++) {
                char charAt2 = str.charAt(i);
                if (!Character.isLetterOrDigit(charAt2) && charAt2 != '_') {
                    throw new SchemaParseException("Illegal character in: " + str);
                }
            }
        }
        return str;
    }

    public void addAlias(String str) {
        throw new AvroRuntimeException("Not a named type: " + this);
    }

    public synchronized void addProp(String str, String str2) {
        this.props.add(str, str2);
        this.hashCode = NO_HASHCODE;
    }

    int computeHash() {
        return getType().hashCode() + this.props.hashCode();
    }

    final boolean equalCachedHash(Schema schema) {
        return this.hashCode == schema.hashCode || this.hashCode == NO_HASHCODE || schema.hashCode == NO_HASHCODE;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Schema) {
            Schema schema = (Schema) obj;
            if (this.type != schema.type) {
                return false;
            }
            return equalCachedHash(schema) && this.props.equals(schema.props);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fieldsToJson(Names names, JsonGenerator jsonGenerator) {
        throw new AvroRuntimeException("Not a record: " + this);
    }

    public Set getAliases() {
        throw new AvroRuntimeException("Not a named type: " + this);
    }

    public String getDoc() {
        return null;
    }

    public Schema getElementType() {
        throw new AvroRuntimeException("Not an array: " + this);
    }

    public int getEnumOrdinal(String str) {
        throw new AvroRuntimeException("Not an enum: " + this);
    }

    public List getEnumSymbols() {
        throw new AvroRuntimeException("Not an enum: " + this);
    }

    public Field getField(String str) {
        throw new AvroRuntimeException("Not a record: " + this);
    }

    public List getFields() {
        throw new AvroRuntimeException("Not a record: " + this);
    }

    public int getFixedSize() {
        throw new AvroRuntimeException("Not fixed: " + this);
    }

    public String getFullName() {
        return getName();
    }

    public Integer getIndexNamed(String str) {
        throw new AvroRuntimeException("Not a union: " + this);
    }

    public String getName() {
        return this.type.name;
    }

    public String getNamespace() {
        throw new AvroRuntimeException("Not a named type: " + this);
    }

    public synchronized String getProp(String str) {
        return (String) this.props.get(str);
    }

    public Map getProps() {
        return Collections.unmodifiableMap(this.props);
    }

    public Type getType() {
        return this.type;
    }

    public List getTypes() {
        throw new AvroRuntimeException("Not a union: " + this);
    }

    public Schema getValueType() {
        throw new AvroRuntimeException("Not a map: " + this);
    }

    public boolean hasEnumSymbol(String str) {
        throw new AvroRuntimeException("Not an enum: " + this);
    }

    public final int hashCode() {
        if (this.hashCode == NO_HASHCODE) {
            this.hashCode = computeHash();
        }
        return this.hashCode;
    }

    public boolean isError() {
        throw new AvroRuntimeException("Not a record: " + this);
    }

    public void setFields(List list) {
        throw new AvroRuntimeException("Not a record: " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toJson(Names names, JsonGenerator jsonGenerator) {
        if (this.props.size() == 0) {
            jsonGenerator.writeString(getName());
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", getName());
        this.props.write(jsonGenerator);
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean z) {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonGenerator createJsonGenerator = FACTORY.createJsonGenerator(stringWriter);
            if (z) {
                createJsonGenerator.useDefaultPrettyPrinter();
            }
            toJson(new Names(), createJsonGenerator);
            createJsonGenerator.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }
}
