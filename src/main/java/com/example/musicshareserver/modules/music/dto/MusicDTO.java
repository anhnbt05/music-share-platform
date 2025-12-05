// File: com/example/musicshareserver/modules/music/dto/MusicDTO.java
package com.example.musicshareserver.modules.music.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class MusicDTO {

    @Schema(description = "Yêu cầu upload bài hát")
    public static class UploadMusicRequest {
        @Schema(description = "Tiêu đề bài hát", required = true)
        @NotBlank(message = "Tiêu đề là bắt buộc")
        private String title;

        @Schema(description = "Thể loại", example = "Pop, Rock, Hip-Hop")
        private String genre;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "ID album (nếu upload vào album)")
        private Long albumId;

        public UploadMusicRequest() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getAlbumId() { return albumId; }
        public void setAlbumId(Long albumId) { this.albumId = albumId; }
    }

    @Schema(description = "Phản hồi thông tin bài hát")
    public static class MusicResponse {
        @Schema(description = "ID bài hát")
        private Long id;

        @Schema(description = "Tiêu đề")
        private String title;

        @Schema(description = "Nghệ sĩ")
        private ArtistInfo artist;

        @Schema(description = "Thể loại")
        private String genre;

        @Schema(description = "Mô tả")
        private String description;

        @Schema(description = "URL file nhạc")
        private String fileUrl;

        @Schema(description = "Thời lượng (giây)")
        private Integer duration;

        @Schema(description = "Kích thước file (bytes)")
        private Long fileSize;

        @Schema(description = "Ngày upload")
        private LocalDateTime uploadedAt;

        @Schema(description = "Số lượt nghe")
        private Integer listens;

        @Schema(description = "Số lượt like")
        private Integer likes;

        @Schema(description = "Số lượt share")
        private Integer shares;

        @Schema(description = "Đã like chưa")
        private Boolean isLiked;

        @Schema(description = "Có trong playlist nào không")
        private Boolean inPlaylist;

        @Schema(description = "Album chứa bài hát (nếu có)")
        private AlbumInfo album;

        public MusicResponse() {}

        @Schema(description = "Thông tin nghệ sĩ")
        public static class ArtistInfo {
            private Long id;
            private String stageName;
            private String photoUrl;
            private Boolean isFollowing;

            public ArtistInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getStageName() { return stageName; }
            public void setStageName(String stageName) { this.stageName = stageName; }
            public String getPhotoUrl() { return photoUrl; }
            public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
            public Boolean getIsFollowing() { return isFollowing; }
            public void setIsFollowing(Boolean isFollowing) { this.isFollowing = isFollowing; }
        }

        @Schema(description = "Thông tin album")
        public static class AlbumInfo {
            private Long id;
            private String title;
            private String coverUrl;

            public AlbumInfo() {}

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public String getCoverUrl() { return coverUrl; }
            public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
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
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getFileUrl() { return fileUrl; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
        public Integer getDuration() { return duration; }
        public void setDuration(Integer duration) { this.duration = duration; }
        public Long getFileSize() { return fileSize; }
        public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
        public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
        public Integer getListens() { return listens; }
        public void setListens(Integer listens) { this.listens = listens; }
        public Integer getLikes() { return likes; }
        public void setLikes(Integer likes) { this.likes = likes; }
        public Integer getShares() { return shares; }
        public void setShares(Integer shares) { this.shares = shares; }
        public Boolean getIsLiked() { return isLiked; }
        public void setIsLiked(Boolean isLiked) { this.isLiked = isLiked; }
        public Boolean getInPlaylist() { return inPlaylist; }
        public void setInPlaylist(Boolean inPlaylist) { this.inPlaylist = inPlaylist; }
        public AlbumInfo getAlbum() { return album; }
        public void setAlbum(AlbumInfo album) { this.album = album; }
    }

    @Schema(description = "Yêu cầu tìm kiếm bài hát")
    public static class SearchMusicRequest {
        @Schema(description = "Từ khóa tìm kiếm")
        private String query;

        @Schema(description = "Thể loại")
        private String genre;

        @Schema(description = "ID nghệ sĩ")
        private Long artistId;

        @Schema(description = "Sắp xếp theo", example = "NEWEST, POPULAR, TRENDING")
        private String sortBy;

        @Schema(description = "Trang")
        private Integer page = 1;

        @Schema(description = "Số lượng mỗi trang")
        private Integer size = 20;

        public SearchMusicRequest() {}

        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public Long getArtistId() { return artistId; }
        public void setArtistId(Long artistId) { this.artistId = artistId; }
        public String getSortBy() { return sortBy; }
        public void setSortBy(String sortBy) { this.sortBy = sortBy; }
        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        public Integer getSize() { return size; }
        public void setSize(Integer size) { this.size = size; }
    }

    @Schema(description = "Phản hồi tìm kiếm bài hát")
    public static class SearchResponse {
        @Schema(description = "Danh sách bài hát")
        private java.util.List<MusicResponse> musics;

        @Schema(description = "Tổng số kết quả")
        private Long total;

        @Schema(description = "Trang hiện tại")
        private Integer currentPage;

        @Schema(description = "Tổng số trang")
        private Integer totalPages;

        public SearchResponse() {}

        // Getters and Setters
        public java.util.List<MusicResponse> getMusics() { return musics; }
        public void setMusics(java.util.List<MusicResponse> musics) { this.musics = musics; }
        public Long getTotal() { return total; }
        public void setTotal(Long total) { this.total = total; }
        public Integer getCurrentPage() { return currentPage; }
        public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }
        public Integer getTotalPages() { return totalPages; }
        public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    }

    @Schema(description = "Yêu cầu chia sẻ bài hát")
    public static class ShareMusicRequest {
        @Schema(description = "Nền tảng chia sẻ", example = "FACEBOOK, TWITTER, COPY_LINK")
        private String platform;

        @Schema(description = "Thông điệp chia sẻ")
        private String message;

        public ShareMusicRequest() {}

        public String getPlatform() { return platform; }
        public void setPlatform(String platform) { this.platform = platform; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    @Schema(description = "Phản hồi chia sẻ bài hát")
    public static class ShareResponse {
        @Schema(description = "URL chia sẻ")
        private String shareUrl;

        @Schema(description = "Mã nhúng (embed code)")
        private String embedCode;

        @Schema(description = "URL ngắn")
        private String shortUrl;

        public ShareResponse() {}

        public ShareResponse(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        // Getters and Setters
        public String getShareUrl() { return shareUrl; }
        public void setShareUrl(String shareUrl) { this.shareUrl = shareUrl; }
        public String getEmbedCode() { return embedCode; }
        public void setEmbedCode(String embedCode) { this.embedCode = embedCode; }
        public String getShortUrl() { return shortUrl; }
        public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }
    }
}