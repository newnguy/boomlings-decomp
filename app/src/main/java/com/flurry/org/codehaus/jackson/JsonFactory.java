package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
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

/* loaded from: classes.dex */
public class JsonFactory implements Versioned {
    public static final String FORMAT_NAME_JSON = "JSON";
    protected CharacterEscapes _characterEscapes;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected BytesToNameCanonicalizer _rootByteSymbols;
    protected CharsToNameCanonicalizer _rootCharSymbols;
    static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final ThreadLocal _recyclerRef = new ThreadLocal();

    public JsonFactory() {
        this(null);
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._objectCodec = objectCodec;
    }

    protected IOContext _createContext(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }

    protected JsonGenerator _createJsonGenerator(Writer writer, IOContext iOContext) {
        WriterBasedGenerator writerBasedGenerator = new WriterBasedGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer);
        if (this._characterEscapes != null) {
            writerBasedGenerator.setCharacterEscapes(this._characterEscapes);
        }
        return writerBasedGenerator;
    }

    protected JsonParser _createJsonParser(InputStream inputStream, IOContext iOContext) {
        return new ByteSourceBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
    }

    protected JsonParser _createJsonParser(Reader reader, IOContext iOContext) {
        return new ReaderBasedParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(isEnabled(JsonParser.Feature.CANONICALIZE_FIELD_NAMES), isEnabled(JsonParser.Feature.INTERN_FIELD_NAMES)));
    }

    protected JsonParser _createJsonParser(byte[] bArr, int i, int i2, IOContext iOContext) {
        return new ByteSourceBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
    }

    protected JsonGenerator _createUTF8JsonGenerator(OutputStream outputStream, IOContext iOContext) {
        Utf8Generator utf8Generator = new Utf8Generator(iOContext, this._generatorFeatures, this._objectCodec, outputStream);
        if (this._characterEscapes != null) {
            utf8Generator.setCharacterEscapes(this._characterEscapes);
        }
        return utf8Generator;
    }

    protected Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) {
        return jsonEncoding == JsonEncoding.UTF8 ? new UTF8Writer(iOContext, outputStream) : new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    public BufferRecycler _getBufferRecycler() {
        SoftReference softReference = (SoftReference) _recyclerRef.get();
        BufferRecycler bufferRecycler = softReference == null ? null : (BufferRecycler) softReference.get();
        if (bufferRecycler == null) {
            BufferRecycler bufferRecycler2 = new BufferRecycler();
            _recyclerRef.set(new SoftReference(bufferRecycler2));
            return bufferRecycler2;
        }
        return bufferRecycler;
    }

    protected InputStream _optimizedStreamFromURL(URL url) {
        String host;
        return ("file".equals(url.getProtocol()) && ((host = url.getHost()) == null || host.length() == 0)) ? new FileInputStream(url.getPath()) : url.openStream();
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public JsonGenerator createJsonGenerator(File file, JsonEncoding jsonEncoding) {
        OutputStream fileOutputStream = new FileOutputStream(file);
        IOContext _createContext = _createContext(fileOutputStream, true);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            if (this._outputDecorator != null) {
                fileOutputStream = this._outputDecorator.decorate(_createContext, fileOutputStream);
            }
            return _createUTF8JsonGenerator(fileOutputStream, _createContext);
        }
        Writer _createWriter = _createWriter(fileOutputStream, jsonEncoding, _createContext);
        if (this._outputDecorator != null) {
            _createWriter = this._outputDecorator.decorate(_createContext, _createWriter);
        }
        return _createJsonGenerator(_createWriter, _createContext);
    }

    public JsonGenerator createJsonGenerator(OutputStream outputStream) {
        return createJsonGenerator(outputStream, JsonEncoding.UTF8);
    }

    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) {
        IOContext _createContext = _createContext(outputStream, false);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            if (this._outputDecorator != null) {
                outputStream = this._outputDecorator.decorate(_createContext, outputStream);
            }
            return _createUTF8JsonGenerator(outputStream, _createContext);
        }
        Writer _createWriter = _createWriter(outputStream, jsonEncoding, _createContext);
        if (this._outputDecorator != null) {
            _createWriter = this._outputDecorator.decorate(_createContext, _createWriter);
        }
        return _createJsonGenerator(_createWriter, _createContext);
    }

    public JsonGenerator createJsonGenerator(Writer writer) {
        IOContext _createContext = _createContext(writer, false);
        if (this._outputDecorator != null) {
            writer = this._outputDecorator.decorate(_createContext, writer);
        }
        return _createJsonGenerator(writer, _createContext);
    }

    public JsonParser createJsonParser(File file) {
        IOContext _createContext = _createContext(file, true);
        InputStream fileInputStream = new FileInputStream(file);
        if (this._inputDecorator != null) {
            fileInputStream = this._inputDecorator.decorate(_createContext, fileInputStream);
        }
        return _createJsonParser(fileInputStream, _createContext);
    }

    public JsonParser createJsonParser(InputStream inputStream) {
        IOContext _createContext = _createContext(inputStream, false);
        if (this._inputDecorator != null) {
            inputStream = this._inputDecorator.decorate(_createContext, inputStream);
        }
        return _createJsonParser(inputStream, _createContext);
    }

    public JsonParser createJsonParser(Reader reader) {
        IOContext _createContext = _createContext(reader, false);
        if (this._inputDecorator != null) {
            reader = this._inputDecorator.decorate(_createContext, reader);
        }
        return _createJsonParser(reader, _createContext);
    }

    public JsonParser createJsonParser(String str) {
        Reader stringReader = new StringReader(str);
        IOContext _createContext = _createContext(stringReader, true);
        if (this._inputDecorator != null) {
            stringReader = this._inputDecorator.decorate(_createContext, stringReader);
        }
        return _createJsonParser(stringReader, _createContext);
    }

    public JsonParser createJsonParser(URL url) {
        IOContext _createContext = _createContext(url, true);
        InputStream _optimizedStreamFromURL = _optimizedStreamFromURL(url);
        if (this._inputDecorator != null) {
            _optimizedStreamFromURL = this._inputDecorator.decorate(_createContext, _optimizedStreamFromURL);
        }
        return _createJsonParser(_optimizedStreamFromURL, _createContext);
    }

    public JsonParser createJsonParser(byte[] bArr) {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        return (this._inputDecorator == null || (decorate = this._inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) == null) ? _createJsonParser(bArr, 0, bArr.length, _createContext) : _createJsonParser(decorate, _createContext);
    }

    public JsonParser createJsonParser(byte[] bArr, int i, int i2) {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        return (this._inputDecorator == null || (decorate = this._inputDecorator.decorate(_createContext, bArr, i, i2)) == null) ? _createJsonParser(bArr, i, i2, _createContext) : _createJsonParser(decorate, _createContext);
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this._generatorFeatures &= feature.getMask() ^ (-1);
        return this;
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this._parserFeatures &= feature.getMask() ^ (-1);
        return this;
    }

    @Deprecated
    public final void disableGeneratorFeature(JsonGenerator.Feature feature) {
        disable(feature);
    }

    public final void disableParserFeature(JsonParser.Feature feature) {
        disable(feature);
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this._generatorFeatures |= feature.getMask();
        return this;
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this._parserFeatures |= feature.getMask();
        return this;
    }

    @Deprecated
    public final void enableGeneratorFeature(JsonGenerator.Feature feature) {
        enable(feature);
    }

    public final void enableParserFeature(JsonParser.Feature feature) {
        enable(feature);
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return FORMAT_NAME_JSON;
        }
        return null;
    }

    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }

    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) {
        if (getClass() == JsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }

    public MatchStrength hasJSONFormat(InputAccessor inputAccessor) {
        return ByteSourceBootstrapper.hasJSONFormat(inputAccessor);
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0;
    }

    public final boolean isEnabled(JsonParser.Feature feature) {
        return (this._parserFeatures & feature.getMask()) != 0;
    }

    @Deprecated
    public final boolean isGeneratorFeatureEnabled(JsonGenerator.Feature feature) {
        return isEnabled(feature);
    }

    public final boolean isParserFeatureEnabled(JsonParser.Feature feature) {
        return (this._parserFeatures & feature.getMask()) != 0;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    @Deprecated
    public final void setGeneratorFeature(JsonGenerator.Feature feature, boolean z) {
        configure(feature, z);
    }

    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }

    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }

    public final void setParserFeature(JsonParser.Feature feature, boolean z) {
        configure(feature, z);
    }

    @Override // com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(Utf8Generator.class);
    }
}
