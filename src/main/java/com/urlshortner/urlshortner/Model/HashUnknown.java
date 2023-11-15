package com.urlshortner.urlshortner.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HashUnknown extends RuntimeException{

	public HashUnknown(String hashVal) {
		super(hashVal);
	}



}
