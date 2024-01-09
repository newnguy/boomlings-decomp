package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* loaded from: classes.dex */
public abstract class StdKeyDeserializer extends KeyDeserializer {
    protected final Class _keyClass;

    /* loaded from: classes.dex */
    final class BoolKD extends StdKeyDeserializer {
        public BoolKD() {
            super(Boolean.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Boolean _parse(String str, DeserializationContext deserializationContext) {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    /* loaded from: classes.dex */
    final class ByteKD extends StdKeyDeserializer {
        public ByteKD() {
            super(Byte.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Byte _parse(String str, DeserializationContext deserializationContext) {
            int _parseInt = _parseInt(str);
            if (_parseInt < -128 || _parseInt > 127) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
            }
            return Byte.valueOf((byte) _parseInt);
        }
    }

    /* loaded from: classes.dex */
    final class CalendarKD extends StdKeyDeserializer {
        public CalendarKD() {
            super(Calendar.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Calendar _parse(String str, DeserializationContext deserializationContext) {
            Date parseDate = deserializationContext.parseDate(str);
            if (parseDate == null) {
                return null;
            }
            return deserializationContext.constructCalendar(parseDate);
        }
    }

    /* loaded from: classes.dex */
    final class CharKD extends StdKeyDeserializer {
        public CharKD() {
            super(Character.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Character _parse(String str, DeserializationContext deserializationContext) {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    /* loaded from: classes.dex */
    final class DateKD extends StdKeyDeserializer {
        public DateKD() {
            super(Date.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Date _parse(String str, DeserializationContext deserializationContext) {
            return deserializationContext.parseDate(str);
        }
    }

    /* loaded from: classes.dex */
    final class DoubleKD extends StdKeyDeserializer {
        public DoubleKD() {
            super(Double.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Double _parse(String str, DeserializationContext deserializationContext) {
            return Double.valueOf(_parseDouble(str));
        }
    }

    /* loaded from: classes.dex */
    final class EnumKD extends StdKeyDeserializer {
        protected final AnnotatedMethod _factory;
        protected final EnumResolver _resolver;

        public EnumKD(EnumResolver enumResolver, AnnotatedMethod annotatedMethod) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
            this._factory = annotatedMethod;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) {
            if (this._factory != null) {
                try {
                    return this._factory.call1(str);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            Enum findEnum = this._resolver.findEnum(str);
            if (findEnum == null) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
            }
            return findEnum;
        }
    }

    /* loaded from: classes.dex */
    final class FloatKD extends StdKeyDeserializer {
        public FloatKD() {
            super(Float.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Float _parse(String str, DeserializationContext deserializationContext) {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    /* loaded from: classes.dex */
    final class IntKD extends StdKeyDeserializer {
        public IntKD() {
            super(Integer.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Integer _parse(String str, DeserializationContext deserializationContext) {
            return Integer.valueOf(_parseInt(str));
        }
    }

    /* loaded from: classes.dex */
    final class LongKD extends StdKeyDeserializer {
        public LongKD() {
            super(Long.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Long _parse(String str, DeserializationContext deserializationContext) {
            return Long.valueOf(_parseLong(str));
        }
    }

    /* loaded from: classes.dex */
    final class ShortKD extends StdKeyDeserializer {
        public ShortKD() {
            super(Integer.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Short _parse(String str, DeserializationContext deserializationContext) {
            int _parseInt = _parseInt(str);
            if (_parseInt < -32768 || _parseInt > 32767) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
            }
            return Short.valueOf((short) _parseInt);
        }
    }

    /* loaded from: classes.dex */
    final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        protected final Constructor _ctor;

        public StringCtorKeyDeserializer(Constructor constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._ctor.newInstance(str);
        }
    }

    /* loaded from: classes.dex */
    final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._factoryMethod.invoke(null, str);
        }
    }

    /* loaded from: classes.dex */
    final class StringKD extends StdKeyDeserializer {
        private static final StringKD sString = new StringKD(String.class);
        private static final StringKD sObject = new StringKD(Object.class);

        private StringKD(Class cls) {
            super(cls);
        }

        public static StringKD forType(Class cls) {
            return cls == String.class ? sString : cls == Object.class ? sObject : new StringKD(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public String _parse(String str, DeserializationContext deserializationContext) {
            return str;
        }
    }

    /* loaded from: classes.dex */
    final class UuidKD extends StdKeyDeserializer {
        public UuidKD() {
            super(UUID.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public UUID _parse(String str, DeserializationContext deserializationContext) {
            return UUID.fromString(str);
        }
    }

    public StdKeyDeserializer(Class cls) {
        this._keyClass = cls;
    }

    protected abstract Object _parse(String str, DeserializationContext deserializationContext);

    protected double _parseDouble(String str) {
        return NumberInput.parseDouble(str);
    }

    protected int _parseInt(String str) {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) {
        return Long.parseLong(str);
    }

    @Override // com.flurry.org.codehaus.jackson.map.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) {
        if (str == null) {
            return null;
        }
        try {
            Object _parse = _parse(str, deserializationContext);
            if (_parse == null) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
            }
            return _parse;
        } catch (Exception e) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class getKeyClass() {
        return this._keyClass;
    }
}
