package com.vini.url_shortner_backend.url.service;

import com.vini.url_shortner_backend.url.exceptions.UrlNotFoundException;
import com.vini.url_shortner_backend.url.infrastructure.mappers.UrlMapper;
import com.vini.url_shortner_backend.url.infrastructure.persistence.UrlPersistence;
import com.vini.url_shortner_backend.url.model.dto.PublicShortUrlGenerateRequestDto;
import com.vini.url_shortner_backend.url.model.dto.UrlGenerationResponseDto;
import com.vini.url_shortner_backend.url.model.entity.Url;
import com.vini.url_shortner_backend.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlMapper urlMapper;
    private final UrlCacheService urlCacheService;
    private final UrlRepository urlRepository;
    private final UrlPersistence urlPersistence;

    public UrlGenerationResponseDto generateShortUrlForNonRegisteredUsers(PublicShortUrlGenerateRequestDto requestDto) {
        return urlCacheService.findPublicUrlGenerationResponseByLongUrl(requestDto.longUrl())
                .orElseGet(() -> resolveAndCachePublicUrl(requestDto.longUrl()));
    }

    public String findLongUrlForRedirect(String shortCode) {
        return urlCacheService.findUrlByShortCode(shortCode)
                .orElseGet(() -> findAndCacheUrl(shortCode))
                .getLongUrl();
    }

    private Url findAndCacheUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("There is no url with shortCode " + shortCode));

        urlCacheService.cacheUrl(url);
        return url;
    }

    private UrlGenerationResponseDto resolveAndCachePublicUrl(String longUrl) {
        Url url = findOrCreatePublicUrl(longUrl);
        urlCacheService.cacheUrl(url);

        UrlGenerationResponseDto dto = urlMapper.toUrlGenerationResponseDto(url);
        urlCacheService.cachePublicUrlGeneration(dto);
        return dto;
    }

    private Url findOrCreatePublicUrl(String longUrl) {
        return urlRepository.findByLongUrlAndPrivateUrlFalse(longUrl)
                .orElseGet(() -> urlPersistence.saveWithUniqueCode(
                        Url.builder()
                                .longUrl(longUrl)
                                .privateUrl(false)
                ));
    }
}
