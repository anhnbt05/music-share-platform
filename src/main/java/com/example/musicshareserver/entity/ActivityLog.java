package com.example.musicshareserver.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "action_type", nullable = false)
    private String actionType; // UPLOAD, VOTE, LOGIN, DELETE, etc.

    @Column(name = "object_id")
    private Long objectId; // ID of the related object (music, user, etc.)

    @Column(name = "object_type")
    private String objectType; // MUSIC, USER, COMMENT, etc.

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    private String description;

    public ActivityLog() {
        this.timestamp = LocalDateTime.now();
    }

    public ActivityLog(User user, String actionType) {
        this();
        this.user = user;
        this.actionType = actionType;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public Long getObjectId() { return objectId; }
    public void setObjectId(Long objectId) { this.objectId = objectId; }
    public String getObjectType() { return objectType; }
    public void setObjectType(String objectType) { this.objectType = objectType; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}