package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String reason;

    private LocalDateTime reportDate;

    @Column(length = 255)
    private String reportType;

    @Column(length = 255)
    private String resolutionNotes;

    @Column(length = 255)
    private String status;

    /* ===== Reported targets ===== */

    @ManyToOne
    @JoinColumn(name = "reported_artist_id")
    private ArtistProfile reportedArtist;

    @ManyToOne
    @JoinColumn(name = "reported_music_id")
    private Music reportedMusic;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reportedUser;

    @ManyToOne
    @JoinColumn(name = "reported_playlist_id")
    private Playlist reportedPlaylist;

    /* ===== Reporter ===== */

    @ManyToOne(optional = false)
    @JoinColumn(name = "reporter_user_id")
    private User reporterUser;
}