package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.format.InputAccessor;
import com.flurry.org.codehaus.jackson.format.MatchStrength;
import com.flurry.org.codehaus.jackson.impl.ByteSourceBootstrapper;
import com.flurry.org.codehaus.jackson.impl.ReaderBasedParser;
import com.flurry.org.codehaus.jackson.impl.Utf8Generator;
import com.flurry.org.codehaus.jackson.impl.WriterBasedGenerator;
import com.flurry.org.codehaus.jackson.io.CharacterEscapes;
import com.flurry.org.codehaus.jackson.io.IOContext;
import com.flurry.org.codehaus.jackson.io.InputDecorator;
import com.flurry.org.codehaus.jackson.io.OutputDecorator;
import com.flurry.org.codehaus.jackson.io.UTF8Writer;
import com.flurry.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.flurry.org.codehaus.jackson.util.BufferRecycler;
import com.flurry.org.codehaus.jackson.util.VersionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.net.URL;

public class JsonFactory implements Versioned {
  static final int DEFAULT_GENERATOR_FEATURE_FLAGS;
  
  static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser$Feature.collectDefaults();
  
  public static final String FORMAT_NAME_JSON = "JSON";
  
  protected static final ThreadLocal _recyclerRef;
  
  protected CharacterEscapes _characterEscapes;
  
  protected int _generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
  
  protected InputDecorator _inputDecorator;
  
  protected ObjectCodec _objectCodec;
  
  protected OutputDecorator _outputDecorator;
  
  protected int _parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
  
  protected BytesToNameCanonicalizer _rootByteSymbols = BytesToNameCanonicalizer.createRoot();
  
  protected CharsToNameCanonicalizer _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
  
  static {
    DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator$Feature.collectDefaults();
    _recyclerRef = new ThreadLocal();
  }
  
  public JsonFactory() {
    this(null);
  }
  
  public JsonFactory(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
  }
  
  protected IOContext _createContext(Object paramObject, boolean paramBoolean) {
    return new IOContext(_getBufferRecycler(), paramObject, paramBoolean);
  }
  
  protected JsonGenerator _createJsonGenerator(Writer paramWriter, IOContext paramIOContext) {
    WriterBasedGenerator writerBasedGenerator = new WriterBasedGenerator(paramIOContext, this._generatorFeatures, this._objectCodec, paramWriter);
    if (this._characterEscapes != null)
      writerBasedGenerator.setCharacterEscapes(this._characterEscapes); 
    return (JsonGenerator)writerBasedGenerator;
  }
  
  protected JsonParser _createJsonParser(InputStream paramInputStream, IOContext paramIOContext) {
    return (new ByteSourceBootstrapper(paramIOContext, paramInputStream)).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
  }
  
  protected JsonParser _createJsonParser(Reader paramReader, IOContext paramIOContext) {
    return (JsonParser)new ReaderBasedParser(paramIOContext, this._parserFeatures, paramReader, this._objectCodec, this._rootCharSymbols.makeChild(isEnabled(JsonParser$Feature.CANONICALIZE_FIELD_NAMES), isEnabled(JsonParser$Feature.INTERN_FIELD_NAMES)));
  }
  
  protected JsonParser _createJsonParser(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, IOContext paramIOContext) {
    return (new ByteSourceBootstrapper(paramIOContext, paramArrayOfbyte, paramInt1, paramInt2)).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
  }
  
  protected JsonGenerator _createUTF8JsonGenerator(OutputStream paramOutputStream, IOContext paramIOContext) {
    Utf8Generator utf8Generator = new Utf8Generator(paramIOContext, this._generatorFeatures, this._objectCodec, paramOutputStream);
    if (this._characterEscapes != null)
      utf8Generator.setCharacterEscapes(this._characterEscapes); 
    return (JsonGenerator)utf8Generator;
  }
  
  protected Writer _createWriter(OutputStream paramOutputStream, JsonEncoding paramJsonEncoding, IOContext paramIOContext) {
    return (Writer)((paramJsonEncoding == JsonEncoding.UTF8) ? new UTF8Writer(paramIOContext, paramOutputStream) : new OutputStreamWriter(paramOutputStream, paramJsonEncoding.getJavaName()));
  }
  
  public BufferRecycler _getBufferRecycler() {
    BufferRecycler bufferRecycler1;
    SoftReference<BufferRecycler> softReference = _recyclerRef.get();
    if (softReference == null) {
      softReference = null;
    } else {
      bufferRecycler1 = softReference.get();
    } 
    BufferRecycler bufferRecycler2 = bufferRecycler1;
    if (bufferRecycler1 == null) {
      bufferRecycler2 = new BufferRecycler();
      _recyclerRef.set(new SoftReference<BufferRecycler>(bufferRecycler2));
    } 
    return bufferRecycler2;
  }
  
  protected InputStream _optimizedStreamFromURL(URL paramURL) {
    if ("file".equals(paramURL.getProtocol())) {
      String str = paramURL.getHost();
      if (str == null || str.length() == 0)
        return new FileInputStream(paramURL.getPath()); 
    } 
    return paramURL.openStream();
  }
  
  public final JsonFactory configure(JsonGenerator$Feature paramJsonGenerator$Feature, boolean paramBoolean) {
    if (paramBoolean) {
      enable(paramJsonGenerator$Feature);
      return this;
    } 
    disable(paramJsonGenerator$Feature);
    return this;
  }
  
  public final JsonFactory configure(JsonParser$Feature paramJsonParser$Feature, boolean paramBoolean) {
    if (paramBoolean) {
      enable(paramJsonParser$Feature);
      return this;
    } 
    disable(paramJsonParser$Feature);
    return this;
  }
  
  public JsonGenerator createJsonGenerator(File paramFile, JsonEncoding paramJsonEncoding) {
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
    IOContext iOContext = _createContext(fileOutputStream, true);
    iOContext.setEncoding(paramJsonEncoding);
    if (paramJsonEncoding == JsonEncoding.UTF8) {
      OutputStream outputStream = fileOutputStream;
      if (this._outputDecorator != null)
        outputStream = this._outputDecorator.decorate(iOContext, fileOutputStream); 
      return _createUTF8JsonGenerator(outputStream, iOContext);
    } 
    Writer writer2 = _createWriter(fileOutputStream, paramJsonEncoding, iOContext);
    Writer writer1 = writer2;
    if (this._outputDecorator != null)
      writer1 = this._outputDecorator.decorate(iOContext, writer2); 
    return _createJsonGenerator(writer1, iOContext);
  }
  
  public JsonGenerator createJsonGenerator(OutputStream paramOutputStream) {
    return createJsonGenerator(paramOutputStream, JsonEncoding.UTF8);
  }
  
  public JsonGenerator createJsonGenerator(OutputStream paramOutputStream, JsonEncoding paramJsonEncoding) {
    OutputStream outputStream;
    IOContext iOContext = _createContext(paramOutputStream, false);
    iOContext.setEncoding(paramJsonEncoding);
    if (paramJsonEncoding == JsonEncoding.UTF8) {
      outputStream = paramOutputStream;
      if (this._outputDecorator != null)
        outputStream = this._outputDecorator.decorate(iOContext, paramOutputStream); 
      return _createUTF8JsonGenerator(outputStream, iOContext);
    } 
    Writer writer2 = _createWriter(paramOutputStream, (JsonEncoding)outputStream, iOContext);
    Writer writer1 = writer2;
    if (this._outputDecorator != null)
      writer1 = this._outputDecorator.decorate(iOContext, writer2); 
    return _createJsonGenerator(writer1, iOContext);
  }
  
  public JsonGenerator createJsonGenerator(Writer paramWriter) {
    IOContext iOContext = _createContext(paramWriter, false);
    Writer writer = paramWriter;
    if (this._outputDecorator != null)
      writer = this._outputDecorator.decorate(iOContext, paramWriter); 
    return _createJsonGenerator(writer, iOContext);
  }
  
  public JsonParser createJsonParser(File paramFile) {
    IOContext iOContext = _createContext(paramFile, true);
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    InputStream inputStream = fileInputStream;
    if (this._inputDecorator != null)
      inputStream = this._inputDecorator.decorate(iOContext, fileInputStream); 
    return _createJsonParser(inputStream, iOContext);
  }
  
  public JsonParser createJsonParser(InputStream paramInputStream) {
    IOContext iOContext = _createContext(paramInputStream, false);
    InputStream inputStream = paramInputStream;
    if (this._inputDecorator != null)
      inputStream = this._inputDecorator.decorate(iOContext, paramInputStream); 
    return _createJsonParser(inputStream, iOContext);
  }
  
  public JsonParser createJsonParser(Reader paramReader) {
    IOContext iOContext = _createContext(paramReader, false);
    Reader reader = paramReader;
    if (this._inputDecorator != null)
      reader = this._inputDecorator.decorate(iOContext, paramReader); 
    return _createJsonParser(reader, iOContext);
  }
  
  public JsonParser createJsonParser(String paramString) {
    StringReader stringReader = new StringReader(paramString);
    IOContext iOContext = _createContext(stringReader, true);
    Reader reader = stringReader;
    if (this._inputDecorator != null)
      reader = this._inputDecorator.decorate(iOContext, stringReader); 
    return _createJsonParser(reader, iOContext);
  }
  
  public JsonParser createJsonParser(URL paramURL) {
    IOContext iOContext = _createContext(paramURL, true);
    InputStream inputStream2 = _optimizedStreamFromURL(paramURL);
    InputStream inputStream1 = inputStream2;
    if (this._inputDecorator != null)
      inputStream1 = this._inputDecorator.decorate(iOContext, inputStream2); 
    return _createJsonParser(inputStream1, iOContext);
  }
  
  public JsonParser createJsonParser(byte[] paramArrayOfbyte) {
    IOContext iOContext = _createContext(paramArrayOfbyte, true);
    if (this._inputDecorator != null) {
      InputStream inputStream = this._inputDecorator.decorate(iOContext, paramArrayOfbyte, 0, paramArrayOfbyte.length);
      if (inputStream != null)
        return _createJsonParser(inputStream, iOContext); 
    } 
    return _createJsonParser(paramArrayOfbyte, 0, paramArrayOfbyte.length, iOContext);
  }
  
  public JsonParser createJsonParser(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    IOContext iOContext = _createContext(paramArrayOfbyte, true);
    if (this._inputDecorator != null) {
      InputStream inputStream = this._inputDecorator.decorate(iOContext, paramArrayOfbyte, paramInt1, paramInt2);
      if (inputStream != null)
        return _createJsonParser(inputStream, iOContext); 
    } 
    return _createJsonParser(paramArrayOfbyte, paramInt1, paramInt2, iOContext);
  }
  
  public JsonFactory disable(JsonGenerator$Feature paramJsonGenerator$Feature) {
    this._generatorFeatures &= paramJsonGenerator$Feature.getMask() ^ 0xFFFFFFFF;
    return this;
  }
  
  public JsonFactory disable(JsonParser$Feature paramJsonParser$Feature) {
    this._parserFeatures &= paramJsonParser$Feature.getMask() ^ 0xFFFFFFFF;
    return this;
  }
  
  @Deprecated
  public final void disableGeneratorFeature(JsonGenerator$Feature paramJsonGenerator$Feature) {
    disable(paramJsonGenerator$Feature);
  }
  
  public final void disableParserFeature(JsonParser$Feature paramJsonParser$Feature) {
    disable(paramJsonParser$Feature);
  }
  
  public JsonFactory enable(JsonGenerator$Feature paramJsonGenerator$Feature) {
    this._generatorFeatures |= paramJsonGenerator$Feature.getMask();
    return this;
  }
  
  public JsonFactory enable(JsonParser$Feature paramJsonParser$Feature) {
    this._parserFeatures |= paramJsonParser$Feature.getMask();
    return this;
  }
  
  @Deprecated
  public final void enableGeneratorFeature(JsonGenerator$Feature paramJsonGenerator$Feature) {
    enable(paramJsonGenerator$Feature);
  }
  
  public final void enableParserFeature(JsonParser$Feature paramJsonParser$Feature) {
    enable(paramJsonParser$Feature);
  }
  
  public CharacterEscapes getCharacterEscapes() {
    return this._characterEscapes;
  }
  
  public ObjectCodec getCodec() {
    return this._objectCodec;
  }
  
  public String getFormatName() {
    return (getClass() == JsonFactory.class) ? "JSON" : null;
  }
  
  public InputDecorator getInputDecorator() {
    return this._inputDecorator;
  }
  
  public OutputDecorator getOutputDecorator() {
    return this._outputDecorator;
  }
  
  public MatchStrength hasFormat(InputAccessor paramInputAccessor) {
    return (getClass() == JsonFactory.class) ? hasJSONFormat(paramInputAccessor) : null;
  }
  
  protected MatchStrength hasJSONFormat(InputAccessor paramInputAccessor) {
    return ByteSourceBootstrapper.hasJSONFormat(paramInputAccessor);
  }
  
  public final boolean isEnabled(JsonGenerator$Feature paramJsonGenerator$Feature) {
    return ((this._generatorFeatures & paramJsonGenerator$Feature.getMask()) != 0);
  }
  
  public final boolean isEnabled(JsonParser$Feature paramJsonParser$Feature) {
    return ((this._parserFeatures & paramJsonParser$Feature.getMask()) != 0);
  }
  
  @Deprecated
  public final boolean isGeneratorFeatureEnabled(JsonGenerator$Feature paramJsonGenerator$Feature) {
    return isEnabled(paramJsonGenerator$Feature);
  }
  
  public final boolean isParserFeatureEnabled(JsonParser$Feature paramJsonParser$Feature) {
    return ((this._parserFeatures & paramJsonParser$Feature.getMask()) != 0);
  }
  
  public JsonFactory setCharacterEscapes(CharacterEscapes paramCharacterEscapes) {
    this._characterEscapes = paramCharacterEscapes;
    return this;
  }
  
  public JsonFactory setCodec(ObjectCodec paramObjectCodec) {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  @Deprecated
  public final void setGeneratorFeature(JsonGenerator$Feature paramJsonGenerator$Feature, boolean paramBoolean) {
    configure(paramJsonGenerator$Feature, paramBoolean);
  }
  
  public JsonFactory setInputDecorator(InputDecorator paramInputDecorator) {
    this._inputDecorator = paramInputDecorator;
    return this;
  }
  
  public JsonFactory setOutputDecorator(OutputDecorator paramOutputDecorator) {
    this._outputDecorator = paramOutputDecorator;
    return this;
  }
  
  public final void setParserFeature(JsonParser$Feature paramJsonParser$Feature, boolean paramBoolean) {
    configure(paramJsonParser$Feature, paramBoolean);
  }
  
  public Version version() {
    return VersionUtil.versionFor(Utf8Generator.class);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */