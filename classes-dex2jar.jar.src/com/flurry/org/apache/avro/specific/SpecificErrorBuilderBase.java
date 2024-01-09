package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.ErrorBuilder;
import com.flurry.org.apache.avro.data.RecordBuilderBase;
import java.lang.reflect.Constructor;

public abstract class SpecificErrorBuilderBase extends RecordBuilderBase implements ErrorBuilder {
  private Throwable cause;
  
  private Constructor errorConstructor;
  
  private boolean hasCause;
  
  private boolean hasValue;
  
  private Object value;
  
  protected SpecificErrorBuilderBase(Schema paramSchema) {
    super(paramSchema, SpecificData.get());
  }
  
  protected SpecificErrorBuilderBase(SpecificErrorBuilderBase paramSpecificErrorBuilderBase) {
    super(paramSpecificErrorBuilderBase, SpecificData.get());
    this.errorConstructor = paramSpecificErrorBuilderBase.errorConstructor;
    this.value = paramSpecificErrorBuilderBase.value;
    this.hasValue = paramSpecificErrorBuilderBase.hasValue;
    this.cause = paramSpecificErrorBuilderBase.cause;
    this.hasCause = paramSpecificErrorBuilderBase.hasCause;
  }
  
  protected SpecificErrorBuilderBase(SpecificExceptionBase paramSpecificExceptionBase) {
    super(paramSpecificExceptionBase.getSchema(), SpecificData.get());
    Object object = paramSpecificExceptionBase.getValue();
    if (object != null)
      setValue(object); 
    Throwable throwable = paramSpecificExceptionBase.getCause();
    if (throwable != null)
      setCause(throwable); 
  }
  
  public SpecificErrorBuilderBase clearCause() {
    this.cause = null;
    this.hasCause = false;
    return this;
  }
  
  public SpecificErrorBuilderBase clearValue() {
    this.value = null;
    this.hasValue = false;
    return this;
  }
  
  public Throwable getCause() {
    return this.cause;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public boolean hasCause() {
    return this.hasCause;
  }
  
  public boolean hasValue() {
    return this.hasValue;
  }
  
  public SpecificErrorBuilderBase setCause(Throwable paramThrowable) {
    this.cause = paramThrowable;
    this.hasCause = true;
    return this;
  }
  
  public SpecificErrorBuilderBase setValue(Object paramObject) {
    this.value = paramObject;
    this.hasValue = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificErrorBuilderBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */