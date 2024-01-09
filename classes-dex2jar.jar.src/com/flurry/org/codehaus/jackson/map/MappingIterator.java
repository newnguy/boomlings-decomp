package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MappingIterator implements Iterator {
  protected static final MappingIterator EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
  
  protected final boolean _closeParser;
  
  protected final DeserializationContext _context;
  
  protected final JsonDeserializer _deserializer;
  
  protected boolean _hasNextChecked;
  
  protected JsonParser _parser;
  
  protected final JavaType _type;
  
  protected final Object _updatedValue;
  
  protected MappingIterator(JavaType paramJavaType, JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer paramJsonDeserializer) {
    this(paramJavaType, paramJsonParser, paramDeserializationContext, paramJsonDeserializer, true, null);
  }
  
  protected MappingIterator(JavaType paramJavaType, JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer paramJsonDeserializer, boolean paramBoolean, Object paramObject) {
    this._type = paramJavaType;
    this._parser = paramJsonParser;
    this._context = paramDeserializationContext;
    this._deserializer = paramJsonDeserializer;
    if (paramJsonParser != null && paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY && !paramJsonParser.getParsingContext().inRoot())
      paramJsonParser.clearCurrentToken(); 
    this._closeParser = paramBoolean;
    if (paramObject == null) {
      this._updatedValue = null;
      return;
    } 
    this._updatedValue = paramObject;
  }
  
  protected static MappingIterator emptyIterator() {
    return EMPTY_ITERATOR;
  }
  
  public boolean hasNext() {
    try {
      return hasNextValue();
    } catch (JsonMappingException jsonMappingException) {
      throw new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException.getMessage(), iOException);
    } 
  }
  
  public boolean hasNextValue() {
    boolean bool = false;
    if (this._parser == null)
      return bool; 
    if (!this._hasNextChecked) {
      JsonToken jsonToken = this._parser.getCurrentToken();
      this._hasNextChecked = true;
      if (jsonToken == null) {
        JsonParser jsonParser;
        jsonToken = this._parser.nextToken();
        if (jsonToken == null) {
          jsonParser = this._parser;
          this._parser = null;
          boolean bool2 = bool;
          if (this._closeParser) {
            jsonParser.close();
            bool2 = bool;
          } 
          return bool2;
        } 
        boolean bool1 = bool;
        return (jsonParser != JsonToken.END_ARRAY) ? true : bool1;
      } 
    } 
    return true;
  }
  
  public Object next() {
    try {
      return nextValue();
    } catch (JsonMappingException jsonMappingException) {
      throw new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException.getMessage(), iOException);
    } 
  }
  
  public Object nextValue() {
    if (!this._hasNextChecked && !hasNextValue())
      throw new NoSuchElementException(); 
    if (this._parser == null)
      throw new NoSuchElementException(); 
    this._hasNextChecked = false;
    if (this._updatedValue == null) {
      Object object1 = this._deserializer.deserialize(this._parser, this._context);
      this._parser.clearCurrentToken();
      return object1;
    } 
    this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
    Object object = this._updatedValue;
    this._parser.clearCurrentToken();
    return object;
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\MappingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */