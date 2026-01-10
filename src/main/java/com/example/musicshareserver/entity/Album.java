package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String coverUrl;

    private LocalDateTime createdAt;

    private String description;

    @Column(length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistProfile artistProfile;

    @OneToMany(mappedBy = "album")
    private List<AlbumTrack> albumTracks;
}
