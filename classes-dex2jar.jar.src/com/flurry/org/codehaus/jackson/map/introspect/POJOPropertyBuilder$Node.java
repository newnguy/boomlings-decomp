package com.flurry.org.codehaus.jackson.map.introspect;

final class POJOPropertyBuilder$Node {
  public final String explicitName;
  
  public final boolean isMarkedIgnored;
  
  public final boolean isVisible;
  
  public final POJOPropertyBuilder$Node next;
  
  public final Object value;
  
  public POJOPropertyBuilder$Node(Object paramObject, POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this.value = paramObject;
    this.next = paramPOJOPropertyBuilder$Node;
    if (paramString == null) {
      this.explicitName = null;
    } else {
      paramObject = paramString;
      if (paramString.length() == 0)
        paramObject = null; 
      this.explicitName = (String)paramObject;
    } 
    this.isVisible = paramBoolean1;
    this.isMarkedIgnored = paramBoolean2;
  }
  
  private POJOPropertyBuilder$Node append(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    return (this.next == null) ? withNext(paramPOJOPropertyBuilder$Node) : withNext(this.next.append(paramPOJOPropertyBuilder$Node));
  }
  
  public String toString() {
    String str2 = this.value.toString() + "[visible=" + this.isVisible + "]";
    String str1 = str2;
    if (this.next != null)
      str1 = str2 + ", " + this.next.toString(); 
    return str1;
  }
  
  public POJOPropertyBuilder$Node trimByVisibility() {
    if (this.next == null)
      return this; 
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node2 = this.next.trimByVisibility();
    if (this.explicitName != null)
      return (pOJOPropertyBuilder$Node2.explicitName == null) ? withNext(null) : withNext(pOJOPropertyBuilder$Node2); 
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node1 = pOJOPropertyBuilder$Node2;
    if (pOJOPropertyBuilder$Node2.explicitName == null) {
      if (this.isVisible == pOJOPropertyBuilder$Node2.isVisible)
        return withNext(pOJOPropertyBuilder$Node2); 
      pOJOPropertyBuilder$Node1 = pOJOPropertyBuilder$Node2;
      if (this.isVisible)
        pOJOPropertyBuilder$Node1 = withNext(null); 
    } 
    return pOJOPropertyBuilder$Node1;
  }
  
  public POJOPropertyBuilder$Node withNext(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    return (paramPOJOPropertyBuilder$Node == this.next) ? this : new POJOPropertyBuilder$Node(this.value, paramPOJOPropertyBuilder$Node, this.explicitName, this.isVisible, this.isMarkedIgnored);
  }
  
  public POJOPropertyBuilder$Node withValue(Object paramObject) {
    return (paramObject == this.value) ? this : new POJOPropertyBuilder$Node(paramObject, this.next, this.explicitName, this.isVisible, this.isMarkedIgnored);
  }
  
  public POJOPropertyBuilder$Node withoutIgnored() {
    if (this.isMarkedIgnored)
      return (this.next == null) ? null : this.next.withoutIgnored(); 
    if (this.next != null) {
      POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = this.next.withoutIgnored();
      if (pOJOPropertyBuilder$Node != this.next)
        return withNext(pOJOPropertyBuilder$Node); 
    } 
    return this;
  }
  
  public POJOPropertyBuilder$Node withoutNonVisible() {
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node1;
    if (this.next == null) {
      pOJOPropertyBuilder$Node1 = null;
    } else {
      pOJOPropertyBuilder$Node1 = this.next.withoutNonVisible();
    } 
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node2 = pOJOPropertyBuilder$Node1;
    if (this.isVisible)
      pOJOPropertyBuilder$Node2 = withNext(pOJOPropertyBuilder$Node1); 
    return pOJOPropertyBuilder$Node2;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\POJOPropertyBuilder$Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */