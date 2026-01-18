package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.music.SearchMusicDto;
import com.example.musicshareserver.dto.request.music.ShareMusicDto;
import com.example.musicshareserver.dto.response.ApiResponse;
import com.example.musicshareserver.dto.response.PageResponse;
import com.example.musicshareserver.entity.Music;
import com.example.musicshareserver.service.music.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/music")
@RequiredArgsConstructor
@Tag(name = "Music", description = "Music management APIs")
public class MusicController {

    private final MusicService musicService;

    @Operation(summary = "Tìm kiếm bài hát")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Music>>> searchMusic(@ModelAttribute SearchMusicDto dto) {
        PageResponse<Music> result = musicService.searchMusic(dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Tìm kiếm thành công", result));
    }

    @Operation(summary = "Lấy chi tiết bài hát")
    @GetMapping("/{trackId}")
    public ResponseEntity<ApiResponse<Music>> getMusicDetail(@PathVariable Long trackId) {
        Music result = musicService.getMusicDetail(trackId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy chi tiết thành công", result));
    }

    @Operation(summary = "Stream bài hát")
    @GetMapping("/{trackId}/stream")
    public ResponseEntity<ApiResponse<Map<String, String>>> streamMusic(@PathVariable Long trackId) {
        Map<String, String> result = musicService.streamMusic(trackId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy link stream thành công", result));
    }

    @Operation(summary = "Chia sẻ bài hát")
    @PostMapping("/{trackId}/share")
    public ResponseEntity<ApiResponse<Map<String, Object>>> shareMusic(
            @PathVariable Long trackId,
            @RequestBody ShareMusicDto dto
    ) {
        Map<String, Object> result = musicService.shareMusic(trackId, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, (String) result.get("message"), result));
    }

    @Operation(summary = "Lấy bài hát theo thể loại")
    @GetMapping("/genre/{genre}")
    public ResponseEntity<ApiResponse<PageResponse<Music>>> getMusicByGenre(
            @PathVariable String genre,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        PageResponse<Music> result = musicService.getMusicByGenre(genre, page, limit);
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách thành công", result));
    }

    @Operation(summary = "Lấy tất cả bài hát")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Music>>> getAllMusic() {
        List<Music> result = musicService.getAllMusic();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy tất cả bài hát thành công", result));
    }
}
