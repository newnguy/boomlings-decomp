package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Protocol;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericData;
import com.flurry.org.apache.avro.io.DatumReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class SpecificData extends GenericData {
    private Map classCache;
    private final ClassLoader classLoader;
    private final WeakHashMap schemaCache;
    private static final SpecificData INSTANCE = new SpecificData();
    private static final Class[] NO_ARG = new Class[0];
    private static final Class[] SCHEMA_ARG = {Schema.class};
    private static final Map CTOR_CACHE = new ConcurrentHashMap();
    private static final Class NO_CLASS = new Object() { // from class: com.flurry.org.apache.avro.specific.SpecificData.1
    }.getClass();
    private static final Schema NULL_SCHEMA = Schema.create(Schema.Type.NULL);

    /* loaded from: classes.dex */
    public interface SchemaConstructable {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SpecificData() {
        this(SpecificData.class.getClassLoader());
    }

    public SpecificData(ClassLoader classLoader) {
        this.classCache = new ConcurrentHashMap();
        this.schemaCache = new WeakHashMap();
        this.classLoader = classLoader;
    }

    public static SpecificData get() {
        return INSTANCE;
    }

    public static String getClassName(Schema schema) {
        String namespace = schema.getNamespace();
        String name = schema.getName();
        if (namespace == null || "".equals(namespace)) {
            return name;
        }
        return namespace + (namespace.endsWith("$") ? "" : ".") + name;
    }

    public static Object newInstance(Class cls, Schema schema) {
        boolean isAssignableFrom = SchemaConstructable.class.isAssignableFrom(cls);
        try {
            Constructor constructor = (Constructor) CTOR_CACHE.get(cls);
            if (constructor == null) {
                constructor = cls.getDeclaredConstructor(isAssignableFrom ? SCHEMA_ARG : NO_ARG);
                constructor.setAccessible(true);
                CTOR_CACHE.put(cls, constructor);
            }
            return constructor.newInstance(isAssignableFrom ? new Object[]{schema} : null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.apache.avro.generic.GenericData
    public int compare(Object obj, Object obj2, Schema schema, boolean z) {
        switch (schema.getType()) {
            case ENUM:
                if (obj instanceof Enum) {
                    return ((Enum) obj).ordinal() - ((Enum) obj2).ordinal();
                }
                break;
        }
        return super.compare(obj, obj2, schema, z);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public DatumReader createDatumReader(Schema schema) {
        return new SpecificDatumReader(schema, schema, this);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public Object createFixed(Object obj, Schema schema) {
        Class cls = get().getClass(schema);
        return cls == null ? super.createFixed(obj, schema) : !cls.isInstance(obj) ? newInstance(cls, schema) : obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Schema createSchema(Type type, Map map) {
        if ((type instanceof Class) && CharSequence.class.isAssignableFrom((Class) type)) {
            return Schema.create(Schema.Type.STRING);
        }
        if (type == ByteBuffer.class) {
            return Schema.create(Schema.Type.BYTES);
        }
        if (type == Integer.class || type == Integer.TYPE) {
            return Schema.create(Schema.Type.INT);
        }
        if (type == Long.class || type == Long.TYPE) {
            return Schema.create(Schema.Type.LONG);
        }
        if (type == Float.class || type == Float.TYPE) {
            return Schema.create(Schema.Type.FLOAT);
        }
        if (type == Double.class || type == Double.TYPE) {
            return Schema.create(Schema.Type.DOUBLE);
        }
        if (type == Boolean.class || type == Boolean.TYPE) {
            return Schema.create(Schema.Type.BOOLEAN);
        }
        if (type == Void.class || type == Void.TYPE) {
            return Schema.create(Schema.Type.NULL);
        }
        if (!(type instanceof ParameterizedType)) {
            if (type instanceof Class) {
                Class cls = (Class) type;
                String name = cls.getName();
                Schema schema = (Schema) map.get(name);
                if (schema == null) {
                    try {
                        schema = (Schema) cls.getDeclaredField("SCHEMA$").get(null);
                        if (!name.equals(getClassName(schema))) {
                            schema = Schema.parse(schema.toString().replace(schema.getNamespace(), cls.getPackage().getName()));
                        }
                    } catch (IllegalAccessException e) {
                        throw new AvroRuntimeException(e);
                    } catch (NoSuchFieldException e2) {
                        throw new AvroRuntimeException("Not a Specific class: " + cls);
                    }
                }
                map.put(name, schema);
                return schema;
            }
            throw new AvroTypeException("Unknown type: " + type);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class cls2 = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (Collection.class.isAssignableFrom(cls2)) {
            if (actualTypeArguments.length != 1) {
                throw new AvroTypeException("No array type specified.");
            }
            return Schema.createArray(createSchema(actualTypeArguments[0], map));
        } else if (Map.class.isAssignableFrom(cls2)) {
            Type type2 = actualTypeArguments[0];
            Type type3 = actualTypeArguments[1];
            if ((type instanceof Class) && CharSequence.class.isAssignableFrom((Class) type)) {
                return Schema.createMap(createSchema(type3, map));
            }
            throw new AvroTypeException("Map key class not CharSequence: " + type2);
        } else {
            return createSchema(cls2, map);
        }
    }

    public Class getClass(Schema schema) {
        switch (schema.getType()) {
            case FIXED:
            case RECORD:
            case ENUM:
                String fullName = schema.getFullName();
                if (fullName == null) {
                    return null;
                }
                Class<?> cls = (Class) this.classCache.get(fullName);
                if (cls == null) {
                    try {
                        cls = this.classLoader.loadClass(getClassName(schema));
                    } catch (ClassNotFoundException e) {
                        cls = NO_CLASS;
                    }
                    this.classCache.put(fullName, cls);
                }
                if (cls == NO_CLASS) {
                    cls = null;
                }
                return cls;
            case ARRAY:
                return List.class;
            case MAP:
                return Map.class;
            case UNION:
                List types = schema.getTypes();
                if (types.size() == 2 && types.contains(NULL_SCHEMA)) {
                    return getClass((Schema) types.get(((Schema) types.get(0)).equals(NULL_SCHEMA) ? 1 : 0));
                }
                return Object.class;
            case STRING:
                return "String".equals(schema.getProp("avro.java.string")) ? String.class : CharSequence.class;
            case BYTES:
                return ByteBuffer.class;
            case INT:
                return Integer.TYPE;
            case LONG:
                return Long.TYPE;
            case FLOAT:
                return Float.TYPE;
            case DOUBLE:
                return Double.TYPE;
            case BOOLEAN:
                return Boolean.TYPE;
            case NULL:
                return Void.TYPE;
            default:
                throw new AvroRuntimeException("Unknown type: " + schema);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.apache.avro.generic.GenericData
    public Schema getEnumSchema(Object obj) {
        return obj instanceof Enum ? getSchema(obj.getClass()) : super.getEnumSchema(obj);
    }

    public Protocol getProtocol(Class cls) {
        try {
            Protocol protocol = (Protocol) cls.getDeclaredField("PROTOCOL").get(null);
            return !protocol.getNamespace().equals(cls.getPackage().getName()) ? Protocol.parse(protocol.toString().replace(protocol.getNamespace(), cls.getPackage().getName())) : protocol;
        } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
        } catch (NoSuchFieldException e2) {
            throw new AvroRuntimeException("Not a Specific protocol: " + cls);
        }
    }

    public Schema getSchema(Type type) {
        Schema schema = (Schema) this.schemaCache.get(type);
        if (schema == null) {
            Schema createSchema = createSchema(type, new LinkedHashMap());
            this.schemaCache.put(type, createSchema);
            return createSchema;
        }
        return schema;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flurry.org.apache.avro.generic.GenericData
    public boolean isEnum(Object obj) {
        return (obj instanceof Enum) || super.isEnum(obj);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public Object newRecord(Object obj, Schema schema) {
        Class cls = get().getClass(schema);
        return cls == null ? super.newRecord(obj, schema) : !cls.isInstance(obj) ? newInstance(cls, schema) : obj;
    }
}
