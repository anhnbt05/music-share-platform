package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;

    @Column(length = 255)
    private String voteType;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Music music;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
