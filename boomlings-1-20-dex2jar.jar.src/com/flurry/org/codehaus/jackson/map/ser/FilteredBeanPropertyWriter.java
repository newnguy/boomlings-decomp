package com.flurry.org.codehaus.jackson.map.ser;

public abstract class FilteredBeanPropertyWriter {
  public static BeanPropertyWriter constructViewBased(BeanPropertyWriter paramBeanPropertyWriter, Class[] paramArrayOfClass) {
    return (BeanPropertyWriter)((paramArrayOfClass.length == 1) ? new FilteredBeanPropertyWriter$SingleView(paramBeanPropertyWriter, paramArrayOfClass[0]) : new FilteredBeanPropertyWriter$MultiView(paramBeanPropertyWriter, paramArrayOfClass));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\FilteredBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */