package org.cocos2dx.lib;

class o implements Runnable {
  private final String a;
  
  o(String paramString) {}
  
  public void run() {
    Cocos2dxActivity.fbDelegate_.postScore(this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */