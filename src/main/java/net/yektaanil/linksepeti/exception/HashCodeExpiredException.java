package net.yektaanil.linksepeti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HashCodeExpiredException extends RuntimeException {

    private static final long serialVersionUID = -5456498018365255603L;

    public HashCodeExpiredException() {

    }

    public HashCodeExpiredException(String msg) {
        super(msg);
    }
}
