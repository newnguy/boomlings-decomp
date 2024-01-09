package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy;
import com.flurry.org.codehaus.jackson.map.util.BeanUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig _config;
    protected final boolean _forSerialization;
    protected Set _ignoredPropertyNames;
    protected Set _ignoredPropertyNamesForDeser;
    protected LinkedHashMap _injectables;
    protected final JavaType _type;
    protected final VisibilityChecker _visibilityChecker;
    protected final LinkedHashMap _properties = new LinkedHashMap();
    protected LinkedList _creatorProperties = null;
    protected LinkedList _anyGetters = null;
    protected LinkedList _anySetters = null;
    protected LinkedList _jsonValueGetters = null;

    public POJOPropertiesCollector(MapperConfig mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass) {
        this._config = mapperConfig;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        this._annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : null;
        if (this._annotationIntrospector == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(annotatedClass, this._config.getDefaultVisibilityChecker());
        }
    }

    private void _addIgnored(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._forSerialization) {
            return;
        }
        String name = pOJOPropertyBuilder.getName();
        this._ignoredPropertyNames = addToSet(this._ignoredPropertyNames, name);
        if (pOJOPropertyBuilder.anyDeserializeIgnorals()) {
            this._ignoredPropertyNamesForDeser = addToSet(this._ignoredPropertyNamesForDeser, name);
        }
    }

    private Set addToSet(Set set, String str) {
        if (set == null) {
            set = new HashSet();
        }
        set.add(str);
        return set;
    }

    protected void _addCreators() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return;
        }
        for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList();
            }
            int parameterCount = annotatedConstructor.getParameterCount();
            for (int i = 0; i < parameterCount; i++) {
                AnnotatedParameter parameter = annotatedConstructor.getParameter(i);
                String findPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
                if (findPropertyNameForParam != null) {
                    POJOPropertyBuilder _property = _property(findPropertyNameForParam);
                    _property.addCtor(parameter, findPropertyNameForParam, true, false);
                    this._creatorProperties.add(_property);
                }
            }
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.getStaticMethods()) {
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList();
            }
            int parameterCount2 = annotatedMethod.getParameterCount();
            for (int i2 = 0; i2 < parameterCount2; i2++) {
                AnnotatedParameter parameter2 = annotatedMethod.getParameter(i2);
                String findPropertyNameForParam2 = annotationIntrospector.findPropertyNameForParam(parameter2);
                if (findPropertyNameForParam2 != null) {
                    POJOPropertyBuilder _property2 = _property(findPropertyNameForParam2);
                    _property2.addCtor(parameter2, findPropertyNameForParam2, true, false);
                    this._creatorProperties.add(_property2);
                }
            }
        }
    }

    protected void _addFields() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            String name = annotatedField.getName();
            String findSerializablePropertyName = annotationIntrospector == null ? null : this._forSerialization ? annotationIntrospector.findSerializablePropertyName(annotatedField) : annotationIntrospector.findDeserializablePropertyName(annotatedField);
            String str = "".equals(findSerializablePropertyName) ? name : findSerializablePropertyName;
            boolean z = str != null;
            _property(name).addField(annotatedField, str, !z ? this._visibilityChecker.isFieldVisible(annotatedField) : z, annotationIntrospector != null && annotationIntrospector.hasIgnoreMarker(annotatedField));
        }
    }

    protected void _addInjectables() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return;
        }
        for (AnnotatedMember annotatedMember : this._classDef.fields()) {
            _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMember), annotatedMember);
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            if (annotatedMethod.getParameterCount() == 1) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod);
            }
        }
    }

    protected void _addMethods() {
        String str;
        String str2;
        boolean z;
        String str3;
        String str4;
        boolean z2;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount == 0) {
                if (annotationIntrospector != null) {
                    if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
                        if (this._anyGetters == null) {
                            this._anyGetters = new LinkedList();
                        }
                        this._anyGetters.add(annotatedMethod);
                    } else if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
                        if (this._jsonValueGetters == null) {
                            this._jsonValueGetters = new LinkedList();
                        }
                        this._jsonValueGetters.add(annotatedMethod);
                    }
                }
                String findGettablePropertyName = annotationIntrospector == null ? null : annotationIntrospector.findGettablePropertyName(annotatedMethod);
                if (findGettablePropertyName == null) {
                    str = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
                    if (str == null) {
                        str = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                        if (str != null) {
                            str2 = findGettablePropertyName;
                            z = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                        }
                    } else {
                        str2 = findGettablePropertyName;
                        z = this._visibilityChecker.isGetterVisible(annotatedMethod);
                    }
                } else {
                    String okNameForGetter = BeanUtil.okNameForGetter(annotatedMethod);
                    if (okNameForGetter == null) {
                        okNameForGetter = annotatedMethod.getName();
                    }
                    if (findGettablePropertyName.length() == 0) {
                        findGettablePropertyName = okNameForGetter;
                    }
                    str = okNameForGetter;
                    str2 = findGettablePropertyName;
                    z = true;
                }
                _property(str).addGetter(annotatedMethod, str2, z, annotationIntrospector == null ? false : annotationIntrospector.hasIgnoreMarker(annotatedMethod));
            } else if (parameterCount == 1) {
                String findSettablePropertyName = annotationIntrospector == null ? null : annotationIntrospector.findSettablePropertyName(annotatedMethod);
                if (findSettablePropertyName == null) {
                    str3 = BeanUtil.okNameForSetter(annotatedMethod);
                    if (str3 != null) {
                        str4 = findSettablePropertyName;
                        z2 = this._visibilityChecker.isSetterVisible(annotatedMethod);
                    }
                } else {
                    String okNameForSetter = BeanUtil.okNameForSetter(annotatedMethod);
                    if (okNameForSetter == null) {
                        okNameForSetter = annotatedMethod.getName();
                    }
                    if (findSettablePropertyName.length() == 0) {
                        findSettablePropertyName = okNameForSetter;
                    }
                    str3 = okNameForSetter;
                    str4 = findSettablePropertyName;
                    z2 = true;
                }
                _property(str3).addSetter(annotatedMethod, str4, z2, annotationIntrospector == null ? false : annotationIntrospector.hasIgnoreMarker(annotatedMethod));
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    protected void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        if (obj == null) {
            return;
        }
        if (this._injectables == null) {
            this._injectables = new LinkedHashMap();
        }
        if (((AnnotatedMember) this._injectables.put(obj, annotatedMember)) != null) {
            throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(obj) + "' (of type " + (obj == null ? "[null]" : obj.getClass().getName()) + ")");
        }
    }

    protected POJOPropertyBuilder _property(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) this._properties.get(str);
        if (pOJOPropertyBuilder == null) {
            POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(str);
            this._properties.put(str, pOJOPropertyBuilder2);
            return pOJOPropertyBuilder2;
        }
        return pOJOPropertyBuilder;
    }

    protected void _removeUnwantedProperties() {
        Iterator it = this._properties.entrySet().iterator();
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Map.Entry) it.next()).getValue();
            if (pOJOPropertyBuilder.anyVisible()) {
                if (pOJOPropertyBuilder.anyIgnorals()) {
                    _addIgnored(pOJOPropertyBuilder);
                    if (pOJOPropertyBuilder.anyExplicitNames()) {
                        pOJOPropertyBuilder.removeIgnored();
                    } else {
                        it.remove();
                    }
                }
                pOJOPropertyBuilder.removeNonVisible();
            } else {
                it.remove();
            }
        }
    }

    protected void _renameProperties() {
        Iterator it = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Map.Entry) it.next()).getValue();
            String findNewName = pOJOPropertyBuilder.findNewName();
            if (findNewName != null) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(pOJOPropertyBuilder.withName(findNewName));
                it.remove();
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) this._properties.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    this._properties.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
            }
        }
    }

    protected void _renameUsing(PropertyNamingStrategy propertyNamingStrategy) {
        String nameForGetterMethod;
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) this._properties.values().toArray(new POJOPropertyBuilder[this._properties.size()]);
        this._properties.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            String name = pOJOPropertyBuilder.getName();
            if (this._forSerialization) {
                if (pOJOPropertyBuilder.hasGetter()) {
                    nameForGetterMethod = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                } else {
                    if (pOJOPropertyBuilder.hasField()) {
                        nameForGetterMethod = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
                    }
                    nameForGetterMethod = name;
                }
            } else if (pOJOPropertyBuilder.hasSetter()) {
                nameForGetterMethod = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), name);
            } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                nameForGetterMethod = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), name);
            } else if (pOJOPropertyBuilder.hasField()) {
                nameForGetterMethod = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
            } else {
                if (pOJOPropertyBuilder.hasGetter()) {
                    nameForGetterMethod = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                }
                nameForGetterMethod = name;
            }
            if (!nameForGetterMethod.equals(pOJOPropertyBuilder.getName())) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.withName(nameForGetterMethod);
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) this._properties.get(nameForGetterMethod);
            if (pOJOPropertyBuilder2 == null) {
                this._properties.put(nameForGetterMethod, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.util.TreeMap] */
    protected void _sortProperties() {
        String str;
        AnnotationIntrospector annotationIntrospector = this._config.getAnnotationIntrospector();
        Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        boolean shouldSortPropertiesAlphabetically = findSerializationSortAlphabetically == null ? this._config.shouldSortPropertiesAlphabetically() : findSerializationSortAlphabetically.booleanValue();
        String[] findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        if (!shouldSortPropertiesAlphabetically && this._creatorProperties == null && findSerializationPropertyOrder == null) {
            return;
        }
        int size = this._properties.size();
        LinkedHashMap treeMap = shouldSortPropertiesAlphabetically ? new TreeMap() : new LinkedHashMap(size + size);
        for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
            treeMap.put(pOJOPropertyBuilder.getName(), pOJOPropertyBuilder);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(size + size);
        if (findSerializationPropertyOrder != null) {
            for (String str2 : findSerializationPropertyOrder) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) treeMap.get(str2);
                if (pOJOPropertyBuilder2 == null) {
                    for (POJOPropertyBuilder pOJOPropertyBuilder3 : this._properties.values()) {
                        if (str2.equals(pOJOPropertyBuilder3.getInternalName())) {
                            str = pOJOPropertyBuilder3.getName();
                            pOJOPropertyBuilder2 = pOJOPropertyBuilder3;
                            break;
                        }
                    }
                }
                str = str2;
                if (pOJOPropertyBuilder2 != null) {
                    linkedHashMap.put(str, pOJOPropertyBuilder2);
                }
            }
        }
        if (this._creatorProperties != null) {
            Iterator it = this._creatorProperties.iterator();
            while (it.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder4 = (POJOPropertyBuilder) it.next();
                linkedHashMap.put(pOJOPropertyBuilder4.getName(), pOJOPropertyBuilder4);
            }
        }
        linkedHashMap.putAll(treeMap);
        this._properties.clear();
        this._properties.putAll(linkedHashMap);
    }

    public POJOPropertiesCollector collect() {
        this._properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
        if (propertyNamingStrategy != null) {
            _renameUsing(propertyNamingStrategy);
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
            pOJOPropertyBuilder.trimByVisibility();
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder2 : this._properties.values()) {
            pOJOPropertyBuilder2.mergeAnnotations(this._forSerialization);
        }
        _sortProperties();
        return this;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public AnnotatedMethod getAnyGetterMethod() {
        if (this._anyGetters != null) {
            if (this._anyGetters.size() > 1) {
                reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")");
            }
            return (AnnotatedMethod) this._anyGetters.getFirst();
        }
        return null;
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (this._anySetters != null) {
            if (this._anySetters.size() > 1) {
                reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")");
            }
            return (AnnotatedMethod) this._anySetters.getFirst();
        }
        return null;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public MapperConfig getConfig() {
        return this._config;
    }

    public Set getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public Set getIgnoredPropertyNamesForDeser() {
        return this._ignoredPropertyNamesForDeser;
    }

    public Map getInjectables() {
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        if (this._jsonValueGetters != null) {
            if (this._jsonValueGetters.size() > 1) {
                reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")");
            }
            return (AnnotatedMethod) this._jsonValueGetters.get(0);
        }
        return null;
    }

    public List getProperties() {
        return new ArrayList(this._properties.values());
    }

    protected Map getPropertyMap() {
        return this._properties;
    }

    public JavaType getType() {
        return this._type;
    }

    protected void reportProblem(String str) {
        throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + str);
    }
}
