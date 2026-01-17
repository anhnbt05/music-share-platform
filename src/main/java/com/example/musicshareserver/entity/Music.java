package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "music")
@Getter
@Setter
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private String description;

    @Column(length = 255)
    private String fileUrl;

    @Column(length = 255)
    private String genre;

    @Column(length = 255)
    private String title;

    private Integer voteCount;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistProfile artistProfile;

    @OneToMany(mappedBy = "music")
    private List<AlbumTrack> albumTracks;

    @OneToMany(mappedBy = "music")
    private List<MusicStat> musicStats;

    @OneToMany(mappedBy = "music")
    private List<Vote> votes;
}
