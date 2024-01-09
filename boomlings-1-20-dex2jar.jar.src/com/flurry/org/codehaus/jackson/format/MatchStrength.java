package com.flurry.org.codehaus.jackson.format;

public enum MatchStrength {
  FULL_MATCH, INCONCLUSIVE, NO_MATCH, SOLID_MATCH, WEAK_MATCH;
  
  private static final MatchStrength[] $VALUES;
  
  static {
    INCONCLUSIVE = new MatchStrength("INCONCLUSIVE", 1);
    WEAK_MATCH = new MatchStrength("WEAK_MATCH", 2);
    SOLID_MATCH = new MatchStrength("SOLID_MATCH", 3);
    FULL_MATCH = new MatchStrength("FULL_MATCH", 4);
    $VALUES = new MatchStrength[] { NO_MATCH, INCONCLUSIVE, WEAK_MATCH, SOLID_MATCH, FULL_MATCH };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\format\MatchStrength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */