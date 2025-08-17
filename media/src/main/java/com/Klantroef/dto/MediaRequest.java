package com.Klantroef.dto;


import com.Klantroef.model.MediaAsset.MediaType;

import lombok.Data;

@Data
public class MediaRequest {
    private String title;
    private MediaType type;
    private String fileUrl; 
    
    
}