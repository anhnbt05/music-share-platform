package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist_profiles")
public class ArtistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "stage_name")
    private String stageName;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "social_links", columnDefinition = "TEXT")
    private String socialLinks; // JSON string or comma-separated

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Music> musicList = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(mappedBy = "followedArtist", cascade = CascadeType.ALL)
    private List<Follow> followers = new ArrayList<>();

    public ArtistProfile() {
        this.updatedAt = LocalDateTime.now();
    }

    public ArtistProfile(User user, String stageName) {
        this();
        this.user = user;
        this.stageName = stageName;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getStageName() { return stageName; }
    public void setStageName(String stageName) { this.stageName = stageName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public String getSocialLinks() { return socialLinks; }
    public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public List<Music> getMusicList() { return musicList; }
    public void setMusicList(List<Music> musicList) { this.musicList = musicList; }
    public List<Album> getAlbums() { return albums; }
    public void setAlbums(List<Album> albums) { this.albums = albums; }
    public List<Follow> getFollowers() { return followers; }
    public void setFollowers(List<Follow> followers) { this.followers = followers; }
}