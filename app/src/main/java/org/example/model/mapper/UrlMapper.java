package org.example.model.mapper;

import java.time.Instant;

import org.example.model.Url;
import org.example.model.dto.UrlRequestDto;
import org.example.model.dto.UrlResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", imports = Instant.class,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrlMapper {

    // Request DTO → Entity
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "shortUrl", source = "shortUrl")
    Url toEntity(UrlRequestDto requestDto,String shortUrl);

    // Entity → Response DTO
    @Mapping(target = "expiration", source = "expirationTime")
    UrlResponseDto toResponseDto(Url url);
}
