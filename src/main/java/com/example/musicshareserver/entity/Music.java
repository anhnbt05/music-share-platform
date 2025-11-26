package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistProfile artist;

    @Column(nullable = false)
    private String title;

    private String genre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount = 0;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<PlaylistTrack> playlistTracks = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<AlbumTrack> albumTracks = new ArrayList<>();

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<MusicStat> musicStats = new ArrayList<>();

    @OneToMany(mappedBy = "reportedMusic", cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    public Music() {
        this.createdAt = LocalDateTime.now();
    }

    public Music(ArtistProfile artist, String title, String fileUrl) {
        this();
        this.artist = artist;
        this.title = title;
        this.fileUrl = fileUrl;
    }

    public Long getId() { return id; }
    public ArtistProfile getArtist() { return artist; }
    public void setArtist(ArtistProfile artist) { this.artist = artist; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Integer getVoteCount() { return voteCount; }
    public void setVoteCount(Integer voteCount) { this.voteCount = voteCount; }
    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
    public List<PlaylistTrack> getPlaylistTracks() { return playlistTracks; }
    public void setPlaylistTracks(List<PlaylistTrack> playlistTracks) { this.playlistTracks = playlistTracks; }
    public List<AlbumTrack> getAlbumTracks() { return albumTracks; }
    public void setAlbumTracks(List<AlbumTrack> albumTracks) { this.albumTracks = albumTracks; }
    public List<MusicStat> getMusicStats() { return musicStats; }
    public void setMusicStats(List<MusicStat> musicStats) { this.musicStats = musicStats; }
    public List<Report> getReports() { return reports; }
    public void setReports(List<Report> reports) { this.reports = reports; }
}