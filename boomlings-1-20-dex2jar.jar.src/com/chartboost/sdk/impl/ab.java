package com.chartboost.sdk.impl;

class ab extends aa {
  private bd a = new bd();
  
  void a(Class paramClass, af paramaf) {
    this.a.a(paramClass, paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    Object object2 = x.a(paramObject);
    if (object2 == null) {
      paramStringBuilder.append(" null ");
      return;
    } 
    paramObject = null;
    for (Object paramObject : bd.a(object2.getClass())) {
      af af = (af)this.a.a(paramObject);
      paramObject = af;
      if (af != null) {
        paramObject = af;
        break;
      } 
    } 
    Object object1 = paramObject;
    if (paramObject == null) {
      object1 = paramObject;
      if (object2.getClass().isArray())
        object1 = this.a.a(Object[].class); 
    } 
    if (object1 == null)
      throw new RuntimeException("json can't serialize type : " + object2.getClass()); 
    object1.a(object2, paramStringBuilder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */