package com.chartboost.sdk.impl;

/* loaded from: classes.dex */
public class ac {
    public static String a(Object obj) {
        StringBuilder sb = new StringBuilder();
        a(obj, sb);
        return sb.toString();
    }

    public static void a(Object obj, StringBuilder sb) {
        ad.a().a(obj, sb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(StringBuilder sb, String str) {
        sb.append("\"");
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                sb.append("\\\\");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else if (charAt == '\n') {
                sb.append("\\n");
            } else if (charAt == '\r') {
                sb.append("\\r");
            } else if (charAt == '\t') {
                sb.append("\\t");
            } else if (charAt == '\b') {
                sb.append("\\b");
            } else if (charAt >= ' ') {
                sb.append(charAt);
            }
        }
        sb.append("\"");
    }
}
