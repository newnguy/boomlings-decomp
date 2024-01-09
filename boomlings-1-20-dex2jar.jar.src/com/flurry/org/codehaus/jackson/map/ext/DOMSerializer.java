package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import java.lang.reflect.Type;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;

public class DOMSerializer extends SerializerBase {
  protected final DOMImplementationLS _domImpl;
  
  public DOMSerializer() {
    super(Node.class);
    try {
      DOMImplementationRegistry dOMImplementationRegistry = DOMImplementationRegistry.newInstance();
      this._domImpl = (DOMImplementationLS)dOMImplementationRegistry.getDOMImplementation("LS");
      return;
    } catch (Exception exception) {
      throw new IllegalStateException("Could not instantiate DOMImplementationRegistry: " + exception.getMessage(), exception);
    } 
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void serialize(Node paramNode, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._domImpl == null)
      throw new IllegalStateException("Could not find DOM LS"); 
    paramJsonGenerator.writeString(this._domImpl.createLSSerializer().writeToString(paramNode));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\DOMSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */