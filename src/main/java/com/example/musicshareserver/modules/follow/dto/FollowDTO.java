// File: com/example/musicshareserver/modules/follow/dto/FollowDTO.java
package com.example.musicshareserver.modules.follow.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class FollowDTO {

    @Schema(description = "Phản hồi danh sách nghệ sĩ đang theo dõi")
    public static class FollowingResponse {
        @Schema(description = "Danh sách nghệ sĩ")
        private List<ArtistInfo> artists;

        @Schema(description = "Tổng số nghệ sĩ đang theo dõi")
        private Integer total;

        public FollowingResponse() {}

        @Schema(description = "Thông tin nghệ sĩ")
        public static class ArtistInfo {
            private Long id;
            private String stageName;
            private String photoUrl;
            private String bio;
            private Integer followerCount;
            private Integer musicCount;
            private LocalDateTime followedSince;

            public ArtistInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getStageName() { return stageName; }
            public void setStageName(String stageName) { this.stageName = stageName; }
            public String getPhotoUrl() { return photoUrl; }
            public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
            public String getBio() { return bio; }
            public void setBio(String bio) { this.bio = bio; }
            public Integer getFollowerCount() { return followerCount; }
            public void setFollowerCount(Integer followerCount) { this.followerCount = followerCount; }
            public Integer getMusicCount() { return musicCount; }
            public void setMusicCount(Integer musicCount) { this.musicCount = musicCount; }
            public LocalDateTime getFollowedSince() { return followedSince; }
            public void setFollowedSince(LocalDateTime followedSince) { this.followedSince = followedSince; }
        }

        // Getters and Setters
        public List<ArtistInfo> getArtists() { return artists; }
        public void setArtists(List<ArtistInfo> artists) { this.artists = artists; }
        public Integer getTotal() { return total; }
        public void setTotal(Integer total) { this.total = total; }
    }

    @Schema(description = "Phản hồi danh sách người theo dõi")
    public static class FollowersResponse {
        @Schema(description = "Danh sách người theo dõi")
        private List<FollowerInfo> followers;

        @Schema(description = "Tổng số người theo dõi")
        private Integer total;

        public FollowersResponse() {}

        @Schema(description = "Thông tin người theo dõi")
        public static class FollowerInfo {
            private Long id;
            private String name;
            private String avatarUrl;
            private LocalDateTime followedAt;

            public FollowerInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public String getAvatarUrl() { return avatarUrl; }
            public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
            public LocalDateTime getFollowedAt() { return followedAt; }
            public void setFollowedAt(LocalDateTime followedAt) { this.followedAt = followedAt; }
        }

        // Getters and Setters
        public List<FollowerInfo> getFollowers() { return followers; }
        public void setFollowers(List<FollowerInfo> followers) { this.followers = followers; }
        public Integer getTotal() { return total; }
        public void setTotal(Integer total) { this.total = total; }
    }
}