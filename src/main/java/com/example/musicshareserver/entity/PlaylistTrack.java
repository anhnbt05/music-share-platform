package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_tracks")
public class PlaylistTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Music music;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    @Column(name = "track_order")
    private Integer trackOrder;

    public PlaylistTrack() {
        this.addedAt = LocalDateTime.now();
    }

    public PlaylistTrack(Playlist playlist, Music music) {
        this();
        this.playlist = playlist;
        this.music = music;
    }

    public Long getId() { return id; }
    public Playlist getPlaylist() { return playlist; }
    public void setPlaylist(Playlist playlist) { this.playlist = playlist; }
    public Music getMusic() { return music; }
    public void setMusic(Music music) { this.music = music; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
    public Integer getTrackOrder() { return trackOrder; }
    public void setTrackOrder(Integer trackOrder) { this.trackOrder = trackOrder; }
}