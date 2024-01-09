package com.flurry.org.apache.avro.data;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.ResolvingDecoder;
import com.flurry.org.codehaus.jackson.JsonNode;

public class Json$Reader implements DatumReader {
  private ResolvingDecoder resolver;
  
  private Schema written;
  
  public JsonNode read(JsonNode paramJsonNode, Decoder paramDecoder) {
    if (this.written == null)
      return Json.read(paramDecoder); 
    if (this.resolver == null)
      this.resolver = DecoderFactory.get().resolvingDecoder(this.written, Json.SCHEMA, null); 
    this.resolver.configure(paramDecoder);
    paramJsonNode = Json.read((Decoder)this.resolver);
    this.resolver.drain();
    return paramJsonNode;
  }
  
  public void setSchema(Schema paramSchema) {
    if (Json.SCHEMA.equals(this.written))
      paramSchema = null; 
    this.written = paramSchema;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\data\Json$Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */