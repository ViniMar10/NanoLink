package com.vini.url_shortner_backend.url.controller;

import com.vini.url_shortner_backend.url.model.dto.PublicShortUrlGenerateRequestDto;
import com.vini.url_shortner_backend.url.model.dto.UrlGenerationResponseDto;
import com.vini.url_shortner_backend.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/public")
    public ResponseEntity<UrlGenerationResponseDto> generatePublicUrl(@Valid @RequestBody PublicShortUrlGenerateRequestDto requestDto){
        UrlGenerationResponseDto response = urlService.generateShortUrlForNonRegisteredUsers(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
