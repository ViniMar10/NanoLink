package com.vini.url_shortner_backend.url.infrastructure.mappers;

import com.vini.url_shortner_backend.url.model.dto.UrlGenerationResponseDto;
import com.vini.url_shortner_backend.url.model.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper {
    public UrlGenerationResponseDto toUrlGenerationResponseDto(Url url) {
        return new UrlGenerationResponseDto(
                url.getShortCode(),
                url.getLabel(),
                url.getLongUrl(),
                url.isQrCodeEnabled(),
                url.getCreatedAt(),
                url.getExpiresAt()
        );
    }
}
