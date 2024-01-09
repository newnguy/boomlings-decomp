package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.Versioned;

public abstract class Module implements Versioned {
  public abstract String getModuleName();
  
  public abstract void setupModule(Module$SetupContext paramModule$SetupContext);
  
  public abstract Version version();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\Module.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */