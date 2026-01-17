package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.artist.*;
import com.example.musicshareserver.dto.response.ApiResponse;
import com.example.musicshareserver.entity.*;
import com.example.musicshareserver.service.artist.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
@Tag(name = "Artist", description = "Artist management APIs")
public class ArtistController {

    private final ArtistService artistService;

    /* ================= MUSIC ================= */

    @Operation(summary = "Upload bài hát mới")
    @PostMapping(value = "/music", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Map<String, Object>>> uploadMusic(
            @Valid @RequestPart("data") UploadMusicRequest request,
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.uploadMusic(userId, request, file);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Danh sách bài hát của artist")
    @GetMapping("/music")
    public ResponseEntity<ApiResponse<List<Music>>> getArtistMusic(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        List<Music> music = artistService.getArtistMusic(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách bài hát thành công", music));
    }

    @Operation(summary = "Cập nhật thông tin bài hát")
    @PutMapping("/music/{trackId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateMusic(
            @PathVariable Long trackId,
            @Valid @RequestBody UpdateMusicRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.updateMusic(userId, trackId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Xóa bài hát")
    @DeleteMapping("/music/{trackId}")
    public ResponseEntity<ApiResponse<Void>> deleteMusic(
            @PathVariable Long trackId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, String> result = artistService.deleteMusic(userId, trackId);
        return ResponseEntity.ok(new ApiResponse<>(true, result.get("message"), null));
    }

    /* ================= ALBUMS ================= */

    @Operation(summary = "Tạo album mới")
    @PostMapping("/albums")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createAlbum(
            @Valid @RequestBody CreateAlbumRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.createAlbum(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Danh sách album của artist")
    @GetMapping("/albums")
    public ResponseEntity<ApiResponse<List<Album>>> getArtistAlbums(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        List<Album> albums = artistService.getArtistAlbums(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách album thành công", albums));
    }

    @Operation(summary = "Chi tiết album")
    @GetMapping("/albums/{albumId}")
    public ResponseEntity<ApiResponse<Album>> getAlbumDetail(
            @PathVariable Long albumId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Album album = artistService.getAlbumDetail(userId, albumId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy chi tiết album thành công", album));
    }

    @Operation(summary = "Cập nhật album")
    @PutMapping("/albums/{albumId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateAlbum(
            @PathVariable Long albumId,
            @Valid @RequestBody UpdateAlbumRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.updateAlbum(userId, albumId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Xóa album")
    @DeleteMapping("/albums/{albumId}")
    public ResponseEntity<ApiResponse<Void>> deleteAlbum(
            @PathVariable Long albumId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, String> result = artistService.deleteAlbum(userId, albumId);
        return ResponseEntity.ok(new ApiResponse<>(true, result.get("message"), null));
    }

    /* ================= ALBUM TRACKS ================= */

    @Operation(summary = "Thêm bài hát vào album")
    @PostMapping("/albums/{albumId}/tracks")
    public ResponseEntity<ApiResponse<Map<String, Object>>> addTracksToAlbum(
            @PathVariable Long albumId,
            @Valid @RequestBody AddTracksToAlbumRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.addTracksToAlbum(userId, albumId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Xóa bài hát khỏi album")
    @DeleteMapping("/albums/{albumId}/tracks/{trackId}")
    public ResponseEntity<ApiResponse<Void>> removeTrackFromAlbum(
            @PathVariable Long albumId,
            @PathVariable Long trackId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, String> result = artistService.removeTrackFromAlbum(userId, albumId, trackId);
        return ResponseEntity.ok(new ApiResponse<>(true, result.get("message"), null));
    }

    /* ================= ANALYTICS ================= */

    @Operation(summary = "Lấy thống kê")
    @GetMapping("/analytics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAnalytics(
            @ModelAttribute AnalyticsFilterRequest filter,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> analytics = artistService.getAnalytics(userId, filter);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thống kê thành công", analytics));
    }

    /* ================= PROFILE ================= */

    @Operation(summary = "Lấy thông tin profile")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ArtistProfile>> getArtistProfile(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        ArtistProfile profile = artistService.getArtistProfile(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin profile thành công", profile));
    }

    @Operation(summary = "Cập nhật profile")
    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        Map<String, Object> result = artistService.updateProfile(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }
}
