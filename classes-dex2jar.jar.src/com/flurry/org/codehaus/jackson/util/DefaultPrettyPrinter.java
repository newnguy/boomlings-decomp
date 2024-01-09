package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.PrettyPrinter;
import com.flurry.org.codehaus.jackson.impl.Indenter;

public class DefaultPrettyPrinter implements PrettyPrinter {
  protected Indenter _arrayIndenter = new DefaultPrettyPrinter$FixedSpaceIndenter();
  
  protected int _nesting = 0;
  
  protected Indenter _objectIndenter = new DefaultPrettyPrinter$Lf2SpacesIndenter();
  
  protected boolean _spacesInObjectEntries = true;
  
  public void beforeArrayValues(JsonGenerator paramJsonGenerator) {
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void beforeObjectEntries(JsonGenerator paramJsonGenerator) {
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void indentArraysWith(Indenter paramIndenter) {
    Indenter indenter = paramIndenter;
    if (paramIndenter == null)
      indenter = new DefaultPrettyPrinter$NopIndenter(); 
    this._arrayIndenter = indenter;
  }
  
  public void indentObjectsWith(Indenter paramIndenter) {
    Indenter indenter = paramIndenter;
    if (paramIndenter == null)
      indenter = new DefaultPrettyPrinter$NopIndenter(); 
    this._objectIndenter = indenter;
  }
  
  public void spacesInObjectEntries(boolean paramBoolean) {
    this._spacesInObjectEntries = paramBoolean;
  }
  
  public void writeArrayValueSeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(',');
    this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt) {
    if (!this._arrayIndenter.isInline())
      this._nesting--; 
    if (paramInt > 0) {
      this._arrayIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    } else {
      paramJsonGenerator.writeRaw(' ');
    } 
    paramJsonGenerator.writeRaw(']');
  }
  
  public void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt) {
    if (!this._objectIndenter.isInline())
      this._nesting--; 
    if (paramInt > 0) {
      this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
    } else {
      paramJsonGenerator.writeRaw(' ');
    } 
    paramJsonGenerator.writeRaw('}');
  }
  
  public void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(',');
    this._objectIndenter.writeIndentation(paramJsonGenerator, this._nesting);
  }
  
  public void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator) {
    if (this._spacesInObjectEntries) {
      paramJsonGenerator.writeRaw(" : ");
      return;
    } 
    paramJsonGenerator.writeRaw(':');
  }
  
  public void writeRootValueSeparator(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw(' ');
  }
  
  public void writeStartArray(JsonGenerator paramJsonGenerator) {
    if (!this._arrayIndenter.isInline())
      this._nesting++; 
    paramJsonGenerator.writeRaw('[');
  }
  
  public void writeStartObject(JsonGenerator paramJsonGenerator) {
    paramJsonGenerator.writeRaw('{');
    if (!this._objectIndenter.isInline())
      this._nesting++; 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\DefaultPrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */