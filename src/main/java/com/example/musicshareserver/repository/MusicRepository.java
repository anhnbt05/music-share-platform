package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    
    List<Music> findByArtistProfileIdAndDeletedAtIsNull(Long artistId);
    
    Optional<Music> findByIdAndArtistProfileId(Long id, Long artistId);
}
