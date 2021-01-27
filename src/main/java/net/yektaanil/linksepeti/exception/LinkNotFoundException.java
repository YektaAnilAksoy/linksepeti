package net.yektaanil.linksepeti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LinkNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6244407828896929554L;

    public LinkNotFoundException(String msg) {
        super(msg);
    }
}
