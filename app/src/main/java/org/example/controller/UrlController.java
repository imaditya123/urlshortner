package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.example.model.dto.ApiResponse;
import org.example.model.dto.UrlRequestDto;
import org.example.model.dto.UrlResponseDto;
import org.example.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController{
    private final UrlService urlService;


    @PostMapping()
    public ApiResponse<UrlResponseDto> createurl(@RequestBody UrlRequestDto entity) {
        
        return ApiResponse.success(urlService.createShorturl(entity), "Url is created");
    }

    @GetMapping("/{shorturl}")
    public ResponseEntity<Void> getMethodName(@PathVariable(name = "shorturl") String shortUrl) {
        String[] parts = shortUrl.split("/");
        String lastPart = parts[parts.length - 1];
        String longUrl = urlService.getLongUrl(lastPart);
        return ResponseEntity
            .status(HttpStatus.FOUND) // 302
            .location(URI.create(longUrl))
            .build(); 
    }
    
    

}
