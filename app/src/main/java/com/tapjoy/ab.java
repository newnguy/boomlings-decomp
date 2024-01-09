package com.tapjoy;

/* loaded from: classes.dex */
public class ab implements Runnable {
    final /* synthetic */ aa a;

    public ab(aa aaVar) {
        this.a = aaVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        am amVar;
        y yVar;
        ac acVar;
        ac acVar2;
        y yVar2;
        y yVar3;
        ac acVar3;
        ac acVar4;
        y yVar4;
        y yVar5;
        ac acVar5;
        ac acVar6;
        y yVar6;
        amVar = aa.e;
        ai a = amVar.a("https://ws.tapjoyads.com/get_offers/featured.html?", aa.a);
        if (a == null) {
            yVar = aa.c;
            if (yVar != null) {
                yVar2 = aa.c;
                yVar2.a("Error retrieving full screen ad data from the server.");
            }
            acVar = aa.d;
            if (acVar != null) {
                acVar2 = aa.d;
                acVar2.getFullScreenAdResponseFailed(2);
                return;
            }
            return;
        }
        switch (a.a) {
            case 200:
                String unused = aa.h = a.c;
                yVar3 = aa.c;
                if (yVar3 != null) {
                    yVar4 = aa.c;
                    yVar4.a((z) null);
                }
                acVar3 = aa.d;
                if (acVar3 != null) {
                    acVar4 = aa.d;
                    acVar4.getFullScreenAdResponse();
                    return;
                }
                return;
            default:
                yVar5 = aa.c;
                if (yVar5 != null) {
                    yVar6 = aa.c;
                    yVar6.a("Error retrieving full screen ad data from the server.");
                }
                acVar5 = aa.d;
                if (acVar5 != null) {
                    acVar6 = aa.d;
                    acVar6.getFullScreenAdResponseFailed(1);
                    return;
                }
                return;
        }
    }
}
