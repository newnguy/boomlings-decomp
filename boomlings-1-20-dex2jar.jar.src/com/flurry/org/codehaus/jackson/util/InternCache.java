package com.flurry.org.codehaus.jackson.util;

import java.util.LinkedHashMap;
import java.util.Map;

public final class InternCache extends LinkedHashMap {
  private static final int MAX_ENTRIES = 192;
  
  public static final InternCache instance = new InternCache();
  
  private InternCache() {
    super(192, 0.8F, true);
  }
  
  public String intern(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   7: checkcast java/lang/String
    //   10: astore_3
    //   11: aload_3
    //   12: astore_2
    //   13: aload_3
    //   14: ifnonnull -> 29
    //   17: aload_1
    //   18: invokevirtual intern : ()Ljava/lang/String;
    //   21: astore_2
    //   22: aload_0
    //   23: aload_2
    //   24: aload_2
    //   25: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   28: pop
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: areturn
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	33	finally
    //   17	29	33	finally
  }
  
  protected boolean removeEldestEntry(Map.Entry paramEntry) {
    return (size() > 192);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\InternCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */