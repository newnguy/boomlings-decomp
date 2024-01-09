package org.cocos2dx.lib;

class m implements Runnable {
  private final String a;
  
  private final String b;
  
  m(String paramString1, String paramString2) {}
  
  public void run() {
    Cocos2dxActivity.fbDelegate_.appRequestFriendsRefID(this.a, this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */