package com.example.musicshareserver.service.storage;

import com.example.musicshareserver.client.SupabaseStorageClient;
import com.example.musicshareserver.common.dto.UploadOptions;
import com.example.musicshareserver.common.dto.UploadResult;
import com.example.musicshareserver.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Value("${SUPABASE_URL}")
    private String supabaseUrl;

    @Value("${SUPABASE_SERVICE_ROLE_KEY}")
    private String serviceRoleKey;

    private final SupabaseStorageClient supabaseClient;

    @Override
    public UploadResult uploadFile(MultipartFile file, UploadOptions options) {
        validateFile(file, options);

        try {
            String ext = getFileExtension(file.getOriginalFilename());
            String folder = options.getFolder() != null ? options.getFolder() : "general";

            String fileName = folder + "/"
                    + System.currentTimeMillis()
                    + "-" + UUID.randomUUID()
                    + "." + ext;

            String bucket = options.getBucket();

            // Upload using Feign
            supabaseClient.uploadFile(
                    "Bearer " + serviceRoleKey,
                    bucket,
                    fileName,
                    file.getContentType(),
                    file.getBytes()
            );

            // PUBLIC URL
            String publicUrl = supabaseUrl
                    + "/storage/v1/object/public/"
                    + bucket
                    + "/" + fileName;

            return new UploadResult(publicUrl, fileName);

        } catch (Exception e) {
            throw new ApiException("File upload bị lỗi: " + e.getMessage());
        }
    }

    @Override
    public List<UploadResult> uploadMultipleFiles(List<MultipartFile> files, UploadOptions options) {
        return files.stream()
                .map(file -> uploadFile(file, options))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteFile(String bucket, String fileUrl) {
        try {
            String filePath = extractFilePathFromUrl(fileUrl);
            return deleteFileByPath(bucket, filePath);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteFileByPath(String bucket, String filePath) {
        try {
            supabaseClient.deleteFile(
                    "Bearer " + serviceRoleKey,
                    bucket,
                    filePath
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* ================= PRIVATE METHODS ================= */

    private void validateFile(MultipartFile file, UploadOptions options) {
        if (file == null || file.isEmpty()) {
            throw new ApiException("Chưa có đính kèm file");
        }

        if (options.getAllowedMimeTypes() != null
                && !options.getAllowedMimeTypes().contains(file.getContentType())) {
            throw new ApiException(
                    "Loại file không hợp lệ, chỉ cho phép: "
                            + String.join(", ", options.getAllowedMimeTypes())
            );
        }

        if (options.getMaxFileSize() != null
                && file.getSize() > options.getMaxFileSize()) {
            long maxSizeMB = options.getMaxFileSize() / (1024 * 1024);
            throw new ApiException("Dung lượng file quá lớn, tối đa: " + maxSizeMB + "MB");
        }
    }

    private String extractFilePathFromUrl(String url) {
        try {
            URL urlObj = new URL(url);
            String[] parts = urlObj.getPath().split("/");
            for (int i = 0; i < parts.length; i++) {
                if ("object".equals(parts[i]) && i + 2 < parts.length) {
                    return String.join("/", List.of(parts).subList(i + 2, parts.length));
                }
            }
        } catch (Exception ignored) {}

        String[] fallback = url.split("/");
        return fallback[fallback.length - 2] + "/" + fallback[fallback.length - 1];
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "dat";
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
