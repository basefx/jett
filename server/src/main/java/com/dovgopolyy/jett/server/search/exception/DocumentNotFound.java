package com.dovgopolyy.jett.server.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
        reason = "Document has not been found by id")
public class DocumentNotFound extends RuntimeException {
}
