package net.yektaanil.linksepeti.exception;

public class LinkNotFoundException extends Exception {

    private static final long serialVersionUID = -6244407828896929554L;

    public LinkNotFoundException() {

    }

    public LinkNotFoundException(String msg) {
        super(msg);
    }
}
