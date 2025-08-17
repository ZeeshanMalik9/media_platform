package com.Klantroef.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
@Table(name = "media_view_logs")
public class MediaViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This links the log entry back to the specific media asset
    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private MediaAsset mediaAsset;

    @Column(name = "viewed_by_ip", nullable = false)
    private String viewedByIp;

    @Column(nullable = false)
    private Instant timestamp = Instant.now();
}