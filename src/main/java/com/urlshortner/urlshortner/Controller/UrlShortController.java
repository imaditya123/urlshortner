package com.urlshortner.urlshortner.Controller;

import java.net.URI;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortner.urlshortner.service.UrlShortService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlShortController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlShortController.class);

	
	UrlShortService service;

	public UrlShortController(UrlShortService service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/")
	public ShortResponse shorten(@RequestBody ShortRequest requestBody,HttpServletRequest request) {
		
		String hashVal=service.shorten(requestBody.url());
		
		String currentUrl=request.getRequestURI();
		
		return new ShortResponse(currentUrl+hashVal);
		
	}
	
	@GetMapping("/{hash}")	
	public ResponseEntity resolve(@PathVariable String hash) {
		String targetUrl= service.resolve(hash);
		return ResponseEntity
				.status(HttpStatus.MOVED_PERMANENTLY)
				.location(URI.create(targetUrl))
//				.headers(HttpHeaders.CONNECTION,"close")
				.build();
		
	}

}

record ShortRequest(String url) {}
record ShortResponse(String hash) {}
