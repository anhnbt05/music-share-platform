package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 255)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String password;

    private LocalDateTime createdAt;

    private Boolean isDeleted;

    private Boolean isActive = false;

    @Column(length = 255)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<ActivityLog> activityLogs;

    @OneToMany(mappedBy = "user")
    private List<ArtistApplication> artistApplications;

    @OneToOne(mappedBy = "user")
    private ArtistProfile artistProfile;

    @OneToMany(mappedBy = "follower")
    private List<Follow> follows;

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "user")
    private List<Vote> votes;
}