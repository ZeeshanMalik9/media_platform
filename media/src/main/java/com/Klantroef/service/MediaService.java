package com.Klantroef.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Klantroef.dto.MediaRequest;
import com.Klantroef.dto.StreamUrlResponse;
import com.Klantroef.model.MediaAsset;
import com.Klantroef.repository.MediaAssetRepository;
import com.Klantroef.security.JwtUtil;

@Service
public class MediaService {

    @Autowired
    private MediaAssetRepository mediaAssetRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public MediaAsset addMedia(MediaRequest mediaRequest) {
        MediaAsset mediaAsset = new MediaAsset();
        mediaAsset.setTitle(mediaRequest.getTitle());
        mediaAsset.setType(mediaRequest.getType());
        mediaAsset.setFileUrl(mediaRequest.getFileUrl());
        return mediaAssetRepository.save(mediaAsset);
    }

    public StreamUrlResponse generateStreamUrl(Long id) {
        MediaAsset mediaAsset = mediaAssetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));

        // Generate a token that is valid for 10 minutes (600,000 milliseconds)
        String secureToken = jwtUtil.generateSecureUrlToken(mediaAsset.getId(), 1000 * 60 * 10);
        
        // This URL is conceptual. You would need another endpoint to handle it.
        String url = "/api/v1/stream/play?token=" + secureToken;

        return new StreamUrlResponse(url);
    }
}