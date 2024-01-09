package org.cocos2dx.lib;

class l implements Runnable {
  private final String a;
  
  l(String paramString) {}
  
  public void run() {
    Cocos2dxActivity.fbDelegate_.appRequestFriends(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */