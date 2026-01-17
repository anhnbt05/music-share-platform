package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.AlbumTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumTrackRepository extends JpaRepository<AlbumTrack, Long> {
    
    List<AlbumTrack> findByAlbumIdOrderByTrackOrderAsc(Long albumId);
    
    Optional<AlbumTrack> findByAlbumIdAndMusicId(Long albumId, Long trackId);
    
    Long countByAlbumId(Long albumId);
    
    @Modifying
    @Query("DELETE FROM AlbumTrack at WHERE at.album.id = ?1 AND at.music.id = ?2")
    void deleteByAlbumIdAndMusicId(Long albumId, Long trackId);
    
    void deleteByAlbumId(Long albumId);
}
