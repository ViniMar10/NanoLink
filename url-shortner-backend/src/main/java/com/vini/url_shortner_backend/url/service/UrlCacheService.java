package com.vini.url_shortner_backend.url.service;

import com.vini.url_shortner_backend.common.constants.RedisKeys;
import com.vini.url_shortner_backend.url.model.dto.UrlGenerationResponseDto;
import com.vini.url_shortner_backend.url.model.entity.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UrlCacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    private <T> Optional<T> getFromCache(String key, Class<T> classType) {
        Object value = redisTemplate.opsForValue().get(key);
        if (classType.isInstance(value)) {
            return Optional.of(classType.cast(value));
        }
        return Optional.empty();
    }

    public void cacheUrl(Url url) {
        redisTemplate.opsForValue().set(
                RedisKeys.urlKey(url.getShortCode()),
                url,
                RedisKeys.URL_TTL_HOURS,
                TimeUnit.HOURS
        );
    }

    public void cachePublicUrlGeneration(UrlGenerationResponseDto urlGenerationResponseDto) {
        redisTemplate.opsForValue().set(
                RedisKeys.publicUrlGenerationHistoryKey(urlGenerationResponseDto.longUrl()),
                urlGenerationResponseDto,
                RedisKeys.PUBLIC_URL_GENERATION_HISTORY_TTL_HOURS,
                TimeUnit.HOURS
        );
    }

    public Optional<Url> findUrlByShortCode(String shortCode) {
        return getFromCache(RedisKeys.urlKey(shortCode), Url.class);
    }

    public Optional<UrlGenerationResponseDto> findPublicUrlGenerationResponseByLongUrl(String longUrl) {
        return getFromCache(RedisKeys.publicUrlGenerationHistoryKey(longUrl), UrlGenerationResponseDto.class);
    }
}
