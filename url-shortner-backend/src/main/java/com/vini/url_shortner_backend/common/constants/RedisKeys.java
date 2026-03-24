package com.vini.url_shortner_backend.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisKeys {
    public static final int URL_TTL_HOURS = 24;
    public static final int PUBLIC_URL_GENERATION_HISTORY_TTL_HOURS = 24;

    public static final String URL_PREFIX = "url-shortener-backend::urls::";
    public static final String PUBLIC_URL_GENERATION_HISTORY_PREFIX = "url-shortener-backend::public-url-generation-history::";
    public static final String STATS_CLICKS_SHORT_PREFIX = "url-shortener-backend::stats::clicks::short::";
    public static final String STATS_CLICKS_QR_PREFIX = "url-shortener-backend::stats::clicks::qr::";

    public static String urlKey(String shortCode) {
        return URL_PREFIX + shortCode;
    }

    public static String publicUrlGenerationHistoryKey(String longUrl) {
        return PUBLIC_URL_GENERATION_HISTORY_PREFIX + longUrl;
    }

    public static String clicksShortKey(String shortCode) {
        return STATS_CLICKS_SHORT_PREFIX + shortCode;
    }

    public static String clicksQrKey(String shortCode) {
        return STATS_CLICKS_QR_PREFIX + shortCode;
    }
}
