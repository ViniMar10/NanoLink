package com.vini.url_shortner_backend.url.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public record PublicShortUrlGenerateRequestDto(
    @NotBlank(message = "The URL must not be blank")
    @URL(message = "Invalid URL format")
    @Size(max = 2048, message = "URL must not exceed 2048 characters")
    String longUrl
){}
