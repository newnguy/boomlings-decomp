package net.robotmedia.billing;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.LinkedList;

/* loaded from: classes.dex */
public class BillingService extends Service implements ServiceConnection {
    private static LinkedList a = new LinkedList();
    private static com.a.a.a.a b;
    private static /* synthetic */ int[] c;

    private static Intent a(Context context, m mVar) {
        Intent intent = new Intent(b(context, mVar));
        intent.setClass(context, BillingService.class);
        return intent;
    }

    private m a(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return null;
        }
        String[] split = action.split("\\.");
        if (split.length > 0) {
            return m.valueOf(split[split.length - 1]);
        }
        return null;
    }

    private void a(int i) {
        b(new e(getPackageName(), i));
    }

    public static void a(Context context) {
        context.startService(a(context, m.CHECK_BILLING_SUPPORTED));
    }

    public static void a(Context context, long j) {
        Intent a2 = a(context, m.RESTORE_TRANSACTIONS);
        a2.setClass(context, BillingService.class);
        a2.putExtra("EXTRA_NONCE", j);
        context.startService(a2);
    }

    public static void a(Context context, String str, String str2) {
        Intent a2 = a(context, m.REQUEST_PURCHASE);
        a2.putExtra("ITEM_ID", str);
        a2.putExtra("DEVELOPER_PAYLOAD", str2);
        context.startService(a2);
    }

    public static void a(Context context, String[] strArr) {
        Intent a2 = a(context, m.CONFIRM_NOTIFICATIONS);
        a2.putExtra("NOTIFY_IDS", strArr);
        context.startService(a2);
    }

    public static void a(Context context, String[] strArr, long j) {
        Intent a2 = a(context, m.GET_PURCHASE_INFORMATION);
        a2.putExtra("NOTIFY_IDS", strArr);
        a2.putExtra("EXTRA_NONCE", j);
        context.startService(a2);
    }

    private void a(Intent intent, int i) {
        b(new g(getPackageName(), i, intent.getStringArrayExtra("NOTIFY_IDS")));
    }

    private void a(d dVar) {
        try {
            a.a(dVar.a(b), dVar);
        } catch (RemoteException e) {
            Log.w(getClass().getSimpleName(), "Remote billing service crashed");
        }
    }

    static /* synthetic */ int[] a() {
        int[] iArr = c;
        if (iArr == null) {
            iArr = new int[m.valuesCustom().length];
            try {
                iArr[m.CHECK_BILLING_SUPPORTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[m.CHECK_SUBSCRIPTION_SUPPORTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[m.CONFIRM_NOTIFICATIONS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[m.GET_PURCHASE_INFORMATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[m.REQUEST_PURCHASE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[m.REQUEST_SUBSCRIPTION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[m.RESTORE_TRANSACTIONS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            c = iArr;
        }
        return iArr;
    }

    private static final String b(Context context, m mVar) {
        return String.valueOf(context.getPackageName()) + "." + mVar.toString();
    }

    private void b() {
        try {
            if (bindService(new Intent("com.android.vending.billing.MarketBillingService.BIND"), this, 1)) {
                return;
            }
            Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService");
        } catch (SecurityException e) {
            Log.e(getClass().getSimpleName(), "Could not bind to MarketBillingService", e);
        }
    }

    private void b(int i) {
        b(new f(getPackageName(), i));
    }

    public static void b(Context context) {
        context.startService(a(context, m.CHECK_SUBSCRIPTION_SUPPORTED));
    }

    public static void b(Context context, String str, String str2) {
        Intent a2 = a(context, m.REQUEST_SUBSCRIPTION);
        a2.putExtra("ITEM_ID", str);
        a2.putExtra("DEVELOPER_PAYLOAD", str2);
        context.startService(a2);
    }

    private void b(Intent intent, int i) {
        String packageName = getPackageName();
        long longExtra = intent.getLongExtra("EXTRA_NONCE", 0L);
        h hVar = new h(packageName, i, intent.getStringArrayExtra("NOTIFY_IDS"));
        hVar.a(longExtra);
        b(hVar);
    }

    private void b(d dVar) {
        a.add(dVar);
        if (b == null) {
            b();
        } else {
            c();
        }
    }

    private void c() {
        int i = -1;
        while (true) {
            d dVar = (d) a.peek();
            if (dVar == null) {
                if (i >= 0) {
                    stopSelf(i);
                    return;
                }
                return;
            } else if (b == null) {
                b();
                return;
            } else {
                a(dVar);
                a.remove();
                if (i < dVar.g()) {
                    i = dVar.g();
                }
            }
        }
    }

    private void c(Intent intent, int i) {
        m a2 = a(intent);
        if (a2 == null) {
            return;
        }
        switch (a()[a2.ordinal()]) {
            case 1:
                a(i);
                return;
            case 2:
                b(i);
                return;
            case 3:
                a(intent, i);
                return;
            case 4:
                b(intent, i);
                return;
            case 5:
                d(intent, i);
                return;
            case 6:
                e(intent, i);
                return;
            case 7:
                f(intent, i);
                return;
            default:
                return;
        }
    }

    private void d(Intent intent, int i) {
        b(new i(getPackageName(), i, intent.getStringExtra("ITEM_ID"), intent.getStringExtra("DEVELOPER_PAYLOAD")));
    }

    private void e(Intent intent, int i) {
        b(new j(getPackageName(), i, intent.getStringExtra("ITEM_ID"), intent.getStringExtra("DEVELOPER_PAYLOAD")));
    }

    private void f(Intent intent, int i) {
        String packageName = getPackageName();
        long longExtra = intent.getLongExtra("EXTRA_NONCE", 0L);
        l lVar = new l(packageName, i);
        lVar.a(longExtra);
        b(lVar);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (b != null) {
            try {
                unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        b = com.a.a.a.b.a(iBinder);
        c();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        b = null;
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        c(intent, i);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        c(intent, i2);
        return net.robotmedia.billing.c.e.a;
    }
}
