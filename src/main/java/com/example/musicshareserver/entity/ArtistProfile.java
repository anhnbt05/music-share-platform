package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist_profiles")
@Getter
@Setter
@Builder
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bio;

    @Column(length = 255)
    private String photoUrl;

    private String socialLinks;

    @Column(length = 255)
    private String stageName;

    @Column(length = 255)
    private String status;

    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "artistProfile")
    private List<Album> albums;

    @OneToMany(mappedBy = "artistProfile")
    private List<Music> music;

    @OneToMany(mappedBy = "artistProfile")
    private List<Follow> follows;
}
