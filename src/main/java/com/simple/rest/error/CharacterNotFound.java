package com.simple.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class CharacterNotFound extends RuntimeException{
	private static final long serialVersionUID = -7034897190745766939L;
}
