package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializationProblemHandler;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.InjectableValues;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.LinkedNode;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class StdDeserializationContext extends DeserializationContext {
    static final int MAX_ERROR_STR_LEN = 500;
    protected ArrayBuilders _arrayBuilders;
    protected DateFormat _dateFormat;
    protected final DeserializerProvider _deserProvider;
    protected final InjectableValues _injectableValues;
    protected ObjectBuffer _objectBuffer;
    protected JsonParser _parser;

    public StdDeserializationContext(DeserializationConfig deserializationConfig, JsonParser jsonParser, DeserializerProvider deserializerProvider, InjectableValues injectableValues) {
        super(deserializationConfig);
        this._parser = jsonParser;
        this._deserProvider = deserializerProvider;
        this._injectableValues = injectableValues;
    }

    protected String _calcName(Class cls) {
        return cls.isArray() ? _calcName(cls.getComponentType()) + "[]" : cls.getName();
    }

    protected String _desc(String str) {
        return str.length() > MAX_ERROR_STR_LEN ? str.substring(0, MAX_ERROR_STR_LEN) + "]...[" + str.substring(str.length() - 500) : str;
    }

    protected String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception e) {
            return "[N/A]";
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    protected String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        if (this._injectableValues == null) {
            throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
        }
        return this._injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    protected DateFormat getDateFormat() {
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        return this._dateFormat;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public DeserializerProvider getDeserializerProvider() {
        return this._deserProvider;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonParser getParser() {
        return this._parser;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer jsonDeserializer, Object obj, String str) {
        LinkedNode problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            JsonParser jsonParser2 = this._parser;
            this._parser = jsonParser;
            for (LinkedNode linkedNode = problemHandlers; linkedNode != null; linkedNode = linkedNode.next()) {
                try {
                    if (((DeserializationProblemHandler) linkedNode.value()).handleUnknownProperty(this, jsonDeserializer, obj, str)) {
                        return true;
                    }
                } finally {
                    this._parser = jsonParser2;
                }
            }
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class cls, String str) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + ", problem: " + str);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class cls, Throwable th) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + ", problem: " + th.getMessage(), th);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class cls, JsonToken jsonToken) {
        return JsonMappingException.from(this._parser, "Can not deserialize instance of " + _calcName(cls) + " out of " + jsonToken + " token");
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public Date parseDate(String str) {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownFieldException(Object obj, String str) {
        return UnrecognizedPropertyException.from(this._parser, obj, str);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        return JsonMappingException.from(this._parser, "Could not resolve type id '" + str + "' into a subtype of " + javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdKeyException(Class cls, String str, String str2) {
        return JsonMappingException.from(this._parser, "Can not construct Map key of type " + cls.getName() + " from String \"" + _desc(str) + "\": " + str2);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdNumberException(Class cls, String str) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + " from number value (" + _valueDesc() + "): " + str);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdStringException(Class cls, String str) {
        return JsonMappingException.from(this._parser, "Can not construct instance of " + cls.getName() + " from String value '" + _valueDesc() + "': " + str);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        return JsonMappingException.from(jsonParser, "Unexpected token (" + jsonParser.getCurrentToken() + "), expected " + jsonToken + ": " + str);
    }
}
