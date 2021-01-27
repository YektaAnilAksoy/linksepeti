package net.yektaanil.linksepeti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class HashCodeCollisonException extends RuntimeException {

    private static final long serialVersionUID = -920779425654899959L;

    public HashCodeCollisonException(String msg) {
        super(msg);
    }

}
