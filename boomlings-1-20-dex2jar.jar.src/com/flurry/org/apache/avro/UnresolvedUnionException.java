package com.flurry.org.apache.avro;

public class UnresolvedUnionException extends AvroRuntimeException {
  private Schema unionSchema;
  
  private Object unresolvedDatum;
  
  public UnresolvedUnionException(Schema paramSchema, Object paramObject) {
    super("Not in union " + paramSchema + ": " + paramObject);
    this.unionSchema = paramSchema;
    this.unresolvedDatum = paramObject;
  }
  
  public Schema getUnionSchema() {
    return this.unionSchema;
  }
  
  public Object getUnresolvedDatum() {
    return this.unresolvedDatum;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\UnresolvedUnionException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */