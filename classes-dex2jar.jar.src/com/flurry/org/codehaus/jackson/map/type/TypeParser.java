package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.List;

public class TypeParser {
  final TypeFactory _factory;
  
  public TypeParser(TypeFactory paramTypeFactory) {
    this._factory = paramTypeFactory;
  }
  
  protected IllegalArgumentException _problem(TypeParser$MyTokenizer paramTypeParser$MyTokenizer, String paramString) {
    return new IllegalArgumentException("Failed to parse type '" + paramTypeParser$MyTokenizer.getAllInput() + "' (remaining: '" + paramTypeParser$MyTokenizer.getRemainingInput() + "'): " + paramString);
  }
  
  protected Class findClass(String paramString, TypeParser$MyTokenizer paramTypeParser$MyTokenizer) {
    try {
      return Class.forName(paramString, true, Thread.currentThread().getContextClassLoader());
    } catch (Exception exception) {
      if (exception instanceof RuntimeException)
        throw (RuntimeException)exception; 
      throw _problem(paramTypeParser$MyTokenizer, "Can not locate class '" + paramString + "', problem: " + exception.getMessage());
    } 
  }
  
  public JavaType parse(String paramString) {
    TypeParser$MyTokenizer typeParser$MyTokenizer = new TypeParser$MyTokenizer(paramString.trim());
    JavaType javaType = parseType(typeParser$MyTokenizer);
    if (typeParser$MyTokenizer.hasMoreTokens())
      throw _problem(typeParser$MyTokenizer, "Unexpected tokens after complete type"); 
    return javaType;
  }
  
  protected JavaType parseType(TypeParser$MyTokenizer paramTypeParser$MyTokenizer) {
    if (!paramTypeParser$MyTokenizer.hasMoreTokens())
      throw _problem(paramTypeParser$MyTokenizer, "Unexpected end-of-string"); 
    Class clazz = findClass(paramTypeParser$MyTokenizer.nextToken(), paramTypeParser$MyTokenizer);
    if (paramTypeParser$MyTokenizer.hasMoreTokens()) {
      String str = paramTypeParser$MyTokenizer.nextToken();
      if ("<".equals(str))
        return this._factory._fromParameterizedClass(clazz, parseTypes(paramTypeParser$MyTokenizer)); 
      paramTypeParser$MyTokenizer.pushBack(str);
    } 
    return this._factory._fromClass(clazz, null);
  }
  
  protected List parseTypes(TypeParser$MyTokenizer paramTypeParser$MyTokenizer) {
    ArrayList<JavaType> arrayList = new ArrayList();
    while (true) {
      if (paramTypeParser$MyTokenizer.hasMoreTokens()) {
        arrayList.add(parseType(paramTypeParser$MyTokenizer));
        if (paramTypeParser$MyTokenizer.hasMoreTokens()) {
          String str = paramTypeParser$MyTokenizer.nextToken();
          if (">".equals(str))
            return arrayList; 
          if (!",".equals(str))
            throw _problem(paramTypeParser$MyTokenizer, "Unexpected token '" + str + "', expected ',' or '>')"); 
          continue;
        } 
      } 
      throw _problem(paramTypeParser$MyTokenizer, "Unexpected end-of-string");
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */