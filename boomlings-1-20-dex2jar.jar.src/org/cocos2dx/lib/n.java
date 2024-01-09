package org.cocos2dx.lib;

class n implements Runnable {
  private final String a;
  
  private final String b;
  
  private final String c;
  
  private final String d;
  
  n(String paramString1, String paramString2, String paramString3, String paramString4) {}
  
  public void run() {
    Cocos2dxActivity.fbDelegate_.postToWall(this.a, this.b, this.c, this.d);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */