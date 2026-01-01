package org.example.service;

import org.example.model.Url;
import org.example.model.dto.UrlRequestDto;
import org.example.model.dto.UrlResponseDto;
import org.example.model.mapper.UrlMapper;
import org.example.repository.UrlRepository;
import org.example.utils.DeterministicShortUrlGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    final Logger logger = LoggerFactory.getLogger(getClass());

    @CachePut(
        value = "shortUrlToLongUrl",
        key = "#result.shortUrl"
    )
    public UrlResponseDto createShorturl(UrlRequestDto request) throws DataIntegrityViolationException{

        String shortUrl = DeterministicShortUrlGenerator.generate(request.getLongUrl());
        if(urlRepository.existsByShortUrl(shortUrl)){
            return  urlMapper.toResponseDto(urlRepository.findByshortUrl(shortUrl));
        }else
        return urlMapper.toResponseDto(urlRepository.save(urlMapper.toEntity(request,shortUrl)));
    }
@Cacheable(
        value = "shortUrlToLongUrl",
        key = "#shortUrl"
    )
    public String getLongUrl(@NotNull String shortUrl) throws DataIntegrityViolationException{
        Url url = urlRepository.findByshortUrl(shortUrl);
        return url.getLongUrl();
    }

}
