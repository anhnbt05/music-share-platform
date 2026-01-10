package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows")
@Getter
@Setter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "followed_artist_id")
    private ArtistProfile artistProfile;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User follower;
}
