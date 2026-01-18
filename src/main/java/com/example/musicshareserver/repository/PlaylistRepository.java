package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUserIdOrderByCreationDateDesc(Long userId);

    Optional<Playlist> findByIdAndUserId(Long id, Long userId);
}
