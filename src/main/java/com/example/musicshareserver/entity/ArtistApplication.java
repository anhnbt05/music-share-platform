package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "artist_applications")
@Getter
@Setter
public class ArtistApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bio;

    private LocalDateTime createdAt;

    @Column(length = 255)
    private String photoUrl;

    @Column(length = 255)
    private String rejectionReason;

    private String socialLinks;

    @Column(length = 255)
    private String stageName;

    @Column(length = 255)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
