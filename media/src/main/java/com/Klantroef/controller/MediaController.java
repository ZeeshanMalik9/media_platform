package com.Klantroef.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Klantroef.dto.MediaRequest;
import com.Klantroef.dto.StreamUrlResponse;
import com.Klantroef.model.MediaAsset;
import com.Klantroef.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<MediaAsset> addMedia(@RequestBody MediaRequest mediaRequest) {
        MediaAsset newMedia = mediaService.addMedia(mediaRequest);
        return ResponseEntity.ok(newMedia);
    }

    @GetMapping("/{id}/stream-url")
    public ResponseEntity<StreamUrlResponse> getStreamUrl(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.generateStreamUrl(id));
    }
}