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

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("UPDATE MusicStat m SET m.listens = m.listens + 1, m.updatedAt = CURRENT_TIMESTAMP WHERE m.music.id = :musicId")
    void incrementListens(Long musicId);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("UPDATE MusicStat m SET m.shares = m.shares + 1, m.updatedAt = CURRENT_TIMESTAMP WHERE m.music.id = :musicId")
    void incrementShares(Long musicId);
}
