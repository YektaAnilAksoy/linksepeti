package net.yektaanil.linksepeti.exception;

public class HashCodeExpiredException extends Exception {

    private static final long serialVersionUID = -5456498018365255603L;

    public HashCodeExpiredException() {

    }

    public HashCodeExpiredException(String msg) {
        super(msg);
    }
}
