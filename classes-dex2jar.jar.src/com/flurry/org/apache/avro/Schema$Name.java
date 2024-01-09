package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonGenerator;

class Schema$Name {
  private final String full;
  
  private final String name;
  
  private final String space;
  
  public Schema$Name(String paramString1, String paramString2) {
    if (paramString1 == null) {
      this.full = null;
      this.space = null;
      this.name = null;
      return;
    } 
    int i = paramString1.lastIndexOf('.');
    if (i < 0) {
      this.space = paramString2;
      this.name = Schema.access$200(paramString1);
    } else {
      this.space = paramString1.substring(0, i);
      this.name = Schema.access$200(paramString1.substring(i + 1, paramString1.length()));
    } 
    if (this.space == null) {
      paramString1 = this.name;
    } else {
      paramString1 = this.space + "." + this.name;
    } 
    this.full = paramString1;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema$Name))
        return false; 
      paramObject = paramObject;
      if (this.full == null) {
        if (((Schema$Name)paramObject).full != null)
          bool = false; 
        return bool;
      } 
      bool = this.full.equals(((Schema$Name)paramObject).full);
    } 
    return bool;
  }
  
  public String getQualified(String paramString) {
    return (this.space == null || this.space.equals(paramString)) ? this.name : this.full;
  }
  
  public int hashCode() {
    return (this.full == null) ? 0 : this.full.hashCode();
  }
  
  public String toString() {
    return this.full;
  }
  
  public void writeName(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (this.name != null)
      paramJsonGenerator.writeStringField("name", this.name); 
    if (this.space != null) {
      if (!this.space.equals(paramSchema$Names.space()))
        paramJsonGenerator.writeStringField("namespace", this.space); 
      if (paramSchema$Names.space() == null)
        paramSchema$Names.space(this.space); 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */