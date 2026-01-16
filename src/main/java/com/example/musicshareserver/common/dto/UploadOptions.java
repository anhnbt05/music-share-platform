package com.example.musicshareserver.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Tuỳ chọn upload file lên storage")
public class UploadOptions {

    @Schema(description = "Tên bucket trong Supabase Storage", example = "music")
    private String bucket;

    @Schema(description = "Thư mục con trong bucket", example = "tracks")
    private String folder;

    @Schema(
            description = "Danh sách MIME type cho phép",
            example = "[\"audio/mpeg\", \"image/png\"]"
    )
    private List<String> allowedMimeTypes;

    @Schema(
            description = "Dung lượng file tối đa (bytes)",
            example = "10485760"
    )
    private Long maxFileSize;
}
