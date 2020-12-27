package net.yektaanil.linksepeti.common;

public final class Util {

    private Util() {

    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s) || "".equals(s.trim());
    }
}
