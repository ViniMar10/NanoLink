package com.vini.url_shortner_backend.url.model.dto;

import java.time.LocalDateTime;

public record UrlGenerationResponseDto(
    String shortCode,
    String label,
    String longUrl,
    boolean qrCodeEnabled,
    LocalDateTime createdAt,
    LocalDateTime expiresAt
) {}
