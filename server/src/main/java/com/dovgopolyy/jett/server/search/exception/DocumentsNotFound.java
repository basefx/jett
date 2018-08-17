package com.dovgopolyy.jett.server.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
        reason = "Documents has not been found by the specified tokens")
public class DocumentsNotFound extends RuntimeException {
}
