package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Music music;

    @Column(name = "vote_type", nullable = false)
    private String voteType = "LIKE";

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Vote() {
        this.createdAt = LocalDateTime.now();
    }

    public Vote(User user, Music music) {
        this();
        this.user = user;
        this.music = music;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Music getMusic() { return music; }
    public void setMusic(Music music) { this.music = music; }
    public String getVoteType() { return voteType; }
    public void setVoteType(String voteType) { this.voteType = voteType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}