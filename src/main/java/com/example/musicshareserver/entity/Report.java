package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User reporter;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "report_date", nullable = false)
    private LocalDateTime reportDate;

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING, RESOLVED

    @ManyToOne
    @JoinColumn(name = "reported_music_id")
    private Music reportedMusic;

    @ManyToOne
    @JoinColumn(name = "reported_artist_id")
    private ArtistProfile reportedArtist;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reportedUser;

    private String resolutionNotes;

    public Report() {
        this.reportDate = LocalDateTime.now();
    }

    public Report(User reporter, String reportType, String reason) {
        this();
        this.reporter = reporter;
        this.reportType = reportType;
        this.reason = reason;
    }

    public Long getId() { return id; }
    public User getReporter() { return reporter; }
    public void setReporter(User reporter) { this.reporter = reporter; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getReportDate() { return reportDate; }
    public void setReportDate(LocalDateTime reportDate) { this.reportDate = reportDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Music getReportedMusic() { return reportedMusic; }
    public void setReportedMusic(Music reportedMusic) { this.reportedMusic = reportedMusic; }
    public ArtistProfile getReportedArtist() { return reportedArtist; }
    public void setReportedArtist(ArtistProfile reportedArtist) { this.reportedArtist = reportedArtist; }
    public User getReportedUser() { return reportedUser; }
    public void setReportedUser(User reportedUser) { this.reportedUser = reportedUser; }
    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
}