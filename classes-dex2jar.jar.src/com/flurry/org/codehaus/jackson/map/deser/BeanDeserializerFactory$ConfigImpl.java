package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

public class BeanDeserializerFactory$ConfigImpl extends DeserializerFactory.Config {
  protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS;
  
  protected static final KeyDeserializers[] NO_KEY_DESERIALIZERS = new KeyDeserializers[0];
  
  protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
  
  protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS;
  
  protected final AbstractTypeResolver[] _abstractTypeResolvers;
  
  protected final Deserializers[] _additionalDeserializers;
  
  protected final KeyDeserializers[] _additionalKeyDeserializers;
  
  protected final BeanDeserializerModifier[] _modifiers;
  
  protected final ValueInstantiators[] _valueInstantiators;
  
  static {
    NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
    NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
  }
  
  public BeanDeserializerFactory$ConfigImpl() {
    this(null, null, null, null, null);
  }
  
  protected BeanDeserializerFactory$ConfigImpl(Deserializers[] paramArrayOfDeserializers, KeyDeserializers[] paramArrayOfKeyDeserializers, BeanDeserializerModifier[] paramArrayOfBeanDeserializerModifier, AbstractTypeResolver[] paramArrayOfAbstractTypeResolver, ValueInstantiators[] paramArrayOfValueInstantiators) {
    Deserializers[] arrayOfDeserializers = paramArrayOfDeserializers;
    if (paramArrayOfDeserializers == null)
      arrayOfDeserializers = BeanDeserializerFactory.access$000(); 
    this._additionalDeserializers = arrayOfDeserializers;
    KeyDeserializers[] arrayOfKeyDeserializers = paramArrayOfKeyDeserializers;
    if (paramArrayOfKeyDeserializers == null)
      arrayOfKeyDeserializers = NO_KEY_DESERIALIZERS; 
    this._additionalKeyDeserializers = arrayOfKeyDeserializers;
    BeanDeserializerModifier[] arrayOfBeanDeserializerModifier = paramArrayOfBeanDeserializerModifier;
    if (paramArrayOfBeanDeserializerModifier == null)
      arrayOfBeanDeserializerModifier = NO_MODIFIERS; 
    this._modifiers = arrayOfBeanDeserializerModifier;
    AbstractTypeResolver[] arrayOfAbstractTypeResolver = paramArrayOfAbstractTypeResolver;
    if (paramArrayOfAbstractTypeResolver == null)
      arrayOfAbstractTypeResolver = NO_ABSTRACT_TYPE_RESOLVERS; 
    this._abstractTypeResolvers = arrayOfAbstractTypeResolver;
    ValueInstantiators[] arrayOfValueInstantiators = paramArrayOfValueInstantiators;
    if (paramArrayOfValueInstantiators == null)
      arrayOfValueInstantiators = NO_VALUE_INSTANTIATORS; 
    this._valueInstantiators = arrayOfValueInstantiators;
  }
  
  public Iterable abstractTypeResolvers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._abstractTypeResolvers);
  }
  
  public Iterable deserializerModifiers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._modifiers);
  }
  
  public Iterable deserializers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._additionalDeserializers);
  }
  
  public boolean hasAbstractTypeResolvers() {
    return (this._abstractTypeResolvers.length > 0);
  }
  
  public boolean hasDeserializerModifiers() {
    return (this._modifiers.length > 0);
  }
  
  public boolean hasDeserializers() {
    return (this._additionalDeserializers.length > 0);
  }
  
  public boolean hasKeyDeserializers() {
    return (this._additionalKeyDeserializers.length > 0);
  }
  
  public boolean hasValueInstantiators() {
    return (this._valueInstantiators.length > 0);
  }
  
  public Iterable keyDeserializers() {
    return ArrayBuilders.arrayAsIterable((Object[])this._additionalKeyDeserializers);
  }
  
  public Iterable valueInstantiators() {
    return ArrayBuilders.arrayAsIterable((Object[])this._valueInstantiators);
  }
  
  public DeserializerFactory.Config withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver) {
    if (paramAbstractTypeResolver == null)
      throw new IllegalArgumentException("Can not pass null resolver"); 
    AbstractTypeResolver[] arrayOfAbstractTypeResolver = (AbstractTypeResolver[])ArrayBuilders.insertInListNoDup((Object[])this._abstractTypeResolvers, paramAbstractTypeResolver);
    return new BeanDeserializerFactory$ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, arrayOfAbstractTypeResolver, this._valueInstantiators);
  }
  
  public DeserializerFactory.Config withAdditionalDeserializers(Deserializers paramDeserializers) {
    if (paramDeserializers == null)
      throw new IllegalArgumentException("Can not pass null Deserializers"); 
    return new BeanDeserializerFactory$ConfigImpl((Deserializers[])ArrayBuilders.insertInListNoDup((Object[])this._additionalDeserializers, paramDeserializers), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactory.Config withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers) {
    if (paramKeyDeserializers == null)
      throw new IllegalArgumentException("Can not pass null KeyDeserializers"); 
    KeyDeserializers[] arrayOfKeyDeserializers = (KeyDeserializers[])ArrayBuilders.insertInListNoDup((Object[])this._additionalKeyDeserializers, paramKeyDeserializers);
    return new BeanDeserializerFactory$ConfigImpl(this._additionalDeserializers, arrayOfKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactory.Config withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier) {
    if (paramBeanDeserializerModifier == null)
      throw new IllegalArgumentException("Can not pass null modifier"); 
    BeanDeserializerModifier[] arrayOfBeanDeserializerModifier = (BeanDeserializerModifier[])ArrayBuilders.insertInListNoDup((Object[])this._modifiers, paramBeanDeserializerModifier);
    return new BeanDeserializerFactory$ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, arrayOfBeanDeserializerModifier, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactory.Config withValueInstantiators(ValueInstantiators paramValueInstantiators) {
    if (paramValueInstantiators == null)
      throw new IllegalArgumentException("Can not pass null resolver"); 
    ValueInstantiators[] arrayOfValueInstantiators = (ValueInstantiators[])ArrayBuilders.insertInListNoDup((Object[])this._valueInstantiators, paramValueInstantiators);
    return new BeanDeserializerFactory$ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, arrayOfValueInstantiators);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\BeanDeserializerFactory$ConfigImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */