package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashSet;

@JacksonStdImpl
@Deprecated
public class MapSerializer extends MapSerializer {
  protected MapSerializer() {
    this((HashSet)null, null, null, false, null, null, null, null);
  }
  
  @Deprecated
  protected MapSerializer(HashSet paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer, BeanProperty paramBeanProperty) {
    super(paramHashSet, paramJavaType1, paramJavaType2, paramBoolean, paramTypeSerializer, paramJsonSerializer, null, paramBeanProperty);
  }
  
  protected MapSerializer(HashSet paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer1, JsonSerializer paramJsonSerializer2, BeanProperty paramBeanProperty) {
    super(paramHashSet, paramJavaType1, paramJavaType2, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
  }
  
  @Deprecated
  protected MapSerializer(HashSet paramHashSet, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer) {
    super(paramHashSet, UNSPECIFIED_TYPE, paramJavaType, paramBoolean, paramTypeSerializer, null, null, null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */