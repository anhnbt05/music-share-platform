package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long> {
    void deleteByPlaylistId(Long playlistId);

    Optional<PlaylistTrack> findByPlaylistIdAndMusicId(Long playlistId, Long musicId);

    Long countByPlaylistId(Long playlistId);

    void deleteByPlaylistIdAndMusicId(Long playlistId, Long musicId);

    List<PlaylistTrack> findByPlaylistIdOrderByTrackOrderAsc(Long playlistId);
}
