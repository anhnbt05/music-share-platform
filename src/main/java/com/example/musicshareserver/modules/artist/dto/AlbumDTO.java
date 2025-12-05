// File: com/example/musicshareserver/modules/artist/dto/AlbumDTO.java
package com.example.musicshareserver.modules.artist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class AlbumDTO {

    @Schema(description = "Yêu cầu tạo album")
    public static class CreateAlbumRequest {
        @Schema(description = "Tiêu đề album", required = true)
        @NotBlank(message = "Tiêu đề album là bắt buộc")
        private String title;

        @Schema(description = "Mô tả album")
        private String description;

        @Schema(description = "URL ảnh bìa")
        private String coverUrl;

        @Schema(description = "Danh sách ID bài hát")
        private List<Long> trackIds;

        public CreateAlbumRequest() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCoverUrl() { return coverUrl; }
        public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }

    @Schema(description = "Yêu cầu cập nhật album")
    public static class UpdateAlbumRequest {
        @Schema(description = "Tiêu đề album")
        private String title;

        @Schema(description = "Mô tả album")
        private String description;

        @Schema(description = "URL ảnh bìa")
        private String coverUrl;

        public UpdateAlbumRequest() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCoverUrl() { return coverUrl; }
        public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
    }

    @Schema(description = "Phản hồi thông tin album")
    public static class AlbumResponse {
        @Schema(description = "ID album")
        private Long id;

        @Schema(description = "Nghệ sĩ")
        private ArtistInfo artist;

        @Schema(description = "Tiêu đề")
        private String title;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "URL ảnh bìa")
        private String coverUrl;

        @Schema(description = "Ngày tạo")
        private LocalDateTime createdAt;

        @Schema(description = "Danh sách bài hát")
        private List<MusicInfo> tracks;

        @Schema(description = "Tổng số bài hát")
        private Integer trackCount;

        @Schema(description = "Tổng thời lượng (giây)")
        private Integer totalDuration;

        @Schema(description = "Tổng lượt nghe")
        private Long totalListens;

        public AlbumResponse() {}

        @Schema(description = "Thông tin nghệ sĩ")
        public static class ArtistInfo {
            private Long id;
            private String stageName;
            private String photoUrl;

            public ArtistInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getStageName() { return stageName; }
            public void setStageName(String stageName) { this.stageName = stageName; }
            public String getPhotoUrl() { return photoUrl; }
            public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
        }

        @Schema(description = "Thông tin bài hát trong album")
        public static class MusicInfo {
            private Long id;
            private String title;
            private Integer duration;
            private Integer trackNumber;
            private Integer listens;
            private Integer likes;

            public MusicInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public Integer getDuration() { return duration; }
            public void setDuration(Integer duration) { this.duration = duration; }
            public Integer getTrackNumber() { return trackNumber; }
            public void setTrackNumber(Integer trackNumber) { this.trackNumber = trackNumber; }
            public Integer getListens() { return listens; }
            public void setListens(Integer listens) { this.listens = listens; }
            public Integer getLikes() { return likes; }
            public void setLikes(Integer likes) { this.likes = likes; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public ArtistInfo getArtist() { return artist; }
        public void setArtist(ArtistInfo artist) { this.artist = artist; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getCoverUrl() { return coverUrl; }
        public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
        public List<MusicInfo> getTracks() { return tracks; }
        public void setTracks(List<MusicInfo> tracks) { this.tracks = tracks; }
        public Integer getTrackCount() { return trackCount; }
        public void setTrackCount(Integer trackCount) { this.trackCount = trackCount; }
        public Integer getTotalDuration() { return totalDuration; }
        public void setTotalDuration(Integer totalDuration) { this.totalDuration = totalDuration; }
        public Long getTotalListens() { return totalListens; }
        public void setTotalListens(Long totalListens) { this.totalListens = totalListens; }
    }

    @Schema(description = "Yêu cầu thêm bài hát vào album")
    public static class AddTracksRequest {
        @Schema(description = "Danh sách ID bài hát", required = true)
        private List<Long> trackIds;

        public AddTracksRequest() {}

        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }

    @Schema(description = "Yêu cầu xóa bài hát khỏi album")
    public static class RemoveTracksRequest {
        @Schema(description = "Danh sách ID bài hát", required = true)
        private List<Long> trackIds;

        public RemoveTracksRequest() {}

        public List<Long> getTrackIds() { return trackIds; }
        public void setTrackIds(List<Long> trackIds) { this.trackIds = trackIds; }
    }
}