package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public abstract class FromStringDeserializer extends StdScalarDeserializer {

    /* loaded from: classes.dex */
    public class CharsetDeserializer extends FromStringDeserializer {
        public CharsetDeserializer() {
            super(Charset.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Charset _deserialize(String str, DeserializationContext deserializationContext) {
            return Charset.forName(str);
        }
    }

    /* loaded from: classes.dex */
    public class CurrencyDeserializer extends FromStringDeserializer {
        public CurrencyDeserializer() {
            super(Currency.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Currency _deserialize(String str, DeserializationContext deserializationContext) {
            return Currency.getInstance(str);
        }
    }

    /* loaded from: classes.dex */
    public class InetAddressDeserializer extends FromStringDeserializer {
        public InetAddressDeserializer() {
            super(InetAddress.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public InetAddress _deserialize(String str, DeserializationContext deserializationContext) {
            return InetAddress.getByName(str);
        }
    }

    /* loaded from: classes.dex */
    public class LocaleDeserializer extends FromStringDeserializer {
        public LocaleDeserializer() {
            super(Locale.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Locale _deserialize(String str, DeserializationContext deserializationContext) {
            int indexOf = str.indexOf(95);
            if (indexOf < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(95);
            return indexOf2 < 0 ? new Locale(substring, substring2) : new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
        }
    }

    /* loaded from: classes.dex */
    public class PatternDeserializer extends FromStringDeserializer {
        public PatternDeserializer() {
            super(Pattern.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Pattern _deserialize(String str, DeserializationContext deserializationContext) {
            return Pattern.compile(str);
        }
    }

    /* loaded from: classes.dex */
    public class TimeZoneDeserializer extends FromStringDeserializer {
        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public TimeZone _deserialize(String str, DeserializationContext deserializationContext) {
            return TimeZone.getTimeZone(str);
        }
    }

    /* loaded from: classes.dex */
    public class URIDeserializer extends FromStringDeserializer {
        public URIDeserializer() {
            super(URI.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public URI _deserialize(String str, DeserializationContext deserializationContext) {
            return URI.create(str);
        }
    }

    /* loaded from: classes.dex */
    public class URLDeserializer extends FromStringDeserializer {
        public URLDeserializer() {
            super(URL.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public URL _deserialize(String str, DeserializationContext deserializationContext) {
            return new URL(str);
        }
    }

    /* loaded from: classes.dex */
    public class UUIDDeserializer extends FromStringDeserializer {
        public UUIDDeserializer() {
            super(UUID.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public UUID _deserialize(String str, DeserializationContext deserializationContext) {
            return UUID.fromString(str);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) {
            if (!(obj instanceof byte[])) {
                super._deserializeEmbedded(obj, deserializationContext);
                return null;
            }
            byte[] bArr = (byte[]) obj;
            if (bArr.length != 16) {
                deserializationContext.mappingException("Can only construct UUIDs from 16 byte arrays; got " + bArr.length + " bytes");
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
        }
    }

    public FromStringDeserializer(Class cls) {
        super(cls);
    }

    public static Iterable all() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UUIDDeserializer());
        arrayList.add(new URLDeserializer());
        arrayList.add(new URIDeserializer());
        arrayList.add(new CurrencyDeserializer());
        arrayList.add(new PatternDeserializer());
        arrayList.add(new LocaleDeserializer());
        arrayList.add(new InetAddressDeserializer());
        arrayList.add(new TimeZoneDeserializer());
        arrayList.add(new CharsetDeserializer());
        return arrayList;
    }

    protected abstract Object _deserialize(String str, DeserializationContext deserializationContext);

    protected Object _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) {
        throw deserializationContext.mappingException("Don't know how to convert embedded Object of type " + obj.getClass().getName() + " into " + this._valueClass.getName());
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject != null) {
                    return this._valueClass.isAssignableFrom(embeddedObject.getClass()) ? embeddedObject : _deserializeEmbedded(embeddedObject, deserializationContext);
                }
                return null;
            }
            throw deserializationContext.mappingException(this._valueClass);
        }
        String trim = jsonParser.getText().trim();
        if (trim.length() == 0) {
            return null;
        }
        try {
            Object _deserialize = _deserialize(trim, deserializationContext);
            if (_deserialize != null) {
                return _deserialize;
            }
        } catch (IllegalArgumentException e) {
        }
        throw deserializationContext.weirdStringException(this._valueClass, "not a valid textual representation");
    }
}
