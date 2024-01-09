package com.flurry.android;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.data.RecordBuilder;
import com.flurry.org.apache.avro.specific.SpecificRecord;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import com.flurry.org.apache.avro.specific.SpecificRecordBuilderBase;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AdRequest extends SpecificRecordBase implements SpecificRecord {
    public static final Schema SCHEMA$ = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdRequest\",\"namespace\":\"com.flurry.android\",\"fields\":[{\"name\":\"apiKey\",\"type\":\"string\"},{\"name\":\"agentVersion\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"adSpaceName\",\"type\":\"string\"},{\"name\":\"sessionId\",\"type\":\"long\"},{\"name\":\"adReportedIds\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AdReportedId\",\"fields\":[{\"name\":\"type\",\"type\":\"int\"},{\"name\":\"id\",\"type\":\"bytes\"}]}}},{\"name\":\"location\",\"type\":{\"type\":\"record\",\"name\":\"Location\",\"fields\":[{\"name\":\"lat\",\"type\":\"float\",\"default\":0.0},{\"name\":\"lon\",\"type\":\"float\",\"default\":0.0}]},\"default\":\"null\"},{\"name\":\"testDevice\",\"type\":\"boolean\",\"default\":false},{\"name\":\"bindings\",\"type\":{\"type\":\"array\",\"items\":\"int\"}},{\"name\":\"adViewContainer\",\"type\":{\"type\":\"record\",\"name\":\"AdViewContainer\",\"fields\":[{\"name\":\"viewWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"viewHeight\",\"type\":\"int\",\"default\":0},{\"name\":\"screenWidth\",\"type\":\"int\",\"default\":0},{\"name\":\"screenHeight\",\"type\":\"int\",\"default\":0}]},\"default\":\"null\"},{\"name\":\"locale\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"timezone\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"osVersion\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"devicePlatform\",\"type\":\"string\",\"default\":\"null\"},{\"name\":\"testAds\",\"type\":{\"type\":\"record\",\"name\":\"TestAds\",\"fields\":[{\"name\":\"adspacePlacement\",\"type\":\"int\",\"default\":0}]},\"default\":\"null\"},{\"name\":\"keywords\",\"type\":{\"type\":\"map\",\"values\":\"string\"},\"default\":[]},{\"name\":\"refresh\",\"type\":\"boolean\",\"default\":false}]}");
    public CharSequence a;
    public CharSequence b;
    public CharSequence c;
    public long d;
    public List e;
    public Location f;
    public boolean g;
    public List h;
    public AdViewContainer i;
    public CharSequence j;
    public CharSequence k;
    public CharSequence l;
    public CharSequence m;
    public TestAds n;
    public Map o;
    public boolean p;

    /* loaded from: classes.dex */
    public class Builder extends SpecificRecordBuilderBase implements RecordBuilder {
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

        /* synthetic */ Builder() {
            this((byte) 0);
        }

        private Builder(byte b) {
            super(AdRequest.SCHEMA$);
        }

        @Override // com.flurry.org.apache.avro.data.RecordBuilder
        public AdRequest build() {
            try {
                AdRequest adRequest = new AdRequest();
                adRequest.a = fieldSetFlags()[0] ? this.a : (CharSequence) defaultValue(fields()[0]);
                adRequest.b = fieldSetFlags()[1] ? this.b : (CharSequence) defaultValue(fields()[1]);
                adRequest.c = fieldSetFlags()[2] ? this.c : (CharSequence) defaultValue(fields()[2]);
                adRequest.d = fieldSetFlags()[3] ? this.d : ((Long) defaultValue(fields()[3])).longValue();
                adRequest.e = fieldSetFlags()[4] ? this.e : (List) defaultValue(fields()[4]);
                adRequest.f = fieldSetFlags()[5] ? this.f : (Location) defaultValue(fields()[5]);
                adRequest.g = fieldSetFlags()[6] ? this.g : ((Boolean) defaultValue(fields()[6])).booleanValue();
                adRequest.h = fieldSetFlags()[7] ? this.h : (List) defaultValue(fields()[7]);
                adRequest.i = fieldSetFlags()[8] ? this.i : (AdViewContainer) defaultValue(fields()[8]);
                adRequest.j = fieldSetFlags()[9] ? this.j : (CharSequence) defaultValue(fields()[9]);
                adRequest.k = fieldSetFlags()[10] ? this.k : (CharSequence) defaultValue(fields()[10]);
                adRequest.l = fieldSetFlags()[11] ? this.l : (CharSequence) defaultValue(fields()[11]);
                adRequest.m = fieldSetFlags()[12] ? this.m : (CharSequence) defaultValue(fields()[12]);
                adRequest.n = fieldSetFlags()[13] ? this.n : (TestAds) defaultValue(fields()[13]);
                adRequest.o = fieldSetFlags()[14] ? this.o : (Map) defaultValue(fields()[14]);
                adRequest.p = fieldSetFlags()[15] ? this.p : ((Boolean) defaultValue(fields()[15])).booleanValue();
                return adRequest;
            } catch (Exception e) {
                throw new AvroRuntimeException(e);
            }
        }

        public Builder clearAdReportedIds() {
            this.e = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        public Builder clearAdSpaceName() {
            this.c = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        public Builder clearAdViewContainer() {
            this.i = null;
            fieldSetFlags()[8] = false;
            return this;
        }

        public Builder clearAgentVersion() {
            this.b = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        public Builder clearApiKey() {
            this.a = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        public Builder clearBindings() {
            this.h = null;
            fieldSetFlags()[7] = false;
            return this;
        }

        public Builder clearDevicePlatform() {
            this.m = null;
            fieldSetFlags()[12] = false;
            return this;
        }

        public Builder clearKeywords() {
            this.o = null;
            fieldSetFlags()[14] = false;
            return this;
        }

        public Builder clearLocale() {
            this.j = null;
            fieldSetFlags()[9] = false;
            return this;
        }

        public Builder clearLocation() {
            this.f = null;
            fieldSetFlags()[5] = false;
            return this;
        }

        public Builder clearOsVersion() {
            this.l = null;
            fieldSetFlags()[11] = false;
            return this;
        }

        public Builder clearRefresh() {
            fieldSetFlags()[15] = false;
            return this;
        }

        public Builder clearSessionId() {
            fieldSetFlags()[3] = false;
            return this;
        }

        public Builder clearTestAds() {
            this.n = null;
            fieldSetFlags()[13] = false;
            return this;
        }

        public Builder clearTestDevice() {
            fieldSetFlags()[6] = false;
            return this;
        }

        public Builder clearTimezone() {
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

        public Builder setAdReportedIds(List list) {
            validate(fields()[4], list);
            this.e = list;
            fieldSetFlags()[4] = true;
            return this;
        }

        public Builder setAdSpaceName(CharSequence charSequence) {
            validate(fields()[2], charSequence);
            this.c = charSequence;
            fieldSetFlags()[2] = true;
            return this;
        }

        public Builder setAdViewContainer(AdViewContainer adViewContainer) {
            validate(fields()[8], adViewContainer);
            this.i = adViewContainer;
            fieldSetFlags()[8] = true;
            return this;
        }

        public Builder setAgentVersion(CharSequence charSequence) {
            validate(fields()[1], charSequence);
            this.b = charSequence;
            fieldSetFlags()[1] = true;
            return this;
        }

        public Builder setApiKey(CharSequence charSequence) {
            validate(fields()[0], charSequence);
            this.a = charSequence;
            fieldSetFlags()[0] = true;
            return this;
        }

        public Builder setBindings(List list) {
            validate(fields()[7], list);
            this.h = list;
            fieldSetFlags()[7] = true;
            return this;
        }

        public Builder setDevicePlatform(CharSequence charSequence) {
            validate(fields()[12], charSequence);
            this.m = charSequence;
            fieldSetFlags()[12] = true;
            return this;
        }

        public Builder setKeywords(Map map) {
            validate(fields()[14], map);
            this.o = map;
            fieldSetFlags()[14] = true;
            return this;
        }

        public Builder setLocale(CharSequence charSequence) {
            validate(fields()[9], charSequence);
            this.j = charSequence;
            fieldSetFlags()[9] = true;
            return this;
        }

        public Builder setLocation(Location location) {
            validate(fields()[5], location);
            this.f = location;
            fieldSetFlags()[5] = true;
            return this;
        }

        public Builder setOsVersion(CharSequence charSequence) {
            validate(fields()[11], charSequence);
            this.l = charSequence;
            fieldSetFlags()[11] = true;
            return this;
        }

        public Builder setRefresh(boolean z) {
            validate(fields()[15], Boolean.valueOf(z));
            this.p = z;
            fieldSetFlags()[15] = true;
            return this;
        }

        public Builder setSessionId(long j) {
            validate(fields()[3], Long.valueOf(j));
            this.d = j;
            fieldSetFlags()[3] = true;
            return this;
        }

        public Builder setTestAds(TestAds testAds) {
            validate(fields()[13], testAds);
            this.n = testAds;
            fieldSetFlags()[13] = true;
            return this;
        }

        public Builder setTestDevice(boolean z) {
            validate(fields()[6], Boolean.valueOf(z));
            this.g = z;
            fieldSetFlags()[6] = true;
            return this;
        }

        public Builder setTimezone(CharSequence charSequence) {
            validate(fields()[10], charSequence);
            this.k = charSequence;
            fieldSetFlags()[10] = true;
            return this;
        }
    }

    AdRequest() {
    }

    public static Builder a() {
        return new Builder();
    }

    public final void a(TestAds testAds) {
        this.n = testAds;
    }

    public final void a(Boolean bool) {
        this.p = bool.booleanValue();
    }

    public final void a(CharSequence charSequence) {
        this.c = charSequence;
    }

    public final void a(Map map) {
        this.o = map;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public Object get(int i) {
        switch (i) {
            case 0:
                return this.a;
            case 1:
                return this.b;
            case 2:
                return this.c;
            case 3:
                return Long.valueOf(this.d);
            case 4:
                return this.e;
            case 5:
                return this.f;
            case 6:
                return Boolean.valueOf(this.g);
            case 7:
                return this.h;
            case 8:
                return this.i;
            case 9:
                return this.j;
            case 10:
                return this.k;
            case 11:
                return this.l;
            case 12:
                return this.m;
            case 13:
                return this.n;
            case 14:
                return this.o;
            case 15:
                return Boolean.valueOf(this.p);
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.GenericContainer
    public Schema getSchema() {
        return SCHEMA$;
    }

    @Override // com.flurry.org.apache.avro.specific.SpecificRecordBase, com.flurry.org.apache.avro.generic.IndexedRecord
    public void put(int i, Object obj) {
        switch (i) {
            case 0:
                this.a = (CharSequence) obj;
                return;
            case 1:
                this.b = (CharSequence) obj;
                return;
            case 2:
                this.c = (CharSequence) obj;
                return;
            case 3:
                this.d = ((Long) obj).longValue();
                return;
            case 4:
                this.e = (List) obj;
                return;
            case 5:
                this.f = (Location) obj;
                return;
            case 6:
                this.g = ((Boolean) obj).booleanValue();
                return;
            case 7:
                this.h = (List) obj;
                return;
            case 8:
                this.i = (AdViewContainer) obj;
                return;
            case 9:
                this.j = (CharSequence) obj;
                return;
            case 10:
                this.k = (CharSequence) obj;
                return;
            case 11:
                this.l = (CharSequence) obj;
                return;
            case 12:
                this.m = (CharSequence) obj;
                return;
            case 13:
                this.n = (TestAds) obj;
                return;
            case 14:
                this.o = (Map) obj;
                return;
            case 15:
                this.p = ((Boolean) obj).booleanValue();
                return;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }
}
