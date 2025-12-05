// File: com/example/musicshareserver/modules/playlist/dto/PlaylistDTO.java
package com.example.musicshareserver.modules.playlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class PlaylistDTO {

    @Schema(description = "Yêu cầu tạo playlist")
    public static class CreatePlaylistRequest {
        @Schema(description = "Tên playlist", required = true)
        @NotBlank(message = "Tên playlist là bắt buộc")
        private String name;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "Công khai")
        private Boolean isPublic = false;

        public CreatePlaylistRequest() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Boolean getIsPublic() { return isPublic; }
        public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
    }

    @Schema(description = "Yêu cầu cập nhật playlist")
    public static class UpdatePlaylistRequest {
        @Schema(description = "Tên playlist")
        private String name;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "Công khai")
        private Boolean isPublic;

        public UpdatePlaylistRequest() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Boolean getIsPublic() { return isPublic; }
        public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
    }

    @Schema(description = "Phản hồi thông tin playlist")
    public static class PlaylistResponse {
        @Schema(description = "ID playlist")
        private Long id;

        @Schema(description = "Chủ sở hữu")
        private OwnerInfo owner;

        @Schema(description = "Tên playlist")
        private String name;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "Công khai")
        private Boolean isPublic;

        @Schema(description = "Ngày tạo")
        private LocalDateTime creationDate;

        @Schema(description = "Danh sách bài hát")
        private List<MusicInfo> tracks;

        @Schema(description = "Tổng số bài hát")
        private Integer trackCount;

        @Schema(description = "Tổng thời lượng (giây)")
        private Integer totalDuration;

        @Schema(description = "Số người theo dõi playlist")
        private Integer followerCount;

        public PlaylistResponse() {}

        @Schema(description = "Thông tin chủ sở hữu")
        public static class OwnerInfo {
            private Long id;
            private String name;
            private String avatarUrl;

            public OwnerInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public String getAvatarUrl() { return avatarUrl; }
            public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
        }

        @Schema(description = "Thông tin bài hát trong playlist")
        public static class MusicInfo {
            private Long id;
            private String title;
            private ArtistInfo artist;
            private Integer duration;
            private Integer trackNumber;

            public MusicInfo() {}

            @Schema(description = "Thông tin nghệ sĩ")
            public static class ArtistInfo {
                private Long id;
                private String stageName;

                public ArtistInfo() {}

                public Long getId() { return id; }
                public void setId(Long id) { this.id = id; }
                public String getStageName() { return stageName; }
                public void setStageName(String stageName) { this.stageName = stageName; }
            }

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public ArtistInfo getArtist() { return artist; }
            public void setArtist(ArtistInfo artist) { this.artist = artist; }
            public Integer getDuration() { return duration; }
            public void setDuration(Integer duration) { this.duration = duration; }
            public Integer getTrackNumber() { return trackNumber; }
            public void setTrackNumber(Integer trackNumber) { this.trackNumber = trackNumber; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public OwnerInfo getOwner() { return owner; }
        public void setOwner(OwnerInfo owner) { this.owner = owner; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Boolean getIsPublic() { return isPublic; }
        public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
        public LocalDateTime getCreationDate() { return creationDate; }
        public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
        public List<MusicInfo> getTracks() { return tracks; }
        public void setTracks(List<MusicInfo> tracks) { this.tracks = tracks; }
        public Integer getTrackCount() { return trackCount; }
        public void setTrackCount(Integer trackCount) { this.trackCount = trackCount; }
        public Integer getTotalDuration() { return totalDuration; }
        public void setTotalDuration(Integer totalDuration) { this.totalDuration = totalDuration; }
        public Integer getFollowerCount() { return followerCount; }
        public void setFollowerCount(Integer followerCount) { this.followerCount = followerCount; }
    }

    @Schema(description = "Yêu cầu thêm bài hát vào playlist")
    public static class AddTracksRequest {
        @Schema(description = "Danh sách ID bài hát", required = true)
        private List<Long> trackIds;

        public AddTracksRequest() {}

        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }

    @Schema(description = "Yêu cầu xóa bài hát khỏi playlist")
    public static class RemoveTracksRequest {
        @Schema(description = "Danh sách ID bài hát", required = true)
        private List<Long> trackIds;

        public RemoveTracksRequest() {}

        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }

    @Schema(description = "Yêu cầu sắp xếp lại playlist")
    public static class ReorderTracksRequest {
        @Schema(description = "Danh sách ID bài hát theo thứ tự mới", required = true)
        private List<Long> trackIds;

        public ReorderTracksRequest() {}

        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }
}