package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserIdAndMusicId(Long userId, Long musicId);
}
