package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistProfile artist;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumTrack> albumTracks = new ArrayList<>();

    public Album() {
        this.createdAt = LocalDateTime.now();
    }

    public Album(ArtistProfile artist, String title) {
        this();
        this.artist = artist;
        this.title = title;
    }

    public Long getId() { return id; }
    public ArtistProfile getArtist() { return artist; }
    public void setArtist(ArtistProfile artist) { this.artist = artist; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<AlbumTrack> getAlbumTracks() { return albumTracks; }
    public void setAlbumTracks(List<AlbumTrack> albumTracks) { this.albumTracks = albumTracks; }
}