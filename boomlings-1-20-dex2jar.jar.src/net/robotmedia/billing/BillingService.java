package net.robotmedia.billing;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.a.a.a.a;
import com.a.a.a.b;
import java.util.LinkedList;
import net.robotmedia.billing.c.e;

public class BillingService extends Service implements ServiceConnection {
  private static LinkedList a = new LinkedList();
  
  private static a b;
  
  private static int[] c;
  
  private static Intent a(Context paramContext, m paramm) {
    Intent intent = new Intent(b(paramContext, paramm));
    intent.setClass(paramContext, BillingService.class);
    return intent;
  }
  
  private m a(Intent paramIntent) {
    m m;
    String str2 = null;
    String str1 = paramIntent.getAction();
    if (str1 == null)
      return (m)str2; 
    String[] arrayOfString = str1.split("\\.");
    str1 = str2;
    if (arrayOfString.length > 0)
      m = m.valueOf(arrayOfString[arrayOfString.length - 1]); 
    return m;
  }
  
  private void a(int paramInt) {
    b(new e(getPackageName(), paramInt));
  }
  
  public static void a(Context paramContext) {
    paramContext.startService(a(paramContext, m.a));
  }
  
  public static void a(Context paramContext, long paramLong) {
    Intent intent = a(paramContext, m.g);
    intent.setClass(paramContext, BillingService.class);
    intent.putExtra("EXTRA_NONCE", paramLong);
    paramContext.startService(intent);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2) {
    Intent intent = a(paramContext, m.e);
    intent.putExtra("ITEM_ID", paramString1);
    intent.putExtra("DEVELOPER_PAYLOAD", paramString2);
    paramContext.startService(intent);
  }
  
  public static void a(Context paramContext, String[] paramArrayOfString) {
    Intent intent = a(paramContext, m.c);
    intent.putExtra("NOTIFY_IDS", paramArrayOfString);
    paramContext.startService(intent);
  }
  
  public static void a(Context paramContext, String[] paramArrayOfString, long paramLong) {
    Intent intent = a(paramContext, m.d);
    intent.putExtra("NOTIFY_IDS", paramArrayOfString);
    intent.putExtra("EXTRA_NONCE", paramLong);
    paramContext.startService(intent);
  }
  
  private void a(Intent paramIntent, int paramInt) {
    b(new g(getPackageName(), paramInt, paramIntent.getStringArrayExtra("NOTIFY_IDS")));
  }
  
  private void a(d paramd) {
    try {
      a.a(paramd.a(b), paramd);
    } catch (RemoteException remoteException) {
      Log.w(getClass().getSimpleName(), "Remote billing service crashed");
    } 
  }
  
  private static final String b(Context paramContext, m paramm) {
    return String.valueOf(paramContext.getPackageName()) + "." + paramm.toString();
  }
  
  private void b() {
    try {
      Intent intent = new Intent();
      this("com.android.vending.billing.MarketBillingService.BIND");
      if (!bindService(intent, this, 1))
        Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService"); 
    } catch (SecurityException securityException) {
      Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService", securityException);
    } 
  }
  
  private void b(int paramInt) {
    b(new f(getPackageName(), paramInt));
  }
  
  public static void b(Context paramContext) {
    paramContext.startService(a(paramContext, m.b));
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2) {
    Intent intent = a(paramContext, m.f);
    intent.putExtra("ITEM_ID", paramString1);
    intent.putExtra("DEVELOPER_PAYLOAD", paramString2);
    paramContext.startService(intent);
  }
  
  private void b(Intent paramIntent, int paramInt) {
    String str = getPackageName();
    long l = paramIntent.getLongExtra("EXTRA_NONCE", 0L);
    h h = new h(str, paramInt, paramIntent.getStringArrayExtra("NOTIFY_IDS"));
    h.a(l);
    b(h);
  }
  
  private void b(d paramd) {
    a.add(paramd);
    if (b == null) {
      b();
      return;
    } 
    c();
  }
  
  private void c() {
    int i = -1;
    while (true) {
      d d = a.peek();
      if (d == null) {
        if (i >= 0)
          stopSelf(i); 
        return;
      } 
      if (b != null) {
        a(d);
        a.remove();
        if (i < d.g())
          i = d.g(); 
        continue;
      } 
      b();
      return;
    } 
  }
  
  private void c(Intent paramIntent, int paramInt) {
    m m = a(paramIntent);
    if (m == null);
    switch (a()[m.ordinal()]) {
      default:
        return;
      case 1:
        a(paramInt);
      case 2:
        b(paramInt);
      case 5:
        d(paramIntent, paramInt);
      case 6:
        e(paramIntent, paramInt);
      case 4:
        b(paramIntent, paramInt);
      case 3:
        a(paramIntent, paramInt);
      case 7:
        break;
    } 
    f(paramIntent, paramInt);
  }
  
  private void d(Intent paramIntent, int paramInt) {
    b(new i(getPackageName(), paramInt, paramIntent.getStringExtra("ITEM_ID"), paramIntent.getStringExtra("DEVELOPER_PAYLOAD")));
  }
  
  private void e(Intent paramIntent, int paramInt) {
    b(new j(getPackageName(), paramInt, paramIntent.getStringExtra("ITEM_ID"), paramIntent.getStringExtra("DEVELOPER_PAYLOAD")));
  }
  
  private void f(Intent paramIntent, int paramInt) {
    String str = getPackageName();
    long l1 = paramIntent.getLongExtra("EXTRA_NONCE", 0L);
    l l = new l(str, paramInt);
    l.a(l1);
    b(l);
  }
  
  public IBinder onBind(Intent paramIntent) {
    return null;
  }
  
  public void onDestroy() {
    super.onDestroy();
    if (b != null)
      try {
        unbindService(this);
      } catch (IllegalArgumentException illegalArgumentException) {} 
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    b = b.a(paramIBinder);
    c();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    b = null;
  }
  
  public void onStart(Intent paramIntent, int paramInt) {
    c(paramIntent, paramInt);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    c(paramIntent, paramInt2);
    return e.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\BillingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */