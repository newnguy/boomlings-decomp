package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.ErrorBuilder;
import com.flurry.org.apache.avro.data.RecordBuilderBase;
import java.lang.reflect.Constructor;

/* loaded from: classes.dex */
public abstract class SpecificErrorBuilderBase extends RecordBuilderBase implements ErrorBuilder {
    private Throwable cause;
    private Constructor errorConstructor;
    private boolean hasCause;
    private boolean hasValue;
    private Object value;

    protected SpecificErrorBuilderBase(Schema schema) {
        super(schema, SpecificData.get());
    }

    protected SpecificErrorBuilderBase(SpecificErrorBuilderBase specificErrorBuilderBase) {
        super(specificErrorBuilderBase, SpecificData.get());
        this.errorConstructor = specificErrorBuilderBase.errorConstructor;
        this.value = specificErrorBuilderBase.value;
        this.hasValue = specificErrorBuilderBase.hasValue;
        this.cause = specificErrorBuilderBase.cause;
        this.hasCause = specificErrorBuilderBase.hasCause;
    }

    protected SpecificErrorBuilderBase(SpecificExceptionBase specificExceptionBase) {
        super(specificExceptionBase.getSchema(), SpecificData.get());
        Object value = specificExceptionBase.getValue();
        if (value != null) {
            setValue(value);
        }
        Throwable cause = specificExceptionBase.getCause();
        if (cause != null) {
            setCause(cause);
        }
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public SpecificErrorBuilderBase clearCause() {
        this.cause = null;
        this.hasCause = false;
        return this;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public SpecificErrorBuilderBase clearValue() {
        this.value = null;
        this.hasValue = false;
        return this;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public Throwable getCause() {
        return this.cause;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public Object getValue() {
        return this.value;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public boolean hasCause() {
        return this.hasCause;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public boolean hasValue() {
        return this.hasValue;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public SpecificErrorBuilderBase setCause(Throwable th) {
        this.cause = th;
        this.hasCause = true;
        return this;
    }

    @Override // com.flurry.org.apache.avro.data.ErrorBuilder
    public SpecificErrorBuilderBase setValue(Object obj) {
        this.value = obj;
        this.hasValue = true;
        return this;
    }
}
