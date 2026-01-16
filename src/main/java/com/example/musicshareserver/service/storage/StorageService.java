package com.example.musicshareserver.service.storage;

import com.example.musicshareserver.common.dto.UploadResult;
import com.example.musicshareserver.common.dto.UploadOptions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    UploadResult uploadFile(MultipartFile file, UploadOptions options);

    List<UploadResult> uploadMultipleFiles(List<MultipartFile> files, UploadOptions options);

    boolean deleteFile(String bucket, String fileUrl);

    boolean deleteFileByPath(String bucket, String filePath);
}
