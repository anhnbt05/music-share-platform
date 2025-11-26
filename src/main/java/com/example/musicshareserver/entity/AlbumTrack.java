package com.example.musicshareserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "album_tracks")
public class AlbumTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Music music;

    @Column(name = "track_order")
    private Integer trackOrder;

    public AlbumTrack() {}

    public AlbumTrack(Album album, Music music) {
        this.album = album;
        this.music = music;
    }

    public Long getId() { return id; }
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
    public Music getMusic() { return music; }
    public void setMusic(Music music) { this.music = music; }
    public Integer getTrackOrder() { return trackOrder; }
    public void setTrackOrder(Integer trackOrder) { this.trackOrder = trackOrder; }
}