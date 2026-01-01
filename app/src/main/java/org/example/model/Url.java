package org.example.model;

import java.time.Instant;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "urls",
    indexes = {
        @Index(name = "idx_shortUrl", columnList = "short_urls")
    }
)
public class Url {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "short_urls",unique = true,nullable = false)
    String shortUrl;

    String longUrl;

    Instant createdAt;

    Instant expirationTime;
    
}
