package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.FormatSchema;
import com.flurry.org.codehaus.jackson.JsonEncoding;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.PrettyPrinter;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.Versioned;
import com.flurry.org.codehaus.jackson.io.SegmentedStringWriter;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.DefaultPrettyPrinter;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import com.flurry.org.codehaus.jackson.util.VersionUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DateFormat;

public class ObjectWriter implements Versioned {
  protected static final PrettyPrinter NULL_PRETTY_PRINTER = (PrettyPrinter)new MinimalPrettyPrinter();
  
  protected final SerializationConfig _config;
  
  protected final JsonFactory _jsonFactory;
  
  protected final PrettyPrinter _prettyPrinter;
  
  protected final SerializerProvider _provider;
  
  protected final JavaType _rootType;
  
  protected final FormatSchema _schema;
  
  protected final SerializerFactory _serializerFactory;
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig) {
    this._config = paramSerializationConfig;
    this._provider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._jsonFactory = paramObjectMapper._jsonFactory;
    this._rootType = null;
    this._prettyPrinter = null;
    this._schema = null;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, FormatSchema paramFormatSchema) {
    this._config = paramSerializationConfig;
    this._provider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._jsonFactory = paramObjectMapper._jsonFactory;
    this._rootType = null;
    this._prettyPrinter = null;
    this._schema = paramFormatSchema;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter) {
    this._config = paramSerializationConfig;
    this._provider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._jsonFactory = paramObjectMapper._jsonFactory;
    this._rootType = paramJavaType;
    this._prettyPrinter = paramPrettyPrinter;
    this._schema = null;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig) {
    this._config = paramSerializationConfig;
    this._provider = paramObjectWriter._provider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._jsonFactory = paramObjectWriter._jsonFactory;
    this._schema = paramObjectWriter._schema;
    this._rootType = paramObjectWriter._rootType;
    this._prettyPrinter = paramObjectWriter._prettyPrinter;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter, FormatSchema paramFormatSchema) {
    this._config = paramSerializationConfig;
    this._provider = paramObjectWriter._provider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._jsonFactory = paramObjectWriter._jsonFactory;
    this._rootType = paramJavaType;
    this._prettyPrinter = paramPrettyPrinter;
    this._schema = paramFormatSchema;
  }
  
  private final void _configAndWriteCloseable(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig) {
    Exception exception;
    Closeable closeable1;
    Closeable closeable2 = (Closeable)paramObject;
    try {
    
    } finally {
      Exception exception1 = null;
      paramObject = paramJsonGenerator;
      closeable1 = closeable2;
    } 
    if (paramObject != null)
      try {
        paramObject.close();
      } catch (IOException iOException) {} 
    if (closeable1 != null)
      try {
        closeable1.close();
      } catch (IOException iOException) {} 
    throw exception;
  }
  
  private final void _writeCloseableValue(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig) {
    Closeable closeable = (Closeable)paramObject;
    try {
      if (this._rootType == null) {
        this._provider.serializeValue(paramSerializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
      } else {
        this._provider.serializeValue(paramSerializationConfig, paramJsonGenerator, paramObject, this._rootType, this._serializerFactory);
      } 
      if (this._config.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
        paramJsonGenerator.flush(); 
    } finally {
      paramJsonGenerator = null;
    } 
    if (paramObject != null)
      try {
        paramObject.close();
      } catch (IOException iOException) {} 
    throw paramJsonGenerator;
  }
  
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    boolean bool;
    if (this._prettyPrinter != null) {
      PrettyPrinter prettyPrinter2 = this._prettyPrinter;
      PrettyPrinter prettyPrinter1 = prettyPrinter2;
      if (prettyPrinter2 == NULL_PRETTY_PRINTER)
        prettyPrinter1 = null; 
      paramJsonGenerator.setPrettyPrinter(prettyPrinter1);
    } else if (this._config.isEnabled(SerializationConfig$Feature.INDENT_OUTPUT)) {
      paramJsonGenerator.useDefaultPrettyPrinter();
    } 
    if (this._schema != null)
      paramJsonGenerator.setSchema(this._schema); 
    if (this._config.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _configAndWriteCloseable(paramJsonGenerator, paramObject, this._config);
      return;
    } 
    try {
      if (this._rootType == null) {
        this._provider.serializeValue(this._config, paramJsonGenerator, paramObject, this._serializerFactory);
      } else {
        this._provider.serializeValue(this._config, paramJsonGenerator, paramObject, this._rootType, this._serializerFactory);
      } 
      bool = true;
    } finally {
      paramObject = null;
    } 
    if (!bool)
      try {
        paramJsonGenerator.close();
      } catch (IOException iOException) {} 
    throw paramObject;
  }
  
  public boolean canSerialize(Class paramClass) {
    return this._provider.hasSerializerFor(this._config, paramClass, this._serializerFactory);
  }
  
  public Version version() {
    return VersionUtil.versionFor(getClass());
  }
  
  public ObjectWriter withDateFormat(DateFormat paramDateFormat) {
    SerializationConfig serializationConfig = this._config.withDateFormat(paramDateFormat);
    return (serializationConfig == this._config) ? this : new ObjectWriter(this, serializationConfig);
  }
  
  public ObjectWriter withDefaultPrettyPrinter() {
    return withPrettyPrinter((PrettyPrinter)new DefaultPrettyPrinter());
  }
  
  public ObjectWriter withFilters(FilterProvider paramFilterProvider) {
    return (paramFilterProvider == this._config.getFilterProvider()) ? this : new ObjectWriter(this, this._config.withFilters(paramFilterProvider));
  }
  
  public ObjectWriter withPrettyPrinter(PrettyPrinter paramPrettyPrinter) {
    if (paramPrettyPrinter == this._prettyPrinter)
      return this; 
    if (paramPrettyPrinter == null)
      paramPrettyPrinter = NULL_PRETTY_PRINTER; 
    return new ObjectWriter(this, this._config, this._rootType, paramPrettyPrinter, this._schema);
  }
  
  public ObjectWriter withSchema(FormatSchema paramFormatSchema) {
    return (this._schema == paramFormatSchema) ? this : new ObjectWriter(this, this._config, this._rootType, this._prettyPrinter, paramFormatSchema);
  }
  
  public ObjectWriter withType(JavaType paramJavaType) {
    return (paramJavaType == this._rootType) ? this : new ObjectWriter(this, this._config, paramJavaType, this._prettyPrinter, this._schema);
  }
  
  public ObjectWriter withType(TypeReference paramTypeReference) {
    return withType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectWriter withType(Class paramClass) {
    return withType(this._config.constructType(paramClass));
  }
  
  public ObjectWriter withView(Class paramClass) {
    return (paramClass == this._config.getSerializationView()) ? this : new ObjectWriter(this, this._config.withView(paramClass));
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    if (this._config.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _writeCloseableValue(paramJsonGenerator, paramObject, this._config);
      return;
    } 
    if (this._rootType == null) {
      this._provider.serializeValue(this._config, paramJsonGenerator, paramObject, this._serializerFactory);
    } else {
      this._provider.serializeValue(this._config, paramJsonGenerator, paramObject, this._rootType, this._serializerFactory);
    } 
    if (this._config.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
      paramJsonGenerator.flush(); 
  }
  
  public void writeValue(File paramFile, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject) {
    ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
    _configAndWriteValue(this._jsonFactory.createJsonGenerator((OutputStream)byteArrayBuilder, JsonEncoding.UTF8), paramObject);
    paramObject = byteArrayBuilder.toByteArray();
    byteArrayBuilder.release();
    return (byte[])paramObject;
  }
  
  public String writeValueAsString(Object paramObject) {
    SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
    _configAndWriteValue(this._jsonFactory.createJsonGenerator((Writer)segmentedStringWriter), paramObject);
    return segmentedStringWriter.getAndClear();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */