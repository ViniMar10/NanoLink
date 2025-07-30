package com.vini.url_shortner_backend.dto.url;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class shortUrlGenerateRequestDto {
    @NotBlank
    @URL(protocol = "https",message="Invalid or insecure URL")
    @Size(max = 2048)
    private String longUrl;
}
