package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "music_stats")
@Getter
@Setter
public class MusicStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer listens;

    private Integer shares;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
