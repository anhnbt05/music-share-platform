package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 255)
    private String email;

    private String name;

    private String password;

    private LocalDateTime createdAt;

    @Builder.Default
    private Boolean isActive = false;

    @Builder.Default
    private Boolean isDeleted = false;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<ActivityLog> activityLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ArtistApplication> artistApplications = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private ArtistProfile artistProfile;

    @OneToMany(mappedBy = "follower")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();
}