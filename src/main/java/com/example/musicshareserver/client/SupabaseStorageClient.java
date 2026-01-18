package com.example.musicshareserver.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "supabase-storage",
        url = "${SUPABASE_URL}/storage/v1"
)
public interface SupabaseStorageClient {

    @PostMapping("/object/{bucket}/{path}")
    @Headers("Authorization: Bearer {token}")
    ResponseEntity<Void> uploadFile(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("bucket") String bucket,
            @PathVariable("path") String path,
            @RequestHeader("Content-Type") String contentType,
            @RequestBody byte[] fileData
    );

    @DeleteMapping("/object/{bucket}/{path}")
    @Headers("Authorization: Bearer {token}")
    ResponseEntity<Void> deleteFile(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("bucket") String bucket,
            @PathVariable("path") String path
    );
}
