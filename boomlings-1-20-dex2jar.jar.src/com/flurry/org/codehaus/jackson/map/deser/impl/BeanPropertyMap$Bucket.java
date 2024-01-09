package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

final class BeanPropertyMap$Bucket {
  public final String key;
  
  public final BeanPropertyMap$Bucket next;
  
  public final SettableBeanProperty value;
  
  public BeanPropertyMap$Bucket(BeanPropertyMap$Bucket paramBeanPropertyMap$Bucket, String paramString, SettableBeanProperty paramSettableBeanProperty) {
    this.next = paramBeanPropertyMap$Bucket;
    this.key = paramString;
    this.value = paramSettableBeanProperty;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\BeanPropertyMap$Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */