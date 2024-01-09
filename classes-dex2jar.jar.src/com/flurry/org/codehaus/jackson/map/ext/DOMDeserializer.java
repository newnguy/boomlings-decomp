package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public abstract class DOMDeserializer extends FromStringDeserializer {
  static final DocumentBuilderFactory _parserFactory = DocumentBuilderFactory.newInstance();
  
  static {
    _parserFactory.setNamespaceAware(true);
  }
  
  protected DOMDeserializer(Class paramClass) {
    super(paramClass);
  }
  
  public abstract Object _deserialize(String paramString, DeserializationContext paramDeserializationContext);
  
  protected final Document parse(String paramString) {
    try {
      DocumentBuilder documentBuilder = _parserFactory.newDocumentBuilder();
      InputSource inputSource = new InputSource();
      StringReader stringReader = new StringReader();
      this(paramString);
      this(stringReader);
      return documentBuilder.parse(inputSource);
    } catch (Exception exception) {
      throw new IllegalArgumentException("Failed to parse JSON String as XML: " + exception.getMessage(), exception);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\DOMDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */