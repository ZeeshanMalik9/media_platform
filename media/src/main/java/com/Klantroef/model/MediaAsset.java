package com.Klantroef.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
@Table(name = "media_assets")
public class MediaAsset {

    public enum MediaType {
        VIDEO, AUDIO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING) // Stores the enum as a string ("VIDEO" or "AUDIO")
    @Column(nullable = false)
    private MediaType type;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();
}