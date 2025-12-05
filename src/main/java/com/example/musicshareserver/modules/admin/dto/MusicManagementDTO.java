// File: com/example/musicshareserver/modules/admin/dto/MusicManagementDTO.java
package com.example.musicshareserver.modules.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class MusicManagementDTO {

    @Schema(description = "Phản hồi quản lý bài hát")
    public static class MusicManagementResponse {
        @Schema(description = "ID bài hát")
        private Long id;

        @Schema(description = "Tiêu đề bài hát")
        private String title;

        @Schema(description = "Nghệ sĩ")
        private ArtistInfo artist;

        @Schema(description = "Thể loại")
        private String genre;

        @Schema(description = "Ngày upload")
        private LocalDateTime uploadedAt;

        @Schema(description = "Số lượt nghe")
        private Integer listens;

        @Schema(description = "Số lượt like")
        private Integer likes;

        @Schema(description = "Số lượt share")
        private Integer shares;

        @Schema(description = "Số báo cáo")
        private Integer reportCount;

        @Schema(description = "Trạng thái")
        private String status; // ACTIVE, FLAGGED, DELETED

        public MusicManagementResponse() {}

        @Schema(description = "Thông tin nghệ sĩ")
        public static class ArtistInfo {
            private Long id;
            private String stageName;
            private String email;

            public ArtistInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getStageName() { return stageName; }
            public void setStageName(String stageName) { this.stageName = stageName; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
        }

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public ArtistInfo getArtist() { return artist; }
        public void setArtist(ArtistInfo artist) { this.artist = artist; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
        public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
        public Integer getListens() { return listens; }
        public void setListens(Integer listens) { this.listens = listens; }
        public Integer getLikes() { return likes; }
        public void setLikes(Integer likes) { this.likes = likes; }
        public Integer getShares() { return shares; }
        public void setShares(Integer shares) { this.shares = shares; }
        public Integer getReportCount() { return reportCount; }
        public void setReportCount(Integer reportCount) { this.reportCount = reportCount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    @Schema(description = "Yêu cầu tìm kiếm bài hát để quản lý")
    public static class SearchMusicRequest {
        @Schema(description = "Từ khóa tìm kiếm")
        private String query;

        @Schema(description = "Thể loại")
        private String genre;

        @Schema(description = "ID nghệ sĩ")
        private Long artistId;

        @Schema(description = "Ngày bắt đầu")
        private LocalDateTime startDate;

        @Schema(description = "Ngày kết thúc")
        private LocalDateTime endDate;

        @Schema(description = "Số báo cáo tối thiểu")
        private Integer minReports;

        public SearchMusicRequest() {}

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public Long getArtistId() { return artistId; }
        public void setArtistId(Long artistId) { this.artistId = artistId; }
        public LocalDateTime getStartDate() { return startDate; }
        public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
        public LocalDateTime getEndDate() { return endDate; }
        public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
        public Integer getMinReports() { return minReports; }
        public void setMinReports(Integer minReports) { this.minReports = minReports; }
    }
}