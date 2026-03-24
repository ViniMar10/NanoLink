package com.vini.url_shortner_backend.url.infrastructure.persistence;

import com.vini.url_shortner_backend.common.utils.Base62Util;
import com.vini.url_shortner_backend.url.model.entity.Url;
import com.vini.url_shortner_backend.url.repository.UrlRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UrlPersistence {

    private final UrlRepository urlRepository;

    private static final int MAX_ATTEMPTS = 5;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Url saveWithUniqueCode(Url.UrlBuilder builder) {
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                String code = Base62Util.generateCode();
                return urlRepository.save(builder.shortCode(code).build());
            } catch (DataIntegrityViolationException e) {
                log.warn("Collision detected for short code. Attempt {}/{}", attempt, MAX_ATTEMPTS);
                if (attempt == MAX_ATTEMPTS)
                    throw new IllegalStateException("Could not generate unique short code", e);
            }
        }
        throw new IllegalStateException("An error occurred while generating unique short code");
    }
}
