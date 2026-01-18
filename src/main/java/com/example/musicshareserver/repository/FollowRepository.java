package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerIdAndArtistProfileId(Long followerId, Long artistProfileId);

    List<Follow> findByFollowerIdOrderByCreatedAtDesc(Long followerId);
}
