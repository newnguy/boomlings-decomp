package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class MappingIterator implements Iterator {
    protected static final MappingIterator EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer _deserializer;
    protected boolean _hasNextChecked;
    protected JsonParser _parser;
    protected final JavaType _type;
    protected final Object _updatedValue;

    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer jsonDeserializer) {
        this(javaType, jsonParser, deserializationContext, jsonDeserializer, true, null);
    }

    public MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer jsonDeserializer, boolean z, Object obj) {
        this._type = javaType;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        if (jsonParser != null && jsonParser.getCurrentToken() == JsonToken.START_ARRAY && !jsonParser.getParsingContext().inRoot()) {
            jsonParser.clearCurrentToken();
        }
        this._closeParser = z;
        if (obj == null) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj;
        }
    }

    protected static MappingIterator emptyIterator() {
        return EMPTY_ITERATOR;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        try {
            return hasNextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public boolean hasNextValue() {
        if (this._parser == null) {
            return false;
        }
        if (!this._hasNextChecked) {
            JsonToken currentToken = this._parser.getCurrentToken();
            this._hasNextChecked = true;
            if (currentToken == null) {
                JsonToken nextToken = this._parser.nextToken();
                if (nextToken == null) {
                    JsonParser jsonParser = this._parser;
                    this._parser = null;
                    if (this._closeParser) {
                        jsonParser.close();
                        return false;
                    }
                    return false;
                } else if (nextToken == JsonToken.END_ARRAY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            return nextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public Object nextValue() {
        Object obj;
        if (this._hasNextChecked || hasNextValue()) {
            if (this._parser == null) {
                throw new NoSuchElementException();
            }
            this._hasNextChecked = false;
            if (this._updatedValue == null) {
                obj = this._deserializer.deserialize(this._parser, this._context);
            } else {
                this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
                obj = this._updatedValue;
            }
            this._parser.clearCurrentToken();
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
