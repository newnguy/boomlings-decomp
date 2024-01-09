package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;
import java.util.Map;

public class AdRequest$Builder extends SpecificRecordBuilderBase implements RecordBuilder {
  private CharSequence a;
  
  private CharSequence b;
  
  private CharSequence c;
  
  private long d;
  
  private List e;
  
  private Location f;
  
  private boolean g;
  
  private List h;
  
  private AdViewContainer i;
  
  private CharSequence j;
  
  private CharSequence k;
  
  private CharSequence l;
  
  private CharSequence m;
  
  private TestAds n;
  
  private Map o;
  
  private boolean p;
  
  private AdRequest$Builder(byte paramByte) {
    super(AdRequest.SCHEMA$);
  }
  
  public AdRequest build() {
    try {
      long l;
      CharSequence charSequence2;
      List list2;
      Location location;
      List list1;
      AdViewContainer adViewContainer;
      CharSequence charSequence1;
      TestAds testAds;
      Map map;
      AdRequest adRequest = new AdRequest();
      this();
      if (fieldSetFlags()[0]) {
        charSequence2 = this.a;
      } else {
        charSequence2 = (CharSequence)defaultValue(fields()[0]);
      } 
      adRequest.a = charSequence2;
      if (fieldSetFlags()[1]) {
        charSequence2 = this.b;
      } else {
        charSequence2 = (CharSequence)defaultValue(fields()[1]);
      } 
      adRequest.b = charSequence2;
      if (fieldSetFlags()[2]) {
        charSequence2 = this.c;
      } else {
        charSequence2 = (CharSequence)defaultValue(fields()[2]);
      } 
      adRequest.c = charSequence2;
      if (fieldSetFlags()[3]) {
        l = this.d;
      } else {
        l = ((Long)defaultValue(fields()[3])).longValue();
      } 
      adRequest.d = l;
      if (fieldSetFlags()[4]) {
        list2 = this.e;
      } else {
        list2 = (List)defaultValue(fields()[4]);
      } 
      adRequest.e = list2;
      if (fieldSetFlags()[5]) {
        location = this.f;
      } else {
        location = (Location)defaultValue(fields()[5]);
      } 
      adRequest.f = location;
      if (fieldSetFlags()[6]) {
        bool = this.g;
      } else {
        bool = ((Boolean)defaultValue(fields()[6])).booleanValue();
      } 
      adRequest.g = bool;
      if (fieldSetFlags()[7]) {
        list1 = this.h;
      } else {
        list1 = (List)defaultValue(fields()[7]);
      } 
      adRequest.h = list1;
      if (fieldSetFlags()[8]) {
        adViewContainer = this.i;
      } else {
        adViewContainer = (AdViewContainer)defaultValue(fields()[8]);
      } 
      adRequest.i = adViewContainer;
      if (fieldSetFlags()[9]) {
        charSequence1 = this.j;
      } else {
        charSequence1 = (CharSequence)defaultValue(fields()[9]);
      } 
      adRequest.j = charSequence1;
      if (fieldSetFlags()[10]) {
        charSequence1 = this.k;
      } else {
        charSequence1 = (CharSequence)defaultValue(fields()[10]);
      } 
      adRequest.k = charSequence1;
      if (fieldSetFlags()[11]) {
        charSequence1 = this.l;
      } else {
        charSequence1 = (CharSequence)defaultValue(fields()[11]);
      } 
      adRequest.l = charSequence1;
      if (fieldSetFlags()[12]) {
        charSequence1 = this.m;
      } else {
        charSequence1 = (CharSequence)defaultValue(fields()[12]);
      } 
      adRequest.m = charSequence1;
      if (fieldSetFlags()[13]) {
        testAds = this.n;
      } else {
        testAds = (TestAds)defaultValue(fields()[13]);
      } 
      adRequest.n = testAds;
      if (fieldSetFlags()[14]) {
        map = this.o;
      } else {
        map = (Map)defaultValue(fields()[14]);
      } 
      adRequest.o = map;
      if (fieldSetFlags()[15]) {
        bool = this.p;
        adRequest.p = bool;
        return adRequest;
      } 
      boolean bool = ((Boolean)defaultValue(fields()[15])).booleanValue();
      adRequest.p = bool;
      return adRequest;
    } catch (Exception exception) {
      throw new AvroRuntimeException(exception);
    } 
  }
  
  public AdRequest$Builder clearAdReportedIds() {
    this.e = null;
    fieldSetFlags()[4] = false;
    return this;
  }
  
  public AdRequest$Builder clearAdSpaceName() {
    this.c = null;
    fieldSetFlags()[2] = false;
    return this;
  }
  
  public AdRequest$Builder clearAdViewContainer() {
    this.i = null;
    fieldSetFlags()[8] = false;
    return this;
  }
  
  public AdRequest$Builder clearAgentVersion() {
    this.b = null;
    fieldSetFlags()[1] = false;
    return this;
  }
  
  public AdRequest$Builder clearApiKey() {
    this.a = null;
    fieldSetFlags()[0] = false;
    return this;
  }
  
  public AdRequest$Builder clearBindings() {
    this.h = null;
    fieldSetFlags()[7] = false;
    return this;
  }
  
  public AdRequest$Builder clearDevicePlatform() {
    this.m = null;
    fieldSetFlags()[12] = false;
    return this;
  }
  
  public AdRequest$Builder clearKeywords() {
    this.o = null;
    fieldSetFlags()[14] = false;
    return this;
  }
  
  public AdRequest$Builder clearLocale() {
    this.j = null;
    fieldSetFlags()[9] = false;
    return this;
  }
  
  public AdRequest$Builder clearLocation() {
    this.f = null;
    fieldSetFlags()[5] = false;
    return this;
  }
  
  public AdRequest$Builder clearOsVersion() {
    this.l = null;
    fieldSetFlags()[11] = false;
    return this;
  }
  
  public AdRequest$Builder clearRefresh() {
    fieldSetFlags()[15] = false;
    return this;
  }
  
  public AdRequest$Builder clearSessionId() {
    fieldSetFlags()[3] = false;
    return this;
  }
  
  public AdRequest$Builder clearTestAds() {
    this.n = null;
    fieldSetFlags()[13] = false;
    return this;
  }
  
  public AdRequest$Builder clearTestDevice() {
    fieldSetFlags()[6] = false;
    return this;
  }
  
  public AdRequest$Builder clearTimezone() {
    this.k = null;
    fieldSetFlags()[10] = false;
    return this;
  }
  
  public List getAdReportedIds() {
    return this.e;
  }
  
  public CharSequence getAdSpaceName() {
    return this.c;
  }
  
  public AdViewContainer getAdViewContainer() {
    return this.i;
  }
  
  public CharSequence getAgentVersion() {
    return this.b;
  }
  
  public CharSequence getApiKey() {
    return this.a;
  }
  
  public List getBindings() {
    return this.h;
  }
  
  public CharSequence getDevicePlatform() {
    return this.m;
  }
  
  public Map getKeywords() {
    return this.o;
  }
  
  public CharSequence getLocale() {
    return this.j;
  }
  
  public Location getLocation() {
    return this.f;
  }
  
  public CharSequence getOsVersion() {
    return this.l;
  }
  
  public Boolean getRefresh() {
    return Boolean.valueOf(this.p);
  }
  
  public Long getSessionId() {
    return Long.valueOf(this.d);
  }
  
  public TestAds getTestAds() {
    return this.n;
  }
  
  public Boolean getTestDevice() {
    return Boolean.valueOf(this.g);
  }
  
  public CharSequence getTimezone() {
    return this.k;
  }
  
  public boolean hasAdReportedIds() {
    return fieldSetFlags()[4];
  }
  
  public boolean hasAdSpaceName() {
    return fieldSetFlags()[2];
  }
  
  public boolean hasAdViewContainer() {
    return fieldSetFlags()[8];
  }
  
  public boolean hasAgentVersion() {
    return fieldSetFlags()[1];
  }
  
  public boolean hasApiKey() {
    return fieldSetFlags()[0];
  }
  
  public boolean hasBindings() {
    return fieldSetFlags()[7];
  }
  
  public boolean hasDevicePlatform() {
    return fieldSetFlags()[12];
  }
  
  public boolean hasKeywords() {
    return fieldSetFlags()[14];
  }
  
  public boolean hasLocale() {
    return fieldSetFlags()[9];
  }
  
  public boolean hasLocation() {
    return fieldSetFlags()[5];
  }
  
  public boolean hasOsVersion() {
    return fieldSetFlags()[11];
  }
  
  public boolean hasRefresh() {
    return fieldSetFlags()[15];
  }
  
  public boolean hasSessionId() {
    return fieldSetFlags()[3];
  }
  
  public boolean hasTestAds() {
    return fieldSetFlags()[13];
  }
  
  public boolean hasTestDevice() {
    return fieldSetFlags()[6];
  }
  
  public boolean hasTimezone() {
    return fieldSetFlags()[10];
  }
  
  public AdRequest$Builder setAdReportedIds(List paramList) {
    validate(fields()[4], paramList);
    this.e = paramList;
    fieldSetFlags()[4] = true;
    return this;
  }
  
  public AdRequest$Builder setAdSpaceName(CharSequence paramCharSequence) {
    validate(fields()[2], paramCharSequence);
    this.c = paramCharSequence;
    fieldSetFlags()[2] = true;
    return this;
  }
  
  public AdRequest$Builder setAdViewContainer(AdViewContainer paramAdViewContainer) {
    validate(fields()[8], paramAdViewContainer);
    this.i = paramAdViewContainer;
    fieldSetFlags()[8] = true;
    return this;
  }
  
  public AdRequest$Builder setAgentVersion(CharSequence paramCharSequence) {
    validate(fields()[1], paramCharSequence);
    this.b = paramCharSequence;
    fieldSetFlags()[1] = true;
    return this;
  }
  
  public AdRequest$Builder setApiKey(CharSequence paramCharSequence) {
    validate(fields()[0], paramCharSequence);
    this.a = paramCharSequence;
    fieldSetFlags()[0] = true;
    return this;
  }
  
  public AdRequest$Builder setBindings(List paramList) {
    validate(fields()[7], paramList);
    this.h = paramList;
    fieldSetFlags()[7] = true;
    return this;
  }
  
  public AdRequest$Builder setDevicePlatform(CharSequence paramCharSequence) {
    validate(fields()[12], paramCharSequence);
    this.m = paramCharSequence;
    fieldSetFlags()[12] = true;
    return this;
  }
  
  public AdRequest$Builder setKeywords(Map paramMap) {
    validate(fields()[14], paramMap);
    this.o = paramMap;
    fieldSetFlags()[14] = true;
    return this;
  }
  
  public AdRequest$Builder setLocale(CharSequence paramCharSequence) {
    validate(fields()[9], paramCharSequence);
    this.j = paramCharSequence;
    fieldSetFlags()[9] = true;
    return this;
  }
  
  public AdRequest$Builder setLocation(Location paramLocation) {
    validate(fields()[5], paramLocation);
    this.f = paramLocation;
    fieldSetFlags()[5] = true;
    return this;
  }
  
  public AdRequest$Builder setOsVersion(CharSequence paramCharSequence) {
    validate(fields()[11], paramCharSequence);
    this.l = paramCharSequence;
    fieldSetFlags()[11] = true;
    return this;
  }
  
  public AdRequest$Builder setRefresh(boolean paramBoolean) {
    validate(fields()[15], Boolean.valueOf(paramBoolean));
    this.p = paramBoolean;
    fieldSetFlags()[15] = true;
    return this;
  }
  
  public AdRequest$Builder setSessionId(long paramLong) {
    validate(fields()[3], Long.valueOf(paramLong));
    this.d = paramLong;
    fieldSetFlags()[3] = true;
    return this;
  }
  
  public AdRequest$Builder setTestAds(TestAds paramTestAds) {
    validate(fields()[13], paramTestAds);
    this.n = paramTestAds;
    fieldSetFlags()[13] = true;
    return this;
  }
  
  public AdRequest$Builder setTestDevice(boolean paramBoolean) {
    validate(fields()[6], Boolean.valueOf(paramBoolean));
    this.g = paramBoolean;
    fieldSetFlags()[6] = true;
    return this;
  }
  
  public AdRequest$Builder setTimezone(CharSequence paramCharSequence) {
    validate(fields()[10], paramCharSequence);
    this.k = paramCharSequence;
    fieldSetFlags()[10] = true;
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\AdRequest$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */