package com.flurry.org.apache.avro;

/* loaded from: classes.dex */
public class UnresolvedUnionException extends AvroRuntimeException {
    private Schema unionSchema;
    private Object unresolvedDatum;

    public UnresolvedUnionException(Schema schema, Object obj) {
        super("Not in union " + schema + ": " + obj);
        this.unionSchema = schema;
        this.unresolvedDatum = obj;
    }

    public Schema getUnionSchema() {
        return this.unionSchema;
    }

    public Object getUnresolvedDatum() {
        return this.unresolvedDatum;
    }
}
