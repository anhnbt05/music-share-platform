package com.example.musicshareserver.controller;

import com.example.musicshareserver.dto.request.user.*;
import com.example.musicshareserver.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/{userId}/playlists")
    public ResponseEntity<?> createPlaylist(@PathVariable Long userId, @Valid @RequestBody CreatePlaylistRequest dto) {
        return ResponseEntity.ok(userService.createPlaylist(userId, dto));
    }

    @GetMapping("/{userId}/playlists")
    public ResponseEntity<?> getUserPlaylists(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserPlaylists(userId));
    }

    @GetMapping("/{userId}/playlists/{playlistId}")
    public ResponseEntity<?> getPlaylistDetail(@PathVariable Long userId, @PathVariable Long playlistId) {
        return ResponseEntity.ok(userService.getPlaylistDetail(userId, playlistId));
    }

    @PutMapping("/{userId}/playlists/{playlistId}")
    public ResponseEntity<?> updatePlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @Valid @RequestBody UpdatePlaylistRequest dto) {
        return ResponseEntity.ok(userService.updatePlaylist(userId, playlistId, dto));
    }

    @DeleteMapping("/{userId}/playlists/{playlistId}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @RequestBody DeletePlaylistRequest dto) {
        return ResponseEntity.ok(userService.deletePlaylist(userId, playlistId, dto));
    }

    @PostMapping("/{userId}/playlists/{playlistId}/tracks")
    public ResponseEntity<?> addTrackToPlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @Valid @RequestBody AddTrackToPlaylistRequest dto) {
        return ResponseEntity.ok(userService.addTrackToPlaylist(userId, playlistId, dto));
    }

    @DeleteMapping("/{userId}/playlists/{playlistId}/tracks/{trackId}")
    public ResponseEntity<?> removeTrackFromPlaylist(@PathVariable Long userId, @PathVariable Long playlistId, @PathVariable Long trackId, @Valid @RequestBody RemoveTrackFromPlaylistRequest dto) {
        return ResponseEntity.ok(userService.removeTrackFromPlaylist(userId, playlistId, trackId, dto));
    }

    @PostMapping("/{userId}/tracks/{trackId}/vote")
    public ResponseEntity<?> voteTrack(@PathVariable Long userId, @PathVariable Long trackId, @Valid @RequestBody VoteRequest dto) {
        return ResponseEntity.ok(userService.voteTrack(userId, trackId, dto));
    }

    @DeleteMapping("/{userId}/tracks/{trackId}/vote")
    public ResponseEntity<?> unvoteTrack(@PathVariable Long userId, @PathVariable Long trackId) {
        return ResponseEntity.ok(userService.unvoteTrack(userId, trackId));
    }

    @PostMapping("/{userId}/artists/{artistId}/follow")
    public ResponseEntity<?> followArtist(@PathVariable Long userId, @PathVariable Long artistId) {
        return ResponseEntity.ok(userService.followArtist(userId, artistId));
    }

    @DeleteMapping("/{userId}/artists/{artistId}/follow")
    public ResponseEntity<?> unfollowArtist(@PathVariable Long userId, @PathVariable Long artistId) {
        return ResponseEntity.ok(userService.unfollowArtist(userId, artistId));
    }

    @GetMapping("/{userId}/artists/following")
    public ResponseEntity<?> getFollowingArtists(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowingArtists(userId));
    }

    @PostMapping("/{userId}/reports")
    public ResponseEntity<?> createReport(@PathVariable Long userId, @Valid @RequestBody CreateReportRequest dto) {
        return ResponseEntity.ok(userService.createReport(userId, dto));
    }

    @PostMapping("/{userId}/artist-application")
    public ResponseEntity<?> applyArtist(@PathVariable Long userId, @Valid @RequestBody ApplyArtistRequest dto) {
        return ResponseEntity.ok(userService.applyArtist(userId, dto));
    }
}
