package org.example.model.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlRequestDto {
    private final String longUrl;
    @Builder.Default
    private final Instant expiration = Instant.now().plus(10, ChronoUnit.DAYS);;
}
