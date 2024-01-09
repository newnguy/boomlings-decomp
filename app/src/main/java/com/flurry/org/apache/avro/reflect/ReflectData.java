package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.AvroRemoteException;
import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Protocol;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericContainer;
import com.flurry.org.apache.avro.generic.GenericFixed;
import com.flurry.org.apache.avro.generic.IndexedRecord;
import com.flurry.org.apache.avro.io.BinaryData;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.specific.FixedSize;
import com.flurry.org.apache.avro.specific.SpecificData;
import com.flurry.org.codehaus.jackson.node.NullNode;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ReflectData extends SpecificData {
    static final String CLASS_PROP = "java-class";
    static final String ELEMENT_PROP = "java-element-class";
    private final Paranamer paranamer = new CachingParanamer();
    private static final ReflectData INSTANCE = new ReflectData();
    private static final Map FIELD_CACHE = new ConcurrentHashMap();
    private static final Class BYTES_CLASS = new byte[0].getClass();
    private static final Schema THROWABLE_MESSAGE = makeNullable(Schema.create(Schema.Type.STRING));

    /* loaded from: classes.dex */
    public class AllowNull extends ReflectData {
        private static final AllowNull INSTANCE = new AllowNull();

        public static AllowNull get() {
            return INSTANCE;
        }

        @Override // com.flurry.org.apache.avro.reflect.ReflectData
        protected Schema createFieldSchema(Field field, Map map) {
            return makeNullable(super.createFieldSchema(field, map));
        }
    }

    protected ReflectData() {
    }

    private static Field findField(Class cls, String str) {
        Class cls2 = cls;
        do {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException e) {
                cls2 = cls2.getSuperclass();
                if (cls2 == null) {
                    throw new AvroRuntimeException("No field named " + str + " in: " + cls);
                }
            }
        } while (cls2 == null);
        throw new AvroRuntimeException("No field named " + str + " in: " + cls);
    }

    public static ReflectData get() {
        return INSTANCE;
    }

    private Schema getAnnotatedUnion(Union union, Map map) {
        ArrayList arrayList = new ArrayList();
        for (Class cls : union.value()) {
            arrayList.add(createSchema(cls, map));
        }
        return Schema.createUnion(arrayList);
    }

    public static Class getClassProp(Schema schema, String str) {
        String prop = schema.getProp(str);
        if (prop == null) {
            return null;
        }
        try {
            return Class.forName(prop);
        } catch (ClassNotFoundException e) {
            throw new AvroRuntimeException(e);
        }
    }

    private static Field getField(Class cls, String str) {
        ConcurrentHashMap concurrentHashMap;
        Map map = (Map) FIELD_CACHE.get(cls);
        if (map == null) {
            ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
            FIELD_CACHE.put(cls, concurrentHashMap2);
            concurrentHashMap = concurrentHashMap2;
        } else {
            concurrentHashMap = map;
        }
        Field field = (Field) concurrentHashMap.get(str);
        if (field == null) {
            Field findField = findField(cls, str);
            concurrentHashMap.put(str, findField);
            return findField;
        }
        return field;
    }

    private Collection getFields(Class cls) {
        Field[] declaredFields;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        do {
            if (cls.getPackage() != null && cls.getPackage().getName().startsWith("java.")) {
                break;
            }
            for (Field field : cls.getDeclaredFields()) {
                if ((field.getModifiers() & 136) == 0 && linkedHashMap.put(field.getName(), field) != null) {
                    throw new AvroTypeException(cls + " contains two fields named: " + field);
                }
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        return linkedHashMap.values();
    }

    private Protocol.Message getMessage(Method method, Protocol protocol, Map map) {
        Type[] genericExceptionTypes;
        ArrayList arrayList = new ArrayList();
        String[] lookupParameterNames = this.paranamer.lookupParameterNames(method);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < genericParameterTypes.length; i++) {
            Schema schema = getSchema(genericParameterTypes[i], map);
            int i2 = 0;
            while (i2 < parameterAnnotations[i].length) {
                Schema annotatedUnion = parameterAnnotations[i][i2] instanceof Union ? getAnnotatedUnion((Union) parameterAnnotations[i][i2], map) : parameterAnnotations[i][i2] instanceof Nullable ? makeNullable(schema) : schema;
                i2++;
                schema = annotatedUnion;
            }
            arrayList.add(new Schema.Field(lookupParameterNames.length == genericParameterTypes.length ? lookupParameterNames[i] : schema.getName() + i, schema, null, null));
        }
        Schema createRecord = Schema.createRecord(arrayList);
        Union union = (Union) method.getAnnotation(Union.class);
        Schema schema2 = union == null ? getSchema(method.getGenericReturnType(), map) : getAnnotatedUnion(union, map);
        Schema makeNullable = method.isAnnotationPresent(Nullable.class) ? makeNullable(schema2) : schema2;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Protocol.SYSTEM_ERROR);
        for (Type type : method.getGenericExceptionTypes()) {
            if (type != AvroRemoteException.class) {
                arrayList2.add(getSchema(type, map));
            }
        }
        return protocol.createMessage(method.getName(), null, createRecord, makeNullable, Schema.createUnion(arrayList2));
    }

    private Schema getSchema(Type type, Map map) {
        try {
            return createSchema(type, map);
        } catch (AvroTypeException e) {
            throw new AvroTypeException("Error getting schema for " + type + ": " + e.getMessage(), e);
        }
    }

    public static Schema makeNullable(Schema schema) {
        return Schema.createUnion(Arrays.asList(Schema.create(Schema.Type.NULL), schema));
    }

    private void setElement(Schema schema, Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (((Union) cls.getAnnotation(Union.class)) != null) {
                schema.addProp(ELEMENT_PROP, cls.getName());
            }
        }
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificData, com.flurry.org.apache.avro.generic.GenericData
    public int compare(Object obj, Object obj2, Schema schema, boolean z) {
        switch (schema.getType()) {
            case ARRAY:
                if (obj.getClass().isArray()) {
                    Schema elementType = schema.getElementType();
                    int length = Array.getLength(obj);
                    int length2 = Array.getLength(obj2);
                    int min = Math.min(length, length2);
                    for (int i = 0; i < min; i++) {
                        int compare = compare(Array.get(obj, i), Array.get(obj2, i), elementType, z);
                        if (compare != 0) {
                            return compare;
                        }
                    }
                    return length - length2;
                }
                break;
            case BYTES:
                if (obj.getClass().isArray()) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = (byte[]) obj2;
                    return BinaryData.compareBytes(bArr, 0, bArr.length, bArr2, 0, bArr2.length);
                }
                break;
        }
        return super.compare(obj, obj2, schema, z);
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificData, com.flurry.org.apache.avro.generic.GenericData
    public DatumReader createDatumReader(Schema schema) {
        return new ReflectDatumReader(schema, schema, this);
    }

    protected Schema createFieldSchema(Field field, Map map) {
        Schema createSchema = createSchema(field.getGenericType(), map);
        return field.isAnnotationPresent(Nullable.class) ? makeNullable(createSchema) : createSchema;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificData
    public Schema createSchema(Type type, Map map) {
        Schema schema;
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType == Byte.TYPE) {
                return Schema.create(Schema.Type.BYTES);
            }
            Schema createArray = Schema.createArray(createSchema(genericComponentType, map));
            setElement(createArray, genericComponentType);
            return createArray;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class cls = (Class) parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (Map.class.isAssignableFrom(cls)) {
                Type type2 = actualTypeArguments[0];
                Type type3 = actualTypeArguments[1];
                if (type2 != String.class) {
                    throw new AvroTypeException("Map key class not String: " + type2);
                }
                return Schema.createMap(createSchema(type3, map));
            } else if (Collection.class.isAssignableFrom(cls)) {
                if (actualTypeArguments.length != 1) {
                    throw new AvroTypeException("No array type specified.");
                }
                Schema createArray2 = Schema.createArray(createSchema(actualTypeArguments[0], map));
                createArray2.addProp(CLASS_PROP, cls.getName());
                return createArray2;
            }
        } else if (type == Byte.class || type == Byte.TYPE) {
            Schema create = Schema.create(Schema.Type.INT);
            create.addProp(CLASS_PROP, Byte.class.getName());
            return create;
        } else if (type == Short.class || type == Short.TYPE) {
            Schema create2 = Schema.create(Schema.Type.INT);
            create2.addProp(CLASS_PROP, Short.class.getName());
            return create2;
        } else if (type instanceof Class) {
            Class cls2 = (Class) type;
            if (cls2.isPrimitive() || Number.class.isAssignableFrom(cls2) || cls2 == Void.class || cls2 == Boolean.class) {
                return super.createSchema(type, map);
            }
            if (cls2.isArray()) {
                Class<?> componentType = cls2.getComponentType();
                if (componentType == Byte.TYPE) {
                    return Schema.create(Schema.Type.BYTES);
                }
                Schema createArray3 = Schema.createArray(createSchema(componentType, map));
                setElement(createArray3, componentType);
                return createArray3;
            } else if (CharSequence.class.isAssignableFrom(cls2)) {
                return Schema.create(Schema.Type.STRING);
            } else {
                if (ByteBuffer.class.isAssignableFrom(cls2)) {
                    return Schema.create(Schema.Type.BYTES);
                }
                if (Collection.class.isAssignableFrom(cls2)) {
                    throw new AvroRuntimeException("Can't find element type of Collection");
                }
                String name = cls2.getName();
                Schema schema2 = (Schema) map.get(name);
                if (schema2 == null) {
                    String simpleName = cls2.getSimpleName();
                    String name2 = cls2.getEnclosingClass() != null ? cls2.getEnclosingClass().getName() + "$" : cls2.getPackage() == null ? "" : cls2.getPackage().getName();
                    Union union = (Union) cls2.getAnnotation(Union.class);
                    if (union != null) {
                        return getAnnotatedUnion(union, map);
                    }
                    if (cls2.isAnnotationPresent(Stringable.class)) {
                        Schema create3 = Schema.create(Schema.Type.STRING);
                        create3.addProp(CLASS_PROP, cls2.getName());
                        return create3;
                    }
                    if (cls2.isEnum()) {
                        ArrayList arrayList = new ArrayList();
                        for (Enum r3 : (Enum[]) cls2.getEnumConstants()) {
                            arrayList.add(r3.name());
                        }
                        schema = Schema.createEnum(simpleName, null, name2, arrayList);
                    } else if (GenericFixed.class.isAssignableFrom(cls2)) {
                        schema = Schema.createFixed(simpleName, null, name2, ((FixedSize) cls2.getAnnotation(FixedSize.class)).value());
                    } else if (IndexedRecord.class.isAssignableFrom(cls2)) {
                        return super.createSchema(type, map);
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        boolean isAssignableFrom = Throwable.class.isAssignableFrom(cls2);
                        Schema createRecord = Schema.createRecord(simpleName, null, name2, isAssignableFrom);
                        map.put(cls2.getName(), createRecord);
                        for (Field field : getFields(cls2)) {
                            if ((field.getModifiers() & 136) == 0) {
                                Schema createFieldSchema = createFieldSchema(field, map);
                                arrayList2.add(new Schema.Field(field.getName(), createFieldSchema, null, (createFieldSchema.getType() == Schema.Type.UNION && ((Schema) createFieldSchema.getTypes().get(0)).getType() == Schema.Type.NULL) ? NullNode.getInstance() : null));
                            }
                        }
                        if (isAssignableFrom) {
                            arrayList2.add(new Schema.Field("detailMessage", THROWABLE_MESSAGE, null, null));
                        }
                        createRecord.setFields(arrayList2);
                        schema = createRecord;
                    }
                    map.put(name, schema);
                    return schema;
                }
                return schema2;
            }
        }
        return super.createSchema(type, map);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.flurry.org.apache.avro.specific.SpecificData
    public Class getClass(Schema schema) {
        switch (schema.getType()) {
            case ARRAY:
                Class classProp = getClassProp(schema, CLASS_PROP);
                return classProp == null ? Array.newInstance(getClass(schema.getElementType()), 0).getClass() : classProp;
            case STRING:
                return String.class;
            case BYTES:
                return BYTES_CLASS;
            case INT:
                String prop = schema.getProp(CLASS_PROP);
                if (Byte.class.getName().equals(prop)) {
                    return Byte.TYPE;
                }
                if (Short.class.getName().equals(prop)) {
                    return Short.TYPE;
                }
                break;
        }
        return super.getClass(schema);
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public Object getField(Object obj, String str, int i) {
        if (obj instanceof IndexedRecord) {
            return super.getField(obj, str, i);
        }
        try {
            return getField(obj.getClass(), str).get(obj);
        } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
        }
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificData
    public Protocol getProtocol(Class cls) {
        Method[] methods;
        Protocol protocol = new Protocol(cls.getSimpleName(), cls.getPackage() == null ? "" : cls.getPackage().getName());
        Map linkedHashMap = new LinkedHashMap();
        Map messages = protocol.getMessages();
        for (Method method : cls.getMethods()) {
            if ((method.getModifiers() & 8) == 0) {
                String name = method.getName();
                if (messages.containsKey(name)) {
                    throw new AvroTypeException("Two methods with same name: " + name);
                }
                messages.put(name, getMessage(method, protocol, linkedHashMap));
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(linkedHashMap.values());
        Collections.reverse(arrayList);
        protocol.setTypes(arrayList);
        return protocol;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public Schema getRecordSchema(Object obj) {
        return obj instanceof GenericContainer ? super.getRecordSchema(obj) : getSchema(obj.getClass());
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    protected boolean isArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return (obj instanceof Collection) || obj.getClass().isArray();
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public boolean isBytes(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.isBytes(obj)) {
            return true;
        }
        Class<?> cls = obj.getClass();
        return cls.isArray() && cls.getComponentType() == Byte.TYPE;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public boolean isRecord(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.isRecord(obj)) {
            return true;
        }
        if (obj instanceof Collection) {
            return false;
        }
        return getSchema(obj.getClass()).getType() == Schema.Type.RECORD;
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public void setField(Object obj, String str, int i, Object obj2) {
        if (obj instanceof IndexedRecord) {
            super.setField(obj, str, i, obj2);
            return;
        }
        try {
            getField(obj.getClass(), str).set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new AvroRuntimeException(e);
        }
    }

    @Override // com.flurry.org.apache.avro.generic.GenericData
    public boolean validate(Schema schema, Object obj) {
        switch (schema.getType()) {
            case ARRAY:
                if (obj.getClass().isArray()) {
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        if (!validate(schema.getElementType(), Array.get(obj, i))) {
                            return false;
                        }
                    }
                    return true;
                }
                return super.validate(schema, obj);
            default:
                return super.validate(schema, obj);
        }
    }
}
