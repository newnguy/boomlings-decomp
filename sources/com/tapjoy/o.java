package com.tapjoy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class o implements Runnable {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar) {
        this.a = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        am amVar;
        p pVar;
        p pVar2;
        p pVar3;
        amVar = n.d;
        ai a = amVar.a("https://ws.tapjoyads.com/reengagement_rewards?", n.a);
        if (a == null) {
            pVar = n.c;
            pVar.getDailyRewardAdResponseFailed(2);
            return;
        }
        switch (a.a) {
            case 200:
                String unused = n.g = a.c;
                pVar3 = n.c;
                pVar3.getDailyRewardAdResponse();
                return;
            case 204:
                pVar2 = n.c;
                pVar2.getDailyRewardAdResponseFailed(1);
                return;
            default:
                return;
        }
    }
}
