package com.example.musicshareserver.service.artist;

import com.example.musicshareserver.dto.request.artist.*;
import com.example.musicshareserver.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArtistService {
    
    Map<String, Object> uploadMusic(Long userId, UploadMusicRequest dto, MultipartFile file);
    
    List<Music> getArtistMusic(Long userId);
    
    Map<String, Object> updateMusic(Long userId, Long trackId, UpdateMusicRequest dto);
    
    Map<String, String> deleteMusic(Long userId, Long trackId);
    
    Map<String, Object> createAlbum(Long userId, CreateAlbumRequest dto);
    
    List<Album> getArtistAlbums(Long userId);
    
    Album getAlbumDetail(Long userId, Long albumId);
    
    Map<String, Object> updateAlbum(Long userId, Long albumId, UpdateAlbumRequest dto);
    
    Map<String, String> deleteAlbum(Long userId, Long albumId);
    
    Map<String, Object> addTracksToAlbum(Long userId, Long albumId, AddTracksToAlbumRequest dto);
    
    Map<String, String> removeTrackFromAlbum(Long userId, Long albumId, Long trackId);
    
    Map<String, Object> getAnalytics(Long userId, AnalyticsFilterRequest filter);
    
    Map<String, Object> updateProfile(Long userId, UpdateProfileRequest dto);
    
    ArtistProfile getArtistProfile(Long userId);
}
