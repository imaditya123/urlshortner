package org.example.model.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponseDto {
    private String shortUrl;
    private String longUrl;
    private Instant expiration;
}
