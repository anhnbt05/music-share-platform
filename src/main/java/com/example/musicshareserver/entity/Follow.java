package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_artist_id", nullable = false)
    private ArtistProfile followedArtist;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Follow() {
        this.createdAt = LocalDateTime.now();
    }

    public Follow(User follower, ArtistProfile followedArtist) {
        this();
        this.follower = follower;
        this.followedArtist = followedArtist;
    }

    public Long getId() { return id; }
    public User getFollower() { return follower; }
    public void setFollower(User follower) { this.follower = follower; }
    public ArtistProfile getFollowedArtist() { return followedArtist; }
    public void setFollowedArtist(ArtistProfile followedArtist) { this.followedArtist = followedArtist; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}