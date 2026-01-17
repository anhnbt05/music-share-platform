package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.MusicStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicStatRepository extends JpaRepository<MusicStat, Long> {
    
    List<MusicStat> findByMusicArtistProfileId(Long artistId);
    
    Optional<MusicStat> findByMusicId(Long musicId);
}
