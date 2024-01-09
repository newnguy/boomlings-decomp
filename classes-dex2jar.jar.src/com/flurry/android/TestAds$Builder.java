package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;

public class TestAds$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private int a;
  
  private TestAds$Builder(byte paramByte) {
    super(TestAds.SCHEMA$);
  }
  
  public TestAds build() {
    try {
      TestAds testAds = new TestAds();
      this();
      if (fieldSetFlags()[0]) {
        int j = this.a;
        testAds.a = j;
        return testAds;
      } 
      int i = ((Integer)defaultValue(fields()[0])).intValue();
      testAds.a = i;
      return testAds;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public TestAds$Builder clearAdspacePlacement() {
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public Integer getAdspacePlacement() {
    return Integer.valueOf(this.a);
  }
  
  public boolean hasAdspacePlacement() {
    return fieldSetFlags()[0];
  }
  
  public TestAds$Builder setAdspacePlacement(int paramInt) {
    validate(fields()[0], Integer.valueOf(paramInt));
    this.a = paramInt;
    fieldSetFlags()[0] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\TestAds$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */