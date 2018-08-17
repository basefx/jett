package com.dovgopolyy.jett.server.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Null is not allowed as parameter")
public class NullNotAllowedException extends RuntimeException {
}
