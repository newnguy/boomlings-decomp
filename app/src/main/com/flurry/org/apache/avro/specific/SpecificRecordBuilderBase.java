package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilderBase;

/* loaded from: classes.dex */
public abstract class SpecificRecordBuilderBase extends RecordBuilderBase {
    /* JADX INFO: Access modifiers changed from: protected */
    public SpecificRecordBuilderBase(Schema schema) {
        super(schema, SpecificData.get());
    }

    protected SpecificRecordBuilderBase(SpecificRecord specificRecord) {
        super(specificRecord.getSchema(), SpecificData.get());
    }

    protected SpecificRecordBuilderBase(SpecificRecordBuilderBase specificRecordBuilderBase) {
        super(specificRecordBuilderBase, SpecificData.get());
    }
}
