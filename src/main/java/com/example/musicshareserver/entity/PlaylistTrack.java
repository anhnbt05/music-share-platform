package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlist_tracks")
@Getter
@Setter
public class PlaylistTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime addedAt;

    private Integer trackOrder;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
