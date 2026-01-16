package com.example.musicshareserver.repository;

import com.example.musicshareserver.entity.ArtistApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistApplicationRepository
        extends JpaRepository<ArtistApplication, Long> {

    Page<ArtistApplication> findByStatus(String status, Pageable pageable);
}
