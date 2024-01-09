package com.flurry.org.codehaus.jackson.io;

/* loaded from: classes.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    public static final boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str.charAt(i) - str2.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static final boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static double parseAsDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            try {
                return parseDouble(trim);
            } catch (NumberFormatException e) {
                return d;
            }
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseAsInt(java.lang.String r6, int r7) {
        /*
            r1 = 1
            r0 = 0
            if (r6 != 0) goto L5
        L4:
            return r7
        L5:
            java.lang.String r3 = r6.trim()
            int r2 = r3.length()
            if (r2 == 0) goto L4
            if (r0 >= r2) goto L49
            char r4 = r3.charAt(r0)
            r5 = 43
            if (r4 != r5) goto L35
            java.lang.String r2 = r3.substring(r1)
            int r1 = r2.length()
        L21:
            if (r0 >= r1) goto L40
            char r3 = r2.charAt(r0)
            r4 = 57
            if (r3 > r4) goto L2f
            r4 = 48
            if (r3 >= r4) goto L3d
        L2f:
            double r0 = parseDouble(r2)     // Catch: java.lang.NumberFormatException -> L45
            int r7 = (int) r0
            goto L4
        L35:
            r5 = 45
            if (r4 != r5) goto L49
            r0 = r1
            r1 = r2
            r2 = r3
            goto L21
        L3d:
            int r0 = r0 + 1
            goto L21
        L40:
            int r7 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L47
            goto L4
        L45:
            r0 = move-exception
            goto L4
        L47:
            r0 = move-exception
            goto L4
        L49:
            r1 = r2
            r2 = r3
            goto L21
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.io.NumberInput.parseAsInt(java.lang.String, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long parseAsLong(java.lang.String r6, long r7) {
        /*
            r1 = 1
            r0 = 0
            if (r6 != 0) goto L5
        L4:
            return r7
        L5:
            java.lang.String r3 = r6.trim()
            int r2 = r3.length()
            if (r2 == 0) goto L4
            if (r0 >= r2) goto L49
            char r4 = r3.charAt(r0)
            r5 = 43
            if (r4 != r5) goto L35
            java.lang.String r2 = r3.substring(r1)
            int r1 = r2.length()
        L21:
            if (r0 >= r1) goto L40
            char r3 = r2.charAt(r0)
            r4 = 57
            if (r3 > r4) goto L2f
            r4 = 48
            if (r3 >= r4) goto L3d
        L2f:
            double r0 = parseDouble(r2)     // Catch: java.lang.NumberFormatException -> L45
            long r7 = (long) r0
            goto L4
        L35:
            r5 = 45
            if (r4 != r5) goto L49
            r0 = r1
            r1 = r2
            r2 = r3
            goto L21
        L3d:
            int r0 = r0 + 1
            goto L21
        L40:
            long r7 = java.lang.Long.parseLong(r2)     // Catch: java.lang.NumberFormatException -> L47
            goto L4
        L45:
            r0 = move-exception
            goto L4
        L47:
            r0 = move-exception
            goto L4
        L49:
            r1 = r2
            r2 = r3
            goto L21
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.io.NumberInput.parseAsLong(java.lang.String, long):long");
    }

    public static final double parseDouble(String str) {
        if (NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    public static final int parseInt(String str) {
        int i = 1;
        char charAt = str.charAt(0);
        int length = str.length();
        boolean z = charAt == '-';
        if (z) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str);
            }
            charAt = str.charAt(1);
            i = 2;
        } else if (length > 9) {
            return Integer.parseInt(str);
        }
        if (charAt > '9' || charAt < '0') {
            return Integer.parseInt(str);
        }
        int i2 = charAt - '0';
        if (i < length) {
            int i3 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                return Integer.parseInt(str);
            }
            i2 = (i2 * 10) + (charAt2 - '0');
            if (i3 < length) {
                int i4 = i3 + 1;
                char charAt3 = str.charAt(i3);
                if (charAt3 > '9' || charAt3 < '0') {
                    return Integer.parseInt(str);
                }
                i2 = (i2 * 10) + (charAt3 - '0');
                if (i4 < length) {
                    while (true) {
                        int i5 = i4 + 1;
                        char charAt4 = str.charAt(i4);
                        if (charAt4 > '9' || charAt4 < '0') {
                            break;
                        }
                        i2 = (i2 * 10) + (charAt4 - '0');
                        if (i5 >= length) {
                            break;
                        }
                        i4 = i5;
                    }
                    return Integer.parseInt(str);
                }
            }
        }
        return z ? -i2 : i2;
    }

    public static final int parseInt(char[] cArr, int i, int i2) {
        int i3;
        int i4 = cArr[i] - '0';
        int i5 = i2 + i;
        int i6 = i + 1;
        if (i6 < i5) {
            int i7 = (i4 * 10) + (cArr[i6] - '0');
            int i8 = i6 + 1;
            if (i8 < i5) {
                int i9 = (i7 * 10) + (cArr[i8] - '0');
                int i10 = i8 + 1;
                if (i10 < i5) {
                    int i11 = (i9 * 10) + (cArr[i10] - '0');
                    int i12 = i10 + 1;
                    if (i12 < i5) {
                        int i13 = (i11 * 10) + (cArr[i12] - '0');
                        int i14 = i12 + 1;
                        if (i14 < i5) {
                            int i15 = (i13 * 10) + (cArr[i14] - '0');
                            int i16 = i14 + 1;
                            if (i16 < i5) {
                                int i17 = (i15 * 10) + (cArr[i16] - '0');
                                int i18 = i16 + 1;
                                if (i18 < i5) {
                                    int i19 = (i17 * 10) + (cArr[i18] - '0');
                                    return i18 + 1 < i5 ? (i19 * 10) + (cArr[i3] - '0') : i19;
                                }
                                return i17;
                            }
                            return i15;
                        }
                        return i13;
                    }
                    return i11;
                }
                return i9;
            }
            return i7;
        }
        return i4;
    }

    public static final long parseLong(String str) {
        return str.length() <= 9 ? parseInt(str) : Long.parseLong(str);
    }

    public static final long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (parseInt(cArr, i, i3) * L_BILLION) + parseInt(cArr, i3 + i, 9);
    }
}
