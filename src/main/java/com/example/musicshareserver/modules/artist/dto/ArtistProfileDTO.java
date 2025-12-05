// File: com/example/musicshareserver/modules/artist/dto/ArtistProfileDTO.java
package com.example.musicshareserver.modules.artist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ArtistProfileDTO {

    @Schema(description = "Yêu cầu cập nhật hồ sơ nghệ sĩ")
    public static class UpdateProfileRequest {
        @Schema(description = "Nghệ danh", required = true)
        @NotBlank(message = "Nghệ danh là bắt buộc")
        private String stageName;

        @Schema(description = "Tiểu sử")
        private String bio;

        @Schema(description = "URL ảnh đại diện")
        private String photoUrl;

        @Schema(description = "Liên kết mạng xã hội (JSON)")
        private String socialLinks;

        public UpdateProfileRequest() {}

        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }
        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        public String getSocialLinks() { return socialLinks; }
        public void setSocialLinks(String socialLinks) { this.socialLinks = socialLinks; }
    }

    @Schema(description = "Phản hồi hồ sơ nghệ sĩ")
    public static class ArtistProfileResponse {
        @Schema(description = "ID hồ sơ")
        private Long id;

        @Schema(description = "Thông tin người dùng")
        private UserInfo user;

        @Schema(description = "Nghệ danh")
        private String stageName;

        @Schema(description = "Tiểu sử")
        private String bio;

        @Schema(description = "URL ảnh đại diện")
        private String photoUrl;

        @Schema(description = "Liên kết mạng xã hội")
        private String socialLinks;

        @Schema(description = "Trạng thái")
        private String status;

        @Schema(description = "Ngày cập nhật")
        private LocalDateTime updatedAt;

        @Schema(description = "Số người theo dõi")
        private Integer followerCount;

        @Schema(description = "Số bài hát")
        private Integer musicCount;

        @Schema(description = "Số album")
        private Integer albumCount;

        @Schema(description = "Tổng lượt nghe")
        private Long totalListens;

        @Schema(description = "Đang theo dõi")
        private Boolean isFollowing;

        public ArtistProfileResponse() {}

        @Schema(description = "Thông tin người dùng")
        public static class UserInfo {
            private Long id;
            private String email;
            private String name;
            private LocalDateTime joinedDate;

            public UserInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public LocalDateTime getJoinedDate() { return joinedDate; }
            public void setJoinedDate(LocalDateTime joinedDate) { this.joinedDate = joinedDate; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public UserInfo getUser() { return user; }
        public void setUser(UserInfo user) { this.user = user; }
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
        public Integer getFollowerCount() { return followerCount; }
        public void setFollowerCount(Integer followerCount) { this.followerCount = followerCount; }
        public Integer getMusicCount() { return musicCount; }
        public void setMusicCount(Integer musicCount) { this.musicCount = musicCount; }
        public Integer getAlbumCount() { return albumCount; }
        public void setAlbumCount(Integer albumCount) { this.albumCount = albumCount; }
        public Long getTotalListens() { return totalListens; }
        public void setTotalListens(Long totalListens) { this.totalListens = totalListens; }
        public Boolean getIsFollowing() { return isFollowing; }
        public void setIsFollowing(Boolean isFollowing) { this.isFollowing = isFollowing; }
    }

    @Schema(description = "Phản hồi thống kê nghệ sĩ")
    public static class AnalyticsResponse {
        @Schema(description = "Tổng lượt nghe")
        private Long totalListens;

        @Schema(description = "Tổng lượt like")
        private Long totalLikes;

        @Schema(description = "Tổng lượt share")
        private Long totalShares;

        @Schema(description = "Số người theo dõi")
        private Integer totalFollowers;

        @Schema(description = "Thống kê theo ngày")
        private java.util.List<DailyStat> dailyStats;

        @Schema(description = "Top bài hát")
        private java.util.List<TopMusic> topMusics;

        public AnalyticsResponse() {}

        @Schema(description = "Thống kê theo ngày")
        public static class DailyStat {
            private String date;
            private Integer listens;
            private Integer likes;
            private Integer shares;
            private Integer newFollowers;

            public DailyStat() {}

            public String getDate() { return date; }
            public void setDate(String date) { this.date = date; }
            public Integer getListens() { return listens; }
            public void setListens(Integer listens) { this.listens = listens; }
            public Integer getLikes() { return likes; }
            public void setLikes(Integer likes) { this.likes = likes; }
            public Integer getShares() { return shares; }
            public void setShares(Integer shares) { this.shares = shares; }
            public Integer getNewFollowers() { return newFollowers; }
            public void setNewFollowers(Integer newFollowers) { this.newFollowers = newFollowers; }
        }

        @Schema(description = "Top bài hát")
        public static class TopMusic {
            private Long id;
            private String title;
            private Integer listens;
            private Integer likes;
            private Integer shares;

            public TopMusic() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public Integer getListens() { return listens; }
            public void setListens(Integer listens) { this.listens = listens; }
            public Integer getLikes() { return likes; }
            public void setLikes(Integer likes) { this.likes = likes; }
            public Integer getShares() { return shares; }
            public void setShares(Integer shares) { this.shares = shares; }
        }

        // Getters and Setters
        public Long getTotalListens() { return totalListens; }
        public void setTotalListens(Long totalListens) { this.totalListens = totalListens; }
        public Long getTotalLikes() { return totalLikes; }
        public void setTotalLikes(Long totalLikes) { this.totalLikes = totalLikes; }
        public Long getTotalShares() { return totalShares; }
        public void setTotalShares(Long totalShares) { this.totalShares = totalShares; }
        public Integer getTotalFollowers() { return totalFollowers; }
        public void setTotalFollowers(Integer totalFollowers) { this.totalFollowers = totalFollowers; }
        public java.util.List<DailyStat> getDailyStats() { return dailyStats; }
        public void setDailyStats(java.util.List<DailyStat> dailyStats) { this.dailyStats = dailyStats; }
        public java.util.List<TopMusic> getTopMusics() { return topMusics; }
        public void setTopMusics(java.util.List<TopMusic> topMusics) { this.topMusics = topMusics; }
    }
}