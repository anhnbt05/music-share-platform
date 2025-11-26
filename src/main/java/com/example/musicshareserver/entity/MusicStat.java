package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "music_stats")
public class MusicStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @Column(nullable = false)
    private Integer listens = 0;

    @Column(nullable = false)
    private Integer shares = 0;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public MusicStat() {
        this.updatedAt = LocalDateTime.now();
    }

    public MusicStat(Music music) {
        this();
        this.music = music;
    }

    public Long getId() { return id; }
    public Music getMusic() { return music; }
    public void setMusic(Music music) { this.music = music; }
    public Integer getListens() { return listens; }
    public void setListens(Integer listens) { this.listens = listens; }
    public Integer getShares() { return shares; }
    public void setShares(Integer shares) { this.shares = shares; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}