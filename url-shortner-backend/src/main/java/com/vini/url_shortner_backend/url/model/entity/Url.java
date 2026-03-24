package com.vini.url_shortner_backend.url.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "urls",
    indexes = {
            @Index(name = "idx_short_code", columnList = "short_code")
    }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //add owner field when developing private short url generation

    @Column(name = "label")
    private String label;

    @Size(min = 6, max = 10, message = "Short code must be min 6 and max 10 characters long")
    @Column(name = "short_code", nullable = false, unique = true, length = 10)
    private String shortCode;

    @URL
    @Column(name = "long_url", nullable = false, length = 2048)
    private String longUrl;

    @Column(name = "qr_code_enabled", nullable = false)
    @Builder.Default
    private boolean qrCodeEnabled = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "clicks_short_code", nullable = false)
    @Builder.Default
    private long clicksShortCode = 0;

    @Column(name = "clicks_qr_code", nullable = false)
    @Builder.Default
    private long clicksQrCode = 0;

    @Column(name = "private_url", nullable = false)
    @Builder.Default
    private boolean privateUrl = false;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "max_clicks")
    private Long maxClicks;

    @Column(name = "deleted_at")
    @SQLRestriction("deleted_at IS NULL")
    private LocalDateTime deletedAt;
}
