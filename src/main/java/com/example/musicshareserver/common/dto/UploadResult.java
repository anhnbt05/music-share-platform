package com.example.musicshareserver.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadResult {
    private String url;
    private String fileName;
}
