package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

public class BeanSerializerFactory$ConfigImpl extends SerializerFactory.Config {
  protected static final BeanSerializerModifier[] NO_MODIFIERS;
  
  protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
  
  protected final Serializers[] _additionalKeySerializers;
  
  protected final Serializers[] _additionalSerializers;
  
  protected final BeanSerializerModifier[] _modifiers;
  
  static {
    NO_MODIFIERS = new BeanSerializerModifier[0];
  }
  
  public BeanSerializerFactory$ConfigImpl() {
    this(null, null, null);
  }
  
  protected BeanSerializerFactory$ConfigImpl(Serializers[] paramArrayOfSerializers1, Serializers[] paramArrayOfSerializers2, BeanSerializerModifier[] paramArrayOfBeanSerializerModifier) {
    Serializers[] arrayOfSerializers = paramArrayOfSerializers1;
    if (paramArrayOfSerializers1 == null)
      arrayOfSerializers = NO_SERIALIZERS; 
    this._additionalSerializers = arrayOfSerializers;
    paramArrayOfSerializers1 = paramArrayOfSerializers2;
    if (paramArrayOfSerializers2 == null)
      paramArrayOfSerializers1 = NO_SERIALIZERS; 
    this._additionalKeySerializers = paramArrayOfSerializers1;
    BeanSerializerModifier[] arrayOfBeanSerializerModifier = paramArrayOfBeanSerializerModifier;
    if (paramArrayOfBeanSerializerModifier == null)
      arrayOfBeanSerializerModifier = NO_MODIFIERS; 
    this._modifiers = arrayOfBeanSerializerModifier;
  }
  
  public boolean hasKeySerializers() {
    return (this._additionalKeySerializers.length > 0);
  }
  
  public boolean hasSerializerModifiers() {
    return (this._modifiers.length > 0);
  }
  
  public boolean hasSerializers() {
    return (this._additionalSerializers.length > 0);
  }
  
  public Iterable keySerializers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._additionalKeySerializers);
  }
  
  public Iterable serializerModifiers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._modifiers);
  }
  
  public Iterable serializers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._additionalSerializers);
  }
  
  public SerializerFactory.Config withAdditionalKeySerializers(Serializers paramSerializers) {
    if (paramSerializers == null)
      throw new IllegalArgumentException("Can not pass null Serializers"); 
    Serializers[] arrayOfSerializers = (Serializers[])ArrayBuilders.insertInListNoDup((Object[])this._additionalKeySerializers, paramSerializers);
    return new BeanSerializerFactory$ConfigImpl(this._additionalSerializers, arrayOfSerializers, this._modifiers);
  }
  
  public SerializerFactory.Config withAdditionalSerializers(Serializers paramSerializers) {
    if (paramSerializers == null)
      throw new IllegalArgumentException("Can not pass null Serializers"); 
    return new BeanSerializerFactory$ConfigImpl((Serializers[])ArrayBuilders.insertInListNoDup((Object[])this._additionalSerializers, paramSerializers), this._additionalKeySerializers, this._modifiers);
  }
  
  public SerializerFactory.Config withSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier) {
    if (paramBeanSerializerModifier == null)
      throw new IllegalArgumentException("Can not pass null modifier"); 
    BeanSerializerModifier[] arrayOfBeanSerializerModifier = (BeanSerializerModifier[])ArrayBuilders.insertInListNoDup((Object[])this._modifiers, paramBeanSerializerModifier);
    return new BeanSerializerFactory$ConfigImpl(this._additionalSerializers, this._additionalKeySerializers, arrayOfBeanSerializerModifier);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanSerializerFactory$ConfigImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */